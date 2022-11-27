package aojie.love.service.impl;

import aojie.love.global.Result;
import aojie.love.global.constants.SystemConstants;
import aojie.love.domain.entity.Link;
import aojie.love.domain.vo.LinkVo;
import aojie.love.mapper.LinkMapper;
import aojie.love.service.LinkService;
import aojie.love.utils.BeanCopyUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 友链(Link)表服务实现类
 *
 * @author makejava
 * @since 2022-11-20 09:34:12
 */
@Service("linkService")
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {

    /**
     *  查询友链信息
     * @return
     */
    @Override
    public Result getAllLink() {
        // 查询所有审核通过的
        LambdaQueryWrapper<Link> linkWrapper = new LambdaQueryWrapper<>();
        linkWrapper.eq(Link::getStatus, SystemConstants.LINK_STATUS_NORMAL);
        List<Link> links = this.list(linkWrapper);

        // 转换成vo
        List<LinkVo> linkVos = BeanCopyUtils.copyBeanList(links, LinkVo.class);
        // 返回
        return Result.okResult(linkVos);
    }
}

