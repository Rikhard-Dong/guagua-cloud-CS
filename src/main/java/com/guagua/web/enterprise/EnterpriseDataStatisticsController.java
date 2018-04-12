package com.guagua.web.enterprise;

import com.guagua.bean.dto.ResultDTO;
import com.guagua.service.common.DataStatisticsService;
import com.guagua.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author ride
 * @date 18-4-11 下午2:54
 */
@RestController
@RequestMapping("/enterprise/data_statistics")
public class EnterpriseDataStatisticsController extends BaseController {

    private final DataStatisticsService dataService;

    @Autowired
    public EnterpriseDataStatisticsController(DataStatisticsService dataService) {
        this.dataService = dataService;
    }

    /**
     * 获取summary
     *
     * @return result
     */
    @GetMapping("/summary")
    public ResultDTO summary(HttpServletRequest request) {

        return dataService.enterpriseSummary(getUserId(request));
    }

    @GetMapping("/summary/{startTime}/to/{endTime}")
    public ResultDTO summaryByTime(@PathVariable("startTime") Date startTime,
                                   @PathVariable("endTime") Date endTime) {
        return dataService.enterpriseSummaryByTime(startTime, endTime);
    }
}
