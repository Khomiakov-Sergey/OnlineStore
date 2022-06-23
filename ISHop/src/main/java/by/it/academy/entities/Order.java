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
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "USER_ID")
    private User user;

    private Long orderQuantity;

    private BigDecimal totalCost;

    private LocalDateTime orderTime;

    public Order(Product product, User user, long orderQuantity, BigDecimal orderPrice) {
        this.product = product;
        this.user = user;
        this.orderQuantity = orderQuantity;
        this.totalCost = orderPrice;
        orderTime = LocalDateTime.now();
    }
}
