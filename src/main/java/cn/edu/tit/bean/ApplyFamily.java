package cn.edu.tit.bean;

import java.util.Date;

/**
 * 申请人家庭成员
 * @author 20586
 *
 */
public class ApplyFamily {

	private String appellation; //称谓
	private String name; // 姓名
	private Date birth; //出生日
	private String politicsStatus; // 政治面貌
	private String organization; // 工作单位
	private String position; // 职位
	public String getAppellation() {
		return appellation;
	}
	public void setAppellation(String appellation) {
		this.appellation = appellation;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public String getPoliticsStatus() {
		return politicsStatus;
	}
	public void setPoliticsStatus(String politicsStatus) {
		this.politicsStatus = politicsStatus;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	
	
}
