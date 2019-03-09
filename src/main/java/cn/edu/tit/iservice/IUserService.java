package cn.edu.tit.iservice;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import cn.edu.tit.bean.Apply;
import cn.edu.tit.bean.ApplyFamily;
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
	public List<Apply> applyList(String recruitId,String positonName);
	public List<Apply> applyListAll(String recruitId);
	public Integer applyNum(String recruitId,String positonName);
	public Integer applyNumDoctor(String recruitId,String positonName);
	public Integer applyNumMaster(String recruitId,String positonName);
	public Integer applyNumBachelor(String recruitId,String positonName);
	public Integer applyNumDoubleOne(String recruitId,String positonName);
	public Integer applyNumInSide(String recruitId,String positonName);
	public Integer applyNumFirstSchoolInUndergraduate(String recruitId,String positonName);
	public Integer applyNumFirstSchoolInPastgraduate(String recruitId,String positonName);
	public Integer applyNumFirstSchoolInDoctor(String recruitId,String positonName);
	public Integer applyNumFirstMajorInUndergraduate(String recruitId, String positonName);
	public Integer applyNumFirstMajorInPastgraduate(String recruitId,String positonName);
	public Integer applyNumFirstMajorInDoctor(String recruitId,String positonName);
	
	public Integer applyNumToday(String recruitId, String dateString,String positonName);
	public Integer applyNumDoctorToday(String recruitId, String dateString,String positonName);
	public Integer applyNumMasterToday(String recruitId, String dateString,String positonName);
	public Integer applyNumBachelorToday(String recruitId, String dateString,String positonName);
	public Integer applyNumDoubleOneToday(String recruitId, String dateString,String positonName);
	public Integer applyNumInSideToday(String recruitId, String dateString,String positonName);
	public Integer applyNumFirstSchoolInUndergraduateToday(String recruitId, String dateString,String positonName);
	public Integer applyNumFirstSchoolInPastgraduateToday(String recruitId, String dateString,String positonName);
	public Integer applyNumFirstSchoolInDoctorToday(String recruitId, String dateString,String positonName);
	public Integer applyNumFirstMajorInUndergraduateToday(String recruitId, String dateString,String positonName);
	public Integer applyNumFirstMajorInPastgraduateToday(String recruitId, String dateString,String positonName);
	public Integer applyNumFirstMajorInDoctorToday(String recruitId, String dateString,String positonName);
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
	public RecruitInfo getRecruitInfoById(String recruitId);
	
	/**
	 * 存储报名材料
	 * @param material
	 */
	public void saveMaterial(Material material);

	public boolean isFirstSchool(String applyId);

	public boolean isFirstMajor(String applyId);

	/**
	 *@author LiMing
	 * @param 职位对象集合
	 * 添加职位
	 */
	public void publishPosition(List<Position> po);

	
	/**
	 *@author LiMing
	 * @param recruitId
	 * @return
	 */
	public List<String> getPositionNameByRecruitId(String recruitId);
	
	/**
	 * 获取报名人家庭信息
	 * @param applyId
	 * @return
	 */
	public List<ApplyFamily> getApplyFamily(String applyId);

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
	/**
	 * @author wenli
	 * @param recruitId
	 * 根据职位名和招聘信息id获得职位信息
	 * @param positionName
	 * @return
	 */
	public Position getPositionByPositionNameAndRecruitId(String recruitId,String positionName);

}
