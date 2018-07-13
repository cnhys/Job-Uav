package com.vt.base;

import java.io.Serializable;

/**
 * <h1>分页请求对象</h1>
 * User: zhangtao
 * Date: 13-11-19
 * Time: 下午8:06
 */
public class PageRequest<T> extends StatefullRequest implements Serializable {

    private static final long serialVersionUID = 3616821956154262259L;
    private int start;
    private int rows = 10;
    private int page;
    private T condition;

    /**
     * 默认构造
     */
    public PageRequest() {
    }

    public int getStart() {
        if (page <= 0) {
            return start;
        } else {
            return (page - 1) * rows;
        }
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLimit() {
        return rows;
    }

    public void setLimit(int limit) {
        this.rows = limit;
    }

    public T getCondition() {
        return condition;
    }

    public void setCondition(T condition) {
        this.condition = condition;
    }

    /**
     * @return the page
     */
    public int getPage() {
        return page;
    }

    /**
     * @param page the page to set
     */
    public void setPage(int page) {
        this.page = page;
    }

    /**
     * @return the rows
     */
    public int getRows() {
        return rows;
    }

    /**
     * @param rows the rows to set
     */
    public void setRows(int rows) {
        this.rows = rows;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PageRequest)) return false;

        @SuppressWarnings("rawtypes")
        PageRequest that = (PageRequest) o;

        if (rows != that.rows) return false;
        if (start != that.start) return false;
        if (condition != null ? !condition.equals(that.condition) : that.condition != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = start;
        result = 31 * result + rows;
        result = 31 * result + (condition != null ? condition.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PageRequest{" +
                "start=" + start +
                ", page=" + page +
                ", rows=" + rows +
                ", condition=" + condition +
                '}';
    }
}
