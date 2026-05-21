package net.likelion.backend.domain.memo.service;


import lombok.RequiredArgsConstructor;
import net.likelion.backend.domain.memo.dto.MemoRequestDto;
import net.likelion.backend.domain.memo.dto.MemoResponseDto;
import net.likelion.backend.domain.memo.entity.Memo;
import net.likelion.backend.domain.memo.repository.MemoryMemoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemoServiceImpl implements MemoService {

    private final MemoryMemoRepository memoRepository;

    @Override
    public List<MemoResponseDto> getAll(){
        return memoRepository.findAll().stream().map(MemoResponseDto::from).collect(Collectors.toList());
    }
    @Override
    public MemoResponseDto create(MemoRequestDto request){
        Long id = memoRepository.generateId();
        Memo memo = new Memo(id, request.getContent());
        return MemoResponseDto.from(memoRepository.save(memo));
    }
}
