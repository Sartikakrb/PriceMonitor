package com.sartika.PriceMonitor.Schedule;

import com.sartika.PriceMonitor.Product.Product;
import com.sartika.PriceMonitor.Product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PriceScheduler {
    @Autowired
    private ProductService productService;

    //@Scheduled(cron = "* * 0/1 * * *")
    @Scheduled(cron = "0/10 * * * * *")
    public void priceCrawl() {
        System.out.println("Excecuting crawling....");
        List<Product> list = productService.findAll();
        for (Product product : list) {
            productService.editProduct(product.getUrl());
        }
    }
}
