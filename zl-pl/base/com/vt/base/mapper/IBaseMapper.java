/**
 *
 */
package com.vt.base.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

/**
 * <h1>Base Mapper for all staff</h1>
 *
 * @author Tony
 * @version 1.0
 */
public interface IBaseMapper<M, E, K> extends ISuperMapper {

    int countByExample(E example);

    int deleteByExample(E example);

    int deleteByPrimaryKey(K id);

    int insert(M record);

    int insertSelective(M record);

    List<M> selectByExample(E example);

    List<M> selectByExample(E example, RowBounds bounds);

    M selectByPrimaryKey(K logId);

    int updateByPrimaryKeySelective(M record);

    int updateByPrimaryKey(M record);
}