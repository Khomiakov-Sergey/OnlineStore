/*
package by.it.academy.ishop.services.user.security;

import by.it.academy.ishop.entities.user.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Data
public class UserDetailsImpl implements UserDetails {
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(User user) {
        this.username = user.getLogin();
        this.password = user.getPassword();
        this.authorities = getAuthorities(user);
    }

    private Collection<? extends GrantedAuthority> getAuthorities(User user) {
        return Collections.<GrantedAuthority>singleton(new SimpleGrantedAuthority(user.getUserRole().getRole().name()));
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
*/
