package com.vt.base.service;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.session.RowBounds;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.vt.base.OptResult;
import com.vt.base.PageData;
import com.vt.base.PageRequest;
import com.vt.base.annotation.PrimaryKey;
import com.vt.base.exception.BizException;
import com.vt.base.mapper.IBaseMapper;
import com.vt.base.util.SpringContextHolder;

/**
 * <h1>基础服务</h1>
 * User: zhangtao
 * Date: 13-11-17
 * Time: 下午10:59
 */
public abstract class BaseService<M, E, K extends Serializable> implements IBaseService<M, E, K> {
    private static final long serialVersionUID = -1666364201802785958L;
    /**
     * 错误码定义
     */
    protected Properties errorCodes;

    /**
     * 初始化
     */
    protected void init() {
        try {
            errorCodes = PropertiesLoaderUtils.loadProperties(new ClassPathResource("error-code.properties"));
        } catch (IOException e) {
            // TODO 错误处理
        }
    }

    /**
     * 获取Mapper
     *
     * @return
     */
    public abstract IBaseMapper<M, E, K> getMapper();

    /**
     * 新增实体
     *
     * @param entity
     * @return
     */
    public OptResult create(M entity) {
        if (entity == null) {
            return new OptResult(false, "param.null");
        }

        Field[] fields = entity.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(PrimaryKey.class)) {
                Class<?> fieldType = field.getType();

                IDBUtilService dbUtilService = SpringContextHolder.getBean(IDBUtilService.class);
                Long pk = dbUtilService.getPrimaryKey(entity);

                Object pkValue = null;

                if (fieldType.isAssignableFrom(Long.class)) {
                    pkValue = pk;
                } else if (fieldType.isAssignableFrom(Integer.class)) {
                    pkValue = pk.intValue();
                } else if (fieldType.isAssignableFrom(BigDecimal.class)) {
                    pkValue = new BigDecimal(pk);
                }

                if (pkValue != null) {
                    try {
                        field.setAccessible(true);
                        field.set(entity, pkValue);
                    } catch (IllegalArgumentException e) {
                        throw new BizException("database.error");
                    } catch (IllegalAccessException e) {
                        throw new BizException("database.error");
                    }
                }
                break;
            }
        }

        int count = getMapper().insertSelective(entity);
        if (count > 0) {
            return new OptResult(true);
        } else {
            throw new BizException("database.error");
        }
    }

    /**
     * 更新实体
     *
     * @param entity
     * @return
     */
    public OptResult update(M entity) {
        if (entity == null) {
            return new OptResult(false, "param.null");
        }
        int count = getMapper().updateByPrimaryKeySelective(entity);
        if (count > 0) {
            return new OptResult(true);
        } else {
            throw new BizException("database.error");
        }
    }

    /**
     * 根据ID删除实体
     *
     * @param entityId
     * @return
     */
    public OptResult remove(K entityId) {
        if (entityId == null) {
            return new OptResult(false, "param.null");
        }
        int count = getMapper().deleteByPrimaryKey(entityId);
        if (count > 0) {
            return new OptResult(true);
        } else {
            throw new BizException("database.error");
        }
    }

    /**
     * 根据ID删除实体
     *
     * @param condition
     * @return
     */
    public OptResult delete(E condition) {
        if (condition == null) {
            throw new BizException("param.null");
        }
        try {
            getMapper().deleteByExample(condition);
            return OptResult.success();
        } catch (Exception e) {
        	e.printStackTrace();
            throw new BizException("database.error");
        }
    }

    /**
     * 根据ID获取实体
     *
     * @param entityId
     * @return
     */
    public M getById(K entityId) {
        if (entityId == null) {
            return null;
        }
        return getMapper().selectByPrimaryKey(entityId);
    }

    /**
     * 根据条件查询实体
     *
     * @param condition
     * @return
     */
    @SuppressWarnings("unchecked")
    public PageData<M> query(PageRequest<E> condition) {
        int count = getMapper().countByExample(condition.getCondition());
        List<M> data = null;
        if (count > 0) {
            data = getMapper().selectByExample(condition.getCondition(), new RowBounds(condition.getStart(), condition.getLimit()));
        } else {
            data = Collections.EMPTY_LIST;
        }
        PageData<M> result = new PageData<M>();
        result.setTotal(count);
        result.setStart(condition.getStart());
        result.setLimit(condition.getLimit());
        result.setData(data);
        result.setSuccess(true);
        return result;
    }

    @SuppressWarnings("unchecked")
    public List<M> getResult(E condition) {
        try {
            return getMapper().selectByExample(condition);
        } catch (Exception e) {
            // TODO 考虑异常处理机制
        }
        return Collections.EMPTY_LIST;
    }


    /**
     * 获取给定条件的结果数量
     *
     * @param condition
     * @return
     */
    public int getResultCount(E condition) {
        try {
            return getMapper().countByExample(condition);
        } catch (Exception e) {
            // TODO 考虑异常处理机制
        }
        return 0;
    }

    public Properties getErrorCodes() {
        return errorCodes;
    }

    public void setErrorCodes(Properties errorCodes) {
        this.errorCodes = errorCodes;
    }

    /**
     * 查找错误消息
     *
     * @param errorCode
     * @return
     */
    protected String getErrorMsg(String errorCode) {
        return getErrorCodes().getProperty(errorCode);
    }

    /**
     * 拒绝交易
     * @param errorCode
     */
    protected void reject(String errorCode) {
        throw new BizException(errorCode);
    }
}
