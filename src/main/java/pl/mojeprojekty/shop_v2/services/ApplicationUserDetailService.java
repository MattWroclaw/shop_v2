package pl.mojeprojekty.shop_v2.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.mojeprojekty.shop_v2.entity.Role;
import pl.mojeprojekty.shop_v2.repositories.UserRepository;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ApplicationUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        pl.mojeprojekty.shop_v2.entity.User user = userRepository.findUserByEmail(email)
                .orElseThrow(()-> new NoSuchElementException(
                        "No user with email: " + email));
        String [] roles = user.getRoles()
                .stream()
                .map(Role::getRole)
                .toArray(String[]::new);

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .roles(roles)
                .build();
    }
}
