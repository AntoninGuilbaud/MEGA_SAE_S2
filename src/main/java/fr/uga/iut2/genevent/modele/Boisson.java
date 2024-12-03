package fr.uga.iut2.genevent.modele;

import fr.uga.iut2.genevent.exception.MariageException;

import java.io.Serializable;

/**
 * Classe Boisson qui permet de définir une boisson avec un nom et un prix par personne
 */
public class Boisson implements Serializable {
    private static final long serialVersionUID = 1L;  // nécessaire pour la sérialisation

    // Attributs
    private String nom;
    private double prixParPersonne;

    /**
     * Constructeur de la classe Boisson permettant de définir une boisson avec un nom et un prix par personne
     * @param nom le nom de la boisson
     * @param prixParPersonne le prix par personne de la boisson
     */
    public Boisson(String nom, double prixParPersonne) throws MariageException {
        setNom(nom);
        setPrixParPersonne(prixParPersonne);
    }

    // Getteurs

    /**
     * Méthode permettant de récupérer le nom de la boisson
     * @return nom
     */
    public String getNom(){
        return nom;
    }

    /**
     * Méthode permettant de récupérer le prix par personne de la boisson
     * @return prixParPersonne
     */
    public double getPrixParPersonne(){
        return prixParPersonne;
    }


    // Setteurs

    /**
     * Méthode permettant de définir le nom de la boisson
     * @param nom le nom de la boisson
     */
    public void setNom(String nom) throws MariageException {
        if (nom == null || nom.isEmpty()) {
            throw new MariageException("Le nom de la boisson ne peut pas être vide");
        } else {
            this.nom = nom;
        }
    }

    /**
     * Méthode permettant de définir le prix par personne de la boisson
     * @param prixParPersonne le prix par personne de la boisson
     */
    public void setPrixParPersonne(double prixParPersonne) throws MariageException {
        if (prixParPersonne < 0) {
            throw new MariageException("Le prix par personne de la boisson ne peut pas être négatif");
        } else {
            this.prixParPersonne = prixParPersonne;
        }
    }



}
