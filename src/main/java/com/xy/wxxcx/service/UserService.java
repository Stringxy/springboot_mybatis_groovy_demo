package com.xy.wxxcx.service;

import com.alibaba.fastjson.JSONObject;
import com.xiaoleilu.hutool.http.HttpUtil;
import com.xy.wxxcx.entity.User;
import com.xy.wxxcx.mapper.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
/**
 * @author xy
 */
@Component
public class UserService {
    private static final String APPID="xx";
    private static final String SECRET="xx";
    private static final String OPENIDAPI="https://api.weixin.qq.com/sns/jscode2session?appid=";
    @Autowired
    private UserDao userDao;

    public String getOpenId(String code) throws Exception{
        String response= HttpUtil.get(OPENIDAPI+APPID+"&secret="+SECRET+"&js_code="+code+"&grant_type=authorization_code");
        System.out.println("获取openid:"+response);
        JSONObject res=JSONObject.parseObject(response);
        return res.getString("openid");
    }

    public User loginByWeixin(User user) throws Exception {
        User nowUser=userDao.findByOpenId(getOpenId(user.getCode()));
        if(null==nowUser){
            user.setOpenid(getOpenId(user.getCode()));
            if(userDao.insert(user)<1){
                return  null;
            }else{
                return user;
            }
        }
        return nowUser;
    }
}
