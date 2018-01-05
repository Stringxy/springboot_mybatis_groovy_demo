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
    Date create_time
    int comments
    int views
    String nick_name


    @Override
    String toString() {
        return "Topic{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", cate=" + cate +
                ", openid='" + openid + '\'' +
                ", avatar='" + avatar + '\'' +
                ", img='" + img + '\'' +
                ", create_time=" + create_time +
                ", comments=" + comments +
                ", views=" + views +
                ", nick_name='" + nick_name + '\'' +
                '}'
    }
}
