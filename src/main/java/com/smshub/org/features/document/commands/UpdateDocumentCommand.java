package com.smshub.org.features.document.commands;

public record UpdateDocumentCommand(int id, String title, String description, String author, int typeDocId) {
}
