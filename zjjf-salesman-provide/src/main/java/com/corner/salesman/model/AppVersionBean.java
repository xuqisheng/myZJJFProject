package com.corner.salesman.model;


public class AppVersionBean{

	private String name;
	private String version;
	private String changelog;
	private String updatedTime;
	private String versionShort;
	private String build;
	private String installUrl;
	private String updateUrl;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getChangelog() {
		return changelog;
	}
	public void setChangelog(String changelog) {
		this.changelog = changelog;
	}
	public String getVersionShort() {
		return versionShort;
	}
	public void setVersionShort(String versionShort) {
		this.versionShort = versionShort;
	}
	public String getBuild() {
		return build;
	}
	public void setBuild(String build) {
		this.build = build;
	}
	public String getInstallUrl() {
		return installUrl;
	}
	public void setInstallUrl(String installUrl) {
		this.installUrl = installUrl;
	}
	public String getUpdateUrl() {
		return updateUrl;
	}
	public void setUpdateUrl(String updateUrl) {
		this.updateUrl = updateUrl;
	}
	public String getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(String updatedTime) {
		this.updatedTime = updatedTime;
	}
}