package com.lipengyun.redis_lpy.test;

import java.util.HashMap;
import java.util.Map;

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
public class RedisJDKHashTest {

	@Resource
	private RedisTemplate<String, Employee> redisTemplate;
	
	@Test
	public void Redis_HashJDKTest() {
		
		Map<String, Employee> employees = new HashMap<String, Employee>();
		for(int i = 1;i < 100000;i++) {
			employees.put("e"+i, new Employee(i, StringUtil.generateChineseName()+StringUtil.randomChineseString(2),
					"13"+RandomUitl.randomString(9), StringUtil.randomChineseString(10)));
		}
		
		long startTimes = System.currentTimeMillis();

		// redisTemplate.opsForHash().put("key","u_" + employee.getId(), employee)
		redisTemplate.opsForHash().putAll("employee_keys", employees);

		long endTimes = System.currentTimeMillis();

		System.out.println("采用Hash,jdk序列化存储的时间：" + (endTimes - startTimes));
		
		
	}
	
}
