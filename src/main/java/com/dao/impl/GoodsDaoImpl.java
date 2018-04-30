package com.dao.impl;



import com.dao.GoodsDao;
import com.entity.Goods;
import com.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: WuHongLin
 * @Description: 商品表操作接口实现类
 * @Date: 23:24 2018\4\23 0023
 */
public class GoodsDaoImpl implements GoodsDao {
    @Override
    public int add(Goods goods) {
        //声明数据库连接对象
        Connection conn = null;
        //声明预编译语句对象
        PreparedStatement pstmt = null;

        try {
            //实例化数据库连接对象
            conn = JdbcUtils.getConnection();
            //编写SQL语句
            String sql = "INSERT INTO goods VALUES(?,?,?,?,?)";
            //实例化预编译语句对象
            pstmt = conn.prepareStatement(sql);
            //设置SQL语句中的参数
            pstmt.setInt(1,goods.getGid());
            pstmt.setString(2,goods.getGname());
            pstmt.setFloat(3,goods.getGpraice());
            pstmt.setString(4,goods.getGsize());
            pstmt.setString(5,goods.getGkind());
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
    public List<Goods> findByPage(int rows, int page) {
        //定义一个部门集合，用来保存当前页的部门信息
        List<Goods> goodsList = new ArrayList<>();
        //声明数据库连接对象
        Connection conn = null;
        //声明预编译语句对象
        PreparedStatement pstmt = null;
        //声明结果集对象
        ResultSet rs=null;

        try {
            //实例化连接对象
            conn = JdbcUtils.getConnection();
            //编写执行的SQL语句
            String sql = "SELECT * FROM goods AS g1 \n" +
                    "JOIN (SELECT gid FROM goods ORDER BY gid desc LIMIT ?, 1) AS g2 \n" +
                    "WHERE g1.gid <= g2.gid ORDER BY g1.gid desc LIMIT ?;";
            //实例化预编译语句对象
            pstmt = conn.prepareStatement(sql);
            //给参数赋值
            pstmt.setInt(1, page);
            pstmt.setInt(2, rows);

            rs=pstmt.executeQuery();
            //使用循环，对结果集中数据进行处理
            while(rs.next()){
                //定义部门对象,用于将结果集中的一行数据保存到部门对象中
                Goods goods = new Goods();

                goods.setGid(rs.getInt("gid"));
                goods.setGname(rs.getString("gname"));
                goods.setGpraice(rs.getFloat("gpraice"));
                goods.setGsize(rs.getString("gsize"));
                goods.setGkind(rs.getString("gkind"));

                goodsList.add(goods);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(),e);
        } finally{
            //关闭/释放资源
            JdbcUtils.close(rs, pstmt, conn);
        }
        //返回分页结果
        return goodsList;
    }

    @Override
    public int getOwnerRepCount() {
        //声明数据库连接对象
        Connection conn = null;
        //声明预编译语句对象
        PreparedStatement pstmt = null;
        //声明结果集对象
        ResultSet rs=null;

        try {
            //实例化数据库连接对象
            conn = JdbcUtils.getConnection();
            //编写SQL语句
            String sql = "SELECT COUNT(*) AS goodscount FROM goods";
            //实例化预编译语句对象
            pstmt = conn.prepareStatement(sql);
            //执行SQL语句，返回结果集对象
            rs = pstmt.executeQuery();
            //处理结果集对象:将结果集中的内容保存到对象中

            //定义一个变量，用于从结果集中获取行数
            int rowCount = 0;
            if(rs.next()){
                //此处用统计列别名获得行数，如果没有指定别名，则用数字0表示
                rowCount=rs.getInt("goodscount");
            }
            //返回方法值：员工对象
            return rowCount;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(),e);
        } finally{
            //关闭/释放资源(结果集对象，语句对象，连接对象)
            JdbcUtils.close(rs, pstmt, conn);
        }
    }
}
