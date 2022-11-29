package aojie.love.service;

import aojie.love.domain.entity.User;
import aojie.love.global.Result;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * 用户表(User)表服务接口
 *
 * @author makejava
 * @since 2022-11-27 11:16:50
 */
public interface UserService extends IService<User> {

    /**
     *  获取用户信息
     * @return
     */
    Result getUserInfo();

    /**
     *  修改用户信息
     * @param user
     * @return
     */
    Result updateUserInfo(User user);
}
