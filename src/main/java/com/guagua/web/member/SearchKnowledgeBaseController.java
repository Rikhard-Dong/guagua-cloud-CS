package com.guagua.web.member;

import com.guagua.bean.dto.ResultDTO;
import com.guagua.service.member.SearchService;
import com.guagua.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ride
 * @date 18-4-10 下午7:11
 * <p>
 * 对知识库进行全文检索, 数据库中使用n-gram进行中文分词, 缓存分词结果, 快速搜索
 */
@RestController
@RequestMapping("/search")
public class SearchKnowledgeBaseController extends BaseController {

    private final SearchService searchService;


    @Autowired
    public SearchKnowledgeBaseController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/search_knowledge_base_by_task/{taskId}/keyword/{keyword}")
    public ResultDTO searchKnowledgeBaseKeywordByTask(@PathVariable("taskId") Integer taskId,
                                                      @PathVariable("keyword") String keyword,
                                                      @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                      @RequestParam(value = "size", defaultValue = "10") Integer size) {
        logger.info("keyword =====> {}", keyword);
        return searchService.searchBaseItemByTask(taskId, keyword, page, size);
    }
}
