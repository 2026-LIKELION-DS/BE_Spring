package net.likelion.backend.domain.pin.repository;

import net.likelion.backend.domain.pin.entity.Pin;

import java.util.List;

public interface PinRepository {
    List<Pin> findAll();
    Pin save(Pin pin);
}
