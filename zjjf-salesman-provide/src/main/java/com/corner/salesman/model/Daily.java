package com.corner.salesman.model;

import java.util.List;

import com.corner.salesman.common.persistence.BaseEntity;

public class Daily extends BaseEntity<Daily> {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	List<DailyReply> replyList = null;//回复列表信息

	private String reportId;

    private String visitLine;

    private String distributionCenter;

    private String lineStoreNum;

    private String visitStoreNum;

    private String visitStoreReapNum;

    private String planRegisterNum;

    private String actualRegisterlNum;

    private String yetDevelopStoreNum;

    private String registerlStoreRatio;

    private String planActiveStoreNum;

    private String actualActiveStoreNum;

    private String ownActiveStoreNum;

    private String lineActiveStoreRatio;

    private String planDevelopHF;

    private String actualDevelopHF;

    private String lineYetStoreHFNum;

    private String lineStoreHFRatio;

    private String extraStoreRegisterNum;

    private String extraStoreActiveNum;

    private String extraStoreHFNum;

    private String planOrderNum;

    private String actualOrderNum;

    private String turnover;

    private String buyTurnover;

    private String salesTurnover;

    private String dgWeekRegisterStoreNum;

    private String dgUpWeekRegisterStoreNum;

    private String dgWeekDevelopActiveNum;

    private String dgUpWeekDevelopActiveNum;

    private String dgWeekDevelopStoreHFNum;

    private String dgUpWeekOwnStoreHFNum;

    private String dgWeekPlanBuyNum;

    private String dgWeekActualBuyNum;

    private String dgWeekPlanTurnover;

    private String dgWeekActualTurnover;

    private String nextDayPlanRegisterNum;

    private String nextDayPlanAddActiveNum;

    private String nextDayPlanWhActiveNum;

    private String nextDayPlanAddHFNum;

    private String nextDayPlanWhHFNum;

    private String nextDayOrderStoreNum;

    private String nextDayPlanTurnover;

    private String nextDaySalesTurnover;

    private String nextDayBuyTurnover;

    private String feedback;

    private String strategyPlan;

    private String support;

    private String remark;

    private String picUrl;

    private String createBy;

    private String createTime;

    private String updateBy;

    private String updateTime;

    private String deptId;

    private String week;
    
    private String timePoint;
    
    private String postName;
    
    private String todayPlanWhActiveNum;
    private String todayActualWhActiveNum;
    private String todayLineTurnover;
    private String relativeYesterOrderNum;
    private String todayVisitPartners;
    private String relativeYesterTurnover;
    private String relativeYesterBuyTurnover;
    private String nextDayPlanVisitPartners;
    private String nextDayVisitLine;
    private String todayPlanLineTurnover;
    private String todayPlanTurnover;

    
    public String getTodayPlanLineTurnover() {
		return todayPlanLineTurnover;
	}

	public void setTodayPlanLineTurnover(String todayPlanLineTurnover) {
		this.todayPlanLineTurnover = todayPlanLineTurnover;
	}

	public String getTodayPlanTurnover() {
		return todayPlanTurnover;
	}

	public void setTodayPlanTurnover(String todayPlanTurnover) {
		this.todayPlanTurnover = todayPlanTurnover;
	}

	public List<DailyReply> getReplyList() {
		return replyList;
	}

	public void setReplyList(List<DailyReply> replyList) {
		this.replyList = replyList;
	}

	public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId == null ? null : reportId.trim();
    }

    public String getVisitLine() {
        return visitLine;
    }

    public void setVisitLine(String visitLine) {
        this.visitLine = visitLine == null ? null : visitLine.trim();
    }

    public String getDistributionCenter() {
        return distributionCenter;
    }

    public void setDistributionCenter(String distributionCenter) {
        this.distributionCenter = distributionCenter == null ? null : distributionCenter.trim();
    }

    public String getLineStoreNum() {
        return lineStoreNum;
    }

    public void setLineStoreNum(String lineStoreNum) {
        this.lineStoreNum = lineStoreNum == null ? null : lineStoreNum.trim();
    }

    public String getVisitStoreNum() {
        return visitStoreNum;
    }

    public void setVisitStoreNum(String visitStoreNum) {
        this.visitStoreNum = visitStoreNum == null ? null : visitStoreNum.trim();
    }

    public String getVisitStoreReapNum() {
        return visitStoreReapNum;
    }

    public void setVisitStoreReapNum(String visitStoreReapNum) {
        this.visitStoreReapNum = visitStoreReapNum == null ? null : visitStoreReapNum.trim();
    }

    public String getPlanRegisterNum() {
        return planRegisterNum;
    }

    public void setPlanRegisterNum(String planRegisterNum) {
        this.planRegisterNum = planRegisterNum == null ? null : planRegisterNum.trim();
    }

    public String getActualRegisterlNum() {
        return actualRegisterlNum;
    }

    public void setActualRegisterlNum(String actualRegisterlNum) {
        this.actualRegisterlNum = actualRegisterlNum == null ? null : actualRegisterlNum.trim();
    }

    public String getYetDevelopStoreNum() {
        return yetDevelopStoreNum;
    }

    public void setYetDevelopStoreNum(String yetDevelopStoreNum) {
        this.yetDevelopStoreNum = yetDevelopStoreNum == null ? null : yetDevelopStoreNum.trim();
    }

    public String getRegisterlStoreRatio() {
        return registerlStoreRatio;
    }

    public void setRegisterlStoreRatio(String registerlStoreRatio) {
        this.registerlStoreRatio = registerlStoreRatio == null ? null : registerlStoreRatio.trim();
    }

    public String getPlanActiveStoreNum() {
        return planActiveStoreNum;
    }

    public void setPlanActiveStoreNum(String planActiveStoreNum) {
        this.planActiveStoreNum = planActiveStoreNum == null ? null : planActiveStoreNum.trim();
    }

    public String getActualActiveStoreNum() {
        return actualActiveStoreNum;
    }

    public void setActualActiveStoreNum(String actualActiveStoreNum) {
        this.actualActiveStoreNum = actualActiveStoreNum == null ? null : actualActiveStoreNum.trim();
    }

    public String getOwnActiveStoreNum() {
        return ownActiveStoreNum;
    }

    public void setOwnActiveStoreNum(String ownActiveStoreNum) {
        this.ownActiveStoreNum = ownActiveStoreNum == null ? null : ownActiveStoreNum.trim();
    }

    public String getLineActiveStoreRatio() {
        return lineActiveStoreRatio;
    }

    public void setLineActiveStoreRatio(String lineActiveStoreRatio) {
        this.lineActiveStoreRatio = lineActiveStoreRatio == null ? null : lineActiveStoreRatio.trim();
    }

    public String getPlanDevelopHF() {
        return planDevelopHF;
    }

    public void setPlanDevelopHF(String planDevelopHF) {
        this.planDevelopHF = planDevelopHF == null ? null : planDevelopHF.trim();
    }

    public String getActualDevelopHF() {
        return actualDevelopHF;
    }

    public void setActualDevelopHF(String actualDevelopHF) {
        this.actualDevelopHF = actualDevelopHF == null ? null : actualDevelopHF.trim();
    }

    public String getLineYetStoreHFNum() {
        return lineYetStoreHFNum;
    }

    public void setLineYetStoreHFNum(String lineYetStoreHFNum) {
        this.lineYetStoreHFNum = lineYetStoreHFNum == null ? null : lineYetStoreHFNum.trim();
    }

    public String getLineStoreHFRatio() {
        return lineStoreHFRatio;
    }

    public void setLineStoreHFRatio(String lineStoreHFRatio) {
        this.lineStoreHFRatio = lineStoreHFRatio == null ? null : lineStoreHFRatio.trim();
    }

    public String getExtraStoreRegisterNum() {
        return extraStoreRegisterNum;
    }

    public void setExtraStoreRegisterNum(String extraStoreRegisterNum) {
        this.extraStoreRegisterNum = extraStoreRegisterNum == null ? null : extraStoreRegisterNum.trim();
    }

    public String getExtraStoreActiveNum() {
        return extraStoreActiveNum;
    }

    public void setExtraStoreActiveNum(String extraStoreActiveNum) {
        this.extraStoreActiveNum = extraStoreActiveNum == null ? null : extraStoreActiveNum.trim();
    }

    public String getExtraStoreHFNum() {
        return extraStoreHFNum;
    }

    public void setExtraStoreHFNum(String extraStoreHFNum) {
        this.extraStoreHFNum = extraStoreHFNum == null ? null : extraStoreHFNum.trim();
    }

    public String getPlanOrderNum() {
        return planOrderNum;
    }

    public void setPlanOrderNum(String planOrderNum) {
        this.planOrderNum = planOrderNum == null ? null : planOrderNum.trim();
    }

    public String getActualOrderNum() {
        return actualOrderNum;
    }

    public void setActualOrderNum(String actualOrderNum) {
        this.actualOrderNum = actualOrderNum == null ? null : actualOrderNum.trim();
    }

    public String getTurnover() {
        return turnover;
    }

    public void setTurnover(String turnover) {
        this.turnover = turnover == null ? null : turnover.trim();
    }

    public String getBuyTurnover() {
        return buyTurnover;
    }

    public void setBuyTurnover(String buyTurnover) {
        this.buyTurnover = buyTurnover == null ? null : buyTurnover.trim();
    }

    public String getSalesTurnover() {
        return salesTurnover;
    }

    public void setSalesTurnover(String salesTurnover) {
        this.salesTurnover = salesTurnover == null ? null : salesTurnover.trim();
    }

    public String getDgWeekRegisterStoreNum() {
        return dgWeekRegisterStoreNum;
    }

    public void setDgWeekRegisterStoreNum(String dgWeekRegisterStoreNum) {
        this.dgWeekRegisterStoreNum = dgWeekRegisterStoreNum == null ? null : dgWeekRegisterStoreNum.trim();
    }

    public String getDgUpWeekRegisterStoreNum() {
        return dgUpWeekRegisterStoreNum;
    }

    public void setDgUpWeekRegisterStoreNum(String dgUpWeekRegisterStoreNum) {
        this.dgUpWeekRegisterStoreNum = dgUpWeekRegisterStoreNum == null ? null : dgUpWeekRegisterStoreNum.trim();
    }

    public String getDgWeekDevelopActiveNum() {
        return dgWeekDevelopActiveNum;
    }

    public void setDgWeekDevelopActiveNum(String dgWeekDevelopActiveNum) {
        this.dgWeekDevelopActiveNum = dgWeekDevelopActiveNum == null ? null : dgWeekDevelopActiveNum.trim();
    }

    public String getDgUpWeekDevelopActiveNum() {
        return dgUpWeekDevelopActiveNum;
    }

    public void setDgUpWeekDevelopActiveNum(String dgUpWeekDevelopActiveNum) {
        this.dgUpWeekDevelopActiveNum = dgUpWeekDevelopActiveNum == null ? null : dgUpWeekDevelopActiveNum.trim();
    }

    public String getDgWeekDevelopStoreHFNum() {
        return dgWeekDevelopStoreHFNum;
    }

    public void setDgWeekDevelopStoreHFNum(String dgWeekDevelopStoreHFNum) {
        this.dgWeekDevelopStoreHFNum = dgWeekDevelopStoreHFNum == null ? null : dgWeekDevelopStoreHFNum.trim();
    }

    public String getDgUpWeekOwnStoreHFNum() {
        return dgUpWeekOwnStoreHFNum;
    }

    public void setDgUpWeekOwnStoreHFNum(String dgUpWeekOwnStoreHFNum) {
        this.dgUpWeekOwnStoreHFNum = dgUpWeekOwnStoreHFNum == null ? null : dgUpWeekOwnStoreHFNum.trim();
    }

    public String getDgWeekPlanBuyNum() {
        return dgWeekPlanBuyNum;
    }

    public void setDgWeekPlanBuyNum(String dgWeekPlanBuyNum) {
        this.dgWeekPlanBuyNum = dgWeekPlanBuyNum == null ? null : dgWeekPlanBuyNum.trim();
    }

    public String getDgWeekActualBuyNum() {
        return dgWeekActualBuyNum;
    }

    public void setDgWeekActualBuyNum(String dgWeekActualBuyNum) {
        this.dgWeekActualBuyNum = dgWeekActualBuyNum == null ? null : dgWeekActualBuyNum.trim();
    }

    public String getDgWeekPlanTurnover() {
        return dgWeekPlanTurnover;
    }

    public void setDgWeekPlanTurnover(String dgWeekPlanTurnover) {
        this.dgWeekPlanTurnover = dgWeekPlanTurnover == null ? null : dgWeekPlanTurnover.trim();
    }

    public String getDgWeekActualTurnover() {
        return dgWeekActualTurnover;
    }

    public void setDgWeekActualTurnover(String dgWeekActualTurnover) {
        this.dgWeekActualTurnover = dgWeekActualTurnover == null ? null : dgWeekActualTurnover.trim();
    }

    public String getNextDayPlanRegisterNum() {
        return nextDayPlanRegisterNum;
    }

    public void setNextDayPlanRegisterNum(String nextDayPlanRegisterNum) {
        this.nextDayPlanRegisterNum = nextDayPlanRegisterNum == null ? null : nextDayPlanRegisterNum.trim();
    }

    public String getNextDayPlanAddActiveNum() {
        return nextDayPlanAddActiveNum;
    }

    public void setNextDayPlanAddActiveNum(String nextDayPlanAddActiveNum) {
        this.nextDayPlanAddActiveNum = nextDayPlanAddActiveNum == null ? null : nextDayPlanAddActiveNum.trim();
    }

    public String getNextDayPlanWhActiveNum() {
        return nextDayPlanWhActiveNum;
    }

    public void setNextDayPlanWhActiveNum(String nextDayPlanWhActiveNum) {
        this.nextDayPlanWhActiveNum = nextDayPlanWhActiveNum == null ? null : nextDayPlanWhActiveNum.trim();
    }

    public String getNextDayPlanAddHFNum() {
        return nextDayPlanAddHFNum;
    }

    public void setNextDayPlanAddHFNum(String nextDayPlanAddHFNum) {
        this.nextDayPlanAddHFNum = nextDayPlanAddHFNum == null ? null : nextDayPlanAddHFNum.trim();
    }

    public String getNextDayPlanWhHFNum() {
        return nextDayPlanWhHFNum;
    }

    public void setNextDayPlanWhHFNum(String nextDayPlanWhHFNum) {
        this.nextDayPlanWhHFNum = nextDayPlanWhHFNum == null ? null : nextDayPlanWhHFNum.trim();
    }

    public String getNextDayOrderStoreNum() {
        return nextDayOrderStoreNum;
    }

    public void setNextDayOrderStoreNum(String nextDayOrderStoreNum) {
        this.nextDayOrderStoreNum = nextDayOrderStoreNum == null ? null : nextDayOrderStoreNum.trim();
    }

    public String getNextDayPlanTurnover() {
        return nextDayPlanTurnover;
    }

    public void setNextDayPlanTurnover(String nextDayPlanTurnover) {
        this.nextDayPlanTurnover = nextDayPlanTurnover == null ? null : nextDayPlanTurnover.trim();
    }

    public String getNextDaySalesTurnover() {
        return nextDaySalesTurnover;
    }

    public void setNextDaySalesTurnover(String nextDaySalesTurnover) {
        this.nextDaySalesTurnover = nextDaySalesTurnover == null ? null : nextDaySalesTurnover.trim();
    }

    public String getNextDayBuyTurnover() {
        return nextDayBuyTurnover;
    }

    public void setNextDayBuyTurnover(String nextDayBuyTurnover) {
        this.nextDayBuyTurnover = nextDayBuyTurnover == null ? null : nextDayBuyTurnover.trim();
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback == null ? null : feedback.trim();
    }

    public String getStrategyPlan() {
        return strategyPlan;
    }

    public void setStrategyPlan(String strategyPlan) {
        this.strategyPlan = strategyPlan == null ? null : strategyPlan.trim();
    }

    public String getSupport() {
        return support;
    }

    public void setSupport(String support) {
        this.support = support == null ? null : support.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl == null ? null : picUrl.trim();
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId == null ? null : deptId.trim();
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week == null ? null : week.trim();
    }

	public String getTimePoint() {
		return timePoint;
	}

	public void setTimePoint(String timePoint) {
		this.timePoint = timePoint;
	}

	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}

	public String getTodayPlanWhActiveNum() {
		return todayPlanWhActiveNum;
	}

	public void setTodayPlanWhActiveNum(String todayPlanWhActiveNum) {
		this.todayPlanWhActiveNum = todayPlanWhActiveNum;
	}

	public String getTodayActualWhActiveNum() {
		return todayActualWhActiveNum;
	}

	public void setTodayActualWhActiveNum(String todayActualWhActiveNum) {
		this.todayActualWhActiveNum = todayActualWhActiveNum;
	}

	public String getTodayLineTurnover() {
		return todayLineTurnover;
	}

	public void setTodayLineTurnover(String todayLineTurnover) {
		this.todayLineTurnover = todayLineTurnover;
	}

	public String getRelativeYesterOrderNum() {
		return relativeYesterOrderNum;
	}

	public void setRelativeYesterOrderNum(String relativeYesterOrderNum) {
		this.relativeYesterOrderNum = relativeYesterOrderNum;
	}

	public String getTodayVisitPartners() {
		return todayVisitPartners;
	}

	public void setTodayVisitPartners(String todayVisitPartners) {
		this.todayVisitPartners = todayVisitPartners;
	}

	public String getRelativeYesterTurnover() {
		return relativeYesterTurnover;
	}

	public void setRelativeYesterTurnover(String relativeYesterTurnover) {
		this.relativeYesterTurnover = relativeYesterTurnover;
	}

	public String getRelativeYesterBuyTurnover() {
		return relativeYesterBuyTurnover;
	}

	public void setRelativeYesterBuyTurnover(String relativeYesterBuyTurnover) {
		this.relativeYesterBuyTurnover = relativeYesterBuyTurnover;
	}

	public String getNextDayPlanVisitPartners() {
		return nextDayPlanVisitPartners;
	}

	public void setNextDayPlanVisitPartners(String nextDayPlanVisitPartners) {
		this.nextDayPlanVisitPartners = nextDayPlanVisitPartners;
	}

	public String getNextDayVisitLine() {
		return nextDayVisitLine;
	}

	public void setNextDayVisitLine(String nextDayVisitLine) {
		this.nextDayVisitLine = nextDayVisitLine;
	}
    
}