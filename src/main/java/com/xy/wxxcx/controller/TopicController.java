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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
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

    @GetMapping(value = "/getAll/{cate}/{pageNo}/{pageSize}")
    @ResponseBody
    @ApiOperation(value = "分页全查")
    BaseResp getAll(@PathVariable int cate, @PathVariable int pageNo, @PathVariable int pageSize) {
        BaseResp baseResp = new BaseResp();
        logger.info("------->>分页全查：topic:" + cate + "/" + pageNo + "/" + pageSize);
        try {
            List<Map<String, Object>> topics = topicService.findAll(cate, pageNo, pageSize, 0);
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
            Map<String, Object> topic = topicService.findById(id);
            if (null != topic) {
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
            if (topicService.insert(topic)) {
                BaseResp.setResp(true, baseResp);
                return baseResp;
            }
        } catch (Exception e) {
            logger.error("---->>  topic add faild", e);
        }
        BaseResp.setResp(false, baseResp);
        return baseResp;
    }

    @GetMapping(value = "/gallery/{userid}/{pageNo}")
    @ResponseBody
    @ApiOperation(value = "获取个人日志PC")
    BaseResp getForGallery(@PathVariable long userid, @PathVariable int pageNo) {
        BaseResp baseResp = new BaseResp();
        logger.info("------->>获取个人日志：userid:" + userid);
        try {
            List<Map<String, Object>> topic = topicService.findForGallery(userid, pageNo);
            if (null != topic) {
                BaseResp.setResp(true, baseResp);
                baseResp.setDetail(topic);
                return baseResp;
            }
        } catch (Exception e) {
            logger.error("---->>  获取个人日志PC faild", e);
        }
        BaseResp.setResp(false, baseResp);
        return baseResp;
    }

    @GetMapping(value = "/getMine/{userid}/{pageNo}")
    @ResponseBody
    @ApiOperation(value = "获取个人日志")
    BaseResp getMine(@PathVariable long userid, @PathVariable int pageNo) {
        BaseResp baseResp = new BaseResp();
        logger.info("------->>获取个人日志：userid:" + userid);
        try {
            List<Map<String, Object>> topic = topicService.findAll(0, pageNo, 10, userid);
            if (null != topic) {
                BaseResp.setResp(true, baseResp);
                baseResp.setDetail(topic);
                return baseResp;
            }
        } catch (Exception e) {
            logger.error("---->>  获取个人日志 faild", e);
        }
        BaseResp.setResp(false, baseResp);
        return baseResp;
    }

    /**
     * 单文件上传
     *
     * @param file
     * @param request
     * @return
     */
    @PostMapping("/upload")
    @ResponseBody
    public BaseResp upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        BaseResp baseResp = new BaseResp();
        if (!file.isEmpty()) {
            String saveFileName = file.getOriginalFilename();
            File saveFile = new File(request.getSession().getServletContext().getRealPath("/upload/") + saveFileName);
            if (!saveFile.getParentFile().exists()) {
                saveFile.getParentFile().mkdirs();
            }
            try {
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(saveFile));
                out.write(file.getBytes());
                out.flush();
                out.close();
                BaseResp.setResp(true, baseResp);
                baseResp.setDetail(saveFileName);
                return baseResp;
            } catch (Exception e) {
                e.printStackTrace();
                BaseResp.setResp(false, baseResp);
                baseResp.setDetail(e.getMessage());
                return baseResp;
            }
        } else {
            BaseResp.setResp(false, baseResp);
            baseResp.setDetail("上传失败，因为文件为空.");
            return baseResp;
        }
    }
}
