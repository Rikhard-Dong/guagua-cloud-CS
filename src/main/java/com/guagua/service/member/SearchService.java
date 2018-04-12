package com.guagua.service.member;

import com.guagua.bean.dto.ResultDTO;

/**
 * @author ride
 * @date 18-4-10 下午7:15
 */
public interface SearchService {

    /**
     * 查询知识库的内容, 根据任务id查询任务绑定知识库的内容, 根据关键字进行全文检索
     *
     * @param taskId  task id
     * @param keyword keyword
     * @return result dto include knowledge base item list
     */
    ResultDTO searchBaseItemByTask(Integer taskId, String keyword, Integer page, Integer size);
}
