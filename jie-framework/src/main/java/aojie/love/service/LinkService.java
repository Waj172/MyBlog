package aojie.love.service;

import aojie.love.global.Result;
import aojie.love.domain.entity.Link;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * 友链(Link)表服务接口
 *
 * @author makejava
 * @since 2022-11-20 09:34:12
 */
public interface LinkService extends IService<Link> {

    /**
     *  查询友链信息
     * @return
     */
    Result getAllLink();
}
