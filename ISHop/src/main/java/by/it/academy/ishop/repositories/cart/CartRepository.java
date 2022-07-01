package by.it.academy.ishop.repositories.cart;

import by.it.academy.ishop.entities.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
