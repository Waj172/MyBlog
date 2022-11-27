package aojie.love.controller;

import aojie.love.global.Result;
import aojie.love.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: JieGe
 * @time:
 * @function:
 */
@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 获取分类对象：
     *      1、展示已经发布的文章（在文章表中，需要查两张表）
     *      2、必须是正常状态的分类（status判断 ），如果该分类下没有文章，则不显示
     * @return
     */
    @GetMapping("/getCategoryList")
    public Result categoryList(){
        return categoryService.getCategoryList();
    }
}
