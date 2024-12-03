package fr.uga.iut2.genevent.modele;

import fr.uga.iut2.genevent.exception.MariageException;

import java.io.Serializable;

/**
 * Classe Contact qui représente un contact avec un nom, un numéro de téléphone, une profession, une rémunération et un mail
 */
public class Contact implements Serializable {

    private static final long serialVersionUID = 1L;  // nécessaire pour la sérialisation

    // Attributs
    private String nom;
    private String numTel;
    private String metier;
    private double honoraire;
    private String email;

    /**
     * Constructeur de la classe Contact permettant de définir un contact avec un nom, un numéro de téléphone, une profession, une rémunération et un mail
     * @param nom le nom du contact
     * @param mail le mail du contact
     * @param numTel le numéro de téléphone du contact
     * @param profession la profession du contact
     * @param honoraire la rémunération du contact
     */
    public Contact(String nom, String mail, String numTel, String profession, double honoraire) throws MariageException {
        setNom(nom);
        setNumTel(numTel);
        setMetier(profession);
        setHonoraire(honoraire);
        setMail(mail);
    }

    // Getteurs

    /**
     * Méthode permettant de récupérer le nom du contact
     * @return nom le nom du contact
     */
    public String getNom() {
        return nom;
    }

    /**
     * Méthode permettant de récupérer le numéro de téléphone du contact
     * @return numTel le numéro de téléphone du contact
     */
    public String getNumTel() {
        return numTel;
    }

    /**
     * Méthode permettant de récupérer la profession du contact
     * @return metier la profession du contact
     */
    public String getProfession() {
        return metier;
    }

    /**
     * Méthode permettant de récupérer le mail du contact
     * @return mail le mail du contact
     */
    public String getMail() {
        return email;
    }

    /**
     * Méthode permettant de récupérer la rémunération du contact
     * @return honoraire la rémunération du contact
     */
    public double getHonoraire() {
        return honoraire;
    }

    // Setteurs

    /**
     * Méthode permettant de définir le nom du contact
     * @param nom le nom du contact
     */
    public void setNom(String nom) throws MariageException {
        if (nom == null || nom.isEmpty()) {
            throw new MariageException("Le nom du contact ne peut pas être vide");
        } else {
            this.nom = nom;
        }
    }

    /**
     * Méthode permettant de définir le mail du contact
     * @param email le mail du contact
     */
    public void setMail(String email) throws MariageException {
        if (email == null || email.isEmpty()) {
            throw new MariageException("Le mail du contact ne peut pas être vide");
        } else {
            this.email = email;
        }
    }

    /**
     * Méthode permettant de définir le numéro de téléphone du contact
     * @param numTel le numéro de téléphone du contact
     */
    public void setNumTel(String numTel) throws MariageException {
        if (numTel == null || numTel.isEmpty()) {
            throw new MariageException("Le numéro de téléphone du contact ne peut pas être vide");
        } else {
            this.numTel = numTel;
        }
    }

    /**
     * Méthode permettant de définir la profession du contact
     * @param metier la profession du contact
     */
    public void setMetier(String metier) throws MariageException {
        if (metier == null || metier.isEmpty()) {
            throw new MariageException("La profession du contact ne peut pas être vide");
        } else {
            this.metier = metier;
        }
    }

    /**
     * Méthode permettant de définir la rémunération du contact
     * @param honoraire la rémunération du contact
     */
    public void setHonoraire(double honoraire) throws MariageException {
        if (honoraire < 0) {
            throw new MariageException("Le montant de la rémunération ne peut pas être négatif");
        } else {
            this.honoraire = honoraire;
        }
    }




}
