package com.sartika.PriceMonitor.Product;

import com.sartika.PriceMonitor.ProductPrice.ProductPrice;
import com.sartika.PriceMonitor.ProductPrice.ProductPriceRepository;
import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
   private ProductRepository productRepository;

    @Autowired
    private ProductPriceRepository productPriceRepository;

    @Override
    public void addProduct(String url) {
        //Product prod = new Product("Kursi", "Kursi desc","image1;image2", 1000l, "http://localhost.com:8080/blabla");

        Product newCrawlProduct = buildProductFromUrl(url);
        Product existingProduct = productRepository.findOneByName(newCrawlProduct.getName());

        if(existingProduct != null) {
            productRepository.updateCurrentPrice(newCrawlProduct.getCurrent_price(), existingProduct.getName());
            productPriceRepository.addProductPrice(new ProductPrice(existingProduct.getId(),
                    newCrawlProduct.getCurrent_price()));
        } else {
            productRepository.addProduct(newCrawlProduct);
            productPriceRepository.addProductPrice(new ProductPrice(newCrawlProduct.getId(),
                    newCrawlProduct.getCurrent_price()));
        }
    }

    private Product buildProductFromUrl(String url) {
        System.out.println("Constructing product model from url...."); // later delete
        //String example = "https://fabelio.com/ip/kursi-vienna-new.html";

        Product product = new Product();

        if(isPageCrawlable(url)) {
           try {
               Document document = Jsoup.connect(url).userAgent("Chrome").timeout(60 * 1000).get();

               product.setName(parseProductName(document));
               product.setDescription(parseProductDescription(document));
               product.setCurrent_price(Long.parseLong(parseProductPrice(document)));
               product.setImages(parseProductImages(document));
               product.setUrl(url);
           } catch (Exception e) {
               System.out.println("An error occured when crawl each attribute product ");
           }
        }
        return product;
    }

    private boolean isPageCrawlable(String url) {
       boolean result = false;
        Connection conn = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 6.1) "
                + "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36");
        try {
            Response resp = conn.execute();
            if (resp.statusCode() != 200) {
                System.out.println("Page can't be crawled");

            } else {
                System.out.println("Page can be crawled");
                result = true;
            }
        } catch(IOException e) {
            System.out.println("Page can't be crawled");
        }
        return result;
    }

    private String parseProductName(Document doc) {
        try {
            Element nameElement = doc.select("div.product-info-main h1.page-title").first();
            System.out.println(nameElement.text());
            return nameElement.text();
        }catch (Exception e) {
            System.out.println("Failed to parse product name");
            return null;
        }
    }

    private String parseProductDescription(Document doc) {
        try {
            /*Element descElement = doc.select("div.product-info__description div#description p").first();
            System.out.println(descElement.text());
            return descElement.text();*/
            Element descElement = doc.select("div#additional-data").first();
            String descriptionHTML = descElement.html();
            System.out.println("descriptionHTML "+descriptionHTML);
            return descriptionHTML;
        }catch (Exception e) {
            System.out.println("Failed to parse product description");
        }
        return null;
    }

    private String parseProductPrice(Document doc) {
        try {
            Element priceElement = doc.select("span.special-price span.price-wrapper").first();
            System.out.println(priceElement.attr("data-price-amount"));
            return priceElement.attr("data-price-amount");
        }catch (Exception e) {
            System.out.println("Failed to parse product price");
            return null;
        }
    }

    private String parseProductImages(Document doc) {
        try {
            //images
            Element images = doc.select("div.fotorama__nav__shaft").first();
            Elements div = doc.getElementsByTag("div");

            System.out.println(images);
            //using HTML unit
            //convert ke string concat
        }catch (Exception e) {
            System.out.println("Failed to parse product images");
        }
        return null;
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = productRepository.findAll();
        return products;
    }

    @Override
    public Product findOneById(Long id) {
        Product product = productRepository.findOneById(id);
        return product;
    }
}
