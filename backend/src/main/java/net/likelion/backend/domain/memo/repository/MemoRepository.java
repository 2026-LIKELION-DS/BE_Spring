package net.likelion.backend.domain.memo.repository;

import net.likelion.backend.domain.memo.entity.Memo;

import java.util.List;
import java.util.Optional;

public interface MemoRepository {
    List<Memo> findAll();

    Memo save(Memo memo);

    Optional<Memo> findById(Long id);

}
