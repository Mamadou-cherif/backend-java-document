package com.smshub.org.features.document.service.serviceImp;

import com.smshub.org.features.document.model.Document;
import com.smshub.org.features.document.repository.DocumentRepository;
import com.smshub.org.features.document.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentServiceImp implements DocumentService {
    @Autowired
    DocumentRepository documentRepository;

    @Override
    public List<Document> all() {
        return this.documentRepository.findAll();
    }

    @Override
    public Optional<Document> get(int id) {
        return this.documentRepository.findById(id);
    }

    @Override
    public Document add(Document document) {
        return this.documentRepository.save(document);
    }

    @Override
    public Document update(int id, Document document) {
        Document doc = this.documentRepository.findById(id).orElse(null);
        if(doc != null) {
           doc.setAuthor(document.getAuthor());
           doc.setTitle(document.getTitle());
           doc.setDescription(document.getDescription());
           doc.setTypeDocument(document.getTypeDocument());
           return this.documentRepository.save(doc);
        }
        return null;
    }

    @Override
    public List<Document> findByTitleContaining(String title) {
        return this.documentRepository.findByTitleContaining(title);
    }

    @Override
    public void delete(int id) {
        this.documentRepository.deleteById(id);
    }
}
