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
	private String organization; //单位
	private String recruitId; //招聘ID
	private String positonName; //职位名称
	@Override
	public String toString() {
		return "Position [id=" + id + ", organization=" + organization + ", recruitId=" + recruitId + ", positonName="
				+ positonName + "]";
	}
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
	public String getPositonName() {
		return positonName;
	}
	public void setPositonName(String positonName) {
		this.positonName = positonName;
	}
	public Position(String id, String organization, String recruitId, String positonName) {
		super();
		this.id = id;
		this.organization = organization;
		this.recruitId = recruitId;
		this.positonName = positonName;
	}
	public Position() {
		super();
		// TODO Auto-generated constructor stub
	}
}
