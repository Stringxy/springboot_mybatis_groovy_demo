package com.xy.wxxcx.entity

class User {
    String openid
    String nickName
    String province
    String country
    String avatarUrl
    int gender
    String language
    String city
    String code
    String username
    String password
    long id
    String email


    @Override
    public String toString() {
        return "User{" +
                "openid='" + openid + '\'' +
                ", nick_name='" + nickName + '\'' +
                ", province='" + province + '\'' +
                ", country='" + country + '\'' +
                ", avatar_url='" + avatarUrl + '\'' +
                ", gender=" + gender +
                ", language='" + language + '\'' +
                ", city='" + city + '\'' +
                ", code='" + code + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", id=" + id +
                '}'
    }
}
