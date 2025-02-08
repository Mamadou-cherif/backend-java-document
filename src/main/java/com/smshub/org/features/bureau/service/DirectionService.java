package com.smshub.org.features.bureau.service;

import com.smshub.org.features.bureau.model.Direction;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface DirectionService {
    Direction create(Direction direction);
    List<Direction> all();
    public Direction update(int id, Direction direction) throws ChangeSetPersister.NotFoundException;
    public void create(List<Direction> directions) throws ChangeSetPersister.NotFoundException;
    public Direction get(int id);
    public void delete(Direction direction);
}
