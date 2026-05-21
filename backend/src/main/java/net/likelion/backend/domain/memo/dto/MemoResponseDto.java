package net.likelion.backend.domain.memo.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import net.likelion.backend.domain.memo.entity.Memo;

@Getter
@AllArgsConstructor
public class MemoResponseDto {

    private Long id;
    private String content;

    public static MemoResponseDto from(Memo memo){
        return new MemoResponseDto(memo.getId(), memo.getContent());
    }
}
