package net.likelion.backend.domain.memo.entity;

import lombok.Getter;

@Getter
public class Memo {
    private final Long id;
    private String content;

    public Memo(Long id, String content){
        this.id = id;
        this.content = content;
    }
}
