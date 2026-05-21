package net.likelion.backend.domain.memo.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemoRequestDto {
    @NotBlank(message = "내용을 입력해주세요.")
    @Size(max = 200, message = "메모는 200자 이하로 입력해주세요.")
    private String content;
}
