package cn.edu.tit.bean;

import java.sql.Timestamp;

/**
 * 申请人家庭成员
 * @author 20586
 *
 */
public class ApplyFamily {

	private String applyId ;// 对应报名信息id
	private String appellation; //称谓
	private String name; // 姓名
	private Timestamp birth; //出生日
	private String politicsStatus; // 政治面貌
	private String organization; // 工作单位
	private String position; // 职位
	
	
	
	public String getApplyId() {
		return applyId;
	}
	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}
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
	public Timestamp getBirth() {
		return birth;
	}
	public void setBirth(Timestamp birth) {
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
