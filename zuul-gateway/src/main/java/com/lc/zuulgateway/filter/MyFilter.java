package com.lc.zuulgateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 自定义Zuul过滤器
 * @author 刘晨
 * @create 2021/5/8 0008
 * @since 1.0.0
 */
@Component
@Slf4j
public class MyFilter extends ZuulFilter {
    /**
     * 对应Zuul的四个声明周期，pre(路由之前)，post(路由之后)，route(路由之时)，error(发送错误调用)
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 对应Zuul过滤器的优先级，数字越小，优先级越高
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 返回true表示执行过滤器的run方法，false则表示不执行
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器的过滤逻辑，这里我们获取一些请求的信息
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String host = request.getRemoteHost();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        log.info("请求URI：{}，HTTP Method：{}，请求IP：{}", uri, method, host);
        return null;
    }
}
