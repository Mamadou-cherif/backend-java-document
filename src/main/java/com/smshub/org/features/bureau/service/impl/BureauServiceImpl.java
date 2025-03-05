package com.smshub.org.features.bureau.service.impl;

import com.smshub.org.core.exceptions.ResourceNotFoundException;
import com.smshub.org.features.bureau.model.Bureau;
import com.smshub.org.features.bureau.repository.BureauRepository;
import com.smshub.org.features.bureau.service.BureauService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BureauServiceImpl implements BureauService {

    private final BureauRepository bureauRepository;

    public BureauServiceImpl(BureauRepository bureauRepository) {
        this.bureauRepository = bureauRepository;
    }

    @Override
    public Bureau create(Bureau bureau) {
        return this.bureauRepository.save(bureau);
    }

    @Async
    @Override
    public void create(List<Bureau> bureaus) throws DataIntegrityViolationException {
        this.bureauRepository.saveAll(bureaus);
    }

    @Override
    public Bureau get(int id) {
        return this.bureauRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Bureau", "id", id));
    }

    @Override
    public List<Bureau> all() {
        return this.bureauRepository.findAll();
    }

    @Override
    public Bureau update(int id, Bureau bureau) {
        return this.bureauRepository.save(bureau);
    }

    @Override
    public void delete(Bureau bureau) {
        this.bureauRepository.delete(bureau);
    }

}
