package aojie.love.handler.exception;

import aojie.love.global.Result;
import aojie.love.global.enums.AppHttpCodeEnum;
import aojie.love.global.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author: JieGe
 * @time:
 * @function:
 */
/**
 * @RestControllerAdvice = @ControllerAdvice + @ResponseBody
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    // 先处理我们自定义的异常
    @ExceptionHandler(SystemException.class)
    public Result systemExceptionHandler(SystemException e){
        // 打印异常信息
        log.error("出现了异常：{}",e);
        // 从异常对象中获取提示信息封账返回
        return Result.errorResult(e.getCode(), e.getMsg());
    }

    // 处理其他的异常
    @ExceptionHandler(Exception.class)
    public Result ExceptionHandler(Exception e){
        // 打印异常信息
        log.error("出现了异常：{}",e);
        // 从异常对象中获取提示信息封账返回
        return Result.errorResult(AppHttpCodeEnum.SYSTEM_ERROR.getCode(),e.getMessage());
    }
}
