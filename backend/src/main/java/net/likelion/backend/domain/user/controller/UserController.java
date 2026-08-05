package net.likelion.backend.domain.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.likelion.backend.domain.user.dto.LoginRequest;
import net.likelion.backend.domain.user.dto.SignUpRequest;
import net.likelion.backend.domain.user.service.UserService;
import net.likelion.backend.global.dto.ApiResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User", description = "로그인/회원가입 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Operation(summary = "회원가입", description = "아이디와 비밀번호를 입력해 새로운 회원을 등록합니다.")
    @PostMapping("/signup")
    public ApiResponse<Void> signUp(
            @Valid @RequestBody SignUpRequest request) {
        userService.signUp(request);
        return ApiResponse.success("회원가입 성공", null);
    }

    @Operation(summary = "로그인", description = "로그인하여 JWT 토큰을 발급받습니다.")
    @PostMapping("/login")
    public ApiResponse<String> login(
            @RequestBody LoginRequest request) {
        String token = userService.login(request);
        return ApiResponse.success("로그인 성공", token);
    }
}