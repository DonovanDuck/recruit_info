package cn.edu.tit.bean;

//报名材料
public class Material {
	private String materialId; 
	private String applyId; //申请id
	private String accessory; //附件
	public String getMaterialId() {
		return materialId;
	}
	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}
	public String getApplyId() {
		return applyId;
	}
	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}
	public String getAccessory() {
		return accessory;
	}
	public void setAccessory(String accessory) {
		this.accessory = accessory;
	}
	@Override
	public String toString() {
		return "Material [materialId=" + materialId + ", applyId=" + applyId + ", accessory=" + accessory + "]";
	}
	public Material(String materialId, String applyId, String accessory) {
		super();
		this.materialId = materialId;
		this.applyId = applyId;
		this.accessory = accessory;
	}
	public Material() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
