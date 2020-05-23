package com.sartika.PriceMonitor.Product;


import java.sql.Timestamp;

public class Product {
    private Long id;
    private String name;
    private String description;
    private String images;
    private Long current_price;
    private String url;

    public Product() {
    }

    public Product(String name, String description, String images, Long current_price, String url) {
        this.name = name;
        this.description = description;
        this.images = images;
        this.current_price = current_price;
        this.url = url;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImages() {
        return this.images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public Long getCurrent_price() {
        return this.current_price;
    }

    public void setCurrent_price(Long current_price) {
        this.current_price = current_price;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
