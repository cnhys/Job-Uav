package com.vt.fencing.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vt.fencing.user.utils.AesUtils;
import com.vt.fencing.user.utils.PropertiesUtils;

import net.sf.json.JSONObject;

@Controller
public class SignatureController {
	
	
	@RequestMapping(value = {"/goFileList"})
	public String fileList(String data,HttpServletRequest request, HttpServletResponse response, Model model) {
		
		
		// 从config.properties配置文件中获取秘钥
		String sKey = PropertiesUtils.getProperties("/config.properties", "secret_key");
		System.out.println(sKey);
		
		try {
			// 解密经过Aes加密后的报文
			String decrypt = AesUtils.Decrypt(data, sKey);
			System.out.println(decrypt);
			
			// 解析报文
			JSONObject JsonObj = JSONObject.fromObject(decrypt);
			
			// 用户信息
			String user = JsonObj.getString("user");
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/jsp/index.jsp";
	}
	
}
