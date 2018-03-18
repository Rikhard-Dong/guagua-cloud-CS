package com.guagua.filter;


import com.guagua.bean.dto.ResultDTO;
import com.guagua.enums.DataDictionary;
import com.guagua.filter.utils.PermissionUtils;
import com.guagua.utils.JWTUtils;
import com.guagua.utils.JacksonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * @author ride
 * @date 18-3-13 下午8:39
 * <p>
 * 进行权限过滤
 */
public class PermissionFilter implements Filter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        logger.info("+++++++++++++++++++++++++permission filter++++++++++++++++++++++++++++++++");

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setHeader("Content-type", "text/html;charset=UTF-8");

        ResultDTO resultDto = new ResultDTO();

        // 获取前端提交的令牌
        String jwt = request.getHeader("authorization");

        // 解析令牌的数据
        logger.info("##### token ====> {} ####", jwt);
        Map<String, Object> claims = JWTUtils.verifyJWT(jwt);
        if (claims == null) {
            PrintWriter out = response.getWriter();

            // 令牌解析失败
            resultDto.setCodeAndMsg(DataDictionary.AUTHORIZATION_FAIL);
            out.print(JacksonUtils.toJSon(resultDto));
            out.flush();
            out.close();
            return;
        }

        Integer userId = (Integer) claims.get("userId");
        if (userId == null) {
            PrintWriter out = response.getWriter();

            // 无效令牌
            resultDto.setCodeAndMsg(DataDictionary.AUTHORIZATION_FAIL);
            out.print(JacksonUtils.toJSon(resultDto));
            out.flush();
            out.close();
            return;
        }

        if (!PermissionUtils.isUserExists(userId)) {
            PrintWriter out = response.getWriter();

            // 用户不存在
            resultDto.setCodeAndMsg(DataDictionary.USER_NOT_EXISTS);
            out.print(JacksonUtils.toJSon(resultDto));
            out.flush();
            out.close();
            return;
        }

        // 获取请求的url和请求方法
        // String url = request.getRequestURL().toString();
        // 只获取url部分
        String url = request.getServletPath();
        String method = request.getMethod();
        // 组合成数据库中要求的格式
        String permissionUrl = method.toLowerCase() + ":" + url.toLowerCase();
        logger.info("######### url =====> {} ##############", permissionUrl);

        // 判断当前用户是否以后权限访问该url
        if (!PermissionUtils.judge(userId, permissionUrl)) {
            // 权限不足
            PrintWriter out = response.getWriter();
            resultDto.setCodeAndMsg(DataDictionary.NO_PERMISSION);
            out.print(JacksonUtils.toJSon(resultDto));
            out.flush();
            out.close();

            return;
        }

        request.getSession().setAttribute("userId", userId);
        filterChain.doFilter(request, response);
    }

    public void destroy() {

    }
}
