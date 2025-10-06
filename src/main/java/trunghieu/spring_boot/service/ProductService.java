package trunghieu.spring_boot.service;

import trunghieu.spring_boot.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {


    // lay tat can danh sach san pham
    List<Product>getAllProduct();

    // lay san pham theo id
    Optional<Product>getProductById(Long id);
    // luu lai san pham
    Product saveProduct(Product product);
    Product updateProduct(Long id, Product updatedProduct);
    //xoa san pham
    void deleteProduct(Long id);
}
