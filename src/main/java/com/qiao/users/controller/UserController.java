package com.qiao.users.controller;

import com.qiao.common.MD5Util;
import com.qiao.common.JWTUtil;
import com.qiao.common.TokenToRedis;
import com.qiao.users.bean.ActiveUser;
import com.qiao.users.bean.User;
import com.qiao.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Controller
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    TokenToRedis tokenToRedis;
    // 跳转到登录页面
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView loginpage() {
        return new ModelAndView("login");
    }
    // 登录进入聊天主页面
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(User loginUser, HttpServletRequest request) {

        User user =  userService.select(loginUser);
        if(user==null){
            return new ModelAndView("login");
        }else if(!user.getPassword().equals(loginUser.getPassword())){
            return new ModelAndView("login");
        }else {
            HttpSession session = request.getSession();
            // 登录操作
            // 判断是否是一个已经登录的用户，没有则登录
            if (null != session.getAttribute("loginUser")) {
                // 清除旧的用户
                session.removeAttribute("loginUser");
            }
            // 新登录，需要构建一个用户
            // 随机生成一个用户
            String id = UUID.randomUUID().toString();
            loginUser.setId(id);
            // 将用户放入session
            session.setAttribute("loginUser", loginUser);
            session.setAttribute("username", loginUser.getNickname());
            // 将登录信息放入数据库，便于协查跟踪聊天者
            System.out.println("新用户诞生了11：" + loginUser);
            return new ModelAndView("redirect:mainpage");
        }
    }

    // 登录进入聊天主页面
    /*@RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(@NotNull String nickname, @NotNull String password, HttpServletRequest request) {

        User user =  userService.select(nickname);
        boolean verify =false;
        try {
            verify = MD5Util.verify(password, MD5Util.MD5_KEY, user.getPassword());
        } catch (Exception e) {
            return new ModelAndView("login");
        }

        if(!verify){
            return new ModelAndView("login");
        }
        // 生成token
        String token = JWTUtil.sign(nickname, password);
        String id = null;
        try {
            // 生成一个用户id
            id = tokenToRedis.saveTokenToRedis(user,token,request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 新登录，需要构建一个用户
        User loginUser = new User();

        loginUser.setId(id);
        loginUser.setNickname(nickname);
        loginUser.setPassword(password);

        HttpSession session = request.getSession();
       // 登录操作
       // 判断是否是一个已经登录的用户，没有则登录
       if (null != session.getAttribute("loginUser")) {
           // 清除旧的用户
           session.removeAttribute("loginUser");
       }
       // 将用户放入session
       session.setAttribute("loginUser", loginUser);
       session.setAttribute("token",token);
       session.setAttribute("username", loginUser.getNickname());
       // 将登录信息放入数据库，便于协查跟踪聊天者
       System.out.println("新用户诞生了11：" + loginUser);
       return new ModelAndView("redirect:mainpage");
    }*/

    // 跳转到聊天室页面
    @RequestMapping(value = "mainpage", method = RequestMethod.GET)
    public ModelAndView mainpage(HttpServletRequest request) {
        System.out.println("输入的值为:"+request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/");
        //判断，如果没有session，则跳到登录页面
        HttpSession session = request.getSession();
        if(null==session.getAttribute("loginUser")){
            return new ModelAndView("login");
        }else{
            return new ModelAndView("main");
        }
    }

    // 跳转至注册界面
    @RequestMapping(value = "/registerA", method = RequestMethod.GET)
    public ModelAndView registerA( HttpServletRequest request) {
            return new ModelAndView("register");

    }
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView register( User loginUser,HttpServletRequest request) {
        if(loginUser.getNickname()==null || loginUser.getNickname()==null){
            request.setAttribute("mess","用户名或者密码错误，请重写输入");
            return new ModelAndView("register");
        }
        User user =  null;//userService.select(loginUser);
        if(user!=null){
            request.setAttribute("mess","用户名重复，请重写输入");
            return new ModelAndView("register");
        }
        userService.insert(loginUser);
        return new ModelAndView("login");

    }

    // 测试操作
    @RequestMapping(value = "test", method = RequestMethod.GET)
    public ModelAndView test1(HttpServletRequest request) {
        System.out.println("输入的值为:"+request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/");
            return new ModelAndView("testwebSocket");

    }

    @RequestMapping("/aa")
    public String aa(){
        ActiveUser a = new ActiveUser();
        System.out.println(a.getLoginTime()+"================");
        return null;
    }
}
