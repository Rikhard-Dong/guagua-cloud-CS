package com.guagua.web.enterprise;

import com.guagua.bean.dto.ResultDTO;
import com.guagua.service.enterprise.EnterprisePropertyService;
import com.guagua.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.Result;

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
     * @param page
     * @param size
     * @param request
     * @return
     */
    @GetMapping("/detail/query")
    public ResultDTO queryPropertyDetail(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                         @RequestParam(value = "size", defaultValue = "30") Integer size,
                                         HttpServletRequest request) {
        return propertyService.queryPropertyDetail(getUserId(request), page, size);
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
