package com.vt.fencing.model;

import java.util.Date;

public class ZlnfFriendsCircleComment {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_FRIENDSCIRCLECOMMENT.ID
     *
     * @mbggenerated Thu Jul 27 18:30:45 CST 2017
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_FRIENDSCIRCLECOMMENT.COMMENTCODE
     *
     * @mbggenerated Thu Jul 27 18:30:45 CST 2017
     */
    private String commentcode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_FRIENDSCIRCLECOMMENT.FRIENDCODE
     *
     * @mbggenerated Thu Jul 27 18:30:45 CST 2017
     */
    private String friendcode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_FRIENDSCIRCLECOMMENT.TYPE
     *
     * @mbggenerated Thu Jul 27 18:30:45 CST 2017
     */
    private String type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_FRIENDSCIRCLECOMMENT.CONTENT
     *
     * @mbggenerated Thu Jul 27 18:30:45 CST 2017
     */
    private String content;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_FRIENDSCIRCLECOMMENT.HEADPORTRAIT
     *
     * @mbggenerated Thu Jul 27 18:30:45 CST 2017
     */
    private String headportrait;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_FRIENDSCIRCLECOMMENT.CREATOR
     *
     * @mbggenerated Thu Jul 27 18:30:45 CST 2017
     */
    private String creator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_FRIENDSCIRCLECOMMENT.CREATORCODE
     *
     * @mbggenerated Thu Jul 27 18:30:45 CST 2017
     */
    private String creatorcode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_FRIENDSCIRCLECOMMENT.CREATEDONUTC
     *
     * @mbggenerated Thu Jul 27 18:30:45 CST 2017
     */
    private Date createdonutc;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_FRIENDSCIRCLECOMMENT.ISDELETED
     *
     * @mbggenerated Thu Jul 27 18:30:45 CST 2017
     */
    private String isdeleted;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_FRIENDSCIRCLECOMMENT.MODIFIER
     *
     * @mbggenerated Thu Jul 27 18:30:45 CST 2017
     */
    private String modifier;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_FRIENDSCIRCLECOMMENT.UPDATEDONUTC
     *
     * @mbggenerated Thu Jul 27 18:30:45 CST 2017
     */
    private Date updatedonutc;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_FRIENDSCIRCLECOMMENT.ID
     *
     * @return the value of ZLNF_FRIENDSCIRCLECOMMENT.ID
     *
     * @mbggenerated Thu Jul 27 18:30:45 CST 2017
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_FRIENDSCIRCLECOMMENT.ID
     *
     * @param id the value for ZLNF_FRIENDSCIRCLECOMMENT.ID
     *
     * @mbggenerated Thu Jul 27 18:30:45 CST 2017
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_FRIENDSCIRCLECOMMENT.COMMENTCODE
     *
     * @return the value of ZLNF_FRIENDSCIRCLECOMMENT.COMMENTCODE
     *
     * @mbggenerated Thu Jul 27 18:30:45 CST 2017
     */
    public String getCommentcode() {
        return commentcode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_FRIENDSCIRCLECOMMENT.COMMENTCODE
     *
     * @param commentcode the value for ZLNF_FRIENDSCIRCLECOMMENT.COMMENTCODE
     *
     * @mbggenerated Thu Jul 27 18:30:45 CST 2017
     */
    public void setCommentcode(String commentcode) {
        this.commentcode = commentcode == null ? null : commentcode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_FRIENDSCIRCLECOMMENT.FRIENDCODE
     *
     * @return the value of ZLNF_FRIENDSCIRCLECOMMENT.FRIENDCODE
     *
     * @mbggenerated Thu Jul 27 18:30:45 CST 2017
     */
    public String getFriendcode() {
        return friendcode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_FRIENDSCIRCLECOMMENT.FRIENDCODE
     *
     * @param friendcode the value for ZLNF_FRIENDSCIRCLECOMMENT.FRIENDCODE
     *
     * @mbggenerated Thu Jul 27 18:30:45 CST 2017
     */
    public void setFriendcode(String friendcode) {
        this.friendcode = friendcode == null ? null : friendcode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_FRIENDSCIRCLECOMMENT.TYPE
     *
     * @return the value of ZLNF_FRIENDSCIRCLECOMMENT.TYPE
     *
     * @mbggenerated Thu Jul 27 18:30:45 CST 2017
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_FRIENDSCIRCLECOMMENT.TYPE
     *
     * @param type the value for ZLNF_FRIENDSCIRCLECOMMENT.TYPE
     *
     * @mbggenerated Thu Jul 27 18:30:45 CST 2017
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_FRIENDSCIRCLECOMMENT.CONTENT
     *
     * @return the value of ZLNF_FRIENDSCIRCLECOMMENT.CONTENT
     *
     * @mbggenerated Thu Jul 27 18:30:45 CST 2017
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_FRIENDSCIRCLECOMMENT.CONTENT
     *
     * @param content the value for ZLNF_FRIENDSCIRCLECOMMENT.CONTENT
     *
     * @mbggenerated Thu Jul 27 18:30:45 CST 2017
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_FRIENDSCIRCLECOMMENT.HEADPORTRAIT
     *
     * @return the value of ZLNF_FRIENDSCIRCLECOMMENT.HEADPORTRAIT
     *
     * @mbggenerated Thu Jul 27 18:30:45 CST 2017
     */
    public String getHeadportrait() {
        return headportrait;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_FRIENDSCIRCLECOMMENT.HEADPORTRAIT
     *
     * @param headportrait the value for ZLNF_FRIENDSCIRCLECOMMENT.HEADPORTRAIT
     *
     * @mbggenerated Thu Jul 27 18:30:45 CST 2017
     */
    public void setHeadportrait(String headportrait) {
        this.headportrait = headportrait == null ? null : headportrait.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_FRIENDSCIRCLECOMMENT.CREATOR
     *
     * @return the value of ZLNF_FRIENDSCIRCLECOMMENT.CREATOR
     *
     * @mbggenerated Thu Jul 27 18:30:45 CST 2017
     */
    public String getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_FRIENDSCIRCLECOMMENT.CREATOR
     *
     * @param creator the value for ZLNF_FRIENDSCIRCLECOMMENT.CREATOR
     *
     * @mbggenerated Thu Jul 27 18:30:45 CST 2017
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_FRIENDSCIRCLECOMMENT.CREATORCODE
     *
     * @return the value of ZLNF_FRIENDSCIRCLECOMMENT.CREATORCODE
     *
     * @mbggenerated Thu Jul 27 18:30:45 CST 2017
     */
    public String getCreatorcode() {
        return creatorcode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_FRIENDSCIRCLECOMMENT.CREATORCODE
     *
     * @param creatorcode the value for ZLNF_FRIENDSCIRCLECOMMENT.CREATORCODE
     *
     * @mbggenerated Thu Jul 27 18:30:45 CST 2017
     */
    public void setCreatorcode(String creatorcode) {
        this.creatorcode = creatorcode == null ? null : creatorcode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_FRIENDSCIRCLECOMMENT.CREATEDONUTC
     *
     * @return the value of ZLNF_FRIENDSCIRCLECOMMENT.CREATEDONUTC
     *
     * @mbggenerated Thu Jul 27 18:30:45 CST 2017
     */
    public Date getCreatedonutc() {
        return createdonutc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_FRIENDSCIRCLECOMMENT.CREATEDONUTC
     *
     * @param createdonutc the value for ZLNF_FRIENDSCIRCLECOMMENT.CREATEDONUTC
     *
     * @mbggenerated Thu Jul 27 18:30:45 CST 2017
     */
    public void setCreatedonutc(Date createdonutc) {
        this.createdonutc = createdonutc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_FRIENDSCIRCLECOMMENT.ISDELETED
     *
     * @return the value of ZLNF_FRIENDSCIRCLECOMMENT.ISDELETED
     *
     * @mbggenerated Thu Jul 27 18:30:45 CST 2017
     */
    public String getIsdeleted() {
        return isdeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_FRIENDSCIRCLECOMMENT.ISDELETED
     *
     * @param isdeleted the value for ZLNF_FRIENDSCIRCLECOMMENT.ISDELETED
     *
     * @mbggenerated Thu Jul 27 18:30:45 CST 2017
     */
    public void setIsdeleted(String isdeleted) {
        this.isdeleted = isdeleted == null ? null : isdeleted.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_FRIENDSCIRCLECOMMENT.MODIFIER
     *
     * @return the value of ZLNF_FRIENDSCIRCLECOMMENT.MODIFIER
     *
     * @mbggenerated Thu Jul 27 18:30:45 CST 2017
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_FRIENDSCIRCLECOMMENT.MODIFIER
     *
     * @param modifier the value for ZLNF_FRIENDSCIRCLECOMMENT.MODIFIER
     *
     * @mbggenerated Thu Jul 27 18:30:45 CST 2017
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_FRIENDSCIRCLECOMMENT.UPDATEDONUTC
     *
     * @return the value of ZLNF_FRIENDSCIRCLECOMMENT.UPDATEDONUTC
     *
     * @mbggenerated Thu Jul 27 18:30:45 CST 2017
     */
    public Date getUpdatedonutc() {
        return updatedonutc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_FRIENDSCIRCLECOMMENT.UPDATEDONUTC
     *
     * @param updatedonutc the value for ZLNF_FRIENDSCIRCLECOMMENT.UPDATEDONUTC
     *
     * @mbggenerated Thu Jul 27 18:30:45 CST 2017
     */
    public void setUpdatedonutc(Date updatedonutc) {
        this.updatedonutc = updatedonutc;
    }
}