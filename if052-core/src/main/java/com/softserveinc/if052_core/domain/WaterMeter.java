package com.softserveinc.if052_core.domain;


import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

public class WaterMeter {

    private int waterMeterId;

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private String description;

    @Min(0)
    private Double tariff;

    @NotNull
    private Address address;

    @NotNull
    private MeterType meterType;

    private List<Indicator> indicators;

    public WaterMeter() {
    }

    public WaterMeter(int waterMeterId, String name, String description, double tariff, Address address, MeterType meterType, List<Indicator> indicators) {
        this.waterMeterId = waterMeterId;
        this.name = name;
        this.description = description;
        this.tariff = tariff;
        this.address = address;
        this.meterType = meterType;
        this.indicators = indicators;
    }

    public int getWaterMeterId() {
        return waterMeterId;
    }

    public void setWaterMeterId(int waterMeterId) {
        this.waterMeterId = waterMeterId;
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

    public double getTariff() {
        return tariff;
    }

    public void setTariff(double tariff) {
        this.tariff = tariff;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public MeterType getMeterType() {
        return meterType;
    }

    public void setMeterType(MeterType meterType) {
        this.meterType = meterType;
    }

    public List<Indicator> getIndicators() {
        return indicators;
    }

    public void setIndicators(List<Indicator> indicators) {
        this.indicators = indicators;
    }

    @Override
    public String toString() {
        return "WaterMeter{" +
                "waterMeterId=" + waterMeterId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", tariff=" + tariff +
                ", meterType=" + meterType +
                '}';
    }
}
