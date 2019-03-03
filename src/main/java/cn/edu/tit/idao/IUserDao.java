package cn.edu.tit.idao;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import cn.edu.tit.bean.Apply;
import cn.edu.tit.bean.Material;
import cn.edu.tit.bean.Position;
import cn.edu.tit.bean.User;
import cn.edu.tit.bean.RecruitInfo;

@Component
public interface IUserDao {

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
	 *@author LiMing
	 * @param recruit
	 * 发布招聘信息
	 * */
	public void publishRecuritInfo() throws Exception;
	
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
	public void modifyPassword(@Param(value="userId")String userId,@Param(value="password")String password);
	
	/**
	 * 根据id，密码查询用户
	 * @param userId
	 * @param password
	 */
	public User getUserByIdAndPs(@Param(value="userId")String userId,@Param(value="password")String password);
	
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
	public void publishRcruit(RecruitInfo info) throws Exception;

	/**
	 *@author LiMing
	 * @param 当参数(publisherId)为空时,查询所有招聘信息.
	 * @param 当参数(publisherId)不空时,条件查询该ID下的招聘信息
	 * @return 获取所有招聘信息
	 */
	public List<RecruitInfo> getRecruitInfo(@Param("publisherId")String publisherId);

	/**
	 *@author LiMing
	 * @param organizationId
	 * @return 获取单位职位
	 */
	public List<Position> getPosition(@Param("string")String string);

	/**
	 *@author LiMing
	 * @param employeeNum
	 * @return 返回用户实体
	 */
	public List<RecruitInfo> searchRecruit(@Param("search")String search);
	
	
	
	/**
	 * 修改用户信息
	 * @param user
	 */
	public void modifyUser(User user);
	public List<Apply> applyList(@Param("recruitId")String recruitId,@Param("positonName")String positonName);
	public List<Apply> applyListAll(@Param("recruitId")String recruitId);
	public Integer applyNum(@Param("recruitId")String recruitId,@Param("positonName")String positonName);

	public Integer applyNumToday(@Param("recruitId")String recruitId,@Param("dateString") String dateString,@Param("positonName")String positonName);

	public Integer applyNumDoctor(@Param("recruitId")String recruitId,@Param("positonName")String positonName);
	public Integer applyNumMaster(@Param("recruitId")String recruitId,@Param("positonName")String positonName);
	public Integer applyNumBachelor(@Param("recruitId")String recruitId,@Param("positonName")String positonName);
	public Integer applyNumDoubleOne(@Param("recruitId")String recruitId,@Param("positonName")String positonName);
	public Integer applyNumInSide(@Param("recruitId")String recruitId,@Param("positonName")String positonName);

	public Integer applyNumDoctorToday(@Param("recruitId")String recruitId,@Param("dateString") String dateString,@Param("positonName")String positonName);
	public Integer applyNumMasterToday(@Param("recruitId")String recruitId, @Param("dateString")String dateString,@Param("positonName")String positonName);
	public Integer applyNumBachelorToday(@Param("recruitId")String recruitId,@Param("dateString") String dateString,@Param("positonName")String positonName);
	public Integer applyNumDoubleOneToday(@Param("recruitId")String recruitId, @Param("dateString")String dateString,@Param("positonName")String positonName);
	public Integer applyNumInSideToday(@Param("recruitId")String recruitId, @Param("dateString")String dateString,@Param("positonName")String positonName);
	
	public User getUserById(@Param("employeeNum")String employeeNum);
	
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

	public Integer undergraduateIsFirstSchool(@Param("applyId")String applyId);

	public Integer graduateIsFirstSchool(@Param("applyId")String applyId);

	public Integer doctorIsFirstSchool(@Param("applyId")String applyId);

	public Integer undergraduateIsFirstMajor(@Param("applyId")String applyId);

	public Integer graduateIsFirstMajor(@Param("applyId")String applyId);

	public Integer doctorIsFirstMajor(@Param("applyId")String applyId);

	public RecruitInfo getRecruitInfoById(@Param("recruitId")String recruitId);


	
	
}
