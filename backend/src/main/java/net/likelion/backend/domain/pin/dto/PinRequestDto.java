package net.likelion.backend.domain.pin.dto;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PinRequestDto {
    @NotNull(message = "메모 아이디를 입력해주세요")
    private Long memoId;


}
