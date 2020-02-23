package com.fhz.crawler.demo.controller;

import com.fhz.crawler.demo.crawler.MyCrawler;
import com.fhz.crawler.demo.entity.TCrawlerMatch;
import com.fhz.crawler.demo.service.TCrawlerMatchService;
import com.fhz.crawler.demo.service.TFilePathService;
import com.fhz.crawler.demo.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author fanghuaizheng
 * @Description:
 * @date 2019-06-05 19:37
 */
@RestController
@RequestMapping("match")
public class TCrawlerMatchController {

    @Autowired
    TCrawlerMatchService tCrawlerMatchService;

    @Autowired
    TFilePathService tFilePathService;


    @RequestMapping("findOne")
    public Object find(){
        List<TCrawlerMatch> list = tCrawlerMatchService.list();

        if (list!=null&&list.size()>0){
            return list.get(0);
        }
        return null;

    }

    @RequestMapping("save")
    public Object save(TCrawlerMatch record){
        boolean b = tCrawlerMatchService.saveOrUpdate(record);

        return ResponseData.builder().code("200").data(b).build();
    }

    @RequestMapping("startCrawler")
    public Object startCrawler(String guid) throws Exception {

        TCrawlerMatch byId = tCrawlerMatchService.getById(guid);

        MyCrawler crawler = new MyCrawler("myCrawler", false, byId);
        crawler.setTFilePathService(tFilePathService);

        crawler.start(2);

        return ResponseData.builder().code("200").data(true).build();
    }

}
