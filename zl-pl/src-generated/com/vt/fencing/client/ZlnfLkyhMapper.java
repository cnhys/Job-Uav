package com.vt.fencing.client;

import com.vt.base.mapper.IBaseMapper;
import com.vt.fencing.model.ZlnfLkyh;
import com.vt.fencing.model.ZlnfLkyhExample;
import java.util.List;

public interface ZlnfLkyhMapper extends IBaseMapper<ZlnfLkyh, ZlnfLkyhExample, Integer> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table VIEW_LKYH
     *
     * @mbggenerated Thu Nov 23 17:11:11 CST 2017
     */
    int countByExample(ZlnfLkyhExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table VIEW_LKYH
     *
     * @mbggenerated Thu Nov 23 17:11:11 CST 2017
     */
    int insert(ZlnfLkyh record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table VIEW_LKYH
     *
     * @mbggenerated Thu Nov 23 17:11:11 CST 2017
     */
    int insertSelective(ZlnfLkyh record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table VIEW_LKYH
     *
     * @mbggenerated Thu Nov 23 17:11:11 CST 2017
     */
    List<ZlnfLkyh> selectByExample(ZlnfLkyhExample example);
}