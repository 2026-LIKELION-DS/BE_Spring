package net.likelion.backend.domain.pin.dto;

import lombok.Getter;
import lombok.AllArgsConstructor;

@Getter
@AllArgsConstructor
public class PinResponseDto {
    private Long id;
    private Long memoId;
    private String memoContent;
}