package cn.edu.tit.bean;

/**
 * @author LiMing
 * 用户实体类
 */
public class User {

	private String userId; //ID
	private String userName; // 用户名
	private String wechartNum; // 微信号
	private String organizationName; //单位名
	private String organizationId;//单位ID
	private String password; //密码
	private String phoneNum; //电话
	private Integer authority; // 权限 默认可查看用户信息0，可添加信息1
	
	
	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getWechartNum() {
		return wechartNum;
	}


	public void setWechartNum(String wechartNum) {
		this.wechartNum = wechartNum;
	}


	public String getOrganizationName() {
		return organizationName;
	}


	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}


	public String getOrganizationId() {
		return organizationId;
	}


	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getPhoneNum() {
		return phoneNum;
	}


	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}


	public Integer getAuthority() {
		return authority;
	}


	public void setAuthority(Integer authority) {
		this.authority = authority;
	}


	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
