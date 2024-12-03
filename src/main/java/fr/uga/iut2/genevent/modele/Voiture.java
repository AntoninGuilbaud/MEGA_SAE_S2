package fr.uga.iut2.genevent.modele;

import fr.uga.iut2.genevent.exception.MariageException;

import java.io.Serializable;

/**
 * Classe Voiture qui permet de définir une voiture avec une marque et un prix
 */
public class Voiture implements Serializable {

    private static final long serialVersionUID = 1L;  // nécessaire pour la sérialisation

    // Attributs
    private String marque;
    private double prix;

    /**
     * Constructeur de la classe Voiture permettant de définir une voiture avec une marque et un prix
     * @param marque la marque de la voiture
     * @param prix le prix de la voiture
     */
    public Voiture(String marque, double prix) throws MariageException {
        setMarque(marque);
        setPrix(prix);
    }

    // Getteurs

    /**
     * Méthode permettant de récupérer la marque de la voiture
     * @return marque la marque de la voiture
     */
    public String getMarque() {
        return this.marque;
    }

    /**
     * Méthode permettant de récupérer le prix de la voiture
     * @return prix le prix de la voiture
     */
    public double getPrix() {
        return this.prix;
    }

    // Setteurs

    /**
     * Méthode permettant de définir la marque de la voiture
     * @param marque la marque de la voiture
     */
    public void setMarque(String marque) throws MariageException {
        if (marque == null || marque.isEmpty()) {
            throw new MariageException("La marque de la voiture ne peut pas être vide");
        } else {
            this.marque = marque;
        }
    }

    /**
     * Méthode permettant de définir le prix de la voiture
     * @param prix le prix de la voiture
     */
    public void setPrix(double prix) throws MariageException {
        if (prix < 0) {
            throw new MariageException("Le prix de la voiture ne peut pas être négatif");
        } else {
            this.prix = prix;
        }
    }
}
