package com.hezhe.springboot.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @author 贺哲
 * @2020-01-12 13:38
 */
@Controller
public class LoginController {

    /**
     * 进入到登录页面
     * @return
     */
    @RequestMapping("/to_login")
    public String to_login(){
        return "login";
    }

    @PostMapping(value = {"/login.html"})
    public String login(String username, String password, Integer rememberMe, HttpSession session) {
        /**
         * 在把密码封装到token中之前，先把前端进行ase加密的密文进行一次解密
         */
//        String uuidSaltVi = (String) session.getAttribute("uuidSaltVi");
//        try {
//            //解密以后再把值赋给password，这个时候的值就是第一次md5加密的值
//            password = AesEncryptUtil.desEncrypt(password, uuidSaltVi, uuidSaltVi);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "error";
//        }
        Subject subject = SecurityUtils.getSubject();
        //没有登录就把用户名和密码封装到UsernamePasswordToken中
        UsernamePasswordToken token =
                new UsernamePasswordToken(username, password);
        session.setAttribute("username", username);
//        //当rememberMe == 1，就让其记住我RememberMe
//        if (rememberMe != null && rememberMe == 1) {
//            token.setRememberMe(true);
//        }
        try {
            //进入到ShiroRealm的AuthenticationInfo中
            subject.login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return "error";
        }
        return "main";
    }

    @RequestMapping("/add")
    public String add(){
        return "add";
    }

    @RequestMapping("/update")
    public String update(){
        return "update";
    }

    @RequestMapping("/unAuth")
    public String unAuth(){
        return "unAuth";
    }

}
