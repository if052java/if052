package com.softserveinc.if052_restful.domain;

import java.util.Date;

public class Indicator {
    private int indicatorId;
    private Date date;
    private int value;
    private boolean isPaid;
    private boolean isPublished;
    private int waterMeterId;

    @Override
    public String toString() {
        return "Indicator [id=" + indicatorId
                + ", date=" + date + ", value=" + value
                + ", isPaid=" + isPaid + ", isPublished=" + isPublished
                + ", waterMeterId=" + waterMeterId + "]";
    }

    public int getIndicatorId() {
        return indicatorId;
    }

    public void setIndicatorId(int indicatorId) {
        this.indicatorId = indicatorId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }

    public boolean isPublished() {
        return isPublished;
    }

    public void setPublished(boolean isPublished) {
        this.isPublished = isPublished;
    }

    public int getWaterMeterId() {
        return waterMeterId;
    }

    public void setWaterMeterId(int waterMeterId) {
        this.waterMeterId = waterMeterId;
    }
}
