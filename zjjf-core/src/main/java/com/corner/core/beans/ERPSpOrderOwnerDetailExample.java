package com.corner.core.beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ERPSpOrderOwnerDetailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ERPSpOrderOwnerDetailExample() {
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

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andOrderInfoIdIsNull() {
            addCriterion("orderInfoId is null");
            return (Criteria) this;
        }

        public Criteria andOrderInfoIdIsNotNull() {
            addCriterion("orderInfoId is not null");
            return (Criteria) this;
        }

        public Criteria andOrderInfoIdEqualTo(String value) {
            addCriterion("orderInfoId =", value, "orderInfoId");
            return (Criteria) this;
        }

        public Criteria andOrderInfoIdNotEqualTo(String value) {
            addCriterion("orderInfoId <>", value, "orderInfoId");
            return (Criteria) this;
        }

        public Criteria andOrderInfoIdGreaterThan(String value) {
            addCriterion("orderInfoId >", value, "orderInfoId");
            return (Criteria) this;
        }

        public Criteria andOrderInfoIdGreaterThanOrEqualTo(String value) {
            addCriterion("orderInfoId >=", value, "orderInfoId");
            return (Criteria) this;
        }

        public Criteria andOrderInfoIdLessThan(String value) {
            addCriterion("orderInfoId <", value, "orderInfoId");
            return (Criteria) this;
        }

        public Criteria andOrderInfoIdLessThanOrEqualTo(String value) {
            addCriterion("orderInfoId <=", value, "orderInfoId");
            return (Criteria) this;
        }

        public Criteria andOrderInfoIdLike(String value) {
            addCriterion("orderInfoId like", value, "orderInfoId");
            return (Criteria) this;
        }

        public Criteria andOrderInfoIdNotLike(String value) {
            addCriterion("orderInfoId not like", value, "orderInfoId");
            return (Criteria) this;
        }

        public Criteria andOrderInfoIdIn(List<String> values) {
            addCriterion("orderInfoId in", values, "orderInfoId");
            return (Criteria) this;
        }

        public Criteria andOrderInfoIdNotIn(List<String> values) {
            addCriterion("orderInfoId not in", values, "orderInfoId");
            return (Criteria) this;
        }

        public Criteria andOrderInfoIdBetween(String value1, String value2) {
            addCriterion("orderInfoId between", value1, value2, "orderInfoId");
            return (Criteria) this;
        }

        public Criteria andOrderInfoIdNotBetween(String value1, String value2) {
            addCriterion("orderInfoId not between", value1, value2, "orderInfoId");
            return (Criteria) this;
        }

        public Criteria andOrderInfoPidIsNull() {
            addCriterion("orderInfoPid is null");
            return (Criteria) this;
        }

        public Criteria andOrderInfoPidIsNotNull() {
            addCriterion("orderInfoPid is not null");
            return (Criteria) this;
        }

        public Criteria andOrderInfoPidEqualTo(String value) {
            addCriterion("orderInfoPid =", value, "orderInfoPid");
            return (Criteria) this;
        }

        public Criteria andOrderInfoPidNotEqualTo(String value) {
            addCriterion("orderInfoPid <>", value, "orderInfoPid");
            return (Criteria) this;
        }

        public Criteria andOrderInfoPidGreaterThan(String value) {
            addCriterion("orderInfoPid >", value, "orderInfoPid");
            return (Criteria) this;
        }

        public Criteria andOrderInfoPidGreaterThanOrEqualTo(String value) {
            addCriterion("orderInfoPid >=", value, "orderInfoPid");
            return (Criteria) this;
        }

        public Criteria andOrderInfoPidLessThan(String value) {
            addCriterion("orderInfoPid <", value, "orderInfoPid");
            return (Criteria) this;
        }

        public Criteria andOrderInfoPidLessThanOrEqualTo(String value) {
            addCriterion("orderInfoPid <=", value, "orderInfoPid");
            return (Criteria) this;
        }

        public Criteria andOrderInfoPidLike(String value) {
            addCriterion("orderInfoPid like", value, "orderInfoPid");
            return (Criteria) this;
        }

        public Criteria andOrderInfoPidNotLike(String value) {
            addCriterion("orderInfoPid not like", value, "orderInfoPid");
            return (Criteria) this;
        }

        public Criteria andOrderInfoPidIn(List<String> values) {
            addCriterion("orderInfoPid in", values, "orderInfoPid");
            return (Criteria) this;
        }

        public Criteria andOrderInfoPidNotIn(List<String> values) {
            addCriterion("orderInfoPid not in", values, "orderInfoPid");
            return (Criteria) this;
        }

        public Criteria andOrderInfoPidBetween(String value1, String value2) {
            addCriterion("orderInfoPid between", value1, value2, "orderInfoPid");
            return (Criteria) this;
        }

        public Criteria andOrderInfoPidNotBetween(String value1, String value2) {
            addCriterion("orderInfoPid not between", value1, value2, "orderInfoPid");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNull() {
            addCriterion("orderId is null");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNotNull() {
            addCriterion("orderId is not null");
            return (Criteria) this;
        }

        public Criteria andOrderIdEqualTo(String value) {
            addCriterion("orderId =", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotEqualTo(String value) {
            addCriterion("orderId <>", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThan(String value) {
            addCriterion("orderId >", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThanOrEqualTo(String value) {
            addCriterion("orderId >=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThan(String value) {
            addCriterion("orderId <", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThanOrEqualTo(String value) {
            addCriterion("orderId <=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLike(String value) {
            addCriterion("orderId like", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotLike(String value) {
            addCriterion("orderId not like", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIn(List<String> values) {
            addCriterion("orderId in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotIn(List<String> values) {
            addCriterion("orderId not in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdBetween(String value1, String value2) {
            addCriterion("orderId between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotBetween(String value1, String value2) {
            addCriterion("orderId not between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andItemBaseIdIsNull() {
            addCriterion("itemBaseId is null");
            return (Criteria) this;
        }

        public Criteria andItemBaseIdIsNotNull() {
            addCriterion("itemBaseId is not null");
            return (Criteria) this;
        }

        public Criteria andItemBaseIdEqualTo(Integer value) {
            addCriterion("itemBaseId =", value, "itemBaseId");
            return (Criteria) this;
        }

        public Criteria andItemBaseIdNotEqualTo(Integer value) {
            addCriterion("itemBaseId <>", value, "itemBaseId");
            return (Criteria) this;
        }

        public Criteria andItemBaseIdGreaterThan(Integer value) {
            addCriterion("itemBaseId >", value, "itemBaseId");
            return (Criteria) this;
        }

        public Criteria andItemBaseIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("itemBaseId >=", value, "itemBaseId");
            return (Criteria) this;
        }

        public Criteria andItemBaseIdLessThan(Integer value) {
            addCriterion("itemBaseId <", value, "itemBaseId");
            return (Criteria) this;
        }

        public Criteria andItemBaseIdLessThanOrEqualTo(Integer value) {
            addCriterion("itemBaseId <=", value, "itemBaseId");
            return (Criteria) this;
        }

        public Criteria andItemBaseIdIn(List<Integer> values) {
            addCriterion("itemBaseId in", values, "itemBaseId");
            return (Criteria) this;
        }

        public Criteria andItemBaseIdNotIn(List<Integer> values) {
            addCriterion("itemBaseId not in", values, "itemBaseId");
            return (Criteria) this;
        }

        public Criteria andItemBaseIdBetween(Integer value1, Integer value2) {
            addCriterion("itemBaseId between", value1, value2, "itemBaseId");
            return (Criteria) this;
        }

        public Criteria andItemBaseIdNotBetween(Integer value1, Integer value2) {
            addCriterion("itemBaseId not between", value1, value2, "itemBaseId");
            return (Criteria) this;
        }

        public Criteria andBarCodeIsNull() {
            addCriterion("barCode is null");
            return (Criteria) this;
        }

        public Criteria andBarCodeIsNotNull() {
            addCriterion("barCode is not null");
            return (Criteria) this;
        }

        public Criteria andBarCodeEqualTo(String value) {
            addCriterion("barCode =", value, "barCode");
            return (Criteria) this;
        }

        public Criteria andBarCodeNotEqualTo(String value) {
            addCriterion("barCode <>", value, "barCode");
            return (Criteria) this;
        }

        public Criteria andBarCodeGreaterThan(String value) {
            addCriterion("barCode >", value, "barCode");
            return (Criteria) this;
        }

        public Criteria andBarCodeGreaterThanOrEqualTo(String value) {
            addCriterion("barCode >=", value, "barCode");
            return (Criteria) this;
        }

        public Criteria andBarCodeLessThan(String value) {
            addCriterion("barCode <", value, "barCode");
            return (Criteria) this;
        }

        public Criteria andBarCodeLessThanOrEqualTo(String value) {
            addCriterion("barCode <=", value, "barCode");
            return (Criteria) this;
        }

        public Criteria andBarCodeLike(String value) {
            addCriterion("barCode like", value, "barCode");
            return (Criteria) this;
        }

        public Criteria andBarCodeNotLike(String value) {
            addCriterion("barCode not like", value, "barCode");
            return (Criteria) this;
        }

        public Criteria andBarCodeIn(List<String> values) {
            addCriterion("barCode in", values, "barCode");
            return (Criteria) this;
        }

        public Criteria andBarCodeNotIn(List<String> values) {
            addCriterion("barCode not in", values, "barCode");
            return (Criteria) this;
        }

        public Criteria andBarCodeBetween(String value1, String value2) {
            addCriterion("barCode between", value1, value2, "barCode");
            return (Criteria) this;
        }

        public Criteria andBarCodeNotBetween(String value1, String value2) {
            addCriterion("barCode not between", value1, value2, "barCode");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andSpecIsNull() {
            addCriterion("spec is null");
            return (Criteria) this;
        }

        public Criteria andSpecIsNotNull() {
            addCriterion("spec is not null");
            return (Criteria) this;
        }

        public Criteria andSpecEqualTo(String value) {
            addCriterion("spec =", value, "spec");
            return (Criteria) this;
        }

        public Criteria andSpecNotEqualTo(String value) {
            addCriterion("spec <>", value, "spec");
            return (Criteria) this;
        }

        public Criteria andSpecGreaterThan(String value) {
            addCriterion("spec >", value, "spec");
            return (Criteria) this;
        }

        public Criteria andSpecGreaterThanOrEqualTo(String value) {
            addCriterion("spec >=", value, "spec");
            return (Criteria) this;
        }

        public Criteria andSpecLessThan(String value) {
            addCriterion("spec <", value, "spec");
            return (Criteria) this;
        }

        public Criteria andSpecLessThanOrEqualTo(String value) {
            addCriterion("spec <=", value, "spec");
            return (Criteria) this;
        }

        public Criteria andSpecLike(String value) {
            addCriterion("spec like", value, "spec");
            return (Criteria) this;
        }

        public Criteria andSpecNotLike(String value) {
            addCriterion("spec not like", value, "spec");
            return (Criteria) this;
        }

        public Criteria andSpecIn(List<String> values) {
            addCriterion("spec in", values, "spec");
            return (Criteria) this;
        }

        public Criteria andSpecNotIn(List<String> values) {
            addCriterion("spec not in", values, "spec");
            return (Criteria) this;
        }

        public Criteria andSpecBetween(String value1, String value2) {
            addCriterion("spec between", value1, value2, "spec");
            return (Criteria) this;
        }

        public Criteria andSpecNotBetween(String value1, String value2) {
            addCriterion("spec not between", value1, value2, "spec");
            return (Criteria) this;
        }

        public Criteria andPkgIsNull() {
            addCriterion("pkg is null");
            return (Criteria) this;
        }

        public Criteria andPkgIsNotNull() {
            addCriterion("pkg is not null");
            return (Criteria) this;
        }

        public Criteria andPkgEqualTo(String value) {
            addCriterion("pkg =", value, "pkg");
            return (Criteria) this;
        }

        public Criteria andPkgNotEqualTo(String value) {
            addCriterion("pkg <>", value, "pkg");
            return (Criteria) this;
        }

        public Criteria andPkgGreaterThan(String value) {
            addCriterion("pkg >", value, "pkg");
            return (Criteria) this;
        }

        public Criteria andPkgGreaterThanOrEqualTo(String value) {
            addCriterion("pkg >=", value, "pkg");
            return (Criteria) this;
        }

        public Criteria andPkgLessThan(String value) {
            addCriterion("pkg <", value, "pkg");
            return (Criteria) this;
        }

        public Criteria andPkgLessThanOrEqualTo(String value) {
            addCriterion("pkg <=", value, "pkg");
            return (Criteria) this;
        }

        public Criteria andPkgLike(String value) {
            addCriterion("pkg like", value, "pkg");
            return (Criteria) this;
        }

        public Criteria andPkgNotLike(String value) {
            addCriterion("pkg not like", value, "pkg");
            return (Criteria) this;
        }

        public Criteria andPkgIn(List<String> values) {
            addCriterion("pkg in", values, "pkg");
            return (Criteria) this;
        }

        public Criteria andPkgNotIn(List<String> values) {
            addCriterion("pkg not in", values, "pkg");
            return (Criteria) this;
        }

        public Criteria andPkgBetween(String value1, String value2) {
            addCriterion("pkg between", value1, value2, "pkg");
            return (Criteria) this;
        }

        public Criteria andPkgNotBetween(String value1, String value2) {
            addCriterion("pkg not between", value1, value2, "pkg");
            return (Criteria) this;
        }

        public Criteria andQuantityIsNull() {
            addCriterion("quantity is null");
            return (Criteria) this;
        }

        public Criteria andQuantityIsNotNull() {
            addCriterion("quantity is not null");
            return (Criteria) this;
        }

        public Criteria andQuantityEqualTo(Short value) {
            addCriterion("quantity =", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityNotEqualTo(Short value) {
            addCriterion("quantity <>", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityGreaterThan(Short value) {
            addCriterion("quantity >", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityGreaterThanOrEqualTo(Short value) {
            addCriterion("quantity >=", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityLessThan(Short value) {
            addCriterion("quantity <", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityLessThanOrEqualTo(Short value) {
            addCriterion("quantity <=", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityIn(List<Short> values) {
            addCriterion("quantity in", values, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityNotIn(List<Short> values) {
            addCriterion("quantity not in", values, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityBetween(Short value1, Short value2) {
            addCriterion("quantity between", value1, value2, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityNotBetween(Short value1, Short value2) {
            addCriterion("quantity not between", value1, value2, "quantity");
            return (Criteria) this;
        }

        public Criteria andCashQuantityIsNull() {
            addCriterion("cashQuantity is null");
            return (Criteria) this;
        }

        public Criteria andCashQuantityIsNotNull() {
            addCriterion("cashQuantity is not null");
            return (Criteria) this;
        }

        public Criteria andCashQuantityEqualTo(Short value) {
            addCriterion("cashQuantity =", value, "cashQuantity");
            return (Criteria) this;
        }

        public Criteria andCashQuantityNotEqualTo(Short value) {
            addCriterion("cashQuantity <>", value, "cashQuantity");
            return (Criteria) this;
        }

        public Criteria andCashQuantityGreaterThan(Short value) {
            addCriterion("cashQuantity >", value, "cashQuantity");
            return (Criteria) this;
        }

        public Criteria andCashQuantityGreaterThanOrEqualTo(Short value) {
            addCriterion("cashQuantity >=", value, "cashQuantity");
            return (Criteria) this;
        }

        public Criteria andCashQuantityLessThan(Short value) {
            addCriterion("cashQuantity <", value, "cashQuantity");
            return (Criteria) this;
        }

        public Criteria andCashQuantityLessThanOrEqualTo(Short value) {
            addCriterion("cashQuantity <=", value, "cashQuantity");
            return (Criteria) this;
        }

        public Criteria andCashQuantityIn(List<Short> values) {
            addCriterion("cashQuantity in", values, "cashQuantity");
            return (Criteria) this;
        }

        public Criteria andCashQuantityNotIn(List<Short> values) {
            addCriterion("cashQuantity not in", values, "cashQuantity");
            return (Criteria) this;
        }

        public Criteria andCashQuantityBetween(Short value1, Short value2) {
            addCriterion("cashQuantity between", value1, value2, "cashQuantity");
            return (Criteria) this;
        }

        public Criteria andCashQuantityNotBetween(Short value1, Short value2) {
            addCriterion("cashQuantity not between", value1, value2, "cashQuantity");
            return (Criteria) this;
        }

        public Criteria andCashPriceIsNull() {
            addCriterion("cashPrice is null");
            return (Criteria) this;
        }

        public Criteria andCashPriceIsNotNull() {
            addCriterion("cashPrice is not null");
            return (Criteria) this;
        }

        public Criteria andCashPriceEqualTo(BigDecimal value) {
            addCriterion("cashPrice =", value, "cashPrice");
            return (Criteria) this;
        }

        public Criteria andCashPriceNotEqualTo(BigDecimal value) {
            addCriterion("cashPrice <>", value, "cashPrice");
            return (Criteria) this;
        }

        public Criteria andCashPriceGreaterThan(BigDecimal value) {
            addCriterion("cashPrice >", value, "cashPrice");
            return (Criteria) this;
        }

        public Criteria andCashPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("cashPrice >=", value, "cashPrice");
            return (Criteria) this;
        }

        public Criteria andCashPriceLessThan(BigDecimal value) {
            addCriterion("cashPrice <", value, "cashPrice");
            return (Criteria) this;
        }

        public Criteria andCashPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("cashPrice <=", value, "cashPrice");
            return (Criteria) this;
        }

        public Criteria andCashPriceIn(List<BigDecimal> values) {
            addCriterion("cashPrice in", values, "cashPrice");
            return (Criteria) this;
        }

        public Criteria andCashPriceNotIn(List<BigDecimal> values) {
            addCriterion("cashPrice not in", values, "cashPrice");
            return (Criteria) this;
        }

        public Criteria andCashPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("cashPrice between", value1, value2, "cashPrice");
            return (Criteria) this;
        }

        public Criteria andCashPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("cashPrice not between", value1, value2, "cashPrice");
            return (Criteria) this;
        }

        public Criteria andBackQuantityIsNull() {
            addCriterion("backQuantity is null");
            return (Criteria) this;
        }

        public Criteria andBackQuantityIsNotNull() {
            addCriterion("backQuantity is not null");
            return (Criteria) this;
        }

        public Criteria andBackQuantityEqualTo(Short value) {
            addCriterion("backQuantity =", value, "backQuantity");
            return (Criteria) this;
        }

        public Criteria andBackQuantityNotEqualTo(Short value) {
            addCriterion("backQuantity <>", value, "backQuantity");
            return (Criteria) this;
        }

        public Criteria andBackQuantityGreaterThan(Short value) {
            addCriterion("backQuantity >", value, "backQuantity");
            return (Criteria) this;
        }

        public Criteria andBackQuantityGreaterThanOrEqualTo(Short value) {
            addCriterion("backQuantity >=", value, "backQuantity");
            return (Criteria) this;
        }

        public Criteria andBackQuantityLessThan(Short value) {
            addCriterion("backQuantity <", value, "backQuantity");
            return (Criteria) this;
        }

        public Criteria andBackQuantityLessThanOrEqualTo(Short value) {
            addCriterion("backQuantity <=", value, "backQuantity");
            return (Criteria) this;
        }

        public Criteria andBackQuantityIn(List<Short> values) {
            addCriterion("backQuantity in", values, "backQuantity");
            return (Criteria) this;
        }

        public Criteria andBackQuantityNotIn(List<Short> values) {
            addCriterion("backQuantity not in", values, "backQuantity");
            return (Criteria) this;
        }

        public Criteria andBackQuantityBetween(Short value1, Short value2) {
            addCriterion("backQuantity between", value1, value2, "backQuantity");
            return (Criteria) this;
        }

        public Criteria andBackQuantityNotBetween(Short value1, Short value2) {
            addCriterion("backQuantity not between", value1, value2, "backQuantity");
            return (Criteria) this;
        }

        public Criteria andSureQuantityIsNull() {
            addCriterion("sureQuantity is null");
            return (Criteria) this;
        }

        public Criteria andSureQuantityIsNotNull() {
            addCriterion("sureQuantity is not null");
            return (Criteria) this;
        }

        public Criteria andSureQuantityEqualTo(Short value) {
            addCriterion("sureQuantity =", value, "sureQuantity");
            return (Criteria) this;
        }

        public Criteria andSureQuantityNotEqualTo(Short value) {
            addCriterion("sureQuantity <>", value, "sureQuantity");
            return (Criteria) this;
        }

        public Criteria andSureQuantityGreaterThan(Short value) {
            addCriterion("sureQuantity >", value, "sureQuantity");
            return (Criteria) this;
        }

        public Criteria andSureQuantityGreaterThanOrEqualTo(Short value) {
            addCriterion("sureQuantity >=", value, "sureQuantity");
            return (Criteria) this;
        }

        public Criteria andSureQuantityLessThan(Short value) {
            addCriterion("sureQuantity <", value, "sureQuantity");
            return (Criteria) this;
        }

        public Criteria andSureQuantityLessThanOrEqualTo(Short value) {
            addCriterion("sureQuantity <=", value, "sureQuantity");
            return (Criteria) this;
        }

        public Criteria andSureQuantityIn(List<Short> values) {
            addCriterion("sureQuantity in", values, "sureQuantity");
            return (Criteria) this;
        }

        public Criteria andSureQuantityNotIn(List<Short> values) {
            addCriterion("sureQuantity not in", values, "sureQuantity");
            return (Criteria) this;
        }

        public Criteria andSureQuantityBetween(Short value1, Short value2) {
            addCriterion("sureQuantity between", value1, value2, "sureQuantity");
            return (Criteria) this;
        }

        public Criteria andSureQuantityNotBetween(Short value1, Short value2) {
            addCriterion("sureQuantity not between", value1, value2, "sureQuantity");
            return (Criteria) this;
        }

        public Criteria andSurePriceIsNull() {
            addCriterion("surePrice is null");
            return (Criteria) this;
        }

        public Criteria andSurePriceIsNotNull() {
            addCriterion("surePrice is not null");
            return (Criteria) this;
        }

        public Criteria andSurePriceEqualTo(BigDecimal value) {
            addCriterion("surePrice =", value, "surePrice");
            return (Criteria) this;
        }

        public Criteria andSurePriceNotEqualTo(BigDecimal value) {
            addCriterion("surePrice <>", value, "surePrice");
            return (Criteria) this;
        }

        public Criteria andSurePriceGreaterThan(BigDecimal value) {
            addCriterion("surePrice >", value, "surePrice");
            return (Criteria) this;
        }

        public Criteria andSurePriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("surePrice >=", value, "surePrice");
            return (Criteria) this;
        }

        public Criteria andSurePriceLessThan(BigDecimal value) {
            addCriterion("surePrice <", value, "surePrice");
            return (Criteria) this;
        }

        public Criteria andSurePriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("surePrice <=", value, "surePrice");
            return (Criteria) this;
        }

        public Criteria andSurePriceIn(List<BigDecimal> values) {
            addCriterion("surePrice in", values, "surePrice");
            return (Criteria) this;
        }

        public Criteria andSurePriceNotIn(List<BigDecimal> values) {
            addCriterion("surePrice not in", values, "surePrice");
            return (Criteria) this;
        }

        public Criteria andSurePriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("surePrice between", value1, value2, "surePrice");
            return (Criteria) this;
        }

        public Criteria andSurePriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("surePrice not between", value1, value2, "surePrice");
            return (Criteria) this;
        }

        public Criteria andOutStockNumIsNull() {
            addCriterion("outStockNum is null");
            return (Criteria) this;
        }

        public Criteria andOutStockNumIsNotNull() {
            addCriterion("outStockNum is not null");
            return (Criteria) this;
        }

        public Criteria andOutStockNumEqualTo(Short value) {
            addCriterion("outStockNum =", value, "outStockNum");
            return (Criteria) this;
        }

        public Criteria andOutStockNumNotEqualTo(Short value) {
            addCriterion("outStockNum <>", value, "outStockNum");
            return (Criteria) this;
        }

        public Criteria andOutStockNumGreaterThan(Short value) {
            addCriterion("outStockNum >", value, "outStockNum");
            return (Criteria) this;
        }

        public Criteria andOutStockNumGreaterThanOrEqualTo(Short value) {
            addCriterion("outStockNum >=", value, "outStockNum");
            return (Criteria) this;
        }

        public Criteria andOutStockNumLessThan(Short value) {
            addCriterion("outStockNum <", value, "outStockNum");
            return (Criteria) this;
        }

        public Criteria andOutStockNumLessThanOrEqualTo(Short value) {
            addCriterion("outStockNum <=", value, "outStockNum");
            return (Criteria) this;
        }

        public Criteria andOutStockNumIn(List<Short> values) {
            addCriterion("outStockNum in", values, "outStockNum");
            return (Criteria) this;
        }

        public Criteria andOutStockNumNotIn(List<Short> values) {
            addCriterion("outStockNum not in", values, "outStockNum");
            return (Criteria) this;
        }

        public Criteria andOutStockNumBetween(Short value1, Short value2) {
            addCriterion("outStockNum between", value1, value2, "outStockNum");
            return (Criteria) this;
        }

        public Criteria andOutStockNumNotBetween(Short value1, Short value2) {
            addCriterion("outStockNum not between", value1, value2, "outStockNum");
            return (Criteria) this;
        }

        public Criteria andWh3IdIsNull() {
            addCriterion("wh3Id is null");
            return (Criteria) this;
        }

        public Criteria andWh3IdIsNotNull() {
            addCriterion("wh3Id is not null");
            return (Criteria) this;
        }

        public Criteria andWh3IdEqualTo(String value) {
            addCriterion("wh3Id =", value, "wh3Id");
            return (Criteria) this;
        }

        public Criteria andWh3IdNotEqualTo(String value) {
            addCriterion("wh3Id <>", value, "wh3Id");
            return (Criteria) this;
        }

        public Criteria andWh3IdGreaterThan(String value) {
            addCriterion("wh3Id >", value, "wh3Id");
            return (Criteria) this;
        }

        public Criteria andWh3IdGreaterThanOrEqualTo(String value) {
            addCriterion("wh3Id >=", value, "wh3Id");
            return (Criteria) this;
        }

        public Criteria andWh3IdLessThan(String value) {
            addCriterion("wh3Id <", value, "wh3Id");
            return (Criteria) this;
        }

        public Criteria andWh3IdLessThanOrEqualTo(String value) {
            addCriterion("wh3Id <=", value, "wh3Id");
            return (Criteria) this;
        }

        public Criteria andWh3IdLike(String value) {
            addCriterion("wh3Id like", value, "wh3Id");
            return (Criteria) this;
        }

        public Criteria andWh3IdNotLike(String value) {
            addCriterion("wh3Id not like", value, "wh3Id");
            return (Criteria) this;
        }

        public Criteria andWh3IdIn(List<String> values) {
            addCriterion("wh3Id in", values, "wh3Id");
            return (Criteria) this;
        }

        public Criteria andWh3IdNotIn(List<String> values) {
            addCriterion("wh3Id not in", values, "wh3Id");
            return (Criteria) this;
        }

        public Criteria andWh3IdBetween(String value1, String value2) {
            addCriterion("wh3Id between", value1, value2, "wh3Id");
            return (Criteria) this;
        }

        public Criteria andWh3IdNotBetween(String value1, String value2) {
            addCriterion("wh3Id not between", value1, value2, "wh3Id");
            return (Criteria) this;
        }

        public Criteria andWh3NameIsNull() {
            addCriterion("wh3Name is null");
            return (Criteria) this;
        }

        public Criteria andWh3NameIsNotNull() {
            addCriterion("wh3Name is not null");
            return (Criteria) this;
        }

        public Criteria andWh3NameEqualTo(String value) {
            addCriterion("wh3Name =", value, "wh3Name");
            return (Criteria) this;
        }

        public Criteria andWh3NameNotEqualTo(String value) {
            addCriterion("wh3Name <>", value, "wh3Name");
            return (Criteria) this;
        }

        public Criteria andWh3NameGreaterThan(String value) {
            addCriterion("wh3Name >", value, "wh3Name");
            return (Criteria) this;
        }

        public Criteria andWh3NameGreaterThanOrEqualTo(String value) {
            addCriterion("wh3Name >=", value, "wh3Name");
            return (Criteria) this;
        }

        public Criteria andWh3NameLessThan(String value) {
            addCriterion("wh3Name <", value, "wh3Name");
            return (Criteria) this;
        }

        public Criteria andWh3NameLessThanOrEqualTo(String value) {
            addCriterion("wh3Name <=", value, "wh3Name");
            return (Criteria) this;
        }

        public Criteria andWh3NameLike(String value) {
            addCriterion("wh3Name like", value, "wh3Name");
            return (Criteria) this;
        }

        public Criteria andWh3NameNotLike(String value) {
            addCriterion("wh3Name not like", value, "wh3Name");
            return (Criteria) this;
        }

        public Criteria andWh3NameIn(List<String> values) {
            addCriterion("wh3Name in", values, "wh3Name");
            return (Criteria) this;
        }

        public Criteria andWh3NameNotIn(List<String> values) {
            addCriterion("wh3Name not in", values, "wh3Name");
            return (Criteria) this;
        }

        public Criteria andWh3NameBetween(String value1, String value2) {
            addCriterion("wh3Name between", value1, value2, "wh3Name");
            return (Criteria) this;
        }

        public Criteria andWh3NameNotBetween(String value1, String value2) {
            addCriterion("wh3Name not between", value1, value2, "wh3Name");
            return (Criteria) this;
        }

        public Criteria andWh2NameIsNull() {
            addCriterion("wh2Name is null");
            return (Criteria) this;
        }

        public Criteria andWh2NameIsNotNull() {
            addCriterion("wh2Name is not null");
            return (Criteria) this;
        }

        public Criteria andWh2NameEqualTo(String value) {
            addCriterion("wh2Name =", value, "wh2Name");
            return (Criteria) this;
        }

        public Criteria andWh2NameNotEqualTo(String value) {
            addCriterion("wh2Name <>", value, "wh2Name");
            return (Criteria) this;
        }

        public Criteria andWh2NameGreaterThan(String value) {
            addCriterion("wh2Name >", value, "wh2Name");
            return (Criteria) this;
        }

        public Criteria andWh2NameGreaterThanOrEqualTo(String value) {
            addCriterion("wh2Name >=", value, "wh2Name");
            return (Criteria) this;
        }

        public Criteria andWh2NameLessThan(String value) {
            addCriterion("wh2Name <", value, "wh2Name");
            return (Criteria) this;
        }

        public Criteria andWh2NameLessThanOrEqualTo(String value) {
            addCriterion("wh2Name <=", value, "wh2Name");
            return (Criteria) this;
        }

        public Criteria andWh2NameLike(String value) {
            addCriterion("wh2Name like", value, "wh2Name");
            return (Criteria) this;
        }

        public Criteria andWh2NameNotLike(String value) {
            addCriterion("wh2Name not like", value, "wh2Name");
            return (Criteria) this;
        }

        public Criteria andWh2NameIn(List<String> values) {
            addCriterion("wh2Name in", values, "wh2Name");
            return (Criteria) this;
        }

        public Criteria andWh2NameNotIn(List<String> values) {
            addCriterion("wh2Name not in", values, "wh2Name");
            return (Criteria) this;
        }

        public Criteria andWh2NameBetween(String value1, String value2) {
            addCriterion("wh2Name between", value1, value2, "wh2Name");
            return (Criteria) this;
        }

        public Criteria andWh2NameNotBetween(String value1, String value2) {
            addCriterion("wh2Name not between", value1, value2, "wh2Name");
            return (Criteria) this;
        }

        public Criteria andWh1IdIsNull() {
            addCriterion("wh1Id is null");
            return (Criteria) this;
        }

        public Criteria andWh1IdIsNotNull() {
            addCriterion("wh1Id is not null");
            return (Criteria) this;
        }

        public Criteria andWh1IdEqualTo(String value) {
            addCriterion("wh1Id =", value, "wh1Id");
            return (Criteria) this;
        }

        public Criteria andWh1IdNotEqualTo(String value) {
            addCriterion("wh1Id <>", value, "wh1Id");
            return (Criteria) this;
        }

        public Criteria andWh1IdGreaterThan(String value) {
            addCriterion("wh1Id >", value, "wh1Id");
            return (Criteria) this;
        }

        public Criteria andWh1IdGreaterThanOrEqualTo(String value) {
            addCriterion("wh1Id >=", value, "wh1Id");
            return (Criteria) this;
        }

        public Criteria andWh1IdLessThan(String value) {
            addCriterion("wh1Id <", value, "wh1Id");
            return (Criteria) this;
        }

        public Criteria andWh1IdLessThanOrEqualTo(String value) {
            addCriterion("wh1Id <=", value, "wh1Id");
            return (Criteria) this;
        }

        public Criteria andWh1IdLike(String value) {
            addCriterion("wh1Id like", value, "wh1Id");
            return (Criteria) this;
        }

        public Criteria andWh1IdNotLike(String value) {
            addCriterion("wh1Id not like", value, "wh1Id");
            return (Criteria) this;
        }

        public Criteria andWh1IdIn(List<String> values) {
            addCriterion("wh1Id in", values, "wh1Id");
            return (Criteria) this;
        }

        public Criteria andWh1IdNotIn(List<String> values) {
            addCriterion("wh1Id not in", values, "wh1Id");
            return (Criteria) this;
        }

        public Criteria andWh1IdBetween(String value1, String value2) {
            addCriterion("wh1Id between", value1, value2, "wh1Id");
            return (Criteria) this;
        }

        public Criteria andWh1IdNotBetween(String value1, String value2) {
            addCriterion("wh1Id not between", value1, value2, "wh1Id");
            return (Criteria) this;
        }

        public Criteria andWh1NameIsNull() {
            addCriterion("wh1Name is null");
            return (Criteria) this;
        }

        public Criteria andWh1NameIsNotNull() {
            addCriterion("wh1Name is not null");
            return (Criteria) this;
        }

        public Criteria andWh1NameEqualTo(String value) {
            addCriterion("wh1Name =", value, "wh1Name");
            return (Criteria) this;
        }

        public Criteria andWh1NameNotEqualTo(String value) {
            addCriterion("wh1Name <>", value, "wh1Name");
            return (Criteria) this;
        }

        public Criteria andWh1NameGreaterThan(String value) {
            addCriterion("wh1Name >", value, "wh1Name");
            return (Criteria) this;
        }

        public Criteria andWh1NameGreaterThanOrEqualTo(String value) {
            addCriterion("wh1Name >=", value, "wh1Name");
            return (Criteria) this;
        }

        public Criteria andWh1NameLessThan(String value) {
            addCriterion("wh1Name <", value, "wh1Name");
            return (Criteria) this;
        }

        public Criteria andWh1NameLessThanOrEqualTo(String value) {
            addCriterion("wh1Name <=", value, "wh1Name");
            return (Criteria) this;
        }

        public Criteria andWh1NameLike(String value) {
            addCriterion("wh1Name like", value, "wh1Name");
            return (Criteria) this;
        }

        public Criteria andWh1NameNotLike(String value) {
            addCriterion("wh1Name not like", value, "wh1Name");
            return (Criteria) this;
        }

        public Criteria andWh1NameIn(List<String> values) {
            addCriterion("wh1Name in", values, "wh1Name");
            return (Criteria) this;
        }

        public Criteria andWh1NameNotIn(List<String> values) {
            addCriterion("wh1Name not in", values, "wh1Name");
            return (Criteria) this;
        }

        public Criteria andWh1NameBetween(String value1, String value2) {
            addCriterion("wh1Name between", value1, value2, "wh1Name");
            return (Criteria) this;
        }

        public Criteria andWh1NameNotBetween(String value1, String value2) {
            addCriterion("wh1Name not between", value1, value2, "wh1Name");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Byte value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Byte value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Byte value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Byte value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Byte value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Byte> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Byte> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Byte value1, Byte value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andCashIsNull() {
            addCriterion("cash is null");
            return (Criteria) this;
        }

        public Criteria andCashIsNotNull() {
            addCriterion("cash is not null");
            return (Criteria) this;
        }

        public Criteria andCashEqualTo(Byte value) {
            addCriterion("cash =", value, "cash");
            return (Criteria) this;
        }

        public Criteria andCashNotEqualTo(Byte value) {
            addCriterion("cash <>", value, "cash");
            return (Criteria) this;
        }

        public Criteria andCashGreaterThan(Byte value) {
            addCriterion("cash >", value, "cash");
            return (Criteria) this;
        }

        public Criteria andCashGreaterThanOrEqualTo(Byte value) {
            addCriterion("cash >=", value, "cash");
            return (Criteria) this;
        }

        public Criteria andCashLessThan(Byte value) {
            addCriterion("cash <", value, "cash");
            return (Criteria) this;
        }

        public Criteria andCashLessThanOrEqualTo(Byte value) {
            addCriterion("cash <=", value, "cash");
            return (Criteria) this;
        }

        public Criteria andCashIn(List<Byte> values) {
            addCriterion("cash in", values, "cash");
            return (Criteria) this;
        }

        public Criteria andCashNotIn(List<Byte> values) {
            addCriterion("cash not in", values, "cash");
            return (Criteria) this;
        }

        public Criteria andCashBetween(Byte value1, Byte value2) {
            addCriterion("cash between", value1, value2, "cash");
            return (Criteria) this;
        }

        public Criteria andCashNotBetween(Byte value1, Byte value2) {
            addCriterion("cash not between", value1, value2, "cash");
            return (Criteria) this;
        }

        public Criteria andAddTimeIsNull() {
            addCriterion("addTime is null");
            return (Criteria) this;
        }

        public Criteria andAddTimeIsNotNull() {
            addCriterion("addTime is not null");
            return (Criteria) this;
        }

        public Criteria andAddTimeEqualTo(Date value) {
            addCriterion("addTime =", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotEqualTo(Date value) {
            addCriterion("addTime <>", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeGreaterThan(Date value) {
            addCriterion("addTime >", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("addTime >=", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeLessThan(Date value) {
            addCriterion("addTime <", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeLessThanOrEqualTo(Date value) {
            addCriterion("addTime <=", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeIn(List<Date> values) {
            addCriterion("addTime in", values, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotIn(List<Date> values) {
            addCriterion("addTime not in", values, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeBetween(Date value1, Date value2) {
            addCriterion("addTime between", value1, value2, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotBetween(Date value1, Date value2) {
            addCriterion("addTime not between", value1, value2, "addTime");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNull() {
            addCriterion("isDelete is null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNotNull() {
            addCriterion("isDelete is not null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteEqualTo(Boolean value) {
            addCriterion("isDelete =", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotEqualTo(Boolean value) {
            addCriterion("isDelete <>", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThan(Boolean value) {
            addCriterion("isDelete >", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThanOrEqualTo(Boolean value) {
            addCriterion("isDelete >=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThan(Boolean value) {
            addCriterion("isDelete <", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThanOrEqualTo(Boolean value) {
            addCriterion("isDelete <=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIn(List<Boolean> values) {
            addCriterion("isDelete in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotIn(List<Boolean> values) {
            addCriterion("isDelete not in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteBetween(Boolean value1, Boolean value2) {
            addCriterion("isDelete between", value1, value2, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotBetween(Boolean value1, Boolean value2) {
            addCriterion("isDelete not between", value1, value2, "isDelete");
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