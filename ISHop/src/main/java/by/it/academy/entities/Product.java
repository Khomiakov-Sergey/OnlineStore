package by.it.academy.entities;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@ToString(exclude = "orders")
@EqualsAndHashCode(exclude = "orders")
@Entity
@Table(name = "product_list")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "products_seq")
    @SequenceGenerator(name = "products_seq", sequenceName = "SEQ_PRODUCT", allocationSize = 10)
    @Column(name = "PRODUCT_ID")
    private Long id;

    @Column(name = "CATEGORY_TYPE")
    @Enumerated(EnumType.STRING)
    private CategoryType categoryType;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PRICE")
    private BigDecimal price;

    @Column(name = "NUMBER")
    private int number;

    @Column(name = "DESCRIPTION")
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
