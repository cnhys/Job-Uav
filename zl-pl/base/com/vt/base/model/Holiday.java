package com.vt.base.model;

import java.util.Date;

import com.vt.base.annotation.PrimaryKey;

public class Holiday {

    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column t_holiday.HOLIDAY_ID
     *
     * @mbggenerated Thu Jul 23 20:34:03 CST 2015
     */
    @PrimaryKey
    private Integer holidayId;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column t_holiday.DAY
     *
     * @mbggenerated Thu Jul 23 20:34:03 CST 2015
     */
    private String day;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column t_holiday.HOLIDAY_TYPE
     *
     * @mbggenerated Thu Jul 23 20:34:03 CST 2015
     */
    private String holidayType;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column t_holiday.CREATOR_ID
     *
     * @mbggenerated Thu Jul 23 20:34:03 CST 2015
     */
    private Integer creatorId;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column t_holiday.CREATE_TIME
     *
     * @mbggenerated Thu Jul 23 20:34:03 CST 2015
     */
    private Date createTime;

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column t_holiday.HOLIDAY_ID
     *
     * @return the value of t_holiday.HOLIDAY_ID
     * @mbggenerated Thu Jul 23 20:34:03 CST 2015
     */
    public Integer getHolidayId() {
        return holidayId;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column t_holiday.HOLIDAY_ID
     *
     * @param holidayId the value for t_holiday.HOLIDAY_ID
     * @mbggenerated Thu Jul 23 20:34:03 CST 2015
     */
    public void setHolidayId(Integer holidayId) {
        this.holidayId = holidayId;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column t_holiday.DAY
     *
     * @return the value of t_holiday.DAY
     * @mbggenerated Thu Jul 23 20:34:03 CST 2015
     */
    public String getDay() {
        return day;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column t_holiday.DAY
     *
     * @param day the value for t_holiday.DAY
     * @mbggenerated Thu Jul 23 20:34:03 CST 2015
     */
    public void setDay(String day) {
        this.day = day;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column t_holiday.HOLIDAY_TYPE
     *
     * @return the value of t_holiday.HOLIDAY_TYPE
     * @mbggenerated Thu Jul 23 20:34:03 CST 2015
     */
    public String getHolidayType() {
        return holidayType;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column t_holiday.HOLIDAY_TYPE
     *
     * @param holidayType the value for t_holiday.HOLIDAY_TYPE
     * @mbggenerated Thu Jul 23 20:34:03 CST 2015
     */
    public void setHolidayType(String holidayType) {
        this.holidayType = holidayType;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column t_holiday.CREATOR_ID
     *
     * @return the value of t_holiday.CREATOR_ID
     * @mbggenerated Thu Jul 23 20:34:03 CST 2015
     */
    public Integer getCreatorId() {
        return creatorId;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column t_holiday.CREATOR_ID
     *
     * @param creatorId the value for t_holiday.CREATOR_ID
     * @mbggenerated Thu Jul 23 20:34:03 CST 2015
     */
    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column t_holiday.CREATE_TIME
     *
     * @return the value of t_holiday.CREATE_TIME
     * @mbggenerated Thu Jul 23 20:34:03 CST 2015
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column t_holiday.CREATE_TIME
     *
     * @param createTime the value for t_holiday.CREATE_TIME
     * @mbggenerated Thu Jul 23 20:34:03 CST 2015
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}