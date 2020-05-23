package com.sartika.PriceMonitor.Product;

import java.util.List;

public interface ProductService {
    void addProduct(String url);
    List<Product> findAll();
    Product findOneById(Long id);
    void editProduct(String url);
}
