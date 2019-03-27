package cn.edu.tit.controller.wxController;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.URL;
import java.net.URLEncoder;
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

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

import cn.edu.tit.bean.Apply;
import cn.edu.tit.bean.Material;
import cn.edu.tit.bean.Organization;
import cn.edu.tit.bean.Position;
import cn.edu.tit.bean.RecruitInfo;
import cn.edu.tit.bean.TemplateData;
import cn.edu.tit.bean.User;
import cn.edu.tit.bean.WxMssVo;
import cn.edu.tit.common.Common;
import cn.edu.tit.iservice.IUserService;
import net.sf.json.JSONArray;

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
	
    private RestTemplate restTemplate = new RestTemplate();
	
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
				password = Common.eccryptMD5(password);
				User user = userService.getUser(userId, password);
				
				if (user != null) {
					ret.put("user", user);
					return ret;
				} 
				else {
					ret.put("status", "ERROR");
					ret.put("msg", "用户名或密码错误");
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
			User user = (User) request.getSession().getAttribute("User");
			userId = user.getUserId();
			 password = request.getParameter("newPassword");
			if(!"".equals(userId) || !"".equals(password)){
				password = Common.eccryptMD5(password);
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
			Apply apply = userService.getApplyById(applyId);
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
	public Map<String, Object> searchRecruit(HttpServletRequest request) throws Exception {
		Map<String, Object> ret = new HashMap<String, Object>();
		Integer index = Integer.parseInt(request.getParameter("index"));
		String openId = request.getParameter("openId");
		List<RecruitInfo> list = new ArrayList<RecruitInfo>();
		List<RecruitInfo> list2 = new ArrayList<RecruitInfo>();
		List<String> recruitList = new ArrayList<>();
		try {
			//获取招聘信息
			User publisher = (User) request.getSession().getAttribute("User");
				list = userService.getAllRecruitInfoBypage(index); // 获取所有招聘信息
				//如果是报名人登录给返回所报recruitId
				if(openId!=null && !"".equals(openId)){
					recruitList = userService.getApplyRecruitId(openId);
				}
				//获取当前时间
				Date date = new Date();       
				Timestamp nousedate = new Timestamp(date.getTime());
			for(RecruitInfo re : list){ // 获取每次招聘的相关职位信息
				if(nousedate.before(re.getEndTime())){ //筛选未结束的
					List<Position> positionList = new ArrayList<>();
					positionList = userService.getPositionByRecruitId(re.getRecruitId());
					re.setPosition(positionList);
					list2.add(re);
				}
			}
			ret.put("list",list2);
			ret.put("recruitList", recruitList);
			ret.put("status", "OK");
			ret.put("user", publisher);
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
			ret.put("status", "OK");
		} catch (Exception e) {
			e.printStackTrace();
			ret.put("status", "ERROR");
		}
		return ret;
	}
	
	/**
	 * 获取所有招聘公司
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="getApplyOrganization")
	public  Map<String, Object> getApplyOrganization(HttpServletRequest request) throws Exception {
		Map<String, Object> ret = new HashMap<String, Object>();
		try {
			// 提供所有公司
			List<Organization> organizationList = new ArrayList<>();
			organizationList = userService.getOrganization();
			ret.put("organization", organizationList);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ret;
	}
	
	/**
	 * 获取某公司所有岗位
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="getApplyposition")
	public  Map<String, Object> getApplyposition(HttpServletRequest request) throws Exception {
		Map<String, Object> ret = new HashMap<String, Object>();
		String organizationName = request.getParameter("organizationName");  // 获取公司名
		try {
			String organizationId = userService.getOrganizaionIdByName(organizationName);
			List<Position> positionList = userService.getPosition(organizationId); // 获取公司所有职位
			ret.put("position", positionList);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
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
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			String applyId = Common.uuid();
			Object[] obj = Common.fileFactory(request,applyId);
			Map<String, Object> formdata = (Map<String, Object>) obj[1];
			List<File> returnFileList = (List<File>) obj[0]; // 要返回的文件集合 
			Apply apply = new Apply(applyId,(String)formdata.get("gender"), (String)formdata.get("applyUserName"),  (String)formdata.get("nation"), (String)formdata.get("politicsStatus"),
					(String)formdata.get("nativePlace"), (String)formdata.get("identityNum"), (String)formdata.get("isMarry"), 
					(String)formdata.get("speciality"), (String)formdata.get("telephone"), (String)formdata.get("bachelorDegreeAndMajor"), (String)formdata.get("undergraduateGraduationTime"), 
					Integer.parseInt((String)formdata.get("undergraduateIsFirstSchool")),Integer.parseInt((String)formdata.get("undergraduateIsFirstMajor")),(String)formdata.get("graduateSchoolAndMajor"), (String)formdata.get("graduateTime"), 
					Integer.parseInt((String)formdata.get("graduateIsFirstSchool")),Integer.parseInt((String)formdata.get("graduateIsFirstMajor")),(String)formdata.get("doctoralDegreeAndMajor"), (String)formdata.get("doctoralGraduationTime"), 
					Integer.parseInt((String)formdata.get("doctorIsFirstSchool")),Integer.parseInt((String)formdata.get("doctorIsFirstMajor")),(String)formdata.get("workOrganization"), 
					(String)formdata.get("position"), (String)formdata.get("telephoneOriganization"), 
					(String)formdata.get("professionalAndTechnicalQualification"), (String)formdata.get("practicingRequirements"), (String)formdata.get("mailingAddress"), 
					(String)formdata.get("postalAddress"), (String)formdata.get("eMail"), (String)formdata.get("applicationOrganization"), 
					(String)formdata.get("majorApplicant"), (String)formdata.get("workExperience"), (String)formdata.get("occupationApplicant"), (String)formdata.get("recruitId"), 
					(String)formdata.get("professionalOrientation"), (String)formdata.get("compilationNature"),
					 (String)formdata.get("education"), (String)formdata.get("degree"), Integer.parseInt((String)formdata.get("isCurrent")), (String)formdata.get("jobPerformance"),
						(String)formdata.get("paperTopicSituation"), (String)formdata.get("sanctionSituation"),(String)formdata.get("openId"),(String)formdata.get("formId"),(String)formdata.get("familyRelationship"));
			apply.setSubmitTime(new Date());
			if(!returnFileList.isEmpty())
			{
				for(File f : returnFileList){
					String path = Common.readProperties("prefix")+f.getPath().substring(2).replace("\\", "/");;
					
					apply.setFace(path);
//					//存储报名材料(附件)
//					Material material = new Material();
//					//material.setApplyId(apply.getApplyId());
//					material.setMaterialId(Common.uuid());
//					material.setAccessory(f.getPath());
//					userService.saveMaterial(material);
				}
			}
			//存储获取的报名信息
			userService.submitApply(apply);
			ret.put("status", "OK");
		} catch (Exception e) {
			e.printStackTrace();
			ret.put("status", "ERROR");
		}
		return ret;
	}
	
	/**
	 * 提交报名材料
	 * */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="submitMaterial")
	public Map<String, Object> submitMaterial(HttpServletRequest request) throws Exception {
		Map<String, Object> ret = new HashMap<String, Object>();
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Object[] obj = Common.fileFactory(request,null);
			Map<String, Object> formdata = (Map<String, Object>) obj[1];
			List<File> returnFileList = (List<File>) obj[0]; // 要返回的文件集合 
			if(!returnFileList.isEmpty())
			{
				for(File f : returnFileList){
					String path = Common.readProperties("prefix")+f.getPath().substring(2).replace("\\", "/");;
					//存储报名材料(附件)
					Material material = new Material();
					material.setRecruitId((String)formdata.get("recruitId"));
					material.setOpenId((String)formdata.get("openId"));
					material.setMaterialId(Common.uuid());
					material.setAccessory(path);
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
	
	/**
	 * 提交报名材料
	 * */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="getMaterial")
	public Map<String, Object> getMaterial(HttpServletRequest request) throws Exception {
		Map<String, Object> ret = new HashMap<String, Object>();
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			String recruitId = request.getParameter("recruitId");
			String openId = request.getParameter("openId");
			List<Material> marerialList = userService.getMaterial(recruitId, openId);// 查材料
			ret.put("marerialList", marerialList);
			ret.put("status", "OK");
		} catch (Exception e) {
			e.printStackTrace();
			ret.put("status", "ERROR");
		}
		return ret;
	}
	
	/**
	 * 获得报名申请
	 * */
	@RequestMapping(value="getApply")
	public Map<String, Object> getApply(HttpServletRequest request) throws Exception {
		Map<String, Object> ret = new HashMap<String, Object>();
		try {
			// 获取openId 和 recruitId
			String openId = request.getParameter("openId");
			String recruitId = request.getParameter("recruitId");
			//根据两个值找apply
			Apply apply = userService.getApplyByOpenAndRecruit(openId, recruitId);
			ret.put("apply",apply);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ret;
	}
	
	/**
	 * 修改报名申请
	 * */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="modifyApply")
	public Map<String, Object> modifyApply(HttpServletRequest request) throws Exception {
		Map<String, Object> ret = new HashMap<String, Object>();
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Object[] obj = Common.fileFactory(request,null);
			Map<String, Object> formdata = (Map<String, Object>) obj[1];
			List<File> returnFileList = (List<File>) obj[0]; // 要返回的文件集合 
			Apply apply = new Apply((String)formdata.get("applyId"),(String)formdata.get("gender"), (String)formdata.get("applyUserName"),  (String)formdata.get("nation"), (String)formdata.get("politicsStatus"),
					(String)formdata.get("nativePlace"), (String)formdata.get("identityNum"), (String)formdata.get("isMarry"), 
					(String)formdata.get("speciality"), (String)formdata.get("telephone"), (String)formdata.get("bachelorDegreeAndMajor"), (String)formdata.get("undergraduateGraduationTime"), 
					Integer.parseInt((String)formdata.get("undergraduateIsFirstSchool")),Integer.parseInt((String)formdata.get("undergraduateIsFirstMajor")),(String)formdata.get("graduateSchoolAndMajor"), (String)formdata.get("graduateTime"), 
					Integer.parseInt((String)formdata.get("graduateIsFirstSchool")),Integer.parseInt((String)formdata.get("graduateIsFirstMajor")),(String)formdata.get("doctoralDegreeAndMajor"), (String)formdata.get("doctoralGraduationTime"), 
					Integer.parseInt((String)formdata.get("doctorIsFirstSchool")),Integer.parseInt((String)formdata.get("doctorIsFirstMajor")),(String)formdata.get("workOrganization"), 
					(String)formdata.get("position"), (String)formdata.get("telephoneOriganization"), 
					(String)formdata.get("professionalAndTechnicalQualification"), (String)formdata.get("practicingRequirements"), (String)formdata.get("mailingAddress"), 
					(String)formdata.get("postalAddress"), (String)formdata.get("eMail"), (String)formdata.get("applicationOrganization"), 
					(String)formdata.get("majorApplicant"), (String)formdata.get("workExperience"), (String)formdata.get("occupationApplicant"), (String)formdata.get("recruitId"), 
					(String)formdata.get("professionalOrientation"), (String)formdata.get("compilationNature"),
					 (String)formdata.get("education"), (String)formdata.get("degree"), Integer.parseInt((String)formdata.get("isCurrent")), (String)formdata.get("jobPerformance"),
						(String)formdata.get("paperTopicSituation"), (String)formdata.get("sanctionSituation"),(String)formdata.get("openId"),(String)formdata.get("formId"),(String)formdata.get("familyRelationship"));
			apply.setSubmitTime(new Date());
			if(!returnFileList.isEmpty())
			{
				for(File f : returnFileList){
					String path = Common.readProperties("prefix")+f.getPath().substring(2).replace("\\", "/");
					apply.setFace(path);
//					//存储报名材料(附件)
//					Material material = new Material();
//					//material.setApplyId(apply.getApplyId());
//					material.setMaterialId(Common.uuid());
//					material.setAccessory(f.getPath());
//					userService.saveMaterial(material);
				}
			}
			//存储获取的报名信息
			userService.modifyApply(apply);
			ret.put("status", "OK");
		} catch (Exception e) {
			e.printStackTrace();
			ret.put("status", "ERROR");
		}
		return ret;
	}
	
	
	/**
	 * 职位信息汇总
	 * @param request
	 * @param positonName
	 * @return
	 */
	@RequestMapping(value = "toStatistics")
	public Map<String, Object> toStatistics(HttpServletRequest request) {
		Map<String, Object> ret = new HashMap<String, Object>();
		try {
			request.setCharacterEncoding("utf-8");
			String positonName = request.getParameter("positionName");
			String recruitId = request.getParameter("recruitId");
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
			numFirstSchoolInUndergraduate = userService.applyNumFirstSchoolInUndergraduate(recruitId, positonName); //本科双一流总数
			numFirstSchoolInUndergraduateToday = userService.applyNumFirstSchoolInUndergraduateToday(recruitId, dateString, positonName); // 本科今日双一流数
			numFirstMajorInUndergraduate = userService.applyNumFirstMajorInUndergraduate(recruitId, positonName); //本科双一流专业总数
			numFirstMajorInUndergraduateToday = userService.applyNumFirstMajorInUndergraduateToday(recruitId, dateString, positonName); //本科今日双一流专业
			numFirstSchoolInPastgraduate = userService.applyNumFirstSchoolInPastgraduate(recruitId, positonName); //研究生双一流总数
			numFirstSchoolInPastgraduateToday = userService.applyNumFirstSchoolInPastgraduateToday(recruitId, dateString, positonName); //研究生今日双一流数
			numFirstMajorInPastgraduate = userService.applyNumFirstMajorInPastgraduate(recruitId, positonName);  //研究生双一流专业总数
			numFirstMajorInPastgraduateToday = userService.applyNumFirstMajorInPastgraduateToday(recruitId, dateString, positonName); //研究生今日双一流专业
			numFirstSchoolInDoctor = userService.applyNumFirstSchoolInDoctor(recruitId, positonName);  //博士生双一流总数
			numFirstSchoolInDoctorToday = userService.applyNumFirstSchoolInDoctorToday(recruitId, dateString, positonName); // 博士今日双一流数
			numFirstMajorInDoctor  = userService.applyNumFirstMajorInDoctor(recruitId, positonName); //博士生双一流专业总数
			numFirstMajorInDoctorToday = userService.applyNumFirstMajorInDoctorToday(recruitId, dateString, positonName); //博士生今日双一流专业
			applList = userService.applyList(recruitId, positonName);// 报名表
			System.out.println("recruitId = " + recruitId);
			for (Apply apply : applList) {
				System.out.println("Id=" + apply.getApplyId());
				System.out.println("applyUserName=" + apply.getApplyUserName());
			}
			
			ret.put("applList", applList);
			ret.put("numInSideToday", numInSideToday);
			ret.put("numInSide", numInSide);
			ret.put("numDoctorToday", numDoctorToday);
			ret.put("numMaster", numMaster);
			ret.put("numMasterToday", numMasterToday);
			ret.put("numBachelorToday", numBachelorToday);
			ret.put("numBachelor", numBachelor);
			ret.put("numAll", numAll);
			ret.put("numAllToday", numAllToday);
			ret.put("numFirstSchoolInUndergraduate",numFirstSchoolInUndergraduate );
			ret.put("numFirstSchoolInUndergraduateToday",numFirstSchoolInUndergraduateToday );
			ret.put("numFirstMajorInUndergraduate",numFirstMajorInUndergraduate );
			ret.put("numFirstMajorInUndergraduateToday",numFirstMajorInUndergraduateToday );
			ret.put("numFirstSchoolInPastgraduate",numFirstSchoolInPastgraduate );
			ret.put("numFirstSchoolInPastgraduateToday",numFirstSchoolInPastgraduateToday );
			ret.put("numFirstMajorInPastgraduate",numFirstMajorInPastgraduate );
			ret.put("numFirstMajorInPastgraduateToday",numFirstMajorInPastgraduateToday );
			ret.put("numFirstSchoolInDoctor",numFirstSchoolInDoctor );
			ret.put("numFirstSchoolInDoctorToday",numFirstSchoolInDoctorToday );
			ret.put("numFirstMajorInDoctor",numFirstMajorInDoctor );
			ret.put("numFirstMajorInDoctorToday",numFirstMajorInDoctorToday );
			ret.put("position", position);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return ret;

	}
	
	/**
	 * 获取openid
	 * @param code
	 * @param APPID
	 * @param APPSecret
	 * @return
	 */
	@RequestMapping(value="getWxUserOpenid")
	public  Map<String, Object> getWxUserOpenid(HttpServletRequest request,@RequestParam(value="js_code")String js_code, @RequestParam(value="appid")String appid, 
			@RequestParam(value="secret")String secret) {
		Map<String, Object> map = new HashMap<String, Object>();
		//如果是工作人员，则会传手机号，如果数据库里找不到，不给登录，并提示
		String phone = "";
		phone = request.getParameter("phone");
		User user = userService.getUserByPhone(phone);
	    //拼接url
		StringBuilder url = new StringBuilder("https://api.weixin.qq.com/sns/jscode2session?");
		url.append("appid=");//appid设置
		url.append(appid);
		url.append("&secret=");//secret设置
		url.append(secret);
		url.append("&js_code=");//code设置
		url.append(js_code);
		url.append("&grant_type=authorization_code");
		try {
	        HttpClient client =HttpClientBuilder.create().build();//构建一个Client
	        HttpGet get = new HttpGet(url.toString());    //构建一个GET请求
	        HttpResponse response = client.execute(get);//提交GET请求
	        HttpEntity result = response.getEntity();//拿到返回的HttpResponse的"实体"
	        String content = EntityUtils.toString(result);   
	        System.out.println(content);//打印返回的信息
	        net.sf.json.JSONObject res = net.sf.json.JSONObject.fromObject(content);//把信息封装为json
		    //把信息封装到map
		    map = Common.parseJSON2Map(res);//这个小工具的代码在下面
		    // 将openId和用户绑定
		    String openId = (String) map.get("openId");
		    if("".equals(openId) && openId !=null && user!=null){
		    	map.put("user", user);
		    	userService.bandOpenId(openId,user.getUserId());
		    }
		    else{
		    	map.put("user", "应聘者");
		    }
		    
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    return map;
	}

	
		/**
		 * 微信小程序推送单个用户
		 * @param openid
		 * @param formid
		 * @return
		 */
		@RequestMapping(value="pushOneUser")
	    public String pushOneUser(HttpServletRequest request) {
			String appid = request.getParameter("appid");
			String secret = request.getParameter("secret");
	        //获取access_token
	        String access_token = getAccessToken(appid, secret);
	        String url = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send" +
	                "?access_token=" + access_token;

	        //拼接推送的模版
	        WxMssVo wxMssVo = new WxMssVo();
	        String openId = request.getParameter("openId");
	        String templateId = request.getParameter("templateId");
	        String formId = request.getParameter("formId");
	        wxMssVo.setTouser(openId);//用户openid
	        wxMssVo.setTemplate_id(templateId);//模版id
	        wxMssVo.setForm_id(formId);//formid

	        
//	        wxMssVo.setTouser("orUkK46Gjtpme5SuTm4JXCkWf-Xs");//用户openid
//	        wxMssVo.setTemplate_id("ZtivWveOEb0C6HQfKtdEu9xyl5fH82Dk1Vj7ax7wsG0");//模版id
//	        wxMssVo.setForm_id("144baaa0ccd546859c25c16e8cfcefd3");//formid


	        Map<String, TemplateData> m = new HashMap<>(5);

	        //keyword1：订单类型，keyword2：下单金额，keyword3：配送地址，keyword4：取件地址，keyword5备注
	        TemplateData keyword1 = new TemplateData();
	        keyword1.setValue("招聘报名成功");
	        m.put("keyword1", keyword1);

	        TemplateData keyword2 = new TemplateData();
	        keyword2.setValue("这里填下单金额的值");
	        m.put("keyword2", keyword2);
	        wxMssVo.setData(m);

	        TemplateData keyword3 = new TemplateData();
	        keyword3.setValue("这里填配送地址");
	        m.put("keyword3", keyword3);
	        wxMssVo.setData(m);

	        TemplateData keyword4 = new TemplateData();
	        keyword4.setValue("这里填取件地址");
	        m.put("keyword4", keyword4);
	        wxMssVo.setData(m);

	        TemplateData keyword5 = new TemplateData();
	        keyword5.setValue("这里填备注");
	        m.put("keyword5", keyword5);
	        wxMssVo.setData(m);

	        ResponseEntity<String> responseEntity =
	                restTemplate.postForEntity(url, wxMssVo, String.class);
	        System.out.println("小程序推送结果="+responseEntity.getBody());
	        return responseEntity.getBody();
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
	
	/**
	     * 获取accessToken
	     * @return
	     */
	private String getAccessToken(String appid, String secret){
		String tmpurl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
//		String url = tmpurl.replace("APPID", "wx8e63a772cbf1ab13");
//		url = url.replace("APPSECRET", "b59cd889d6f3dd545e8b0ab970207413");
		String url = tmpurl.replace("APPID", appid);
		url = url.replace("APPSECRET", secret);
		JSONObject resultJson =null;
		String result = Common.httpsRequest(url, "POST", null);
			try {
					resultJson = JSON.parseObject(result);
					String errmsg = (String) resultJson.get("errmsg");
					if(!"".equals(errmsg) && errmsg != null){ //如果为errmsg为ok，则代表发送成功，公众号推送信息给用户了。
//						logger.error("获取access_token失败："+errmsg);
						return "error";
					}
			} catch (JSONException e) {
			e.printStackTrace();
			}
			System.err.println((String) resultJson.get("access_token"));
			return (String) resultJson.get("access_token");
		}
	
	/**
	 * 下载文件
	 * 
	 * @param id
	 *            appid
	 * @param response
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "download")
	public void download( HttpServletResponse response, HttpServletRequest request)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		String filepath = "";
		filepath = request.getParameter("path");

		File file = new File(filepath);
		InputStream inputStream = null;
		OutputStream outputStream = null;
		byte[] b = new byte[1024];
		int len = 0;
		try {
			inputStream = new FileInputStream(file);
			outputStream = response.getOutputStream();

			response.setContentType("application/force-download");
			String filename = file.getName();
			response.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(filename, "UTF-8"));
			response.setContentLength((int) file.length());

			while ((len = inputStream.read(b)) != -1) {
				outputStream.write(b, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
					inputStream = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (outputStream != null) {
				try {
					outputStream.close();
					outputStream = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
