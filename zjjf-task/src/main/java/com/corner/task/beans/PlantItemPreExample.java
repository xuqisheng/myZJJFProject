package com.corner.task.beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PlantItemPreExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PlantItemPreExample() {
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

        public Criteria andAreaIdIsNull() {
            addCriterion("areaId is null");
            return (Criteria) this;
        }

        public Criteria andAreaIdIsNotNull() {
            addCriterion("areaId is not null");
            return (Criteria) this;
        }

        public Criteria andAreaIdEqualTo(Integer value) {
            addCriterion("areaId =", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdNotEqualTo(Integer value) {
            addCriterion("areaId <>", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdGreaterThan(Integer value) {
            addCriterion("areaId >", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("areaId >=", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdLessThan(Integer value) {
            addCriterion("areaId <", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdLessThanOrEqualTo(Integer value) {
            addCriterion("areaId <=", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdIn(List<Integer> values) {
            addCriterion("areaId in", values, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdNotIn(List<Integer> values) {
            addCriterion("areaId not in", values, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdBetween(Integer value1, Integer value2) {
            addCriterion("areaId between", value1, value2, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdNotBetween(Integer value1, Integer value2) {
            addCriterion("areaId not between", value1, value2, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaNameIsNull() {
            addCriterion("areaName is null");
            return (Criteria) this;
        }

        public Criteria andAreaNameIsNotNull() {
            addCriterion("areaName is not null");
            return (Criteria) this;
        }

        public Criteria andAreaNameEqualTo(String value) {
            addCriterion("areaName =", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameNotEqualTo(String value) {
            addCriterion("areaName <>", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameGreaterThan(String value) {
            addCriterion("areaName >", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameGreaterThanOrEqualTo(String value) {
            addCriterion("areaName >=", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameLessThan(String value) {
            addCriterion("areaName <", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameLessThanOrEqualTo(String value) {
            addCriterion("areaName <=", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameLike(String value) {
            addCriterion("areaName like", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameNotLike(String value) {
            addCriterion("areaName not like", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameIn(List<String> values) {
            addCriterion("areaName in", values, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameNotIn(List<String> values) {
            addCriterion("areaName not in", values, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameBetween(String value1, String value2) {
            addCriterion("areaName between", value1, value2, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameNotBetween(String value1, String value2) {
            addCriterion("areaName not between", value1, value2, "areaName");
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

        public Criteria andOrdIdIsNull() {
            addCriterion("ordId is null");
            return (Criteria) this;
        }

        public Criteria andOrdIdIsNotNull() {
            addCriterion("ordId is not null");
            return (Criteria) this;
        }

        public Criteria andOrdIdEqualTo(Integer value) {
            addCriterion("ordId =", value, "ordId");
            return (Criteria) this;
        }

        public Criteria andOrdIdNotEqualTo(Integer value) {
            addCriterion("ordId <>", value, "ordId");
            return (Criteria) this;
        }

        public Criteria andOrdIdGreaterThan(Integer value) {
            addCriterion("ordId >", value, "ordId");
            return (Criteria) this;
        }

        public Criteria andOrdIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ordId >=", value, "ordId");
            return (Criteria) this;
        }

        public Criteria andOrdIdLessThan(Integer value) {
            addCriterion("ordId <", value, "ordId");
            return (Criteria) this;
        }

        public Criteria andOrdIdLessThanOrEqualTo(Integer value) {
            addCriterion("ordId <=", value, "ordId");
            return (Criteria) this;
        }

        public Criteria andOrdIdIn(List<Integer> values) {
            addCriterion("ordId in", values, "ordId");
            return (Criteria) this;
        }

        public Criteria andOrdIdNotIn(List<Integer> values) {
            addCriterion("ordId not in", values, "ordId");
            return (Criteria) this;
        }

        public Criteria andOrdIdBetween(Integer value1, Integer value2) {
            addCriterion("ordId between", value1, value2, "ordId");
            return (Criteria) this;
        }

        public Criteria andOrdIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ordId not between", value1, value2, "ordId");
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

        public Criteria andTuijianIsNull() {
            addCriterion("tuijian is null");
            return (Criteria) this;
        }

        public Criteria andTuijianIsNotNull() {
            addCriterion("tuijian is not null");
            return (Criteria) this;
        }

        public Criteria andTuijianEqualTo(Boolean value) {
            addCriterion("tuijian =", value, "tuijian");
            return (Criteria) this;
        }

        public Criteria andTuijianNotEqualTo(Boolean value) {
            addCriterion("tuijian <>", value, "tuijian");
            return (Criteria) this;
        }

        public Criteria andTuijianGreaterThan(Boolean value) {
            addCriterion("tuijian >", value, "tuijian");
            return (Criteria) this;
        }

        public Criteria andTuijianGreaterThanOrEqualTo(Boolean value) {
            addCriterion("tuijian >=", value, "tuijian");
            return (Criteria) this;
        }

        public Criteria andTuijianLessThan(Boolean value) {
            addCriterion("tuijian <", value, "tuijian");
            return (Criteria) this;
        }

        public Criteria andTuijianLessThanOrEqualTo(Boolean value) {
            addCriterion("tuijian <=", value, "tuijian");
            return (Criteria) this;
        }

        public Criteria andTuijianIn(List<Boolean> values) {
            addCriterion("tuijian in", values, "tuijian");
            return (Criteria) this;
        }

        public Criteria andTuijianNotIn(List<Boolean> values) {
            addCriterion("tuijian not in", values, "tuijian");
            return (Criteria) this;
        }

        public Criteria andTuijianBetween(Boolean value1, Boolean value2) {
            addCriterion("tuijian between", value1, value2, "tuijian");
            return (Criteria) this;
        }

        public Criteria andTuijianNotBetween(Boolean value1, Boolean value2) {
            addCriterion("tuijian not between", value1, value2, "tuijian");
            return (Criteria) this;
        }

        public Criteria andGoodsStockIsNull() {
            addCriterion("goodsStock is null");
            return (Criteria) this;
        }

        public Criteria andGoodsStockIsNotNull() {
            addCriterion("goodsStock is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsStockEqualTo(Integer value) {
            addCriterion("goodsStock =", value, "goodsStock");
            return (Criteria) this;
        }

        public Criteria andGoodsStockNotEqualTo(Integer value) {
            addCriterion("goodsStock <>", value, "goodsStock");
            return (Criteria) this;
        }

        public Criteria andGoodsStockGreaterThan(Integer value) {
            addCriterion("goodsStock >", value, "goodsStock");
            return (Criteria) this;
        }

        public Criteria andGoodsStockGreaterThanOrEqualTo(Integer value) {
            addCriterion("goodsStock >=", value, "goodsStock");
            return (Criteria) this;
        }

        public Criteria andGoodsStockLessThan(Integer value) {
            addCriterion("goodsStock <", value, "goodsStock");
            return (Criteria) this;
        }

        public Criteria andGoodsStockLessThanOrEqualTo(Integer value) {
            addCriterion("goodsStock <=", value, "goodsStock");
            return (Criteria) this;
        }

        public Criteria andGoodsStockIn(List<Integer> values) {
            addCriterion("goodsStock in", values, "goodsStock");
            return (Criteria) this;
        }

        public Criteria andGoodsStockNotIn(List<Integer> values) {
            addCriterion("goodsStock not in", values, "goodsStock");
            return (Criteria) this;
        }

        public Criteria andGoodsStockBetween(Integer value1, Integer value2) {
            addCriterion("goodsStock between", value1, value2, "goodsStock");
            return (Criteria) this;
        }

        public Criteria andGoodsStockNotBetween(Integer value1, Integer value2) {
            addCriterion("goodsStock not between", value1, value2, "goodsStock");
            return (Criteria) this;
        }

        public Criteria andMiddleStockIsNull() {
            addCriterion("middleStock is null");
            return (Criteria) this;
        }

        public Criteria andMiddleStockIsNotNull() {
            addCriterion("middleStock is not null");
            return (Criteria) this;
        }

        public Criteria andMiddleStockEqualTo(Integer value) {
            addCriterion("middleStock =", value, "middleStock");
            return (Criteria) this;
        }

        public Criteria andMiddleStockNotEqualTo(Integer value) {
            addCriterion("middleStock <>", value, "middleStock");
            return (Criteria) this;
        }

        public Criteria andMiddleStockGreaterThan(Integer value) {
            addCriterion("middleStock >", value, "middleStock");
            return (Criteria) this;
        }

        public Criteria andMiddleStockGreaterThanOrEqualTo(Integer value) {
            addCriterion("middleStock >=", value, "middleStock");
            return (Criteria) this;
        }

        public Criteria andMiddleStockLessThan(Integer value) {
            addCriterion("middleStock <", value, "middleStock");
            return (Criteria) this;
        }

        public Criteria andMiddleStockLessThanOrEqualTo(Integer value) {
            addCriterion("middleStock <=", value, "middleStock");
            return (Criteria) this;
        }

        public Criteria andMiddleStockIn(List<Integer> values) {
            addCriterion("middleStock in", values, "middleStock");
            return (Criteria) this;
        }

        public Criteria andMiddleStockNotIn(List<Integer> values) {
            addCriterion("middleStock not in", values, "middleStock");
            return (Criteria) this;
        }

        public Criteria andMiddleStockBetween(Integer value1, Integer value2) {
            addCriterion("middleStock between", value1, value2, "middleStock");
            return (Criteria) this;
        }

        public Criteria andMiddleStockNotBetween(Integer value1, Integer value2) {
            addCriterion("middleStock not between", value1, value2, "middleStock");
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

        public Criteria andScInPriceIsNull() {
            addCriterion("scInPrice is null");
            return (Criteria) this;
        }

        public Criteria andScInPriceIsNotNull() {
            addCriterion("scInPrice is not null");
            return (Criteria) this;
        }

        public Criteria andScInPriceEqualTo(BigDecimal value) {
            addCriterion("scInPrice =", value, "scInPrice");
            return (Criteria) this;
        }

        public Criteria andScInPriceNotEqualTo(BigDecimal value) {
            addCriterion("scInPrice <>", value, "scInPrice");
            return (Criteria) this;
        }

        public Criteria andScInPriceGreaterThan(BigDecimal value) {
            addCriterion("scInPrice >", value, "scInPrice");
            return (Criteria) this;
        }

        public Criteria andScInPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("scInPrice >=", value, "scInPrice");
            return (Criteria) this;
        }

        public Criteria andScInPriceLessThan(BigDecimal value) {
            addCriterion("scInPrice <", value, "scInPrice");
            return (Criteria) this;
        }

        public Criteria andScInPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("scInPrice <=", value, "scInPrice");
            return (Criteria) this;
        }

        public Criteria andScInPriceIn(List<BigDecimal> values) {
            addCriterion("scInPrice in", values, "scInPrice");
            return (Criteria) this;
        }

        public Criteria andScInPriceNotIn(List<BigDecimal> values) {
            addCriterion("scInPrice not in", values, "scInPrice");
            return (Criteria) this;
        }

        public Criteria andScInPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("scInPrice between", value1, value2, "scInPrice");
            return (Criteria) this;
        }

        public Criteria andScInPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("scInPrice not between", value1, value2, "scInPrice");
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

        public Criteria andClickRateIsNull() {
            addCriterion("clickRate is null");
            return (Criteria) this;
        }

        public Criteria andClickRateIsNotNull() {
            addCriterion("clickRate is not null");
            return (Criteria) this;
        }

        public Criteria andClickRateEqualTo(Integer value) {
            addCriterion("clickRate =", value, "clickRate");
            return (Criteria) this;
        }

        public Criteria andClickRateNotEqualTo(Integer value) {
            addCriterion("clickRate <>", value, "clickRate");
            return (Criteria) this;
        }

        public Criteria andClickRateGreaterThan(Integer value) {
            addCriterion("clickRate >", value, "clickRate");
            return (Criteria) this;
        }

        public Criteria andClickRateGreaterThanOrEqualTo(Integer value) {
            addCriterion("clickRate >=", value, "clickRate");
            return (Criteria) this;
        }

        public Criteria andClickRateLessThan(Integer value) {
            addCriterion("clickRate <", value, "clickRate");
            return (Criteria) this;
        }

        public Criteria andClickRateLessThanOrEqualTo(Integer value) {
            addCriterion("clickRate <=", value, "clickRate");
            return (Criteria) this;
        }

        public Criteria andClickRateIn(List<Integer> values) {
            addCriterion("clickRate in", values, "clickRate");
            return (Criteria) this;
        }

        public Criteria andClickRateNotIn(List<Integer> values) {
            addCriterion("clickRate not in", values, "clickRate");
            return (Criteria) this;
        }

        public Criteria andClickRateBetween(Integer value1, Integer value2) {
            addCriterion("clickRate between", value1, value2, "clickRate");
            return (Criteria) this;
        }

        public Criteria andClickRateNotBetween(Integer value1, Integer value2) {
            addCriterion("clickRate not between", value1, value2, "clickRate");
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

        public Criteria andStockTacticIsNull() {
            addCriterion("stockTactic is null");
            return (Criteria) this;
        }

        public Criteria andStockTacticIsNotNull() {
            addCriterion("stockTactic is not null");
            return (Criteria) this;
        }

        public Criteria andStockTacticEqualTo(Integer value) {
            addCriterion("stockTactic =", value, "stockTactic");
            return (Criteria) this;
        }

        public Criteria andStockTacticNotEqualTo(Integer value) {
            addCriterion("stockTactic <>", value, "stockTactic");
            return (Criteria) this;
        }

        public Criteria andStockTacticGreaterThan(Integer value) {
            addCriterion("stockTactic >", value, "stockTactic");
            return (Criteria) this;
        }

        public Criteria andStockTacticGreaterThanOrEqualTo(Integer value) {
            addCriterion("stockTactic >=", value, "stockTactic");
            return (Criteria) this;
        }

        public Criteria andStockTacticLessThan(Integer value) {
            addCriterion("stockTactic <", value, "stockTactic");
            return (Criteria) this;
        }

        public Criteria andStockTacticLessThanOrEqualTo(Integer value) {
            addCriterion("stockTactic <=", value, "stockTactic");
            return (Criteria) this;
        }

        public Criteria andStockTacticIn(List<Integer> values) {
            addCriterion("stockTactic in", values, "stockTactic");
            return (Criteria) this;
        }

        public Criteria andStockTacticNotIn(List<Integer> values) {
            addCriterion("stockTactic not in", values, "stockTactic");
            return (Criteria) this;
        }

        public Criteria andStockTacticBetween(Integer value1, Integer value2) {
            addCriterion("stockTactic between", value1, value2, "stockTactic");
            return (Criteria) this;
        }

        public Criteria andStockTacticNotBetween(Integer value1, Integer value2) {
            addCriterion("stockTactic not between", value1, value2, "stockTactic");
            return (Criteria) this;
        }

        public Criteria andPriceTacticIsNull() {
            addCriterion("priceTactic is null");
            return (Criteria) this;
        }

        public Criteria andPriceTacticIsNotNull() {
            addCriterion("priceTactic is not null");
            return (Criteria) this;
        }

        public Criteria andPriceTacticEqualTo(Integer value) {
            addCriterion("priceTactic =", value, "priceTactic");
            return (Criteria) this;
        }

        public Criteria andPriceTacticNotEqualTo(Integer value) {
            addCriterion("priceTactic <>", value, "priceTactic");
            return (Criteria) this;
        }

        public Criteria andPriceTacticGreaterThan(Integer value) {
            addCriterion("priceTactic >", value, "priceTactic");
            return (Criteria) this;
        }

        public Criteria andPriceTacticGreaterThanOrEqualTo(Integer value) {
            addCriterion("priceTactic >=", value, "priceTactic");
            return (Criteria) this;
        }

        public Criteria andPriceTacticLessThan(Integer value) {
            addCriterion("priceTactic <", value, "priceTactic");
            return (Criteria) this;
        }

        public Criteria andPriceTacticLessThanOrEqualTo(Integer value) {
            addCriterion("priceTactic <=", value, "priceTactic");
            return (Criteria) this;
        }

        public Criteria andPriceTacticIn(List<Integer> values) {
            addCriterion("priceTactic in", values, "priceTactic");
            return (Criteria) this;
        }

        public Criteria andPriceTacticNotIn(List<Integer> values) {
            addCriterion("priceTactic not in", values, "priceTactic");
            return (Criteria) this;
        }

        public Criteria andPriceTacticBetween(Integer value1, Integer value2) {
            addCriterion("priceTactic between", value1, value2, "priceTactic");
            return (Criteria) this;
        }

        public Criteria andPriceTacticNotBetween(Integer value1, Integer value2) {
            addCriterion("priceTactic not between", value1, value2, "priceTactic");
            return (Criteria) this;
        }

        public Criteria andIsSKUPromotionIsNull() {
            addCriterion("isSKUPromotion is null");
            return (Criteria) this;
        }

        public Criteria andIsSKUPromotionIsNotNull() {
            addCriterion("isSKUPromotion is not null");
            return (Criteria) this;
        }

        public Criteria andIsSKUPromotionEqualTo(Integer value) {
            addCriterion("isSKUPromotion =", value, "isSKUPromotion");
            return (Criteria) this;
        }

        public Criteria andIsSKUPromotionNotEqualTo(Integer value) {
            addCriterion("isSKUPromotion <>", value, "isSKUPromotion");
            return (Criteria) this;
        }

        public Criteria andIsSKUPromotionGreaterThan(Integer value) {
            addCriterion("isSKUPromotion >", value, "isSKUPromotion");
            return (Criteria) this;
        }

        public Criteria andIsSKUPromotionGreaterThanOrEqualTo(Integer value) {
            addCriterion("isSKUPromotion >=", value, "isSKUPromotion");
            return (Criteria) this;
        }

        public Criteria andIsSKUPromotionLessThan(Integer value) {
            addCriterion("isSKUPromotion <", value, "isSKUPromotion");
            return (Criteria) this;
        }

        public Criteria andIsSKUPromotionLessThanOrEqualTo(Integer value) {
            addCriterion("isSKUPromotion <=", value, "isSKUPromotion");
            return (Criteria) this;
        }

        public Criteria andIsSKUPromotionIn(List<Integer> values) {
            addCriterion("isSKUPromotion in", values, "isSKUPromotion");
            return (Criteria) this;
        }

        public Criteria andIsSKUPromotionNotIn(List<Integer> values) {
            addCriterion("isSKUPromotion not in", values, "isSKUPromotion");
            return (Criteria) this;
        }

        public Criteria andIsSKUPromotionBetween(Integer value1, Integer value2) {
            addCriterion("isSKUPromotion between", value1, value2, "isSKUPromotion");
            return (Criteria) this;
        }

        public Criteria andIsSKUPromotionNotBetween(Integer value1, Integer value2) {
            addCriterion("isSKUPromotion not between", value1, value2, "isSKUPromotion");
            return (Criteria) this;
        }

        public Criteria andSKUProStartTimeIsNull() {
            addCriterion("SKUProStartTime is null");
            return (Criteria) this;
        }

        public Criteria andSKUProStartTimeIsNotNull() {
            addCriterion("SKUProStartTime is not null");
            return (Criteria) this;
        }

        public Criteria andSKUProStartTimeEqualTo(Date value) {
            addCriterion("SKUProStartTime =", value, "SKUProStartTime");
            return (Criteria) this;
        }

        public Criteria andSKUProStartTimeNotEqualTo(Date value) {
            addCriterion("SKUProStartTime <>", value, "SKUProStartTime");
            return (Criteria) this;
        }

        public Criteria andSKUProStartTimeGreaterThan(Date value) {
            addCriterion("SKUProStartTime >", value, "SKUProStartTime");
            return (Criteria) this;
        }

        public Criteria andSKUProStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("SKUProStartTime >=", value, "SKUProStartTime");
            return (Criteria) this;
        }

        public Criteria andSKUProStartTimeLessThan(Date value) {
            addCriterion("SKUProStartTime <", value, "SKUProStartTime");
            return (Criteria) this;
        }

        public Criteria andSKUProStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("SKUProStartTime <=", value, "SKUProStartTime");
            return (Criteria) this;
        }

        public Criteria andSKUProStartTimeIn(List<Date> values) {
            addCriterion("SKUProStartTime in", values, "SKUProStartTime");
            return (Criteria) this;
        }

        public Criteria andSKUProStartTimeNotIn(List<Date> values) {
            addCriterion("SKUProStartTime not in", values, "SKUProStartTime");
            return (Criteria) this;
        }

        public Criteria andSKUProStartTimeBetween(Date value1, Date value2) {
            addCriterion("SKUProStartTime between", value1, value2, "SKUProStartTime");
            return (Criteria) this;
        }

        public Criteria andSKUProStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("SKUProStartTime not between", value1, value2, "SKUProStartTime");
            return (Criteria) this;
        }

        public Criteria andSKUProEndTimeIsNull() {
            addCriterion("SKUProEndTime is null");
            return (Criteria) this;
        }

        public Criteria andSKUProEndTimeIsNotNull() {
            addCriterion("SKUProEndTime is not null");
            return (Criteria) this;
        }

        public Criteria andSKUProEndTimeEqualTo(Date value) {
            addCriterion("SKUProEndTime =", value, "SKUProEndTime");
            return (Criteria) this;
        }

        public Criteria andSKUProEndTimeNotEqualTo(Date value) {
            addCriterion("SKUProEndTime <>", value, "SKUProEndTime");
            return (Criteria) this;
        }

        public Criteria andSKUProEndTimeGreaterThan(Date value) {
            addCriterion("SKUProEndTime >", value, "SKUProEndTime");
            return (Criteria) this;
        }

        public Criteria andSKUProEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("SKUProEndTime >=", value, "SKUProEndTime");
            return (Criteria) this;
        }

        public Criteria andSKUProEndTimeLessThan(Date value) {
            addCriterion("SKUProEndTime <", value, "SKUProEndTime");
            return (Criteria) this;
        }

        public Criteria andSKUProEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("SKUProEndTime <=", value, "SKUProEndTime");
            return (Criteria) this;
        }

        public Criteria andSKUProEndTimeIn(List<Date> values) {
            addCriterion("SKUProEndTime in", values, "SKUProEndTime");
            return (Criteria) this;
        }

        public Criteria andSKUProEndTimeNotIn(List<Date> values) {
            addCriterion("SKUProEndTime not in", values, "SKUProEndTime");
            return (Criteria) this;
        }

        public Criteria andSKUProEndTimeBetween(Date value1, Date value2) {
            addCriterion("SKUProEndTime between", value1, value2, "SKUProEndTime");
            return (Criteria) this;
        }

        public Criteria andSKUProEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("SKUProEndTime not between", value1, value2, "SKUProEndTime");
            return (Criteria) this;
        }

        public Criteria andSKUProPriceIsNull() {
            addCriterion("SKUProPrice is null");
            return (Criteria) this;
        }

        public Criteria andSKUProPriceIsNotNull() {
            addCriterion("SKUProPrice is not null");
            return (Criteria) this;
        }

        public Criteria andSKUProPriceEqualTo(BigDecimal value) {
            addCriterion("SKUProPrice =", value, "SKUProPrice");
            return (Criteria) this;
        }

        public Criteria andSKUProPriceNotEqualTo(BigDecimal value) {
            addCriterion("SKUProPrice <>", value, "SKUProPrice");
            return (Criteria) this;
        }

        public Criteria andSKUProPriceGreaterThan(BigDecimal value) {
            addCriterion("SKUProPrice >", value, "SKUProPrice");
            return (Criteria) this;
        }

        public Criteria andSKUProPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("SKUProPrice >=", value, "SKUProPrice");
            return (Criteria) this;
        }

        public Criteria andSKUProPriceLessThan(BigDecimal value) {
            addCriterion("SKUProPrice <", value, "SKUProPrice");
            return (Criteria) this;
        }

        public Criteria andSKUProPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("SKUProPrice <=", value, "SKUProPrice");
            return (Criteria) this;
        }

        public Criteria andSKUProPriceIn(List<BigDecimal> values) {
            addCriterion("SKUProPrice in", values, "SKUProPrice");
            return (Criteria) this;
        }

        public Criteria andSKUProPriceNotIn(List<BigDecimal> values) {
            addCriterion("SKUProPrice not in", values, "SKUProPrice");
            return (Criteria) this;
        }

        public Criteria andSKUProPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("SKUProPrice between", value1, value2, "SKUProPrice");
            return (Criteria) this;
        }

        public Criteria andSKUProPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("SKUProPrice not between", value1, value2, "SKUProPrice");
            return (Criteria) this;
        }

        public Criteria andSKUProLimitNumIsNull() {
            addCriterion("SKUProLimitNum is null");
            return (Criteria) this;
        }

        public Criteria andSKUProLimitNumIsNotNull() {
            addCriterion("SKUProLimitNum is not null");
            return (Criteria) this;
        }

        public Criteria andSKUProLimitNumEqualTo(Integer value) {
            addCriterion("SKUProLimitNum =", value, "SKUProLimitNum");
            return (Criteria) this;
        }

        public Criteria andSKUProLimitNumNotEqualTo(Integer value) {
            addCriterion("SKUProLimitNum <>", value, "SKUProLimitNum");
            return (Criteria) this;
        }

        public Criteria andSKUProLimitNumGreaterThan(Integer value) {
            addCriterion("SKUProLimitNum >", value, "SKUProLimitNum");
            return (Criteria) this;
        }

        public Criteria andSKUProLimitNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("SKUProLimitNum >=", value, "SKUProLimitNum");
            return (Criteria) this;
        }

        public Criteria andSKUProLimitNumLessThan(Integer value) {
            addCriterion("SKUProLimitNum <", value, "SKUProLimitNum");
            return (Criteria) this;
        }

        public Criteria andSKUProLimitNumLessThanOrEqualTo(Integer value) {
            addCriterion("SKUProLimitNum <=", value, "SKUProLimitNum");
            return (Criteria) this;
        }

        public Criteria andSKUProLimitNumIn(List<Integer> values) {
            addCriterion("SKUProLimitNum in", values, "SKUProLimitNum");
            return (Criteria) this;
        }

        public Criteria andSKUProLimitNumNotIn(List<Integer> values) {
            addCriterion("SKUProLimitNum not in", values, "SKUProLimitNum");
            return (Criteria) this;
        }

        public Criteria andSKUProLimitNumBetween(Integer value1, Integer value2) {
            addCriterion("SKUProLimitNum between", value1, value2, "SKUProLimitNum");
            return (Criteria) this;
        }

        public Criteria andSKUProLimitNumNotBetween(Integer value1, Integer value2) {
            addCriterion("SKUProLimitNum not between", value1, value2, "SKUProLimitNum");
            return (Criteria) this;
        }

        public Criteria andTagLabelId1IsNull() {
            addCriterion("tagLabelId1 is null");
            return (Criteria) this;
        }

        public Criteria andTagLabelId1IsNotNull() {
            addCriterion("tagLabelId1 is not null");
            return (Criteria) this;
        }

        public Criteria andTagLabelId1EqualTo(String value) {
            addCriterion("tagLabelId1 =", value, "tagLabelId1");
            return (Criteria) this;
        }

        public Criteria andTagLabelId1NotEqualTo(String value) {
            addCriterion("tagLabelId1 <>", value, "tagLabelId1");
            return (Criteria) this;
        }

        public Criteria andTagLabelId1GreaterThan(String value) {
            addCriterion("tagLabelId1 >", value, "tagLabelId1");
            return (Criteria) this;
        }

        public Criteria andTagLabelId1GreaterThanOrEqualTo(String value) {
            addCriterion("tagLabelId1 >=", value, "tagLabelId1");
            return (Criteria) this;
        }

        public Criteria andTagLabelId1LessThan(String value) {
            addCriterion("tagLabelId1 <", value, "tagLabelId1");
            return (Criteria) this;
        }

        public Criteria andTagLabelId1LessThanOrEqualTo(String value) {
            addCriterion("tagLabelId1 <=", value, "tagLabelId1");
            return (Criteria) this;
        }

        public Criteria andTagLabelId1Like(String value) {
            addCriterion("tagLabelId1 like", value, "tagLabelId1");
            return (Criteria) this;
        }

        public Criteria andTagLabelId1NotLike(String value) {
            addCriterion("tagLabelId1 not like", value, "tagLabelId1");
            return (Criteria) this;
        }

        public Criteria andTagLabelId1In(List<String> values) {
            addCriterion("tagLabelId1 in", values, "tagLabelId1");
            return (Criteria) this;
        }

        public Criteria andTagLabelId1NotIn(List<String> values) {
            addCriterion("tagLabelId1 not in", values, "tagLabelId1");
            return (Criteria) this;
        }

        public Criteria andTagLabelId1Between(String value1, String value2) {
            addCriterion("tagLabelId1 between", value1, value2, "tagLabelId1");
            return (Criteria) this;
        }

        public Criteria andTagLabelId1NotBetween(String value1, String value2) {
            addCriterion("tagLabelId1 not between", value1, value2, "tagLabelId1");
            return (Criteria) this;
        }

        public Criteria andTagLabelId2IsNull() {
            addCriterion("tagLabelId2 is null");
            return (Criteria) this;
        }

        public Criteria andTagLabelId2IsNotNull() {
            addCriterion("tagLabelId2 is not null");
            return (Criteria) this;
        }

        public Criteria andTagLabelId2EqualTo(String value) {
            addCriterion("tagLabelId2 =", value, "tagLabelId2");
            return (Criteria) this;
        }

        public Criteria andTagLabelId2NotEqualTo(String value) {
            addCriterion("tagLabelId2 <>", value, "tagLabelId2");
            return (Criteria) this;
        }

        public Criteria andTagLabelId2GreaterThan(String value) {
            addCriterion("tagLabelId2 >", value, "tagLabelId2");
            return (Criteria) this;
        }

        public Criteria andTagLabelId2GreaterThanOrEqualTo(String value) {
            addCriterion("tagLabelId2 >=", value, "tagLabelId2");
            return (Criteria) this;
        }

        public Criteria andTagLabelId2LessThan(String value) {
            addCriterion("tagLabelId2 <", value, "tagLabelId2");
            return (Criteria) this;
        }

        public Criteria andTagLabelId2LessThanOrEqualTo(String value) {
            addCriterion("tagLabelId2 <=", value, "tagLabelId2");
            return (Criteria) this;
        }

        public Criteria andTagLabelId2Like(String value) {
            addCriterion("tagLabelId2 like", value, "tagLabelId2");
            return (Criteria) this;
        }

        public Criteria andTagLabelId2NotLike(String value) {
            addCriterion("tagLabelId2 not like", value, "tagLabelId2");
            return (Criteria) this;
        }

        public Criteria andTagLabelId2In(List<String> values) {
            addCriterion("tagLabelId2 in", values, "tagLabelId2");
            return (Criteria) this;
        }

        public Criteria andTagLabelId2NotIn(List<String> values) {
            addCriterion("tagLabelId2 not in", values, "tagLabelId2");
            return (Criteria) this;
        }

        public Criteria andTagLabelId2Between(String value1, String value2) {
            addCriterion("tagLabelId2 between", value1, value2, "tagLabelId2");
            return (Criteria) this;
        }

        public Criteria andTagLabelId2NotBetween(String value1, String value2) {
            addCriterion("tagLabelId2 not between", value1, value2, "tagLabelId2");
            return (Criteria) this;
        }

        public Criteria andTagLabelId3IsNull() {
            addCriterion("tagLabelId3 is null");
            return (Criteria) this;
        }

        public Criteria andTagLabelId3IsNotNull() {
            addCriterion("tagLabelId3 is not null");
            return (Criteria) this;
        }

        public Criteria andTagLabelId3EqualTo(String value) {
            addCriterion("tagLabelId3 =", value, "tagLabelId3");
            return (Criteria) this;
        }

        public Criteria andTagLabelId3NotEqualTo(String value) {
            addCriterion("tagLabelId3 <>", value, "tagLabelId3");
            return (Criteria) this;
        }

        public Criteria andTagLabelId3GreaterThan(String value) {
            addCriterion("tagLabelId3 >", value, "tagLabelId3");
            return (Criteria) this;
        }

        public Criteria andTagLabelId3GreaterThanOrEqualTo(String value) {
            addCriterion("tagLabelId3 >=", value, "tagLabelId3");
            return (Criteria) this;
        }

        public Criteria andTagLabelId3LessThan(String value) {
            addCriterion("tagLabelId3 <", value, "tagLabelId3");
            return (Criteria) this;
        }

        public Criteria andTagLabelId3LessThanOrEqualTo(String value) {
            addCriterion("tagLabelId3 <=", value, "tagLabelId3");
            return (Criteria) this;
        }

        public Criteria andTagLabelId3Like(String value) {
            addCriterion("tagLabelId3 like", value, "tagLabelId3");
            return (Criteria) this;
        }

        public Criteria andTagLabelId3NotLike(String value) {
            addCriterion("tagLabelId3 not like", value, "tagLabelId3");
            return (Criteria) this;
        }

        public Criteria andTagLabelId3In(List<String> values) {
            addCriterion("tagLabelId3 in", values, "tagLabelId3");
            return (Criteria) this;
        }

        public Criteria andTagLabelId3NotIn(List<String> values) {
            addCriterion("tagLabelId3 not in", values, "tagLabelId3");
            return (Criteria) this;
        }

        public Criteria andTagLabelId3Between(String value1, String value2) {
            addCriterion("tagLabelId3 between", value1, value2, "tagLabelId3");
            return (Criteria) this;
        }

        public Criteria andTagLabelId3NotBetween(String value1, String value2) {
            addCriterion("tagLabelId3 not between", value1, value2, "tagLabelId3");
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

        public Criteria andWarehouseIdIsNull() {
            addCriterion("warehouseId is null");
            return (Criteria) this;
        }

        public Criteria andWarehouseIdIsNotNull() {
            addCriterion("warehouseId is not null");
            return (Criteria) this;
        }

        public Criteria andWarehouseIdEqualTo(String value) {
            addCriterion("warehouseId =", value, "warehouseId");
            return (Criteria) this;
        }

        public Criteria andWarehouseIdNotEqualTo(String value) {
            addCriterion("warehouseId <>", value, "warehouseId");
            return (Criteria) this;
        }

        public Criteria andWarehouseIdGreaterThan(String value) {
            addCriterion("warehouseId >", value, "warehouseId");
            return (Criteria) this;
        }

        public Criteria andWarehouseIdGreaterThanOrEqualTo(String value) {
            addCriterion("warehouseId >=", value, "warehouseId");
            return (Criteria) this;
        }

        public Criteria andWarehouseIdLessThan(String value) {
            addCriterion("warehouseId <", value, "warehouseId");
            return (Criteria) this;
        }

        public Criteria andWarehouseIdLessThanOrEqualTo(String value) {
            addCriterion("warehouseId <=", value, "warehouseId");
            return (Criteria) this;
        }

        public Criteria andWarehouseIdLike(String value) {
            addCriterion("warehouseId like", value, "warehouseId");
            return (Criteria) this;
        }

        public Criteria andWarehouseIdNotLike(String value) {
            addCriterion("warehouseId not like", value, "warehouseId");
            return (Criteria) this;
        }

        public Criteria andWarehouseIdIn(List<String> values) {
            addCriterion("warehouseId in", values, "warehouseId");
            return (Criteria) this;
        }

        public Criteria andWarehouseIdNotIn(List<String> values) {
            addCriterion("warehouseId not in", values, "warehouseId");
            return (Criteria) this;
        }

        public Criteria andWarehouseIdBetween(String value1, String value2) {
            addCriterion("warehouseId between", value1, value2, "warehouseId");
            return (Criteria) this;
        }

        public Criteria andWarehouseIdNotBetween(String value1, String value2) {
            addCriterion("warehouseId not between", value1, value2, "warehouseId");
            return (Criteria) this;
        }

        public Criteria andLogicStockIdIsNull() {
            addCriterion("logicStockId is null");
            return (Criteria) this;
        }

        public Criteria andLogicStockIdIsNotNull() {
            addCriterion("logicStockId is not null");
            return (Criteria) this;
        }

        public Criteria andLogicStockIdEqualTo(String value) {
            addCriterion("logicStockId =", value, "logicStockId");
            return (Criteria) this;
        }

        public Criteria andLogicStockIdNotEqualTo(String value) {
            addCriterion("logicStockId <>", value, "logicStockId");
            return (Criteria) this;
        }

        public Criteria andLogicStockIdGreaterThan(String value) {
            addCriterion("logicStockId >", value, "logicStockId");
            return (Criteria) this;
        }

        public Criteria andLogicStockIdGreaterThanOrEqualTo(String value) {
            addCriterion("logicStockId >=", value, "logicStockId");
            return (Criteria) this;
        }

        public Criteria andLogicStockIdLessThan(String value) {
            addCriterion("logicStockId <", value, "logicStockId");
            return (Criteria) this;
        }

        public Criteria andLogicStockIdLessThanOrEqualTo(String value) {
            addCriterion("logicStockId <=", value, "logicStockId");
            return (Criteria) this;
        }

        public Criteria andLogicStockIdLike(String value) {
            addCriterion("logicStockId like", value, "logicStockId");
            return (Criteria) this;
        }

        public Criteria andLogicStockIdNotLike(String value) {
            addCriterion("logicStockId not like", value, "logicStockId");
            return (Criteria) this;
        }

        public Criteria andLogicStockIdIn(List<String> values) {
            addCriterion("logicStockId in", values, "logicStockId");
            return (Criteria) this;
        }

        public Criteria andLogicStockIdNotIn(List<String> values) {
            addCriterion("logicStockId not in", values, "logicStockId");
            return (Criteria) this;
        }

        public Criteria andLogicStockIdBetween(String value1, String value2) {
            addCriterion("logicStockId between", value1, value2, "logicStockId");
            return (Criteria) this;
        }

        public Criteria andLogicStockIdNotBetween(String value1, String value2) {
            addCriterion("logicStockId not between", value1, value2, "logicStockId");
            return (Criteria) this;
        }

        public Criteria andLogicStockTypeMgIsNull() {
            addCriterion("logicStockTypeMg is null");
            return (Criteria) this;
        }

        public Criteria andLogicStockTypeMgIsNotNull() {
            addCriterion("logicStockTypeMg is not null");
            return (Criteria) this;
        }

        public Criteria andLogicStockTypeMgEqualTo(Byte value) {
            addCriterion("logicStockTypeMg =", value, "logicStockTypeMg");
            return (Criteria) this;
        }

        public Criteria andLogicStockTypeMgNotEqualTo(Byte value) {
            addCriterion("logicStockTypeMg <>", value, "logicStockTypeMg");
            return (Criteria) this;
        }

        public Criteria andLogicStockTypeMgGreaterThan(Byte value) {
            addCriterion("logicStockTypeMg >", value, "logicStockTypeMg");
            return (Criteria) this;
        }

        public Criteria andLogicStockTypeMgGreaterThanOrEqualTo(Byte value) {
            addCriterion("logicStockTypeMg >=", value, "logicStockTypeMg");
            return (Criteria) this;
        }

        public Criteria andLogicStockTypeMgLessThan(Byte value) {
            addCriterion("logicStockTypeMg <", value, "logicStockTypeMg");
            return (Criteria) this;
        }

        public Criteria andLogicStockTypeMgLessThanOrEqualTo(Byte value) {
            addCriterion("logicStockTypeMg <=", value, "logicStockTypeMg");
            return (Criteria) this;
        }

        public Criteria andLogicStockTypeMgIn(List<Byte> values) {
            addCriterion("logicStockTypeMg in", values, "logicStockTypeMg");
            return (Criteria) this;
        }

        public Criteria andLogicStockTypeMgNotIn(List<Byte> values) {
            addCriterion("logicStockTypeMg not in", values, "logicStockTypeMg");
            return (Criteria) this;
        }

        public Criteria andLogicStockTypeMgBetween(Byte value1, Byte value2) {
            addCriterion("logicStockTypeMg between", value1, value2, "logicStockTypeMg");
            return (Criteria) this;
        }

        public Criteria andLogicStockTypeMgNotBetween(Byte value1, Byte value2) {
            addCriterion("logicStockTypeMg not between", value1, value2, "logicStockTypeMg");
            return (Criteria) this;
        }

        public Criteria andSKUProTotalLimitNumIsNull() {
            addCriterion("SKUProTotalLimitNum is null");
            return (Criteria) this;
        }

        public Criteria andSKUProTotalLimitNumIsNotNull() {
            addCriterion("SKUProTotalLimitNum is not null");
            return (Criteria) this;
        }

        public Criteria andSKUProTotalLimitNumEqualTo(Integer value) {
            addCriterion("SKUProTotalLimitNum =", value, "SKUProTotalLimitNum");
            return (Criteria) this;
        }

        public Criteria andSKUProTotalLimitNumNotEqualTo(Integer value) {
            addCriterion("SKUProTotalLimitNum <>", value, "SKUProTotalLimitNum");
            return (Criteria) this;
        }

        public Criteria andSKUProTotalLimitNumGreaterThan(Integer value) {
            addCriterion("SKUProTotalLimitNum >", value, "SKUProTotalLimitNum");
            return (Criteria) this;
        }

        public Criteria andSKUProTotalLimitNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("SKUProTotalLimitNum >=", value, "SKUProTotalLimitNum");
            return (Criteria) this;
        }

        public Criteria andSKUProTotalLimitNumLessThan(Integer value) {
            addCriterion("SKUProTotalLimitNum <", value, "SKUProTotalLimitNum");
            return (Criteria) this;
        }

        public Criteria andSKUProTotalLimitNumLessThanOrEqualTo(Integer value) {
            addCriterion("SKUProTotalLimitNum <=", value, "SKUProTotalLimitNum");
            return (Criteria) this;
        }

        public Criteria andSKUProTotalLimitNumIn(List<Integer> values) {
            addCriterion("SKUProTotalLimitNum in", values, "SKUProTotalLimitNum");
            return (Criteria) this;
        }

        public Criteria andSKUProTotalLimitNumNotIn(List<Integer> values) {
            addCriterion("SKUProTotalLimitNum not in", values, "SKUProTotalLimitNum");
            return (Criteria) this;
        }

        public Criteria andSKUProTotalLimitNumBetween(Integer value1, Integer value2) {
            addCriterion("SKUProTotalLimitNum between", value1, value2, "SKUProTotalLimitNum");
            return (Criteria) this;
        }

        public Criteria andSKUProTotalLimitNumNotBetween(Integer value1, Integer value2) {
            addCriterion("SKUProTotalLimitNum not between", value1, value2, "SKUProTotalLimitNum");
            return (Criteria) this;
        }

        public Criteria andSKUPromotionIdIsNull() {
            addCriterion("SKUPromotionId is null");
            return (Criteria) this;
        }

        public Criteria andSKUPromotionIdIsNotNull() {
            addCriterion("SKUPromotionId is not null");
            return (Criteria) this;
        }

        public Criteria andSKUPromotionIdEqualTo(String value) {
            addCriterion("SKUPromotionId =", value, "SKUPromotionId");
            return (Criteria) this;
        }

        public Criteria andSKUPromotionIdNotEqualTo(String value) {
            addCriterion("SKUPromotionId <>", value, "SKUPromotionId");
            return (Criteria) this;
        }

        public Criteria andSKUPromotionIdGreaterThan(String value) {
            addCriterion("SKUPromotionId >", value, "SKUPromotionId");
            return (Criteria) this;
        }

        public Criteria andSKUPromotionIdGreaterThanOrEqualTo(String value) {
            addCriterion("SKUPromotionId >=", value, "SKUPromotionId");
            return (Criteria) this;
        }

        public Criteria andSKUPromotionIdLessThan(String value) {
            addCriterion("SKUPromotionId <", value, "SKUPromotionId");
            return (Criteria) this;
        }

        public Criteria andSKUPromotionIdLessThanOrEqualTo(String value) {
            addCriterion("SKUPromotionId <=", value, "SKUPromotionId");
            return (Criteria) this;
        }

        public Criteria andSKUPromotionIdLike(String value) {
            addCriterion("SKUPromotionId like", value, "SKUPromotionId");
            return (Criteria) this;
        }

        public Criteria andSKUPromotionIdNotLike(String value) {
            addCriterion("SKUPromotionId not like", value, "SKUPromotionId");
            return (Criteria) this;
        }

        public Criteria andSKUPromotionIdIn(List<String> values) {
            addCriterion("SKUPromotionId in", values, "SKUPromotionId");
            return (Criteria) this;
        }

        public Criteria andSKUPromotionIdNotIn(List<String> values) {
            addCriterion("SKUPromotionId not in", values, "SKUPromotionId");
            return (Criteria) this;
        }

        public Criteria andSKUPromotionIdBetween(String value1, String value2) {
            addCriterion("SKUPromotionId between", value1, value2, "SKUPromotionId");
            return (Criteria) this;
        }

        public Criteria andSKUPromotionIdNotBetween(String value1, String value2) {
            addCriterion("SKUPromotionId not between", value1, value2, "SKUPromotionId");
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