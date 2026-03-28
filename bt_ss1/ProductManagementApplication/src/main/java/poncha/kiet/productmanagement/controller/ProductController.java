package poncha.kiet.productmanagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import poncha.kiet.productmanagement.model.Product;
import poncha.kiet.productmanagement.service.ProductService;

import java.util.List;

@RestController
public class ProductController {
    private ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/api/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }
}
