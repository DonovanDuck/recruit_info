package cn.edu.tit.iservice;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import cn.edu.tit.bean.Apply;
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
	 * @author 
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
}
