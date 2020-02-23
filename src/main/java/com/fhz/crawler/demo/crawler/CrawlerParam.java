package com.fhz.crawler.demo.crawler;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fanghuaizheng
 * @Description:
 * @date 2019-06-02 11:40
 */
@Data
public class CrawlerParam {

    //网址集合
    private List<String> urls = new ArrayList<>();

    //guanjianci
    private String key;

    //信息类型
    private Integer infoType;

}
