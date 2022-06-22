package by.it.academy.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order  {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "USER_ID")
    private User user;

    private long orderQuantity;

    private BigDecimal orderPrice;

    public Order(Product product, User user, long orderQuantity, BigDecimal orderPrice) {
        this.product = product;
        this.user = user;
        this.orderQuantity = orderQuantity;
        this.orderPrice = orderPrice;
    }
}
