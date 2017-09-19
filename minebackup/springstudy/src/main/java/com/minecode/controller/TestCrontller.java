package com.minecode.controller;

import com.minecode.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by wqkenqing on 2017/9/19.
 */
@Controller
@RequestMapping("/test")

public class TestCrontller extends BaseController {

    @RequestMapping(value = "index.htm", method = RequestMethod.GET)
    public String index() {

        System.out.println("进入了test");

        return "index";
    }
}
