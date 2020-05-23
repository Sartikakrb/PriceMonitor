package com.sartika.PriceMonitor.ProductPrice;

import java.sql.Timestamp;

public class ProductPrice {
    private Long id;
    private Long product_id;
    private Long price;
    private Timestamp created_time;

    ProductPrice() {}

    public ProductPrice(Long product_id, Long price) {
        this.product_id = product_id;
        this.price = price;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProduct_id() {
        return this.product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public Long getPrice() {
        return this.price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Timestamp getCreated_time() {
        return this.created_time;
    }

    public void setCreated_time(Timestamp created_time) {
         this.created_time = created_time;
    }

}
