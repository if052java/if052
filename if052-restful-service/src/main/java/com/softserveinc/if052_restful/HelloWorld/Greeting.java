package com.softserveinc.if052_restful.HelloWorld;

import org.springframework.hateoas.ResourceSupport;

public class Greeting extends ResourceSupport {

    private final String content;


    public Greeting(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}