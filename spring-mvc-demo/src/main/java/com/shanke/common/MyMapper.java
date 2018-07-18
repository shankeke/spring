package com.shanke.common;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 描述 : MyMapper. <br>
 * <p>
 * 
 * Copyright (c) 2016 优识云创(YSYC)
 * 
 * @author Uknower-yjw
 * @date 2016-8-22 下午2:17:46
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
