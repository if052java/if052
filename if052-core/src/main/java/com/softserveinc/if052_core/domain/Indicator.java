package com.softserveinc.if052_core.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * Created by Maksym Vynnyk on 02.02.2015.
 */
public class Indicator {
    private int indicatorId;

    @NotNull
    private Date date;

    @Min(0)
    private Double tariffPerDate;

    @NotNull
    @Min(0)
    private Integer value;

    private boolean paid;

    private boolean published;

    @NotNull
    private WaterMeter waterMeter;

    public Indicator() {
    }

    public Indicator(
        int indicatorId, 
        Date date, 
        Double tariffPerDate, 
        Integer value, 
        boolean paid,
        boolean published,
        WaterMeter waterMeter) {
            this.indicatorId = indicatorId;
            this.date = date;
            this.tariffPerDate = tariffPerDate;
            this.value = value;
            this.paid = paid;
            this.published = published;
            this.waterMeter = waterMeter;
    }

    public Indicator(
        Date date, 
        Double tariffPerDate, 
        Integer value, 
        WaterMeter waterMeter) {
            this.date = date;
            this.tariffPerDate = tariffPerDate;
            this.value = value;
            this.paid = paid;
            this.published = published;
            this.waterMeter = waterMeter;
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

    public Double getTariffPerDate() {
        return tariffPerDate;
    }

    public void setTariffPerDate(Double tariffPerDate) {
        this.tariffPerDate = tariffPerDate;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public WaterMeter getWaterMeter() {
        return waterMeter;
    }

    public void setWaterMeter(WaterMeter waterMeter) {
        this.waterMeter = waterMeter;
    }

    @Override
    public String toString() {
        return "Indicator{" +
                "indicatorId=" + indicatorId +
                ", date=" + date +
                ", tariffPerDate=" + tariffPerDate +
                ", value=" + value +
                ", paid=" + paid +
                ", published=" + published +
                '}';
    }
}