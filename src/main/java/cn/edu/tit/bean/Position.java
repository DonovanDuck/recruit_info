/**
 * 
 */
package cn.edu.tit.bean;

/**
 * @author LiMing
 * 职位表
 */
public class Position {
	private String id; //职位ID
	private String organization; //单位Id
	private String recruitId; //招聘ID
	private Integer positionNum; // 招聘数
	private String positonName; //职位名称
	private String professionalOrientation; // 专业及方向
	private String compilationNature; // 编制性质
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getRecruitId() {
		return recruitId;
	}
	public void setRecruitId(String recruitId) {
		this.recruitId = recruitId;
	}
	public Integer getPositionNum() {
		return positionNum;
	}
	public void setPositionNum(Integer positionNum) {
		this.positionNum = positionNum;
	}
	public String getPositonName() {
		return positonName;
	}
	public void setPositonName(String positonName) {
		this.positonName = positonName;
	}
	public String getProfessionalOrientation() {
		return professionalOrientation;
	}
	public void setProfessionalOrientation(String professionalOrientation) {
		this.professionalOrientation = professionalOrientation;
	}
	public String getCompilationNature() {
		return compilationNature;
	}
	public void setCompilationNature(String compilationNature) {
		this.compilationNature = compilationNature;
	}
	@Override
	public String toString() {
		return "Position [id=" + id + ", organization=" + organization + ", recruitId=" + recruitId + ", positionNum="
				+ positionNum + ", positonName=" + positonName + ", professionalOrientation=" + professionalOrientation
				+ ", compilationNature=" + compilationNature + "]";
	}
	public Position(String id, String organization, String recruitId, Integer positionNum, String positonName,
			String professionalOrientation, String compilationNature) {
		super();
		this.id = id;
		this.organization = organization;
		this.recruitId = recruitId;
		this.positionNum = positionNum;
		this.positonName = positonName;
		this.professionalOrientation = professionalOrientation;
		this.compilationNature = compilationNature;
	}
	public Position() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
}
