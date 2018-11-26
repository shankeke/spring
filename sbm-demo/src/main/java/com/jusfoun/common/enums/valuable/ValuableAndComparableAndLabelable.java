package com.jusfoun.common.enums.valuable;

/**
 * 说明：可取常量且可比较大小可取标签常量值枚举接口. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2018年1月17日 下午3:32:53
 */
public interface ValuableAndComparableAndLabelable<T extends Comparable<T>> extends ValuableAndComparable<T>, Labelable {
}
