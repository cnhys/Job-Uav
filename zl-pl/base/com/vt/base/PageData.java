package com.vt.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.vt.IConst;


/**
 * <h1>分页数据</h1>
 * User: zhangtao
 * Date: 13-11-17
 * Time: 下午10:09
 */
public class PageData<T> extends OptResult implements Serializable {

    private static final long serialVersionUID = -1567448978335212151L;
    private int start;
    private int limit;
    private int total;
    private List<T> data;

    /**
     * 默认构造
     */
    public PageData() {
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public List<T> getData() {
        return data;
    }

    public List<T> getRows() {
        return data;
    }

    public void setData(List<T> data) {
    	if(data == null){
    		data = new ArrayList<>();
    	}
        this.data = data;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PageData)) return false;

        @SuppressWarnings("rawtypes")
        PageData pageData = (PageData) o;

        if (limit != pageData.limit) return false;
        if (start != pageData.start) return false;
        if (total != pageData.total) return false;
        if (data != null ? !data.equals(pageData.data) : pageData.data != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = start;
        result = 31 * result + limit;
        result = 31 * result + total;
        result = 31 * result + (data != null ? data.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PageData{" +
                "start=" + start +
                ", limit=" + limit +
                ", total=" + total +
                ", data=" + data +
                '}';
    }

    /**
     * 获取总页码
     *
     * @return
     */
    public int getTotalPage() {
        int pageTemp = total / IConst.PAGE_SIZE;
        int totalPage = total % IConst.PAGE_SIZE == 0 ? pageTemp : pageTemp + 1;
        return totalPage;
    }

    /**
     * 获取当前页码
     *
     * @return
     */
    public int getCurrentPage() {
        return start / IConst.PAGE_SIZE + 1;
    }
}
