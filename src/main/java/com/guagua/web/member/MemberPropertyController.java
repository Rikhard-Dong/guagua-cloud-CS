package com.guagua.web.member;

import com.guagua.bean.dto.ResultDTO;
import com.guagua.service.member.MemberPropertyService;
import com.guagua.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
     * @param page
     * @param size
     * @param request
     * @return
     */
    @GetMapping("/detail/query")
    public ResultDTO queryDetailCapitalFlow(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                            @RequestParam(value = "size", defaultValue = "30") Integer size,
                                            HttpServletRequest request) {
        return propertyService.queryDetailCapitalFlow(getUserId(request), page, size);
    }

    @PutMapping("/withdraw")
    public ResultDTO withdraw(Double value,
                              HttpServletRequest request) {
        return propertyService.withdraw(getUserId(request), value);
    }
}
