package com.hezhe.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * @author 贺哲
 * @2020-01-06 15:14
 */
@Controller
public class LoginController {

    @PostMapping(value = "/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model, HttpSession session) {
        if (username.equals("hezhe") && password.equals("123456")) {
            session.setAttribute("username", username);
            return "redirect:/main";
        } else {
            return "redirect:/index";
        }
    }

    @RequestMapping("/main")
    public String to_main() {
        return "main";
    }

}
