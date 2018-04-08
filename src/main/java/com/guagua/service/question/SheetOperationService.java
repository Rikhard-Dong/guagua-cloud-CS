package com.guagua.service.question;

import com.guagua.bean.dto.ResultDTO;

/**
 * @author ride
 * @date 18-4-6 下午8:13
 */
public interface SheetOperationService {

    /**
     * 得到该客服在该任务申请下的答卷
     *
     * @param taskId
     * @param answerId
     * @return
     */
    ResultDTO getSheetByTaskIdAndAnswerId(Integer taskId, Integer answerId);

    /**
     * 给答卷评分
     *
     * @param sheetId
     * @param score
     * @param remark
     * @return
     */
    ResultDTO scoreSheet(Integer sheetId, Integer score, String remark);
}
