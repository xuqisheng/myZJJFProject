package com.corner.task.beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ItemBaseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ItemBaseExample() {
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

        public Criteria andCateIdIsNull() {
            addCriterion("cateId is null");
            return (Criteria) this;
        }

        public Criteria andCateIdIsNotNull() {
            addCriterion("cateId is not null");
            return (Criteria) this;
        }

        public Criteria andCateIdEqualTo(Integer value) {
            addCriterion("cateId =", value, "cateId");
            return (Criteria) this;
        }

        public Criteria andCateIdNotEqualTo(Integer value) {
            addCriterion("cateId <>", value, "cateId");
            return (Criteria) this;
        }

        public Criteria andCateIdGreaterThan(Integer value) {
            addCriterion("cateId >", value, "cateId");
            return (Criteria) this;
        }

        public Criteria andCateIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("cateId >=", value, "cateId");
            return (Criteria) this;
        }

        public Criteria andCateIdLessThan(Integer value) {
            addCriterion("cateId <", value, "cateId");
            return (Criteria) this;
        }

        public Criteria andCateIdLessThanOrEqualTo(Integer value) {
            addCriterion("cateId <=", value, "cateId");
            return (Criteria) this;
        }

        public Criteria andCateIdIn(List<Integer> values) {
            addCriterion("cateId in", values, "cateId");
            return (Criteria) this;
        }

        public Criteria andCateIdNotIn(List<Integer> values) {
            addCriterion("cateId not in", values, "cateId");
            return (Criteria) this;
        }

        public Criteria andCateIdBetween(Integer value1, Integer value2) {
            addCriterion("cateId between", value1, value2, "cateId");
            return (Criteria) this;
        }

        public Criteria andCateIdNotBetween(Integer value1, Integer value2) {
            addCriterion("cateId not between", value1, value2, "cateId");
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

        public Criteria andOrdIdIsNull() {
            addCriterion("ordId is null");
            return (Criteria) this;
        }

        public Criteria andOrdIdIsNotNull() {
            addCriterion("ordId is not null");
            return (Criteria) this;
        }

        public Criteria andOrdIdEqualTo(Byte value) {
            addCriterion("ordId =", value, "ordId");
            return (Criteria) this;
        }

        public Criteria andOrdIdNotEqualTo(Byte value) {
            addCriterion("ordId <>", value, "ordId");
            return (Criteria) this;
        }

        public Criteria andOrdIdGreaterThan(Byte value) {
            addCriterion("ordId >", value, "ordId");
            return (Criteria) this;
        }

        public Criteria andOrdIdGreaterThanOrEqualTo(Byte value) {
            addCriterion("ordId >=", value, "ordId");
            return (Criteria) this;
        }

        public Criteria andOrdIdLessThan(Byte value) {
            addCriterion("ordId <", value, "ordId");
            return (Criteria) this;
        }

        public Criteria andOrdIdLessThanOrEqualTo(Byte value) {
            addCriterion("ordId <=", value, "ordId");
            return (Criteria) this;
        }

        public Criteria andOrdIdIn(List<Byte> values) {
            addCriterion("ordId in", values, "ordId");
            return (Criteria) this;
        }

        public Criteria andOrdIdNotIn(List<Byte> values) {
            addCriterion("ordId not in", values, "ordId");
            return (Criteria) this;
        }

        public Criteria andOrdIdBetween(Byte value1, Byte value2) {
            addCriterion("ordId between", value1, value2, "ordId");
            return (Criteria) this;
        }

        public Criteria andOrdIdNotBetween(Byte value1, Byte value2) {
            addCriterion("ordId not between", value1, value2, "ordId");
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

        public Criteria andImgSIsNull() {
            addCriterion("imgS is null");
            return (Criteria) this;
        }

        public Criteria andImgSIsNotNull() {
            addCriterion("imgS is not null");
            return (Criteria) this;
        }

        public Criteria andImgSEqualTo(String value) {
            addCriterion("imgS =", value, "imgS");
            return (Criteria) this;
        }

        public Criteria andImgSNotEqualTo(String value) {
            addCriterion("imgS <>", value, "imgS");
            return (Criteria) this;
        }

        public Criteria andImgSGreaterThan(String value) {
            addCriterion("imgS >", value, "imgS");
            return (Criteria) this;
        }

        public Criteria andImgSGreaterThanOrEqualTo(String value) {
            addCriterion("imgS >=", value, "imgS");
            return (Criteria) this;
        }

        public Criteria andImgSLessThan(String value) {
            addCriterion("imgS <", value, "imgS");
            return (Criteria) this;
        }

        public Criteria andImgSLessThanOrEqualTo(String value) {
            addCriterion("imgS <=", value, "imgS");
            return (Criteria) this;
        }

        public Criteria andImgSLike(String value) {
            addCriterion("imgS like", value, "imgS");
            return (Criteria) this;
        }

        public Criteria andImgSNotLike(String value) {
            addCriterion("imgS not like", value, "imgS");
            return (Criteria) this;
        }

        public Criteria andImgSIn(List<String> values) {
            addCriterion("imgS in", values, "imgS");
            return (Criteria) this;
        }

        public Criteria andImgSNotIn(List<String> values) {
            addCriterion("imgS not in", values, "imgS");
            return (Criteria) this;
        }

        public Criteria andImgSBetween(String value1, String value2) {
            addCriterion("imgS between", value1, value2, "imgS");
            return (Criteria) this;
        }

        public Criteria andImgSNotBetween(String value1, String value2) {
            addCriterion("imgS not between", value1, value2, "imgS");
            return (Criteria) this;
        }

        public Criteria andImgBIsNull() {
            addCriterion("imgB is null");
            return (Criteria) this;
        }

        public Criteria andImgBIsNotNull() {
            addCriterion("imgB is not null");
            return (Criteria) this;
        }

        public Criteria andImgBEqualTo(String value) {
            addCriterion("imgB =", value, "imgB");
            return (Criteria) this;
        }

        public Criteria andImgBNotEqualTo(String value) {
            addCriterion("imgB <>", value, "imgB");
            return (Criteria) this;
        }

        public Criteria andImgBGreaterThan(String value) {
            addCriterion("imgB >", value, "imgB");
            return (Criteria) this;
        }

        public Criteria andImgBGreaterThanOrEqualTo(String value) {
            addCriterion("imgB >=", value, "imgB");
            return (Criteria) this;
        }

        public Criteria andImgBLessThan(String value) {
            addCriterion("imgB <", value, "imgB");
            return (Criteria) this;
        }

        public Criteria andImgBLessThanOrEqualTo(String value) {
            addCriterion("imgB <=", value, "imgB");
            return (Criteria) this;
        }

        public Criteria andImgBLike(String value) {
            addCriterion("imgB like", value, "imgB");
            return (Criteria) this;
        }

        public Criteria andImgBNotLike(String value) {
            addCriterion("imgB not like", value, "imgB");
            return (Criteria) this;
        }

        public Criteria andImgBIn(List<String> values) {
            addCriterion("imgB in", values, "imgB");
            return (Criteria) this;
        }

        public Criteria andImgBNotIn(List<String> values) {
            addCriterion("imgB not in", values, "imgB");
            return (Criteria) this;
        }

        public Criteria andImgBBetween(String value1, String value2) {
            addCriterion("imgB between", value1, value2, "imgB");
            return (Criteria) this;
        }

        public Criteria andImgBNotBetween(String value1, String value2) {
            addCriterion("imgB not between", value1, value2, "imgB");
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

        public Criteria andSalesIsNull() {
            addCriterion("sales is null");
            return (Criteria) this;
        }

        public Criteria andSalesIsNotNull() {
            addCriterion("sales is not null");
            return (Criteria) this;
        }

        public Criteria andSalesEqualTo(Integer value) {
            addCriterion("sales =", value, "sales");
            return (Criteria) this;
        }

        public Criteria andSalesNotEqualTo(Integer value) {
            addCriterion("sales <>", value, "sales");
            return (Criteria) this;
        }

        public Criteria andSalesGreaterThan(Integer value) {
            addCriterion("sales >", value, "sales");
            return (Criteria) this;
        }

        public Criteria andSalesGreaterThanOrEqualTo(Integer value) {
            addCriterion("sales >=", value, "sales");
            return (Criteria) this;
        }

        public Criteria andSalesLessThan(Integer value) {
            addCriterion("sales <", value, "sales");
            return (Criteria) this;
        }

        public Criteria andSalesLessThanOrEqualTo(Integer value) {
            addCriterion("sales <=", value, "sales");
            return (Criteria) this;
        }

        public Criteria andSalesIn(List<Integer> values) {
            addCriterion("sales in", values, "sales");
            return (Criteria) this;
        }

        public Criteria andSalesNotIn(List<Integer> values) {
            addCriterion("sales not in", values, "sales");
            return (Criteria) this;
        }

        public Criteria andSalesBetween(Integer value1, Integer value2) {
            addCriterion("sales between", value1, value2, "sales");
            return (Criteria) this;
        }

        public Criteria andSalesNotBetween(Integer value1, Integer value2) {
            addCriterion("sales not between", value1, value2, "sales");
            return (Criteria) this;
        }

        public Criteria andNewsIsNull() {
            addCriterion("news is null");
            return (Criteria) this;
        }

        public Criteria andNewsIsNotNull() {
            addCriterion("news is not null");
            return (Criteria) this;
        }

        public Criteria andNewsEqualTo(Boolean value) {
            addCriterion("news =", value, "news");
            return (Criteria) this;
        }

        public Criteria andNewsNotEqualTo(Boolean value) {
            addCriterion("news <>", value, "news");
            return (Criteria) this;
        }

        public Criteria andNewsGreaterThan(Boolean value) {
            addCriterion("news >", value, "news");
            return (Criteria) this;
        }

        public Criteria andNewsGreaterThanOrEqualTo(Boolean value) {
            addCriterion("news >=", value, "news");
            return (Criteria) this;
        }

        public Criteria andNewsLessThan(Boolean value) {
            addCriterion("news <", value, "news");
            return (Criteria) this;
        }

        public Criteria andNewsLessThanOrEqualTo(Boolean value) {
            addCriterion("news <=", value, "news");
            return (Criteria) this;
        }

        public Criteria andNewsIn(List<Boolean> values) {
            addCriterion("news in", values, "news");
            return (Criteria) this;
        }

        public Criteria andNewsNotIn(List<Boolean> values) {
            addCriterion("news not in", values, "news");
            return (Criteria) this;
        }

        public Criteria andNewsBetween(Boolean value1, Boolean value2) {
            addCriterion("news between", value1, value2, "news");
            return (Criteria) this;
        }

        public Criteria andNewsNotBetween(Boolean value1, Boolean value2) {
            addCriterion("news not between", value1, value2, "news");
            return (Criteria) this;
        }

        public Criteria andTuiJianIsNull() {
            addCriterion("tuiJian is null");
            return (Criteria) this;
        }

        public Criteria andTuiJianIsNotNull() {
            addCriterion("tuiJian is not null");
            return (Criteria) this;
        }

        public Criteria andTuiJianEqualTo(Boolean value) {
            addCriterion("tuiJian =", value, "tuiJian");
            return (Criteria) this;
        }

        public Criteria andTuiJianNotEqualTo(Boolean value) {
            addCriterion("tuiJian <>", value, "tuiJian");
            return (Criteria) this;
        }

        public Criteria andTuiJianGreaterThan(Boolean value) {
            addCriterion("tuiJian >", value, "tuiJian");
            return (Criteria) this;
        }

        public Criteria andTuiJianGreaterThanOrEqualTo(Boolean value) {
            addCriterion("tuiJian >=", value, "tuiJian");
            return (Criteria) this;
        }

        public Criteria andTuiJianLessThan(Boolean value) {
            addCriterion("tuiJian <", value, "tuiJian");
            return (Criteria) this;
        }

        public Criteria andTuiJianLessThanOrEqualTo(Boolean value) {
            addCriterion("tuiJian <=", value, "tuiJian");
            return (Criteria) this;
        }

        public Criteria andTuiJianIn(List<Boolean> values) {
            addCriterion("tuiJian in", values, "tuiJian");
            return (Criteria) this;
        }

        public Criteria andTuiJianNotIn(List<Boolean> values) {
            addCriterion("tuiJian not in", values, "tuiJian");
            return (Criteria) this;
        }

        public Criteria andTuiJianBetween(Boolean value1, Boolean value2) {
            addCriterion("tuiJian between", value1, value2, "tuiJian");
            return (Criteria) this;
        }

        public Criteria andTuiJianNotBetween(Boolean value1, Boolean value2) {
            addCriterion("tuiJian not between", value1, value2, "tuiJian");
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

        public Criteria andUpdateTimeIsNull() {
            addCriterion("updateTime is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("updateTime is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("updateTime =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("updateTime <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("updateTime >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("updateTime >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("updateTime <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("updateTime <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("updateTime in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("updateTime not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("updateTime between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("updateTime not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIsNull() {
            addCriterion("checkStatus is null");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIsNotNull() {
            addCriterion("checkStatus is not null");
            return (Criteria) this;
        }

        public Criteria andCheckStatusEqualTo(Byte value) {
            addCriterion("checkStatus =", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNotEqualTo(Byte value) {
            addCriterion("checkStatus <>", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusGreaterThan(Byte value) {
            addCriterion("checkStatus >", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("checkStatus >=", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusLessThan(Byte value) {
            addCriterion("checkStatus <", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusLessThanOrEqualTo(Byte value) {
            addCriterion("checkStatus <=", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIn(List<Byte> values) {
            addCriterion("checkStatus in", values, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNotIn(List<Byte> values) {
            addCriterion("checkStatus not in", values, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusBetween(Byte value1, Byte value2) {
            addCriterion("checkStatus between", value1, value2, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("checkStatus not between", value1, value2, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckUserIsNull() {
            addCriterion("checkUser is null");
            return (Criteria) this;
        }

        public Criteria andCheckUserIsNotNull() {
            addCriterion("checkUser is not null");
            return (Criteria) this;
        }

        public Criteria andCheckUserEqualTo(String value) {
            addCriterion("checkUser =", value, "checkUser");
            return (Criteria) this;
        }

        public Criteria andCheckUserNotEqualTo(String value) {
            addCriterion("checkUser <>", value, "checkUser");
            return (Criteria) this;
        }

        public Criteria andCheckUserGreaterThan(String value) {
            addCriterion("checkUser >", value, "checkUser");
            return (Criteria) this;
        }

        public Criteria andCheckUserGreaterThanOrEqualTo(String value) {
            addCriterion("checkUser >=", value, "checkUser");
            return (Criteria) this;
        }

        public Criteria andCheckUserLessThan(String value) {
            addCriterion("checkUser <", value, "checkUser");
            return (Criteria) this;
        }

        public Criteria andCheckUserLessThanOrEqualTo(String value) {
            addCriterion("checkUser <=", value, "checkUser");
            return (Criteria) this;
        }

        public Criteria andCheckUserLike(String value) {
            addCriterion("checkUser like", value, "checkUser");
            return (Criteria) this;
        }

        public Criteria andCheckUserNotLike(String value) {
            addCriterion("checkUser not like", value, "checkUser");
            return (Criteria) this;
        }

        public Criteria andCheckUserIn(List<String> values) {
            addCriterion("checkUser in", values, "checkUser");
            return (Criteria) this;
        }

        public Criteria andCheckUserNotIn(List<String> values) {
            addCriterion("checkUser not in", values, "checkUser");
            return (Criteria) this;
        }

        public Criteria andCheckUserBetween(String value1, String value2) {
            addCriterion("checkUser between", value1, value2, "checkUser");
            return (Criteria) this;
        }

        public Criteria andCheckUserNotBetween(String value1, String value2) {
            addCriterion("checkUser not between", value1, value2, "checkUser");
            return (Criteria) this;
        }

        public Criteria andCheckTimeIsNull() {
            addCriterion("checkTime is null");
            return (Criteria) this;
        }

        public Criteria andCheckTimeIsNotNull() {
            addCriterion("checkTime is not null");
            return (Criteria) this;
        }

        public Criteria andCheckTimeEqualTo(Date value) {
            addCriterion("checkTime =", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeNotEqualTo(Date value) {
            addCriterion("checkTime <>", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeGreaterThan(Date value) {
            addCriterion("checkTime >", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("checkTime >=", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeLessThan(Date value) {
            addCriterion("checkTime <", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeLessThanOrEqualTo(Date value) {
            addCriterion("checkTime <=", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeIn(List<Date> values) {
            addCriterion("checkTime in", values, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeNotIn(List<Date> values) {
            addCriterion("checkTime not in", values, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeBetween(Date value1, Date value2) {
            addCriterion("checkTime between", value1, value2, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeNotBetween(Date value1, Date value2) {
            addCriterion("checkTime not between", value1, value2, "checkTime");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Byte value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Byte value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Byte value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Byte value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Byte value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Byte> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Byte> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Byte value1, Byte value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("status not between", value1, value2, "status");
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

        public Criteria andCol4IsNull() {
            addCriterion("col4 is null");
            return (Criteria) this;
        }

        public Criteria andCol4IsNotNull() {
            addCriterion("col4 is not null");
            return (Criteria) this;
        }

        public Criteria andCol4EqualTo(String value) {
            addCriterion("col4 =", value, "col4");
            return (Criteria) this;
        }

        public Criteria andCol4NotEqualTo(String value) {
            addCriterion("col4 <>", value, "col4");
            return (Criteria) this;
        }

        public Criteria andCol4GreaterThan(String value) {
            addCriterion("col4 >", value, "col4");
            return (Criteria) this;
        }

        public Criteria andCol4GreaterThanOrEqualTo(String value) {
            addCriterion("col4 >=", value, "col4");
            return (Criteria) this;
        }

        public Criteria andCol4LessThan(String value) {
            addCriterion("col4 <", value, "col4");
            return (Criteria) this;
        }

        public Criteria andCol4LessThanOrEqualTo(String value) {
            addCriterion("col4 <=", value, "col4");
            return (Criteria) this;
        }

        public Criteria andCol4Like(String value) {
            addCriterion("col4 like", value, "col4");
            return (Criteria) this;
        }

        public Criteria andCol4NotLike(String value) {
            addCriterion("col4 not like", value, "col4");
            return (Criteria) this;
        }

        public Criteria andCol4In(List<String> values) {
            addCriterion("col4 in", values, "col4");
            return (Criteria) this;
        }

        public Criteria andCol4NotIn(List<String> values) {
            addCriterion("col4 not in", values, "col4");
            return (Criteria) this;
        }

        public Criteria andCol4Between(String value1, String value2) {
            addCriterion("col4 between", value1, value2, "col4");
            return (Criteria) this;
        }

        public Criteria andCol4NotBetween(String value1, String value2) {
            addCriterion("col4 not between", value1, value2, "col4");
            return (Criteria) this;
        }

        public Criteria andCol5IsNull() {
            addCriterion("col5 is null");
            return (Criteria) this;
        }

        public Criteria andCol5IsNotNull() {
            addCriterion("col5 is not null");
            return (Criteria) this;
        }

        public Criteria andCol5EqualTo(String value) {
            addCriterion("col5 =", value, "col5");
            return (Criteria) this;
        }

        public Criteria andCol5NotEqualTo(String value) {
            addCriterion("col5 <>", value, "col5");
            return (Criteria) this;
        }

        public Criteria andCol5GreaterThan(String value) {
            addCriterion("col5 >", value, "col5");
            return (Criteria) this;
        }

        public Criteria andCol5GreaterThanOrEqualTo(String value) {
            addCriterion("col5 >=", value, "col5");
            return (Criteria) this;
        }

        public Criteria andCol5LessThan(String value) {
            addCriterion("col5 <", value, "col5");
            return (Criteria) this;
        }

        public Criteria andCol5LessThanOrEqualTo(String value) {
            addCriterion("col5 <=", value, "col5");
            return (Criteria) this;
        }

        public Criteria andCol5Like(String value) {
            addCriterion("col5 like", value, "col5");
            return (Criteria) this;
        }

        public Criteria andCol5NotLike(String value) {
            addCriterion("col5 not like", value, "col5");
            return (Criteria) this;
        }

        public Criteria andCol5In(List<String> values) {
            addCriterion("col5 in", values, "col5");
            return (Criteria) this;
        }

        public Criteria andCol5NotIn(List<String> values) {
            addCriterion("col5 not in", values, "col5");
            return (Criteria) this;
        }

        public Criteria andCol5Between(String value1, String value2) {
            addCriterion("col5 between", value1, value2, "col5");
            return (Criteria) this;
        }

        public Criteria andCol5NotBetween(String value1, String value2) {
            addCriterion("col5 not between", value1, value2, "col5");
            return (Criteria) this;
        }

        public Criteria andCol6IsNull() {
            addCriterion("col6 is null");
            return (Criteria) this;
        }

        public Criteria andCol6IsNotNull() {
            addCriterion("col6 is not null");
            return (Criteria) this;
        }

        public Criteria andCol6EqualTo(String value) {
            addCriterion("col6 =", value, "col6");
            return (Criteria) this;
        }

        public Criteria andCol6NotEqualTo(String value) {
            addCriterion("col6 <>", value, "col6");
            return (Criteria) this;
        }

        public Criteria andCol6GreaterThan(String value) {
            addCriterion("col6 >", value, "col6");
            return (Criteria) this;
        }

        public Criteria andCol6GreaterThanOrEqualTo(String value) {
            addCriterion("col6 >=", value, "col6");
            return (Criteria) this;
        }

        public Criteria andCol6LessThan(String value) {
            addCriterion("col6 <", value, "col6");
            return (Criteria) this;
        }

        public Criteria andCol6LessThanOrEqualTo(String value) {
            addCriterion("col6 <=", value, "col6");
            return (Criteria) this;
        }

        public Criteria andCol6Like(String value) {
            addCriterion("col6 like", value, "col6");
            return (Criteria) this;
        }

        public Criteria andCol6NotLike(String value) {
            addCriterion("col6 not like", value, "col6");
            return (Criteria) this;
        }

        public Criteria andCol6In(List<String> values) {
            addCriterion("col6 in", values, "col6");
            return (Criteria) this;
        }

        public Criteria andCol6NotIn(List<String> values) {
            addCriterion("col6 not in", values, "col6");
            return (Criteria) this;
        }

        public Criteria andCol6Between(String value1, String value2) {
            addCriterion("col6 between", value1, value2, "col6");
            return (Criteria) this;
        }

        public Criteria andCol6NotBetween(String value1, String value2) {
            addCriterion("col6 not between", value1, value2, "col6");
            return (Criteria) this;
        }

        public Criteria andCol7IsNull() {
            addCriterion("col7 is null");
            return (Criteria) this;
        }

        public Criteria andCol7IsNotNull() {
            addCriterion("col7 is not null");
            return (Criteria) this;
        }

        public Criteria andCol7EqualTo(String value) {
            addCriterion("col7 =", value, "col7");
            return (Criteria) this;
        }

        public Criteria andCol7NotEqualTo(String value) {
            addCriterion("col7 <>", value, "col7");
            return (Criteria) this;
        }

        public Criteria andCol7GreaterThan(String value) {
            addCriterion("col7 >", value, "col7");
            return (Criteria) this;
        }

        public Criteria andCol7GreaterThanOrEqualTo(String value) {
            addCriterion("col7 >=", value, "col7");
            return (Criteria) this;
        }

        public Criteria andCol7LessThan(String value) {
            addCriterion("col7 <", value, "col7");
            return (Criteria) this;
        }

        public Criteria andCol7LessThanOrEqualTo(String value) {
            addCriterion("col7 <=", value, "col7");
            return (Criteria) this;
        }

        public Criteria andCol7Like(String value) {
            addCriterion("col7 like", value, "col7");
            return (Criteria) this;
        }

        public Criteria andCol7NotLike(String value) {
            addCriterion("col7 not like", value, "col7");
            return (Criteria) this;
        }

        public Criteria andCol7In(List<String> values) {
            addCriterion("col7 in", values, "col7");
            return (Criteria) this;
        }

        public Criteria andCol7NotIn(List<String> values) {
            addCriterion("col7 not in", values, "col7");
            return (Criteria) this;
        }

        public Criteria andCol7Between(String value1, String value2) {
            addCriterion("col7 between", value1, value2, "col7");
            return (Criteria) this;
        }

        public Criteria andCol7NotBetween(String value1, String value2) {
            addCriterion("col7 not between", value1, value2, "col7");
            return (Criteria) this;
        }

        public Criteria andCol8IsNull() {
            addCriterion("col8 is null");
            return (Criteria) this;
        }

        public Criteria andCol8IsNotNull() {
            addCriterion("col8 is not null");
            return (Criteria) this;
        }

        public Criteria andCol8EqualTo(String value) {
            addCriterion("col8 =", value, "col8");
            return (Criteria) this;
        }

        public Criteria andCol8NotEqualTo(String value) {
            addCriterion("col8 <>", value, "col8");
            return (Criteria) this;
        }

        public Criteria andCol8GreaterThan(String value) {
            addCriterion("col8 >", value, "col8");
            return (Criteria) this;
        }

        public Criteria andCol8GreaterThanOrEqualTo(String value) {
            addCriterion("col8 >=", value, "col8");
            return (Criteria) this;
        }

        public Criteria andCol8LessThan(String value) {
            addCriterion("col8 <", value, "col8");
            return (Criteria) this;
        }

        public Criteria andCol8LessThanOrEqualTo(String value) {
            addCriterion("col8 <=", value, "col8");
            return (Criteria) this;
        }

        public Criteria andCol8Like(String value) {
            addCriterion("col8 like", value, "col8");
            return (Criteria) this;
        }

        public Criteria andCol8NotLike(String value) {
            addCriterion("col8 not like", value, "col8");
            return (Criteria) this;
        }

        public Criteria andCol8In(List<String> values) {
            addCriterion("col8 in", values, "col8");
            return (Criteria) this;
        }

        public Criteria andCol8NotIn(List<String> values) {
            addCriterion("col8 not in", values, "col8");
            return (Criteria) this;
        }

        public Criteria andCol8Between(String value1, String value2) {
            addCriterion("col8 between", value1, value2, "col8");
            return (Criteria) this;
        }

        public Criteria andCol8NotBetween(String value1, String value2) {
            addCriterion("col8 not between", value1, value2, "col8");
            return (Criteria) this;
        }

        public Criteria andCol9IsNull() {
            addCriterion("col9 is null");
            return (Criteria) this;
        }

        public Criteria andCol9IsNotNull() {
            addCriterion("col9 is not null");
            return (Criteria) this;
        }

        public Criteria andCol9EqualTo(String value) {
            addCriterion("col9 =", value, "col9");
            return (Criteria) this;
        }

        public Criteria andCol9NotEqualTo(String value) {
            addCriterion("col9 <>", value, "col9");
            return (Criteria) this;
        }

        public Criteria andCol9GreaterThan(String value) {
            addCriterion("col9 >", value, "col9");
            return (Criteria) this;
        }

        public Criteria andCol9GreaterThanOrEqualTo(String value) {
            addCriterion("col9 >=", value, "col9");
            return (Criteria) this;
        }

        public Criteria andCol9LessThan(String value) {
            addCriterion("col9 <", value, "col9");
            return (Criteria) this;
        }

        public Criteria andCol9LessThanOrEqualTo(String value) {
            addCriterion("col9 <=", value, "col9");
            return (Criteria) this;
        }

        public Criteria andCol9Like(String value) {
            addCriterion("col9 like", value, "col9");
            return (Criteria) this;
        }

        public Criteria andCol9NotLike(String value) {
            addCriterion("col9 not like", value, "col9");
            return (Criteria) this;
        }

        public Criteria andCol9In(List<String> values) {
            addCriterion("col9 in", values, "col9");
            return (Criteria) this;
        }

        public Criteria andCol9NotIn(List<String> values) {
            addCriterion("col9 not in", values, "col9");
            return (Criteria) this;
        }

        public Criteria andCol9Between(String value1, String value2) {
            addCriterion("col9 between", value1, value2, "col9");
            return (Criteria) this;
        }

        public Criteria andCol9NotBetween(String value1, String value2) {
            addCriterion("col9 not between", value1, value2, "col9");
            return (Criteria) this;
        }

        public Criteria andCol10IsNull() {
            addCriterion("col10 is null");
            return (Criteria) this;
        }

        public Criteria andCol10IsNotNull() {
            addCriterion("col10 is not null");
            return (Criteria) this;
        }

        public Criteria andCol10EqualTo(String value) {
            addCriterion("col10 =", value, "col10");
            return (Criteria) this;
        }

        public Criteria andCol10NotEqualTo(String value) {
            addCriterion("col10 <>", value, "col10");
            return (Criteria) this;
        }

        public Criteria andCol10GreaterThan(String value) {
            addCriterion("col10 >", value, "col10");
            return (Criteria) this;
        }

        public Criteria andCol10GreaterThanOrEqualTo(String value) {
            addCriterion("col10 >=", value, "col10");
            return (Criteria) this;
        }

        public Criteria andCol10LessThan(String value) {
            addCriterion("col10 <", value, "col10");
            return (Criteria) this;
        }

        public Criteria andCol10LessThanOrEqualTo(String value) {
            addCriterion("col10 <=", value, "col10");
            return (Criteria) this;
        }

        public Criteria andCol10Like(String value) {
            addCriterion("col10 like", value, "col10");
            return (Criteria) this;
        }

        public Criteria andCol10NotLike(String value) {
            addCriterion("col10 not like", value, "col10");
            return (Criteria) this;
        }

        public Criteria andCol10In(List<String> values) {
            addCriterion("col10 in", values, "col10");
            return (Criteria) this;
        }

        public Criteria andCol10NotIn(List<String> values) {
            addCriterion("col10 not in", values, "col10");
            return (Criteria) this;
        }

        public Criteria andCol10Between(String value1, String value2) {
            addCriterion("col10 between", value1, value2, "col10");
            return (Criteria) this;
        }

        public Criteria andCol10NotBetween(String value1, String value2) {
            addCriterion("col10 not between", value1, value2, "col10");
            return (Criteria) this;
        }

        public Criteria andPfPriceIsNull() {
            addCriterion("pfPrice is null");
            return (Criteria) this;
        }

        public Criteria andPfPriceIsNotNull() {
            addCriterion("pfPrice is not null");
            return (Criteria) this;
        }

        public Criteria andPfPriceEqualTo(BigDecimal value) {
            addCriterion("pfPrice =", value, "pfPrice");
            return (Criteria) this;
        }

        public Criteria andPfPriceNotEqualTo(BigDecimal value) {
            addCriterion("pfPrice <>", value, "pfPrice");
            return (Criteria) this;
        }

        public Criteria andPfPriceGreaterThan(BigDecimal value) {
            addCriterion("pfPrice >", value, "pfPrice");
            return (Criteria) this;
        }

        public Criteria andPfPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("pfPrice >=", value, "pfPrice");
            return (Criteria) this;
        }

        public Criteria andPfPriceLessThan(BigDecimal value) {
            addCriterion("pfPrice <", value, "pfPrice");
            return (Criteria) this;
        }

        public Criteria andPfPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("pfPrice <=", value, "pfPrice");
            return (Criteria) this;
        }

        public Criteria andPfPriceIn(List<BigDecimal> values) {
            addCriterion("pfPrice in", values, "pfPrice");
            return (Criteria) this;
        }

        public Criteria andPfPriceNotIn(List<BigDecimal> values) {
            addCriterion("pfPrice not in", values, "pfPrice");
            return (Criteria) this;
        }

        public Criteria andPfPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("pfPrice between", value1, value2, "pfPrice");
            return (Criteria) this;
        }

        public Criteria andPfPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("pfPrice not between", value1, value2, "pfPrice");
            return (Criteria) this;
        }

        public Criteria andUseTypeIsNull() {
            addCriterion("useType is null");
            return (Criteria) this;
        }

        public Criteria andUseTypeIsNotNull() {
            addCriterion("useType is not null");
            return (Criteria) this;
        }

        public Criteria andUseTypeEqualTo(Byte value) {
            addCriterion("useType =", value, "useType");
            return (Criteria) this;
        }

        public Criteria andUseTypeNotEqualTo(Byte value) {
            addCriterion("useType <>", value, "useType");
            return (Criteria) this;
        }

        public Criteria andUseTypeGreaterThan(Byte value) {
            addCriterion("useType >", value, "useType");
            return (Criteria) this;
        }

        public Criteria andUseTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("useType >=", value, "useType");
            return (Criteria) this;
        }

        public Criteria andUseTypeLessThan(Byte value) {
            addCriterion("useType <", value, "useType");
            return (Criteria) this;
        }

        public Criteria andUseTypeLessThanOrEqualTo(Byte value) {
            addCriterion("useType <=", value, "useType");
            return (Criteria) this;
        }

        public Criteria andUseTypeIn(List<Byte> values) {
            addCriterion("useType in", values, "useType");
            return (Criteria) this;
        }

        public Criteria andUseTypeNotIn(List<Byte> values) {
            addCriterion("useType not in", values, "useType");
            return (Criteria) this;
        }

        public Criteria andUseTypeBetween(Byte value1, Byte value2) {
            addCriterion("useType between", value1, value2, "useType");
            return (Criteria) this;
        }

        public Criteria andUseTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("useType not between", value1, value2, "useType");
            return (Criteria) this;
        }

        public Criteria andTasteIsNull() {
            addCriterion("taste is null");
            return (Criteria) this;
        }

        public Criteria andTasteIsNotNull() {
            addCriterion("taste is not null");
            return (Criteria) this;
        }

        public Criteria andTasteEqualTo(String value) {
            addCriterion("taste =", value, "taste");
            return (Criteria) this;
        }

        public Criteria andTasteNotEqualTo(String value) {
            addCriterion("taste <>", value, "taste");
            return (Criteria) this;
        }

        public Criteria andTasteGreaterThan(String value) {
            addCriterion("taste >", value, "taste");
            return (Criteria) this;
        }

        public Criteria andTasteGreaterThanOrEqualTo(String value) {
            addCriterion("taste >=", value, "taste");
            return (Criteria) this;
        }

        public Criteria andTasteLessThan(String value) {
            addCriterion("taste <", value, "taste");
            return (Criteria) this;
        }

        public Criteria andTasteLessThanOrEqualTo(String value) {
            addCriterion("taste <=", value, "taste");
            return (Criteria) this;
        }

        public Criteria andTasteLike(String value) {
            addCriterion("taste like", value, "taste");
            return (Criteria) this;
        }

        public Criteria andTasteNotLike(String value) {
            addCriterion("taste not like", value, "taste");
            return (Criteria) this;
        }

        public Criteria andTasteIn(List<String> values) {
            addCriterion("taste in", values, "taste");
            return (Criteria) this;
        }

        public Criteria andTasteNotIn(List<String> values) {
            addCriterion("taste not in", values, "taste");
            return (Criteria) this;
        }

        public Criteria andTasteBetween(String value1, String value2) {
            addCriterion("taste between", value1, value2, "taste");
            return (Criteria) this;
        }

        public Criteria andTasteNotBetween(String value1, String value2) {
            addCriterion("taste not between", value1, value2, "taste");
            return (Criteria) this;
        }

        public Criteria andMeasureIsNull() {
            addCriterion("measure is null");
            return (Criteria) this;
        }

        public Criteria andMeasureIsNotNull() {
            addCriterion("measure is not null");
            return (Criteria) this;
        }

        public Criteria andMeasureEqualTo(String value) {
            addCriterion("measure =", value, "measure");
            return (Criteria) this;
        }

        public Criteria andMeasureNotEqualTo(String value) {
            addCriterion("measure <>", value, "measure");
            return (Criteria) this;
        }

        public Criteria andMeasureGreaterThan(String value) {
            addCriterion("measure >", value, "measure");
            return (Criteria) this;
        }

        public Criteria andMeasureGreaterThanOrEqualTo(String value) {
            addCriterion("measure >=", value, "measure");
            return (Criteria) this;
        }

        public Criteria andMeasureLessThan(String value) {
            addCriterion("measure <", value, "measure");
            return (Criteria) this;
        }

        public Criteria andMeasureLessThanOrEqualTo(String value) {
            addCriterion("measure <=", value, "measure");
            return (Criteria) this;
        }

        public Criteria andMeasureLike(String value) {
            addCriterion("measure like", value, "measure");
            return (Criteria) this;
        }

        public Criteria andMeasureNotLike(String value) {
            addCriterion("measure not like", value, "measure");
            return (Criteria) this;
        }

        public Criteria andMeasureIn(List<String> values) {
            addCriterion("measure in", values, "measure");
            return (Criteria) this;
        }

        public Criteria andMeasureNotIn(List<String> values) {
            addCriterion("measure not in", values, "measure");
            return (Criteria) this;
        }

        public Criteria andMeasureBetween(String value1, String value2) {
            addCriterion("measure between", value1, value2, "measure");
            return (Criteria) this;
        }

        public Criteria andMeasureNotBetween(String value1, String value2) {
            addCriterion("measure not between", value1, value2, "measure");
            return (Criteria) this;
        }

        public Criteria andQuanNmIsNull() {
            addCriterion("quanNm is null");
            return (Criteria) this;
        }

        public Criteria andQuanNmIsNotNull() {
            addCriterion("quanNm is not null");
            return (Criteria) this;
        }

        public Criteria andQuanNmEqualTo(String value) {
            addCriterion("quanNm =", value, "quanNm");
            return (Criteria) this;
        }

        public Criteria andQuanNmNotEqualTo(String value) {
            addCriterion("quanNm <>", value, "quanNm");
            return (Criteria) this;
        }

        public Criteria andQuanNmGreaterThan(String value) {
            addCriterion("quanNm >", value, "quanNm");
            return (Criteria) this;
        }

        public Criteria andQuanNmGreaterThanOrEqualTo(String value) {
            addCriterion("quanNm >=", value, "quanNm");
            return (Criteria) this;
        }

        public Criteria andQuanNmLessThan(String value) {
            addCriterion("quanNm <", value, "quanNm");
            return (Criteria) this;
        }

        public Criteria andQuanNmLessThanOrEqualTo(String value) {
            addCriterion("quanNm <=", value, "quanNm");
            return (Criteria) this;
        }

        public Criteria andQuanNmLike(String value) {
            addCriterion("quanNm like", value, "quanNm");
            return (Criteria) this;
        }

        public Criteria andQuanNmNotLike(String value) {
            addCriterion("quanNm not like", value, "quanNm");
            return (Criteria) this;
        }

        public Criteria andQuanNmIn(List<String> values) {
            addCriterion("quanNm in", values, "quanNm");
            return (Criteria) this;
        }

        public Criteria andQuanNmNotIn(List<String> values) {
            addCriterion("quanNm not in", values, "quanNm");
            return (Criteria) this;
        }

        public Criteria andQuanNmBetween(String value1, String value2) {
            addCriterion("quanNm between", value1, value2, "quanNm");
            return (Criteria) this;
        }

        public Criteria andQuanNmNotBetween(String value1, String value2) {
            addCriterion("quanNm not between", value1, value2, "quanNm");
            return (Criteria) this;
        }

        public Criteria andShortNmIsNull() {
            addCriterion("shortNm is null");
            return (Criteria) this;
        }

        public Criteria andShortNmIsNotNull() {
            addCriterion("shortNm is not null");
            return (Criteria) this;
        }

        public Criteria andShortNmEqualTo(String value) {
            addCriterion("shortNm =", value, "shortNm");
            return (Criteria) this;
        }

        public Criteria andShortNmNotEqualTo(String value) {
            addCriterion("shortNm <>", value, "shortNm");
            return (Criteria) this;
        }

        public Criteria andShortNmGreaterThan(String value) {
            addCriterion("shortNm >", value, "shortNm");
            return (Criteria) this;
        }

        public Criteria andShortNmGreaterThanOrEqualTo(String value) {
            addCriterion("shortNm >=", value, "shortNm");
            return (Criteria) this;
        }

        public Criteria andShortNmLessThan(String value) {
            addCriterion("shortNm <", value, "shortNm");
            return (Criteria) this;
        }

        public Criteria andShortNmLessThanOrEqualTo(String value) {
            addCriterion("shortNm <=", value, "shortNm");
            return (Criteria) this;
        }

        public Criteria andShortNmLike(String value) {
            addCriterion("shortNm like", value, "shortNm");
            return (Criteria) this;
        }

        public Criteria andShortNmNotLike(String value) {
            addCriterion("shortNm not like", value, "shortNm");
            return (Criteria) this;
        }

        public Criteria andShortNmIn(List<String> values) {
            addCriterion("shortNm in", values, "shortNm");
            return (Criteria) this;
        }

        public Criteria andShortNmNotIn(List<String> values) {
            addCriterion("shortNm not in", values, "shortNm");
            return (Criteria) this;
        }

        public Criteria andShortNmBetween(String value1, String value2) {
            addCriterion("shortNm between", value1, value2, "shortNm");
            return (Criteria) this;
        }

        public Criteria andShortNmNotBetween(String value1, String value2) {
            addCriterion("shortNm not between", value1, value2, "shortNm");
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

        public Criteria andPkgNumIsNull() {
            addCriterion("pkgNum is null");
            return (Criteria) this;
        }

        public Criteria andPkgNumIsNotNull() {
            addCriterion("pkgNum is not null");
            return (Criteria) this;
        }

        public Criteria andPkgNumEqualTo(Integer value) {
            addCriterion("pkgNum =", value, "pkgNum");
            return (Criteria) this;
        }

        public Criteria andPkgNumNotEqualTo(Integer value) {
            addCriterion("pkgNum <>", value, "pkgNum");
            return (Criteria) this;
        }

        public Criteria andPkgNumGreaterThan(Integer value) {
            addCriterion("pkgNum >", value, "pkgNum");
            return (Criteria) this;
        }

        public Criteria andPkgNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("pkgNum >=", value, "pkgNum");
            return (Criteria) this;
        }

        public Criteria andPkgNumLessThan(Integer value) {
            addCriterion("pkgNum <", value, "pkgNum");
            return (Criteria) this;
        }

        public Criteria andPkgNumLessThanOrEqualTo(Integer value) {
            addCriterion("pkgNum <=", value, "pkgNum");
            return (Criteria) this;
        }

        public Criteria andPkgNumIn(List<Integer> values) {
            addCriterion("pkgNum in", values, "pkgNum");
            return (Criteria) this;
        }

        public Criteria andPkgNumNotIn(List<Integer> values) {
            addCriterion("pkgNum not in", values, "pkgNum");
            return (Criteria) this;
        }

        public Criteria andPkgNumBetween(Integer value1, Integer value2) {
            addCriterion("pkgNum between", value1, value2, "pkgNum");
            return (Criteria) this;
        }

        public Criteria andPkgNumNotBetween(Integer value1, Integer value2) {
            addCriterion("pkgNum not between", value1, value2, "pkgNum");
            return (Criteria) this;
        }

        public Criteria andUpIdIsNull() {
            addCriterion("upId is null");
            return (Criteria) this;
        }

        public Criteria andUpIdIsNotNull() {
            addCriterion("upId is not null");
            return (Criteria) this;
        }

        public Criteria andUpIdEqualTo(Integer value) {
            addCriterion("upId =", value, "upId");
            return (Criteria) this;
        }

        public Criteria andUpIdNotEqualTo(Integer value) {
            addCriterion("upId <>", value, "upId");
            return (Criteria) this;
        }

        public Criteria andUpIdGreaterThan(Integer value) {
            addCriterion("upId >", value, "upId");
            return (Criteria) this;
        }

        public Criteria andUpIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("upId >=", value, "upId");
            return (Criteria) this;
        }

        public Criteria andUpIdLessThan(Integer value) {
            addCriterion("upId <", value, "upId");
            return (Criteria) this;
        }

        public Criteria andUpIdLessThanOrEqualTo(Integer value) {
            addCriterion("upId <=", value, "upId");
            return (Criteria) this;
        }

        public Criteria andUpIdIn(List<Integer> values) {
            addCriterion("upId in", values, "upId");
            return (Criteria) this;
        }

        public Criteria andUpIdNotIn(List<Integer> values) {
            addCriterion("upId not in", values, "upId");
            return (Criteria) this;
        }

        public Criteria andUpIdBetween(Integer value1, Integer value2) {
            addCriterion("upId between", value1, value2, "upId");
            return (Criteria) this;
        }

        public Criteria andUpIdNotBetween(Integer value1, Integer value2) {
            addCriterion("upId not between", value1, value2, "upId");
            return (Criteria) this;
        }

        public Criteria andBrandIdIsNull() {
            addCriterion("brandId is null");
            return (Criteria) this;
        }

        public Criteria andBrandIdIsNotNull() {
            addCriterion("brandId is not null");
            return (Criteria) this;
        }

        public Criteria andBrandIdEqualTo(Integer value) {
            addCriterion("brandId =", value, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdNotEqualTo(Integer value) {
            addCriterion("brandId <>", value, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdGreaterThan(Integer value) {
            addCriterion("brandId >", value, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("brandId >=", value, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdLessThan(Integer value) {
            addCriterion("brandId <", value, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdLessThanOrEqualTo(Integer value) {
            addCriterion("brandId <=", value, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdIn(List<Integer> values) {
            addCriterion("brandId in", values, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdNotIn(List<Integer> values) {
            addCriterion("brandId not in", values, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdBetween(Integer value1, Integer value2) {
            addCriterion("brandId between", value1, value2, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdNotBetween(Integer value1, Integer value2) {
            addCriterion("brandId not between", value1, value2, "brandId");
            return (Criteria) this;
        }

        public Criteria andTgIdIsNull() {
            addCriterion("tgId is null");
            return (Criteria) this;
        }

        public Criteria andTgIdIsNotNull() {
            addCriterion("tgId is not null");
            return (Criteria) this;
        }

        public Criteria andTgIdEqualTo(String value) {
            addCriterion("tgId =", value, "tgId");
            return (Criteria) this;
        }

        public Criteria andTgIdNotEqualTo(String value) {
            addCriterion("tgId <>", value, "tgId");
            return (Criteria) this;
        }

        public Criteria andTgIdGreaterThan(String value) {
            addCriterion("tgId >", value, "tgId");
            return (Criteria) this;
        }

        public Criteria andTgIdGreaterThanOrEqualTo(String value) {
            addCriterion("tgId >=", value, "tgId");
            return (Criteria) this;
        }

        public Criteria andTgIdLessThan(String value) {
            addCriterion("tgId <", value, "tgId");
            return (Criteria) this;
        }

        public Criteria andTgIdLessThanOrEqualTo(String value) {
            addCriterion("tgId <=", value, "tgId");
            return (Criteria) this;
        }

        public Criteria andTgIdLike(String value) {
            addCriterion("tgId like", value, "tgId");
            return (Criteria) this;
        }

        public Criteria andTgIdNotLike(String value) {
            addCriterion("tgId not like", value, "tgId");
            return (Criteria) this;
        }

        public Criteria andTgIdIn(List<String> values) {
            addCriterion("tgId in", values, "tgId");
            return (Criteria) this;
        }

        public Criteria andTgIdNotIn(List<String> values) {
            addCriterion("tgId not in", values, "tgId");
            return (Criteria) this;
        }

        public Criteria andTgIdBetween(String value1, String value2) {
            addCriterion("tgId between", value1, value2, "tgId");
            return (Criteria) this;
        }

        public Criteria andTgIdNotBetween(String value1, String value2) {
            addCriterion("tgId not between", value1, value2, "tgId");
            return (Criteria) this;
        }

        public Criteria andColorIsNull() {
            addCriterion("color is null");
            return (Criteria) this;
        }

        public Criteria andColorIsNotNull() {
            addCriterion("color is not null");
            return (Criteria) this;
        }

        public Criteria andColorEqualTo(String value) {
            addCriterion("color =", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorNotEqualTo(String value) {
            addCriterion("color <>", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorGreaterThan(String value) {
            addCriterion("color >", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorGreaterThanOrEqualTo(String value) {
            addCriterion("color >=", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorLessThan(String value) {
            addCriterion("color <", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorLessThanOrEqualTo(String value) {
            addCriterion("color <=", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorLike(String value) {
            addCriterion("color like", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorNotLike(String value) {
            addCriterion("color not like", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorIn(List<String> values) {
            addCriterion("color in", values, "color");
            return (Criteria) this;
        }

        public Criteria andColorNotIn(List<String> values) {
            addCriterion("color not in", values, "color");
            return (Criteria) this;
        }

        public Criteria andColorBetween(String value1, String value2) {
            addCriterion("color between", value1, value2, "color");
            return (Criteria) this;
        }

        public Criteria andColorNotBetween(String value1, String value2) {
            addCriterion("color not between", value1, value2, "color");
            return (Criteria) this;
        }

        public Criteria andUpperIsNull() {
            addCriterion("upper is null");
            return (Criteria) this;
        }

        public Criteria andUpperIsNotNull() {
            addCriterion("upper is not null");
            return (Criteria) this;
        }

        public Criteria andUpperEqualTo(Integer value) {
            addCriterion("upper =", value, "upper");
            return (Criteria) this;
        }

        public Criteria andUpperNotEqualTo(Integer value) {
            addCriterion("upper <>", value, "upper");
            return (Criteria) this;
        }

        public Criteria andUpperGreaterThan(Integer value) {
            addCriterion("upper >", value, "upper");
            return (Criteria) this;
        }

        public Criteria andUpperGreaterThanOrEqualTo(Integer value) {
            addCriterion("upper >=", value, "upper");
            return (Criteria) this;
        }

        public Criteria andUpperLessThan(Integer value) {
            addCriterion("upper <", value, "upper");
            return (Criteria) this;
        }

        public Criteria andUpperLessThanOrEqualTo(Integer value) {
            addCriterion("upper <=", value, "upper");
            return (Criteria) this;
        }

        public Criteria andUpperIn(List<Integer> values) {
            addCriterion("upper in", values, "upper");
            return (Criteria) this;
        }

        public Criteria andUpperNotIn(List<Integer> values) {
            addCriterion("upper not in", values, "upper");
            return (Criteria) this;
        }

        public Criteria andUpperBetween(Integer value1, Integer value2) {
            addCriterion("upper between", value1, value2, "upper");
            return (Criteria) this;
        }

        public Criteria andUpperNotBetween(Integer value1, Integer value2) {
            addCriterion("upper not between", value1, value2, "upper");
            return (Criteria) this;
        }

        public Criteria andLowerIsNull() {
            addCriterion("lower is null");
            return (Criteria) this;
        }

        public Criteria andLowerIsNotNull() {
            addCriterion("lower is not null");
            return (Criteria) this;
        }

        public Criteria andLowerEqualTo(Integer value) {
            addCriterion("lower =", value, "lower");
            return (Criteria) this;
        }

        public Criteria andLowerNotEqualTo(Integer value) {
            addCriterion("lower <>", value, "lower");
            return (Criteria) this;
        }

        public Criteria andLowerGreaterThan(Integer value) {
            addCriterion("lower >", value, "lower");
            return (Criteria) this;
        }

        public Criteria andLowerGreaterThanOrEqualTo(Integer value) {
            addCriterion("lower >=", value, "lower");
            return (Criteria) this;
        }

        public Criteria andLowerLessThan(Integer value) {
            addCriterion("lower <", value, "lower");
            return (Criteria) this;
        }

        public Criteria andLowerLessThanOrEqualTo(Integer value) {
            addCriterion("lower <=", value, "lower");
            return (Criteria) this;
        }

        public Criteria andLowerIn(List<Integer> values) {
            addCriterion("lower in", values, "lower");
            return (Criteria) this;
        }

        public Criteria andLowerNotIn(List<Integer> values) {
            addCriterion("lower not in", values, "lower");
            return (Criteria) this;
        }

        public Criteria andLowerBetween(Integer value1, Integer value2) {
            addCriterion("lower between", value1, value2, "lower");
            return (Criteria) this;
        }

        public Criteria andLowerNotBetween(Integer value1, Integer value2) {
            addCriterion("lower not between", value1, value2, "lower");
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