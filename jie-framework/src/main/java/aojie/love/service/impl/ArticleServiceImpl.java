package aojie.love.service.impl;

import aojie.love.global.Result;
import aojie.love.global.constants.SystemConstants;
import aojie.love.domain.entity.Article;
import aojie.love.domain.entity.Category;
import aojie.love.domain.vo.ArticleDetailVo;
import aojie.love.domain.vo.ArticleListVo;
import aojie.love.domain.vo.HotArticleVo;
import aojie.love.domain.vo.PageVo;
import aojie.love.mapper.ArticleMapper;
import aojie.love.service.ArticleService;
import aojie.love.service.CategoryService;
import aojie.love.utils.BeanCopyUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * @author: JieGe
 * @time:
 * @function:
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper,Article> implements ArticleService {

    @Autowired
    private CategoryService categoryService;
    /**
     *  查询热门文章
     * @return
     */
    @Override
    public Result hotArticleList() {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        // 查询条件，正式文章，判断statue状态，0 为发布， 1 为草稿
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        // 按照浏览量进行排序
        queryWrapper.orderByDesc(Article::getViewCount);
        // 最多只能查询10条,这里只查询第一页的十条数据
        Page<Article> pageInfo = new Page<>(1,10);

        this.page(pageInfo, queryWrapper);

        List<Article> articles = pageInfo.getRecords();

//        List<HotArticleVo> hotArticleVos = this.copy(articles);

        List<HotArticleVo> hotArticleVos = BeanCopyUtils.copyBeanList(articles,HotArticleVo.class);
        return Result.okResult(hotArticleVos);
    }

    /**
     *  查询分页数据
     * @param pageNum
     * @param pageSize
     * @param categoryId
     * @return
     */
    @Override
    public Result getArticleList(int pageNum, int pageSize, Long categoryId) {
        // 构建查询条件
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        // 判断是否传入categoryId或小于0，来判断是都查询categoryId
        queryWrapper.eq(Objects.nonNull(categoryId)&&categoryId>0, Article::getCategoryId,categoryId);
        // 展示正常发布的文章
        queryWrapper.eq(Article::getStatus,SystemConstants.ARTICLE_STATUS_NORMAL);
        // 对isTop进行降序排列
        queryWrapper.orderByDesc(Article::getIsTop);
        // 分页查询
        Page<Article> articlePage = new Page<>(pageNum,pageSize);
        this.page(articlePage, queryWrapper);

        // 查询根据文章表中的categoryId，在分类表中查询categoryName
        List<Article> articles = articlePage.getRecords();
        articles = articles.stream().map((item)->{
            Long id = item.getCategoryId();
            Category category = categoryService.getById(id);
            item.setCategoryName(category.getName());
            return item;
        }).collect(Collectors.toList());

        // 封装成vo(此时分类名称没有赋值)
        List<ArticleListVo> articleListVos = BeanCopyUtils.copyBeanList(articles, ArticleListVo.class);

        // 前端接收的对象需要使用这个封装一下
        PageVo pageVo = new PageVo(articleListVos,articlePage.getTotal());

        return Result.okResult(pageVo);
    }

    /**
     *  查询文章详情
     * @param id
     * @return
     */
    @Override
    public Result getArticleDetail(Long id) {
        // 根据id查询文章详情
        Article article = this.getById(id);
        // 转换为vo
        ArticleDetailVo articleDetailVo = BeanCopyUtils.copyBean(article, ArticleDetailVo.class);
        // 根据分类id查询分类名称
        Category category = categoryService.getById(article.getCategoryId());
        if (category != null) {
            articleDetailVo.setCategoryName(category.getName());
        }
        // 封装返回
        return Result.okResult(articleDetailVo);
    }

}
