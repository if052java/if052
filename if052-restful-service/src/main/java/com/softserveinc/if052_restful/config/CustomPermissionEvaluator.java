package com.softserveinc.if052_restful.config;

import com.softserveinc.if052_core.domain.Address;
import com.softserveinc.if052_core.domain.Indicator;
import com.softserveinc.if052_core.domain.WaterMeter;
import com.softserveinc.if052_restful.service.AddressService;
import com.softserveinc.if052_restful.service.IndicatorService;
import com.softserveinc.if052_restful.service.WaterMeterService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import java.io.Serializable;
import java.util.IdentityHashMap;

/**
 * Created by student on 4/16/2015.
 */
public class CustomPermissionEvaluator implements PermissionEvaluator {

    @Autowired
    AddressService addressService;

    @Autowired
    WaterMeterService waterMeterService;

    @Autowired
    IndicatorService indicatorService;

    private static Logger LOGGER = Logger.getLogger(CustomPermissionEvaluator.class.getName());

    @Override
    public boolean hasPermission(Authentication authentication, Object target, Object permission) {
        LOGGER.debug("getName: " + authentication.getName() + " permission: " + permission);

        if (permission.toString().equals("getAddress")) {
            Address address = (Address) target;
            return check(address.getUser().getUserId(), authentication);
        } else if (permission.toString().equals("udAddress")) {
            Integer addressId = (Integer) target;
            Address address = addressService.getAddressById(addressId);
            return check(address.getUser().getUserId(), authentication);
        } else

        if (permission.toString().equals("getWaterMeter")) {
            WaterMeter waterMeter = (WaterMeter) target;
            return check(waterMeter.getAddress().getUser().getUserId(), authentication);
        } else if (permission.toString().equals("udWaterMeter")) {
            Integer waterMeterId = (Integer) target;
            WaterMeter waterMeter = waterMeterService.getWaterMeterById(waterMeterId);
            return check(waterMeter.getAddress().getUser().getUserId(), authentication);
        } else

        if (permission.toString().equals("indicator")){
            LOGGER.debug("indicator");
            Integer indicatorId = (Integer) target;
            Integer userId = indicatorService.getUserIdForIndicator(indicatorId);
            return check(userId, authentication);
        }

        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable serializable, String s, Object o) {
        throw new UnsupportedOperationException();
    }

    public boolean check(Integer userId, Authentication authentication) {
        LOGGER.debug("checking userId: " + userId);
        if (Integer.toString(userId).equals(authentication.getName())) {
            LOGGER.debug("hasPermission result: true");
            return true;
        } else {
            LOGGER.debug("hasPermission result: false");
            return false;
        }
    }
}