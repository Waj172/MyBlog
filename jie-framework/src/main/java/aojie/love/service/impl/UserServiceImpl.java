package aojie.love.service.impl;

import aojie.love.domain.entity.User;
import aojie.love.mapper.UserMapper;
import aojie.love.service.UserService;
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

}

