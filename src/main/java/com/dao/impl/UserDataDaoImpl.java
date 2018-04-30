package com.dao.impl;



import com.dao.UserDataDao;
import com.entity.UserData;
import com.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: WuHongLin
 * @Description: 用户资料数据操作接口实现类
 * @Date: 9:12 2018\4\19 0019
 */
public class UserDataDaoImpl implements UserDataDao {


    @Override
    public int add(UserData userData) {
        //声明数据库连接对象
        Connection conn = null;
        //声明预编译语句对象
        PreparedStatement pstmt = null;

        try {
            //实例化数据库连接对象
            conn = JdbcUtils.getConnection();
            //编写SQL语句
            String sql = "INSERT INTO userdata VALUES(null,?,?,?,?,?)";
            //实例化预编译语句对象
            pstmt = conn.prepareStatement(sql);
            //设置SQL语句中的参数
            pstmt.setString(1, userData.getName() );
            pstmt.setString(2, userData.getPassword() );
            pstmt.setString(3, userData.getSex() );
            pstmt.setDate(4, userData.getBirthday());
            pstmt.setString(5, userData.getPhonew() );
            //执行SQL操作
            //将执行后，在数据库表中影响的行数作为参数返回
            return pstmt.executeUpdate();
        } catch ( SQLException e ) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            JdbcUtils.close( null, pstmt, conn);
        }
    }

    @Override
    public List<UserData> findByName(String name) {
        //定义一个部门集合，用来保存当前页的部门信息
        List<UserData> userDataList = new ArrayList<UserData>();
        //声明数据库连接对象
        Connection conn = null;
        //声明预编译语句对象
        PreparedStatement pstmt = null;
        //声明结果集
        ResultSet rs = null;

        try {
            //实例化数据库连接对象
            conn = JdbcUtils.getConnection();
            //编写SQL语句
            String sql = "SELECT name,password FROM userdata WHERE name = ?";
            //实例化预编译语句对象
            pstmt = conn.prepareStatement(sql);
            //设置SQL语句中的参数
            pstmt.setString(1,name);
            //执行SQL操作
            rs = pstmt.executeQuery();
            //使用循环，对结果集中数据进行处理
            while(rs.next()){
                //定义部门对象,用于将结果集中的一行数据保存到部门对象中
                UserData userData = new UserData();
                userData.setName(rs.getString("name"));
                userData.setPassword(rs.getString("password"));
                userDataList.add(userData);
            }
            //将执行后，在数据库表中影响的行数作为参数返回
            return userDataList;
        } catch ( SQLException e ) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            JdbcUtils.close( null, pstmt, conn);
        }
    }
}
