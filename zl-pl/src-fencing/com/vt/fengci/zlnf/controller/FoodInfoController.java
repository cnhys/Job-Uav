package com.vt.fengci.zlnf.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vt.base.OptResult;
import com.vt.base.controller.BaseGatewayController;
import com.vt.base.util.GetLocation;
import com.vt.fencing.model.ZlnfApppiontOrderTime;
import com.vt.fencing.model.ZlnfApppiontOrderTimeExample;
import com.vt.fencing.model.ZlnfCoopInfo;
import com.vt.fencing.model.ZlnfCoopInfoExample;
import com.vt.fencing.model.ZlnfCoopUser;
import com.vt.fencing.model.ZlnfCoopUserExample;
import com.vt.fencing.model.ZlnfDictInfo;
import com.vt.fencing.model.ZlnfDictInfoExample;
import com.vt.fencing.model.ZlnfFieldInfo;
import com.vt.fencing.model.ZlnfFieldInfoExample;
import com.vt.fencing.model.ZlnfFoodInfo;
import com.vt.fencing.model.ZlnfFoodInfoExample;
import com.vt.fencing.model.ZlnfFoodServiceInfo;
import com.vt.fencing.model.ZlnfFoodServiceInfoExample;
import com.vt.fencing.model.ZlnfGraindepotHis;
import com.vt.fencing.model.ZlnfGraindepotHisExample;
import com.vt.fencing.model.ZlnfGraindepotNew;
import com.vt.fencing.request.ZlnfFoodInfoRequest;
import com.vt.fengci.zlnf.service.IApppiontOrderTimeService;
import com.vt.fengci.zlnf.service.ICoopInfoService;
import com.vt.fengci.zlnf.service.ICoopUserService;
import com.vt.fengci.zlnf.service.IDictInfoService;
import com.vt.fengci.zlnf.service.IFieldInfoService;
import com.vt.fengci.zlnf.service.IFoodInfoService;
import com.vt.fengci.zlnf.service.IFoodServiceInfoService;
import com.vt.fengci.zlnf.service.IGraindepotHisService;
import com.vt.fengci.zlnf.service.IGraindepotNewService;

/**
 * user related functions Created by john on 17/7/17.
 */
@Controller
public class FoodInfoController extends BaseGatewayController {

	private static final long serialVersionUID = 4193011716881568078L;
	/**
	 * member service
	 */

	@Autowired
	private IFoodInfoService foodInfoService;

	@Autowired
	private ICoopUserService coopUserService;

	@Autowired
	private ICoopInfoService coopInfoService;

	@Autowired
	private IGraindepotHisService graindepotHisService;

	@Autowired
	private IApppiontOrderTimeService apppiontOrderTimeService;

	@Autowired
	private IFoodServiceInfoService foodServiceInfoService;

	@Autowired
	private IDictInfoService dictInfoService;

	@Autowired
	private IGraindepotNewService graindepotNewService;

	@Autowired
	private IFieldInfoService fieldInfoService;

	/**
	 * 获取合作社附近粮库信息
	 * 
	 * @param channel
	 * @param key
	 * @param data
	 * @return
	 */
	@RequestMapping(value = { "/api/foodInfo/query" }, method = { RequestMethod.POST })
	@ResponseBody
	public OptResult foodInfoQuery(String channel, String key, String data) {
		// 0. 检查
		channelKeyCheck(channel, key);
		if (StringUtils.isEmpty(data)) {
			reject("user.activate.data.empty");
		}
		// 1. 转换数据
		ZlnfFoodInfoRequest zlnfFoodInfoRequest = convert(data, ZlnfFoodInfoRequest.class);
		// 2. 业务检查
		if (zlnfFoodInfoRequest == null) {
			reject("user.activate.model.convert.error");
		}
		OptResult result = OptResult.success();
		try {

			ZlnfCoopUserExample coopUserExample = new ZlnfCoopUserExample();
			coopUserExample.createCriteria().andUsercodeEqualTo(zlnfFoodInfoRequest.getUsercode());
			List<ZlnfCoopUser> coopUserlist = coopUserService.getResult(coopUserExample);
			if (coopUserlist.size() > 0) {
				ZlnfCoopInfoExample coopInfoExample = new ZlnfCoopInfoExample();
				coopInfoExample.createCriteria().andCoopcodeEqualTo(coopUserlist.get(0).getCoopcode());
				List<ZlnfCoopInfo> coopInfolist = coopInfoService.getResult(coopInfoExample);
				if (coopInfolist.size() > 0) {
					String lng = coopInfolist.get(0).getLng();
					String lat = coopInfolist.get(0).getLat();
					ZlnfFoodInfoExample foodInfoExample = new ZlnfFoodInfoExample();
					ZlnfFoodInfoExample.Criteria criteria = foodInfoExample.createCriteria();
					criteria.andProviceEqualTo(coopInfolist.get(0).getProvice());
					criteria.andCityEqualTo(coopInfolist.get(0).getCity());
					List<ZlnfFoodInfo> foodInfolist = foodInfoService.getResult(foodInfoExample);
					List<ZlnfFoodInfo> foodInfoLists = new ArrayList<ZlnfFoodInfo>();
					if (foodInfolist.size() > 3) {
						List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
						for (int i = 0; i < foodInfolist.size(); i++) {
							Map<String, Object> map = new HashMap<String, Object>();
							String lng1 = foodInfolist.get(i).getLng();
							String lat1 = foodInfolist.get(i).getLat();
							Float lngf = Math.abs(Float.valueOf(lng) - Float.valueOf(lng1));
							Float latf = Math.abs(Float.valueOf(lat) - Float.valueOf(lat1));
							map.put("floats", lngf * lngf + latf * latf);
							map.put("foodCoods", foodInfolist.get(i).getFoodcode());
							list.add(map);
						}
						List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();
						int n = 0;
						for (int j = 0; j < 3; j++) {
							String fl1 = String.valueOf(list.get(0).get("floats"));
							Float float1 = Float.valueOf(fl1);
							for (int i = 0; i < list.size(); i++) {
								String fl2 = String.valueOf(list.get(i).get("floats"));
								Float float2 = Float.valueOf(fl2);
								if (float1 > float2) {
									float1 = float2;
									n = i;
								}
							}
							list1.add(list.get(n));
							list.remove(n);
						}
						for (int i = 0; i < list1.size(); i++) {
							for (int j = 0; j < foodInfolist.size(); j++) {
								if (list1.get(i).get("foodCoods").equals(foodInfolist.get(j).getFoodcode())) {
									foodInfoLists.add(foodInfolist.get(j));
								}
							}
						}
					} else {
						for (int i = 0; i < foodInfolist.size(); i++) {
							foodInfoLists.add(foodInfolist.get(i));
						}
					}
					result.setSuccess(true);
					result.setData(foodInfoLists);
					result.setReturnCode("0000");
					result.setMessage("获取合作社附近粮库信息成功");
				} else {
					result.setSuccess(false);
					result.setReturnCode("0001");
					result.setMessage("该合作社不存在");
				}
			} else {
				result.setSuccess(false);
				result.setReturnCode("0001");
				result.setMessage("该合作社不存在");
			}
		} catch (Exception e) {
			// TODO: handle exception
			result.setSuccess(false);
			result.setReturnCode("1111");
			result.setMessage("获取合作社附近粮库信息失败");
		}
		return result;
	}

	/**
	 * 获取粮库信息
	 * 
	 * @param channel
	 * @param key
	 * @param data
	 * @return
	 */
	@RequestMapping(value = { "/api/foodInfo/querys" }, method = { RequestMethod.POST })
	@ResponseBody
	public OptResult foodInfoQuerys(String channel, String key, String data) {
		// 0. 检查
		channelKeyCheck(channel, key);
		if (StringUtils.isEmpty(data)) {
			reject("user.activate.data.empty");
		}
		// 1. 转换数据
		ZlnfFoodInfoRequest zlnfFoodInfoRequest = convert(data, ZlnfFoodInfoRequest.class);
		// 2. 业务检查
		if (zlnfFoodInfoRequest == null) {
			reject("user.activate.model.convert.error");
		}
		OptResult result = OptResult.success();
		try {
			String province = zlnfFoodInfoRequest.getProvice();
			if (province != null && province != "") {
				ZlnfFoodInfoExample foodInfoExample = new ZlnfFoodInfoExample();
				ZlnfFoodInfoExample.Criteria foodInfoCriteria = foodInfoExample.createCriteria();
				foodInfoCriteria.andProviceEqualTo(province);
				foodInfoCriteria.andIsdeletedEqualTo("0");
				List<ZlnfFoodInfo> foodInfoList = foodInfoService.getResult(foodInfoExample);
				for(int i=0;i<foodInfoList.size();i++){
					ZlnfGraindepotNew zlnfGraindepotNew = new ZlnfGraindepotNew();
					zlnfGraindepotNew.setFoodcode(foodInfoList.get(i).getFoodcode());
					List<ZlnfGraindepotNew> graindepotNewList = graindepotNewService
							.queryFoodcode(zlnfGraindepotNew);
					foodInfoList.get(i).setGraindepotNewList(graindepotNewList);
				}
				result.setSuccess(true);
				result.setData(foodInfoList);
				result.setReturnCode("0000");
				result.setMessage("获取粮库信息成功");
			} else {

				ZlnfFieldInfoExample fieldInfoExample = new ZlnfFieldInfoExample();
				ZlnfFieldInfoExample.Criteria coopInfoCriteria = fieldInfoExample.createCriteria();
				coopInfoCriteria.andIsdeletedEqualTo("0");
				coopInfoCriteria.andCoopcodeEqualTo(zlnfFoodInfoRequest.getCoopcode());
				String roletype = zlnfFoodInfoRequest.getRoletype();
				if (!"5".equals(roletype) && !"1".equals(roletype) && !"2".equals(roletype)) {
					coopInfoCriteria.andCreatorcodeEqualTo(zlnfFoodInfoRequest.getUsercode());
				}
				List<ZlnfFieldInfo> fieldInfoList = fieldInfoService.getResult(fieldInfoExample);
				// for(int i = 0;i<fieldInfoList.size();i++){
				// ZlnfDictInfoExample dictInfoExample = new
				// ZlnfDictInfoExample();
				// ZlnfDictInfoExample.Criteria criteria =
				// dictInfoExample.createCriteria();
				// criteria.andIsdeletedEqualTo("0");
				// criteria.andDictcodeEqualTo(fieldInfoList.get(i).getCrops());
				// List<ZlnfDictInfo> dictInfoList2 =
				// dictInfoService.getResult(dictInfoExample);
				// if(dictInfoList2.size()>0){
				// fieldInfoList.get(i).setCrops(dictInfoList2.get(0).getDictname());
				// }
				// }
				ZlnfCoopUserExample coopExample = new ZlnfCoopUserExample();
				coopExample.createCriteria().andUsercodeEqualTo(zlnfFoodInfoRequest.getUsercode());
				List<ZlnfCoopUser> coopList = coopUserService.getResult(coopExample);
				ZlnfCoopInfoExample coopInfoExample = new ZlnfCoopInfoExample();//
				coopInfoExample.createCriteria().andCoopcodeEqualTo(coopList.get(0).getCoopcode());
				List<ZlnfCoopInfo> coopInfoList = coopInfoService.getResult(coopInfoExample);
				List<ZlnfFoodInfo> foodInfoList = new ArrayList<>();
				if (coopInfoList.get(0).getProvice() != null) {
					ZlnfFoodInfoExample foodInfoExample = new ZlnfFoodInfoExample();
					ZlnfFoodInfoExample.Criteria foodInfoCriteria = foodInfoExample.createCriteria();
					foodInfoCriteria.andProvicecodeEqualTo(coopInfoList.get(0).getProvicecode());
					foodInfoList = foodInfoService.getResult(foodInfoExample);
					if (foodInfoList.size() > 0) {
						for (int i = 0; i < foodInfoList.size(); i++) {
							ZlnfGraindepotNew zlnfGraindepotNew = new ZlnfGraindepotNew();
							zlnfGraindepotNew.setFoodcode(foodInfoList.get(i).getFoodcode());
							List<ZlnfGraindepotNew> graindepotNewList = graindepotNewService
									.queryFoodcode(zlnfGraindepotNew);
							foodInfoList.get(i).setGraindepotNewList(graindepotNewList);
							ZlnfGraindepotHisExample graindepotExample1 = new ZlnfGraindepotHisExample();
							ZlnfGraindepotHisExample.Criteria criteria1 = graindepotExample1.createCriteria();
							Calendar now = Calendar.getInstance();
							now.setTime(new Date());
							now.set(Calendar.DATE, now.get(Calendar.DATE) - 1);
							criteria1.andCreatedonutcEqualTo(now.getTime());
							criteria1.andFoodcodeEqualTo(foodInfoList.get(i).getFoodcode());
							criteria1.andDatatypeEqualTo("2");
							List<ZlnfGraindepotHis> graindepotList1 = graindepotHisService
									.getResult(graindepotExample1);

							if (graindepotList1.size() > 0) {
								foodInfoList.get(i).setYesterFoodKind(graindepotList1.get(0).getFoodkind());
								foodInfoList.get(i).setYesterGraindNum(graindepotList1.get(0).getGrainnum());
							}
							if (coopInfoList.get(0).getLng() != null && coopInfoList.get(0).getLat() != null
									&& foodInfoList.get(i).getLng() != null && foodInfoList.get(i).getLat() != null) {
								Double distancedb = GetLocation.GetDistance(
										Double.valueOf(coopInfoList.get(0).getLng()),
										Double.valueOf(coopInfoList.get(0).getLat()),
										Double.valueOf(foodInfoList.get(i).getLng()),
										Double.valueOf(foodInfoList.get(i).getLat()));
								foodInfoList.get(i).setDistance(distancedb);
							}
						}
					}
				}
				// 按照距离排序
				List<ZlnfFoodInfo> foodInfoList1 = new ArrayList<>();
				foodInfoList1 = distanceOrder(foodInfoList, foodInfoList1);
				Map<String, Object> map = new HashMap<>();
				map.put("foodInfoList", foodInfoList1);
				map.put("fieldInfoList", fieldInfoList);
				result.setSuccess(true);
				result.setData(map);
				result.setReturnCode("0000");
				result.setMessage("获取粮库信息成功");
			}
		} catch (Exception e) {
			// TODO: handle exception
			result.setSuccess(false);
			result.setReturnCode("1111");
			result.setMessage("获取粮库信息失败");
		}
		return result;
	}

	// 递归实现按距离排序
	public List<ZlnfFoodInfo> distanceOrder(List<ZlnfFoodInfo> foodInfoList, List<ZlnfFoodInfo> foodInfoList1) {
		int a = 0;
		Double d1 = (double) 0;
		for (int i = 0; i < foodInfoList.size(); i++) {
			if (i == 0) {
				a = 0;
				d1 = foodInfoList.get(0).getDistance();
			} else {
				Double d2 = foodInfoList.get(i).getDistance();
				if (d1 > d2) {
					d1 = d2;
					a = i;
				}
			}
		}
		foodInfoList1.add(foodInfoList.get(a));
		foodInfoList.remove(a);
		if (foodInfoList.size() > 0) {
			return distanceOrder(foodInfoList, foodInfoList1);
		} else {
			return foodInfoList1;
		}
	}

	/**
	 * 获取粮库30天，7天记录
	 * 
	 * @param channel
	 * @param key
	 * @param data
	 * @return
	 */
	@RequestMapping(value = { "/api/foodInfo/queryDate" }, method = { RequestMethod.POST })
	@ResponseBody
	public OptResult foodInfoQueryThirty(String channel, String key, String data) {
		// 0. 检查
		channelKeyCheck(channel, key);
		if (StringUtils.isEmpty(data)) {
			reject("user.activate.data.empty");
		}
		// 1. 转换数据
		ZlnfFoodInfoRequest zlnfFoodInfoRequest = convert(data, ZlnfFoodInfoRequest.class);
		// 2. 业务检查
		if (zlnfFoodInfoRequest == null) {
			reject("user.activate.model.convert.error");
		}
		OptResult result = OptResult.success();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Map<String, Object> m = new HashMap<>();
			// 查询30历史记录
			ZlnfFoodInfoExample foodInfoExample1 = new ZlnfFoodInfoExample();
			ZlnfFoodInfoExample.Criteria foodInfoCriteria1 = foodInfoExample1.createCriteria();
			foodInfoCriteria1.andFoodcodeEqualTo(zlnfFoodInfoRequest.getFoodcode());
			List<ZlnfFoodInfo> foodInfoList1 = foodInfoService.getResult(foodInfoExample1);

			List<ZlnfGraindepotHis> graindepotList1 = new ArrayList<>();
			ZlnfGraindepotHis record = new ZlnfGraindepotHis();
			record.setFoodcode(zlnfFoodInfoRequest.getFoodcode());
			List<String> listStr = graindepotHisService.queryCreateDate(record);
			if (listStr.size() > 0 && listStr != null) {

				for (int i = 0; i < listStr.size(); i++) {
					ZlnfGraindepotNew zlnfGraindepotNew0 = new ZlnfGraindepotNew();
					zlnfGraindepotNew0.setFoodcode(zlnfFoodInfoRequest.getFoodcode());
					List<ZlnfGraindepotNew> graindepotNewList0 = graindepotNewService.queryFoodcode(zlnfGraindepotNew0);

					ZlnfGraindepotHisExample graindepotExample1 = new ZlnfGraindepotHisExample();
					ZlnfGraindepotHisExample.Criteria criteria1 = graindepotExample1.createCriteria();
					criteria1.andCreatedonutcEqualTo(sdf.parse(listStr.get(i)));
					criteria1.andFoodcodeEqualTo(zlnfFoodInfoRequest.getFoodcode());
					criteria1.andFoodkindcodeEqualTo(graindepotNewList0.get(0).getFoodkindcode());
					criteria1.andFoodlevelcodeEqualTo(graindepotNewList0.get(0).getFoodlevelcode());
					criteria1.andWaterEqualTo(graindepotNewList0.get(0).getWater());
					List<ZlnfGraindepotHis> graindepotList2 = graindepotHisService.getResult(graindepotExample1);
					// ZlnfGraindepotHis graindepotHis =
					// getGraindepotHis(graindepotList2);
					if (graindepotList2.size() > 0) {
						graindepotList1.add(graindepotList2.get(0));
					}
				}
				String nowDate = sdf.format(new Date());
				for (int i = 0; i < graindepotList1.size(); i++) {
					if (nowDate.equals(sdf.format(graindepotList1.get(i).getCreatedonutc()))) {
						graindepotList1.remove(i);
					}
				}
				for (int i = 0; i < graindepotList1.size(); i++) {
					ZlnfDictInfoExample dictInfoExample = new ZlnfDictInfoExample();
					ZlnfDictInfoExample.Criteria criteria = dictInfoExample.createCriteria();
					criteria.andIsdeletedEqualTo("0");
					criteria.andDictcodeEqualTo(graindepotList1.get(i).getFoodkind());
					List<ZlnfDictInfo> dictInfoList2 = dictInfoService.getResult(dictInfoExample);
					if (dictInfoList2.size() > 0) {
						graindepotList1.get(i).setFoodkind(dictInfoList2.get(0).getDictname());
					}
				}
				// 对获取到的数据根据日期进行排序
				List<ZlnfGraindepotHis> graindepotList2 = new ArrayList<>();
				graindepotList2 = createDateOrder(graindepotList1, graindepotList2);
				foodInfoList1.get(0).setZlnfGraindepotHis(graindepotList2);
				// 获取最大价格和数量
				Double fp = (double) 0;
				Double fn = (double) 0;
				for (int i = 0; i < graindepotList2.size(); i++) {
					if (graindepotList2.get(i).getPrice() != null) {
						if (graindepotList2.get(i).getPrice() > fp) {
							fp = graindepotList2.get(i).getPrice();
						}
					}
					if (graindepotList2.get(i).getGrainnum() != null) {
						if (graindepotList2.get(i).getGrainnum() > fn) {
							fn = graindepotList2.get(i).getGrainnum();
						}
					}
				}
				foodInfoList1.get(0).setMaxprice(fp);
				foodInfoList1.get(0).setMaxnum(fn);
				// 今日粮食价格
				ZlnfGraindepotNew zlnfGraindepotNew = new ZlnfGraindepotNew();
				zlnfGraindepotNew.setFoodcode(zlnfFoodInfoRequest.getFoodcode());
				List<ZlnfGraindepotNew> graindepotNewList = graindepotNewService.queryFoodcode(zlnfGraindepotNew);
				if (graindepotNewList.size() > 0) {
					foodInfoList1.get(0).setTodayPrice(graindepotNewList.get(0).getPrice());
					foodInfoList1.get(0).setTodayWater(graindepotNewList.get(0).getWater());
					foodInfoList1.get(0).setTodayLevel(graindepotNewList.get(0).getFoodlevel());
					ZlnfDictInfoExample dictInfoExample = new ZlnfDictInfoExample();
					ZlnfDictInfoExample.Criteria dictInfoCriteria = dictInfoExample.createCriteria();
					dictInfoCriteria.andIsdeletedEqualTo("0");
					dictInfoCriteria.andTypeEqualTo("2");
					dictInfoCriteria.andDictcodeEqualTo(graindepotNewList.get(0).getFoodkindcode());
					List<ZlnfDictInfo> dictInfoList = dictInfoService.getResult(dictInfoExample);
					if (dictInfoList.size() > 0) {
						foodInfoList1.get(0).setDictInfo(dictInfoList.get(0));
					}
				}
				// 今日预约数量
				ZlnfApppiontOrderTime apppiontTime = new ZlnfApppiontOrderTime();
				apppiontTime.setState("0");
				apppiontTime.setFoodcode(zlnfFoodInfoRequest.getFoodcode());
				apppiontTime.setAppionttimestr(sdf.format(new Date()));
				List<ZlnfApppiontOrderTime> apppiontTimeList1 = apppiontOrderTimeService
						.queryTodaySellNum(apppiontTime);
				Double num = (double) 0;
				if (apppiontTimeList1.size() > 0) {
					for (int i = 0; i < apppiontTimeList1.size(); i++) {
						num = num + Double.valueOf(apppiontTimeList1.get(i).getSellnum());
					}
				}
				foodInfoList1.get(0).setTodayGraindNum(num);
				m.put("queryThirty", foodInfoList1.get(0));
				// 查看7天预约
				ZlnfFoodInfoExample foodInfoExample = new ZlnfFoodInfoExample();
				ZlnfFoodInfoExample.Criteria foodInfoCriteria = foodInfoExample.createCriteria();
				foodInfoCriteria.andFoodcodeEqualTo(zlnfFoodInfoRequest.getFoodcode());
				List<ZlnfFoodInfo> foodInfoList = foodInfoService.getResult(foodInfoExample);
				ZlnfGraindepotNew zlnfGraindepotNew1 = new ZlnfGraindepotNew();
				zlnfGraindepotNew1.setFoodcode(zlnfFoodInfoRequest.getFoodcode());
				List<ZlnfGraindepotNew> graindepotNewList1 = graindepotNewService.queryFoodcode(zlnfGraindepotNew1);
				if (graindepotNewList1.size() > 0) {
					foodInfoList.get(0).setTodayPrice(graindepotNewList1.get(0).getPrice());
					foodInfoList.get(0).setTodayLevel(graindepotNewList1.get(0).getFoodlevel());
					ZlnfDictInfoExample dictInfoExample1 = new ZlnfDictInfoExample();
					ZlnfDictInfoExample.Criteria dictInfoCriteria1 = dictInfoExample1.createCriteria();
					dictInfoCriteria1.andIsdeletedEqualTo("0");
					dictInfoCriteria1.andTypeEqualTo("2");
					dictInfoCriteria1.andDictcodeEqualTo(graindepotNewList1.get(0).getFoodkind());
					List<ZlnfDictInfo> dictInfoList1 = dictInfoService.getResult(dictInfoExample1);
					if (dictInfoList1.size() > 0) {
						foodInfoList.get(0).setDictInfo(dictInfoList1.get(0));
					}
				}
				ZlnfApppiontOrderTimeExample apppiontOrderTimeExample = new ZlnfApppiontOrderTimeExample();
				ZlnfApppiontOrderTimeExample.Criteria apppiontTimeCriteria = apppiontOrderTimeExample.createCriteria();
				apppiontTimeCriteria.andStateEqualTo("0");
				apppiontTimeCriteria.andFoodcodeEqualTo(zlnfFoodInfoRequest.getFoodcode());
				Calendar now = Calendar.getInstance();
				now.setTime(new Date());
				now.set(Calendar.DATE, now.get(Calendar.DATE) + 7);
				apppiontTimeCriteria.andCreatedonutcLessThanOrEqualTo(now.getTime());
				List<ZlnfApppiontOrderTime> apppiontTimeList = apppiontOrderTimeService
						.getResult(apppiontOrderTimeExample);
				Double fn1 = (double) 0;
				List<Map<String, Object>> apppiontOrderTimeList = new ArrayList<Map<String, Object>>();
				int k = 1;
				for (int j = 1; j < 8; j++) {
					Double SellnumInt = (double) 0;
					Map<String, Object> map = new HashMap<String, Object>();
					Calendar now1 = Calendar.getInstance();
					String date2 = "";
					now1.setTime(new Date());
					now1.set(Calendar.DATE, now1.get(Calendar.DATE) + k);
					date2 = sdf.format(now1.getTime());
					for (int i = 0; i < apppiontTimeList.size(); i++) {
						String date1 = sdf.format(apppiontTimeList.get(i).getAppionttime());
						if (date1.equals(date2)) {
							SellnumInt = SellnumInt + Integer.valueOf(apppiontTimeList.get(i).getSellnum());
						}
					}
					k++;
					if (SellnumInt > fn1) {
						fn1 = SellnumInt;
					}
					/*
					 * map.put("Sellnum", SellnumInt); map.put("date", date2);
					 * apppiontOrderTimeList.add(map);
					 */
				}
				Double SellnumInt1 = (double) 0;
				Calendar now2 = Calendar.getInstance();
				String date3 = "";
				now2.setTime(new Date());
				// now1.set(Calendar.DATE, now1.get(Calendar.DATE) + k);
				date3 = sdf.format(now2.getTime());
				for (int i = 0; i < apppiontTimeList.size(); i++) {
					String date1 = sdf.format(apppiontTimeList.get(i).getAppionttime());
					Map<String, Object> map1 = new HashMap<String, Object>();
					if (date1.equals(date3)) {
						SellnumInt1 = SellnumInt1 + Integer.valueOf(apppiontTimeList.get(i).getSellnum());
						map1.put("Sellnum", SellnumInt1);
						map1.put("date", date3);
						apppiontOrderTimeList.add(map1);
						break;
					}
				}

				foodInfoList.get(0).setApppiontOrderTimeList(apppiontOrderTimeList);
				foodInfoList.get(0).setMaxnum(fn1);
				m.put("queryWeek", foodInfoList.get(0));
				result.setSuccess(true);
				result.setData(m);
				result.setReturnCode("0000");
				result.setMessage("获取历史信息成功");
			} else {
				result.setSuccess(false);
				result.setReturnCode("0003");
				result.setMessage("暂无此粮库记录");
			}
		} catch (Exception e) {
			// TODO: handle exception
			result.setSuccess(false);
			result.setReturnCode("1111");
			result.setMessage("获取历史信息失败");
		}
		return result;
	}

	/**
	 * 获取粮库30天，7天记录
	 * 
	 * @param channel
	 * @param key
	 * @param data
	 * @return
	 */
	@RequestMapping(value = { "/api/foodInfo/queryDateNew" }, method = { RequestMethod.POST })
	@ResponseBody
	public OptResult foodInfoQueryThirtyNew(String channel, String key, String data) {
		// 0. 检查
		channelKeyCheck(channel, key);
		if (StringUtils.isEmpty(data)) {
			reject("user.activate.data.empty");
		}
		// 1. 转换数据
		ZlnfFoodInfoRequest zlnfFoodInfoRequest = convert(data, ZlnfFoodInfoRequest.class);
		// 2. 业务检查
		if (zlnfFoodInfoRequest == null) {
			reject("user.activate.model.convert.error");
		}
		OptResult result = OptResult.success();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Map<String, Object> m = new HashMap<>();
			// 查询30历史记录
			ZlnfFoodInfoExample foodInfoExample1 = new ZlnfFoodInfoExample();
			ZlnfFoodInfoExample.Criteria foodInfoCriteria1 = foodInfoExample1.createCriteria();
			foodInfoCriteria1.andFoodcodeEqualTo(zlnfFoodInfoRequest.getFoodcode());
			List<ZlnfFoodInfo> foodInfoList1 = foodInfoService.getResult(foodInfoExample1);
			List<ZlnfGraindepotHis> graindepotList1 = new ArrayList<>();
			ZlnfGraindepotHis record = new ZlnfGraindepotHis();
			record.setFoodcode(zlnfFoodInfoRequest.getFoodcode());
			List<ZlnfGraindepotHis> graindepotList = graindepotHisService.queryCreateDateNew(record);
			for (int i = 0; i < graindepotList.size(); i++) {
				graindepotList1.add(graindepotList.get(i));
			}
			/*
			 * for(int i = 0;i<listStr.size();i++){ ZlnfGraindepotHisExample
			 * graindepotExample1 = new ZlnfGraindepotHisExample();
			 * ZlnfGraindepotHisExample.Criteria criteria1 =
			 * graindepotExample1.createCriteria();
			 * criteria1.andCreatedonutcEqualTo(sdf.parse(listStr.get(i)));
			 * criteria1.andFoodcodeEqualTo(zlnfFoodInfoRequest.getFoodcode());
			 * List<ZlnfGraindepotHis> graindepotList2 =
			 * graindepotHisService.getResult(graindepotExample1); //
			 * ZlnfGraindepotHis graindepotHis =
			 * getGraindepotHis(graindepotList2);
			 * graindepotList1.add(graindepotList2.get(0)); }
			 */
			String nowDate = sdf.format(new Date());
			for (int i = 0; i < graindepotList1.size(); i++) {
				if (nowDate.equals(sdf.format(graindepotList1.get(i).getCreatedonutc()))) {
					graindepotList1.remove(i);
				}
			}
			for (int i = 0; i < graindepotList1.size(); i++) {
				ZlnfDictInfoExample dictInfoExample = new ZlnfDictInfoExample();
				ZlnfDictInfoExample.Criteria criteria = dictInfoExample.createCriteria();
				criteria.andIsdeletedEqualTo("0");
				criteria.andDictcodeEqualTo(graindepotList1.get(i).getFoodkind());
				List<ZlnfDictInfo> dictInfoList2 = dictInfoService.getResult(dictInfoExample);
				if (dictInfoList2.size() > 0) {
					graindepotList1.get(i).setFoodkind(dictInfoList2.get(0).getDictname());
				}
			}
			// 对获取到的数据根据日期进行排序
			List<ZlnfGraindepotHis> graindepotList2 = new ArrayList<>();
			graindepotList2 = createDateOrder(graindepotList1, graindepotList2);
			foodInfoList1.get(0).setZlnfGraindepotHis(graindepotList2);
			// 获取最大价格和数量
			Double fp = (double) 0;
			Double fn = (double) 0;
			for (int i = 0; i < graindepotList2.size(); i++) {
				if (graindepotList2.get(i).getPrice() != null) {
					if (graindepotList2.get(i).getPrice() > fp) {
						fp = graindepotList2.get(i).getPrice();
					}
				}
				if (graindepotList2.get(i).getGrainnum() != null) {
					if (graindepotList2.get(i).getGrainnum() > fn) {
						fn = graindepotList2.get(i).getGrainnum();
					}
				}
			}
			foodInfoList1.get(0).setMaxprice(fp);
			foodInfoList1.get(0).setMaxnum(fn);
			// 今日粮食价格
			ZlnfGraindepotNew zlnfGraindepotNew = new ZlnfGraindepotNew();
			zlnfGraindepotNew.setFoodcode(zlnfFoodInfoRequest.getFoodcode());
			List<ZlnfGraindepotNew> graindepotNewList = graindepotNewService.queryFoodcode(zlnfGraindepotNew);
			if (graindepotNewList.size() > 0) {
				foodInfoList1.get(0).setTodayPrice(graindepotNewList.get(0).getPrice());
				foodInfoList1.get(0).setTodayWater(graindepotNewList.get(0).getWater());
				foodInfoList1.get(0).setTodayLevel(graindepotNewList.get(0).getFoodlevel());
				ZlnfDictInfoExample dictInfoExample = new ZlnfDictInfoExample();
				ZlnfDictInfoExample.Criteria dictInfoCriteria = dictInfoExample.createCriteria();
				dictInfoCriteria.andIsdeletedEqualTo("0");
				dictInfoCriteria.andTypeEqualTo("2");
				dictInfoCriteria.andDictcodeEqualTo(graindepotNewList.get(0).getFoodkindcode());
				List<ZlnfDictInfo> dictInfoList = dictInfoService.getResult(dictInfoExample);
				if (dictInfoList.size() > 0) {
					foodInfoList1.get(0).setDictInfo(dictInfoList.get(0));
				}
			}

			// 今日预约数量
			ZlnfApppiontOrderTimeExample apppiontOrderTimeExample1 = new ZlnfApppiontOrderTimeExample();
			ZlnfApppiontOrderTimeExample.Criteria apppiontTimeCriteria1 = apppiontOrderTimeExample1.createCriteria();
			apppiontTimeCriteria1.andStateEqualTo("0");
			apppiontTimeCriteria1.andFoodcodeEqualTo(zlnfFoodInfoRequest.getFoodcode());
			apppiontTimeCriteria1.andCreatedonutcLessThanOrEqualTo(new Date());
			List<ZlnfApppiontOrderTime> apppiontTimeList1 = apppiontOrderTimeService
					.getResult(apppiontOrderTimeExample1);
			Double num = (double) 0;
			for (int i = 0; i < apppiontTimeList1.size(); i++) {
				num = num + Double.valueOf(apppiontTimeList1.get(i).getSellnum());
			}
			foodInfoList1.get(0).setTodayGraindNum(num);
			m.put("queryThirty", foodInfoList1.get(0));
			// 查看7天预约
			ZlnfFoodInfoExample foodInfoExample = new ZlnfFoodInfoExample();
			ZlnfFoodInfoExample.Criteria foodInfoCriteria = foodInfoExample.createCriteria();
			foodInfoCriteria.andFoodcodeEqualTo(zlnfFoodInfoRequest.getFoodcode());
			List<ZlnfFoodInfo> foodInfoList = foodInfoService.getResult(foodInfoExample);
			ZlnfGraindepotNew zlnfGraindepotNew1 = new ZlnfGraindepotNew();
			zlnfGraindepotNew1.setFoodcode(zlnfFoodInfoRequest.getFoodcode());
			List<ZlnfGraindepotNew> graindepotNewList1 = graindepotNewService.queryFoodcode(zlnfGraindepotNew1);
			if (graindepotNewList1.size() > 0) {
				foodInfoList.get(0).setTodayPrice(graindepotNewList1.get(0).getPrice());
				foodInfoList.get(0).setTodayLevel(graindepotNewList1.get(0).getFoodlevel());
				ZlnfDictInfoExample dictInfoExample1 = new ZlnfDictInfoExample();
				ZlnfDictInfoExample.Criteria dictInfoCriteria1 = dictInfoExample1.createCriteria();
				dictInfoCriteria1.andIsdeletedEqualTo("0");
				dictInfoCriteria1.andTypeEqualTo("2");
				dictInfoCriteria1.andDictcodeEqualTo(graindepotNewList1.get(0).getFoodkind());
				List<ZlnfDictInfo> dictInfoList1 = dictInfoService.getResult(dictInfoExample1);
				if (dictInfoList1.size() > 0) {
					foodInfoList.get(0).setDictInfo(dictInfoList1.get(0));
				}
			}
			ZlnfApppiontOrderTimeExample apppiontOrderTimeExample = new ZlnfApppiontOrderTimeExample();
			ZlnfApppiontOrderTimeExample.Criteria apppiontTimeCriteria = apppiontOrderTimeExample.createCriteria();
			apppiontTimeCriteria.andStateEqualTo("0");
			apppiontTimeCriteria.andFoodcodeEqualTo(zlnfFoodInfoRequest.getFoodcode());
			Calendar now = Calendar.getInstance();
			now.setTime(new Date());
			now.set(Calendar.DATE, now.get(Calendar.DATE) + 7);
			apppiontTimeCriteria.andCreatedonutcLessThanOrEqualTo(now.getTime());
			List<ZlnfApppiontOrderTime> apppiontTimeList = apppiontOrderTimeService.getResult(apppiontOrderTimeExample);
			Double fn1 = (double) 0;
			List<Map<String, Object>> apppiontOrderTimeList = new ArrayList<Map<String, Object>>();
			int k = 1;

			// now1.set(Calendar.DATE, now1.get(Calendar.DATE) + k);
			Double SellnumInt1 = (double) 0;
			Calendar now2 = Calendar.getInstance();
			String date3 = "";
			now2.setTime(new Date());
			// now1.set(Calendar.DATE, now1.get(Calendar.DATE) + k);
			date3 = sdf.format(now2.getTime());
			for (int i = 0; i < apppiontTimeList.size(); i++) {
				String date1 = sdf.format(apppiontTimeList.get(i).getAppionttime());
				if (date1.equals(date3)) {
					SellnumInt1 = SellnumInt1 + Integer.valueOf(apppiontTimeList.get(i).getSellnum());
					m.put("todaySellnum", SellnumInt1);
					m.put("today", date3);
					break;
				}
			}
			for (int j = 1; j < 8; j++) {
				Double SellnumInt = (double) 0;
				Map<String, Object> map = new HashMap<String, Object>();
				Calendar now1 = Calendar.getInstance();
				String date2 = "";
				now1.setTime(new Date());
				// now1.set(Calendar.DATE, now1.get(Calendar.DATE) + k);
				date2 = sdf.format(now1.getTime());
				for (int i = 0; i < apppiontTimeList.size(); i++) {
					String date1 = sdf.format(apppiontTimeList.get(i).getAppionttime());
					if (date1.equals(date2)) {
						SellnumInt = SellnumInt + Integer.valueOf(apppiontTimeList.get(i).getSellnum());
					}
				}
				k++;
				if (SellnumInt > fn1) {
					fn1 = SellnumInt;
				}
				map.put("Sellnum", SellnumInt);
				map.put("date", date2);
				apppiontOrderTimeList.add(map);
			}
			foodInfoList.get(0).setApppiontOrderTimeList(apppiontOrderTimeList);
			foodInfoList.get(0).setMaxnum(fn1);
			m.put("queryWeek", foodInfoList.get(0));
			result.setSuccess(true);
			result.setData(m);
			result.setReturnCode("0000");
			result.setMessage("获取历史信息成功");
		} catch (Exception e) {
			// TODO: handle exception
			result.setSuccess(false);
			result.setReturnCode("1111");
			result.setMessage("获取历史信息失败");
		}
		return result;
	}

	public List<ZlnfGraindepotHis> createDateOrder(List<ZlnfGraindepotHis> graindepotList1,
			List<ZlnfGraindepotHis> graindepotList2) {
		int a = 0;
		Date d1 = null;
		;
		for (int i = 0; i < graindepotList1.size(); i++) {
			if (i == 0) {
				a = 0;
				d1 = graindepotList1.get(0).getCreatedonutc();
			} else {
				Date d2 = graindepotList1.get(i).getCreatedonutc();
				if (d1.getTime() < d2.getTime()) {
					d1 = d2;
					a = i;
				}
			}
		}
		graindepotList2.add(graindepotList1.get(a));
		graindepotList1.remove(a);
		if (graindepotList1.size() > 0) {
			return createDateOrder(graindepotList1, graindepotList2);
		} else {
			return graindepotList2;
		}
	}

	public ZlnfGraindepotHis getGraindepotHis(List<ZlnfGraindepotHis> graindepotList) {
		Map<String, Object> map = new HashMap<>();
		for (int i = 0; i < graindepotList.size(); i++) {
			Boolean bo = false;
			Double todayPrice = (double) 0;
			Double water = (double) 0;
			String[] level = { "一等", "二等", "三等", "四等", "五等" };
			if ("1".equals(graindepotList.get(i).getDatatype())) {
				for (int j = 0; j < level.length; j++) {
					if (level[j].equals(graindepotList.get(i).getFoodlevel())) {
						todayPrice = graindepotList.get(i).getPrice();
					}
					if (todayPrice != (double) 0 && todayPrice != null) {
						water = graindepotList.get(i).getWater();
						map.put("todayPrice", todayPrice);
						map.put("water", water);
						map.put("level", level[j]);
						bo = true;
						break;
					}
				}
				if (bo) {
					break;
				}
			}
		}
		for (int i = 0; i < graindepotList.size(); i++) {
			Boolean bo = false;
			Double graindnum = (double) 0;
			if ("2".equals(graindepotList.get(i).getDatatype())) {
				graindnum = graindepotList.get(i).getGrainnum();
				bo = true;
			}
			map.put("grainnum", graindnum);
			if (bo) {
				break;
			}
		}
		ZlnfGraindepotHis graindepotHis = graindepotList.get(0);
		graindepotHis.setPrice(Double.valueOf(String.valueOf(map.get("todayPrice"))));
		graindepotHis.setGrainnum(Double.valueOf(String.valueOf(map.get("grainnum"))));
		graindepotHis.setWater(Double.valueOf(String.valueOf(map.get("water"))));
		graindepotHis.setFoodlevel(String.valueOf(map.get("level")));
		return graindepotHis;
	}

	/**
	 * 获取粮库是否营业
	 * 
	 * @param channel
	 * @param key
	 * @param data
	 * @return
	 */
	@RequestMapping(value = { "/api/foodInfo/queryYorn" }, method = { RequestMethod.POST })
	@ResponseBody
	public OptResult queryYorn(String channel, String key, String data) {
		// 0. 检查
		channelKeyCheck(channel, key);
		if (StringUtils.isEmpty(data)) {
			reject("user.activate.data.empty");
		}
		// 1. 转换数据
		ZlnfFoodInfoRequest zlnfFoodInfoRequest = convert(data, ZlnfFoodInfoRequest.class);
		// 2. 业务检查
		if (zlnfFoodInfoRequest == null) {
			reject("user.activate.model.convert.error");
		}
		OptResult result = OptResult.success();
		try {
			ZlnfFoodInfoExample foodInfoExample = new ZlnfFoodInfoExample();
			ZlnfFoodInfoExample.Criteria foodInfoCriteria = foodInfoExample.createCriteria();
			foodInfoCriteria.andFoodcodeEqualTo(zlnfFoodInfoRequest.getFoodcode());
			foodInfoCriteria.andIsdeletedEqualTo("0");
			List<ZlnfFoodInfo> foodInfoList = foodInfoService.getResult(foodInfoExample);
			if (foodInfoList.size() > 0) {
				result.setSuccess(true);
				result.setData(foodInfoList.get(0).getYorn());
				result.setReturnCode("0000");
				result.setMessage("获取粮库营业信息成功");
			} else {
				result.setSuccess(false);
				result.setReturnCode("0001");
				result.setMessage("没有此粮库");
			}
		} catch (Exception e) {
			// TODO: handle exception
			result.setSuccess(false);
			result.setReturnCode("1111");
			result.setMessage("获取粮库营业信息成功");
		}
		return result;
	}

	/**
	 * 获取粮库详情
	 * 
	 * @param channel
	 * @param key
	 * @param data
	 * @return
	 */
	@RequestMapping(value = { "/api/foodInfo/queryByCode" }, method = { RequestMethod.POST })
	@ResponseBody
	public OptResult foodInfoQueryByCode(String channel, String key, String data) {
		// 0. 检查
		channelKeyCheck(channel, key);
		if (StringUtils.isEmpty(data)) {
			reject("user.activate.data.empty");
		}
		// 1. 转换数据
		ZlnfFoodInfoRequest zlnfFoodInfoRequest = convert(data, ZlnfFoodInfoRequest.class);
		// 2. 业务检查
		if (zlnfFoodInfoRequest == null) {
			reject("user.activate.model.convert.error");
		}
		OptResult result = OptResult.success();
		try {
			ZlnfFoodInfoExample foodInfoExample = new ZlnfFoodInfoExample();
			ZlnfFoodInfoExample.Criteria foodInfoCriteria = foodInfoExample.createCriteria();
			foodInfoCriteria.andFoodcodeEqualTo(zlnfFoodInfoRequest.getFoodcode());
			List<ZlnfFoodInfo> foodInfoList = foodInfoService.getResult(foodInfoExample);
			// 今日粮库收粮记录
			ZlnfGraindepotNew zlnfGraindepotNew = new ZlnfGraindepotNew();
			zlnfGraindepotNew.setFoodcode(zlnfFoodInfoRequest.getFoodcode());
			List<ZlnfGraindepotNew> graindepotNewList = graindepotNewService.queryFoodcode(zlnfGraindepotNew);
			foodInfoList.get(0).setGraindepotNewList(graindepotNewList);
			// 明日粮库收粮记录
			if (graindepotNewList.size() > 0) {
				ZlnfDictInfoExample dictInfoExample = new ZlnfDictInfoExample();
				ZlnfDictInfoExample.Criteria criteria1 = dictInfoExample.createCriteria();
				criteria1.andIsdeletedEqualTo("0");
				criteria1.andDictcodeEqualTo(graindepotNewList.get(0).getFoodkind());
				List<ZlnfDictInfo> dictInfoList2 = dictInfoService.getResult(dictInfoExample);
				if (dictInfoList2.size() > 0) {
					graindepotNewList.get(0).setFoodkind(dictInfoList2.get(0).getDictname());
				}
				foodInfoList.get(0).setTomorrowFoodlevel(graindepotNewList.get(0).getFoodlevel());
				foodInfoList.get(0).setTomorrowFoodKind(graindepotNewList.get(0).getFoodkind());
				foodInfoList.get(0).setTomorrowPrice(graindepotNewList.get(0).getPrice());
				foodInfoList.get(0).setTomorrowWater(graindepotNewList.get(0).getWater());
				foodInfoList.get(0).setTodayPrice(graindepotNewList.get(0).getPrice());
				foodInfoList.get(0).setTodayLevel(graindepotNewList.get(0).getFoodlevel());
				foodInfoList.get(0).setTodayFoodkind(graindepotNewList.get(0).getFoodkind());
				foodInfoList.get(0).setTodayWater(graindepotNewList.get(0).getWater());
			} else {
				foodInfoList.get(0).setTomorrowFoodlevel("");
				foodInfoList.get(0).setTomorrowFoodKind("");
				foodInfoList.get(0).setTomorrowPrice(Double.valueOf("0"));
				foodInfoList.get(0).setTomorrowWater(Double.valueOf("0"));
				foodInfoList.get(0).setTodayPrice(Double.valueOf("0"));
				foodInfoList.get(0).setTodayLevel("");
				foodInfoList.get(0).setTodayFoodkind("");
				foodInfoList.get(0).setTodayWater(Double.valueOf("0"));
			}
			// 昨日收粮价格
			// ZlnfGraindepotHisExample graindepotExample1 = new
			// ZlnfGraindepotHisExample();
			Calendar now1 = Calendar.getInstance();
			now1.setTime(new Date());
			now1.set(Calendar.DATE, now1.get(Calendar.DATE) - 1);
			ZlnfGraindepotHisExample graindepotExample2 = new ZlnfGraindepotHisExample();
			ZlnfGraindepotHisExample.Criteria criteria2 = graindepotExample2.createCriteria();
			criteria2.andFoodcodeEqualTo(zlnfFoodInfoRequest.getFoodcode());
			criteria2.andCreatedonutcEqualTo(now1.getTime());
			// 与今日同品种等级水分
			criteria2.andFoodlevelcodeEqualTo(graindepotNewList.get(0).getFoodlevelcode());
			criteria2.andFoodkindcodeEqualTo(graindepotNewList.get(0).getFoodkindcode());
			criteria2.andWaterEqualTo(graindepotNewList.get(0).getWater());
			List<ZlnfGraindepotHis> graindepotList2 = graindepotHisService.getResult(graindepotExample2);
			if (graindepotList2.size() > 0) {
				foodInfoList.get(0).setYesterPrice(graindepotList2.get(0).getPrice());
				foodInfoList.get(0).setYesterFoodlevel(graindepotList2.get(0).getFoodlevel());
				foodInfoList.get(0).setYesterFoodKind(graindepotList2.get(0).getFoodkind());
				foodInfoList.get(0).setYesterWater(graindepotList2.get(0).getWater());
			} else if (graindepotNewList.size() > 0) {
				// foodInfoList.get(0).setYesterPrice("");
				foodInfoList.get(0).setYesterFoodlevel(graindepotNewList.get(0).getFoodlevel());
				foodInfoList.get(0).setYesterFoodKind(graindepotNewList.get(0).getFoodkind());
				foodInfoList.get(0).setYesterWater(graindepotNewList.get(0).getWater());
			}
			// 卖粮记录
			ZlnfApppiontOrderTimeExample apppiontOrderTimeExample = new ZlnfApppiontOrderTimeExample();
			ZlnfApppiontOrderTimeExample.Criteria apppiontTimeCriteria = apppiontOrderTimeExample.createCriteria();
			apppiontTimeCriteria.andStateEqualTo("1");
			apppiontTimeCriteria.andFoodcodeEqualTo(zlnfFoodInfoRequest.getFoodcode());
			List<ZlnfApppiontOrderTime> apppiontTimeList = apppiontOrderTimeService.getResult(apppiontOrderTimeExample);
			List<ZlnfApppiontOrderTime> apppiontTimeList1 = new ArrayList<>();
			if (apppiontTimeList.size() > 4) {
				for (int i = 0; i < 4; i++) {
					apppiontTimeList1.add(apppiontTimeList.get(i));
				}
				foodInfoList.get(0).setApppiontTimeList(apppiontTimeList1);
			} else {
				foodInfoList.get(0).setApppiontTimeList(apppiontTimeList);
			}
			// 粮库服务
			ZlnfFoodServiceInfoExample foodServiceInfoExample = new ZlnfFoodServiceInfoExample();
			ZlnfFoodServiceInfoExample.Criteria foodServiceCriteria = foodServiceInfoExample.createCriteria();
			foodServiceCriteria.andIsdeletedEqualTo("0");
			foodServiceCriteria.andFoodcodeEqualTo(zlnfFoodInfoRequest.getFoodcode());
			List<ZlnfFoodServiceInfo> foodServiceList = foodServiceInfoService.getResult(foodServiceInfoExample);
			if (foodServiceList.size() > 0) {
				foodInfoList.get(0).setFoodServiceList(foodServiceList);
			}
			result.setSuccess(true);
			result.setData(foodInfoList.get(0));
			result.setReturnCode("0000");
			result.setMessage("获取详情信息成功");
		} catch (Exception e) {
			// TODO: handle exception
			result.setSuccess(false);
			result.setReturnCode("1111");
			result.setMessage("获取详情信息失败");
		}
		return result;
	}

	/**
	 * 返回8个最近粮库信息
	 * 
	 * @param channel
	 * @param key
	 * @param data
	 * @return
	 */
	@RequestMapping(value = { "/api/foodInfo/queryEight" }, method = { RequestMethod.POST })
	@ResponseBody
	public OptResult queryEightfood(String channel, String key, String data) {
		// 0. 检查
		channelKeyCheck(channel, key);
		if (StringUtils.isEmpty(data)) {
			reject("user.activate.data.empty");
		}
		// 1. 转换数据
		ZlnfFoodInfoRequest zlnfFoodInfoRequest = convert(data, ZlnfFoodInfoRequest.class);
		// 2. 业务检查
		if (zlnfFoodInfoRequest == null) {
			reject("user.activate.model.convert.error");
		}
		OptResult result = OptResult.success();
		try {
			ZlnfFoodInfo record = new ZlnfFoodInfo();
			record.setUsercode(zlnfFoodInfoRequest.getUsercode());
			record.setType(zlnfFoodInfoRequest.getType());
			List<ZlnfFoodInfo> foodInfoList = new ArrayList<>();
			// 判断是否是选择点击粮库进入
			if ("1".equals(zlnfFoodInfoRequest.getType())) {
				record.setFoodcode(zlnfFoodInfoRequest.getFoodcode());
				foodInfoList = foodInfoService.queryEightfood(record);
			} else if ("2".equals(zlnfFoodInfoRequest.getType())) {
				foodInfoList = foodInfoService.queryEightfood1(record);
			}
			for (int i = 0; i < foodInfoList.size(); i++) {
				ZlnfGraindepotNew zlnfGraindepotNew = new ZlnfGraindepotNew();
				zlnfGraindepotNew.setFoodcode(foodInfoList.get(i).getFoodcode());
				String foodkind = zlnfFoodInfoRequest.getFoodkind();
				String foodlevel = zlnfFoodInfoRequest.getFoodlevel();
				Double water = zlnfFoodInfoRequest.getWater();
				// 是否有筛选条件
				if (!"".equals(foodkind) && foodkind != null && !"".equals(foodlevel) && foodlevel != null && water != 0
						&& water != null) {
					zlnfGraindepotNew.setFoodkind(foodkind);
					zlnfGraindepotNew.setWater(water);
					zlnfGraindepotNew.setFoodlevel(foodlevel);
					// 查看是否有条件价格，如果没有显示默认价格
					List<ZlnfGraindepotNew> graindepotNewList1 = graindepotNewService.queryDefault(zlnfGraindepotNew);
					List<ZlnfGraindepotNew> graindepotNewList2 = graindepotNewService
							.queryByParameter(zlnfGraindepotNew);
					if (graindepotNewList2.size() > 0) {
						if (graindepotNewList2.get(0).getPrice() != null) {
							foodInfoList.get(i).setTodayPrice(graindepotNewList2.get(0).getPrice());
						}
						if (graindepotNewList2.get(0).getFoodkind() != null) {
							foodInfoList.get(i).setTodayFoodkind(graindepotNewList2.get(0).getFoodkind());
						}
						if (graindepotNewList2.get(0).getFoodlevel() != null) {
							foodInfoList.get(i).setTodayLevel(graindepotNewList2.get(0).getFoodlevel());
						}
						if (graindepotNewList2.get(0).getWater() != null) {
							foodInfoList.get(i).setTodayWater(graindepotNewList2.get(0).getWater());
						}
					} else {
						if (graindepotNewList1.size() > 0) {
							if (graindepotNewList1.get(0).getPrice() != null) {
								foodInfoList.get(i).setTodayPrice(graindepotNewList1.get(0).getPrice());
							}
							if (graindepotNewList1.get(0).getFoodkind() != null) {
								foodInfoList.get(i).setTodayFoodkind(graindepotNewList1.get(0).getFoodkind());
							}
							if (graindepotNewList1.get(0).getFoodlevel() != null) {
								foodInfoList.get(i).setTodayLevel(graindepotNewList1.get(0).getFoodlevel());
							}
							if (graindepotNewList1.get(0).getWater() != null) {
								foodInfoList.get(i).setTodayWater(graindepotNewList1.get(0).getWater());
							}
						}
					}
				} else {
					List<ZlnfGraindepotNew> graindepotNewList1 = graindepotNewService.queryDefault(zlnfGraindepotNew);
					if (graindepotNewList1.size() > 0) {
						if (graindepotNewList1.get(0).getPrice() != null) {
							foodInfoList.get(i).setTodayPrice(graindepotNewList1.get(0).getPrice());
						}
						if (graindepotNewList1.get(0).getFoodkind() != null) {
							foodInfoList.get(i).setTodayFoodkind(graindepotNewList1.get(0).getFoodkind());
						}
						if (graindepotNewList1.get(0).getFoodlevel() != null) {
							foodInfoList.get(i).setTodayLevel(graindepotNewList1.get(0).getFoodlevel());
						}
						if (graindepotNewList1.get(0).getWater() != null) {
							foodInfoList.get(i).setTodayWater(graindepotNewList1.get(0).getWater());
						}
					}
				}
			}
			result.setSuccess(true);
			result.setData(foodInfoList);
			result.setReturnCode("0000");
			result.setMessage("获取粮库信息成功");
		} catch (Exception e) {
			// TODO: handle exception
			result.setSuccess(false);
			result.setReturnCode("1111");
			result.setMessage("获取粮库信息失败");
		}
		return result;
	}
}
