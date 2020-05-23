package com.sartika.PriceMonitor.ProductPrice;

import com.sartika.PriceMonitor.Product.Product;
import java.util.List;

public interface ProductPriceRepository {
    ProductPrice addProductPrice(ProductPrice productPrice);
    List<ProductPrice> findAllByProductId(Long productId);
}
