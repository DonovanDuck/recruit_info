package cn.edu.tit.controller.wxController;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.URL;
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
//					if (password != user.getPassword()) {
//						ret.put("user", user);
//						return ret;
//					} else {
//						ret.put("status", "ERROR");
//						ret.put("msg", "密码错误");
//						return ret;
//					}
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
		try {
			User user = new User();
			user.setUserId(Common.uuid());
			user.setPassword(Common.eccryptMD5("123456"));
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
			User user = (User) request.getSession().getAttribute("User");
			userId = user.getUserId();
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
						(String)formdata.get("paperTopicSituation"), (String)formdata.get("sanctionSituation"),(String)formdata.get("openId"),(String)formdata.get("formId"));
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
//			List<Position> occupationApplicantLsit = new ArrayList<Position>();
			Integer numAll, numAllToday, numDoctor, numDoctorToday, numMaster, numMasterToday, numBachelor,
					numBachelorToday, numInSide, numInSideToday,numFirstSchool,numFirstSchoolToday,numFirstMajor,numFirstMajorToday;
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
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
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
	public  Map<String, Object> getWxUserOpenid(@RequestParam(value="js_code")String js_code, @RequestParam(value="appid")String appid, 
			@RequestParam(value="secret")String secret) {
	    //拼接url
		StringBuilder url = new StringBuilder("https://api.weixin.qq.com/sns/jscode2session?");
		url.append("appid=");//appid设置
		url.append(appid);
		url.append("&secret=");//secret设置
		url.append(secret);
		url.append("&js_code=");//code设置
		url.append(js_code);
		url.append("&grant_type=authorization_code");
		Map<String, Object> map = null;
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
	

}
