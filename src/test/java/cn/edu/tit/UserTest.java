package cn.edu.tit;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

<<<<<<< HEAD:src/test/java/cn/edu/tit/AdminTest.java
import com.alibaba.fastjson.JSONObject;

=======
import cn.edu.tit.bean.RecruitInfo;
>>>>>>> dd9bcbbe450a1cdca06914e3277e54d95eb0e7e4:src/test/java/cn/edu/tit/UserTest.java
import cn.edu.tit.bean.User;
import cn.edu.tit.idao.IUserDao;
import cn.edu.tit.iservice.IUserService;


@RunWith(SpringJUnit4ClassRunner.class) // 表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class UserTest {
	private static Logger logger = Logger.getLogger(UserTest.class);

	@Resource
	private IUserService iUserService;

	@Resource
<<<<<<< HEAD:src/test/java/cn/edu/tit/AdminTest.java
	private IUserDao userDao;

	@Test
	public void Test() {
		System.out.println(userDao.getApplyById("1"));
=======
	private IUserDao iUserDao;

	@Test
	public void Test() {
		//		List<RecruitInfo> list = new ArrayList<RecruitInfo>();
		//		list = iUserService.getRecruitInfo(null);
		//		for (RecruitInfo recruitInfo : list) {
		//			System.out.println(recruitInfo.toString());
>>>>>>> dd9bcbbe450a1cdca06914e3277e54d95eb0e7e4:src/test/java/cn/edu/tit/UserTest.java
	}
}


