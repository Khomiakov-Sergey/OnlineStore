package by.it.academy.ishop.entities.product;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "products")
@EqualsAndHashCode(exclude = "products")
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

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private Set<Product> products;
}
