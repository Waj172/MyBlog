package aojie.love.service.impl;

import aojie.love.global.Result;
import aojie.love.global.constants.SystemConstants;
import aojie.love.domain.entity.Article;
import aojie.love.domain.entity.Category;
import aojie.love.domain.vo.CategoryVo;
import aojie.love.mapper.CategoryMapper;
import aojie.love.service.ArticleService;
import aojie.love.service.CategoryService;
import aojie.love.utils.BeanCopyUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 分类表(Category)表服务实现类
 *
 * @author makejava
 * @since 2022-11-19 17:03:19
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private ArticleService articleService;

    @Override
    public Result getCategoryList() {
        // 使用分步查询 ，在article表中，查询已经发布的文章的分类id（需要去重，可能一个分类下有许多文章）
        LambdaQueryWrapper<Article> articleWrapper = new LambdaQueryWrapper<>();
        articleWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        List<Article> articles = articleService.list(articleWrapper);
        // 去重,转换成set集合，直接去重
        Set<Long> categoryIds = articles.stream()
                .map(Article::getCategoryId)
                .collect(Collectors.toSet());

        // 根据分类id查询分类表，查询出分类数据（注意，分类状态必须可用）
        List<Category> categories = this.listByIds(categoryIds);
        // 只有正常状态的可以加入到集合中
        categories = categories.stream()
                .filter(category -> SystemConstants.CATEGORY_STATUS_NORMAL.equals(category.getStatus()))
                .collect(Collectors.toList());

        // 封装vo
        List<CategoryVo> categoryVos = BeanCopyUtils.copyBeanList(categories, CategoryVo.class);
        return Result.okResult(categoryVos);
    }
}

