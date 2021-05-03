package com.RestWebserver.datamodel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table(name = "giphy_main_table")
public class GiphyEntity {
    private int id;
    private String giphy_id;
    private String giphy_type;
    private String embed_url;
    private String trending_datetime;
    private String title;

    public GiphyEntity() {

    }


    public GiphyEntity(int id, String giphy_id, String giphy_type, String embed_url, String trending_datetime, String title) {
        this.id = id;
        this.giphy_id = giphy_id;
        this.giphy_type = giphy_type;
        this.embed_url = embed_url;
        this.trending_datetime = trending_datetime;
        this.title = title;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGiphy_id() {
        return this.giphy_id;
    }

    public void setGiphy_id(String giphy_id) {
        this.giphy_id = giphy_id;
    }

    public String getGiphy_Type() {
        return this.giphy_type;
    }

    public void setGiphy_Type(String giphy_type) {
        this.giphy_type = giphy_type;
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
}
