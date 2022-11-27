package aojie.love.utils;

import aojie.love.domain.entity.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author: JieGe
 * @time:
 * @function: 从token中获取一些信息
 */
public class SecurityUtils {

    /**
     *  获取Authentication
     * @return
     */
    public static Authentication getAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     *  获取用户
     * @return
     */
    public static LoginUser getLoginUser(){
        return ((LoginUser) getAuthentication().getPrincipal());
    }

    /**
     *  判断是否是管理员
     * @return
     */
    public static boolean isAdmin(){
        Long id = getLoginUser().getUser().getId();
        return id != null && 1L == id;
    }

    /**
     *  获取用户id
     * @return
     */
    public static Long getUserId(){
        return getLoginUser().getUser().getId();
    }
}
