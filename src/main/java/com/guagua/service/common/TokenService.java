package com.guagua.service.common;

import com.guagua.bean.dto.ResultDTO;

/**
 * @author ride
 * @date 18-3-13 下午5:43
 * <p>
 * Token service 接口
 */
public interface TokenService {


    /**
     * 刷新token请求
     *
     * @param userId 需要更新的用户id
     * @return 结果
     */
    ResultDTO refresh(Integer userId);

}
