package com.guagua.bean.entity.common;

/**
 * @author ride
 * @date 18-3-14 下午3:26
 * <p>
 * 菜单
 */
public class Menu {
    private Integer id;             // 主键
    private String name;            // 菜单名字, 用于前端显示
    private String description;     // 描述
    private Integer parentId;       // 父菜单id, 如果是一级菜单就为null

    public Menu() {
    }

    public Menu(String name, String description, Integer parentId) {
        this.name = name;
        this.description = description;
        this.parentId = parentId;
    }

    public Menu(Integer id, String name, String description, Integer parentId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.parentId = parentId;
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

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", parentId=" + parentId +
                '}';
    }
}
