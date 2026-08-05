package net.likelion.backend.domain.pin.entity;

import lombok.Getter;

@Getter
public class Pin {
    private final Long id;
    private final Long memoId;


    public Pin(Long id, Long memoId){
        this.id=id;
        this.memoId=memoId;
    }
}
