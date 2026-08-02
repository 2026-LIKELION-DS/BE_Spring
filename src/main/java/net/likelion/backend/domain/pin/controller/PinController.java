package net.likelion.backend.domain.pin.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.likelion.backend.domain.pin.dto.PinRequestDto;
import net.likelion.backend.domain.pin.dto.PinResponseDto;
import net.likelion.backend.domain.pin.service.PinService;
import net.likelion.backend.global.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Swagger UI에서 "Pin" 그룹으로 묶어서 보여줌
@Tag(name = "Pin", description = "메모 고정 API")
// @RestController: 이 클래스가 REST 컨트롤러임을 선언 (각 메서드의 반환값이 JSON으로 변환됨)
@RestController
// 이 컨트롤러의 모든 API는 /pins 경로를 기본으로 사용
@RequestMapping("/pins")
// @RequiredArgsConstructor: final 필드인 pinService를 자동으로 주입받는 생성자 생성
@RequiredArgsConstructor
public class PinController {

    private final PinService pinService; // 비즈니스 로직을 담당하는 서비스 (인터페이스에 의존)

    // Swagger: 이 API의 제목과 설명 표시
    @Operation(summary = "고정된 메모 전체 조회", description = "고정된 모든 메모 목록을 반환합니다.")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "조회 성공")
    // GET /pins → 모든 핀 목록 조회
    @GetMapping
    public ResponseEntity<ApiResponse<List<PinResponseDto>>> getAll() {
        // 서비스에서 받은 핀 목록을 200 OK 응답으로 반환
        return ResponseEntity.ok(ApiResponse.success("조회 성공", pinService.getAll()));
    }

    // Swagger: 이 API의 제목과 설명 표시
    @Operation(summary = "메모 고정", description = "메모를 고정합니다.")
    // Swagger: 성공 시 201, 잘못된 memoId면 400 응답
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "고정 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "존재하지 않는 메모 ID")
    })
    // POST /pins → 새 핀 생성
    @PostMapping
    // @RequestBody: HTTP 요청 바디의 JSON을 PinRequestDto 객체로 변환
    // @Valid: PinRequestDto의 @NotNull 등 유효성 검사를 실행
    public ResponseEntity<ApiResponse<PinResponseDto>> create(@RequestBody @Valid PinRequestDto request) {
        // 서비스에서 핀을 생성하고, 201 Created 상태코드와 함께 생성된 핀을 반환
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("메모가 고정되었습니다.", pinService.create(request)));
    }
}
