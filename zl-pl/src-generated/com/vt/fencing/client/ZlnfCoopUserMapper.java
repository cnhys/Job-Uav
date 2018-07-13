package com.vt.fencing.client;

import com.vt.base.mapper.IBaseMapper;
import com.vt.fencing.model.ZlnfCoopUser;
import com.vt.fencing.model.ZlnfCoopUserExample;

import java.util.List;

public interface ZlnfCoopUserMapper extends IBaseMapper<ZlnfCoopUser, ZlnfCoopUserExample, Integer> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ZLNF_COOPUSER
     *
     * @mbggenerated Tue Jul 18 16:17:31 CST 2017
     */
    int countByExample(ZlnfCoopUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ZLNF_COOPUSER
     *
     * @mbggenerated Tue Jul 18 16:17:32 CST 2017
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ZLNF_COOPUSER
     *
     * @mbggenerated Tue Jul 18 16:17:32 CST 2017
     */
    int insert(ZlnfCoopUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ZLNF_COOPUSER
     *
     * @mbggenerated Tue Jul 18 16:17:32 CST 2017
     */
    int insertSelective(ZlnfCoopUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ZLNF_COOPUSER
     *
     * @mbggenerated Tue Jul 18 16:17:32 CST 2017
     */
    List<ZlnfCoopUser> selectByExample(ZlnfCoopUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ZLNF_COOPUSER
     *
     * @mbggenerated Tue Jul 18 16:17:32 CST 2017
     */
    ZlnfCoopUser selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ZLNF_COOPUSER
     *
     * @mbggenerated Tue Jul 18 16:17:32 CST 2017
     */
    int updateByPrimaryKeySelective(ZlnfCoopUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ZLNF_COOPUSER
     *
     * @mbggenerated Tue Jul 18 16:17:32 CST 2017
     */
    int updateByPrimaryKey(ZlnfCoopUser record);
    
    ZlnfCoopUser queryroletype(ZlnfCoopUser record);
}