/**
 * 
 */
package cn.edu.tit.bean;

import java.util.Date;
import java.util.List;

/**
 * @author LiMing
 * 申请人实体类
 */
public class Apply {
	private String applyId; //ID
	private String gender;
	private String applyUserName; //姓名
	private String face; //图片
	private String nation; //民族
	private String politicsStatus; //政治面貌
	private String nativePlace; //籍贯
	private String identityNum; //身份证
	private String isMarry; //婚否
	private String speciality; //特长
	private String telephone; //联系方式
	private String bachelorDegreeAndMajor; //本科毕业院校及专业
	private String undergraduateGraduationTime; //本科毕业时间
	private Integer undergraduateIsFirstSchool; // 是否双一流本科学校，双一流1，非双2    默认没有0
	private Integer undergraduateIsFirstMajor; // 是否双一流本科专业，是1，否2  默认没有0
	
	private String graduateSchoolAndMajor; //研究生毕业院校及专业
	private String graduateTime; //研究生毕业时间
	private Integer graduateIsFirstSchool; // 是否研究生双一流学校 是1，否0  
	private Integer graduateIsFirstMajor; // 是否双一流研究生专业，是1，否 
	
	private String doctoralDegreeAndMajor; //博士毕业院校及专业
	private String doctoralGraduationTime; //博士毕业时间
	private Integer doctorIsFirstSchool; // 博士，是否双一流院校，是1 否   默认没有0
	private Integer doctorIsFirstMajor; // 博士，是否双一流专业，是1 否0   默认没有0
	
	private String workOrganization; //原工作单位
	private String position; //原单位所任职位
	private String telephoneOriganization; //原工作单位联系方式
	private String professionalAndTechnicalQualification; //专业技术资格
	private String practicingRequirements; //执业资格
	private String mailingAddress; //通讯地址
	private String postalAddress; //通讯地址邮编
	private String eMail; //邮件地址
	private String applicationOrganization; //申请单位
	private String majorApplicant; //申请单位专业
	private String workExperience; //工作经历
	private String occupationApplicant; //申请职位
	private String recruitId;
	private Date submitTime;
	
	
	private String professionalOrientation; // 专业及方向
	private String compilationNature; // 编制性质
	private String education; // 学历
	private String degree; // 学位
	private Integer isCurrent; //是否应届,是1否0
	private String jobPerformance; // 工作业绩
	private String paperTopicSituation; // 论文和课题情况
	private String sanctionSituation; // 奖罚情况
	private List<ApplyFamily> familyRelationship; // 家庭成员及重要社会关系
	public String getApplyId() {
		return applyId;
	}
	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getApplyUserName() {
		return applyUserName;
	}
	public void setApplyUserName(String applyUserName) {
		this.applyUserName = applyUserName;
	}
	public String getFace() {
		return face;
	}
	public void setFace(String face) {
		this.face = face;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getPoliticsStatus() {
		return politicsStatus;
	}
	public void setPoliticsStatus(String politicsStatus) {
		this.politicsStatus = politicsStatus;
	}
	public String getNativePlace() {
		return nativePlace;
	}
	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}
	public String getIdentityNum() {
		return identityNum;
	}
	public void setIdentityNum(String identityNum) {
		this.identityNum = identityNum;
	}
	public String getIsMarry() {
		return isMarry;
	}
	public void setIsMarry(String isMarry) {
		this.isMarry = isMarry;
	}
	public String getSpeciality() {
		return speciality;
	}
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getBachelorDegreeAndMajor() {
		return bachelorDegreeAndMajor;
	}
	public void setBachelorDegreeAndMajor(String bachelorDegreeAndMajor) {
		this.bachelorDegreeAndMajor = bachelorDegreeAndMajor;
	}
	public String getUndergraduateGraduationTime() {
		return undergraduateGraduationTime;
	}
	public void setUndergraduateGraduationTime(String undergraduateGraduationTime) {
		this.undergraduateGraduationTime = undergraduateGraduationTime;
	}
	public Integer getUndergraduateIsFirstSchool() {
		return undergraduateIsFirstSchool;
	}
	public void setUndergraduateIsFirstSchool(Integer undergraduateIsFirstSchool) {
		this.undergraduateIsFirstSchool = undergraduateIsFirstSchool;
	}
	public Integer getUndergraduateIsFirstMajor() {
		return undergraduateIsFirstMajor;
	}
	public void setUndergraduateIsFirstMajor(Integer undergraduateIsFirstMajor) {
		this.undergraduateIsFirstMajor = undergraduateIsFirstMajor;
	}
	public String getGraduateSchoolAndMajor() {
		return graduateSchoolAndMajor;
	}
	public void setGraduateSchoolAndMajor(String graduateSchoolAndMajor) {
		this.graduateSchoolAndMajor = graduateSchoolAndMajor;
	}
	public String getGraduateTime() {
		return graduateTime;
	}
	public void setGraduateTime(String graduateTime) {
		this.graduateTime = graduateTime;
	}
	public Integer getGraduateIsFirstSchool() {
		return graduateIsFirstSchool;
	}
	public void setGraduateIsFirstSchool(Integer graduateIsFirstSchool) {
		this.graduateIsFirstSchool = graduateIsFirstSchool;
	}
	public Integer getGraduateIsFirstMajor() {
		return graduateIsFirstMajor;
	}
	public void setGraduateIsFirstMajor(Integer graduateIsFirstMajor) {
		this.graduateIsFirstMajor = graduateIsFirstMajor;
	}
	public String getDoctoralDegreeAndMajor() {
		return doctoralDegreeAndMajor;
	}
	public void setDoctoralDegreeAndMajor(String doctoralDegreeAndMajor) {
		this.doctoralDegreeAndMajor = doctoralDegreeAndMajor;
	}
	public String getDoctoralGraduationTime() {
		return doctoralGraduationTime;
	}
	public void setDoctoralGraduationTime(String doctoralGraduationTime) {
		this.doctoralGraduationTime = doctoralGraduationTime;
	}
	public Integer getDoctorIsFirstSchool() {
		return doctorIsFirstSchool;
	}
	public void setDoctorIsFirstSchool(Integer doctorIsFirstSchool) {
		this.doctorIsFirstSchool = doctorIsFirstSchool;
	}
	public Integer getDoctorIsFirstMajor() {
		return doctorIsFirstMajor;
	}
	public void setDoctorIsFirstMajor(Integer doctorIsFirstMajor) {
		this.doctorIsFirstMajor = doctorIsFirstMajor;
	}
	public String getWorkOrganization() {
		return workOrganization;
	}
	public void setWorkOrganization(String workOrganization) {
		this.workOrganization = workOrganization;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getTelephoneOriganization() {
		return telephoneOriganization;
	}
	public void setTelephoneOriganization(String telephoneOriganization) {
		this.telephoneOriganization = telephoneOriganization;
	}
	public String getProfessionalAndTechnicalQualification() {
		return professionalAndTechnicalQualification;
	}
	public void setProfessionalAndTechnicalQualification(String professionalAndTechnicalQualification) {
		this.professionalAndTechnicalQualification = professionalAndTechnicalQualification;
	}
	public String getPracticingRequirements() {
		return practicingRequirements;
	}
	public void setPracticingRequirements(String practicingRequirements) {
		this.practicingRequirements = practicingRequirements;
	}
	public String getMailingAddress() {
		return mailingAddress;
	}
	public void setMailingAddress(String mailingAddress) {
		this.mailingAddress = mailingAddress;
	}
	public String getPostalAddress() {
		return postalAddress;
	}
	public void setPostalAddress(String postalAddress) {
		this.postalAddress = postalAddress;
	}
	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	public String getApplicationOrganization() {
		return applicationOrganization;
	}
	public void setApplicationOrganization(String applicationOrganization) {
		this.applicationOrganization = applicationOrganization;
	}
	public String getMajorApplicant() {
		return majorApplicant;
	}
	public void setMajorApplicant(String majorApplicant) {
		this.majorApplicant = majorApplicant;
	}
	public String getWorkExperience() {
		return workExperience;
	}
	public void setWorkExperience(String workExperience) {
		this.workExperience = workExperience;
	}
	public String getOccupationApplicant() {
		return occupationApplicant;
	}
	public void setOccupationApplicant(String occupationApplicant) {
		this.occupationApplicant = occupationApplicant;
	}
	public String getRecruitId() {
		return recruitId;
	}
	public void setRecruitId(String recruitId) {
		this.recruitId = recruitId;
	}
	public Date getSubmitTime() {
		return submitTime;
	}
	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
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
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public Integer getIsCurrent() {
		return isCurrent;
	}
	public void setIsCurrent(Integer isCurrent) {
		this.isCurrent = isCurrent;
	}
	public String getJobPerformance() {
		return jobPerformance;
	}
	public void setJobPerformance(String jobPerformance) {
		this.jobPerformance = jobPerformance;
	}
	public String getPaperTopicSituation() {
		return paperTopicSituation;
	}
	public void setPaperTopicSituation(String paperTopicSituation) {
		this.paperTopicSituation = paperTopicSituation;
	}
	public String getSanctionSituation() {
		return sanctionSituation;
	}
	public void setSanctionSituation(String sanctionSituation) {
		this.sanctionSituation = sanctionSituation;
	}
	public List<ApplyFamily> getFamilyRelationship() {
		return familyRelationship;
	}
	public void setFamilyRelationship(List<ApplyFamily> familyRelationship) {
		this.familyRelationship = familyRelationship;
	}
	public Apply(String applyId, String gender, String applyUserName, String face, String nation, String politicsStatus,
			String nativePlace, String identityNum, String isMarry, String speciality, String telephone,
			String bachelorDegreeAndMajor, String undergraduateGraduationTime, Integer undergraduateIsFirstSchool,
			Integer undergraduateIsFirstMajor, String graduateSchoolAndMajor, String graduateTime,
			Integer graduateIsFirstSchool, Integer graduateIsFirstMajor, String doctoralDegreeAndMajor,
			String doctoralGraduationTime, Integer doctorIsFirstSchool, Integer doctorIsFirstMajor,
			String workOrganization, String position, String telephoneOriganization,
			String professionalAndTechnicalQualification, String practicingRequirements, String mailingAddress,
			String postalAddress, String eMail, String applicationOrganization, String majorApplicant,
			String workExperience, String occupationApplicant, String recruitId, Date submitTime,
			String professionalOrientation, String compilationNature, String education, String degree,
			Integer isCurrent, String jobPerformance, String paperTopicSituation, String sanctionSituation) {
		super();
		this.applyId = applyId;
		this.gender = gender;
		this.applyUserName = applyUserName;
		this.face = face;
		this.nation = nation;
		this.politicsStatus = politicsStatus;
		this.nativePlace = nativePlace;
		this.identityNum = identityNum;
		this.isMarry = isMarry;
		this.speciality = speciality;
		this.telephone = telephone;
		this.bachelorDegreeAndMajor = bachelorDegreeAndMajor;
		this.undergraduateGraduationTime = undergraduateGraduationTime;
		this.undergraduateIsFirstSchool = undergraduateIsFirstSchool;
		this.undergraduateIsFirstMajor = undergraduateIsFirstMajor;
		this.graduateSchoolAndMajor = graduateSchoolAndMajor;
		this.graduateTime = graduateTime;
		this.graduateIsFirstSchool = graduateIsFirstSchool;
		this.graduateIsFirstMajor = graduateIsFirstMajor;
		this.doctoralDegreeAndMajor = doctoralDegreeAndMajor;
		this.doctoralGraduationTime = doctoralGraduationTime;
		this.doctorIsFirstSchool = doctorIsFirstSchool;
		this.doctorIsFirstMajor = doctorIsFirstMajor;
		this.workOrganization = workOrganization;
		this.position = position;
		this.telephoneOriganization = telephoneOriganization;
		this.professionalAndTechnicalQualification = professionalAndTechnicalQualification;
		this.practicingRequirements = practicingRequirements;
		this.mailingAddress = mailingAddress;
		this.postalAddress = postalAddress;
		this.eMail = eMail;
		this.applicationOrganization = applicationOrganization;
		this.majorApplicant = majorApplicant;
		this.workExperience = workExperience;
		this.occupationApplicant = occupationApplicant;
		this.recruitId = recruitId;
		this.submitTime = submitTime;
		this.professionalOrientation = professionalOrientation;
		this.compilationNature = compilationNature;
		this.education = education;
		this.degree = degree;
		this.isCurrent = isCurrent;
		this.jobPerformance = jobPerformance;
		this.paperTopicSituation = paperTopicSituation;
		this.sanctionSituation = sanctionSituation;
	}
	public Apply() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}