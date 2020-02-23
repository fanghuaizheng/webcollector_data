package com.fhz.crawler.demo;

import com.alibaba.fastjson.JSONObject;
import com.fhz.crawler.demo.utils.HttpUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author fanghuaizheng
 * @Description:
 * @date 2019-06-03 20:34
 */
public class CommonTest {

    public static void main(String[] args) {

        String url1 = null;

//        String url1 = "https://www.ximalaya.com/revision/album/getTracksList?albumId=" + 12891461 + "&pageNum=1";


//        String url1 = "https://www.ximalaya.com/revision/play/album?albumId="+12891461+"&pageNum="+1+"&sort=-1&pageSize=30";

        url1 = "http://www.v1.cn/index/getList4Ajax?id=1";

        System.out.println("这是url"+url1);

//        String re = HttpUtils.doGet(url1,null);

//        Map<String,Object> param = new HashMap<>();
//        param.put("cid","3");
//        param.put("page","2");
//
//        String s = HttpUtils.doPost(url1, param, true);
//
//        //获取总页
//        JSONObject total = JSONObject.parseObject(s);
//
//        System.out.println(total);

        String name = url1.split("\\?|@")[0];

        System.out.println(name);






    }
}
