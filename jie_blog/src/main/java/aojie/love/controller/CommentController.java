package aojie.love.controller;

import aojie.love.domain.entity.Comment;
import aojie.love.global.Result;
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

    @GetMapping("/commentList")
    public Result commentList(Long articleId ,Integer pageNum ,Integer pageSize){
        return commentService.getCommentList(articleId,pageNum,pageSize);
    }

    @PostMapping
    public Result addComment(@RequestBody Comment comment){
        return commentService.addComment(comment);
    }
}
