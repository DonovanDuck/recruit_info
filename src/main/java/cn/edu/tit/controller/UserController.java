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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
	private IUserService iUserService;

	@RequestMapping(value="toMainPage",method= {RequestMethod.GET})
	public ModelAndView toMainPage(HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		List<RecruitInfo> list = new ArrayList<RecruitInfo>();
		//		User publisher = (User) request.getSession().getAttribute("User");
		//		String publisherId = publisher.getUserId();
		try {
			//获取招聘信息
			list = iUserService.getRecruitInfo(null);
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
			list = iUserService.searchRecruit(search);
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
			iUserService.publishRcruit(recruit);
			mv = toMainPage(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}

	/**
	 * @author Liming
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
