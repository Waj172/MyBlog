package aojie.love.service.impl;

import aojie.love.domain.entity.Comment;
import aojie.love.domain.entity.User;
import aojie.love.domain.vo.CommentVo;
import aojie.love.domain.vo.PageVo;
import aojie.love.global.Result;
import aojie.love.global.constants.SystemConstants;
import aojie.love.global.enums.AppHttpCodeEnum;
import aojie.love.global.exception.SystemException;
import aojie.love.mapper.CommentMapper;
import aojie.love.service.CommentService;
import aojie.love.service.UserService;
import aojie.love.utils.BeanCopyUtils;
import aojie.love.utils.SecurityUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 评论表(Comment)表服务实现类
 *
 * @author makejava
 * @since 2022-11-24 17:16:49
 */
@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    private UserService userService;

    /**
     *  查询评论
     * @param articleId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public Result getCommentList(Long articleId, Integer pageNum, Integer pageSize) {
        // 查询文章的根评论
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();

        // 根据文章id进行查询
        queryWrapper.eq(Comment::getArticleId,articleId);
        // 根据根评论进行查询
        queryWrapper.eq(Comment::getRootId, SystemConstants.IS_ROOT);

        // 分页查询
        Page<Comment> page = new Page<>(pageNum,pageSize);
        this.page(page,queryWrapper);

        // 页面返回的数据有文章名称，所以封装成一个vo
        // 因为vo中还有两个属性没有赋值，仅仅使用copy方法不行，所以单独写一个私有方法
        List<CommentVo> commentVo = toCommentVoList(page.getRecords());

        // 查询子评论
        for (CommentVo vo : commentVo) {
            List<CommentVo> children = getChildren(vo.getId());
            vo.setChildren(children);
        }

        return Result.okResult(new PageVo(commentVo,page.getTotal()));
    }

    /**
     *  添加评论
     * @param comment
     * @return
     */
    @Override
    public Result addComment(Comment comment) {
        // 进行公共字段的填充（mybatisPlus的handler进行了填充）
        // 执行添加操作
        // 评论内容不能空
        if (!StringUtils.hasText(comment.getContent())){
            throw new SystemException(AppHttpCodeEnum.CONTEXT_NOT_NULL);
        }
        this.save(comment);
        return Result.okResult();
    }

    /**
     *  根据子评论的rootid查询评论
     * @param id
     * @return
     */
    private List<CommentVo> getChildren(Long id) {
        // 构造查询条件
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();

        // 根据rootId查询（）
        queryWrapper.eq(Comment::getRootId,id);
        queryWrapper.orderByAsc(Comment::getCreateTime);

        List<Comment> commentList = this.list(queryWrapper);

        // 封装成vo
        List<CommentVo> commentVos = toCommentVoList(commentList);

        return commentVos;
    }

    /**
     *  给commentVo赋值使用
     * @param list
     * @return
     */
    private List<CommentVo> toCommentVoList(List<Comment> list){
        // 先copy已有的属性
        List<CommentVo> commentVos = BeanCopyUtils.copyBeanList(list, CommentVo.class);

        // 遍历vo集合
        return commentVos.stream().map(commentVo ->{
            // 通过createBy的id查询出评论人的昵称
            User rootUser = userService.getById(commentVo.getCreateBy());
            String rootNickName = rootUser.getNickName();
            commentVo.setUsername(rootNickName);

            // 通过toCommentId查询用户昵称（评论别人评论的人姓名）
            // 如果是根评论，toCommentId为-1，所有要判断
            if (commentVo.getToCommentUserId() != -1){
                User noRootUser = userService.getById(commentVo.getToCommentUserId());
                String noRootNickName = noRootUser.getNickName();
                commentVo.setToCommentUserName(noRootNickName);
            }
            return commentVo;
        }).collect(Collectors.toList());
    }
}

