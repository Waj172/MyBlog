package aojie.love.controller;

import aojie.love.global.Result;
import aojie.love.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: JieGe
 * @time:
 * @function: 友联相关接口
 */
@RestController
@RequestMapping("/link")
public class LinkController {

    @Autowired
    private LinkService linkService;

    /**
     *  查询友链信息
     *    1、审核通过的，即status为0的
     * @return
     */
    @GetMapping("/getAllLink")
    public Result allLink(){
        return linkService.getAllLink();
    }

}
