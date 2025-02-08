package com.smshub.org.core.exceptions;

/**
 * Exception levée lorsqu'une ressource demandée n'est pas trouvée.
 * Cette exception peut être utilisée dans toute l'application pour gérer les cas où
 * une entité ou une ressource n'existe pas dans la base de données.
 */
public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructeur par défaut
     */
    public ResourceNotFoundException() {
        super("La ressource demandée n'a pas été trouvée");
    }

    /**
     * Constructeur avec message personnalisé
     * @param message Le message d'erreur
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructeur avec message et cause
     * @param message Le message d'erreur
     * @param cause La cause de l'exception
     */
    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructeur utilitaire pour les entités avec ID
     * @param resourceName Nom de la ressource (ex: "Organization")
     * @param fieldName Nom du champ (ex: "id")
     * @param fieldValue Valeur du champ
     */
    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s non trouvé avec %s : '%s'", resourceName, fieldName, fieldValue));
    }
}
