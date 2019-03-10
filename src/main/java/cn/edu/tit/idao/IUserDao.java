package cn.edu.tit.idao;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import cn.edu.tit.bean.Apply;
import cn.edu.tit.bean.ApplyFamily;
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
	 * 获取报名人家庭信息
	 * @param applyId
	 * @return
	 */
	public List<ApplyFamily> getApplyFamily(String applyId);

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
	public List<RecruitInfo> getRecruitInfo(@Param("organization")String organization);

	/**
	 *@author LiMing
	 * @param organizationId
	 * @return 获取单位职位
	 */
	public List<Position> getPosition(@Param("organizationId")String organizationId);

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
	public Integer applyNumFirstSchoolInUndergraduate(@Param("recruitId")String recruitId,@Param("positonName")String positonName);
	public Integer applyNumFirstSchoolInPastgraduate(@Param("recruitId")String recruitId,@Param("positonName")String positonName);
	public Integer applyNumFirstSchoolInDoctor(@Param("recruitId")String recruitId,@Param("positonName")String positonName);
	public Integer applyNumFirstMajorInUndergraduate(@Param("recruitId")String recruitId,@Param("positonName")String positonName);
	public Integer applyNumFirstMajorInPastgraduate(@Param("recruitId")String recruitId,@Param("positonName")String positonName);
	public Integer applyNumFirstMajorInDoctor(@Param("recruitId")String recruitId,@Param("positonName")String positonName);
	

	public Integer applyNumDoctorToday(@Param("recruitId")String recruitId,@Param("dateString") String dateString,@Param("positonName")String positonName);
	public Integer applyNumMasterToday(@Param("recruitId")String recruitId, @Param("dateString")String dateString,@Param("positonName")String positonName);
	public Integer applyNumBachelorToday(@Param("recruitId")String recruitId,@Param("dateString") String dateString,@Param("positonName")String positonName);
	public Integer applyNumDoubleOneToday(@Param("recruitId")String recruitId, @Param("dateString")String dateString,@Param("positonName")String positonName);
	public Integer applyNumInSideToday(@Param("recruitId")String recruitId, @Param("dateString")String dateString,@Param("positonName")String positonName);
	public Integer applyNumFirstSchoolInUndergraduateToday(@Param("recruitId")String recruitId, @Param("dateString")String dateString,@Param("positonName")String positonName);
	public Integer applyNumFirstSchoolInPastgraduateToday(@Param("recruitId")String recruitId, @Param("dateString")String dateString,@Param("positonName")String positonName);
	public Integer applyNumFirstSchoolInDoctorToday(@Param("recruitId")String recruitId, @Param("dateString")String dateString,@Param("positonName")String positonName);
	public Integer applyNumFirstMajorInUndergraduateToday(@Param("recruitId")String recruitId, @Param("dateString")String dateString,@Param("positonName")String positonName);
	public Integer applyNumFirstMajorInPastgraduateToday(@Param("recruitId")String recruitId, @Param("dateString")String dateString,@Param("positonName")String positonName);
	public Integer applyNumFirstMajorInDoctorToday(@Param("recruitId")String recruitId, @Param("dateString")String dateString,@Param("positonName")String positonName);
	
	public User getUserByPhone(@Param("phoneNum")String phoneNum);
	public User getUserById(@Param("userId")String userId);
	
	/**
	 * 重置密码
	 * @param userId
	 */
	public void rePassword(@Param("userId")String userId,@Param("password")String password);
	
	/**
	 * 通过招聘信息获取相应职位信息
	 * @return
	 */
	public List<Position> getPositionByRecruitId(@Param("recruitId")String recruitId);
	
	
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
	public RecruitInfo getRecruitInfoById(@Param("recruitId")String recuritId);
	
	/**
	 *@author LiMing
	 * @param recruitId
	 * @return
	 */
	public List<String> getPositionNameByRecruitId(@Param("recuritId")String recuritId);

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
	public void deletePosition(@Param("organizationId")String organizationId,@Param("recuritId") String recruitId);

	/**
	 *@author LiMing
	 * @param organizationId
	 * @return
	 */
	public List<String> getPositionNameByorganizationId(@Param("organizationId")String organizationId);

	
	 * @author wenli
	 * @param recruitId
	 * 根据职位名和招聘信息id获得职位信息
	 * @param positionName
	 * @return
	 */
	public Position getPositionByPositionNameAndRecruitId(@Param("recruitId")String recruitId,@Param("positionName")String positionName);
}
