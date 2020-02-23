package com.fhz.crawler.demo.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * 发送http请求工具类
 */
public class HttpUtils {

    private static final Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    private static String CHARSET="UTF-8";

    private static String CONTENT_TYPE="applicationjson";

    /**
     * 发送get请求
     * @param url
     * @param params
     * @return
     */
    public static String doGet(String url, Map<String,Object> params){
        if (params!=null){

            url = getGetUrl(url+"?",params);
        }
        CloseableHttpClient client = getClient();

        HttpGet get = new HttpGet(url);

        String result = null;
        try {

            CloseableHttpResponse response = client.execute(get);

            HttpEntity entity = response.getEntity();

            if (entity!=null){
                result = EntityUtils.toString(entity,CHARSET);
            }

        }catch (Exception e){
            logger.info("请求发送错误"+url,e);
        }finally {
            closeClient(client);
            return result;

        }




    }


    /**
     * post请求
     * @param url
     * @param params
     * @param isForm 是否以form表单方式提交
     * @return
     */
    public static String doPost(String url,Map<String,Object> params,Boolean isForm){
        String result = null;
        CloseableHttpClient client = getClient();
        try {

            HttpPost post = new HttpPost(url);

            if (isForm){

                List<BasicNameValuePair> formDatas = new ArrayList<>();

                for (Map.Entry<String ,Object> entry:params.entrySet()){

                    formDatas.add(new BasicNameValuePair(entry.getKey(),entry.getValue().toString()));
                }

                UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(formDatas);
                post.setEntity(urlEncodedFormEntity);

            }else {
                post.setEntity(new StringEntity(JSONObject.toJSONString(params)));
            }

            CloseableHttpResponse response = client.execute(post);

            HttpEntity entity = response.getEntity();

            if (entity!=null){
                result = EntityUtils.toString(entity,CHARSET);
            }


        }catch (Exception e){
            logger.info("POST请求发生错误\t"+url+ "参数：\t"+JSONObject.toJSONString(params),e);
        }finally {
            closeClient(client);
            return result;

        }

    }



    private static String getGetUrl(String url,Map<String,Object> params){
        if (params!=null){
            Set<Map.Entry<String, Object>> entrySet = params.entrySet();
            Iterator<Map.Entry<String, Object>> iterator = entrySet.iterator();

            while (iterator.hasNext()){
                Map.Entry<String, Object> entry = iterator.next();
                url = url.concat(entry.getKey()).concat("=").concat(entry.getValue().toString());
                if (iterator.hasNext()){
                    url = url.concat("&");
                }
            }


        }
        return url;
    }

    private static CloseableHttpClient getClient(){
        return HttpClientBuilder.create().build();
    }

    private static void closeClient(CloseableHttpClient client){

        try {

            if (client!=null){
                client.close();
            }
        }catch (Exception e){

            logger.info("报错了",e);
        }
    }




    public static void main(String[] args) {

//        String url = "http://localhost:8282/appframe-web/u/submitLogin.do";
//
//        Map<String,Object> params = new HashMap<>();
//        params.put("ciphername","fhz112");
//        params.put("ciphercode","c4ca4238a0b923820dcc509a6f75849b");
//        params.put("rememberMe","false");
//        String result = doPost(url,params,true);
//        System.out.println(result);


        String menuDataUrl = "http://localhost:8282/appframe-web/platform/appframe/afauser/getMenuRelation.do";

         Map<String,Object> param1 = new HashMap<>();

         param1.put("userCode","fhz1");

//         String menuData = doPost(menuDataUrl,param1,true);

//        JSONObject menu = transMenu(menuData);


//        System.out.println("获取到菜单信息:\t"+menu);

    }

}
