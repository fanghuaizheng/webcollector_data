package com.fhz.crawler.demo.controller;

import com.fhz.crawler.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fanghuaizheng
 * @Description:
 * @date 2019-05-30 17:34
 */
@RestController
@RequestMapping("user")
public class UserController {


    @Autowired
    UserService userService;

    @RequestMapping("find")
    public Object find(){
        return userService.list();
    }

}
