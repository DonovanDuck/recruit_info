package cn.edu.tit.idao;

import java.util.List;
import org.springframework.stereotype.Component;
import cn.edu.tit.bean.User;

@Component
public interface IUserDao {

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
	 * @author 
	 * 发布招聘信息
	 * */
	public void publishRecuritInfo() throws Exception;
}
