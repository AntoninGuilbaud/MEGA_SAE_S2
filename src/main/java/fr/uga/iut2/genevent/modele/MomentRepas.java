package fr.uga.iut2.genevent.modele;

import java.io.Serializable;

/**
 * Enumération des moments de repas possibles
 */
public enum MomentRepas implements Serializable {

    MATIN,
    MIDI,
    SOIR,
    ;

    /**
     * Méthode permettant de convertir une chaîne de caractères en moment de repas
     * @param moment le moment de repas sous forme de chaîne de caractères
     * @return MomentRepas le moment de repas
     */
    public static MomentRepas fromString(String moment) {
        switch (moment) {
            case "MATIN":
                return MATIN;
            case "MIDI":
                return MIDI;
            case "SOIR":
                return SOIR;
            default:
                throw new IllegalArgumentException("Momment repas inconnu : " + moment);
        }
    }

    private static final long serialVersionUID = 1L;  // nécessaire pour la sérialisation

    /**
     * Méthode permettant de convertir un moment de repas en chaîne de caractères
     * @return String le moment de repas sous forme de chaîne de caractères
     */
    @Override
    public String toString() {
        switch (this) {
            case MATIN:
                return "MATIN";
            case MIDI:
                return "MIDI";
            case SOIR:
                return "SOIR";
            default:
                throw new IllegalArgumentException("Genre musical inconnu : " + this);
        }
    }
}
