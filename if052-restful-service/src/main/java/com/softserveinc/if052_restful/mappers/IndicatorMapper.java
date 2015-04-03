package com.softserveinc.if052_restful.mappers;

import com.softserveinc.if052_core.domain.Indicator;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by Maksym Vynnyk on 02.02.2015.
 */
public interface IndicatorMapper {

    public void insertIndicator(Indicator indicator);

    public Indicator getIndicatorById(int indicatorId);

    public List<Indicator> getAllIndicators();

    public List<Indicator> getIndicatorsByWaterMeter(int waterMeterId);

    public void updateIndicator(Indicator indicator);

    public void deleteIndicator(int indicatorId);

    public Date getMinDate();

    public Date getMaxDate();

    /**
     * Get indicators by start and end of date
     * 
     * @param waterMeterId
     * @param startDate
     * @param endDate
     * @return List of indicators
     */
    public List < Indicator > getIndicatorsByDates(@Param("waterMeterId") int waterMeterId,
                                                 @Param("startDate") String startDate,
                                                 @Param("endDate") String endDate);

    public List<Indicator> getIndicatorsByUserId(@Param("userId")int userId,
                                                 @Param("number") int number);
}
