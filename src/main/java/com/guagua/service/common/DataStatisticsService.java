package com.guagua.service.common;

import com.guagua.bean.dto.ResultDTO;

import java.util.Date;

/**
 * @author ride
 * @date 18-4-11 上午8:42
 * <p>
 * 数据统计服务
 */
public interface DataStatisticsService {
    /**
     * get admin data statistics summary
     *
     * @return result dto
     */
    ResultDTO adminSummary();

    /**
     * get admin data statistics summary by [start time, end time]
     *
     * @param startTime start time
     * @param endTime   end time
     * @return result dto
     */
    ResultDTO adminSummaryByTime(Date startTime, Date endTime);

    /**
     * @return result dto
     */
    ResultDTO enterpriseSummary(Integer userId);

    /**
     * @param startTime start time
     * @param endTime   end time
     * @return result
     */
    ResultDTO enterpriseSummaryByTime(Date startTime, Date endTime);

    /**
     * @param userId userId id
     * @return result
     */
    ResultDTO memberSummary(Integer userId);
}
