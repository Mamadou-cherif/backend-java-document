package com.smshub.org.features.document.service;

import com.smshub.org.features.document.model.Document;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface DocumentService {
    List<Document> all();
    Optional<Document> get(int id);
    Document add(Document document);
    Document update(int id, Document document);
    List<Document> findByTitleContaining(String title);
    void delete(int id);
}
