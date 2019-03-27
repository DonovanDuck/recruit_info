package cn.edu.tit.bean;

//报名材料
public class Material {
	private String materialId; 
	private String recruitId; 
	private String openId;
	private String accessory; //附件
	public String getMaterialId() {
		return materialId;
	}
	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}
	public String getRecruitId() {
		return recruitId;
	}
	public void setRecruitId(String recruitId) {
		this.recruitId = recruitId;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getAccessory() {
		return accessory;
	}
	public void setAccessory(String accessory) {
		this.accessory = accessory;
	}
	@Override
	public String toString() {
		return "Material [materialId=" + materialId + ", recruitId=" + recruitId + ", openId=" + openId + ", accessory="
				+ accessory + "]";
	}
	public Material(String materialId, String recruitId, String openId, String accessory) {
		super();
		this.materialId = materialId;
		this.recruitId = recruitId;
		this.openId = openId;
		this.accessory = accessory;
	}
	public Material() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
