package net.likelion.backend.domain.user.service;

import lombok.RequiredArgsConstructor;
import net.likelion.backend.domain.user.dto.LoginRequest;
import net.likelion.backend.domain.user.dto.SignUpRequest;
import net.likelion.backend.domain.user.entity.Role;
import net.likelion.backend.domain.user.entity.User;
import net.likelion.backend.domain.user.repository.UserRepository;
import net.likelion.backend.global.exception.BaseException;
import net.likelion.backend.global.exception.ErrorCode;
import net.likelion.backend.global.jwt.JwtTokenProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public void signUp(SignUpRequest request) {
        if (userRepository.existsByUsername(
                request.getUsername())) {
            throw new BaseException(
                    ErrorCode.DUPLICATE_USERNAME);
        }

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(
                        request.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);
    }

    public String login(LoginRequest request) {
        User user = userRepository
                .findByUsername(request.getUsername())
                .orElseThrow(() ->
                        new BaseException(
                                ErrorCode.USER_NOT_FOUND));

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {
            throw new BaseException(
                    ErrorCode.INVALID_PASSWORD);
        }

        return jwtTokenProvider.createToken(
                user.getUsername(), user.getRole());
    }

}
