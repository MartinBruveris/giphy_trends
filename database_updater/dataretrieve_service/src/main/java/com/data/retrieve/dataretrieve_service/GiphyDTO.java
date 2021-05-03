package com.data.retrieve.dataretrieve_service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GiphyDTO {
    private String id;
    private String type;
    private String embed_url;
    private String trending_datetime;
    private String title;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmbed_url() {
        return this.embed_url;
    }

    public void setEmbed_url(String embed_url) {
        this.embed_url = embed_url;
    }

    public String getTrending_datetime() {
        return this.trending_datetime;
    }

    public void setTrending_datetime(String trending_datetime) {
        this.trending_datetime = trending_datetime;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return this.id+" "+this.type+" "+this.embed_url+" "+this.trending_datetime+" "+this.title;
    }

}
