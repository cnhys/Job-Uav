package com.vt.base.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vt.base.OptResult;
import com.vt.base.PageData;
import com.vt.base.PageRequest;


/**
 * <h1>基础服务定义</h1>
 * User: zhangtao
 * Date: 13-11-24
 * Time: 上午10:03
 */
public interface IBaseService<M, E, K> extends Serializable {
    /**
     * 新增实体
     *
     * @param user
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    OptResult create(M entity);

    /**
     * 更新实体
     *
     * @param entity
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    OptResult update(M entity);

    /**
     * 根据ID删除实体
     *
     * @param entityId
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    OptResult remove(K entityId);

    /**
     * 根据套件删除实体
     *
     * @param condition
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    OptResult delete(E condition);

    /**
     * 根据ID获取实体
     *
     * @param entityId
     * @return
     */
    M getById(K entityId);

    /**
     * 根据条件查询实体
     *
     * @param condition
     * @return
     */
    PageData<M> query(PageRequest<E> condition);

    /**
     * 查询符合给定条件的实体列表
     *
     * @param condition
     * @return
     */
    List<M> getResult(E condition);

    /**
     * 查询符合给定条件的实体数量
     *
     * @param condition
     * @return
     */
    int getResultCount(E condition);
}
