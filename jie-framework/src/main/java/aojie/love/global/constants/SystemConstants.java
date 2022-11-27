package aojie.love.global.constants;

/**
 * @author: JieGe
 * @time:
 * @function:
 */
public class SystemConstants {
    /**
     * 文章草稿
     */
    public static final int ARTICLE_STATUS_DRAFT = 1;
    /**
     * 文章是正常发布状态
     */
    public static final int ARTICLE_STATUS_NORMAL = 0;
    /**
     *  分类禁用（给分类下没有文章）
     */
    public static final String CATEGORY_STATUS_DISABLE = "1";
    /**
     *  分类正常
     */
    public static final String CATEGORY_STATUS_NORMAL = "0";
    /**
     * 友链审核通过
     */
    public static final String LINK_STATUS_NORMAL = "0";
    /**
     *  友链审核未通过
     */
    public static final String LINK_STATUS_DISABLE = "1";
    /**
     *  友链未审核
     */
    public static final String LINK_STATUS_NO_PROCESS = "2";
    /**
     *  文章评论是根评论
     */
    public static final String IS_ROOT = "-1";
    /**
     * 文章评论
     */
    public static final String ARTICLE_COMMENT = "0";
    /**
     * 友链评论
     */
    public static final String LINK_COMMENT = "1";

}
