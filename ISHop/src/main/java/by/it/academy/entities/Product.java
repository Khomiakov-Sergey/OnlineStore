package by.it.academy.entities;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@ToString(exclude = "orders")
@EqualsAndHashCode(exclude = "orders")
@Entity
@Table(name = "product_list")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "PRODUCT_ID")
    private int id;

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
