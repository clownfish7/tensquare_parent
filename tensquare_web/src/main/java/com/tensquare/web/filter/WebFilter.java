package com.tensquare.web.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yzy
 * @classname WebFilter
 * @description TODO
 * @create 2019-07-16 19:57
 */
@Component
public class WebFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        //得到 request 上下文
        RequestContext requestContext = RequestContext.getCurrentContext();
        //得到 request 域对象
        HttpServletRequest request = requestContext.getRequest();
        //得到 header
        String header = request.getHeader("Authorization");
        //判断是否有头信息
        if (!StringUtils.isEmpty(header)) {
            //把头信息继续下传
            requestContext.addZuulRequestHeader("Authorization", header);
        }
        return null;
    }
}
