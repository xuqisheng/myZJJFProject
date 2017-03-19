package com.corner.core.beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class StoreExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public StoreExample() {
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

        protected void addCriterionForJDBCTime(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Time(value.getTime()), property);
        }

        protected void addCriterionForJDBCTime(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Time> timeList = new ArrayList<java.sql.Time>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                timeList.add(new java.sql.Time(iter.next().getTime()));
            }
            addCriterion(condition, timeList, property);
        }

        protected void addCriterionForJDBCTime(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Time(value1.getTime()), new java.sql.Time(value2.getTime()), property);
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

        public Criteria andSupplierCodeIsNull() {
            addCriterion("supplierCode is null");
            return (Criteria) this;
        }

        public Criteria andSupplierCodeIsNotNull() {
            addCriterion("supplierCode is not null");
            return (Criteria) this;
        }

        public Criteria andSupplierCodeEqualTo(String value) {
            addCriterion("supplierCode =", value, "supplierCode");
            return (Criteria) this;
        }

        public Criteria andSupplierCodeNotEqualTo(String value) {
            addCriterion("supplierCode <>", value, "supplierCode");
            return (Criteria) this;
        }

        public Criteria andSupplierCodeGreaterThan(String value) {
            addCriterion("supplierCode >", value, "supplierCode");
            return (Criteria) this;
        }

        public Criteria andSupplierCodeGreaterThanOrEqualTo(String value) {
            addCriterion("supplierCode >=", value, "supplierCode");
            return (Criteria) this;
        }

        public Criteria andSupplierCodeLessThan(String value) {
            addCriterion("supplierCode <", value, "supplierCode");
            return (Criteria) this;
        }

        public Criteria andSupplierCodeLessThanOrEqualTo(String value) {
            addCriterion("supplierCode <=", value, "supplierCode");
            return (Criteria) this;
        }

        public Criteria andSupplierCodeLike(String value) {
            addCriterion("supplierCode like", value, "supplierCode");
            return (Criteria) this;
        }

        public Criteria andSupplierCodeNotLike(String value) {
            addCriterion("supplierCode not like", value, "supplierCode");
            return (Criteria) this;
        }

        public Criteria andSupplierCodeIn(List<String> values) {
            addCriterion("supplierCode in", values, "supplierCode");
            return (Criteria) this;
        }

        public Criteria andSupplierCodeNotIn(List<String> values) {
            addCriterion("supplierCode not in", values, "supplierCode");
            return (Criteria) this;
        }

        public Criteria andSupplierCodeBetween(String value1, String value2) {
            addCriterion("supplierCode between", value1, value2, "supplierCode");
            return (Criteria) this;
        }

        public Criteria andSupplierCodeNotBetween(String value1, String value2) {
            addCriterion("supplierCode not between", value1, value2, "supplierCode");
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

        public Criteria andContactIsNull() {
            addCriterion("contact is null");
            return (Criteria) this;
        }

        public Criteria andContactIsNotNull() {
            addCriterion("contact is not null");
            return (Criteria) this;
        }

        public Criteria andContactEqualTo(String value) {
            addCriterion("contact =", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactNotEqualTo(String value) {
            addCriterion("contact <>", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactGreaterThan(String value) {
            addCriterion("contact >", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactGreaterThanOrEqualTo(String value) {
            addCriterion("contact >=", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactLessThan(String value) {
            addCriterion("contact <", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactLessThanOrEqualTo(String value) {
            addCriterion("contact <=", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactLike(String value) {
            addCriterion("contact like", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactNotLike(String value) {
            addCriterion("contact not like", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactIn(List<String> values) {
            addCriterion("contact in", values, "contact");
            return (Criteria) this;
        }

        public Criteria andContactNotIn(List<String> values) {
            addCriterion("contact not in", values, "contact");
            return (Criteria) this;
        }

        public Criteria andContactBetween(String value1, String value2) {
            addCriterion("contact between", value1, value2, "contact");
            return (Criteria) this;
        }

        public Criteria andContactNotBetween(String value1, String value2) {
            addCriterion("contact not between", value1, value2, "contact");
            return (Criteria) this;
        }

        public Criteria andTelIsNull() {
            addCriterion("tel is null");
            return (Criteria) this;
        }

        public Criteria andTelIsNotNull() {
            addCriterion("tel is not null");
            return (Criteria) this;
        }

        public Criteria andTelEqualTo(String value) {
            addCriterion("tel =", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotEqualTo(String value) {
            addCriterion("tel <>", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelGreaterThan(String value) {
            addCriterion("tel >", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelGreaterThanOrEqualTo(String value) {
            addCriterion("tel >=", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelLessThan(String value) {
            addCriterion("tel <", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelLessThanOrEqualTo(String value) {
            addCriterion("tel <=", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelLike(String value) {
            addCriterion("tel like", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotLike(String value) {
            addCriterion("tel not like", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelIn(List<String> values) {
            addCriterion("tel in", values, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotIn(List<String> values) {
            addCriterion("tel not in", values, "tel");
            return (Criteria) this;
        }

        public Criteria andTelBetween(String value1, String value2) {
            addCriterion("tel between", value1, value2, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotBetween(String value1, String value2) {
            addCriterion("tel not between", value1, value2, "tel");
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

        public Criteria andProvinceIdIsNull() {
            addCriterion("provinceId is null");
            return (Criteria) this;
        }

        public Criteria andProvinceIdIsNotNull() {
            addCriterion("provinceId is not null");
            return (Criteria) this;
        }

        public Criteria andProvinceIdEqualTo(Integer value) {
            addCriterion("provinceId =", value, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdNotEqualTo(Integer value) {
            addCriterion("provinceId <>", value, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdGreaterThan(Integer value) {
            addCriterion("provinceId >", value, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("provinceId >=", value, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdLessThan(Integer value) {
            addCriterion("provinceId <", value, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdLessThanOrEqualTo(Integer value) {
            addCriterion("provinceId <=", value, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdIn(List<Integer> values) {
            addCriterion("provinceId in", values, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdNotIn(List<Integer> values) {
            addCriterion("provinceId not in", values, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdBetween(Integer value1, Integer value2) {
            addCriterion("provinceId between", value1, value2, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdNotBetween(Integer value1, Integer value2) {
            addCriterion("provinceId not between", value1, value2, "provinceId");
            return (Criteria) this;
        }

        public Criteria andCityIdIsNull() {
            addCriterion("cityId is null");
            return (Criteria) this;
        }

        public Criteria andCityIdIsNotNull() {
            addCriterion("cityId is not null");
            return (Criteria) this;
        }

        public Criteria andCityIdEqualTo(Integer value) {
            addCriterion("cityId =", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdNotEqualTo(Integer value) {
            addCriterion("cityId <>", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdGreaterThan(Integer value) {
            addCriterion("cityId >", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("cityId >=", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdLessThan(Integer value) {
            addCriterion("cityId <", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdLessThanOrEqualTo(Integer value) {
            addCriterion("cityId <=", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdIn(List<Integer> values) {
            addCriterion("cityId in", values, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdNotIn(List<Integer> values) {
            addCriterion("cityId not in", values, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdBetween(Integer value1, Integer value2) {
            addCriterion("cityId between", value1, value2, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdNotBetween(Integer value1, Integer value2) {
            addCriterion("cityId not between", value1, value2, "cityId");
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

        public Criteria andStreetIdIsNull() {
            addCriterion("streetId is null");
            return (Criteria) this;
        }

        public Criteria andStreetIdIsNotNull() {
            addCriterion("streetId is not null");
            return (Criteria) this;
        }

        public Criteria andStreetIdEqualTo(Integer value) {
            addCriterion("streetId =", value, "streetId");
            return (Criteria) this;
        }

        public Criteria andStreetIdNotEqualTo(Integer value) {
            addCriterion("streetId <>", value, "streetId");
            return (Criteria) this;
        }

        public Criteria andStreetIdGreaterThan(Integer value) {
            addCriterion("streetId >", value, "streetId");
            return (Criteria) this;
        }

        public Criteria andStreetIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("streetId >=", value, "streetId");
            return (Criteria) this;
        }

        public Criteria andStreetIdLessThan(Integer value) {
            addCriterion("streetId <", value, "streetId");
            return (Criteria) this;
        }

        public Criteria andStreetIdLessThanOrEqualTo(Integer value) {
            addCriterion("streetId <=", value, "streetId");
            return (Criteria) this;
        }

        public Criteria andStreetIdIn(List<Integer> values) {
            addCriterion("streetId in", values, "streetId");
            return (Criteria) this;
        }

        public Criteria andStreetIdNotIn(List<Integer> values) {
            addCriterion("streetId not in", values, "streetId");
            return (Criteria) this;
        }

        public Criteria andStreetIdBetween(Integer value1, Integer value2) {
            addCriterion("streetId between", value1, value2, "streetId");
            return (Criteria) this;
        }

        public Criteria andStreetIdNotBetween(Integer value1, Integer value2) {
            addCriterion("streetId not between", value1, value2, "streetId");
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

        public Criteria andAddressRemarkIsNull() {
            addCriterion("addressRemark is null");
            return (Criteria) this;
        }

        public Criteria andAddressRemarkIsNotNull() {
            addCriterion("addressRemark is not null");
            return (Criteria) this;
        }

        public Criteria andAddressRemarkEqualTo(String value) {
            addCriterion("addressRemark =", value, "addressRemark");
            return (Criteria) this;
        }

        public Criteria andAddressRemarkNotEqualTo(String value) {
            addCriterion("addressRemark <>", value, "addressRemark");
            return (Criteria) this;
        }

        public Criteria andAddressRemarkGreaterThan(String value) {
            addCriterion("addressRemark >", value, "addressRemark");
            return (Criteria) this;
        }

        public Criteria andAddressRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("addressRemark >=", value, "addressRemark");
            return (Criteria) this;
        }

        public Criteria andAddressRemarkLessThan(String value) {
            addCriterion("addressRemark <", value, "addressRemark");
            return (Criteria) this;
        }

        public Criteria andAddressRemarkLessThanOrEqualTo(String value) {
            addCriterion("addressRemark <=", value, "addressRemark");
            return (Criteria) this;
        }

        public Criteria andAddressRemarkLike(String value) {
            addCriterion("addressRemark like", value, "addressRemark");
            return (Criteria) this;
        }

        public Criteria andAddressRemarkNotLike(String value) {
            addCriterion("addressRemark not like", value, "addressRemark");
            return (Criteria) this;
        }

        public Criteria andAddressRemarkIn(List<String> values) {
            addCriterion("addressRemark in", values, "addressRemark");
            return (Criteria) this;
        }

        public Criteria andAddressRemarkNotIn(List<String> values) {
            addCriterion("addressRemark not in", values, "addressRemark");
            return (Criteria) this;
        }

        public Criteria andAddressRemarkBetween(String value1, String value2) {
            addCriterion("addressRemark between", value1, value2, "addressRemark");
            return (Criteria) this;
        }

        public Criteria andAddressRemarkNotBetween(String value1, String value2) {
            addCriterion("addressRemark not between", value1, value2, "addressRemark");
            return (Criteria) this;
        }

        public Criteria andLngIsNull() {
            addCriterion("lng is null");
            return (Criteria) this;
        }

        public Criteria andLngIsNotNull() {
            addCriterion("lng is not null");
            return (Criteria) this;
        }

        public Criteria andLngEqualTo(String value) {
            addCriterion("lng =", value, "lng");
            return (Criteria) this;
        }

        public Criteria andLngNotEqualTo(String value) {
            addCriterion("lng <>", value, "lng");
            return (Criteria) this;
        }

        public Criteria andLngGreaterThan(String value) {
            addCriterion("lng >", value, "lng");
            return (Criteria) this;
        }

        public Criteria andLngGreaterThanOrEqualTo(String value) {
            addCriterion("lng >=", value, "lng");
            return (Criteria) this;
        }

        public Criteria andLngLessThan(String value) {
            addCriterion("lng <", value, "lng");
            return (Criteria) this;
        }

        public Criteria andLngLessThanOrEqualTo(String value) {
            addCriterion("lng <=", value, "lng");
            return (Criteria) this;
        }

        public Criteria andLngLike(String value) {
            addCriterion("lng like", value, "lng");
            return (Criteria) this;
        }

        public Criteria andLngNotLike(String value) {
            addCriterion("lng not like", value, "lng");
            return (Criteria) this;
        }

        public Criteria andLngIn(List<String> values) {
            addCriterion("lng in", values, "lng");
            return (Criteria) this;
        }

        public Criteria andLngNotIn(List<String> values) {
            addCriterion("lng not in", values, "lng");
            return (Criteria) this;
        }

        public Criteria andLngBetween(String value1, String value2) {
            addCriterion("lng between", value1, value2, "lng");
            return (Criteria) this;
        }

        public Criteria andLngNotBetween(String value1, String value2) {
            addCriterion("lng not between", value1, value2, "lng");
            return (Criteria) this;
        }

        public Criteria andLatIsNull() {
            addCriterion("lat is null");
            return (Criteria) this;
        }

        public Criteria andLatIsNotNull() {
            addCriterion("lat is not null");
            return (Criteria) this;
        }

        public Criteria andLatEqualTo(String value) {
            addCriterion("lat =", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatNotEqualTo(String value) {
            addCriterion("lat <>", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatGreaterThan(String value) {
            addCriterion("lat >", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatGreaterThanOrEqualTo(String value) {
            addCriterion("lat >=", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatLessThan(String value) {
            addCriterion("lat <", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatLessThanOrEqualTo(String value) {
            addCriterion("lat <=", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatLike(String value) {
            addCriterion("lat like", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatNotLike(String value) {
            addCriterion("lat not like", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatIn(List<String> values) {
            addCriterion("lat in", values, "lat");
            return (Criteria) this;
        }

        public Criteria andLatNotIn(List<String> values) {
            addCriterion("lat not in", values, "lat");
            return (Criteria) this;
        }

        public Criteria andLatBetween(String value1, String value2) {
            addCriterion("lat between", value1, value2, "lat");
            return (Criteria) this;
        }

        public Criteria andLatNotBetween(String value1, String value2) {
            addCriterion("lat not between", value1, value2, "lat");
            return (Criteria) this;
        }

        public Criteria andContactUserIsNull() {
            addCriterion("contactUser is null");
            return (Criteria) this;
        }

        public Criteria andContactUserIsNotNull() {
            addCriterion("contactUser is not null");
            return (Criteria) this;
        }

        public Criteria andContactUserEqualTo(String value) {
            addCriterion("contactUser =", value, "contactUser");
            return (Criteria) this;
        }

        public Criteria andContactUserNotEqualTo(String value) {
            addCriterion("contactUser <>", value, "contactUser");
            return (Criteria) this;
        }

        public Criteria andContactUserGreaterThan(String value) {
            addCriterion("contactUser >", value, "contactUser");
            return (Criteria) this;
        }

        public Criteria andContactUserGreaterThanOrEqualTo(String value) {
            addCriterion("contactUser >=", value, "contactUser");
            return (Criteria) this;
        }

        public Criteria andContactUserLessThan(String value) {
            addCriterion("contactUser <", value, "contactUser");
            return (Criteria) this;
        }

        public Criteria andContactUserLessThanOrEqualTo(String value) {
            addCriterion("contactUser <=", value, "contactUser");
            return (Criteria) this;
        }

        public Criteria andContactUserLike(String value) {
            addCriterion("contactUser like", value, "contactUser");
            return (Criteria) this;
        }

        public Criteria andContactUserNotLike(String value) {
            addCriterion("contactUser not like", value, "contactUser");
            return (Criteria) this;
        }

        public Criteria andContactUserIn(List<String> values) {
            addCriterion("contactUser in", values, "contactUser");
            return (Criteria) this;
        }

        public Criteria andContactUserNotIn(List<String> values) {
            addCriterion("contactUser not in", values, "contactUser");
            return (Criteria) this;
        }

        public Criteria andContactUserBetween(String value1, String value2) {
            addCriterion("contactUser between", value1, value2, "contactUser");
            return (Criteria) this;
        }

        public Criteria andContactUserNotBetween(String value1, String value2) {
            addCriterion("contactUser not between", value1, value2, "contactUser");
            return (Criteria) this;
        }

        public Criteria andContactPwdIsNull() {
            addCriterion("contactPwd is null");
            return (Criteria) this;
        }

        public Criteria andContactPwdIsNotNull() {
            addCriterion("contactPwd is not null");
            return (Criteria) this;
        }

        public Criteria andContactPwdEqualTo(String value) {
            addCriterion("contactPwd =", value, "contactPwd");
            return (Criteria) this;
        }

        public Criteria andContactPwdNotEqualTo(String value) {
            addCriterion("contactPwd <>", value, "contactPwd");
            return (Criteria) this;
        }

        public Criteria andContactPwdGreaterThan(String value) {
            addCriterion("contactPwd >", value, "contactPwd");
            return (Criteria) this;
        }

        public Criteria andContactPwdGreaterThanOrEqualTo(String value) {
            addCriterion("contactPwd >=", value, "contactPwd");
            return (Criteria) this;
        }

        public Criteria andContactPwdLessThan(String value) {
            addCriterion("contactPwd <", value, "contactPwd");
            return (Criteria) this;
        }

        public Criteria andContactPwdLessThanOrEqualTo(String value) {
            addCriterion("contactPwd <=", value, "contactPwd");
            return (Criteria) this;
        }

        public Criteria andContactPwdLike(String value) {
            addCriterion("contactPwd like", value, "contactPwd");
            return (Criteria) this;
        }

        public Criteria andContactPwdNotLike(String value) {
            addCriterion("contactPwd not like", value, "contactPwd");
            return (Criteria) this;
        }

        public Criteria andContactPwdIn(List<String> values) {
            addCriterion("contactPwd in", values, "contactPwd");
            return (Criteria) this;
        }

        public Criteria andContactPwdNotIn(List<String> values) {
            addCriterion("contactPwd not in", values, "contactPwd");
            return (Criteria) this;
        }

        public Criteria andContactPwdBetween(String value1, String value2) {
            addCriterion("contactPwd between", value1, value2, "contactPwd");
            return (Criteria) this;
        }

        public Criteria andContactPwdNotBetween(String value1, String value2) {
            addCriterion("contactPwd not between", value1, value2, "contactPwd");
            return (Criteria) this;
        }

        public Criteria andShoperNameIsNull() {
            addCriterion("shoperName is null");
            return (Criteria) this;
        }

        public Criteria andShoperNameIsNotNull() {
            addCriterion("shoperName is not null");
            return (Criteria) this;
        }

        public Criteria andShoperNameEqualTo(String value) {
            addCriterion("shoperName =", value, "shoperName");
            return (Criteria) this;
        }

        public Criteria andShoperNameNotEqualTo(String value) {
            addCriterion("shoperName <>", value, "shoperName");
            return (Criteria) this;
        }

        public Criteria andShoperNameGreaterThan(String value) {
            addCriterion("shoperName >", value, "shoperName");
            return (Criteria) this;
        }

        public Criteria andShoperNameGreaterThanOrEqualTo(String value) {
            addCriterion("shoperName >=", value, "shoperName");
            return (Criteria) this;
        }

        public Criteria andShoperNameLessThan(String value) {
            addCriterion("shoperName <", value, "shoperName");
            return (Criteria) this;
        }

        public Criteria andShoperNameLessThanOrEqualTo(String value) {
            addCriterion("shoperName <=", value, "shoperName");
            return (Criteria) this;
        }

        public Criteria andShoperNameLike(String value) {
            addCriterion("shoperName like", value, "shoperName");
            return (Criteria) this;
        }

        public Criteria andShoperNameNotLike(String value) {
            addCriterion("shoperName not like", value, "shoperName");
            return (Criteria) this;
        }

        public Criteria andShoperNameIn(List<String> values) {
            addCriterion("shoperName in", values, "shoperName");
            return (Criteria) this;
        }

        public Criteria andShoperNameNotIn(List<String> values) {
            addCriterion("shoperName not in", values, "shoperName");
            return (Criteria) this;
        }

        public Criteria andShoperNameBetween(String value1, String value2) {
            addCriterion("shoperName between", value1, value2, "shoperName");
            return (Criteria) this;
        }

        public Criteria andShoperNameNotBetween(String value1, String value2) {
            addCriterion("shoperName not between", value1, value2, "shoperName");
            return (Criteria) this;
        }

        public Criteria andShoperTelIsNull() {
            addCriterion("shoperTel is null");
            return (Criteria) this;
        }

        public Criteria andShoperTelIsNotNull() {
            addCriterion("shoperTel is not null");
            return (Criteria) this;
        }

        public Criteria andShoperTelEqualTo(String value) {
            addCriterion("shoperTel =", value, "shoperTel");
            return (Criteria) this;
        }

        public Criteria andShoperTelNotEqualTo(String value) {
            addCriterion("shoperTel <>", value, "shoperTel");
            return (Criteria) this;
        }

        public Criteria andShoperTelGreaterThan(String value) {
            addCriterion("shoperTel >", value, "shoperTel");
            return (Criteria) this;
        }

        public Criteria andShoperTelGreaterThanOrEqualTo(String value) {
            addCriterion("shoperTel >=", value, "shoperTel");
            return (Criteria) this;
        }

        public Criteria andShoperTelLessThan(String value) {
            addCriterion("shoperTel <", value, "shoperTel");
            return (Criteria) this;
        }

        public Criteria andShoperTelLessThanOrEqualTo(String value) {
            addCriterion("shoperTel <=", value, "shoperTel");
            return (Criteria) this;
        }

        public Criteria andShoperTelLike(String value) {
            addCriterion("shoperTel like", value, "shoperTel");
            return (Criteria) this;
        }

        public Criteria andShoperTelNotLike(String value) {
            addCriterion("shoperTel not like", value, "shoperTel");
            return (Criteria) this;
        }

        public Criteria andShoperTelIn(List<String> values) {
            addCriterion("shoperTel in", values, "shoperTel");
            return (Criteria) this;
        }

        public Criteria andShoperTelNotIn(List<String> values) {
            addCriterion("shoperTel not in", values, "shoperTel");
            return (Criteria) this;
        }

        public Criteria andShoperTelBetween(String value1, String value2) {
            addCriterion("shoperTel between", value1, value2, "shoperTel");
            return (Criteria) this;
        }

        public Criteria andShoperTelNotBetween(String value1, String value2) {
            addCriterion("shoperTel not between", value1, value2, "shoperTel");
            return (Criteria) this;
        }

        public Criteria andShoperQQIsNull() {
            addCriterion("shoperQQ is null");
            return (Criteria) this;
        }

        public Criteria andShoperQQIsNotNull() {
            addCriterion("shoperQQ is not null");
            return (Criteria) this;
        }

        public Criteria andShoperQQEqualTo(String value) {
            addCriterion("shoperQQ =", value, "shoperQQ");
            return (Criteria) this;
        }

        public Criteria andShoperQQNotEqualTo(String value) {
            addCriterion("shoperQQ <>", value, "shoperQQ");
            return (Criteria) this;
        }

        public Criteria andShoperQQGreaterThan(String value) {
            addCriterion("shoperQQ >", value, "shoperQQ");
            return (Criteria) this;
        }

        public Criteria andShoperQQGreaterThanOrEqualTo(String value) {
            addCriterion("shoperQQ >=", value, "shoperQQ");
            return (Criteria) this;
        }

        public Criteria andShoperQQLessThan(String value) {
            addCriterion("shoperQQ <", value, "shoperQQ");
            return (Criteria) this;
        }

        public Criteria andShoperQQLessThanOrEqualTo(String value) {
            addCriterion("shoperQQ <=", value, "shoperQQ");
            return (Criteria) this;
        }

        public Criteria andShoperQQLike(String value) {
            addCriterion("shoperQQ like", value, "shoperQQ");
            return (Criteria) this;
        }

        public Criteria andShoperQQNotLike(String value) {
            addCriterion("shoperQQ not like", value, "shoperQQ");
            return (Criteria) this;
        }

        public Criteria andShoperQQIn(List<String> values) {
            addCriterion("shoperQQ in", values, "shoperQQ");
            return (Criteria) this;
        }

        public Criteria andShoperQQNotIn(List<String> values) {
            addCriterion("shoperQQ not in", values, "shoperQQ");
            return (Criteria) this;
        }

        public Criteria andShoperQQBetween(String value1, String value2) {
            addCriterion("shoperQQ between", value1, value2, "shoperQQ");
            return (Criteria) this;
        }

        public Criteria andShoperQQNotBetween(String value1, String value2) {
            addCriterion("shoperQQ not between", value1, value2, "shoperQQ");
            return (Criteria) this;
        }

        public Criteria andBankAccountIsNull() {
            addCriterion("bankAccount is null");
            return (Criteria) this;
        }

        public Criteria andBankAccountIsNotNull() {
            addCriterion("bankAccount is not null");
            return (Criteria) this;
        }

        public Criteria andBankAccountEqualTo(String value) {
            addCriterion("bankAccount =", value, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountNotEqualTo(String value) {
            addCriterion("bankAccount <>", value, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountGreaterThan(String value) {
            addCriterion("bankAccount >", value, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountGreaterThanOrEqualTo(String value) {
            addCriterion("bankAccount >=", value, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountLessThan(String value) {
            addCriterion("bankAccount <", value, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountLessThanOrEqualTo(String value) {
            addCriterion("bankAccount <=", value, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountLike(String value) {
            addCriterion("bankAccount like", value, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountNotLike(String value) {
            addCriterion("bankAccount not like", value, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountIn(List<String> values) {
            addCriterion("bankAccount in", values, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountNotIn(List<String> values) {
            addCriterion("bankAccount not in", values, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountBetween(String value1, String value2) {
            addCriterion("bankAccount between", value1, value2, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountNotBetween(String value1, String value2) {
            addCriterion("bankAccount not between", value1, value2, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankNameIsNull() {
            addCriterion("bankName is null");
            return (Criteria) this;
        }

        public Criteria andBankNameIsNotNull() {
            addCriterion("bankName is not null");
            return (Criteria) this;
        }

        public Criteria andBankNameEqualTo(String value) {
            addCriterion("bankName =", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotEqualTo(String value) {
            addCriterion("bankName <>", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameGreaterThan(String value) {
            addCriterion("bankName >", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameGreaterThanOrEqualTo(String value) {
            addCriterion("bankName >=", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameLessThan(String value) {
            addCriterion("bankName <", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameLessThanOrEqualTo(String value) {
            addCriterion("bankName <=", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameLike(String value) {
            addCriterion("bankName like", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotLike(String value) {
            addCriterion("bankName not like", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameIn(List<String> values) {
            addCriterion("bankName in", values, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotIn(List<String> values) {
            addCriterion("bankName not in", values, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameBetween(String value1, String value2) {
            addCriterion("bankName between", value1, value2, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotBetween(String value1, String value2) {
            addCriterion("bankName not between", value1, value2, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankCardIsNull() {
            addCriterion("bankCard is null");
            return (Criteria) this;
        }

        public Criteria andBankCardIsNotNull() {
            addCriterion("bankCard is not null");
            return (Criteria) this;
        }

        public Criteria andBankCardEqualTo(String value) {
            addCriterion("bankCard =", value, "bankCard");
            return (Criteria) this;
        }

        public Criteria andBankCardNotEqualTo(String value) {
            addCriterion("bankCard <>", value, "bankCard");
            return (Criteria) this;
        }

        public Criteria andBankCardGreaterThan(String value) {
            addCriterion("bankCard >", value, "bankCard");
            return (Criteria) this;
        }

        public Criteria andBankCardGreaterThanOrEqualTo(String value) {
            addCriterion("bankCard >=", value, "bankCard");
            return (Criteria) this;
        }

        public Criteria andBankCardLessThan(String value) {
            addCriterion("bankCard <", value, "bankCard");
            return (Criteria) this;
        }

        public Criteria andBankCardLessThanOrEqualTo(String value) {
            addCriterion("bankCard <=", value, "bankCard");
            return (Criteria) this;
        }

        public Criteria andBankCardLike(String value) {
            addCriterion("bankCard like", value, "bankCard");
            return (Criteria) this;
        }

        public Criteria andBankCardNotLike(String value) {
            addCriterion("bankCard not like", value, "bankCard");
            return (Criteria) this;
        }

        public Criteria andBankCardIn(List<String> values) {
            addCriterion("bankCard in", values, "bankCard");
            return (Criteria) this;
        }

        public Criteria andBankCardNotIn(List<String> values) {
            addCriterion("bankCard not in", values, "bankCard");
            return (Criteria) this;
        }

        public Criteria andBankCardBetween(String value1, String value2) {
            addCriterion("bankCard between", value1, value2, "bankCard");
            return (Criteria) this;
        }

        public Criteria andBankCardNotBetween(String value1, String value2) {
            addCriterion("bankCard not between", value1, value2, "bankCard");
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

        public Criteria andLastTimeIsNull() {
            addCriterion("lastTime is null");
            return (Criteria) this;
        }

        public Criteria andLastTimeIsNotNull() {
            addCriterion("lastTime is not null");
            return (Criteria) this;
        }

        public Criteria andLastTimeEqualTo(Date value) {
            addCriterion("lastTime =", value, "lastTime");
            return (Criteria) this;
        }

        public Criteria andLastTimeNotEqualTo(Date value) {
            addCriterion("lastTime <>", value, "lastTime");
            return (Criteria) this;
        }

        public Criteria andLastTimeGreaterThan(Date value) {
            addCriterion("lastTime >", value, "lastTime");
            return (Criteria) this;
        }

        public Criteria andLastTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("lastTime >=", value, "lastTime");
            return (Criteria) this;
        }

        public Criteria andLastTimeLessThan(Date value) {
            addCriterion("lastTime <", value, "lastTime");
            return (Criteria) this;
        }

        public Criteria andLastTimeLessThanOrEqualTo(Date value) {
            addCriterion("lastTime <=", value, "lastTime");
            return (Criteria) this;
        }

        public Criteria andLastTimeIn(List<Date> values) {
            addCriterion("lastTime in", values, "lastTime");
            return (Criteria) this;
        }

        public Criteria andLastTimeNotIn(List<Date> values) {
            addCriterion("lastTime not in", values, "lastTime");
            return (Criteria) this;
        }

        public Criteria andLastTimeBetween(Date value1, Date value2) {
            addCriterion("lastTime between", value1, value2, "lastTime");
            return (Criteria) this;
        }

        public Criteria andLastTimeNotBetween(Date value1, Date value2) {
            addCriterion("lastTime not between", value1, value2, "lastTime");
            return (Criteria) this;
        }

        public Criteria andLastIPIsNull() {
            addCriterion("lastIP is null");
            return (Criteria) this;
        }

        public Criteria andLastIPIsNotNull() {
            addCriterion("lastIP is not null");
            return (Criteria) this;
        }

        public Criteria andLastIPEqualTo(String value) {
            addCriterion("lastIP =", value, "lastIP");
            return (Criteria) this;
        }

        public Criteria andLastIPNotEqualTo(String value) {
            addCriterion("lastIP <>", value, "lastIP");
            return (Criteria) this;
        }

        public Criteria andLastIPGreaterThan(String value) {
            addCriterion("lastIP >", value, "lastIP");
            return (Criteria) this;
        }

        public Criteria andLastIPGreaterThanOrEqualTo(String value) {
            addCriterion("lastIP >=", value, "lastIP");
            return (Criteria) this;
        }

        public Criteria andLastIPLessThan(String value) {
            addCriterion("lastIP <", value, "lastIP");
            return (Criteria) this;
        }

        public Criteria andLastIPLessThanOrEqualTo(String value) {
            addCriterion("lastIP <=", value, "lastIP");
            return (Criteria) this;
        }

        public Criteria andLastIPLike(String value) {
            addCriterion("lastIP like", value, "lastIP");
            return (Criteria) this;
        }

        public Criteria andLastIPNotLike(String value) {
            addCriterion("lastIP not like", value, "lastIP");
            return (Criteria) this;
        }

        public Criteria andLastIPIn(List<String> values) {
            addCriterion("lastIP in", values, "lastIP");
            return (Criteria) this;
        }

        public Criteria andLastIPNotIn(List<String> values) {
            addCriterion("lastIP not in", values, "lastIP");
            return (Criteria) this;
        }

        public Criteria andLastIPBetween(String value1, String value2) {
            addCriterion("lastIP between", value1, value2, "lastIP");
            return (Criteria) this;
        }

        public Criteria andLastIPNotBetween(String value1, String value2) {
            addCriterion("lastIP not between", value1, value2, "lastIP");
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

        public Criteria andEditTimeIsNull() {
            addCriterion("editTime is null");
            return (Criteria) this;
        }

        public Criteria andEditTimeIsNotNull() {
            addCriterion("editTime is not null");
            return (Criteria) this;
        }

        public Criteria andEditTimeEqualTo(Date value) {
            addCriterion("editTime =", value, "editTime");
            return (Criteria) this;
        }

        public Criteria andEditTimeNotEqualTo(Date value) {
            addCriterion("editTime <>", value, "editTime");
            return (Criteria) this;
        }

        public Criteria andEditTimeGreaterThan(Date value) {
            addCriterion("editTime >", value, "editTime");
            return (Criteria) this;
        }

        public Criteria andEditTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("editTime >=", value, "editTime");
            return (Criteria) this;
        }

        public Criteria andEditTimeLessThan(Date value) {
            addCriterion("editTime <", value, "editTime");
            return (Criteria) this;
        }

        public Criteria andEditTimeLessThanOrEqualTo(Date value) {
            addCriterion("editTime <=", value, "editTime");
            return (Criteria) this;
        }

        public Criteria andEditTimeIn(List<Date> values) {
            addCriterion("editTime in", values, "editTime");
            return (Criteria) this;
        }

        public Criteria andEditTimeNotIn(List<Date> values) {
            addCriterion("editTime not in", values, "editTime");
            return (Criteria) this;
        }

        public Criteria andEditTimeBetween(Date value1, Date value2) {
            addCriterion("editTime between", value1, value2, "editTime");
            return (Criteria) this;
        }

        public Criteria andEditTimeNotBetween(Date value1, Date value2) {
            addCriterion("editTime not between", value1, value2, "editTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeBeginIsNull() {
            addCriterion("sendTimeBegin is null");
            return (Criteria) this;
        }

        public Criteria andSendTimeBeginIsNotNull() {
            addCriterion("sendTimeBegin is not null");
            return (Criteria) this;
        }

        public Criteria andSendTimeBeginEqualTo(Date value) {
            addCriterionForJDBCTime("sendTimeBegin =", value, "sendTimeBegin");
            return (Criteria) this;
        }

        public Criteria andSendTimeBeginNotEqualTo(Date value) {
            addCriterionForJDBCTime("sendTimeBegin <>", value, "sendTimeBegin");
            return (Criteria) this;
        }

        public Criteria andSendTimeBeginGreaterThan(Date value) {
            addCriterionForJDBCTime("sendTimeBegin >", value, "sendTimeBegin");
            return (Criteria) this;
        }

        public Criteria andSendTimeBeginGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCTime("sendTimeBegin >=", value, "sendTimeBegin");
            return (Criteria) this;
        }

        public Criteria andSendTimeBeginLessThan(Date value) {
            addCriterionForJDBCTime("sendTimeBegin <", value, "sendTimeBegin");
            return (Criteria) this;
        }

        public Criteria andSendTimeBeginLessThanOrEqualTo(Date value) {
            addCriterionForJDBCTime("sendTimeBegin <=", value, "sendTimeBegin");
            return (Criteria) this;
        }

        public Criteria andSendTimeBeginIn(List<Date> values) {
            addCriterionForJDBCTime("sendTimeBegin in", values, "sendTimeBegin");
            return (Criteria) this;
        }

        public Criteria andSendTimeBeginNotIn(List<Date> values) {
            addCriterionForJDBCTime("sendTimeBegin not in", values, "sendTimeBegin");
            return (Criteria) this;
        }

        public Criteria andSendTimeBeginBetween(Date value1, Date value2) {
            addCriterionForJDBCTime("sendTimeBegin between", value1, value2, "sendTimeBegin");
            return (Criteria) this;
        }

        public Criteria andSendTimeBeginNotBetween(Date value1, Date value2) {
            addCriterionForJDBCTime("sendTimeBegin not between", value1, value2, "sendTimeBegin");
            return (Criteria) this;
        }

        public Criteria andSendTimeEndIsNull() {
            addCriterion("sendTimeEnd is null");
            return (Criteria) this;
        }

        public Criteria andSendTimeEndIsNotNull() {
            addCriterion("sendTimeEnd is not null");
            return (Criteria) this;
        }

        public Criteria andSendTimeEndEqualTo(Date value) {
            addCriterionForJDBCTime("sendTimeEnd =", value, "sendTimeEnd");
            return (Criteria) this;
        }

        public Criteria andSendTimeEndNotEqualTo(Date value) {
            addCriterionForJDBCTime("sendTimeEnd <>", value, "sendTimeEnd");
            return (Criteria) this;
        }

        public Criteria andSendTimeEndGreaterThan(Date value) {
            addCriterionForJDBCTime("sendTimeEnd >", value, "sendTimeEnd");
            return (Criteria) this;
        }

        public Criteria andSendTimeEndGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCTime("sendTimeEnd >=", value, "sendTimeEnd");
            return (Criteria) this;
        }

        public Criteria andSendTimeEndLessThan(Date value) {
            addCriterionForJDBCTime("sendTimeEnd <", value, "sendTimeEnd");
            return (Criteria) this;
        }

        public Criteria andSendTimeEndLessThanOrEqualTo(Date value) {
            addCriterionForJDBCTime("sendTimeEnd <=", value, "sendTimeEnd");
            return (Criteria) this;
        }

        public Criteria andSendTimeEndIn(List<Date> values) {
            addCriterionForJDBCTime("sendTimeEnd in", values, "sendTimeEnd");
            return (Criteria) this;
        }

        public Criteria andSendTimeEndNotIn(List<Date> values) {
            addCriterionForJDBCTime("sendTimeEnd not in", values, "sendTimeEnd");
            return (Criteria) this;
        }

        public Criteria andSendTimeEndBetween(Date value1, Date value2) {
            addCriterionForJDBCTime("sendTimeEnd between", value1, value2, "sendTimeEnd");
            return (Criteria) this;
        }

        public Criteria andSendTimeEndNotBetween(Date value1, Date value2) {
            addCriterionForJDBCTime("sendTimeEnd not between", value1, value2, "sendTimeEnd");
            return (Criteria) this;
        }

        public Criteria andSendessIsNull() {
            addCriterion("sendess is null");
            return (Criteria) this;
        }

        public Criteria andSendessIsNotNull() {
            addCriterion("sendess is not null");
            return (Criteria) this;
        }

        public Criteria andSendessEqualTo(BigDecimal value) {
            addCriterion("sendess =", value, "sendess");
            return (Criteria) this;
        }

        public Criteria andSendessNotEqualTo(BigDecimal value) {
            addCriterion("sendess <>", value, "sendess");
            return (Criteria) this;
        }

        public Criteria andSendessGreaterThan(BigDecimal value) {
            addCriterion("sendess >", value, "sendess");
            return (Criteria) this;
        }

        public Criteria andSendessGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("sendess >=", value, "sendess");
            return (Criteria) this;
        }

        public Criteria andSendessLessThan(BigDecimal value) {
            addCriterion("sendess <", value, "sendess");
            return (Criteria) this;
        }

        public Criteria andSendessLessThanOrEqualTo(BigDecimal value) {
            addCriterion("sendess <=", value, "sendess");
            return (Criteria) this;
        }

        public Criteria andSendessIn(List<BigDecimal> values) {
            addCriterion("sendess in", values, "sendess");
            return (Criteria) this;
        }

        public Criteria andSendessNotIn(List<BigDecimal> values) {
            addCriterion("sendess not in", values, "sendess");
            return (Criteria) this;
        }

        public Criteria andSendessBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sendess between", value1, value2, "sendess");
            return (Criteria) this;
        }

        public Criteria andSendessNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sendess not between", value1, value2, "sendess");
            return (Criteria) this;
        }

        public Criteria andServiceFeeIsNull() {
            addCriterion("serviceFee is null");
            return (Criteria) this;
        }

        public Criteria andServiceFeeIsNotNull() {
            addCriterion("serviceFee is not null");
            return (Criteria) this;
        }

        public Criteria andServiceFeeEqualTo(BigDecimal value) {
            addCriterion("serviceFee =", value, "serviceFee");
            return (Criteria) this;
        }

        public Criteria andServiceFeeNotEqualTo(BigDecimal value) {
            addCriterion("serviceFee <>", value, "serviceFee");
            return (Criteria) this;
        }

        public Criteria andServiceFeeGreaterThan(BigDecimal value) {
            addCriterion("serviceFee >", value, "serviceFee");
            return (Criteria) this;
        }

        public Criteria andServiceFeeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("serviceFee >=", value, "serviceFee");
            return (Criteria) this;
        }

        public Criteria andServiceFeeLessThan(BigDecimal value) {
            addCriterion("serviceFee <", value, "serviceFee");
            return (Criteria) this;
        }

        public Criteria andServiceFeeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("serviceFee <=", value, "serviceFee");
            return (Criteria) this;
        }

        public Criteria andServiceFeeIn(List<BigDecimal> values) {
            addCriterion("serviceFee in", values, "serviceFee");
            return (Criteria) this;
        }

        public Criteria andServiceFeeNotIn(List<BigDecimal> values) {
            addCriterion("serviceFee not in", values, "serviceFee");
            return (Criteria) this;
        }

        public Criteria andServiceFeeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("serviceFee between", value1, value2, "serviceFee");
            return (Criteria) this;
        }

        public Criteria andServiceFeeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("serviceFee not between", value1, value2, "serviceFee");
            return (Criteria) this;
        }

        public Criteria andRatesIsNull() {
            addCriterion("rates is null");
            return (Criteria) this;
        }

        public Criteria andRatesIsNotNull() {
            addCriterion("rates is not null");
            return (Criteria) this;
        }

        public Criteria andRatesEqualTo(Float value) {
            addCriterion("rates =", value, "rates");
            return (Criteria) this;
        }

        public Criteria andRatesNotEqualTo(Float value) {
            addCriterion("rates <>", value, "rates");
            return (Criteria) this;
        }

        public Criteria andRatesGreaterThan(Float value) {
            addCriterion("rates >", value, "rates");
            return (Criteria) this;
        }

        public Criteria andRatesGreaterThanOrEqualTo(Float value) {
            addCriterion("rates >=", value, "rates");
            return (Criteria) this;
        }

        public Criteria andRatesLessThan(Float value) {
            addCriterion("rates <", value, "rates");
            return (Criteria) this;
        }

        public Criteria andRatesLessThanOrEqualTo(Float value) {
            addCriterion("rates <=", value, "rates");
            return (Criteria) this;
        }

        public Criteria andRatesIn(List<Float> values) {
            addCriterion("rates in", values, "rates");
            return (Criteria) this;
        }

        public Criteria andRatesNotIn(List<Float> values) {
            addCriterion("rates not in", values, "rates");
            return (Criteria) this;
        }

        public Criteria andRatesBetween(Float value1, Float value2) {
            addCriterion("rates between", value1, value2, "rates");
            return (Criteria) this;
        }

        public Criteria andRatesNotBetween(Float value1, Float value2) {
            addCriterion("rates not between", value1, value2, "rates");
            return (Criteria) this;
        }

        public Criteria andAgreementPeriodIsNull() {
            addCriterion("agreementPeriod is null");
            return (Criteria) this;
        }

        public Criteria andAgreementPeriodIsNotNull() {
            addCriterion("agreementPeriod is not null");
            return (Criteria) this;
        }

        public Criteria andAgreementPeriodEqualTo(Byte value) {
            addCriterion("agreementPeriod =", value, "agreementPeriod");
            return (Criteria) this;
        }

        public Criteria andAgreementPeriodNotEqualTo(Byte value) {
            addCriterion("agreementPeriod <>", value, "agreementPeriod");
            return (Criteria) this;
        }

        public Criteria andAgreementPeriodGreaterThan(Byte value) {
            addCriterion("agreementPeriod >", value, "agreementPeriod");
            return (Criteria) this;
        }

        public Criteria andAgreementPeriodGreaterThanOrEqualTo(Byte value) {
            addCriterion("agreementPeriod >=", value, "agreementPeriod");
            return (Criteria) this;
        }

        public Criteria andAgreementPeriodLessThan(Byte value) {
            addCriterion("agreementPeriod <", value, "agreementPeriod");
            return (Criteria) this;
        }

        public Criteria andAgreementPeriodLessThanOrEqualTo(Byte value) {
            addCriterion("agreementPeriod <=", value, "agreementPeriod");
            return (Criteria) this;
        }

        public Criteria andAgreementPeriodIn(List<Byte> values) {
            addCriterion("agreementPeriod in", values, "agreementPeriod");
            return (Criteria) this;
        }

        public Criteria andAgreementPeriodNotIn(List<Byte> values) {
            addCriterion("agreementPeriod not in", values, "agreementPeriod");
            return (Criteria) this;
        }

        public Criteria andAgreementPeriodBetween(Byte value1, Byte value2) {
            addCriterion("agreementPeriod between", value1, value2, "agreementPeriod");
            return (Criteria) this;
        }

        public Criteria andAgreementPeriodNotBetween(Byte value1, Byte value2) {
            addCriterion("agreementPeriod not between", value1, value2, "agreementPeriod");
            return (Criteria) this;
        }

        public Criteria andTotalPeriodIsNull() {
            addCriterion("totalPeriod is null");
            return (Criteria) this;
        }

        public Criteria andTotalPeriodIsNotNull() {
            addCriterion("totalPeriod is not null");
            return (Criteria) this;
        }

        public Criteria andTotalPeriodEqualTo(Integer value) {
            addCriterion("totalPeriod =", value, "totalPeriod");
            return (Criteria) this;
        }

        public Criteria andTotalPeriodNotEqualTo(Integer value) {
            addCriterion("totalPeriod <>", value, "totalPeriod");
            return (Criteria) this;
        }

        public Criteria andTotalPeriodGreaterThan(Integer value) {
            addCriterion("totalPeriod >", value, "totalPeriod");
            return (Criteria) this;
        }

        public Criteria andTotalPeriodGreaterThanOrEqualTo(Integer value) {
            addCriterion("totalPeriod >=", value, "totalPeriod");
            return (Criteria) this;
        }

        public Criteria andTotalPeriodLessThan(Integer value) {
            addCriterion("totalPeriod <", value, "totalPeriod");
            return (Criteria) this;
        }

        public Criteria andTotalPeriodLessThanOrEqualTo(Integer value) {
            addCriterion("totalPeriod <=", value, "totalPeriod");
            return (Criteria) this;
        }

        public Criteria andTotalPeriodIn(List<Integer> values) {
            addCriterion("totalPeriod in", values, "totalPeriod");
            return (Criteria) this;
        }

        public Criteria andTotalPeriodNotIn(List<Integer> values) {
            addCriterion("totalPeriod not in", values, "totalPeriod");
            return (Criteria) this;
        }

        public Criteria andTotalPeriodBetween(Integer value1, Integer value2) {
            addCriterion("totalPeriod between", value1, value2, "totalPeriod");
            return (Criteria) this;
        }

        public Criteria andTotalPeriodNotBetween(Integer value1, Integer value2) {
            addCriterion("totalPeriod not between", value1, value2, "totalPeriod");
            return (Criteria) this;
        }

        public Criteria andSignTimeIsNull() {
            addCriterion("signTime is null");
            return (Criteria) this;
        }

        public Criteria andSignTimeIsNotNull() {
            addCriterion("signTime is not null");
            return (Criteria) this;
        }

        public Criteria andSignTimeEqualTo(Date value) {
            addCriterionForJDBCDate("signTime =", value, "signTime");
            return (Criteria) this;
        }

        public Criteria andSignTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("signTime <>", value, "signTime");
            return (Criteria) this;
        }

        public Criteria andSignTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("signTime >", value, "signTime");
            return (Criteria) this;
        }

        public Criteria andSignTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("signTime >=", value, "signTime");
            return (Criteria) this;
        }

        public Criteria andSignTimeLessThan(Date value) {
            addCriterionForJDBCDate("signTime <", value, "signTime");
            return (Criteria) this;
        }

        public Criteria andSignTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("signTime <=", value, "signTime");
            return (Criteria) this;
        }

        public Criteria andSignTimeIn(List<Date> values) {
            addCriterionForJDBCDate("signTime in", values, "signTime");
            return (Criteria) this;
        }

        public Criteria andSignTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("signTime not in", values, "signTime");
            return (Criteria) this;
        }

        public Criteria andSignTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("signTime between", value1, value2, "signTime");
            return (Criteria) this;
        }

        public Criteria andSignTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("signTime not between", value1, value2, "signTime");
            return (Criteria) this;
        }

        public Criteria andIosCidIsNull() {
            addCriterion("iosCid is null");
            return (Criteria) this;
        }

        public Criteria andIosCidIsNotNull() {
            addCriterion("iosCid is not null");
            return (Criteria) this;
        }

        public Criteria andIosCidEqualTo(String value) {
            addCriterion("iosCid =", value, "iosCid");
            return (Criteria) this;
        }

        public Criteria andIosCidNotEqualTo(String value) {
            addCriterion("iosCid <>", value, "iosCid");
            return (Criteria) this;
        }

        public Criteria andIosCidGreaterThan(String value) {
            addCriterion("iosCid >", value, "iosCid");
            return (Criteria) this;
        }

        public Criteria andIosCidGreaterThanOrEqualTo(String value) {
            addCriterion("iosCid >=", value, "iosCid");
            return (Criteria) this;
        }

        public Criteria andIosCidLessThan(String value) {
            addCriterion("iosCid <", value, "iosCid");
            return (Criteria) this;
        }

        public Criteria andIosCidLessThanOrEqualTo(String value) {
            addCriterion("iosCid <=", value, "iosCid");
            return (Criteria) this;
        }

        public Criteria andIosCidLike(String value) {
            addCriterion("iosCid like", value, "iosCid");
            return (Criteria) this;
        }

        public Criteria andIosCidNotLike(String value) {
            addCriterion("iosCid not like", value, "iosCid");
            return (Criteria) this;
        }

        public Criteria andIosCidIn(List<String> values) {
            addCriterion("iosCid in", values, "iosCid");
            return (Criteria) this;
        }

        public Criteria andIosCidNotIn(List<String> values) {
            addCriterion("iosCid not in", values, "iosCid");
            return (Criteria) this;
        }

        public Criteria andIosCidBetween(String value1, String value2) {
            addCriterion("iosCid between", value1, value2, "iosCid");
            return (Criteria) this;
        }

        public Criteria andIosCidNotBetween(String value1, String value2) {
            addCriterion("iosCid not between", value1, value2, "iosCid");
            return (Criteria) this;
        }

        public Criteria andAndroidCidIsNull() {
            addCriterion("androidCid is null");
            return (Criteria) this;
        }

        public Criteria andAndroidCidIsNotNull() {
            addCriterion("androidCid is not null");
            return (Criteria) this;
        }

        public Criteria andAndroidCidEqualTo(String value) {
            addCriterion("androidCid =", value, "androidCid");
            return (Criteria) this;
        }

        public Criteria andAndroidCidNotEqualTo(String value) {
            addCriterion("androidCid <>", value, "androidCid");
            return (Criteria) this;
        }

        public Criteria andAndroidCidGreaterThan(String value) {
            addCriterion("androidCid >", value, "androidCid");
            return (Criteria) this;
        }

        public Criteria andAndroidCidGreaterThanOrEqualTo(String value) {
            addCriterion("androidCid >=", value, "androidCid");
            return (Criteria) this;
        }

        public Criteria andAndroidCidLessThan(String value) {
            addCriterion("androidCid <", value, "androidCid");
            return (Criteria) this;
        }

        public Criteria andAndroidCidLessThanOrEqualTo(String value) {
            addCriterion("androidCid <=", value, "androidCid");
            return (Criteria) this;
        }

        public Criteria andAndroidCidLike(String value) {
            addCriterion("androidCid like", value, "androidCid");
            return (Criteria) this;
        }

        public Criteria andAndroidCidNotLike(String value) {
            addCriterion("androidCid not like", value, "androidCid");
            return (Criteria) this;
        }

        public Criteria andAndroidCidIn(List<String> values) {
            addCriterion("androidCid in", values, "androidCid");
            return (Criteria) this;
        }

        public Criteria andAndroidCidNotIn(List<String> values) {
            addCriterion("androidCid not in", values, "androidCid");
            return (Criteria) this;
        }

        public Criteria andAndroidCidBetween(String value1, String value2) {
            addCriterion("androidCid between", value1, value2, "androidCid");
            return (Criteria) this;
        }

        public Criteria andAndroidCidNotBetween(String value1, String value2) {
            addCriterion("androidCid not between", value1, value2, "androidCid");
            return (Criteria) this;
        }

        public Criteria andTotalCommentIsNull() {
            addCriterion("totalComment is null");
            return (Criteria) this;
        }

        public Criteria andTotalCommentIsNotNull() {
            addCriterion("totalComment is not null");
            return (Criteria) this;
        }

        public Criteria andTotalCommentEqualTo(Short value) {
            addCriterion("totalComment =", value, "totalComment");
            return (Criteria) this;
        }

        public Criteria andTotalCommentNotEqualTo(Short value) {
            addCriterion("totalComment <>", value, "totalComment");
            return (Criteria) this;
        }

        public Criteria andTotalCommentGreaterThan(Short value) {
            addCriterion("totalComment >", value, "totalComment");
            return (Criteria) this;
        }

        public Criteria andTotalCommentGreaterThanOrEqualTo(Short value) {
            addCriterion("totalComment >=", value, "totalComment");
            return (Criteria) this;
        }

        public Criteria andTotalCommentLessThan(Short value) {
            addCriterion("totalComment <", value, "totalComment");
            return (Criteria) this;
        }

        public Criteria andTotalCommentLessThanOrEqualTo(Short value) {
            addCriterion("totalComment <=", value, "totalComment");
            return (Criteria) this;
        }

        public Criteria andTotalCommentIn(List<Short> values) {
            addCriterion("totalComment in", values, "totalComment");
            return (Criteria) this;
        }

        public Criteria andTotalCommentNotIn(List<Short> values) {
            addCriterion("totalComment not in", values, "totalComment");
            return (Criteria) this;
        }

        public Criteria andTotalCommentBetween(Short value1, Short value2) {
            addCriterion("totalComment between", value1, value2, "totalComment");
            return (Criteria) this;
        }

        public Criteria andTotalCommentNotBetween(Short value1, Short value2) {
            addCriterion("totalComment not between", value1, value2, "totalComment");
            return (Criteria) this;
        }

        public Criteria andAvgCommentIsNull() {
            addCriterion("avgComment is null");
            return (Criteria) this;
        }

        public Criteria andAvgCommentIsNotNull() {
            addCriterion("avgComment is not null");
            return (Criteria) this;
        }

        public Criteria andAvgCommentEqualTo(BigDecimal value) {
            addCriterion("avgComment =", value, "avgComment");
            return (Criteria) this;
        }

        public Criteria andAvgCommentNotEqualTo(BigDecimal value) {
            addCriterion("avgComment <>", value, "avgComment");
            return (Criteria) this;
        }

        public Criteria andAvgCommentGreaterThan(BigDecimal value) {
            addCriterion("avgComment >", value, "avgComment");
            return (Criteria) this;
        }

        public Criteria andAvgCommentGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("avgComment >=", value, "avgComment");
            return (Criteria) this;
        }

        public Criteria andAvgCommentLessThan(BigDecimal value) {
            addCriterion("avgComment <", value, "avgComment");
            return (Criteria) this;
        }

        public Criteria andAvgCommentLessThanOrEqualTo(BigDecimal value) {
            addCriterion("avgComment <=", value, "avgComment");
            return (Criteria) this;
        }

        public Criteria andAvgCommentIn(List<BigDecimal> values) {
            addCriterion("avgComment in", values, "avgComment");
            return (Criteria) this;
        }

        public Criteria andAvgCommentNotIn(List<BigDecimal> values) {
            addCriterion("avgComment not in", values, "avgComment");
            return (Criteria) this;
        }

        public Criteria andAvgCommentBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("avgComment between", value1, value2, "avgComment");
            return (Criteria) this;
        }

        public Criteria andAvgCommentNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("avgComment not between", value1, value2, "avgComment");
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

        public Criteria andIsDrawCIsNull() {
            addCriterion("isDrawC is null");
            return (Criteria) this;
        }

        public Criteria andIsDrawCIsNotNull() {
            addCriterion("isDrawC is not null");
            return (Criteria) this;
        }

        public Criteria andIsDrawCEqualTo(Boolean value) {
            addCriterion("isDrawC =", value, "isDrawC");
            return (Criteria) this;
        }

        public Criteria andIsDrawCNotEqualTo(Boolean value) {
            addCriterion("isDrawC <>", value, "isDrawC");
            return (Criteria) this;
        }

        public Criteria andIsDrawCGreaterThan(Boolean value) {
            addCriterion("isDrawC >", value, "isDrawC");
            return (Criteria) this;
        }

        public Criteria andIsDrawCGreaterThanOrEqualTo(Boolean value) {
            addCriterion("isDrawC >=", value, "isDrawC");
            return (Criteria) this;
        }

        public Criteria andIsDrawCLessThan(Boolean value) {
            addCriterion("isDrawC <", value, "isDrawC");
            return (Criteria) this;
        }

        public Criteria andIsDrawCLessThanOrEqualTo(Boolean value) {
            addCriterion("isDrawC <=", value, "isDrawC");
            return (Criteria) this;
        }

        public Criteria andIsDrawCIn(List<Boolean> values) {
            addCriterion("isDrawC in", values, "isDrawC");
            return (Criteria) this;
        }

        public Criteria andIsDrawCNotIn(List<Boolean> values) {
            addCriterion("isDrawC not in", values, "isDrawC");
            return (Criteria) this;
        }

        public Criteria andIsDrawCBetween(Boolean value1, Boolean value2) {
            addCriterion("isDrawC between", value1, value2, "isDrawC");
            return (Criteria) this;
        }

        public Criteria andIsDrawCNotBetween(Boolean value1, Boolean value2) {
            addCriterion("isDrawC not between", value1, value2, "isDrawC");
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

        public Criteria andAmountIsNull() {
            addCriterion("amount is null");
            return (Criteria) this;
        }

        public Criteria andAmountIsNotNull() {
            addCriterion("amount is not null");
            return (Criteria) this;
        }

        public Criteria andAmountEqualTo(BigDecimal value) {
            addCriterion("amount =", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotEqualTo(BigDecimal value) {
            addCriterion("amount <>", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThan(BigDecimal value) {
            addCriterion("amount >", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("amount >=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThan(BigDecimal value) {
            addCriterion("amount <", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("amount <=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountIn(List<BigDecimal> values) {
            addCriterion("amount in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotIn(List<BigDecimal> values) {
            addCriterion("amount not in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("amount between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("amount not between", value1, value2, "amount");
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

        public Criteria andAcGroupIdIsNull() {
            addCriterion("acGroupId is null");
            return (Criteria) this;
        }

        public Criteria andAcGroupIdIsNotNull() {
            addCriterion("acGroupId is not null");
            return (Criteria) this;
        }

        public Criteria andAcGroupIdEqualTo(String value) {
            addCriterion("acGroupId =", value, "acGroupId");
            return (Criteria) this;
        }

        public Criteria andAcGroupIdNotEqualTo(String value) {
            addCriterion("acGroupId <>", value, "acGroupId");
            return (Criteria) this;
        }

        public Criteria andAcGroupIdGreaterThan(String value) {
            addCriterion("acGroupId >", value, "acGroupId");
            return (Criteria) this;
        }

        public Criteria andAcGroupIdGreaterThanOrEqualTo(String value) {
            addCriterion("acGroupId >=", value, "acGroupId");
            return (Criteria) this;
        }

        public Criteria andAcGroupIdLessThan(String value) {
            addCriterion("acGroupId <", value, "acGroupId");
            return (Criteria) this;
        }

        public Criteria andAcGroupIdLessThanOrEqualTo(String value) {
            addCriterion("acGroupId <=", value, "acGroupId");
            return (Criteria) this;
        }

        public Criteria andAcGroupIdLike(String value) {
            addCriterion("acGroupId like", value, "acGroupId");
            return (Criteria) this;
        }

        public Criteria andAcGroupIdNotLike(String value) {
            addCriterion("acGroupId not like", value, "acGroupId");
            return (Criteria) this;
        }

        public Criteria andAcGroupIdIn(List<String> values) {
            addCriterion("acGroupId in", values, "acGroupId");
            return (Criteria) this;
        }

        public Criteria andAcGroupIdNotIn(List<String> values) {
            addCriterion("acGroupId not in", values, "acGroupId");
            return (Criteria) this;
        }

        public Criteria andAcGroupIdBetween(String value1, String value2) {
            addCriterion("acGroupId between", value1, value2, "acGroupId");
            return (Criteria) this;
        }

        public Criteria andAcGroupIdNotBetween(String value1, String value2) {
            addCriterion("acGroupId not between", value1, value2, "acGroupId");
            return (Criteria) this;
        }

        public Criteria andIsFirstOrdIsNull() {
            addCriterion("isFirstOrd is null");
            return (Criteria) this;
        }

        public Criteria andIsFirstOrdIsNotNull() {
            addCriterion("isFirstOrd is not null");
            return (Criteria) this;
        }

        public Criteria andIsFirstOrdEqualTo(Boolean value) {
            addCriterion("isFirstOrd =", value, "isFirstOrd");
            return (Criteria) this;
        }

        public Criteria andIsFirstOrdNotEqualTo(Boolean value) {
            addCriterion("isFirstOrd <>", value, "isFirstOrd");
            return (Criteria) this;
        }

        public Criteria andIsFirstOrdGreaterThan(Boolean value) {
            addCriterion("isFirstOrd >", value, "isFirstOrd");
            return (Criteria) this;
        }

        public Criteria andIsFirstOrdGreaterThanOrEqualTo(Boolean value) {
            addCriterion("isFirstOrd >=", value, "isFirstOrd");
            return (Criteria) this;
        }

        public Criteria andIsFirstOrdLessThan(Boolean value) {
            addCriterion("isFirstOrd <", value, "isFirstOrd");
            return (Criteria) this;
        }

        public Criteria andIsFirstOrdLessThanOrEqualTo(Boolean value) {
            addCriterion("isFirstOrd <=", value, "isFirstOrd");
            return (Criteria) this;
        }

        public Criteria andIsFirstOrdIn(List<Boolean> values) {
            addCriterion("isFirstOrd in", values, "isFirstOrd");
            return (Criteria) this;
        }

        public Criteria andIsFirstOrdNotIn(List<Boolean> values) {
            addCriterion("isFirstOrd not in", values, "isFirstOrd");
            return (Criteria) this;
        }

        public Criteria andIsFirstOrdBetween(Boolean value1, Boolean value2) {
            addCriterion("isFirstOrd between", value1, value2, "isFirstOrd");
            return (Criteria) this;
        }

        public Criteria andIsFirstOrdNotBetween(Boolean value1, Boolean value2) {
            addCriterion("isFirstOrd not between", value1, value2, "isFirstOrd");
            return (Criteria) this;
        }

        public Criteria andFromWhoIsNull() {
            addCriterion("fromWho is null");
            return (Criteria) this;
        }

        public Criteria andFromWhoIsNotNull() {
            addCriterion("fromWho is not null");
            return (Criteria) this;
        }

        public Criteria andFromWhoEqualTo(Integer value) {
            addCriterion("fromWho =", value, "fromWho");
            return (Criteria) this;
        }

        public Criteria andFromWhoNotEqualTo(Integer value) {
            addCriterion("fromWho <>", value, "fromWho");
            return (Criteria) this;
        }

        public Criteria andFromWhoGreaterThan(Integer value) {
            addCriterion("fromWho >", value, "fromWho");
            return (Criteria) this;
        }

        public Criteria andFromWhoGreaterThanOrEqualTo(Integer value) {
            addCriterion("fromWho >=", value, "fromWho");
            return (Criteria) this;
        }

        public Criteria andFromWhoLessThan(Integer value) {
            addCriterion("fromWho <", value, "fromWho");
            return (Criteria) this;
        }

        public Criteria andFromWhoLessThanOrEqualTo(Integer value) {
            addCriterion("fromWho <=", value, "fromWho");
            return (Criteria) this;
        }

        public Criteria andFromWhoIn(List<Integer> values) {
            addCriterion("fromWho in", values, "fromWho");
            return (Criteria) this;
        }

        public Criteria andFromWhoNotIn(List<Integer> values) {
            addCriterion("fromWho not in", values, "fromWho");
            return (Criteria) this;
        }

        public Criteria andFromWhoBetween(Integer value1, Integer value2) {
            addCriterion("fromWho between", value1, value2, "fromWho");
            return (Criteria) this;
        }

        public Criteria andFromWhoNotBetween(Integer value1, Integer value2) {
            addCriterion("fromWho not between", value1, value2, "fromWho");
            return (Criteria) this;
        }

        public Criteria andInviteIdIsNull() {
            addCriterion("inviteId is null");
            return (Criteria) this;
        }

        public Criteria andInviteIdIsNotNull() {
            addCriterion("inviteId is not null");
            return (Criteria) this;
        }

        public Criteria andInviteIdEqualTo(String value) {
            addCriterion("inviteId =", value, "inviteId");
            return (Criteria) this;
        }

        public Criteria andInviteIdNotEqualTo(String value) {
            addCriterion("inviteId <>", value, "inviteId");
            return (Criteria) this;
        }

        public Criteria andInviteIdGreaterThan(String value) {
            addCriterion("inviteId >", value, "inviteId");
            return (Criteria) this;
        }

        public Criteria andInviteIdGreaterThanOrEqualTo(String value) {
            addCriterion("inviteId >=", value, "inviteId");
            return (Criteria) this;
        }

        public Criteria andInviteIdLessThan(String value) {
            addCriterion("inviteId <", value, "inviteId");
            return (Criteria) this;
        }

        public Criteria andInviteIdLessThanOrEqualTo(String value) {
            addCriterion("inviteId <=", value, "inviteId");
            return (Criteria) this;
        }

        public Criteria andInviteIdLike(String value) {
            addCriterion("inviteId like", value, "inviteId");
            return (Criteria) this;
        }

        public Criteria andInviteIdNotLike(String value) {
            addCriterion("inviteId not like", value, "inviteId");
            return (Criteria) this;
        }

        public Criteria andInviteIdIn(List<String> values) {
            addCriterion("inviteId in", values, "inviteId");
            return (Criteria) this;
        }

        public Criteria andInviteIdNotIn(List<String> values) {
            addCriterion("inviteId not in", values, "inviteId");
            return (Criteria) this;
        }

        public Criteria andInviteIdBetween(String value1, String value2) {
            addCriterion("inviteId between", value1, value2, "inviteId");
            return (Criteria) this;
        }

        public Criteria andInviteIdNotBetween(String value1, String value2) {
            addCriterion("inviteId not between", value1, value2, "inviteId");
            return (Criteria) this;
        }

        public Criteria andSequenceNumIsNull() {
            addCriterion("sequenceNum is null");
            return (Criteria) this;
        }

        public Criteria andSequenceNumIsNotNull() {
            addCriterion("sequenceNum is not null");
            return (Criteria) this;
        }

        public Criteria andSequenceNumEqualTo(Integer value) {
            addCriterion("sequenceNum =", value, "sequenceNum");
            return (Criteria) this;
        }

        public Criteria andSequenceNumNotEqualTo(Integer value) {
            addCriterion("sequenceNum <>", value, "sequenceNum");
            return (Criteria) this;
        }

        public Criteria andSequenceNumGreaterThan(Integer value) {
            addCriterion("sequenceNum >", value, "sequenceNum");
            return (Criteria) this;
        }

        public Criteria andSequenceNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("sequenceNum >=", value, "sequenceNum");
            return (Criteria) this;
        }

        public Criteria andSequenceNumLessThan(Integer value) {
            addCriterion("sequenceNum <", value, "sequenceNum");
            return (Criteria) this;
        }

        public Criteria andSequenceNumLessThanOrEqualTo(Integer value) {
            addCriterion("sequenceNum <=", value, "sequenceNum");
            return (Criteria) this;
        }

        public Criteria andSequenceNumIn(List<Integer> values) {
            addCriterion("sequenceNum in", values, "sequenceNum");
            return (Criteria) this;
        }

        public Criteria andSequenceNumNotIn(List<Integer> values) {
            addCriterion("sequenceNum not in", values, "sequenceNum");
            return (Criteria) this;
        }

        public Criteria andSequenceNumBetween(Integer value1, Integer value2) {
            addCriterion("sequenceNum between", value1, value2, "sequenceNum");
            return (Criteria) this;
        }

        public Criteria andSequenceNumNotBetween(Integer value1, Integer value2) {
            addCriterion("sequenceNum not between", value1, value2, "sequenceNum");
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