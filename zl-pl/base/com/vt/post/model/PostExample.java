package com.vt.post.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class PostExample {
    protected String orderByClause;
    protected boolean distinct;
    protected List<Criteria> oredCriteria;

    public PostExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria and() {
        if (oredCriteria.size() == 0) {
            Criteria criteria = createCriteriaInternal();
            oredCriteria.add(criteria);
        }
        return oredCriteria.get(0);
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        public void addCriterion(String condition, Object value,
                                 String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property
                        + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1,
                                    Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property
                        + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        protected void addCriterionForJDBCDate(String condition, Date value,
                                               String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property
                        + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()),
                    property);
        }

        protected void addCriterionForJDBCDate(String condition,
                                               List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property
                        + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1,
                                               Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property
                        + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()),
                    new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andPostIdIsNull() {
            addCriterion("POST_ID is null");
            return (Criteria) this;
        }

        public Criteria andPostIdIsNotNull() {
            addCriterion("POST_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPostIdEqualTo(Integer value) {
            addCriterion("POST_ID =", value, "postId");
            return (Criteria) this;
        }

        public Criteria andPostIdNotEqualTo(Integer value) {
            addCriterion("POST_ID <>", value, "postId");
            return (Criteria) this;
        }

        public Criteria andPostIdGreaterThan(Integer value) {
            addCriterion("POST_ID >", value, "postId");
            return (Criteria) this;
        }

        public Criteria andPostIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("POST_ID >=", value, "postId");
            return (Criteria) this;
        }

        public Criteria andPostIdLessThan(Integer value) {
            addCriterion("POST_ID <", value, "postId");
            return (Criteria) this;
        }

        public Criteria andPostIdLessThanOrEqualTo(Integer value) {
            addCriterion("POST_ID <=", value, "postId");
            return (Criteria) this;
        }

        public Criteria andPostIdIn(List<Integer> values) {
            addCriterion("POST_ID in", values, "postId");
            return (Criteria) this;
        }

        public Criteria andPostIdNotIn(List<Integer> values) {
            addCriterion("POST_ID not in", values, "postId");
            return (Criteria) this;
        }

        public Criteria andPostIdBetween(Integer value1, Integer value2) {
            addCriterion("POST_ID between", value1, value2, "postId");
            return (Criteria) this;
        }

        public Criteria andPostIdNotBetween(Integer value1, Integer value2) {
            addCriterion("POST_ID not between", value1, value2, "postId");
            return (Criteria) this;
        }

        public Criteria andPostCodeIsNull() {
            addCriterion("POST_CODE is null");
            return (Criteria) this;
        }

        public Criteria andPostCodeIsNotNull() {
            addCriterion("POST_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andPostCodeEqualTo(String value) {
            addCriterion("POST_CODE =", value, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeNotEqualTo(String value) {
            addCriterion("POST_CODE <>", value, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeGreaterThan(String value) {
            addCriterion("POST_CODE >", value, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeGreaterThanOrEqualTo(String value) {
            addCriterion("POST_CODE >=", value, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeLessThan(String value) {
            addCriterion("POST_CODE <", value, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeLessThanOrEqualTo(String value) {
            addCriterion("POST_CODE <=", value, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeLike(String value) {
            addCriterion("POST_CODE like", value, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeNotLike(String value) {
            addCriterion("POST_CODE not like", value, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeIn(List<String> values) {
            addCriterion("POST_CODE in", values, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeNotIn(List<String> values) {
            addCriterion("POST_CODE not in", values, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeBetween(String value1, String value2) {
            addCriterion("POST_CODE between", value1, value2, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeNotBetween(String value1, String value2) {
            addCriterion("POST_CODE not between", value1, value2, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostNameIsNull() {
            addCriterion("POST_NAME is null");
            return (Criteria) this;
        }

        public Criteria andPostNameIsNotNull() {
            addCriterion("POST_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andPostNameEqualTo(String value) {
            addCriterion("POST_NAME =", value, "postName");
            return (Criteria) this;
        }

        public Criteria andPostNameNotEqualTo(String value) {
            addCriterion("POST_NAME <>", value, "postName");
            return (Criteria) this;
        }

        public Criteria andPostNameGreaterThan(String value) {
            addCriterion("POST_NAME >", value, "postName");
            return (Criteria) this;
        }

        public Criteria andPostNameGreaterThanOrEqualTo(String value) {
            addCriterion("POST_NAME >=", value, "postName");
            return (Criteria) this;
        }

        public Criteria andPostNameLessThan(String value) {
            addCriterion("POST_NAME <", value, "postName");
            return (Criteria) this;
        }

        public Criteria andPostNameLessThanOrEqualTo(String value) {
            addCriterion("POST_NAME <=", value, "postName");
            return (Criteria) this;
        }

        public Criteria andPostNameLike(String value) {
            addCriterion("POST_NAME like", value, "postName");
            return (Criteria) this;
        }

        public Criteria andPostNameNotLike(String value) {
            addCriterion("POST_NAME not like", value, "postName");
            return (Criteria) this;
        }

        public Criteria andPostNameIn(List<String> values) {
            addCriterion("POST_NAME in", values, "postName");
            return (Criteria) this;
        }

        public Criteria andPostNameNotIn(List<String> values) {
            addCriterion("POST_NAME not in", values, "postName");
            return (Criteria) this;
        }

        public Criteria andPostNameBetween(String value1, String value2) {
            addCriterion("POST_NAME between", value1, value2, "postName");
            return (Criteria) this;
        }

        public Criteria andPostNameNotBetween(String value1, String value2) {
            addCriterion("POST_NAME not between", value1, value2, "postName");
            return (Criteria) this;
        }

        public Criteria andPostLevelIsNull() {
            addCriterion("POST_LEVEL is null");
            return (Criteria) this;
        }

        public Criteria andPostLevelIsNotNull() {
            addCriterion("POST_LEVEL is not null");
            return (Criteria) this;
        }

        public Criteria andPostLevelEqualTo(String value) {
            addCriterion("POST_LEVEL =", value, "postLevel");
            return (Criteria) this;
        }

        public Criteria andPostLevelNotEqualTo(String value) {
            addCriterion("POST_LEVEL <>", value, "postLevel");
            return (Criteria) this;
        }

        public Criteria andPostLevelGreaterThan(String value) {
            addCriterion("POST_LEVEL >", value, "postLevel");
            return (Criteria) this;
        }

        public Criteria andPostLevelGreaterThanOrEqualTo(String value) {
            addCriterion("POST_LEVEL >=", value, "postLevel");
            return (Criteria) this;
        }

        public Criteria andPostLevelLessThan(String value) {
            addCriterion("POST_LEVEL <", value, "postLevel");
            return (Criteria) this;
        }

        public Criteria andPostLevelLessThanOrEqualTo(String value) {
            addCriterion("POST_LEVEL <=", value, "postLevel");
            return (Criteria) this;
        }

        public Criteria andPostLevelLike(String value) {
            addCriterion("POST_LEVEL like", value, "postLevel");
            return (Criteria) this;
        }

        public Criteria andPostLevelNotLike(String value) {
            addCriterion("POST_LEVEL not like", value, "postLevel");
            return (Criteria) this;
        }

        public Criteria andPostLevelIn(List<String> values) {
            addCriterion("POST_LEVEL in", values, "postLevel");
            return (Criteria) this;
        }

        public Criteria andPostLevelNotIn(List<String> values) {
            addCriterion("POST_LEVEL not in", values, "postLevel");
            return (Criteria) this;
        }

        public Criteria andPostLevelBetween(String value1, String value2) {
            addCriterion("POST_LEVEL between", value1, value2, "postLevel");
            return (Criteria) this;
        }

        public Criteria andPostLevelNotBetween(String value1, String value2) {
            addCriterion("POST_LEVEL not between", value1, value2, "postLevel");
            return (Criteria) this;
        }

        public Criteria andHigherPostIdIsNull() {
            addCriterion("HIGHER_POST_ID is null");
            return (Criteria) this;
        }

        public Criteria andHigherPostIdIsNotNull() {
            addCriterion("HIGHER_POST_ID is not null");
            return (Criteria) this;
        }

        public Criteria andHigherPostIdEqualTo(Integer value) {
            addCriterion("HIGHER_POST_ID =", value, "higherPostId");
            return (Criteria) this;
        }

        public Criteria andHigherPostIdNotEqualTo(Integer value) {
            addCriterion("HIGHER_POST_ID <>", value, "higherPostId");
            return (Criteria) this;
        }

        public Criteria andHigherPostIdGreaterThan(Integer value) {
            addCriterion("HIGHER_POST_ID >", value, "higherPostId");
            return (Criteria) this;
        }

        public Criteria andHigherPostIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("HIGHER_POST_ID >=", value, "higherPostId");
            return (Criteria) this;
        }

        public Criteria andHigherPostIdLessThan(Integer value) {
            addCriterion("HIGHER_POST_ID <", value, "higherPostId");
            return (Criteria) this;
        }

        public Criteria andHigherPostIdLessThanOrEqualTo(Integer value) {
            addCriterion("HIGHER_POST_ID <=", value, "higherPostId");
            return (Criteria) this;
        }

        public Criteria andHigherPostIdIn(List<Integer> values) {
            addCriterion("HIGHER_POST_ID in", values, "higherPostId");
            return (Criteria) this;
        }

        public Criteria andHigherPostIdNotIn(List<Integer> values) {
            addCriterion("HIGHER_POST_ID not in", values, "higherPostId");
            return (Criteria) this;
        }

        public Criteria andHigherPostIdBetween(Integer value1,
                                               Integer value2) {
            addCriterion("HIGHER_POST_ID between", value1, value2,
                    "higherPostId");
            return (Criteria) this;
        }

        public Criteria andHigherPostIdNotBetween(Integer value1,
                                                  Integer value2) {
            addCriterion("HIGHER_POST_ID not between", value1, value2,
                    "higherPostId");
            return (Criteria) this;
        }

        public Criteria andBusOrgCodeIsNull() {
            addCriterion("BUS_ORG_CODE is null");
            return (Criteria) this;
        }

        public Criteria andBusOrgCodeIsNotNull() {
            addCriterion("BUS_ORG_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andBusOrgCodeEqualTo(Integer value) {
            addCriterion("BUS_ORG_CODE =", value, "busOrgCode");
            return (Criteria) this;
        }

        public Criteria andBusOrgCodeNotEqualTo(Integer value) {
            addCriterion("BUS_ORG_CODE <>", value, "busOrgCode");
            return (Criteria) this;
        }

        public Criteria andBusOrgCodeGreaterThan(Integer value) {
            addCriterion("BUS_ORG_CODE >", value, "busOrgCode");
            return (Criteria) this;
        }

        public Criteria andBusOrgCodeGreaterThanOrEqualTo(Integer value) {
            addCriterion("BUS_ORG_CODE >=", value, "busOrgCode");
            return (Criteria) this;
        }

        public Criteria andBusOrgCodeLessThan(Integer value) {
            addCriterion("BUS_ORG_CODE <", value, "busOrgCode");
            return (Criteria) this;
        }

        public Criteria andBusOrgCodeLessThanOrEqualTo(Integer value) {
            addCriterion("BUS_ORG_CODE <=", value, "busOrgCode");
            return (Criteria) this;
        }

        public Criteria andBusOrgCodeIn(List<Integer> values) {
            addCriterion("BUS_ORG_CODE in", values, "busOrgCode");
            return (Criteria) this;
        }

        public Criteria andBusOrgCodeNotIn(List<Integer> values) {
            addCriterion("BUS_ORG_CODE not in", values, "busOrgCode");
            return (Criteria) this;
        }

        public Criteria andBusOrgCodeBetween(Integer value1,
                                             Integer value2) {
            addCriterion("BUS_ORG_CODE between", value1, value2, "busOrgCode");
            return (Criteria) this;
        }

        public Criteria andBusOrgCodeNotBetween(Integer value1,
                                                Integer value2) {
            addCriterion("BUS_ORG_CODE not between", value1, value2,
                    "busOrgCode");
            return (Criteria) this;
        }

        public Criteria andUnderOrgCodeIsNull() {
            addCriterion("UNDER_ORG_CODE is null");
            return (Criteria) this;
        }

        public Criteria andUnderOrgCodeIsNotNull() {
            addCriterion("UNDER_ORG_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andUnderOrgCodeEqualTo(Integer value) {
            addCriterion("UNDER_ORG_CODE =", value, "underOrgCode");
            return (Criteria) this;
        }

        public Criteria andUnderOrgCodeNotEqualTo(Integer value) {
            addCriterion("UNDER_ORG_CODE <>", value, "underOrgCode");
            return (Criteria) this;
        }

        public Criteria andUnderOrgCodeGreaterThan(Integer value) {
            addCriterion("UNDER_ORG_CODE >", value, "underOrgCode");
            return (Criteria) this;
        }

        public Criteria andUnderOrgCodeGreaterThanOrEqualTo(Integer value) {
            addCriterion("UNDER_ORG_CODE >=", value, "underOrgCode");
            return (Criteria) this;
        }

        public Criteria andUnderOrgCodeLessThan(Integer value) {
            addCriterion("UNDER_ORG_CODE <", value, "underOrgCode");
            return (Criteria) this;
        }

        public Criteria andUnderOrgCodeLessThanOrEqualTo(Integer value) {
            addCriterion("UNDER_ORG_CODE <=", value, "underOrgCode");
            return (Criteria) this;
        }

        public Criteria andUnderOrgCodeIn(List<Integer> values) {
            addCriterion("UNDER_ORG_CODE in", values, "underOrgCode");
            return (Criteria) this;
        }

        public Criteria andUnderOrgCodeNotIn(List<Integer> values) {
            addCriterion("UNDER_ORG_CODE not in", values, "underOrgCode");
            return (Criteria) this;
        }

        public Criteria andUnderOrgCodeBetween(Integer value1,
                                               Integer value2) {
            addCriterion("UNDER_ORG_CODE between", value1, value2,
                    "underOrgCode");
            return (Criteria) this;
        }

        public Criteria andUnderOrgCodeNotBetween(Integer value1,
                                                  Integer value2) {
            addCriterion("UNDER_ORG_CODE not between", value1, value2,
                    "underOrgCode");
            return (Criteria) this;
        }

        public Criteria andPostSerialnoIsNull() {
            addCriterion("POST_SERIALNO is null");
            return (Criteria) this;
        }

        public Criteria andPostSerialnoIsNotNull() {
            addCriterion("POST_SERIALNO is not null");
            return (Criteria) this;
        }

        public Criteria andPostSerialnoEqualTo(Integer value) {
            addCriterion("POST_SERIALNO =", value, "postSerialno");
            return (Criteria) this;
        }

        public Criteria andPostSerialnoNotEqualTo(Integer value) {
            addCriterion("POST_SERIALNO <>", value, "postSerialno");
            return (Criteria) this;
        }

        public Criteria andPostSerialnoGreaterThan(Integer value) {
            addCriterion("POST_SERIALNO >", value, "postSerialno");
            return (Criteria) this;
        }

        public Criteria andPostSerialnoGreaterThanOrEqualTo(Integer value) {
            addCriterion("POST_SERIALNO >=", value, "postSerialno");
            return (Criteria) this;
        }

        public Criteria andPostSerialnoLessThan(Integer value) {
            addCriterion("POST_SERIALNO <", value, "postSerialno");
            return (Criteria) this;
        }

        public Criteria andPostSerialnoLessThanOrEqualTo(Integer value) {
            addCriterion("POST_SERIALNO <=", value, "postSerialno");
            return (Criteria) this;
        }

        public Criteria andPostSerialnoIn(List<Integer> values) {
            addCriterion("POST_SERIALNO in", values, "postSerialno");
            return (Criteria) this;
        }

        public Criteria andPostSerialnoNotIn(List<Integer> values) {
            addCriterion("POST_SERIALNO not in", values, "postSerialno");
            return (Criteria) this;
        }

        public Criteria andPostSerialnoBetween(Integer value1,
                                               Integer value2) {
            addCriterion("POST_SERIALNO between", value1, value2,
                    "postSerialno");
            return (Criteria) this;
        }

        public Criteria andPostSerialnoNotBetween(Integer value1,
                                                  Integer value2) {
            addCriterion("POST_SERIALNO not between", value1, value2,
                    "postSerialno");
            return (Criteria) this;
        }

        public Criteria andPostTypeIsNull() {
            addCriterion("POST_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andPostTypeIsNotNull() {
            addCriterion("POST_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andPostTypeEqualTo(String value) {
            addCriterion("POST_TYPE =", value, "postType");
            return (Criteria) this;
        }

        public Criteria andPostTypeNotEqualTo(String value) {
            addCriterion("POST_TYPE <>", value, "postType");
            return (Criteria) this;
        }

        public Criteria andPostTypeGreaterThan(String value) {
            addCriterion("POST_TYPE >", value, "postType");
            return (Criteria) this;
        }

        public Criteria andPostTypeGreaterThanOrEqualTo(String value) {
            addCriterion("POST_TYPE >=", value, "postType");
            return (Criteria) this;
        }

        public Criteria andPostTypeLessThan(String value) {
            addCriterion("POST_TYPE <", value, "postType");
            return (Criteria) this;
        }

        public Criteria andPostTypeLessThanOrEqualTo(String value) {
            addCriterion("POST_TYPE <=", value, "postType");
            return (Criteria) this;
        }

        public Criteria andPostTypeLike(String value) {
            addCriterion("POST_TYPE like", value, "postType");
            return (Criteria) this;
        }

        public Criteria andPostTypeNotLike(String value) {
            addCriterion("POST_TYPE not like", value, "postType");
            return (Criteria) this;
        }

        public Criteria andPostTypeIn(List<String> values) {
            addCriterion("POST_TYPE in", values, "postType");
            return (Criteria) this;
        }

        public Criteria andPostTypeNotIn(List<String> values) {
            addCriterion("POST_TYPE not in", values, "postType");
            return (Criteria) this;
        }

        public Criteria andPostTypeBetween(String value1, String value2) {
            addCriterion("POST_TYPE between", value1, value2, "postType");
            return (Criteria) this;
        }

        public Criteria andPostTypeNotBetween(String value1, String value2) {
            addCriterion("POST_TYPE not between", value1, value2, "postType");
            return (Criteria) this;
        }

        public Criteria andPostStartDateIsNull() {
            addCriterion("POST_START_DATE is null");
            return (Criteria) this;
        }

        public Criteria andPostStartDateIsNotNull() {
            addCriterion("POST_START_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andPostStartDateEqualTo(Date value) {
            addCriterionForJDBCDate("POST_START_DATE =", value, "postStartDate");
            return (Criteria) this;
        }

        public Criteria andPostStartDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("POST_START_DATE <>", value,
                    "postStartDate");
            return (Criteria) this;
        }

        public Criteria andPostStartDateGreaterThan(Date value) {
            addCriterionForJDBCDate("POST_START_DATE >", value, "postStartDate");
            return (Criteria) this;
        }

        public Criteria andPostStartDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("POST_START_DATE >=", value,
                    "postStartDate");
            return (Criteria) this;
        }

        public Criteria andPostStartDateLessThan(Date value) {
            addCriterionForJDBCDate("POST_START_DATE <", value, "postStartDate");
            return (Criteria) this;
        }

        public Criteria andPostStartDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("POST_START_DATE <=", value,
                    "postStartDate");
            return (Criteria) this;
        }

        public Criteria andPostStartDateIn(List<Date> values) {
            addCriterionForJDBCDate("POST_START_DATE in", values,
                    "postStartDate");
            return (Criteria) this;
        }

        public Criteria andPostStartDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("POST_START_DATE not in", values,
                    "postStartDate");
            return (Criteria) this;
        }

        public Criteria andPostStartDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("POST_START_DATE between", value1, value2,
                    "postStartDate");
            return (Criteria) this;
        }

        public Criteria andPostStartDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("POST_START_DATE not between", value1,
                    value2, "postStartDate");
            return (Criteria) this;
        }

        public Criteria andPostEndDateIsNull() {
            addCriterion("POST_END_DATE is null");
            return (Criteria) this;
        }

        public Criteria andPostEndDateIsNotNull() {
            addCriterion("POST_END_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andPostEndDateEqualTo(Date value) {
            addCriterionForJDBCDate("POST_END_DATE =", value, "postEndDate");
            return (Criteria) this;
        }

        public Criteria andPostEndDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("POST_END_DATE <>", value, "postEndDate");
            return (Criteria) this;
        }

        public Criteria andPostEndDateGreaterThan(Date value) {
            addCriterionForJDBCDate("POST_END_DATE >", value, "postEndDate");
            return (Criteria) this;
        }

        public Criteria andPostEndDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("POST_END_DATE >=", value, "postEndDate");
            return (Criteria) this;
        }

        public Criteria andPostEndDateLessThan(Date value) {
            addCriterionForJDBCDate("POST_END_DATE <", value, "postEndDate");
            return (Criteria) this;
        }

        public Criteria andPostEndDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("POST_END_DATE <=", value, "postEndDate");
            return (Criteria) this;
        }

        public Criteria andPostEndDateIn(List<Date> values) {
            addCriterionForJDBCDate("POST_END_DATE in", values, "postEndDate");
            return (Criteria) this;
        }

        public Criteria andPostEndDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("POST_END_DATE not in", values,
                    "postEndDate");
            return (Criteria) this;
        }

        public Criteria andPostEndDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("POST_END_DATE between", value1, value2,
                    "postEndDate");
            return (Criteria) this;
        }

        public Criteria andPostEndDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("POST_END_DATE not between", value1,
                    value2, "postEndDate");
            return (Criteria) this;
        }

        public Criteria andPostStateIsNull() {
            addCriterion("POST_STATE is null");
            return (Criteria) this;
        }

        public Criteria andPostStateIsNotNull() {
            addCriterion("POST_STATE is not null");
            return (Criteria) this;
        }

        public Criteria andPostStateEqualTo(String value) {
            addCriterion("POST_STATE =", value, "postState");
            return (Criteria) this;
        }

        public Criteria andPostStateNotEqualTo(String value) {
            addCriterion("POST_STATE <>", value, "postState");
            return (Criteria) this;
        }

        public Criteria andPostStateGreaterThan(String value) {
            addCriterion("POST_STATE >", value, "postState");
            return (Criteria) this;
        }

        public Criteria andPostStateGreaterThanOrEqualTo(String value) {
            addCriterion("POST_STATE >=", value, "postState");
            return (Criteria) this;
        }

        public Criteria andPostStateLessThan(String value) {
            addCriterion("POST_STATE <", value, "postState");
            return (Criteria) this;
        }

        public Criteria andPostStateLessThanOrEqualTo(String value) {
            addCriterion("POST_STATE <=", value, "postState");
            return (Criteria) this;
        }

        public Criteria andPostStateLike(String value) {
            addCriterion("POST_STATE like", value, "postState");
            return (Criteria) this;
        }

        public Criteria andPostStateNotLike(String value) {
            addCriterion("POST_STATE not like", value, "postState");
            return (Criteria) this;
        }

        public Criteria andPostStateIn(List<String> values) {
            addCriterion("POST_STATE in", values, "postState");
            return (Criteria) this;
        }

        public Criteria andPostStateNotIn(List<String> values) {
            addCriterion("POST_STATE not in", values, "postState");
            return (Criteria) this;
        }

        public Criteria andPostStateBetween(String value1, String value2) {
            addCriterion("POST_STATE between", value1, value2, "postState");
            return (Criteria) this;
        }

        public Criteria andPostStateNotBetween(String value1, String value2) {
            addCriterion("POST_STATE not between", value1, value2, "postState");
            return (Criteria) this;
        }

        public Criteria andLeafFlagIsNull() {
            addCriterion("LEAF_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andLeafFlagIsNotNull() {
            addCriterion("LEAF_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andLeafFlagEqualTo(String value) {
            addCriterion("LEAF_FLAG =", value, "leafFlag");
            return (Criteria) this;
        }

        public Criteria andLeafFlagNotEqualTo(String value) {
            addCriterion("LEAF_FLAG <>", value, "leafFlag");
            return (Criteria) this;
        }

        public Criteria andLeafFlagGreaterThan(String value) {
            addCriterion("LEAF_FLAG >", value, "leafFlag");
            return (Criteria) this;
        }

        public Criteria andLeafFlagGreaterThanOrEqualTo(String value) {
            addCriterion("LEAF_FLAG >=", value, "leafFlag");
            return (Criteria) this;
        }

        public Criteria andLeafFlagLessThan(String value) {
            addCriterion("LEAF_FLAG <", value, "leafFlag");
            return (Criteria) this;
        }

        public Criteria andLeafFlagLessThanOrEqualTo(String value) {
            addCriterion("LEAF_FLAG <=", value, "leafFlag");
            return (Criteria) this;
        }

        public Criteria andLeafFlagLike(String value) {
            addCriterion("LEAF_FLAG like", value, "leafFlag");
            return (Criteria) this;
        }

        public Criteria andLeafFlagNotLike(String value) {
            addCriterion("LEAF_FLAG not like", value, "leafFlag");
            return (Criteria) this;
        }

        public Criteria andLeafFlagIn(List<String> values) {
            addCriterion("LEAF_FLAG in", values, "leafFlag");
            return (Criteria) this;
        }

        public Criteria andLeafFlagNotIn(List<String> values) {
            addCriterion("LEAF_FLAG not in", values, "leafFlag");
            return (Criteria) this;
        }

        public Criteria andLeafFlagBetween(String value1, String value2) {
            addCriterion("LEAF_FLAG between", value1, value2, "leafFlag");
            return (Criteria) this;
        }

        public Criteria andLeafFlagNotBetween(String value1, String value2) {
            addCriterion("LEAF_FLAG not between", value1, value2, "leafFlag");
            return (Criteria) this;
        }

        public Criteria andLeafCountIsNull() {
            addCriterion("LEAF_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andLeafCountIsNotNull() {
            addCriterion("LEAF_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andLeafCountEqualTo(Integer value) {
            addCriterion("LEAF_COUNT =", value, "leafCount");
            return (Criteria) this;
        }

        public Criteria andLeafCountNotEqualTo(Integer value) {
            addCriterion("LEAF_COUNT <>", value, "leafCount");
            return (Criteria) this;
        }

        public Criteria andLeafCountGreaterThan(Integer value) {
            addCriterion("LEAF_COUNT >", value, "leafCount");
            return (Criteria) this;
        }

        public Criteria andLeafCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("LEAF_COUNT >=", value, "leafCount");
            return (Criteria) this;
        }

        public Criteria andLeafCountLessThan(Integer value) {
            addCriterion("LEAF_COUNT <", value, "leafCount");
            return (Criteria) this;
        }

        public Criteria andLeafCountLessThanOrEqualTo(Integer value) {
            addCriterion("LEAF_COUNT <=", value, "leafCount");
            return (Criteria) this;
        }

        public Criteria andLeafCountIn(List<Integer> values) {
            addCriterion("LEAF_COUNT in", values, "leafCount");
            return (Criteria) this;
        }

        public Criteria andLeafCountNotIn(List<Integer> values) {
            addCriterion("LEAF_COUNT not in", values, "leafCount");
            return (Criteria) this;
        }

        public Criteria andLeafCountBetween(Integer value1, Integer value2) {
            addCriterion("LEAF_COUNT between", value1, value2, "leafCount");
            return (Criteria) this;
        }

        public Criteria andLeafCountNotBetween(Integer value1,
                                               Integer value2) {
            addCriterion("LEAF_COUNT not between", value1, value2, "leafCount");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("CREATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("CREATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterionForJDBCDate("CREATE_TIME =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("CREATE_TIME <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("CREATE_TIME >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("CREATE_TIME >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterionForJDBCDate("CREATE_TIME <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("CREATE_TIME <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterionForJDBCDate("CREATE_TIME in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("CREATE_TIME not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("CREATE_TIME between", value1, value2,
                    "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("CREATE_TIME not between", value1, value2,
                    "createTime");
            return (Criteria) this;
        }

        public Criteria andLastModfiyTimeIsNull() {
            addCriterion("LAST_MODFIY_TIME is null");
            return (Criteria) this;
        }

        public Criteria andLastModfiyTimeIsNotNull() {
            addCriterion("LAST_MODFIY_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andLastModfiyTimeEqualTo(Date value) {
            addCriterion("LAST_MODFIY_TIME =", value, "lastModfiyTime");
            return (Criteria) this;
        }

        public Criteria andLastModfiyTimeNotEqualTo(Date value) {
            addCriterion("LAST_MODFIY_TIME <>", value, "lastModfiyTime");
            return (Criteria) this;
        }

        public Criteria andLastModfiyTimeGreaterThan(Date value) {
            addCriterion("LAST_MODFIY_TIME >", value, "lastModfiyTime");
            return (Criteria) this;
        }

        public Criteria andLastModfiyTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("LAST_MODFIY_TIME >=", value, "lastModfiyTime");
            return (Criteria) this;
        }

        public Criteria andLastModfiyTimeLessThan(Date value) {
            addCriterion("LAST_MODFIY_TIME <", value, "lastModfiyTime");
            return (Criteria) this;
        }

        public Criteria andLastModfiyTimeLessThanOrEqualTo(Date value) {
            addCriterion("LAST_MODFIY_TIME <=", value, "lastModfiyTime");
            return (Criteria) this;
        }

        public Criteria andLastModfiyTimeIn(List<Date> values) {
            addCriterion("LAST_MODFIY_TIME in", values, "lastModfiyTime");
            return (Criteria) this;
        }

        public Criteria andLastModfiyTimeNotIn(List<Date> values) {
            addCriterion("LAST_MODFIY_TIME not in", values, "lastModfiyTime");
            return (Criteria) this;
        }

        public Criteria andLastModfiyTimeBetween(Date value1, Date value2) {
            addCriterion("LAST_MODFIY_TIME between", value1, value2,
                    "lastModfiyTime");
            return (Criteria) this;
        }

        public Criteria andLastModfiyTimeNotBetween(Date value1, Date value2) {
            addCriterion("LAST_MODFIY_TIME not between", value1, value2,
                    "lastModfiyTime");
            return (Criteria) this;
        }

        public Criteria andLastModifyUserIsNull() {
            addCriterion("LAST_MODIFY_USER is null");
            return (Criteria) this;
        }

        public Criteria andLastModifyUserIsNotNull() {
            addCriterion("LAST_MODIFY_USER is not null");
            return (Criteria) this;
        }

        public Criteria andLastModifyUserEqualTo(String value) {
            addCriterion("LAST_MODIFY_USER =", value, "lastModifyUser");
            return (Criteria) this;
        }

        public Criteria andLastModifyUserNotEqualTo(String value) {
            addCriterion("LAST_MODIFY_USER <>", value, "lastModifyUser");
            return (Criteria) this;
        }

        public Criteria andLastModifyUserGreaterThan(String value) {
            addCriterion("LAST_MODIFY_USER >", value, "lastModifyUser");
            return (Criteria) this;
        }

        public Criteria andLastModifyUserGreaterThanOrEqualTo(String value) {
            addCriterion("LAST_MODIFY_USER >=", value, "lastModifyUser");
            return (Criteria) this;
        }

        public Criteria andLastModifyUserLessThan(String value) {
            addCriterion("LAST_MODIFY_USER <", value, "lastModifyUser");
            return (Criteria) this;
        }

        public Criteria andLastModifyUserLessThanOrEqualTo(String value) {
            addCriterion("LAST_MODIFY_USER <=", value, "lastModifyUser");
            return (Criteria) this;
        }

        public Criteria andLastModifyUserLike(String value) {
            addCriterion("LAST_MODIFY_USER like", value, "lastModifyUser");
            return (Criteria) this;
        }

        public Criteria andLastModifyUserNotLike(String value) {
            addCriterion("LAST_MODIFY_USER not like", value, "lastModifyUser");
            return (Criteria) this;
        }

        public Criteria andLastModifyUserIn(List<String> values) {
            addCriterion("LAST_MODIFY_USER in", values, "lastModifyUser");
            return (Criteria) this;
        }

        public Criteria andLastModifyUserNotIn(List<String> values) {
            addCriterion("LAST_MODIFY_USER not in", values, "lastModifyUser");
            return (Criteria) this;
        }

        public Criteria andLastModifyUserBetween(String value1, String value2) {
            addCriterion("LAST_MODIFY_USER between", value1, value2,
                    "lastModifyUser");
            return (Criteria) this;
        }

        public Criteria andLastModifyUserNotBetween(String value1, String value2) {
            addCriterion("LAST_MODIFY_USER not between", value1, value2,
                    "lastModifyUser");
            return (Criteria) this;
        }

        public Criteria andField1IsNull() {
            addCriterion("FIELD1 is null");
            return (Criteria) this;
        }

        public Criteria andField1IsNotNull() {
            addCriterion("FIELD1 is not null");
            return (Criteria) this;
        }

        public Criteria andField1EqualTo(Integer value) {
            addCriterion("FIELD1 =", value, "field1");
            return (Criteria) this;
        }

        public Criteria andField1NotEqualTo(Integer value) {
            addCriterion("FIELD1 <>", value, "field1");
            return (Criteria) this;
        }

        public Criteria andField1GreaterThan(Integer value) {
            addCriterion("FIELD1 >", value, "field1");
            return (Criteria) this;
        }

        public Criteria andField1GreaterThanOrEqualTo(Integer value) {
            addCriterion("FIELD1 >=", value, "field1");
            return (Criteria) this;
        }

        public Criteria andField1LessThan(Integer value) {
            addCriterion("FIELD1 <", value, "field1");
            return (Criteria) this;
        }

        public Criteria andField1LessThanOrEqualTo(Integer value) {
            addCriterion("FIELD1 <=", value, "field1");
            return (Criteria) this;
        }

        public Criteria andField1In(List<Integer> values) {
            addCriterion("FIELD1 in", values, "field1");
            return (Criteria) this;
        }

        public Criteria andField1NotIn(List<Integer> values) {
            addCriterion("FIELD1 not in", values, "field1");
            return (Criteria) this;
        }

        public Criteria andField1Between(Integer value1, Integer value2) {
            addCriterion("FIELD1 between", value1, value2, "field1");
            return (Criteria) this;
        }

        public Criteria andField1NotBetween(Integer value1, Integer value2) {
            addCriterion("FIELD1 not between", value1, value2, "field1");
            return (Criteria) this;
        }

        public Criteria andField2IsNull() {
            addCriterion("FIELD2 is null");
            return (Criteria) this;
        }

        public Criteria andField2IsNotNull() {
            addCriterion("FIELD2 is not null");
            return (Criteria) this;
        }

        public Criteria andField2EqualTo(Integer value) {
            addCriterion("FIELD2 =", value, "field2");
            return (Criteria) this;
        }

        public Criteria andField2NotEqualTo(Integer value) {
            addCriterion("FIELD2 <>", value, "field2");
            return (Criteria) this;
        }

        public Criteria andField2GreaterThan(Integer value) {
            addCriterion("FIELD2 >", value, "field2");
            return (Criteria) this;
        }

        public Criteria andField2GreaterThanOrEqualTo(Integer value) {
            addCriterion("FIELD2 >=", value, "field2");
            return (Criteria) this;
        }

        public Criteria andField2LessThan(Integer value) {
            addCriterion("FIELD2 <", value, "field2");
            return (Criteria) this;
        }

        public Criteria andField2LessThanOrEqualTo(Integer value) {
            addCriterion("FIELD2 <=", value, "field2");
            return (Criteria) this;
        }

        public Criteria andField2In(List<Integer> values) {
            addCriterion("FIELD2 in", values, "field2");
            return (Criteria) this;
        }

        public Criteria andField2NotIn(List<Integer> values) {
            addCriterion("FIELD2 not in", values, "field2");
            return (Criteria) this;
        }

        public Criteria andField2Between(Integer value1, Integer value2) {
            addCriterion("FIELD2 between", value1, value2, "field2");
            return (Criteria) this;
        }

        public Criteria andField2NotBetween(Integer value1, Integer value2) {
            addCriterion("FIELD2 not between", value1, value2, "field2");
            return (Criteria) this;
        }

        public Criteria andField3IsNull() {
            addCriterion("FIELD3 is null");
            return (Criteria) this;
        }

        public Criteria andField3IsNotNull() {
            addCriterion("FIELD3 is not null");
            return (Criteria) this;
        }

        public Criteria andField3EqualTo(Integer value) {
            addCriterion("FIELD3 =", value, "field3");
            return (Criteria) this;
        }

        public Criteria andField3NotEqualTo(Integer value) {
            addCriterion("FIELD3 <>", value, "field3");
            return (Criteria) this;
        }

        public Criteria andField3GreaterThan(Integer value) {
            addCriterion("FIELD3 >", value, "field3");
            return (Criteria) this;
        }

        public Criteria andField3GreaterThanOrEqualTo(Integer value) {
            addCriterion("FIELD3 >=", value, "field3");
            return (Criteria) this;
        }

        public Criteria andField3LessThan(Integer value) {
            addCriterion("FIELD3 <", value, "field3");
            return (Criteria) this;
        }

        public Criteria andField3LessThanOrEqualTo(Integer value) {
            addCriterion("FIELD3 <=", value, "field3");
            return (Criteria) this;
        }

        public Criteria andField3In(List<Integer> values) {
            addCriterion("FIELD3 in", values, "field3");
            return (Criteria) this;
        }

        public Criteria andField3NotIn(List<Integer> values) {
            addCriterion("FIELD3 not in", values, "field3");
            return (Criteria) this;
        }

        public Criteria andField3Between(Integer value1, Integer value2) {
            addCriterion("FIELD3 between", value1, value2, "field3");
            return (Criteria) this;
        }

        public Criteria andField3NotBetween(Integer value1, Integer value2) {
            addCriterion("FIELD3 not between", value1, value2, "field3");
            return (Criteria) this;
        }

        public Criteria andField4IsNull() {
            addCriterion("FIELD4 is null");
            return (Criteria) this;
        }

        public Criteria andField4IsNotNull() {
            addCriterion("FIELD4 is not null");
            return (Criteria) this;
        }

        public Criteria andField4EqualTo(String value) {
            addCriterion("FIELD4 =", value, "field4");
            return (Criteria) this;
        }

        public Criteria andField4NotEqualTo(String value) {
            addCriterion("FIELD4 <>", value, "field4");
            return (Criteria) this;
        }

        public Criteria andField4GreaterThan(String value) {
            addCriterion("FIELD4 >", value, "field4");
            return (Criteria) this;
        }

        public Criteria andField4GreaterThanOrEqualTo(String value) {
            addCriterion("FIELD4 >=", value, "field4");
            return (Criteria) this;
        }

        public Criteria andField4LessThan(String value) {
            addCriterion("FIELD4 <", value, "field4");
            return (Criteria) this;
        }

        public Criteria andField4LessThanOrEqualTo(String value) {
            addCriterion("FIELD4 <=", value, "field4");
            return (Criteria) this;
        }

        public Criteria andField4Like(String value) {
            addCriterion("FIELD4 like", value, "field4");
            return (Criteria) this;
        }

        public Criteria andField4NotLike(String value) {
            addCriterion("FIELD4 not like", value, "field4");
            return (Criteria) this;
        }

        public Criteria andField4In(List<String> values) {
            addCriterion("FIELD4 in", values, "field4");
            return (Criteria) this;
        }

        public Criteria andField4NotIn(List<String> values) {
            addCriterion("FIELD4 not in", values, "field4");
            return (Criteria) this;
        }

        public Criteria andField4Between(String value1, String value2) {
            addCriterion("FIELD4 between", value1, value2, "field4");
            return (Criteria) this;
        }

        public Criteria andField4NotBetween(String value1, String value2) {
            addCriterion("FIELD4 not between", value1, value2, "field4");
            return (Criteria) this;
        }

        public Criteria andField5IsNull() {
            addCriterion("FIELD5 is null");
            return (Criteria) this;
        }

        public Criteria andField5IsNotNull() {
            addCriterion("FIELD5 is not null");
            return (Criteria) this;
        }

        public Criteria andField5EqualTo(String value) {
            addCriterion("FIELD5 =", value, "field5");
            return (Criteria) this;
        }

        public Criteria andField5NotEqualTo(String value) {
            addCriterion("FIELD5 <>", value, "field5");
            return (Criteria) this;
        }

        public Criteria andField5GreaterThan(String value) {
            addCriterion("FIELD5 >", value, "field5");
            return (Criteria) this;
        }

        public Criteria andField5GreaterThanOrEqualTo(String value) {
            addCriterion("FIELD5 >=", value, "field5");
            return (Criteria) this;
        }

        public Criteria andField5LessThan(String value) {
            addCriterion("FIELD5 <", value, "field5");
            return (Criteria) this;
        }

        public Criteria andField5LessThanOrEqualTo(String value) {
            addCriterion("FIELD5 <=", value, "field5");
            return (Criteria) this;
        }

        public Criteria andField5Like(String value) {
            addCriterion("FIELD5 like", value, "field5");
            return (Criteria) this;
        }

        public Criteria andField5NotLike(String value) {
            addCriterion("FIELD5 not like", value, "field5");
            return (Criteria) this;
        }

        public Criteria andField5In(List<String> values) {
            addCriterion("FIELD5 in", values, "field5");
            return (Criteria) this;
        }

        public Criteria andField5NotIn(List<String> values) {
            addCriterion("FIELD5 not in", values, "field5");
            return (Criteria) this;
        }

        public Criteria andField5Between(String value1, String value2) {
            addCriterion("FIELD5 between", value1, value2, "field5");
            return (Criteria) this;
        }

        public Criteria andField5NotBetween(String value1, String value2) {
            addCriterion("FIELD5 not between", value1, value2, "field5");
            return (Criteria) this;
        }

        public Criteria andField6IsNull() {
            addCriterion("FIELD6 is null");
            return (Criteria) this;
        }

        public Criteria andField6IsNotNull() {
            addCriterion("FIELD6 is not null");
            return (Criteria) this;
        }

        public Criteria andField6EqualTo(String value) {
            addCriterion("FIELD6 =", value, "field6");
            return (Criteria) this;
        }

        public Criteria andField6NotEqualTo(String value) {
            addCriterion("FIELD6 <>", value, "field6");
            return (Criteria) this;
        }

        public Criteria andField6GreaterThan(String value) {
            addCriterion("FIELD6 >", value, "field6");
            return (Criteria) this;
        }

        public Criteria andField6GreaterThanOrEqualTo(String value) {
            addCriterion("FIELD6 >=", value, "field6");
            return (Criteria) this;
        }

        public Criteria andField6LessThan(String value) {
            addCriterion("FIELD6 <", value, "field6");
            return (Criteria) this;
        }

        public Criteria andField6LessThanOrEqualTo(String value) {
            addCriterion("FIELD6 <=", value, "field6");
            return (Criteria) this;
        }

        public Criteria andField6Like(String value) {
            addCriterion("FIELD6 like", value, "field6");
            return (Criteria) this;
        }

        public Criteria andField6NotLike(String value) {
            addCriterion("FIELD6 not like", value, "field6");
            return (Criteria) this;
        }

        public Criteria andField6In(List<String> values) {
            addCriterion("FIELD6 in", values, "field6");
            return (Criteria) this;
        }

        public Criteria andField6NotIn(List<String> values) {
            addCriterion("FIELD6 not in", values, "field6");
            return (Criteria) this;
        }

        public Criteria andField6Between(String value1, String value2) {
            addCriterion("FIELD6 between", value1, value2, "field6");
            return (Criteria) this;
        }

        public Criteria andField6NotBetween(String value1, String value2) {
            addCriterion("FIELD6 not between", value1, value2, "field6");
            return (Criteria) this;
        }

        public Criteria andField7IsNull() {
            addCriterion("FIELD7 is null");
            return (Criteria) this;
        }

        public Criteria andField7IsNotNull() {
            addCriterion("FIELD7 is not null");
            return (Criteria) this;
        }

        public Criteria andField7EqualTo(String value) {
            addCriterion("FIELD7 =", value, "field7");
            return (Criteria) this;
        }

        public Criteria andField7NotEqualTo(String value) {
            addCriterion("FIELD7 <>", value, "field7");
            return (Criteria) this;
        }

        public Criteria andField7GreaterThan(String value) {
            addCriterion("FIELD7 >", value, "field7");
            return (Criteria) this;
        }

        public Criteria andField7GreaterThanOrEqualTo(String value) {
            addCriterion("FIELD7 >=", value, "field7");
            return (Criteria) this;
        }

        public Criteria andField7LessThan(String value) {
            addCriterion("FIELD7 <", value, "field7");
            return (Criteria) this;
        }

        public Criteria andField7LessThanOrEqualTo(String value) {
            addCriterion("FIELD7 <=", value, "field7");
            return (Criteria) this;
        }

        public Criteria andField7Like(String value) {
            addCriterion("FIELD7 like", value, "field7");
            return (Criteria) this;
        }

        public Criteria andField7NotLike(String value) {
            addCriterion("FIELD7 not like", value, "field7");
            return (Criteria) this;
        }

        public Criteria andField7In(List<String> values) {
            addCriterion("FIELD7 in", values, "field7");
            return (Criteria) this;
        }

        public Criteria andField7NotIn(List<String> values) {
            addCriterion("FIELD7 not in", values, "field7");
            return (Criteria) this;
        }

        public Criteria andField7Between(String value1, String value2) {
            addCriterion("FIELD7 between", value1, value2, "field7");
            return (Criteria) this;
        }

        public Criteria andField7NotBetween(String value1, String value2) {
            addCriterion("FIELD7 not between", value1, value2, "field7");
            return (Criteria) this;
        }

        public Criteria andField8IsNull() {
            addCriterion("FIELD8 is null");
            return (Criteria) this;
        }

        public Criteria andField8IsNotNull() {
            addCriterion("FIELD8 is not null");
            return (Criteria) this;
        }

        public Criteria andField8EqualTo(String value) {
            addCriterion("FIELD8 =", value, "field8");
            return (Criteria) this;
        }

        public Criteria andField8NotEqualTo(String value) {
            addCriterion("FIELD8 <>", value, "field8");
            return (Criteria) this;
        }

        public Criteria andField8GreaterThan(String value) {
            addCriterion("FIELD8 >", value, "field8");
            return (Criteria) this;
        }

        public Criteria andField8GreaterThanOrEqualTo(String value) {
            addCriterion("FIELD8 >=", value, "field8");
            return (Criteria) this;
        }

        public Criteria andField8LessThan(String value) {
            addCriterion("FIELD8 <", value, "field8");
            return (Criteria) this;
        }

        public Criteria andField8LessThanOrEqualTo(String value) {
            addCriterion("FIELD8 <=", value, "field8");
            return (Criteria) this;
        }

        public Criteria andField8Like(String value) {
            addCriterion("FIELD8 like", value, "field8");
            return (Criteria) this;
        }

        public Criteria andField8NotLike(String value) {
            addCriterion("FIELD8 not like", value, "field8");
            return (Criteria) this;
        }

        public Criteria andField8In(List<String> values) {
            addCriterion("FIELD8 in", values, "field8");
            return (Criteria) this;
        }

        public Criteria andField8NotIn(List<String> values) {
            addCriterion("FIELD8 not in", values, "field8");
            return (Criteria) this;
        }

        public Criteria andField8Between(String value1, String value2) {
            addCriterion("FIELD8 between", value1, value2, "field8");
            return (Criteria) this;
        }

        public Criteria andField8NotBetween(String value1, String value2) {
            addCriterion("FIELD8 not between", value1, value2, "field8");
            return (Criteria) this;
        }

        public Criteria andField9IsNull() {
            addCriterion("FIELD9 is null");
            return (Criteria) this;
        }

        public Criteria andField9IsNotNull() {
            addCriterion("FIELD9 is not null");
            return (Criteria) this;
        }

        public Criteria andField9EqualTo(String value) {
            addCriterion("FIELD9 =", value, "field9");
            return (Criteria) this;
        }

        public Criteria andField9NotEqualTo(String value) {
            addCriterion("FIELD9 <>", value, "field9");
            return (Criteria) this;
        }

        public Criteria andField9GreaterThan(String value) {
            addCriterion("FIELD9 >", value, "field9");
            return (Criteria) this;
        }

        public Criteria andField9GreaterThanOrEqualTo(String value) {
            addCriterion("FIELD9 >=", value, "field9");
            return (Criteria) this;
        }

        public Criteria andField9LessThan(String value) {
            addCriterion("FIELD9 <", value, "field9");
            return (Criteria) this;
        }

        public Criteria andField9LessThanOrEqualTo(String value) {
            addCriterion("FIELD9 <=", value, "field9");
            return (Criteria) this;
        }

        public Criteria andField9Like(String value) {
            addCriterion("FIELD9 like", value, "field9");
            return (Criteria) this;
        }

        public Criteria andField9NotLike(String value) {
            addCriterion("FIELD9 not like", value, "field9");
            return (Criteria) this;
        }

        public Criteria andField9In(List<String> values) {
            addCriterion("FIELD9 in", values, "field9");
            return (Criteria) this;
        }

        public Criteria andField9NotIn(List<String> values) {
            addCriterion("FIELD9 not in", values, "field9");
            return (Criteria) this;
        }

        public Criteria andField9Between(String value1, String value2) {
            addCriterion("FIELD9 between", value1, value2, "field9");
            return (Criteria) this;
        }

        public Criteria andField9NotBetween(String value1, String value2) {
            addCriterion("FIELD9 not between", value1, value2, "field9");
            return (Criteria) this;
        }

        public Criteria andField10IsNull() {
            addCriterion("FIELD10 is null");
            return (Criteria) this;
        }

        public Criteria andField10IsNotNull() {
            addCriterion("FIELD10 is not null");
            return (Criteria) this;
        }

        public Criteria andField10EqualTo(String value) {
            addCriterion("FIELD10 =", value, "field10");
            return (Criteria) this;
        }

        public Criteria andField10NotEqualTo(String value) {
            addCriterion("FIELD10 <>", value, "field10");
            return (Criteria) this;
        }

        public Criteria andField10GreaterThan(String value) {
            addCriterion("FIELD10 >", value, "field10");
            return (Criteria) this;
        }

        public Criteria andField10GreaterThanOrEqualTo(String value) {
            addCriterion("FIELD10 >=", value, "field10");
            return (Criteria) this;
        }

        public Criteria andField10LessThan(String value) {
            addCriterion("FIELD10 <", value, "field10");
            return (Criteria) this;
        }

        public Criteria andField10LessThanOrEqualTo(String value) {
            addCriterion("FIELD10 <=", value, "field10");
            return (Criteria) this;
        }

        public Criteria andField10Like(String value) {
            addCriterion("FIELD10 like", value, "field10");
            return (Criteria) this;
        }

        public Criteria andField10NotLike(String value) {
            addCriterion("FIELD10 not like", value, "field10");
            return (Criteria) this;
        }

        public Criteria andField10In(List<String> values) {
            addCriterion("FIELD10 in", values, "field10");
            return (Criteria) this;
        }

        public Criteria andField10NotIn(List<String> values) {
            addCriterion("FIELD10 not in", values, "field10");
            return (Criteria) this;
        }

        public Criteria andField10Between(String value1, String value2) {
            addCriterion("FIELD10 between", value1, value2, "field10");
            return (Criteria) this;
        }

        public Criteria andField10NotBetween(String value1, String value2) {
            addCriterion("FIELD10 not between", value1, value2, "field10");
            return (Criteria) this;
        }
    }

    public static class Criterion {
        private String condition;
        private Object value;
        private Object secondValue;
        private boolean noValue;
        private boolean singleValue;
        private boolean betweenValue;
        private boolean listValue;
        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue,
                            String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table PF.T_PM_POST
     *
     * @mbggenerated do_not_delete_during_merge Thu May 28 15:01:22 CST 2015
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}