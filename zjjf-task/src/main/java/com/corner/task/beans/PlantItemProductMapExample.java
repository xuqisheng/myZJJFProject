package com.corner.task.beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PlantItemProductMapExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PlantItemProductMapExample() {
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

        public Criteria andProductIdIsNull() {
            addCriterion("productId is null");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNotNull() {
            addCriterion("productId is not null");
            return (Criteria) this;
        }

        public Criteria andProductIdEqualTo(String value) {
            addCriterion("productId =", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotEqualTo(String value) {
            addCriterion("productId <>", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThan(String value) {
            addCriterion("productId >", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThanOrEqualTo(String value) {
            addCriterion("productId >=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThan(String value) {
            addCriterion("productId <", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThanOrEqualTo(String value) {
            addCriterion("productId <=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLike(String value) {
            addCriterion("productId like", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotLike(String value) {
            addCriterion("productId not like", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdIn(List<String> values) {
            addCriterion("productId in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotIn(List<String> values) {
            addCriterion("productId not in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdBetween(String value1, String value2) {
            addCriterion("productId between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotBetween(String value1, String value2) {
            addCriterion("productId not between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andPlantItemIdIsNull() {
            addCriterion("plantItemId is null");
            return (Criteria) this;
        }

        public Criteria andPlantItemIdIsNotNull() {
            addCriterion("plantItemId is not null");
            return (Criteria) this;
        }

        public Criteria andPlantItemIdEqualTo(String value) {
            addCriterion("plantItemId =", value, "plantItemId");
            return (Criteria) this;
        }

        public Criteria andPlantItemIdNotEqualTo(String value) {
            addCriterion("plantItemId <>", value, "plantItemId");
            return (Criteria) this;
        }

        public Criteria andPlantItemIdGreaterThan(String value) {
            addCriterion("plantItemId >", value, "plantItemId");
            return (Criteria) this;
        }

        public Criteria andPlantItemIdGreaterThanOrEqualTo(String value) {
            addCriterion("plantItemId >=", value, "plantItemId");
            return (Criteria) this;
        }

        public Criteria andPlantItemIdLessThan(String value) {
            addCriterion("plantItemId <", value, "plantItemId");
            return (Criteria) this;
        }

        public Criteria andPlantItemIdLessThanOrEqualTo(String value) {
            addCriterion("plantItemId <=", value, "plantItemId");
            return (Criteria) this;
        }

        public Criteria andPlantItemIdLike(String value) {
            addCriterion("plantItemId like", value, "plantItemId");
            return (Criteria) this;
        }

        public Criteria andPlantItemIdNotLike(String value) {
            addCriterion("plantItemId not like", value, "plantItemId");
            return (Criteria) this;
        }

        public Criteria andPlantItemIdIn(List<String> values) {
            addCriterion("plantItemId in", values, "plantItemId");
            return (Criteria) this;
        }

        public Criteria andPlantItemIdNotIn(List<String> values) {
            addCriterion("plantItemId not in", values, "plantItemId");
            return (Criteria) this;
        }

        public Criteria andPlantItemIdBetween(String value1, String value2) {
            addCriterion("plantItemId between", value1, value2, "plantItemId");
            return (Criteria) this;
        }

        public Criteria andPlantItemIdNotBetween(String value1, String value2) {
            addCriterion("plantItemId not between", value1, value2, "plantItemId");
            return (Criteria) this;
        }

        public Criteria andPkgPriceIsNull() {
            addCriterion("pkgPrice is null");
            return (Criteria) this;
        }

        public Criteria andPkgPriceIsNotNull() {
            addCriterion("pkgPrice is not null");
            return (Criteria) this;
        }

        public Criteria andPkgPriceEqualTo(BigDecimal value) {
            addCriterion("pkgPrice =", value, "pkgPrice");
            return (Criteria) this;
        }

        public Criteria andPkgPriceNotEqualTo(BigDecimal value) {
            addCriterion("pkgPrice <>", value, "pkgPrice");
            return (Criteria) this;
        }

        public Criteria andPkgPriceGreaterThan(BigDecimal value) {
            addCriterion("pkgPrice >", value, "pkgPrice");
            return (Criteria) this;
        }

        public Criteria andPkgPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("pkgPrice >=", value, "pkgPrice");
            return (Criteria) this;
        }

        public Criteria andPkgPriceLessThan(BigDecimal value) {
            addCriterion("pkgPrice <", value, "pkgPrice");
            return (Criteria) this;
        }

        public Criteria andPkgPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("pkgPrice <=", value, "pkgPrice");
            return (Criteria) this;
        }

        public Criteria andPkgPriceIn(List<BigDecimal> values) {
            addCriterion("pkgPrice in", values, "pkgPrice");
            return (Criteria) this;
        }

        public Criteria andPkgPriceNotIn(List<BigDecimal> values) {
            addCriterion("pkgPrice not in", values, "pkgPrice");
            return (Criteria) this;
        }

        public Criteria andPkgPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("pkgPrice between", value1, value2, "pkgPrice");
            return (Criteria) this;
        }

        public Criteria andPkgPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("pkgPrice not between", value1, value2, "pkgPrice");
            return (Criteria) this;
        }

        public Criteria andNumIsNull() {
            addCriterion("num is null");
            return (Criteria) this;
        }

        public Criteria andNumIsNotNull() {
            addCriterion("num is not null");
            return (Criteria) this;
        }

        public Criteria andNumEqualTo(Integer value) {
            addCriterion("num =", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotEqualTo(Integer value) {
            addCriterion("num <>", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumGreaterThan(Integer value) {
            addCriterion("num >", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("num >=", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumLessThan(Integer value) {
            addCriterion("num <", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumLessThanOrEqualTo(Integer value) {
            addCriterion("num <=", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumIn(List<Integer> values) {
            addCriterion("num in", values, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotIn(List<Integer> values) {
            addCriterion("num not in", values, "num");
            return (Criteria) this;
        }

        public Criteria andNumBetween(Integer value1, Integer value2) {
            addCriterion("num between", value1, value2, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotBetween(Integer value1, Integer value2) {
            addCriterion("num not between", value1, value2, "num");
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