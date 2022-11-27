package aojie.love.controller;

import aojie.love.global.Result;
import aojie.love.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: JieGe
 * @time:
 * @function:
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     *  获取热门文章：
     *      1、首先需要判断status状态，是否发布（是否删除，在配置文件中已经定义）
     *      2、根据浏览量倒序排列
     *      3、取前十条数据即可
     *      4、我们只需要展示id，title，viewCount，所以引入vo对象
     * @return
     */
    @GetMapping("/hotArticleList")
    public Result hotArticleList(){
        return articleService.hotArticleList();
    }

    /**
     *  获取分页文章数据，特殊的是，携带了一个分类id，用于显示每个不同类型的文章数据
     *      1、查询正式发布的文章
     *      2、置顶文章显示在最前面
     * @param pageNum
     * @param pageSize
     * @param categoryId
     * @return
     */
    @GetMapping("/articleList")
    public Result articleList(int pageNum ,int pageSize,Long categoryId){
        return articleService.getArticleList(pageNum,pageSize,categoryId);
    }

    /**
     *  查询具体的文章详情，用户点击时，通过文章id，来获取到文章的详情
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result articleDetail(@PathVariable("id") Long id){
        return articleService.getArticleDetail(id);
    }
}
