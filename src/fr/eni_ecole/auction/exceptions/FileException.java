package fr.eni_ecole.auction.exceptions;

public class FileException extends Exception {

    public static final String NO_NAME_GIVEN = "No name for save as been given";
    static public final String BAD_TYPE = "Le fichier doit être de type png ou jpg";
    static public final String BAD_PATH = "Bad path check servlet";
    static public final String CANT_SAVE = "Impossible de sauvegarder l'image";
    static public final String CANT_LOAD = "Impossible de charger l'image";

    public FileException(String message) {
        super(message);
    }
}
