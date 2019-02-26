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

import com.alibaba.fastjson.JSONObject;

import cn.edu.tit.bean.User;
import cn.edu.tit.idao.IUserDao;
import cn.edu.tit.iservice.IUserService;


@RunWith(SpringJUnit4ClassRunner.class) // 表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class AdminTest {
	private static Logger logger = Logger.getLogger(AdminTest.class);

	@Resource
	private IUserService iadminService;

	@Resource
	private IUserDao userDao;

	@Test
	public void Test() {
		System.out.println(userDao.getApplyById("1"));
	}
}

