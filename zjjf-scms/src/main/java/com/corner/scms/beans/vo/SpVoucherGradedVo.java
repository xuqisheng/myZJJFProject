package com.corner.scms.beans.vo;



import com.corner.core.beans.SpVoucherGraded;
import com.corner.core.beans.SpVoucherTemp;

public class SpVoucherGradedVo extends SpVoucherGraded {
	private SpVoucherTemp spVoucherTemp;

	public SpVoucherTemp getSpVoucherTemp() {
		return spVoucherTemp;
	}

	public void setSpVoucherTemp(SpVoucherTemp spVoucherTemp) {
		this.spVoucherTemp = spVoucherTemp;
	}

}
