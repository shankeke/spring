package com.shanke.common.conf;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.apache.commons.configuration.CombinedConfiguration;
import org.apache.commons.configuration.PropertyConverter;
import org.apache.commons.configuration.tree.UnionCombiner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 * Description: 
 *      配置信息管理
 * </pre>
 * 
 * @author yjw
 * @date 2015年11月26日 下午8:32:56
 */
public class Configuration extends CombinedConfiguration implements
		Serializable {
	private static final long serialVersionUID = 8099999470975138244L;
	private static final Logger logger = LoggerFactory
			.getLogger(Configuration.class);

	/**
	 * 单例模式，添加双重校验锁 volatile
	 */
	private volatile static Configuration conf;

	/**
	 * <pre>
	 * 通过同步锁机制获取当前的实例
	 * </pre>
	 * 
	 * @return 配置信息实例
	 */
	public static Configuration getInstance() {
		if (conf == null) {
			synchronized (Configuration.class) {
				CustomConfigurationBuilder builder = null;
				if (conf == null) {
					try {
						builder = new CustomConfigurationBuilder(
								"conf/config/config.xml");
						conf = builder.getConfiguration();
						logger.error("加载配置信息成功");
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("加载配置信息失败", e);
					}
				}
			}
		}
		return conf;
	}

	protected Configuration(UnionCombiner unionCombiner) {
		super(unionCombiner);
	}

	protected Configuration() {
		super();
	}

	/**
	 * Returns the interpolated value. Non String values are returned without
	 * change.
	 * 
	 * @param value
	 *            the value to interpolate
	 * 
	 * @return returns the value with variables substituted
	 */
	protected Object interpolate(Object value) {
		return PropertyConverter.interpolate(value, this);
	}

	/**
	 * Set the <code>value</code> of the <code>name</code> property. If
	 * <code>name</code> is deprecated or there is a deprecated name associated
	 * to it, it sets the value to both names.
	 * 
	 * @param name
	 *            property name.
	 * @param value
	 *            property value.
	 */
	public void set(String key, String value) {
		addProperty(key, value);
	}

	/**
	 * Sets a property if it is currently unset.
	 * 
	 * @param key
	 *            the property name
	 * @param value
	 *            the new value
	 */
	public synchronized void setIfUnset(String key, String value) {
		if (get(key) == null) {
			set(key, value);
		}
	}

	/**
	 * Set the value of the <code>name</code> property to an <code>int</code>.
	 * 
	 * @param name
	 *            property name.
	 * @param value
	 *            <code>int</code> value of the property.
	 */
	public void setInt(String name, int value) {
		set(name, Integer.toString(value));
	}

	/**
	 * Set the value of the <code>name</code> property to a <code>long</code>.
	 * 
	 * @param name
	 *            property name.
	 * @param value
	 *            <code>long</code> value of the property.
	 */
	public void setLong(String name, long value) {
		set(name, Long.toString(value));
	}

	/**
	 * Set the value of the <code>name</code> property to a <code>float</code>.
	 * 
	 * @param name
	 *            property name.
	 * @param value
	 *            property value.
	 */
	public void setFloat(String name, float value) {
		set(name, Float.toString(value));
	}

	/**
	 * Set the value of the <code>name</code> property to a <code>double</code>.
	 * 
	 * @param name
	 *            property name.
	 * @param value
	 *            property value.
	 */
	public void setDouble(String name, double value) {
		set(name, Double.toString(value));
	}

	/**
	 * Set the value of the <code>name</code> property to a <code>boolean</code>
	 * .
	 * 
	 * @param name
	 *            property name.
	 * @param value
	 *            <code>boolean</code> value of the property.
	 */
	public void setBoolean(String name, boolean value) {
		set(name, Boolean.toString(value));
	}

	/**
	 * Set the value of <code>name</code> to the given time duration. This is
	 * equivalent to <code>set(&lt;name&gt;, value + &lt;time suffix&gt;)</code>
	 * .
	 * 
	 * @param name
	 *            Property name
	 * @param value
	 *            Time duration
	 * @param unit
	 *            Unit of time
	 */
	public void setTimeDuration(String name, long value, TimeUnit unit) {
		set(name, value + ParsedTimeDuration.unitFor(unit).suffix());
	}

	/**
	 * Set the given property to <code>Pattern</code>. If the pattern is passed
	 * as null, sets the empty pattern which results in further calls to
	 * getPattern(...) returning the default value.
	 * 
	 * @param name
	 *            property name
	 * @param pattern
	 *            new value
	 */
	public void setPattern(String name, Pattern pattern) {
		if (null == pattern) {
			set(name, null);
		} else {
			set(name, pattern.pattern());
		}
	}

	/**
	 * Set the array of string values for the <code>name</code> property as as
	 * comma delimited values.
	 * 
	 * @param name
	 *            property name.
	 * @param values
	 *            The values
	 */
	public void setStrings(String name, String... values) {
		set(name, StringHandle.arrayToString(values));
	}

	/**
	 * Set the value of the <code>name</code> property to the given type. This
	 * is equivalent to <code>set(&lt;name&gt;, value.toString())</code>.
	 * 
	 * @param name
	 *            property name
	 * @param value
	 *            new value
	 */
	public <T extends Enum<T>> void setEnum(String name, T value) {
		set(name, value.toString());
	}

	/**
	 * Set the value of the <code>name</code> property to the name of a
	 * <code>theClass</code> implementing the given interface <code>xface</code>
	 * .
	 * 
	 * An exception is thrown if <code>theClass</code> does not implement the
	 * interface <code>xface</code>.
	 * 
	 * @param name
	 *            property name.
	 * @param theClass
	 *            property value.
	 * @param xface
	 *            the interface implemented by the named class.
	 */
	public void setClass(String name, Class<?> theClass, Class<?> xface) {
		if (!xface.isAssignableFrom(theClass))
			throw new RuntimeException(theClass + " not " + xface.getName());
		set(name, theClass.getName());
	}

	/**
	 * <pre>
	 * Description: 
	 *        获取字符串，默认方法
	 * </pre>
	 * 
	 * @author shanke
	 * @date 2015年11月29日 下午1:15:32
	 * @param key
	 *            属性名称
	 * @return
	 */
	public String get(String key) {
		return getString(key);
	}

	/**
	 * <pre>
	 * Description: 
	 *         获取字符串，默认方法
	 * </pre>
	 * 
	 * @author shanke
	 * @date 2015年11月29日 下午1:17:00
	 * @param key
	 *            属性名称
	 * @param defaultValue
	 *            默认值
	 * @return
	 */
	public String get(String key, String defaultValue) {
		return getString(key, defaultValue);
	}

	/**
	 * <pre>
	 * Description: 
	 *       获取trim后的字符串
	 * </pre>
	 * 
	 * @author shanke
	 * @date 2015年11月29日 下午1:20:03
	 * @param key
	 *            属性名称
	 * @return
	 */
	public String getTrimmed(String key) {
		String value = get(key);
		if (null == value) {
			return null;
		} else {
			return value.trim();
		}
	}

	/**
	 * <pre>
	 * Description: 
	 *       获取trim后的字符串
	 * </pre>
	 * 
	 * @author shanke
	 * @date 2015年11月29日 下午1:20:03
	 * @param key
	 *            属性名称
	 * @param defaultValue
	 *            默认值
	 * @return
	 */
	public String getTrimmed(String name, String defaultValue) {
		String ret = getTrimmed(name);
		return ret == null ? defaultValue : ret;
	}

	/**
	 * Get the comma delimited values of the <code>name</code> property as an
	 * array of <code>String</code>s. If no such property is specified then
	 * <code>null</code> is returned.
	 * 
	 * @param name
	 *            property name.
	 * @return property value as an array of <code>String</code>s, or
	 *         <code>null</code>.
	 */
	public String[] getStrings(String name) {
		String valueString = get(name);
		return StringHandle.getStrings(valueString);
	}

	/**
	 * Get the comma delimited values of the <code>name</code> property as an
	 * array of <code>String</code>s. If no such property is specified then
	 * default value is returned.
	 * 
	 * @param name
	 *            property name.
	 * @param defaultValue
	 *            The default value
	 * @return property value as an array of <code>String</code>s, or default
	 *         value.
	 */
	public String[] getStrings(String name, String... defaultValue) {
		String valueString = get(name);
		if (valueString == null) {
			return defaultValue;
		} else {
			return StringHandle.getStrings(valueString);
		}
	}

	/**
	 * Get the comma delimited values of the <code>name</code> property as a
	 * collection of <code>String</code>s. If no such property is specified then
	 * empty collection is returned.
	 * <p>
	 * This is an optimized version of {@link #getStrings(String)}
	 * 
	 * @param name
	 *            property name.
	 * @return property value as a collection of <code>String</code>s.
	 */
	public Collection<String> getStringCollection(String name) {
		String valueString = get(name);
		return StringHandle.getStringCollection(valueString);
	}

	/**
	 * Get the comma delimited values of the <code>name</code> property as a
	 * collection of <code>String</code>s, trimmed of the leading and trailing
	 * whitespace. If no such property is specified then empty
	 * <code>Collection</code> is returned.
	 * 
	 * @param name
	 *            property name.
	 * @return property value as a collection of <code>String</code>s, or empty
	 *         <code>Collection</code>
	 */
	public Collection<String> getTrimmedStringCollection(String name) {
		String valueString = get(name);
		if (null == valueString) {
			Collection<String> empty = new ArrayList<String>();
			return empty;
		}
		return StringHandle.getTrimmedStringCollection(valueString);
	}

	/**
	 * Get the comma delimited values of the <code>name</code> property as an
	 * array of <code>String</code>s, trimmed of the leading and trailing
	 * whitespace. If no such property is specified then an empty array is
	 * returned.
	 * 
	 * @param name
	 *            property name.
	 * @return property value as an array of trimmed <code>String</code>s, or
	 *         empty array.
	 */
	public String[] getTrimmedStrings(String name) {
		String valueString = get(name);
		return StringHandle.getTrimmedStrings(valueString);
	}

	/**
	 * Get the comma delimited values of the <code>name</code> property as an
	 * array of <code>String</code>s, trimmed of the leading and trailing
	 * whitespace. If no such property is specified then default value is
	 * returned.
	 * 
	 * @param name
	 *            property name.
	 * @param defaultValue
	 *            The default value
	 * @return property value as an array of trimmed <code>String</code>s, or
	 *         default value.
	 */
	public String[] getTrimmedStrings(String name, String... defaultValue) {
		String valueString = get(name);
		if (null == valueString) {
			return defaultValue;
		} else {
			return StringHandle.getTrimmedStrings(valueString);
		}
	}

	/**
	 * Load a class by name.
	 * 
	 * @param name
	 *            the class name.
	 * @return the class object.
	 * @throws ClassNotFoundException
	 *             if the class is not found.
	 */
	public Class<?> getClassByName(String name) throws ClassNotFoundException {
		Class<?> ret = getClassByNameOrNull(get(name));
		if (ret == null) {
			throw new ClassNotFoundException("Class " + name + " not found");
		}
		return ret;
	}

	/**
	 * Load a class by name, returning null rather than throwing an exception if
	 * it couldn't be loaded. This is to avoid the overhead of creating an
	 * exception.
	 * 
	 * @param name
	 *            the class name
	 * @return the class object, or null if it could not be found.
	 */
	public Class<?> getClassByNameOrNull(String className) {
		try {
			return Class.forName(className);
		} catch (ClassNotFoundException e) {
			return null;
		}
	}

	/**
	 * Get the value of the <code>name</code> property as an array of
	 * <code>Class</code>. The value of the property specifies a list of comma
	 * separated class names. If no such property is specified, then
	 * <code>defaultValue</code> is returned.
	 * 
	 * @param name
	 *            the property name.
	 * @param defaultValue
	 *            default value.
	 * @return property value as a <code>Class[]</code>, or
	 *         <code>defaultValue</code>.
	 */
	public Class<?>[] getClasses(String name, Class<?>... defaultValue) {
		String[] classnames = getTrimmedStrings(name);
		if (classnames == null)
			return defaultValue;
		try {
			Class<?>[] classes = new Class<?>[classnames.length];
			for (int i = 0; i < classnames.length; i++) {
				classes[i] = getClassByName(classnames[i]);
			}
			return classes;
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Get the value of the <code>name</code> property as a <code>Class</code>.
	 * If no such property is specified, then <code>defaultValue</code> is
	 * returned.
	 * 
	 * @param name
	 *            the class name.
	 * @param defaultValue
	 *            default value.
	 * @return property value as a <code>Class</code>, or
	 *         <code>defaultValue</code>.
	 */
	public Class<?> getClass(String name, Class<?> defaultValue) {
		String valueString = getTrimmed(name);
		if (valueString == null)
			return defaultValue;
		try {
			return getClassByName(valueString);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Get the value of the <code>name</code> property as a <code>Class</code>
	 * implementing the interface specified by <code>xface</code>.
	 * 
	 * If no such property is specified, then <code>defaultValue</code> is
	 * returned.
	 * 
	 * An exception is thrown if the returned class does not implement the named
	 * interface.
	 * 
	 * @param name
	 *            the class name.
	 * @param defaultValue
	 *            default value.
	 * @param xface
	 *            the interface implemented by the named class.
	 * @return property value as a <code>Class</code>, or
	 *         <code>defaultValue</code>.
	 */
	public <U> Class<? extends U> getClass(String name,
			Class<? extends U> defaultValue, Class<U> xface) {
		try {
			Class<?> theClass = getClass(name, defaultValue);
			if (theClass != null && !xface.isAssignableFrom(theClass))
				throw new RuntimeException(theClass + " not " + xface.getName());
			else if (theClass != null)
				return theClass.asSubclass(xface);
			else
				return null;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Get the value of the <code>name</code> property as a <code>List</code> of
	 * objects implementing the interface specified by <code>xface</code>.
	 * 
	 * An exception is thrown if any of the classes does not exist, or if it
	 * does not implement the named interface.
	 * 
	 * @param name
	 *            the property name.
	 * @param xface
	 *            the interface implemented by the classes named by
	 *            <code>name</code>.
	 * @return a <code>List</code> of objects implementing <code>xface</code>.
	 */
	@SuppressWarnings("unchecked")
	public <U> List<U> getInstances(String name, Class<U> xface) {
		List<U> ret = new ArrayList<U>();
		Class<?>[] classes = getClasses(name);
		for (Class<?> cl : classes) {
			if (!xface.isAssignableFrom(cl)) {
				throw new RuntimeException(cl + " does not implement " + xface);
			}
			ret.add((U) Reflection.newInstance(cl));
		}
		return ret;
	}

	/**
	 * Return value matching this enumerated type.
	 * 
	 * @param name
	 *            Property name
	 * @param defaultValue
	 *            Value returned if no mapping exists
	 * @throws IllegalArgumentException
	 *             If mapping is illegal for the type provided
	 */
	public <T extends Enum<T>> T getEnum(String name, T defaultValue) {
		final String val = get(name);
		return null == val ? defaultValue : Enum.valueOf(
				defaultValue.getDeclaringClass(), val);
	}

	/**
	 * Get a local file name under a directory named in <i>dirsProp</i> with the
	 * given <i>path</i>. If <i>dirsProp</i> contains multiple directories, then
	 * one is chosen based on <i>path</i>'s hash code. If the selected directory
	 * does not exist, an attempt is made to create it.
	 * 
	 * @param dirsProp
	 *            directory in which to locate the file.
	 * @param path
	 *            file-path.
	 * @return local file under the directory with the given path.
	 */
	public File getFile(String dirsProp, String path) throws IOException {
		String[] dirs = getTrimmedStrings(dirsProp);
		int hashCode = path.hashCode();
		for (int i = 0; i < dirs.length; i++) { // try each local dir
			int index = (hashCode + i & Integer.MAX_VALUE) % dirs.length;
			File file = new File(dirs[index], path);
			File dir = file.getParentFile();
			if (dir.exists() || dir.mkdirs()) {
				return file;
			}
		}
		throw new IOException("No valid local directories in property: "
				+ dirsProp);
	}

	/**
	 * A class that represents a set of positive integer ranges. It parses
	 * strings of the form: "2-3,5,7-" where ranges are separated by comma and
	 * the lower/upper bounds are separated by dash. Either the lower or upper
	 * bound may be omitted meaning all values up to or over. So the string
	 * above means 2, 3, 5, and 7, 8, 9, ...
	 */
	public static class IntegerRanges implements Iterable<Integer> {
		private static class Range {
			int start;
			int end;
		}

		private static class RangeNumberIterator implements Iterator<Integer> {
			Iterator<Range> internal;
			int at;
			int end;

			public RangeNumberIterator(List<Range> ranges) {
				if (ranges != null) {
					internal = ranges.iterator();
				}
				at = -1;
				end = -2;
			}

			@Override
			public boolean hasNext() {
				if (at <= end) {
					return true;
				} else if (internal != null) {
					return internal.hasNext();
				}
				return false;
			}

			@Override
			public Integer next() {
				if (at <= end) {
					at++;
					return at - 1;
				} else if (internal != null) {
					Range found = internal.next();
					if (found != null) {
						at = found.start;
						end = found.end;
						at++;
						return at - 1;
					}
				}
				return null;
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};

		List<Range> ranges = new ArrayList<Range>();

		public IntegerRanges() {
		}

		public IntegerRanges(String newValue) {
			StringTokenizer itr = new StringTokenizer(newValue, ",");
			while (itr.hasMoreTokens()) {
				String rng = itr.nextToken().trim();
				String[] parts = rng.split("-", 3);
				if (parts.length < 1 || parts.length > 2) {
					throw new IllegalArgumentException(
							"integer range badly formed: " + rng);
				}
				Range r = new Range();
				r.start = convertToInt(parts[0], 0);
				if (parts.length == 2) {
					r.end = convertToInt(parts[1], Integer.MAX_VALUE);
				} else {
					r.end = r.start;
				}
				if (r.start > r.end) {
					throw new IllegalArgumentException("IntegerRange from "
							+ r.start + " to " + r.end + " is invalid");
				}
				ranges.add(r);
			}
		}

		/**
		 * Convert a string to an int treating empty strings as the default
		 * value.
		 * 
		 * @param value
		 *            the string value
		 * @param defaultValue
		 *            the value for if the string is empty
		 * @return the desired integer
		 */
		private static int convertToInt(String value, int defaultValue) {
			String trim = value.trim();
			if (trim.length() == 0) {
				return defaultValue;
			}
			return Integer.parseInt(trim);
		}

		/**
		 * Is the given value in the set of ranges
		 * 
		 * @param value
		 *            the value to check
		 * @return is the value in the ranges?
		 */
		public boolean isIncluded(int value) {
			for (Range r : ranges) {
				if (r.start <= value && value <= r.end) {
					return true;
				}
			}
			return false;
		}

		/**
		 * @return true if there are no values in this range, else false.
		 */
		public boolean isEmpty() {
			return ranges == null || ranges.isEmpty();
		}

		@Override
		public String toString() {
			StringBuilder result = new StringBuilder();
			boolean first = true;
			for (Range r : ranges) {
				if (first) {
					first = false;
				} else {
					result.append(',');
				}
				result.append(r.start);
				result.append('-');
				result.append(r.end);
			}
			return result.toString();
		}

		@Override
		public Iterator<Integer> iterator() {
			return new RangeNumberIterator(ranges);
		}

	}

	/**
	 * Parse the given attribute as a set of integer ranges
	 * 
	 * @param name
	 *            the attribute name
	 * @param defaultValue
	 *            the default value if it is not set
	 * @return a new set of ranges from the configured value
	 */
	public IntegerRanges getRange(String name, String defaultValue) {
		return new IntegerRanges(get(name, defaultValue));
	}

	/**
	 * Get the value of the <code>name</code> property as a <code>Pattern</code>
	 * . If no such property is specified, or if the specified value is not a
	 * valid <code>Pattern</code>, then <code>DefaultValue</code> is returned.
	 * 
	 * @param name
	 *            property name
	 * @param defaultValue
	 *            default value
	 * @return property value as a compiled Pattern, or defaultValue
	 */
	public Pattern getPattern(String name, Pattern defaultValue) {
		String valString = get(name);
		if (null == valString || valString.isEmpty()) {
			return defaultValue;
		}
		try {
			return Pattern.compile(valString);
		} catch (PatternSyntaxException pse) {
			logger.warn("Regular expression '" + valString + "' for property '"
					+ name + "' not valid. Using default", pse);
			return defaultValue;
		}
	}

	enum ParsedTimeDuration {
		NS {
			TimeUnit unit() {
				return TimeUnit.NANOSECONDS;
			}

			String suffix() {
				return "ns";
			}
		},
		US {
			TimeUnit unit() {
				return TimeUnit.MICROSECONDS;
			}

			String suffix() {
				return "us";
			}
		},
		MS {
			TimeUnit unit() {
				return TimeUnit.MILLISECONDS;
			}

			String suffix() {
				return "ms";
			}
		},
		S {
			TimeUnit unit() {
				return TimeUnit.SECONDS;
			}

			String suffix() {
				return "s";
			}
		},
		M {
			TimeUnit unit() {
				return TimeUnit.MINUTES;
			}

			String suffix() {
				return "m";
			}
		},
		H {
			TimeUnit unit() {
				return TimeUnit.HOURS;
			}

			String suffix() {
				return "h";
			}
		},
		D {
			TimeUnit unit() {
				return TimeUnit.DAYS;
			}

			String suffix() {
				return "d";
			}
		};
		abstract TimeUnit unit();

		abstract String suffix();

		static ParsedTimeDuration unitFor(String s) {
			for (ParsedTimeDuration ptd : values()) {
				// iteration order is in decl order, so SECONDS matched last
				if (s.endsWith(ptd.suffix())) {
					return ptd;
				}
			}
			return null;
		}

		static ParsedTimeDuration unitFor(TimeUnit unit) {
			for (ParsedTimeDuration ptd : values()) {
				if (ptd.unit() == unit) {
					return ptd;
				}
			}
			return null;
		}
	}

	/**
	 * Return time duration in the given time unit. Valid units are encoded in
	 * properties as suffixes: nanoseconds (ns), microseconds (us), milliseconds
	 * (ms), seconds (s), minutes (m), hours (h), and days (d).
	 * 
	 * @param name
	 *            Property name
	 * @param defaultValue
	 *            Value returned if no mapping exists.
	 * @param unit
	 *            Unit to convert the stored property, if it exists.
	 * @throws NumberFormatException
	 *             If the property stripped of its unit is not a number
	 */
	public long getTimeDuration(String name, long defaultValue, TimeUnit unit) {
		String vStr = get(name);
		if (null == vStr) {
			return defaultValue;
		}
		vStr = vStr.trim();
		ParsedTimeDuration vUnit = ParsedTimeDuration.unitFor(vStr);
		if (null == vUnit) {
			logger.warn("No unit for " + name + "(" + vStr + ") assuming "
					+ unit);
			vUnit = ParsedTimeDuration.unitFor(unit);
		} else {
			vStr = vStr.substring(0, vStr.lastIndexOf(vUnit.suffix()));
		}
		return unit.convert(Long.parseLong(vStr), vUnit.unit());
	}

	/**
	 * <pre>
	 * 返回一个数组
	 * </pre>
	 * 
	 * @param name
	 *            key
	 * @param defaultValue
	 *            默认值
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Object[] getArray(String name, Object[] defaultValue) {
		List list = getList(name, new ArrayList<Object>());
		return list != null && list.size() > 0 ? list.toArray() : defaultValue;
	}

	/**
	 * Get the value of the <code>byte</code> property as a set of
	 * comma-delimited <code>short</code> values.
	 * 
	 * If no such property exists, an empty array is returned.
	 * 
	 * @param name
	 *            property name
	 * @return property value interpreted as an array of comma-delimited
	 *         <code>byte</code> values
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public byte[] getBytes(String name) {
		List list = getList(name, new ArrayList());
		Object[] strings = list.toArray();
		byte[] bytes = new byte[strings.length];
		for (int i = 0; i < strings.length; i++) {
			bytes[i] = Byte.parseByte(strings[i].toString());
		}
		return bytes;
	}

	/**
	 * Get the value of the <code>name</code> property as a set of
	 * comma-delimited <code>short</code> values.
	 * 
	 * If no such property exists, an empty array is returned.
	 * 
	 * @param name
	 *            property name
	 * @return property value interpreted as an array of comma-delimited
	 *         <code>short</code> values
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public short[] getShorts(String name) {
		List list = getList(name, new ArrayList());
		Object[] strings = list.toArray();
		short[] shorts = new short[strings.length];
		for (int i = 0; i < strings.length; i++) {
			shorts[i] = Short.parseShort(strings[i].toString());
		}
		return shorts;
	}

	/**
	 * Get the value of the <code>name</code> property as a set of
	 * comma-delimited <code>int</code> values.
	 * 
	 * If no such property exists, an empty array is returned.
	 * 
	 * @param name
	 *            property name
	 * @return property value interpreted as an array of comma-delimited
	 *         <code>int</code> values
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public int[] getInts(String name) {
		List list = getList(name, new ArrayList());
		Object[] array = list.toArray();
		int[] ints = new int[array.length];
		for (int i = 0; i < array.length; i++) {
			ints[i] = Integer.parseInt(array[i].toString());
		}
		return ints;
	}

	/**
	 * Get the value of the <code>name</code> property as a set of
	 * comma-delimited <code>long</code> values.
	 * 
	 * If no such property exists, an empty array is returned.
	 * 
	 * @param key
	 *            property name
	 * @return property value interpreted as an array of comma-delimited
	 *         <code>long</code> values
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public long[] getLongs(String name) {
		List list = getList(name, new ArrayList());
		Object[] strings = list.toArray();
		long[] longs = new long[strings.length];
		for (int i = 0; i < strings.length; i++) {
			longs[i] = Long.parseLong(strings[i].toString());
		}
		return longs;
	}

	/**
	 * Get the value of the <code>name</code> property as a set of
	 * comma-delimited <code>float</code> values.
	 * 
	 * If no such property exists, an empty array is returned.
	 * 
	 * @param key
	 *            property name
	 * @return property value interpreted as an array of comma-delimited
	 *         <code>float</code> values
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public float[] getFloats(String name) {
		List list = getList(name, new ArrayList());
		Object[] strings = list.toArray();
		float[] floats = new float[strings.length];
		for (int i = 0; i < strings.length; i++) {
			floats[i] = Float.parseFloat(strings[i].toString());
		}
		return floats;
	}

	/**
	 * Get the value of the <code>name</code> property as a set of
	 * comma-delimited <code>double</code> values.
	 * 
	 * If no such property exists, an empty array is returned.
	 * 
	 * @param key
	 *            property name
	 * @return property value interpreted as an array of comma-delimited
	 *         <code>double</code> values
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public double[] getDoubles(String name) {
		List list = getList(name, new ArrayList());
		Object[] strings = list.toArray();
		double[] doubles = new double[strings.length];
		for (int i = 0; i < strings.length; i++) {
			doubles[i] = Double.parseDouble(strings[i].toString());
		}
		return doubles;
	}

	/**
	 * Get the value of the <code>name</code> property as a set of
	 * comma-delimited <code>boolean</code> values.
	 * 
	 * If no such property exists, an empty array is returned.
	 * 
	 * @param key
	 *            property name
	 * @return property value interpreted as an array of comma-delimited
	 *         <code>boolean</code> values
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean[] getBooleans(String name) {
		List list = getList(name, new ArrayList());
		Object[] strings = list.toArray();
		boolean[] booleans = new boolean[strings.length];
		for (int i = 0; i < strings.length; i++) {
			booleans[i] = Boolean.parseBoolean(strings[i].toString());
		}
		return booleans;
	}

	/**
	 * 描述 : 获取所有的属性值. <br>
	 * <p>
	 * 
	 * @author Uknower-yjw
	 * @date 2016年8月4日 下午11:03:30
	 * @return
	 */
	protected synchronized Properties getProps() {
		Properties properties = new Properties();
		Iterator<String> keys = getKeys();
		String key = null;
		while (keys.hasNext()) {
			key = keys.next();
			properties.setProperty(key, getString(key));
		}
		return properties;
	}

	public static void main(String[] args) throws ClassNotFoundException {
		Configuration conf = Configuration.getInstance();

		Iterator<String> keys = conf.getKeys();
		String key = "";
		while (keys.hasNext()) {
			key = (String) keys.next();
			System.out.println(key + ":" + conf.getString(key));
		}
		// conf.setInt("int", 2);s
	}
}