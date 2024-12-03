package fr.uga.iut2.genevent.modele;

import java.io.Serializable;

/**
 * Enumération des genres musicaux possibles
 */
public enum GenreMusical implements Serializable {

    ROCK,
    COUNTRY,
    POP,
    RAP,
    SLOW,
    DISCO,
    LOUNGE,
    ;

    /**
     * Méthode permettant de convertir une chaîne de caractères en genre musical
     * @param genre le genre musical sous forme de chaîne de caractères
     * @return GenreMusical le genre musical
     */
    public static GenreMusical fromString(String genre) {
        switch (genre) {
            case "ROCK":
                return ROCK;
            case "COUNTRY":
                return COUNTRY;
            case "POP":
                return POP;
            case "RAP":
                return RAP;
            case "SLOW":
                return SLOW;
            case "DISCO":
                return DISCO;
            case "LOUNGE":
                return LOUNGE;
            default:
                throw new IllegalArgumentException("Genre musical inconnu : " + genre);
        }
    }

    private static final long serialVersionUID = 1L;  // nécessaire pour la sérialisation


    /**
     * Méthode permettant de convertir un genre musical en chaîne de caractères
     * @return String le genre musical sous forme de chaîne de caractères
     */
    @Override
    public String toString() {
        switch (this) {
            case ROCK:
                return "ROCK";
            case COUNTRY:
                return "COUNTRY";
            case POP:
                return "POP";
            case RAP:
                return "RAP";
            case SLOW:
                return "SLOW";
            case DISCO:
                return "DISCO";
            case LOUNGE:
                return "LOUNGE";
            default:
                throw new IllegalArgumentException("Genre musical inconnu : " + this);
        }
    }
}
