package net.likelion.backend.domain.user.security;

import lombok.RequiredArgsConstructor;
import net.likelion.backend.domain.user.repository.UserRepository;
import net.likelion.backend.global.exception.BaseException;
import net.likelion.backend.global.exception.ErrorCode;
import org.springframework.security.core.userdetails.UserDetails; // 💡 1. UserDetails 임포트 수정
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        net.likelion.backend.domain.user.entity.User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new BaseException(ErrorCode.USER_NOT_FOUND));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername()) // withUserName -> withUsername
                .password(user.getPassword())
                .roles(user.getRole().name()) // roses -> roles
                .build();
    }
}