package cn.edu.tit;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;

import cn.edu.tit.bean.Apply;
import cn.edu.tit.bean.Material;
import cn.edu.tit.bean.Position;
import cn.edu.tit.bean.RecruitInfo;
import cn.edu.tit.bean.User;
import cn.edu.tit.common.Common;
import cn.edu.tit.idao.IUserDao;
import cn.edu.tit.iservice.IUserService;


@RunWith(SpringJUnit4ClassRunner.class) // 表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class UserTest {
	private static Logger logger = Logger.getLogger(UserTest.class);

	@Resource
	private IUserService iUserService;

	@Resource
	private IUserDao userDao;

	@Test
	public void Test() throws Exception {
		//		Material m = new Material();
		//		m.setApplyId("1");
		//		m.setMaterialId(Common.uuid());
		//		userDao.saveMaterial(m);
		//		User user = new User();
		//		user = iUserService.getUserById("2");
		//		System.out.println(user.toString());
		//		List<RecruitInfo> list = new ArrayList<RecruitInfo>();
		//		list = userDao.getRecruitInfo(null);
		//		for (RecruitInfo recruitInfo : list) {
		//			recruitInfo.toString();
		//		}
		//		RecruitInfo i;
		//		for (int j = 0; j < 30; j++) {
		//			i = new RecruitInfo();
		//			i.setRecruitId(Common.uuid());
		//			i.setAccessory("1");
		//			i.setOrganization("1");
		//			i.setPublisher("690DCF33E85A41538FD7CC56DA8A1BBC");
		//			i.setRecruitInfo("1");
		//			userDao.publishRcruit(i);
		//		}
		//		List<Position> list = new ArrayList<Position>();
		//		list = userDao.getPositionByRecruitId("FAA761275C8F4CC5B9933703FA27B42E");
		//		for (Position position : list) {
		//			System.out.println(position);
		//		}
	//	iUserService.deletePosition("1", "2");
	}
}


