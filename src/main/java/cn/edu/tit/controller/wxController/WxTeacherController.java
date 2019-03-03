package cn.edu.tit.controller.wxController;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
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
import cn.edu.tit.bean.Material;
import cn.edu.tit.bean.Position;
import cn.edu.tit.bean.RecruitInfo;
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
				
				if (user != null) {
					if (password != user.getPassword()) {
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
			 userId = "0170DED2D11248F6BD0619A3C850A56A";
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
	
	/**
	 * 查询招聘信息
	 * @param request
	 * @param search
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="searchRecruit",method= {RequestMethod.GET})
	public Map<String, Object> searchRecruit(HttpServletRequest request,@RequestParam(value="search") String search) throws Exception {
		Map<String, Object> ret = new HashMap<String, Object>();
		List<RecruitInfo> list = new ArrayList<RecruitInfo>();
		try {
			//获取招聘信息
			list = userService.searchRecruit(search);
			for(RecruitInfo re : list){ // 获取每次招聘的相关职位信息
				List<Position> positionList = new ArrayList<>();
				positionList = userService.getPositionByRecruitId(re.getRecruitId());
				re.setPosition(positionList);
			}
			ret.put("list",list);
			ret.put("status", "OK");
		} catch (Exception e) {
			e.printStackTrace();
			ret.put("status", "ERROR");
		}
		return ret;
	}
	
	/**
	 * 发布招聘信息
	 * */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="publishRcruit")
	public Map<String, Object> publishRcruit(HttpServletRequest request) throws Exception {
		Map<String, Object> ret = new HashMap<String, Object>();
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
			ret.put("status", "OK");
		} catch (Exception e) {
			e.printStackTrace();
			ret.put("status", "ERROR");
		}
		return ret;
	}
	
	/**
	 * 提交报名申请
	 * */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="submitApplyAndAcc")
	public Map<String, Object> submitApply(HttpServletRequest request) throws Exception {
		Map<String, Object> ret = new HashMap<String, Object>();
		try {
			String applyId = Common.uuid();
			Object[] obj = Common.fileFactory(request,applyId);
			Map<String, Object> formdata = (Map<String, Object>) obj[1];
			List<File> returnFileList = (List<File>) obj[0]; // 要返回的文件集合
//			Apply apply = new Apply(applyId, (String) (String)formdata.get("applyUserName"), (String)formdata.get("gender"), (String)formdata.get("nation"), (String)formdata.get("politicsStatus"),
//					(String)formdata.get("nativePlace"), (String)formdata.get("identityNum"), (String)formdata.get("isMarry"), 
//					(String)formdata.get("speciality"), (String)formdata.get("telephone"), (String)formdata.get("bachelorDegreeAndMajor"), (String)formdata.get("undergraduateGraduationTime"), 
//					(String)formdata.get("graduateSchoolAndMajor"), (String)formdata.get("graduateTime"), 
//					(String)formdata.get("doctoralDegreeAndMajor"), (String)formdata.get("doctoralGraduationTime"), (String)formdata.get("workOrganization"), 
//					(String)formdata.get("position"), (String)formdata.get("telephoneOriganization"), 
//					(String)formdata.get("professionalAndTechnicalQualification"), (String)formdata.get("practicingRequirements"), (String)formdata.get("mailingAddress"), 
//					(String)formdata.get("postalAddress"), (String)formdata.get("eMail"), (String)formdata.get("applicationOrganization"), 
//					(String)formdata.get("majorApplicant"), (String)formdata.get("workExperience"), (String)formdata.get("occupationApplicant"), (String)formdata.get("recruitId"), 
//					(Date)formdata.get("submitTime"), (Integer)formdata.get("undergraduateIsFirstSchool"), (Integer)formdata.get("undergraduateIsFirstMajor"), 
//					(Integer)formdata.get("graduateIsFirstSchool"), (Integer)formdata.get("graduateIsFirstMajor"), (Integer)formdata.get("doctorIsFirstSchool"), 
//					(Integer)formdata.get("doctorIsFirstMajor"), (String)formdata.get("professionalOrientation"), (String)formdata.get("compilationNature"));
			//apply.setSubmitTime(new Date());
			//存储获取的报名信息（文本信息）
			//userService.submitApply(apply);
			if(!returnFileList.isEmpty())
			{
				for(File f : returnFileList){
					//存储报名材料(附件)
					Material material = new Material();
					//material.setApplyId(apply.getApplyId());
					material.setMaterialId(Common.uuid());
					material.setAccessory(f.getPath());
					userService.saveMaterial(material);
				}
			}
			ret.put("status", "OK");
		} catch (Exception e) {
			e.printStackTrace();
			ret.put("status", "ERROR");
		}
		return ret;
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
