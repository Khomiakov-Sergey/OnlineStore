package by.it.academy.ishop.repositories.product;

import by.it.academy.ishop.entities.product.CategoryType;
import by.it.academy.ishop.entities.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findProductByCategory_CategoryType(CategoryType categoryType);
}
