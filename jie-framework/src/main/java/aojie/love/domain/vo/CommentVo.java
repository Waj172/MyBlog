package aojie.love.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author: JieGe
 * @time:
 * @function: 用于向页面展示的评论
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentVo {

    private Long id;
    /**
     * 文章id
     */
    private Long articleId;
    /**
     * 根评论id
     */
    private Long rootId;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 所回复的目标评论的userid
     */
    private Long toCommentUserId;
    /**
     * 所回复的目标评论的userName
     */
    private String toCommentUserName;
    /**
     *  评论人的username
     */
    private String username;
    /**
     * 回复目标评论id
     */
    private Long toCommentId;

    private Long createBy;

    private Date createTime;
    /**
     * 子品论信息
     */
    private List<CommentVo> children;

}
