/**
 * 
 */
package cn.edu.tit.bean;

/**
 * @author LiMing
 * 招聘信息表
 */
public class recruitInfo {
	private String recruitId; //id
	private String organization; //单位
	private String recruitInfo; //招聘信息
	private String accessory; //附件
	private String startTime; //开始时间
	private String endTime; //截止时间
	private String publisher; //发布人
	private String publishTime; //发布时间
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
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}
	@Override
	public String toString() {
		return "recruitInfo [recruitId=" + recruitId + ", organization=" + organization + ", recruitInfo=" + recruitInfo
				+ ", accessory=" + accessory + ", startTime=" + startTime + ", endTime=" + endTime + ", publisher="
				+ publisher + ", publishTime=" + publishTime + "]";
	}
	public recruitInfo(String recruitId, String organization, String recruitInfo, String accessory, String startTime,
			String endTime, String publisher, String publishTime) {
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
	public recruitInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
}
