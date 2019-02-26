package cn.edu.tit.controller;

import java.io.File;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

import cn.edu.tit.bean.Apply;
import cn.edu.tit.bean.RecruitInfo;
import cn.edu.tit.bean.User;
import cn.edu.tit.common.Common;
import cn.edu.tit.common.DateConverter;
import cn.edu.tit.iservice.IUserService;
/**
 * @author LiMing
 * 管理员Controller层
 */
@RequestMapping("/user")
@Controller
public class UserController {
	@Autowired
	private IUserService userService;

	@RequestMapping(value="toMainPage",method= {RequestMethod.GET})
	public ModelAndView toMainPage(HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		List<RecruitInfo> list = new ArrayList<RecruitInfo>();
		//		User publisher = (User) request.getSession().getAttribute("User");
		//		String publisherId = publisher.getUserId();
		try {
			//获取招聘信息
			list = userService.getRecruitInfo(null);
			mv.addObject("list",list);
			mv.setViewName("/jsp/mainJsp");//设置返回页面
		} catch (Exception e) {
			e.printStackTrace();
			mv = null;
		}
		return mv;
	}
	
	@RequestMapping(value="searchRecruit",method= {RequestMethod.GET})
	public ModelAndView searchRecruit(HttpServletRequest request,@RequestParam(value="search") String search) throws Exception {
		ModelAndView mv = new ModelAndView();
		List<RecruitInfo> list = new ArrayList<RecruitInfo>();
		try {
			//获取招聘信息
			list = userService.searchRecruit(search);
			mv.addObject("list",list);
			mv.setViewName("/jsp/mainJsp");//设置返回页面
		} catch (Exception e) {
			e.printStackTrace();
			mv = null;
		}
		return mv;
	}

	@RequestMapping(value="toPublishRcruitPage",method= {RequestMethod.GET})
	public ModelAndView toPublishRcruitPage() throws Exception {
		ModelAndView mv = new ModelAndView();
		try {
			mv.setViewName("/jsp/publishRecruit");//设置返回页面
		} catch (Exception e) {
			e.printStackTrace();
			mv = null;
		}
		return mv;
	}

	/**
	 * 发布招聘信息
	 * 返回页面调主页面(toMainPage)
	 * */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="publishRcruit",method= {RequestMethod.POST})
	public ModelAndView publishRcruit(HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		try {
			String recruitId = Common.uuid();
			Object[] obj = Common.fileFactory(request,recruitId);
			Map<String, Object> formdata = (Map<String, Object>) obj[1];
			List<File> returnFileList = (List<File>) obj[0]; // 要返回的文件集合
			RecruitInfo recruit = new RecruitInfo();
			recruit.setOrganization((String) formdata.get("organization"));
			recruit.setPublisher("111");
			recruit.setEndTime(timeConverter((String)formdata.get("endTime")));
			recruit.setPublishTime(new Timestamp(System.currentTimeMillis()));
			recruit.setRecruitId(recruitId);
			recruit.setRecruitInfo((String) formdata.get("recruitInfo"));
			recruit.setStartTime(timeConverter((String)formdata.get("startTime")));
			if(!returnFileList.isEmpty())
			{
				recruit.setAccessory(returnFileList.get(0).getPath());
			}
			userService.publishRcruit(recruit);
			mv = toMainPage(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	
	/** @author Liming
	 * @param 前台获取的时间格式 
	 * 返回 Timestamp 格式时间
	 * */
	@SuppressWarnings("unused")
	private Timestamp timeConverter(String time) throws ParseException {
		SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm",Locale.ENGLISH);//输入的被转化的时间格式
		SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//需要转化成的时间格式
		java.util.Date date1 = dff.parse(time);  
		String str1 = df1.format(date1);
		Timestamp ts = new Timestamp(System.currentTimeMillis());  
		try {  
			ts = Timestamp.valueOf(str1);  
			System.out.println("获取的时间为----->"+ts);
		} catch (Exception e) {  
			e.printStackTrace();  
		} 
		return ts;
	}

}