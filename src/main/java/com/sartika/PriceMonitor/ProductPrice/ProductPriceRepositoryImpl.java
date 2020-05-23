package com.sartika.PriceMonitor.ProductPrice;

import com.sartika.PriceMonitor.Common.NestedRowMapper;
import com.sartika.PriceMonitor.Product.Product;
import com.sartika.PriceMonitor.Product.ProductRepositoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class ProductPriceRepositoryImpl implements ProductPriceRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductRepositoryImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public ProductPrice addProductPrice(ProductPrice productPrice) {
        String sql = "INSERT INTO product_price(product_id, price, created_time) "
                + "VALUES(?,?, NOW())";

        LOGGER.debug("SQL : {}", sql);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    sql, Statement.RETURN_GENERATED_KEYS);
            int ctr = 0;
            ps.setLong(++ctr, productPrice.getProduct_id());
            ps.setLong(++ctr, productPrice.getPrice());
            return ps;
        }, keyHolder);

        Long id = (rowAffected > 0) ? (Long) keyHolder.getKeys().get("id") : null;
        productPrice.setId(id);
        return productPrice;
    }

    @Override
    public List<ProductPrice> findAllByProductId(Long productId) {
        String sql = "SELECT * FROM product_price WHERE product_id = ?";

        List<ProductPrice> productPrices = jdbcTemplate.query(sql, new Object[]{productId},
                new NestedRowMapper<>(ProductPrice.class));

        return productPrices;
    }
}
