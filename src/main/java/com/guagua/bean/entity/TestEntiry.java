package com.guagua.bean.entity;

import java.sql.Time;

/**
 * @author ride
 * @date 18-3-14 下午2:18
 * <p>
 * 测试类
 */
public class TestEntiry {
    private Integer id;
    private String content;
    private Time timeSet;

    public TestEntiry() {
    }

    public TestEntiry(String content) {
        this.content = content;
    }

    public TestEntiry(String content, Time timeSet) {
        this.content = content;
        this.timeSet = timeSet;
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

    public Time getTimeSet() {
        return timeSet;
    }

    public void setTimeSet(Time timeSet) {
        this.timeSet = timeSet;
    }

    @Override
    public String toString() {
        return "TestEntiry{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", timeSet=" + timeSet +
                '}';
    }
}
