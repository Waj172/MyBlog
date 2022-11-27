package aojie.love.service;

import aojie.love.global.Result;
import aojie.love.domain.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * 分类表(Category)表服务接口
 *
 * @author makejava
 * @since 2022-11-19 17:03:18
 */
public interface CategoryService extends IService<Category> {

    /**
     *  获取分类对象：
     * @return
     */
    Result getCategoryList();
}
