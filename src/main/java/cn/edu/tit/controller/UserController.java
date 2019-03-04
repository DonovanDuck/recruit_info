package cn.edu.tit.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import cn.edu.tit.bean.Apply;
import cn.edu.tit.bean.Position;
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
		User publisher = (User) request.getSession().getAttribute("User");
		String publisherId = publisher.getUserId();
		try {
			//获取招聘信息
			list = userService.getRecruitInfo(publisherId);
			mv.addObject("list",list);
			mv.setViewName("/jsp/mainJsp");//设置返回页面
		} catch (Exception e) {
			e.printStackTrace();
			mv = null;
		}
		return mv;
	}
	
	/**
	 * 查询招聘信息
	 * @param request
	 * @param search
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="searchRecruit",method= {RequestMethod.GET})
	public ModelAndView searchRecruit(HttpServletRequest request,@RequestParam(value="search") String search) throws Exception {
		ModelAndView mv = new ModelAndView();
		List<Position> list = new ArrayList<Position>();
		User publisher = (User) request.getSession().getAttribute("User");
		String organizationId = publisher.getOrganizationId();
		try {
			list = userService.getPosition(organizationId);
			if(list!=null)
			{
			mv.addObject("list",list);
			}
			mv.setViewName("/jsp/publishRecruit");//设置返回页面
		} catch (Exception e) {
			e.printStackTrace();
			mv = null;
		}
		return mv;
	}


	@RequestMapping(value="userLogin",method= {RequestMethod.GET})
	public ModelAndView userLogin(HttpServletRequest request,@RequestParam(value="employeeNum") String employeeNum,@RequestParam(value="password") String password) throws Exception {
		ModelAndView mv = new ModelAndView();
		User user = new User();
		try {
			user = userService.getUserByName(employeeNum);
			if(user ==null||!user.getPassword().equals(password))
			{
				mv.setViewName("/jsp/login");//设置返回页面
			}else {
				request.getSession().setAttribute("User", user);
				mv = toMainPage(request);
			}
		} catch (Exception e) {
			e.printStackTrace();
			mv = null;
		}
		return mv;
	}
	
	@RequestMapping(value="toPublishRcruitPage",method= {RequestMethod.GET})
	public ModelAndView toPublishRcruitPage(HttpServletRequest request)throws Exception {
		ModelAndView mv = new ModelAndView();
		try {
			User user = (User)request.getSession().getAttribute("User");
			String organizationId = user.getOrganizationId();
			List<Position> list = new ArrayList<Position>();
			list = userService.getPosition(organizationId);//根据单位ID找单位的职位
			mv.addObject("positionList", list);
			mv.setViewName("/jsp/publishRecruit");
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
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		try {
			String recruitId = Common.uuid();//设置招聘信息的ID
			Object[] obj = Common.fileFactory(request,recruitId);
			Map<String, Object> formdata = (Map<String, Object>) obj[1];
			List<File> returnFileList = (List<File>) obj[0]; // 要返回的文件集合
			RecruitInfo recruit = new RecruitInfo();
			User publisher = (User) request.getSession().getAttribute("User");
			String publisherId = publisher.getUserId();
			
			recruit.setOrganization(publisher.getOrganizationName());//设置招聘单位
			recruit.setPublisher(publisherId);//发布者ID
			ts = Timestamp.valueOf((String)formdata.get("endTime"));  
			recruit.setEndTime(ts);//设置结束时间
			recruit.setPublishTime(new Timestamp(System.currentTimeMillis()));//设置发布时间
			recruit.setRecruitId(recruitId);//设置招聘信息ID
			recruit.setRecruitInfo((String) formdata.get("organizationTitle"));//设置招聘标题
			ts = Timestamp.valueOf((String)formdata.get("startTime"));  
			recruit.setStartTime(ts);//设置开始时间
			/**
			 * 处理前台的职位数据，共四个数据
			 * */
			String pos = (String)formdata.get("positionContent");
			String nu =  (String)formdata.get("positionNumber");
			String pro = (String)formdata.get("positionProfession");
			String comp = (String)formdata.get("compilationNature");
			String[] positon = pos.split(",");
			String[] num = nu.split(",");
			String[] profetional = pro.split(",");
			String[] compilationNature = comp.split(",");
			Position po;
			List<Position> listPo = new ArrayList<Position>();
			for (int i = 0; i < positon.length; i++) {
				po = new Position();
				po.setCompilationNature(compilationNature[i]);
				po.setId(Common.uuid());
				po.setOrganization(publisher.getOrganizationId());
				po.setPositionNum(Integer.parseInt(num[i]));
				po.setPositonName(positon[i]);
				po.setProfessionalOrientation(profetional[i]);
				po.setRecruitId(recruitId);
				listPo.add(po);
			}
			userService.publishPosition(listPo);//添加职位
			/**结束*/
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
			user.setOrganizationId(Common.uuid());
			if("on".equals(request.getParameter("authority")))
				user.setAuthority(1);
			else
				user.setAuthority(0);
			userService.addUser(user);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return toUserInfo(request, mv);
	}

	/**
	 * 添加用户
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="toUpdateRecuit")
	public ModelAndView toUpdateRecuit(HttpServletRequest request,@RequestParam(value="recuritId")String recuritId) throws Exception{
		ModelAndView mv = new ModelAndView();
		List<Position> list = new ArrayList<Position>();
		List<String> positionName = new ArrayList<String>();
		try {
			RecruitInfo r =new RecruitInfo();
			r = userService.getRecruitInfoById(recuritId);//招聘信息
			list = userService.getPositionByRecruitId(r.getRecruitId());//单位发布的职位信息
			positionName = userService.getPositionNameByRecruitId(r.getRecruitId());//单位下的所有职位
			mv.addObject("recruit", r);
			mv.addObject("positionList", list);
			mv.addObject("positionName", positionName);
			mv.setViewName("/jsp/updateRecruit");
		} catch (Exception e) {
			mv = toMainPage(request);
			e.printStackTrace();
		}
		return mv;
	}

	/**
	 * 跳转到用户列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value="toUserInfo")
	public ModelAndView toUserInfo(HttpServletRequest request, ModelAndView mv){
		if(mv == null)
			 mv = new ModelAndView();
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
	 * 跳转到用户列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value="toPersonalInfo")
	public ModelAndView toPersonalInfo(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		try {
			User user = (User) request.getSession().getAttribute("user");
			mv.addObject("user", user);
			mv.setViewName("/jsp/personalInfo");
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
	 * 修改用户信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value="modifyUser")
	public ModelAndView modifyUser(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		try {
			//获取用户信息
			User user = new User();
			user.setUserId((request.getParameter("userId")));
			user.setOrganizationName(request.getParameter("organizationName"));
			user.setUserName(request.getParameter("userName"));
			String au = request.getParameter("authority");
			if("on".equals(au))
				user.setAuthority(1);
			else
				user.setAuthority(0);
			if(!"".equals(user.getUserId())){
				userService.modifyuser(user);
				mv.addObject("status","OK");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			mv.addObject("status","ERROR");
		}
		return toUserInfo(request, mv);
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
	 * 重置密码
	 * @param request
	 * @return
	 */
	@RequestMapping(value="ajaxRePassword")
	public void ajaxRePassword(HttpServletRequest request, HttpServletResponse response){
		String result = "";
		try {
			//校验密码
			String userId = request.getParameter("userId");
			userService.rePassword(userId);
			result = JSONObject.toJSONString("OK");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result = JSONObject.toJSONString("ERROR");
		}
		try {
			response.getWriter().println(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
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

	@RequestMapping(value="toSignInInfo")
	public ModelAndView toSignInInfo(HttpServletRequest request,@RequestParam(value="recruitId")String recruitId) {
		ModelAndView mv = new ModelAndView();
		request.getSession().setAttribute("recruitId", recruitId);
		List<Position> occupationApplicantLsit = new ArrayList<Position>();
		occupationApplicantLsit = userService.getPosition(recruitId);//该招聘信息对应的所有职位对象
		mv.addObject("occupationApplicantLsit", occupationApplicantLsit);
		mv.setViewName("/jsp/application_status");
		return mv;
		
	}
	@RequestMapping(value = "toStatistics")
	public ModelAndView toStatistics(HttpServletRequest request,@RequestParam(value="positonName")String positonName) {
		
		ModelAndView mv = new ModelAndView();
		String recruitId = (String) request.getSession().getAttribute("recruitId");
		List<Apply> applList = new ArrayList<Apply>();
		List<Position> occupationApplicantLsit = new ArrayList<Position>();
		Integer numAll, numAllToday ,numDoctor,numDoctorToday,numMaster,numMasterToday,numBachelor,numBachelorToday,numInSide,numInSideToday;
		//获取今日时间
		Date date = new Date();// 取时间
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.DATE, 0);// 把日期往后增加一天.整数往后推,负数往前移动
		date = calendar.getTime(); // 这个时间就是日期往后推一天的结果
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(date);
		
		numAllToday = userService.applyNumToday(recruitId,dateString,positonName); //今天报名人数
		numAll = userService.applyNum(recruitId,positonName);//总报名人数
		numBachelor = userService.applyNumBachelor(recruitId,positonName);//总学士人数
		numBachelorToday = userService.applyNumBachelorToday(recruitId, dateString,positonName);//今日总学士人数
		numMaster = userService.applyNumMaster(recruitId,positonName);//总硕士人数
		numMasterToday = userService.applyNumMasterToday(recruitId, dateString,positonName);//今日总硕士人数
		numDoctor = userService.applyNumDoctor(recruitId,positonName);//总博士人数
		numDoctorToday = userService.applyNumDoctorToday(recruitId, dateString,positonName);//今日总博士人数
		numInSide = userService.applyNumInSide(recruitId,positonName);//疆内人数
		numInSideToday = userService.applyNumInSideToday(recruitId, dateString,positonName);//今日疆内人数
		applList = userService.applyList(recruitId);//报名表
		mv.addObject("applList",applList);
		mv.addObject("numInSideToday",numInSideToday);
		mv.addObject("numInSide",numInSide);
		mv.addObject("numDoctorToday",numDoctorToday);
		mv.addObject("numMaster",numMaster);
		mv.addObject("numMasterToday",numMasterToday);
		mv.addObject("numBachelorToday",numBachelorToday);
		mv.addObject("numBachelor",numBachelor);
		mv.addObject("numAll",numAll);
		mv.addObject("numAllToday",numAllToday);
		mv.setViewName("/jsp/signIn_iframe");
		return mv;
		
	}

}
