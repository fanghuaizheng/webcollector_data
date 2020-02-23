package com.fhz.crawler.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fhz.crawler.demo.entity.TFilePath;
import com.fhz.crawler.demo.entity.User;
import com.fhz.crawler.demo.mapper.TFilePathMapper;
import com.fhz.crawler.demo.mapper.UserMapper;
import com.fhz.crawler.demo.service.TFilePathService;
import com.fhz.crawler.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author fanghuaizheng
 * @Description:
 * @date 2019-05-30 17:31
 */
@Service
@Slf4j
public class TFilePathServiceImpl extends ServiceImpl<TFilePathMapper, TFilePath> implements TFilePathService {

    private static final BlockingQueue<TFilePath> quene = new LinkedBlockingQueue<>();


    @Override
    public void addData(TFilePath tFilePath)  {
        try {
            quene.put(tFilePath);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    @PostConstruct
    public void initMethod() {
        Thread init = new Thread(()->{
            for (; !Thread.interrupted(); ) {
                try {
                    TFilePath entity = quene.take();
                    this.saveOrUpdate(entity);
                } catch (Exception e) {
                    log.error("日志记录发生错误\t{}", e.getCause());
                }
            }
        });

        init.setDaemon(true);
        init.start();
    }
}
