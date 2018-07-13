package com.vt.fencing.user.service;

import java.util.List;
import java.util.Map;

import com.vt.base.service.IBaseService;
import com.vt.fencing.model.AaRouteLog;
import com.vt.fencing.model.AaUav;
import com.vt.fencing.model.AaUserLogin;
import com.vt.fencing.model.SealInfo;
import com.vt.fencing.model.VangoMember;
import com.vt.fencing.model.VangoMemberExample;
import com.vt.fencing.model.vo.HomePageInfoResponse;
import com.vt.fencing.model.vo.InformationRes;
import com.vt.fencing.model.vo.LogResult;
import com.vt.fencing.model.vo.ShenFeiRes;
import com.vt.fencing.model.vo.UavDetailResponse;
import com.vt.fencing.model.vo.UavResult;
import com.vt.fencing.model.vo.WeiguiResult;
import com.vt.fencing.user.util.WrjResult;

public interface IVangoMemberService extends IBaseService<VangoMember, VangoMemberExample, Integer>{
	
	// 查询印章信息列表
	public List<SealInfo> findSealInfoList();
	
	// 保存印章信息
	public Integer saveSealInfo(SealInfo sealInfo);
	
	// 登录
	public WrjResult userLogin(AaUserLogin vangomem);
	
	// 上传头像
	public Integer updateUserHeadByUserId(Map<String,Object> map);
	
	// 注册
	// 1、个人注册
	public WrjResult createUserPr(AaUserLogin user);
	///2、企业注册
	public WrjResult createUserER(AaUserLogin user);
	
	
	
	// 忘记密码
	// 1. 验证用户名是否存在
	public int getUserLoginAccount(AaUserLogin user);
	// 2. 更新密码
	public WrjResult updateUserByUserLoginAccount(AaUserLogin user);
	
	
	// 飞行记录
	// 1、记录总条数
	public Integer queryCount(Integer userId);
	// 2、记录的内容
	public List<LogResult> getItemList(Map<String,Object> map);
	// 飞行记录详情
	public WrjResult getLogDetail(Integer routeId);

	
	// 省市区
	public WrjResult getProvinceCityDistrict();
	// 无人机名称和任务类型
	public WrjResult getUavType(Integer userId);
	// 添加飞行申请
	public WrjResult createRoute(AaRouteLog route);

	
	
	// 消息列表展示
	public Integer queryInfoCount(Integer userId);
	public List<InformationRes> getInfomationList(Map<String,Object> map);
	// 消息详情
	public WrjResult getInformation(Integer infoId);


	// 点击进入个人信息展示
	public WrjResult getPersonalInfo(Integer userId);
	// 编辑保存用户个人信息
	public WrjResult updatePersonalInfo(AaUserLogin user);
	// 编辑保存企业信息
	public WrjResult updateEnterpriseInfo(AaUserLogin user);
	
	
	// 首页
	public List<HomePageInfoResponse> getHomePageInfo(Double longitude, Double latitude);
	

	/**
	 * 通过会员编号查询会员信息
	 * @param memberCode
	 * @return
	 */
	public VangoMember getMemberByMemCode(String memberCode);


	
	
	
	// 无人机列表
	// 1.无人机总条数
	public int queryCountUav();
	// 2.无人机信息list
	public List<UavResult> getUavList(Integer userId);
	
	
	//无人机详情
	public UavDetailResponse getUavMessageList(Integer uavId);
	
	//注销无人机
	public int updateUavMessage(Integer uavId);
	
	
	//类型、品牌、型号
	public WrjResult getTypeBrand();
	// 添加无人机
	public int createUav(AaUav uav);
	



	//我的申飞
	//1. 总条数
	public Integer queryCountShenFei(Map<String,Object> map);
	//2. 内容
	public List<ShenFeiRes> getUavShenFeiList(Map<String,Object> map);


	//查询用户名和密码
	public int selectPwd(AaUserLogin aaUserLogin);


	//修改密码
	public int updatePwd(AaUserLogin aaUserLogin);

	
	//违规行径
	//1. 违规飞行总数查询
	public int queryCountRoute(Integer userId);
	//2. 违规飞行记录
	public List<WeiguiResult> getRouteList(Map<String,Object> map);




}
