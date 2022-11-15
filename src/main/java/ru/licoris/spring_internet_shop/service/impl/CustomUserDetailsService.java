package ru.licoris.spring_internet_shop.service.impl;

import ru.licoris.spring_internet_shop.model.CustomUserDetails;
import ru.licoris.spring_internet_shop.model.User;
import ru.licoris.spring_internet_shop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User with " +
                "email=" + email + " not found"));
        return new CustomUserDetails(user);
    }
}
