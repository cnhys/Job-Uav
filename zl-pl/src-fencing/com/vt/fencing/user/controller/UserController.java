package com.vt.fencing.user.controller;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.vt.base.IServiceDef;
import com.vt.base.OptResult;
import com.vt.base.PageData;
import com.vt.base.PageRequest;
import com.vt.base.controller.BaseGatewayController;
import com.vt.base.util.DateUtil;
import com.vt.base.util.DigestUtils;
import com.vt.base.util.JsonUtil;
import com.vt.base.util.StringUtil;
import com.vt.fencing.IFencingConst;
import com.vt.fencing.IGatewayServiceDef;
import com.vt.fencing.model.AaRouteLog;
import com.vt.fencing.model.AaUserLogin;
import com.vt.fencing.model.VangoMember;
import com.vt.fencing.model.VangoMemberExample;
import com.vt.fencing.model.vo.HomePageInfoResponse;
import com.vt.fencing.model.vo.InformationRes;
import com.vt.fencing.model.vo.LogResult;
import com.vt.fencing.model.vo.WeatherInfo;
import com.vt.fencing.model.vo.WeatherResponse;
import com.vt.fencing.request.MemberRequest;
import com.vt.fencing.request.OperatorRequest;
import com.vt.fencing.request.ValidCodeRequest;
import com.vt.fencing.user.service.IVangoMemberRedisService;
import com.vt.fencing.user.service.IVangoMemberService;
import com.vt.fencing.user.util.MemberSessionUtil;
import com.vt.fencing.user.util.OperatorSessionUtil;
import com.vt.fencing.user.util.PagingResult;
import com.vt.fencing.user.util.WrjResult;
import com.vt.fencing.user.util.WyMessageUtils;
import com.vt.user.model.OperRoleRel;
import com.vt.user.model.Operator;
import com.vt.user.model.OperatorExample;
import com.vt.user.service.IOperatorService;

/**
 * user related functions Created by john on 16/11/18.
 */
@Controller
public class UserController extends BaseGatewayController {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7720698921084072156L;

	/**
	 * member service
	 */
	@Autowired
	@Qualifier(IGatewayServiceDef.MEMBER_SERVICE)
	private IVangoMemberService vangoMemberService;

	/**
	 * member redis service
	 */
	@Autowired
	@Qualifier(IGatewayServiceDef.MEMBER_REDIS_SERVICE)
	private IVangoMemberRedisService vangoMemberRedisService;

	/**
	 * operator service
	 */
	@Autowired
	@Qualifier(IServiceDef.OPERATOR_SERVICE)
	private IOperatorService operatorService;
	

	/**
	 * 无人机登录
	 * 
	 * @param user
	 * @param res
	 * @param req
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = { "/api/member/login" }, method = { RequestMethod.POST })
	@ResponseBody
	public WrjResult userLogin(AaUserLogin user,HttpServletResponse res, HttpServletRequest req) throws IOException {
		String userLoginAccount = req.getParameter("userLoginAccount");
		String userPwd = req.getParameter("userPwd");
		
		
		if (StringUtils.isBlank(userLoginAccount)) {
			return WrjResult.build(400, "用户名不能为空");
		} else if (StringUtils.isBlank(userPwd)) {
			return WrjResult.build(400, "密码不能为空");
		} else {
			user.setUserLoginAccount(userLoginAccount);
			user.setUserPwd(userPwd);

			return vangoMemberService.userLogin(user);
		}

	}
	
	/**
	 * 上传头像
	 * @param res
	 * @param req
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value = { "/api/member/uploadHeadPhoto" }, method = { RequestMethod.POST })
	@ResponseBody
	public WrjResult uploadHeadPhoto(HttpServletResponse res, HttpServletRequest req) throws IOException {
		String userIdStr = req.getParameter("userId");
		if(StringUtils.isBlank(userIdStr)){
			return WrjResult.build(400, "用户id不能为空", "");
		}
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
		MultipartFile file = multipartRequest.getFile("file");
		
		Integer userId = Integer.parseInt(userIdStr);
		
		if(file != null && !file.isEmpty()){
			Map<String, Object> resMap = uploadHeadSculpture(file);
			String photoPath = null;
			if(StringUtils.isNotBlank((String) resMap.get("path"))){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("userId", userId);
				// 获取本机的IP
				InetAddress ia = null;
				String localip = "";
		        try {
		            ia=ia.getLocalHost();
		            localip = ia.getHostAddress();
		            System.out.println("本机的ip是 ："+localip);
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
//		        photoPath = "http://" + localip + ":8888/nginx/headSculpture/" + resMap.get("trueFileName").toString();
//		        photoPath = "http://101.200.172.176:8888/nginx/headSculpture/" + resMap.get("trueFileName").toString();
		        photoPath = "http://111.230.242.15:8888/nginx/headSculpture/" + resMap.get("trueFileName").toString();
		        System.out.println(photoPath);
				map.put("userHead", photoPath);
				Integer result = vangoMemberService.updateUserHeadByUserId(map);
				
				if(result > 0){
					return WrjResult.build(200, "头像上传成功", photoPath);
				}
			}
		}
		
		return WrjResult.build(400, "头像上传失败", "");
		
	}
	
	
	public Map<String, Object> uploadHeadSculpture(MultipartFile file) throws IllegalStateException, IOException{
		Map<String, Object> map = new HashMap<String, Object>();
//		String path = "D:/imgs/"; // 定义文件路径
		String path = "C:/WrjPicture/headSculpture/";
		if (file != null) {// 判断上传的文件是否为空
			String type = null;// 文件类型
			String fileName = file.getOriginalFilename();// 文件原名称
			System.out.println("上传的文件原名称:" + fileName);
			// 判断文件类型
			type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length())
					: null;
			if (type != null) {// 判断文件类型是否为空
				if ("GIF".equals(type.toUpperCase()) || "PNG".equals(type.toUpperCase())
						|| "JPG".equals(type.toUpperCase())) {
					// 项目在容器中实际发布运行的根路径
//					String realPath = request.getSession().getServletContext().getRealPath("/");
					// 自定义的文件名称
					String trueFileName = String.valueOf(System.currentTimeMillis()) + fileName;
					// 设置存放图片文件的路径
					/*
					path = realPath
							+  System.getProperty("file.separator")+ trueFileName;
					*/
					map.put("trueFileName", trueFileName);
					path = path + trueFileName;
					System.out.println("存放图片文件的路径:" + path);
					// 转存文件到指定的路径
					file.transferTo(new File(path));
					System.out.println("文件成功上传到指定目录下");
				} else {
					System.out.println("不是我们想要的文件类型,请按要求重新上传");
					return null;
				}
			} else {
				System.out.println("文件类型为空");
				return null;
			}
		} else {
			System.out.println("没有找到相对应的文件");
			return null;
		}
		map.put("path", path);
		return map;
	}
	
	
	
	

	/**
	 * 个人注册
	 * 
	 * @param res
	 * @param req
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = { "/api/member/personalRegister" }, method = { RequestMethod.POST })
	@ResponseBody
	public WrjResult userZc(HttpServletResponse res, HttpServletRequest req) throws IOException {
		String code = req.getParameter("code");
		String userName = req.getParameter("userName");
		String userCardTypeStr = req.getParameter("userCardType");
		String userCardCode = req.getParameter("useCardCode");
		String userFszh = req.getParameter("userFszh");
		String userMobilePhone = req.getParameter("userMobilePhone");
		String userPwd = req.getParameter("userPwd");
		
		if (StringUtils.isBlank(userCardTypeStr)) {
			return WrjResult.build(400, "证件类型不能为空");
		}
		Integer userCardType = Integer.parseInt(userCardTypeStr);
		
		AaUserLogin user = new AaUserLogin();
		user.setUserName(userName);
		user.setUserCardType(userCardType);
		user.setUserCardCode(userCardCode);
		user.setUserFszh(userFszh);
		user.setUserMobilePhone(userMobilePhone);
		user.setUserPwd(userPwd); 
		user.setUserType(1);
		
		// 验证验证码
		if(req.getSession().getAttribute("code") != null && req.getSession().getAttribute("code") != ""){
			
			String sessionCode = req.getSession().getAttribute("code").toString();
			if (code != null && !"".equals(code) && sessionCode != null && !"".equals(sessionCode)) {
	            if (code.equalsIgnoreCase(sessionCode)) {
	            	// 验证通过，无效化验证码
	            	req.getSession().setAttribute("code", null);
	            	
	        		WrjResult result = vangoMemberService.createUserPr(user);
	        		
	        		return result;
	            } else {
	                return WrjResult.build(400, "验证码错误");
	            }
	        } else {
	        	return WrjResult.build(400, "验证码错误");
	        }
		
		}else {
			return WrjResult.build(400, "请发送验证码", "");
		}
		
	}
	
	/**
	 * 获取验证码
	 * @param res
	 * @param req
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = { "/api/member/getCode" }, method = { RequestMethod.POST })
	@ResponseBody
	public WrjResult getIdentifyingCode(HttpServletResponse res, HttpServletRequest req) throws IOException {
		String phone = req.getParameter("phone");
		String code = "";
		
		if (StringUtils.isBlank(phone)) {
			return WrjResult.build(400, "电话号码不能为空", "");
		}else {
			//验证手否是手机号
			Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
			if( p.matcher(phone).matches() ){
				// 调用网易云短信API。
				try {
					String responseEntity = WyMessageUtils.SendCode(phone);
					String statusCode = JSON.parseObject(responseEntity).getString("code");
			        if (statusCode.equals("200")) {
			        	code = JSON.parseObject(responseEntity).getString("obj");
			        	if(code != null){
			    			HttpSession session = req.getSession();
			    			session.setAttribute("code", code);
//			    			System.out.println(code);
//				            System.out.println("sucess");
			    			
			    			return WrjResult.build(200, "验证码发送成功", "");
			    		}else {
			    			return WrjResult.build(400, "验证码发送失败", "");
			    		}
			        }else {
			        	return WrjResult.build(400, "验证码发送失败", "");
					}
					
				} catch (Exception e) {
					e.printStackTrace();
					return WrjResult.build(400, "验证码发送失败", "");
				}
			}else {
				return WrjResult.build(400, "电话号码格式不对", "");
			}
		}
		
		
		
	}
	
	

	/**
	 * 企业注册
	 * 
	 * @param res
	 * @param req
	 * @return
	 * @throws IOException
	 */

	@SuppressWarnings("static-access")
	@RequestMapping(value = { "/api/member/enterpriseRegister" }, method = { RequestMethod.POST })
	@ResponseBody
	public WrjResult userZcBusiness(HttpServletResponse res, HttpServletRequest req) throws IOException {
		String userDep = req.getParameter("userDep");
		String legalPersonName = req.getParameter("legalPersonName");
		String cuscc = req.getParameter("cuscc");
		String userMobilePhone = req.getParameter("userMobilePhone");
		String userEmail = req.getParameter("userEmail");
		String userAddress = req.getParameter("userAddress");
		String userPwd = req.getParameter("userPwd");
		String code = req.getParameter("code");
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
		
		MultipartFile file1 = multipartRequest.getFile("file1");
		MultipartFile file2 = multipartRequest.getFile("file2");

		// 验证验证码
		if(req.getSession().getAttribute("code") != null && req.getSession().getAttribute("code") != ""){
		
			String sessionCode = req.getSession().getAttribute("code").toString();
			if (code != null && !"".equals(code) && sessionCode != null && !"".equals(sessionCode)) {
	            if (code.equalsIgnoreCase(sessionCode)) {

	            	// 验证通过，无效化验证码
	            	req.getSession().setAttribute("code", null);
	            	
	            	// 获取本机的IP
					InetAddress ia = null;
					String localip = "";
			        try {
			            ia=ia.getLocalHost();
			            localip = ia.getHostAddress();
			            System.out.println("本机的ip是 ："+localip);
			        } catch (Exception e) {
			            e.printStackTrace();
			        }
	            	
	        		// 第二张图片上传的路径
	        		String photoPath2 = "";
	        		if(file2 != null && !file2.isEmpty()){
//	        			photoPath2 = "http://" + localip + ":8888/nginx/businessLicence/" + uploadBusinessLicence(file2).get("trueFileName").toString();
//	        			photoPath2 = "http://101.200.172.176:8888/nginx/businessLicence/" + uploadBusinessLicence(file2).get("trueFileName").toString();
	        			photoPath2 = "http://111.230.242.15:8888/nginx/businessLicence/" + uploadBusinessLicence(file2).get("trueFileName").toString();
	        		}
	        		
	        		// 营业照片路径 
	        		String businessLicense = null;
	        		String photoPath1 = "";
	        		if( file1 == null || file1.isEmpty() || uploadBusinessLicence(file1)==null || uploadBusinessLicence(file1).size() <= 0){
	        			return WrjResult.build(400, "营业执照上传失败");
	        		}else {
//	        			photoPath1 = "http://" + localip + ":8888/nginx/businessLicence/" + uploadBusinessLicence(file1).get("trueFileName").toString();
//	        			photoPath1 = "http://101.200.172.176:8888/nginx/businessLicence/" + uploadBusinessLicence(file1).get("trueFileName").toString();
	        			photoPath1 = "http://111.230.242.15:8888/nginx/businessLicence/" + uploadBusinessLicence(file1).get("trueFileName").toString();
	        			businessLicense = photoPath1 + "," + photoPath2;
	        			
	        			System.out.println(businessLicense);
	        			
	        			
	        			AaUserLogin user = new AaUserLogin();
	        			user.setUserDep(userDep);
	        			user.setLegalPersonName(legalPersonName);
	        			user.setCuscc(cuscc);
	        			user.setUserMobilePhone(userMobilePhone);
	        			user.setUserEmail(userEmail);
	        			user.setUserAddress(userAddress);
	        			user.setUserPwd(userPwd);
	        			user.setBusinessLicense(businessLicense);
	        			user.setUserType(2);
	        			
	        			WrjResult result = vangoMemberService.createUserER(user);
	
	        			return result;
	        		}
	        		
	            	
	            } else {
	                return WrjResult.build(400, "验证码错误");
	            }
	        } else {
	        	return WrjResult.build(400, "验证码错误");
	        }
		
		}else {
			return WrjResult.build(400, "请发送验证码", "");
		}
		
	}
	
	public Map<String, Object> uploadBusinessLicence(MultipartFile file) throws IllegalStateException, IOException{
		Map<String, Object> map = new HashMap<String, Object>();
//		String path = "D:/imgs/"; // 定义文件路径
		String path = "C:/WrjPicture/businessLicence/";
		if (file != null) {// 判断上传的文件是否为空
			String type = null;// 文件类型
			String fileName = file.getOriginalFilename();// 文件原名称
			System.out.println("上传的文件原名称:" + fileName);
			// 判断文件类型
			type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length())
					: null;
			if (type != null) {// 判断文件类型是否为空
				if ("GIF".equals(type.toUpperCase()) || "PNG".equals(type.toUpperCase())
						|| "JPG".equals(type.toUpperCase())) {
					// 项目在容器中实际发布运行的根路径
//					String realPath = request.getSession().getServletContext().getRealPath("/");
					// 自定义的文件名称
					String trueFileName = String.valueOf(System.currentTimeMillis()) + fileName;
					// 设置存放图片文件的路径
					/*
					path = realPath
							+  System.getProperty("file.separator")+ trueFileName;
					*/
					map.put("trueFileName", trueFileName);
					path = path + trueFileName;
					System.out.println("存放图片文件的路径:" + path);
					// 转存文件到指定的路径
					file.transferTo(new File(path));
					System.out.println("文件成功上传到指定目录下");
				} else {
					System.out.println("不是我们想要的文件类型,请按要求重新上传");
					return null;
				}
			} else {
				System.out.println("文件类型为空");
				return null;
			}
		} else {
			System.out.println("没有找到相对应的文件");
			return null;
		}
		map.put("path", path);
		return map;
	}

	
	
	/**
	 * 忘记密码
	 * 
	 * @param res
	 * @param req
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = { "/api/member/forgotPwd" }, method = { RequestMethod.POST })
	@ResponseBody
	public WrjResult pwdBack(HttpServletResponse res, HttpServletRequest req) throws IOException {
		String userLoginAccount = req.getParameter("userLoginAccount");
		String userMobilePhone = req.getParameter("userMobilePhone");
		String userPwd = req.getParameter("userPwd");
		String code = req.getParameter("code");
		
		if (StringUtils.isBlank(code)) {
			return WrjResult.build(400, "验证码不能为空", "");
		}else if (StringUtils.isBlank(userLoginAccount)) {
			return WrjResult.build(400, "账号不能为空", "");
		} else if (StringUtils.isBlank(userMobilePhone)) {
			return WrjResult.build(400, "手机号码不能为空", "");
		} else if (StringUtils.isBlank(userPwd)) {
			return WrjResult.build(400, "密码不能为空", "");
		} else {
			

			// 验证验证码
			String sessionCode = req.getSession().getAttribute("code").toString();
			if (code != null && !"".equals(code) && sessionCode != null && !"".equals(sessionCode)) {
	            if (code.equalsIgnoreCase(sessionCode)) {
	            	
	            	// 验证通过，无效化验证码
	            	req.getSession().setAttribute("code", null);

	    			AaUserLogin user = new AaUserLogin();
	    			user.setUserLoginAccount(userLoginAccount);
	    			int i = vangoMemberService.getUserLoginAccount(user);
	    			
	    			if (i == 1) {
	    				user.setUserMobilePhone(userMobilePhone);
	    				user.setUserPwd(userPwd);
	    				return vangoMemberService.updateUserByUserLoginAccount(user);
	    			} else {
	    				return WrjResult.build(400, "用户名不存在");
	    			}
	    			
	            } else {
	                return WrjResult.build(400, "验证码错误");
	            }
	        } else {
	        	return WrjResult.build(400, "验证码错误");
	        }
			
			
		}

	}

	/**
	 * 飞行记录
	 * 
	 * @param page
	 *            当前的页码，从1开始。
	 * @param rows
	 *            每页显示的记录数。
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = { "/api/member/log" })
	@ResponseBody
	public WrjResult getRouteList(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String param = req.getParameter("userId");
		String parameter1 = req.getParameter("page");
		String parameter2 = req.getParameter("rows");
		if (StringUtils.isBlank(parameter1)) {
			return WrjResult.build(400, "当前页码不能为空");
		} else if (StringUtils.isBlank(parameter2)) {
			return WrjResult.build(400, "每页显示的记录数不能为空");
		} else if (StringUtils.isBlank(param)) {
			return WrjResult.build(400, "用户id不能为空");
		} else {
			Integer userId = Integer.parseInt(param);
			Integer page = Integer.parseInt(parameter1);
			Integer rows = Integer.parseInt(parameter2);
			// 记录的总条数
			Integer totalCount = vangoMemberService.queryCount(userId);
			// 记录的内容
//			PagingParameters pp = new PagingParameters();
//			pp.setPage(page);
//			pp.setRows(rows);
//			pp.setUserId(userId);
//			List<LogResult> list = vangoMemberService.getItemList(pp);
			Map<String,Object> map = new HashMap<>();
			Integer realPage = (page-1)*rows+1;
			Integer realRows = page*rows;
			map.put("page", realPage);
			map.put("rows", realRows);
			map.put("userId", userId);
			List<LogResult> list = vangoMemberService.getItemList(map);

			if (list != null) {
				PagingResult result = new PagingResult();
				result.setTotal(totalCount);
				result.setRows(list);

				return WrjResult.ok(result);
			} else {
				return WrjResult.build(400, "查询失败");
			}

		}
	}

	/**
	 * 飞行记录详情
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = { "/api/member/logDetail" })
	@ResponseBody
	public WrjResult getLogDetail(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String str = req.getParameter("routeId");
		if (StringUtils.isBlank(str)) {
			return WrjResult.build(400, "飞行计划id不能为空");
		} else {
			Integer routeId = Integer.parseInt(str);
			return vangoMemberService.getLogDetail(routeId);
		}
	}
	
	/**
	 * 省市区三级联动
	 * @param req
	 * @param resp
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = { "/api/member/getProvinceCityDistrict" })
	@ResponseBody
	public WrjResult getProvinceCityDistrict(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		return vangoMemberService.getProvinceCityDistrict();
	}
	/**
	 * 无人机名称和任务类型
	 * @param req
	 * @param resp
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = { "/api/member/getUavNameAndTaskType" })
	@ResponseBody
	public WrjResult getUavNameAndTaskType(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String str = req.getParameter("userId");
		if (StringUtils.isBlank(str)) {
			return WrjResult.build(400, "用户id为空", "");
		}
		Integer userId = Integer.parseInt(str);
		return vangoMemberService.getUavType(userId);
	}
	
	/**
	 * 添加飞行记录-立即申请
	 * 
	 * @param res
	 * @param req
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	@RequestMapping(value = { "/api/member/flightApplication" }, method = { RequestMethod.POST })
	@ResponseBody
	public WrjResult addRoute(HttpServletResponse res, HttpServletRequest req) throws IOException, ParseException {
		// 飞手姓名
		String flyerName = req.getParameter("flyerName");
		String graph = req.getParameter("graph");
		String str = req.getParameter("startTime");
		String str2 = req.getParameter("endTime");
		
		if (StringUtils.isBlank(flyerName)) {
			return WrjResult.build(400, "飞手姓名不能为空", "");
		}
		if (StringUtils.isBlank(req.getParameter("userId"))) {
			return WrjResult.build(400, "请选择无人机", "");
		}
		if (StringUtils.isBlank(req.getParameter("uavId"))) {
			return WrjResult.build(400, "无人机ID不能为空", "");
		}
		if (StringUtils.isBlank(graph)) {
			return WrjResult.build(400, "申请区域不能为空", "");
		}
		if (StringUtils.isBlank(str)) {
			return WrjResult.build(400, "起飞时间不能为空", "");
		}
		if (StringUtils.isBlank(str2)) {
			return WrjResult.build(400, "降落时间不能为空", "");
		}
		if (StringUtils.isBlank(req.getParameter("maxHeightId"))) {
			return WrjResult.build(400, "最大飞行高度不能为空", "");
		}
		if (StringUtils.isBlank(req.getParameter("routeTypeId"))) {
			return WrjResult.build(400, "任务类型不能为空", "");
		}

		Integer userId = Integer.parseInt(req.getParameter("userId"));
		Integer uavId = Integer.parseInt(req.getParameter("uavId"));

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date startTime = sdf.parse(str);
		Date endTime = sdf.parse(str2);
		
		Integer routeTypeId = Integer.parseInt(req.getParameter("routeTypeId"));

		Integer maxHeightId = Integer.parseInt(req.getParameter("maxHeightId"));
		

		AaRouteLog route = new AaRouteLog();
		route.setUserId(userId);
		route.setUavId(uavId);
		route.setGraph(graph);
		route.setStartTime(startTime);
		route.setEndTime(endTime);
		route.setMaxHeightId(maxHeightId);
		route.setRouteTypeId(routeTypeId);
		route.setFlyerName(flyerName);
		
		return vangoMemberService.createRoute(route);

	}
	
	
	

	/**
	 * 消息列表展示
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = { "/api/member/informationList" })
	@ResponseBody
	public WrjResult getInformationList(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String param = req.getParameter("userId");
		String parameter1 = req.getParameter("page");
		String parameter2 = req.getParameter("rows");
		if (StringUtils.isBlank(parameter1)) {
			return WrjResult.build(400, "当前页码不能为空");
		} else if (StringUtils.isBlank(parameter2)) {
			return WrjResult.build(400, "每页显示的记录数不能为空");
		} else if (StringUtils.isBlank(param)) {
			return WrjResult.build(400, "用户id不能为空");
		} else {
			Integer userId = Integer.parseInt(param);
			Integer page = Integer.parseInt(parameter1);
			Integer rows = Integer.parseInt(parameter2);
			// 1、消息的总条数
			Integer totalCount = vangoMemberService.queryInfoCount(userId);
			// 2、消息的内容
			Map<String,Object> map = new HashMap<>();
			Integer realPage = (page-1)*rows+1;
			Integer realRows = page*rows;
			map.put("page", realPage);
			map.put("rows", realRows);
			map.put("userId", userId);
			List<InformationRes> list = vangoMemberService.getInfomationList(map);

			if (list != null) {
				PagingResult result = new PagingResult();
				result.setTotal(totalCount);
				result.setRows(list);

				return WrjResult.ok(result);
			} else {
				return WrjResult.build(400, "查询失败");
			}
		}
	}

	/**
	 * 消息详情
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = { "/api/member/informationDetail" })
	@ResponseBody
	public WrjResult getinformationDetail(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String str = req.getParameter("infoId");
		if (StringUtils.isBlank(str)) {
			return WrjResult.build(400, "消息id不能为空");
		} else {
			Integer infoId = Integer.parseInt(str);
			return vangoMemberService.getInformation(infoId);
		}
	}

	/**
	 * 点击进入个人信息回显
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = { "/api/member/personalInfoDetail" })
	@ResponseBody
	public WrjResult getPersonalInfoDetail(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String str = req.getParameter("userId");
		if (StringUtils.isBlank(str)) {
			return WrjResult.build(400, "用户id不能为空");
		} else {
			Integer userId = Integer.parseInt(str);
			return vangoMemberService.getPersonalInfo(userId);
		}
	}

	/**
	 * 编辑保存用户个人信息
	 * 
	 * @param res
	 * @param req
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value = { "/api/member/editPersonalInfo" }, method = { RequestMethod.POST })
	@ResponseBody
	public WrjResult updatePersonalInfo(HttpServletResponse res, HttpServletRequest req) throws IOException {
		String param1 = req.getParameter("unitId");
		String param2 = req.getParameter("userId");
		String userAddress = req.getParameter("userAddress");
		String userDep = req.getParameter("userDep");
		String userEmail = req.getParameter("userEmail");
		String imagePath = req.getParameter("imagePath");
		
		AaUserLogin user = new AaUserLogin();
		
		if(StringUtils.isBlank(imagePath)){
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
			MultipartFile file1 = multipartRequest.getFile("file1");
			MultipartFile file2 = multipartRequest.getFile("file2");
			

			// 获取本机的IP
			InetAddress ia = null;
			String localip = "";
	        try {
	            ia=ia.getLocalHost();
	            localip = ia.getHostAddress();
	            System.out.println("本机的ip是 ："+localip);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
			
			// 第二张图片上传的路径
			String photoPath2 = "";
			if(file2 != null && !file2.isEmpty()){
//				photoPath2 = "http://" + localip + ":8888/nginx/IDCard/" + uploadIDCard(file2).get("trueFileName").toString();
//				photoPath2 = "http://101.200.172.176:8888/nginx/IDCard/" + uploadIDCard(file2).get("trueFileName").toString();
				photoPath2 = "http://111.230.242.15:8888/nginx/IDCard/" + uploadIDCard(file2).get("trueFileName").toString();
			}
			
			// 身份证照片路径 
			String photoPath1 = "";
			if( file1 == null || file1.isEmpty() || uploadIDCard(file1)==null || uploadIDCard(file1).size() <= 0){
				return WrjResult.build(400, "上传图片失败", "");
			}else {
//				photoPath1 = "http://" + localip + ":8888/nginx/IDCard/" + uploadIDCard(file2).get("trueFileName").toString();
//				photoPath1 = "http://101.200.172.176:8888/nginx/IDCard/" + uploadIDCard(file2).get("trueFileName").toString();
				photoPath1 = "http://111.230.242.15:8888/nginx/IDCard/" + uploadIDCard(file2).get("trueFileName").toString();
				imagePath = photoPath1 + "," + photoPath2;
				System.out.println("imagePath = " + imagePath);
			}
			user.setImagePath(imagePath);
		} else {
			user.setImagePath(imagePath);
		}
		
		if (StringUtils.isBlank(userAddress)) {
			userAddress = "";
		}
		if (StringUtils.isBlank(userDep)) {
			userDep = "";
		}
		if (StringUtils.isBlank(param2)) {
			return WrjResult.build(400, "用户id不能为空");
		}
		if (StringUtils.isBlank(userEmail)) {
			userEmail = "";
		}
		Integer unitId = Integer.parseInt(param1);
		Integer userId = Integer.parseInt(param2);
		
		user.setUserId(userId);
		user.setUserAddress(userAddress);
		user.setUserDep(userDep);
		user.setUnitId(unitId);
		user.setUserEmail(userEmail);
		
		return vangoMemberService.updatePersonalInfo(user);
		
	}
	
	
	public Map<String, Object> uploadIDCard(MultipartFile file) throws IllegalStateException, IOException{
		Map<String, Object> map = new HashMap<String, Object>();
		String path = "C:/WrjPicture/IDCard/";
		if (file != null) {// 判断上传的文件是否为空
			String type = null;// 文件类型
			String fileName = file.getOriginalFilename();// 文件原名称
			System.out.println("上传的文件原名称:" + fileName);
			// 判断文件类型
			type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length())
					: null;
			if (type != null) {// 判断文件类型是否为空
				if ("GIF".equals(type.toUpperCase()) || "PNG".equals(type.toUpperCase())
						|| "JPG".equals(type.toUpperCase())) {
					// 项目在容器中实际发布运行的根路径
//					String realPath = request.getSession().getServletContext().getRealPath("/");
					// 自定义的文件名称
					String trueFileName = String.valueOf(System.currentTimeMillis()) + fileName;
					// 设置存放图片文件的路径
					/*
					path = realPath
							+  System.getProperty("file.separator")+ trueFileName;
					*/
					map.put("trueFileName", trueFileName);
					path = path + trueFileName;
					System.out.println("存放图片文件的路径:" + path);
					// 转存文件到指定的路径
					file.transferTo(new File(path));
					System.out.println("文件成功上传到指定目录下");
				} else {
					System.out.println("不是我们想要的文件类型,请按要求重新上传");
					return null;
				}
			} else {
				System.out.println("文件类型为空");
				return null;
			}
		} else {
			System.out.println("没有找到相对应的文件");
			return null;
		}
		map.put("path", path);
		return map;
	}
	
	/**
	 * 编辑保存企业信息
	 * @param res
	 * @param req
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = { "/api/member/editEnterpriseInfo" }, method = { RequestMethod.POST })
	@ResponseBody
	public WrjResult updateEnterpriseInfo(HttpServletResponse res, HttpServletRequest req) throws IOException {
		String param1 = req.getParameter("userId");
		String userAddress = req.getParameter("userAddress");
		String userEmail = req.getParameter("userEmail");
		
//		String businessLicense = req.getParameter("businessLicense");
//		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
//		MultipartFile file1 = multipartRequest.getFile("file1");
//		MultipartFile file2 = multipartRequest.getFile("file2");
		
		if (StringUtils.isBlank(param1)) {
			return WrjResult.build(400, "用户id不能为空");
		}
		if (StringUtils.isBlank(userAddress)) {
			userAddress = "";
		}
		if (StringUtils.isBlank(userEmail)) {
			userEmail = "";
		}
		Integer userId = Integer.parseInt(param1);
		
		AaUserLogin user = new AaUserLogin();
		user.setUserId(userId);
		user.setUserAddress(userAddress);
		user.setUserEmail(userEmail);
		
//		
//		if(StringUtils.isNotBlank(businessLicense)){
//			user.setBusinessLicense(businessLicense);
//		}else {
//			// 第二张图片上传的路径
//			String photoPath2 = null;
//			if(file2 != null && !file2.isEmpty()){
//				photoPath2 = uploadPhoto(file2);
//			}
//			
//			// 身份证照片路径 
//			if( file1 == null && file1.isEmpty() && uploadPhoto(file1)==null){
//				return WrjResult.build(400, "上传图片失败", "");
//			}else {
//				businessLicense = uploadPhoto(file1) + "," + photoPath2;
//				System.out.println("businessLicense = " + businessLicense);
//			}
//			user.setBusinessLicense(businessLicense);
//		}
//		
		
		return vangoMemberService.updateEnterpriseInfo(user);
		
	}
	

	@RequestMapping("/api/member/fxsm")
	public void getFxsm(HttpServletResponse res, HttpServletRequest req) throws IOException {
		res.sendRedirect("/zl-pl/fei.html");
	}

	
	
	/**
	 * 图片文件上传 MultipartFile file,
	 */
	@ResponseBody
	@RequestMapping(value = "/api/member/photoUpload", method = RequestMethod.POST)
	public WrjResult photoUpload(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws IllegalStateException, IOException {
		// 判断用户是否登录
		/*
		 * User user=(User) session.getAttribute("user"); if (user==null) {
		 * resultData.setCode(40029); resultData.setMsg("用户未登录"); return
		 * resultData; }
		 */
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

		MultipartFile file = multipartRequest.getFile("file");

		String path = "D:/imgs/"; // 定义文件路径
		if (file != null) {// 判断上传的文件是否为空
			String type = null;// 文件类型
			String fileName = file.getOriginalFilename();// 文件原名称
			System.out.println(fileName);
			System.out.println("上传的文件原名称:" + fileName);
			// 判断文件类型
			type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length())
					: null;
			if (type != null) {// 判断文件类型是否为空
				if ("GIF".equals(type.toUpperCase()) || "PNG".equals(type.toUpperCase())
						|| "JPG".equals(type.toUpperCase())) {
					// 项目在容器中实际发布运行的根路径
//					String realPath = request.getSession().getServletContext().getRealPath("/");
					// 自定义的文件名称
					String trueFileName = String.valueOf(System.currentTimeMillis()) + fileName;
					// 设置存放图片文件的路径
					/*
					path = realPath
							+  System.getProperty("file.separator")+ trueFileName;
					*/
					path = path + trueFileName;
					System.out.println("存放图片文件的路径:" + path);
					// 转存文件到指定的路径
					file.transferTo(new File(path));
					System.out.println("文件成功上传到指定目录下");
				} else {
					System.out.println("不是我们想要的文件类型,请按要求重新上传");
					return null;
				}
			} else {
				System.out.println("文件类型为空");
				return null;
			}
		} else {
			System.out.println("没有找到相对应的文件");
			return null;
		}
		return WrjResult.build(200, "上传成功", path);
	}
	 
	/**
	 * 天气
	 * @param res
	 * @param req
	 * @return
	 * @throws IOException
	 */
//	@RequestMapping(value = "/api/member/getWeather", produces = "application/json; charset=utf-8")
//	@ResponseBody
//	public WrjResult getWeather(HttpServletResponse res, HttpServletRequest req) throws IOException {
//		String city = req.getParameter("city");
//		if (StringUtils.isBlank(city)) {
//			return WrjResult.build(400, "城市名称不能为空", "");
//		} else {
//			
//			String info;
//			WeatherInfo weatherinfo = null;
//			try {
//				info = WeatherUtils.GetWeatherData(city);
//				weatherinfo = WeatherUtils.GetWeather(info);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}  
//			
//			// 写死数据
////			WeatherInfo weatherinfo = new WeatherInfo();
////			weatherinfo.setCity("北京市");
////			weatherinfo.setLiveTemperature("29℃");
////			weatherinfo.setTemperature("10℃~28℃");
////			weatherinfo.setWeatherCondition("多云");
////			weatherinfo.setWind("东风3-4级");
//			
//	        if( weatherinfo != null ){
//	        	return WrjResult.build(200, "天气查询成功", weatherinfo);
//	        }else {
//	        	return WrjResult.build(400, "天气查询失败", "");
//			}
//	        
//		}
//	}
	

	/**
	 * 移动端用户激活
	 * 
	 * @param channel
	 * @param key
	 * @param data
	 * @return
	 */
	@RequestMapping(value = { "/api/member/member-activate" }, method = { RequestMethod.POST })
	@ResponseBody
	public OptResult memberActivate(String channel, String key, String data) {
		// 0. 检查
		channelKeyCheck(channel, key);
		if (StringUtils.isEmpty(data)) {
			reject("user.activate.data.empty");
		}
		// 1. 转换数据
		MemberRequest memberRequest = convert(data, MemberRequest.class);
		// 2. 业务检查
		if (memberRequest == null) {
			reject("user.activate.model.convert.error");
		}
		// 校验用户必须在CRM系统已经册
		// 手机号码不能为空
		if (StringUtils.isEmpty(memberRequest.getMobile())) {
			reject("user.activate.mobile.empty");
		}
		if (StringUtils.isEmpty(memberRequest.getPassword())) {
			reject("user.activate.password.empty");
		}
		if (StringUtils.isEmpty(memberRequest.getMemberCode())) {
			reject("user.activate.membercode.empty");
		}
		if (StringUtils.isEmpty(memberRequest.getMemberName())) {
			reject("user.activate.membername.empty");
		}
		if (StringUtils.isEmpty(memberRequest.getActivateCode())) {
			reject("user.activate.activecode.empty");
		}
		/*
		 * if(StringUtils.isEmpty(memberRequest.getCrmStadiumCode())){
		 * reject("user.activate.crmstadiumcode.empty"); }
		 */
		// 3、校验该会员是否存在
		VangoMemberExample example = new VangoMemberExample();
		example.createCriteria().andMemberCodeEqualTo(memberRequest.getMemberCode());
		int count = vangoMemberService.getResultCount(example);
		if (count > 0) {
			reject("user.activate.mobile.already.reged");
		}
		// 3. 存储数据
		VangoMember member = new VangoMember();
		member.setMemberCode(memberRequest.getMemberCode());
		member.setMemberName(memberRequest.getMemberName());
		member.setMobile(memberRequest.getMobile());
		member.setPassword(DigestUtils.encode(memberRequest.getPassword()));
		member.setRegDate(DateUtil.getCurrentDateTime());
		member.setMemberStatus("01");// 01-激活
		member.setCrmStadiumCode(memberRequest.getCrmStadiumCode());
		member.setMemberImage(memberRequest.getMemberImage());
		/**
		 * 公共字段信息创建
		 */
		member.setCreateDate(DateUtil.getCurrentDateTime());
		member.setCreator(memberRequest.getMemberName());
		member.setIsAudit(IFencingConst.IS_AUDIT);
		member.setIsDeleted(IFencingConst.ISNOT_DELDETED);
		member.setModifyDate(DateUtil.getCurrentDateTime());
		member.setModifier(memberRequest.getMemberName());
		member.setCreatorDeptCode("");
		OptResult result = vangoMemberService.create(member);
		result.setData(member);
		return result;
	}

	/**
	 * 发送验证码
	 * 
	 * @param channel
	 * @param key
	 * @param data
	 * @return
	 */
	@RequestMapping(value = { "/api/member/send-valid-code" }, method = { RequestMethod.POST })
	@ResponseBody
	public OptResult sendValidCode(String channel, String key, String data) {
		// 0. 检查
		channelKeyCheck(channel, key);
		if (StringUtils.isEmpty(data)) {
			reject("common.data.empty");
		}
		// 1. 数据转换
		ValidCodeRequest request = convert(data, ValidCodeRequest.class);
		// 2. 检查
		if (request == null) {
			reject("user.validcode.model.convert.error");
		}
		if (StringUtils.isEmpty(request.getMobile())) {
			reject("user.validcode.mobile.empty");
		}
		if (StringUtils.isEmpty(request.getTrade())) {
			reject("user.validcode.trade.empty");
		}

		// 3.生成验证码
		String validCode = DigestUtils.generateRandomNumber(6);
		logger.debug("手机号为[{}],交易为[{}],生成的验证码为[{}]",
				new Object[] { request.getMobile(), request.getTrade(), validCode });
		// 4.发送验证码
		String prefix = "";
		if (StringUtils.equals("01", request.getTrade())) {
			prefix = "您正在进行注册";
		} else if (StringUtils.equals("02", request.getTrade())) {
			prefix = "您正在进行密码重置";
		}
		@SuppressWarnings("unused")
		String message = prefix + "，验证码为" + validCode + "【中粮】";
		// SmsUtils.sendMsg(request.getMobile(), message);
		// 5.返回数据
		OptResult result = OptResult.success();
		result.setData(validCode);
		return result;
	}

	/**
	 * 检查给定的手机号是否在系统内注册
	 * 
	 * @param channel
	 * @param key
	 * @param data
	 * @return
	 */
	@RequestMapping(value = { "/api/member/check-mobile" }, method = { RequestMethod.POST })
	@ResponseBody
	public OptResult checkMobile(String channel, String key, String data) {
		// 0. 检查
		channelKeyCheck(channel, key);
		if (StringUtils.isEmpty(data)) {
			reject("common.data.empty");
		}
		// 1. 数据转换
		ValidCodeRequest request = convert(data, ValidCodeRequest.class);
		// 2. 检查
		if (request == null) {
			reject("user.checkmobile.model.convert.error");
		}
		if (StringUtils.isEmpty(request.getMobile())) {
			reject("user.checkmobile.mobile.empty");
		}
		OptResult result = OptResult.success();
		VangoMemberExample example = new VangoMemberExample();
		example.createCriteria().andMobileEqualTo(request.getMobile());
		int count = vangoMemberService.getResultCount(example);
		if (count > 0) {
			result.setData(true);// 用户已注册
		} else {
			result.setData(false);// 用户未注册
		}
		return result;
	}

	/**
	 * 移动用户登录系统
	 * 
	 * @param channel
	 * @param key
	 * @param data
	 * @return
	 */
	@RequestMapping(value = { "/api/member/member-login" }, method = { RequestMethod.POST })
	@ResponseBody
	public OptResult login(String channel, String key, String data) {
		// 0. 检查
		channelKeyCheck(channel, key);
		if (StringUtils.isEmpty(data)) {
			reject("common.data.empty");
		}
		// 1. 转换
		MemberRequest memberRequest = convert(data, MemberRequest.class);
		// 2. 检查
		if (memberRequest == null) {
			reject("user.login.model.convert.error");
		}
		if (StringUtils.isEmpty(memberRequest.getMemberCode())) {
			reject("user.login.memberCode.empty");
		}
		if (StringUtils.isEmpty(memberRequest.getPassword())) {
			reject("user.login.password.empty");
		}
		VangoMemberExample example = new VangoMemberExample();
		example.createCriteria().andMemberCodeEqualTo(memberRequest.getMemberCode());
		List<VangoMember> members = vangoMemberService.getResult(example);
		if (members == null || members.size() == 0) {
			reject("user.login.user.not.exists");
		}
		VangoMember member = members.get(0);
		String password = DigestUtils.encode(memberRequest.getPassword());
		if (!StringUtils.equals(password, member.getPassword())) {
			reject("user.login.password.mismatch");
		}

		// 判断用户状态
		if (!StringUtils.equals("01", member.getMemberStatus())) {
			// 锁定状态不允许登陆
			reject("user.login.invalid.status");
		}
		// 3. 登录成功
		// 获取存放session用户信息
		MemberSessionUtil sessionUtil = new MemberSessionUtil();
		sessionUtil.setUserId(member.getMemberId());
		sessionUtil.setName(member.getMemberName());
		sessionUtil.setMobile(member.getMobile());
		sessionUtil.setPassword(member.getPassword());
		sessionUtil.setSecret(UUID.randomUUID().toString());
		sessionUtil.setMemberStatus(member.getMemberStatus());
		sessionUtil.setRegDate(member.getRegDate());
		// 4. 存储至Redis
		vangoMemberRedisService.setUserSecret(sessionUtil.getUserId(), sessionUtil.getSecret());
		OptResult result = OptResult.success();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", sessionUtil.getUserId());
		paramMap.put("secret", sessionUtil.getSecret());
		paramMap.put("userName", sessionUtil.getName());
		String imagePath = member.getMemberImage();
		if (StringUtils.isEmpty(imagePath)) {
			paramMap.put("headImagePath", "null");
		} else {
			// paramMap.put("headImagePath", imagePath.substring(6,
			// imagePath.length()));
			paramMap.put("headImagePath", imagePath);
		}
		result.setData(paramMap);
		return result;
	}

	/**
	 * 忘记密码
	 * 
	 * @param channel
	 * @param key
	 * @param data
	 * @return
	 */
	@RequestMapping(value = { "/api/member/forget-password" }, method = { RequestMethod.POST })
	@ResponseBody
	public OptResult forgetPassword(String channel, String key, String data) {

		// 0. 检查
		channelKeyCheck(channel, key);
		if (StringUtils.isEmpty(data)) {
			reject("user.activate.data.empty");
		}
		// 1. 转换数据
		MemberRequest memberRequest = convert(data, MemberRequest.class);
		// 2. 业务检查
		if (memberRequest == null) {
			reject("user.forgetpwd.model.convert.error");
		}
		// 手机号码不能为空
		if (StringUtils.isEmpty(memberRequest.getMobile())) {
			reject("user.forgetpwd.mobile.empty");
		}
		if (StringUtils.isEmpty(memberRequest.getPassword())) {
			reject("user.forgetpwd.password.empty");
		}
		if (StringUtils.isEmpty(memberRequest.getMemberCode())) {
			reject("user.forgetpwd.membercode.empty");
		}
		if (StringUtils.isEmpty(memberRequest.getMemberName())) {
			reject("user.forgetpwd.membername.empty");
		}
		if (StringUtils.isEmpty(memberRequest.getActivateCode())) {
			reject("user.forgetpwd.activecode.empty");
		}
		// 3、校验该会员是否存在
		VangoMemberExample example = new VangoMemberExample();
		example.createCriteria().andMemberCodeEqualTo(memberRequest.getMemberCode());
		List<VangoMember> memberList = vangoMemberService.getResult(example);
		if (null == memberList || memberList.size() == 0) {
			reject("user.forgetpwd.member.not.exist");
		}
		VangoMember vangoMember = memberList.get(0);
		vangoMember.setPassword(DigestUtils.encode(memberRequest.getPassword()));
		OptResult result = vangoMemberService.update(vangoMember);
		result.setData(vangoMember);
		return result;
	}

	/**
	 * 修改密码
	 * 
	 * @param channel
	 * @param key
	 * @param data
	 * @return
	 */
	@RequestMapping(value = { "/api/member/modify-password" }, method = { RequestMethod.POST })
	@ResponseBody
	public OptResult modifyPassword(String channel, String key, String data) {
		// 0. 检查
		channelKeyCheck(channel, key);
		if (StringUtils.isEmpty(data)) {
			reject("common.data.empty");
		}
		// 1. 转换
		MemberRequest request = convert(data, MemberRequest.class);
		// 2. 检查
		if (request == null) {
			reject("user.modifypassword.model.convert.error");
		}
		// 2.1 秘钥及用户id校验
		if (StringUtils.isEmpty(request.getSecret())) {
			reject("user.modifypassword.secret.empty");
		}
		if (request.getUserId() == null || request.getUserId() <= 0) {
			reject("user.modifypassword.userid.is.null");
		}
		// 2.2 获取Redis中存的秘钥
		String secret = vangoMemberRedisService.getUserSecret(request.getUserId());
		if (!StringUtils.equals(secret, request.getSecret())) {
			reject("user.modifypassword.secret.mismatch");
		}
		// 3.业务校验
		if (StringUtils.isEmpty(request.getOrigPassword())) {
			reject("user.modifypassword.origpassword.empty");
		}
		if (StringUtils.isEmpty(request.getPassword())) {
			reject("user.modifypassword.newpassword.empty");
		}
		// 3.1 查询会员实体
		VangoMember member = vangoMemberService.getById(request.getUserId());
		if (member == null) {
			reject("user.modifypassword.user.not.exists");
		}
		String origPasswordHashed = DigestUtils.encode(request.getOrigPassword());
		if (!StringUtils.equals(member.getPassword(), origPasswordHashed)) {
			reject("user.modifypassword.password.mismatch");
		}
		member.setPassword(DigestUtils.encode(request.getPassword()));
		return vangoMemberService.update(member);
	}

	/**
	 * 修改头像
	 * 
	 * @param channel
	 * @param key
	 * @param data
	 * @return
	 */
	@RequestMapping(value = { "/api/member/modify-headportrait" }, method = { RequestMethod.POST })
	@ResponseBody
	public OptResult modifyHeadportrait(String channel, String key, String data) {
		// 0. 检查
		channelKeyCheck(channel, key);
		if (StringUtils.isEmpty(data)) {
			reject("common.data.empty");
		}
		// 1. 转换
		MemberRequest request = convert(data, MemberRequest.class);
		// 2. 检查
		if (request == null) {
			reject("user.modifyheadpt.model.convert.error");
		}
		// 2.1 秘钥及用户id校验
		if (StringUtils.isEmpty(request.getSecret())) {
			reject("user.modifyheadpt.secret.empty");
		}
		if (request.getUserId() == null || request.getUserId() <= 0) {
			reject("user.modifyheadpt.userid.is.null");
		}
		// 2.2 获取Redis中存的秘钥
		String secret = vangoMemberRedisService.getUserSecret(request.getUserId());
		if (!StringUtils.equals(secret, request.getSecret())) {
			reject("user.modifyheadpt.secret.mismatch");
		}
		// 3.业务校验
		if (StringUtils.isEmpty(request.getMemberImage())) {
			reject("user.modifyheadpt.memberImage.empty");
		}
		// 查询会员实体
		VangoMember member = vangoMemberService.getById(request.getUserId());
		if (member == null) {
			reject("user.modifyheadpt.user.not.exists");
		}
		member.setMemberImage(request.getMemberImage());
		return vangoMemberService.update(member);
	}

	/**
	 * 后台管理端_登录系统
	 * 
	 * @param channel
	 * @param key
	 * @param data
	 * @return
	 */
	@RequestMapping(value = { "/mgr/operator/operator-login" }, method = { RequestMethod.POST })
	@ResponseBody
	public OptResult mgrLogin(String channel, String key, String data) {
		// 0. 检查
		channelKeyCheck(channel, key);
		if (StringUtils.isEmpty(data)) {
			reject("common.data.empty");
		}
		// 1. 转换
		OperatorRequest memberRequest = convert(data, OperatorRequest.class);
		// 2. 检查
		if (memberRequest == null) {
			reject("user.login.model.convert.error");
		}
		if (StringUtils.isEmpty(memberRequest.getUserId())) {
			reject("user.login.memberCode.empty");
		}
		if (StringUtils.isEmpty(memberRequest.getPassword())) {
			reject("user.login.password.empty");
		}
		OperatorExample example = new OperatorExample();
		example.createCriteria().andUserIdEqualTo(memberRequest.getUserId());
		List<Operator> operatorList = operatorService.getResult(example);
		if (operatorList == null || operatorList.size() == 0) {
			reject("user.login.user.not.exists");
		}
		Operator operator = operatorList.get(0);
		String password = DigestUtils.encode(memberRequest.getPassword());
		if (!StringUtils.equals(password, operator.getPassword())) {
			reject("user.login.password.mismatch");
		}

		// 判断用户状态
		if (!StringUtils.equals("01", operator.getStatus())) {
			// 锁定状态不允许登陆
			reject("user.login.invalid.status");
		}
		// 3. 登录成功
		// 获取该操作人员的角色ID
		List<Integer> roleList = operatorService.getOperRoleRelListById(operator.getOperatorId());
		if (null == roleList || roleList.size() != 1) {
			reject("user.login.roleList.is.null");
		}
		// 获取存放session用户信息
		OperatorSessionUtil sessionUtil = new OperatorSessionUtil();
		sessionUtil.setOperatorId(operator.getOperatorId());
		sessionUtil.setUserId(operator.getUserId());
		sessionUtil.setUserName(operator.getUserName());
		sessionUtil.setSecret(UUID.randomUUID().toString());
		sessionUtil.setPassword(operator.getPassword());
		sessionUtil.setEmail(operator.getEmail());
		sessionUtil.setRoleId(roleList.get(0));
		sessionUtil.setStadiumCode(operator.getField1());
		OptResult result = OptResult.success();
		result.setData(sessionUtil);
		return result;
	}

	/**
	 * 管理_系统用户列表
	 * 
	 * @param channel
	 * @param key
	 * @param data
	 * @return
	 */
	@RequestMapping(value = { "/mgr/sys/query-operatorList" }, method = { RequestMethod.POST })
	@ResponseBody
	public PageData<Operator> mgrSysOperatorList(String channel, String key, String data) {
		// 0. 检查
		channelKeyCheck(channel, key);
		if (StringUtils.isEmpty(data)) {
			reject("common.data.empty");
		}
		// 1. 数据转换
		PageRequest<OperatorRequest> request = JsonUtil.transferToPageRequest(data, OperatorRequest.class);
		// 2. 检查
		if (request == null) {
			reject("mgr.operatorlist.model.convert.error");
		}
		if (StringUtils.isEmpty(request.getCondition().getSecret())) {
			reject("mgr.operatorlist.secret.empty");
		}
		if (request.getCondition().getLoginEmpId() == null || request.getCondition().getLoginEmpId() <= 0) {
			reject("mgr.operatorlist.userid.is.null");
		}
		// 查询登陆用户
		Operator operator = operatorService.getById(request.getCondition().getLoginEmpId());
		if (operator == null) {
			reject("mgr.operatorlist.user.not.exists");
		}
		// 3. 处理
		PageRequest<OperatorExample> _request = new PageRequest<OperatorExample>();
		OperatorExample example = new OperatorExample();
		OperatorExample.Criteria criteria = example.createCriteria();
		if (request.getCondition() != null) {
			if (!StringUtils.isEmpty(request.getCondition().getUserId())) {
				criteria.andUserIdLike("%" + request.getCondition().getUserId() + "%");
			}
			if (!StringUtils.isEmpty(request.getCondition().getUserName())) {
				criteria.andUserNameLike("%" + request.getCondition().getUserName() + "%");
			}
			if (!StringUtils.isEmpty(request.getCondition().getStatus())) {
				criteria.andStatusEqualTo(request.getCondition().getStatus());
			}
		}
		example.setOrderByClause("OPERATOR_ID DESC");
		_request.setCondition(example);
		_request.setPage(request.getPage());
		_request.setRows(request.getRows());
		PageData<Operator> result = operatorService.query(_request);
		return result;
	}

	/**
	 * 管理_添加系统用户
	 * 
	 * @param channel
	 * @param key
	 * @param data
	 * @return
	 */
	@RequestMapping(value = { "/mgr/sys/create-operator" }, method = { RequestMethod.POST })
	@ResponseBody
	public OptResult mgrSysAddOperator(String channel, String key, String data) {
		// 0. 检查
		channelKeyCheck(channel, key);
		if (StringUtils.isEmpty(data)) {
			reject("common.data.empty");
		}
		// 1. 数据转换
		OperatorRequest request = convert(data, OperatorRequest.class);
		// 2. 检查
		if (request == null) {
			reject("mgr.addoperator.model.convert.error");
		}
		if (StringUtils.isEmpty(request.getSecret())) {
			reject("mgr.addoperator.secret.empty");
		}
		if (request.getLoginEmpId() == null || request.getLoginEmpId() <= 0) {
			reject("mgr.addoperator.userid.is.null");
		}
		// 查询处理登陆用户实体
		Operator operator = operatorService.getById(request.getLoginEmpId());
		if (operator == null) {
			reject("mgr.addoperator.user.not.exists");
		}
		if (StringUtils.isEmpty(request.getUserName())) {
			reject("mgr.addoperator.username.empty");
		}
		if (StringUtils.isEmpty(request.getPassword())) {
			reject("mgr.addoperator.password.empty");
		}
		if (StringUtils.isEmpty(request.getField1())) {
			reject("mgr.addoperator.stadiumcode.empty");
		}
		// 3. 处理
		Operator _operator = new Operator();
		_operator.setUserId(request.getUserId());
		_operator.setUserName(request.getUserName());
		_operator.setPassword(DigestUtils.encode(request.getPassword()));
		_operator.setStatus(request.getStatus());
		_operator.setField1(request.getField1());
		return operatorService.create(_operator);
	}

	/**
	 * 管理_更新系统用户
	 * 
	 * @param channel
	 * @param key
	 * @param data
	 * @return
	 */
	@RequestMapping(value = { "/mgr/sys/modify-operator" }, method = { RequestMethod.POST })
	@ResponseBody
	public OptResult mgrSysUpdateOperator(String channel, String key, String data) {
		// 0. 检查
		channelKeyCheck(channel, key);
		if (StringUtils.isEmpty(data)) {
			reject("common.data.empty");
		}
		// 1. 数据转换
		OperatorRequest request = convert(data, OperatorRequest.class);
		// 2. 检查
		if (request == null) {
			reject("mgr.updateoperator.model.convert.error");
		}
		if (StringUtils.isEmpty(request.getSecret())) {
			reject("mgr.updateoperator.secret.empty");
		}
		if (request.getLoginEmpId() == null || request.getLoginEmpId() <= 0) {
			reject("mgr.updateoperator.userid.is.null");
		}
		// 查询处理登陆用户实体
		Operator operator = operatorService.getById(request.getLoginEmpId());
		if (operator == null) {
			reject("mgr.updateoperator.user.not.exists");
		}
		if (StringUtils.isEmpty(request.getUserName())) {
			reject("mgr.updateoperator.username.empty");
		}
		if (StringUtils.isEmpty(request.getUserId())) {
			reject("mgr.updateoperator.userid.empty");
		}
		if (StringUtils.isEmpty(request.getStatus())) {
			reject("mgr.updateoperator.password.empty");
		}
		// 3. 处理
		Operator _operator = operatorService.getById(request.getOperatorId());
		_operator.setUserId(request.getUserId());
		_operator.setUserName(request.getUserName());
		_operator.setStatus(request.getStatus());
		_operator.setField1(request.getField1());
		return operatorService.update(_operator);
	}

	/**
	 * 管理_删除系统用户
	 * 
	 * @param channel
	 * @param key
	 * @param data
	 * @return
	 */
	@RequestMapping(value = { "/mgr/sys/delete-operator" }, method = { RequestMethod.POST })
	@ResponseBody
	public OptResult mgrSysDeleteOperator(String channel, String key, String data) {
		// 0. 检查
		channelKeyCheck(channel, key);
		if (StringUtils.isEmpty(data)) {
			reject("common.data.empty");
		}
		// 1. 数据转换
		OperatorRequest request = convert(data, OperatorRequest.class);
		// 2. 检查
		if (request == null) {
			reject("mgr.deleteoperator.model.convert.error");
		}
		if (StringUtils.isEmpty(request.getSecret())) {
			reject("mgr.deleteoperator.secret.empty");
		}
		if (request.getLoginEmpId() == null || request.getLoginEmpId() <= 0) {
			reject("mgr.deleteoperator.userid.is.null");
		}
		// 查询处理登陆用户实体
		Operator operator = operatorService.getById(request.getLoginEmpId());
		if (operator == null) {
			reject("mgr.deleteoperator.user.not.exists");
		}
		// 3. 处理
		Operator _operator = operatorService.getById(request.getOperatorId());
		_operator.setStatus("02");// 已锁定
		return operatorService.update(_operator);
	}

	/**
	 * 后台管理_设置用户权限
	 * 
	 * @param channel
	 * @param key
	 * @param data
	 * @return
	 */
	@RequestMapping(value = { "/mgr/sys/setup-authority" }, method = { RequestMethod.POST })
	@ResponseBody
	public OptResult mgrSetupAuthority(String channel, String key, String data) {
		// 0. 检查
		channelKeyCheck(channel, key);
		if (StringUtils.isEmpty(data)) {
			reject("common.data.empty");
		}
		// 1. 数据转换
		OperatorRequest request = convert(data, OperatorRequest.class);
		// 2. 检查
		if (request == null) {
			reject("mgr.setupauthority.model.convert.error");
		}
		if (StringUtils.isEmpty(request.getSecret())) {
			reject("mgr.setupauthority.secret.empty");
		}
		if (request.getLoginEmpId() == null || request.getLoginEmpId() <= 0) {
			reject("mgr.setupauthority.userid.is.null");
		}
		// 查询处理登陆用户实体
		Operator operator = operatorService.getById(request.getLoginEmpId());
		if (operator == null) {
			reject("mgr.setupauthority.user.not.exists");
		}
		// 3. 处理
		// 3.1 校验必输项
		if (null == request.getOperatorId() || request.getOperatorId() <= 0) {
			reject("mgr.setupauthority.operatorld.is.null");
		}
		if (StringUtils.isEmpty(request.getRoleLike())) {
			reject("mgr.setupauthority.rolelike.is.null");
		}
		// 3.2 业务逻辑处理
		int countRole = operatorService.countOperRoleRelByOperId(request.getOperatorId());
		if (countRole > 0) {
			operatorService.deleteOperRoleRelByOperId(request.getOperatorId());
		}
		OptResult result = OptResult.success();
		String[] roleSplit = request.getRoleLike().split(",");
		for (int i = 0; i < roleSplit.length; i++) {
			OperRoleRel operRoleRel = new OperRoleRel();
			operRoleRel.setOperatorId(request.getOperatorId());
			operRoleRel.setRoleId(Integer.parseInt(roleSplit[i]));
			operatorService.insertOperRoleRelByOperIdAndRoleId(operRoleRel);
		}
		return result;
	}

	/**
	 * 后台管理_查询指定用户全部角色ID
	 * 
	 * @param channel
	 * @param key
	 * @param data
	 * @return
	 */
	@RequestMapping(value = { "/mgr/sys/query-userole" }, method = { RequestMethod.POST })
	@ResponseBody
	public OptResult mgrQueryUserRole(String channel, String key, String data) {
		// 0. 检查
		channelKeyCheck(channel, key);
		if (StringUtils.isEmpty(data)) {
			reject("common.data.empty");
		}
		// 1. 数据转换
		OperatorRequest request = convert(data, OperatorRequest.class);
		// 2. 检查
		if (request == null) {
			reject("mgr.queryuserole.model.convert.error");
		}
		if (StringUtils.isEmpty(request.getSecret())) {
			reject("mgr.queryuserole.secret.empty");
		}
		if (request.getLoginEmpId() == null || request.getLoginEmpId() <= 0) {
			reject("mgr.queryuserole.userid.is.null");
		}
		// 查询处理登陆用户实体
		Operator operator = operatorService.getById(request.getLoginEmpId());
		if (operator == null) {
			reject("mgr.queryuserole.user.not.exists");
		}
		// 3. 处理
		// 3.1 校验必输项
		if (null == request.getOperatorId() || request.getOperatorId() <= 0) {
			reject("mgr.queryuserole.operatorld.is.null");
		}
		// 3.2 业务逻辑处理
		StringBuffer sb = new StringBuffer();
		List<Integer> roleList = operatorService.getOperRoleRelListById(request.getOperatorId());
		if (roleList != null) {
			for (int i = 0; i < roleList.size(); i++) {
				sb.append(roleList.get(i).toString()).append(",");
			}
		}
		OptResult optResult = OptResult.success();
		optResult.setData(sb.toString());
		return optResult;
	}

	/**
	 * 后台管理端_查询会员信息列表
	 * 
	 * @param channel
	 * @param key
	 * @param data
	 * @return
	 */
	@RequestMapping(value = { "/mgr/member/query-memberlist" }, method = { RequestMethod.POST })
	@ResponseBody
	public PageData<VangoMember> mgrMemberList(String channel, String key, String data) {
		// 0. 检查
		channelKeyCheck(channel, key);
		if (StringUtils.isEmpty(data)) {
			reject("common.data.empty");
		}
		// 1. 数据转换
		PageRequest<MemberRequest> request = JsonUtil.transferToPageRequest(data, MemberRequest.class);
		// 2. 检查
		if (request == null) {
			reject("mgr.memberlist.model.convert.error");
		}
		if (StringUtils.isEmpty(request.getCondition().getSecret())) {
			reject("mgr.memberlist.secret.empty");
		}
		if (request.getCondition().getLoginEmpId() == null || request.getCondition().getLoginEmpId() <= 0) {
			reject("mgr.memberlist.userid.is.null");
		}
		if ((null == request.getCondition().getRoleId() || request.getCondition().getRoleId() <= 0)) {
			reject("mgr.memberlist.roleid.empty");
		}
		if (StringUtils.isEmpty(request.getCondition().getStadiumCode())) {
			reject("mgr.memberlist.stadiumcode.empty");
		}
		// 查询登陆用户
		Operator operator = operatorService.getById(request.getCondition().getLoginEmpId());
		if (operator == null) {
			reject("mgr.memberlist.user.not.exists");
		}
		// 3. 处理
		PageRequest<VangoMemberExample> _request = new PageRequest<VangoMemberExample>();
		VangoMemberExample example = new VangoMemberExample();
		VangoMemberExample.Criteria criteria = example.createCriteria();
		// 3.1 查询条件：会员编号，会员姓名，会员状态
		if (!StringUtils.isEmpty(request.getCondition().getMemberCode())) {
			criteria.andMemberCodeEqualTo(request.getCondition().getMemberCode());
		}
		if (!StringUtils.isEmpty(request.getCondition().getMemberName())) {
			criteria.andMemberNameLike("%" + request.getCondition().getMemberName().trim() + "%");
		}
		if (!StringUtils.isEmpty(request.getCondition().getMemberStatus())) {
			criteria.andMemberStatusEqualTo(request.getCondition().getMemberStatus());
		}
		// 处理数据权限过滤
		/*
		 * String crmStadiumCode =
		 * vangoStadiumService.getStadiumInfoByCode(request.getCondition().
		 * getStadiumCode()).getCrmStadiumCode();
		 * if(StringUtils.isNotBlank(crmStadiumCode) &&
		 * request.getCondition().getRoleId() != 1){//管理员角色不用于过滤数据
		 * criteria.andCrmStadiumCodeEqualTo(crmStadiumCode); }
		 */

		// 3.2 根据表id倒序排列
		example.setOrderByClause("MEMBER_ID DESC");
		_request.setCondition(example);
		_request.setPage(request.getPage());
		_request.setRows(request.getRows());
		return vangoMemberService.query(_request);
	}

	/**
	 * 后台管理_查看会员详情
	 * 
	 * @param channel
	 * @param key
	 * @param data
	 * @return
	 */
	@RequestMapping(value = { "/mgr/member/view-member" }, method = { RequestMethod.POST })
	@ResponseBody
	public OptResult mgrViewMember(String channel, String key, String data) {
		// 0. 检查
		channelKeyCheck(channel, key);
		if (StringUtils.isEmpty(data)) {
			reject("common.data.empty");
		}
		// 1. 数据转换
		MemberRequest request = convert(data, MemberRequest.class);
		// 2. 检查
		if (request == null) {
			reject("mgr.viewmember.model.convert.error");
		}
		if (StringUtils.isEmpty(request.getSecret())) {
			reject("mgr.viewmember.secret.empty");
		}
		if (request.getLoginEmpId() == null || request.getLoginEmpId() <= 0) {
			reject("mgr.viewmember.userid.is.null");
		}
		// 查询处理登陆用户实体
		Operator operator = operatorService.getById(request.getLoginEmpId());
		if (operator == null) {
			reject("mgr.viewmember.user.not.exists");
		}
		// 3. 处理
		// 3.1 校验必输项
		if (request.getMemberId() == null || request.getMemberId() <= 0) {
			reject("mgr.viewmember.memberId.is.null");
		}
		// 3.2 查询会员实体
		VangoMember vangoMember = vangoMemberService.getById(request.getMemberId());
		OptResult result = OptResult.success();
		result.setData(vangoMember);
		return result;
	}

	@RequestMapping(value = { "/api/member/query-member" }, method = { RequestMethod.POST })
	@ResponseBody
	public OptResult apiQueryMember(String channel, String key, String data) {
		// 0. 检查
		channelKeyCheck(channel, key);
		if (StringUtils.isEmpty(data)) {
			reject("common.data.empty");
		}
		// 1. 数据转换
		MemberRequest request = convert(data, MemberRequest.class);
		// 2. 检查
		if (request == null) {
			reject("mgr.viewmember.model.convert.error");
		}
		// 3. 处理
		// 3.1 校验必输项
		String memCode = request.getMemberCode();
		if (StringUtil.isEmpty(memCode)) {
			reject("mgr.viewmember.memberId.is.null");
		}
		// 3.2 查询会员实体
		VangoMember vangoMember = vangoMemberService.getMemberByMemCode(memCode);
		OptResult result = null;
		if (vangoMember == null) {
			result = OptResult.failure("1111");
		} else {
			result = OptResult.success();
			result.setData(vangoMember);
		}
		return result;
	}

	/**
	 * 后台管理_修改会员信息
	 * 
	 * @param channel
	 * @param key
	 * @param data
	 * @return
	 */
	@RequestMapping(value = { "/mgr/member/modify-member" }, method = { RequestMethod.POST })
	@ResponseBody
	public OptResult mgrModifyMember(String channel, String key, String data) {
		// 0. 检查
		channelKeyCheck(channel, key);
		if (StringUtils.isEmpty(data)) {
			reject("common.data.empty");
		}
		// 1. 数据转换
		MemberRequest request = convert(data, MemberRequest.class);
		// 2. 检查
		if (request == null) {
			reject("mgr.modifymember.model.convert.error");
		}
		if (StringUtils.isEmpty(request.getSecret())) {
			reject("mgr.modifymember.secret.empty");
		}
		if (request.getLoginEmpId() == null || request.getLoginEmpId() <= 0) {
			reject("mgr.modifymember.userid.is.null");
		}
		// 查询处理登陆用户实体
		Operator operator = operatorService.getById(request.getLoginEmpId());
		if (operator == null) {
			reject("mgr.modifycity.user.not.exists");
		}
		// 3. 处理
		// 3.1 校验必输项
		if (null == request.getMemberId() || request.getMemberId() <= 0) {
			reject("mgr.modifycity.memberld.is.null");
		}
		// 3.2 查询会员实体
		VangoMember vangoMember = vangoMemberService.getById(request.getMemberId());

		vangoMember.setMemberName(request.getMemberName());
		vangoMember.setMemberNickname(request.getMemberNickname());
		vangoMember.setMobile(request.getMobile());
		vangoMember.setEmail(request.getEmail());
		vangoMember.setMemberStatus(request.getMemberStatus());

		/**
		 * 公共字段信息创建
		 */
		vangoMember.setModifyDate(DateUtil.getCurrentDateTime());
		vangoMember.setModifier(operator.getUserName());
		// 3.3 更新会员实体
		return vangoMemberService.update(vangoMember);
	}

}
