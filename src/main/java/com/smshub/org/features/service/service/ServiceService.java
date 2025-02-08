package com.smshub.org.features.service.service;

import com.smshub.org.features.service.model.Services;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface ServiceService {
    Services create(Services service);
    List<Services> all();
    public Services update(int id, Services service) throws ChangeSetPersister.NotFoundException;
    public void create(List<Services> services) throws ChangeSetPersister.NotFoundException;
    public Services get(int id);
    public void delete(Services service);
}
