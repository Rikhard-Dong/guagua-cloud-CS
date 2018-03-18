package com.guagua.service.common;

import com.guagua.bean.dto.ResultDTO;


/**
 * @author ride
 * @date 18-3-18 下午3:27
 */
public interface MessageService {
    /**
     * 得到用户最近的五条记录
     *
     * @param userId 用户id
     * @return result
     */
    ResultDTO getLatestFive(Integer userId);
}
