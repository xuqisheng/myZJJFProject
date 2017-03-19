package com.corner.task.beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PlantItemProductExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PlantItemProductExample() {
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

        public Criteria andPkgNameIsNull() {
            addCriterion("pkgName is null");
            return (Criteria) this;
        }

        public Criteria andPkgNameIsNotNull() {
            addCriterion("pkgName is not null");
            return (Criteria) this;
        }

        public Criteria andPkgNameEqualTo(String value) {
            addCriterion("pkgName =", value, "pkgName");
            return (Criteria) this;
        }

        public Criteria andPkgNameNotEqualTo(String value) {
            addCriterion("pkgName <>", value, "pkgName");
            return (Criteria) this;
        }

        public Criteria andPkgNameGreaterThan(String value) {
            addCriterion("pkgName >", value, "pkgName");
            return (Criteria) this;
        }

        public Criteria andPkgNameGreaterThanOrEqualTo(String value) {
            addCriterion("pkgName >=", value, "pkgName");
            return (Criteria) this;
        }

        public Criteria andPkgNameLessThan(String value) {
            addCriterion("pkgName <", value, "pkgName");
            return (Criteria) this;
        }

        public Criteria andPkgNameLessThanOrEqualTo(String value) {
            addCriterion("pkgName <=", value, "pkgName");
            return (Criteria) this;
        }

        public Criteria andPkgNameLike(String value) {
            addCriterion("pkgName like", value, "pkgName");
            return (Criteria) this;
        }

        public Criteria andPkgNameNotLike(String value) {
            addCriterion("pkgName not like", value, "pkgName");
            return (Criteria) this;
        }

        public Criteria andPkgNameIn(List<String> values) {
            addCriterion("pkgName in", values, "pkgName");
            return (Criteria) this;
        }

        public Criteria andPkgNameNotIn(List<String> values) {
            addCriterion("pkgName not in", values, "pkgName");
            return (Criteria) this;
        }

        public Criteria andPkgNameBetween(String value1, String value2) {
            addCriterion("pkgName between", value1, value2, "pkgName");
            return (Criteria) this;
        }

        public Criteria andPkgNameNotBetween(String value1, String value2) {
            addCriterion("pkgName not between", value1, value2, "pkgName");
            return (Criteria) this;
        }

        public Criteria andAddtimeIsNull() {
            addCriterion("addtime is null");
            return (Criteria) this;
        }

        public Criteria andAddtimeIsNotNull() {
            addCriterion("addtime is not null");
            return (Criteria) this;
        }

        public Criteria andAddtimeEqualTo(Date value) {
            addCriterion("addtime =", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeNotEqualTo(Date value) {
            addCriterion("addtime <>", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeGreaterThan(Date value) {
            addCriterion("addtime >", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("addtime >=", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeLessThan(Date value) {
            addCriterion("addtime <", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeLessThanOrEqualTo(Date value) {
            addCriterion("addtime <=", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeIn(List<Date> values) {
            addCriterion("addtime in", values, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeNotIn(List<Date> values) {
            addCriterion("addtime not in", values, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeBetween(Date value1, Date value2) {
            addCriterion("addtime between", value1, value2, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeNotBetween(Date value1, Date value2) {
            addCriterion("addtime not between", value1, value2, "addtime");
            return (Criteria) this;
        }

        public Criteria andPkgTypeIsNull() {
            addCriterion("pkgType is null");
            return (Criteria) this;
        }

        public Criteria andPkgTypeIsNotNull() {
            addCriterion("pkgType is not null");
            return (Criteria) this;
        }

        public Criteria andPkgTypeEqualTo(Byte value) {
            addCriterion("pkgType =", value, "pkgType");
            return (Criteria) this;
        }

        public Criteria andPkgTypeNotEqualTo(Byte value) {
            addCriterion("pkgType <>", value, "pkgType");
            return (Criteria) this;
        }

        public Criteria andPkgTypeGreaterThan(Byte value) {
            addCriterion("pkgType >", value, "pkgType");
            return (Criteria) this;
        }

        public Criteria andPkgTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("pkgType >=", value, "pkgType");
            return (Criteria) this;
        }

        public Criteria andPkgTypeLessThan(Byte value) {
            addCriterion("pkgType <", value, "pkgType");
            return (Criteria) this;
        }

        public Criteria andPkgTypeLessThanOrEqualTo(Byte value) {
            addCriterion("pkgType <=", value, "pkgType");
            return (Criteria) this;
        }

        public Criteria andPkgTypeIn(List<Byte> values) {
            addCriterion("pkgType in", values, "pkgType");
            return (Criteria) this;
        }

        public Criteria andPkgTypeNotIn(List<Byte> values) {
            addCriterion("pkgType not in", values, "pkgType");
            return (Criteria) this;
        }

        public Criteria andPkgTypeBetween(Byte value1, Byte value2) {
            addCriterion("pkgType between", value1, value2, "pkgType");
            return (Criteria) this;
        }

        public Criteria andPkgTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("pkgType not between", value1, value2, "pkgType");
            return (Criteria) this;
        }

        public Criteria andAsDefaultIsNull() {
            addCriterion("asDefault is null");
            return (Criteria) this;
        }

        public Criteria andAsDefaultIsNotNull() {
            addCriterion("asDefault is not null");
            return (Criteria) this;
        }

        public Criteria andAsDefaultEqualTo(Boolean value) {
            addCriterion("asDefault =", value, "asDefault");
            return (Criteria) this;
        }

        public Criteria andAsDefaultNotEqualTo(Boolean value) {
            addCriterion("asDefault <>", value, "asDefault");
            return (Criteria) this;
        }

        public Criteria andAsDefaultGreaterThan(Boolean value) {
            addCriterion("asDefault >", value, "asDefault");
            return (Criteria) this;
        }

        public Criteria andAsDefaultGreaterThanOrEqualTo(Boolean value) {
            addCriterion("asDefault >=", value, "asDefault");
            return (Criteria) this;
        }

        public Criteria andAsDefaultLessThan(Boolean value) {
            addCriterion("asDefault <", value, "asDefault");
            return (Criteria) this;
        }

        public Criteria andAsDefaultLessThanOrEqualTo(Boolean value) {
            addCriterion("asDefault <=", value, "asDefault");
            return (Criteria) this;
        }

        public Criteria andAsDefaultIn(List<Boolean> values) {
            addCriterion("asDefault in", values, "asDefault");
            return (Criteria) this;
        }

        public Criteria andAsDefaultNotIn(List<Boolean> values) {
            addCriterion("asDefault not in", values, "asDefault");
            return (Criteria) this;
        }

        public Criteria andAsDefaultBetween(Boolean value1, Boolean value2) {
            addCriterion("asDefault between", value1, value2, "asDefault");
            return (Criteria) this;
        }

        public Criteria andAsDefaultNotBetween(Boolean value1, Boolean value2) {
            addCriterion("asDefault not between", value1, value2, "asDefault");
            return (Criteria) this;
        }

        public Criteria andProductPriceIsNull() {
            addCriterion("productPrice is null");
            return (Criteria) this;
        }

        public Criteria andProductPriceIsNotNull() {
            addCriterion("productPrice is not null");
            return (Criteria) this;
        }

        public Criteria andProductPriceEqualTo(BigDecimal value) {
            addCriterion("productPrice =", value, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceNotEqualTo(BigDecimal value) {
            addCriterion("productPrice <>", value, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceGreaterThan(BigDecimal value) {
            addCriterion("productPrice >", value, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("productPrice >=", value, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceLessThan(BigDecimal value) {
            addCriterion("productPrice <", value, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("productPrice <=", value, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceIn(List<BigDecimal> values) {
            addCriterion("productPrice in", values, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceNotIn(List<BigDecimal> values) {
            addCriterion("productPrice not in", values, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("productPrice between", value1, value2, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("productPrice not between", value1, value2, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProMarketPriceIsNull() {
            addCriterion("proMarketPrice is null");
            return (Criteria) this;
        }

        public Criteria andProMarketPriceIsNotNull() {
            addCriterion("proMarketPrice is not null");
            return (Criteria) this;
        }

        public Criteria andProMarketPriceEqualTo(BigDecimal value) {
            addCriterion("proMarketPrice =", value, "proMarketPrice");
            return (Criteria) this;
        }

        public Criteria andProMarketPriceNotEqualTo(BigDecimal value) {
            addCriterion("proMarketPrice <>", value, "proMarketPrice");
            return (Criteria) this;
        }

        public Criteria andProMarketPriceGreaterThan(BigDecimal value) {
            addCriterion("proMarketPrice >", value, "proMarketPrice");
            return (Criteria) this;
        }

        public Criteria andProMarketPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("proMarketPrice >=", value, "proMarketPrice");
            return (Criteria) this;
        }

        public Criteria andProMarketPriceLessThan(BigDecimal value) {
            addCriterion("proMarketPrice <", value, "proMarketPrice");
            return (Criteria) this;
        }

        public Criteria andProMarketPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("proMarketPrice <=", value, "proMarketPrice");
            return (Criteria) this;
        }

        public Criteria andProMarketPriceIn(List<BigDecimal> values) {
            addCriterion("proMarketPrice in", values, "proMarketPrice");
            return (Criteria) this;
        }

        public Criteria andProMarketPriceNotIn(List<BigDecimal> values) {
            addCriterion("proMarketPrice not in", values, "proMarketPrice");
            return (Criteria) this;
        }

        public Criteria andProMarketPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("proMarketPrice between", value1, value2, "proMarketPrice");
            return (Criteria) this;
        }

        public Criteria andProMarketPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("proMarketPrice not between", value1, value2, "proMarketPrice");
            return (Criteria) this;
        }

        public Criteria andUpTimeIsNull() {
            addCriterion("upTime is null");
            return (Criteria) this;
        }

        public Criteria andUpTimeIsNotNull() {
            addCriterion("upTime is not null");
            return (Criteria) this;
        }

        public Criteria andUpTimeEqualTo(Date value) {
            addCriterion("upTime =", value, "upTime");
            return (Criteria) this;
        }

        public Criteria andUpTimeNotEqualTo(Date value) {
            addCriterion("upTime <>", value, "upTime");
            return (Criteria) this;
        }

        public Criteria andUpTimeGreaterThan(Date value) {
            addCriterion("upTime >", value, "upTime");
            return (Criteria) this;
        }

        public Criteria andUpTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("upTime >=", value, "upTime");
            return (Criteria) this;
        }

        public Criteria andUpTimeLessThan(Date value) {
            addCriterion("upTime <", value, "upTime");
            return (Criteria) this;
        }

        public Criteria andUpTimeLessThanOrEqualTo(Date value) {
            addCriterion("upTime <=", value, "upTime");
            return (Criteria) this;
        }

        public Criteria andUpTimeIn(List<Date> values) {
            addCriterion("upTime in", values, "upTime");
            return (Criteria) this;
        }

        public Criteria andUpTimeNotIn(List<Date> values) {
            addCriterion("upTime not in", values, "upTime");
            return (Criteria) this;
        }

        public Criteria andUpTimeBetween(Date value1, Date value2) {
            addCriterion("upTime between", value1, value2, "upTime");
            return (Criteria) this;
        }

        public Criteria andUpTimeNotBetween(Date value1, Date value2) {
            addCriterion("upTime not between", value1, value2, "upTime");
            return (Criteria) this;
        }

        public Criteria andDownTimeIsNull() {
            addCriterion("downTime is null");
            return (Criteria) this;
        }

        public Criteria andDownTimeIsNotNull() {
            addCriterion("downTime is not null");
            return (Criteria) this;
        }

        public Criteria andDownTimeEqualTo(Date value) {
            addCriterion("downTime =", value, "downTime");
            return (Criteria) this;
        }

        public Criteria andDownTimeNotEqualTo(Date value) {
            addCriterion("downTime <>", value, "downTime");
            return (Criteria) this;
        }

        public Criteria andDownTimeGreaterThan(Date value) {
            addCriterion("downTime >", value, "downTime");
            return (Criteria) this;
        }

        public Criteria andDownTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("downTime >=", value, "downTime");
            return (Criteria) this;
        }

        public Criteria andDownTimeLessThan(Date value) {
            addCriterion("downTime <", value, "downTime");
            return (Criteria) this;
        }

        public Criteria andDownTimeLessThanOrEqualTo(Date value) {
            addCriterion("downTime <=", value, "downTime");
            return (Criteria) this;
        }

        public Criteria andDownTimeIn(List<Date> values) {
            addCriterion("downTime in", values, "downTime");
            return (Criteria) this;
        }

        public Criteria andDownTimeNotIn(List<Date> values) {
            addCriterion("downTime not in", values, "downTime");
            return (Criteria) this;
        }

        public Criteria andDownTimeBetween(Date value1, Date value2) {
            addCriterion("downTime between", value1, value2, "downTime");
            return (Criteria) this;
        }

        public Criteria andDownTimeNotBetween(Date value1, Date value2) {
            addCriterion("downTime not between", value1, value2, "downTime");
            return (Criteria) this;
        }

        public Criteria andBuyStartTimeIsNull() {
            addCriterion("buyStartTime is null");
            return (Criteria) this;
        }

        public Criteria andBuyStartTimeIsNotNull() {
            addCriterion("buyStartTime is not null");
            return (Criteria) this;
        }

        public Criteria andBuyStartTimeEqualTo(Date value) {
            addCriterion("buyStartTime =", value, "buyStartTime");
            return (Criteria) this;
        }

        public Criteria andBuyStartTimeNotEqualTo(Date value) {
            addCriterion("buyStartTime <>", value, "buyStartTime");
            return (Criteria) this;
        }

        public Criteria andBuyStartTimeGreaterThan(Date value) {
            addCriterion("buyStartTime >", value, "buyStartTime");
            return (Criteria) this;
        }

        public Criteria andBuyStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("buyStartTime >=", value, "buyStartTime");
            return (Criteria) this;
        }

        public Criteria andBuyStartTimeLessThan(Date value) {
            addCriterion("buyStartTime <", value, "buyStartTime");
            return (Criteria) this;
        }

        public Criteria andBuyStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("buyStartTime <=", value, "buyStartTime");
            return (Criteria) this;
        }

        public Criteria andBuyStartTimeIn(List<Date> values) {
            addCriterion("buyStartTime in", values, "buyStartTime");
            return (Criteria) this;
        }

        public Criteria andBuyStartTimeNotIn(List<Date> values) {
            addCriterion("buyStartTime not in", values, "buyStartTime");
            return (Criteria) this;
        }

        public Criteria andBuyStartTimeBetween(Date value1, Date value2) {
            addCriterion("buyStartTime between", value1, value2, "buyStartTime");
            return (Criteria) this;
        }

        public Criteria andBuyStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("buyStartTime not between", value1, value2, "buyStartTime");
            return (Criteria) this;
        }

        public Criteria andBuyEndTimeIsNull() {
            addCriterion("buyEndTime is null");
            return (Criteria) this;
        }

        public Criteria andBuyEndTimeIsNotNull() {
            addCriterion("buyEndTime is not null");
            return (Criteria) this;
        }

        public Criteria andBuyEndTimeEqualTo(Date value) {
            addCriterion("buyEndTime =", value, "buyEndTime");
            return (Criteria) this;
        }

        public Criteria andBuyEndTimeNotEqualTo(Date value) {
            addCriterion("buyEndTime <>", value, "buyEndTime");
            return (Criteria) this;
        }

        public Criteria andBuyEndTimeGreaterThan(Date value) {
            addCriterion("buyEndTime >", value, "buyEndTime");
            return (Criteria) this;
        }

        public Criteria andBuyEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("buyEndTime >=", value, "buyEndTime");
            return (Criteria) this;
        }

        public Criteria andBuyEndTimeLessThan(Date value) {
            addCriterion("buyEndTime <", value, "buyEndTime");
            return (Criteria) this;
        }

        public Criteria andBuyEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("buyEndTime <=", value, "buyEndTime");
            return (Criteria) this;
        }

        public Criteria andBuyEndTimeIn(List<Date> values) {
            addCriterion("buyEndTime in", values, "buyEndTime");
            return (Criteria) this;
        }

        public Criteria andBuyEndTimeNotIn(List<Date> values) {
            addCriterion("buyEndTime not in", values, "buyEndTime");
            return (Criteria) this;
        }

        public Criteria andBuyEndTimeBetween(Date value1, Date value2) {
            addCriterion("buyEndTime between", value1, value2, "buyEndTime");
            return (Criteria) this;
        }

        public Criteria andBuyEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("buyEndTime not between", value1, value2, "buyEndTime");
            return (Criteria) this;
        }

        public Criteria andBuyLimitNumIsNull() {
            addCriterion("buyLimitNum is null");
            return (Criteria) this;
        }

        public Criteria andBuyLimitNumIsNotNull() {
            addCriterion("buyLimitNum is not null");
            return (Criteria) this;
        }

        public Criteria andBuyLimitNumEqualTo(Integer value) {
            addCriterion("buyLimitNum =", value, "buyLimitNum");
            return (Criteria) this;
        }

        public Criteria andBuyLimitNumNotEqualTo(Integer value) {
            addCriterion("buyLimitNum <>", value, "buyLimitNum");
            return (Criteria) this;
        }

        public Criteria andBuyLimitNumGreaterThan(Integer value) {
            addCriterion("buyLimitNum >", value, "buyLimitNum");
            return (Criteria) this;
        }

        public Criteria andBuyLimitNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("buyLimitNum >=", value, "buyLimitNum");
            return (Criteria) this;
        }

        public Criteria andBuyLimitNumLessThan(Integer value) {
            addCriterion("buyLimitNum <", value, "buyLimitNum");
            return (Criteria) this;
        }

        public Criteria andBuyLimitNumLessThanOrEqualTo(Integer value) {
            addCriterion("buyLimitNum <=", value, "buyLimitNum");
            return (Criteria) this;
        }

        public Criteria andBuyLimitNumIn(List<Integer> values) {
            addCriterion("buyLimitNum in", values, "buyLimitNum");
            return (Criteria) this;
        }

        public Criteria andBuyLimitNumNotIn(List<Integer> values) {
            addCriterion("buyLimitNum not in", values, "buyLimitNum");
            return (Criteria) this;
        }

        public Criteria andBuyLimitNumBetween(Integer value1, Integer value2) {
            addCriterion("buyLimitNum between", value1, value2, "buyLimitNum");
            return (Criteria) this;
        }

        public Criteria andBuyLimitNumNotBetween(Integer value1, Integer value2) {
            addCriterion("buyLimitNum not between", value1, value2, "buyLimitNum");
            return (Criteria) this;
        }

        public Criteria andTotalLimitNumIsNull() {
            addCriterion("totalLimitNum is null");
            return (Criteria) this;
        }

        public Criteria andTotalLimitNumIsNotNull() {
            addCriterion("totalLimitNum is not null");
            return (Criteria) this;
        }

        public Criteria andTotalLimitNumEqualTo(Integer value) {
            addCriterion("totalLimitNum =", value, "totalLimitNum");
            return (Criteria) this;
        }

        public Criteria andTotalLimitNumNotEqualTo(Integer value) {
            addCriterion("totalLimitNum <>", value, "totalLimitNum");
            return (Criteria) this;
        }

        public Criteria andTotalLimitNumGreaterThan(Integer value) {
            addCriterion("totalLimitNum >", value, "totalLimitNum");
            return (Criteria) this;
        }

        public Criteria andTotalLimitNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("totalLimitNum >=", value, "totalLimitNum");
            return (Criteria) this;
        }

        public Criteria andTotalLimitNumLessThan(Integer value) {
            addCriterion("totalLimitNum <", value, "totalLimitNum");
            return (Criteria) this;
        }

        public Criteria andTotalLimitNumLessThanOrEqualTo(Integer value) {
            addCriterion("totalLimitNum <=", value, "totalLimitNum");
            return (Criteria) this;
        }

        public Criteria andTotalLimitNumIn(List<Integer> values) {
            addCriterion("totalLimitNum in", values, "totalLimitNum");
            return (Criteria) this;
        }

        public Criteria andTotalLimitNumNotIn(List<Integer> values) {
            addCriterion("totalLimitNum not in", values, "totalLimitNum");
            return (Criteria) this;
        }

        public Criteria andTotalLimitNumBetween(Integer value1, Integer value2) {
            addCriterion("totalLimitNum between", value1, value2, "totalLimitNum");
            return (Criteria) this;
        }

        public Criteria andTotalLimitNumNotBetween(Integer value1, Integer value2) {
            addCriterion("totalLimitNum not between", value1, value2, "totalLimitNum");
            return (Criteria) this;
        }

        public Criteria andTotalBuyNumIsNull() {
            addCriterion("totalBuyNum is null");
            return (Criteria) this;
        }

        public Criteria andTotalBuyNumIsNotNull() {
            addCriterion("totalBuyNum is not null");
            return (Criteria) this;
        }

        public Criteria andTotalBuyNumEqualTo(Integer value) {
            addCriterion("totalBuyNum =", value, "totalBuyNum");
            return (Criteria) this;
        }

        public Criteria andTotalBuyNumNotEqualTo(Integer value) {
            addCriterion("totalBuyNum <>", value, "totalBuyNum");
            return (Criteria) this;
        }

        public Criteria andTotalBuyNumGreaterThan(Integer value) {
            addCriterion("totalBuyNum >", value, "totalBuyNum");
            return (Criteria) this;
        }

        public Criteria andTotalBuyNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("totalBuyNum >=", value, "totalBuyNum");
            return (Criteria) this;
        }

        public Criteria andTotalBuyNumLessThan(Integer value) {
            addCriterion("totalBuyNum <", value, "totalBuyNum");
            return (Criteria) this;
        }

        public Criteria andTotalBuyNumLessThanOrEqualTo(Integer value) {
            addCriterion("totalBuyNum <=", value, "totalBuyNum");
            return (Criteria) this;
        }

        public Criteria andTotalBuyNumIn(List<Integer> values) {
            addCriterion("totalBuyNum in", values, "totalBuyNum");
            return (Criteria) this;
        }

        public Criteria andTotalBuyNumNotIn(List<Integer> values) {
            addCriterion("totalBuyNum not in", values, "totalBuyNum");
            return (Criteria) this;
        }

        public Criteria andTotalBuyNumBetween(Integer value1, Integer value2) {
            addCriterion("totalBuyNum between", value1, value2, "totalBuyNum");
            return (Criteria) this;
        }

        public Criteria andTotalBuyNumNotBetween(Integer value1, Integer value2) {
            addCriterion("totalBuyNum not between", value1, value2, "totalBuyNum");
            return (Criteria) this;
        }

        public Criteria andLabelIdIsNull() {
            addCriterion("labelId is null");
            return (Criteria) this;
        }

        public Criteria andLabelIdIsNotNull() {
            addCriterion("labelId is not null");
            return (Criteria) this;
        }

        public Criteria andLabelIdEqualTo(String value) {
            addCriterion("labelId =", value, "labelId");
            return (Criteria) this;
        }

        public Criteria andLabelIdNotEqualTo(String value) {
            addCriterion("labelId <>", value, "labelId");
            return (Criteria) this;
        }

        public Criteria andLabelIdGreaterThan(String value) {
            addCriterion("labelId >", value, "labelId");
            return (Criteria) this;
        }

        public Criteria andLabelIdGreaterThanOrEqualTo(String value) {
            addCriterion("labelId >=", value, "labelId");
            return (Criteria) this;
        }

        public Criteria andLabelIdLessThan(String value) {
            addCriterion("labelId <", value, "labelId");
            return (Criteria) this;
        }

        public Criteria andLabelIdLessThanOrEqualTo(String value) {
            addCriterion("labelId <=", value, "labelId");
            return (Criteria) this;
        }

        public Criteria andLabelIdLike(String value) {
            addCriterion("labelId like", value, "labelId");
            return (Criteria) this;
        }

        public Criteria andLabelIdNotLike(String value) {
            addCriterion("labelId not like", value, "labelId");
            return (Criteria) this;
        }

        public Criteria andLabelIdIn(List<String> values) {
            addCriterion("labelId in", values, "labelId");
            return (Criteria) this;
        }

        public Criteria andLabelIdNotIn(List<String> values) {
            addCriterion("labelId not in", values, "labelId");
            return (Criteria) this;
        }

        public Criteria andLabelIdBetween(String value1, String value2) {
            addCriterion("labelId between", value1, value2, "labelId");
            return (Criteria) this;
        }

        public Criteria andLabelIdNotBetween(String value1, String value2) {
            addCriterion("labelId not between", value1, value2, "labelId");
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

        public Criteria andSKUActiveIdIsNull() {
            addCriterion("SKUActiveId is null");
            return (Criteria) this;
        }

        public Criteria andSKUActiveIdIsNotNull() {
            addCriterion("SKUActiveId is not null");
            return (Criteria) this;
        }

        public Criteria andSKUActiveIdEqualTo(String value) {
            addCriterion("SKUActiveId =", value, "SKUActiveId");
            return (Criteria) this;
        }

        public Criteria andSKUActiveIdNotEqualTo(String value) {
            addCriterion("SKUActiveId <>", value, "SKUActiveId");
            return (Criteria) this;
        }

        public Criteria andSKUActiveIdGreaterThan(String value) {
            addCriterion("SKUActiveId >", value, "SKUActiveId");
            return (Criteria) this;
        }

        public Criteria andSKUActiveIdGreaterThanOrEqualTo(String value) {
            addCriterion("SKUActiveId >=", value, "SKUActiveId");
            return (Criteria) this;
        }

        public Criteria andSKUActiveIdLessThan(String value) {
            addCriterion("SKUActiveId <", value, "SKUActiveId");
            return (Criteria) this;
        }

        public Criteria andSKUActiveIdLessThanOrEqualTo(String value) {
            addCriterion("SKUActiveId <=", value, "SKUActiveId");
            return (Criteria) this;
        }

        public Criteria andSKUActiveIdLike(String value) {
            addCriterion("SKUActiveId like", value, "SKUActiveId");
            return (Criteria) this;
        }

        public Criteria andSKUActiveIdNotLike(String value) {
            addCriterion("SKUActiveId not like", value, "SKUActiveId");
            return (Criteria) this;
        }

        public Criteria andSKUActiveIdIn(List<String> values) {
            addCriterion("SKUActiveId in", values, "SKUActiveId");
            return (Criteria) this;
        }

        public Criteria andSKUActiveIdNotIn(List<String> values) {
            addCriterion("SKUActiveId not in", values, "SKUActiveId");
            return (Criteria) this;
        }

        public Criteria andSKUActiveIdBetween(String value1, String value2) {
            addCriterion("SKUActiveId between", value1, value2, "SKUActiveId");
            return (Criteria) this;
        }

        public Criteria andSKUActiveIdNotBetween(String value1, String value2) {
            addCriterion("SKUActiveId not between", value1, value2, "SKUActiveId");
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