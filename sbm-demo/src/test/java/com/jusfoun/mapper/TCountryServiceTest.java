package com.jusfoun.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.common.collect.Lists;
import com.jusfoun.common.base.page.IPage;
import com.jusfoun.common.message.exception.ServiceException;
import com.jusfoun.entity.TCountry;
import com.jusfoun.entity.vo.TCountryTotalVo;
import com.jusfoun.service.TCountryService;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TCountryServiceTest {

	@Autowired
	private TCountryService tCountryService;

	@Test
	public void delete() {
		TCountry record = new TCountry();
		record.setEnName("马雷特星");
		int i = tCountryService.delete(record);
		System.out.println(i);
	}

	@Test
	public void deleteList() {
		List<TCountry> recordList = Lists.newArrayList();
		TCountry record = new TCountry();
		record.setEnName("马雷特星");
		recordList.add(record);
		int i = tCountryService.deleteList(recordList);
		System.out.println(i);
	}

	@Test
	public void deleteByExample() {
		Example example = new Example(TCountry.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("id", 20);
		int i = tCountryService.deleteByExample(example);
		System.out.println(i);
	}

	@Test
	public void selectOne() {
		TCountry record = new TCountry();
		record.setId(1L);
		TCountry t = tCountryService.selectOne(record);
		System.out.println(t);
	}

	@Test
	public void selectOneByExample() {
		Example example = new Example(TCountry.class);
		Criteria criteria = example.createCriteria();
		criteria.andGreaterThan("id", 20);
		TCountry t = tCountryService.selectOneByExample(example);
		System.out.println(t);
	}

	@Test
	public void select() {
		TCountry record = new TCountry();
		record.setId(1L);
		List<TCountry> list = tCountryService.select(record);
		System.out.println(list);
	}

	@Test
	public void selectOrderBy() {
		TCountry record = new TCountry();
		record.setId(1L);
		List<TCountry> list = tCountryService.select(record, "id ASC");
		System.out.println(list);
	}

	@Test
	public void selectAll() {
		List<TCountry> list = tCountryService.selectAll();
		System.out.println(list);
	}

	@Test
	public void selectAll(String orderByClause) {

		List<TCountry> list = tCountryService.selectAll("id ASC");
		System.out.println(list);
	}

	@Test
	public void selectByExample() {
		Example example = new Example(TCountry.class);
		Criteria criteria = example.createCriteria();
		criteria.andGreaterThan("id", 20);
		List<TCountry> list = tCountryService.selectByExampleAndPage(example, new IPage<TCountry>());
		System.out.println(list);
	}

	@Test
	public void selectByRowBounds() {
		TCountry record = new TCountry();
		record.setId(1L);
		List<TCountry> list = tCountryService.selectByRowBounds(record, new RowBounds(0, 10));
		System.out.println(list);
	}

	@Test
	public void selectByExampleAndRowBounds() {
		Example example = new Example(TCountry.class);
		example.orderBy("en_name asc");
		List<TCountry> list = tCountryService.selectByExampleAndRowBounds(example, new RowBounds(0, 10));
		System.out.println(list);
	}

	@Test
	public void selectByExampleAndPage() {
		Example example = new Example(TCountry.class);
		example.orderBy("en_name asc");
		List<TCountry> list = tCountryService.selectByExampleAndPage(example, new IPage<TCountry>("id ASC"));
		System.out.println(list);
	}

	@Test
	public void selectByPage() {
		TCountry record = new TCountry();
		record.setId(1L);
		List<TCountry> list = tCountryService.selectByPage(record, new IPage<TCountry>(true, 1, 10, "id ASC"));
		System.out.println(list);
	}

	@Test
	public void selectPage() {
		TCountry record = new TCountry();
		record.setId(1L);
		IPage<TCountry> page = tCountryService.selectPage(record, new IPage<TCountry>(true, 1, 10, "id ASC"));
		System.out.println(page);
	}

	@Test
	public void selectPageByExample() {
		Example example = new Example(TCountry.class);
		example.orderBy("id ASC");
		IPage<TCountry> list = tCountryService.selectPageByExample(example, new IPage<TCountry>(true, 1, 10, "id ASC"));
		System.out.println(list);
	}

	@Test
	public void selectCount() {
		TCountry record = new TCountry();
		record.setId(1L);
		int count = tCountryService.selectCount(record);
		System.out.println(count);
	}

	@Test
	public void selectCountByExample() {
		Example example = new Example(TCountry.class);
		example.orderBy("id ASC");
		Criteria criteria = example.createCriteria();
		criteria.andLike("en_name", "A");
		int count = tCountryService.selectCountByExample(example);
		System.out.println(count);
	}

	@Test
	public void selectCounties() throws ServiceException {
		TCountryTotalVo t = tCountryService.selectCounties();
		System.out.println(t);
	}

}
