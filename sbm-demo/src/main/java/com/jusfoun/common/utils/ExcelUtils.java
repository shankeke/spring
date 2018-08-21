package com.jusfoun.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
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
 * 描述 :Excel文件读入为java对象 . <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年10月26日 下午4:47:59
 */
public abstract class ExcelUtils {

	private static final String EXTENSION_XLS = "xls";
	private static final String EXTENSION_XLSX = "xlsx";

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
	private static Workbook getWorkbook(String filePath) throws IOException {
		Workbook workbook = null;
		InputStream is = new FileInputStream(filePath);
		if (filePath.endsWith(EXTENSION_XLS)) {
			workbook = new HSSFWorkbook(is);
		} else if (filePath.endsWith(EXTENSION_XLSX)) {
			workbook = new XSSFWorkbook(is);
		}
		return workbook;
	}

	private static void preReadCheck(String filePath) throws FileNotFoundException, FileFormatException {
		// 常规检查
		File file = new File(filePath);
		if (!file.exists()) {
			throw new FileNotFoundException("传入的文件不存在：" + filePath);
		}
		if (!(filePath.endsWith(EXTENSION_XLS) || filePath.endsWith(EXTENSION_XLSX))) {
			throw new FileFormatException("传入的文件不是excel");
		}
	}

	/**
	 * 描述: 读取excel中内容到java类型的集合中去. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年1月25日 下午3:05:42
	 * @param clazz
	 *            需要解析成的java类型
	 * @param fieldClasss
	 *            对应解析属性的类型集合，与excel中对应字段排序要一致，包含基本数据类型:byte,short,int,long,float,long,String,Date及他们的包装类（如果有的话）
	 * @param fieldNames
	 *            属性名称，这里要和fieldClasss对应关系处理好，并且与excel中对应字段排序要一致
	 * @param filePath
	 *            文件路径
	 * @param index
	 *            sheet下标
	 * @param dateFormat
	 *            如果有日期类型，指定日期字符串解析格式
	 * @param numberFormat
	 *            如果有数值类型，指定数值字符串解析格式
	 * @return 返回实体集合
	 * @throws Exception
	 */
	public static <T> List<T> readXls2List(Class<T> clazz, Class<?>[] fieldClasss, String[] fieldNames, String filePath, Integer index, String dateFormat, String numberFormat)
			throws Exception {
		preReadCheck(filePath);
		if (fieldClasss == null || fieldClasss.length == 0 || fieldNames == null || fieldNames.length == 0 || fieldClasss.length != fieldNames.length) {
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
						initField(t, fieldClasss[i], fieldNames[i], getValue(row.getCell(i), false, dateFormat, numberFormat), dateFormat);
					}
					list.add(t);
				}
			}
		}
		return list;
	}

	/**
	 * 描述: 读取excel中内容到javabean的集合中中去. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年1月25日 下午3:05:42
	 * @param clazz
	 *            需要解析成的java类型
	 * @param fieldClasss
	 *            对应解析属性的类型集合，与excel中对应字段排序要一致，包含基本数据类型:byte,short,int,long,float,long,String,Date及他们的包装类（如果有的话）
	 * @param fieldNames
	 *            属性名称，这里要和fieldClasss对应关系处理好，并且与excel中对应字段排序要一致
	 * @param filePath
	 *            文件路径
	 * @param dateFormat
	 *            如果有日期类型，指定日期字符串解析格式
	 * @param numberFormat
	 *            如果有数值类型，指定数值字符串解析格式
	 * @return 返回实体集合
	 * @throws Exception
	 */
	public static <T> List<T> readXls2List(Class<T> clazz, Class<?>[] fieldClasss, String[] fieldNames, String filePath, String dateFormat) throws Exception {
		return readXls2List(clazz, fieldClasss, fieldNames, filePath, null, dateFormat, null);
	}

	/**
	 * 描述: 读取excel中内容到javabean的集合中中去. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年1月25日 下午3:05:42
	 * @param clazz
	 *            需要解析成的java类型
	 * @param filePath
	 *            文件路径
	 * @param fieldClasss
	 *            对应解析属性的类型集合，与excel中对应字段排序要一致，包含基本数据类型:byte,short,int,long,float,long,String,Date及他们的包装类（如果有的话）
	 * @param fieldNames
	 *            属性名称，这里要和fieldClasss对应关系处理好，并且与excel中对应字段排序要一致
	 * @param dateFormat
	 *            如果有日期类型，指定日期字符串解析格式
	 * @param numberFormat
	 *            如果有数值类型，指定数值字符串解析格式
	 * @return 返回实体集合
	 * @throws Exception
	 */
	public static <T> List<T> readXls2List(Class<T> clazz, Class<?>[] fieldClasss, String[] fieldNames, String filePath, String dateFormat, String numberFormat) throws Exception {
		return readXls2List(clazz, fieldClasss, fieldNames, filePath, null, dateFormat, numberFormat);
	}

	/**
	 * 描述:获取单元格中的数据为字符串格式. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年1月25日 下午3:35:28
	 * @param cell
	 *            单元格
	 * @param treatAsStr
	 *            是否当做字符串处理
	 * @param dateFormat
	 *            日期格式
	 * @param numberFormat
	 *            数值格式
	 * @return 数据串
	 */
	private static String getValue(Cell cell, boolean treatAsStr, String dateFormat, String numberFormat) {
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
				if (StringUtils.isEmpty(dateFormat)) {
					return cell.getStringCellValue();
				} else {
					Date date = cell.getDateCellValue();
					return DateFormatUtils.format(date, dateFormat);
				}
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
				if (StringUtils.isEmpty(numberFormat)) {
					numberFormat = "#.##";
				}
				DecimalFormat df = new DecimalFormat(numberFormat); // 格式化为指定小数位，按自己需求选择；
				return String.valueOf(df.format(inputValue)); // 返回String类型
			}
		} else {
			return cell.getStringCellValue();
		}
	}

	// 首字母大写
	private static String captureName(String name) {
		char[] cs = name.toCharArray();
		cs[0] -= 32;
		return String.valueOf(cs);
	}

	/**
	 * 描述: 实体字段赋值. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年1月25日 下午3:14:16
	 * @param t
	 *            实体对象
	 * @param fieldClass
	 *            字段类型
	 * @param fieldName
	 *            字段名称
	 * @param fieldValue
	 *            字段值字符串
	 * @param format
	 *            日期格式
	 * 
	 * @throws Exception
	 */
	private static <T> void initField(T t, Class<?> fieldClass, String fieldName, String fieldValue, String format) throws Exception {
		if (StringUtils.isEmpty(fieldValue)) {
			return;
		}

		// 将属性的首字符大写，方便构造get，set方法
		fieldName = captureName(fieldName);

		// 获取实体的类型
		Class<? extends Object> clazz = t.getClass();
		Method m = clazz.getMethod("set" + fieldName, fieldClass);

		// 判断数据类型并执行方法
		if (Integer.class.isAssignableFrom(fieldClass) || int.class.isAssignableFrom(fieldClass)) {
			m.invoke(t, Integer.valueOf(fieldValue));
		} else if (Long.class.isAssignableFrom(fieldClass) || long.class.isAssignableFrom(fieldClass)) {
			m.invoke(t, Long.valueOf(fieldValue));
		} else if (Float.class.isAssignableFrom(fieldClass) || float.class.isAssignableFrom(fieldClass)) {
			m.invoke(t, Float.valueOf(fieldValue));
		} else if (Short.class.isAssignableFrom(fieldClass) || short.class.isAssignableFrom(fieldClass)) {
			m.invoke(t, Short.valueOf(fieldValue));
		} else if (Byte.class.isAssignableFrom(fieldClass) || byte.class.isAssignableFrom(fieldClass)) {
			m.invoke(t, Byte.valueOf(fieldValue));
		} else if (Double.class.isAssignableFrom(fieldClass) || double.class.isAssignableFrom(fieldClass)) {
			m.invoke(t, Double.valueOf(fieldValue));
		} else if (Boolean.class.isAssignableFrom(fieldClass) || boolean.class.isAssignableFrom(fieldClass)) {
			m.invoke(t, Boolean.valueOf(fieldValue));
		} else if (char.class.isAssignableFrom(fieldClass)) {
			m.invoke(t, DateUtils.parse(format, fieldValue));
		} else if (Date.class.isAssignableFrom(fieldClass)) {
			m.invoke(t, DateUtils.parse(format, fieldValue));
		} else {
			m.invoke(t, fieldValue);
		}
	}

	/*public static void main(String[] args) throws Exception {
		List<User> readXls2List = ExcelUtils.readXls2List(//
				User.class, //
				new Class[]{String.class, int.class, double.class, Date.class}, //
				new String[]{"name", "age", "salary", "birth"}, //
				"D://user.xlsx", //
				"yyyy/MM/dd"//
		);
		readXls2List.stream().forEach(t -> {
			System.out.println(t);
		});
	}*/

}

class User {
	private String name;
	private int age;
	private double salary;
	private Date birth;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", age=" + age + ", salary=" + salary + ", birth=" + birth + "]";
	}

}