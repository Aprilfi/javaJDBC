package com.servlet;


import com.dao.UserDataDao;
import com.dao.impl.UserDataDaoImpl;
import com.entity.UserData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

/**
 * @Author: WuHongLin
 * @Description: 对业主前端界面进行请求转发
 * @Date: 19:56 2018/3/8 0008
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置响应文本字符编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        //根据路径后操作方式调用操作方法
        String method=request.getParameter("method");

        /*定义方法名字符串*/
        String login = "login";
        String register = "register";

        //判断操作方式
        if (login.equals(method)) {
            login(request,response);
        } else if (register.equals(method)) {
            register(request,response);
        }

    }

    /**
     * 注册添加用户资料
     * @param request
     * @param response
     */
    private void register(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //获取提交表单内的参数
        String name = request.getParameter("usernameArray");
        String password = request.getParameter("userpasswArray");
        String sex = request.getParameter("usersexArray");
        int year = Integer.parseInt(request.getParameter("useryearArray"));
        int month = Integer.parseInt(request.getParameter("usermonthArray"));
        int nub = Integer.parseInt(request.getParameter("usernubArray"));
        String phone = request.getParameter("userphonenubArray");
        //将生日封装成date对象
        Date date = new Date(year-month-nub);
        //把数据封装在实体对象中
        UserData userData = new UserData(name,password,sex,date,phone);
        //通过实现类添加数据到数据库
        UserDataDao userDataDao = new UserDataDaoImpl();
        int excute = userDataDao.add(userData);

        //创建输出对象
        PrintWriter out = response.getWriter();

        if(excute > 0){
            out.print("success");

        } else
            out.print("fair");

        out.close();
    }

    /**
     * 登录验证用户资料
     * @param request
     * @param response
     */
    private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取提交表单内的参数
        String name = request.getParameter("nameArray");
        String password = request.getParameter("passwordArray");

        UserDataDao userDataDao = new UserDataDaoImpl();
        List<UserData> userDataList = userDataDao.findByName(name);

        boolean state = false;
        int nub = 0;

        for(int i = 0; i < userDataList.size();i ++){
            if(name.equals(userDataList.get(i).getName())){
                nub = i;
                break;
            }
        }

        if(password.equals(userDataList.get(nub).getPassword())) state = true;
        //创建输出对象
        PrintWriter out = response.getWriter();

        if(!state)
            out.print("fair");
        if(userDataList.get(nub).getPassword().equals(password))
            out.print("success");
        else
            out.print("fair");
        out.close();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request,response);
    }
}
