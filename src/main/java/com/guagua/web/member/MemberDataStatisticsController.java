package com.guagua.web.member;

import com.guagua.bean.dto.ResultDTO;
import com.guagua.service.common.DataStatisticsService;
import com.guagua.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ride
 * @date 18-4-11 下午7:17
 */
@RestController
@RequestMapping("/member/data_statistics")
public class MemberDataStatisticsController extends BaseController {

    private final DataStatisticsService dataService;

    @Autowired
    public MemberDataStatisticsController(DataStatisticsService dataService) {
        this.dataService = dataService;
    }

    @GetMapping("/summary")
    public ResultDTO summary(HttpServletRequest request) {

        return dataService.memberSummary(getUserId(request));
    }
}
