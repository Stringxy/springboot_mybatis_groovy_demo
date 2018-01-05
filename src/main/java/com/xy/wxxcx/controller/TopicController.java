package com.xy.wxxcx.controller;

import com.xy.wxxcx.common.resp.BaseResp;
import com.xy.wxxcx.entity.Topic;
import com.xy.wxxcx.service.TopicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author xy
 */
@Api(description = "话题管理")
@RestController
@EnableAutoConfiguration
@RequestMapping("topic")
public class TopicController {
    @Autowired
    private TopicService topicService;
    private Logger logger = Logger.getLogger(TopicController.class);
    @GetMapping(value = "/getAll/{pageNo}/{pageSize}")
    @ResponseBody
    @ApiOperation(value = "分页全查")
    BaseResp getAll(@PathVariable int pageNo,@PathVariable int pageSize) {
        BaseResp baseResp = new BaseResp();
        logger.info("------->>分页全查：topic:" + pageNo+"/"+pageSize);
        try {
            List<Map<String,Object>> topics = topicService.findAll(pageNo,pageSize);
            BaseResp.setResp(true, baseResp);
            baseResp.setDetail(topics);
            return baseResp;
        } catch (Exception e) {
            logger.error("---->>  topic getAll faild", e);
            BaseResp.setResp(false, baseResp);
            return baseResp;
        }
    }

    @GetMapping(value = "/get/{id}")
    @ResponseBody
    @ApiOperation(value = "根据id查询")
    BaseResp get(@PathVariable int id) {
        BaseResp baseResp = new BaseResp();
        logger.info("------->>根据id查询：topic:" + id);
        try {
            Map<String,Object> topic = topicService.findById(id);
            if(null!=topic) {
                BaseResp.setResp(true, baseResp);
                baseResp.setDetail(topic);
                return baseResp;
            }
        } catch (Exception e) {
            logger.error("---->>  topic getAll faild", e);
        }
        BaseResp.setResp(false, baseResp);
        return baseResp;
    }

    @PostMapping(value = "/add")
    @ResponseBody
    @ApiOperation(value = "新增话题")
    BaseResp add(@RequestBody Topic topic) {
        BaseResp baseResp = new BaseResp();
        logger.info("------->>新增话题：topic:" + topic.toString());
        try {
            if(topicService.insert(topic)){
                BaseResp.setResp(true, baseResp);
                return baseResp;
            }
        } catch (Exception e) {
            logger.error("---->>  topic add faild", e);
        }
        BaseResp.setResp(false, baseResp);
        return baseResp;
    }
}
