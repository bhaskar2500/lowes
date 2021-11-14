package com.lowes.urlshortner.dao.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "url")
public class URL {

    public URL() {
    }

    public URL(Long id, String key, String longUrl) {
        this.key = key;
        this.longUrl = longUrl;
        this.id = id;
    }

    private String key;
    @Column(name = "long_url")
    private String longUrl;
    @Id
    private Long id;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
