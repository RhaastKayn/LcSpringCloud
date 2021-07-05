package com.lc.shoppingcommon.util;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

//该接口不能被扫描到
public interface MyMapper<T> extends Mapper<T>,MySqlMapper<T> {
}