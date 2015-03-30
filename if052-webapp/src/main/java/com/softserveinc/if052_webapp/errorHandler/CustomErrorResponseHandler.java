package com.softserveinc.if052_webapp.errorHandler;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;
import java.io.IOException;


/**
 * Created by Danylo Tiahun on 06.03.2015.
 */

public class CustomErrorResponseHandler implements ResponseErrorHandler {

    private static Logger LOGGER = Logger.getLogger(CustomErrorResponseHandler.class);

    @Override
    public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
        HttpStatus.Series series = clientHttpResponse.getStatusCode().series();
        return  (series.equals(HttpStatus.Series.CLIENT_ERROR)
                || series.equals(HttpStatus.Series.SERVER_ERROR));
    }

    @Override
    public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
        LOGGER.warn("Error response: status code = " + clientHttpResponse.getStatusCode()
                + " status text = " + clientHttpResponse.getStatusText());
    }
}
