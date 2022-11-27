package aojie.love.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author: JieGe
 * @time:
 * @function:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LinkVo {
    private Long id;

    private String name;

    private String logo;

    private String description;
    /**
     * 网站地址
     */
    private String address;
}