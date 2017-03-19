package com.corner.core.beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SpOrderDetailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SpOrderDetailExample() {
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

        public Criteria andOrderId2IsNull() {
            addCriterion("orderId2 is null");
            return (Criteria) this;
        }

        public Criteria andOrderId2IsNotNull() {
            addCriterion("orderId2 is not null");
            return (Criteria) this;
        }

        public Criteria andOrderId2EqualTo(String value) {
            addCriterion("orderId2 =", value, "orderId2");
            return (Criteria) this;
        }

        public Criteria andOrderId2NotEqualTo(String value) {
            addCriterion("orderId2 <>", value, "orderId2");
            return (Criteria) this;
        }

        public Criteria andOrderId2GreaterThan(String value) {
            addCriterion("orderId2 >", value, "orderId2");
            return (Criteria) this;
        }

        public Criteria andOrderId2GreaterThanOrEqualTo(String value) {
            addCriterion("orderId2 >=", value, "orderId2");
            return (Criteria) this;
        }

        public Criteria andOrderId2LessThan(String value) {
            addCriterion("orderId2 <", value, "orderId2");
            return (Criteria) this;
        }

        public Criteria andOrderId2LessThanOrEqualTo(String value) {
            addCriterion("orderId2 <=", value, "orderId2");
            return (Criteria) this;
        }

        public Criteria andOrderId2Like(String value) {
            addCriterion("orderId2 like", value, "orderId2");
            return (Criteria) this;
        }

        public Criteria andOrderId2NotLike(String value) {
            addCriterion("orderId2 not like", value, "orderId2");
            return (Criteria) this;
        }

        public Criteria andOrderId2In(List<String> values) {
            addCriterion("orderId2 in", values, "orderId2");
            return (Criteria) this;
        }

        public Criteria andOrderId2NotIn(List<String> values) {
            addCriterion("orderId2 not in", values, "orderId2");
            return (Criteria) this;
        }

        public Criteria andOrderId2Between(String value1, String value2) {
            addCriterion("orderId2 between", value1, value2, "orderId2");
            return (Criteria) this;
        }

        public Criteria andOrderId2NotBetween(String value1, String value2) {
            addCriterion("orderId2 not between", value1, value2, "orderId2");
            return (Criteria) this;
        }

        public Criteria andItemIdIsNull() {
            addCriterion("itemId is null");
            return (Criteria) this;
        }

        public Criteria andItemIdIsNotNull() {
            addCriterion("itemId is not null");
            return (Criteria) this;
        }

        public Criteria andItemIdEqualTo(String value) {
            addCriterion("itemId =", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotEqualTo(String value) {
            addCriterion("itemId <>", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdGreaterThan(String value) {
            addCriterion("itemId >", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdGreaterThanOrEqualTo(String value) {
            addCriterion("itemId >=", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdLessThan(String value) {
            addCriterion("itemId <", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdLessThanOrEqualTo(String value) {
            addCriterion("itemId <=", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdLike(String value) {
            addCriterion("itemId like", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotLike(String value) {
            addCriterion("itemId not like", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdIn(List<String> values) {
            addCriterion("itemId in", values, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotIn(List<String> values) {
            addCriterion("itemId not in", values, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdBetween(String value1, String value2) {
            addCriterion("itemId between", value1, value2, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotBetween(String value1, String value2) {
            addCriterion("itemId not between", value1, value2, "itemId");
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

        public Criteria andBrandIsNull() {
            addCriterion("brand is null");
            return (Criteria) this;
        }

        public Criteria andBrandIsNotNull() {
            addCriterion("brand is not null");
            return (Criteria) this;
        }

        public Criteria andBrandEqualTo(String value) {
            addCriterion("brand =", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandNotEqualTo(String value) {
            addCriterion("brand <>", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandGreaterThan(String value) {
            addCriterion("brand >", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandGreaterThanOrEqualTo(String value) {
            addCriterion("brand >=", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandLessThan(String value) {
            addCriterion("brand <", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandLessThanOrEqualTo(String value) {
            addCriterion("brand <=", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandLike(String value) {
            addCriterion("brand like", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandNotLike(String value) {
            addCriterion("brand not like", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandIn(List<String> values) {
            addCriterion("brand in", values, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandNotIn(List<String> values) {
            addCriterion("brand not in", values, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandBetween(String value1, String value2) {
            addCriterion("brand between", value1, value2, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandNotBetween(String value1, String value2) {
            addCriterion("brand not between", value1, value2, "brand");
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

        public Criteria andPlantDisPriceIsNull() {
            addCriterion("plantDisPrice is null");
            return (Criteria) this;
        }

        public Criteria andPlantDisPriceIsNotNull() {
            addCriterion("plantDisPrice is not null");
            return (Criteria) this;
        }

        public Criteria andPlantDisPriceEqualTo(BigDecimal value) {
            addCriterion("plantDisPrice =", value, "plantDisPrice");
            return (Criteria) this;
        }

        public Criteria andPlantDisPriceNotEqualTo(BigDecimal value) {
            addCriterion("plantDisPrice <>", value, "plantDisPrice");
            return (Criteria) this;
        }

        public Criteria andPlantDisPriceGreaterThan(BigDecimal value) {
            addCriterion("plantDisPrice >", value, "plantDisPrice");
            return (Criteria) this;
        }

        public Criteria andPlantDisPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("plantDisPrice >=", value, "plantDisPrice");
            return (Criteria) this;
        }

        public Criteria andPlantDisPriceLessThan(BigDecimal value) {
            addCriterion("plantDisPrice <", value, "plantDisPrice");
            return (Criteria) this;
        }

        public Criteria andPlantDisPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("plantDisPrice <=", value, "plantDisPrice");
            return (Criteria) this;
        }

        public Criteria andPlantDisPriceIn(List<BigDecimal> values) {
            addCriterion("plantDisPrice in", values, "plantDisPrice");
            return (Criteria) this;
        }

        public Criteria andPlantDisPriceNotIn(List<BigDecimal> values) {
            addCriterion("plantDisPrice not in", values, "plantDisPrice");
            return (Criteria) this;
        }

        public Criteria andPlantDisPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("plantDisPrice between", value1, value2, "plantDisPrice");
            return (Criteria) this;
        }

        public Criteria andPlantDisPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("plantDisPrice not between", value1, value2, "plantDisPrice");
            return (Criteria) this;
        }

        public Criteria andPlantMemPriceIsNull() {
            addCriterion("plantMemPrice is null");
            return (Criteria) this;
        }

        public Criteria andPlantMemPriceIsNotNull() {
            addCriterion("plantMemPrice is not null");
            return (Criteria) this;
        }

        public Criteria andPlantMemPriceEqualTo(BigDecimal value) {
            addCriterion("plantMemPrice =", value, "plantMemPrice");
            return (Criteria) this;
        }

        public Criteria andPlantMemPriceNotEqualTo(BigDecimal value) {
            addCriterion("plantMemPrice <>", value, "plantMemPrice");
            return (Criteria) this;
        }

        public Criteria andPlantMemPriceGreaterThan(BigDecimal value) {
            addCriterion("plantMemPrice >", value, "plantMemPrice");
            return (Criteria) this;
        }

        public Criteria andPlantMemPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("plantMemPrice >=", value, "plantMemPrice");
            return (Criteria) this;
        }

        public Criteria andPlantMemPriceLessThan(BigDecimal value) {
            addCriterion("plantMemPrice <", value, "plantMemPrice");
            return (Criteria) this;
        }

        public Criteria andPlantMemPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("plantMemPrice <=", value, "plantMemPrice");
            return (Criteria) this;
        }

        public Criteria andPlantMemPriceIn(List<BigDecimal> values) {
            addCriterion("plantMemPrice in", values, "plantMemPrice");
            return (Criteria) this;
        }

        public Criteria andPlantMemPriceNotIn(List<BigDecimal> values) {
            addCriterion("plantMemPrice not in", values, "plantMemPrice");
            return (Criteria) this;
        }

        public Criteria andPlantMemPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("plantMemPrice between", value1, value2, "plantMemPrice");
            return (Criteria) this;
        }

        public Criteria andPlantMemPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("plantMemPrice not between", value1, value2, "plantMemPrice");
            return (Criteria) this;
        }

        public Criteria andMaoliIsNull() {
            addCriterion("maoli is null");
            return (Criteria) this;
        }

        public Criteria andMaoliIsNotNull() {
            addCriterion("maoli is not null");
            return (Criteria) this;
        }

        public Criteria andMaoliEqualTo(BigDecimal value) {
            addCriterion("maoli =", value, "maoli");
            return (Criteria) this;
        }

        public Criteria andMaoliNotEqualTo(BigDecimal value) {
            addCriterion("maoli <>", value, "maoli");
            return (Criteria) this;
        }

        public Criteria andMaoliGreaterThan(BigDecimal value) {
            addCriterion("maoli >", value, "maoli");
            return (Criteria) this;
        }

        public Criteria andMaoliGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("maoli >=", value, "maoli");
            return (Criteria) this;
        }

        public Criteria andMaoliLessThan(BigDecimal value) {
            addCriterion("maoli <", value, "maoli");
            return (Criteria) this;
        }

        public Criteria andMaoliLessThanOrEqualTo(BigDecimal value) {
            addCriterion("maoli <=", value, "maoli");
            return (Criteria) this;
        }

        public Criteria andMaoliIn(List<BigDecimal> values) {
            addCriterion("maoli in", values, "maoli");
            return (Criteria) this;
        }

        public Criteria andMaoliNotIn(List<BigDecimal> values) {
            addCriterion("maoli not in", values, "maoli");
            return (Criteria) this;
        }

        public Criteria andMaoliBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("maoli between", value1, value2, "maoli");
            return (Criteria) this;
        }

        public Criteria andMaoliNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("maoli not between", value1, value2, "maoli");
            return (Criteria) this;
        }

        public Criteria andFeeIsNull() {
            addCriterion("fee is null");
            return (Criteria) this;
        }

        public Criteria andFeeIsNotNull() {
            addCriterion("fee is not null");
            return (Criteria) this;
        }

        public Criteria andFeeEqualTo(BigDecimal value) {
            addCriterion("fee =", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeNotEqualTo(BigDecimal value) {
            addCriterion("fee <>", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeGreaterThan(BigDecimal value) {
            addCriterion("fee >", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("fee >=", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeLessThan(BigDecimal value) {
            addCriterion("fee <", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("fee <=", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeIn(List<BigDecimal> values) {
            addCriterion("fee in", values, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeNotIn(List<BigDecimal> values) {
            addCriterion("fee not in", values, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fee between", value1, value2, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fee not between", value1, value2, "fee");
            return (Criteria) this;
        }

        public Criteria andStoreIdIsNull() {
            addCriterion("storeId is null");
            return (Criteria) this;
        }

        public Criteria andStoreIdIsNotNull() {
            addCriterion("storeId is not null");
            return (Criteria) this;
        }

        public Criteria andStoreIdEqualTo(Integer value) {
            addCriterion("storeId =", value, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdNotEqualTo(Integer value) {
            addCriterion("storeId <>", value, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdGreaterThan(Integer value) {
            addCriterion("storeId >", value, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("storeId >=", value, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdLessThan(Integer value) {
            addCriterion("storeId <", value, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdLessThanOrEqualTo(Integer value) {
            addCriterion("storeId <=", value, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdIn(List<Integer> values) {
            addCriterion("storeId in", values, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdNotIn(List<Integer> values) {
            addCriterion("storeId not in", values, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdBetween(Integer value1, Integer value2) {
            addCriterion("storeId between", value1, value2, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdNotBetween(Integer value1, Integer value2) {
            addCriterion("storeId not between", value1, value2, "storeId");
            return (Criteria) this;
        }

        public Criteria andSpGroupIdIsNull() {
            addCriterion("spGroupId is null");
            return (Criteria) this;
        }

        public Criteria andSpGroupIdIsNotNull() {
            addCriterion("spGroupId is not null");
            return (Criteria) this;
        }

        public Criteria andSpGroupIdEqualTo(Integer value) {
            addCriterion("spGroupId =", value, "spGroupId");
            return (Criteria) this;
        }

        public Criteria andSpGroupIdNotEqualTo(Integer value) {
            addCriterion("spGroupId <>", value, "spGroupId");
            return (Criteria) this;
        }

        public Criteria andSpGroupIdGreaterThan(Integer value) {
            addCriterion("spGroupId >", value, "spGroupId");
            return (Criteria) this;
        }

        public Criteria andSpGroupIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("spGroupId >=", value, "spGroupId");
            return (Criteria) this;
        }

        public Criteria andSpGroupIdLessThan(Integer value) {
            addCriterion("spGroupId <", value, "spGroupId");
            return (Criteria) this;
        }

        public Criteria andSpGroupIdLessThanOrEqualTo(Integer value) {
            addCriterion("spGroupId <=", value, "spGroupId");
            return (Criteria) this;
        }

        public Criteria andSpGroupIdIn(List<Integer> values) {
            addCriterion("spGroupId in", values, "spGroupId");
            return (Criteria) this;
        }

        public Criteria andSpGroupIdNotIn(List<Integer> values) {
            addCriterion("spGroupId not in", values, "spGroupId");
            return (Criteria) this;
        }

        public Criteria andSpGroupIdBetween(Integer value1, Integer value2) {
            addCriterion("spGroupId between", value1, value2, "spGroupId");
            return (Criteria) this;
        }

        public Criteria andSpGroupIdNotBetween(Integer value1, Integer value2) {
            addCriterion("spGroupId not between", value1, value2, "spGroupId");
            return (Criteria) this;
        }

        public Criteria andSpIdIsNull() {
            addCriterion("spId is null");
            return (Criteria) this;
        }

        public Criteria andSpIdIsNotNull() {
            addCriterion("spId is not null");
            return (Criteria) this;
        }

        public Criteria andSpIdEqualTo(String value) {
            addCriterion("spId =", value, "spId");
            return (Criteria) this;
        }

        public Criteria andSpIdNotEqualTo(String value) {
            addCriterion("spId <>", value, "spId");
            return (Criteria) this;
        }

        public Criteria andSpIdGreaterThan(String value) {
            addCriterion("spId >", value, "spId");
            return (Criteria) this;
        }

        public Criteria andSpIdGreaterThanOrEqualTo(String value) {
            addCriterion("spId >=", value, "spId");
            return (Criteria) this;
        }

        public Criteria andSpIdLessThan(String value) {
            addCriterion("spId <", value, "spId");
            return (Criteria) this;
        }

        public Criteria andSpIdLessThanOrEqualTo(String value) {
            addCriterion("spId <=", value, "spId");
            return (Criteria) this;
        }

        public Criteria andSpIdLike(String value) {
            addCriterion("spId like", value, "spId");
            return (Criteria) this;
        }

        public Criteria andSpIdNotLike(String value) {
            addCriterion("spId not like", value, "spId");
            return (Criteria) this;
        }

        public Criteria andSpIdIn(List<String> values) {
            addCriterion("spId in", values, "spId");
            return (Criteria) this;
        }

        public Criteria andSpIdNotIn(List<String> values) {
            addCriterion("spId not in", values, "spId");
            return (Criteria) this;
        }

        public Criteria andSpIdBetween(String value1, String value2) {
            addCriterion("spId between", value1, value2, "spId");
            return (Criteria) this;
        }

        public Criteria andSpIdNotBetween(String value1, String value2) {
            addCriterion("spId not between", value1, value2, "spId");
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

        public Criteria andYouHuiIsNull() {
            addCriterion("youHui is null");
            return (Criteria) this;
        }

        public Criteria andYouHuiIsNotNull() {
            addCriterion("youHui is not null");
            return (Criteria) this;
        }

        public Criteria andYouHuiEqualTo(String value) {
            addCriterion("youHui =", value, "youHui");
            return (Criteria) this;
        }

        public Criteria andYouHuiNotEqualTo(String value) {
            addCriterion("youHui <>", value, "youHui");
            return (Criteria) this;
        }

        public Criteria andYouHuiGreaterThan(String value) {
            addCriterion("youHui >", value, "youHui");
            return (Criteria) this;
        }

        public Criteria andYouHuiGreaterThanOrEqualTo(String value) {
            addCriterion("youHui >=", value, "youHui");
            return (Criteria) this;
        }

        public Criteria andYouHuiLessThan(String value) {
            addCriterion("youHui <", value, "youHui");
            return (Criteria) this;
        }

        public Criteria andYouHuiLessThanOrEqualTo(String value) {
            addCriterion("youHui <=", value, "youHui");
            return (Criteria) this;
        }

        public Criteria andYouHuiLike(String value) {
            addCriterion("youHui like", value, "youHui");
            return (Criteria) this;
        }

        public Criteria andYouHuiNotLike(String value) {
            addCriterion("youHui not like", value, "youHui");
            return (Criteria) this;
        }

        public Criteria andYouHuiIn(List<String> values) {
            addCriterion("youHui in", values, "youHui");
            return (Criteria) this;
        }

        public Criteria andYouHuiNotIn(List<String> values) {
            addCriterion("youHui not in", values, "youHui");
            return (Criteria) this;
        }

        public Criteria andYouHuiBetween(String value1, String value2) {
            addCriterion("youHui between", value1, value2, "youHui");
            return (Criteria) this;
        }

        public Criteria andYouHuiNotBetween(String value1, String value2) {
            addCriterion("youHui not between", value1, value2, "youHui");
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

        public Criteria andRestrictIsNull() {
            addCriterion("restrict is null");
            return (Criteria) this;
        }

        public Criteria andRestrictIsNotNull() {
            addCriterion("restrict is not null");
            return (Criteria) this;
        }

        public Criteria andRestrictEqualTo(Integer value) {
            addCriterion("restrict =", value, "restrict");
            return (Criteria) this;
        }

        public Criteria andRestrictNotEqualTo(Integer value) {
            addCriterion("restrict <>", value, "restrict");
            return (Criteria) this;
        }

        public Criteria andRestrictGreaterThan(Integer value) {
            addCriterion("restrict >", value, "restrict");
            return (Criteria) this;
        }

        public Criteria andRestrictGreaterThanOrEqualTo(Integer value) {
            addCriterion("restrict >=", value, "restrict");
            return (Criteria) this;
        }

        public Criteria andRestrictLessThan(Integer value) {
            addCriterion("restrict <", value, "restrict");
            return (Criteria) this;
        }

        public Criteria andRestrictLessThanOrEqualTo(Integer value) {
            addCriterion("restrict <=", value, "restrict");
            return (Criteria) this;
        }

        public Criteria andRestrictIn(List<Integer> values) {
            addCriterion("restrict in", values, "restrict");
            return (Criteria) this;
        }

        public Criteria andRestrictNotIn(List<Integer> values) {
            addCriterion("restrict not in", values, "restrict");
            return (Criteria) this;
        }

        public Criteria andRestrictBetween(Integer value1, Integer value2) {
            addCriterion("restrict between", value1, value2, "restrict");
            return (Criteria) this;
        }

        public Criteria andRestrictNotBetween(Integer value1, Integer value2) {
            addCriterion("restrict not between", value1, value2, "restrict");
            return (Criteria) this;
        }

        public Criteria andCol1IsNull() {
            addCriterion("col1 is null");
            return (Criteria) this;
        }

        public Criteria andCol1IsNotNull() {
            addCriterion("col1 is not null");
            return (Criteria) this;
        }

        public Criteria andCol1EqualTo(String value) {
            addCriterion("col1 =", value, "col1");
            return (Criteria) this;
        }

        public Criteria andCol1NotEqualTo(String value) {
            addCriterion("col1 <>", value, "col1");
            return (Criteria) this;
        }

        public Criteria andCol1GreaterThan(String value) {
            addCriterion("col1 >", value, "col1");
            return (Criteria) this;
        }

        public Criteria andCol1GreaterThanOrEqualTo(String value) {
            addCriterion("col1 >=", value, "col1");
            return (Criteria) this;
        }

        public Criteria andCol1LessThan(String value) {
            addCriterion("col1 <", value, "col1");
            return (Criteria) this;
        }

        public Criteria andCol1LessThanOrEqualTo(String value) {
            addCriterion("col1 <=", value, "col1");
            return (Criteria) this;
        }

        public Criteria andCol1Like(String value) {
            addCriterion("col1 like", value, "col1");
            return (Criteria) this;
        }

        public Criteria andCol1NotLike(String value) {
            addCriterion("col1 not like", value, "col1");
            return (Criteria) this;
        }

        public Criteria andCol1In(List<String> values) {
            addCriterion("col1 in", values, "col1");
            return (Criteria) this;
        }

        public Criteria andCol1NotIn(List<String> values) {
            addCriterion("col1 not in", values, "col1");
            return (Criteria) this;
        }

        public Criteria andCol1Between(String value1, String value2) {
            addCriterion("col1 between", value1, value2, "col1");
            return (Criteria) this;
        }

        public Criteria andCol1NotBetween(String value1, String value2) {
            addCriterion("col1 not between", value1, value2, "col1");
            return (Criteria) this;
        }

        public Criteria andCol2IsNull() {
            addCriterion("col2 is null");
            return (Criteria) this;
        }

        public Criteria andCol2IsNotNull() {
            addCriterion("col2 is not null");
            return (Criteria) this;
        }

        public Criteria andCol2EqualTo(String value) {
            addCriterion("col2 =", value, "col2");
            return (Criteria) this;
        }

        public Criteria andCol2NotEqualTo(String value) {
            addCriterion("col2 <>", value, "col2");
            return (Criteria) this;
        }

        public Criteria andCol2GreaterThan(String value) {
            addCriterion("col2 >", value, "col2");
            return (Criteria) this;
        }

        public Criteria andCol2GreaterThanOrEqualTo(String value) {
            addCriterion("col2 >=", value, "col2");
            return (Criteria) this;
        }

        public Criteria andCol2LessThan(String value) {
            addCriterion("col2 <", value, "col2");
            return (Criteria) this;
        }

        public Criteria andCol2LessThanOrEqualTo(String value) {
            addCriterion("col2 <=", value, "col2");
            return (Criteria) this;
        }

        public Criteria andCol2Like(String value) {
            addCriterion("col2 like", value, "col2");
            return (Criteria) this;
        }

        public Criteria andCol2NotLike(String value) {
            addCriterion("col2 not like", value, "col2");
            return (Criteria) this;
        }

        public Criteria andCol2In(List<String> values) {
            addCriterion("col2 in", values, "col2");
            return (Criteria) this;
        }

        public Criteria andCol2NotIn(List<String> values) {
            addCriterion("col2 not in", values, "col2");
            return (Criteria) this;
        }

        public Criteria andCol2Between(String value1, String value2) {
            addCriterion("col2 between", value1, value2, "col2");
            return (Criteria) this;
        }

        public Criteria andCol2NotBetween(String value1, String value2) {
            addCriterion("col2 not between", value1, value2, "col2");
            return (Criteria) this;
        }

        public Criteria andCol3IsNull() {
            addCriterion("col3 is null");
            return (Criteria) this;
        }

        public Criteria andCol3IsNotNull() {
            addCriterion("col3 is not null");
            return (Criteria) this;
        }

        public Criteria andCol3EqualTo(String value) {
            addCriterion("col3 =", value, "col3");
            return (Criteria) this;
        }

        public Criteria andCol3NotEqualTo(String value) {
            addCriterion("col3 <>", value, "col3");
            return (Criteria) this;
        }

        public Criteria andCol3GreaterThan(String value) {
            addCriterion("col3 >", value, "col3");
            return (Criteria) this;
        }

        public Criteria andCol3GreaterThanOrEqualTo(String value) {
            addCriterion("col3 >=", value, "col3");
            return (Criteria) this;
        }

        public Criteria andCol3LessThan(String value) {
            addCriterion("col3 <", value, "col3");
            return (Criteria) this;
        }

        public Criteria andCol3LessThanOrEqualTo(String value) {
            addCriterion("col3 <=", value, "col3");
            return (Criteria) this;
        }

        public Criteria andCol3Like(String value) {
            addCriterion("col3 like", value, "col3");
            return (Criteria) this;
        }

        public Criteria andCol3NotLike(String value) {
            addCriterion("col3 not like", value, "col3");
            return (Criteria) this;
        }

        public Criteria andCol3In(List<String> values) {
            addCriterion("col3 in", values, "col3");
            return (Criteria) this;
        }

        public Criteria andCol3NotIn(List<String> values) {
            addCriterion("col3 not in", values, "col3");
            return (Criteria) this;
        }

        public Criteria andCol3Between(String value1, String value2) {
            addCriterion("col3 between", value1, value2, "col3");
            return (Criteria) this;
        }

        public Criteria andCol3NotBetween(String value1, String value2) {
            addCriterion("col3 not between", value1, value2, "col3");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeIsNull() {
            addCriterion("goodsType is null");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeIsNotNull() {
            addCriterion("goodsType is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeEqualTo(Integer value) {
            addCriterion("goodsType =", value, "goodsType");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeNotEqualTo(Integer value) {
            addCriterion("goodsType <>", value, "goodsType");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeGreaterThan(Integer value) {
            addCriterion("goodsType >", value, "goodsType");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("goodsType >=", value, "goodsType");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeLessThan(Integer value) {
            addCriterion("goodsType <", value, "goodsType");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeLessThanOrEqualTo(Integer value) {
            addCriterion("goodsType <=", value, "goodsType");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeIn(List<Integer> values) {
            addCriterion("goodsType in", values, "goodsType");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeNotIn(List<Integer> values) {
            addCriterion("goodsType not in", values, "goodsType");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeBetween(Integer value1, Integer value2) {
            addCriterion("goodsType between", value1, value2, "goodsType");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("goodsType not between", value1, value2, "goodsType");
            return (Criteria) this;
        }

        public Criteria andGiftActivityIdIsNull() {
            addCriterion("giftActivityId is null");
            return (Criteria) this;
        }

        public Criteria andGiftActivityIdIsNotNull() {
            addCriterion("giftActivityId is not null");
            return (Criteria) this;
        }

        public Criteria andGiftActivityIdEqualTo(Integer value) {
            addCriterion("giftActivityId =", value, "giftActivityId");
            return (Criteria) this;
        }

        public Criteria andGiftActivityIdNotEqualTo(Integer value) {
            addCriterion("giftActivityId <>", value, "giftActivityId");
            return (Criteria) this;
        }

        public Criteria andGiftActivityIdGreaterThan(Integer value) {
            addCriterion("giftActivityId >", value, "giftActivityId");
            return (Criteria) this;
        }

        public Criteria andGiftActivityIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("giftActivityId >=", value, "giftActivityId");
            return (Criteria) this;
        }

        public Criteria andGiftActivityIdLessThan(Integer value) {
            addCriterion("giftActivityId <", value, "giftActivityId");
            return (Criteria) this;
        }

        public Criteria andGiftActivityIdLessThanOrEqualTo(Integer value) {
            addCriterion("giftActivityId <=", value, "giftActivityId");
            return (Criteria) this;
        }

        public Criteria andGiftActivityIdIn(List<Integer> values) {
            addCriterion("giftActivityId in", values, "giftActivityId");
            return (Criteria) this;
        }

        public Criteria andGiftActivityIdNotIn(List<Integer> values) {
            addCriterion("giftActivityId not in", values, "giftActivityId");
            return (Criteria) this;
        }

        public Criteria andGiftActivityIdBetween(Integer value1, Integer value2) {
            addCriterion("giftActivityId between", value1, value2, "giftActivityId");
            return (Criteria) this;
        }

        public Criteria andGiftActivityIdNotBetween(Integer value1, Integer value2) {
            addCriterion("giftActivityId not between", value1, value2, "giftActivityId");
            return (Criteria) this;
        }

        public Criteria andGiftSpIdIsNull() {
            addCriterion("giftSpId is null");
            return (Criteria) this;
        }

        public Criteria andGiftSpIdIsNotNull() {
            addCriterion("giftSpId is not null");
            return (Criteria) this;
        }

        public Criteria andGiftSpIdEqualTo(String value) {
            addCriterion("giftSpId =", value, "giftSpId");
            return (Criteria) this;
        }

        public Criteria andGiftSpIdNotEqualTo(String value) {
            addCriterion("giftSpId <>", value, "giftSpId");
            return (Criteria) this;
        }

        public Criteria andGiftSpIdGreaterThan(String value) {
            addCriterion("giftSpId >", value, "giftSpId");
            return (Criteria) this;
        }

        public Criteria andGiftSpIdGreaterThanOrEqualTo(String value) {
            addCriterion("giftSpId >=", value, "giftSpId");
            return (Criteria) this;
        }

        public Criteria andGiftSpIdLessThan(String value) {
            addCriterion("giftSpId <", value, "giftSpId");
            return (Criteria) this;
        }

        public Criteria andGiftSpIdLessThanOrEqualTo(String value) {
            addCriterion("giftSpId <=", value, "giftSpId");
            return (Criteria) this;
        }

        public Criteria andGiftSpIdLike(String value) {
            addCriterion("giftSpId like", value, "giftSpId");
            return (Criteria) this;
        }

        public Criteria andGiftSpIdNotLike(String value) {
            addCriterion("giftSpId not like", value, "giftSpId");
            return (Criteria) this;
        }

        public Criteria andGiftSpIdIn(List<String> values) {
            addCriterion("giftSpId in", values, "giftSpId");
            return (Criteria) this;
        }

        public Criteria andGiftSpIdNotIn(List<String> values) {
            addCriterion("giftSpId not in", values, "giftSpId");
            return (Criteria) this;
        }

        public Criteria andGiftSpIdBetween(String value1, String value2) {
            addCriterion("giftSpId between", value1, value2, "giftSpId");
            return (Criteria) this;
        }

        public Criteria andGiftSpIdNotBetween(String value1, String value2) {
            addCriterion("giftSpId not between", value1, value2, "giftSpId");
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