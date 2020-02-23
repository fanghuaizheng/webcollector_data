package com.fhz.crawler.demo;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fhz.crawler.demo.entity.TFilePath;
import com.fhz.crawler.demo.entity.User;
import com.fhz.crawler.demo.mapper.TFilePathMapper;
import com.fhz.crawler.demo.mapper.UserMapper;
import com.fhz.crawler.demo.service.UserService;
import com.fhz.crawler.demo.utils.HttpUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CrawlerApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserService userService;

    @Autowired
    TFilePathMapper tFilePathMapper;

    @Test
    public void test01(){
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
        Assert.assertEquals(5,users.size());

    }

    @Test
    public void test02(){
        List<User> list = userService.list();
        list.forEach(System.out::println);
    }

    @Test
    public void insertUser(){
        User user = new User();
        user.setName("fff");
        user.setAge(12);
        user.setPassword("123");
        boolean save = userService.save(user);

        Assert.assertTrue(save);

    }

    @Test
    public void query(){
//        List<User> age = userService.list(new QueryWrapper<User>().gt("age", 12));
//        age.forEach(System.out::println);
//        //使用自定义sql
//        List<User> list = userService.list(new QueryWrapper<User>().apply("age = 12"));
//        list.forEach(System.out::println);

//        Page<User> page = new Page<>();
//        userService.page(page);

        Page<TFilePath> page = new Page<>(1,10);

        IPage<TFilePath> page1 = tFilePathMapper.selectPage(page, Wrappers.emptyWrapper());

        System.out.println(page1);


    }

    @Test
    public void testRestTemplate(){
        RestTemplate template = new RestTemplate();
//        String url1 = "https://www.ximalaya.com/revision/album/getTracksList?albumId=" + 12891461 + "&pageNum=1";
//
        HttpHeaders headers = new HttpHeaders();
//
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        headers.set("xm-sign","766ae512b8b11aeedf9ff06b08ba90b4(29)1559566544815(65)1559566546493");
//
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(null, headers);
//
//

        String url1 = "https://www.ximalaya.com/revision/play/album?albumId="+12891461+"&pageNum="+1+"&sort=-1&pageSize=30";



//
//
        ResponseEntity<String> response = template.exchange(url1, HttpMethod.GET, request, String.class);

//        String re = HttpUtils.doGet(url1,null);

        //获取总页
        JSONObject total = JSONObject.parseObject(response.getBody());

        System.out.println(total);


    }

}
