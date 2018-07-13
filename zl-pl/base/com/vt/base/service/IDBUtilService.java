package com.vt.base.service;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vt.base.model.PrimaryKeyTable;
import com.vt.base.model.PrimaryKeyTableExample;


/**
 * @author july
 * @version V1.0
 * @Title: IDBUtilService.java
 * @Package com.infosys.base.service
 * @Description: TODO
 * @date May 28, 2015 11:41:36 AM
 */

public interface IDBUtilService extends IBaseService<PrimaryKeyTable, PrimaryKeyTableExample, Integer> {
    /**
     * @param 根据传入的数据实体获取主键ID
     * @return 主键ID
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    public Long getPrimaryKey(Object obj);

}
