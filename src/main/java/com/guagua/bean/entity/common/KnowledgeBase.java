package com.guagua.bean.entity.common;

import java.util.Date;

/**
 * @author ride
 * @date 18-3-18 下午5:49
 * <p>
 * 知识库
 */
public class KnowledgeBase {
    private Integer id;             // id
    private String name;            // 知识库名字
    private String description;     // 知识库描述
    private Integer creatorId;       // 创建者id
    private Date createTime;        // 创建时间

    public KnowledgeBase() {
    }

    public KnowledgeBase(String name, String description, Integer creatorId) {
        this.name = name;
        this.description = description;
        this.creatorId = creatorId;
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

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "KnowledgeBase{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", creatorId='" + creatorId + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
