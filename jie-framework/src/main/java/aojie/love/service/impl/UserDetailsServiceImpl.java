package aojie.love.service.impl;

import aojie.love.domain.entity.LoginUser;
import aojie.love.domain.entity.User;
import aojie.love.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author: JieGe
 * @time:
 * @function:
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 根据用户名查询用户信息
        LambdaQueryWrapper<User> userWrapper = new LambdaQueryWrapper<>();
        userWrapper.eq(User::getUserName,username);
        User user = userMapper.selectOne(userWrapper);
        // 判断是否查到用户，如果没查到直接抛出异常
        if (Objects.isNull(user)){
            throw new RuntimeException("用户不存在");
        }
        // 返回用户信息
        // TODO 查询用户权限信息。
        return new LoginUser(user);
    }
}
