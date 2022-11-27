package aojie.love.global.exception;

import aojie.love.global.enums.AppHttpCodeEnum;
import lombok.Data;

/**
 * @author: JieGe
 * @time:
 * @function: 全局处理异常
 */
public class SystemException extends RuntimeException {

    private int code;

    private String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public SystemException(AppHttpCodeEnum httpCodeEnum) {
        super(httpCodeEnum.getMsg());
        this.code = httpCodeEnum.getCode();
        this.msg = httpCodeEnum.getMsg();
    }

}
