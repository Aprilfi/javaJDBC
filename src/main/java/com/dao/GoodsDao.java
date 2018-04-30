package com.dao;



import com.entity.Goods;

import java.util.List;

/**
 * @Author: WuHongLin
 * @Description: 商品表数据操作接口类
 * @Date: 17:03 2018\4\23 0023
 */
public interface GoodsDao {

    /**
     * 添加
     * @param goods
     * @return
     */
    public int add(Goods goods);

    /**
     * 分页查询
     * @param rows
     * @param page
     * @return
     */
    public List<Goods> findByPage(int rows, int page);

    /**
     * 获取当前页数
     * @return
     */
    public int getOwnerRepCount();

}
