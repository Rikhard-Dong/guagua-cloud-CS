package com.guagua.service.member.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guagua.bean.dto.ResultDTO;
import com.guagua.bean.entity.common.KnowledgeBaseItem;
import com.guagua.dao.common.KnowledgeBaseDao;
import com.guagua.dao.common.KnowledgeBaseItemDao;
import com.guagua.enums.DataDictionary;
import com.guagua.exception.common.CustomException;
import com.guagua.service.BaseService;
import com.guagua.service.member.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ride
 * @date 18-4-10 下午7:15
 */
@Service("searchService")
public class SearchServiceImpl extends BaseService implements SearchService {

    private final KnowledgeBaseDao baseDao;

    private final KnowledgeBaseItemDao itemDao;


    @Autowired
    public SearchServiceImpl(KnowledgeBaseDao baseDao,
                             KnowledgeBaseItemDao itemDao) {
        this.baseDao = baseDao;
        this.itemDao = itemDao;
    }

    // 根据关键字对知识库进行全文检索
    public ResultDTO searchBaseItemByTask(Integer taskId, String keyword, Integer page, Integer size) {
        List<Integer> bindBaseIds = baseDao.findBaseIdByTaskId(taskId);
        if (bindBaseIds == null || bindBaseIds.size() <= 0) {
            throw new CustomException(DataDictionary.ERROR)
                    .addData("detail", "该任务没有绑定知识库")
                    .addData("detail2", "搜索结果为空");
        }

        PageHelper.startPage(page, size);
        List<KnowledgeBaseItem> items = itemDao.searchKeyWord(bindBaseIds, keyword);
        PageInfo<KnowledgeBaseItem> info = new PageInfo<KnowledgeBaseItem>(items);

        return new ResultDTO(DataDictionary.QUERY_SUCCESS).addData("search_result", info);
    }
}
