package cn.edu.tit.bean;

public class Organization {
	private String organizaionId; // 公司id
	private String organizatinName; // 公司名
	public String getOrganizaionId() {
		return organizaionId;
	}
	public void setOrganizaionId(String organizaionId) {
		this.organizaionId = organizaionId;
	}
	public String getOrganizatinName() {
		return organizatinName;
	}
	public void setOrganizatinName(String organizatinName) {
		this.organizatinName = organizatinName;
	}
	public Organization(String organizaionId, String organizatinName) {
		super();
		this.organizaionId = organizaionId;
		this.organizatinName = organizatinName;
	}
	
	
}
