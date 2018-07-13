package com.vt.base.model;

import java.util.Date;

import com.vt.base.annotation.PrimaryKey;

public class SystemMessage {

    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column t_system_message.MESSAGE_ID
     *
     * @mbggenerated Thu Jul 23 20:34:03 CST 2015
     */
    @PrimaryKey
    private Integer messageId;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column t_system_message.SENDER_ID
     *
     * @mbggenerated Thu Jul 23 20:34:03 CST 2015
     */
    private Integer senderId;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column t_system_message.RECEIVER_ID
     *
     * @mbggenerated Thu Jul 23 20:34:03 CST 2015
     */
    private Integer receiverId;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column t_system_message.RECEIVER_MOBILE
     *
     * @mbggenerated Thu Jul 23 20:34:03 CST 2015
     */
    private String receiverMobile;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column t_system_message.RECEIVER_EMAIL
     *
     * @mbggenerated Thu Jul 23 20:34:03 CST 2015
     */
    private String receiverEmail;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column t_system_message.MESSAGE_TYPE
     *
     * @mbggenerated Thu Jul 23 20:34:03 CST 2015
     */
    private String messageType;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column t_system_message.CHANNEL
     *
     * @mbggenerated Thu Jul 23 20:34:03 CST 2015
     */
    private String channel;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column t_system_message.CREATE_TIME
     *
     * @mbggenerated Thu Jul 23 20:34:03 CST 2015
     */
    private Date createTime;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column t_system_message.STATUS
     *
     * @mbggenerated Thu Jul 23 20:34:03 CST 2015
     */
    private String status;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column t_system_message.FAILURE_REASON
     *
     * @mbggenerated Thu Jul 23 20:34:03 CST 2015
     */
    private String failureReason;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column t_system_message.TITLE
     *
     * @mbggenerated Thu Jul 23 20:34:03 CST 2015
     */
    private String title;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column t_system_message.SENT_TIME
     *
     * @mbggenerated Thu Jul 23 20:34:03 CST 2015
     */
    private Date sentTime;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column t_system_message.CONTENT
     *
     * @mbggenerated Thu Jul 23 20:34:03 CST 2015
     */
    private String content;

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column t_system_message.MESSAGE_ID
     *
     * @return the value of t_system_message.MESSAGE_ID
     * @mbggenerated Thu Jul 23 20:34:03 CST 2015
     */
    public Integer getMessageId() {
        return messageId;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column t_system_message.MESSAGE_ID
     *
     * @param messageId the value for t_system_message.MESSAGE_ID
     * @mbggenerated Thu Jul 23 20:34:03 CST 2015
     */
    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column t_system_message.SENDER_ID
     *
     * @return the value of t_system_message.SENDER_ID
     * @mbggenerated Thu Jul 23 20:34:03 CST 2015
     */
    public Integer getSenderId() {
        return senderId;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column t_system_message.SENDER_ID
     *
     * @param senderId the value for t_system_message.SENDER_ID
     * @mbggenerated Thu Jul 23 20:34:03 CST 2015
     */
    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column t_system_message.RECEIVER_ID
     *
     * @return the value of t_system_message.RECEIVER_ID
     * @mbggenerated Thu Jul 23 20:34:03 CST 2015
     */
    public Integer getReceiverId() {
        return receiverId;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column t_system_message.RECEIVER_ID
     *
     * @param receiverId the value for t_system_message.RECEIVER_ID
     * @mbggenerated Thu Jul 23 20:34:03 CST 2015
     */
    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column t_system_message.RECEIVER_MOBILE
     *
     * @return the value of t_system_message.RECEIVER_MOBILE
     * @mbggenerated Thu Jul 23 20:34:03 CST 2015
     */
    public String getReceiverMobile() {
        return receiverMobile;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column t_system_message.RECEIVER_MOBILE
     *
     * @param receiverMobile the value for t_system_message.RECEIVER_MOBILE
     * @mbggenerated Thu Jul 23 20:34:03 CST 2015
     */
    public void setReceiverMobile(String receiverMobile) {
        this.receiverMobile = receiverMobile;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column t_system_message.RECEIVER_EMAIL
     *
     * @return the value of t_system_message.RECEIVER_EMAIL
     * @mbggenerated Thu Jul 23 20:34:03 CST 2015
     */
    public String getReceiverEmail() {
        return receiverEmail;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column t_system_message.RECEIVER_EMAIL
     *
     * @param receiverEmail the value for t_system_message.RECEIVER_EMAIL
     * @mbggenerated Thu Jul 23 20:34:03 CST 2015
     */
    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column t_system_message.MESSAGE_TYPE
     *
     * @return the value of t_system_message.MESSAGE_TYPE
     * @mbggenerated Thu Jul 23 20:34:03 CST 2015
     */
    public String getMessageType() {
        return messageType;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column t_system_message.MESSAGE_TYPE
     *
     * @param messageType the value for t_system_message.MESSAGE_TYPE
     * @mbggenerated Thu Jul 23 20:34:03 CST 2015
     */
    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column t_system_message.CHANNEL
     *
     * @return the value of t_system_message.CHANNEL
     * @mbggenerated Thu Jul 23 20:34:03 CST 2015
     */
    public String getChannel() {
        return channel;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column t_system_message.CHANNEL
     *
     * @param channel the value for t_system_message.CHANNEL
     * @mbggenerated Thu Jul 23 20:34:03 CST 2015
     */
    public void setChannel(String channel) {
        this.channel = channel;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column t_system_message.CREATE_TIME
     *
     * @return the value of t_system_message.CREATE_TIME
     * @mbggenerated Thu Jul 23 20:34:03 CST 2015
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column t_system_message.CREATE_TIME
     *
     * @param createTime the value for t_system_message.CREATE_TIME
     * @mbggenerated Thu Jul 23 20:34:03 CST 2015
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column t_system_message.STATUS
     *
     * @return the value of t_system_message.STATUS
     * @mbggenerated Thu Jul 23 20:34:03 CST 2015
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column t_system_message.STATUS
     *
     * @param status the value for t_system_message.STATUS
     * @mbggenerated Thu Jul 23 20:34:03 CST 2015
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column t_system_message.FAILURE_REASON
     *
     * @return the value of t_system_message.FAILURE_REASON
     * @mbggenerated Thu Jul 23 20:34:03 CST 2015
     */
    public String getFailureReason() {
        return failureReason;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column t_system_message.FAILURE_REASON
     *
     * @param failureReason the value for t_system_message.FAILURE_REASON
     * @mbggenerated Thu Jul 23 20:34:03 CST 2015
     */
    public void setFailureReason(String failureReason) {
        this.failureReason = failureReason;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column t_system_message.TITLE
     *
     * @return the value of t_system_message.TITLE
     * @mbggenerated Thu Jul 23 20:34:03 CST 2015
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column t_system_message.TITLE
     *
     * @param title the value for t_system_message.TITLE
     * @mbggenerated Thu Jul 23 20:34:03 CST 2015
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column t_system_message.SENT_TIME
     *
     * @return the value of t_system_message.SENT_TIME
     * @mbggenerated Thu Jul 23 20:34:03 CST 2015
     */
    public Date getSentTime() {
        return sentTime;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column t_system_message.SENT_TIME
     *
     * @param sentTime the value for t_system_message.SENT_TIME
     * @mbggenerated Thu Jul 23 20:34:03 CST 2015
     */
    public void setSentTime(Date sentTime) {
        this.sentTime = sentTime;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column t_system_message.CONTENT
     *
     * @return the value of t_system_message.CONTENT
     * @mbggenerated Thu Jul 23 20:34:03 CST 2015
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column t_system_message.CONTENT
     *
     * @param content the value for t_system_message.CONTENT
     * @mbggenerated Thu Jul 23 20:34:03 CST 2015
     */
    public void setContent(String content) {
        this.content = content;
    }
}