package net.likelion.backend.domain.pin.service;

import net.likelion.backend.domain.pin.dto.PinRequestDto;
import net.likelion.backend.domain.pin.dto.PinResponseDto;

import java.util.List;

public interface PinService {
    List<PinResponseDto> getAll();
    PinResponseDto create(PinRequestDto request);


}
