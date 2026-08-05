package net.likelion.backend.domain.pin.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PinResponseDto {
    private Long id;
    private Long memoId;
    private String memoContent;

}
