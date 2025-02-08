package com.smshub.org.features.document.commands;

import com.smshub.org.features.typedocument.model.TypeDocument;

public record CreatDocumentCommand(String title, String description, String author,int typeDocId) {
}
