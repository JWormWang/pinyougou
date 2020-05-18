package com.oracle.pinyougou;

import com.oracle.pinyougou.mapper.TbUserMapper;
import com.oracle.pinyougou.pojo.TbAreasExample;
import com.oracle.pinyougou.pojo.TbUser;
import com.oracle.pinyougou.pojo.TbUserExample;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PinyougouDaoApplicationTests {

	@Autowired
	private TbUserMapper userMapper;
	@Test
	public void contextLoads() {
		TbUserExample example = new TbUserExample();
		TbUserExample.Criteria criteria = example.createCriteria();
		criteria.andIdIsNotNull();
		List<TbUser> list = userMapper.selectByExample(example);
		for(TbUser user:list){
			System.out.println(user.getUsername()+"--------------------------");
		}
	}

}
