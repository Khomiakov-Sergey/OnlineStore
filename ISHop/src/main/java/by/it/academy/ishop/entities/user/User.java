package by.it.academy.ishop.entities.user;

import by.it.academy.ishop.entities.cart.Cart;
import by.it.academy.ishop.entities.order.Order;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Class for entity, which describes user.
 * @author Siarhei Khamiakou
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq")
    @SequenceGenerator(name = "users_seq", sequenceName = "SEQ_USER")
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Column(name = "user_first_name", nullable = false)
    private String firstName;

    @Column(name = "user_last_name", nullable = false)
    private String lastName;

    @Column(name = "user_age", nullable = false)
    private int age;

    @Column(name = "user_login", nullable = false, unique = true)
    private String login;

    @Column(name = "user_password", nullable = false)
    private String password;

    @Column(name = "user_email", nullable = false, unique = true)
    private String email;

    @Column(name = "user_created_at", nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "role_id")
    private UserRole userRole;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orders;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private List<Cart> carts;

}
