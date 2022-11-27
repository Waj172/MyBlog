package aojie.love.controller;

import aojie.love.global.Result;
import aojie.love.domain.entity.User;
import aojie.love.global.enums.AppHttpCodeEnum;
import aojie.love.global.exception.SystemException;
import aojie.love.service.BlogLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: JieGe
 * @time:
 * @function:
 */
@RestController
public class BlogLoginController {

    @Autowired
    private BlogLoginService LoginService;

    /**
     *  实现用户登录
     * @param user
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody User user){
        // 后端校验
        if (!StringUtils.hasText(user.getUserName())){
            // 如果用户名不存在
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return LoginService.login(user);
    }

    /**
     *  退出登录，删除redis中的用户信息
     *      1、获取到redis的key（userId）
     *      2、从token的user中获取
     * @return
     */
    @PostMapping("/logout")
    public Result logout(){
        return LoginService.logout();
    }
}
