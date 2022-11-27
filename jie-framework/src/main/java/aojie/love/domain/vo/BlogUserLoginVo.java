package aojie.love.domain.vo;

import aojie.love.domain.entity.LoginUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: JieGe
 * @time:
 * @function: 用于返回token和用户信息，为了和前端保持一致
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogUserLoginVo {

    /**
     *  token信息
     */
    private String token;

    /**
     *  返回给前端的用户信息
     */
    private UserInfoVo userInfo;
}
