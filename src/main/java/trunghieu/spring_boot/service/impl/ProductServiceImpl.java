package trunghieu.spring_boot.service.impl;


import lombok.RequiredArgsConstructor;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import trunghieu.spring_boot.entity.Product;
import trunghieu.spring_boot.repository.ProductRepository;
import trunghieu.spring_boot.service.ProductService;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {



    private final ProductRepository productRepository;
    private final PasswordEncoder passwordEncoder; // Thêm vào


    @Override
    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }
    @Override
    public Optional<Product>getProductById(Long id ){
        return productRepository.findById(id);
    }
    @Override
    public Product saveProduct(Product product){
        return productRepository.save(product);
    }
    @Override
    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }
    @Override
    public Product updateProduct(Long id,Product updatedProduct){
        return productRepository.findById(id)
                .map(product ->
                        {
                            product.setName(updatedProduct.getName());
                            product.setDescription(updatedProduct.getDescription());
                            product.setPrice(updatedProduct.getPrice());
                            product.setQuantity(updatedProduct.getQuantity());
                            product.setImageUrl(updatedProduct.getImageUrl());
                            return productRepository.save(product);
                        }
                        )
                .orElseThrow(()->new RuntimeException("Không tìm thấy sản phẩm có ID: " + id));
    }

}
