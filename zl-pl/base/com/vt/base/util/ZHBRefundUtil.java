package com.vt.base.util;


import java.util.UUID;

import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConstants;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;
public class ZHBRefundUtil {
	
	/**
	 * main函数，实现非部署情况下退款（仅限于全额退款）
	 * @param args
	 */
	public static void main(String[] args) {
		
		zfbRefund1("N7739365089",0.01);//第一个参数是商户订单号，第二个参数是总金额（double型，以“元”为单位）
	}
	
	/**
	 * 退款函数，该方法可以对曾经部分退款的订单进行再次退款
	 * @param out_trade_no 商户订单号
	 * @param total_fee1 退款对应的订单的总金额（以“元”为单位）
	 * @param refund_fee1 计划退款的金额（以“元”为单位）
	 * @return
	 */
	public static void zfbRefund1(String trade_no,double refund_fee1){
		String out_refund_no = UUID.randomUUID().toString().substring(0, 32).replaceAll("-", "");// 退款单号，随机生成 ，但长度应该跟文档一样（32位）(卖家信息校验不一致，请核实后再试)
		
		//int total_fee = (int) (total_fee1);//订单的总金额,以分为单位（填错了貌似提示：同一个out_refund_no退款金额要一致）
		//int refund_fee = (int) (refund_fee1);;// 退款金额，以分为单位（填错了貌似提示：同一个out_refund_no退款金额要一致）
		
		//“开发者ID”
		String appId ="2017080908111299";
		//私钥
		String privateKey = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAKyyHIVlkPa3dSc7R04+zRtKyQbiVmxbksLKNKbsCMvfDIEanwMuEeHqRPPiWAz0bUJF8jWWrIn/wabmLnzoZZKHGNWJNfBp8X4U2smpPTZaUfSpUFMe0v1JgkN4Z0ssdDtX4GU5W30Tw1K1Lezi2Eg1Cbx546VihBEFivmUSo5nAgMBAAECgYB3t6F9+ELeRZ+5aqar321glh913kH7LCA5WOPXM5mFe0K33TSOh8lty3GxgB96G5gCcWrPK3zAzcP5Z2iivphqKxQ6cysac8qLZGjVs9s0cr5E6TIWdr2g/W7ZdkyJUN1MaeZaj4g/SXCqoYoUJ2BDVMVKkvZE3dMOHGJ008gbQQJBAP1Tqq+CZSgS5TOYHRjU5WBB7gclfdIpsyHFfaAXd85GXbt6hXLETkQy5lneWOPTfMZEFNpn8JfSfHCNjOqGWkMCQQCuhKEMm80T85u5ywYy0dfRV26WUT3r8fUexIhUPlZhV6ssOjaQQVw9grJOTT89m5EHIijFY64v8Kr9NTmCVxMNAkEAnred+GrFQC9ehU3n5Rf2QrHsFXQq+dMAlccrp3MssAOsVHs2Qyq1MsuFQYcqwEtQINSlEBYRSr4pE0fMo1/cCwJBAKUN3T+Ojywih5L12HvyCXr9hSL33Wes5FbgK8v5VTslbkgbGoXeuz2VwcvatUgwPL8RaJwfhZvjs+RdFXIXpnUCQQCt40DUgn+ET9Zs9fQKZoMTLFBjOjTNBrVcYjAtDOTuzbSZ8ipUfRt8x1kqKpucbHnV/73hAYL1G2TsgkmLXjK9";
		//公钥
		String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDI6d306Q8fIfCOaTXyiUeJHkrIvYISRcc73s3vF1ZT7XN8RNPwJxo8pWaJMmvyTn9N4HQ632qJBVHf8sxHi/fEsraprwCtzvzQETrNRwVxLO5jVmRGi60j8Ue1efIlzPXV9je9mkjzOmdssymZkh2QhUrCmZYI/FCEa3/cNMW0QIDAQAB";
		
		String createOrderURL = "https://openapi.alipay.com/gateway.do";
		
		try {
		    AlipayClient alipayClient = new DefaultAlipayClient(
		    		createOrderURL,appId,privateKey,"json",
		            "utf-8",publicKey,AlipayConstants.SIGN_TYPE_RSA);
		    AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
		    request.setBizContent("{" +
		            "    \"out_trade_no\":\""+trade_no+"\"," +
		            "    \"refund_amount\":"+refund_fee1+"," +
		            "    \"refund_reason\":\"正常退款\"," +
		            "    \"out_request_no\":\""+out_refund_no+"\"" +
		            "  }");
		    AlipayTradeRefundResponse response = alipayClient.execute(request);
		    if(response.isSuccess()){
		        //dealRefundSuccess(refundDetailModel,refundPrice,response.getTradeNo());
		    	System.out.println("退款成功：\n"+response.getTradeNo());
		    } else {
		    	System.out.println("退款失败：\n"+response.getSubMsg());
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
