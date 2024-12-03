package fr.uga.iut2.genevent.modele;

import fr.uga.iut2.genevent.exception.MariageException;

import java.io.Serializable;

/**
 * Classe GroupeMusique qui permet de définir un groupe de musique avec une durée, une heure de passage, un nom, un genre musical et un prix
 */
public class GroupeMusique implements Serializable {

    private static final long serialVersionUID = 1L;  // nécessaire pour la sérialisation

    // Attributs
    private int duree;
    private double heurePassage;
    private String nom;
    private GenreMusical genreMusical;
    private int prix;

    /**
     * Constructeur de la classe GroupeMusique permettant de définir un groupe de musique avec une durée, une heure de passage, un nom, un genre musical et un prix
     * @param duree la durée du groupe de musique
     * @param heurePassage l'heure de passage du groupe de musique
     * @param nom le nom du groupe de musique
     * @param genreMusical le genre musical du groupe de musique
     * @param prix le prix du groupe de musique
     */
    public GroupeMusique(int duree, double heurePassage, String nom, GenreMusical genreMusical, int prix) throws MariageException {
        setDuree(duree);
        setHeurePassage(heurePassage);
        setNom(nom);
        setGenreMusical(genreMusical);
        setPrix(prix);
    }

    // Getteurs

    /**
     * Méthode permettant de récupérer la durée du groupe de musique
     * @return duree la durée du groupe de musique
     */
    public int getDuree() {
        return duree;
    }

    /**
     * Méthode permettant de récupérer l'heure de passage du groupe de musique
     * @return heurePassage l'heure de passage du groupe de musique
     */
    public double getHeurePassage() {
        return heurePassage;
    }

    /**
     * Méthode permettant de récupérer le nom du groupe de musique
     * @return nom le nom du groupe de musique
     */
    public String getNom() {
        return nom;
    }

    /**
     * Méthode permettant de récupérer le genre musical du groupe de musique
     * @return genreMusical le genre musical du groupe de musique
     */
    public GenreMusical getGenreMusical() {
        return genreMusical;
    }

    /**
     * Méthode permettant de récupérer le prix du groupe de musique
     * @return prix le prix du groupe de musique
     */
    public int getPrix() {
        return prix;
    }

    // Setteurs

    /**
     * Méthode permettant de définir la durée du groupe de musique
     * @param duree la durée du groupe de musique
     */
    public void setDuree(int duree) throws MariageException {
        if (duree < 0) {
            throw new MariageException("La durée du groupe de musique ne peut pas être négative");
        } else {
            this.duree = duree;
        }
    }

    /**
     * Méthode permettant de définir l'heure de passage du groupe de musique
     * @param heurePassage l'heure de passage du groupe de musique
     */
    public void setHeurePassage(double heurePassage) throws MariageException {
        if (heurePassage < 0) {
            throw new MariageException("L'heure de passage du groupe de musique ne peut pas être négative");
        } else {
            this.heurePassage = heurePassage;
        }
    }

    /**
     * Méthode permettant de définir le nom du groupe de musique
     * @param nom le nom du groupe de musique
     */
    public void setNom(String nom) throws MariageException {
        if (nom == null || nom.isEmpty()) {
            throw new MariageException("Le nom du groupe de musique ne peut pas être vide");
        } else {
            this.nom = nom;
        }
    }

    /**
     * Méthode permettant de définir le genre musical du groupe de musique
     * @param genreMusical le genre musical du groupe de musique
     */
    public void setGenreMusical(GenreMusical genreMusical) throws MariageException {
        if (genreMusical == null) {
            throw new MariageException("Le genre musical du groupe de musique ne peut pas être vide");
        } else {
            this.genreMusical = genreMusical;
        }
    }

    /**
     * Méthode permettant de définir le prix du groupe de musique
     * @param prix le prix du groupe de musique
     */
    public void setPrix(int prix) throws MariageException {
        if (prix < 0) {
            throw new MariageException("Le prix du groupe de musique ne peut pas être négatif");
        } else {
            this.prix = prix;
        }
    }
}
