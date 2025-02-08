package com.smshub.org.features.service.service.impl;

import com.smshub.org.core.exceptions.ResourceNotFoundException;
import com.smshub.org.features.service.model.Services;
import com.smshub.org.features.service.repository.ServiceRepository;
import com.smshub.org.features.service.service.ServiceService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceServiceImpl implements ServiceService {

    private final ServiceRepository serviceRepository;

    public ServiceServiceImpl(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    public Services create(Services service) {
        return this.serviceRepository.save(service);
    }

    @Async
    @Override
    public void create(List<Services> services) throws DataIntegrityViolationException {
        this.serviceRepository.saveAll(services);
    }

    @Override
    public Services get(int id) {
        return this.serviceRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Service", "id", id));
    }

    @Override
    public List<Services> all() {
        return this.serviceRepository.findAll();
    }

    @Override
    public Services update(int id, Services service) {
        return this.serviceRepository.save(service);
    }

    @Override
    public void delete(Services service) {
        this.serviceRepository.delete(service);
    }

}
