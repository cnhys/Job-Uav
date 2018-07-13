/**
 *
 */
package com.vt.base.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * dynamic data source
 *
 * @author tony_zhang05
 * @version 1.0
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    /* (non-Javadoc)
     * @see org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource#determineCurrentLookupKey()
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceHolder.getDataSouce();
    }
}
