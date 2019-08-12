package com.lipengyun.redis_lpy.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lipengyun.redis_lpy.domain.Employee;
import com.lipengyun.redis_lpy.utils.RandomUitl;
import com.lipengyun.redis_lpy.utils.StringUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring-beans.xml")
public class RedisJDKTest {

	@Resource
	private RedisTemplate<String, Serializable> redisTemplate;
	
	@Test
	public void test_insert() {
		
		List<Employee> employees = new ArrayList<>();
		for(int i=1;i<100000;i++) {
			employees.add(new Employee(i, StringUtil.generateChineseName()+StringUtil.randomChineseString(2),
					"13"+RandomUitl.randomString(9), StringUtil.randomChineseString(10)));
		}
		long startTimes = System.currentTimeMillis();
		for (Employee employee : employees) {
			
			redisTemplate.opsForValue().set("u_"+employee.getId(), employee);
		}
		long endTimes = System.currentTimeMillis();
		
		System.out.println(""+(endTimes-startTimes));
		
	}
	
	
}
