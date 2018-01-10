package com.xy.wxxcx.service;

import com.github.pagehelper.PageHelper;
import com.xy.wxxcx.common.util.JavaBeanUtil;
import com.xy.wxxcx.entity.Comment;
import com.xy.wxxcx.mapper.CommonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xy
 * 2018/1/10
 */
@Component
public class CommentService {
    @Autowired
    private CommonDao commonDao;

    @Transactional(rollbackFor = Exception.class)
    public boolean insert(Comment comment) throws Exception {
        //增加文章评论数
        Map<String, Object> setParam = new HashMap<>();
        Map<String, Object> whereParam = new HashMap<>();
        setParam.put("comments", "comments+1");
        whereParam.put("topicid", comment.getTopicid());
        commonDao.update("t_topic_weixin", setParam, whereParam);
        //增加用户积分
//        setParam.clear();
//        whereParam.clear();
//        setParam.put("score", "score+1");
//        whereParam.put("id", comment.getUserid());
//        commonDao.update("t_user_weixin", setParam, whereParam);
        return commonDao.insert("t_comment_weixin", JavaBeanUtil.beanToMap(comment)) > 0;
    }

    public List<Map<String, Object>> findByTopicId(long topicId){
        PageHelper.orderBy("create_time desc");
        Map<String, Object> params = new HashMap<>();
        params.put("topicid", topicId);
        return commonDao.select("t_comment_weixin", params);
    }
}
