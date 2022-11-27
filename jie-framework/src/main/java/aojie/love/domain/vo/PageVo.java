package aojie.love.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: JieGe
 * @time:
 * @function: 前端设计的返回的分页数据包含这两个属性
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageVo {
    /**
     * 包含分页显示的数据，即data在这个里面
     */
    private List rows;

    private Long total;
}