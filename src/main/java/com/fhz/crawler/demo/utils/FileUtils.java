package com.fhz.crawler.demo.utils;

import lombok.experimental.UtilityClass;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author fanghuaizheng
 * @Description:
 * @date 2019-06-04 20:43
 */
@UtilityClass
public class FileUtils {


    /**
     * 保存文本到本地
     * @param content 文本内容
     * @param path 本地地址
     */
    public void saveTxt(String content,String path) {

        try {
            content = content+"\r\n";
            org.apache.commons.io.FileUtils.writeByteArrayToFile(new File(path),content.getBytes(),true);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     *
     * 保存图片到本地
     * @param urlList 图片url地址
     * @param path 本地地址
     */
    public void saveImgVideoSound(String urlList,String path) {
        URL url = null;
        try {
            url = new URL(urlList);
            DataInputStream dataInputStream = new DataInputStream(url.openStream());

            FileOutputStream fileOutputStream = new FileOutputStream(new File(path));
            ByteArrayOutputStream output = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int length;

            while ((length = dataInputStream.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            fileOutputStream.write(output.toByteArray());
            dataInputStream.close();
            fileOutputStream.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {

        //正则
        String reg = "setCssToHead\\((.+?)\\)";

        //将文件读入内存
        String s = org.apache.commons.io.FileUtils.readFileToString(new File("app-wxss.js"),"UTF-8");

        //使用java内置正则框架，匹配文件
        Matcher matcher = Pattern.compile(reg).matcher(s);

        List<String> list = new ArrayList<>();

        while (matcher.find()){
            list.add(matcher.group());
        }

        //输出匹配到信息
        list.forEach(x->{
            System.out.println(x);
        });



    }


}
