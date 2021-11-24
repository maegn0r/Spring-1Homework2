package ru;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Component
@PropertySource("classpath:application.properties")
public class ProductRepository {
    private List <Product> productList;



    @Autowired
    public ProductRepository (@Value("${listOfProducts}") String data) {
        productList = Arrays.stream(data.split(";"))
                .map(item -> item.split(","))
                .map(item -> new Product(Integer.parseInt(item[0]),item[1],Double.parseDouble(item[2]))).collect(Collectors.toList());
    }

    public List<Product> getProductList() {
        return productList;
    }
    public Product getById(int id) {
        for (Product product : productList) {
            if (product.getId() == id)
                return product;
        }
        return null;
    }
}
