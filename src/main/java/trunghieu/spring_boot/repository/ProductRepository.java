package trunghieu.spring_boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import trunghieu.spring_boot.entity.Product;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
