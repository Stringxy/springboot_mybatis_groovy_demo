package com.xy.wxxcx.controller;

import com.github.pagehelper.util.StringUtil;
import com.xy.wxxcx.common.resp.BaseResp;
import com.xy.wxxcx.entity.User;
import com.xy.wxxcx.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;


/**
 * @author xy
 */
@Api(description = "用户管理")
@RestController
@EnableAutoConfiguration
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    Logger logger = Logger.getLogger(UserController.class);

    @PostMapping(value = "/bindWeixin")
    @ResponseBody
    @ApiOperation(value = "绑定微信账号", notes = "")
    BaseResp bindWeixin(@RequestBody User weixinUser) {
        BaseResp baseResp = new BaseResp();
        logger.info("------->>绑定微信账号：user:" + weixinUser.toString());
        try {
            User user = userService.bindWeixin(weixinUser);
            if (null != user) {
                BaseResp.setResp(true, baseResp);
                baseResp.setDetail(user);
                return baseResp;
            }
        } catch (Exception e) {
            logger.error("---->>  weixinuser bind faild", e);
        }
        BaseResp.setResp(false, baseResp);
        return baseResp;
    }

    @PostMapping(value = "/login")
    @ResponseBody
    @ApiOperation(value = "登陆", notes = "")
    BaseResp login(@RequestParam("username") String username, @RequestParam("password") String password) {
        BaseResp baseResp = new BaseResp();
        logger.info("------->>登陆：user:" + username + "/" + password);
        try {
            User user = userService.login(username, password);
            if (null != user) {
                BaseResp.setResp(true, baseResp);
                baseResp.setDetail(user);
                return baseResp;
            }
        } catch (Exception e) {
            logger.error("---->>  user login faild", e);
        }
        BaseResp.setResp(false, baseResp);
        return baseResp;
    }

    @GetMapping(value = "/validate/{code}")
    @ResponseBody
    @ApiOperation(value = "验证是否绑定微信", notes = "")
    BaseResp validate(@PathVariable String code) {
        BaseResp baseResp = new BaseResp();
        try {
            User user = userService.findByOpenId(code);
            BaseResp.setResp(true, baseResp);
            baseResp.setDetail(user);
            return baseResp;
        } catch (Exception e) {
            logger.error("---->>  user validate faild", e);
        }
        BaseResp.setResp(false, baseResp);
        return baseResp;
    }


    @PostMapping(value = "/register")
    @ResponseBody
    @ApiOperation(value = "注册账号", notes = "")
    BaseResp register(@RequestBody User user) {
        BaseResp baseResp = new BaseResp();
        logger.info("------->>注册账号：user:" + user.toString());
        try {
            if (userService.insert(user)) {
                BaseResp.setResp(true, baseResp);
                baseResp.setDetail(user);
                return baseResp;
            }
        } catch (Exception e) {
            logger.error("---->>  register faild", e);
        }
        BaseResp.setResp(false, baseResp);
        return baseResp;
    }
}
