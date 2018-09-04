// package com.jusfoun.mapper;
//
// import java.util.List;
// import java.util.Map;
//
// import org.apache.ibatis.session.RowBounds;
// import org.junit.Test;
// import org.junit.runner.RunWith;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.test.context.junit4.SpringRunner;
//
// import com.google.common.collect.Lists;
// import com.jusfoun.common.base.page.IPage;
// import com.jusfoun.common.message.exception.ServiceException;
// import com.jusfoun.common.mybatis.mapper.MyBaseMapper;
// import com.jusfoun.common.mybatis.mapper.MyIdableMapper;
// import
// com.jusfoun.common.mybatis.mapper.extend.BaseWithAssociateSelectMapper;
// import com.jusfoun.entity.TCountry;
// import com.jusfoun.entity.vo.TCountryTotalVo;
// import com.jusfoun.service.TCountryService;
//
// import tk.mybatis.mapper.entity.Example;
// import tk.mybatis.mapper.entity.Example.Criteria;
//
// @RunWith(SpringRunner.class)
// @SpringBootTest
// public class TCountryServiceTest {
//
// @Autowired
// private TCountryService tCountryService;
//
// @Test
// public void selectCountWithAssociate(Map<?, ?> params) throws
// ServiceException {
//
// tCountryService.selectCountWithAssociate(params);
// }
//
// @Test
// public List<TCountry> selectListWithAssociate(Map<?, ?> params, String
// orderByClause) throws ServiceException {
//
// tCountryService.selectListWithAssociate(params, orderByClause);
// }
//
// @Test
// public List<TCountry> selectListWithAssociate(Map<?, ?> params) throws
// ServiceException {
//
// tCountryService.selectListWithAssociate(params);
// }
//
// @Test
// public List<TCountry> selectListWithAssociate(Map<?, ?> params,
// IPage<TCountry> page) throws ServiceException {
//
// tCountryService.selectListWithAssociate(params, page);
// }
//
// @Test
// public IPage<TCountry> selectPageWithAssociate(Map<?, ?> params,
// IPage<TCountry> page) throws ServiceException {
//
// tCountryService.selectPageWithAssociate(params, page);
// }
//
// @Test
// public TCountry selectOneWithAssociate(Map<?, ?> params) throws
// ServiceException {
//
// tCountryService.selectOneWithAssociate(params);
// }
//
// @Test
// public TCountry selectPKWithAssociate(Object id) throws ServiceException {
//
// tCountryService.selectPKWithAssociate(id);
// }
//
// @Test
// public TCountry selectPKWithAssociate(String primaryKeyName, Object
// primaryKeyValue) throws ServiceException {
//
// tCountryService.selectPKWithAssociate(primaryKeyName, primaryKeyValue);
// }
//
// @Test
// public int insertListWithPrimaryKey(List<TCountry> recordList) {
//
// tCountryService.insertListWithPrimaryKey(recordList);
// }
//
// @Test
// public int insertUseGeneratedKeys(TCountry record) {
//
// tCountryService.insertUseGeneratedKeys(record);
// }
//
// @Test
// public int replace(TCountry record) {
//
// tCountryService.replace(record);
// }
//
// @Test
// public int replaceListWithPrimaryKey(List<TCountry> recordList) {
//
// tCountryService.replaceListWithPrimaryKey(recordList);
// }
//
// @Test
// public int updateByPrimaryKey(TCountry record) {
//
// tCountryService.updateByPrimaryKey(record);
// }
//
// @Test
// public int updateByPrimaryKeySelective(TCountry record) {
//
// tCountryService.updateByPrimaryKeySelective(record);
// }
//
// @Test
// public int updateListByPrimaryKey(List<TCountry> recordList) {
//
// tCountryService.updateListByPrimaryKey(recordList);
// }
//
// @Test
// public int updateListByPrimaryKeySelective(List<TCountry> recordList) {
//
// tCountryService.updateListByPrimaryKeySelective(recordList);
// }
//
// @Test
// public int deleteByPrimaryKey(Object key) {
//
// tCountryService.deleteByPrimaryKey(key);
// }
//
// @Test
// public int deleteListByPrimaryKey(List<TCountry> recordList) {
//
// tCountryService.deleteListByPrimaryKey(recordList);
// }
//
// @Test
// public int deleteByPrimaryKeys(List<?> keys) {
//
// tCountryService.deleteByPrimaryKeys(keys);
// }
//
// @Test
// public int deleteByPrimaryKeys(Object... keys) {
//
// tCountryService.deleteByPrimaryKeys(keys);
// }
//
// @Test
// public TCountry selectByPrimaryKey(Object key) {
//
// tCountryService.selectByPrimaryKey(key);
// }
//
// @Test
// public List<TCountry> selectByPrimaryKeys(List<?> keys) {
//
// tCountryService.selectByPrimaryKeys(keys);
// }
//
// @Test
// public List<TCountry> selectByPrimaryKeys(Object... keys) {
//
// tCountryService.selectByPrimaryKeys(keys);
// }
//
// @Test
// public boolean existsWithPrimaryKey(Object key) {
//
// tCountryService.existsWithPrimaryKey(key);
// }
//
// @Test
// public int deleteByIds(String ids) {
//
// tCountryService.deleteByIds(ids);
// }
//
// @Test
// public List<TCountry> selectByIds(String ids) {
//
// tCountryService.selectByIds(ids);
// }
//
// @Test
// public MyBaseMapper<TCountry> getMyBaseMapper() {
//
// return null;
// }
//
// @Test
// public int insert(TCountry record) {
//
// tCountryService.insert(record);
// }
//
// @Test
// public int insertSelective(TCountry record) {
//
// tCountryService.insertSelective(record);
// }
//
// @Test
// public int insertList(List<? extends TCountry> recordList) {
//
// tCountryService.insertList(recordList);
// }
//
// @Test
// public int insertListSelective(List<TCountry> recordList) throws
// ServiceException {
//
// tCountryService.insertListSelective(recordList);
// }
//
// @Test
// public int updateByExample(TCountry record, Object example) {
//
// tCountryService.updateByExample(record, example);
// }
//
// @Test
// public int updateByExampleSelective(TCountry record, Object example) {
//
// tCountryService.updateByExampleSelective(record, example);
// }
//
// @Test
// public int updateByDiffer(TCountry old, TCountry newer) {
//
// tCountryService.updateByDiffer(old, newer);
// }
//
// @Test
// public int updateByPrimaryKeySelectiveForce(TCountry record, List<String>
// forceUpdateProperties) {
//
// tCountryService.updateByPrimaryKeySelectiveForce(record,
// forceUpdateProperties);
// }
//
// @Test
// public int delete() {
// TCountry record = new TCountry();
// record.setEnName("马雷特星");
// int i = tCountryService.delete(record);
// System.out.println(i);
// }
//
// @Test
// public void deleteList() {
// List<TCountry> recordList = Lists.newArrayList();
// TCountry record = new TCountry();
// record.setEnName("马雷特星");
// recordList.add(record);
// int i = tCountryService.deleteList(recordList);
// System.out.println(i);
// }
//
// @Test
// public void deleteByExample() {
// Example example = new Example(TCountry.class);
// Criteria criteria = example.createCriteria();
// criteria.andEqualTo("id", 20);
// int i = tCountryService.deleteByExample(example);
// System.out.println(i);
// }
//
// @Test
// public void selectOne() {
// TCountry record = new TCountry();
// record.setId(1L);
// TCountry t = tCountryService.selectOne(record);
// System.out.println(t);
// }
//
// @Test
// public void selectOneByExample() {
// Example example = new Example(TCountry.class);
// Criteria criteria = example.createCriteria();
// criteria.andGreaterThan("id", 20);
// TCountry t = tCountryService.selectOneByExample(example);
// System.out.println(t);
// }
//
// @Test
// public void select() {
// TCountry record = new TCountry();
// record.setId(1L);
// List<TCountry> list = tCountryService.select(record);
// System.out.println(list);
// }
//
// @Test
// public void selectOrderBy() {
// TCountry record = new TCountry();
// record.setId(1L);
// List<TCountry> list = tCountryService.select(record, "id ASC");
// System.out.println(list);
// }
//
// @Test
// public void selectAll() {
// List<TCountry> list = tCountryService.selectAll();
// System.out.println(list);
// }
//
// @Test
// public void selectAll(String orderByClause) {
//
// List<TCountry> list = tCountryService.selectAll("id ASC");
// System.out.println(list);
// }
//
// @Test
// public void selectByExample() {
// Example example = new Example(TCountry.class);
// Criteria criteria = example.createCriteria();
// criteria.andGreaterThan("id", 20);
// List<TCountry> list = tCountryService.selectByExampleAndPage(example, new
// IPage<TCountry>());
// System.out.println(list);
// }
//
// @Test
// public void selectByRowBounds() {
// TCountry record = new TCountry();
// record.setId(1L);
// List<TCountry> list = tCountryService.selectByRowBounds(record, new
// RowBounds(0, 10));
// System.out.println(list);
// }
//
// @Test
// public void selectByExampleAndRowBounds() {
// Example example = new Example(TCountry.class);
// example.orderBy("en_name asc");
// List<TCountry> list = tCountryService.selectByExampleAndRowBounds(example,
// new RowBounds(0, 10));
// System.out.println(list);
// }
//
// @Test
// public void selectByExampleAndPage() {
// Example example = new Example(TCountry.class);
// example.orderBy("en_name asc");
// List<TCountry> list = tCountryService.selectByExampleAndPage(example, new
// IPage<TCountry>("id ASC"));
// System.out.println(list);
// }
//
// @Test
// public void selectByPage() {
// TCountry record = new TCountry();
// record.setId(1L);
// List<TCountry> list = tCountryService.selectByPage(record, new
// IPage<TCountry>(true, 1, 10, "id ASC"));
// System.out.println(list);
// }
//
// @Test
// public void selectPage() {
// TCountry record = new TCountry();
// record.setId(1L);
// IPage<TCountry> page = tCountryService.selectPage(record, new
// IPage<TCountry>(true, 1, 10, "id ASC"));
// System.out.println(page);
// }
//
// @Test
// public void selectPageByExample() {
// Example example = new Example(TCountry.class);
// example.orderBy("id ASC");
// IPage<TCountry> list = tCountryService.selectPageByExample(example, new
// IPage<TCountry>(true, 1, 10, "id ASC"));
// System.out.println(list);
// }
//
// @Test
// public void selectCount() {
// TCountry record = new TCountry();
// record.setId(1L);
// int count = tCountryService.selectCount(record);
// System.out.println(count);
// }
//
// @Test
// public void selectCountByExample() {
// Example example = new Example(TCountry.class);
// example.orderBy("id ASC");
// Criteria criteria = example.createCriteria();
// criteria.andLike("en_name", "A");
// int count = tCountryService.selectCountByExample(example);
// System.out.println(count);
// }
//
// @Test
// public void selectCounties() throws ServiceException {
// TCountryTotalVo t = tCountryService.selectCounties();
// System.out.println(t);
// }
//
// }
