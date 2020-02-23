package com.fhz.crawler.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author fanghuaizheng
 * @Description:
 * @date 2019-06-02 18:10
 */
@Controller
public class PageToController {

    @RequestMapping("index")
    public Object index(){
        return "index";
    }

    @RequestMapping("filePath")
    public Object path(){
        return "filePath";
    }

}
