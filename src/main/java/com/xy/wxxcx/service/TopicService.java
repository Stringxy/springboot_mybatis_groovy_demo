package com.xy.wxxcx.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.util.StringUtil;
import com.xy.wxxcx.common.util.JavaBeanUtil;
import com.xy.wxxcx.entity.Topic;
import com.xy.wxxcx.mapper.CommonDao;
import com.xy.wxxcx.mapper.TopicDao;
import com.xy.wxxcx.mapper.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    @Autowired
    private UserDao userDao;
    @Autowired
    private CommentService commentService;
    @Autowired
    private TopicDao topicDao;

    public List<Map<String, Object>> findAll(int cate, int pageNo, int pageSize, long userid) {
        PageHelper.startPage(pageNo, pageSize);
        PageHelper.orderBy("create_time desc");
        Map<String, Object> params = new HashMap<>();
        if (cate != 0) {
            params.put("cate", cate);
        }
        if (userid != 0) {
            params.put("userid", userid);
        }
        return commonDao.select("t_topic_weixin", params);
    }

    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> findById(int id) {
        Map<String, Object> setParam = new HashMap<>();
        Map<String, Object> whereParam = new HashMap<>();
        setParam.put("views", "views+1");
        whereParam.put("topicid", id);
        commonDao.update("t_topic_weixin", setParam, whereParam);
        Map<String, Object> params = new HashMap<>(1);
        params.put("topicid", id);
        List<Map<String, Object>> topics = commonDao.select("t_topic_weixin", params);
        if (null == topics || topics.size() < 1) {
            return null;
        }
        Map<String, Object> topic=topics.get(0);
        topic.put("author",userDao.findById(Long.parseLong(topic.get("userid").toString())));
        topic.put("comment",commentService.findByTopicId(id));
        return topic;
    }

    public boolean insert(Topic topic) throws IllegalAccessException {
        if(StringUtil.isEmpty(topic.getTitle())||StringUtil.isEmpty(topic.getContent())){
            return false;
        }
        return commonDao.insert("t_topic_weixin", JavaBeanUtil.beanToMap(topic)) > 0;
    }

    public List<Map<String, Object>> findForGallery(long userId, int pageNo) {
        PageHelper.startPage(pageNo, 10);
        Map<String, Object> params = new HashMap<>(1);
        params.put("userid", userId);
        List<Map<String, Object>> topics = commonDao.select("t_topic_weixin", params);
        List<Map<String, Object>> cards = new ArrayList<>();
        topics.forEach(map -> {
            Map<String, Object> card = new HashMap<>();
            card.put("fileName", map.get("img"));
            card.put("title", map.get("title"));
            card.put("desc", map.get("content"));
            cards.add(card);
        });
        return cards;
    }

    public List<Topic> getCommentedTopic(long userid,int pageNo){
        PageHelper.startPage(pageNo, 10);
        PageHelper.orderBy("create_time desc");
        return topicDao.getByCommentUserId(userid);
    }
}
