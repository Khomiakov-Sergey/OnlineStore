package by.it.academy.ishop.repositories.cart;

import by.it.academy.ishop.entities.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {

    List<Cart> findCartByUserId(Long id);

    void deleteCartByIdAndUserId(Long userId, Long cartId);
}
