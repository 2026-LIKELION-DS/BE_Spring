package net.likelion.backend.domain.pin.service;

import jakarta.validation.Valid;
import net.likelion.backend.domain.memo.dto.MemoResponseDto;
import net.likelion.backend.domain.pin.dto.PinRequestDto;
import net.likelion.backend.domain.pin.dto.PinResponseDto;

import java.util.List;

public interface PinService {
    List<PinResponseDto> getAll();

    MemoResponseDto create(@Valid PinResponseDto request);

    PinResponseDto create(PinRequestDto request);
}
