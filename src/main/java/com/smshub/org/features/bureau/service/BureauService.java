package com.smshub.org.features.bureau.service;

import com.smshub.org.features.bureau.model.Bureau;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface BureauService {
    Bureau create(Bureau bureau);
    List<Bureau> all();
    public Bureau update(int id, Bureau bureau) throws ChangeSetPersister.NotFoundException;
    public void create(List<Bureau> bureaus) throws ChangeSetPersister.NotFoundException;
    public Bureau get(int id);
    public void delete(Bureau bureau);
}
