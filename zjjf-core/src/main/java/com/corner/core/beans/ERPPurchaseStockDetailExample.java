package com.corner.core.beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ERPPurchaseStockDetailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ERPPurchaseStockDetailExample() {
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

        public Criteria andPIdIsNull() {
            addCriterion("pId is null");
            return (Criteria) this;
        }

        public Criteria andPIdIsNotNull() {
            addCriterion("pId is not null");
            return (Criteria) this;
        }

        public Criteria andPIdEqualTo(String value) {
            addCriterion("pId =", value, "pId");
            return (Criteria) this;
        }

        public Criteria andPIdNotEqualTo(String value) {
            addCriterion("pId <>", value, "pId");
            return (Criteria) this;
        }

        public Criteria andPIdGreaterThan(String value) {
            addCriterion("pId >", value, "pId");
            return (Criteria) this;
        }

        public Criteria andPIdGreaterThanOrEqualTo(String value) {
            addCriterion("pId >=", value, "pId");
            return (Criteria) this;
        }

        public Criteria andPIdLessThan(String value) {
            addCriterion("pId <", value, "pId");
            return (Criteria) this;
        }

        public Criteria andPIdLessThanOrEqualTo(String value) {
            addCriterion("pId <=", value, "pId");
            return (Criteria) this;
        }

        public Criteria andPIdLike(String value) {
            addCriterion("pId like", value, "pId");
            return (Criteria) this;
        }

        public Criteria andPIdNotLike(String value) {
            addCriterion("pId not like", value, "pId");
            return (Criteria) this;
        }

        public Criteria andPIdIn(List<String> values) {
            addCriterion("pId in", values, "pId");
            return (Criteria) this;
        }

        public Criteria andPIdNotIn(List<String> values) {
            addCriterion("pId not in", values, "pId");
            return (Criteria) this;
        }

        public Criteria andPIdBetween(String value1, String value2) {
            addCriterion("pId between", value1, value2, "pId");
            return (Criteria) this;
        }

        public Criteria andPIdNotBetween(String value1, String value2) {
            addCriterion("pId not between", value1, value2, "pId");
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

        public Criteria andMdseIdIsNull() {
            addCriterion("mdseId is null");
            return (Criteria) this;
        }

        public Criteria andMdseIdIsNotNull() {
            addCriterion("mdseId is not null");
            return (Criteria) this;
        }

        public Criteria andMdseIdEqualTo(String value) {
            addCriterion("mdseId =", value, "mdseId");
            return (Criteria) this;
        }

        public Criteria andMdseIdNotEqualTo(String value) {
            addCriterion("mdseId <>", value, "mdseId");
            return (Criteria) this;
        }

        public Criteria andMdseIdGreaterThan(String value) {
            addCriterion("mdseId >", value, "mdseId");
            return (Criteria) this;
        }

        public Criteria andMdseIdGreaterThanOrEqualTo(String value) {
            addCriterion("mdseId >=", value, "mdseId");
            return (Criteria) this;
        }

        public Criteria andMdseIdLessThan(String value) {
            addCriterion("mdseId <", value, "mdseId");
            return (Criteria) this;
        }

        public Criteria andMdseIdLessThanOrEqualTo(String value) {
            addCriterion("mdseId <=", value, "mdseId");
            return (Criteria) this;
        }

        public Criteria andMdseIdLike(String value) {
            addCriterion("mdseId like", value, "mdseId");
            return (Criteria) this;
        }

        public Criteria andMdseIdNotLike(String value) {
            addCriterion("mdseId not like", value, "mdseId");
            return (Criteria) this;
        }

        public Criteria andMdseIdIn(List<String> values) {
            addCriterion("mdseId in", values, "mdseId");
            return (Criteria) this;
        }

        public Criteria andMdseIdNotIn(List<String> values) {
            addCriterion("mdseId not in", values, "mdseId");
            return (Criteria) this;
        }

        public Criteria andMdseIdBetween(String value1, String value2) {
            addCriterion("mdseId between", value1, value2, "mdseId");
            return (Criteria) this;
        }

        public Criteria andMdseIdNotBetween(String value1, String value2) {
            addCriterion("mdseId not between", value1, value2, "mdseId");
            return (Criteria) this;
        }

        public Criteria andItemCodeIsNull() {
            addCriterion("itemCode is null");
            return (Criteria) this;
        }

        public Criteria andItemCodeIsNotNull() {
            addCriterion("itemCode is not null");
            return (Criteria) this;
        }

        public Criteria andItemCodeEqualTo(String value) {
            addCriterion("itemCode =", value, "itemCode");
            return (Criteria) this;
        }

        public Criteria andItemCodeNotEqualTo(String value) {
            addCriterion("itemCode <>", value, "itemCode");
            return (Criteria) this;
        }

        public Criteria andItemCodeGreaterThan(String value) {
            addCriterion("itemCode >", value, "itemCode");
            return (Criteria) this;
        }

        public Criteria andItemCodeGreaterThanOrEqualTo(String value) {
            addCriterion("itemCode >=", value, "itemCode");
            return (Criteria) this;
        }

        public Criteria andItemCodeLessThan(String value) {
            addCriterion("itemCode <", value, "itemCode");
            return (Criteria) this;
        }

        public Criteria andItemCodeLessThanOrEqualTo(String value) {
            addCriterion("itemCode <=", value, "itemCode");
            return (Criteria) this;
        }

        public Criteria andItemCodeLike(String value) {
            addCriterion("itemCode like", value, "itemCode");
            return (Criteria) this;
        }

        public Criteria andItemCodeNotLike(String value) {
            addCriterion("itemCode not like", value, "itemCode");
            return (Criteria) this;
        }

        public Criteria andItemCodeIn(List<String> values) {
            addCriterion("itemCode in", values, "itemCode");
            return (Criteria) this;
        }

        public Criteria andItemCodeNotIn(List<String> values) {
            addCriterion("itemCode not in", values, "itemCode");
            return (Criteria) this;
        }

        public Criteria andItemCodeBetween(String value1, String value2) {
            addCriterion("itemCode between", value1, value2, "itemCode");
            return (Criteria) this;
        }

        public Criteria andItemCodeNotBetween(String value1, String value2) {
            addCriterion("itemCode not between", value1, value2, "itemCode");
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

        public Criteria andImgIsNull() {
            addCriterion("img is null");
            return (Criteria) this;
        }

        public Criteria andImgIsNotNull() {
            addCriterion("img is not null");
            return (Criteria) this;
        }

        public Criteria andImgEqualTo(String value) {
            addCriterion("img =", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgNotEqualTo(String value) {
            addCriterion("img <>", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgGreaterThan(String value) {
            addCriterion("img >", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgGreaterThanOrEqualTo(String value) {
            addCriterion("img >=", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgLessThan(String value) {
            addCriterion("img <", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgLessThanOrEqualTo(String value) {
            addCriterion("img <=", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgLike(String value) {
            addCriterion("img like", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgNotLike(String value) {
            addCriterion("img not like", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgIn(List<String> values) {
            addCriterion("img in", values, "img");
            return (Criteria) this;
        }

        public Criteria andImgNotIn(List<String> values) {
            addCriterion("img not in", values, "img");
            return (Criteria) this;
        }

        public Criteria andImgBetween(String value1, String value2) {
            addCriterion("img between", value1, value2, "img");
            return (Criteria) this;
        }

        public Criteria andImgNotBetween(String value1, String value2) {
            addCriterion("img not between", value1, value2, "img");
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

        public Criteria andOperateQuantityIsNull() {
            addCriterion("operateQuantity is null");
            return (Criteria) this;
        }

        public Criteria andOperateQuantityIsNotNull() {
            addCriterion("operateQuantity is not null");
            return (Criteria) this;
        }

        public Criteria andOperateQuantityEqualTo(Short value) {
            addCriterion("operateQuantity =", value, "operateQuantity");
            return (Criteria) this;
        }

        public Criteria andOperateQuantityNotEqualTo(Short value) {
            addCriterion("operateQuantity <>", value, "operateQuantity");
            return (Criteria) this;
        }

        public Criteria andOperateQuantityGreaterThan(Short value) {
            addCriterion("operateQuantity >", value, "operateQuantity");
            return (Criteria) this;
        }

        public Criteria andOperateQuantityGreaterThanOrEqualTo(Short value) {
            addCriterion("operateQuantity >=", value, "operateQuantity");
            return (Criteria) this;
        }

        public Criteria andOperateQuantityLessThan(Short value) {
            addCriterion("operateQuantity <", value, "operateQuantity");
            return (Criteria) this;
        }

        public Criteria andOperateQuantityLessThanOrEqualTo(Short value) {
            addCriterion("operateQuantity <=", value, "operateQuantity");
            return (Criteria) this;
        }

        public Criteria andOperateQuantityIn(List<Short> values) {
            addCriterion("operateQuantity in", values, "operateQuantity");
            return (Criteria) this;
        }

        public Criteria andOperateQuantityNotIn(List<Short> values) {
            addCriterion("operateQuantity not in", values, "operateQuantity");
            return (Criteria) this;
        }

        public Criteria andOperateQuantityBetween(Short value1, Short value2) {
            addCriterion("operateQuantity between", value1, value2, "operateQuantity");
            return (Criteria) this;
        }

        public Criteria andOperateQuantityNotBetween(Short value1, Short value2) {
            addCriterion("operateQuantity not between", value1, value2, "operateQuantity");
            return (Criteria) this;
        }

        public Criteria andOperateStockIsNull() {
            addCriterion("operateStock is null");
            return (Criteria) this;
        }

        public Criteria andOperateStockIsNotNull() {
            addCriterion("operateStock is not null");
            return (Criteria) this;
        }

        public Criteria andOperateStockEqualTo(Short value) {
            addCriterion("operateStock =", value, "operateStock");
            return (Criteria) this;
        }

        public Criteria andOperateStockNotEqualTo(Short value) {
            addCriterion("operateStock <>", value, "operateStock");
            return (Criteria) this;
        }

        public Criteria andOperateStockGreaterThan(Short value) {
            addCriterion("operateStock >", value, "operateStock");
            return (Criteria) this;
        }

        public Criteria andOperateStockGreaterThanOrEqualTo(Short value) {
            addCriterion("operateStock >=", value, "operateStock");
            return (Criteria) this;
        }

        public Criteria andOperateStockLessThan(Short value) {
            addCriterion("operateStock <", value, "operateStock");
            return (Criteria) this;
        }

        public Criteria andOperateStockLessThanOrEqualTo(Short value) {
            addCriterion("operateStock <=", value, "operateStock");
            return (Criteria) this;
        }

        public Criteria andOperateStockIn(List<Short> values) {
            addCriterion("operateStock in", values, "operateStock");
            return (Criteria) this;
        }

        public Criteria andOperateStockNotIn(List<Short> values) {
            addCriterion("operateStock not in", values, "operateStock");
            return (Criteria) this;
        }

        public Criteria andOperateStockBetween(Short value1, Short value2) {
            addCriterion("operateStock between", value1, value2, "operateStock");
            return (Criteria) this;
        }

        public Criteria andOperateStockNotBetween(Short value1, Short value2) {
            addCriterion("operateStock not between", value1, value2, "operateStock");
            return (Criteria) this;
        }

        public Criteria andPriceIsNull() {
            addCriterion("price is null");
            return (Criteria) this;
        }

        public Criteria andPriceIsNotNull() {
            addCriterion("price is not null");
            return (Criteria) this;
        }

        public Criteria andPriceEqualTo(BigDecimal value) {
            addCriterion("price =", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotEqualTo(BigDecimal value) {
            addCriterion("price <>", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThan(BigDecimal value) {
            addCriterion("price >", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("price >=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThan(BigDecimal value) {
            addCriterion("price <", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("price <=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceIn(List<BigDecimal> values) {
            addCriterion("price in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotIn(List<BigDecimal> values) {
            addCriterion("price not in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price not between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andAreaPriceIsNull() {
            addCriterion("areaPrice is null");
            return (Criteria) this;
        }

        public Criteria andAreaPriceIsNotNull() {
            addCriterion("areaPrice is not null");
            return (Criteria) this;
        }

        public Criteria andAreaPriceEqualTo(BigDecimal value) {
            addCriterion("areaPrice =", value, "areaPrice");
            return (Criteria) this;
        }

        public Criteria andAreaPriceNotEqualTo(BigDecimal value) {
            addCriterion("areaPrice <>", value, "areaPrice");
            return (Criteria) this;
        }

        public Criteria andAreaPriceGreaterThan(BigDecimal value) {
            addCriterion("areaPrice >", value, "areaPrice");
            return (Criteria) this;
        }

        public Criteria andAreaPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("areaPrice >=", value, "areaPrice");
            return (Criteria) this;
        }

        public Criteria andAreaPriceLessThan(BigDecimal value) {
            addCriterion("areaPrice <", value, "areaPrice");
            return (Criteria) this;
        }

        public Criteria andAreaPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("areaPrice <=", value, "areaPrice");
            return (Criteria) this;
        }

        public Criteria andAreaPriceIn(List<BigDecimal> values) {
            addCriterion("areaPrice in", values, "areaPrice");
            return (Criteria) this;
        }

        public Criteria andAreaPriceNotIn(List<BigDecimal> values) {
            addCriterion("areaPrice not in", values, "areaPrice");
            return (Criteria) this;
        }

        public Criteria andAreaPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("areaPrice between", value1, value2, "areaPrice");
            return (Criteria) this;
        }

        public Criteria andAreaPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("areaPrice not between", value1, value2, "areaPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceIsNull() {
            addCriterion("totalPrice is null");
            return (Criteria) this;
        }

        public Criteria andTotalPriceIsNotNull() {
            addCriterion("totalPrice is not null");
            return (Criteria) this;
        }

        public Criteria andTotalPriceEqualTo(BigDecimal value) {
            addCriterion("totalPrice =", value, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceNotEqualTo(BigDecimal value) {
            addCriterion("totalPrice <>", value, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceGreaterThan(BigDecimal value) {
            addCriterion("totalPrice >", value, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("totalPrice >=", value, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceLessThan(BigDecimal value) {
            addCriterion("totalPrice <", value, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("totalPrice <=", value, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceIn(List<BigDecimal> values) {
            addCriterion("totalPrice in", values, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceNotIn(List<BigDecimal> values) {
            addCriterion("totalPrice not in", values, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("totalPrice between", value1, value2, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("totalPrice not between", value1, value2, "totalPrice");
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

        public Criteria andProductionTimeIsNull() {
            addCriterion("productionTime is null");
            return (Criteria) this;
        }

        public Criteria andProductionTimeIsNotNull() {
            addCriterion("productionTime is not null");
            return (Criteria) this;
        }

        public Criteria andProductionTimeEqualTo(Date value) {
            addCriterion("productionTime =", value, "productionTime");
            return (Criteria) this;
        }

        public Criteria andProductionTimeNotEqualTo(Date value) {
            addCriterion("productionTime <>", value, "productionTime");
            return (Criteria) this;
        }

        public Criteria andProductionTimeGreaterThan(Date value) {
            addCriterion("productionTime >", value, "productionTime");
            return (Criteria) this;
        }

        public Criteria andProductionTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("productionTime >=", value, "productionTime");
            return (Criteria) this;
        }

        public Criteria andProductionTimeLessThan(Date value) {
            addCriterion("productionTime <", value, "productionTime");
            return (Criteria) this;
        }

        public Criteria andProductionTimeLessThanOrEqualTo(Date value) {
            addCriterion("productionTime <=", value, "productionTime");
            return (Criteria) this;
        }

        public Criteria andProductionTimeIn(List<Date> values) {
            addCriterion("productionTime in", values, "productionTime");
            return (Criteria) this;
        }

        public Criteria andProductionTimeNotIn(List<Date> values) {
            addCriterion("productionTime not in", values, "productionTime");
            return (Criteria) this;
        }

        public Criteria andProductionTimeBetween(Date value1, Date value2) {
            addCriterion("productionTime between", value1, value2, "productionTime");
            return (Criteria) this;
        }

        public Criteria andProductionTimeNotBetween(Date value1, Date value2) {
            addCriterion("productionTime not between", value1, value2, "productionTime");
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