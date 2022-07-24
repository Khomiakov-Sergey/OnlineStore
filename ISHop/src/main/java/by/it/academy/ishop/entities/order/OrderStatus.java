package by.it.academy.ishop.entities.order;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * Class for entity, which describes order status(such as CREATED, IN_THE_PROCESSING, SENT_TO, DELIVERED_TO, CANCELLED).
 * @author Siarhei Khamiakou
 * @version 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_status")
public class OrderStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "status_seq")
    @SequenceGenerator(name = "status_seq", sequenceName = "seq_status")
    private Long id;

    @Column(name = "order_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "orderStatus", cascade = CascadeType.ALL)
    private Set<Order> orders;
}


