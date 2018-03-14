package com.guagua.filter;


import com.guagua.bean.dto.ResultDto;
import com.guagua.enums.DataDictionary;
import com.guagua.exception.common.CustomException;
import com.guagua.utils.JWTUtils;
import com.guagua.utils.JacksonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
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
@WebFilter(filterName = "permissionFilter",
        value = {"/manager/*", "/member/*", "/enterprise", "/token/*", "/user/profile/*"})
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

        ResultDto resultDto = new ResultDto();

        // 获取前端提交的令牌
        String jwt = request.getHeader("authorization");

        // 解析令牌的数据
        logger.info("##### token ====> {} ####", jwt);
        Map<String, Object> claims = JWTUtils.verifyJWT(jwt);
        if (claims == null) {
            PrintWriter out = response.getWriter();

            // 令牌解析失败
            resultDto.setCodeAndMsg(DataDictionary.INVALID_TOKEN);
            out.print(JacksonUtils.toJSon(resultDto));
            out.flush();
            out.close();
            return;
        }

        Integer userId = (Integer) claims.get("userId");
        if (userId == null) {
            PrintWriter out = response.getWriter();

            // 无效令牌
            resultDto.setCodeAndMsg(DataDictionary.INVALID_TOKEN);
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
