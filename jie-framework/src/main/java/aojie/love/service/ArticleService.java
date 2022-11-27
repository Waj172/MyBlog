package aojie.love.service;

import aojie.love.global.Result;
import aojie.love.domain.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author: JieGe
 * @time:
 * @function:
 */
public interface ArticleService extends IService<Article> {

    /**
     *  获取热门文章
     * @return
     */
    Result hotArticleList();

    /**
     *  获取分页文章数据
     * @param pageNum
     * @param pageSize
     * @param categoryId
     * @return
     */
    Result getArticleList(int pageNum, int pageSize, Long categoryId);

    /**
     *  获取文章详情
     * @param id
     * @return
     */
    Result getArticleDetail(Long id);
}
