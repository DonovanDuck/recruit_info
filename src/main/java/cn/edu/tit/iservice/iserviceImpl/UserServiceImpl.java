package cn.edu.tit.iservice.iserviceImpl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cn.edu.tit.bean.Apply;
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
			System.out.println("getRecruitInfo-------------执行成功");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("getRecruitInfo-------------执行失败");
			list = null;
		}
		return list;
	}

	/**
	 * @author LiMing
	 * @param 单位名
	 * @return 按照单位名查找招聘信息
	 */
	@Override
	public List<RecruitInfo> searchRecruit(String search) {
		List<RecruitInfo> list =new ArrayList<RecruitInfo>();
		try {
			list = iUserDao.searchRecruit(search);
			System.out.println("searchRecruit-------------执行成功");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("searchRecruit-------------执行失败");
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
	public List<Apply> applyList(String recruitId) {
		// TODO Auto-generated method stub
		return iUserDao.applyList(recruitId);
	}

	@Override
	public Integer applyNum(String recruitId) {
		// TODO Auto-generated method stub
		return iUserDao.applyNum(recruitId);
	}

	@Override
	public Integer applyNumToday(String recruitId, String dateString) {
		// TODO Auto-generated method stub
		return iUserDao.applyNumToday(recruitId,dateString);
	}

	@Override
	public Integer applyNumDoctor(String recruitId) {
		// TODO Auto-generated method stub
		return iUserDao.applyNumDoctor(recruitId);
	}

	@Override
	public Integer applyNumMaster(String recruitId) {
		// TODO Auto-generated method stub
		return iUserDao.applyNumMaster(recruitId);
	}

	@Override
	public Integer applyNumBachelor(String recruitId) {
		// TODO Auto-generated method stub
		return iUserDao.applyNumBachelor(recruitId);
	}

	@Override
	public Integer applyNumDoubleOne(String recruitId) {
		// TODO Auto-generated method stub
		return iUserDao.applyNumDoubleOne(recruitId);
	}

	@Override
	public Integer applyNumInSide(String recruitId) {
		// TODO Auto-generated method stub
		return iUserDao.applyNumInSide(recruitId);
	}

	@Override
	public Integer applyNumDoctorToday(String recruitId, String dateString) {
		// TODO Auto-generated method stub
		return iUserDao.applyNumDoctorToday(recruitId, dateString);
	}

	@Override
	public Integer applyNumMasterToday(String recruitId, String dateString) {
		// TODO Auto-generated method stub
		return iUserDao.applyNumMasterToday(recruitId, dateString);
	}

	@Override
	public Integer applyNumBachelorToday(String recruitId, String dateString) {
		// TODO Auto-generated method stub
		return iUserDao.applyNumBachelorToday(recruitId, dateString);
	}

	@Override
	public Integer applyNumDoubleOneToday(String recruitId, String dateString) {
		// TODO Auto-generated method stub
		return iUserDao.applyNumDoubleOneToday(recruitId, dateString);
	}

	@Override
	public Integer applyNumInSideToday(String recruitId, String dateString) {
		// TODO Auto-generated method stub
		return iUserDao.applyNumInSideToday(recruitId, dateString);
	}


}
