package net.likelion.backend.domain.memo.entity;

import lombok.Getter;

@Getter // 어노테이션
public class Memo {
    private final Long id;
    private String content;


    public Memo(Long id, String content) {
        this.id = id;
        this.content = content;
    }
}
