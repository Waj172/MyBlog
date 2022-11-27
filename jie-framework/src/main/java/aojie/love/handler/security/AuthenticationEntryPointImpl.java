package aojie.love.handler.security;

import aojie.love.global.Result;
import aojie.love.global.enums.AppHttpCodeEnum;
import aojie.love.utils.WebUtils;
import com.alibaba.fastjson.JSON;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: JieGe
 * @time:
 * @function: 解决token过时(认证失败)，或非法时所返回给前端的信息，
 *                      这些都是security给我们返回的，我们希望返回自己定义的信息
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        // 打印异常信息，方便观察
        e.printStackTrace();

        // 根据不同的异常，来返回不同的异常信息
        // 用户名或密码错误
        Result result = null;
        if (e instanceof InsufficientAuthenticationException){
            // 自定义响应信息
            result = Result.errorResult(AppHttpCodeEnum.LOGIN_ERROR.getCode(),e.getMessage());
        }else if (e instanceof BadCredentialsException){
            // 自定义响应信息(需要登录)
            result = Result.errorResult(AppHttpCodeEnum.LOGIN_ERROR);
        }else {
            result = Result.errorResult(AppHttpCodeEnum.SYSTEM_ERROR.getCode(),"认证或授权失败");
        }
        // 响应给前端
        WebUtils.renderString(response, JSON.toJSONString(result));
    }
}
