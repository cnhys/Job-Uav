package com.vt.org.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class OrganizationExample {
    protected String orderByClause;
    protected boolean distinct;
    protected List<Criteria> oredCriteria;

    public OrganizationExample() {
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

        protected void addCriterion(String condition, Object value,
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

        public Criteria andOrgIdIsNull() {
            addCriterion("ORG_ID is null");
            return (Criteria) this;
        }

        public Criteria andOrgIdIsNotNull() {
            addCriterion("ORG_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOrgIdEqualTo(Integer value) {
            addCriterion("ORG_ID =", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotEqualTo(Integer value) {
            addCriterion("ORG_ID <>", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdGreaterThan(Integer value) {
            addCriterion("ORG_ID >", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ORG_ID >=", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdLessThan(Integer value) {
            addCriterion("ORG_ID <", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdLessThanOrEqualTo(Integer value) {
            addCriterion("ORG_ID <=", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdIn(List<Integer> values) {
            addCriterion("ORG_ID in", values, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotIn(List<Integer> values) {
            addCriterion("ORG_ID not in", values, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdBetween(Integer value1, Integer value2) {
            addCriterion("ORG_ID between", value1, value2, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ORG_ID not between", value1, value2, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgCodeIsNull() {
            addCriterion("ORG_CODE is null");
            return (Criteria) this;
        }

        public Criteria andOrgCodeIsNotNull() {
            addCriterion("ORG_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andOrgCodeEqualTo(String value) {
            addCriterion("ORG_CODE =", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeNotEqualTo(String value) {
            addCriterion("ORG_CODE <>", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeGreaterThan(String value) {
            addCriterion("ORG_CODE >", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_CODE >=", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeLessThan(String value) {
            addCriterion("ORG_CODE <", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeLessThanOrEqualTo(String value) {
            addCriterion("ORG_CODE <=", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeLike(String value) {
            addCriterion("ORG_CODE like", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeNotLike(String value) {
            addCriterion("ORG_CODE not like", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeIn(List<String> values) {
            addCriterion("ORG_CODE in", values, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeNotIn(List<String> values) {
            addCriterion("ORG_CODE not in", values, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeBetween(String value1, String value2) {
            addCriterion("ORG_CODE between", value1, value2, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeNotBetween(String value1, String value2) {
            addCriterion("ORG_CODE not between", value1, value2, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgNameIsNull() {
            addCriterion("ORG_NAME is null");
            return (Criteria) this;
        }

        public Criteria andOrgNameIsNotNull() {
            addCriterion("ORG_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andOrgNameEqualTo(String value) {
            addCriterion("ORG_NAME =", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotEqualTo(String value) {
            addCriterion("ORG_NAME <>", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameGreaterThan(String value) {
            addCriterion("ORG_NAME >", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_NAME >=", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameLessThan(String value) {
            addCriterion("ORG_NAME <", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameLessThanOrEqualTo(String value) {
            addCriterion("ORG_NAME <=", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameLike(String value) {
            addCriterion("ORG_NAME like", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotLike(String value) {
            addCriterion("ORG_NAME not like", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameIn(List<String> values) {
            addCriterion("ORG_NAME in", values, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotIn(List<String> values) {
            addCriterion("ORG_NAME not in", values, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameBetween(String value1, String value2) {
            addCriterion("ORG_NAME between", value1, value2, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotBetween(String value1, String value2) {
            addCriterion("ORG_NAME not between", value1, value2, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameEnIsNull() {
            addCriterion("ORG_NAME_EN is null");
            return (Criteria) this;
        }

        public Criteria andOrgNameEnIsNotNull() {
            addCriterion("ORG_NAME_EN is not null");
            return (Criteria) this;
        }

        public Criteria andOrgNameEnEqualTo(String value) {
            addCriterion("ORG_NAME_EN =", value, "orgNameEn");
            return (Criteria) this;
        }

        public Criteria andOrgNameEnNotEqualTo(String value) {
            addCriterion("ORG_NAME_EN <>", value, "orgNameEn");
            return (Criteria) this;
        }

        public Criteria andOrgNameEnGreaterThan(String value) {
            addCriterion("ORG_NAME_EN >", value, "orgNameEn");
            return (Criteria) this;
        }

        public Criteria andOrgNameEnGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_NAME_EN >=", value, "orgNameEn");
            return (Criteria) this;
        }

        public Criteria andOrgNameEnLessThan(String value) {
            addCriterion("ORG_NAME_EN <", value, "orgNameEn");
            return (Criteria) this;
        }

        public Criteria andOrgNameEnLessThanOrEqualTo(String value) {
            addCriterion("ORG_NAME_EN <=", value, "orgNameEn");
            return (Criteria) this;
        }

        public Criteria andOrgNameEnLike(String value) {
            addCriterion("ORG_NAME_EN like", value, "orgNameEn");
            return (Criteria) this;
        }

        public Criteria andOrgNameEnNotLike(String value) {
            addCriterion("ORG_NAME_EN not like", value, "orgNameEn");
            return (Criteria) this;
        }

        public Criteria andOrgNameEnIn(List<String> values) {
            addCriterion("ORG_NAME_EN in", values, "orgNameEn");
            return (Criteria) this;
        }

        public Criteria andOrgNameEnNotIn(List<String> values) {
            addCriterion("ORG_NAME_EN not in", values, "orgNameEn");
            return (Criteria) this;
        }

        public Criteria andOrgNameEnBetween(String value1, String value2) {
            addCriterion("ORG_NAME_EN between", value1, value2, "orgNameEn");
            return (Criteria) this;
        }

        public Criteria andOrgNameEnNotBetween(String value1, String value2) {
            addCriterion("ORG_NAME_EN not between", value1, value2, "orgNameEn");
            return (Criteria) this;
        }

        public Criteria andOrgLevelIsNull() {
            addCriterion("ORG_LEVEL is null");
            return (Criteria) this;
        }

        public Criteria andOrgLevelIsNotNull() {
            addCriterion("ORG_LEVEL is not null");
            return (Criteria) this;
        }

        public Criteria andOrgLevelEqualTo(String value) {
            addCriterion("ORG_LEVEL =", value, "orgLevel");
            return (Criteria) this;
        }

        public Criteria andOrgLevelNotEqualTo(String value) {
            addCriterion("ORG_LEVEL <>", value, "orgLevel");
            return (Criteria) this;
        }

        public Criteria andOrgLevelGreaterThan(String value) {
            addCriterion("ORG_LEVEL >", value, "orgLevel");
            return (Criteria) this;
        }

        public Criteria andOrgLevelGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_LEVEL >=", value, "orgLevel");
            return (Criteria) this;
        }

        public Criteria andOrgLevelLessThan(String value) {
            addCriterion("ORG_LEVEL <", value, "orgLevel");
            return (Criteria) this;
        }

        public Criteria andOrgLevelLessThanOrEqualTo(String value) {
            addCriterion("ORG_LEVEL <=", value, "orgLevel");
            return (Criteria) this;
        }

        public Criteria andOrgLevelLike(String value) {
            addCriterion("ORG_LEVEL like", value, "orgLevel");
            return (Criteria) this;
        }

        public Criteria andOrgLevelNotLike(String value) {
            addCriterion("ORG_LEVEL not like", value, "orgLevel");
            return (Criteria) this;
        }

        public Criteria andOrgLevelIn(List<String> values) {
            addCriterion("ORG_LEVEL in", values, "orgLevel");
            return (Criteria) this;
        }

        public Criteria andOrgLevelNotIn(List<String> values) {
            addCriterion("ORG_LEVEL not in", values, "orgLevel");
            return (Criteria) this;
        }

        public Criteria andOrgLevelBetween(String value1, String value2) {
            addCriterion("ORG_LEVEL between", value1, value2, "orgLevel");
            return (Criteria) this;
        }

        public Criteria andOrgLevelNotBetween(String value1, String value2) {
            addCriterion("ORG_LEVEL not between", value1, value2, "orgLevel");
            return (Criteria) this;
        }

        public Criteria andParentOrgIdIsNull() {
            addCriterion("PARENT_ORG_ID is null");
            return (Criteria) this;
        }

        public Criteria andParentOrgIdIsNotNull() {
            addCriterion("PARENT_ORG_ID is not null");
            return (Criteria) this;
        }

        public Criteria andParentOrgIdEqualTo(Integer value) {
            addCriterion("PARENT_ORG_ID =", value, "parentOrgId");
            return (Criteria) this;
        }

        public Criteria andParentOrgIdNotEqualTo(Integer value) {
            addCriterion("PARENT_ORG_ID <>", value, "parentOrgId");
            return (Criteria) this;
        }

        public Criteria andParentOrgIdGreaterThan(Integer value) {
            addCriterion("PARENT_ORG_ID >", value, "parentOrgId");
            return (Criteria) this;
        }

        public Criteria andParentOrgIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("PARENT_ORG_ID >=", value, "parentOrgId");
            return (Criteria) this;
        }

        public Criteria andParentOrgIdLessThan(Integer value) {
            addCriterion("PARENT_ORG_ID <", value, "parentOrgId");
            return (Criteria) this;
        }

        public Criteria andParentOrgIdLessThanOrEqualTo(Integer value) {
            addCriterion("PARENT_ORG_ID <=", value, "parentOrgId");
            return (Criteria) this;
        }

        public Criteria andParentOrgIdIn(List<Integer> values) {
            addCriterion("PARENT_ORG_ID in", values, "parentOrgId");
            return (Criteria) this;
        }

        public Criteria andParentOrgIdNotIn(List<Integer> values) {
            addCriterion("PARENT_ORG_ID not in", values, "parentOrgId");
            return (Criteria) this;
        }

        public Criteria andParentOrgIdBetween(Integer value1,
                                              Integer value2) {
            addCriterion("PARENT_ORG_ID between", value1, value2, "parentOrgId");
            return (Criteria) this;
        }

        public Criteria andParentOrgIdNotBetween(Integer value1,
                                                 Integer value2) {
            addCriterion("PARENT_ORG_ID not between", value1, value2,
                    "parentOrgId");
            return (Criteria) this;
        }

        public Criteria andOrgSerialnoIsNull() {
            addCriterion("ORG_SERIALNO is null");
            return (Criteria) this;
        }

        public Criteria andOrgSerialnoIsNotNull() {
            addCriterion("ORG_SERIALNO is not null");
            return (Criteria) this;
        }

        public Criteria andOrgSerialnoEqualTo(Integer value) {
            addCriterion("ORG_SERIALNO =", value, "orgSerialno");
            return (Criteria) this;
        }

        public Criteria andOrgSerialnoNotEqualTo(Integer value) {
            addCriterion("ORG_SERIALNO <>", value, "orgSerialno");
            return (Criteria) this;
        }

        public Criteria andOrgSerialnoGreaterThan(Integer value) {
            addCriterion("ORG_SERIALNO >", value, "orgSerialno");
            return (Criteria) this;
        }

        public Criteria andOrgSerialnoGreaterThanOrEqualTo(Integer value) {
            addCriterion("ORG_SERIALNO >=", value, "orgSerialno");
            return (Criteria) this;
        }

        public Criteria andOrgSerialnoLessThan(Integer value) {
            addCriterion("ORG_SERIALNO <", value, "orgSerialno");
            return (Criteria) this;
        }

        public Criteria andOrgSerialnoLessThanOrEqualTo(Integer value) {
            addCriterion("ORG_SERIALNO <=", value, "orgSerialno");
            return (Criteria) this;
        }

        public Criteria andOrgSerialnoIn(List<Integer> values) {
            addCriterion("ORG_SERIALNO in", values, "orgSerialno");
            return (Criteria) this;
        }

        public Criteria andOrgSerialnoNotIn(List<Integer> values) {
            addCriterion("ORG_SERIALNO not in", values, "orgSerialno");
            return (Criteria) this;
        }

        public Criteria andOrgSerialnoBetween(Integer value1,
                                              Integer value2) {
            addCriterion("ORG_SERIALNO between", value1, value2, "orgSerialno");
            return (Criteria) this;
        }

        public Criteria andOrgSerialnoNotBetween(Integer value1,
                                                 Integer value2) {
            addCriterion("ORG_SERIALNO not between", value1, value2,
                    "orgSerialno");
            return (Criteria) this;
        }

        public Criteria andOrgSeqIsNull() {
            addCriterion("ORG_SEQ is null");
            return (Criteria) this;
        }

        public Criteria andOrgSeqIsNotNull() {
            addCriterion("ORG_SEQ is not null");
            return (Criteria) this;
        }

        public Criteria andOrgSeqEqualTo(String value) {
            addCriterion("ORG_SEQ =", value, "orgSeq");
            return (Criteria) this;
        }

        public Criteria andOrgSeqNotEqualTo(String value) {
            addCriterion("ORG_SEQ <>", value, "orgSeq");
            return (Criteria) this;
        }

        public Criteria andOrgSeqGreaterThan(String value) {
            addCriterion("ORG_SEQ >", value, "orgSeq");
            return (Criteria) this;
        }

        public Criteria andOrgSeqGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_SEQ >=", value, "orgSeq");
            return (Criteria) this;
        }

        public Criteria andOrgSeqLessThan(String value) {
            addCriterion("ORG_SEQ <", value, "orgSeq");
            return (Criteria) this;
        }

        public Criteria andOrgSeqLessThanOrEqualTo(String value) {
            addCriterion("ORG_SEQ <=", value, "orgSeq");
            return (Criteria) this;
        }

        public Criteria andOrgSeqLike(String value) {
            addCriterion("ORG_SEQ like", value, "orgSeq");
            return (Criteria) this;
        }

        public Criteria andOrgSeqNotLike(String value) {
            addCriterion("ORG_SEQ not like", value, "orgSeq");
            return (Criteria) this;
        }

        public Criteria andOrgSeqIn(List<String> values) {
            addCriterion("ORG_SEQ in", values, "orgSeq");
            return (Criteria) this;
        }

        public Criteria andOrgSeqNotIn(List<String> values) {
            addCriterion("ORG_SEQ not in", values, "orgSeq");
            return (Criteria) this;
        }

        public Criteria andOrgSeqBetween(String value1, String value2) {
            addCriterion("ORG_SEQ between", value1, value2, "orgSeq");
            return (Criteria) this;
        }

        public Criteria andOrgSeqNotBetween(String value1, String value2) {
            addCriterion("ORG_SEQ not between", value1, value2, "orgSeq");
            return (Criteria) this;
        }

        public Criteria andOrgTypeIsNull() {
            addCriterion("ORG_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andOrgTypeIsNotNull() {
            addCriterion("ORG_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andOrgTypeEqualTo(String value) {
            addCriterion("ORG_TYPE =", value, "orgType");
            return (Criteria) this;
        }

        public Criteria andOrgTypeNotEqualTo(String value) {
            addCriterion("ORG_TYPE <>", value, "orgType");
            return (Criteria) this;
        }

        public Criteria andOrgTypeGreaterThan(String value) {
            addCriterion("ORG_TYPE >", value, "orgType");
            return (Criteria) this;
        }

        public Criteria andOrgTypeGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_TYPE >=", value, "orgType");
            return (Criteria) this;
        }

        public Criteria andOrgTypeLessThan(String value) {
            addCriterion("ORG_TYPE <", value, "orgType");
            return (Criteria) this;
        }

        public Criteria andOrgTypeLessThanOrEqualTo(String value) {
            addCriterion("ORG_TYPE <=", value, "orgType");
            return (Criteria) this;
        }

        public Criteria andOrgTypeLike(String value) {
            addCriterion("ORG_TYPE like", value, "orgType");
            return (Criteria) this;
        }

        public Criteria andOrgTypeNotLike(String value) {
            addCriterion("ORG_TYPE not like", value, "orgType");
            return (Criteria) this;
        }

        public Criteria andOrgTypeIn(List<String> values) {
            addCriterion("ORG_TYPE in", values, "orgType");
            return (Criteria) this;
        }

        public Criteria andOrgTypeNotIn(List<String> values) {
            addCriterion("ORG_TYPE not in", values, "orgType");
            return (Criteria) this;
        }

        public Criteria andOrgTypeBetween(String value1, String value2) {
            addCriterion("ORG_TYPE between", value1, value2, "orgType");
            return (Criteria) this;
        }

        public Criteria andOrgTypeNotBetween(String value1, String value2) {
            addCriterion("ORG_TYPE not between", value1, value2, "orgType");
            return (Criteria) this;
        }

        public Criteria andOrgAddressIsNull() {
            addCriterion("ORG_ADDRESS is null");
            return (Criteria) this;
        }

        public Criteria andOrgAddressIsNotNull() {
            addCriterion("ORG_ADDRESS is not null");
            return (Criteria) this;
        }

        public Criteria andOrgAddressEqualTo(String value) {
            addCriterion("ORG_ADDRESS =", value, "orgAddress");
            return (Criteria) this;
        }

        public Criteria andOrgAddressNotEqualTo(String value) {
            addCriterion("ORG_ADDRESS <>", value, "orgAddress");
            return (Criteria) this;
        }

        public Criteria andOrgAddressGreaterThan(String value) {
            addCriterion("ORG_ADDRESS >", value, "orgAddress");
            return (Criteria) this;
        }

        public Criteria andOrgAddressGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_ADDRESS >=", value, "orgAddress");
            return (Criteria) this;
        }

        public Criteria andOrgAddressLessThan(String value) {
            addCriterion("ORG_ADDRESS <", value, "orgAddress");
            return (Criteria) this;
        }

        public Criteria andOrgAddressLessThanOrEqualTo(String value) {
            addCriterion("ORG_ADDRESS <=", value, "orgAddress");
            return (Criteria) this;
        }

        public Criteria andOrgAddressLike(String value) {
            addCriterion("ORG_ADDRESS like", value, "orgAddress");
            return (Criteria) this;
        }

        public Criteria andOrgAddressNotLike(String value) {
            addCriterion("ORG_ADDRESS not like", value, "orgAddress");
            return (Criteria) this;
        }

        public Criteria andOrgAddressIn(List<String> values) {
            addCriterion("ORG_ADDRESS in", values, "orgAddress");
            return (Criteria) this;
        }

        public Criteria andOrgAddressNotIn(List<String> values) {
            addCriterion("ORG_ADDRESS not in", values, "orgAddress");
            return (Criteria) this;
        }

        public Criteria andOrgAddressBetween(String value1, String value2) {
            addCriterion("ORG_ADDRESS between", value1, value2, "orgAddress");
            return (Criteria) this;
        }

        public Criteria andOrgAddressNotBetween(String value1, String value2) {
            addCriterion("ORG_ADDRESS not between", value1, value2,
                    "orgAddress");
            return (Criteria) this;
        }

        public Criteria andZipCodeIsNull() {
            addCriterion("ZIP_CODE is null");
            return (Criteria) this;
        }

        public Criteria andZipCodeIsNotNull() {
            addCriterion("ZIP_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andZipCodeEqualTo(String value) {
            addCriterion("ZIP_CODE =", value, "zipCode");
            return (Criteria) this;
        }

        public Criteria andZipCodeNotEqualTo(String value) {
            addCriterion("ZIP_CODE <>", value, "zipCode");
            return (Criteria) this;
        }

        public Criteria andZipCodeGreaterThan(String value) {
            addCriterion("ZIP_CODE >", value, "zipCode");
            return (Criteria) this;
        }

        public Criteria andZipCodeGreaterThanOrEqualTo(String value) {
            addCriterion("ZIP_CODE >=", value, "zipCode");
            return (Criteria) this;
        }

        public Criteria andZipCodeLessThan(String value) {
            addCriterion("ZIP_CODE <", value, "zipCode");
            return (Criteria) this;
        }

        public Criteria andZipCodeLessThanOrEqualTo(String value) {
            addCriterion("ZIP_CODE <=", value, "zipCode");
            return (Criteria) this;
        }

        public Criteria andZipCodeLike(String value) {
            addCriterion("ZIP_CODE like", value, "zipCode");
            return (Criteria) this;
        }

        public Criteria andZipCodeNotLike(String value) {
            addCriterion("ZIP_CODE not like", value, "zipCode");
            return (Criteria) this;
        }

        public Criteria andZipCodeIn(List<String> values) {
            addCriterion("ZIP_CODE in", values, "zipCode");
            return (Criteria) this;
        }

        public Criteria andZipCodeNotIn(List<String> values) {
            addCriterion("ZIP_CODE not in", values, "zipCode");
            return (Criteria) this;
        }

        public Criteria andZipCodeBetween(String value1, String value2) {
            addCriterion("ZIP_CODE between", value1, value2, "zipCode");
            return (Criteria) this;
        }

        public Criteria andZipCodeNotBetween(String value1, String value2) {
            addCriterion("ZIP_CODE not between", value1, value2, "zipCode");
            return (Criteria) this;
        }

        public Criteria andContactsIsNull() {
            addCriterion("CONTACTS is null");
            return (Criteria) this;
        }

        public Criteria andContactsIsNotNull() {
            addCriterion("CONTACTS is not null");
            return (Criteria) this;
        }

        public Criteria andContactsEqualTo(String value) {
            addCriterion("CONTACTS =", value, "contacts");
            return (Criteria) this;
        }

        public Criteria andContactsNotEqualTo(String value) {
            addCriterion("CONTACTS <>", value, "contacts");
            return (Criteria) this;
        }

        public Criteria andContactsGreaterThan(String value) {
            addCriterion("CONTACTS >", value, "contacts");
            return (Criteria) this;
        }

        public Criteria andContactsGreaterThanOrEqualTo(String value) {
            addCriterion("CONTACTS >=", value, "contacts");
            return (Criteria) this;
        }

        public Criteria andContactsLessThan(String value) {
            addCriterion("CONTACTS <", value, "contacts");
            return (Criteria) this;
        }

        public Criteria andContactsLessThanOrEqualTo(String value) {
            addCriterion("CONTACTS <=", value, "contacts");
            return (Criteria) this;
        }

        public Criteria andContactsLike(String value) {
            addCriterion("CONTACTS like", value, "contacts");
            return (Criteria) this;
        }

        public Criteria andContactsNotLike(String value) {
            addCriterion("CONTACTS not like", value, "contacts");
            return (Criteria) this;
        }

        public Criteria andContactsIn(List<String> values) {
            addCriterion("CONTACTS in", values, "contacts");
            return (Criteria) this;
        }

        public Criteria andContactsNotIn(List<String> values) {
            addCriterion("CONTACTS not in", values, "contacts");
            return (Criteria) this;
        }

        public Criteria andContactsBetween(String value1, String value2) {
            addCriterion("CONTACTS between", value1, value2, "contacts");
            return (Criteria) this;
        }

        public Criteria andContactsNotBetween(String value1, String value2) {
            addCriterion("CONTACTS not between", value1, value2, "contacts");
            return (Criteria) this;
        }

        public Criteria andContactPhoneIsNull() {
            addCriterion("CONTACT_PHONE is null");
            return (Criteria) this;
        }

        public Criteria andContactPhoneIsNotNull() {
            addCriterion("CONTACT_PHONE is not null");
            return (Criteria) this;
        }

        public Criteria andContactPhoneEqualTo(String value) {
            addCriterion("CONTACT_PHONE =", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneNotEqualTo(String value) {
            addCriterion("CONTACT_PHONE <>", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneGreaterThan(String value) {
            addCriterion("CONTACT_PHONE >", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("CONTACT_PHONE >=", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneLessThan(String value) {
            addCriterion("CONTACT_PHONE <", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneLessThanOrEqualTo(String value) {
            addCriterion("CONTACT_PHONE <=", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneLike(String value) {
            addCriterion("CONTACT_PHONE like", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneNotLike(String value) {
            addCriterion("CONTACT_PHONE not like", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneIn(List<String> values) {
            addCriterion("CONTACT_PHONE in", values, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneNotIn(List<String> values) {
            addCriterion("CONTACT_PHONE not in", values, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneBetween(String value1, String value2) {
            addCriterion("CONTACT_PHONE between", value1, value2,
                    "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneNotBetween(String value1, String value2) {
            addCriterion("CONTACT_PHONE not between", value1, value2,
                    "contactPhone");
            return (Criteria) this;
        }

        public Criteria andEMailIsNull() {
            addCriterion("E_MAIL is null");
            return (Criteria) this;
        }

        public Criteria andEMailIsNotNull() {
            addCriterion("E_MAIL is not null");
            return (Criteria) this;
        }

        public Criteria andEMailEqualTo(String value) {
            addCriterion("E_MAIL =", value, "eMail");
            return (Criteria) this;
        }

        public Criteria andEMailNotEqualTo(String value) {
            addCriterion("E_MAIL <>", value, "eMail");
            return (Criteria) this;
        }

        public Criteria andEMailGreaterThan(String value) {
            addCriterion("E_MAIL >", value, "eMail");
            return (Criteria) this;
        }

        public Criteria andEMailGreaterThanOrEqualTo(String value) {
            addCriterion("E_MAIL >=", value, "eMail");
            return (Criteria) this;
        }

        public Criteria andEMailLessThan(String value) {
            addCriterion("E_MAIL <", value, "eMail");
            return (Criteria) this;
        }

        public Criteria andEMailLessThanOrEqualTo(String value) {
            addCriterion("E_MAIL <=", value, "eMail");
            return (Criteria) this;
        }

        public Criteria andEMailLike(String value) {
            addCriterion("E_MAIL like", value, "eMail");
            return (Criteria) this;
        }

        public Criteria andEMailNotLike(String value) {
            addCriterion("E_MAIL not like", value, "eMail");
            return (Criteria) this;
        }

        public Criteria andEMailIn(List<String> values) {
            addCriterion("E_MAIL in", values, "eMail");
            return (Criteria) this;
        }

        public Criteria andEMailNotIn(List<String> values) {
            addCriterion("E_MAIL not in", values, "eMail");
            return (Criteria) this;
        }

        public Criteria andEMailBetween(String value1, String value2) {
            addCriterion("E_MAIL between", value1, value2, "eMail");
            return (Criteria) this;
        }

        public Criteria andEMailNotBetween(String value1, String value2) {
            addCriterion("E_MAIL not between", value1, value2, "eMail");
            return (Criteria) this;
        }

        public Criteria andFaxPhoneIsNull() {
            addCriterion("FAX_PHONE is null");
            return (Criteria) this;
        }

        public Criteria andFaxPhoneIsNotNull() {
            addCriterion("FAX_PHONE is not null");
            return (Criteria) this;
        }

        public Criteria andFaxPhoneEqualTo(String value) {
            addCriterion("FAX_PHONE =", value, "faxPhone");
            return (Criteria) this;
        }

        public Criteria andFaxPhoneNotEqualTo(String value) {
            addCriterion("FAX_PHONE <>", value, "faxPhone");
            return (Criteria) this;
        }

        public Criteria andFaxPhoneGreaterThan(String value) {
            addCriterion("FAX_PHONE >", value, "faxPhone");
            return (Criteria) this;
        }

        public Criteria andFaxPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("FAX_PHONE >=", value, "faxPhone");
            return (Criteria) this;
        }

        public Criteria andFaxPhoneLessThan(String value) {
            addCriterion("FAX_PHONE <", value, "faxPhone");
            return (Criteria) this;
        }

        public Criteria andFaxPhoneLessThanOrEqualTo(String value) {
            addCriterion("FAX_PHONE <=", value, "faxPhone");
            return (Criteria) this;
        }

        public Criteria andFaxPhoneLike(String value) {
            addCriterion("FAX_PHONE like", value, "faxPhone");
            return (Criteria) this;
        }

        public Criteria andFaxPhoneNotLike(String value) {
            addCriterion("FAX_PHONE not like", value, "faxPhone");
            return (Criteria) this;
        }

        public Criteria andFaxPhoneIn(List<String> values) {
            addCriterion("FAX_PHONE in", values, "faxPhone");
            return (Criteria) this;
        }

        public Criteria andFaxPhoneNotIn(List<String> values) {
            addCriterion("FAX_PHONE not in", values, "faxPhone");
            return (Criteria) this;
        }

        public Criteria andFaxPhoneBetween(String value1, String value2) {
            addCriterion("FAX_PHONE between", value1, value2, "faxPhone");
            return (Criteria) this;
        }

        public Criteria andFaxPhoneNotBetween(String value1, String value2) {
            addCriterion("FAX_PHONE not between", value1, value2, "faxPhone");
            return (Criteria) this;
        }

        public Criteria andWebSiteIsNull() {
            addCriterion("WEB_SITE is null");
            return (Criteria) this;
        }

        public Criteria andWebSiteIsNotNull() {
            addCriterion("WEB_SITE is not null");
            return (Criteria) this;
        }

        public Criteria andWebSiteEqualTo(String value) {
            addCriterion("WEB_SITE =", value, "webSite");
            return (Criteria) this;
        }

        public Criteria andWebSiteNotEqualTo(String value) {
            addCriterion("WEB_SITE <>", value, "webSite");
            return (Criteria) this;
        }

        public Criteria andWebSiteGreaterThan(String value) {
            addCriterion("WEB_SITE >", value, "webSite");
            return (Criteria) this;
        }

        public Criteria andWebSiteGreaterThanOrEqualTo(String value) {
            addCriterion("WEB_SITE >=", value, "webSite");
            return (Criteria) this;
        }

        public Criteria andWebSiteLessThan(String value) {
            addCriterion("WEB_SITE <", value, "webSite");
            return (Criteria) this;
        }

        public Criteria andWebSiteLessThanOrEqualTo(String value) {
            addCriterion("WEB_SITE <=", value, "webSite");
            return (Criteria) this;
        }

        public Criteria andWebSiteLike(String value) {
            addCriterion("WEB_SITE like", value, "webSite");
            return (Criteria) this;
        }

        public Criteria andWebSiteNotLike(String value) {
            addCriterion("WEB_SITE not like", value, "webSite");
            return (Criteria) this;
        }

        public Criteria andWebSiteIn(List<String> values) {
            addCriterion("WEB_SITE in", values, "webSite");
            return (Criteria) this;
        }

        public Criteria andWebSiteNotIn(List<String> values) {
            addCriterion("WEB_SITE not in", values, "webSite");
            return (Criteria) this;
        }

        public Criteria andWebSiteBetween(String value1, String value2) {
            addCriterion("WEB_SITE between", value1, value2, "webSite");
            return (Criteria) this;
        }

        public Criteria andWebSiteNotBetween(String value1, String value2) {
            addCriterion("WEB_SITE not between", value1, value2, "webSite");
            return (Criteria) this;
        }

        public Criteria andEffectDateIsNull() {
            addCriterion("EFFECT_DATE is null");
            return (Criteria) this;
        }

        public Criteria andEffectDateIsNotNull() {
            addCriterion("EFFECT_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andEffectDateEqualTo(Date value) {
            addCriterionForJDBCDate("EFFECT_DATE =", value, "effectDate");
            return (Criteria) this;
        }

        public Criteria andEffectDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("EFFECT_DATE <>", value, "effectDate");
            return (Criteria) this;
        }

        public Criteria andEffectDateGreaterThan(Date value) {
            addCriterionForJDBCDate("EFFECT_DATE >", value, "effectDate");
            return (Criteria) this;
        }

        public Criteria andEffectDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("EFFECT_DATE >=", value, "effectDate");
            return (Criteria) this;
        }

        public Criteria andEffectDateLessThan(Date value) {
            addCriterionForJDBCDate("EFFECT_DATE <", value, "effectDate");
            return (Criteria) this;
        }

        public Criteria andEffectDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("EFFECT_DATE <=", value, "effectDate");
            return (Criteria) this;
        }

        public Criteria andEffectDateIn(List<Date> values) {
            addCriterionForJDBCDate("EFFECT_DATE in", values, "effectDate");
            return (Criteria) this;
        }

        public Criteria andEffectDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("EFFECT_DATE not in", values, "effectDate");
            return (Criteria) this;
        }

        public Criteria andEffectDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("EFFECT_DATE between", value1, value2,
                    "effectDate");
            return (Criteria) this;
        }

        public Criteria andEffectDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("EFFECT_DATE not between", value1, value2,
                    "effectDate");
            return (Criteria) this;
        }

        public Criteria andInvalidDateIsNull() {
            addCriterion("INVALID_DATE is null");
            return (Criteria) this;
        }

        public Criteria andInvalidDateIsNotNull() {
            addCriterion("INVALID_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andInvalidDateEqualTo(Date value) {
            addCriterionForJDBCDate("INVALID_DATE =", value, "invalidDate");
            return (Criteria) this;
        }

        public Criteria andInvalidDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("INVALID_DATE <>", value, "invalidDate");
            return (Criteria) this;
        }

        public Criteria andInvalidDateGreaterThan(Date value) {
            addCriterionForJDBCDate("INVALID_DATE >", value, "invalidDate");
            return (Criteria) this;
        }

        public Criteria andInvalidDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("INVALID_DATE >=", value, "invalidDate");
            return (Criteria) this;
        }

        public Criteria andInvalidDateLessThan(Date value) {
            addCriterionForJDBCDate("INVALID_DATE <", value, "invalidDate");
            return (Criteria) this;
        }

        public Criteria andInvalidDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("INVALID_DATE <=", value, "invalidDate");
            return (Criteria) this;
        }

        public Criteria andInvalidDateIn(List<Date> values) {
            addCriterionForJDBCDate("INVALID_DATE in", values, "invalidDate");
            return (Criteria) this;
        }

        public Criteria andInvalidDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("INVALID_DATE not in", values,
                    "invalidDate");
            return (Criteria) this;
        }

        public Criteria andInvalidDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("INVALID_DATE between", value1, value2,
                    "invalidDate");
            return (Criteria) this;
        }

        public Criteria andInvalidDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("INVALID_DATE not between", value1, value2,
                    "invalidDate");
            return (Criteria) this;
        }

        public Criteria andOrgStateIsNull() {
            addCriterion("ORG_STATE is null");
            return (Criteria) this;
        }

        public Criteria andOrgStateIsNotNull() {
            addCriterion("ORG_STATE is not null");
            return (Criteria) this;
        }

        public Criteria andOrgStateEqualTo(String value) {
            addCriterion("ORG_STATE =", value, "orgState");
            return (Criteria) this;
        }

        public Criteria andOrgStateNotEqualTo(String value) {
            addCriterion("ORG_STATE <>", value, "orgState");
            return (Criteria) this;
        }

        public Criteria andOrgStateGreaterThan(String value) {
            addCriterion("ORG_STATE >", value, "orgState");
            return (Criteria) this;
        }

        public Criteria andOrgStateGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_STATE >=", value, "orgState");
            return (Criteria) this;
        }

        public Criteria andOrgStateLessThan(String value) {
            addCriterion("ORG_STATE <", value, "orgState");
            return (Criteria) this;
        }

        public Criteria andOrgStateLessThanOrEqualTo(String value) {
            addCriterion("ORG_STATE <=", value, "orgState");
            return (Criteria) this;
        }

        public Criteria andOrgStateLike(String value) {
            addCriterion("ORG_STATE like", value, "orgState");
            return (Criteria) this;
        }

        public Criteria andOrgStateNotLike(String value) {
            addCriterion("ORG_STATE not like", value, "orgState");
            return (Criteria) this;
        }

        public Criteria andOrgStateIn(List<String> values) {
            addCriterion("ORG_STATE in", values, "orgState");
            return (Criteria) this;
        }

        public Criteria andOrgStateNotIn(List<String> values) {
            addCriterion("ORG_STATE not in", values, "orgState");
            return (Criteria) this;
        }

        public Criteria andOrgStateBetween(String value1, String value2) {
            addCriterion("ORG_STATE between", value1, value2, "orgState");
            return (Criteria) this;
        }

        public Criteria andOrgStateNotBetween(String value1, String value2) {
            addCriterion("ORG_STATE not between", value1, value2, "orgState");
            return (Criteria) this;
        }

        public Criteria andRegionIsNull() {
            addCriterion("REGION is null");
            return (Criteria) this;
        }

        public Criteria andRegionIsNotNull() {
            addCriterion("REGION is not null");
            return (Criteria) this;
        }

        public Criteria andRegionEqualTo(String value) {
            addCriterion("REGION =", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionNotEqualTo(String value) {
            addCriterion("REGION <>", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionGreaterThan(String value) {
            addCriterion("REGION >", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionGreaterThanOrEqualTo(String value) {
            addCriterion("REGION >=", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionLessThan(String value) {
            addCriterion("REGION <", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionLessThanOrEqualTo(String value) {
            addCriterion("REGION <=", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionLike(String value) {
            addCriterion("REGION like", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionNotLike(String value) {
            addCriterion("REGION not like", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionIn(List<String> values) {
            addCriterion("REGION in", values, "region");
            return (Criteria) this;
        }

        public Criteria andRegionNotIn(List<String> values) {
            addCriterion("REGION not in", values, "region");
            return (Criteria) this;
        }

        public Criteria andRegionBetween(String value1, String value2) {
            addCriterion("REGION between", value1, value2, "region");
            return (Criteria) this;
        }

        public Criteria andRegionNotBetween(String value1, String value2) {
            addCriterion("REGION not between", value1, value2, "region");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("REMARK is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("REMARK is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("REMARK =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("REMARK <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("REMARK >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("REMARK >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("REMARK <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("REMARK <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("REMARK like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("REMARK not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("REMARK in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("REMARK not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("REMARK between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("REMARK not between", value1, value2, "remark");
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
     * This class corresponds to the database table PF.T_PM_ORGANIZATION
     *
     * @mbggenerated do_not_delete_during_merge Thu May 28 15:01:22 CST 2015
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}