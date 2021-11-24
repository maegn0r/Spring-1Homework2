package ru;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
@Scope("prototype")
@Data
public class Cart {
    Map<Product, Integer> items;
    ProductRepository productRepository;

    @Autowired
    public Cart (ProductRepository productRepository){
        this.items = new HashMap<>();
        this.productRepository = productRepository;
    }

    public void addProduct (int id){
        Product product = productRepository.getById(id);
        if (product != null){
            items.merge(product, 1, Integer::sum);
        } else System.out.println("Product not found");
    }
    public void removeProduct(int id){
        Product product = productRepository.getById(id);
        if (product == null){
            System.out.println("Product not found");
        } else {
             if (items.containsKey(product)){
                items.computeIfPresent(product,(key,value) -> value - 1);
                if (items.containsValue(0)){
                    items.remove(product);
                }
            } else System.out.println("This product not found in cart");

        }
    }
    public void addAllProducts (){
        List<Product> productList = productRepository.getProductList();
        for (Product product : productList) {
            items.merge(product, 1, Integer::sum);
        }
    }
    public void removeAllProducts (){
        items.clear();
    }
}
