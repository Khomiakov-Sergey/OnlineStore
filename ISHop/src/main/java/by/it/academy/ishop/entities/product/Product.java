package by.it.academy.ishop.entities.product;

import by.it.academy.ishop.entities.cart.Cart;
import by.it.academy.ishop.entities.order.Order;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@EqualsAndHashCode(exclude = {"orders", "carts"})
@ToString(exclude = {"orders", "carts"})
@Builder
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "products_seq")
    @SequenceGenerator(name = "products_seq", sequenceName = "seq_product")
    @Column(name = "product_id", nullable = false)
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "product_model", nullable = false)
    private String model;

    @Column(name = "product_price", nullable = false)
    private BigDecimal price;

    @Column(name = "product_number", nullable = false)
    private Long number;

    @Column(name = "product_description", nullable = false)
    private String description;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private List<Order> orders;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private List<Cart> carts;


}
