package net.likelion.backend.domain.user.security;

import lombok.RequiredArgsConstructor;
import net.likelion.backend.domain.user.entity.User;
import net.likelion.backend.domain.user.repository.UserRepository;
import net.likelion.backend.global.exception.BaseException;
import net.likelion.backend.global.exception.ErrorCode;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new BaseException(ErrorCode.USER_NOT_FOUND));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole().name())
                .build();
    }
}
