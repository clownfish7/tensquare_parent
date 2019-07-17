package com.tensquare.manager.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yzy
 * @classname ManagerFilter
 * @description TODO
 * @create 2019-07-16 19:36
 */
@Component
public class ManagerFilter extends ZuulFilter {

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 在请求前 pre 或者 请求后 post 执行
     * pre    ：可以在请求被路由之前调用
     * route  ：在路由请求时候被调用
     * post   ：在route和error过滤器之后被调用
     * error  ：处理请求时发生错误时被调用
     *
     * @return
     */
    @Override
    public String filterType() {
        //before 前置过滤器   post -> after
        return "pre";
    }

    /**
     * 多个过滤器的执行顺序，数字越小，表示越先执行
     *
     * @return
     */
    @Override
    public int filterOrder() {
        //优先级为0，数字越大，优先级越低
        return 0;
    }

    /**
     * 当前过滤器是否开启 true表示开启
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器内执行的操作 return 任何 Object 的值都表示继续执行
     * setSendZuulResponse(false) 表示不在继续执行
     *
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        System.out.println("经过过滤器了~");
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();

        if (request.getMethod().equals("OPTIONS")) {
            return null;
        }

        if (request.getRequestURL().indexOf("login") > 0) {
            return null;
        }

        String header = request.getHeader("Authorization");
        if (!StringUtils.isEmpty(header)) {
            if (header.startsWith("Bearer ")) {
                String token = header.substring(7);
                try {
                    Claims claims = jwtUtil.parseJWT(token);
                    String roles = (String) claims.get("roles");
                    if (roles.equals("admin")) {
                        requestContext.addZuulRequestHeader("Authorization", header);
                        return null;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    //终止运行
                    requestContext.setSendZuulResponse(false);
                }
            }
        }
        //终止运行
        requestContext.setSendZuulResponse(false);
        //http状态码
        requestContext.setResponseStatusCode(403);
        requestContext.setResponseBody("权限不足，拒绝访问！");
        requestContext.getResponse().setContentType("text/html;charset=utf-8");
        return null;
    }
}
