package poncha.kiet.productmanagement.service;

import org.springframework.stereotype.Service;
import poncha.kiet.productmanagement.model.Product;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private List<Product> productList = new ArrayList<>();
    public ProductService() {
        productList.add(new Product(1, "Laptop Gaming Nitro 5", 20000000));
        productList.add(new Product(2, "Chuột Logitech G502", 1200000));
        productList.add(new Product(3, "Bàn phím cơ Akko", 1500000));
    }

    public List<Product> getAllProducts() {
        return productList;
    }
}
