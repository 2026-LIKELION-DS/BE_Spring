package net.likelion.backend.domain.pin.repository;

import net.likelion.backend.domain.pin.entity.Pin;

import java.util.List;
import java.util.Optional;

public interface PinRepository {

    List<Pin> findAll();
    Pin save(Pin pin);
    Optional<Pin> findById(Long id);
    Long generateId();
}
