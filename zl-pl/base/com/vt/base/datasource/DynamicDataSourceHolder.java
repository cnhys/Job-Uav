/**
 *
 */
package com.vt.base.datasource;

/**
 * dynamic datasource holder
 *
 * @author tony_zhang05
 * @version 1.0
 */
public class DynamicDataSourceHolder {
    public static final ThreadLocal<String> holder = new ThreadLocal<String>();

    /**
     * set datasource
     *
     * @param name
     */
    public static void setDataSource(String name) {
        holder.set(name);
    }

    /**
     * get datasource
     *
     * @return
     */
    public static String getDataSouce() {
        return holder.get();
    }
}
