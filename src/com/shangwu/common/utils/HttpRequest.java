package com.shangwu.common.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Map.Entry;

/**
 * 
 * http请求工具类
 *@Description:
 *@Author:youjian
 *@Since:2017年10月23日
 */
@Controller
public class HttpRequest {
	/**
	 * https post请求
	 * @param url
	 * @param params
	 * @param token 为工单系统请求的令牌
	 * @return
	 * @Author:youjian
	 * @Since:2017年10月23日
	 */
	public static String doPost(String url,Map<String,String> map,String token){  
		
        SSLClient httpClient = null;  
        HttpPost httpPost = null;  
        String result = null;  
        try{  
        
            httpClient = new SSLClient();  
            httpPost = new HttpPost(url);  
            httpPost.setHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8");
            httpPost.setHeader("token",token);
       
            //设置参数  
            List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();  
            Iterator iterator = map.entrySet().iterator();  
           
            while(iterator.hasNext()){  
                Entry<String,String> elem = (Entry<String, String>) iterator.next();  
                list.add(new BasicNameValuePair(elem.getKey(),elem.getValue()));  
            }  
         
            if(list.size() > 0){  
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,"utf-8");  
                httpPost.setEntity(entity);  
            }  
            HttpResponse response = httpClient.execute(httpPost);  
          
            if(response != null){  
                HttpEntity resEntity = response.getEntity();  
                if(resEntity != null){  
                    result = EntityUtils.toString(resEntity,"utf-8");  
                }  
            }  

        }catch(Exception ex){  
            ex.printStackTrace();
        	JsonObject resJson=new JsonObject();
			resJson.addProperty("success", false);
			resJson.addProperty("msg", "服务器请求异常:" + ex.getMessage());
			return resJson.toString();
        }  
        return result;  
    }

    /**
     *@author ruanhui
     *@date 2018/11/29
     *@description 推送降幅预警数据到工单系统
     */
    public static String pushDataToGongdan(String url,String param){

        SSLClient httpClient = null;
        HttpPost httpPost = null;
        String result = null;
        try{

            httpClient = new SSLClient();
            httpPost = new HttpPost(url);
            httpPost.setHeader("Content-Type","application/json;charset=utf-8");
            byte[] requestBytes = new byte[0]; // 将参数转为二进制流
            requestBytes = param.getBytes("utf-8");
            InputStream inputStream = new ByteArrayInputStream(requestBytes, 0,
                    requestBytes.length);
            InputStreamEntity inputStreamEntity = new InputStreamEntity(inputStream, requestBytes.length);
//            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,"utf-8");
            httpPost.setEntity(inputStreamEntity);
            HttpResponse response = httpClient.execute(httpPost);

            if(response != null){
                HttpEntity resEntity = response.getEntity();
                if(resEntity != null){
                    result = EntityUtils.toString(resEntity,"utf-8");
                }
            }

        }catch(Exception ex){
            ex.printStackTrace();
        	JsonObject resJson=new JsonObject();
			resJson.addProperty("success", false);
			resJson.addProperty("msg", "服务器请求异常:" + ex.getMessage());
			return resJson.toString();
        }
        return result;
    }
	/**
	 * http post请求
	 * @param url
	 * @param params
	 * @param token 为工单系统请求的令牌
	 * @return
	 * @Author:youjian
	 * @Since:2017年10月23日
	 */
	/*public static String doPost(String url, Map<String, String> params,String token) {  

       StringBuffer response = new StringBuffer();  
     
        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod(url);  
        // 设置Http Post数据  
        method.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8" );  
        method.setRequestHeader("token",token);  
        
        if(params != null){  
            Set<String> keySet = params.keySet();  
            NameValuePair[] param = new NameValuePair[keySet.size()];  
            int i = 0;  
            for(String key : keySet){  
                param[i] = new NameValuePair(key, params.get(key));  
                i++;  
            }  
            method.setRequestBody(param);  
        }  
        InputStream responseBodyStream = null;  
        InputStreamReader streamReader = null;  
        BufferedReader reader = null;  
        try {  
            client.executeMethod(method);  
            if (method.getStatusCode() == HttpStatus.SC_OK) {  
                responseBodyStream = method.getResponseBodyAsStream();  
                streamReader = new InputStreamReader(responseBodyStream, "utf-8");  
                reader = new BufferedReader(streamReader);  
                String line;  
                while ((line = reader.readLine()) != null) {  
                    response.append(line);  
                }  
            }  else{
            	JsonObject resJson=new JsonObject();
    			resJson.addProperty("success", false);
    			resJson.addProperty("msg", "服务器响应结果为:" + method.getStatusCode());
    			return resJson.toString();
            }
        } catch (Exception e) {
        	JsonObject resJson=new JsonObject();
			resJson.addProperty("success", false);
			resJson.addProperty("msg", "服务器请求异常:" + e.getMessage());
			return resJson.toString();
        } finally {  
            try {  
                responseBodyStream.close();  
                streamReader.close();  
                reader.close();  
            } catch (Exception e) {  
            	JsonObject resJson=new JsonObject();
    			resJson.addProperty("success", false);
    			resJson.addProperty("msg", "流关闭异常:" + e.getMessage());
    			return resJson.toString();
            }  
            method.releaseConnection();  
        }  
        return response.toString();  
  }*/  
	
	public static String doPost(String url, Map<String, String> params) {  

        StringBuffer response = new StringBuffer();  
        HttpClient client = new HttpClient();  
        PostMethod method = new PostMethod(url);  
        // 设置Http Post数据  
        method.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8" );  
    
        if(params != null){  
            Set<String> keySet = params.keySet();  
            NameValuePair[] param = new NameValuePair[keySet.size()];  
            int i = 0;  
            for(String key : keySet){  
                param[i] = new NameValuePair(key, params.get(key));  
                i++;  
            }  
            method.setRequestBody(param);  
        }  
        InputStream responseBodyStream = null;  
        InputStreamReader streamReader = null;  
        BufferedReader reader = null;  
        try {  
            client.executeMethod(method);  
            if (method.getStatusCode() == HttpStatus.SC_OK) {  
                responseBodyStream = method.getResponseBodyAsStream();  
                streamReader = new InputStreamReader(responseBodyStream, "utf-8");  
                reader = new BufferedReader(streamReader);  
                String line;  
                while ((line = reader.readLine()) != null) {  
                    response.append(line);  
                }  
            }  else{
            	JsonObject resJson=new JsonObject();
    			resJson.addProperty("success", false);
    			resJson.addProperty("msg", "服务器响应结果为:" + method.getStatusCode());
    			return resJson.toString();
            }
        } catch (Exception e) {
        	JsonObject resJson=new JsonObject();
			resJson.addProperty("success", false);
			resJson.addProperty("msg", "服务器请求异常:" + e.getMessage());
			return resJson.toString();
        } finally {  
            try {  
                responseBodyStream.close();  
                streamReader.close();  
                reader.close();  
            } catch (Exception e) {  
            	JsonObject resJson=new JsonObject();
    			resJson.addProperty("success", false);
    			resJson.addProperty("msg", "流关闭异常:" + e.getMessage());
    			return resJson.toString();
            }  
            method.releaseConnection();  
        }  
        return response.toString();  
    } 
    public static void main(String[] args) {
    	long start=System.currentTimeMillis();
     Map<String, String> params = new HashMap<String, String>();  
     	params.put("proc_code", "p-109");
        params.put("title", "流程实例测试");
        params.put("user_no", "FGS_13765131727");
        params.put("user_name", "张菊");
        params.put("node_code", "processDefineDiv_node_3");
        params.put("assign_user_no", "1");
        params.put("proc_vars", "1");
	 /*    params.put("id", "59f13ab4d6c8af001184cd1b");
	     params.put("user_no", "7539");
	     params.put("memo", "处理意见");
	     Map<String, Boolean> f=new HashMap<>();
	     f.put("flag", false);
	     params.put("params",new Gson().toJson(f));*/
   /* params.put("user_no", "1");
     params.put("page", "1");
     params.put("rows", "30");*/
   
     
   /*  params.put("node_code", "processDefineDiv_node_3");
     params.put("proc_task_id", "59efeb6b08a13b0011531368");*/
    // String result=doPost("http://10.201.253.162:30003/v1/task/todo",params);
      // String result=doPost("http://10.201.253.162:30003/v1/instance/next/nodeAnduser", params);
     // String result=doPost("http://192.168.1.114:3003/v1/task/complete", params);

       String result=doPost("https://135.10.82.76:8080/gdgl/api/process_instance/createAndAcceptAssign", params,"MdHk08AaAwif7X9s6VHPiDkKZ");
        JsonObject json=new JsonParser().parse(result).getAsJsonObject();
        System.out.println(json);   
		 /*JsonObject data=json.get("data").getAsJsonArray().get(0).getAsJsonObject();
		
         if(json.get("success").getAsBoolean()){
        	 JsonObject data1=json.get("data").getAsJsonArray().get(0).getAsJsonObject();
        	 String proc_inst_id=data1.get("proc_inst_id").getAsString();
        	  System.out.println(data);   
         }*/
 
         long end=System.currentTimeMillis();
         
    		 System.out.println(end-start);   

} 

}