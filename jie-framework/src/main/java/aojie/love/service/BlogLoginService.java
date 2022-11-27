package aojie.love.service;

import aojie.love.global.Result;
import aojie.love.domain.entity.User;

/**
 * @author: JieGe
 * @time:
 * @function:
 */
public interface BlogLoginService{
    /**
     *  实现用户登录
     * @param user
     * @return
     */
    Result login(User user);

    /**
     *  实现用户退出
     * @return
     */
    Result logout();
}
