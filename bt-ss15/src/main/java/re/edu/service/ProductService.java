package re.edu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import re.edu.entity.Product;
import re.edu.repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product create(Product product) {
        return productRepository.save(product);
    }

    public Product update(Long id, Product updated) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found: " + id));
        product.setName(updated.getName());
        product.setDescription(updated.getDescription());
        product.setPrice(updated.getPrice());
        product.setSize(updated.getSize());
        product.setToppings(updated.getToppings());
        return productRepository.save(product);
    }

    public void delete(Long id) {
        if (!productRepository.existsById(id)) {
            throw new IllegalArgumentException("Product not found: " + id);
        }
        productRepository.deleteById(id);
    }
}
