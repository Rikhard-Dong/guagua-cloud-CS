package com.guagua.exception.resolver;

import com.guagua.bean.dto.ResultDTO;
import com.guagua.enums.DataDictionary;
import com.guagua.exception.common.CustomException;
import com.guagua.utils.JacksonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author ride
 * @date 18-3-12 下午1:54
 * <p>
 * 全局异常处理器
 */
public class CustomExceptionResolver implements HandlerExceptionResolver {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public ModelAndView resolveException(HttpServletRequest httpServletRequest,
                                         HttpServletResponse httpServletResponse,
                                         Object o, Exception e) {

        ModelAndView mv = new ModelAndView();

        try {
            logger.info("in custom exception resolver resolveException function");
            // 解决捕获异常后返回的json数据中文乱码问题
            httpServletResponse.setHeader("Content-type", "text/html;charset=UTF-8");

            PrintWriter out = httpServletResponse.getWriter();

            CustomException customException;

            // 自定义异常
            if (e instanceof CustomException) {
                customException = (CustomException) e;
            } else { // 未知异常
                e.printStackTrace();

                customException = new CustomException(DataDictionary.UNKNOWN_ERROR);
            }

            ResultDTO resultDto = new ResultDTO(customException.getMsg());
            resultDto.setData(customException.getData());

            out.print(JacksonUtils.toJSon(resultDto));
            out.flush();

            out.close();
        } catch (IOException e1) {
            logger.error("error");
            e1.printStackTrace();

        }

        return mv;
    }
}
