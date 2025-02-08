package com.smshub.org.features.typedocument.service.impl;

import com.smshub.org.core.exceptions.ResourceNotFoundException;
import com.smshub.org.features.typedocument.model.TypeDocument;
import com.smshub.org.features.typedocument.repository.TypeDocumentRepository;
import com.smshub.org.features.typedocument.service.TypeDocumentService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TypeDocumentServiceImpl implements TypeDocumentService {

    private final TypeDocumentRepository typeDocumentRepository;

    public TypeDocumentServiceImpl(TypeDocumentRepository typeDocumentRepository) {
        this.typeDocumentRepository = typeDocumentRepository;
    }

    @Override
    public TypeDocument create(TypeDocument typeDocument) {
        return this.typeDocumentRepository.save(typeDocument);
    }

    @Async
    @Override
    public void create(List<TypeDocument> typeDocuments) throws DataIntegrityViolationException {
        this.typeDocumentRepository.saveAll(typeDocuments);
    }

    @Override
    public TypeDocument get(int id) {
        return this.typeDocumentRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("TypeDocument", "id", id));
    }

    @Override
    public List<TypeDocument> all() {
        return this.typeDocumentRepository.findAll();
    }

    @Override
    public TypeDocument update(int id,TypeDocument typeDocument) {
        return this.typeDocumentRepository.save(typeDocument);
    }

    @Override
    public void delete(TypeDocument typeDocument) {
        this.typeDocumentRepository.delete(typeDocument);
    }

}
