package com.vt.fencing.client;

import com.vt.base.mapper.IBaseMapper;
import com.vt.fencing.model.DmXzqh;
import com.vt.fencing.model.DmXzqhExample;

import java.util.List;
import java.util.Map;

public interface DmXzqhMapper extends IBaseMapper<DmXzqh, DmXzqhExample, Integer> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table DM_XZQH
     *
     * @mbggenerated Fri Aug 18 12:05:50 CST 2017
     */
    int countByExample(DmXzqhExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table DM_XZQH
     *
     * @mbggenerated Fri Aug 18 12:05:50 CST 2017
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table DM_XZQH
     *
     * @mbggenerated Fri Aug 18 12:05:50 CST 2017
     */
    int insert(DmXzqh record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table DM_XZQH
     *
     * @mbggenerated Fri Aug 18 12:05:50 CST 2017
     */
    int insertSelective(DmXzqh record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table DM_XZQH
     *
     * @mbggenerated Fri Aug 18 12:05:50 CST 2017
     */
    List<DmXzqh> selectByExample(DmXzqhExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table DM_XZQH
     *
     * @mbggenerated Fri Aug 18 12:05:50 CST 2017
     */
    DmXzqh selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table DM_XZQH
     *
     * @mbggenerated Fri Aug 18 12:05:50 CST 2017
     */
    int updateByPrimaryKeySelective(DmXzqh record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table DM_XZQH
     *
     * @mbggenerated Fri Aug 18 12:05:50 CST 2017
     */
    int updateByPrimaryKey(DmXzqh record);
    
    List<Map<String, Object>> queryCity(DmXzqh record);
}