package com.lowes.urlshortner.dao.models;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class URLRequest {
    private String url;

    @JsonCreator
    public URLRequest() {

    }

    @JsonCreator
    public URLRequest(@JsonProperty("url") String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
