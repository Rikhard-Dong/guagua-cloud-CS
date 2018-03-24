package com.guagua.web.enterprise;

import com.guagua.bean.dto.ResultDTO;
import com.guagua.enums.DataDictionary;
import com.guagua.exception.common.CustomException;
import com.guagua.service.enterprise.EnterprisePropertyService;
import com.guagua.utils.DateUtils;
import com.guagua.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.Result;
import java.text.ParseException;
import java.util.Date;

/**
 * @author ride
 * @date 18-3-19 下午7:51
 */
@RestController
@RequestMapping("/enterprise/property")
public class EnterprisePropertyController extends BaseController {

    private final EnterprisePropertyService propertyService;

    @Autowired
    public EnterprisePropertyController(EnterprisePropertyService propertyService) {
        this.propertyService = propertyService;
    }

    /**
     * 查询企业账户的资产信息
     *
     * @param request
     * @return
     */
    @GetMapping("/query")
    public ResultDTO queryProperty(HttpServletRequest request) {
        return propertyService.queryProperty(getUserId(request));
    }

    /**
     * 查询流水细则
     *
     * @param request
     * @return
     */
    @PostMapping("/detail/query")
    public ResultDTO queryPropertyDetail(@RequestParam(value = "startTime") String startTime,
                                         @RequestParam(value = "endTime") String endTime,
                                         HttpServletRequest request) {
        try {
            Date startDate = DateUtils.str2Date(startTime + " 0:0:0");
            Date endDate = DateUtils.str2Date(endTime + " 23:59:59");
            return propertyService.queryPropertyDetail(getUserId(request), startDate, endDate);
        } catch (ParseException e) {
            throw new CustomException(DataDictionary.STRING_CONVERT_DATE_FAIL);
        }

    }

    /**
     * 提现
     *
     * @param value
     * @param request
     * @return
     */
    @PutMapping("/withdraw")
    public ResultDTO withdraw(Double value,
                              HttpServletRequest request) {
        return propertyService.withdraw(getUserId(request), value);
    }

    /**
     * 充值
     *
     * @param value
     * @param request
     * @return
     */
    @PutMapping("/recharge")
    public ResultDTO recharge(Double value,
                              HttpServletRequest request) {
        return propertyService.recharge(getUserId(request), value);
    }
}
