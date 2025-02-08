package com.smshub.org.features.direction.service.impl;

import com.smshub.org.core.exceptions.ResourceNotFoundException;
import com.smshub.org.features.direction.model.Direction;
import com.smshub.org.features.direction.repository.DirectionRepository;
import com.smshub.org.features.direction.service.DirectionService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DirectionServiceImpl implements DirectionService {

    private final DirectionRepository directionRepository;

    public DirectionServiceImpl(DirectionRepository directionRepository) {
        this.directionRepository = directionRepository;
    }

    @Override
    public Direction create(Direction direction) {
        return this.directionRepository.save(direction);
    }

    @Async
    @Override
    public void create(List<Direction> directions) throws DataIntegrityViolationException {
        this.directionRepository.saveAll(directions);
    }

    @Override
    public Direction get(int id) {
        return this.directionRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Direction", "id", id));
    }

    @Override
    public List<Direction> all() {
        return this.directionRepository.findAll();
    }

    @Override
    public Direction update(int id, Direction direction) {
        return this.directionRepository.save(direction);
    }

    @Override
    public void delete(Direction direction) {
        this.directionRepository.delete(direction);
    }

}
