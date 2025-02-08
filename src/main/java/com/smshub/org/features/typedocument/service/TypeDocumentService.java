package com.smshub.org.features.typedocument.service;

import com.smshub.org.features.typedocument.model.TypeDocument;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface TypeDocumentService {
    TypeDocument create(TypeDocument typeDocument);
    List<TypeDocument> all();
    public TypeDocument update(int id, TypeDocument typeDocument) throws ChangeSetPersister.NotFoundException;
    public void create(List<TypeDocument> typeDocuments) throws ChangeSetPersister.NotFoundException;
    public TypeDocument get(int id);
    public void delete(TypeDocument typeDocument);
}
