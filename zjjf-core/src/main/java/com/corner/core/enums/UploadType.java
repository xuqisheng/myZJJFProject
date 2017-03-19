package com.corner.core.enums;

/**
 * 上传附件类型
 * 
 * @author jason at 2014年12月3日下午8:28:22
 * @Email jason@mibodoctor.com
 * @Desc
 */
public enum UploadType {
	// 包含了枚举的中文名称, 枚举的索引值
	Doctor_Register("医生注册", 0), Patient_Case("患者病历", 1), User_Pic("用户头像", 3), ARTICLE_PIC("文章图片", 4), PHOTO("相册图片", 5), HEALTHPLAN_PIC("健康计划", 6), MEDICALREPORT(
			"体检报告", 7), Chat("用户头像", 8);

	private String name;
	private int index;

	private UploadType(String name, int index) {
		this.name = name;
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
