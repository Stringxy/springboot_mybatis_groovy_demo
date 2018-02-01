package com.xy.wxxcx.controller;

import com.xy.wxxcx.common.resp.BaseResp;
import com.xy.wxxcx.entity.Question;
import com.xy.wxxcx.service.QuestionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xy
 * 2018/1/8
 */
@Api(description = "试题管理")
@RestController
@EnableAutoConfiguration
@RequestMapping("question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    private Logger logger= LoggerFactory.getLogger(QuestionController.class);
    @GetMapping(value = "/get/{examId}")
    @ResponseBody
    @ApiOperation(value = "获取随机试题")
    BaseResp getAll(@PathVariable int examId) {
        BaseResp baseResp = new BaseResp();
        logger.info("------->>获取随机试题：examId:" + examId);
        try {
            List<Question> topics = questionService.fetchQuestions(examId);
            BaseResp.setResp(true, baseResp);
            baseResp.setDetail(topics);
            return baseResp;
        } catch (Exception e) {
            logger.error("---->>  questions get faild", e);
            BaseResp.setResp(false, baseResp);
            return baseResp;
        }
    }
}
