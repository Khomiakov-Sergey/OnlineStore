package by.it.academy.ishop.entities.user;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * Class for entity, which describes user role(such as ROLE_USER and ROLE_ADMIN).
 * @author Siarhei Khamiakou
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "user_role")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_seq")
    @SequenceGenerator(name = "role_seq", sequenceName = "seq_role")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "userRole", cascade = CascadeType.ALL)
    private Set<User> users;
}
