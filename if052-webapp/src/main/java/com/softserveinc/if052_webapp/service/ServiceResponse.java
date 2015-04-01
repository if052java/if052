package com.softserveinc.if052_webapp.service;

import java.util.List;

/**
 * Created by student on 4/1/2015.
 */
public class ServiceResponse {
    private String status = "OK";
    private String message;
    private List response;

    public ServiceResponse() {
    }

    public ServiceResponse(List response) {
        this.response = response;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List getResponse() {
        return response;
    }

    public void setResponse(List response) {
        this.response = response;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ServiceResponse{" +
                "status='" + status + '\'' +
                ", response=" + response +
                '}';
    }


}

