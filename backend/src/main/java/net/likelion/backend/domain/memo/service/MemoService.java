package net.likelion.backend.domain.memo.service;

import net.likelion.backend.domain.memo.dto.MemoRequestDto;
import net.likelion.backend.domain.memo.dto.MemoResponseDto;

import java.util.List;

public interface MemoService {

    List<MemoResponseDto> getAll();

    MemoResponseDto create(MemoRequestDto request);
}
