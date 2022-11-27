package aojie.love;

import aojie.love.domain.entity.Article;
import aojie.love.domain.vo.HotArticleVo;
import aojie.love.utils.BeanCopyUtils;

/**
 * @author: JieGe
 * @time:
 * @function:
 */

public class beanTest {
    public static void main(String[] args) {
        Article article = new Article();
        article.setId(24L);
        article.setTitle("林三");
        article.setViewCount(15L);


        HotArticleVo hotArticleVo = BeanCopyUtils.copyBean(article, HotArticleVo.class);
        System.out.println(hotArticleVo);

    }
}
