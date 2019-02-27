package cn.edu.tit.iservice;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import cn.edu.tit.bean.Apply;
import cn.edu.tit.bean.RecruitInfo;
import cn.edu.tit.bean.User;


public interface IUserService {
	
	/**
	 * 通过用户名和密码获取用户
	 * @param userId
	 * @param password
	 * @return
	 */
	public User getUser(String userId, String password);

	/**
	 * @author 
	 * 增加用户信息
	 * */
	public void addUser(User user) throws Exception;
	
	/**
	 * @author 
	 * 更新用户信息
	 * */
	public void updateUser() throws Exception;

	
	/**
	 * 查询所有用户
	 * @return
	 */
	public List<User> getUser();
	
	/**
	 * 修改用户信息
	 * @param userId
	 * @param password
	 */
	public void modifyPassword(String userId, String password);
	
	/**
	 * 校验原密码
	 * @param userId
	 * @param password
	 * @return
	 */
	public Boolean checkPassword(String userId, String password);
	
	/**
	 * 根据报名id获取表明表
	 * @param applyId
	 * @return
	 */
	public Apply getApplyById(String applyId);

	/**
	 *@author LiMing
	 * @param recruit
	 * 发布招聘信息
	 * */
	public void publishRcruit(RecruitInfo recruit)throws Exception ;

	/**
	 *@author LiMing
	 * @param 当参数(publisherId)为空时,查询所有招聘信息.
	 * @param 当参数(publisherId)不空时,条件查询该ID下的招聘信息
	 * @return 获取所有招聘信息
	 */
	public List<RecruitInfo> getRecruitInfo(String publisherId);

	/**
	 *@author LiMing
	 * @param 单位名
	 * @return 按照单位名查找招聘信息
	 */
	public List<RecruitInfo> searchRecruit(String search);
	
	/**
	 * 修改用户信息
	 * @param user
	 */
	public void modifyuser(User user);
	public List<Apply> applyList(String recruitId);
	public Integer applyNum(String recruitId);
	public Integer applyNumDoctor(String recruitId);
	public Integer applyNumMaster(String recruitId);
	public Integer applyNumBachelor(String recruitId);
	public Integer applyNumDoubleOne(String recruitId);
	public Integer applyNumInSide(String recruitId);
	
	public Integer applyNumToday(String recruitId, String dateString);
	public Integer applyNumDoctorToday(String recruitId, String dateString);
	public Integer applyNumMasterToday(String recruitId, String dateString);
	public Integer applyNumBachelorToday(String recruitId, String dateString);
	public Integer applyNumDoubleOneToday(String recruitId, String dateString);
	public Integer applyNumInSideToday(String recruitId, String dateString);
	
}
