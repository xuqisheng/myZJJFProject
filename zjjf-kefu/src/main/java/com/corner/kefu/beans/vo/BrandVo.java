package com.corner.kefu.beans.vo;

import java.io.Serializable;
import java.util.Date;

import com.corner.core.beans.Brand;


public class BrandVo extends Brand implements Serializable {

		private Integer s_id;

	    private String s_brandNo;

	    private String s_name;

	    private String s_remark;

	    private Integer s_upId;

	    private Byte s_xLevel;

	    private Byte s_xType;

	    private Date s_createTime;

	    private Date s_updateTime;

	    private Byte s_status;

	    private Boolean s_isDelete;

		public Integer getS_id() {
			return s_id;
		}

		public void setS_id(Integer s_id) {
			this.s_id = s_id;
		}

		public String getS_brandNo() {
			return s_brandNo;
		}

		public void setS_brandNo(String s_brandNo) {
			this.s_brandNo = s_brandNo;
		}

		public String getS_name() {
			return s_name;
		}

		public void setS_name(String s_name) {
			this.s_name = s_name;
		}

		public String getS_remark() {
			return s_remark;
		}

		public void setS_remark(String s_remark) {
			this.s_remark = s_remark;
		}

		public Integer getS_upId() {
			return s_upId;
		}

		public void setS_upId(Integer s_upId) {
			this.s_upId = s_upId;
		}

		public Byte getS_xLevel() {
			return s_xLevel;
		}

		public void setS_xLevel(Byte s_xLevel) {
			this.s_xLevel = s_xLevel;
		}

		public Byte getS_xType() {
			return s_xType;
		}

		public void setS_xType(Byte s_xType) {
			this.s_xType = s_xType;
		}

		public Date getS_createTime() {
			return s_createTime;
		}

		public void setS_createTime(Date s_createTime) {
			this.s_createTime = s_createTime;
		}

		public Date getS_updateTime() {
			return s_updateTime;
		}

		public void setS_updateTime(Date s_updateTime) {
			this.s_updateTime = s_updateTime;
		}

		public Byte getS_status() {
			return s_status;
		}

		public void setS_status(Byte s_status) {
			this.s_status = s_status;
		}

		public Boolean getS_isDelete() {
			return s_isDelete;
		}

		public void setS_isDelete(Boolean s_isDelete) {
			this.s_isDelete = s_isDelete;
		}

	
}
