/*package by.it.academy.ishop.services.user.security;

import by.it.academy.ishop.entities.user.User;
import by.it.academy.ishop.repositories.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsApiService implements UserDetailsService {
*//*
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(login);
        if (user != null) {
            return new UserDetailsImpl(user);
        } else {
            throw new UsernameNotFoundException("User not found with login: " + login);
        }
    }*//*
}*/
