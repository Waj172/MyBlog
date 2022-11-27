package aojie.love.controller;

import aojie.love.domain.entity.Comment;
import aojie.love.global.Result;
import aojie.love.global.constants.SystemConstants;
import aojie.love.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: JieGe
 * @time:
 * @function:
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     *  文章评论信息
     * @param articleId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/commentList")
    public Result commentList(Long articleId ,Integer pageNum ,Integer pageSize){
        return commentService.getCommentList(SystemConstants.ARTICLE_COMMENT,articleId,pageNum,pageSize);
    }

    /**
     * 添加文章评论
     * @param comment
     * @return
     */
    @PostMapping
    public Result addComment(@RequestBody Comment comment){
        return commentService.addComment(comment);
    }

    @GetMapping("/linkCommentList")
    public Result linkCommentList(Integer pageNum ,Integer pageSize){
        return commentService.getCommentList(SystemConstants.LINK_COMMENT,null,pageNum,pageSize);
    }

}
