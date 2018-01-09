package com.xy.wxxcx.entity

/**
 * @author xy
 */
class Topic {
    String id
    String title
    String content
    int cate
    String openid
    String avatar
    String img
    Date createTime
    int comments
    int views
    String nick_name
    int userid


    @Override
    public String toString() {
        return "Topic{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", cate=" + cate +
                ", openid='" + openid + '\'' +
                ", avatar='" + avatar + '\'' +
                ", img='" + img + '\'' +
                ", createTime=" + createTime +
                ", comments=" + comments +
                ", views=" + views +
                ", nick_name='" + nick_name + '\'' +
                ", userid=" + userid +
                '}'
    }
}
