package fr.uga.iut2.genevent.modele;

import fr.uga.iut2.genevent.exception.MariageException;

import java.io.Serializable;

/**
 * Classe Personne qui permet de définir une personne avec un nom, un âge et un prénom
 */
public class Personne implements Serializable {

    private static final long serialVersionUID = 1L;  // nécessaire pour la sérialisation

    // Attributs
    private String nom;
    private int age;
    private String prenom;

    /**
     * Constructeur de la classe Personne permettant de définir une personne avec un nom, un âge et un prénom
     * @param nom le nom de la personne
     * @param age l'âge de la personne
     * @param prenom le prénom de la personne
     */
    public Personne(String nom, int age, String prenom) throws MariageException {
        setNom(nom);
        setAge(age);
        setPrenom(prenom);
    }

    // Getteurs

    /**
     * Méthode permettant de récupérer le nom de la personne
     * @return nom le nom de la personne
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * Méthode permettant de récupérer l'âge de la personne
     * @return age l'âge de la personne
     */
    public int getAge() {
        return this.age;
    }

    /**
     * Méthode permettant de récupérer le prénom de la personne
     * @return prenom le prénom de la personne
     */
    public String getPrenom() {
        return this.prenom;
    }

    // Setteurs

    /**
     * Méthode permettant de définir le nom de la personne
     * @param nom le nom de la personne
     */
    public void setNom(String nom) throws MariageException {
        if (nom == null || nom.isEmpty()) {
            throw new MariageException("Le nom ne peut pas être vide");
        } else {
            this.nom = nom;
        }
    }

    /**
     * Méthode permettant de définir l'âge de la personne
     * @param age l'âge de la personne
     */
    public void setAge(int age) throws MariageException {
        if (age < 0) {
            throw new MariageException("L'âge ne peut pas être négatif");
        } else {
            this.age = age;
        }
    }

    /**
     * Méthode permettant de définir le prénom de la personne
     * @param prenom le prénom de la personne
     */
    public void setPrenom(String prenom) throws MariageException {
        if (prenom == null || prenom.isEmpty()) {
            throw new MariageException("Le prénom ne peut pas être vide");
        } else {
            this.prenom = prenom;
        }
    }
}
