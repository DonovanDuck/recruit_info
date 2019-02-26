package cn.edu.tit.controller.wxController;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

import cn.edu.tit.bean.Apply;
import cn.edu.tit.bean.User;
import cn.edu.tit.common.Common;
import cn.edu.tit.iservice.IUserService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 提供给微信的接口
 * 
 * @author 20586
 *
 */
@RequestMapping("WxController")
@RestController
public class WxTeacherController {

	@Autowired
	private IUserService userService;
	
	/**
	 * 微信端登录
	 * 
	 * @param request
	 * @param userId
	 * @param password
	 */
	@RequestMapping(value = "userLogin")
	public Map<String, Object> userLogin(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "userId", required = false) String userId,
			@RequestParam(value = "password", required = false) String password) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Map<String, Object> ret = new HashMap<String, Object>();
		response.setContentType("text/plain;charset=UTF-8");
		String readResult = null;
		String userPassword = null;
		if (userId == null || password == null) {
			ret.put("status", "ERROR");
			ret.put("msg", "信息错误");
			return ret;
		} else {
			try {
				// 查找用户
				User user = userService.getUser(userId, password);
				userPassword = Common.eccryptMD5(password);
				if (user != null) {
					if (userPassword.equals(user.getPassword())) {
						ret.put("user", user);
						return ret;
					} else {
						ret.put("status", "ERROR");
						ret.put("msg", "密码错误");
						return ret;
					}
				} 
				else {
					ret.put("status", "ERROR");
					ret.put("msg", "用户名错误");
					return ret;
				}

			} catch (Exception e) {
				e.printStackTrace();
				ret.put("status", "ERROR");
				ret.put("msg", "用户名或密码错误");
				return ret;
			}
		}
	}

	/**
	 * 添加用户
	 * @param request
	 * @return
	 */
	@RequestMapping(value="addUser")
	public Map<String, Object> addUser(HttpServletRequest request){
		Map<String, Object> ret = new HashMap<String, Object>();
		try {
			User user = new User();
			user.setUserId(Common.uuid());
			user.setPassword("123456");
			user.setOrganizationName(request.getParameter("organization"));
			user.setUserName(request.getParameter("userName"));
			user.setWechartNum(request.getParameter("weChat"));
			userService.addUser(user);
			ret.put("status", "OK");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			ret.put("status", "ERROR");
		}
		return ret;
	}
	
	/**
	 * 修改用户密码
	 * @param request
	 * @return
	 */
	@RequestMapping(value="modifyPassword")
	public Map<String, Object> modifyPassword(HttpServletRequest request){
		Map<String, Object> ret = new HashMap<String, Object>();
		String userId = "";
		String password = "";
		try {
			//获取用户id
			 userId = (String) request.getSession().getAttribute("userId");
			 password = request.getParameter("newPassword");
			if(!"".equals(userId) || !"".equals(password)){
				userService.modifyPassword(userId, password);
			}
			ret.put("status", "OK");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			ret.put("status", "ERROR");
		}
		return ret;
	}
	
	/**
	 * 读取用户报名表
	 * @param request
	 * @return
	 */
	@RequestMapping(value="readApply")
	public Map<String, Object> readApply(HttpServletRequest request){
		Map<String, Object> ret = new HashMap<String, Object>();
		String applyId = request.getParameter("applyId");
		try {
			//通过applyId获取报名表
			Apply apply = userService.getApplyById("1");
			if(apply != null){
				ret.put("apply",apply);
				ret.put("status", "OK");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			ret.put("status", "ERROR");
		}
		return ret;
	}
	

}
