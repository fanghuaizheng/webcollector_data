package com.fhz.crawler.demo.crawler;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;
import com.alibaba.fastjson.JSONObject;
import com.fhz.crawler.demo.entity.TCrawlerMatch;
import com.fhz.crawler.demo.entity.TFilePath;
import com.fhz.crawler.demo.service.TFilePathService;
import com.fhz.crawler.demo.utils.FileUtils;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.Data;
import org.apache.commons.lang.StringUtils;
import org.jsoup.select.Elements;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author fanghuaizheng
 * @Description:
 * @date 2019-06-04 20:48
 */
@Data
public class MyCrawler extends BreadthCrawler {
    public static final String WB_FILE_PATH= "test.txt";
    public static final String TP_FILE_PATH = "img/";
    public static final String YSP_GILE_PATH = "video/";

    private TFilePathService tFilePathService;


    private static AtomicInteger index = new AtomicInteger(0);


    /**
     * 加载各种条件还有抓取url
     * @return
     */
    public CrawlerParam getData(TCrawlerMatch match){
        CrawlerParam param = new CrawlerParam();
        param.setUrls(Arrays.asList(match.getUrls().split(",")));
        param.setKey(match.getDeKey());
        param.setInfoType(match.getInfoType());
        return param;
    }

    /**
     * 加载各种条件还有抓取url
     * @return
     */
    public CrawlerParam getData(){
        CrawlerParam param = new CrawlerParam();
        return param;
    }


    public MyCrawler(String crawlPath, boolean autoParse,TCrawlerMatch match){
        super(crawlPath,autoParse);
        CrawlerParam data = getData(match);
        String listVal = null;
        switch (data.getInfoType()){
            case 1:
                listVal = "textList";
                break;
            case 2:
                listVal = "textList";
                break;

            case 3:
                listVal = "soundList";break;

            case 4:
                listVal = "videoList";break;

        }
        String[] urls = match.getUrls().split(",");
        for (String item:urls){
            CrawlDatum datum = new CrawlDatum(item,listVal);
            datum.meta("param",JSONObject.toJSONString(getData(match)));
            addSeed(datum);
        }
    }

    public MyCrawler(String crawlPath, boolean autoParse){

        super(crawlPath,autoParse);

        //获取参数
        getData();

        //文本、图片抓取地址
        CrawlDatum datum = new CrawlDatum("https://news.baidu.com/","textList");
        datum.meta("param",JSONObject.toJSONString(getData()));
        datum.setExecuteTime(1000L);


        addSeed(datum);

//        //音频抓取地址
//        addSeed("http://sc.chinaz.com/tag_yinxiao/XinWen.html","soundList");
//
//        //视频抓取
//        addSeed("http://www.v1.cn/","videoList");


        addRegex(".*");
        //通过getConf方法，可以设置爬虫网络请求参数
        // 如设置超时时间
        getConf().setConnectTimeout(20);
        //设置线程数
        setThreads(30);


    }

    private void doPhoto(Page page){
        page.select("img").iterator().forEachRemaining(x ->{
            String url = x.absUrl("src");
            if (StringUtils.isNotBlank(url)){
                String[] splits = url.split("/");
                String name = splits[splits.length-1].split("\\?|@|%")[0];

                tFilePathService.addData(TFilePath.builder().fileUrl(url).filePath(System.getProperty("user.dir")+"/"+TP_FILE_PATH+name).build());
                FileUtils.saveImgVideoSound(url,TP_FILE_PATH+name);
            }

        });
    }

    @Override
    public void visit(Page page, CrawlDatums crawlDatums) {

        index.incrementAndGet();

        CrawlerParam crawlerParam = JSONObject.parseObject(page.meta("param"), CrawlerParam.class);


        if (page.matchType("textList")){//文本列表页

            doPhoto(page);

            Elements select = page.select("a");
            select.iterator().forEachRemaining(item ->{
                String url = item.absUrl("href");
                String title = item.text();
                if (url!=null){
                    if (crawlerParam!=null&&StringUtils.isNotBlank(crawlerParam.getKey())){
                        if (title.indexOf(crawlerParam.getKey())>-1){
                            //对指定key进行处理
                            crawlDatums.add(url,"textDetail").meta("param",
                                    JSONObject.toJSONString(crawlerParam));
                        }
                    }else {
                        crawlDatums.add(url,"textDetail").meta("param",
                                JSONObject.toJSONString(crawlerParam));
                    }
                }
            });
        }else if (page.matchType("soundList")){//音频列表页

            //增加下一页数据
            page.select(".nextpage").iterator().forEachRemaining(item ->{
                String url = item.absUrl("href");
                if (StringUtils.isNotBlank(url)){
                    crawlDatums.add(url,"soundList").meta("param",
                            JSONObject.toJSONString(crawlerParam));
                }
            });

            //增加详情页
            page.select(".audio-player").iterator().forEachRemaining(x->{
                crawlDatums.add(x.absUrl("href"),"soundDetail").meta("param",
                        JSONObject.toJSONString(crawlerParam));
            });

        }else if (page.matchType("textDetail")){//文本详情页
            //详情页抓取
            String content = page.select(".content").text();

            doPhoto(page);



            if (StringUtils.isNotBlank(content)){
                Boolean hasSave = false;

                if (crawlerParam!=null){
                    if (crawlerParam.getKey()!=null&&content.indexOf(crawlerParam.getKey())>-1){
                        hasSave = true;
                        FileUtils.saveTxt(content,WB_FILE_PATH);
                    }else if (crawlerParam.getKey()==null){
                        hasSave = true;
                        FileUtils.saveTxt(content,WB_FILE_PATH);
                    }
                }else {
                    hasSave = true;
                    FileUtils.saveTxt(content,WB_FILE_PATH);
                }
                if (hasSave){
                    tFilePathService.addData(TFilePath.builder().fileUrl(page.url()).filePath(System.getProperty("user.dir")+"/"+WB_FILE_PATH).build());

                }
            }
        }else if (page.matchType("videoList")){//视频列表页

            //视频解析
            page.select("a").iterator().forEachRemaining( item ->{
                if (StringUtils.equalsIgnoreCase(item.attr("target"),"_blank")){
                    //这是更多界面
                    if (StringUtils.equalsIgnoreCase("colMore gray2",item.attr("class"))){
                        crawlDatums.add(item.absUrl("href"),"Videolist").meta("param",
                                JSONObject.toJSONString(crawlerParam));
                    }else if (StringUtils.isNotBlank(item.absUrl("href"))){
                        crawlDatums.add(item.absUrl("href"),"videoDetail").meta("param",
                                JSONObject.toJSONString(crawlerParam));
                    }
                }
            });

        }else if (page.matchType("soundDetail")){//音频详情页

            //音频操作
            Elements select = page.select(".musi");

            String downloadUrl = select.attr("thumb");

            String name = page.select("body > div.all_wrap > div.down_wrap > div.left > div.text_wrap > h2 > a").text();

            tFilePathService.addData(TFilePath.builder().fileUrl(downloadUrl).filePath(System.getProperty("user.dir")+"/"+YSP_GILE_PATH+name+".mp3").build());

            FileUtils.saveImgVideoSound(downloadUrl,YSP_GILE_PATH+name+".mp3");

        }else if (page.matchType("videoDetail")){//视频详情页
            page.select("embed").iterator().forEachRemaining(item ->{
                String flashvars = item.attr("flashvars");

                String[] downloadUrl = flashvars.split("videoUrl=");
                if (downloadUrl.length==2){
                    String url = downloadUrl[1];
                    String[] urlSp = url.split("/");
                    String name = urlSp[urlSp.length-1];

                    Boolean hasSave = false;


                    if (crawlerParam!=null){
                        if (crawlerParam.getKey()!=null&&StringUtils.equalsIgnoreCase(name,crawlerParam.getKey())){
                            hasSave = true;
                            FileUtils.saveImgVideoSound(downloadUrl[1],YSP_GILE_PATH+name);
                        }else if (crawlerParam.getKey()==null){
                            hasSave = true;
                            FileUtils.saveImgVideoSound(downloadUrl[1],YSP_GILE_PATH+name);
                        }
                    }else{
                        hasSave = true;
                        FileUtils.saveImgVideoSound(downloadUrl[1],YSP_GILE_PATH+name);
                    }
                    if (hasSave){
                        tFilePathService.addData(TFilePath.builder().fileUrl(downloadUrl[1]).filePath(System.getProperty("user.dir")+"/"+YSP_GILE_PATH+name).build());

                    }




                }

            });
        }


    }

    public static void main(String[] args) throws Exception {

        MyCrawler crawler = new MyCrawler("myCrawler", false);

        crawler.start(2);

    }

}
