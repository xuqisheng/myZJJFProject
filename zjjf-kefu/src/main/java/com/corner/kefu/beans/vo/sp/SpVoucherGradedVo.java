package com.corner.kefu.beans.vo.sp;

import com.corner.core.beans.SpVoucherGraded;
import com.corner.core.beans.SpVoucherTemp;

import java.util.List;

public class SpVoucherGradedVo extends SpVoucherGraded {
	private SpVoucherTemp spVoucherTemp;

	private List<SpVoucherTemp> spVoucherTempList;

	public List<SpVoucherTemp> getSpVoucherTempList() {
		return spVoucherTempList;
	}

	public void setSpVoucherTempList(List<SpVoucherTemp> spVoucherTempList) {
		this.spVoucherTempList = spVoucherTempList;
	}

	public SpVoucherTemp getSpVoucherTemp() {
		return spVoucherTemp;
	}

	public void setSpVoucherTemp(SpVoucherTemp spVoucherTemp) {
		this.spVoucherTemp = spVoucherTemp;
	}

}
