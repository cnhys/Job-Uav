package com.vt.fencing.client;

import com.vt.base.mapper.IBaseMapper;
import com.vt.fencing.model.ZlnfFarmServiceMachiney;
import com.vt.fencing.model.ZlnfFarmServiceMachineyExample;

import java.util.List;

public interface ZlnfFarmServiceMachineyMapper extends IBaseMapper<ZlnfFarmServiceMachiney, ZlnfFarmServiceMachineyExample, Integer> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ZLNF_FARMSERVICEMACHINEY
     *
     * @mbggenerated Mon Jul 31 11:24:03 CST 2017
     */
    int countByExample(ZlnfFarmServiceMachineyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ZLNF_FARMSERVICEMACHINEY
     *
     * @mbggenerated Mon Jul 31 11:24:03 CST 2017
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ZLNF_FARMSERVICEMACHINEY
     *
     * @mbggenerated Mon Jul 31 11:24:03 CST 2017
     */
    int insert(ZlnfFarmServiceMachiney record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ZLNF_FARMSERVICEMACHINEY
     *
     * @mbggenerated Mon Jul 31 11:24:03 CST 2017
     */
    int insertSelective(ZlnfFarmServiceMachiney record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ZLNF_FARMSERVICEMACHINEY
     *
     * @mbggenerated Mon Jul 31 11:24:03 CST 2017
     */
    List<ZlnfFarmServiceMachiney> selectByExample(ZlnfFarmServiceMachineyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ZLNF_FARMSERVICEMACHINEY
     *
     * @mbggenerated Mon Jul 31 11:24:03 CST 2017
     */
    ZlnfFarmServiceMachiney selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ZLNF_FARMSERVICEMACHINEY
     *
     * @mbggenerated Mon Jul 31 11:24:03 CST 2017
     */
    int updateByPrimaryKeySelective(ZlnfFarmServiceMachiney record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ZLNF_FARMSERVICEMACHINEY
     *
     * @mbggenerated Mon Jul 31 11:24:03 CST 2017
     */
    int updateByPrimaryKey(ZlnfFarmServiceMachiney record);
}