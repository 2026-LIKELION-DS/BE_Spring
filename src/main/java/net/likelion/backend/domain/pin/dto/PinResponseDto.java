package net.likelion.backend.domain.pin.dto;

import lombok.AllArgsConstructor; // 모든 필드를 파라미터로 받는 생성자를 자동 생성
import lombok.Getter;             // getter 자동 생성

// @Getter: 각 필드의 getXxx() 메서드 자동 생성
@Getter
// @AllArgsConstructor: new PinResponseDto(id, memoId, memoContent) 형태의 생성자를 자동 생성
//   → 서비스 레이어에서 객체를 직접 만들 때 편리하게 사용
@AllArgsConstructor
public class PinResponseDto {

    private Long id;           // 핀의 고유 ID
    private Long memoId;       // 연결된 메모의 ID
    private String memoContent; // 연결된 메모의 본문 내용 (삭제된 경우 "(삭제된 메모)")
}
