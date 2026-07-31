
package net.likelion.backend.domain.pin.service;

import lombok.RequiredArgsConstructor;
import net.likelion.backend.domain.memo.dto.MemoResponseDto;
import net.likelion.backend.domain.memo.entity.Memo;
import net.likelion.backend.domain.memo.repository.MemoRepository;
import net.likelion.backend.domain.pin.dto.PinRequestDto;
import net.likelion.backend.domain.pin.dto.PinResponseDto;
import net.likelion.backend.domain.pin.entity.Pin;
import net.likelion.backend.domain.pin.repository.PinRepository;
import net.likelion.backend.global.exception.BaseException;
import net.likelion.backend.global.exception.ErrorCode;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PinServiceImpl implements PinService {
    @Override
    public MemoResponseDto create(PinResponseDto request) {
        return null;
    }

    private final PinRepository pinRepository;
    private final MemoRepository memoRepository;

    @Override
    public List<PinResponseDto> getAll() {
        return pinRepository.findAll()
                .stream()
                .map(pin -> {
                    String content = memoRepository.findById(String.valueOf(pin.getMemoId()))
                            .map(Memo::getContent)
                            .orElse("(삭제된 메모)");

                    return new PinResponseDto(
                            pin.getId(),
                            pin.getMemoId(),
                            content
                    );
                })
                .collect(Collectors.toList());
    }

    @Override
    public PinResponseDto create(PinRequestDto request) {
        Memo memo = memoRepository.findById(String.valueOf(request.getMemoId()))
                .orElseThrow(() -> new BaseException(ErrorCode.MEMO_NOT_FOUND));

        Long id = pinRepository.generateId();
        Pin pin = new Pin(id, memo.getId());

        pinRepository.save(pin);

        return new PinResponseDto(
                pin.getId(),
                pin.getMemoId(),
                memo.getContent()
        );
    }
}