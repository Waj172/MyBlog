package aojie.love.service.impl;

import aojie.love.global.Result;
import aojie.love.domain.entity.LoginUser;
import aojie.love.domain.entity.User;
import aojie.love.domain.vo.BlogUserLoginVo;
import aojie.love.domain.vo.UserInfoVo;
import aojie.love.service.BlogLoginService;
import aojie.love.utils.BeanCopyUtils;
import aojie.love.utils.JwtUtil;
import aojie.love.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;


/**
 * @author: JieGe
 * @time:
 * @function:
 */
@Service
public class BlogLoginServiceImpl implements BlogLoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    /**
     *  用户登录
     * @param user
     * @return
     */
    @Override
    public Result login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //判断是否认证通过
        if (Objects.isNull(authenticate)){
            throw new RuntimeException("用户名或密码错误");
        }
        // 获取userid生成token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String token = JwtUtil.createJWT(userId);
        // 把用户信息存入redis
        redisCache.setCacheObject("blogLogin:" + userId, loginUser);

        // 把token和userInfo封装，返回
        // 把loginUser转换成UserInfoVo
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVo.class);
        BlogUserLoginVo loginVo = new BlogUserLoginVo(token,userInfoVo);

        return Result.okResult(loginVo);
    }

    @Override
    public Result logout() {
        // 获取token，解析，获取userId
        // 先从这里面获取
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getUser().getId();

        // 删除redis的用户信息
        redisCache.deleteObject("blogLogin:" + userId);

        return Result.okResult();
    }
}
