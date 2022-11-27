package aojie.love.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: JieGe
 * @time:
 * @function:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotArticleVo {

    private Long id;

    private String title;

    private Long viewCount;
}
