package com.corner.core.beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class SpOrderInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SpOrderInfoExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
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

        public Criteria andTradeNoIsNull() {
            addCriterion("tradeNo is null");
            return (Criteria) this;
        }

        public Criteria andTradeNoIsNotNull() {
            addCriterion("tradeNo is not null");
            return (Criteria) this;
        }

        public Criteria andTradeNoEqualTo(String value) {
            addCriterion("tradeNo =", value, "tradeNo");
            return (Criteria) this;
        }

        public Criteria andTradeNoNotEqualTo(String value) {
            addCriterion("tradeNo <>", value, "tradeNo");
            return (Criteria) this;
        }

        public Criteria andTradeNoGreaterThan(String value) {
            addCriterion("tradeNo >", value, "tradeNo");
            return (Criteria) this;
        }

        public Criteria andTradeNoGreaterThanOrEqualTo(String value) {
            addCriterion("tradeNo >=", value, "tradeNo");
            return (Criteria) this;
        }

        public Criteria andTradeNoLessThan(String value) {
            addCriterion("tradeNo <", value, "tradeNo");
            return (Criteria) this;
        }

        public Criteria andTradeNoLessThanOrEqualTo(String value) {
            addCriterion("tradeNo <=", value, "tradeNo");
            return (Criteria) this;
        }

        public Criteria andTradeNoLike(String value) {
            addCriterion("tradeNo like", value, "tradeNo");
            return (Criteria) this;
        }

        public Criteria andTradeNoNotLike(String value) {
            addCriterion("tradeNo not like", value, "tradeNo");
            return (Criteria) this;
        }

        public Criteria andTradeNoIn(List<String> values) {
            addCriterion("tradeNo in", values, "tradeNo");
            return (Criteria) this;
        }

        public Criteria andTradeNoNotIn(List<String> values) {
            addCriterion("tradeNo not in", values, "tradeNo");
            return (Criteria) this;
        }

        public Criteria andTradeNoBetween(String value1, String value2) {
            addCriterion("tradeNo between", value1, value2, "tradeNo");
            return (Criteria) this;
        }

        public Criteria andTradeNoNotBetween(String value1, String value2) {
            addCriterion("tradeNo not between", value1, value2, "tradeNo");
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

        public Criteria andGoodsPriceIsNull() {
            addCriterion("goodsPrice is null");
            return (Criteria) this;
        }

        public Criteria andGoodsPriceIsNotNull() {
            addCriterion("goodsPrice is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsPriceEqualTo(BigDecimal value) {
            addCriterion("goodsPrice =", value, "goodsPrice");
            return (Criteria) this;
        }

        public Criteria andGoodsPriceNotEqualTo(BigDecimal value) {
            addCriterion("goodsPrice <>", value, "goodsPrice");
            return (Criteria) this;
        }

        public Criteria andGoodsPriceGreaterThan(BigDecimal value) {
            addCriterion("goodsPrice >", value, "goodsPrice");
            return (Criteria) this;
        }

        public Criteria andGoodsPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("goodsPrice >=", value, "goodsPrice");
            return (Criteria) this;
        }

        public Criteria andGoodsPriceLessThan(BigDecimal value) {
            addCriterion("goodsPrice <", value, "goodsPrice");
            return (Criteria) this;
        }

        public Criteria andGoodsPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("goodsPrice <=", value, "goodsPrice");
            return (Criteria) this;
        }

        public Criteria andGoodsPriceIn(List<BigDecimal> values) {
            addCriterion("goodsPrice in", values, "goodsPrice");
            return (Criteria) this;
        }

        public Criteria andGoodsPriceNotIn(List<BigDecimal> values) {
            addCriterion("goodsPrice not in", values, "goodsPrice");
            return (Criteria) this;
        }

        public Criteria andGoodsPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("goodsPrice between", value1, value2, "goodsPrice");
            return (Criteria) this;
        }

        public Criteria andGoodsPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("goodsPrice not between", value1, value2, "goodsPrice");
            return (Criteria) this;
        }

        public Criteria andOrderPriceIsNull() {
            addCriterion("orderPrice is null");
            return (Criteria) this;
        }

        public Criteria andOrderPriceIsNotNull() {
            addCriterion("orderPrice is not null");
            return (Criteria) this;
        }

        public Criteria andOrderPriceEqualTo(BigDecimal value) {
            addCriterion("orderPrice =", value, "orderPrice");
            return (Criteria) this;
        }

        public Criteria andOrderPriceNotEqualTo(BigDecimal value) {
            addCriterion("orderPrice <>", value, "orderPrice");
            return (Criteria) this;
        }

        public Criteria andOrderPriceGreaterThan(BigDecimal value) {
            addCriterion("orderPrice >", value, "orderPrice");
            return (Criteria) this;
        }

        public Criteria andOrderPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("orderPrice >=", value, "orderPrice");
            return (Criteria) this;
        }

        public Criteria andOrderPriceLessThan(BigDecimal value) {
            addCriterion("orderPrice <", value, "orderPrice");
            return (Criteria) this;
        }

        public Criteria andOrderPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("orderPrice <=", value, "orderPrice");
            return (Criteria) this;
        }

        public Criteria andOrderPriceIn(List<BigDecimal> values) {
            addCriterion("orderPrice in", values, "orderPrice");
            return (Criteria) this;
        }

        public Criteria andOrderPriceNotIn(List<BigDecimal> values) {
            addCriterion("orderPrice not in", values, "orderPrice");
            return (Criteria) this;
        }

        public Criteria andOrderPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("orderPrice between", value1, value2, "orderPrice");
            return (Criteria) this;
        }

        public Criteria andOrderPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("orderPrice not between", value1, value2, "orderPrice");
            return (Criteria) this;
        }

        public Criteria andItemPriceIsNull() {
            addCriterion("itemPrice is null");
            return (Criteria) this;
        }

        public Criteria andItemPriceIsNotNull() {
            addCriterion("itemPrice is not null");
            return (Criteria) this;
        }

        public Criteria andItemPriceEqualTo(BigDecimal value) {
            addCriterion("itemPrice =", value, "itemPrice");
            return (Criteria) this;
        }

        public Criteria andItemPriceNotEqualTo(BigDecimal value) {
            addCriterion("itemPrice <>", value, "itemPrice");
            return (Criteria) this;
        }

        public Criteria andItemPriceGreaterThan(BigDecimal value) {
            addCriterion("itemPrice >", value, "itemPrice");
            return (Criteria) this;
        }

        public Criteria andItemPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("itemPrice >=", value, "itemPrice");
            return (Criteria) this;
        }

        public Criteria andItemPriceLessThan(BigDecimal value) {
            addCriterion("itemPrice <", value, "itemPrice");
            return (Criteria) this;
        }

        public Criteria andItemPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("itemPrice <=", value, "itemPrice");
            return (Criteria) this;
        }

        public Criteria andItemPriceIn(List<BigDecimal> values) {
            addCriterion("itemPrice in", values, "itemPrice");
            return (Criteria) this;
        }

        public Criteria andItemPriceNotIn(List<BigDecimal> values) {
            addCriterion("itemPrice not in", values, "itemPrice");
            return (Criteria) this;
        }

        public Criteria andItemPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("itemPrice between", value1, value2, "itemPrice");
            return (Criteria) this;
        }

        public Criteria andItemPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("itemPrice not between", value1, value2, "itemPrice");
            return (Criteria) this;
        }

        public Criteria andRebateIsNull() {
            addCriterion("rebate is null");
            return (Criteria) this;
        }

        public Criteria andRebateIsNotNull() {
            addCriterion("rebate is not null");
            return (Criteria) this;
        }

        public Criteria andRebateEqualTo(BigDecimal value) {
            addCriterion("rebate =", value, "rebate");
            return (Criteria) this;
        }

        public Criteria andRebateNotEqualTo(BigDecimal value) {
            addCriterion("rebate <>", value, "rebate");
            return (Criteria) this;
        }

        public Criteria andRebateGreaterThan(BigDecimal value) {
            addCriterion("rebate >", value, "rebate");
            return (Criteria) this;
        }

        public Criteria andRebateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("rebate >=", value, "rebate");
            return (Criteria) this;
        }

        public Criteria andRebateLessThan(BigDecimal value) {
            addCriterion("rebate <", value, "rebate");
            return (Criteria) this;
        }

        public Criteria andRebateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("rebate <=", value, "rebate");
            return (Criteria) this;
        }

        public Criteria andRebateIn(List<BigDecimal> values) {
            addCriterion("rebate in", values, "rebate");
            return (Criteria) this;
        }

        public Criteria andRebateNotIn(List<BigDecimal> values) {
            addCriterion("rebate not in", values, "rebate");
            return (Criteria) this;
        }

        public Criteria andRebateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("rebate between", value1, value2, "rebate");
            return (Criteria) this;
        }

        public Criteria andRebateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("rebate not between", value1, value2, "rebate");
            return (Criteria) this;
        }

        public Criteria andRebatePlatIsNull() {
            addCriterion("rebatePlat is null");
            return (Criteria) this;
        }

        public Criteria andRebatePlatIsNotNull() {
            addCriterion("rebatePlat is not null");
            return (Criteria) this;
        }

        public Criteria andRebatePlatEqualTo(BigDecimal value) {
            addCriterion("rebatePlat =", value, "rebatePlat");
            return (Criteria) this;
        }

        public Criteria andRebatePlatNotEqualTo(BigDecimal value) {
            addCriterion("rebatePlat <>", value, "rebatePlat");
            return (Criteria) this;
        }

        public Criteria andRebatePlatGreaterThan(BigDecimal value) {
            addCriterion("rebatePlat >", value, "rebatePlat");
            return (Criteria) this;
        }

        public Criteria andRebatePlatGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("rebatePlat >=", value, "rebatePlat");
            return (Criteria) this;
        }

        public Criteria andRebatePlatLessThan(BigDecimal value) {
            addCriterion("rebatePlat <", value, "rebatePlat");
            return (Criteria) this;
        }

        public Criteria andRebatePlatLessThanOrEqualTo(BigDecimal value) {
            addCriterion("rebatePlat <=", value, "rebatePlat");
            return (Criteria) this;
        }

        public Criteria andRebatePlatIn(List<BigDecimal> values) {
            addCriterion("rebatePlat in", values, "rebatePlat");
            return (Criteria) this;
        }

        public Criteria andRebatePlatNotIn(List<BigDecimal> values) {
            addCriterion("rebatePlat not in", values, "rebatePlat");
            return (Criteria) this;
        }

        public Criteria andRebatePlatBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("rebatePlat between", value1, value2, "rebatePlat");
            return (Criteria) this;
        }

        public Criteria andRebatePlatNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("rebatePlat not between", value1, value2, "rebatePlat");
            return (Criteria) this;
        }

        public Criteria andRebateSp2SpIsNull() {
            addCriterion("rebateSp2Sp is null");
            return (Criteria) this;
        }

        public Criteria andRebateSp2SpIsNotNull() {
            addCriterion("rebateSp2Sp is not null");
            return (Criteria) this;
        }

        public Criteria andRebateSp2SpEqualTo(BigDecimal value) {
            addCriterion("rebateSp2Sp =", value, "rebateSp2Sp");
            return (Criteria) this;
        }

        public Criteria andRebateSp2SpNotEqualTo(BigDecimal value) {
            addCriterion("rebateSp2Sp <>", value, "rebateSp2Sp");
            return (Criteria) this;
        }

        public Criteria andRebateSp2SpGreaterThan(BigDecimal value) {
            addCriterion("rebateSp2Sp >", value, "rebateSp2Sp");
            return (Criteria) this;
        }

        public Criteria andRebateSp2SpGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("rebateSp2Sp >=", value, "rebateSp2Sp");
            return (Criteria) this;
        }

        public Criteria andRebateSp2SpLessThan(BigDecimal value) {
            addCriterion("rebateSp2Sp <", value, "rebateSp2Sp");
            return (Criteria) this;
        }

        public Criteria andRebateSp2SpLessThanOrEqualTo(BigDecimal value) {
            addCriterion("rebateSp2Sp <=", value, "rebateSp2Sp");
            return (Criteria) this;
        }

        public Criteria andRebateSp2SpIn(List<BigDecimal> values) {
            addCriterion("rebateSp2Sp in", values, "rebateSp2Sp");
            return (Criteria) this;
        }

        public Criteria andRebateSp2SpNotIn(List<BigDecimal> values) {
            addCriterion("rebateSp2Sp not in", values, "rebateSp2Sp");
            return (Criteria) this;
        }

        public Criteria andRebateSp2SpBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("rebateSp2Sp between", value1, value2, "rebateSp2Sp");
            return (Criteria) this;
        }

        public Criteria andRebateSp2SpNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("rebateSp2Sp not between", value1, value2, "rebateSp2Sp");
            return (Criteria) this;
        }

        public Criteria andRebateSp2PlatIsNull() {
            addCriterion("rebateSp2Plat is null");
            return (Criteria) this;
        }

        public Criteria andRebateSp2PlatIsNotNull() {
            addCriterion("rebateSp2Plat is not null");
            return (Criteria) this;
        }

        public Criteria andRebateSp2PlatEqualTo(BigDecimal value) {
            addCriterion("rebateSp2Plat =", value, "rebateSp2Plat");
            return (Criteria) this;
        }

        public Criteria andRebateSp2PlatNotEqualTo(BigDecimal value) {
            addCriterion("rebateSp2Plat <>", value, "rebateSp2Plat");
            return (Criteria) this;
        }

        public Criteria andRebateSp2PlatGreaterThan(BigDecimal value) {
            addCriterion("rebateSp2Plat >", value, "rebateSp2Plat");
            return (Criteria) this;
        }

        public Criteria andRebateSp2PlatGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("rebateSp2Plat >=", value, "rebateSp2Plat");
            return (Criteria) this;
        }

        public Criteria andRebateSp2PlatLessThan(BigDecimal value) {
            addCriterion("rebateSp2Plat <", value, "rebateSp2Plat");
            return (Criteria) this;
        }

        public Criteria andRebateSp2PlatLessThanOrEqualTo(BigDecimal value) {
            addCriterion("rebateSp2Plat <=", value, "rebateSp2Plat");
            return (Criteria) this;
        }

        public Criteria andRebateSp2PlatIn(List<BigDecimal> values) {
            addCriterion("rebateSp2Plat in", values, "rebateSp2Plat");
            return (Criteria) this;
        }

        public Criteria andRebateSp2PlatNotIn(List<BigDecimal> values) {
            addCriterion("rebateSp2Plat not in", values, "rebateSp2Plat");
            return (Criteria) this;
        }

        public Criteria andRebateSp2PlatBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("rebateSp2Plat between", value1, value2, "rebateSp2Plat");
            return (Criteria) this;
        }

        public Criteria andRebateSp2PlatNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("rebateSp2Plat not between", value1, value2, "rebateSp2Plat");
            return (Criteria) this;
        }

        public Criteria andCouponIsNull() {
            addCriterion("coupon is null");
            return (Criteria) this;
        }

        public Criteria andCouponIsNotNull() {
            addCriterion("coupon is not null");
            return (Criteria) this;
        }

        public Criteria andCouponEqualTo(BigDecimal value) {
            addCriterion("coupon =", value, "coupon");
            return (Criteria) this;
        }

        public Criteria andCouponNotEqualTo(BigDecimal value) {
            addCriterion("coupon <>", value, "coupon");
            return (Criteria) this;
        }

        public Criteria andCouponGreaterThan(BigDecimal value) {
            addCriterion("coupon >", value, "coupon");
            return (Criteria) this;
        }

        public Criteria andCouponGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("coupon >=", value, "coupon");
            return (Criteria) this;
        }

        public Criteria andCouponLessThan(BigDecimal value) {
            addCriterion("coupon <", value, "coupon");
            return (Criteria) this;
        }

        public Criteria andCouponLessThanOrEqualTo(BigDecimal value) {
            addCriterion("coupon <=", value, "coupon");
            return (Criteria) this;
        }

        public Criteria andCouponIn(List<BigDecimal> values) {
            addCriterion("coupon in", values, "coupon");
            return (Criteria) this;
        }

        public Criteria andCouponNotIn(List<BigDecimal> values) {
            addCriterion("coupon not in", values, "coupon");
            return (Criteria) this;
        }

        public Criteria andCouponBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("coupon between", value1, value2, "coupon");
            return (Criteria) this;
        }

        public Criteria andCouponNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("coupon not between", value1, value2, "coupon");
            return (Criteria) this;
        }

        public Criteria andPlatCouponIsNull() {
            addCriterion("platCoupon is null");
            return (Criteria) this;
        }

        public Criteria andPlatCouponIsNotNull() {
            addCriterion("platCoupon is not null");
            return (Criteria) this;
        }

        public Criteria andPlatCouponEqualTo(BigDecimal value) {
            addCriterion("platCoupon =", value, "platCoupon");
            return (Criteria) this;
        }

        public Criteria andPlatCouponNotEqualTo(BigDecimal value) {
            addCriterion("platCoupon <>", value, "platCoupon");
            return (Criteria) this;
        }

        public Criteria andPlatCouponGreaterThan(BigDecimal value) {
            addCriterion("platCoupon >", value, "platCoupon");
            return (Criteria) this;
        }

        public Criteria andPlatCouponGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("platCoupon >=", value, "platCoupon");
            return (Criteria) this;
        }

        public Criteria andPlatCouponLessThan(BigDecimal value) {
            addCriterion("platCoupon <", value, "platCoupon");
            return (Criteria) this;
        }

        public Criteria andPlatCouponLessThanOrEqualTo(BigDecimal value) {
            addCriterion("platCoupon <=", value, "platCoupon");
            return (Criteria) this;
        }

        public Criteria andPlatCouponIn(List<BigDecimal> values) {
            addCriterion("platCoupon in", values, "platCoupon");
            return (Criteria) this;
        }

        public Criteria andPlatCouponNotIn(List<BigDecimal> values) {
            addCriterion("platCoupon not in", values, "platCoupon");
            return (Criteria) this;
        }

        public Criteria andPlatCouponBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("platCoupon between", value1, value2, "platCoupon");
            return (Criteria) this;
        }

        public Criteria andPlatCouponNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("platCoupon not between", value1, value2, "platCoupon");
            return (Criteria) this;
        }

        public Criteria andCouponIdIsNull() {
            addCriterion("couponId is null");
            return (Criteria) this;
        }

        public Criteria andCouponIdIsNotNull() {
            addCriterion("couponId is not null");
            return (Criteria) this;
        }

        public Criteria andCouponIdEqualTo(String value) {
            addCriterion("couponId =", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdNotEqualTo(String value) {
            addCriterion("couponId <>", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdGreaterThan(String value) {
            addCriterion("couponId >", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdGreaterThanOrEqualTo(String value) {
            addCriterion("couponId >=", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdLessThan(String value) {
            addCriterion("couponId <", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdLessThanOrEqualTo(String value) {
            addCriterion("couponId <=", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdLike(String value) {
            addCriterion("couponId like", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdNotLike(String value) {
            addCriterion("couponId not like", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdIn(List<String> values) {
            addCriterion("couponId in", values, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdNotIn(List<String> values) {
            addCriterion("couponId not in", values, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdBetween(String value1, String value2) {
            addCriterion("couponId between", value1, value2, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdNotBetween(String value1, String value2) {
            addCriterion("couponId not between", value1, value2, "couponId");
            return (Criteria) this;
        }

        public Criteria andSpCouponIsNull() {
            addCriterion("spCoupon is null");
            return (Criteria) this;
        }

        public Criteria andSpCouponIsNotNull() {
            addCriterion("spCoupon is not null");
            return (Criteria) this;
        }

        public Criteria andSpCouponEqualTo(BigDecimal value) {
            addCriterion("spCoupon =", value, "spCoupon");
            return (Criteria) this;
        }

        public Criteria andSpCouponNotEqualTo(BigDecimal value) {
            addCriterion("spCoupon <>", value, "spCoupon");
            return (Criteria) this;
        }

        public Criteria andSpCouponGreaterThan(BigDecimal value) {
            addCriterion("spCoupon >", value, "spCoupon");
            return (Criteria) this;
        }

        public Criteria andSpCouponGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("spCoupon >=", value, "spCoupon");
            return (Criteria) this;
        }

        public Criteria andSpCouponLessThan(BigDecimal value) {
            addCriterion("spCoupon <", value, "spCoupon");
            return (Criteria) this;
        }

        public Criteria andSpCouponLessThanOrEqualTo(BigDecimal value) {
            addCriterion("spCoupon <=", value, "spCoupon");
            return (Criteria) this;
        }

        public Criteria andSpCouponIn(List<BigDecimal> values) {
            addCriterion("spCoupon in", values, "spCoupon");
            return (Criteria) this;
        }

        public Criteria andSpCouponNotIn(List<BigDecimal> values) {
            addCriterion("spCoupon not in", values, "spCoupon");
            return (Criteria) this;
        }

        public Criteria andSpCouponBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("spCoupon between", value1, value2, "spCoupon");
            return (Criteria) this;
        }

        public Criteria andSpCouponNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("spCoupon not between", value1, value2, "spCoupon");
            return (Criteria) this;
        }

        public Criteria andSpCouponIdIsNull() {
            addCriterion("spCouponId is null");
            return (Criteria) this;
        }

        public Criteria andSpCouponIdIsNotNull() {
            addCriterion("spCouponId is not null");
            return (Criteria) this;
        }

        public Criteria andSpCouponIdEqualTo(String value) {
            addCriterion("spCouponId =", value, "spCouponId");
            return (Criteria) this;
        }

        public Criteria andSpCouponIdNotEqualTo(String value) {
            addCriterion("spCouponId <>", value, "spCouponId");
            return (Criteria) this;
        }

        public Criteria andSpCouponIdGreaterThan(String value) {
            addCriterion("spCouponId >", value, "spCouponId");
            return (Criteria) this;
        }

        public Criteria andSpCouponIdGreaterThanOrEqualTo(String value) {
            addCriterion("spCouponId >=", value, "spCouponId");
            return (Criteria) this;
        }

        public Criteria andSpCouponIdLessThan(String value) {
            addCriterion("spCouponId <", value, "spCouponId");
            return (Criteria) this;
        }

        public Criteria andSpCouponIdLessThanOrEqualTo(String value) {
            addCriterion("spCouponId <=", value, "spCouponId");
            return (Criteria) this;
        }

        public Criteria andSpCouponIdLike(String value) {
            addCriterion("spCouponId like", value, "spCouponId");
            return (Criteria) this;
        }

        public Criteria andSpCouponIdNotLike(String value) {
            addCriterion("spCouponId not like", value, "spCouponId");
            return (Criteria) this;
        }

        public Criteria andSpCouponIdIn(List<String> values) {
            addCriterion("spCouponId in", values, "spCouponId");
            return (Criteria) this;
        }

        public Criteria andSpCouponIdNotIn(List<String> values) {
            addCriterion("spCouponId not in", values, "spCouponId");
            return (Criteria) this;
        }

        public Criteria andSpCouponIdBetween(String value1, String value2) {
            addCriterion("spCouponId between", value1, value2, "spCouponId");
            return (Criteria) this;
        }

        public Criteria andSpCouponIdNotBetween(String value1, String value2) {
            addCriterion("spCouponId not between", value1, value2, "spCouponId");
            return (Criteria) this;
        }

        public Criteria andSpCouponPlatIsNull() {
            addCriterion("spCouponPlat is null");
            return (Criteria) this;
        }

        public Criteria andSpCouponPlatIsNotNull() {
            addCriterion("spCouponPlat is not null");
            return (Criteria) this;
        }

        public Criteria andSpCouponPlatEqualTo(BigDecimal value) {
            addCriterion("spCouponPlat =", value, "spCouponPlat");
            return (Criteria) this;
        }

        public Criteria andSpCouponPlatNotEqualTo(BigDecimal value) {
            addCriterion("spCouponPlat <>", value, "spCouponPlat");
            return (Criteria) this;
        }

        public Criteria andSpCouponPlatGreaterThan(BigDecimal value) {
            addCriterion("spCouponPlat >", value, "spCouponPlat");
            return (Criteria) this;
        }

        public Criteria andSpCouponPlatGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("spCouponPlat >=", value, "spCouponPlat");
            return (Criteria) this;
        }

        public Criteria andSpCouponPlatLessThan(BigDecimal value) {
            addCriterion("spCouponPlat <", value, "spCouponPlat");
            return (Criteria) this;
        }

        public Criteria andSpCouponPlatLessThanOrEqualTo(BigDecimal value) {
            addCriterion("spCouponPlat <=", value, "spCouponPlat");
            return (Criteria) this;
        }

        public Criteria andSpCouponPlatIn(List<BigDecimal> values) {
            addCriterion("spCouponPlat in", values, "spCouponPlat");
            return (Criteria) this;
        }

        public Criteria andSpCouponPlatNotIn(List<BigDecimal> values) {
            addCriterion("spCouponPlat not in", values, "spCouponPlat");
            return (Criteria) this;
        }

        public Criteria andSpCouponPlatBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("spCouponPlat between", value1, value2, "spCouponPlat");
            return (Criteria) this;
        }

        public Criteria andSpCouponPlatNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("spCouponPlat not between", value1, value2, "spCouponPlat");
            return (Criteria) this;
        }

        public Criteria andSpCouponSpIsNull() {
            addCriterion("spCouponSp is null");
            return (Criteria) this;
        }

        public Criteria andSpCouponSpIsNotNull() {
            addCriterion("spCouponSp is not null");
            return (Criteria) this;
        }

        public Criteria andSpCouponSpEqualTo(BigDecimal value) {
            addCriterion("spCouponSp =", value, "spCouponSp");
            return (Criteria) this;
        }

        public Criteria andSpCouponSpNotEqualTo(BigDecimal value) {
            addCriterion("spCouponSp <>", value, "spCouponSp");
            return (Criteria) this;
        }

        public Criteria andSpCouponSpGreaterThan(BigDecimal value) {
            addCriterion("spCouponSp >", value, "spCouponSp");
            return (Criteria) this;
        }

        public Criteria andSpCouponSpGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("spCouponSp >=", value, "spCouponSp");
            return (Criteria) this;
        }

        public Criteria andSpCouponSpLessThan(BigDecimal value) {
            addCriterion("spCouponSp <", value, "spCouponSp");
            return (Criteria) this;
        }

        public Criteria andSpCouponSpLessThanOrEqualTo(BigDecimal value) {
            addCriterion("spCouponSp <=", value, "spCouponSp");
            return (Criteria) this;
        }

        public Criteria andSpCouponSpIn(List<BigDecimal> values) {
            addCriterion("spCouponSp in", values, "spCouponSp");
            return (Criteria) this;
        }

        public Criteria andSpCouponSpNotIn(List<BigDecimal> values) {
            addCriterion("spCouponSp not in", values, "spCouponSp");
            return (Criteria) this;
        }

        public Criteria andSpCouponSpBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("spCouponSp between", value1, value2, "spCouponSp");
            return (Criteria) this;
        }

        public Criteria andSpCouponSpNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("spCouponSp not between", value1, value2, "spCouponSp");
            return (Criteria) this;
        }

        public Criteria andKfIdIsNull() {
            addCriterion("kfId is null");
            return (Criteria) this;
        }

        public Criteria andKfIdIsNotNull() {
            addCriterion("kfId is not null");
            return (Criteria) this;
        }

        public Criteria andKfIdEqualTo(String value) {
            addCriterion("kfId =", value, "kfId");
            return (Criteria) this;
        }

        public Criteria andKfIdNotEqualTo(String value) {
            addCriterion("kfId <>", value, "kfId");
            return (Criteria) this;
        }

        public Criteria andKfIdGreaterThan(String value) {
            addCriterion("kfId >", value, "kfId");
            return (Criteria) this;
        }

        public Criteria andKfIdGreaterThanOrEqualTo(String value) {
            addCriterion("kfId >=", value, "kfId");
            return (Criteria) this;
        }

        public Criteria andKfIdLessThan(String value) {
            addCriterion("kfId <", value, "kfId");
            return (Criteria) this;
        }

        public Criteria andKfIdLessThanOrEqualTo(String value) {
            addCriterion("kfId <=", value, "kfId");
            return (Criteria) this;
        }

        public Criteria andKfIdLike(String value) {
            addCriterion("kfId like", value, "kfId");
            return (Criteria) this;
        }

        public Criteria andKfIdNotLike(String value) {
            addCriterion("kfId not like", value, "kfId");
            return (Criteria) this;
        }

        public Criteria andKfIdIn(List<String> values) {
            addCriterion("kfId in", values, "kfId");
            return (Criteria) this;
        }

        public Criteria andKfIdNotIn(List<String> values) {
            addCriterion("kfId not in", values, "kfId");
            return (Criteria) this;
        }

        public Criteria andKfIdBetween(String value1, String value2) {
            addCriterion("kfId between", value1, value2, "kfId");
            return (Criteria) this;
        }

        public Criteria andKfIdNotBetween(String value1, String value2) {
            addCriterion("kfId not between", value1, value2, "kfId");
            return (Criteria) this;
        }

        public Criteria andKfNameIsNull() {
            addCriterion("kfName is null");
            return (Criteria) this;
        }

        public Criteria andKfNameIsNotNull() {
            addCriterion("kfName is not null");
            return (Criteria) this;
        }

        public Criteria andKfNameEqualTo(String value) {
            addCriterion("kfName =", value, "kfName");
            return (Criteria) this;
        }

        public Criteria andKfNameNotEqualTo(String value) {
            addCriterion("kfName <>", value, "kfName");
            return (Criteria) this;
        }

        public Criteria andKfNameGreaterThan(String value) {
            addCriterion("kfName >", value, "kfName");
            return (Criteria) this;
        }

        public Criteria andKfNameGreaterThanOrEqualTo(String value) {
            addCriterion("kfName >=", value, "kfName");
            return (Criteria) this;
        }

        public Criteria andKfNameLessThan(String value) {
            addCriterion("kfName <", value, "kfName");
            return (Criteria) this;
        }

        public Criteria andKfNameLessThanOrEqualTo(String value) {
            addCriterion("kfName <=", value, "kfName");
            return (Criteria) this;
        }

        public Criteria andKfNameLike(String value) {
            addCriterion("kfName like", value, "kfName");
            return (Criteria) this;
        }

        public Criteria andKfNameNotLike(String value) {
            addCriterion("kfName not like", value, "kfName");
            return (Criteria) this;
        }

        public Criteria andKfNameIn(List<String> values) {
            addCriterion("kfName in", values, "kfName");
            return (Criteria) this;
        }

        public Criteria andKfNameNotIn(List<String> values) {
            addCriterion("kfName not in", values, "kfName");
            return (Criteria) this;
        }

        public Criteria andKfNameBetween(String value1, String value2) {
            addCriterion("kfName between", value1, value2, "kfName");
            return (Criteria) this;
        }

        public Criteria andKfNameNotBetween(String value1, String value2) {
            addCriterion("kfName not between", value1, value2, "kfName");
            return (Criteria) this;
        }

        public Criteria andKfnoteIsNull() {
            addCriterion("kfnote is null");
            return (Criteria) this;
        }

        public Criteria andKfnoteIsNotNull() {
            addCriterion("kfnote is not null");
            return (Criteria) this;
        }

        public Criteria andKfnoteEqualTo(String value) {
            addCriterion("kfnote =", value, "kfnote");
            return (Criteria) this;
        }

        public Criteria andKfnoteNotEqualTo(String value) {
            addCriterion("kfnote <>", value, "kfnote");
            return (Criteria) this;
        }

        public Criteria andKfnoteGreaterThan(String value) {
            addCriterion("kfnote >", value, "kfnote");
            return (Criteria) this;
        }

        public Criteria andKfnoteGreaterThanOrEqualTo(String value) {
            addCriterion("kfnote >=", value, "kfnote");
            return (Criteria) this;
        }

        public Criteria andKfnoteLessThan(String value) {
            addCriterion("kfnote <", value, "kfnote");
            return (Criteria) this;
        }

        public Criteria andKfnoteLessThanOrEqualTo(String value) {
            addCriterion("kfnote <=", value, "kfnote");
            return (Criteria) this;
        }

        public Criteria andKfnoteLike(String value) {
            addCriterion("kfnote like", value, "kfnote");
            return (Criteria) this;
        }

        public Criteria andKfnoteNotLike(String value) {
            addCriterion("kfnote not like", value, "kfnote");
            return (Criteria) this;
        }

        public Criteria andKfnoteIn(List<String> values) {
            addCriterion("kfnote in", values, "kfnote");
            return (Criteria) this;
        }

        public Criteria andKfnoteNotIn(List<String> values) {
            addCriterion("kfnote not in", values, "kfnote");
            return (Criteria) this;
        }

        public Criteria andKfnoteBetween(String value1, String value2) {
            addCriterion("kfnote between", value1, value2, "kfnote");
            return (Criteria) this;
        }

        public Criteria andKfnoteNotBetween(String value1, String value2) {
            addCriterion("kfnote not between", value1, value2, "kfnote");
            return (Criteria) this;
        }

        public Criteria andKfStatusIsNull() {
            addCriterion("kfStatus is null");
            return (Criteria) this;
        }

        public Criteria andKfStatusIsNotNull() {
            addCriterion("kfStatus is not null");
            return (Criteria) this;
        }

        public Criteria andKfStatusEqualTo(Byte value) {
            addCriterion("kfStatus =", value, "kfStatus");
            return (Criteria) this;
        }

        public Criteria andKfStatusNotEqualTo(Byte value) {
            addCriterion("kfStatus <>", value, "kfStatus");
            return (Criteria) this;
        }

        public Criteria andKfStatusGreaterThan(Byte value) {
            addCriterion("kfStatus >", value, "kfStatus");
            return (Criteria) this;
        }

        public Criteria andKfStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("kfStatus >=", value, "kfStatus");
            return (Criteria) this;
        }

        public Criteria andKfStatusLessThan(Byte value) {
            addCriterion("kfStatus <", value, "kfStatus");
            return (Criteria) this;
        }

        public Criteria andKfStatusLessThanOrEqualTo(Byte value) {
            addCriterion("kfStatus <=", value, "kfStatus");
            return (Criteria) this;
        }

        public Criteria andKfStatusIn(List<Byte> values) {
            addCriterion("kfStatus in", values, "kfStatus");
            return (Criteria) this;
        }

        public Criteria andKfStatusNotIn(List<Byte> values) {
            addCriterion("kfStatus not in", values, "kfStatus");
            return (Criteria) this;
        }

        public Criteria andKfStatusBetween(Byte value1, Byte value2) {
            addCriterion("kfStatus between", value1, value2, "kfStatus");
            return (Criteria) this;
        }

        public Criteria andKfStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("kfStatus not between", value1, value2, "kfStatus");
            return (Criteria) this;
        }

        public Criteria andKfCheckTimeIsNull() {
            addCriterion("kfCheckTime is null");
            return (Criteria) this;
        }

        public Criteria andKfCheckTimeIsNotNull() {
            addCriterion("kfCheckTime is not null");
            return (Criteria) this;
        }

        public Criteria andKfCheckTimeEqualTo(Date value) {
            addCriterion("kfCheckTime =", value, "kfCheckTime");
            return (Criteria) this;
        }

        public Criteria andKfCheckTimeNotEqualTo(Date value) {
            addCriterion("kfCheckTime <>", value, "kfCheckTime");
            return (Criteria) this;
        }

        public Criteria andKfCheckTimeGreaterThan(Date value) {
            addCriterion("kfCheckTime >", value, "kfCheckTime");
            return (Criteria) this;
        }

        public Criteria andKfCheckTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("kfCheckTime >=", value, "kfCheckTime");
            return (Criteria) this;
        }

        public Criteria andKfCheckTimeLessThan(Date value) {
            addCriterion("kfCheckTime <", value, "kfCheckTime");
            return (Criteria) this;
        }

        public Criteria andKfCheckTimeLessThanOrEqualTo(Date value) {
            addCriterion("kfCheckTime <=", value, "kfCheckTime");
            return (Criteria) this;
        }

        public Criteria andKfCheckTimeIn(List<Date> values) {
            addCriterion("kfCheckTime in", values, "kfCheckTime");
            return (Criteria) this;
        }

        public Criteria andKfCheckTimeNotIn(List<Date> values) {
            addCriterion("kfCheckTime not in", values, "kfCheckTime");
            return (Criteria) this;
        }

        public Criteria andKfCheckTimeBetween(Date value1, Date value2) {
            addCriterion("kfCheckTime between", value1, value2, "kfCheckTime");
            return (Criteria) this;
        }

        public Criteria andKfCheckTimeNotBetween(Date value1, Date value2) {
            addCriterion("kfCheckTime not between", value1, value2, "kfCheckTime");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("userId is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("userId is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("userId =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("userId <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("userId >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("userId >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("userId <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("userId <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("userId like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("userId not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("userId in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("userId not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("userId between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("userId not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNull() {
            addCriterion("userName is null");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNotNull() {
            addCriterion("userName is not null");
            return (Criteria) this;
        }

        public Criteria andUserNameEqualTo(String value) {
            addCriterion("userName =", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotEqualTo(String value) {
            addCriterion("userName <>", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThan(String value) {
            addCriterion("userName >", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("userName >=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThan(String value) {
            addCriterion("userName <", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThanOrEqualTo(String value) {
            addCriterion("userName <=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLike(String value) {
            addCriterion("userName like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotLike(String value) {
            addCriterion("userName not like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameIn(List<String> values) {
            addCriterion("userName in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotIn(List<String> values) {
            addCriterion("userName not in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameBetween(String value1, String value2) {
            addCriterion("userName between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotBetween(String value1, String value2) {
            addCriterion("userName not between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserRemarkIsNull() {
            addCriterion("userRemark is null");
            return (Criteria) this;
        }

        public Criteria andUserRemarkIsNotNull() {
            addCriterion("userRemark is not null");
            return (Criteria) this;
        }

        public Criteria andUserRemarkEqualTo(String value) {
            addCriterion("userRemark =", value, "userRemark");
            return (Criteria) this;
        }

        public Criteria andUserRemarkNotEqualTo(String value) {
            addCriterion("userRemark <>", value, "userRemark");
            return (Criteria) this;
        }

        public Criteria andUserRemarkGreaterThan(String value) {
            addCriterion("userRemark >", value, "userRemark");
            return (Criteria) this;
        }

        public Criteria andUserRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("userRemark >=", value, "userRemark");
            return (Criteria) this;
        }

        public Criteria andUserRemarkLessThan(String value) {
            addCriterion("userRemark <", value, "userRemark");
            return (Criteria) this;
        }

        public Criteria andUserRemarkLessThanOrEqualTo(String value) {
            addCriterion("userRemark <=", value, "userRemark");
            return (Criteria) this;
        }

        public Criteria andUserRemarkLike(String value) {
            addCriterion("userRemark like", value, "userRemark");
            return (Criteria) this;
        }

        public Criteria andUserRemarkNotLike(String value) {
            addCriterion("userRemark not like", value, "userRemark");
            return (Criteria) this;
        }

        public Criteria andUserRemarkIn(List<String> values) {
            addCriterion("userRemark in", values, "userRemark");
            return (Criteria) this;
        }

        public Criteria andUserRemarkNotIn(List<String> values) {
            addCriterion("userRemark not in", values, "userRemark");
            return (Criteria) this;
        }

        public Criteria andUserRemarkBetween(String value1, String value2) {
            addCriterion("userRemark between", value1, value2, "userRemark");
            return (Criteria) this;
        }

        public Criteria andUserRemarkNotBetween(String value1, String value2) {
            addCriterion("userRemark not between", value1, value2, "userRemark");
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

        public Criteria andStoreNameIsNull() {
            addCriterion("storeName is null");
            return (Criteria) this;
        }

        public Criteria andStoreNameIsNotNull() {
            addCriterion("storeName is not null");
            return (Criteria) this;
        }

        public Criteria andStoreNameEqualTo(String value) {
            addCriterion("storeName =", value, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameNotEqualTo(String value) {
            addCriterion("storeName <>", value, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameGreaterThan(String value) {
            addCriterion("storeName >", value, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameGreaterThanOrEqualTo(String value) {
            addCriterion("storeName >=", value, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameLessThan(String value) {
            addCriterion("storeName <", value, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameLessThanOrEqualTo(String value) {
            addCriterion("storeName <=", value, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameLike(String value) {
            addCriterion("storeName like", value, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameNotLike(String value) {
            addCriterion("storeName not like", value, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameIn(List<String> values) {
            addCriterion("storeName in", values, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameNotIn(List<String> values) {
            addCriterion("storeName not in", values, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameBetween(String value1, String value2) {
            addCriterion("storeName between", value1, value2, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameNotBetween(String value1, String value2) {
            addCriterion("storeName not between", value1, value2, "storeName");
            return (Criteria) this;
        }

        public Criteria andConsigneeIsNull() {
            addCriterion("consignee is null");
            return (Criteria) this;
        }

        public Criteria andConsigneeIsNotNull() {
            addCriterion("consignee is not null");
            return (Criteria) this;
        }

        public Criteria andConsigneeEqualTo(String value) {
            addCriterion("consignee =", value, "consignee");
            return (Criteria) this;
        }

        public Criteria andConsigneeNotEqualTo(String value) {
            addCriterion("consignee <>", value, "consignee");
            return (Criteria) this;
        }

        public Criteria andConsigneeGreaterThan(String value) {
            addCriterion("consignee >", value, "consignee");
            return (Criteria) this;
        }

        public Criteria andConsigneeGreaterThanOrEqualTo(String value) {
            addCriterion("consignee >=", value, "consignee");
            return (Criteria) this;
        }

        public Criteria andConsigneeLessThan(String value) {
            addCriterion("consignee <", value, "consignee");
            return (Criteria) this;
        }

        public Criteria andConsigneeLessThanOrEqualTo(String value) {
            addCriterion("consignee <=", value, "consignee");
            return (Criteria) this;
        }

        public Criteria andConsigneeLike(String value) {
            addCriterion("consignee like", value, "consignee");
            return (Criteria) this;
        }

        public Criteria andConsigneeNotLike(String value) {
            addCriterion("consignee not like", value, "consignee");
            return (Criteria) this;
        }

        public Criteria andConsigneeIn(List<String> values) {
            addCriterion("consignee in", values, "consignee");
            return (Criteria) this;
        }

        public Criteria andConsigneeNotIn(List<String> values) {
            addCriterion("consignee not in", values, "consignee");
            return (Criteria) this;
        }

        public Criteria andConsigneeBetween(String value1, String value2) {
            addCriterion("consignee between", value1, value2, "consignee");
            return (Criteria) this;
        }

        public Criteria andConsigneeNotBetween(String value1, String value2) {
            addCriterion("consignee not between", value1, value2, "consignee");
            return (Criteria) this;
        }

        public Criteria andMobileIsNull() {
            addCriterion("mobile is null");
            return (Criteria) this;
        }

        public Criteria andMobileIsNotNull() {
            addCriterion("mobile is not null");
            return (Criteria) this;
        }

        public Criteria andMobileEqualTo(String value) {
            addCriterion("mobile =", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotEqualTo(String value) {
            addCriterion("mobile <>", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThan(String value) {
            addCriterion("mobile >", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThanOrEqualTo(String value) {
            addCriterion("mobile >=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThan(String value) {
            addCriterion("mobile <", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThanOrEqualTo(String value) {
            addCriterion("mobile <=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLike(String value) {
            addCriterion("mobile like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotLike(String value) {
            addCriterion("mobile not like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileIn(List<String> values) {
            addCriterion("mobile in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotIn(List<String> values) {
            addCriterion("mobile not in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileBetween(String value1, String value2) {
            addCriterion("mobile between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotBetween(String value1, String value2) {
            addCriterion("mobile not between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andUserTelIsNull() {
            addCriterion("userTel is null");
            return (Criteria) this;
        }

        public Criteria andUserTelIsNotNull() {
            addCriterion("userTel is not null");
            return (Criteria) this;
        }

        public Criteria andUserTelEqualTo(String value) {
            addCriterion("userTel =", value, "userTel");
            return (Criteria) this;
        }

        public Criteria andUserTelNotEqualTo(String value) {
            addCriterion("userTel <>", value, "userTel");
            return (Criteria) this;
        }

        public Criteria andUserTelGreaterThan(String value) {
            addCriterion("userTel >", value, "userTel");
            return (Criteria) this;
        }

        public Criteria andUserTelGreaterThanOrEqualTo(String value) {
            addCriterion("userTel >=", value, "userTel");
            return (Criteria) this;
        }

        public Criteria andUserTelLessThan(String value) {
            addCriterion("userTel <", value, "userTel");
            return (Criteria) this;
        }

        public Criteria andUserTelLessThanOrEqualTo(String value) {
            addCriterion("userTel <=", value, "userTel");
            return (Criteria) this;
        }

        public Criteria andUserTelLike(String value) {
            addCriterion("userTel like", value, "userTel");
            return (Criteria) this;
        }

        public Criteria andUserTelNotLike(String value) {
            addCriterion("userTel not like", value, "userTel");
            return (Criteria) this;
        }

        public Criteria andUserTelIn(List<String> values) {
            addCriterion("userTel in", values, "userTel");
            return (Criteria) this;
        }

        public Criteria andUserTelNotIn(List<String> values) {
            addCriterion("userTel not in", values, "userTel");
            return (Criteria) this;
        }

        public Criteria andUserTelBetween(String value1, String value2) {
            addCriterion("userTel between", value1, value2, "userTel");
            return (Criteria) this;
        }

        public Criteria andUserTelNotBetween(String value1, String value2) {
            addCriterion("userTel not between", value1, value2, "userTel");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
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

        public Criteria andEvaluationIsNull() {
            addCriterion("evaluation is null");
            return (Criteria) this;
        }

        public Criteria andEvaluationIsNotNull() {
            addCriterion("evaluation is not null");
            return (Criteria) this;
        }

        public Criteria andEvaluationEqualTo(Byte value) {
            addCriterion("evaluation =", value, "evaluation");
            return (Criteria) this;
        }

        public Criteria andEvaluationNotEqualTo(Byte value) {
            addCriterion("evaluation <>", value, "evaluation");
            return (Criteria) this;
        }

        public Criteria andEvaluationGreaterThan(Byte value) {
            addCriterion("evaluation >", value, "evaluation");
            return (Criteria) this;
        }

        public Criteria andEvaluationGreaterThanOrEqualTo(Byte value) {
            addCriterion("evaluation >=", value, "evaluation");
            return (Criteria) this;
        }

        public Criteria andEvaluationLessThan(Byte value) {
            addCriterion("evaluation <", value, "evaluation");
            return (Criteria) this;
        }

        public Criteria andEvaluationLessThanOrEqualTo(Byte value) {
            addCriterion("evaluation <=", value, "evaluation");
            return (Criteria) this;
        }

        public Criteria andEvaluationIn(List<Byte> values) {
            addCriterion("evaluation in", values, "evaluation");
            return (Criteria) this;
        }

        public Criteria andEvaluationNotIn(List<Byte> values) {
            addCriterion("evaluation not in", values, "evaluation");
            return (Criteria) this;
        }

        public Criteria andEvaluationBetween(Byte value1, Byte value2) {
            addCriterion("evaluation between", value1, value2, "evaluation");
            return (Criteria) this;
        }

        public Criteria andEvaluationNotBetween(Byte value1, Byte value2) {
            addCriterion("evaluation not between", value1, value2, "evaluation");
            return (Criteria) this;
        }

        public Criteria andSupportStatusIsNull() {
            addCriterion("supportStatus is null");
            return (Criteria) this;
        }

        public Criteria andSupportStatusIsNotNull() {
            addCriterion("supportStatus is not null");
            return (Criteria) this;
        }

        public Criteria andSupportStatusEqualTo(Byte value) {
            addCriterion("supportStatus =", value, "supportStatus");
            return (Criteria) this;
        }

        public Criteria andSupportStatusNotEqualTo(Byte value) {
            addCriterion("supportStatus <>", value, "supportStatus");
            return (Criteria) this;
        }

        public Criteria andSupportStatusGreaterThan(Byte value) {
            addCriterion("supportStatus >", value, "supportStatus");
            return (Criteria) this;
        }

        public Criteria andSupportStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("supportStatus >=", value, "supportStatus");
            return (Criteria) this;
        }

        public Criteria andSupportStatusLessThan(Byte value) {
            addCriterion("supportStatus <", value, "supportStatus");
            return (Criteria) this;
        }

        public Criteria andSupportStatusLessThanOrEqualTo(Byte value) {
            addCriterion("supportStatus <=", value, "supportStatus");
            return (Criteria) this;
        }

        public Criteria andSupportStatusIn(List<Byte> values) {
            addCriterion("supportStatus in", values, "supportStatus");
            return (Criteria) this;
        }

        public Criteria andSupportStatusNotIn(List<Byte> values) {
            addCriterion("supportStatus not in", values, "supportStatus");
            return (Criteria) this;
        }

        public Criteria andSupportStatusBetween(Byte value1, Byte value2) {
            addCriterion("supportStatus between", value1, value2, "supportStatus");
            return (Criteria) this;
        }

        public Criteria andSupportStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("supportStatus not between", value1, value2, "supportStatus");
            return (Criteria) this;
        }

        public Criteria andSupportmethoIsNull() {
            addCriterion("supportmetho is null");
            return (Criteria) this;
        }

        public Criteria andSupportmethoIsNotNull() {
            addCriterion("supportmetho is not null");
            return (Criteria) this;
        }

        public Criteria andSupportmethoEqualTo(Byte value) {
            addCriterion("supportmetho =", value, "supportmetho");
            return (Criteria) this;
        }

        public Criteria andSupportmethoNotEqualTo(Byte value) {
            addCriterion("supportmetho <>", value, "supportmetho");
            return (Criteria) this;
        }

        public Criteria andSupportmethoGreaterThan(Byte value) {
            addCriterion("supportmetho >", value, "supportmetho");
            return (Criteria) this;
        }

        public Criteria andSupportmethoGreaterThanOrEqualTo(Byte value) {
            addCriterion("supportmetho >=", value, "supportmetho");
            return (Criteria) this;
        }

        public Criteria andSupportmethoLessThan(Byte value) {
            addCriterion("supportmetho <", value, "supportmetho");
            return (Criteria) this;
        }

        public Criteria andSupportmethoLessThanOrEqualTo(Byte value) {
            addCriterion("supportmetho <=", value, "supportmetho");
            return (Criteria) this;
        }

        public Criteria andSupportmethoIn(List<Byte> values) {
            addCriterion("supportmetho in", values, "supportmetho");
            return (Criteria) this;
        }

        public Criteria andSupportmethoNotIn(List<Byte> values) {
            addCriterion("supportmetho not in", values, "supportmetho");
            return (Criteria) this;
        }

        public Criteria andSupportmethoBetween(Byte value1, Byte value2) {
            addCriterion("supportmetho between", value1, value2, "supportmetho");
            return (Criteria) this;
        }

        public Criteria andSupportmethoNotBetween(Byte value1, Byte value2) {
            addCriterion("supportmetho not between", value1, value2, "supportmetho");
            return (Criteria) this;
        }

        public Criteria andSupportTimeIsNull() {
            addCriterion("supportTime is null");
            return (Criteria) this;
        }

        public Criteria andSupportTimeIsNotNull() {
            addCriterion("supportTime is not null");
            return (Criteria) this;
        }

        public Criteria andSupportTimeEqualTo(Date value) {
            addCriterion("supportTime =", value, "supportTime");
            return (Criteria) this;
        }

        public Criteria andSupportTimeNotEqualTo(Date value) {
            addCriterion("supportTime <>", value, "supportTime");
            return (Criteria) this;
        }

        public Criteria andSupportTimeGreaterThan(Date value) {
            addCriterion("supportTime >", value, "supportTime");
            return (Criteria) this;
        }

        public Criteria andSupportTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("supportTime >=", value, "supportTime");
            return (Criteria) this;
        }

        public Criteria andSupportTimeLessThan(Date value) {
            addCriterion("supportTime <", value, "supportTime");
            return (Criteria) this;
        }

        public Criteria andSupportTimeLessThanOrEqualTo(Date value) {
            addCriterion("supportTime <=", value, "supportTime");
            return (Criteria) this;
        }

        public Criteria andSupportTimeIn(List<Date> values) {
            addCriterion("supportTime in", values, "supportTime");
            return (Criteria) this;
        }

        public Criteria andSupportTimeNotIn(List<Date> values) {
            addCriterion("supportTime not in", values, "supportTime");
            return (Criteria) this;
        }

        public Criteria andSupportTimeBetween(Date value1, Date value2) {
            addCriterion("supportTime between", value1, value2, "supportTime");
            return (Criteria) this;
        }

        public Criteria andSupportTimeNotBetween(Date value1, Date value2) {
            addCriterion("supportTime not between", value1, value2, "supportTime");
            return (Criteria) this;
        }

        public Criteria andOrdertypeIsNull() {
            addCriterion("ordertype is null");
            return (Criteria) this;
        }

        public Criteria andOrdertypeIsNotNull() {
            addCriterion("ordertype is not null");
            return (Criteria) this;
        }

        public Criteria andOrdertypeEqualTo(Byte value) {
            addCriterion("ordertype =", value, "ordertype");
            return (Criteria) this;
        }

        public Criteria andOrdertypeNotEqualTo(Byte value) {
            addCriterion("ordertype <>", value, "ordertype");
            return (Criteria) this;
        }

        public Criteria andOrdertypeGreaterThan(Byte value) {
            addCriterion("ordertype >", value, "ordertype");
            return (Criteria) this;
        }

        public Criteria andOrdertypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("ordertype >=", value, "ordertype");
            return (Criteria) this;
        }

        public Criteria andOrdertypeLessThan(Byte value) {
            addCriterion("ordertype <", value, "ordertype");
            return (Criteria) this;
        }

        public Criteria andOrdertypeLessThanOrEqualTo(Byte value) {
            addCriterion("ordertype <=", value, "ordertype");
            return (Criteria) this;
        }

        public Criteria andOrdertypeIn(List<Byte> values) {
            addCriterion("ordertype in", values, "ordertype");
            return (Criteria) this;
        }

        public Criteria andOrdertypeNotIn(List<Byte> values) {
            addCriterion("ordertype not in", values, "ordertype");
            return (Criteria) this;
        }

        public Criteria andOrdertypeBetween(Byte value1, Byte value2) {
            addCriterion("ordertype between", value1, value2, "ordertype");
            return (Criteria) this;
        }

        public Criteria andOrdertypeNotBetween(Byte value1, Byte value2) {
            addCriterion("ordertype not between", value1, value2, "ordertype");
            return (Criteria) this;
        }

        public Criteria andClosemsgIsNull() {
            addCriterion("closemsg is null");
            return (Criteria) this;
        }

        public Criteria andClosemsgIsNotNull() {
            addCriterion("closemsg is not null");
            return (Criteria) this;
        }

        public Criteria andClosemsgEqualTo(String value) {
            addCriterion("closemsg =", value, "closemsg");
            return (Criteria) this;
        }

        public Criteria andClosemsgNotEqualTo(String value) {
            addCriterion("closemsg <>", value, "closemsg");
            return (Criteria) this;
        }

        public Criteria andClosemsgGreaterThan(String value) {
            addCriterion("closemsg >", value, "closemsg");
            return (Criteria) this;
        }

        public Criteria andClosemsgGreaterThanOrEqualTo(String value) {
            addCriterion("closemsg >=", value, "closemsg");
            return (Criteria) this;
        }

        public Criteria andClosemsgLessThan(String value) {
            addCriterion("closemsg <", value, "closemsg");
            return (Criteria) this;
        }

        public Criteria andClosemsgLessThanOrEqualTo(String value) {
            addCriterion("closemsg <=", value, "closemsg");
            return (Criteria) this;
        }

        public Criteria andClosemsgLike(String value) {
            addCriterion("closemsg like", value, "closemsg");
            return (Criteria) this;
        }

        public Criteria andClosemsgNotLike(String value) {
            addCriterion("closemsg not like", value, "closemsg");
            return (Criteria) this;
        }

        public Criteria andClosemsgIn(List<String> values) {
            addCriterion("closemsg in", values, "closemsg");
            return (Criteria) this;
        }

        public Criteria andClosemsgNotIn(List<String> values) {
            addCriterion("closemsg not in", values, "closemsg");
            return (Criteria) this;
        }

        public Criteria andClosemsgBetween(String value1, String value2) {
            addCriterion("closemsg between", value1, value2, "closemsg");
            return (Criteria) this;
        }

        public Criteria andClosemsgNotBetween(String value1, String value2) {
            addCriterion("closemsg not between", value1, value2, "closemsg");
            return (Criteria) this;
        }

        public Criteria andGaveTimeIsNull() {
            addCriterion("gaveTime is null");
            return (Criteria) this;
        }

        public Criteria andGaveTimeIsNotNull() {
            addCriterion("gaveTime is not null");
            return (Criteria) this;
        }

        public Criteria andGaveTimeEqualTo(Date value) {
            addCriterion("gaveTime =", value, "gaveTime");
            return (Criteria) this;
        }

        public Criteria andGaveTimeNotEqualTo(Date value) {
            addCriterion("gaveTime <>", value, "gaveTime");
            return (Criteria) this;
        }

        public Criteria andGaveTimeGreaterThan(Date value) {
            addCriterion("gaveTime >", value, "gaveTime");
            return (Criteria) this;
        }

        public Criteria andGaveTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("gaveTime >=", value, "gaveTime");
            return (Criteria) this;
        }

        public Criteria andGaveTimeLessThan(Date value) {
            addCriterion("gaveTime <", value, "gaveTime");
            return (Criteria) this;
        }

        public Criteria andGaveTimeLessThanOrEqualTo(Date value) {
            addCriterion("gaveTime <=", value, "gaveTime");
            return (Criteria) this;
        }

        public Criteria andGaveTimeIn(List<Date> values) {
            addCriterion("gaveTime in", values, "gaveTime");
            return (Criteria) this;
        }

        public Criteria andGaveTimeNotIn(List<Date> values) {
            addCriterion("gaveTime not in", values, "gaveTime");
            return (Criteria) this;
        }

        public Criteria andGaveTimeBetween(Date value1, Date value2) {
            addCriterion("gaveTime between", value1, value2, "gaveTime");
            return (Criteria) this;
        }

        public Criteria andGaveTimeNotBetween(Date value1, Date value2) {
            addCriterion("gaveTime not between", value1, value2, "gaveTime");
            return (Criteria) this;
        }

        public Criteria andSupplierIdIsNull() {
            addCriterion("supplierId is null");
            return (Criteria) this;
        }

        public Criteria andSupplierIdIsNotNull() {
            addCriterion("supplierId is not null");
            return (Criteria) this;
        }

        public Criteria andSupplierIdEqualTo(String value) {
            addCriterion("supplierId =", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdNotEqualTo(String value) {
            addCriterion("supplierId <>", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdGreaterThan(String value) {
            addCriterion("supplierId >", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdGreaterThanOrEqualTo(String value) {
            addCriterion("supplierId >=", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdLessThan(String value) {
            addCriterion("supplierId <", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdLessThanOrEqualTo(String value) {
            addCriterion("supplierId <=", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdLike(String value) {
            addCriterion("supplierId like", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdNotLike(String value) {
            addCriterion("supplierId not like", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdIn(List<String> values) {
            addCriterion("supplierId in", values, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdNotIn(List<String> values) {
            addCriterion("supplierId not in", values, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdBetween(String value1, String value2) {
            addCriterion("supplierId between", value1, value2, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdNotBetween(String value1, String value2) {
            addCriterion("supplierId not between", value1, value2, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierTelIsNull() {
            addCriterion("supplierTel is null");
            return (Criteria) this;
        }

        public Criteria andSupplierTelIsNotNull() {
            addCriterion("supplierTel is not null");
            return (Criteria) this;
        }

        public Criteria andSupplierTelEqualTo(String value) {
            addCriterion("supplierTel =", value, "supplierTel");
            return (Criteria) this;
        }

        public Criteria andSupplierTelNotEqualTo(String value) {
            addCriterion("supplierTel <>", value, "supplierTel");
            return (Criteria) this;
        }

        public Criteria andSupplierTelGreaterThan(String value) {
            addCriterion("supplierTel >", value, "supplierTel");
            return (Criteria) this;
        }

        public Criteria andSupplierTelGreaterThanOrEqualTo(String value) {
            addCriterion("supplierTel >=", value, "supplierTel");
            return (Criteria) this;
        }

        public Criteria andSupplierTelLessThan(String value) {
            addCriterion("supplierTel <", value, "supplierTel");
            return (Criteria) this;
        }

        public Criteria andSupplierTelLessThanOrEqualTo(String value) {
            addCriterion("supplierTel <=", value, "supplierTel");
            return (Criteria) this;
        }

        public Criteria andSupplierTelLike(String value) {
            addCriterion("supplierTel like", value, "supplierTel");
            return (Criteria) this;
        }

        public Criteria andSupplierTelNotLike(String value) {
            addCriterion("supplierTel not like", value, "supplierTel");
            return (Criteria) this;
        }

        public Criteria andSupplierTelIn(List<String> values) {
            addCriterion("supplierTel in", values, "supplierTel");
            return (Criteria) this;
        }

        public Criteria andSupplierTelNotIn(List<String> values) {
            addCriterion("supplierTel not in", values, "supplierTel");
            return (Criteria) this;
        }

        public Criteria andSupplierTelBetween(String value1, String value2) {
            addCriterion("supplierTel between", value1, value2, "supplierTel");
            return (Criteria) this;
        }

        public Criteria andSupplierTelNotBetween(String value1, String value2) {
            addCriterion("supplierTel not between", value1, value2, "supplierTel");
            return (Criteria) this;
        }

        public Criteria andSupplierNamIsNull() {
            addCriterion("supplierNam is null");
            return (Criteria) this;
        }

        public Criteria andSupplierNamIsNotNull() {
            addCriterion("supplierNam is not null");
            return (Criteria) this;
        }

        public Criteria andSupplierNamEqualTo(String value) {
            addCriterion("supplierNam =", value, "supplierNam");
            return (Criteria) this;
        }

        public Criteria andSupplierNamNotEqualTo(String value) {
            addCriterion("supplierNam <>", value, "supplierNam");
            return (Criteria) this;
        }

        public Criteria andSupplierNamGreaterThan(String value) {
            addCriterion("supplierNam >", value, "supplierNam");
            return (Criteria) this;
        }

        public Criteria andSupplierNamGreaterThanOrEqualTo(String value) {
            addCriterion("supplierNam >=", value, "supplierNam");
            return (Criteria) this;
        }

        public Criteria andSupplierNamLessThan(String value) {
            addCriterion("supplierNam <", value, "supplierNam");
            return (Criteria) this;
        }

        public Criteria andSupplierNamLessThanOrEqualTo(String value) {
            addCriterion("supplierNam <=", value, "supplierNam");
            return (Criteria) this;
        }

        public Criteria andSupplierNamLike(String value) {
            addCriterion("supplierNam like", value, "supplierNam");
            return (Criteria) this;
        }

        public Criteria andSupplierNamNotLike(String value) {
            addCriterion("supplierNam not like", value, "supplierNam");
            return (Criteria) this;
        }

        public Criteria andSupplierNamIn(List<String> values) {
            addCriterion("supplierNam in", values, "supplierNam");
            return (Criteria) this;
        }

        public Criteria andSupplierNamNotIn(List<String> values) {
            addCriterion("supplierNam not in", values, "supplierNam");
            return (Criteria) this;
        }

        public Criteria andSupplierNamBetween(String value1, String value2) {
            addCriterion("supplierNam between", value1, value2, "supplierNam");
            return (Criteria) this;
        }

        public Criteria andSupplierNamNotBetween(String value1, String value2) {
            addCriterion("supplierNam not between", value1, value2, "supplierNam");
            return (Criteria) this;
        }

        public Criteria andSpStatusIsNull() {
            addCriterion("spStatus is null");
            return (Criteria) this;
        }

        public Criteria andSpStatusIsNotNull() {
            addCriterion("spStatus is not null");
            return (Criteria) this;
        }

        public Criteria andSpStatusEqualTo(Byte value) {
            addCriterion("spStatus =", value, "spStatus");
            return (Criteria) this;
        }

        public Criteria andSpStatusNotEqualTo(Byte value) {
            addCriterion("spStatus <>", value, "spStatus");
            return (Criteria) this;
        }

        public Criteria andSpStatusGreaterThan(Byte value) {
            addCriterion("spStatus >", value, "spStatus");
            return (Criteria) this;
        }

        public Criteria andSpStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("spStatus >=", value, "spStatus");
            return (Criteria) this;
        }

        public Criteria andSpStatusLessThan(Byte value) {
            addCriterion("spStatus <", value, "spStatus");
            return (Criteria) this;
        }

        public Criteria andSpStatusLessThanOrEqualTo(Byte value) {
            addCriterion("spStatus <=", value, "spStatus");
            return (Criteria) this;
        }

        public Criteria andSpStatusIn(List<Byte> values) {
            addCriterion("spStatus in", values, "spStatus");
            return (Criteria) this;
        }

        public Criteria andSpStatusNotIn(List<Byte> values) {
            addCriterion("spStatus not in", values, "spStatus");
            return (Criteria) this;
        }

        public Criteria andSpStatusBetween(Byte value1, Byte value2) {
            addCriterion("spStatus between", value1, value2, "spStatus");
            return (Criteria) this;
        }

        public Criteria andSpStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("spStatus not between", value1, value2, "spStatus");
            return (Criteria) this;
        }

        public Criteria andSpRemarkIsNull() {
            addCriterion("spRemark is null");
            return (Criteria) this;
        }

        public Criteria andSpRemarkIsNotNull() {
            addCriterion("spRemark is not null");
            return (Criteria) this;
        }

        public Criteria andSpRemarkEqualTo(String value) {
            addCriterion("spRemark =", value, "spRemark");
            return (Criteria) this;
        }

        public Criteria andSpRemarkNotEqualTo(String value) {
            addCriterion("spRemark <>", value, "spRemark");
            return (Criteria) this;
        }

        public Criteria andSpRemarkGreaterThan(String value) {
            addCriterion("spRemark >", value, "spRemark");
            return (Criteria) this;
        }

        public Criteria andSpRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("spRemark >=", value, "spRemark");
            return (Criteria) this;
        }

        public Criteria andSpRemarkLessThan(String value) {
            addCriterion("spRemark <", value, "spRemark");
            return (Criteria) this;
        }

        public Criteria andSpRemarkLessThanOrEqualTo(String value) {
            addCriterion("spRemark <=", value, "spRemark");
            return (Criteria) this;
        }

        public Criteria andSpRemarkLike(String value) {
            addCriterion("spRemark like", value, "spRemark");
            return (Criteria) this;
        }

        public Criteria andSpRemarkNotLike(String value) {
            addCriterion("spRemark not like", value, "spRemark");
            return (Criteria) this;
        }

        public Criteria andSpRemarkIn(List<String> values) {
            addCriterion("spRemark in", values, "spRemark");
            return (Criteria) this;
        }

        public Criteria andSpRemarkNotIn(List<String> values) {
            addCriterion("spRemark not in", values, "spRemark");
            return (Criteria) this;
        }

        public Criteria andSpRemarkBetween(String value1, String value2) {
            addCriterion("spRemark between", value1, value2, "spRemark");
            return (Criteria) this;
        }

        public Criteria andSpRemarkNotBetween(String value1, String value2) {
            addCriterion("spRemark not between", value1, value2, "spRemark");
            return (Criteria) this;
        }

        public Criteria andSpCheckTimeIsNull() {
            addCriterion("spCheckTime is null");
            return (Criteria) this;
        }

        public Criteria andSpCheckTimeIsNotNull() {
            addCriterion("spCheckTime is not null");
            return (Criteria) this;
        }

        public Criteria andSpCheckTimeEqualTo(Date value) {
            addCriterion("spCheckTime =", value, "spCheckTime");
            return (Criteria) this;
        }

        public Criteria andSpCheckTimeNotEqualTo(Date value) {
            addCriterion("spCheckTime <>", value, "spCheckTime");
            return (Criteria) this;
        }

        public Criteria andSpCheckTimeGreaterThan(Date value) {
            addCriterion("spCheckTime >", value, "spCheckTime");
            return (Criteria) this;
        }

        public Criteria andSpCheckTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("spCheckTime >=", value, "spCheckTime");
            return (Criteria) this;
        }

        public Criteria andSpCheckTimeLessThan(Date value) {
            addCriterion("spCheckTime <", value, "spCheckTime");
            return (Criteria) this;
        }

        public Criteria andSpCheckTimeLessThanOrEqualTo(Date value) {
            addCriterion("spCheckTime <=", value, "spCheckTime");
            return (Criteria) this;
        }

        public Criteria andSpCheckTimeIn(List<Date> values) {
            addCriterion("spCheckTime in", values, "spCheckTime");
            return (Criteria) this;
        }

        public Criteria andSpCheckTimeNotIn(List<Date> values) {
            addCriterion("spCheckTime not in", values, "spCheckTime");
            return (Criteria) this;
        }

        public Criteria andSpCheckTimeBetween(Date value1, Date value2) {
            addCriterion("spCheckTime between", value1, value2, "spCheckTime");
            return (Criteria) this;
        }

        public Criteria andSpCheckTimeNotBetween(Date value1, Date value2) {
            addCriterion("spCheckTime not between", value1, value2, "spCheckTime");
            return (Criteria) this;
        }

        public Criteria andWarningStatusIsNull() {
            addCriterion("warningStatus is null");
            return (Criteria) this;
        }

        public Criteria andWarningStatusIsNotNull() {
            addCriterion("warningStatus is not null");
            return (Criteria) this;
        }

        public Criteria andWarningStatusEqualTo(Byte value) {
            addCriterion("warningStatus =", value, "warningStatus");
            return (Criteria) this;
        }

        public Criteria andWarningStatusNotEqualTo(Byte value) {
            addCriterion("warningStatus <>", value, "warningStatus");
            return (Criteria) this;
        }

        public Criteria andWarningStatusGreaterThan(Byte value) {
            addCriterion("warningStatus >", value, "warningStatus");
            return (Criteria) this;
        }

        public Criteria andWarningStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("warningStatus >=", value, "warningStatus");
            return (Criteria) this;
        }

        public Criteria andWarningStatusLessThan(Byte value) {
            addCriterion("warningStatus <", value, "warningStatus");
            return (Criteria) this;
        }

        public Criteria andWarningStatusLessThanOrEqualTo(Byte value) {
            addCriterion("warningStatus <=", value, "warningStatus");
            return (Criteria) this;
        }

        public Criteria andWarningStatusIn(List<Byte> values) {
            addCriterion("warningStatus in", values, "warningStatus");
            return (Criteria) this;
        }

        public Criteria andWarningStatusNotIn(List<Byte> values) {
            addCriterion("warningStatus not in", values, "warningStatus");
            return (Criteria) this;
        }

        public Criteria andWarningStatusBetween(Byte value1, Byte value2) {
            addCriterion("warningStatus between", value1, value2, "warningStatus");
            return (Criteria) this;
        }

        public Criteria andWarningStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("warningStatus not between", value1, value2, "warningStatus");
            return (Criteria) this;
        }

        public Criteria andIsOutStockIsNull() {
            addCriterion("isOutStock is null");
            return (Criteria) this;
        }

        public Criteria andIsOutStockIsNotNull() {
            addCriterion("isOutStock is not null");
            return (Criteria) this;
        }

        public Criteria andIsOutStockEqualTo(Boolean value) {
            addCriterion("isOutStock =", value, "isOutStock");
            return (Criteria) this;
        }

        public Criteria andIsOutStockNotEqualTo(Boolean value) {
            addCriterion("isOutStock <>", value, "isOutStock");
            return (Criteria) this;
        }

        public Criteria andIsOutStockGreaterThan(Boolean value) {
            addCriterion("isOutStock >", value, "isOutStock");
            return (Criteria) this;
        }

        public Criteria andIsOutStockGreaterThanOrEqualTo(Boolean value) {
            addCriterion("isOutStock >=", value, "isOutStock");
            return (Criteria) this;
        }

        public Criteria andIsOutStockLessThan(Boolean value) {
            addCriterion("isOutStock <", value, "isOutStock");
            return (Criteria) this;
        }

        public Criteria andIsOutStockLessThanOrEqualTo(Boolean value) {
            addCriterion("isOutStock <=", value, "isOutStock");
            return (Criteria) this;
        }

        public Criteria andIsOutStockIn(List<Boolean> values) {
            addCriterion("isOutStock in", values, "isOutStock");
            return (Criteria) this;
        }

        public Criteria andIsOutStockNotIn(List<Boolean> values) {
            addCriterion("isOutStock not in", values, "isOutStock");
            return (Criteria) this;
        }

        public Criteria andIsOutStockBetween(Boolean value1, Boolean value2) {
            addCriterion("isOutStock between", value1, value2, "isOutStock");
            return (Criteria) this;
        }

        public Criteria andIsOutStockNotBetween(Boolean value1, Boolean value2) {
            addCriterion("isOutStock not between", value1, value2, "isOutStock");
            return (Criteria) this;
        }

        public Criteria andLogisticsStatusIsNull() {
            addCriterion("logisticsStatus is null");
            return (Criteria) this;
        }

        public Criteria andLogisticsStatusIsNotNull() {
            addCriterion("logisticsStatus is not null");
            return (Criteria) this;
        }

        public Criteria andLogisticsStatusEqualTo(Byte value) {
            addCriterion("logisticsStatus =", value, "logisticsStatus");
            return (Criteria) this;
        }

        public Criteria andLogisticsStatusNotEqualTo(Byte value) {
            addCriterion("logisticsStatus <>", value, "logisticsStatus");
            return (Criteria) this;
        }

        public Criteria andLogisticsStatusGreaterThan(Byte value) {
            addCriterion("logisticsStatus >", value, "logisticsStatus");
            return (Criteria) this;
        }

        public Criteria andLogisticsStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("logisticsStatus >=", value, "logisticsStatus");
            return (Criteria) this;
        }

        public Criteria andLogisticsStatusLessThan(Byte value) {
            addCriterion("logisticsStatus <", value, "logisticsStatus");
            return (Criteria) this;
        }

        public Criteria andLogisticsStatusLessThanOrEqualTo(Byte value) {
            addCriterion("logisticsStatus <=", value, "logisticsStatus");
            return (Criteria) this;
        }

        public Criteria andLogisticsStatusIn(List<Byte> values) {
            addCriterion("logisticsStatus in", values, "logisticsStatus");
            return (Criteria) this;
        }

        public Criteria andLogisticsStatusNotIn(List<Byte> values) {
            addCriterion("logisticsStatus not in", values, "logisticsStatus");
            return (Criteria) this;
        }

        public Criteria andLogisticsStatusBetween(Byte value1, Byte value2) {
            addCriterion("logisticsStatus between", value1, value2, "logisticsStatus");
            return (Criteria) this;
        }

        public Criteria andLogisticsStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("logisticsStatus not between", value1, value2, "logisticsStatus");
            return (Criteria) this;
        }

        public Criteria andWhSendTimeIsNull() {
            addCriterion("whSendTime is null");
            return (Criteria) this;
        }

        public Criteria andWhSendTimeIsNotNull() {
            addCriterion("whSendTime is not null");
            return (Criteria) this;
        }

        public Criteria andWhSendTimeEqualTo(Date value) {
            addCriterion("whSendTime =", value, "whSendTime");
            return (Criteria) this;
        }

        public Criteria andWhSendTimeNotEqualTo(Date value) {
            addCriterion("whSendTime <>", value, "whSendTime");
            return (Criteria) this;
        }

        public Criteria andWhSendTimeGreaterThan(Date value) {
            addCriterion("whSendTime >", value, "whSendTime");
            return (Criteria) this;
        }

        public Criteria andWhSendTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("whSendTime >=", value, "whSendTime");
            return (Criteria) this;
        }

        public Criteria andWhSendTimeLessThan(Date value) {
            addCriterion("whSendTime <", value, "whSendTime");
            return (Criteria) this;
        }

        public Criteria andWhSendTimeLessThanOrEqualTo(Date value) {
            addCriterion("whSendTime <=", value, "whSendTime");
            return (Criteria) this;
        }

        public Criteria andWhSendTimeIn(List<Date> values) {
            addCriterion("whSendTime in", values, "whSendTime");
            return (Criteria) this;
        }

        public Criteria andWhSendTimeNotIn(List<Date> values) {
            addCriterion("whSendTime not in", values, "whSendTime");
            return (Criteria) this;
        }

        public Criteria andWhSendTimeBetween(Date value1, Date value2) {
            addCriterion("whSendTime between", value1, value2, "whSendTime");
            return (Criteria) this;
        }

        public Criteria andWhSendTimeNotBetween(Date value1, Date value2) {
            addCriterion("whSendTime not between", value1, value2, "whSendTime");
            return (Criteria) this;
        }

        public Criteria andWhAckTimeIsNull() {
            addCriterion("whAckTime is null");
            return (Criteria) this;
        }

        public Criteria andWhAckTimeIsNotNull() {
            addCriterion("whAckTime is not null");
            return (Criteria) this;
        }

        public Criteria andWhAckTimeEqualTo(Date value) {
            addCriterion("whAckTime =", value, "whAckTime");
            return (Criteria) this;
        }

        public Criteria andWhAckTimeNotEqualTo(Date value) {
            addCriterion("whAckTime <>", value, "whAckTime");
            return (Criteria) this;
        }

        public Criteria andWhAckTimeGreaterThan(Date value) {
            addCriterion("whAckTime >", value, "whAckTime");
            return (Criteria) this;
        }

        public Criteria andWhAckTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("whAckTime >=", value, "whAckTime");
            return (Criteria) this;
        }

        public Criteria andWhAckTimeLessThan(Date value) {
            addCriterion("whAckTime <", value, "whAckTime");
            return (Criteria) this;
        }

        public Criteria andWhAckTimeLessThanOrEqualTo(Date value) {
            addCriterion("whAckTime <=", value, "whAckTime");
            return (Criteria) this;
        }

        public Criteria andWhAckTimeIn(List<Date> values) {
            addCriterion("whAckTime in", values, "whAckTime");
            return (Criteria) this;
        }

        public Criteria andWhAckTimeNotIn(List<Date> values) {
            addCriterion("whAckTime not in", values, "whAckTime");
            return (Criteria) this;
        }

        public Criteria andWhAckTimeBetween(Date value1, Date value2) {
            addCriterion("whAckTime between", value1, value2, "whAckTime");
            return (Criteria) this;
        }

        public Criteria andWhAckTimeNotBetween(Date value1, Date value2) {
            addCriterion("whAckTime not between", value1, value2, "whAckTime");
            return (Criteria) this;
        }

        public Criteria andPrintTimeIsNull() {
            addCriterion("printTime is null");
            return (Criteria) this;
        }

        public Criteria andPrintTimeIsNotNull() {
            addCriterion("printTime is not null");
            return (Criteria) this;
        }

        public Criteria andPrintTimeEqualTo(Date value) {
            addCriterion("printTime =", value, "printTime");
            return (Criteria) this;
        }

        public Criteria andPrintTimeNotEqualTo(Date value) {
            addCriterion("printTime <>", value, "printTime");
            return (Criteria) this;
        }

        public Criteria andPrintTimeGreaterThan(Date value) {
            addCriterion("printTime >", value, "printTime");
            return (Criteria) this;
        }

        public Criteria andPrintTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("printTime >=", value, "printTime");
            return (Criteria) this;
        }

        public Criteria andPrintTimeLessThan(Date value) {
            addCriterion("printTime <", value, "printTime");
            return (Criteria) this;
        }

        public Criteria andPrintTimeLessThanOrEqualTo(Date value) {
            addCriterion("printTime <=", value, "printTime");
            return (Criteria) this;
        }

        public Criteria andPrintTimeIn(List<Date> values) {
            addCriterion("printTime in", values, "printTime");
            return (Criteria) this;
        }

        public Criteria andPrintTimeNotIn(List<Date> values) {
            addCriterion("printTime not in", values, "printTime");
            return (Criteria) this;
        }

        public Criteria andPrintTimeBetween(Date value1, Date value2) {
            addCriterion("printTime between", value1, value2, "printTime");
            return (Criteria) this;
        }

        public Criteria andPrintTimeNotBetween(Date value1, Date value2) {
            addCriterion("printTime not between", value1, value2, "printTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeIsNull() {
            addCriterion("deliveryTime is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeIsNotNull() {
            addCriterion("deliveryTime is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeEqualTo(Date value) {
            addCriterion("deliveryTime =", value, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeNotEqualTo(Date value) {
            addCriterion("deliveryTime <>", value, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeGreaterThan(Date value) {
            addCriterion("deliveryTime >", value, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("deliveryTime >=", value, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeLessThan(Date value) {
            addCriterion("deliveryTime <", value, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeLessThanOrEqualTo(Date value) {
            addCriterion("deliveryTime <=", value, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeIn(List<Date> values) {
            addCriterion("deliveryTime in", values, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeNotIn(List<Date> values) {
            addCriterion("deliveryTime not in", values, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeBetween(Date value1, Date value2) {
            addCriterion("deliveryTime between", value1, value2, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeNotBetween(Date value1, Date value2) {
            addCriterion("deliveryTime not between", value1, value2, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andGetOrderTimeIsNull() {
            addCriterion("getOrderTime is null");
            return (Criteria) this;
        }

        public Criteria andGetOrderTimeIsNotNull() {
            addCriterion("getOrderTime is not null");
            return (Criteria) this;
        }

        public Criteria andGetOrderTimeEqualTo(Date value) {
            addCriterion("getOrderTime =", value, "getOrderTime");
            return (Criteria) this;
        }

        public Criteria andGetOrderTimeNotEqualTo(Date value) {
            addCriterion("getOrderTime <>", value, "getOrderTime");
            return (Criteria) this;
        }

        public Criteria andGetOrderTimeGreaterThan(Date value) {
            addCriterion("getOrderTime >", value, "getOrderTime");
            return (Criteria) this;
        }

        public Criteria andGetOrderTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("getOrderTime >=", value, "getOrderTime");
            return (Criteria) this;
        }

        public Criteria andGetOrderTimeLessThan(Date value) {
            addCriterion("getOrderTime <", value, "getOrderTime");
            return (Criteria) this;
        }

        public Criteria andGetOrderTimeLessThanOrEqualTo(Date value) {
            addCriterion("getOrderTime <=", value, "getOrderTime");
            return (Criteria) this;
        }

        public Criteria andGetOrderTimeIn(List<Date> values) {
            addCriterion("getOrderTime in", values, "getOrderTime");
            return (Criteria) this;
        }

        public Criteria andGetOrderTimeNotIn(List<Date> values) {
            addCriterion("getOrderTime not in", values, "getOrderTime");
            return (Criteria) this;
        }

        public Criteria andGetOrderTimeBetween(Date value1, Date value2) {
            addCriterion("getOrderTime between", value1, value2, "getOrderTime");
            return (Criteria) this;
        }

        public Criteria andGetOrderTimeNotBetween(Date value1, Date value2) {
            addCriterion("getOrderTime not between", value1, value2, "getOrderTime");
            return (Criteria) this;
        }

        public Criteria andAckTimeIsNull() {
            addCriterion("ackTime is null");
            return (Criteria) this;
        }

        public Criteria andAckTimeIsNotNull() {
            addCriterion("ackTime is not null");
            return (Criteria) this;
        }

        public Criteria andAckTimeEqualTo(Date value) {
            addCriterion("ackTime =", value, "ackTime");
            return (Criteria) this;
        }

        public Criteria andAckTimeNotEqualTo(Date value) {
            addCriterion("ackTime <>", value, "ackTime");
            return (Criteria) this;
        }

        public Criteria andAckTimeGreaterThan(Date value) {
            addCriterion("ackTime >", value, "ackTime");
            return (Criteria) this;
        }

        public Criteria andAckTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("ackTime >=", value, "ackTime");
            return (Criteria) this;
        }

        public Criteria andAckTimeLessThan(Date value) {
            addCriterion("ackTime <", value, "ackTime");
            return (Criteria) this;
        }

        public Criteria andAckTimeLessThanOrEqualTo(Date value) {
            addCriterion("ackTime <=", value, "ackTime");
            return (Criteria) this;
        }

        public Criteria andAckTimeIn(List<Date> values) {
            addCriterion("ackTime in", values, "ackTime");
            return (Criteria) this;
        }

        public Criteria andAckTimeNotIn(List<Date> values) {
            addCriterion("ackTime not in", values, "ackTime");
            return (Criteria) this;
        }

        public Criteria andAckTimeBetween(Date value1, Date value2) {
            addCriterion("ackTime between", value1, value2, "ackTime");
            return (Criteria) this;
        }

        public Criteria andAckTimeNotBetween(Date value1, Date value2) {
            addCriterion("ackTime not between", value1, value2, "ackTime");
            return (Criteria) this;
        }

        public Criteria andRevokeTimeIsNull() {
            addCriterion("revokeTime is null");
            return (Criteria) this;
        }

        public Criteria andRevokeTimeIsNotNull() {
            addCriterion("revokeTime is not null");
            return (Criteria) this;
        }

        public Criteria andRevokeTimeEqualTo(Date value) {
            addCriterion("revokeTime =", value, "revokeTime");
            return (Criteria) this;
        }

        public Criteria andRevokeTimeNotEqualTo(Date value) {
            addCriterion("revokeTime <>", value, "revokeTime");
            return (Criteria) this;
        }

        public Criteria andRevokeTimeGreaterThan(Date value) {
            addCriterion("revokeTime >", value, "revokeTime");
            return (Criteria) this;
        }

        public Criteria andRevokeTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("revokeTime >=", value, "revokeTime");
            return (Criteria) this;
        }

        public Criteria andRevokeTimeLessThan(Date value) {
            addCriterion("revokeTime <", value, "revokeTime");
            return (Criteria) this;
        }

        public Criteria andRevokeTimeLessThanOrEqualTo(Date value) {
            addCriterion("revokeTime <=", value, "revokeTime");
            return (Criteria) this;
        }

        public Criteria andRevokeTimeIn(List<Date> values) {
            addCriterion("revokeTime in", values, "revokeTime");
            return (Criteria) this;
        }

        public Criteria andRevokeTimeNotIn(List<Date> values) {
            addCriterion("revokeTime not in", values, "revokeTime");
            return (Criteria) this;
        }

        public Criteria andRevokeTimeBetween(Date value1, Date value2) {
            addCriterion("revokeTime between", value1, value2, "revokeTime");
            return (Criteria) this;
        }

        public Criteria andRevokeTimeNotBetween(Date value1, Date value2) {
            addCriterion("revokeTime not between", value1, value2, "revokeTime");
            return (Criteria) this;
        }

        public Criteria andAckCodeIsNull() {
            addCriterion("ackCode is null");
            return (Criteria) this;
        }

        public Criteria andAckCodeIsNotNull() {
            addCriterion("ackCode is not null");
            return (Criteria) this;
        }

        public Criteria andAckCodeEqualTo(Short value) {
            addCriterion("ackCode =", value, "ackCode");
            return (Criteria) this;
        }

        public Criteria andAckCodeNotEqualTo(Short value) {
            addCriterion("ackCode <>", value, "ackCode");
            return (Criteria) this;
        }

        public Criteria andAckCodeGreaterThan(Short value) {
            addCriterion("ackCode >", value, "ackCode");
            return (Criteria) this;
        }

        public Criteria andAckCodeGreaterThanOrEqualTo(Short value) {
            addCriterion("ackCode >=", value, "ackCode");
            return (Criteria) this;
        }

        public Criteria andAckCodeLessThan(Short value) {
            addCriterion("ackCode <", value, "ackCode");
            return (Criteria) this;
        }

        public Criteria andAckCodeLessThanOrEqualTo(Short value) {
            addCriterion("ackCode <=", value, "ackCode");
            return (Criteria) this;
        }

        public Criteria andAckCodeIn(List<Short> values) {
            addCriterion("ackCode in", values, "ackCode");
            return (Criteria) this;
        }

        public Criteria andAckCodeNotIn(List<Short> values) {
            addCriterion("ackCode not in", values, "ackCode");
            return (Criteria) this;
        }

        public Criteria andAckCodeBetween(Short value1, Short value2) {
            addCriterion("ackCode between", value1, value2, "ackCode");
            return (Criteria) this;
        }

        public Criteria andAckCodeNotBetween(Short value1, Short value2) {
            addCriterion("ackCode not between", value1, value2, "ackCode");
            return (Criteria) this;
        }

        public Criteria andIsStarIsNull() {
            addCriterion("isStar is null");
            return (Criteria) this;
        }

        public Criteria andIsStarIsNotNull() {
            addCriterion("isStar is not null");
            return (Criteria) this;
        }

        public Criteria andIsStarEqualTo(Byte value) {
            addCriterion("isStar =", value, "isStar");
            return (Criteria) this;
        }

        public Criteria andIsStarNotEqualTo(Byte value) {
            addCriterion("isStar <>", value, "isStar");
            return (Criteria) this;
        }

        public Criteria andIsStarGreaterThan(Byte value) {
            addCriterion("isStar >", value, "isStar");
            return (Criteria) this;
        }

        public Criteria andIsStarGreaterThanOrEqualTo(Byte value) {
            addCriterion("isStar >=", value, "isStar");
            return (Criteria) this;
        }

        public Criteria andIsStarLessThan(Byte value) {
            addCriterion("isStar <", value, "isStar");
            return (Criteria) this;
        }

        public Criteria andIsStarLessThanOrEqualTo(Byte value) {
            addCriterion("isStar <=", value, "isStar");
            return (Criteria) this;
        }

        public Criteria andIsStarIn(List<Byte> values) {
            addCriterion("isStar in", values, "isStar");
            return (Criteria) this;
        }

        public Criteria andIsStarNotIn(List<Byte> values) {
            addCriterion("isStar not in", values, "isStar");
            return (Criteria) this;
        }

        public Criteria andIsStarBetween(Byte value1, Byte value2) {
            addCriterion("isStar between", value1, value2, "isStar");
            return (Criteria) this;
        }

        public Criteria andIsStarNotBetween(Byte value1, Byte value2) {
            addCriterion("isStar not between", value1, value2, "isStar");
            return (Criteria) this;
        }

        public Criteria andSendDateTypeIsNull() {
            addCriterion("sendDateType is null");
            return (Criteria) this;
        }

        public Criteria andSendDateTypeIsNotNull() {
            addCriterion("sendDateType is not null");
            return (Criteria) this;
        }

        public Criteria andSendDateTypeEqualTo(Byte value) {
            addCriterion("sendDateType =", value, "sendDateType");
            return (Criteria) this;
        }

        public Criteria andSendDateTypeNotEqualTo(Byte value) {
            addCriterion("sendDateType <>", value, "sendDateType");
            return (Criteria) this;
        }

        public Criteria andSendDateTypeGreaterThan(Byte value) {
            addCriterion("sendDateType >", value, "sendDateType");
            return (Criteria) this;
        }

        public Criteria andSendDateTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("sendDateType >=", value, "sendDateType");
            return (Criteria) this;
        }

        public Criteria andSendDateTypeLessThan(Byte value) {
            addCriterion("sendDateType <", value, "sendDateType");
            return (Criteria) this;
        }

        public Criteria andSendDateTypeLessThanOrEqualTo(Byte value) {
            addCriterion("sendDateType <=", value, "sendDateType");
            return (Criteria) this;
        }

        public Criteria andSendDateTypeIn(List<Byte> values) {
            addCriterion("sendDateType in", values, "sendDateType");
            return (Criteria) this;
        }

        public Criteria andSendDateTypeNotIn(List<Byte> values) {
            addCriterion("sendDateType not in", values, "sendDateType");
            return (Criteria) this;
        }

        public Criteria andSendDateTypeBetween(Byte value1, Byte value2) {
            addCriterion("sendDateType between", value1, value2, "sendDateType");
            return (Criteria) this;
        }

        public Criteria andSendDateTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("sendDateType not between", value1, value2, "sendDateType");
            return (Criteria) this;
        }

        public Criteria andSendDateIsNull() {
            addCriterion("sendDate is null");
            return (Criteria) this;
        }

        public Criteria andSendDateIsNotNull() {
            addCriterion("sendDate is not null");
            return (Criteria) this;
        }

        public Criteria andSendDateEqualTo(Date value) {
            addCriterionForJDBCDate("sendDate =", value, "sendDate");
            return (Criteria) this;
        }

        public Criteria andSendDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("sendDate <>", value, "sendDate");
            return (Criteria) this;
        }

        public Criteria andSendDateGreaterThan(Date value) {
            addCriterionForJDBCDate("sendDate >", value, "sendDate");
            return (Criteria) this;
        }

        public Criteria andSendDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("sendDate >=", value, "sendDate");
            return (Criteria) this;
        }

        public Criteria andSendDateLessThan(Date value) {
            addCriterionForJDBCDate("sendDate <", value, "sendDate");
            return (Criteria) this;
        }

        public Criteria andSendDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("sendDate <=", value, "sendDate");
            return (Criteria) this;
        }

        public Criteria andSendDateIn(List<Date> values) {
            addCriterionForJDBCDate("sendDate in", values, "sendDate");
            return (Criteria) this;
        }

        public Criteria andSendDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("sendDate not in", values, "sendDate");
            return (Criteria) this;
        }

        public Criteria andSendDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("sendDate between", value1, value2, "sendDate");
            return (Criteria) this;
        }

        public Criteria andSendDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("sendDate not between", value1, value2, "sendDate");
            return (Criteria) this;
        }

        public Criteria andFistTimeIsNull() {
            addCriterion("fistTime is null");
            return (Criteria) this;
        }

        public Criteria andFistTimeIsNotNull() {
            addCriterion("fistTime is not null");
            return (Criteria) this;
        }

        public Criteria andFistTimeEqualTo(Date value) {
            addCriterion("fistTime =", value, "fistTime");
            return (Criteria) this;
        }

        public Criteria andFistTimeNotEqualTo(Date value) {
            addCriterion("fistTime <>", value, "fistTime");
            return (Criteria) this;
        }

        public Criteria andFistTimeGreaterThan(Date value) {
            addCriterion("fistTime >", value, "fistTime");
            return (Criteria) this;
        }

        public Criteria andFistTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("fistTime >=", value, "fistTime");
            return (Criteria) this;
        }

        public Criteria andFistTimeLessThan(Date value) {
            addCriterion("fistTime <", value, "fistTime");
            return (Criteria) this;
        }

        public Criteria andFistTimeLessThanOrEqualTo(Date value) {
            addCriterion("fistTime <=", value, "fistTime");
            return (Criteria) this;
        }

        public Criteria andFistTimeIn(List<Date> values) {
            addCriterion("fistTime in", values, "fistTime");
            return (Criteria) this;
        }

        public Criteria andFistTimeNotIn(List<Date> values) {
            addCriterion("fistTime not in", values, "fistTime");
            return (Criteria) this;
        }

        public Criteria andFistTimeBetween(Date value1, Date value2) {
            addCriterion("fistTime between", value1, value2, "fistTime");
            return (Criteria) this;
        }

        public Criteria andFistTimeNotBetween(Date value1, Date value2) {
            addCriterion("fistTime not between", value1, value2, "fistTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("endTime is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("endTime is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(Date value) {
            addCriterion("endTime =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(Date value) {
            addCriterion("endTime <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(Date value) {
            addCriterion("endTime >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("endTime >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(Date value) {
            addCriterion("endTime <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("endTime <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<Date> values) {
            addCriterion("endTime in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<Date> values) {
            addCriterion("endTime not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(Date value1, Date value2) {
            addCriterion("endTime between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("endTime not between", value1, value2, "endTime");
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

        public Criteria andQrcodeurlIsNull() {
            addCriterion("qrcodeurl is null");
            return (Criteria) this;
        }

        public Criteria andQrcodeurlIsNotNull() {
            addCriterion("qrcodeurl is not null");
            return (Criteria) this;
        }

        public Criteria andQrcodeurlEqualTo(String value) {
            addCriterion("qrcodeurl =", value, "qrcodeurl");
            return (Criteria) this;
        }

        public Criteria andQrcodeurlNotEqualTo(String value) {
            addCriterion("qrcodeurl <>", value, "qrcodeurl");
            return (Criteria) this;
        }

        public Criteria andQrcodeurlGreaterThan(String value) {
            addCriterion("qrcodeurl >", value, "qrcodeurl");
            return (Criteria) this;
        }

        public Criteria andQrcodeurlGreaterThanOrEqualTo(String value) {
            addCriterion("qrcodeurl >=", value, "qrcodeurl");
            return (Criteria) this;
        }

        public Criteria andQrcodeurlLessThan(String value) {
            addCriterion("qrcodeurl <", value, "qrcodeurl");
            return (Criteria) this;
        }

        public Criteria andQrcodeurlLessThanOrEqualTo(String value) {
            addCriterion("qrcodeurl <=", value, "qrcodeurl");
            return (Criteria) this;
        }

        public Criteria andQrcodeurlLike(String value) {
            addCriterion("qrcodeurl like", value, "qrcodeurl");
            return (Criteria) this;
        }

        public Criteria andQrcodeurlNotLike(String value) {
            addCriterion("qrcodeurl not like", value, "qrcodeurl");
            return (Criteria) this;
        }

        public Criteria andQrcodeurlIn(List<String> values) {
            addCriterion("qrcodeurl in", values, "qrcodeurl");
            return (Criteria) this;
        }

        public Criteria andQrcodeurlNotIn(List<String> values) {
            addCriterion("qrcodeurl not in", values, "qrcodeurl");
            return (Criteria) this;
        }

        public Criteria andQrcodeurlBetween(String value1, String value2) {
            addCriterion("qrcodeurl between", value1, value2, "qrcodeurl");
            return (Criteria) this;
        }

        public Criteria andQrcodeurlNotBetween(String value1, String value2) {
            addCriterion("qrcodeurl not between", value1, value2, "qrcodeurl");
            return (Criteria) this;
        }

        public Criteria andIsRemindIsNull() {
            addCriterion("isRemind is null");
            return (Criteria) this;
        }

        public Criteria andIsRemindIsNotNull() {
            addCriterion("isRemind is not null");
            return (Criteria) this;
        }

        public Criteria andIsRemindEqualTo(Byte value) {
            addCriterion("isRemind =", value, "isRemind");
            return (Criteria) this;
        }

        public Criteria andIsRemindNotEqualTo(Byte value) {
            addCriterion("isRemind <>", value, "isRemind");
            return (Criteria) this;
        }

        public Criteria andIsRemindGreaterThan(Byte value) {
            addCriterion("isRemind >", value, "isRemind");
            return (Criteria) this;
        }

        public Criteria andIsRemindGreaterThanOrEqualTo(Byte value) {
            addCriterion("isRemind >=", value, "isRemind");
            return (Criteria) this;
        }

        public Criteria andIsRemindLessThan(Byte value) {
            addCriterion("isRemind <", value, "isRemind");
            return (Criteria) this;
        }

        public Criteria andIsRemindLessThanOrEqualTo(Byte value) {
            addCriterion("isRemind <=", value, "isRemind");
            return (Criteria) this;
        }

        public Criteria andIsRemindIn(List<Byte> values) {
            addCriterion("isRemind in", values, "isRemind");
            return (Criteria) this;
        }

        public Criteria andIsRemindNotIn(List<Byte> values) {
            addCriterion("isRemind not in", values, "isRemind");
            return (Criteria) this;
        }

        public Criteria andIsRemindBetween(Byte value1, Byte value2) {
            addCriterion("isRemind between", value1, value2, "isRemind");
            return (Criteria) this;
        }

        public Criteria andIsRemindNotBetween(Byte value1, Byte value2) {
            addCriterion("isRemind not between", value1, value2, "isRemind");
            return (Criteria) this;
        }

        public Criteria andZmaoliIsNull() {
            addCriterion("zmaoli is null");
            return (Criteria) this;
        }

        public Criteria andZmaoliIsNotNull() {
            addCriterion("zmaoli is not null");
            return (Criteria) this;
        }

        public Criteria andZmaoliEqualTo(BigDecimal value) {
            addCriterion("zmaoli =", value, "zmaoli");
            return (Criteria) this;
        }

        public Criteria andZmaoliNotEqualTo(BigDecimal value) {
            addCriterion("zmaoli <>", value, "zmaoli");
            return (Criteria) this;
        }

        public Criteria andZmaoliGreaterThan(BigDecimal value) {
            addCriterion("zmaoli >", value, "zmaoli");
            return (Criteria) this;
        }

        public Criteria andZmaoliGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("zmaoli >=", value, "zmaoli");
            return (Criteria) this;
        }

        public Criteria andZmaoliLessThan(BigDecimal value) {
            addCriterion("zmaoli <", value, "zmaoli");
            return (Criteria) this;
        }

        public Criteria andZmaoliLessThanOrEqualTo(BigDecimal value) {
            addCriterion("zmaoli <=", value, "zmaoli");
            return (Criteria) this;
        }

        public Criteria andZmaoliIn(List<BigDecimal> values) {
            addCriterion("zmaoli in", values, "zmaoli");
            return (Criteria) this;
        }

        public Criteria andZmaoliNotIn(List<BigDecimal> values) {
            addCriterion("zmaoli not in", values, "zmaoli");
            return (Criteria) this;
        }

        public Criteria andZmaoliBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("zmaoli between", value1, value2, "zmaoli");
            return (Criteria) this;
        }

        public Criteria andZmaoliNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("zmaoli not between", value1, value2, "zmaoli");
            return (Criteria) this;
        }

        public Criteria andZfeeIsNull() {
            addCriterion("zfee is null");
            return (Criteria) this;
        }

        public Criteria andZfeeIsNotNull() {
            addCriterion("zfee is not null");
            return (Criteria) this;
        }

        public Criteria andZfeeEqualTo(BigDecimal value) {
            addCriterion("zfee =", value, "zfee");
            return (Criteria) this;
        }

        public Criteria andZfeeNotEqualTo(BigDecimal value) {
            addCriterion("zfee <>", value, "zfee");
            return (Criteria) this;
        }

        public Criteria andZfeeGreaterThan(BigDecimal value) {
            addCriterion("zfee >", value, "zfee");
            return (Criteria) this;
        }

        public Criteria andZfeeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("zfee >=", value, "zfee");
            return (Criteria) this;
        }

        public Criteria andZfeeLessThan(BigDecimal value) {
            addCriterion("zfee <", value, "zfee");
            return (Criteria) this;
        }

        public Criteria andZfeeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("zfee <=", value, "zfee");
            return (Criteria) this;
        }

        public Criteria andZfeeIn(List<BigDecimal> values) {
            addCriterion("zfee in", values, "zfee");
            return (Criteria) this;
        }

        public Criteria andZfeeNotIn(List<BigDecimal> values) {
            addCriterion("zfee not in", values, "zfee");
            return (Criteria) this;
        }

        public Criteria andZfeeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("zfee between", value1, value2, "zfee");
            return (Criteria) this;
        }

        public Criteria andZfeeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("zfee not between", value1, value2, "zfee");
            return (Criteria) this;
        }

        public Criteria andAcIdIsNull() {
            addCriterion("acId is null");
            return (Criteria) this;
        }

        public Criteria andAcIdIsNotNull() {
            addCriterion("acId is not null");
            return (Criteria) this;
        }

        public Criteria andAcIdEqualTo(String value) {
            addCriterion("acId =", value, "acId");
            return (Criteria) this;
        }

        public Criteria andAcIdNotEqualTo(String value) {
            addCriterion("acId <>", value, "acId");
            return (Criteria) this;
        }

        public Criteria andAcIdGreaterThan(String value) {
            addCriterion("acId >", value, "acId");
            return (Criteria) this;
        }

        public Criteria andAcIdGreaterThanOrEqualTo(String value) {
            addCriterion("acId >=", value, "acId");
            return (Criteria) this;
        }

        public Criteria andAcIdLessThan(String value) {
            addCriterion("acId <", value, "acId");
            return (Criteria) this;
        }

        public Criteria andAcIdLessThanOrEqualTo(String value) {
            addCriterion("acId <=", value, "acId");
            return (Criteria) this;
        }

        public Criteria andAcIdLike(String value) {
            addCriterion("acId like", value, "acId");
            return (Criteria) this;
        }

        public Criteria andAcIdNotLike(String value) {
            addCriterion("acId not like", value, "acId");
            return (Criteria) this;
        }

        public Criteria andAcIdIn(List<String> values) {
            addCriterion("acId in", values, "acId");
            return (Criteria) this;
        }

        public Criteria andAcIdNotIn(List<String> values) {
            addCriterion("acId not in", values, "acId");
            return (Criteria) this;
        }

        public Criteria andAcIdBetween(String value1, String value2) {
            addCriterion("acId between", value1, value2, "acId");
            return (Criteria) this;
        }

        public Criteria andAcIdNotBetween(String value1, String value2) {
            addCriterion("acId not between", value1, value2, "acId");
            return (Criteria) this;
        }

        public Criteria andAcStatusIsNull() {
            addCriterion("acStatus is null");
            return (Criteria) this;
        }

        public Criteria andAcStatusIsNotNull() {
            addCriterion("acStatus is not null");
            return (Criteria) this;
        }

        public Criteria andAcStatusEqualTo(Integer value) {
            addCriterion("acStatus =", value, "acStatus");
            return (Criteria) this;
        }

        public Criteria andAcStatusNotEqualTo(Integer value) {
            addCriterion("acStatus <>", value, "acStatus");
            return (Criteria) this;
        }

        public Criteria andAcStatusGreaterThan(Integer value) {
            addCriterion("acStatus >", value, "acStatus");
            return (Criteria) this;
        }

        public Criteria andAcStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("acStatus >=", value, "acStatus");
            return (Criteria) this;
        }

        public Criteria andAcStatusLessThan(Integer value) {
            addCriterion("acStatus <", value, "acStatus");
            return (Criteria) this;
        }

        public Criteria andAcStatusLessThanOrEqualTo(Integer value) {
            addCriterion("acStatus <=", value, "acStatus");
            return (Criteria) this;
        }

        public Criteria andAcStatusIn(List<Integer> values) {
            addCriterion("acStatus in", values, "acStatus");
            return (Criteria) this;
        }

        public Criteria andAcStatusNotIn(List<Integer> values) {
            addCriterion("acStatus not in", values, "acStatus");
            return (Criteria) this;
        }

        public Criteria andAcStatusBetween(Integer value1, Integer value2) {
            addCriterion("acStatus between", value1, value2, "acStatus");
            return (Criteria) this;
        }

        public Criteria andAcStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("acStatus not between", value1, value2, "acStatus");
            return (Criteria) this;
        }

        public Criteria andAcRemarkIsNull() {
            addCriterion("acRemark is null");
            return (Criteria) this;
        }

        public Criteria andAcRemarkIsNotNull() {
            addCriterion("acRemark is not null");
            return (Criteria) this;
        }

        public Criteria andAcRemarkEqualTo(String value) {
            addCriterion("acRemark =", value, "acRemark");
            return (Criteria) this;
        }

        public Criteria andAcRemarkNotEqualTo(String value) {
            addCriterion("acRemark <>", value, "acRemark");
            return (Criteria) this;
        }

        public Criteria andAcRemarkGreaterThan(String value) {
            addCriterion("acRemark >", value, "acRemark");
            return (Criteria) this;
        }

        public Criteria andAcRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("acRemark >=", value, "acRemark");
            return (Criteria) this;
        }

        public Criteria andAcRemarkLessThan(String value) {
            addCriterion("acRemark <", value, "acRemark");
            return (Criteria) this;
        }

        public Criteria andAcRemarkLessThanOrEqualTo(String value) {
            addCriterion("acRemark <=", value, "acRemark");
            return (Criteria) this;
        }

        public Criteria andAcRemarkLike(String value) {
            addCriterion("acRemark like", value, "acRemark");
            return (Criteria) this;
        }

        public Criteria andAcRemarkNotLike(String value) {
            addCriterion("acRemark not like", value, "acRemark");
            return (Criteria) this;
        }

        public Criteria andAcRemarkIn(List<String> values) {
            addCriterion("acRemark in", values, "acRemark");
            return (Criteria) this;
        }

        public Criteria andAcRemarkNotIn(List<String> values) {
            addCriterion("acRemark not in", values, "acRemark");
            return (Criteria) this;
        }

        public Criteria andAcRemarkBetween(String value1, String value2) {
            addCriterion("acRemark between", value1, value2, "acRemark");
            return (Criteria) this;
        }

        public Criteria andAcRemarkNotBetween(String value1, String value2) {
            addCriterion("acRemark not between", value1, value2, "acRemark");
            return (Criteria) this;
        }

        public Criteria andAcCheckTimeIsNull() {
            addCriterion("acCheckTime is null");
            return (Criteria) this;
        }

        public Criteria andAcCheckTimeIsNotNull() {
            addCriterion("acCheckTime is not null");
            return (Criteria) this;
        }

        public Criteria andAcCheckTimeEqualTo(Date value) {
            addCriterion("acCheckTime =", value, "acCheckTime");
            return (Criteria) this;
        }

        public Criteria andAcCheckTimeNotEqualTo(Date value) {
            addCriterion("acCheckTime <>", value, "acCheckTime");
            return (Criteria) this;
        }

        public Criteria andAcCheckTimeGreaterThan(Date value) {
            addCriterion("acCheckTime >", value, "acCheckTime");
            return (Criteria) this;
        }

        public Criteria andAcCheckTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("acCheckTime >=", value, "acCheckTime");
            return (Criteria) this;
        }

        public Criteria andAcCheckTimeLessThan(Date value) {
            addCriterion("acCheckTime <", value, "acCheckTime");
            return (Criteria) this;
        }

        public Criteria andAcCheckTimeLessThanOrEqualTo(Date value) {
            addCriterion("acCheckTime <=", value, "acCheckTime");
            return (Criteria) this;
        }

        public Criteria andAcCheckTimeIn(List<Date> values) {
            addCriterion("acCheckTime in", values, "acCheckTime");
            return (Criteria) this;
        }

        public Criteria andAcCheckTimeNotIn(List<Date> values) {
            addCriterion("acCheckTime not in", values, "acCheckTime");
            return (Criteria) this;
        }

        public Criteria andAcCheckTimeBetween(Date value1, Date value2) {
            addCriterion("acCheckTime between", value1, value2, "acCheckTime");
            return (Criteria) this;
        }

        public Criteria andAcCheckTimeNotBetween(Date value1, Date value2) {
            addCriterion("acCheckTime not between", value1, value2, "acCheckTime");
            return (Criteria) this;
        }

        public Criteria andAcSettleTimeIsNull() {
            addCriterion("acSettleTime is null");
            return (Criteria) this;
        }

        public Criteria andAcSettleTimeIsNotNull() {
            addCriterion("acSettleTime is not null");
            return (Criteria) this;
        }

        public Criteria andAcSettleTimeEqualTo(Date value) {
            addCriterion("acSettleTime =", value, "acSettleTime");
            return (Criteria) this;
        }

        public Criteria andAcSettleTimeNotEqualTo(Date value) {
            addCriterion("acSettleTime <>", value, "acSettleTime");
            return (Criteria) this;
        }

        public Criteria andAcSettleTimeGreaterThan(Date value) {
            addCriterion("acSettleTime >", value, "acSettleTime");
            return (Criteria) this;
        }

        public Criteria andAcSettleTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("acSettleTime >=", value, "acSettleTime");
            return (Criteria) this;
        }

        public Criteria andAcSettleTimeLessThan(Date value) {
            addCriterion("acSettleTime <", value, "acSettleTime");
            return (Criteria) this;
        }

        public Criteria andAcSettleTimeLessThanOrEqualTo(Date value) {
            addCriterion("acSettleTime <=", value, "acSettleTime");
            return (Criteria) this;
        }

        public Criteria andAcSettleTimeIn(List<Date> values) {
            addCriterion("acSettleTime in", values, "acSettleTime");
            return (Criteria) this;
        }

        public Criteria andAcSettleTimeNotIn(List<Date> values) {
            addCriterion("acSettleTime not in", values, "acSettleTime");
            return (Criteria) this;
        }

        public Criteria andAcSettleTimeBetween(Date value1, Date value2) {
            addCriterion("acSettleTime between", value1, value2, "acSettleTime");
            return (Criteria) this;
        }

        public Criteria andAcSettleTimeNotBetween(Date value1, Date value2) {
            addCriterion("acSettleTime not between", value1, value2, "acSettleTime");
            return (Criteria) this;
        }

        public Criteria andAcPayTimeIsNull() {
            addCriterion("acPayTime is null");
            return (Criteria) this;
        }

        public Criteria andAcPayTimeIsNotNull() {
            addCriterion("acPayTime is not null");
            return (Criteria) this;
        }

        public Criteria andAcPayTimeEqualTo(Date value) {
            addCriterion("acPayTime =", value, "acPayTime");
            return (Criteria) this;
        }

        public Criteria andAcPayTimeNotEqualTo(Date value) {
            addCriterion("acPayTime <>", value, "acPayTime");
            return (Criteria) this;
        }

        public Criteria andAcPayTimeGreaterThan(Date value) {
            addCriterion("acPayTime >", value, "acPayTime");
            return (Criteria) this;
        }

        public Criteria andAcPayTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("acPayTime >=", value, "acPayTime");
            return (Criteria) this;
        }

        public Criteria andAcPayTimeLessThan(Date value) {
            addCriterion("acPayTime <", value, "acPayTime");
            return (Criteria) this;
        }

        public Criteria andAcPayTimeLessThanOrEqualTo(Date value) {
            addCriterion("acPayTime <=", value, "acPayTime");
            return (Criteria) this;
        }

        public Criteria andAcPayTimeIn(List<Date> values) {
            addCriterion("acPayTime in", values, "acPayTime");
            return (Criteria) this;
        }

        public Criteria andAcPayTimeNotIn(List<Date> values) {
            addCriterion("acPayTime not in", values, "acPayTime");
            return (Criteria) this;
        }

        public Criteria andAcPayTimeBetween(Date value1, Date value2) {
            addCriterion("acPayTime between", value1, value2, "acPayTime");
            return (Criteria) this;
        }

        public Criteria andAcPayTimeNotBetween(Date value1, Date value2) {
            addCriterion("acPayTime not between", value1, value2, "acPayTime");
            return (Criteria) this;
        }

        public Criteria andLevelIsNull() {
            addCriterion("level is null");
            return (Criteria) this;
        }

        public Criteria andLevelIsNotNull() {
            addCriterion("level is not null");
            return (Criteria) this;
        }

        public Criteria andLevelEqualTo(Byte value) {
            addCriterion("level =", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotEqualTo(Byte value) {
            addCriterion("level <>", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThan(Byte value) {
            addCriterion("level >", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThanOrEqualTo(Byte value) {
            addCriterion("level >=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThan(Byte value) {
            addCriterion("level <", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThanOrEqualTo(Byte value) {
            addCriterion("level <=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelIn(List<Byte> values) {
            addCriterion("level in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotIn(List<Byte> values) {
            addCriterion("level not in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelBetween(Byte value1, Byte value2) {
            addCriterion("level between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotBetween(Byte value1, Byte value2) {
            addCriterion("level not between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andFreightIsNull() {
            addCriterion("freight is null");
            return (Criteria) this;
        }

        public Criteria andFreightIsNotNull() {
            addCriterion("freight is not null");
            return (Criteria) this;
        }

        public Criteria andFreightEqualTo(BigDecimal value) {
            addCriterion("freight =", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightNotEqualTo(BigDecimal value) {
            addCriterion("freight <>", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightGreaterThan(BigDecimal value) {
            addCriterion("freight >", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("freight >=", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightLessThan(BigDecimal value) {
            addCriterion("freight <", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightLessThanOrEqualTo(BigDecimal value) {
            addCriterion("freight <=", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightIn(List<BigDecimal> values) {
            addCriterion("freight in", values, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightNotIn(List<BigDecimal> values) {
            addCriterion("freight not in", values, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("freight between", value1, value2, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("freight not between", value1, value2, "freight");
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