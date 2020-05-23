package com.sartika.PriceMonitor.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductWebController {
    @Autowired
    private ProductService productService;

    @GetMapping("/add-product")
    public String add(Model model) {
        return "addProduct";
    }

    @GetMapping("/list-products")
    public String list(Model model) {
        return "listProducts";
    }

    @GetMapping("/detail-product")
    public String detail(Model model) {
        return "detailProduct";
    }
}
