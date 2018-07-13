package com.yeepay;

import java.util.Map;
import java.util.TreeMap;
import java.util.HashMap;
import java.net.URLEncoder;
import java.io.InputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.yeepay.AES;
import com.yeepay.RSA;
import com.yeepay.EncryUtil;
import com.yeepay.RandomUtil;
import com.yeepay.Configuration;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.HttpClient;

/**
 * 一键支付异步-鉴权绑卡-易宝短验 接口范例
 * 
 * @author ：bing.xiao
 * @since ：2016-08-11
 */

public class InstantPayService {

	/**
	 * 取得商户编号
	 */
	public static String getMerchantAccount() {
		return Configuration.getInstance().getValue("merchantAccount");
	}

	/**
	 * 取得商户私钥
	 */
	public static String getMerchantPrivateKey() {
		return Configuration.getInstance().getValue("merchantPrivateKey");
	}

	/**
	 * 取得商户AESKey
	 */
	public static String getMerchantAESKey() {
		return (RandomUtil.getRandom(16));
	}

	/**
	 * 取得易宝公玥
	 */
	public static String getYeepayPublicKey() {
		return Configuration.getInstance().getValue("yeepayPublicKey");
	}

	/**
	 * 格式化字符串
	 */
	public static String formatString(String text) {
		return (text == null ? "" : text.trim());
	}

	/**
	 * String2Integer
	 */
	public static int String2Int(String text) throws NumberFormatException {
		return text == null ? 0 : Integer.valueOf(text);
	}

	/**
	 * 4.1 鉴权绑卡请求地址
	 */
	public static String getBindCardRequestURL() {
		return Configuration.getInstance().getValue("bindCardRequestURL");
	}
	
	/**
	 * 4.2 绑定银行卡短验发送请求地址
	 */
	public static String getBindCardSendSmsURL() {
		return Configuration.getInstance().getValue("bindCardSendSmsURL");
	}
	
	/**
	 * 4.3 校验绑定银行卡短验请求地址
	 */
	public static String getBindCardCheckSmsURL() {
		return Configuration.getInstance().getValue("bindCardCheckSmsURL");
	}

	/**
	 * 4.4 查询绑卡信息列表请求地址
	 */
	public static String getBankcardBindQueryURL() {
		return Configuration.getInstance().getValue("bankcardBindQueryURL");
	}

	/**
	 * 4.5 绑卡支付请求请求地址
	 */
	public static String getBindidRequestURL() {
		return Configuration.getInstance().getValue("bindidRequestURL");
	}

	/**
	 * 4.6 发送短信验证码请求地址
	 */
	public static String getSendMessageURL() {
		return Configuration.getInstance().getValue("sendMessageURL");
	}

	/**
	 * 4.7 确认支付请求地址
	 */
	public static String getPaymentConfirmURL() {
		return Configuration.getInstance().getValue("paymentConfirmURL");
	}

	/**
	 * 4.8 支付结果查询请求地址
	 */
	public static String getPayapiQueryURL() {
		return Configuration.getInstance().getValue("payapiQueryURL");
	}

	/**
	 * 4.10 银行卡解绑请求地址
	 */
	public static String getUnbindBankcardURL() {
		return Configuration.getInstance().getValue("unbindBankcardURL");
	}

	/**
	 * 4.11 查询银行卡信息请求地址
	 */
	public static String getBankCardCheckURL() {
		return Configuration.getInstance().getValue("bankCardCheckURL");
	}

	/**
	 * 5.1 单笔退款请求地址
	 */
	public static String getRefundURL() {
		return Configuration.getInstance().getValue("refundURL");
	}

	/**
	 * 5.2 订单单笔查询请求地址
	 */
	public static String getSingleQueryURL() {
		return Configuration.getInstance().getValue("singleQueryURL");
	}

	/**
	 * 5.3 单笔退款查询请求地址
	 */
	public static String getRefundQueryURL() {
		return Configuration.getInstance().getValue("refundQueryURL");
	}

	/**
	 * 5.4 消费对账文件下载请求地址
	 */
	public static String getPayClearDataURL() {
		return Configuration.getInstance().getValue("payClearDataURL");
	}

	/**
	 * 5.5 退款对账文件下载请求地址
	 */
	public static String getRefundClearDataURL() {
		return Configuration.getInstance().getValue("refundClearDataURL");
	}

	/**
	 * 解析http请求返回
	 */
	public static Map<String, String> parseHttpResponseBody(int statusCode,
			String responseBody) throws Exception {

		String merchantPrivateKey = getMerchantPrivateKey();
		String yeepayPublicKey = getYeepayPublicKey();

		Map<String, String> result = new HashMap<String, String>();
		String customError = "";

		if (statusCode != 200) {
			customError = "Request failed, response code : " + statusCode;
			result.put("customError", customError);
			return (result);
		}

		Map<String, String> jsonMap = JSON.parseObject(responseBody,
				new TypeReference<TreeMap<String, String>>() {
				});

		if (jsonMap.containsKey("error_code")) {
			result = jsonMap;
			return (result);
		}

		String dataFromYeepay = formatString(jsonMap.get("data"));
		String encryptkeyFromYeepay = formatString(jsonMap.get("encryptkey"));

		boolean signMatch = EncryUtil.checkDecryptAndSign(dataFromYeepay,
				encryptkeyFromYeepay, yeepayPublicKey, merchantPrivateKey);
		if (!signMatch) {
			customError = "Sign not match error";
			result.put("customError", customError);
			return (result);
		}

		String yeepayAESKey = RSA.decrypt(encryptkeyFromYeepay,
				merchantPrivateKey);
		String decryptData = AES
				.decryptFromBase64(dataFromYeepay, yeepayAESKey);

		result = JSON.parseObject(decryptData,
				new TypeReference<TreeMap<String, String>>() {
				});

		return (result);
	}

	/**
	 * bindCardRequest() : 4.1 鉴权绑卡请求
	 */

	public static Map<String, String> bindCardRequest(Map<String, String> params) {

		System.out.println("##### bindCardRequest() #####");

		Map<String, String> result = new HashMap<String, String>();
		String customError = ""; // 自定义，非接口返回

		String merchantaccount = getMerchantAccount();
		String merchantPrivateKey = getMerchantPrivateKey();
		String merchantAESKey = getMerchantAESKey();
		String yeepayPublicKey = getYeepayPublicKey();
		String bindCardRequestURL = getBindCardRequestURL();
		
		String requestid = formatString(params.get("requestid"));
		String cardno = formatString(params.get("cardno"));
		String phone = formatString(params.get("phone"));
		String username = formatString(params.get("username"));
		String idcardtype = formatString(params.get("idcardtype"));
		String idcardno = formatString(params.get("idcardno"));
		String validthru = formatString(params.get("validthru"));
		String cvv2 = formatString(params.get("cvv2"));
		String identityid = formatString(params.get("identityid"));
		String userip = formatString(params.get("userip"));
		String terminalid = formatString(params.get("terminalid"));
		String bankbranch = formatString(params.get("bankbranch"));
		String province = formatString(params.get("province"));
		String city = formatString(params.get("city"));

		int terminaltype = 0;
		int identitytype = 0;

		try {
			if (params.get("terminaltype") == null) {
				throw new Exception("terminaltype is null!!!!!");
			} else {
				terminaltype = String2Int(params.get("terminaltype"));
			}

			if (params.get("identitytype") == null) {
				throw new Exception("identitytype is null!!!!!");
			} else {
				identitytype = String2Int(params.get("identitytype"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			customError = "Caught an Exception. " + e.toString();
			result.put("customError", customError);
			return (result);
		}

		TreeMap<String, Object> dataMap = new TreeMap<String, Object>();
		dataMap.put("merchantaccount", merchantaccount);
		dataMap.put("requestid", requestid);
		dataMap.put("cardno", cardno);
		dataMap.put("phone", phone);
		dataMap.put("username", username);
		dataMap.put("idcardtype", idcardtype);
		dataMap.put("idcardno", idcardno);
		dataMap.put("validthru", validthru);
		dataMap.put("cvv2", cvv2);
		dataMap.put("identitytype", identitytype);
		dataMap.put("identityid", identityid);
		dataMap.put("userip", userip);
		dataMap.put("terminaltype", terminaltype);
		dataMap.put("terminalid", terminalid);
		dataMap.put("bankbranch", bankbranch);
		dataMap.put("province", province);
		dataMap.put("city", city);

		String sign = EncryUtil.handleRSA(dataMap, merchantPrivateKey);
		dataMap.put("sign", sign);

		System.out.println("bindCardRequestURL : " + bindCardRequestURL);
		System.out.println("dataMap : " + dataMap);

		HttpClient httpClient = new HttpClient();
		PostMethod postMethod = new PostMethod(bindCardRequestURL);

		try {
			String jsonStr = JSON.toJSONString(dataMap);
			String data = AES.encryptToBase64(jsonStr, merchantAESKey);
			String encryptkey = RSA.encrypt(merchantAESKey, yeepayPublicKey);

			NameValuePair[] datas = {
					new NameValuePair("merchantaccount", merchantaccount),
					new NameValuePair("data", data),
					new NameValuePair("encryptkey", encryptkey) };

			postMethod.setRequestBody(datas);

			int statusCode = httpClient.executeMethod(postMethod);
			byte[] responseByte = postMethod.getResponseBody();
			String responseBody = new String(responseByte, "UTF-8");

			result = parseHttpResponseBody(statusCode, responseBody);

		} catch (Exception e) {
			e.printStackTrace();
			customError = "Caught an Exception." + e.toString();
			result.put("customError", customError);
		} finally {
			postMethod.releaseConnection();
		}

		System.out.println("绑卡请求result : " + result);

		return (result);
	}
	
	/**
	 * bindCardRequest() : 4.2 绑定银行卡短验发送请求
	 */

	public static Map<String, String> bindCardSendSms(String requestid) {

		System.out.println("##### bindCardSendSms() #####");

		Map<String, String> result = new HashMap<String, String>();
		String customError = ""; // 自定义，非接口返回

		String merchantaccount = getMerchantAccount();
		String merchantPrivateKey = getMerchantPrivateKey();
		String merchantAESKey = getMerchantAESKey();
		String yeepayPublicKey = getYeepayPublicKey();
		String bindCardSendSmsURL = getBindCardSendSmsURL();

		try {
			if (requestid == null) {
				throw new Exception("requestid is null!!!!!");
			} else {
				requestid = formatString(requestid);
			}

		} catch (Exception e) {
			e.printStackTrace();
			customError = "Caught an Exception. " + e.toString();
			result.put("customError", customError);
			return (result);
		}

		TreeMap<String, Object> dataMap = new TreeMap<String, Object>();
		dataMap.put("merchantaccount", merchantaccount);
		dataMap.put("requestid", requestid);

		String sign = EncryUtil.handleRSA(dataMap, merchantPrivateKey);
		dataMap.put("sign", sign);

		System.out.println("bindCardSendSmsURL : " + bindCardSendSmsURL);
		System.out.println("dataMap : " + dataMap);

		HttpClient httpClient = new HttpClient();
		PostMethod postMethod = new PostMethod(bindCardSendSmsURL);

		try {
			String jsonStr = JSON.toJSONString(dataMap);
			String data = AES.encryptToBase64(jsonStr, merchantAESKey);
			String encryptkey = RSA.encrypt(merchantAESKey, yeepayPublicKey);

			NameValuePair[] datas = {
					new NameValuePair("merchantaccount", merchantaccount),
					new NameValuePair("data", data),
					new NameValuePair("encryptkey", encryptkey) };

			postMethod.setRequestBody(datas);

			int statusCode = httpClient.executeMethod(postMethod);
			byte[] responseByte = postMethod.getResponseBody();
			String responseBody = new String(responseByte, "UTF-8");

			result = parseHttpResponseBody(statusCode, responseBody);

		} catch (Exception e) {
			e.printStackTrace();
			customError = "Caught an Exception." + e.toString();
			result.put("customError", customError);
		} finally {
			postMethod.releaseConnection();
		}

		System.out.println("发送短信result : " + result);

		return (result);
	}
	
	/**
	 * bindCardRequest() : 4.3 校验绑定银行卡短验请求
	 */

	public static Map<String, String> bindCardCheckSms(Map<String, String> params) {

		System.out.println("##### bindCardCheckSms() #####");

		Map<String, String> result = new HashMap<String, String>();
		String customError = ""; // 自定义，非接口返回

		String merchantaccount = getMerchantAccount();
		String merchantPrivateKey = getMerchantPrivateKey();
		String merchantAESKey = getMerchantAESKey();
		String yeepayPublicKey = getYeepayPublicKey();
		String bindCardCheckSmsURL = getBindCardCheckSmsURL();

		String validatecode = params.get("validatecode");
		String requestid = params.get("requestid");

		TreeMap<String, Object> dataMap = new TreeMap<String, Object>();
		dataMap.put("merchantaccount", merchantaccount);
		dataMap.put("validatecode", validatecode);
		dataMap.put("requestid", requestid);

		String sign = EncryUtil.handleRSA(dataMap, merchantPrivateKey);
		dataMap.put("sign", sign);

		System.out.println("bindCardCheckSmsURL : " + bindCardCheckSmsURL);
		System.out.println("dataMap : " + dataMap);

		HttpClient httpClient = new HttpClient();
		PostMethod postMethod = new PostMethod(bindCardCheckSmsURL);

		try {
			String jsonStr = JSON.toJSONString(dataMap);
			String data = AES.encryptToBase64(jsonStr, merchantAESKey);
			String encryptkey = RSA.encrypt(merchantAESKey, yeepayPublicKey);

			NameValuePair[] datas = {
					new NameValuePair("merchantaccount", merchantaccount),
					new NameValuePair("data", data),
					new NameValuePair("encryptkey", encryptkey) };

			postMethod.setRequestBody(datas);

			int statusCode = httpClient.executeMethod(postMethod);
			byte[] responseByte = postMethod.getResponseBody();
			String responseBody = new String(responseByte, "UTF-8");

			result = parseHttpResponseBody(statusCode, responseBody);

		} catch (Exception e) {
			e.printStackTrace();
			customError = "Caught an Exception." + e.toString();
			result.put("customError", customError);
		} finally {
			postMethod.releaseConnection();
		}

		System.out.println("校验短信result : " + result);

		return (result);
	}
	
	/**
	 * bankcardBindQuery() : 4.4 查询绑卡信息列表
	 */

	public static Map<String, String> bankcardBindQuery(String identityid,
			String identitytype) {

		System.out.println("##### bankcardBindQuery() #####");

		Map<String, String> result = new HashMap<String, String>();
		String customError = ""; // 自定义，非接口返回

		String merchantaccount = getMerchantAccount();
		String merchantPrivateKey = getMerchantPrivateKey();
		String merchantAESKey = getMerchantAESKey();
		String yeepayPublicKey = getYeepayPublicKey();
		String bankcardBindQueryURL = getBankcardBindQueryURL();

		int identitytype2Int = -1;

		try {
			identitytype2Int = String2Int(identitytype);
		} catch (Exception e) {
			e.printStackTrace();
			customError = "Caught an Exception. " + e.toString();
			result.put("customError", customError);
			return (result);
		}

		TreeMap<String, Object> dataMap = new TreeMap<String, Object>();
		dataMap.put("merchantaccount", merchantaccount);
		dataMap.put("identityid", identityid);
		dataMap.put("identitytype", identitytype2Int);

		String sign = EncryUtil.handleRSA(dataMap, merchantPrivateKey);
		dataMap.put("sign", sign);

		System.out.println("bankcardBindQueryURL : " + bankcardBindQueryURL);
		System.out.println("dataMap : " + dataMap);

		HttpClient httpClient = new HttpClient();
		GetMethod getMethod = new GetMethod();

		try {
			String jsonStr = JSON.toJSONString(dataMap);
			String data = AES.encryptToBase64(jsonStr, merchantAESKey);
			String encryptkey = RSA.encrypt(merchantAESKey, yeepayPublicKey);

			String url = bankcardBindQueryURL + "?merchantaccount="
					+ URLEncoder.encode(merchantaccount, "UTF-8") + "&data="
					+ URLEncoder.encode(data, "UTF-8") + "&encryptkey="
					+ URLEncoder.encode(encryptkey, "UTF-8");

			getMethod = new GetMethod(url);
			int statusCode = httpClient.executeMethod(getMethod);
			String responseBody = getMethod.getResponseBodyAsString();

			result = parseHttpResponseBody(statusCode, responseBody);

		} catch (Exception e) {
			e.printStackTrace();
			customError = "Caught an Exception." + e.toString();
			result.put("customError", customError);
		} finally {
			getMethod.releaseConnection();
		}

		System.out.println("result -----------ss " + result);

		return (result);
	}

	/**
	 * bindidRequest() : 4.5 绑卡支付请求
	 */

	public static Map<String, String> bindidRequest(Map<String, String> params) {

		System.out.println("##### bindidRequest() #####");

		Map<String, String> result = new HashMap<String, String>();
		String customError = ""; // 自定义，非接口返回

		String merchantaccount = getMerchantAccount();
		String merchantPrivateKey = getMerchantPrivateKey();
		String merchantAESKey = getMerchantAESKey();
		String yeepayPublicKey = getYeepayPublicKey();
		String bindidRequestURL = getBindidRequestURL();

		String bindid = formatString(params.get("bindid"));
		String orderid = formatString(params.get("orderid"));
		String productcatalog = formatString(params.get("productcatalog"));
		String productname = formatString(params.get("productname"));
		String identityid = formatString(params.get("identityid"));
		String terminalid = formatString(params.get("terminalid"));
		String userip = formatString(params.get("userip"));
		String callbackurl = formatString(params.get("callbackurl"));
		String productdesc = formatString(params.get("productdesc"));
		String other = formatString(params.get("other"));

		int transtime = 0;
		int amount = 0;
		int identitytype = 0;
		int terminaltype = 0;
		int currency = 0;
		int orderexpdate = 0;

		try {
			if (params.get("transtime") == null) {
				throw new Exception("transtime is null!!!!!");
			} else {
				transtime = String2Int(params.get("transtime"));
			}

			if (params.get("identitytype") == null) {
				throw new Exception("identitytype is null!!!!!");
			} else {
				identitytype = String2Int(params.get("identitytype"));
			}

			if (params.get("amount") == null) {
				throw new Exception("amount is null!!!!!");
			} else {
				amount = String2Int(params.get("amount"));
			}

			if (params.get("orderexpdate") == null) {
				throw new Exception("orderexpdate is null!!!!!");
			} else {
				orderexpdate = String2Int(params.get("orderexpdate"));
			}

			terminaltype = String2Int(params.get("terminaltype"));
			currency = String2Int(params.get("currency"));
		} catch (Exception e) {
			e.printStackTrace();
			customError = "Caught an Exception. " + e.toString();
			result.put("customError", customError);
			System.out.println("params: " + params);
			return (result);
		}

		TreeMap<String, Object> dataMap = new TreeMap<String, Object>();
		dataMap.put("merchantaccount", merchantaccount);
		dataMap.put("bindid", bindid);
		dataMap.put("orderid", orderid);
		dataMap.put("productcatalog", productcatalog);
		dataMap.put("productname", productname);
		dataMap.put("identityid", identityid);
		dataMap.put("terminalid", terminalid);
		dataMap.put("userip", userip);
		dataMap.put("transtime", transtime);
		dataMap.put("amount", amount);
		dataMap.put("identitytype", identitytype);
		dataMap.put("terminaltype", terminaltype);
		dataMap.put("callbackurl", callbackurl);
		dataMap.put("productdesc", productdesc);
		dataMap.put("currency", currency);
		dataMap.put("other", other);
		dataMap.put("orderexpdate", orderexpdate);

		String sign = EncryUtil.handleRSA(dataMap, merchantPrivateKey);
		dataMap.put("sign", sign);

		System.out.println("bindidRequestURL : " + bindidRequestURL);
		System.out.println("dataMap : " + dataMap);

		HttpClient httpClient = new HttpClient();
		PostMethod postMethod = new PostMethod(bindidRequestURL);

		try {
			String jsonStr = JSON.toJSONString(dataMap);
			String data = AES.encryptToBase64(jsonStr, merchantAESKey);
			String encryptkey = RSA.encrypt(merchantAESKey, yeepayPublicKey);

			NameValuePair[] datas = {
					new NameValuePair("merchantaccount", merchantaccount),
					new NameValuePair("data", data),
					new NameValuePair("encryptkey", encryptkey) };
			postMethod.setRequestBody(datas);

			int statusCode = httpClient.executeMethod(postMethod);
			byte[] responseByte = postMethod.getResponseBody();
			String responseBody = new String(responseByte, "UTF-8");

			result = parseHttpResponseBody(statusCode, responseBody);
		} catch (Exception e) {
			e.printStackTrace();
			customError = "Caught an Exception." + e.toString();
			result.put("customError", customError);
		} finally {
			postMethod.releaseConnection();
		}

		System.out.println("result : " + result);

		return (result);
	}

	/**
	 * sendMessage() : 4.6 发送短信验证码
	 */

	public static Map<String, String> sendMessage(String orderid) {

		System.out.println("##### sendMessage() #####");

		String merchantaccount = getMerchantAccount();
		String merchantPrivateKey = getMerchantPrivateKey();
		String merchantAESKey = getMerchantAESKey();
		String yeepayPublicKey = getYeepayPublicKey();
		String sendMessageURL = getSendMessageURL();

		TreeMap<String, Object> dataMap = new TreeMap<String, Object>();
		dataMap.put("merchantaccount", merchantaccount);
		dataMap.put("orderid", orderid);

		String sign = EncryUtil.handleRSA(dataMap, merchantPrivateKey);
		dataMap.put("sign", sign);

		System.out.println("sendMessageURL : " + sendMessageURL);
		System.out.println("dataMap : " + dataMap);

		Map<String, String> result = new HashMap<String, String>();
		String customError = ""; // 自定义，非接口返回

		HttpClient httpClient = new HttpClient();
		PostMethod postMethod = new PostMethod(sendMessageURL);

		try {
			String jsonStr = JSON.toJSONString(dataMap);
			String data = AES.encryptToBase64(jsonStr, merchantAESKey);
			String encryptkey = RSA.encrypt(merchantAESKey, yeepayPublicKey);

			NameValuePair[] datas = {
					new NameValuePair("merchantaccount", merchantaccount),
					new NameValuePair("data", data),
					new NameValuePair("encryptkey", encryptkey) };
			postMethod.setRequestBody(datas);

			int statusCode = httpClient.executeMethod(postMethod);
			byte[] responseByte = postMethod.getResponseBody();
			String responseBody = new String(responseByte, "UTF-8");

			result = parseHttpResponseBody(statusCode, responseBody);
		} catch (Exception e) {
			e.printStackTrace();
			customError = "Caught an Exception." + e.toString();
			result.put("customError", customError);
		} finally {
			postMethod.releaseConnection();
		}

		System.out.println("result : " + result);

		return (result);
	}

	/**
	 * paymentConfirm() : 4.7 确认支付
	 */

	public static Map<String, String> paymentConfirm(Map<String, String> params) {

		System.out.println("##### payapiPayConfirm() #####");

		String merchantaccount = getMerchantAccount();
		String merchantPrivateKey = getMerchantPrivateKey();
		String merchantAESKey = getMerchantAESKey();
		String yeepayPublicKey = getYeepayPublicKey();
		String paymentConfirmURL = getPaymentConfirmURL();

		String validatecode = params.get("validatecode");
		String orderid 	 	= params.get("orderid");
		String idcardtype   = params.get("idcardtype");
		String idcard  	 	= params.get("idcard");
		String owner 	 	= params.get("owner");
		String cvv2		 	= params.get("cvv2");
		String validthru 	= params.get("validthru");

		TreeMap<String, Object> dataMap = new TreeMap<String, Object>();
		dataMap.put("merchantaccount",  merchantaccount);
		dataMap.put("validatecode", 	validatecode);
		dataMap.put("orderid", 			orderid);
		dataMap.put("idcardtype",		idcardtype);
		dataMap.put("idcard",	 		idcard);
		dataMap.put("owner",	 		owner);
		dataMap.put("cvv2",		 		cvv2);
		dataMap.put("validthru", 		validthru);

		String sign = EncryUtil.handleRSA(dataMap, merchantPrivateKey);
		dataMap.put("sign", sign);

		System.out.println("paymentConfirmURL : " + paymentConfirmURL);
		System.out.println("dataMap : " + dataMap);

		Map<String, String> result = new HashMap<String, String>();
		String customError = "";

		HttpClient httpClient = new HttpClient();
		PostMethod postMethod = new PostMethod(paymentConfirmURL);

		try {
			String jsonStr = JSON.toJSONString(dataMap);
			String data = AES.encryptToBase64(jsonStr, merchantAESKey);
			String encryptkey = RSA.encrypt(merchantAESKey, yeepayPublicKey);

			NameValuePair[] datas = {
					new NameValuePair("merchantaccount", merchantaccount),
					new NameValuePair("data", data),
					new NameValuePair("encryptkey", encryptkey) };
			postMethod.setRequestBody(datas);

			int statusCode = httpClient.executeMethod(postMethod);
			byte[] responseByte = postMethod.getResponseBody();
			String responseBody = new String(responseByte, "UTF-8");

			result = parseHttpResponseBody(statusCode, responseBody);

		} catch (Exception e) {
			e.printStackTrace();
			customError = "Caught an Exception." + e.toString();
			result.put("customError", customError);
		} finally {
			postMethod.releaseConnection();
		}

		System.out.println("result : " + result);

		return (result);
	}

	/**
	 * payapiQueryByOrderid() : 4.8 支付结果查询
	 */

	public static Map<String, String> payapiQueryByOrderid(String orderid) {

		System.out.println("##### payapiQueryByOrderid() #####");

		String merchantaccount = getMerchantAccount();
		String merchantPrivateKey = getMerchantPrivateKey();
		String merchantAESKey = getMerchantAESKey();
		String yeepayPublicKey = getYeepayPublicKey();
		String payapiQueryURL = getPayapiQueryURL();

		TreeMap<String, Object> dataMap = new TreeMap<String, Object>();
		dataMap.put("merchantaccount", merchantaccount);
		dataMap.put("orderid", orderid);

		String sign = EncryUtil.handleRSA(dataMap, merchantPrivateKey);
		dataMap.put("sign", sign);

		System.out.println("payapiQueryURL : " + payapiQueryURL);
		System.out.println("dataMap : " + dataMap);

		Map<String, String> result = new HashMap<String, String>();
		String customError = ""; // 自定义，非接口返回

		HttpClient httpClient = new HttpClient();
		GetMethod getMethod = new GetMethod();

		try {
			String jsonStr = JSON.toJSONString(dataMap);
			String data = AES.encryptToBase64(jsonStr, merchantAESKey);
			String encryptkey = RSA.encrypt(merchantAESKey, yeepayPublicKey);

			String url = payapiQueryURL + "?merchantaccount="
					+ URLEncoder.encode(merchantaccount, "UTF-8") + "&data="
					+ URLEncoder.encode(data, "UTF-8") + "&encryptkey="
					+ URLEncoder.encode(encryptkey, "UTF-8");

			System.out.println("url	 : " + url);

			getMethod = new GetMethod(url);
			int statusCode = httpClient.executeMethod(getMethod);
			String responseBody = getMethod.getResponseBodyAsString();

			result = parseHttpResponseBody(statusCode, responseBody);

		} catch (Exception e) {
			customError = "Caught an Exception. " + e.toString();
			result.put("customError", customError);
			e.printStackTrace();
		} finally {
			getMethod.releaseConnection();
		}

		System.out.println("result : " + result);

		return (result);
	}

	/**
	 * decryptCallbackData() : 4.9 返回支付回调参数
	 */

	public static Map<String, String> decryptCallbackData(String data,
			String encryptkey) {

		System.out.println("##### decryptCallbackData() #####");

		String merchantaccount = getMerchantAccount();
		String merchantPrivateKey = getMerchantPrivateKey();
		String merchantAESKey = getMerchantAESKey();
		String yeepayPublicKey = getYeepayPublicKey();

		Map<String, String> result = new HashMap<String, String>();
		String customError = ""; // 自定义，非接口返回

		try {
			boolean signMatch = EncryUtil.checkDecryptAndSign(data, encryptkey,
					yeepayPublicKey, merchantPrivateKey);

			if (!signMatch) {
				customError = "Sign not match error";
				result.put("customError", customError);
				return result;
			}

			String yeepayAESKey = RSA.decrypt(encryptkey, merchantPrivateKey);
			String decryptData = AES.decryptFromBase64(data, yeepayAESKey);

			result = JSON.parseObject(decryptData,
					new TypeReference<TreeMap<String, String>>() {
					});

		} catch (Exception e) {
			customError = "Caught an Exception. " + e.toString();
			result.put("customError", customError);
			e.printStackTrace();
		}

		System.out.println("result : " + result);

		return (result);
	}

	/**
	 * unbindBankcard() : 4.10 银行卡解绑方法
	 */

	public static Map<String, String> unbindBankcard(Map<String, String> params) {

		System.out.println("##### unbindBankcard() #####");

		Map<String, String> result = new HashMap<String, String>();
		String customError = ""; // 自定义，非接口返回

		String merchantaccount = getMerchantAccount();
		String merchantPrivateKey = getMerchantPrivateKey();
		String merchantAESKey = getMerchantAESKey();
		String yeepayPublicKey = getYeepayPublicKey();
		String unbindBankcardURL = getUnbindBankcardURL();

		String bindid = formatString(params.get("bindid"));
		String identityid = formatString(params.get("identityid"));

		int identitytype = 0;

		try {
			if (params.get("identitytype") == null) {
				throw new Exception("identitytype is null!!!!!");
			} else {
				identitytype = String2Int(params.get("identitytype"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			customError = "******input params error : String to Int Exception - "
					+ " identitytype=["
					+ formatString(params.get("identitytype"))
					+ "]"
					+ e.toString();
			result.put("customError", customError);
			return (result);
		}

		TreeMap<String, Object> dataMap = new TreeMap<String, Object>();
		dataMap.put("merchantaccount", merchantaccount);
		dataMap.put("bindid", bindid);
		dataMap.put("identityid", identityid);
		dataMap.put("identitytype", identitytype);

		String sign = EncryUtil.handleRSA(dataMap, merchantPrivateKey);
		dataMap.put("sign", sign);

		System.out.println("unbindBankcardURL : " + unbindBankcardURL);
		System.out.println("dataMap : " + dataMap);

		HttpClient httpClient = new HttpClient();
		PostMethod postMethod = new PostMethod(unbindBankcardURL);

		try {
			String jsonStr = JSON.toJSONString(dataMap);
			String data = AES.encryptToBase64(jsonStr, merchantAESKey);
			String encryptkey = RSA.encrypt(merchantAESKey, yeepayPublicKey);

			NameValuePair[] datas = {
					new NameValuePair("merchantaccount", merchantaccount),
					new NameValuePair("data", data),
					new NameValuePair("encryptkey", encryptkey) };
			postMethod.setRequestBody(datas);

			int statusCode = httpClient.executeMethod(postMethod);
			byte[] responseByte = postMethod.getResponseBody();
			String responseBody = new String(responseByte, "UTF-8");

			result = parseHttpResponseBody(statusCode, responseBody);

		} catch (Exception e) {
			customError = "Caught Exception!" + e.toString();
			result.put("customError", customError);
			e.printStackTrace();
		} finally {
			postMethod.releaseConnection();
		}

		System.out.println("result : " + result);

		return (result);
	}

	/**
	 * bankCardCheck() : 4.11 银行卡查询方法
	 */

	public static Map<String, String> bankCardCheck(String cardno) {

		System.out.println("##### bankCardCheck() #####");

		String merchantaccount = getMerchantAccount();
		String merchantPrivateKey = getMerchantPrivateKey();
		String merchantAESKey = getMerchantAESKey();
		String yeepayPublicKey = getYeepayPublicKey();
		String bankCardCheckURL = getBankCardCheckURL();

		TreeMap<String, Object> dataMap = new TreeMap<String, Object>();
		dataMap.put("merchantaccount", merchantaccount);
		dataMap.put("cardno", cardno);

		String sign = EncryUtil.handleRSA(dataMap, merchantPrivateKey);
		dataMap.put("sign", sign);

		System.out.println("bankCardCheckURL : " + bankCardCheckURL);
		System.out.println("dataMap : " + dataMap);

		Map<String, String> result = new HashMap<String, String>();
		String customError = ""; // 自定义，非接口返回

		HttpClient httpClient = new HttpClient();
		PostMethod postMethod = new PostMethod(bankCardCheckURL);

		try {
			String jsonStr = JSON.toJSONString(dataMap);
			String data = AES.encryptToBase64(jsonStr, merchantAESKey);
			String encryptkey = RSA.encrypt(merchantAESKey, yeepayPublicKey);

			NameValuePair[] datas = {
					new NameValuePair("merchantaccount", merchantaccount),
					new NameValuePair("data", data),
					new NameValuePair("encryptkey", encryptkey) };
			postMethod.setRequestBody(datas);

			int statusCode = httpClient.executeMethod(postMethod);
			byte[] responseByte = postMethod.getResponseBody();
			String responseBody = new String(responseByte, "UTF-8");

			result = parseHttpResponseBody(statusCode, responseBody);

		} catch (Exception e) {
			customError = "Caught Exception!" + e.toString();
			result.put("customError", customError);
			e.printStackTrace();
		} finally {
			postMethod.releaseConnection();
		}

		System.out.println("result : " + result);

		return (result);
	}
public static void main(String[] args) {
	Map<String,String> map=bankCardCheck("6228480010652491019");
	
}
	/**
	 * singleQuery() : 单笔查询
	 */

	public static Map<String, String> singleQuery(String orderid,
			String yborderid) {

		System.out.println("##### singleQuery() #####");

		String merchantaccount = getMerchantAccount();
		String merchantPrivateKey = getMerchantPrivateKey();
		String merchantAESKey = getMerchantAESKey();
		String yeepayPublicKey = getYeepayPublicKey();
		String singleQueryURL = getSingleQueryURL();

		TreeMap<String, Object> dataMap = new TreeMap<String, Object>();
		dataMap.put("merchantaccount", merchantaccount);
		dataMap.put("orderid", orderid);
		dataMap.put("yborderid", yborderid);

		String sign = EncryUtil.handleRSA(dataMap, merchantPrivateKey);
		dataMap.put("sign", sign);

		System.out.println("singleQueryURL : " + singleQueryURL);
		System.out.println("dataMap : " + dataMap);

		Map<String, String> result = new HashMap<String, String>();
		String customError = ""; // 自定义，非接口返回

		HttpClient httpClient = new HttpClient();
		GetMethod getMethod = new GetMethod();

		try {
			String jsonStr = JSON.toJSONString(dataMap);
			String data = AES.encryptToBase64(jsonStr, merchantAESKey);
			String encryptkey = RSA.encrypt(merchantAESKey, yeepayPublicKey);

			String url = singleQueryURL + "?merchantaccount="
					+ URLEncoder.encode(merchantaccount, "UTF-8") + "&data="
					+ URLEncoder.encode(data, "UTF-8") + "&encryptkey="
					+ URLEncoder.encode(encryptkey, "UTF-8");

			System.out.println("url	 : " + url);

			getMethod = new GetMethod(url);
			int statusCode = httpClient.executeMethod(getMethod);
			String responseBody = getMethod.getResponseBodyAsString();

			result = parseHttpResponseBody(statusCode, responseBody);

		} catch (Exception e) {
			customError = "Caught an Exception. " + e.toString();
			result.put("customError", customError);
			e.printStackTrace();
		} finally {
			getMethod.releaseConnection();
		}

		System.out.println("result : " + result);

		return (result);
	}

	/**
	 * getPathOfPayClearData()
	 *
	 * 参数说明：
	 *
	 * merchantaccount - 商户编号 merchantPrivateKey - 商户私钥 merchantAESKey -
	 * 商户随机生成的AESKey yeepayPublicKey - 易宝公玥
	 *
	 * 接口请求参数：所有的请求参数名，均是大小写敏感的，如：merchantaccount，为小写无大写。
	 *
	 * merchantaccount - string - 必填 - 商户编号 startdate - string - 必填 -
	 * 查询起始时间，格式：2015-01-01 enddate - string - 必填 - 查询终止时间，格式：2015-01-31 sign -
	 * string - 必填 - 签名信息
	 *
	 * 返回说明：
	 *
	 * filePath - 批量查询结果文件的路径 error_code - 错误返回码 error - 错误信息 customError -
	 * 自定义，非接口返回
	 *
	 */

	public static Map<String, String> getPathOfPayClearData(String startdate,
			String enddate, String sysPath) {

		System.out.println("##### getPathOfPayClearData() #####");

		String merchantaccount = getMerchantAccount();
		String merchantPrivateKey = getMerchantPrivateKey();
		String merchantAESKey = getMerchantAESKey();
		String yeepayPublicKey = getYeepayPublicKey();
		String payClearDataURL = getPayClearDataURL();

		TreeMap<String, Object> dataMap = new TreeMap<String, Object>();
		dataMap.put("merchantaccount", merchantaccount);
		dataMap.put("startdate", startdate);
		dataMap.put("enddate", enddate);

		String sign = EncryUtil.handleRSA(dataMap, merchantPrivateKey);
		dataMap.put("sign", sign);

		Map<String, String> queryResult = new HashMap<String, String>();
		String filePath = "";
		String error_code = "";
		String error = "";
		String customError = "";

		HttpClient httpClient = new HttpClient();
		GetMethod getMethod = new GetMethod();

		try {
			String jsonStr = JSON.toJSONString(dataMap);
			String data = AES.encryptToBase64(jsonStr, merchantAESKey);
			String encryptkey = RSA.encrypt(merchantAESKey, yeepayPublicKey);

			String url = payClearDataURL + "?merchantaccount="
					+ URLEncoder.encode(merchantaccount, "UTF-8") + "&data="
					+ URLEncoder.encode(data, "UTF-8") + "&encryptkey="
					+ URLEncoder.encode(encryptkey, "UTF-8");

			getMethod = new GetMethod(url);

			int statusCode = httpClient.executeMethod(getMethod);

			if (statusCode != 200) {
				customError = "Get request failed, response code = "
						+ statusCode;
				queryResult.put("customError", customError);
				return (queryResult);
			}

			InputStream responseStream = getMethod.getResponseBodyAsStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					responseStream, "UTF-8"));
			// BufferedReader reader = new BufferedReader(new
			// InputStreamReader(responseStream));

			String line = reader.readLine();
			if (line.startsWith("{")) {
				Map<String, Object> jsonMap = JSON.parseObject(line, TreeMap.class);

				if (jsonMap.containsKey("error_code")) {
					error_code = formatString((String) jsonMap
							.get("error_code"));
					error = formatString((String) jsonMap.get("error"));
				} else {
					String dataFromYeepay = formatString((String) jsonMap
							.get("data"));
					String encryptkeyFromYeepay = formatString((String) jsonMap
							.get("encryptkey"));

					String yeepayAESKey = RSA.decrypt(encryptkeyFromYeepay,
							merchantPrivateKey);
					String decryptData = AES.decryptFromBase64(dataFromYeepay,
							yeepayAESKey);
					Map<String, Object> decryptDataMap = JSON.parseObject(decryptData, TreeMap.class);

					error_code = formatString((String) decryptDataMap
							.get("error_code"));
					error = formatString((String) decryptDataMap.get("error"));

					System.out.println("decryptData : " + decryptData);
				}
			} else {
				String outputFilePath = sysPath + File.separator + "clearData";
				File file = new File(outputFilePath);
				file.mkdir();

				String time = String.valueOf(System.currentTimeMillis());
				String fileName = "payClearData_" + startdate + "_" + enddate
						+ "_" + time + ".txt";
				String absolutePathOfOutputFile = outputFilePath
						+ File.separator + fileName;
				filePath = absolutePathOfOutputFile;

				File outputFile = new File(absolutePathOfOutputFile);
				FileWriter fileWriter = new FileWriter(outputFile);
				BufferedWriter writer = new BufferedWriter(fileWriter);

				System.out.println("filePath : " + filePath);

				writer.write(line);
				writer.write(System.getProperty("line.separator"));
				while ((line = reader.readLine()) != null) {
					writer.write(line);
					writer.write(System.getProperty("line.separator"));
				}

				writer.close();
			}
		} catch (Exception e) {
			customError = "Caught an Exception. " + e.toString();
			e.printStackTrace();
		} finally {
			getMethod.releaseConnection();
		}

		queryResult.put("filePath", filePath);
		queryResult.put("error_code", error_code);
		queryResult.put("error", error);
		queryResult.put("customError", customError);

		return (queryResult);
	}

	/**
	 * refund() : 单笔退款方法
	 */

	public static Map<String, String> refund(Map<String, String> params) {

		System.out.println("##### refund() #####");

		Map<String, String> result = new HashMap<String, String>();
		String customError = ""; // 自定义，非接口返回

		String merchantaccount = getMerchantAccount();
		String merchantPrivateKey = getMerchantPrivateKey();
		String merchantAESKey = getMerchantAESKey();
		String yeepayPublicKey = getYeepayPublicKey();
		String refundURL = getRefundURL();

		String origyborderid = formatString(params.get("origyborderid"));
		String orderid = formatString(params.get("orderid"));
		String cause = formatString(params.get("cause"));

		int amount = 0;
		int currency = 0;

		try {
			// amount、currency是必填参数
			if (params.get("amount") == null) {
				throw new Exception("amount is null!!!!!");
			} else {
				amount = String2Int(params.get("amount"));
			}

			if (params.get("currency") == null) {
				throw new Exception("currency is null!!!!!");
			} else {
				currency = String2Int(params.get("currency"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			customError = "******input params error : String to Int Exception - "
					+ "], amount=["
					+ amount
					+ "], currency=["
					+ currency
					+ "]"
					+ e.toString();
			result.put("customError", customError);
			return (result);
		}

		TreeMap<String, Object> dataMap = new TreeMap<String, Object>();
		dataMap.put("merchantaccount", merchantaccount);
		dataMap.put("origyborderid", origyborderid);
		dataMap.put("orderid", orderid);
		dataMap.put("cause", cause);
		dataMap.put("amount", amount);
		dataMap.put("currency", currency);

		String sign = EncryUtil.handleRSA(dataMap, merchantPrivateKey);
		dataMap.put("sign", sign);

		System.out.println("params : " + params);
		System.out.println("refundURL : " + refundURL);
		System.out.println("dataMap : " + dataMap);

		HttpClient httpClient = new HttpClient();
		PostMethod postMethod = new PostMethod(refundURL);

		try {
			String jsonStr = JSON.toJSONString(dataMap);
			String data = AES.encryptToBase64(jsonStr, merchantAESKey);
			String encryptkey = RSA.encrypt(merchantAESKey, yeepayPublicKey);

			System.out.println("data : " + data);
			System.out.println("encryptkey : " + encryptkey);

			NameValuePair[] datas = {
					new NameValuePair("merchantaccount", merchantaccount),
					new NameValuePair("data", data),
					new NameValuePair("encryptkey", encryptkey) };

			postMethod.setRequestBody(datas);

			int statusCode = httpClient.executeMethod(postMethod);
			byte[] responseByte = postMethod.getResponseBody();
			String responseBody = new String(responseByte, "UTF-8");

			result = parseHttpResponseBody(statusCode, responseBody);

		} catch (Exception e) {
			customError = "Caught an Exception. " + e.toString();
			result.put("customError", customError);
			e.printStackTrace();
		} finally {
			postMethod.releaseConnection();
		}

		System.out.println("result : " + result);

		return (result);
	}

	/**
	 * refundQuery() : 退款查询
	 */

	public static Map<String, String> refundQuery(String orderid,
			String yborderid) {

		System.out.println("##### refundQuery() #####");

		String merchantaccount = getMerchantAccount();
		String merchantPrivateKey = getMerchantPrivateKey();
		String merchantAESKey = getMerchantAESKey();
		String yeepayPublicKey = getYeepayPublicKey();
		String refundQueryURL = getRefundQueryURL();

		TreeMap<String, Object> dataMap = new TreeMap<String, Object>();
		dataMap.put("merchantaccount", merchantaccount);
		dataMap.put("orderid", orderid);
		dataMap.put("yborderid", yborderid);

		String sign = EncryUtil.handleRSA(dataMap, merchantPrivateKey);
		dataMap.put("sign", sign);

		System.out.println("refundQueryURL : " + refundQueryURL);
		System.out.println("dataMap : " + dataMap);

		Map<String, String> result = new HashMap<String, String>();
		String customError = "";

		HttpClient httpClient = new HttpClient();
		GetMethod getMethod = new GetMethod();

		try {
			String jsonStr = JSON.toJSONString(dataMap);
			String data = AES.encryptToBase64(jsonStr, merchantAESKey);
			String encryptkey = RSA.encrypt(merchantAESKey, yeepayPublicKey);

			String url = refundQueryURL + "?merchantaccount="
					+ URLEncoder.encode(merchantaccount, "UTF-8") + "&data="
					+ URLEncoder.encode(data, "UTF-8") + "&encryptkey="
					+ URLEncoder.encode(encryptkey, "UTF-8");

			getMethod = new GetMethod(url);
			int statusCode = httpClient.executeMethod(getMethod);
			String responseBody = getMethod.getResponseBodyAsString();

			result = parseHttpResponseBody(statusCode, responseBody);

		} catch (Exception e) {
			customError = "Caught an Exception. " + e.toString();
			result.put("customError", customError);
			e.printStackTrace();
		} finally {
			getMethod.releaseConnection();
		}

		System.out.println("result : " + result);

		return (result);
	}

	/**
	 * getPathOfRefundClearData()
	 *
	 * 参数说明：
	 *
	 * merchantaccount - 商户编号 merchantPrivateKey - 商户私钥 merchantAESKey -
	 * 商户随机生成的AESKey yeepayPublicKey - 易宝公玥
	 *
	 * 接口请求参数：所有的请求参数名，均是大小写敏感的，如：merchantaccount，为小写无大写。
	 *
	 * merchantaccount - string - 必填 - 商户编号 startdate - string - 必填 -
	 * 查询起始时间，格式：2015-01-01 enddate - string - 必填 - 查询终止时间，格式：2015-01-31 sign -
	 * string - 必填 - 签名信息
	 *
	 * 返回说明：
	 *
	 * filePath - 批量查询结果文件的路径 error_code - 错误返回码 error - 错误信息 customError -
	 * 自定义，非接口返回
	 *
	 */

	public static Map<String, String> getPathOfRefundClearData(
			String startdate, String enddate, String sysPath) {

		System.out.println("##### getPathOfRefundClearData() #####");

		String merchantaccount = getMerchantAccount();
		String merchantPrivateKey = getMerchantPrivateKey();
		String merchantAESKey = getMerchantAESKey();
		String yeepayPublicKey = getYeepayPublicKey();
		String refundClearDataURL = getRefundClearDataURL();

		TreeMap<String, Object> dataMap = new TreeMap<String, Object>();
		dataMap.put("merchantaccount", merchantaccount);
		dataMap.put("startdate", startdate);
		dataMap.put("enddate", enddate);

		String sign = EncryUtil.handleRSA(dataMap, merchantPrivateKey);
		dataMap.put("sign", sign);

		System.out.println("refundClearDataURL : " + refundClearDataURL);
		System.out.println("dataMap : " + dataMap);

		Map<String, String> queryResult = new HashMap<String, String>();
		String filePath = "";
		String error_code = "";
		String error = "";
		String customError = "";

		HttpClient httpClient = new HttpClient();
		GetMethod getMethod = new GetMethod();

		try {
			String jsonStr = JSON.toJSONString(dataMap);
			String data = AES.encryptToBase64(jsonStr, merchantAESKey);
			String encryptkey = RSA.encrypt(merchantAESKey, yeepayPublicKey);

			String url = refundClearDataURL + "?merchantaccount="
					+ URLEncoder.encode(merchantaccount, "UTF-8") + "&data="
					+ URLEncoder.encode(data, "UTF-8") + "&encryptkey="
					+ URLEncoder.encode(encryptkey, "UTF-8");

			getMethod = new GetMethod(url);
			int statusCode = httpClient.executeMethod(getMethod);

			if (statusCode != 200) {
				customError = "Get request failed, response code = "
						+ statusCode;
				queryResult.put("customError", customError);
				return (queryResult);
			}

			InputStream responseStream = getMethod.getResponseBodyAsStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					responseStream, "UTF-8"));
			// BufferedReader reader = new BufferedReader(new
			// InputStreamReader(responseStream));

			String line = reader.readLine();
			if (line.startsWith("{")) {
				Map<String, Object> jsonMap = JSON.parseObject(line, TreeMap.class);

				if (jsonMap.containsKey("error_code")) {
					error_code = formatString((String) jsonMap
							.get("error_code"));
					error = formatString((String) jsonMap.get("error"));
				} else {
					String dataFromYeepay = formatString((String) jsonMap
							.get("data"));
					String encryptkeyFromYeepay = formatString((String) jsonMap
							.get("encryptkey"));

					String yeepayAESKey = RSA.decrypt(encryptkeyFromYeepay,
							merchantPrivateKey);
					String decryptData = AES.decryptFromBase64(dataFromYeepay,
							yeepayAESKey);
					Map<String, Object> decryptDataMap = JSON.parseObject(decryptData, TreeMap.class);

					error_code = formatString((String) decryptDataMap
							.get("error_code"));
					error = formatString((String) decryptDataMap.get("error"));

					System.out.println("decryptData : " + decryptData);
				}
			} else {
				String outputFilePath = sysPath + File.separator + "clearData";
				File file = new File(outputFilePath);
				file.mkdir();

				String time = String.valueOf(System.currentTimeMillis());
				String fileName = "refundClearData_" + startdate + "_"
						+ enddate + "_" + time + ".txt";
				String absolutePathOfOutputFile = outputFilePath
						+ File.separator + fileName;
				filePath = absolutePathOfOutputFile;

				File outputFile = new File(absolutePathOfOutputFile);
				FileWriter fileWriter = new FileWriter(outputFile);
				BufferedWriter writer = new BufferedWriter(fileWriter);

				System.out.println("filePath : " + filePath);

				writer.write(line);
				writer.write(System.getProperty("line.separator"));
				while ((line = reader.readLine()) != null) {
					writer.write(line);
					writer.write(System.getProperty("line.separator"));
				}

				writer.close();
			}
		} catch (Exception e) {
			customError = "Caught an Exception. " + e.toString();
			e.printStackTrace();
		} finally {
			getMethod.releaseConnection();
		}

		queryResult.put("filePath", filePath);
		queryResult.put("error_code", error_code);
		queryResult.put("error", error);
		queryResult.put("customError", customError);

		return (queryResult);
	}

}
