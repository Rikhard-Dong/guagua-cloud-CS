package com.guagua.bean.entity;

/**
 * @author ride
 * @date 18-3-14 下午2:18
 */
public class TestEntiry {
    private Integer id;
    private String content;

    public TestEntiry() {
    }

    public TestEntiry(String content) {
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "TestEntiry{" +
                "id=" + id +
                ", content='" + content + '\'' +
                '}';
    }
}
