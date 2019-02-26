package cn.edu.tit.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Controller;
import org.apache.http.entity.ContentType;
import org.junit.runner.Request;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

import cn.edu.tit.bean.Apply;
import cn.edu.tit.bean.User;
import cn.edu.tit.common.Common;
import cn.edu.tit.iservice.IUserService;
import net.sf.json.JSONArray;
/**
 * @author LiMing
 * 管理员Controller层
 */

@Controller
@RequestMapping("/admin")
public class UserController {
	@Autowired
	private IUserService userService;


	/**
	 * @author LiMing
	 * 添加教师的方法  excel 相关的操作,将数据插入到数据库 
	 * 使用spring的MultipartFile上传文件
	 * */
	@RequestMapping(value="AddTeacher",method= {RequestMethod.POST})
	public ModelAndView DoExcelTeacher(HttpServletRequest request) throws Exception {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax(4194304); // 设置最大文件尺寸，这里是4MB
		List<FileItem> items = new ArrayList<FileItem>();
		try {
			items = upload.parseRequest(request);
		} catch (FileUploadException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		File file = new File("");
		for(FileItem fi : items) {
			File fullFile = new File(new String(fi.getName().getBytes(), "utf-8")); // 解决文件名乱码问题,获得文件内容
			file = new File("/home/wenruo/Desktop/userInfo", fullFile.getName()); // 为文件设置存储路径
			fi.write(file);
		}
		FileInputStream fileInputStream = new FileInputStream(file);
		MultipartFile multipartFile = new MockMultipartFile(file.getName(), file.getName(),
				ContentType.APPLICATION_OCTET_STREAM.toString(), fileInputStream);
		ModelAndView mv = new ModelAndView();
		String readResult =null;
		try {
			//调用ITeacherService 下的方法，完成增加教师
		//	readResult = iAdminService.addTeacherInfo(multipartFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//mv = readTeacherInfo();
		mv.addObject("readResult", readResult);//返回信息
		return mv;
	}
	
	/**
	 * 添加用户
	 * @param request
	 * @return
	 */
	@RequestMapping(value="addUser")
	public ModelAndView addUser(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		try {
			User user = new User();
			user.setUserId(Common.uuid());
			user.setPassword("123456");
			user.setOrganizationName(request.getParameter("organization"));
			user.setUserName(request.getParameter("userName"));
			user.setWechartNum(request.getParameter("weChat"));
			userService.addUser(user);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return toUserInfo(request);
	}

	/**
	 * 跳转到用户列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value="toUserInfo")
	public ModelAndView toUserInfo(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		try {
			List<User> userList = userService.getUser();
			mv.addObject("userList", userList);
			mv.setViewName("/jsp/userInfo");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return mv;
	}
	
	/**
	 * 修改用户密码
	 * @param request
	 * @return
	 */
	@RequestMapping(value="modifyPassword")
	public ModelAndView modifyPassword(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		String userId = "";
		String password = "";
		try {
			//获取用户id
			 userId = (String) request.getSession().getAttribute("userId");
			 password = request.getParameter("newPassword");
			if(!"".equals(userId) || !"".equals(password)){
				userService.modifyPassword(userId, password);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return mv;
	}
	
	
	/**
	 * 校验用户原密码
	 * @param request
	 * @return
	 */
	@RequestMapping(value="ajaxCheckPassword")
	public void ajaxCheckPassword(HttpServletRequest request, HttpServletResponse response){
		try {
			//校验密码
//			String userId = (String) request.getSession().getAttribute("userId");
			String userId = "1";
			String password = request.getParameter("password");
			String result = "";
			Boolean isRight = userService.checkPassword(userId, password);
			if(isRight)
				result = JSONObject.toJSONString("OK");
			else
				result = JSONObject.toJSONString("ERROR");
			response.getWriter().println(result);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	/**
	 * 读取用户报名表
	 * @param request
	 * @return
	 */
	@RequestMapping(value="readApply")
	public ModelAndView readApply(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		String applyId = request.getParameter("applyId");
		try {
			//通过applyId获取报名表
			Apply apply = userService.getApplyById("1");
			if(apply != null){
				request.setAttribute("apply",apply);
			}
			mv.setViewName("/jsp/userApplySituation");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return mv;
	}
	
}
