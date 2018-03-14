package com.guagua.filter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ride
 * @date 18-3-12 下午7:47
 *
 * 解决编码和快于问题
 */
@WebFilter(filterName = "headerFilter", value = {"/*"},
        initParams = {@WebInitParam(name = "encoding", value = "utf-8")})
public class HeaderFilter implements Filter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static String encoding;

    public void init(FilterConfig filterConfig) throws ServletException {
        encoding = filterConfig.getInitParameter("encoding");


        logger.info("#####################\n" +
                "编码和跨域问题\n" +
                "#################################################\n");
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        logger.info("#############in header filter doFilter() ################");

        // 顺便解决跨域问题
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        httpServletResponse.setContentType("text/html;charset=UTF-8");
//        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.setHeader("Access-Control-Allow-Origin", httpServletRequest.getHeader("Origin"));

        httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, OPTIONS");
        httpServletResponse.setHeader("Access-Control-Max-Age", "0");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token, Access-Control-Allow-Origin");
        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpServletResponse.setHeader("XDomainRequestAllowed", "1");

        servletRequest.setCharacterEncoding(encoding);
        servletResponse.setCharacterEncoding(encoding);

        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {

    }
}
