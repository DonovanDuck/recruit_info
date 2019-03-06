package cn.edu.tit.iservice;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import cn.edu.tit.bean.Apply;
import cn.edu.tit.bean.Material;
import cn.edu.tit.bean.Position;
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
	 * @param organizationId
	 * @return 获取单位职位
	 */
	public List<RecruitInfo> searchRecruit(String search);
	
	/**
	 * 修改用户信息
	 * @param user
	 */
	public void modifyuser(User user);
	public List<Apply> applyList(String recruitId);
	public Integer applyNum(String recruitId,String positonName);
	public Integer applyNumDoctor(String recruitId,String positonName);
	public Integer applyNumMaster(String recruitId,String positonName);
	public Integer applyNumBachelor(String recruitId,String positonName);
	public Integer applyNumDoubleOne(String recruitId,String positonName);
	public Integer applyNumInSide(String recruitId,String positonName);
	
	public Integer applyNumToday(String recruitId, String dateString,String positonName);
	public Integer applyNumDoctorToday(String recruitId, String dateString,String positonName);
	public Integer applyNumMasterToday(String recruitId, String dateString,String positonName);
	public Integer applyNumBachelorToday(String recruitId, String dateString,String positonName);
	public Integer applyNumDoubleOneToday(String recruitId, String dateString,String positonName);
	public Integer applyNumInSideToday(String recruitId, String dateString,String positonName);
	
	
	/**
	 *@author LiMing
	 * @param organizationId
	 * @return 返回职位集合
	 */
	public List<Position> getPosition(String organizationId);

	/**
	 *@author LiMing
	 * @param employeeNum
	 * @return 返回用户实体
	 */
	public User getUserByName(String employeeNum);
	public User getUserById(String userId);
	
	/**
	 * 重置密码
	 * @param userId
	 */
	public void rePassword(String userId);
	
	/**
	 * 通过招聘信息获取相应职位信息
	 * @return
	 */
	public List<Position> getPositionByRecruitId(String recruitId);
	
	/**
	 * 提交申请
	 * @param apply
	 */
	public void submitApply(Apply apply);
	
	/**
	 * 存储报名材料
	 * @param material
	 */
	public void saveMaterial(Material material);

	/**
	 *@author LiMing
	 * @param 职位对象集合
	 * 添加职位
	 */
	public void publishPosition(List<Position> po);

	/**
	 *@author LiMing
	 * @param recuritId
	 * @return
	 */
	public RecruitInfo getRecruitInfoById(String recuritId);

	/**
	 *@author LiMing
	 * @param recruitId
	 * @return
	 */
	public List<String> getPositionNameByRecruitId(String recruitId);

	/**
	 *@author LiMing
	 * @param recruit
	 * 更新发布信息
	 */
	public void updateRcruit(RecruitInfo recruit);

	/**
	 *@author LiMing
	 * @param organizationId
	 * 删除原职位信息
	 * @param recruitId 
	 */
	public void deletePosition(String organizationId, String recruitId);
}
