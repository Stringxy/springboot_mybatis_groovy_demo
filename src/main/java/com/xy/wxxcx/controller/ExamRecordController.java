package com.xy.wxxcx.controller;

import com.xy.wxxcx.common.resp.BaseResp;
import com.xy.wxxcx.entity.ExamRecord;
import com.xy.wxxcx.service.ExamRecordService;
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
 * 2018/1/8
 */
@Api(description = "测试记录管理")
@RestController
@EnableAutoConfiguration
@RequestMapping("record")
public class ExamRecordController {
    @Autowired
    private ExamRecordService examRecordService;
    private Logger logger = Logger.getLogger(ExamRecordController.class);

    @GetMapping(value = "/get/{userId}/{pageNo}/{pageSize}")
    @ResponseBody
    @ApiOperation(value = "用户分页查询")
    BaseResp getAll(@PathVariable long userId, @PathVariable int pageNo, @PathVariable int pageSize) {
        BaseResp baseResp = new BaseResp();
        logger.info("------->>用户分页查询：record:" + userId + "/" + pageNo + "/" + pageSize);
        try {
            List<Map<String, Object>> topics = examRecordService.findByUserId(userId, pageNo, pageSize);
            BaseResp.setResp(true, baseResp);
            baseResp.setDetail(topics);
            return baseResp;
        } catch (Exception e) {
            logger.error("---->>  record get faild", e);
            BaseResp.setResp(false, baseResp);
            return baseResp;
        }
    }

    @PostMapping(value = "/add")
    @ResponseBody
    @ApiOperation(value = "新增答题记录")
    BaseResp add(@RequestBody ExamRecord examRecord) {
        BaseResp baseResp = new BaseResp();
        logger.info("------->>新增答题记录：examRecord:" + examRecord.toString());
        try {
            if (examRecordService.insert(examRecord)) {
                BaseResp.setResp(true, baseResp);
                return baseResp;
            }
        } catch (Exception e) {
            logger.error("---->>  examRecord add faild", e);
        }
        BaseResp.setResp(false, baseResp);
        return baseResp;
    }
}
