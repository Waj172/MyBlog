package aojie.love.service.impl;

import aojie.love.domain.entity.User;
import aojie.love.domain.vo.UserInfoVo;
import aojie.love.global.Result;
import aojie.love.mapper.UserMapper;
import aojie.love.service.UserService;
import aojie.love.utils.BeanCopyUtils;
import aojie.love.utils.SecurityUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 用户表(User)表服务实现类
 *
 * @author makejava
 * @since 2022-11-27 11:16:50
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    /**
     *  获取用户信息
     * @return
     */
    @Override
    public Result getUserInfo() {
        // 从token中获取用户id
        Long userId = SecurityUtils.getUserId();
        // 根据用户id查询用户信息
        User user = this.getById(userId);
        // 封装成userInfoVo
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(user, UserInfoVo.class);

        return Result.okResult(userInfoVo);
    }

    @Override
    public Result updateUserInfo(User user) {
        this.updateById(user);
        return Result.okResult();
    }
}

