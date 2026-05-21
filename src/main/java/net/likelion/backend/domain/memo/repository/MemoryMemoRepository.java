package net.likelion.backend.domain.memo.repository;

import jakarta.validation.constraints.NotBlank;
import net.likelion.backend.domain.memo.entity.Memo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class MemoryMemoRepository implements MemoRepository {

    private final List<Memo> memos = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    @Override
    public List<Memo> findAll() {
        return memos;
    }

    @Override
    public Memo save(Memo memo) {
        memos.add(memo);
        return memo;
    }

    @Override
    public Optional<Memo> findById(@NotBlank String id) {
        return memos.stream()
                .filter( memo -> memo.getId().equals(id))
                .findFirst();
    }

    public Long generateId() {
        return idCounter.getAndIncrement();
    }
}
