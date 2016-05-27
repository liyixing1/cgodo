package com.cgodo.server.entity;

import com.cgodo.constant.EnumStatus;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClientRequestSummaryEntityCondition {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table client_request_summary
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table client_request_summary
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table client_request_summary
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client_request_summary
     *
     * @mbggenerated
     */
    public ClientRequestSummaryEntityCondition() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client_request_summary
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client_request_summary
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client_request_summary
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client_request_summary
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client_request_summary
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client_request_summary
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client_request_summary
     *
     * @mbggenerated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client_request_summary
     *
     * @mbggenerated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client_request_summary
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client_request_summary
     *
     * @mbggenerated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * <ul>
     * <li>gmtCreated 创建时间</li>
     * <li>gmtUpdated 修改时间</li>
     * <li>status 数据状态，表示该数据对应的数据逻辑状态，如已删除，已修改等</li>
     * <li>version 该数据当前所属的版本，用来做事物控制</li>
     * <li>clientNumber 总客户数，每次请求，都被视为一次统计</li>
     * <li>currentDayNumber 当天客户数，每次请求，都被视为一次统计</li>
     * <li>summaryTime 统计当天时间</li>
     * <li>uv 当天UV，按站点统计，即当天进入站点的人数(IP)</li>
     * </ul>
     *
     * @mbggenerated
     */
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

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("ID like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("ID not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedIsNull() {
            addCriterion("GMT_CREATED is null");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedIsNotNull() {
            addCriterion("GMT_CREATED is not null");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedEqualTo(Date value) {
            addCriterion("GMT_CREATED =", value, "gmtCreated");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedNotEqualTo(Date value) {
            addCriterion("GMT_CREATED <>", value, "gmtCreated");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedGreaterThan(Date value) {
            addCriterion("GMT_CREATED >", value, "gmtCreated");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedGreaterThanOrEqualTo(Date value) {
            addCriterion("GMT_CREATED >=", value, "gmtCreated");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedLessThan(Date value) {
            addCriterion("GMT_CREATED <", value, "gmtCreated");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedLessThanOrEqualTo(Date value) {
            addCriterion("GMT_CREATED <=", value, "gmtCreated");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedIn(List<Date> values) {
            addCriterion("GMT_CREATED in", values, "gmtCreated");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedNotIn(List<Date> values) {
            addCriterion("GMT_CREATED not in", values, "gmtCreated");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedBetween(Date value1, Date value2) {
            addCriterion("GMT_CREATED between", value1, value2, "gmtCreated");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedNotBetween(Date value1, Date value2) {
            addCriterion("GMT_CREATED not between", value1, value2, "gmtCreated");
            return (Criteria) this;
        }

        public Criteria andGmtUpdatedIsNull() {
            addCriterion("GMT_UPDATED is null");
            return (Criteria) this;
        }

        public Criteria andGmtUpdatedIsNotNull() {
            addCriterion("GMT_UPDATED is not null");
            return (Criteria) this;
        }

        public Criteria andGmtUpdatedEqualTo(Date value) {
            addCriterion("GMT_UPDATED =", value, "gmtUpdated");
            return (Criteria) this;
        }

        public Criteria andGmtUpdatedNotEqualTo(Date value) {
            addCriterion("GMT_UPDATED <>", value, "gmtUpdated");
            return (Criteria) this;
        }

        public Criteria andGmtUpdatedGreaterThan(Date value) {
            addCriterion("GMT_UPDATED >", value, "gmtUpdated");
            return (Criteria) this;
        }

        public Criteria andGmtUpdatedGreaterThanOrEqualTo(Date value) {
            addCriterion("GMT_UPDATED >=", value, "gmtUpdated");
            return (Criteria) this;
        }

        public Criteria andGmtUpdatedLessThan(Date value) {
            addCriterion("GMT_UPDATED <", value, "gmtUpdated");
            return (Criteria) this;
        }

        public Criteria andGmtUpdatedLessThanOrEqualTo(Date value) {
            addCriterion("GMT_UPDATED <=", value, "gmtUpdated");
            return (Criteria) this;
        }

        public Criteria andGmtUpdatedIn(List<Date> values) {
            addCriterion("GMT_UPDATED in", values, "gmtUpdated");
            return (Criteria) this;
        }

        public Criteria andGmtUpdatedNotIn(List<Date> values) {
            addCriterion("GMT_UPDATED not in", values, "gmtUpdated");
            return (Criteria) this;
        }

        public Criteria andGmtUpdatedBetween(Date value1, Date value2) {
            addCriterion("GMT_UPDATED between", value1, value2, "gmtUpdated");
            return (Criteria) this;
        }

        public Criteria andGmtUpdatedNotBetween(Date value1, Date value2) {
            addCriterion("GMT_UPDATED not between", value1, value2, "gmtUpdated");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("STATUS is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(EnumStatus value) {
            addCriterion("STATUS =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(EnumStatus value) {
            addCriterion("STATUS <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(EnumStatus value) {
            addCriterion("STATUS >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(EnumStatus value) {
            addCriterion("STATUS >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(EnumStatus value) {
            addCriterion("STATUS <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(EnumStatus value) {
            addCriterion("STATUS <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(EnumStatus value) {
            addCriterion("STATUS like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(EnumStatus value) {
            addCriterion("STATUS not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<EnumStatus> values) {
            addCriterion("STATUS in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<EnumStatus> values) {
            addCriterion("STATUS not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(EnumStatus value1, EnumStatus value2) {
            addCriterion("STATUS between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(EnumStatus value1, EnumStatus value2) {
            addCriterion("STATUS not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andVersionIsNull() {
            addCriterion("VERSION is null");
            return (Criteria) this;
        }

        public Criteria andVersionIsNotNull() {
            addCriterion("VERSION is not null");
            return (Criteria) this;
        }

        public Criteria andVersionEqualTo(Long value) {
            addCriterion("VERSION =", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotEqualTo(Long value) {
            addCriterion("VERSION <>", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThan(Long value) {
            addCriterion("VERSION >", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThanOrEqualTo(Long value) {
            addCriterion("VERSION >=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThan(Long value) {
            addCriterion("VERSION <", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThanOrEqualTo(Long value) {
            addCriterion("VERSION <=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionIn(List<Long> values) {
            addCriterion("VERSION in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotIn(List<Long> values) {
            addCriterion("VERSION not in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionBetween(Long value1, Long value2) {
            addCriterion("VERSION between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotBetween(Long value1, Long value2) {
            addCriterion("VERSION not between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andClientNumberIsNull() {
            addCriterion("CLIENT_NUMBER is null");
            return (Criteria) this;
        }

        public Criteria andClientNumberIsNotNull() {
            addCriterion("CLIENT_NUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andClientNumberEqualTo(Integer value) {
            addCriterion("CLIENT_NUMBER =", value, "clientNumber");
            return (Criteria) this;
        }

        public Criteria andClientNumberNotEqualTo(Integer value) {
            addCriterion("CLIENT_NUMBER <>", value, "clientNumber");
            return (Criteria) this;
        }

        public Criteria andClientNumberGreaterThan(Integer value) {
            addCriterion("CLIENT_NUMBER >", value, "clientNumber");
            return (Criteria) this;
        }

        public Criteria andClientNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("CLIENT_NUMBER >=", value, "clientNumber");
            return (Criteria) this;
        }

        public Criteria andClientNumberLessThan(Integer value) {
            addCriterion("CLIENT_NUMBER <", value, "clientNumber");
            return (Criteria) this;
        }

        public Criteria andClientNumberLessThanOrEqualTo(Integer value) {
            addCriterion("CLIENT_NUMBER <=", value, "clientNumber");
            return (Criteria) this;
        }

        public Criteria andClientNumberIn(List<Integer> values) {
            addCriterion("CLIENT_NUMBER in", values, "clientNumber");
            return (Criteria) this;
        }

        public Criteria andClientNumberNotIn(List<Integer> values) {
            addCriterion("CLIENT_NUMBER not in", values, "clientNumber");
            return (Criteria) this;
        }

        public Criteria andClientNumberBetween(Integer value1, Integer value2) {
            addCriterion("CLIENT_NUMBER between", value1, value2, "clientNumber");
            return (Criteria) this;
        }

        public Criteria andClientNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("CLIENT_NUMBER not between", value1, value2, "clientNumber");
            return (Criteria) this;
        }

        public Criteria andCurrentDayNumberIsNull() {
            addCriterion("CURRENT_DAY_NUMBER is null");
            return (Criteria) this;
        }

        public Criteria andCurrentDayNumberIsNotNull() {
            addCriterion("CURRENT_DAY_NUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andCurrentDayNumberEqualTo(Integer value) {
            addCriterion("CURRENT_DAY_NUMBER =", value, "currentDayNumber");
            return (Criteria) this;
        }

        public Criteria andCurrentDayNumberNotEqualTo(Integer value) {
            addCriterion("CURRENT_DAY_NUMBER <>", value, "currentDayNumber");
            return (Criteria) this;
        }

        public Criteria andCurrentDayNumberGreaterThan(Integer value) {
            addCriterion("CURRENT_DAY_NUMBER >", value, "currentDayNumber");
            return (Criteria) this;
        }

        public Criteria andCurrentDayNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("CURRENT_DAY_NUMBER >=", value, "currentDayNumber");
            return (Criteria) this;
        }

        public Criteria andCurrentDayNumberLessThan(Integer value) {
            addCriterion("CURRENT_DAY_NUMBER <", value, "currentDayNumber");
            return (Criteria) this;
        }

        public Criteria andCurrentDayNumberLessThanOrEqualTo(Integer value) {
            addCriterion("CURRENT_DAY_NUMBER <=", value, "currentDayNumber");
            return (Criteria) this;
        }

        public Criteria andCurrentDayNumberIn(List<Integer> values) {
            addCriterion("CURRENT_DAY_NUMBER in", values, "currentDayNumber");
            return (Criteria) this;
        }

        public Criteria andCurrentDayNumberNotIn(List<Integer> values) {
            addCriterion("CURRENT_DAY_NUMBER not in", values, "currentDayNumber");
            return (Criteria) this;
        }

        public Criteria andCurrentDayNumberBetween(Integer value1, Integer value2) {
            addCriterion("CURRENT_DAY_NUMBER between", value1, value2, "currentDayNumber");
            return (Criteria) this;
        }

        public Criteria andCurrentDayNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("CURRENT_DAY_NUMBER not between", value1, value2, "currentDayNumber");
            return (Criteria) this;
        }

        public Criteria andSummaryTimeIsNull() {
            addCriterion("SUMMARY_TIME is null");
            return (Criteria) this;
        }

        public Criteria andSummaryTimeIsNotNull() {
            addCriterion("SUMMARY_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andSummaryTimeEqualTo(Date value) {
            addCriterion("SUMMARY_TIME =", value, "summaryTime");
            return (Criteria) this;
        }

        public Criteria andSummaryTimeNotEqualTo(Date value) {
            addCriterion("SUMMARY_TIME <>", value, "summaryTime");
            return (Criteria) this;
        }

        public Criteria andSummaryTimeGreaterThan(Date value) {
            addCriterion("SUMMARY_TIME >", value, "summaryTime");
            return (Criteria) this;
        }

        public Criteria andSummaryTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("SUMMARY_TIME >=", value, "summaryTime");
            return (Criteria) this;
        }

        public Criteria andSummaryTimeLessThan(Date value) {
            addCriterion("SUMMARY_TIME <", value, "summaryTime");
            return (Criteria) this;
        }

        public Criteria andSummaryTimeLessThanOrEqualTo(Date value) {
            addCriterion("SUMMARY_TIME <=", value, "summaryTime");
            return (Criteria) this;
        }

        public Criteria andSummaryTimeIn(List<Date> values) {
            addCriterion("SUMMARY_TIME in", values, "summaryTime");
            return (Criteria) this;
        }

        public Criteria andSummaryTimeNotIn(List<Date> values) {
            addCriterion("SUMMARY_TIME not in", values, "summaryTime");
            return (Criteria) this;
        }

        public Criteria andSummaryTimeBetween(Date value1, Date value2) {
            addCriterion("SUMMARY_TIME between", value1, value2, "summaryTime");
            return (Criteria) this;
        }

        public Criteria andSummaryTimeNotBetween(Date value1, Date value2) {
            addCriterion("SUMMARY_TIME not between", value1, value2, "summaryTime");
            return (Criteria) this;
        }

        public Criteria andUvIsNull() {
            addCriterion("UV is null");
            return (Criteria) this;
        }

        public Criteria andUvIsNotNull() {
            addCriterion("UV is not null");
            return (Criteria) this;
        }

        public Criteria andUvEqualTo(Integer value) {
            addCriterion("UV =", value, "uv");
            return (Criteria) this;
        }

        public Criteria andUvNotEqualTo(Integer value) {
            addCriterion("UV <>", value, "uv");
            return (Criteria) this;
        }

        public Criteria andUvGreaterThan(Integer value) {
            addCriterion("UV >", value, "uv");
            return (Criteria) this;
        }

        public Criteria andUvGreaterThanOrEqualTo(Integer value) {
            addCriterion("UV >=", value, "uv");
            return (Criteria) this;
        }

        public Criteria andUvLessThan(Integer value) {
            addCriterion("UV <", value, "uv");
            return (Criteria) this;
        }

        public Criteria andUvLessThanOrEqualTo(Integer value) {
            addCriterion("UV <=", value, "uv");
            return (Criteria) this;
        }

        public Criteria andUvIn(List<Integer> values) {
            addCriterion("UV in", values, "uv");
            return (Criteria) this;
        }

        public Criteria andUvNotIn(List<Integer> values) {
            addCriterion("UV not in", values, "uv");
            return (Criteria) this;
        }

        public Criteria andUvBetween(Integer value1, Integer value2) {
            addCriterion("UV between", value1, value2, "uv");
            return (Criteria) this;
        }

        public Criteria andUvNotBetween(Integer value1, Integer value2) {
            addCriterion("UV not between", value1, value2, "uv");
            return (Criteria) this;
        }

        public Criteria andIdLikeInsensitive(String value) {
            addCriterion("upper(ID) like", value.toUpperCase(), "id");
            return (Criteria) this;
        }
    }

    /**
     * <ul>
     * <li>gmtCreated 创建时间</li>
     * <li>gmtUpdated 修改时间</li>
     * <li>status 数据状态，表示该数据对应的数据逻辑状态，如已删除，已修改等</li>
     * <li>version 该数据当前所属的版本，用来做事物控制</li>
     * <li>clientNumber 总客户数，每次请求，都被视为一次统计</li>
     * <li>currentDayNumber 当天客户数，每次请求，都被视为一次统计</li>
     * <li>summaryTime 统计当天时间</li>
     * <li>uv 当天UV，按站点统计，即当天进入站点的人数(IP)</li>
     * </ul>
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * <ul>
     * <li>gmtCreated 创建时间</li>
     * <li>gmtUpdated 修改时间</li>
     * <li>status 数据状态，表示该数据对应的数据逻辑状态，如已删除，已修改等</li>
     * <li>version 该数据当前所属的版本，用来做事物控制</li>
     * <li>clientNumber 总客户数，每次请求，都被视为一次统计</li>
     * <li>currentDayNumber 当天客户数，每次请求，都被视为一次统计</li>
     * <li>summaryTime 统计当天时间</li>
     * <li>uv 当天UV，按站点统计，即当天进入站点的人数(IP)</li>
     * </ul>
     *
     * @mbggenerated
     */
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

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
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
}