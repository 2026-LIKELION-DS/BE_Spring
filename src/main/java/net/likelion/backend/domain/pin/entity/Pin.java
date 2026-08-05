package net.likelion.backend.domain.pin.entity;

import lombok.Getter;
import lombok.AllArgsConstructor;

@Getter
@AllArgsConstructor
public class Pin {
    private final Long id;
    private final Long memoId;
}