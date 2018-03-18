package com.guagua.bean.dto.common;

import com.guagua.bean.entity.common.KnowledgeBase;
import com.guagua.utils.DateUtils;

import java.util.Date;

/**
 * @author ride
 * @date 18-3-18 下午8:10
 */
public class KnowledgeBaseDTO {
    private Integer id;             // id
    private String name;            // 知识库名字
    private String description;     // 知识库描述
    //    private Integer creatorId;       // 创建者id
    private String creator;         // 创建者
    private String createTime;        // 创建时间

    public KnowledgeBaseDTO() {

    }

    public KnowledgeBaseDTO(KnowledgeBase base) {
        this.id = base.getId();
        this.name = base.getName();
        this.description = base.getDescription();
        this.createTime = DateUtils.date2StrCN(base.getCreateTime());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "KnowledgeBaseDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", creator='" + creator + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
