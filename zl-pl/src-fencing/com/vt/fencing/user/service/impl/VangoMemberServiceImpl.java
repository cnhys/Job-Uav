package com.vt.fencing.user.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vt.base.mapper.IBaseMapper;
import com.vt.base.service.BaseService;
import com.vt.fencing.IGatewayServiceDef;
import com.vt.fencing.client.VangoMemberMapper;
import com.vt.fencing.model.AaIrregularities;
import com.vt.fencing.model.AaNoflyZone;
import com.vt.fencing.model.AaRouteLog;
import com.vt.fencing.model.AaUav;
import com.vt.fencing.model.AaUavStatusHis;
import com.vt.fencing.model.AaUserLogin;
import com.vt.fencing.model.SealInfo;
import com.vt.fencing.model.VangoMember;
import com.vt.fencing.model.VangoMemberExample;
import com.vt.fencing.model.vo.BrandResponse;
import com.vt.fencing.model.vo.City;
import com.vt.fencing.model.vo.CityResponse;
import com.vt.fencing.model.vo.District;
import com.vt.fencing.model.vo.GeoCoordinate;
import com.vt.fencing.model.vo.HomePageInfoResponse;
import com.vt.fencing.model.vo.InformationRes;
import com.vt.fencing.model.vo.InformationResult;
import com.vt.fencing.model.vo.LogDetailResult;
import com.vt.fencing.model.vo.LogPojo;
import com.vt.fencing.model.vo.LogResult;
import com.vt.fencing.model.vo.LoginResponse;
import com.vt.fencing.model.vo.Province;
import com.vt.fencing.model.vo.ProvinceResponse;
import com.vt.fencing.model.vo.ShenFeiRes;
import com.vt.fencing.model.vo.ShenFeiResult;
import com.vt.fencing.model.vo.TaskTypeResponse;
import com.vt.fencing.model.vo.UavDetailResponse;
import com.vt.fencing.model.vo.UavDetailResult;
import com.vt.fencing.model.vo.UavResponse;
import com.vt.fencing.model.vo.UavResult;
import com.vt.fencing.model.vo.UavStatusHisResponse;
import com.vt.fencing.model.vo.UavTypeResponse;
import com.vt.fencing.model.vo.WeiguiResult;
import com.vt.fencing.model.vo.WorkingLoadResponse;
import com.vt.fencing.user.service.IVangoMemberService;
import com.vt.fencing.user.util.AppMD5Util;
import com.vt.fencing.user.util.DateUtil;
import com.vt.fencing.user.util.WrjResult;


/**
 * /** 会员服务实现 Created by john on 16/11/18.
 */
@Service(IGatewayServiceDef.MEMBER_SERVICE)
public class VangoMemberServiceImpl extends BaseService<VangoMember, VangoMemberExample, Integer>
		implements IVangoMemberService {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 3677976970934506556L;
	/**
	 * mapper
	 */
	@Autowired
	private VangoMemberMapper mapper;

	@Override
	public IBaseMapper<VangoMember, VangoMemberExample, Integer> getMapper() {
		return mapper;
	}

	@Override
	public VangoMember getMemberByMemCode(String memberCode) {
		VangoMemberExample example = new VangoMemberExample();
		example.createCriteria().andMemberCodeEqualTo(memberCode);
		List<VangoMember> memberList = super.getResult(example);
		return memberList.get(0);
	}
	
	
	// 上传头像
	@Override
	public Integer updateUserHeadByUserId(Map<String, Object> map) {
		return mapper.updateUserHeadByUserId(map);
	}
	
	
	//首页
	@Override
	public List<HomePageInfoResponse> getHomePageInfo(Double longitude, Double latitude) {
		List<HomePageInfoResponse> results = new ArrayList<HomePageInfoResponse>();
		
		//先计算查询点的经纬度范围  
        double r = 6371;//地球半径千米  
        double dis = 20;//20 千米距离  
        double dlng =  2*Math.asin(Math.sin(dis/(2*r))/Math.cos(latitude*Math.PI/180));  
        dlng = dlng*180/Math.PI;//角度转为弧度  
        double dlat = dis/r;  
        dlat = dlat*180/Math.PI;          
        double minlat = latitude - dlat;  
        double maxlat = latitude + dlat;  
        double minlng = longitude - dlng;  
        double maxlng = longitude + dlng; 
        
        List<AaNoflyZone> graphs = mapper.selectGraph();
        List<Integer> zoneIds = new ArrayList<Integer>();
        if(graphs != null && graphs.size() > 0){
        	for (AaNoflyZone aaNoflyZone : graphs) {
				// 处理Graph
        		String graph = aaNoflyZone.getGraph();
        		
        		String[] str1 = graph.toString().split("\\;");
        		if(str1 != null && str1.length > 0){
        			// 获取Graph字段中的首数字 (首数字1为圆型，2为多边形).
        			Integer areaType = Integer.parseInt(str1[0]);
        			// 定义经纬度
        			String longitudeDataStr,latitudeDataStr;
        			Double longitudeData = null,latitudeData = null;
        			// 如果是圆形
            		if(areaType == 1){
            			// 获取圆形圆心的经纬度
            			String[] str2 = str1[1].split("\\,");
            			longitudeDataStr = str2[0];
            			latitudeDataStr = str2[1];
            			if(StringUtils.isNotBlank(longitudeDataStr) && StringUtils.isNotBlank(latitudeDataStr)){
            				longitudeData = Double.parseDouble(longitudeDataStr.trim());
            				latitudeData = Double.parseDouble(latitudeDataStr.trim());
                		}
            			// 判断圆心是否在20公里内
            			if(longitudeData >= minlng && longitudeData <= maxlng 
                    			&& latitudeData >= minlat && latitudeData <= maxlat){
            				// 把这条记录的区域id存储起来。
                    		zoneIds.add(aaNoflyZone.getZoneId());
                    	}
            		}else {
            			List<GeoCoordinate> geoCoordinateList = new ArrayList<GeoCoordinate>();
            			
            			for(int i = 1; i < str1.length; i++){
            				// 获取多边形禁飞区域所有的点(经纬度)
            				if( str1[i] != null ){
            					GeoCoordinate geo = new GeoCoordinate();
            					// 获取多边形点的经纬度
                    			String[] str2 = str1[i].split("\\,");
                    			longitudeDataStr = str2[0];
                    			latitudeDataStr = str2[1];
                    			if(StringUtils.isNotBlank(longitudeDataStr) && StringUtils.isNotBlank(latitudeDataStr)){
                    				longitudeData = Double.parseDouble(longitudeDataStr.trim());
                    				latitudeData = Double.parseDouble(latitudeDataStr.trim());
                        		}
                    			geo.setLatitude(latitudeData);
                    			geo.setLongitude(longitudeData);
                    			
                    			geoCoordinateList.add(geo);
            				}
            			}
            			// 获取多边形的中心点
            			GeoCoordinate centerPoint = GetCenterPointFromListOfCoordinates(geoCoordinateList);
            			// 判断中心点是否在20公里内
            			if(centerPoint.getLongitude() >= minlng && centerPoint.getLongitude() <= maxlng 
                    			&& centerPoint.getLatitude() >= minlat && centerPoint.getLatitude() <= maxlat){
            				// 把这条记录的区域id存储起来。
                    		zoneIds.add(aaNoflyZone.getZoneId());
                    	}
					}
        		}
			}
        }
        
        if(zoneIds != null && zoneIds.size() > 0){
        	for (Integer zoneId : zoneIds) {
        		AaNoflyZone noflyZone = mapper.selectNoflyZone(zoneId);
        		if(noflyZone != null){
        			HomePageInfoResponse result = new HomePageInfoResponse();
        			result.setZoneId(noflyZone.getZoneId());
        			result.setAreaType(noflyZone.getAreaType());
        			//处理 Graph 字段
        			String graph = noflyZone.getGraph();
        			String[] str1 = graph.toString().split("\\;");
        			
            		if(str1 != null && str1.length > 0){
            			// 获取Graph字段中的首数字 (首数字1为圆型，2为多边形).
            			Integer areaType = Integer.parseInt(str1[0]);
            			// 定义经纬度
            			String longitudeDataStr,latitudeDataStr;
            			Double longitudeData = null,latitudeData = null;
            			// 如果是圆形
                		if(areaType == 1){
                			// 处理圆的半径
                			if( str1[2] != null && str1[2] != ""){
                				result.setRadius(Double.parseDouble(str1[2]));
                			}
                			// 获取圆形圆心的经纬度
                			String[] str2 = str1[1].split("\\,");
                			longitudeDataStr = str2[0];
                			latitudeDataStr = str2[1];
                			if(StringUtils.isNotBlank(longitudeDataStr) && StringUtils.isNotBlank(latitudeDataStr)){
                				longitudeData = Double.parseDouble(longitudeDataStr.trim());
                				latitudeData = Double.parseDouble(latitudeDataStr.trim());
                    		}
                			
                			result.setLongitude(longitudeData);
                			result.setLatitude(latitudeData);
                		}else {
                			result.setZoneId(noflyZone.getZoneId());
                			List<GeoCoordinate> geoCoordinateList = new ArrayList<GeoCoordinate>();
                			
                			for(int i = 1; i < str1.length; i++){
                				// 获取多边形禁飞区域所有的点(经纬度)
                				if( str1[i] != null ){
                					GeoCoordinate geo = new GeoCoordinate();
                					// 获取多边形点的经纬度
                        			String[] str2 = str1[i].split("\\,");
                        			longitudeDataStr = str2[0];
                        			latitudeDataStr = str2[1];
                        			if(StringUtils.isNotBlank(longitudeDataStr) && StringUtils.isNotBlank(latitudeDataStr)){
                        				longitudeData = Double.parseDouble(longitudeDataStr.trim());
                        				latitudeData = Double.parseDouble(latitudeDataStr.trim());
                            		}
                        			geo.setLatitude(latitudeData);
                        			geo.setLongitude(longitudeData);
                        			
                        			geoCoordinateList.add(geo);
                				}
                			}
                			
                			result.setGeoCoordinates(geoCoordinateList);
                			
    					}
            		
            		}
        			
        			results.add(result);
        		}
			} 
        	
        }
        
		return results;
	}
	
	/// 根据输入的地点坐标计算中心点（适用于400km以下的场合）  
	/// <param name="geoCoordinateList"></param>  
	/// <returns></returns>  
	public GeoCoordinate GetCenterPointFromListOfCoordinates(List<GeoCoordinate> geoCoordinateList)  
	{  
	    //以下为简化方法（400km以内）  
	    int total = geoCoordinateList.size();  
	    double lat = 0, lon = 0;  
	    for (GeoCoordinate geoCoordinate : geoCoordinateList) {
	    	lat += geoCoordinate.getLatitude() * Math.PI / 180;  
	        lon += geoCoordinate.getLongitude() * Math.PI / 180; 
		}
	    /*
	    foreach (GeoCoordinate g in geoCoordinateList)  
	    {  
	        lat += g.Latitude * Math.PI / 180;  
	        lon += g.Longitude * Math.PI / 180;  
	    } 
	    */
	    lat /= total;  
	    lon /= total;  
	    return new GeoCoordinate(lat * 180 / Math.PI, lon * 180 / Math.PI);  
	} 
	
	/// <summary>  
	/// 根据输入的地点坐标计算中心点   详细！
	/// </summary>  
	/// <param name="geoCoordinateList"></param>  
	/// <returns></returns>  
	/*public GeoCoordinate GetCenterPointFromListOfCoordinates(List<GeoCoordinate> geoCoordinateList)  
	{  
	    int total = geoCoordinateList.Count;  
	    double X = 0, Y = 0, Z = 0;  
	    foreach (GeoCoordinate g in geoCoordinateList)  
	    {  
	        double lat, lon, x, y, z;  
	        lat = g.Latitude * Math.PI / 180;  
	        lon = g.Longitude * Math.PI / 180;  
	        x = Math.Cos(lat) * Math.Cos(lon);  
	        y = Math.Cos(lat) * Math.Sin(lon);  
	        z = Math.Sin(lat);  
	        X += x;  
	        Y += y;  
	        Z += z;  
	    }  
	    X = X / total;  
	    Y = Y / total;  
	    Z = Z / total;  
	    double Lon = Math.Atan2(Y, X);  
	    double Hyp = Math.Sqrt(X * X + Y * Y);  
	    double Lat = Math.Atan2(Z, Hyp);  
	    return new GeoCoordinate(Lat * 180 / Math.PI, Lon * 180 / Math.PI);  
	}  */

	// 登录
	@Override
	public WrjResult userLogin(AaUserLogin vangomem) {

		// 1、先判断是否有相应的用户名
		Integer id = mapper.selectByUserName(vangomem.getUserLoginAccount());
		if (id != null && !id.equals("")) {
			
			// 密码进行md5加密
			String pwd = AppMD5Util.getMD5(vangomem.getUserPwd());
			vangomem.setUserPwd(pwd);
			// 2、在判断密码是否正确
			AaUserLogin userLogin = mapper.selectConPwd(vangomem);
			if( userLogin != null ){

				LoginResponse result = new LoginResponse();
				result.setUserId(userLogin.getUserId());
				result.setUserName(userLogin.getUserName());
				result.setUserFszh(userLogin.getUserFszh());
				result.setImagePath(userLogin.getImagePath());
				result.setBusinessLicense(userLogin.getBusinessLicense());
				result.setCuscc(userLogin.getCuscc());
				result.setEnabled(userLogin.getEnabled());
				result.setImagePath(userLogin.getImagePath());
				result.setLegalPersonName(userLogin.getLegalPersonName());
				result.setRoleId(userLogin.getRoleId());
				result.setUserAddress(userLogin.getUserAddress());
				result.setUserAge(userLogin.getUserAge());
				result.setUserCardCode(userLogin.getUserCardCode());
				result.setUserDep(userLogin.getUserDep());
				result.setUserEmail(userLogin.getUserEmail());
				result.setUserLoginAccount(userLogin.getUserLoginAccount());
				result.setUserMobilePhone(userLogin.getUserMobilePhone());
				result.setUserPhone(userLogin.getUserPhone());
				result.setUserSex(userLogin.getUserSex());
				result.setUserType(userLogin.getUserType());
				result.setUserUavIds(userLogin.getUserUavIds());
				
				result.setDisName(userLogin.getDisName());
				result.setTypeName(userLogin.getTypeName());
				result.setUserHead(userLogin.getUserHead());
				result.setCityName(userLogin.getCityName());
				result.setProName(userLogin.getProName());
				if(userLogin.getUserBirthday() != null ){
					//  日期格式化
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String userBirth = sdf.format(userLogin.getUserBirthday());
					result.setUserBirthday(userBirth);
				}
				//是否认证
				String identification = null;
				if( (userLogin.getUserType() == 1 && userLogin.getImagePath() != null && userLogin.getImagePath() != "") ||
						(userLogin.getUserType() == 2 && userLogin.getBusinessLicense() != null && userLogin.getBusinessLicense() != "") ){
					identification = "已认证";
				} else {
					identification = "未认证";
				}
				result.setIdentification(identification);
				
				return WrjResult.build(200, "登录成功", result);
				} else {
					return WrjResult.build(400, "密码错误！","");
				}
			} else {
				return WrjResult.build(400, "用户名不存在","");
			}
			
			

	}

	// 个人注册
	@Override
	public WrjResult createUserPr(AaUserLogin user) {

		// 1、使用AaUserLogin接收提交的请求。
		if (StringUtils.isBlank(user.getUserName())) {
			return WrjResult.build(400, "用户名不能为空");
		}
		if (StringUtils.isBlank(user.getUserCardCode())) {
			return WrjResult.build(400, "证件号码不能为空");
		}
		if (StringUtils.isBlank(user.getUserMobilePhone())) {
			return WrjResult.build(400, "手机号不能为空");
		}
		if (StringUtils.isBlank(user.getUserPwd())) {
			return WrjResult.build(400, "用户密码不能为空");
		}
		
		// 验证手机号是否注册
		Integer isPhone = mapper.selectUserMobilePhone(user.getUserMobilePhone());
		if(isPhone == 1){
			return WrjResult.build(400, "手机号已注册", "");
		}
		
		
		// 2、补全user其他属性。
		user.setUserSex("M");
		user.setEnabled(1);
		user.setUserDep("中国");
		// 3、密码要进行MD5加密。
		String md5Pass = AppMD5Util.getMD5(user.getUserPwd());
		user.setUserPwd(md5Pass);
		// 4、把用户信息插入到数据库中。
		mapper.insertUserPr(user);
		// 5、注册完成后将手机号设置为登录名。
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userLoginAccount", user.getUserMobilePhone());
		map.put("userMobilePhone", user.getUserMobilePhone());
		Integer result = mapper.updateUserLoginAccountForPhone(map);
		if(result > 0){
			// 6、返回WrjResult。
			return WrjResult.build(200, "注册成功", "");
		}else {
			return WrjResult.build(400, "登录名设置失败", "");
		}

	}

	// 企业注册
	@Override
	public WrjResult createUserER(AaUserLogin user) {
		// 1、使用AaUserLogin接收提交的请求。
		if (StringUtils.isBlank(user.getUserDep())) {
			return WrjResult.build(400, "工作单位不能为空");
		}
		if (StringUtils.isBlank(user.getLegalPersonName())) {
			return WrjResult.build(400, "法人代表不能为空");
		}
		if (StringUtils.isBlank(user.getCuscc())) {
			return WrjResult.build(400, "社会信用代码不能为空");
		}
		if (StringUtils.isBlank(user.getUserMobilePhone())) {
			return WrjResult.build(400, "联系电话不能为空");
		}
		if (StringUtils.isBlank(user.getUserAddress())) {
			return WrjResult.build(400, "地址不能为空");
		}
		if (StringUtils.isBlank(user.getUserPwd())) {
			return WrjResult.build(400, "用户密码不能为空");
		}
		if (StringUtils.isBlank(user.getBusinessLicense())) {
			return WrjResult.build(400, "图片为空");
		}
		
		// 验证手机号是否注册
		Integer isPhone = mapper.selectUserMobilePhone(user.getUserMobilePhone());
		if(isPhone == 1){
			return WrjResult.build(400, "手机号已注册", "");
		}

		// 2、补全user其他属性。
		user.setUserSex("M");
		user.setEnabled(1);
		user.setUserCardCode("111");
		user.setUserCardType(1);

		// 3、密码要进行MD5加密。
		String md5Pass = AppMD5Util.getMD5(user.getUserPwd());
		user.setUserPwd(md5Pass);
		// 4、把单位名称值赋予user_name这个字段
		user.setUserName(user.getUserDep());

		// 5、把用户信息插入到数据库中。
		mapper.insertUserEr(user);

		// 6、注册完成后将手机号设置为登录名。
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userLoginAccount", user.getUserMobilePhone());
		map.put("userMobilePhone", user.getUserMobilePhone());
		Integer result = mapper.updateUserLoginAccountForPhone(map);
		if(result > 0){
			// 7、返回WrjResult。
			return WrjResult.build(200, "注册成功", "");
		}else {
			return WrjResult.build(400, "登录名设置失败", "");
		}
	}

	// 忘记密码
	// 1. 验证用户名是否存在
	@Override
	public int getUserLoginAccount(AaUserLogin user) {
		return mapper.selectUserLoginAccount(user);
	}
	// 2. 更新密码
	@Override
	public WrjResult updateUserByUserLoginAccount(AaUserLogin user) {
		// 1、密码要进行MD5加密。
		String md5Pass = AppMD5Util.getMD5(user.getUserPwd());
		user.setUserPwd(md5Pass);

		mapper.updateUserByUserLoginAccount(user);

		return WrjResult.build(200, "密码修改成功");

	}

	// 飞行记录
	// 1.记录总条数
	@Override
	public Integer queryCount(Integer userId) {
		return mapper.selectCount(userId);
	}

//	// 2.记录的内容
//	@Override
//	public List<LogResult> getItemList(PagingParameters pp) {
//		List<LogResult> list = new ArrayList<>();
//
//		List<LogPojo> itemList = mapper.selectItemList(pp);
//		for (LogPojo logPojo : itemList) {
//			LogResult log = new LogResult();
//
//			log.setRouteId(logPojo.getRouteId());
//			log.setGraph(logPojo.getGraph());
//			log.setImagePath(logPojo.getImagePath());
//			log.setMaxHeightName(logPojo.getMaxHeightName());
//			log.setStartTime(logPojo.getStartTime());
//			// 处理飞行时间
//			long time = logPojo.getEndTime().getTime() - logPojo.getStartTime().getTime();
//			// long days = time / (1000 * 60 * 60 * 24);
//			// long hours = (time % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
//			// long minutes = (time % (1000 * 60 * 60)) / (1000 * 60);
//			// long seconds = (time % (1000 * 60)) / 1000;
//			String flyTime = time + "毫秒";
//			log.setFlyTime(flyTime);
//
//			list.add(log);
//		}
//		return list;
//	}
	
	// 2.记录的内容
		@Override
		public List<LogResult> getItemList(Map<String,Object> map) {
			List<LogResult> list = new ArrayList<>();

			List<LogPojo> itemList = mapper.selectItemList(map);
			if(itemList != null && itemList.size() != 0){
				for (LogPojo logPojo : itemList) {
					LogResult log = new LogResult();

					log.setRouteId(logPojo.getRouteId());
					log.setImagePath(logPojo.getImagePath());
					log.setMaxHeightName(logPojo.getMaxHeightName());
					log.setStartTime(DateUtil.formatDate(logPojo.getStartTime()));
					log.setEndTime(DateUtil.formatDate(logPojo.getEndTime()));
					
					log.setUnitName(logPojo.getUnitName());
					
					// 处理飞行时间
					SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
					String startTime = sdf.format(logPojo.getStartTime());
					String endTime = sdf.format(logPojo.getEndTime());
					log.setFlyTime(startTime + "-" + endTime);
//					long time = logPojo.getEndTime().getTime() - logPojo.getStartTime().getTime();
					// long days = time / (1000 * 60 * 60 * 24);
					// long hours = (time % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
					// long minutes = (time % (1000 * 60 * 60)) / (1000 * 60);
					// long seconds = (time % (1000 * 60)) / 1000;

					list.add(log);
				}
			}
			
			return list;
		}

	// 飞行记录详情
	@Override
	public WrjResult getLogDetail(Integer routeId) {
		LogPojo logPojo = mapper.selectLogDetail(routeId);

		if (logPojo != null) {
			LogDetailResult result = new LogDetailResult();
			result.setRouteId(logPojo.getRouteId());
			result.setImagePath(logPojo.getImagePath());
			result.setMaxHeightName(logPojo.getMaxHeightName());
			
			result.setStartTime(DateUtil.formatDate(logPojo.getStartTime()));
			result.setEndTime(DateUtil.formatDate(logPojo.getEndTime()));
			result.setUnitName(logPojo.getUnitName());
			
			// 飞行轨迹 路径详情
			List<UavStatusHisResponse> list = new ArrayList<UavStatusHisResponse>();
			List<AaUavStatusHis> flyTrails = mapper.selectFlyTrail(routeId);
			
			Double uavGpsLat, uavGpsLon;
			if( flyTrails != null && flyTrails.size() > 0){
				for (AaUavStatusHis ush : flyTrails) {
					UavStatusHisResponse res = new UavStatusHisResponse();
					res.setInNoflyingZone(ush.getInNoflyingZone());
					
					uavGpsLat = ush.getUavGpsLat();
					uavGpsLon = ush.getUavGpsLon();
					res.setUavGpsLat(uavGpsLat);
					res.setUavGpsLon(uavGpsLon);
					
					// 调用百度API- 逆向地理编码服务提供将坐标点（经纬度）转换为对应位置信息（如所在行政区划，周边地标点分布）功能。
//					String info = GeocodingUtils.GetGeocodingData(uavGpsLat,uavGpsLon,"fUOnjZHHx1kAF7Q2Dq34mloqypa2m1eh");
					
					
					list.add(res);
				}
			}
			result.setFlyTrails(list);
			
			
			
			//处理飞行时间
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
			String startTime = sdf.format(logPojo.getStartTime());
			String endTime = sdf.format(logPojo.getEndTime());
			result.setFlyTime(startTime + "-" + endTime);
			
			return WrjResult.ok(result);
		} else {
			return WrjResult.build(400, "查询失败", "");
		}
	}
	
	
	// 省市区
	@Override
	public WrjResult getProvinceCityDistrict() {
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<Province> provinces = mapper.selectAllProvince();
		List<ProvinceResponse> provinceResults = new ArrayList<ProvinceResponse>();
		if( provinces != null && provinces.size() > 0){
			for (Province province : provinces) {
				ProvinceResponse provinceResponse = new ProvinceResponse();
				
				provinceResponse.setProId(province.getProId());
				provinceResponse.setProName(province.getProName());
				
				List<City> cityList = mapper.selectCityByProId(province.getProId());
				List<CityResponse> cityResults = new ArrayList<CityResponse>();
				if(cityList != null && cityList.size() > 0){
					for (City city : cityList) {
						CityResponse cityResponse = new CityResponse();
						
						cityResponse.setCityId(city.getCityId());
						cityResponse.setCityName(city.getCityName());
						
						List<District> districtList = mapper.selectDistrictByCityId(city.getCityId());
						if(districtList != null && districtList.size() > 0){
							cityResponse.setDistricts(districtList);
						}
						
						cityResults.add(cityResponse);
					}
					
				}
				provinceResponse.setCities(cityResults);
				
				provinceResults.add(provinceResponse);
			}
		}
		map.put("provinces", provinceResults);
		
		if(map != null && map.size() > 0){
			return WrjResult.build(200, "查询成功", map);
		}else {
			return WrjResult.build(400, "查询失败", "");
		}
		
	}
	// 无人机名称和任务类型
	@Override
	public WrjResult getUavType(Integer userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<UavResponse> uavName = mapper.selectUavName(userId);
		List<TaskTypeResponse> taskType = mapper.selectTaskType();
		if(uavName != null && uavName.size() > 0 ){
			map.put("uavName", uavName);
		}else {
			return WrjResult.build(400, "该用户没有无人机", "");
		}
		
		if(taskType != null && taskType.size() > 0){
			map.put("taskType", taskType);
		}else {
			return WrjResult.build(400, "任务类型查询失败", map);
		}
		
		List<Map<String, Object>> maxHeight = mapper.selectMaxHeight();
		if(maxHeight != null && maxHeight.size() > 0){
			map.put("maxHeight", maxHeight);
		}else {
			return WrjResult.build(400, "最大飞行高度查询失败", map);
		}
		
		return WrjResult.build(200, "查询成功", map);
		
	}
	// 添加飞行申请
	@Override
	public WrjResult createRoute(AaRouteLog route) {
		// 1、补全route其他属性。
		route.setStartGps("1");
		route.setEndGps("2");
		route.setRouteUavUniqueId("uav");
		// 当前计划状态，2已完成，1在飞，0未飞
		route.setStatus(0);
		// 1:审批中 2:已通过 3:未通过
		route.setApprovalStatus(1);
		// 2、把用户信息插入到数据库中。
		mapper.insertRoute(route);
		// 3、返回WrjResult。
		return WrjResult.build(200, "申请成功", "");

	}

	// 消息列表展示
	@Override
	public Integer queryInfoCount(Integer userId) {
		return mapper.selectInfoCount(userId);
	}
	@Override
	public List<InformationRes> getInfomationList(Map<String,Object> map) {
		List<InformationRes> list = new ArrayList<InformationRes>();
		
		List<InformationResult> infoList = mapper.selectInfomationList(map);
		if(infoList!=null && infoList.size()!=0){
			for (InformationResult info : infoList) {
				InformationRes result = new InformationRes();
				
				result.setAddTime(DateUtil.formatDate(info.getAddTime()));
				result.setInfoContent(info.getInfoContent());
				result.setInfoId(info.getInfoId());
				result.setInfoType(info.getInfoType());
				result.setTypeName(info.getTypeName());
				
				list.add(result);
			}
		}
		return list;
	}

	// 消息详情
	@Override
	public WrjResult getInformation(Integer infoId) {
		InformationResult info = mapper.selectInfomation(infoId);
		if (info != null) {
			InformationRes result = new InformationRes();
			result.setAddTime(DateUtil.formatDate(info.getAddTime()));
			result.setInfoContent(info.getInfoContent());
			result.setInfoId(info.getInfoId());
			result.setTypeName(info.getTypeName());
			result.setInfoType(info.getInfoType());
			
			return WrjResult.ok(result);
		} else {
			return WrjResult.build(400, "查询失败");
		}

	}

	// 点击进入个人信息展示
	@Override
	public WrjResult getPersonalInfo(Integer userId) {
		AaUserLogin userLogin = mapper.selectPersonalInfo(userId);
		if (userLogin != null) {
			
			LoginResponse result = new LoginResponse();
			result.setUserId(userLogin.getUserId());
			result.setUserName(userLogin.getUserName());
			result.setUserFszh(userLogin.getUserFszh());
			result.setImagePath(userLogin.getImagePath());
			result.setBusinessLicense(userLogin.getBusinessLicense());
			result.setCuscc(userLogin.getCuscc());
			result.setEnabled(userLogin.getEnabled());
			result.setImagePath(userLogin.getImagePath());
			result.setLegalPersonName(userLogin.getLegalPersonName());
			result.setRoleId(userLogin.getRoleId());
			result.setUserAddress(userLogin.getUserAddress());
			result.setUserAge(userLogin.getUserAge());
			result.setUserCardCode(userLogin.getUserCardCode());
			result.setUserDep(userLogin.getUserDep());
			result.setUserEmail(userLogin.getUserEmail());
			result.setUserLoginAccount(userLogin.getUserLoginAccount());
			result.setUserMobilePhone(userLogin.getUserMobilePhone());
			result.setUserPhone(userLogin.getUserPhone());
			result.setUserSex(userLogin.getUserSex());
			result.setUserType(userLogin.getUserType());
			result.setUserUavIds(userLogin.getUserUavIds());
			
			result.setDisName(userLogin.getDisName());
			result.setTypeName(userLogin.getTypeName());
			result.setUserHead(userLogin.getUserHead());
			result.setCityName(userLogin.getCityName());
			result.setProName(userLogin.getProName());
			// 生日格式化。
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if(userLogin.getUserBirthday() != null){
				String userBirth = sdf.format(userLogin.getUserBirthday());
				result.setUserBirthday(userBirth);
			}
			
			//是否认证
			String identification = null;
			if( (userLogin.getUserType() == 1 && userLogin.getImagePath() != null && userLogin.getImagePath() != "") ||
					(userLogin.getUserType() == 2 && userLogin.getBusinessLicense() != null && userLogin.getBusinessLicense() != "") ){
				identification = "已认证";
			} else {
				identification = "未认证";
			}
			result.setIdentification(identification);
			
			return WrjResult.ok(result);
		} else {
			return WrjResult.build(400, "查询失败", "");
		}
	}

	// 编辑保存用户个人信息
	@Override
	public WrjResult updatePersonalInfo(AaUserLogin user) {
		Integer result = mapper.updatePersonalInfo(user);
		if(result > 0){
			return WrjResult.build(200, "保存成功", "");
		}
		return WrjResult.build(400, "保存失败", "");
	}
	// 编辑保存企业信息
	@Override
	public WrjResult updateEnterpriseInfo(AaUserLogin user) {
		Integer result = mapper.updateEnterpriseInfo(user);
		if(result > 0){
			return WrjResult.build(200, "保存成功", "");
		}
		return WrjResult.build(400, "保存失败", "");
	}
	
	
	
	
	
	
	// 无人机列表
	// 1.无人机总条数
	@Override
	public int queryCountUav() {
		return mapper.selectCountUav();
	}
	// 2.无人机列表
	@Override
	public List<UavResult> getUavList(Integer userId) {
		List<UavResult> list = mapper.selectUavList(userId);
		if(list != null && list.size() != 0){
			return list;
		}else {
			return null;
		}
	}
	
	//类型、品牌、型号
	@Override
	public WrjResult getTypeBrand() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<BrandResponse> brandList = mapper.selectBrandList();
		List<UavTypeResponse> uavTypeList = mapper.selectUavTypeList();
		List<WorkingLoadResponse> wokingLoadList = mapper.selectWokingLoadList();
		if(brandList != null && brandList.size() > 0 
				&& uavTypeList != null && uavTypeList.size() > 0
				&& wokingLoadList != null && wokingLoadList.size() > 0){
			map.put("uavType", uavTypeList);
			map.put("uavBrand", brandList);
			map.put("wokingLoad", wokingLoadList);
		}
		return WrjResult.build(200, "查询成功", map);
	}
	
	// 添加无人机
	@Override
	public int createUav(AaUav uav) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date valiaDate = formatter.parse("2019-05-01");
			uav.setValidDate(valiaDate);
			
		} catch (ParseException e) {
			e.printStackTrace();
			return 0;
		}
		return mapper.createUav(uav);
	}


	//无人机详情
	@Override
	public UavDetailResponse getUavMessageList(Integer uavId) {
		UavDetailResponse result = new UavDetailResponse();
		UavDetailResult info = mapper.getUavMessageList(uavId);
		if(info != null){
			result.setImagePath(info.getImagePath());
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String madeDate = sdf.format(info.getMadeDate());
			result.setMadeDate(madeDate);
			
			result.setStatusName(info.getStatusName());
			result.setUavApplicatton(info.getUavApplicatton());
			result.setUavAxleDistance(info.getUavAxleDistance());
			result.setUavName(info.getUavName());
			result.setUavPropellerLength(info.getUavPropellerLength());
			result.setUavStructure(info.getUavStructure());
			result.setUavType(info.getUavType());
			result.setUavUniqueId(info.getUavUniqueId());
			result.setWorkingLoadId(info.getWorkingLoadId());
			result.setBrandName(info.getBrandName());
			result.setWorkingLoadDesc(info.getWorkingLoadDesc());
		}
		return result;
	}
	
	
	// 注销无人机
	@Override
	public int updateUavMessage(Integer uavId) {
		return mapper.updateUavMessage(uavId);
	}

	//我的申飞
	//1. 总条数
	@Override
	public Integer queryCountShenFei(Map<String,Object> map) {
		return mapper.selectCountShenFei(map);
	}
	//2. 内容
	@Override
	public List<ShenFeiRes> getUavShenFeiList(Map<String,Object> map) {
		List<ShenFeiRes> list = new ArrayList<ShenFeiRes>();
		
		List<ShenFeiResult> infoList = mapper.getUavShenFeiList(map);
		if(infoList!=null && infoList.size()!=0){
			for (ShenFeiResult info : infoList) {
				ShenFeiRes result = new ShenFeiRes();
				
				result.setIamgePath(info.getIamgePath());
				result.setMaxHeightName(info.getMaxHeightName());
				result.setStatusName(info.getStatusName());
				result.setUavType(info.getUavType());
				result.setUnitName(info.getUnitName());
				result.setBrandName(info.getBrandName());
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String startTime = sdf.format(info.getStartTime());
				result.setStartTime(startTime);
				//处理时间
				SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");
				String startTime2 = sdf2.format(info.getStartTime());
				String endTime = sdf2.format(info.getEndTime());
				result.setFlyTime(startTime2 + "-" + endTime);
				
				list.add(result);
			}
		}
		return list;
	}

	//查询用户名和密码
	@Override
	public int selectPwd(AaUserLogin aaUserLogin) {
		return mapper.selectPwd(aaUserLogin);
	}

	//修改密码
	@Override
	public int updatePwd(AaUserLogin aaUserLogin) {
		return mapper.updatePwd(aaUserLogin);
	}

	
	
	//违规行径
	//1. 违规飞行总数查询
	@Override
	public int queryCountRoute(Integer userId) {
		return mapper.queryCountRoute(userId);
	}
	//2. 违规飞行记录
	@Override
	public List<WeiguiResult> getRouteList(Map<String,Object> map) {
		List<WeiguiResult> list = new ArrayList<WeiguiResult>();
		
		List<AaIrregularities> infoList = mapper.getRouteList(map);
		if(infoList!=null && infoList.size()!=0){
			for (AaIrregularities info : infoList) {
				WeiguiResult result = new WeiguiResult();
				
				result.setRuleContent(info.getRuleContent());
				result.setRuleTitle(info.getRuleTitle());
				result.setRuleDate(DateUtil.formatDate(info.getRuleDate()));
					
				list.add(result);
			}
		}
		return list;
	}

	@Override
	public List<SealInfo> findSealInfoList() {
		return mapper.selectSealInfoList();
	}

	@Override
	public Integer saveSealInfo(SealInfo sealInfo) {
		return mapper.insertSealInfo(sealInfo);
	}




}
