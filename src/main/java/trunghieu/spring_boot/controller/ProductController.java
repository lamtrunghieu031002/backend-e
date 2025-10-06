package trunghieu.spring_boot.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trunghieu.spring_boot.entity.Product;

import trunghieu.spring_boot.service.ProductService;

import java.util.List;

@RequiredArgsConstructor

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;


    //lay toan bo danh sach san pham
    @GetMapping
    public ResponseEntity<List<Product>>getAllProducts(){
        return ResponseEntity.ok(productService.getAllProduct());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?>getProductById(@PathVariable Long id){
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }
    //thêm sản phẩm mới
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.saveProduct(product));
    }
    //Xóa sản phẩm
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Đã xóa sản phẩm thành công!");
    }


    //Cập nhật sản phẩm
    @PutMapping("/{id}")
    public ResponseEntity<?>updateProduct(@PathVariable Long id ,@RequestBody Product updateProduct){
        return ResponseEntity.ok(productService.updateProduct(id,updateProduct));
    }

}
