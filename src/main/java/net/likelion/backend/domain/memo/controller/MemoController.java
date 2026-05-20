package net.likelion.backend.domain.memo.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.likelion.backend.domain.memo.dto.MemoRequestDto;
import net.likelion.backend.domain.memo.dto.MemoResponseDto;
import net.likelion.backend.domain.memo.service.MemoService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Memo", description = "메모 CRUD API")
@RestController
@RequestMapping("/memos")
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;

    @Operation(summary = "전체 메모 조회", description ="저장된 모든 메모 목록을 반환합니다.")
    @ApiResponse(responseCode = "200", description = "조회 성공")
    @GetMapping
    public ResponseEntity<List<MemoResponseDto>> getAll() {
        return ResponseEntity.ok(memoService.getAll());
    }

    @PostMapping
    public ResponseEntity<MemoResponseDto> create(@RequestBody @Valid MemoRequestDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(memoService.create(request));
    }
}
