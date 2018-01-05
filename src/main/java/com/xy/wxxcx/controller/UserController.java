package com.xy.wxxcx.controller;

import com.xy.wxxcx.common.resp.BaseResp;
import com.xy.wxxcx.entity.User;
import com.xy.wxxcx.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
    @RequestMapping(value = "/loginByWeixin", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "微信账号登陆", notes = "")
    BaseResp loginByWeixin(@RequestBody User weixinUser) {
        BaseResp baseResp = new BaseResp();
        logger.info("------->>微信账号登陆小程序：user:" + weixinUser.toString());
        try {
            User user = userService.loginByWeixin(weixinUser);
            BaseResp.setResp(true, baseResp);
            baseResp.setDetail(user);
            return baseResp;
        } catch (Exception e) {
            logger.error("---->>  weixinuser login faild", e);
            BaseResp.setResp(false, baseResp);
            return baseResp;
        }
    }

}
