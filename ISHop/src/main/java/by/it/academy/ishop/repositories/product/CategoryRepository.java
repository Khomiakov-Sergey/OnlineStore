package by.it.academy.ishop.repositories.product;

import by.it.academy.ishop.entities.product.Category;
import by.it.academy.ishop.entities.product.CategoryType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByCategoryType(CategoryType category);
}
