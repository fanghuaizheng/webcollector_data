package com.fhz.crawler.demo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fhz.crawler.demo.entity.TFilePath;
import com.fhz.crawler.demo.service.TFilePathService;
import com.fhz.crawler.demo.utils.EasyUIData;
import com.fhz.crawler.demo.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fanghuaizheng
 * @Description:
 * @date 2019-06-05 19:40
 */
@RestController
@RequestMapping("path")
public class TFilePathController {

    @Autowired
    TFilePathService tFilePathService;

    @RequestMapping("pageData")
    public Object pageData(PageUtils<TFilePath> pageUtils){

        Page<TFilePath> page = pageUtils.easyUiToPage();

        IPage<TFilePath> pageData = tFilePathService.page(page);

        pageUtils.setData(pageData.getRecords());

        return new EasyUIData(pageData);


    }

}
