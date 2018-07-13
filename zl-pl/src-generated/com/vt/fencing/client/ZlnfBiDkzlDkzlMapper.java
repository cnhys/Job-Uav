package com.vt.fencing.client;

import com.vt.base.mapper.IBaseMapper;
import com.vt.fencing.model.ZlnfBiDkzlDkzl;
import com.vt.fencing.model.ZlnfBiDkzlDkzlExample;
import java.util.List;

public interface ZlnfBiDkzlDkzlMapper extends IBaseMapper<ZlnfBiDkzlDkzl, ZlnfBiDkzlDkzlExample, Integer> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BI_DKZL_DKZL
     *
     * @mbggenerated Fri Nov 24 15:11:32 CST 2017
     */
    int countByExample(ZlnfBiDkzlDkzlExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BI_DKZL_DKZL
     *
     * @mbggenerated Fri Nov 24 15:11:32 CST 2017
     */
    int insert(ZlnfBiDkzlDkzl record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BI_DKZL_DKZL
     *
     * @mbggenerated Fri Nov 24 15:11:32 CST 2017
     */
    int insertSelective(ZlnfBiDkzlDkzl record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BI_DKZL_DKZL
     *
     * @mbggenerated Fri Nov 24 15:11:32 CST 2017
     */
    List<ZlnfBiDkzlDkzl> selectByExample(ZlnfBiDkzlDkzlExample example);
}