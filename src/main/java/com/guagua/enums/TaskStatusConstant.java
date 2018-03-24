package com.guagua.enums;

/**
 * @author ride
 * @date 18-3-23 下午3:57
 */
public enum TaskStatusConstant {
    TENDERING(1, "任务招标中"),
    COMPLETION_OF_TENDER(2, "招标完成, 等待任务开始"),
    TASK_IN_PROGRESS(3, "任务进行中"),
    TASK_END(4, "任务结束"),
    TASK_CANCEL(5, "任务取消");
    private Integer status;
    private String description;

    TaskStatusConstant(Integer status, String description) {
        this.status = status;
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
