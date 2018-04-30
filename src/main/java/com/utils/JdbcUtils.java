package com.utils;

import java.sql.*;

/**
 * @Author: WuHongLin
 * @Description: 数据库连接和关闭工具类
 * @version: 1.0
 * @Date: 22:38 2018/2/25 0025
 */
public class JdbcUtils {
    //数据库驱动程序
    private static String driver="com.mysql.jdbc.Driver";
    //连接字符串
    private static String url="jdbc:mysql://127.0.0.1:3306/whljava?characterEncoding=UTF-8";
    //用户名
    private static String user = "root";
    //密码
    private static String password = "whl123456";

    //静态块，实现类加载时，加载数据库的驱动程序
    static {
        try {
            Class.forName( driver );
        } catch ( ClassNotFoundException e ) {
            e.printStackTrace();
        }
    }

    /**
     * 构造方法
     */
    private JdbcUtils() {

    };

    /**
     * 数据库连接方法
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
    /**
     * 释放数据库连接，语句，结果集对象资源
     * @param rs 结果集对象
     * @param pstmt 语句对象
     * @param conn 数据库连接对象
     */
    public static void close (ResultSet rs, Statement pstmt, Connection conn) {
        try {
            //如果结果集对象不为空，则关闭
            if ( rs != null ) {
                rs.close();
            }
        } catch ( SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                //如果语句对象不为空，则关闭
                if ( pstmt != null ) {
                    pstmt.close();
                }
            } catch ( SQLException e ) {
                e.printStackTrace();
            } finally {
                try {
                    //如果连接对象不为空，则关闭
                    if ( conn != null ) {
                        conn.close();
                    }
                } catch ( SQLException e ) {
                    e.printStackTrace();
                }
            }
        }
    }
}

