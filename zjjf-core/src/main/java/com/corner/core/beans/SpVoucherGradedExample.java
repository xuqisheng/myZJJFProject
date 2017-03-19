package com.corner.core.beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SpVoucherGradedExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SpVoucherGradedExample() {
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
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andActiveIdIsNull() {
            addCriterion("activeId is null");
            return (Criteria) this;
        }

        public Criteria andActiveIdIsNotNull() {
            addCriterion("activeId is not null");
            return (Criteria) this;
        }

        public Criteria andActiveIdEqualTo(Integer value) {
            addCriterion("activeId =", value, "activeId");
            return (Criteria) this;
        }

        public Criteria andActiveIdNotEqualTo(Integer value) {
            addCriterion("activeId <>", value, "activeId");
            return (Criteria) this;
        }

        public Criteria andActiveIdGreaterThan(Integer value) {
            addCriterion("activeId >", value, "activeId");
            return (Criteria) this;
        }

        public Criteria andActiveIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("activeId >=", value, "activeId");
            return (Criteria) this;
        }

        public Criteria andActiveIdLessThan(Integer value) {
            addCriterion("activeId <", value, "activeId");
            return (Criteria) this;
        }

        public Criteria andActiveIdLessThanOrEqualTo(Integer value) {
            addCriterion("activeId <=", value, "activeId");
            return (Criteria) this;
        }

        public Criteria andActiveIdIn(List<Integer> values) {
            addCriterion("activeId in", values, "activeId");
            return (Criteria) this;
        }

        public Criteria andActiveIdNotIn(List<Integer> values) {
            addCriterion("activeId not in", values, "activeId");
            return (Criteria) this;
        }

        public Criteria andActiveIdBetween(Integer value1, Integer value2) {
            addCriterion("activeId between", value1, value2, "activeId");
            return (Criteria) this;
        }

        public Criteria andActiveIdNotBetween(Integer value1, Integer value2) {
            addCriterion("activeId not between", value1, value2, "activeId");
            return (Criteria) this;
        }

        public Criteria andTempIdIsNull() {
            addCriterion("tempId is null");
            return (Criteria) this;
        }

        public Criteria andTempIdIsNotNull() {
            addCriterion("tempId is not null");
            return (Criteria) this;
        }

        public Criteria andTempIdEqualTo(Integer value) {
            addCriterion("tempId =", value, "tempId");
            return (Criteria) this;
        }

        public Criteria andTempIdNotEqualTo(Integer value) {
            addCriterion("tempId <>", value, "tempId");
            return (Criteria) this;
        }

        public Criteria andTempIdGreaterThan(Integer value) {
            addCriterion("tempId >", value, "tempId");
            return (Criteria) this;
        }

        public Criteria andTempIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("tempId >=", value, "tempId");
            return (Criteria) this;
        }

        public Criteria andTempIdLessThan(Integer value) {
            addCriterion("tempId <", value, "tempId");
            return (Criteria) this;
        }

        public Criteria andTempIdLessThanOrEqualTo(Integer value) {
            addCriterion("tempId <=", value, "tempId");
            return (Criteria) this;
        }

        public Criteria andTempIdIn(List<Integer> values) {
            addCriterion("tempId in", values, "tempId");
            return (Criteria) this;
        }

        public Criteria andTempIdNotIn(List<Integer> values) {
            addCriterion("tempId not in", values, "tempId");
            return (Criteria) this;
        }

        public Criteria andTempIdBetween(Integer value1, Integer value2) {
            addCriterion("tempId between", value1, value2, "tempId");
            return (Criteria) this;
        }

        public Criteria andTempIdNotBetween(Integer value1, Integer value2) {
            addCriterion("tempId not between", value1, value2, "tempId");
            return (Criteria) this;
        }

        public Criteria andSendLimitIsNull() {
            addCriterion("sendLimit is null");
            return (Criteria) this;
        }

        public Criteria andSendLimitIsNotNull() {
            addCriterion("sendLimit is not null");
            return (Criteria) this;
        }

        public Criteria andSendLimitEqualTo(Byte value) {
            addCriterion("sendLimit =", value, "sendLimit");
            return (Criteria) this;
        }

        public Criteria andSendLimitNotEqualTo(Byte value) {
            addCriterion("sendLimit <>", value, "sendLimit");
            return (Criteria) this;
        }

        public Criteria andSendLimitGreaterThan(Byte value) {
            addCriterion("sendLimit >", value, "sendLimit");
            return (Criteria) this;
        }

        public Criteria andSendLimitGreaterThanOrEqualTo(Byte value) {
            addCriterion("sendLimit >=", value, "sendLimit");
            return (Criteria) this;
        }

        public Criteria andSendLimitLessThan(Byte value) {
            addCriterion("sendLimit <", value, "sendLimit");
            return (Criteria) this;
        }

        public Criteria andSendLimitLessThanOrEqualTo(Byte value) {
            addCriterion("sendLimit <=", value, "sendLimit");
            return (Criteria) this;
        }

        public Criteria andSendLimitIn(List<Byte> values) {
            addCriterion("sendLimit in", values, "sendLimit");
            return (Criteria) this;
        }

        public Criteria andSendLimitNotIn(List<Byte> values) {
            addCriterion("sendLimit not in", values, "sendLimit");
            return (Criteria) this;
        }

        public Criteria andSendLimitBetween(Byte value1, Byte value2) {
            addCriterion("sendLimit between", value1, value2, "sendLimit");
            return (Criteria) this;
        }

        public Criteria andSendLimitNotBetween(Byte value1, Byte value2) {
            addCriterion("sendLimit not between", value1, value2, "sendLimit");
            return (Criteria) this;
        }

        public Criteria andStartPriceIsNull() {
            addCriterion("startPrice is null");
            return (Criteria) this;
        }

        public Criteria andStartPriceIsNotNull() {
            addCriterion("startPrice is not null");
            return (Criteria) this;
        }

        public Criteria andStartPriceEqualTo(BigDecimal value) {
            addCriterion("startPrice =", value, "startPrice");
            return (Criteria) this;
        }

        public Criteria andStartPriceNotEqualTo(BigDecimal value) {
            addCriterion("startPrice <>", value, "startPrice");
            return (Criteria) this;
        }

        public Criteria andStartPriceGreaterThan(BigDecimal value) {
            addCriterion("startPrice >", value, "startPrice");
            return (Criteria) this;
        }

        public Criteria andStartPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("startPrice >=", value, "startPrice");
            return (Criteria) this;
        }

        public Criteria andStartPriceLessThan(BigDecimal value) {
            addCriterion("startPrice <", value, "startPrice");
            return (Criteria) this;
        }

        public Criteria andStartPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("startPrice <=", value, "startPrice");
            return (Criteria) this;
        }

        public Criteria andStartPriceIn(List<BigDecimal> values) {
            addCriterion("startPrice in", values, "startPrice");
            return (Criteria) this;
        }

        public Criteria andStartPriceNotIn(List<BigDecimal> values) {
            addCriterion("startPrice not in", values, "startPrice");
            return (Criteria) this;
        }

        public Criteria andStartPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("startPrice between", value1, value2, "startPrice");
            return (Criteria) this;
        }

        public Criteria andStartPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("startPrice not between", value1, value2, "startPrice");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
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