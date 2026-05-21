package net.likelion.backend.domain.pin.dto;

import lombok.Getter;

@Getter
public class PinResponseDto {

    private final Long id;
    private final Long memoId;
    private final String memoContent;

    public PinResponseDto(Long id, Long memoId, String memoContent) {
        this.id = id;
        this.memoId = memoId;
        this.memoContent = memoContent;
    }
}