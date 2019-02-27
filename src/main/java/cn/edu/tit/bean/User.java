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
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", wechartNum=" + wechartNum
				+ ", organizationName=" + organizationName + ", organizationId=" + organizationId + ", password="
				+ password + "]";
	}
	public User(String userId, String userName, String wechartNum, String organizationName, String organizationId,
			String password) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.wechartNum = wechartNum;
		this.organizationName = organizationName;
		this.organizationId = organizationId;
		this.password = password;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
