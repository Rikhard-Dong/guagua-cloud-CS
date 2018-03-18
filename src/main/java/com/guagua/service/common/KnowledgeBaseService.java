package com.guagua.service.common;

import com.guagua.bean.dto.ResultDTO;
import com.guagua.bean.entity.common.KnowledgeBaseItem;

/**
 * @author ride
 * @date 18-3-18 下午7:23
 * <p>
 * 知识库维护
 */
public interface KnowledgeBaseService {

    /**
     * 创建对应的知识库
     *
     * @param userId      创建者id
     * @param name        知识库名字
     * @param description 知识库描述
     * @return result
     */
    ResultDTO createKnowledgeBase(Integer userId, String name, String description);

    /**
     * 删除对应的知识库
     *
     * @param userId 用户id
     * @param id     知识库id
     * @return result
     */
    ResultDTO deleteKnowledgeBase(Integer userId, Integer id);

    /**
     * 列出该角色创建的所有知识库
     *
     * @param userId user id
     * @param page   分页页码
     * @param size   每页多少数据
     * @return result
     */
    ResultDTO listWithCreator(Integer userId, Integer page, Integer size);

    /**
     * 更新知识库
     *
     * @param id
     * @param name
     * @param description
     * @return
     */
    ResultDTO updateKnowledgeBase(Integer id, String name, String description);

    /**
     * 添加一套item
     *
     * @param userId
     * @param item
     * @return
     */
    ResultDTO createItem(Integer userId, KnowledgeBaseItem item);

    /**
     * 删除一个item
     *
     * @param userId
     * @param knowledgeBaseId
     * @param itemId
     * @return
     */
    ResultDTO deleteItem(Integer userId, Integer knowledgeBaseId, Integer itemId);

    /**
     * 更新一条
     *
     * @param userId
     * @param knowledgeBaseId
     * @param itemId
     * @param question
     * @param answer
     * @param similarQuestion1
     * @param similarQuestion2
     * @param similarQuestion3
     * @param similarQuestion4
     * @param similarQuestion5
     * @return
     */
    ResultDTO updateItem(Integer userId, Integer knowledgeBaseId, Integer itemId,
                         String question, String answer, String similarQuestion1,
                         String similarQuestion2, String similarQuestion3,
                         String similarQuestion4, String similarQuestion5);

    /**
     * 查询某个知识库下的所有条目
     *
     * @param userId
     * @param knowledgeBaseId
     * @param page
     * @param size
     * @return
     */
    ResultDTO listItems(Integer userId, Integer knowledgeBaseId, Integer page, Integer size);
}
