package aojie.love.controller;

import aojie.love.domain.entity.User;
import aojie.love.global.Result;
import aojie.love.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.*;

/**
 * @author: JieGe
 * @time:
 * @function:
 */
@RestController
@RequestMapping(("/user"))
public class UserController {

    @Autowired
    private UserService userService;

    /**
     *  获取用户信息
     * @return
     */
    @GetMapping("/userInfo")
    public Result userInfo(){
        return userService.getUserInfo();
    }


    @PutMapping("/userInfo")
    public Result updateUserInfo(@RequestBody User user){
        return userService.updateUserInfo(user);
    }

}
