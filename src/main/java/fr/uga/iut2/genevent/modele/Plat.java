package fr.uga.iut2.genevent.modele;

import fr.uga.iut2.genevent.exception.MariageException;

import java.io.Serializable;

/**
 * Classe Plat qui permet de définir un plat avec un nom et un prix par personne
 */
public class Plat implements Serializable {

    private static final long serialVersionUID = 1L;  // nécessaire pour la sérialisation

    // Attributs
    private String nom;
    private double prixParPersonne;

    /**
     * Constructeur de la classe Plat permettant de définir un plat avec un nom et un prix par personne
     * @param nom le nom du plat
     * @param prixParPersonne le prix par personne
     */
    public Plat(String nom, double prixParPersonne) throws MariageException {
        setNom(nom);
        setPrixParPersonne(prixParPersonne);
    }

    // GETTERS
    /**
     * Méthode permettant de récupérer le nom du plat
     * @return nom le nom du plat
     */
    public String getNom(){
        return nom;
    }

    /**
     * Méthode permettant de récupérer le prix par personne du plat
     * @return prixParPersonne le prix par personne du plat
     */
    public double getPrixParPersonne(){
        return prixParPersonne;
    }

    // SETTERS
    /**
     * Méthode permettant de définir le prix du plat par personne
     * @param prixParPersonne le prix par personne du plat
     */
    public void setPrixParPersonne(double prixParPersonne) throws MariageException {
        if (prixParPersonne < 0) {
            throw new MariageException("Le prix par personne du plat ne peut pas être négatif");
        } else {
            this.prixParPersonne = prixParPersonne;
        }
    }

    /**
     * Méthode permettant de définir le nom du plat
     * @param nom le nom du plat
     */
    public void setNom(String nom) throws MariageException {
        if (nom == null || nom.isEmpty()) {
            throw new MariageException("Le nom du plat ne peut pas être vide");
        } else {
            this.nom = nom;
        }
    }


}
