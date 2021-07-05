package com.lc.service;

import com.lc.shoppingcommon.SpringContextUtil;
import com.lc.shoppingcommon.pojo.BaseEntity;
import com.lc.shoppingcommon.util.MyMapper;
import org.omg.CORBA.SystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * AbstractMybatisService
 */
@SuppressWarnings("unchecked")
public class AbstractMybatisService<T extends BaseEntity> {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired(required = true)
	protected MyMapper<T> mapper;

	protected String group;

	protected Class<T> clazz;

	@Autowired
	SpringContextUtil springContextUtil;

	public AbstractMybatisService() {
		Type genType = this.getClass().getGenericSuperclass();
		if (genType instanceof ParameterizedType) {
			Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
			if (params != null && params.length > 0) {
				clazz = (Class<T>) params[0];
			}
		}
	}

	/**
	 * 根据id查询
	 */
	public T queryById(Long id) {
		Example example = new Example(clazz);
		example.createCriteria().andNotEqualTo("dataStatus", 0).andEqualTo("id", id);
		T t = mapper.selectOneByExample(example);
		return t;
	}

	/**
	 * 根据Property查询单条数据
	 */
	public T queryByProperty(String propertyName, Object value) {
		Example example = new Example(clazz);
		example.createCriteria().andEqualTo(propertyName, value);
		T t = mapper.selectOneByExample(example);
		return t;
	}

	/**
	 * 根据多个Property查询单条数据
	 */
	public T queryByPropertys(Map<String, Object> propertys) {
		Example example = new Example(clazz);
		Example.Criteria criteria = example.createCriteria();
		propertys.forEach((k, v) -> criteria.andEqualTo(k, v));
		T t = mapper.selectOneByExample(example);
		return t;
	}

	/**
	 * 根据Property查询所有
	 */
	public List<T> queryAllByProperty(String propertyName, Object value) {
		Example example = new Example(clazz);
		example.createCriteria().andEqualTo(propertyName, value);
		List<T> t = mapper.selectByExample(example);
		return t;
	}

	/**
	 * 根据多个Property查询所有
	 */
	public List<T> queryAllByPropertys(Map<String, Object> propertys) {
		Example example = new Example(clazz);
		Example.Criteria criteria = example.createCriteria();
		propertys.forEach((k, v) -> criteria.andEqualTo(k, v));
		List<T> t = mapper.selectByExample(example);
		return t;
	}

	/**
	 * 删除功能业务验证
	 *
	 * @param entity
	 * @throws BusinessException
	 * @throws SystemException
	 */
//	protected void validateDelete(T entity) throws BusinessException, SystemException {
//		if (entity == null) {
//			throw new BusinessException("object does not exist");
//		}
//	}
}
