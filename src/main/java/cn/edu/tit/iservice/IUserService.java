package cn.edu.tit.iservice;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import cn.edu.tit.bean.RecruitInfo;
import cn.edu.tit.bean.User;


public interface IUserService {

	/**
	 * @author 
	 * 增加用户信息
	 * */
	public void addUser() throws Exception;
	
	/**
	 * @author 
	 * 更新用户信息
	 * */
	public void updateUser() throws Exception;

	/**
	 *@author LiMing
	 * @param recruit
	 * 发布招聘信息
	 */
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
}
