package cn.edu.tit.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
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
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import cn.edu.tit.bean.Apply;
import cn.edu.tit.bean.ApplyFamily;
import cn.edu.tit.bean.Organization;
import cn.edu.tit.bean.Position;
import cn.edu.tit.bean.RecruitInfo;
import cn.edu.tit.bean.User;
import cn.edu.tit.common.Common;
import cn.edu.tit.common.DateConverter;
import cn.edu.tit.iservice.IUserService;
import cn.edu.tit.util.ExcelExportUtil;

/**
 * @author LiMing 管理员Controller层
 */
@RequestMapping("/user")
@Controller
public class UserController {
	@Autowired
	private IUserService userService;

	@RequestMapping(value = "toMainPage", method = { RequestMethod.GET })
	public ModelAndView toMainPage(HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		List<RecruitInfo> list = new ArrayList<RecruitInfo>();
		Date day=new Date();    
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		String time = df.format(day).toString(); //获取系统时间，将时间放入session
		try {
			
				list = userService.getAllRecruitInfo(); // 获取所有招聘信息
			
			//获取公司名
			List<String> onameList = new ArrayList<>();
			for(RecruitInfo r : list){
				String oName = userService.getOrganizationNameById(r.getOrganization());
				onameList.add(oName);
			}
			mv.addObject("systemTime",time);
			mv.addObject("onameList",onameList);
			mv.addObject("list",list);
			mv.setViewName("/jsp/mainJsp");//设置返回页面
		} catch (Exception e) {
			e.printStackTrace();
			mv.setViewName("/jsp/login");
		}
		return mv;
	}


	/**
	 * 登录校验
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "ajaxCheckLogin")
	public void ajaxCheckLogin(HttpServletRequest request, HttpServletResponse response) {
		String result = "";
		try {
			// 校验密码
			String phoneNum = request.getParameter("phoneNum");
			String password = request.getParameter("password");
			User user = userService.getUserByPhone(phoneNum);//根据电话找用户
			
			password = Common.eccryptMD5(password);
			if (user == null || !user.getPassword().equals(password)) {
				result = JSONObject.toJSONString("ERROR");
			} else {
				result = JSONObject.toJSONString("OK");
			}
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

	@RequestMapping(value = "userLogin", method = { RequestMethod.GET })
	public ModelAndView userLogin(HttpServletRequest request, @RequestParam(value = "phoneNum") String phoneNum,
			@RequestParam(value = "password") String password) throws Exception {
		ModelAndView mv = new ModelAndView();
		User user = new User();
		try {
			user = userService.getUserByPhone(phoneNum);
			password = Common.eccryptMD5(password);
			if (user == null || !user.getPassword().equals(password)) {
				mv.setViewName("/jsp/login");// 设置返回页面
			} else {
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
			List<String> list = new ArrayList<String>();
			list = userService.getPositionNameByorganizationId(organizationId);//根据单位ID找单位的职位
			mv.addObject("positionName", list);
			mv.setViewName("/jsp/publishRecruit");
		} catch (Exception e) {
			e.printStackTrace();
			mv = null;
		}
		return mv;
	}

	/**
	 * 发布招聘信息 返回页面调主页面(toMainPage)
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "publishRcruit", method = { RequestMethod.POST })
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
			recruit.setOrganization(publisher.getOrganizationId());//设置招聘单位
			recruit.setPublisher(publisherId);//发布者ID
			recruit.setPublisherName(publisher.getUserName()); //发布人名
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
			//判断，如果输入人数为空，则默认0人
			for (int i = 0; i < num.length; i++) {
				if(num[i]==""||num[i]==null)
					num[i]="0";
			}
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
			if(positon.length!=0)
			{
				userService.publishPosition(listPo);//添加职位
			}
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
	 * 更新发布信息页
	 * 表现为更新，原理类似于重新发布
	 * 返回页面调主页面(toMainPage)
	 * */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="updateRcruit",method= {RequestMethod.POST})
	public ModelAndView updateRcruit(HttpServletRequest request,@RequestParam(value="recruitId") String recruitId) throws Exception {
		ModelAndView mv = new ModelAndView();
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		try {
			Object[] obj = Common.fileFactory(request,recruitId);
			Map<String, Object> formdata = (Map<String, Object>) obj[1];
			List<File> returnFileList = (List<File>) obj[0]; // 要返回的文件集合
			RecruitInfo recruit = new RecruitInfo();
			User publisher = (User) request.getSession().getAttribute("User");
			String publisherId = publisher.getUserId();
			String organizationId = publisher.getOrganizationId();
			//根据recruitId找到原有的招聘信息，将现有信息全部替换
			recruit = userService.getRecruitInfoById(recruitId);
			recruit.setOrganization(publisher.getOrganizationId());//设置招聘单位
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
			//判断，如果输入人数为空，则默认0人
			for (int i = 0; i < num.length; i++) {
				if(num[i]==""||num[i]==null)
					num[i]="0";
			}
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
			//根据招聘信息ID和招聘单位ID删除原发布的职位信息
			userService.deletePosition(organizationId,recruitId);
			if(positon.length!=0)
			{
				userService.publishPosition(listPo);//添加职位
			}
			/**结束*/	

			if(!returnFileList.isEmpty())
			{
				recruit.setAccessory(returnFileList.get(0).getPath());
			}
			userService.updateRcruit(recruit);
			mv = toMainPage(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}

	
	/**
	 * 跳转到添加用户界面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="toAddUser")
	public ModelAndView toAddUser(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		try {
			User user = (User) request.getSession().getAttribute("User");
			//查询所有单位
			List<Organization> organizationList = userService.getOrganization();
			List<String> organizationList2 = new ArrayList<>();
			if(user.getAuthority() == 20){
				for(Organization o : organizationList){
					if(!"01".equals(o.getOrganizaionId())){
						organizationList2.add(o.getOrganizatinName());
					}
				}
			}
			else{
				for(Organization o : organizationList){
					organizationList2.add(o.getOrganizatinName());
				}
			}
			request.setAttribute("organizationList", organizationList2);
			mv.setViewName("jsp/addUserInfo");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return mv;
	}
	/**
	 * 添加用户
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = "addUser")
	public ModelAndView addUser(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		//先查有无用户，若有此次添加的是二级公司，若无则是一级公司
		List<Organization> orList = userService.getOrganization();
		try {
			User user = new User();
			user.setUserId(Common.uuid());
			user.setPassword(Common.eccryptMD5("123456"));
			user.setUserName(request.getParameter("userName"));
			user.setWechartNum(request.getParameter("weChat"));
			user.setPhoneNum(request.getParameter("phoneNum"));
			String organizationName = request.getParameter("organization");
			user.setOrganizationName(organizationName);
			if(orList.isEmpty()){ // 用户单位为空，公司为一级公司，id：01
				user.setOrganizationId("01");
				Organization orga = new Organization("01", organizationName);
				userService.addOrganizaion(orga); // 添加公司
				if ("on".equals(request.getParameter("authority")))
					user.setAuthority(10);
				else
					user.setAuthority(11);
			}
			else{ 
				// 通过获取的id是否为空判断此处填写的公司名是已有的还是新添加的，已有的将原公司id赋予，新添加的给新的id。
				String organizationId = userService.getOrganizaionIdByName(organizationName);
				if("".equals(organizationId) || organizationId == null || organizationId == ""){
					 organizationId = Common.uuid();
					user.setOrganizationId(organizationId);
					 Organization orga = new Organization(organizationId, organizationName);
					 userService.addOrganizaion(orga); //添加新公司
					 if ("on".equals(request.getParameter("authority")))
							user.setAuthority(20);
						else
							user.setAuthority(21);
				}
				else{ // id存在，说明公司已存在
					user.setOrganizationId(organizationId);
					//判断此次添加的是否为一级公司成员而设置相应权限
					if("01".equals(organizationId)){
						if ("on".equals(request.getParameter("authority")))
							user.setAuthority(10);
						else
							user.setAuthority(11);
					}
					else{
						if ("on".equals(request.getParameter("authority")))
							user.setAuthority(20);
						else
							user.setAuthority(21);
					}
				}
				}
				
			userService.addUser(user);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return toUserInfo(request, mv);
	}

	/**
	 * 添加招聘信息
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
			positionName = userService.getPositionNameByorganizationId(r.getOrganization());//根据单位ID找单位的职位
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
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="toConsultRecuit")
	public ModelAndView toConsultRecuit(HttpServletRequest request,@RequestParam(value="recuritId")String recuritId) throws Exception{
		ModelAndView mv = new ModelAndView();
		List<Position> list = new ArrayList<Position>();
		List<String> positionName = new ArrayList<String>();
		try {
			RecruitInfo r =new RecruitInfo();
			r = userService.getRecruitInfoById(recuritId);//招聘信息
			list = userService.getPositionByRecruitId(r.getRecruitId());//单位发布的职位信息
			mv.addObject("recruit", r);
			mv.addObject("positionList", list);
			mv.setViewName("jsp/consultRecurit");
		} catch (Exception e) {
			mv = toMainPage(request);
			e.printStackTrace();
		}
		return mv;
	}

	/**
	 * 跳转到用户列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value="toUserInfo")
	public ModelAndView toUserInfo(HttpServletRequest request, ModelAndView mv){
		List<User> userList = new ArrayList<>();
		if(mv == null)
			mv = new ModelAndView();
		try {
			//获取用户权限
			User user = (User) request.getSession().getAttribute("User");
			int authority = user.getAuthority();
			// 如果是超级管理员，显示所有用户
			if(authority == 0){
				userList = userService.getUser();
			}
			// 如果是一级或二级管理，显示本单位
			else{
				userList = userService.getUserByOrganizationId(user.getOrganizationId());
			}
			mv.addObject("userList", userList);
			mv.setViewName("/jsp/userInfo");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return mv;
	}

	/**
	 * 跳转到个人信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "toPersonalInfo")
	public ModelAndView toPersonalInfo(HttpServletRequest request) {
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
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "modifyPassword")
	public ModelAndView modifyPassword(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		String userId = "";
		String password = "";
		try {
			// 获取用户id
			User user = (User) request.getSession().getAttribute("User");
			userId = user.getUserId();
			password = request.getParameter("newPassword");
			if (!"".equals(userId) && !"".equals(password)) {
				password = Common.eccryptMD5(password);
				userService.modifyPassword(userId, password);
				mv.setViewName("jsp/login");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return mv;
	}

	/**
	 * 修改用户信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "modifyUser")
	public ModelAndView modifyUser(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		try {
			// 获取用户信息
			User user = new User();
			user.setUserId(request.getParameter("userId"));
			String organizationName = request.getParameter("organizationName");
			user.setOrganizationName(organizationName);
			user.setPhoneNum(request.getParameter("phoneNum"));
			user.setUserName(request.getParameter("userName"));
			//获取公司id，是一级公司01，权限为10,11，否则为20，21
			String organizationId = userService.getOrganizaionIdByName(organizationName);
			String au = request.getParameter("authority");
			if("01".equals(organizationId)){
				if("on".equals(au))
					user.setAuthority(10);
				else
					user.setAuthority(11);
			}
			else{
				if("on".equals(au))
					user.setAuthority(20);
				else
					user.setAuthority(21);
			}
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
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "ajaxCheckPassword")
	public void ajaxCheckPassword(HttpServletRequest request, HttpServletResponse response) {
		try {
			// 校验密码
			 User user = (User) request.getSession().getAttribute("User");
//			String userId = "1";
			String password = request.getParameter("password");
			password = Common.eccryptMD5(password);
			String result = "";
			Boolean isRight = userService.checkPassword(user.getUserId(), password);
			if (isRight)
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
	 * 校验电话
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "ajaxCheckPhone")
	public void ajaxCheckPhone(HttpServletRequest request, HttpServletResponse response) {
		String result = "";
		try {
			// 校验密码
			String phoneNum = request.getParameter("phoneNum");
			User user = userService.getUserByPhone(phoneNum);//根据电话找用户
			if (user != null)
				result = JSONObject.toJSONString("ERROR");
			else
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
	 * 重置密码
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "ajaxRePassword")
	public void ajaxRePassword(HttpServletRequest request, HttpServletResponse response) {
		String result = "";
		try {
			// 校验密码
			String userId = request.getParameter("userId");
			userService.rePassword(userId, Common.eccryptMD5("123456"));
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
	 * 删除用户
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "deleteUser")
	public ModelAndView deleteUser(HttpServletRequest request, HttpServletResponse response) {
		try {
			// 校验密码
			String userId = request.getParameter("deleteUserId");
			userService.deleteUser(userId);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return toUserInfo(request, null);
	}

	/**
	 * 跳转用户报名表页
	 * @param request
	 * @return
	 */
	@RequestMapping(value="toUserApply")
	public ModelAndView toUserApply(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		String applyId = request.getParameter("applyId");
		try {
			// 通过applyId获取报名表
			Apply apply = userService.getApplyById(applyId);
			if(apply != null){
				String undergraduateGraduationTime =  apply.getUndergraduateGraduationTime().toString().substring(0, 10);
				String graduateTime =  apply.getGraduateTime().toString().substring(0, 10);
				String doctoralGraduationTime =  apply.getDoctoralGraduationTime().toString().substring(0, 10);
				request.setAttribute("undergraduateGraduationTime", undergraduateGraduationTime);
				request.setAttribute("graduateTime", graduateTime);
				request.setAttribute("doctoralGraduationTime", doctoralGraduationTime);
				//获取报名人家庭信息
				List<ApplyFamily> familyList = userService.getApplyFamily(apply.getApplyId());
				List<String> birthList = new ArrayList<>();
				for(ApplyFamily f : familyList){
					if(f.getBirth() != null){
						birthList.add(f.getBirth().toString().substring(0, 10));
					}
				}
				apply.setFamilyRelationship(familyList);
				request.setAttribute("apply",apply);
				request.setAttribute("birthList", birthList);
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
		RecruitInfo recruitInfo = new RecruitInfo();
		request.getSession().setAttribute("recruitId", recruitId);
		List<Position> occupationApplicantLsit = new ArrayList<Position>();
		occupationApplicantLsit = userService.getPositionByRecruitId(recruitId);// 该招聘信息对应的所有职位对象
		recruitInfo = userService.getRecruitInfoById(recruitId);
		// 获取单位名
		String organizationName = userService.getOrganizationNameById(recruitInfo.getOrganization());
		mv.addObject("occupationApplicantLsit", occupationApplicantLsit);
		mv.addObject("recruitInfo", recruitInfo);
		mv.addObject("organizationName",organizationName);
		mv.setViewName("/jsp/application_status");
		return mv;
	}

	@RequestMapping(value = "toStatistics")

	public ModelAndView toStatistics(HttpServletRequest request,@RequestParam(value="positonName")String positonName) {

		ModelAndView mv = new ModelAndView();
		String recruitId = (String) request.getSession().getAttribute("recruitId");
		List<Apply> applList = new ArrayList<Apply>();
		Position position = new Position();
		Integer numAll, numAllToday, numDoctor, numDoctorToday, numMaster, numMasterToday, numBachelor,
		numBachelorToday, numInSide, numInSideToday,numFirstSchool,numFirstSchoolToday,numFirstMajor,numFirstMajorToday,
				numFirstSchoolInUndergraduate,numFirstSchoolInUndergraduateToday,numFirstMajorInUndergraduate,numFirstMajorInUndergraduateToday,
				numFirstSchoolInPastgraduate,numFirstSchoolInPastgraduateToday,numFirstMajorInPastgraduate,numFirstMajorInPastgraduateToday,
				numFirstSchoolInDoctor,numFirstSchoolInDoctorToday,numFirstMajorInDoctor,numFirstMajorInDoctorToday;

		// 获取今日时间
		Date date = new Date();// 取时间
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.DATE, 0);// 把日期往后增加一天.整数往后推,负数往前移动
		date = calendar.getTime(); // 这个时间就是日期往后推一天的结果
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(date);
		
		position = userService.getPositionByPositionNameAndRecruitId(recruitId, positonName);
		
		numAllToday = userService.applyNumToday(recruitId, dateString, positonName); // 今天报名人数
		numAll = userService.applyNum(recruitId, positonName);// 总报名人数
		numBachelor = userService.applyNumBachelor(recruitId, positonName);// 总学士人数
		numBachelorToday = userService.applyNumBachelorToday(recruitId, dateString, positonName);// 今日总学士人数
		numMaster = userService.applyNumMaster(recruitId, positonName);// 总硕士人数
		numMasterToday = userService.applyNumMasterToday(recruitId, dateString, positonName);// 今日总硕士人数
		numDoctor = userService.applyNumDoctor(recruitId, positonName);// 总博士人数
		numDoctorToday = userService.applyNumDoctorToday(recruitId, dateString, positonName);// 今日总博士人数
		numInSide = userService.applyNumInSide(recruitId, positonName);// 疆内人数
		numInSideToday = userService.applyNumInSideToday(recruitId, dateString, positonName);// 今日疆内人数
		numFirstSchoolInUndergraduate = userService.applyNumFirstSchoolInUndergraduate(recruitId, positonName);
		numFirstSchoolInUndergraduateToday = userService.applyNumFirstSchoolInUndergraduateToday(recruitId, dateString, positonName);
		numFirstMajorInUndergraduate = userService.applyNumFirstMajorInUndergraduate(recruitId, positonName);
		numFirstMajorInUndergraduateToday = userService.applyNumFirstMajorInUndergraduateToday(recruitId, dateString, positonName);
		numFirstSchoolInPastgraduate = userService.applyNumFirstSchoolInPastgraduate(recruitId, positonName);
		numFirstSchoolInPastgraduateToday = userService.applyNumFirstSchoolInPastgraduateToday(recruitId, dateString, positonName);
		numFirstMajorInPastgraduate = userService.applyNumFirstMajorInPastgraduate(recruitId, positonName);
		numFirstMajorInPastgraduateToday = userService.applyNumFirstMajorInPastgraduateToday(recruitId, dateString, positonName);
		numFirstSchoolInDoctor = userService.applyNumFirstSchoolInDoctor(recruitId, positonName);
		numFirstSchoolInDoctorToday = userService.applyNumFirstSchoolInDoctorToday(recruitId, dateString, positonName);
		numFirstMajorInDoctor  = userService.applyNumFirstMajorInDoctor(recruitId, positonName);
		numFirstMajorInDoctorToday = userService.applyNumFirstMajorInDoctorToday(recruitId, dateString, positonName);
		applList = userService.applyList(recruitId, positonName);// 报名表
		System.out.println("recruitId = " + recruitId);
		for (Apply apply : applList) {
			System.out.println("Id=" + apply.getApplyId());
			System.out.println("applyUserName=" + apply.getApplyUserName());
		}
		
		mv.addObject("applList", applList);
		mv.addObject("numInSideToday", numInSideToday);
		mv.addObject("numInSide", numInSide);
		mv.addObject("numDoctorToday", numDoctorToday);
		mv.addObject("numMaster", numMaster);
		mv.addObject("numMasterToday", numMasterToday);
		mv.addObject("numBachelorToday", numBachelorToday);
		mv.addObject("numBachelor", numBachelor);
		mv.addObject("numAll", numAll);
		mv.addObject("numAllToday", numAllToday);
		mv.addObject("numFirstSchoolInUndergraduate",numFirstSchoolInUndergraduate );
		mv.addObject("numFirstSchoolInUndergraduateToday",numFirstSchoolInUndergraduateToday );
		mv.addObject("numFirstMajorInUndergraduate",numFirstMajorInUndergraduate );
		mv.addObject("numFirstMajorInUndergraduateToday",numFirstMajorInUndergraduateToday );
		mv.addObject("numFirstSchoolInPastgraduate",numFirstSchoolInPastgraduate );
		mv.addObject("numFirstSchoolInPastgraduateToday",numFirstSchoolInPastgraduateToday );
		mv.addObject("numFirstMajorInPastgraduate",numFirstMajorInPastgraduate );
		mv.addObject("numFirstMajorInPastgraduateToday",numFirstMajorInPastgraduateToday );
		mv.addObject("numFirstSchoolInDoctor",numFirstSchoolInDoctor );
		mv.addObject("numFirstSchoolInDoctorToday",numFirstSchoolInDoctorToday );
		mv.addObject("numFirstMajorInDoctor",numFirstMajorInDoctor );
		mv.addObject("numFirstMajorInDoctorToday",numFirstMajorInDoctorToday );
		mv.addObject("position", position);
		
		mv.setViewName("/jsp/signIn_iframe");
		return mv;

	}

	/**

	 * 导出报表
	 * @return
	 */
	@RequestMapping(value = "/export")
	@ResponseBody
	public void export(HttpServletRequest request,HttpServletResponse response) throws Exception {
		//获取数据
		String recruitId = (String) request.getSession().getAttribute("recruitId");
		List<Apply> list =userService.applyListAll(recruitId);
		RecruitInfo recruitInfo = userService.getRecruitInfoById(recruitId);
		//excel标题
		String[] title = {"报考单位","报名职位","专业及专业方向",
				"编制性质","姓名","性别",
				"民族","籍贯","政治面貌",
				"身份证号","婚否","学历",
				"学位","应历届","是否双一流大学",
				"是否双一流专业","本科就读院校及专业","本科毕业时间",
				"研究生就读院校及专业","研究生毕业时间","博士就读院校及专业",
				"博士毕业时间","原工作单位","原工作单位职务","专业技术资格",
				"执业资格","联系电话","邮箱","通讯地址","邮编","备注"};

		//excel文件名
		String fileName = recruitInfo.getRecruitInfo()+".xls";
		System.out.println("文件名称"+fileName);
		//sheet名
		String sheetName = recruitInfo.getRecruitInfo();

		String [][] content  = new String[list.size()][];
		for (int i = 0; i < list.size(); i++) {
			content[i] = new String[title.length];
			Apply obj = list.get(i);
			content[i][0] = obj.getApplicationOrganization();//报考单位
			content[i][1] = obj.getOccupationApplicant();//报名职业
			content[i][2] = obj.getProfessionalOrientation();//专业及方向
			content[i][3] = obj.getCompilationNature();//编制性质
			content[i][4] = obj.getApplyUserName();//姓名
			content[i][5] = obj.getGender();//性别
			content[i][6] = obj.getNation();//民族
			content[i][7] = obj.getNativePlace();//籍贯
			content[i][8] = obj.getPoliticsStatus();//政治面貌
			content[i][9] = obj.getIdentityNum();//身份证号
			content[i][10] = obj.getIsMarry();//婚否
			content[i][11] = obj.getEducation();//学历
			content[i][12] = obj.getDegree();//学位

			//是否应届
			if(obj.getIsCurrent()!=null&&obj.getIsCurrent()==1) {
				content[i][13] = "是";
			}else {
				content[i][13] = "否";

			}

			content[i][14] = "";//是否双一流大学
			if(userService.isFirstSchool(obj.getApplyId())) {
				content[i][14] = "是";
			}
			content[i][15] = "";//是否双一流学科
			if(userService.isFirstMajor(obj.getApplyId())) {
				content[i][15] = "是";
			}

			content[i][16] = obj.getBachelorDegreeAndMajor();//本科就读专业
			content[i][17] = obj.getUndergraduateGraduationTime();//本科毕业时间
			content[i][18] = obj.getGraduateSchoolAndMajor();//研究生就读学院
			content[i][19] = obj.getGraduateTime();//研究生毕业时间
			content[i][20] = obj.getDoctoralDegreeAndMajor();//博士就读学院
			content[i][21] = obj.getDoctoralGraduationTime();//博士毕业时间

			content[i][22] = obj.getWorkOrganization();//原工作单位
			content[i][23] = obj.getPosition();//原工作单位职务
			content[i][24] = obj.getProfessionalAndTechnicalQualification();//专业技术资格
			content[i][25] = obj.getPracticingRequirements();//执业资格
			content[i][26] = obj.getTelephone();//联系方式
			content[i][27] = obj.geteMail();//邮箱
			content[i][28] = obj.getMailingAddress();//通讯地址
			content[i][29] = obj.getPostalAddress();//邮编
			content[i][30] = null;
		}

		//创建HSSFWorkbook 
		HSSFWorkbook wb = ExcelExportUtil.getHSSFWorkbook(sheetName, title, content, null);

		//响应到客户端
		try {
			this.setResponseHeader(response, fileName);
			OutputStream os = response.getOutputStream();
			wb.write(os);
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
   
	// 发送响应流方法

	public void setResponseHeader(HttpServletResponse response, String fileName) {
		try {
			try {
				fileName = new String(fileName.getBytes(),"ISO8859-1");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.setContentType("application/octet-stream;charset=ISO8859-1");
			response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
			response.addHeader("Pargam", "no-cache");
			response.addHeader("Cache-Control", "no-cache");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}


	/**
	 * @author liming
	 * @param request
	 * @return
	 * @throws IOException
	 * spring方式下载，当文件较小且下载复杂度不是很大时使用效率较高
	 */
	@RequestMapping("/resourceDownload")
	public ResponseEntity<byte[]> download(HttpServletRequest request,@RequestParam(value="filePath")String filePath) throws IOException {
		ResponseEntity<byte[]> entity;
		File file = new File(filePath);
		byte[] body = null;
		InputStream is = new FileInputStream(file);//将该文件加入到输入流之中
		body = new byte[is.available()];// 返回下一次对此输入流调用的方法可以不受阻塞地从此输入流读取（或跳过）的估计剩余字节数
		is.read(body);//读入到输入流里面
		HttpHeaders headers = new HttpHeaders();//设置响应头
		headers.add("Content-Disposition", "attchement;filename=" + file.getName());
		HttpStatus statusCode = HttpStatus.OK;//设置响应码
		entity = new ResponseEntity<byte[]>(body, headers, statusCode);
		return entity;
	}

}
