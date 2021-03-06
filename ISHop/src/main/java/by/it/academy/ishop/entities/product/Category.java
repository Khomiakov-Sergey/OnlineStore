package by.it.academy.ishop.entities.product;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * Class for entity, which describes product category(such as IPHONE, IPOD, IMAC, WATCH, etc).
 * @author Siarhei Khamiakou
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_seq")
    @SequenceGenerator(name = "category_seq", sequenceName = "seq_category")
    @Column(name = "category_id", nullable = false)
    private Long id;

    @Column(name = "category_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private CategoryType categoryType;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private Set<Product> products;
}
