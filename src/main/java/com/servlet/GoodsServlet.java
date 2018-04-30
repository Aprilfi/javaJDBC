package com.servlet;



import com.dao.GoodsDao;
import com.dao.impl.GoodsDaoImpl;
import com.entity.Goods;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/GoodsServlet")
public class GoodsServlet extends HttpServlet {

    GoodsDao goodsDao = new GoodsDaoImpl();

    public GoodsServlet(){
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置响应文本字符编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        //根据路径后操作方式调用操作方法
        String method = request.getParameter("method");
        //定义方法名字符串
        String findByPage = "findbypage";
        String addGoods = "addGoods";

        if(findByPage.equals(method)){
            int pageSize = Integer.parseInt(request.getParameter("limit"));
            int pageNumber = Integer.parseInt(request.getParameter("offset"));
            //limit - 1 * offset
            System.out.println("rows:"+pageSize+";page:"+pageNumber);
            findByPage(pageSize,pageNumber,request,response);
        }else if(addGoods.equals(method)){
            addGoods(request,response);
        }

    }

    /**
     * 添加商品信息
     * @param request
     * @param response
     */
    private void addGoods(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //定义输出对象
        PrintWriter out = response.getWriter();
        //获取来自表单的数据
        int gid = Integer.parseInt(request.getParameter("gid"));
        String gname = request.getParameter("gname");
        String gsize = request.getParameter("gsize");
        String gkind = request.getParameter("gkind");
        float gpraice = Float.parseFloat(request.getParameter("gpraice"));
        //封装成对象
        Goods goods = new Goods(gid,gname,gpraice,gsize,gkind);
        //调用数据层对象实现添加新员工,通过异常判断成功与失败]\
        try{
            goodsDao.add(goods);
            out.println("success");
        }catch(Exception e){
            out.println("failure");
        }

        //关闭输出对象
        out.close();
    }

    /**
     * 分页查询
     * @param request
     * @param response
     */
    private void findByPage(Integer pageSize, Integer pageNumber, HttpServletRequest request, HttpServletResponse
            response) throws IOException {
        //定义一个输出对象
        PrintWriter out = response.getWriter();
        //调用数据层分页方式，获取数据
        List<Goods> goodsList = goodsDao.findByPage(pageSize,pageNumber);
        //获得总行数
        int totalRows = goodsDao.getOwnerRepCount();

        //使用JSON对数据进行转换(集合转换成JSON数组)
        String jsonStr = JSONArray.fromObject(goodsList).toString();

        //组合符合datagrid要求格式的数据字符串
        String json = "{\"total\":"+totalRows+",\"rows\":"+jsonStr+"}";
        //输出到页面
        out.println(json);
        //关闭输出对象
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request,response);
    }
}
