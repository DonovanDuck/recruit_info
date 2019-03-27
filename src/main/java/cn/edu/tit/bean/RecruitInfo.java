/**
 * 
 */
package cn.edu.tit.bean;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * 招聘信息表
 */
public class RecruitInfo {
	private String recruitId; //id
	private String organization; //单位

	private String recruitInfo; //招聘标题

	private String accessory; //附件
	private Timestamp startTime; //开始时间
	private Timestamp endTime; //截止时间
	private String publisher; //发布人
	private Timestamp publishTime; //发布时间
	private String publisherName; //发布人名
	private String organizatinName; //公司名
	private List<Position> position; //招聘职位信息
	private int flag; //小程序页面判断标志位
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
	public String getPublisherName() {
		return publisherName;
	}
	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}
	public String getOrganizatinName() {
		return organizatinName;
	}
	public void setOrganizatinName(String organizatinName) {
		this.organizatinName = organizatinName;
	}
	public List<Position> getPosition() {
		return position;
	}
	public void setPosition(List<Position> position) {
		this.position = position;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	@Override
	public String toString() {
		return "RecruitInfo [recruitId=" + recruitId + ", organization=" + organization + ", recruitInfo=" + recruitInfo
				+ ", accessory=" + accessory + ", startTime=" + startTime + ", endTime=" + endTime + ", publisher="
				+ publisher + ", publishTime=" + publishTime + ", publisherName=" + publisherName + ", organizatinName="
				+ organizatinName + ", position=" + position + ", flag=" + flag + "]";
	}
	public RecruitInfo(String recruitId, String organization, String recruitInfo, String accessory, Timestamp startTime,
			Timestamp endTime, String publisher, Timestamp publishTime, String publisherName, String organizatinName,
			List<Position> position, int flag) {
		super();
		this.recruitId = recruitId;
		this.organization = organization;
		this.recruitInfo = recruitInfo;
		this.accessory = accessory;
		this.startTime = startTime;
		this.endTime = endTime;
		this.publisher = publisher;
		this.publishTime = publishTime;
		this.publisherName = publisherName;
		this.organizatinName = organizatinName;
		this.position = position;
		this.flag = flag;
	}
	public RecruitInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
