package aojie.love.service;

import aojie.love.domain.entity.Comment;
import aojie.love.global.Result;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * 评论表(Comment)表服务接口
 *
 * @author makejava
 * @since 2022-11-24 17:16:49
 */
public interface CommentService extends IService<Comment> {

    /**
     *  根据文章id，获取文章评论
     * @param commentType
     * @param pageNum
     * @param articleId
     * @param pageSize
     * @return
     */
    Result getCommentList(String commentType, Long articleId, Integer pageNum, Integer pageSize);

    /**
     *  添加评论
     * @param comment
     * @return
     */
    Result addComment(Comment comment);
}
