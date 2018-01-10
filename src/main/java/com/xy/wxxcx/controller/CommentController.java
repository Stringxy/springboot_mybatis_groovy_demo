package com.xy.wxxcx.controller;

import com.xy.wxxcx.common.resp.BaseResp;
import com.xy.wxxcx.entity.Comment;
import com.xy.wxxcx.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

/**
 * @author xy
 * 2018/1/10
 */
@Api(description = "评论管理")
@RestController
@EnableAutoConfiguration
@RequestMapping("comment")
public class CommentController {
    @Autowired
    private CommentService commentService;
    private Logger logger = Logger.getLogger(CommentController.class);
    @PostMapping(value = "/add")
    @ResponseBody
    @ApiOperation(value = "新增评论")
    BaseResp add(@RequestBody Comment comment) {
        BaseResp baseResp = new BaseResp();
        logger.info("------->>新增评论：comment:" + comment.toString());
        try {
            if (commentService.insert(comment)) {
                BaseResp.setResp(true, baseResp);
                return baseResp;
            }
        } catch (Exception e) {
            logger.error("---->>  comment add faild", e);
        }
        BaseResp.setResp(false, baseResp);
        return baseResp;
    }

}
