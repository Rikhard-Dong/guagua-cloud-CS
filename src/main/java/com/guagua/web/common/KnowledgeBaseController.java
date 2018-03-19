package com.guagua.web.common;

import com.guagua.bean.dto.ResultDTO;
import com.guagua.bean.entity.common.KnowledgeBaseItem;
import com.guagua.service.common.KnowledgeBaseService;
import com.guagua.web.BaseController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ride
 * @date 18-3-18 下午7:21
 * <p>
 * 知识库方面操作
 */
@RestController
@RequestMapping("/knowledge_base")
public class KnowledgeBaseController extends BaseController {

    private final KnowledgeBaseService knowledgeBaseService;


    @Autowired
    public KnowledgeBaseController(KnowledgeBaseService knowledgeBaseService) {
        this.knowledgeBaseService = knowledgeBaseService;
    }

    /**
     * 创建一个知识库
     *
     * @param name        知识库名字
     * @param description 知识库描述
     * @param request     request
     * @return result
     */
    @PostMapping("/create")
    public ResultDTO createKnowledgeBase(@RequestParam(value = "name") String name,
                                         @RequestParam(value = "description", required = false) String description,
                                         HttpServletRequest request) {

        return knowledgeBaseService.createKnowledgeBase(getUserId(request), name, description);
    }


    /**
     * 删除一个知识库
     *
     * @param id      id
     * @param request request
     * @return result
     */
    @DeleteMapping("/delete/{id}")
    public ResultDTO deleteKnowledgeBase(@PathVariable(value = "id") Integer id,
                                         HttpServletRequest request) {

        return knowledgeBaseService.deleteKnowledgeBase(getUserId(request), id);
    }

    /**
     * 列出该角色创建的所有知识库
     *
     * @param page    页码
     * @param size    每页大小
     * @param request 请求
     * @return result
     */
    @GetMapping("/list")
    public ResultDTO listKnowledgeBase(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                       @RequestParam(value = "size", defaultValue = "10") Integer size,
                                       HttpServletRequest request) {

        return knowledgeBaseService.listWithCreator(getUserId(request), page, size);
    }

    @GetMapping("/query/{knowledgeBaseId}")
    public ResultDTO queryOne(@PathVariable("knowledgeBaseId") Integer knowledgeBaseId,
                              HttpServletRequest request) {
        return knowledgeBaseService.queryByKnowledgeBaseId(getUserId(request), knowledgeBaseId);
    }

    /**
     * 更新知识库
     *
     * @param id          对应知识库id
     * @param name        知识库名称
     * @param description 知识库描述
     * @param request     request
     * @return result
     */
    @PutMapping("/update/{id}")
    public ResultDTO updateKnowledgeBase(@PathVariable("id") Integer id,
                                         @RequestParam(value = "name") String name,
                                         @RequestParam(value = "description") String description,
                                         HttpServletRequest request) {

        return knowledgeBaseService.updateKnowledgeBase(id, name, description);
    }

    /**
     * 添加一条知识库条目
     *
     * @param knowledgeBaseId
     * @param question
     * @param answer
     * @param similarQuestion1
     * @param similarQuestion2
     * @param similarQuestion3
     * @param similarQuestion4
     * @param similarQuestion5
     * @param request
     * @return
     */
    @PostMapping("/{knowledgeBaseId}/item/create")
    public ResultDTO createItem(@PathVariable("knowledgeBaseId") Integer knowledgeBaseId,
                                @RequestParam("question") String question,
                                @RequestParam("answer") String answer,
                                @RequestParam(value = "similar_question1", required = false) String similarQuestion1,
                                @RequestParam(value = "similar_question2", required = false) String similarQuestion2,
                                @RequestParam(value = "similar_question3", required = false) String similarQuestion3,
                                @RequestParam(value = "similar_question4", required = false) String similarQuestion4,
                                @RequestParam(value = "similar_question5", required = false) String similarQuestion5,
                                HttpServletRequest request) {
        KnowledgeBaseItem item = new KnowledgeBaseItem();
        item.setKnowledgeBaseId(knowledgeBaseId);
        item.setQuestion(question);
        item.setAnswer(answer);
        item.setSimilarQuestion1(similarQuestion1);
        item.setSimilarQuestion2(similarQuestion2);
        item.setSimilarQuestion3(similarQuestion3);
        item.setSimilarQuestion4(similarQuestion4);
        item.setSimilarQuestion5(similarQuestion5);

        return knowledgeBaseService.createItem(getUserId(request), item);
    }

    /**
     * 删除一个item
     *
     * @param knowledgeBaseId 对应知识库id
     * @param itemId          对应item id
     * @param request         request
     * @return result
     */
    @DeleteMapping("/{knowledgeBaseId}/item/delete/{itemId}")
    public ResultDTO deleteItem(@PathVariable("knowledgeBaseId") Integer knowledgeBaseId,
                                @PathVariable("itemId") Integer itemId,
                                HttpServletRequest request) {

        return knowledgeBaseService.deleteItem(getUserId(request), knowledgeBaseId, itemId);
    }

    /**
     * 更新一条
     *
     * @param knowledgeBaseId
     * @param itemId
     * @param question
     * @param answer
     * @param similarQuestion1
     * @param similarQuestion2
     * @param similarQuestion3
     * @param similarQuestion4
     * @param similarQuestion5
     * @param request
     * @return
     */
    @PutMapping("/{knowledgeBaseId}/item/update/{itemId}")
    public ResultDTO updateItem(@PathVariable("knowledgeBaseId") Integer knowledgeBaseId,
                                @PathVariable("itemId") Integer itemId,
                                @RequestParam("question") String question,
                                @RequestParam("answer") String answer,
                                @RequestParam(value = "similar_question1", required = false) String similarQuestion1,
                                @RequestParam(value = "similar_question2", required = false) String similarQuestion2,
                                @RequestParam(value = "similar_question3", required = false) String similarQuestion3,
                                @RequestParam(value = "similar_question4", required = false) String similarQuestion4,
                                @RequestParam(value = "similar_question5", required = false) String similarQuestion5,
                                HttpServletRequest request) {

        return knowledgeBaseService.updateItem(getUserId(request), knowledgeBaseId,
                itemId, question, answer, similarQuestion1, similarQuestion2,
                similarQuestion3, similarQuestion4, similarQuestion5);
    }

    /**
     * 查询某个知识库下所有item
     *
     * @param knowledgeBaseId
     * @param page
     * @param size
     * @param request
     * @return
     */
    @GetMapping("/{knowledgeBaseId}/item/list")
    public ResultDTO listItems(@PathVariable("knowledgeBaseId") Integer knowledgeBaseId,
                               @RequestParam(value = "page", defaultValue = "1") Integer page,
                               @RequestParam(value = "size", defaultValue = "20") Integer size,
                               HttpServletRequest request) {

        return knowledgeBaseService.listItems(getUserId(request), knowledgeBaseId, page, size);
    }
}
