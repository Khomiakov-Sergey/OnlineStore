package by.it.academy.ishop.entities.product;

import by.it.academy.ishop.entities.order.Order;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * This class is responsible for entity Product.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "orders")
@EqualsAndHashCode(exclude = "orders")
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "products_seq")
    @SequenceGenerator(name = "products_seq", sequenceName = "seq_product")
    @Column(name = "product_id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "product_model", nullable = false)
    private String model;

    @Column(name = "product_price", nullable = false)
    private BigDecimal price;

    @Column(name = "product_number", nullable = false)
    private int number;

    @Column(name = "product_description", nullable = false)
    private String description;

    @OneToMany(mappedBy = "product")
    private List<Order> orders;


}
