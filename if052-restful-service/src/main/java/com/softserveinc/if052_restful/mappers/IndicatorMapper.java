package com.softserveinc.if052_restful.mappers;

import com.softserveinc.if052_restful.domain.Indicator;

import java.util.List;

/**
 * Created by Maksym Vynnyk on 02.02.2015.
 */
public interface IndicatorMapper {

    public void insertIndicator(Indicator indicator);

    public Indicator getIndicatorById(int indicatorId);

    public List<Indicator> getAllIndicators();

    public void updateIndicator(Indicator indicator);

    public void deleteIndicator(int indicatorId);

}
