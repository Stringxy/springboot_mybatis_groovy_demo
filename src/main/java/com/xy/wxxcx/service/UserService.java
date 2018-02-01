package com.xy.wxxcx.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.util.StringUtil;
import com.xiaoleilu.hutool.http.HttpUtil;
import com.xy.wxxcx.entity.User;
import com.xy.wxxcx.mapper.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author xy
 */
@Component
public class UserService {
    @Value("${xcx.appid}")
    private String APPID;
    @Value("${xcx.secret}")
    private String SECRET;
    private static final String OPENIDAPI = "https://api.weixin.qq.com/sns/jscode2session?appid=";
    @Autowired
    private UserDao userDao;

    public String getOpenId(String code) throws Exception {
        String response = HttpUtil.get(OPENIDAPI + APPID + "&secret=" + SECRET + "&js_code=" + code + "&grant_type=authorization_code");
        System.out.println("获取openid:" + response);
        JSONObject res = JSONObject.parseObject(response);
        return res.getString("openid");
    }

    public User bindWeixin(User user) throws Exception {
        User nowUser = userDao.login(user.getUsername(), user.getPassword());
        if (null != nowUser && nowUser.getUsername().equals(user.getUsername())) {
            nowUser.setOpenid(user.getOpenid());
            if (userDao.bind(nowUser) < 1) {
                return null;
            } else {
                return user;
            }
        }
        return null;
    }

    public User findByOpenId(String code) throws Exception {
        String openId = getOpenId(code);
        User nowUser = userDao.findByOpenId(openId);
        if (null == nowUser) {
            nowUser = new User();
            nowUser.setId(0);
            nowUser.setOpenid(openId);
        }
        return nowUser;
    }

    public User login(String username, String password) {
        return userDao.login(username, password);
    }

    public boolean insert(User user) throws Exception {
        user.setOpenid(getOpenId(user.getCode()));
        return userDao.insert(user) > 0;
    }


}
