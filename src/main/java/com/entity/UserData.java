package com.entity;


import java.sql.Date;

/**
 * @Author: WuHongLin
 * @Description: 用户资料实体类
 * @Date: 9:01 2018\4\19 0019
 */
public class UserData {
    /*
     * 用户姓名
     */
    private String name;

    /*
     * 用户密码
     */
    private String password;

    /*
     * 用户性别
     */
    private String sex;

    /*
     * 用户出生日期
     */
    private Date birthday;

    /*
     * 用户手机号码
     */
    private String phonew;

    /**
     * 无参构造器
     */
    public UserData() {
    }

    /**
     * 带参构造器
     * @param name 姓名
     * @param password 密码
     * @param sex 性别
     * @param birthday 出生日期
     * @param phonew 手机号码
     */
    public UserData(String name, String password, String sex, Date birthday, String phonew) {
        this.name = name;
        this.password = password;
        this.sex = sex;
        this.birthday = birthday;
        this.phonew = phonew;
    }

    /**
     * 带参构造器
     * @param name 用户名
     * @param password 密码
     */
    public UserData(String name, String password) {
        this.name = name;
        this.password = password;
    }

    //自动生成setget方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhonew() {
        return phonew;
    }

    public void setPhonew(String phonew) {
        this.phonew = phonew;
    }
}
