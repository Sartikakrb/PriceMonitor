package com.sartika.PriceMonitor.Product;

import com.sartika.PriceMonitor.Common.ModelToResponseMapper;
import com.sartika.PriceMonitor.Common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductWebApiController {
    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Response<Object> save(@ModelAttribute Product product) {
        productService.addProduct(product.getUrl());
        return ModelToResponseMapper.mapThisSuccess();
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Response<List<Product>> all() {
        List<Product> products = productService.findAll();
        return ModelToResponseMapper.mapThisSuccess(products);
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public Response<Product> detail(@RequestParam(value = "id") Long id) {
        Product product = productService.findOneById(id);
        return ModelToResponseMapper.mapThisSuccess(product);
    }
}
