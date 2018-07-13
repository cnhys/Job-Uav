package com.vt.base.util;

import java.net.URL;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class GetLocation {
//    public static void main(String[] args) {  
//        // lat 39.97646       
//        //log 116.3039   
//        String add = getAdd("116.3039", "39.97646");  
//       
//    }  
	
//	public static void main(String[] args) {  
//        System.out.println(GetLocation.GetDistance(29.490295,106.486654,29.615467,106.581515));  
//    }
	
	private static double EARTH_RADIUS = 6371.393;  
	
    private static double rad(double d)  
    {  
       return d * Math.PI / 180.0;  
    } 
      
    public static String[] getAdd(String lng, String lat ){  
        //lat 小  log  大  
        //参数解释: 纬度,经度 type 001 (100代表道路，010代表POI，001代表门址，111可以同时显示前三项)  
        String urlString = "http://gc.ditu.aliyun.com/regeocoding?l="+lat+","+lng+"&type=010";  
        String res = "";     
        try {     
            URL url = new URL(urlString);    
            java.net.HttpURLConnection conn = (java.net.HttpURLConnection)url.openConnection();    
            conn.setDoOutput(true);    
            conn.setRequestMethod("POST");    
            java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(conn.getInputStream(),"UTF-8"));    
            String line;    
           while ((line = in.readLine()) != null) {    
               res += line+"\n";    
         }    
            in.close();    
        } catch (Exception e) {    
            System.out.println("error in wapaction,and e is " + e.getMessage());    
        }   
//        System.out.println(res);  
        
        JSONObject jsonObject = JSONObject.parseObject(res);  
        JSONArray jsonArray = JSONArray.parseArray(jsonObject.getString("addrList"));  
        JSONObject j_2 = JSONObject.parseObject(jsonArray.get(0).toString());  
        String allAdd = j_2.getString("admName");  
        String arr[] = allAdd.split(",");  
        System.out.println("省："+arr[0]+"\n市："+arr[1]+"\n区："+arr[2]);  
        
        return arr;    
    }  
    
    public static double GetDistance(double lng1, double lat1, double lng2, double lat2)  
    {  
       double radLat1 = rad(lat1);  
       double radLat2 = rad(lat2);  
       double a = radLat1 - radLat2;      
       double b = rad(lng1) - rad(lng2);  
       double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) +   
        Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));  
       s = s * EARTH_RADIUS;  
       s = Math.round(s * 1000);  
       return s/1000;  
    } 
}