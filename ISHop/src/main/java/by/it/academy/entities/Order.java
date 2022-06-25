package by.it.academy.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orders_seq")
    @SequenceGenerator(name = "orders_seq", sequenceName = "SEQ_ORDER", allocationSize = 10)
    @Column(nullable = false)
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PRODUCT_ID", nullable = false)
    private Product product;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @Column(nullable = false)
    private Long orderQuantity;

    @Column(nullable = false)
    private BigDecimal totalCost;

    @Column(nullable = false)
    private LocalDateTime orderTime;

    public Order(Product product, User user, long orderQuantity, BigDecimal orderPrice) {
        this.product = product;
        this.user = user;
        this.orderQuantity = orderQuantity;
        this.totalCost = orderPrice;
        orderTime = LocalDateTime.now();
    }
}
