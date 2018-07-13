package com.vt.base.service;

/**
 * 交易码服务
 * Created by zhangtao on 16/1/9.
 */
public interface ITradeNoService {
    /**
     * 生成交易批次号
     * @return
     */
    Integer generateTxNo();
    /**
     * 生成交易流水号
     * @param prefix
     * @param length
     * @return
     */
    String generateFlowNumber(String prefix, int length);
}
