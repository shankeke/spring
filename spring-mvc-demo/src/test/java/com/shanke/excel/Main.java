package com.shanke.excel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.alibaba.fastjson.JSON;

public class Main {

	@SuppressWarnings("unused")
	public static void main(String[] args) throws Exception {
		BaseResult result = new BaseResult();

		List<HtSpbmMx> list = new ArrayList<HtSpbmMx>();
		HtSpbmMx t = null;

		File f = new File("C:/spbm.txt");
		FileWriter fileWritter = new FileWriter(f);
		BufferedWriter bufferWritter = new BufferedWriter(fileWritter);

		FileInputStream file = new FileInputStream("D:/商品编码表.xls");
		POIFSFileSystem ts = new POIFSFileSystem(file);
		HSSFWorkbook wb = new HSSFWorkbook(ts);
		HSSFSheet sh = wb.getSheetAt(0);
		HSSFRow ro = null;
		int c = 0;
		for (int i = 0; sh.getRow(i) != null; i++) {
			ro = sh.getRow(i);
			if (i > 1) {
				t = new HtSpbmMx();
				t.setLc(ro.getCell(0) + "");// 栏次
				t.setSpbm(ro.getCell(1) + "");// 商品编码
				t.setSpmc(ro.getCell(2) + "");// 商品名称
				t.setSm(ro.getCell(3) + "");// 说明
				t.setZzssl(ro.getCell(4) + "");// 增值税税率
				t.setGjz(ro.getCell(5) + "");// 关键字
				t.setZzstsgl(ro.getCell(6) + "");// 增值税特殊管理
				t.setTjjbm(ro.getCell(7) + "");// 统计局编码或国民行业代码
				t.setBbh(ro.getCell(8) + "");// 版本号
				t.setQysj(ro.getCell(9) + "");// 启用时间
				t.setGxsj(ro.getCell(10) + "");// 更新时间
				t.setP_spbm(ro.getCell(11) + "");// 上级商品编码
				// list.add(t);
				bufferWritter.write(JSON.toJSONString(t) + "\n");
			}
		}
		/*
		 * result.setCode(0); result.setMessage("数据处理成功");
		 * result.setContent(list); String json = JSON.toJSONString(result);
		 * System.out.println(json); File f = new File("C:/spbm.txt");
		 * FileWriter fileWritter = new FileWriter(f); BufferedWriter
		 * bufferWritter = new BufferedWriter(fileWritter);
		 * bufferWritter.write(json);
		 */
		bufferWritter.flush();
		bufferWritter.close();
	}
}
