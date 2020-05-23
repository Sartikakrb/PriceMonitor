package com.sartika.PriceMonitor.Product;


import com.sartika.PriceMonitor.Common.NestedRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductRepositoryImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Product addProduct(Product product) {
        String sql = "INSERT INTO product(name, description, images, current_price, url, created_time) "
                + "VALUES(?,?,?,?,?, NOW())";

        LOGGER.debug("SQL : {}", sql);

        System.out.println("price "+ product.getCurrent_price());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    sql, Statement.RETURN_GENERATED_KEYS);
            int ctr = 0;
            ps.setString(++ctr, product.getName());
            ps.setString(++ctr, product.getDescription());
            ps.setString(++ctr, product.getImages());
            ps.setLong(++ctr, product.getCurrent_price());
            ps.setString(++ctr, product.getUrl());
            return ps;
        }, keyHolder);

        Long id = (rowAffected > 0) ? (Long) keyHolder.getKeys().get("id") : null;
        product.setId(id);
        return product;
    }

    @Override
    public void updateCurrentPrice(Long price, String name) {
        String sql = "UPDATE product SET current_price=? WHERE name = ?";

        LOGGER.debug("SQL : {}", sql);

        jdbcTemplate.update(sql, price, name);
    }

    @Override
    public Product findOneById(Long id) {
        String sql = "SELECT * FROM product WHERE id = ?";

        Product product = jdbcTemplate.queryForObject(sql,
                new BeanPropertyRowMapper<>(Product.class),
                new Object[]{id});

        return product;
    }

    @Override
    public Product findOneByName(String name) {
        String sql = "SELECT * FROM product WHERE name = ?";

        try {
            Product product = jdbcTemplate.queryForObject(sql,
                    new BeanPropertyRowMapper<>(Product.class),
                    new Object[]{name});
            return product;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Product> findAll() {
        String sql = "SELECT id, name, description, current_price, url FROM product";

        List<Product> products = jdbcTemplate.query(sql, new Object[]{}, new NestedRowMapper<>(Product.class));

        return products;
    }
}
