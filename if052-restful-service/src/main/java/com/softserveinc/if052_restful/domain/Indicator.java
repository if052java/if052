package com.softserveinc.if052_restful.domain;

import java.util.Date;

/**
 * Created by Maksym Vynnyk on 02.02.2015.
 */
public class Indicator {
    private int indicatorId;
    private Date date;
    private int value;
    private boolean isPaid;
    private boolean isPublished;
    private WaterMeter waterMeter;

    @Override
    public String toString() {
        return "Indicator [id=" + indicatorId
                + ", date=" + date + ", value=" + value
                + ", isPaid=" + isPaid + ", isPublished=" + isPublished
                + ", .";
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

    public WaterMeter getWaterMeter() {
        return waterMeter;
    }

    public void setWaterMeter(WaterMeter waterMeter) {
        this.waterMeter = waterMeter;
    }
}
