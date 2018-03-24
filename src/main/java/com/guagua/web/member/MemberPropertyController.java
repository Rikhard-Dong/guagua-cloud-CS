package com.guagua.web.member;

import com.guagua.bean.dto.ResultDTO;
import com.guagua.enums.DataDictionary;
import com.guagua.exception.common.CustomException;
import com.guagua.service.member.MemberPropertyService;
import com.guagua.utils.DateUtils;
import com.guagua.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Date;

/**
 * @author ride
 * @date 18-3-19 下午7:50
 * <p>
 * 获取会员相关的资金信息
 */
@RestController
@RequestMapping("/member/property")
public class MemberPropertyController extends BaseController {

    private final MemberPropertyService propertyService;

    @Autowired
    public MemberPropertyController(MemberPropertyService propertyService) {
        this.propertyService = propertyService;
    }

    /**
     * 查询用户的信息
     *
     * @param request request
     * @return result
     */
    @GetMapping("/query")
    public ResultDTO queryProperty(HttpServletRequest request) {

        return propertyService.queryProperty(getUserId(request));
    }

    /**
     * 查询资金流水细则
     *
     * @param request
     * @return
     */
    @PostMapping("/detail/query")
    public ResultDTO queryDetailCapitalFlow(@RequestParam(value = "startTime") String startTime,
                                            @RequestParam(value = "endTime") Integer endTime,
                                            HttpServletRequest request) {
        try {
            Date startDate = DateUtils.str2Date(startTime + " 0:0:0");
            Date endDate = DateUtils.str2Date(endTime + " 23:59:59");
            return propertyService.queryDetailCapitalFlow(getUserId(request), startDate, endDate);
        } catch (ParseException e) {
            throw new CustomException(DataDictionary.STRING_CONVERT_DATE_FAIL);
        }

    }

    @PutMapping("/withdraw")
    public ResultDTO withdraw(Double value,
                              HttpServletRequest request) {
        return propertyService.withdraw(getUserId(request), value);
    }
}
