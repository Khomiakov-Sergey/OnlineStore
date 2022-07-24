package by.it.academy.ishop.configurations;

import by.it.academy.ishop.entities.user.User;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@Data
@Builder
public class JwtUser implements UserDetails {


    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private String email;
    private int age;
    private Collection<? extends GrantedAuthority> authorities;

    public static JwtUser create(User user) {
        return JwtUser.builder()
                .login(user.getLogin())
                .password(user.getPassword())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .age(user.getAge())
                .authorities(getSimpleGrantedAuthorities(user))
                .build();
    }

    private static Set<SimpleGrantedAuthority> getSimpleGrantedAuthorities(User user) {
        return Collections.singleton
                (new SimpleGrantedAuthority(user.getUserRole().getRole().name()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
