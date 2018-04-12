package com.guagua.web.admin;

import com.guagua.bean.dto.ResultDTO;
import com.guagua.service.common.DataStatisticsService;
import com.guagua.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author ride
 * @date 18-4-11 上午8:21
 * <p>
 * 后台数据统计
 */
@RestController
@RequestMapping("/admin/data_statistics")
public class AdminDataStatisticsController extends BaseController {
    private final DataStatisticsService dataService;

    @Autowired
    public AdminDataStatisticsController(DataStatisticsService dataService) {
        this.dataService = dataService;
    }

    /**
     * get summary
     *
     * @return result dto include some summary info
     */
    @GetMapping("/summary")
    public ResultDTO summary() {
        return dataService.adminSummary();
    }

    /**
     * get summary the time in [start time, end time]
     *
     * @param startTime start time
     * @param endTime   end time
     * @return result dto
     */
    @GetMapping("summary/{startTime}/to/{endTime}")
    public ResultDTO summaryByTime(@PathVariable("startTime") Date startTime,
                                   @PathVariable("endTime") Date endTime) {

        return dataService.adminSummaryByTime(startTime, endTime);
    }

}
