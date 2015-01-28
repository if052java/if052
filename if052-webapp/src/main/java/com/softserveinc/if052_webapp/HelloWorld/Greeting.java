package com.softserveinc.if052_webapp.HelloWorld;

/**
 * Created by nazar on 1/28/15.
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Greeting {

    public String getContent() {
        return content;
    }

    private String content;

}
