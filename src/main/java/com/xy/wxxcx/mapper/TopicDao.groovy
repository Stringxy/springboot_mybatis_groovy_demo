package com.xy.wxxcx.mapper

import com.xy.wxxcx.entity.Topic
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select
import org.springframework.stereotype.Component
/**
 * @author xy
 */
@Component
interface TopicDao {
    @Select(//language=MySQL
            '''
            SELECT * FROM t_topic_weixin 
            WHERE topicid IN (SELECT topicid FROM t_comment_weixin where userid=#{userId})
            ''')
    List<Topic> getByCommentUserId(@Param("userId")long userId);
}