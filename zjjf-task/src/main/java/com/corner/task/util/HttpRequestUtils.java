package com.corner.task.util;

import com.corner.task.beans.msg.ModelMsg;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URLDecoder;
 
@SuppressWarnings("deprecation")
public class HttpRequestUtils {
    private static Logger logger = LoggerFactory.getLogger(HttpRequestUtils.class);    //日志记录
 
    /**
     * httpPost
     * @param url  路径
     * @param jsonParam 参数
     * @return
     */
    public static Object httpPost(String url,Object jsonParam){
        return httpPost(url, jsonParam, false);
    }
 
    /**
     * post请求
     * @param url         url地址
     * @param jsonParam     参数
     * @param noNeedResponse    不需要返回结果
     * @return
     */
    @SuppressWarnings({"resource" })
	public static Object httpPost(String url,Object jsonParam, boolean noNeedResponse){
        //post请求返回结果
    	CloseableHttpClient httpClient = new DefaultHttpClient();
        Object jsonResult = null;
        HttpPost method = new HttpPost(url);
        try {
            if (null != jsonParam) {
                //解决中文乱码问题
                StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/json");
                method.setEntity(entity);
                logger.info("url：{} ,参数：{}" , method.getURI() , EntityUtils.toString(method.getEntity()));
            }
            HttpResponse result = httpClient.execute(method);
            url = URLDecoder.decode(url, "UTF-8");
            /**请求发送成功，并得到响应**/
            if (result.getStatusLine().getStatusCode() == 200) {
                String str = "";
                try {
                    /**读取服务器返回过来的json字符串数据**/
                    str = EntityUtils.toString(result.getEntity()).replaceAll("()", "");
                    if("(".equals(str.substring(0 , 1)))
                    	str = str.substring(1 , str.length() -1);
                    if (noNeedResponse) {
                        return null;
                    }
                    /**把json字符串转换成json对象**/
                    jsonResult = JSONUtil.JSONToObject(str, Object.class);
                } catch (Exception e) {
                    logger.error("post请求提交失败:" + url, e);
                }
            }else{
                jsonResult = new ModelMsg(false, "请求响应失败：错误码("+result.getStatusLine().getStatusCode()+")");
            }
        } catch (IOException e) {
            jsonResult = new ModelMsg(false, "post请求提交失败:" + e.getMessage());
            logger.error("post请求提交失败:" + url, e);
        }
        return jsonResult;
    }
 
 
    /**
     * 发送get请求
     * @param url    路径
     * @return
     */
    @SuppressWarnings("resource")
	public static Object httpGet(String url){
        //get请求返回结果
        Object jsonResult = null;
        try {
        	CloseableHttpClient client = new DefaultHttpClient();
            //发送get请求
            HttpGet request = new HttpGet(url);
            logger.info("url：{}" , request.getURI());
            HttpResponse response = client.execute(request);
 
            /**请求发送成功，并得到响应**/
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                /**读取服务器返回过来的json字符串数据**/
                String strResult = EntityUtils.toString(response.getEntity());
                /**把json字符串转换成json对象**/
                jsonResult = JSONUtil.JSONToObject(strResult, Object.class);
                url = URLDecoder.decode(url, "UTF-8");
            } else {
                logger.error("get请求提交失败:" + url);
            }
        } catch (IOException e) {
            logger.error("get请求提交失败:" + url, e);
        }
        return jsonResult;
    }
}