package com.vt.base.util;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * <h1>采集工具类</h1>
 * <p>
 * 根据给定的URL和字符编码采集数据
 * </p>
 * User: zhangtao
 * Date: 13-12-7
 * Time: 下午10:44
 */
public abstract class FetchUtils {
    /**
     * logger
     */
    private static final Logger logger = LoggerFactory.getLogger(FetchUtils.class);

    /**
     * 使用给定方法、给定字符集采集给定URL地址的内容
     *
     * @param url
     * @param encoding
     * @return
     */
    public static String fetch(String url, String encoding, boolean trim) {
        // http client
        HttpClient client = new DefaultHttpClient();
        client.getParams().setIntParameter("http.socket.timeout", 5000);
        client.getParams().setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS, true);
        // request
        HttpPost post = new HttpPost(url);
        // response
        HttpResponse response = null;
        // execute
        try {
            response = client.execute(post);
        } catch (ClientProtocolException e) {
            logger.error("使用协议错误!给定的URL为{}", url, e);
            throw new RuntimeException(e);
        } catch (IOException e) {
            logger.error("发生IO异常!", e);
            throw new RuntimeException(e);
        }
        // get data from response
        if (response == null) {
            return null;
        }
        // entity
        HttpEntity entity = response.getEntity();
        if (entity == null) {
            return null;
        }
        // get content
        String usedEncoding = null;
        // header
        Header encodingHeader = entity.getContentEncoding();
        if (encodingHeader != null) {
            usedEncoding = encodingHeader.getValue();
        } else {
            usedEncoding = encoding;
        }
        // read as string
        String content = null;
        try {
            content = EntityUtils.toString(entity, usedEncoding);
            if (trim) {
                // 替换tab,回车
                content = content.replaceAll("(\r\n|\r|\n|\n\r|\t)", " ");
                // 替换空格
                content = content.replaceAll("[ ]{2,}", " ");
            }
        } catch (IOException e) {
            logger.error("读取IO流发生异常!", e);
            return null;
        }
        return content;
    }
    /**
     * 
     * @param postData
     * @param postUrl
     * @return
     */
    public static String SMS(String postData, String postUrl) {
        try {
            //发送POST请求
            URL url = new URL(postUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setUseCaches(false);
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Length", "" + postData.length());
            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            out.write(postData);
            out.flush();
            out.close();
            //获取响应状态
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                System.out.println("connect failed!");
                return "";
            }
            //获取响应内容体
            String line, result = "";
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            while ((line = in.readLine()) != null) {
                result += line + "\n";
            }
            in.close();
            return result;
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
        return "";
    }
}