package cn.edu.tit.idao;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import cn.edu.tit.bean.Apply;
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
	 * @author LiMing
	 * @param 单位名
	 * @return 按照单位名查找招聘信息
	 */
	public List<RecruitInfo> searchRecruit(@Param("search")String search);
	
	
	
	/**
	 * 修改用户信息
	 * @param user
	 */
	public void modifyUser(User user);
}
