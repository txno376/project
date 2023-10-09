package com.project;

import com.project.sys.entity.User;
import com.project.sys.mapper.UserMapper;
import com.project.sys.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;


import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@SpringBootTest
class ProjectApplicationTests {

	@Autowired
	private IUserService userMapper;
	@Test
	void testMapper() {
		User user=new User();
		user.setUsername("1");user.setPassword("1");
		System.out.println(user);
		Map<String,Object> data = userMapper.login(user);
		System.out.println(data);
		Assert.isNull(data,"1");


	}
}
