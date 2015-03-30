package com.softserveinc.if052_webapp.service;

import com.softserveinc.if052_webapp.domain.Indicator;
import com.softserveinc.if052_webapp.domain.WaterMeter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.client.RestOperations;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Maksym on 2/12/2015.
 */
public class IndicatorService {

    @Autowired
    @Qualifier("restUrl")
    private String restUrl;

    @Autowired
    @Qualifier("passwordTemplate")
    private RestOperations restTemplate;

    public List<Indicator> getIndicatorList(int meterId){
        Indicator[] arrayOfIndicators = restTemplate.getForObject(restUrl + "indicators/" + meterId, Indicator[].class);
        List<Indicator> indicators = Arrays.asList(arrayOfIndicators);

        return indicators;
    }

    public void deleteIndicator(int indicatorId) {
        Indicator indicator = restTemplate.getForObject(restUrl + "indicators/getone/"+ indicatorId, Indicator.class);
        if (!indicator.isPublished()) {
            restTemplate.delete(restUrl + "indicators/" + indicatorId);
        }
    }

    public Indicator addIndicator(Indicator indicator){
        restTemplate.postForObject(restUrl + "indicators/", indicator, Indicator.class);

        return indicator;
    }

    public void updateIndicator(Indicator indicator, int meterId){
        WaterMeter waterMeter = restTemplate.getForObject(restUrl+ "watermeters/" + meterId, WaterMeter.class);
        indicator.setWaterMeter(waterMeter);
        restTemplate.put(restUrl + "indicators/" + indicator.getIndicatorId(), indicator);
    }

    public WaterMeter getMeterById(int meterId){
        return restTemplate.getForObject(restUrl + "watermeters/" + meterId, WaterMeter.class);
    }

    public Indicator getIndicatorById(int indicatorId){
        return restTemplate.getForObject(restUrl + "indicators/getone/" + indicatorId, Indicator.class);
    }
}