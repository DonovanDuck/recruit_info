/**
 * 
 */
package cn.edu.tit.bean;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * 招聘信息表
 */
public class RecruitInfo {
	private String recruitId; //id
	private String organization; //单位
	private String recruitInfo; //招聘信息
	private String accessory; //附件
	private Timestamp startTime; //开始时间
	private Timestamp endTime; //截止时间
	private String publisher; //发布人
	private Timestamp publishTime; //发布时间
	
	
	public String getRecruitId() {
		return recruitId;
	}
	public void setRecruitId(String recruitId) {
		this.recruitId = recruitId;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getRecruitInfo() {
		return recruitInfo;
	}
	public void setRecruitInfo(String recruitInfo) {
		this.recruitInfo = recruitInfo;
	}
	public String getAccessory() {
		return accessory;
	}
	public void setAccessory(String accessory) {
		this.accessory = accessory;
	}
	public Timestamp getStartTime() {
		return startTime;
	}
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public Timestamp getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(Timestamp publishTime) {
		this.publishTime = publishTime;
	}
	@Override
	public String toString() {
		return "RecruitInfo [recruitId=" + recruitId + ", organization=" + organization + ", recruitInfo=" + recruitInfo
				+ ", accessory=" + accessory + ", startTime=" + startTime + ", endTime=" + endTime + ", publisher="
				+ publisher + ", publishTime=" + publishTime + "]";
	}
	public RecruitInfo(String recruitId, String organization, String recruitInfo, String accessory, Timestamp startTime,
			Timestamp endTime, String publisher, Timestamp publishTime) {
		super();
		this.recruitId = recruitId;
		this.organization = organization;
		this.recruitInfo = recruitInfo;
		this.accessory = accessory;
		this.startTime = startTime;
		this.endTime = endTime;
		this.publisher = publisher;
		this.publishTime = publishTime;
	}
	public RecruitInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
