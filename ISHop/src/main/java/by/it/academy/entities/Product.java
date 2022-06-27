package by.it.academy.entities;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * This class is responsible for entity Product.
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "orders")
@EqualsAndHashCode(exclude = "orders")
@Entity
@Table(name = "product_list")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "products_seq")
    @SequenceGenerator(name = "products_seq", sequenceName = "SEQ_PRODUCT", allocationSize = 10)
    @Column(name = "PRODUCT_ID", nullable = false)
    private Long id;

    @Column(name = "CATEGORY_TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private CategoryType categoryType;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "PRICE", nullable = false)
    private BigDecimal price;

    @Column(name = "NUMBER", nullable = false)
    private int number;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @OneToMany(mappedBy = "product")
    private List<Order> orders;

    @Builder
    public Product(CategoryType categoryType, String name, BigDecimal price, int number, String description) {
        this.categoryType = categoryType;
        this.name = name;
        this.price = price;
        this.number = number;
        this.description = description;
    }


}
