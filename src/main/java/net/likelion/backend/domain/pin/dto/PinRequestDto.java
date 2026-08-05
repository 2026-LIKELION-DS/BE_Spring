package net.likelion.backend.domain.pin.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PinRequestDto {
    @NotNull(message = "고정할 메모 ID는 필수입니다.")
    private Long memoId;
}