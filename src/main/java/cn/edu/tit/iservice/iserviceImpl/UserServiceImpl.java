package cn.edu.tit.iservice.iserviceImpl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cn.edu.tit.bean.Apply;
import cn.edu.tit.bean.Material;
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
	public List<RecruitInfo> getRecruitInfo(String publisherId) {
		List<RecruitInfo> list =new ArrayList<RecruitInfo>();
		try {
			list = iUserDao.getRecruitInfo(publisherId);
			for (RecruitInfo recruitInfo : list) {
				String publisher = recruitInfo.getPublisher();
				User user = iUserDao.getUserById(publisher);
				recruitInfo.setPublisher(user.getUserName());
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
	public User getUserByName(String employeeNum) {
		// TODO Auto-generated method stub
		return iUserDao.getUserByName(employeeNum);
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
	public List<RecruitInfo> searchRecruit(String search) {
		// TODO Auto-generated method stub
		return iUserDao.getRecruitInfo(search);
	}

	@Override
	public void rePassword(String userId) {
		// TODO Auto-generated method stub
		iUserDao.rePassword(userId);
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
	 * @see cn.edu.tit.iservice.IUserService#getRecruitInfoById(java.lang.String)
	 */
	@Override
	public RecruitInfo getRecruitInfoById(String recuritId) {
		// TODO Auto-generated method stub
		return iUserDao.getRecruitInfoById(recuritId);
	}


	/* (non-Javadoc)
	 * @see cn.edu.tit.iservice.IUserService#getPositionNameByRecruitId(java.lang.String)
	 */
	@Override
	public List<String> getPositionNameByRecruitId(String recruitId) {
		// TODO Auto-generated method stub
		return iUserDao.getPositionNameByRecruitId(recruitId);
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
}
