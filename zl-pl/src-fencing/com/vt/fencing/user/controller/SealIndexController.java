package com.vt.fencing.user.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.vt.fencing.IGatewayServiceDef;
import com.vt.fencing.model.SealInfo;
import com.vt.fencing.user.service.IVangoMemberService;
import com.vt.fencing.user.utils.PropertiesUtils;



@Controller
public class SealIndexController {
	
	/**
	 * 上传文件基础虚拟路径
	 */
	public static final String USERFILES_BASE_URL = "/sealPictures/";
	
	@Autowired
	@Qualifier(IGatewayServiceDef.MEMBER_SERVICE)
	private IVangoMemberService vangoMemberService;

	
	@RequestMapping(value = {"/sealIndex"})
	public String sealIndex(HttpServletRequest request, HttpServletResponse response, Model model) {

		return "/jsp/index.jsp";
	}
	
	@RequestMapping(value = {"/sealLogin"})
	public String sealLogin(String account, String pwd, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(account != null && account != "" && pwd != null && pwd != ""){
			String realPwd = PropertiesUtils.getProperties("/config_login.properties", "user_pwd");
			if(account.equals("admin") && pwd.equals(realPwd)){
				
				return "redirect:/sealList";
			}else {
				return "/jsp/index.jsp";
			}
		} 
		
		return "/jsp/index.jsp";
		
	}
	
	// 验证用户名密码是否正确
	@RequestMapping(value = {"/checkPwd"})
	@ResponseBody
	public int checkPwd(String account, String pwd){
		String realPwd = PropertiesUtils.getProperties("/config_login.properties", "user_pwd");
		if(account.equals("admin") && pwd.equals(realPwd)){
			System.out.println(account.equals("admin") && pwd.equals(realPwd));
			return 1;
		}
		return 0;
	}
		
	
	// 查询印章信息列表
	@RequestMapping(value = {"/sealList"})
	public String sealList(HttpServletRequest request, HttpServletResponse response, Model model , HttpSession session) {
		if(vangoMemberService.findSealInfoList().size() > 0){
			List<SealInfo> seaInfolList = vangoMemberService.findSealInfoList();
			for (SealInfo sealInfo : seaInfolList) {
				System.out.println(sealInfo);
			}
			session.setAttribute("sealInfolList", seaInfolList);
			session.setAttribute("name", "lisi");
//			request.setAttribute("sealInfolList", seaInfolList);
//			model.addAttribute("seaInfolList", seaInfolList);
		}else {
			model.addAttribute("sealInfolList", null);
		}
		return "/jsp/sealInfo.jsp";
	}
	
	// 保存印章信息
	@RequestMapping(value = {"/saveSeal"})
	public String saveSeal(SealInfo sealInfo, HttpServletRequest request, HttpServletResponse response,MultipartFile image) {
		
		
		Integer result = vangoMemberService.saveSealInfo(sealInfo);
		if( result > 0){
			return "redirect:/sealList";
		}
		return "/jsp/index.jsp";
	}
	
}

