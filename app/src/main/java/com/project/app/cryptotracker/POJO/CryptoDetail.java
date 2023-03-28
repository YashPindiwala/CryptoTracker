package com.project.app.cryptotracker.POJO;

import java.util.List;
import java.util.Map;

public class CryptoDetail {
    private int id;
    private String name;
    private String symbol;
    private String slug;
    private String logo;
    private String description;
    private String dateAdded;
    private String dateLaunched;
    private List<String> tags;
    private String platform;
    private String category;
    private Map<String, List<String>> urls;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getDateLaunched() {
        return dateLaunched;
    }

    public void setDateLaunched(String dateLaunched) {
        this.dateLaunched = dateLaunched;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Map<String, List<String>> getUrls() {
        return urls;
    }

    public void setUrls(Map<String, List<String>> urls) {
        this.urls = urls;
    }
}
