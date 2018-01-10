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
            INSERT INTO t_user_weixin 
            (username,password,avatar_url,city,country,gender,language,nick_name,province,email,openid)
             VALUES(#{username}, #{password}, #{avatarUrl}, #{city}, #{country}, #{gender}, #{language}, #{nickName}, #{province},#{email},#{openid})
            ''')
    int insert(User user)

    @Select("SELECT username,password,avatar_url as avatarUrl,city,country,gender,language,nick_name as nickName,province,email,openid,id FROM t_user_weixin where openid=#{openId}")
    User findByOpenId(@Param("openId")String openId)

    @Select("SELECT username,password,avatar_url as avatarUrl,city,country,gender,language,nick_name as nickName,province,email,openid,id FROM t_user_weixin where username=#{username} and password=#{password} limit 0,1")
    User login(@Param("username")String username,@Param("password")String password)

    @Select("SELECT username,password,avatar_url as avatarUrl,city,country,gender,language,nick_name as nickName,province,email,openid,id FROM t_user_weixin where id=#{id} limit 0,1")
    User findById(@Param("id")long id)
}