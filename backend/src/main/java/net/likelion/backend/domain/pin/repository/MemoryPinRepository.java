package net.likelion.backend.domain.pin.repository;

import net.likelion.backend.domain.pin.entity.Pin;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class MemoryPinRepository implements PinRepository {
    private final List<Pin> pins = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    @Override
    public List<Pin> findAll(){
        return pins;
    }
    @Override
    public Pin save(Pin pin){
        pins.add(pin);
        return pin;
    }

    public Long generateId(){
        return idCounter.getAndIncrement();
    }


}
