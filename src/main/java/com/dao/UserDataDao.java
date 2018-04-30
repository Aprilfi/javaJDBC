package com.dao;


import com.entity.UserData;

import java.util.List;

/**
 * @Author: WuHongLin
 * @Description: 用户资料数据操作接口类
 * @Date: 9:10 2018\4\19 0019
 */
public interface UserDataDao {

    /**
     * 用户资料添加方法
     * @param userData 用户资料实体对象
     * @return 数据库影响行数
     */
    public int add(UserData userData);

    /**
     * 用户资料查询方法
     * @param name 用户名
     * @return 用户资料对象
     */
    public List<UserData> findByName(String name);

}
