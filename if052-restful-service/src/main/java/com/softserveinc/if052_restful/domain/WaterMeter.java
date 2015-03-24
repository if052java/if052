package com.softserveinc.if052_restful.domain;


import java.util.List;

public class WaterMeter {

    private int waterMeterId;
    private String name;
    private String description;
    private double tariff;
    private Address address;
    private MeterType meterType;
    private List<Indicator> indicators;

    public WaterMeter() {

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

    public double getTariff() { return tariff; }

    public void setTariff(double tariff) { this.tariff = tariff; }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public MeterType getMeterType() { return meterType; }

    public void setMeterType(MeterType meterType) { this.meterType = meterType; }

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
                ", tariff=" + tariff + '\'' +
                ", type=" + meterType +
                '}';
    }
}
