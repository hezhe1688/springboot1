package com.hezhe.springboot.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author 贺哲
 * @2020-01-02 14:07
 */
@Controller
public class ControllerDemo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping("/index")
    public String test() {
        return "index";
    }

    @ResponseBody
    @GetMapping("/demo")
    public List<Map<String, Object>> demo() {
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from employee");
        return list;
    }
}
