package by.it.academy.ishop.repositories.product;

import by.it.academy.ishop.entities.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
