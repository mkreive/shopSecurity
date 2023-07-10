package lt.javau5.securityShop.controller;

import lt.javau5.securityShop.entity.Product;
import lt.javau5.securityShop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductRestController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public List<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable int id) {
        Product product = productService.findById(id);

        if(product == null) {
            throw new RuntimeException("Product id not found - " + id);
        }
        return product;
    }

    @PostMapping("/products")
    public Product addProduct(@RequestBody Product product) {
        product.setId(0);

        return productService.save(product);
    }

    @PutMapping("/products")
    public Product updateProduct(@RequestBody Product product) {
        return productService.save(product);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable int id) {
        Product tempProduct = productService.findById(id);

        if(tempProduct == null) {
            throw new RuntimeException("Product id not found " + id);
        }

        productService.deleteById(id);
    }
}
