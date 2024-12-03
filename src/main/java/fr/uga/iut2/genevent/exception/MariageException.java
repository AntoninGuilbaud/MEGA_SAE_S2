package fr.uga.iut2.genevent.exception;

public class MariageException extends Exception {
    public MariageException(String message) {
        super(message);
    }

    public MariageException () {
        super("Erreur lors de la création du mariage");
    }
}
