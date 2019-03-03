package cn.edu.tit.controller;

import java.io.File;
import java.io.IOException;
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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
		User publisher = (User) request.getSession().getAttribute("User");
		String publisherId = publisher.getUserId();
		try {
			// 获取招聘信息
			list = userService.getRecruitInfo(publisherId);
			mv.addObject("list", list);
			mv.setViewName("/jsp/mainJsp");// 设置返回页面
		} catch (Exception e) {
			e.printStackTrace();
			mv = null;
		}
		return mv;
	}

	/**
	 * 查询招聘信息
	 * 
	 * @param request
	 * @param search
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "searchRecruit", method = { RequestMethod.GET })
	public ModelAndView searchRecruit(HttpServletRequest request, @RequestParam(value = "search") String search)
			throws Exception {
		ModelAndView mv = new ModelAndView();
		List<Position> list = new ArrayList<Position>();
		User publisher = (User) request.getSession().getAttribute("User");
		String organizationId = publisher.getOrganizationId();
		try {
			list = userService.getPosition(organizationId);
			if (list != null) {
				mv.addObject("list", list);
			}
			mv.setViewName("/jsp/publishRecruit");// 设置返回页面
		} catch (Exception e) {
			e.printStackTrace();
			mv = null;
		}
		return mv;
	}

	@RequestMapping(value = "userLogin", method = { RequestMethod.GET })
	public ModelAndView userLogin(HttpServletRequest request, @RequestParam(value = "employeeNum") String employeeNum,
			@RequestParam(value = "password") String password) throws Exception {
		ModelAndView mv = new ModelAndView();
		User user = new User();
		try {
			user = userService.getUserById(employeeNum);
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

	/**
	 * 发布招聘信息 返回页面调主页面(toMainPage)
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "publishRcruit", method = { RequestMethod.POST })
	public ModelAndView publishRcruit(HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		try {
			String recruitId = Common.uuid();
			Object[] obj = Common.fileFactory(request, recruitId);
			Map<String, Object> formdata = (Map<String, Object>) obj[1];
			List<File> returnFileList = (List<File>) obj[0]; // 要返回的文件集合
			RecruitInfo recruit = new RecruitInfo();
			recruit.setOrganization((String) formdata.get("organization"));
			User publisher = (User) request.getSession().getAttribute("User");
			String publisherId = publisher.getUserId();
			recruit.setPublisher(publisherId);
			recruit.setEndTime(timeConverter((String) formdata.get("endTime")));
			recruit.setPublishTime(new Timestamp(System.currentTimeMillis()));
			recruit.setRecruitId(recruitId);
			recruit.setRecruitInfo((String) formdata.get("recruitInfo"));
			recruit.setStartTime(timeConverter((String) formdata.get("startTime")));
			if (!returnFileList.isEmpty()) {
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
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "addUser")
	public ModelAndView addUser(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		try {
			User user = new User();
			user.setUserId(Common.uuid());
			user.setPassword("123456");
			user.setOrganizationName(request.getParameter("organization"));
			user.setUserName(request.getParameter("userName"));
			user.setWechartNum(request.getParameter("weChat"));
			user.setOrganizationId(Common.uuid());
			if ("on".equals(request.getParameter("authority")))
				user.setAuthority(1);
			else
				user.setAuthority(0);
			userService.addUser(user);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return toUserInfo(request);
	}

	/**
	 * 跳转到用户列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "toUserInfo")
	public ModelAndView toUserInfo(HttpServletRequest request) {
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
	 * 跳转到用户列表
	 * 
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
			userId = (String) request.getSession().getAttribute("userId");
			password = request.getParameter("newPassword");
			if (!"".equals(userId) || !"".equals(password)) {
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
			user.setUserId((request.getParameter("userId")));
			user.setOrganizationName(request.getParameter("organizationName"));
			user.setUserName(request.getParameter("userName"));
			if (!"".equals(user.getUserId())) {
				userService.modifyuser(user);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return toUserInfo(request);
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
			// String userId = (String) request.getSession().getAttribute("userId");
			String userId = "1";
			String password = request.getParameter("password");
			String result = "";
			Boolean isRight = userService.checkPassword(userId, password);
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
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "readApply")
	public ModelAndView readApply(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		String applyId = request.getParameter("applyId");
		try {
			// 通过applyId获取报名表
			Apply apply = userService.getApplyById("1");
			if (apply != null) {
				request.setAttribute("apply", apply);
			}
			mv.setViewName("/jsp/userApplySituation");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return mv;
	}

	/**
	 * @author Liming
	 * @param 前台获取的时间格式 返回 Timestamp 格式时间
	 */
	@SuppressWarnings("unused")
	private Timestamp timeConverter(String time) throws ParseException {
		SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.ENGLISH);// 输入的被转化的时间格式
		SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 需要转化成的时间格式
		java.util.Date date1 = dff.parse(time);
		String str1 = df1.format(date1);
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		try {
			ts = Timestamp.valueOf(str1);
			System.out.println("获取的时间为----->" + ts);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ts;
	}

	@RequestMapping(value = "toSignInInfo")
	public ModelAndView toSignInInfo(HttpServletRequest request, @RequestParam(value = "recruitId") String recruitId) {
		ModelAndView mv = new ModelAndView();
		RecruitInfo recruitInfo = new RecruitInfo();
		request.getSession().setAttribute("recruitId", recruitId);
		List<Position> occupationApplicantLsit = new ArrayList<Position>();
		occupationApplicantLsit = userService.getPosition(recruitId);// 该招聘信息对应的所有职位对象
		recruitInfo = userService.getRecruitInfoById(recruitId);
		mv.addObject("occupationApplicantLsit", occupationApplicantLsit);
		mv.addObject("recruitInfo", recruitInfo);
		mv.setViewName("/jsp/application_status");
		return mv;

	}

	@RequestMapping(value = "toStatistics")
	public ModelAndView toStatistics(HttpServletRequest request,
			@RequestParam(value = "positonName") String positonName) {

		ModelAndView mv = new ModelAndView();
		String recruitId = (String) request.getSession().getAttribute("recruitId");
		List<Apply> applList = new ArrayList<Apply>();
		List<Position> occupationApplicantLsit = new ArrayList<Position>();
		Integer numAll, numAllToday, numDoctor, numDoctorToday, numMaster, numMasterToday, numBachelor,
				numBachelorToday, numInSide, numInSideToday;
		// 获取今日时间
		Date date = new Date();// 取时间
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.DATE, 0);// 把日期往后增加一天.整数往后推,负数往前移动
		date = calendar.getTime(); // 这个时间就是日期往后推一天的结果
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(date);

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

}
