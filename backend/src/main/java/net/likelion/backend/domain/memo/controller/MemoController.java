package net.likelion.backend.domain.memo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.likelion.backend.domain.memo.dto.MemoRequestDto;
import net.likelion.backend.domain.memo.dto.MemoResponseDto;
import net.likelion.backend.domain.memo.service.MemoService;
import net.likelion.backend.global.dto.ApiResponse;
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

    @Operation(summary = "전체 메모 조회", description = "저장된 모든 메모 목록을 반환합니다.")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "조회 성공")


    @GetMapping
    public ResponseEntity<ApiResponse<List<MemoResponseDto>>> getAll(){
        return ResponseEntity.ok(ApiResponse.success("조회 성공", memoService.getAll()));
    }

    @Operation(summary = "메모 생성", description = "새로운 메모를 생성합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "생성 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "유효하지 않은 요청(content 누락 또는 200자 초과)")
    })
    @PostMapping
    public ResponseEntity<ApiResponse<MemoResponseDto>> create(@RequestBody @Valid MemoRequestDto request){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("메모가 생성되었습니다.", memoService.create(request)));
    }
}
