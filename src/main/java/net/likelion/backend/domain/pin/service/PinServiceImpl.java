package net.likelion.backend.domain.pin.service;

import lombok.RequiredArgsConstructor; // final 필드를 파라미터로 받는 생성자를 자동 생성 (DI용)
import net.likelion.backend.domain.memo.entity.Memo;
import net.likelion.backend.domain.memo.repository.MemoryMemoRepository;
import net.likelion.backend.domain.pin.dto.PinRequestDto;
import net.likelion.backend.domain.pin.dto.PinResponseDto;
import net.likelion.backend.domain.pin.entity.Pin;
import net.likelion.backend.domain.pin.repository.MemoryPinRepository;
import org.springframework.stereotype.Service; // 이 클래스를 Spring의 서비스 Bean으로 등록

import java.util.List;
import java.util.stream.Collectors;

// @Service: Spring이 이 클래스를 서비스 Bean으로 관리
// @RequiredArgsConstructor: final 필드(pinRepository, memoRepository)를 주입받는 생성자 자동 생성
@Service
@RequiredArgsConstructor
public class PinServiceImpl implements PinService { // PinService 인터페이스를 구현

    private final MemoryPinRepository pinRepository;   // 핀 저장소 (메모리 기반)
    private final MemoryMemoRepository memoRepository; // 메모 저장소 (핀이 가리키는 메모 조회용)

    // 저장된 모든 핀을 응답 DTO 리스트로 변환하여 반환
    @Override
    public List<PinResponseDto> getAll() {
        return pinRepository.findAll()       // 모든 핀을 리스트로 가져옴
                .stream()                        // 리스트를 스트림(파이프라인)으로 변환
                .map(pin -> {                    // 각 Pin 객체를 PinResponseDto로 변환
                    // 핀이 가리키는 메모의 내용을 조회
                    // 메모가 없으면(삭제된 경우) "(삭제된 메모)" 문자열로 대체
                    String content = memoRepository.findById(pin.getMemoId())
                            .map(Memo::getContent)   // 메모가 있으면 content 필드 추출
                            .orElse("(삭제된 메모)"); // 메모가 없으면 기본값 사용
                    // 변환된 DTO 객체 생성 후 반환
                    return new PinResponseDto(pin.getId(), pin.getMemoId(), content);
                })
                .collect(Collectors.toList()); // 스트림 결과를 다시 List로 수집
    }

    // 새 핀을 생성하고 응답 DTO를 반환
    @Override
    public PinResponseDto create(PinRequestDto request) {
        // 요청에 담긴 memoId로 메모를 조회 → 없으면 예외 발생
        Memo memo = memoRepository.findById(request.getMemoId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 메모입니다."));

        // 새 핀의 고유 ID 발급
        Long id = pinRepository.generateId();

        // Pin 엔티티 생성 (발급된 id와 메모 id로 구성)
        Pin pin = new Pin(id, memo.getId());

        // 핀을 메모리 저장소에 저장
        pinRepository.save(pin);

        // 저장된 핀 정보와 메모 내용을 담아 응답 DTO 반환
        return new PinResponseDto(pin.getId(), pin.getMemoId(), memo.getContent());
    }
}
