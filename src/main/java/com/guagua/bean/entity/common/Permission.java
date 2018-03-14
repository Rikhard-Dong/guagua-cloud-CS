package com.guagua.bean.entity.common;

/**
 * @author ride
 * @date 18-3-14 下午2:56
 * <p>
 * 权限bean
 */
public class Permission {

    private Integer id;             // 主键
    private String name;            // 权限名称
    private String url;             // 权限地址, 格式 method:url
    private String description;     // 权限描述
    private Integer menuId;         // 外键, 对应菜单id, 表名该操作权限属于哪一个菜单下面

    public Permission() {
    }

    public Permission(String name, String url, String description) {
        this.name = name;
        this.url = url;
        this.description = description;
    }

    public Permission(String name, String url, String description, Integer menuId) {
        this.name = name;
        this.url = url;
        this.description = description;
        this.menuId = menuId;
    }

    public Permission(Integer id, String name, String url, String description, Integer menuId) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.description = description;
        this.menuId = menuId;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
