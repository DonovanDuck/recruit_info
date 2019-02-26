package cn.edu.tit.iservice.iserviceImpl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cn.edu.tit.bean.Apply;
import cn.edu.tit.bean.User;
import cn.edu.tit.common.Common;
import cn.edu.tit.idao.IUserDao;
import cn.edu.tit.iservice.IUserService;

/**
 * @author LiMing
 * 管理员功能模块
 * 无返回值时返回Sting类型  msg 信息，在页面弹出信息可以利用
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
	 * @author 
	 * 发布招聘信息
	 * */
	@Override
	public void publishRecuritInfo() throws Exception {
		
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
}
