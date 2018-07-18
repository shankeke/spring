package com.jusfoun.common.util.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.impl.piccolo.io.FileFormatException;

/**
 * 描述 :Excel文件读入为java Bean转化抽象. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年10月26日 下午4:47:59
 */
public abstract class AbstractExcelParser<T> {

	protected ParameterizedType pt = null;
	protected Class<?> clazz = null;

	private static final String EXTENSION_XLS = "xls";
	private static final String EXTENSION_XLSX = "xlsx";

	public AbstractExcelParser() {
		pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		clazz = (Class<?>) pt.getActualTypeArguments()[0];
	}

	/**
	 * 描述 : 获取Workbook. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年10月26日 上午11:39:00
	 * @param filePath
	 *            文件路径
	 * @return Workbook
	 * @throws IOException
	 */
	private Workbook getWorkbook(String filePath) throws IOException {
		Workbook workbook = null;
		InputStream is = new FileInputStream(filePath);
		if (filePath.endsWith(EXTENSION_XLS)) {
			workbook = new HSSFWorkbook(is);
		} else if (filePath.endsWith(EXTENSION_XLSX)) {
			workbook = new XSSFWorkbook(is);
		}
		return workbook;
	}

	private void preReadCheck(String filePath) throws FileNotFoundException, FileFormatException {
		// 常规检查
		File file = new File(filePath);
		if (!file.exists()) {
			throw new FileNotFoundException("传入的文件不存在：" + filePath);
		}
		if (!(filePath.endsWith(EXTENSION_XLS) || filePath.endsWith(EXTENSION_XLSX))) {
			throw new FileFormatException("传入的文件不是excel");
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> readXls2List(String filePath, Class<?>[] clazzs, String[] fieldNames, Integer index,
			String dateFormat) throws IOException, InstantiationException, IllegalAccessException,
			NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		preReadCheck(filePath);
		if (clazzs == null || clazzs.length == 0 || fieldNames == null || fieldNames.length == 0
				|| clazzs.length != fieldNames.length) {
			throw new IllegalAccessException("必须指定java bean的属性类型和属性名，并且指定对应关系");
		}
		Workbook workbook = getWorkbook(filePath);
		List<T> list = new ArrayList<T>();
		Row row = null;
		List<Sheet> sheets = new ArrayList<Sheet>();
		// 如果指定sheet则只解析改sheet的数据，否则解析全部
		if (index == null) {
			Sheet sheet = null;
			for (int numSheet = 0; numSheet < workbook.getNumberOfSheets(); numSheet++) {
				sheet = workbook.getSheetAt(numSheet);
				if (sheet == null) {
					continue;
				}
				sheets.add(sheet);
			}
		} else {
			sheets.add(workbook.getSheetAt(index));
		}

		if (sheets == null || sheets.size() == 0) {
			return null;
		}

		T t = null;
		// 循环工作表Sheet
		for (Sheet sheet : sheets) {
			// 循环行Row
			for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
				row = sheet.getRow(rowNum);
				if (row != null) {
					// 通过反射生成model的实例
					t = (T) clazz.newInstance();
					for (int i = 0; i < fieldNames.length; i++) {
						initField(t, clazzs[i], fieldNames[i], getValue(row.getCell(i), false, dateFormat));
					}
					list.add(t);
				}
			}
		}
		return list;
	}

	public List<T> readXls2List(String filePath, Class<?>[] clazzs, String[] fieldNames, String dateFormat)
			throws IOException, InstantiationException, IllegalAccessException, NoSuchMethodException,
			SecurityException, IllegalArgumentException, InvocationTargetException {
		return readXls2List(filePath, clazzs, fieldNames, null, dateFormat);
	}

	private String getValue(Cell cell, boolean treatAsStr, String dateFormat) {
		if (cell == null) {
			return "";
		}
		if (treatAsStr) {
			// 虽然excel中设置的都是文本，但是数字文本还被读错，如“1”取成“1.0”
			// 加上下面这句，临时把它当做文本来读取
			cell.setCellType(Cell.CELL_TYPE_STRING);
		}
		if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(cell.getBooleanCellValue());
		} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			if (HSSFDateUtil.isCellDateFormatted(cell)) {
				Date date = cell.getDateCellValue();
				return DateFormatUtils.format(date, dateFormat);
			} else {
				// 返回数值类型的值
				Object inputValue = null;// 单元格值
				Long longVal = Math.round(cell.getNumericCellValue());
				Double doubleVal = cell.getNumericCellValue();
				if (Double.parseDouble(longVal + ".0") == doubleVal) { // 判断是否含有小数位.0
					inputValue = longVal;
				} else {
					inputValue = doubleVal;
				}
				DecimalFormat df = new DecimalFormat("#.##"); // 格式化为两位小数，按自己需求选择；
				return String.valueOf(df.format(inputValue)); // 返回String类型
			}
		} else {
			return String.valueOf(cell.getStringCellValue());
		}
	}

	// 首字母大写
	public String captureName(String name) {
		char[] cs = name.toCharArray();
		cs[0] -= 32;
		return String.valueOf(cs);
	}

	public void initField(T t, Class<?> claxx, String fieldName, String value) throws NoSuchMethodException,
			SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		if (value != null) {
			fieldName = captureName(fieldName); // 将属性的首字符大写，方便构造get，set方法
			Method m = clazz.getMethod("set" + fieldName, claxx);
			if (Integer.class.isAssignableFrom(claxx)) { // 如果type是类类型，则前面包含"class
				m.invoke(t, Integer.valueOf(value));
			} else if (Long.class.isAssignableFrom(claxx)) { // 如果type是类类型，则前面包含"class
				m.invoke(t, Long.valueOf(value));
			} else if (Float.class.isAssignableFrom(claxx)) { // 如果type是类类型，则前面包含"class
				m.invoke(t, Float.valueOf(value));
			} else if (Short.class.isAssignableFrom(claxx)) { // 如果type是类类型，则前面包含"class
				m.invoke(t, Short.valueOf(value));
			} else if (Byte.class.isAssignableFrom(claxx)) { // 如果type是类类型，则前面包含"class
				m.invoke(t, Byte.valueOf(value));
			} else if (Double.class.isAssignableFrom(claxx)) { // 如果type是类类型，则前面包含"class
				m.invoke(t, Double.valueOf(value));
			} else {
				m.invoke(t, value);
			}
		}
	}
}
