package net.likelion.backend.domain.pin.repository;

import net.likelion.backend.domain.pin.entity.Pin;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MemoryPinRepository implements PinRepository {

    private final List<Pin> pins = new ArrayList<>();
    private Long idCounter = 0L;

    @Override
    public List<Pin> findAll() {
        return new ArrayList<>(pins);
    }

    @Override
    public Pin save(Pin pin) {
        pins.add(pin);
        return pin;
    }

    @Override
    public Long generateId() {
        idCounter++;
        return idCounter;
    }
}