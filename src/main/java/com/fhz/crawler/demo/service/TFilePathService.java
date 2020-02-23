package com.fhz.crawler.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fhz.crawler.demo.entity.TFilePath;
import com.fhz.crawler.demo.entity.User;

/**
 * @author fanghuaizheng
 * @Description:
 * @date 2019-05-30 17:31
 */
public interface TFilePathService extends IService<TFilePath> {

    void addData(TFilePath tFilePath);

    void initMethod();

}
