package com.corner.salesman.model;

import java.util.Date;

import com.corner.salesman.common.persistence.DataEntity;

public class User extends DataEntity<User> {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

    private String userName;

    private String nickName;

    private Integer gender;

    private String password;
    
    private String newPwd;//新密码
    
    private String confirmPwd;//确认密码

    private String mobile;

    private String email;

    private String lastIP;

    private Date lastTime;

    private Integer isService;

    private Integer postId;
    private String postType;
    private String postName;//岗位名称

    private String createrId;

    private Byte status;

    private Integer isDelete;
    private String deviceName; //设备名称
    private String sessionid;//会话ID
    private String token;//凭证号
    private String userType;//用户类型（0：普通用户;1:管理员）
    private String timeHz;//时间频率（设定获取百度坐标的时间频率）
    private Integer signStart;//上班签到标记（0：为签到；1：签到）
    private Integer signEnd;//下班签到标记（0：为签到；1：签到）
    private String version;//app版本后
    private String bdType;//是否为业务员（0：不是；1：是；）
    
	public String getBdType() {
		return bdType;
	}
	public void setBdType(String bdType) {
		this.bdType = bdType;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public Integer getSignStart() {
		return signStart;
	}
	public void setSignStart(Integer signStart) {
		this.signStart = signStart;
	}
	public Integer getSignEnd() {
		return signEnd;
	}
	public void setSignEnd(Integer signEnd) {
		this.signEnd = signEnd;
	}
	public String getTimeHz() {
		return timeHz;
	}
	public void setTimeHz(String timeHz) {
		this.timeHz = timeHz;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getSessionid() {
		return sessionid;
	}
	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getLastIP() {
        return lastIP;
    }

    public void setLastIP(String lastIP) {
        this.lastIP = lastIP == null ? null : lastIP.trim();
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public Integer getIsService() {
        return isService;
    }

    public void setIsService(Integer isService) {
        this.isService = isService;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getCreaterId() {
        return createrId;
    }

    public void setCreaterId(String createrId) {
        this.createrId = createrId == null ? null : createrId.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
	public String getNewPwd() {
		return newPwd;
	}
	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}
	public String getConfirmPwd() {
		return confirmPwd;
	}
	public void setConfirmPwd(String confirmPwd) {
		this.confirmPwd = confirmPwd;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getPostName() {
		return postName;
	}
	public void setPostName(String postName) {
		this.postName = postName;
	}
	public String getPostType() {
		return postType;
	}
	public void setPostType(String postType) {
		this.postType = postType;
	}
	
}