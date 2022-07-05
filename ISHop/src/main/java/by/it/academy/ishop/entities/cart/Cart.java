package by.it.academy.ishop.entities.cart;

import by.it.academy.ishop.entities.product.Product;
import by.it.academy.ishop.entities.user.User;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"product", "user"})
@EqualsAndHashCode(exclude = {"product", "user"})
@Entity
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "carts_seq")
    @SequenceGenerator(name = "carts_seq", sequenceName = "seq_cart")
    @Column(name = "cart_id", nullable = false)
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "cart_quantity", nullable = false)
    private Long quantity;

    @Column(name = "cart_cost", nullable = false)
    private BigDecimal cost;

}
