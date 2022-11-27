package aojie.love.handler.security;

import aojie.love.global.Result;
import aojie.love.global.enums.AppHttpCodeEnum;
import aojie.love.utils.WebUtils;
import com.alibaba.fastjson.JSON;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: JieGe
 * @time:
 * @function: 授权失败，
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        // 打印异常信息，方便观察
        e.printStackTrace();

        // 自定义响应信息
        Result result = Result.errorResult(AppHttpCodeEnum.NO_OPERATOR_AUTH);

        // 响应给前端
        WebUtils.renderString(response, JSON.toJSONString(result));
    }
}
