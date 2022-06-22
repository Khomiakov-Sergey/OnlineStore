package by.it.academy.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@ToString(exclude = "orders")
@EqualsAndHashCode(exclude = "orders")
@Entity
@Table(name = "user_list")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "USER_ID")
    private long id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "SECOND_NAME")
    private String secondName;

    @Column(name = "AGE")
    private int age;

    @Column(name = "LOGIN")
    private String login;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "USER_TYPE")
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;

    public User(String firstName, String secondName, int age, String login, String password) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.age = age;
        this.login = login;
        this.password = password;
    }
}
