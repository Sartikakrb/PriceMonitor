package com.sartika.PriceMonitor.Product;

import java.util.List;

public interface ProductRepository {
    Product addProduct(Product product);
    void updateCurrentPrice(Long price, String name);
    Product findOneById(Long id);
    Product findOneByName(String name);
    List<Product> findAll();

}
