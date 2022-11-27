package aojie.love.filter;

import aojie.love.global.Result;
import aojie.love.global.enums.AppHttpCodeEnum;
import aojie.love.domain.entity.LoginUser;
import aojie.love.utils.JwtUtil;
import aojie.love.utils.RedisCache;
import aojie.love.utils.WebUtils;
import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author: JieGe
 * @time:
 * @function:
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 获取请求头中的token
        String token = request.getHeader("token");
        // 判断接口是否需要登录（请求头中没有token）
        if (!StringUtils.hasText(token)){
            // 如果没有token，直接放行
            filterChain.doFilter(request, response);
            return;
        }
        // 解析获取的userid
        Claims claims = null;
        try {
            claims = JwtUtil.parseJWT(token);
        } catch (Exception e) {
            e.printStackTrace();
            // 解析出现异常，token超时，或者token非法（人为修改）
            // 和前端约定好的，响应状态码为401
            // 我们通过自定义类返回给前端，也可以直接返回
            Result result = Result.errorResult(AppHttpCodeEnum.NEED_LOGIN);
            WebUtils.renderString(response, JSON.toJSONString(result));
            return;
        }
        String userId = claims.getSubject();
        // 从redis中获取用户信息
        LoginUser loginUser = redisCache.getCacheObject("blogLogin:" + userId);
        // 判断是否有值
        if (Objects.isNull(loginUser)){
            // 登录过期
            Result result = Result.errorResult(AppHttpCodeEnum.NEED_LOGIN);
            WebUtils.renderString(response, JSON.toJSONString(result));
            return;
        }

        // 存入securityContextHolder
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginUser,null,null);

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        // 登录状态，token合法，验证完毕，放行
        filterChain.doFilter(request, response);

        // 最后需要把过滤器添加到配置中，见config中的securityConfig类
    }
}
