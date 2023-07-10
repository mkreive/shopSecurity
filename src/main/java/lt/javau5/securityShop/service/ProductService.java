package lt.javau5.securityShop.service;

import lt.javau5.securityShop.entity.Product;
import lt.javau5.securityShop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(int id) {
        Optional<Product> result = productRepository.findById(id);
        Product product = null;

        if(result.isPresent()) {
            product = result.get();
        } else {
            throw new RuntimeException("Did not found product with id: " + id);
        }

        return product;
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public void deleteById(int id) {
        productRepository.deleteById(id);
    }


}
