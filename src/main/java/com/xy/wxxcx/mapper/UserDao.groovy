package com.xy.wxxcx.mapper

import com.xy.wxxcx.entity.User
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select
import org.springframework.stereotype.Component

/**
 * @author xy
 */
@Component
interface UserDao {
    @Select("SELECT * FROM t_user_weixin ORDER BY score DESC")
    List<User> getAll()

    @Insert(//language=MySQL
            '''
            INSERT INTO user 
            (username,password,avatar_url,city,code,country,gender,language,nick_name,province)
             VALUES(#{username}, #{password}, #{avatarUrl}, #{city}, #{code}, #{country}, #{gender}, #{language}, #{nickName}, #{province})
            ''')
    int insert(User user)

    @Select("SELECT * FROM t_user_weixin where openid=#{openId}")
    User findByOpenId(@Param("openId")String openId)
}