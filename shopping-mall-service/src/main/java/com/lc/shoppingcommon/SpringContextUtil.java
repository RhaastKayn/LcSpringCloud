package com.lc.shoppingcommon;

import com.google.common.base.CaseFormat;
import com.lc.shoppingcommon.util.MyMapper;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("springContextUtil")
public class SpringContextUtil implements ApplicationContextAware {
	public static ApplicationContext appCtx;

	public static Optional<Object> getServiceByTableName(String tableName) {

		String beamName = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, tableName) + "Service";

		try {
			Object ctxBean = appCtx.getBean(beamName);
			return Optional.of(ctxBean);
		} catch (BeansException ignored) {
			return  Optional.empty();
		}

	}

	/**
	 * 得到这个类的Mapper
	 * @param entityName 如DemoChild
	 * @return MyMapper<demoChild>
	 */
	@SuppressWarnings("rawtypes")
	public static MyMapper getMapper(String entityName) {
		String name = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL,
				entityName.replace("Entity", "")) + "Mapper";
		return (MyMapper)appCtx.getBean(name);
	}


	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		appCtx = applicationContext;
	}

	public static ApplicationContext getApplicationContext() {
		return appCtx;
	}
}
