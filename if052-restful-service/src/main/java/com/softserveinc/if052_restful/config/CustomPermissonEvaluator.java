package com.softserveinc.if052_restful.config;

        import com.softserveinc.if052_core.domain.Address;
        import org.apache.log4j.Logger;
        import org.springframework.security.access.PermissionEvaluator;
        import org.springframework.security.core.Authentication;

        import java.io.Serializable;

/**
 * Created by student on 4/16/2015.
 */
public class CustomPermissonEvaluator implements PermissionEvaluator {

    private static Logger LOGGER = Logger.getLogger(CustomPermissonEvaluator.class.getName());

    @Override
    public boolean hasPermission(Authentication authentication, Object target, Object permission) {
        Address address = (Address) target;
//        LOGGER.debug("hasPermission");
        LOGGER.debug("hasPermission userId: " + address.getUser().getUserId());
        LOGGER.debug("hasPermission getName " + authentication.getName());
        if (Integer.toString(address.getUser().getUserId()).equals(authentication.getName())){
            LOGGER.debug("hasPermission result: true");
            return true;
        }
        LOGGER.debug("hasPermission result: false");
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable serializable, String s, Object o) {
        throw new UnsupportedOperationException();
    }
}
