package by.it.academy.ishop.entities.order;

import by.it.academy.ishop.entities.product.Product;
import by.it.academy.ishop.entities.user.User;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Class for entity, which describes order(set of products, which user has already bought).
 * @author Siarhei Khamiakou
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orders_seq")
    @SequenceGenerator(name = "orders_seq", sequenceName = "seq_order")
    @Column(name = "order_id", nullable = false)
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_status")
    private OrderStatus orderStatus;

    @Column(name = "order_quantity", nullable = false)
    private Long quantity;

    @Column(name = "order_amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "order_created_at", nullable = false)
    private LocalDateTime createdAt;

}
