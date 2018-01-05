package com.xy.wxxcx.service;

import com.github.pagehelper.PageHelper;
import com.xy.wxxcx.common.util.JavaBeanUtil;
import com.xy.wxxcx.entity.Topic;
import com.xy.wxxcx.mapper.CommonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xy
 */
@Component
public class TopicService {
    @Autowired
    private CommonDao commonDao;
    public List<Map<String,Object>> findAll(int pageNo, int pageSize){
        PageHelper.startPage(pageNo,pageSize);
        PageHelper.orderBy("create_time desc");
        return commonDao.select("t_topic_weixin",null);
    }

    public Map<String,Object> findById(int id){
        Map<String,Object> params=new HashMap<>(1);
        params.put("topicid",id);
        List<Map<String,Object>> topics=commonDao.select("t_topic_weixin",params);
        if(null==topics||topics.size()<1){
            return null;
        }
        return topics.get(0);
    }

    public boolean insert(Topic topic) throws IllegalAccessException {
       return commonDao.insert("t_topic_weixin", JavaBeanUtil.beanToMap(topic)) > 0;
    }
}
