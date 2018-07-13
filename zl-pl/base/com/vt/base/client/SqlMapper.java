package com.vt.base.client;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.vt.base.mapper.IBaseMapper;


@SuppressWarnings("rawtypes")
public interface SqlMapper extends IBaseMapper {
    /**
     * 执行指定的sql语句
     *
     * @param value
     * @return
     */
    public List<Map> getBySql(String sql, RowBounds bounds);

    /**
     * 查询sql语句记录数
     *
     * @param sql
     * @return
     */
    public Integer getBySqlCount(String sql);

    public void insertBySql(String sql);

    public void updateBySql(String sql);

    public void deleteBySql(String sql);

}