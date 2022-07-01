package by.it.academy.ishop.entities.order;

import by.it.academy.ishop.entities.product.Product;
import by.it.academy.ishop.entities.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * This class is responsible for entity Order, which display what kind of product, and how mich it was
 * purchased by the user.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    @Column(name = "order_cost", nullable = false)
    private BigDecimal cost;

    @Column(name = "order_created_at",nullable = false)
    private LocalDateTime created_at;

}
