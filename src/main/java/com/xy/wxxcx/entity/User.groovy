package com.xy.wxxcx.entity

class User {
    String openid
    String nick_name
    String province
    String country
    String avatar_url
    int gender
    String language
    String city
    String code
    String username
    String password
    long id


    @Override
    public String toString() {
        return "User{" +
                "openid='" + openid + '\'' +
                ", nick_name='" + nick_name + '\'' +
                ", province='" + province + '\'' +
                ", country='" + country + '\'' +
                ", avatar_url='" + avatar_url + '\'' +
                ", gender=" + gender +
                ", language='" + language + '\'' +
                ", city='" + city + '\'' +
                ", code='" + code + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", id=" + id +
                '}';
    }
}
