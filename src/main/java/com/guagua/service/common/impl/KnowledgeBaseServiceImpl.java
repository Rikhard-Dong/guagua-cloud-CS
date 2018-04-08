package com.guagua.service.common.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guagua.bean.dto.ResultDTO;
import com.guagua.bean.dto.common.KnowledgeBaseDTO;
import com.guagua.bean.dto.common.KnowledgeBaseItemDTO;
import com.guagua.bean.entity.MyPageInfo;
import com.guagua.bean.entity.common.KnowledgeBase;
import com.guagua.bean.entity.common.KnowledgeBaseItem;
import com.guagua.bean.entity.common.Task;
import com.guagua.bean.entity.common.User;
import com.guagua.dao.common.KnowledgeBaseDao;
import com.guagua.dao.common.KnowledgeBaseItemDao;
import com.guagua.dao.common.TaskDao;
import com.guagua.dao.common.UserDao;
import com.guagua.dao.enterprise.BindTaskKnowledgeDao;
import com.guagua.enums.DataDictionary;
import com.guagua.exception.common.CustomException;
import com.guagua.service.BaseService;
import com.guagua.service.common.KnowledgeBaseService;

import com.mysql.fabric.xmlrpc.base.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ride
 * @date 18-3-18 下午7:23
 * <p>
 * 知识库维护
 */
@Service("knowledgeBaseService")
@Transactional
public class KnowledgeBaseServiceImpl extends BaseService implements KnowledgeBaseService {

    private final UserDao userDao;

    private final KnowledgeBaseDao knowledgeBaseDao;

    private final KnowledgeBaseItemDao itemDao;

    private final BindTaskKnowledgeDao bindTaskKnowledgeDao;

    private final TaskDao taskDao;

    @Autowired
    public KnowledgeBaseServiceImpl(KnowledgeBaseDao knowledgeBaseDao,
                                    UserDao userDao,
                                    KnowledgeBaseItemDao itemDao,
                                    BindTaskKnowledgeDao bindTaskKnowledgeDao,
                                    TaskDao taskDao) {
        this.knowledgeBaseDao = knowledgeBaseDao;
        this.userDao = userDao;
        this.itemDao = itemDao;
        this.bindTaskKnowledgeDao = bindTaskKnowledgeDao;
        this.taskDao = taskDao;
    }

    // 创建一个知识库
    public ResultDTO createKnowledgeBase(Integer userId, String name, String description) {
        KnowledgeBase base = new KnowledgeBase(name, description, userId);
        Integer var1 = knowledgeBaseDao.insertOne(base);

        // 插入失败
        if (var1 == 0) {
            throw new CustomException(DataDictionary.SQL_OPERATION_EXCEPTION);
        }

        return new ResultDTO(DataDictionary.INSERT_SUCCESS);
    }

    // 删除对应的数据库
    public ResultDTO deleteKnowledgeBase(Integer userId, Integer id) {
        Integer var1 = knowledgeBaseDao.deleteById(id);
        if (var1 == 0) {
            throw new CustomException(DataDictionary.SQL_OPERATION_EXCEPTION);
        }
        return new ResultDTO(DataDictionary.DELETE_SUCCESS);
    }

    // 查询数据库, 分页
    public ResultDTO listWithCreator(Integer userId, Integer page, Integer size) {
        // 获取创建者
        User creator = userDao.findById(userId);
        if (creator == null) {
            throw new CustomException(DataDictionary.USER_NOT_EXISTS);
        }
        PageHelper.startPage(page, size);
        List<KnowledgeBase> bases = knowledgeBaseDao.findByCreatorId(userId);
        PageInfo<KnowledgeBase> info = new PageInfo<KnowledgeBase>(bases);
        List<KnowledgeBaseDTO> dtos = convert2KnowledgeBaseDTO(info.getList(), creator.getUsername());
        MyPageInfo<KnowledgeBaseDTO> myInfo = new MyPageInfo<KnowledgeBaseDTO>(info);
        myInfo.setList(dtos);

        return new ResultDTO(DataDictionary.QUERY_SUCCESS).addData("info", myInfo);
    }

    // 更新知识库
    public ResultDTO updateKnowledgeBase(Integer id, String name, String description) {
        // 获取知识库
        KnowledgeBase knowledgeBase = knowledgeBaseDao.findById(id);
        if (knowledgeBase == null) {
            throw new CustomException(DataDictionary.QUERY_FAIL);
        }

        knowledgeBase.setName(name);
        knowledgeBase.setDescription(description);

        Integer var1 = knowledgeBaseDao.updateOne(knowledgeBase);
        if (var1 == 0) {
            throw new CustomException(DataDictionary.UPDATE_FAIL);
        }
        return new ResultDTO(DataDictionary.UPDATE_SUCCESS);
    }

    // 插入一条item
    public ResultDTO createItem(Integer userId, KnowledgeBaseItem item) {
        Integer var1 = itemDao.insertOne(item);
        if (var1 == 0) {
            throw new CustomException(DataDictionary.SQL_OPERATION_EXCEPTION);
        }

        return new ResultDTO(DataDictionary.INSERT_SUCCESS);
    }

    // 删除一个item
    public ResultDTO deleteItem(Integer userId, Integer knowledgeBaseId, Integer itemId) {
        Integer var1 = itemDao.deleteOne(itemId);
        if (var1 == 0) {
            throw new CustomException(DataDictionary.SQL_OPERATION_EXCEPTION);
        }
        return new ResultDTO(DataDictionary.DELETE_SUCCESS);
    }

    // 更新一条item
    public ResultDTO updateItem(Integer userId, Integer knowledgeBaseId, Integer itemId,
                                String question, String answer, String similarQuestion1,
                                String similarQuestion2, String similarQuestion3,
                                String similarQuestion4, String similarQuestion5) {
        KnowledgeBaseItem item = itemDao.findByItemId(itemId);
        if (item == null) {
            throw new CustomException(DataDictionary.QUERY_FAIL);
        }
        item.setQuestion(question);
        item.setAnswer(answer);
        item.setSimilarQuestion1(similarQuestion1);
        item.setSimilarQuestion2(similarQuestion2);
        item.setSimilarQuestion3(similarQuestion3);
        item.setSimilarQuestion4(similarQuestion4);
        item.setSimilarQuestion5(similarQuestion5);

        Integer var1 = itemDao.updateOne(item);
        if (var1 == 0) {
            throw new CustomException(DataDictionary.UPDATE_FAIL);
        }

        return new ResultDTO(DataDictionary.UPDATE_SUCCESS);
    }

    // 查询所有item
    public ResultDTO listItems(Integer userId, Integer knowledgeBaseId, Integer page, Integer size) {
        KnowledgeBase base = knowledgeBaseDao.findById(knowledgeBaseId);
        if (base == null) {
            throw new CustomException(DataDictionary.QUERY_FAIL);
        }

        PageHelper.startPage(page, size);
        List<KnowledgeBaseItem> items = itemDao.findByKnowledgeBase(knowledgeBaseId);
        List<KnowledgeBaseItemDTO> dtos = convert2KnowledgeBaseItemDTO(items, base.getName());
        PageInfo<KnowledgeBaseItemDTO> info = new PageInfo<KnowledgeBaseItemDTO>(dtos);

        return new ResultDTO(DataDictionary.QUERY_SUCCESS).addData("info", info);
    }

    // 查询单个知识库描述
    public ResultDTO queryByKnowledgeBaseId(Integer userId, Integer knowledgeBaseId) {
        KnowledgeBase base = knowledgeBaseDao.findById(knowledgeBaseId);
        KnowledgeBaseDTO dto = new KnowledgeBaseDTO(base);

        return new ResultDTO(DataDictionary.QUERY_SUCCESS).addData("base", dto);
    }

    // 查询与其关联的任务知识库
    public ResultDTO findBaseByTaskId(Integer userId, Integer taskId, Integer page, Integer size) {
        Task task = taskDao.findByTaskId(taskId);
        if (task == null) {
            throw new CustomException(DataDictionary.TASK_NOT_EXISTS);
        }

        User creator = userDao.findById(task.getEnterpriseId());

        List<KnowledgeBaseDTO> dtos = null;
        PageHelper.startPage(page, size);
        List<KnowledgeBase> bases = bindTaskKnowledgeDao.findBaseByTaskId(taskId);
        dtos = convert2KnowledgeBaseDTO(bases, creator.getUsername());
        PageInfo<KnowledgeBaseDTO> info = new PageInfo<KnowledgeBaseDTO>(dtos);

        return new ResultDTO(DataDictionary.QUERY_SUCCESS).addData("bases", info);
    }

    public ResultDTO itemDetail(Integer itemId) {
        KnowledgeBaseItem item = itemDao.findByItemId(itemId);
        return new ResultDTO(DataDictionary.QUERY_SUCCESS).addData("item", item);
    }

    /**
     * 将base 转换成dto
     *
     * @param bases   list
     * @param creator 穿见者名称
     * @return 转换结果
     */
    private List<KnowledgeBaseDTO> convert2KnowledgeBaseDTO(List<KnowledgeBase> bases, String creator) {
        if (bases == null || bases.size() == 0) {
            return null;
        }
        List<KnowledgeBaseDTO> results = new ArrayList<KnowledgeBaseDTO>();
        for (KnowledgeBase base : bases) {
            KnowledgeBaseDTO dto = new KnowledgeBaseDTO(base);
            dto.setCreator(creator);
            results.add(dto);
        }

        return results;
    }

    /**
     * 将base 转换成dto
     *
     * @param bases list
     * @return 转换结果
     */
    private List<KnowledgeBaseDTO> convert2KnowledgeBaseDTO(List<KnowledgeBase> bases) {
        if (bases == null || bases.size() == 0) {
            return null;
        }
        List<KnowledgeBaseDTO> results = new ArrayList<KnowledgeBaseDTO>();
        for (KnowledgeBase base : bases) {
            KnowledgeBaseDTO dto = new KnowledgeBaseDTO(base);
            User user = userDao.findById(base.getCreatorId());
            dto.setCreator(user.getUsername());
            results.add(dto);
        }

        return results;
    }


    /**
     * 将item 转换成item
     *
     * @param items
     * @param knowledgeBaseName
     * @return
     */
    private List<KnowledgeBaseItemDTO> convert2KnowledgeBaseItemDTO(List<KnowledgeBaseItem> items,
                                                                    String knowledgeBaseName) {
        if (items == null || items.size() == 0) {
            return null;
        }

        List<KnowledgeBaseItemDTO> results = new ArrayList<KnowledgeBaseItemDTO>();
        for (KnowledgeBaseItem item : items) {
            KnowledgeBaseItemDTO dto = new KnowledgeBaseItemDTO(item);
            dto.setKnowledgeBaseName(knowledgeBaseName);
            results.add(dto);
        }
        return results;
    }

}
