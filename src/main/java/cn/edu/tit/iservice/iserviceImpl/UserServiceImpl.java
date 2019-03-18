package cn.edu.tit.iservice.iserviceImpl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cn.edu.tit.bean.Apply;
import cn.edu.tit.bean.ApplyFamily;
import cn.edu.tit.bean.Material;
import cn.edu.tit.bean.Organization;
import cn.edu.tit.bean.Position;
import cn.edu.tit.bean.RecruitInfo;
import cn.edu.tit.bean.User;
import cn.edu.tit.common.Common;
import cn.edu.tit.idao.IUserDao;
import cn.edu.tit.iservice.IUserService;

/**
 * @author LiMing
 * 管理员功能模块
 * */
@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao iUserDao;

	/**
	 * @author 
	 * 增加用户信息
	 * */
	@Override
	public void addUser(User user) throws Exception {
		iUserDao.addUser(user);
	}

	/**
	 * @author 
	 * 更新用户信息
	 * */
	@Override
	public void updateUser() throws Exception {

	}

	/**
	 *@author LiMing
	 * @param recruit
	 * 发布招聘信息
	 */
	@Override
	public void publishRcruit(RecruitInfo recruit)throws Exception {
		try {
			iUserDao.publishRcruit(recruit);
			System.out.println("publishRcruit-------------执行成功");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("publishRcruit-------------执行失败");
		}
	}

	/**
	 *@author LiMing
	 * @param 当参数(publisherId)为空时,查询所有招聘信息.
	 * @param 当参数(publisherId)不空时,条件查询该ID下的招聘信息
	 * @return 获取所有招聘信息
	 */
	@Override
	public List<RecruitInfo> getRecruitInfo(String organizationId) {
		List<RecruitInfo> list =new ArrayList<RecruitInfo>();
		try {
			list = iUserDao.getRecruitInfo(organizationId);
			for (RecruitInfo recruitInfo : list) {
				String publisher = recruitInfo.getPublisher();
				User user = iUserDao.getUserById(publisher);
				recruitInfo.setPublisher(user.getUserName());
				recruitInfo.setOrganization(user.getOrganizationName());
			}
			System.out.println("getRecruitInfo-------------执行成功");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("getRecruitInfo-------------执行失败");
			list = null;
		}
		return list;
	}


	@Override
	public List<User> getUser() {

		return iUserDao.getUser();
	}

	@Override
	public void modifyPassword(String userId, String password) {
		// TODO Auto-generated method stub
		iUserDao.modifyPassword(userId, password);
	}

	@Override
	public Boolean checkPassword(String userId, String password) {
		try {
			User user = iUserDao.getUserByIdAndPs(userId, password);
			if(user != null) 
				return true; 
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Apply getApplyById(String applyId) {
		// TODO Auto-generated method stub
		return iUserDao.getApplyById(applyId);
	}

	@Override
	public User getUser(String userId, String password) {
		// TODO Auto-generated method stub
		return iUserDao.getUserByIdAndPs(userId, password);
	}

	@Override
	public void modifyuser(User user) {
		// TODO Auto-generated method stub
		iUserDao.modifyUser(user);
	}
	public List<Apply> applyList(String recruitId,String positonName) {
		// TODO Auto-generated method stub
		return iUserDao.applyList(recruitId,positonName);
	}

	@Override
	public Integer applyNum(String recruitId,String positonName) {
		// TODO Auto-generated method stub
		return iUserDao.applyNum(recruitId,positonName);
	}

	@Override
	public Integer applyNumToday(String recruitId, String dateString,String positonName) {
		// TODO Auto-generated method stub
		return iUserDao.applyNumToday(recruitId,dateString,positonName);
	}

	@Override
	public Integer applyNumDoctor(String recruitId,String positonName) {
		// TODO Auto-generated method stub
		return iUserDao.applyNumDoctor(recruitId,positonName);
	}

	@Override
	public Integer applyNumMaster(String recruitId,String positonName) {
		// TODO Auto-generated method stub
		return iUserDao.applyNumMaster(recruitId,positonName);
	}

	@Override
	public Integer applyNumBachelor(String recruitId,String positonName) {
		// TODO Auto-generated method stub
		return iUserDao.applyNumBachelor(recruitId,positonName);
	}

	@Override
	public Integer applyNumDoubleOne(String recruitId,String positonName) {
		// TODO Auto-generated method stub
		return iUserDao.applyNumDoubleOne(recruitId,positonName);
	}

	@Override
	public Integer applyNumInSide(String recruitId,String positonName) {
		// TODO Auto-generated method stub
		return iUserDao.applyNumInSide(recruitId,positonName);
	}

	@Override
	public Integer applyNumDoctorToday(String recruitId, String dateString,String positonName) {
		// TODO Auto-generated method stub
		return iUserDao.applyNumDoctorToday(recruitId, dateString,positonName);
	}

	@Override
	public Integer applyNumMasterToday(String recruitId, String dateString,String positonName) {
		// TODO Auto-generated method stub
		return iUserDao.applyNumMasterToday(recruitId, dateString,positonName);
	}

	@Override
	public Integer applyNumBachelorToday(String recruitId, String dateString,String positonName) {
		// TODO Auto-generated method stub
		return iUserDao.applyNumBachelorToday(recruitId, dateString,positonName);
	}

	@Override
	public Integer applyNumDoubleOneToday(String recruitId, String dateString,String positonName) {
		// TODO Auto-generated method stub
		return iUserDao.applyNumDoubleOneToday(recruitId, dateString,positonName);
	}

	@Override
	public Integer applyNumInSideToday(String recruitId, String dateString,String positonName) {
		// TODO Auto-generated method stub
		return iUserDao.applyNumInSideToday(recruitId, dateString,positonName);
	}
	/**
	 *@author LiMing
	 * @param organizationId
	 * @return 获取单位职位
	 */
	@Override
	public List<Position> getPosition(String organizationId) {
		List<Position> list =new ArrayList<Position>();
		try {
			list = iUserDao.getPosition(organizationId);
			System.out.println("getPosition-------------执行成功");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("getPosition-------------执行失败");
			list = null;
		}
		return list;
	}



	/**
	 *@author LiMing
	 * @param employeeNum
	 * @return 返回用户实体
	 */
	@Override
	public User getUserByPhone(String employeeNum) {
		// TODO Auto-generated method stub
		return iUserDao.getUserByPhone(employeeNum);
	}

	/**
	 *@author LiMing
	 * @param employeeNum
	 * @return 返回用户实体
	 */
	@Override
	public User getUserById(String userId) {
		// TODO Auto-generated method stub
		return iUserDao.getUserById(userId);
	}

	@Override
	public List<RecruitInfo> searchRecruit(String search, Integer index) {
		// TODO Auto-generated method stub
		return iUserDao.getRecruitInfoLimit(search,index);
	}

	@Override
	public void rePassword(String userId,String password) {
		// TODO Auto-generated method stub
		iUserDao.rePassword(userId, password);
	}

	@Override
	public List<Position> getPositionByRecruitId(String recruitId) {
		// TODO Auto-generated method stub
		return iUserDao.getPositionByRecruitId(recruitId);
	}

	@Override
	public void submitApply(Apply apply) {
		// TODO Auto-generated method stub
		iUserDao.submitApply(apply);
	}

	@Override
	public void saveMaterial(Material material) {
		// TODO Auto-generated method stub
		iUserDao.saveMaterial(material);
	}


	@Override
	public List<Apply> applyListAll(String recruitId) {
		// TODO Auto-generated method stub
		return iUserDao.applyListAll(recruitId);
	}

	@Override
	public boolean isFirstSchool(String applyId) {
		// TODO Auto-generated method stub
		Integer undergraduateIsFirstSchool = iUserDao.undergraduateIsFirstSchool(applyId);
		Integer graduateIsFirstSchool =  iUserDao.graduateIsFirstSchool(applyId);
		Integer doctorIsFirstSchool =  iUserDao.doctorIsFirstSchool(applyId);
		if((undergraduateIsFirstSchool!=null||graduateIsFirstSchool!=null||doctorIsFirstSchool!=null)&&(undergraduateIsFirstSchool==1||graduateIsFirstSchool ==1||doctorIsFirstSchool == 1)) {
			return true;
		}else {

			return false;	
		}

	}

	@Override
	public boolean isFirstMajor(String applyId) {
		// TODO Auto-generated method stub
		Integer undergraduateIsFirstMajor = iUserDao.undergraduateIsFirstMajor(applyId);
		Integer graduateIsFirstMajor =  iUserDao.graduateIsFirstMajor(applyId);
		Integer doctorIsFirstMajor  =  iUserDao.doctorIsFirstMajor(applyId);
		if((undergraduateIsFirstMajor!=null||graduateIsFirstMajor!=null||doctorIsFirstMajor!=null)&&(undergraduateIsFirstMajor ==1||graduateIsFirstMajor ==1||doctorIsFirstMajor ==1 )) {

			return true;
		}else {
			return false;
		}

	}

	@Override
	public RecruitInfo getRecruitInfoById(String recruitId) {
		// TODO Auto-generated method stub
		return iUserDao.getRecruitInfoById(recruitId);
	}


	/**
	 *@author LiMing
	 * @param 职位对象集合
	 * 添加职位
	 */
	@Override
	public void publishPosition(List<Position> po) {
		iUserDao.publishPosition(po);
	}




	/* (non-Javadoc)
	 * @see cn.edu.tit.iservice.IUserService#getPositionNameByRecruitId(java.lang.String)
	 */
	@Override
	public List<String> getPositionNameByRecruitId(String recruitId) {
		// TODO Auto-generated method stub
		return iUserDao.getPositionNameByRecruitId(recruitId);
	}

	@Override
	public List<ApplyFamily> getApplyFamily(String applyId) {
		// TODO Auto-generated method stub
		return iUserDao.getApplyFamily(applyId);
	}

	/**
	 *@author LiMing
	 * @param recruit
	 * 更新发布信息
	 */
	@Override
	public void updateRcruit(RecruitInfo recruit) {
		iUserDao.updateRcruit(recruit);
	}

	/**
	 *@author LiMing
	 * @param organizationId
	 * 删除原职位信息
	 */
	@Override
	public void deletePosition(String organizationId,String recruitId) {
		iUserDao.deletePosition(organizationId,recruitId);	
	}
	/* (non-Javadoc)
	 * @see cn.edu.tit.iservice.IUserService#getPositionNameByorganizationId(java.lang.String)
	 */
	@Override
	public List<String> getPositionNameByorganizationId(String organizationId) {
		// TODO Auto-generated method stub
		return iUserDao.getPositionNameByorganizationId(organizationId);}
	@Override
	public Integer applyNumFirstSchoolInUndergraduate(String recruitId, String positonName) {
		// TODO Auto-generated method stub
		return iUserDao.applyNumFirstSchoolInUndergraduate(recruitId, positonName);
	}

	@Override
	public Integer applyNumFirstSchoolInPastgraduate(String recruitId, String positonName) {
		// TODO Auto-generated method stub
		return iUserDao.applyNumFirstSchoolInPastgraduate(recruitId, positonName);
	}

	@Override
	public Integer applyNumFirstSchoolInDoctor(String recruitId, String positonName) {
		// TODO Auto-generated method stub
		return iUserDao.applyNumFirstSchoolInDoctor(recruitId, positonName);
	}

	@Override
	public Integer applyNumFirstSchoolInUndergraduateToday(String recruitId, String dateString, String positonName) {
		// TODO Auto-generated method stub
		return iUserDao.applyNumFirstSchoolInUndergraduateToday(recruitId, dateString, positonName);
	}

	@Override
	public Integer applyNumFirstSchoolInPastgraduateToday(String recruitId, String dateString, String positonName) {
		// TODO Auto-generated method stub
		return iUserDao.applyNumFirstSchoolInPastgraduateToday(recruitId, dateString, positonName);
	}

	@Override
	public Integer applyNumFirstSchoolInDoctorToday(String recruitId, String dateString, String positonName) {
		// TODO Auto-generated method stub
		return iUserDao.applyNumFirstSchoolInDoctorToday(recruitId, dateString, positonName);
	}

	@Override
	public Integer applyNumFirstMajorInUndergraduate(String recruitId, String positonName) {
		// TODO Auto-generated method stub
		return iUserDao.applyNumFirstMajorInUndergraduate(recruitId, positonName);
	}

	@Override
	public Integer applyNumFirstMajorInPastgraduate(String recruitId, String positonName) {
		// TODO Auto-generated method stub
		return iUserDao.applyNumFirstMajorInPastgraduate(recruitId, positonName);
	}

	@Override
	public Integer applyNumFirstMajorInDoctor(String recruitId, String positonName) {
		// TODO Auto-generated method stub
		return iUserDao.applyNumFirstMajorInDoctor(recruitId, positonName);
	}

	@Override
	public Integer applyNumFirstMajorInUndergraduateToday(String recruitId, String dateString, String positonName) {
		// TODO Auto-generated method stub
		return iUserDao.applyNumFirstMajorInUndergraduateToday(recruitId, dateString, positonName);
	}

	@Override
	public Integer applyNumFirstMajorInPastgraduateToday(String recruitId, String dateString, String positonName) {
		// TODO Auto-generated method stub
		return iUserDao.applyNumFirstMajorInPastgraduateToday(recruitId, dateString, positonName);
	}

	@Override
	public Integer applyNumFirstMajorInDoctorToday(String recruitId, String dateString, String positonName) {
		// TODO Auto-generated method stub
		return iUserDao.applyNumFirstMajorInDoctorToday(recruitId, dateString, positonName);
	}

	@Override
	public Position getPositionByPositionNameAndRecruitId(String recruitId, String positionName) {
		// TODO Auto-generated method stub
		return iUserDao.getPositionByPositionNameAndRecruitId(recruitId, positionName);
	}

	@Override
	public String getOrganizaionIdByName(String organizationName) {
		// TODO Auto-generated method stub
		return iUserDao.getOrganizaionIdByName(organizationName);
	}

	@Override
	public void addOrganizaion(Organization orga) {
		// TODO Auto-generated method stub
		iUserDao.addOrganizaion(orga);
	}

	@Override
	public List<String> getOrganizationName() {
		// TODO Auto-generated method stub
		return iUserDao.getOrganizationName();
	}

	@Override
	public void deleteUser(String userId) {
		// TODO Auto-generated method stub
		iUserDao.deleteUser(userId);
	}

	@Override
	public List<RecruitInfo> getAllRecruitInfo() {
		// TODO Auto-generated method stub
		return iUserDao.getAllRecruitInfo();
	}

	@Override
	public List<User> getUserByOrganizationId(String organizationId) {
		// TODO Auto-generated method stub
		return iUserDao.getUserByOrganizationId(organizationId);
	}

	@Override
	public String getOrganizationNameById(String organizationId) {
		// TODO Auto-generated method stub
		return iUserDao.getOrganizationNameById(organizationId);
	}

	@Override
	public List<Organization> getOrganization() {
		// TODO Auto-generated method stub
		return iUserDao.getOrganization();
	}

	@Override
	public List<RecruitInfo> getAllRecruitInfoBypage(int index) {
		// TODO Auto-generated method stub
		return iUserDao.getAllRecruitInfoBypage(index);
	}

	@Override
	public void bandOpenId(String openId,String userId) {
		// TODO Auto-generated method stub
		iUserDao.bandOpenId(openId, userId);
	}

	@Override
	public List<String> getApplyRecruitId(String openId) {
		// TODO Auto-generated method stub
		return iUserDao.getApplyRecruitId(openId);
	}

	@Override
	public Apply getApplyByOpenAndRecruit(String openId, String recruitId) {
		// TODO Auto-generated method stub
		return iUserDao.getApplyByOpenAndRecruit( openId,  recruitId);
	}

	@Override
	public void modifyApply(Apply apply) {
		// TODO Auto-generated method stub
		iUserDao.modifyApply(apply);
	}
}
