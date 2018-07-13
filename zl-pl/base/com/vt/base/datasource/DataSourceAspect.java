/**
 *
 */
package com.vt.base.datasource;

import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * @author Tony_Zhang05
 * @version 1.0
 */
public class DataSourceAspect {
    /**
     * data source keys
     */
    private String[] datasourceKeys;

    public void before(JoinPoint point) {
        // get transaction related information
        String transactionName = TransactionSynchronizationManager.getCurrentTransactionName();
        Integer isolationLevel = TransactionSynchronizationManager.getCurrentTransactionIsolationLevel();
        if (StringUtils.isNotBlank(transactionName)) {// no transaction exists
            // check transaction isolation level
            if (isolationLevel >= TransactionDefinition.ISOLATION_SERIALIZABLE) {// read committed
                DynamicDataSourceHolder.setDataSource("master");
                return;
            }
        }
        // set datasource type
        DynamicDataSourceHolder.setDataSource(calculate());
    }

    /**
     * calculate which key to use
     *
     * @return
     */
    private String calculate() {
        Random random = new Random();
        int index = random.nextInt(datasourceKeys.length);
        return datasourceKeys[index];
    }

    /**
     * set datasource keys
     *
     * @param datasourceKeys
     */
    public void setDatasourceKeys(String[] datasourceKeys) {
        this.datasourceKeys = datasourceKeys;
    }
}
