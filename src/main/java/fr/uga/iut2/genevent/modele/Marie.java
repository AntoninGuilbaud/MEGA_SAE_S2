package fr.uga.iut2.genevent.modele;

import fr.uga.iut2.genevent.exception.MariageException;

import java.io.Serializable;

/**
 * Classe Marie qui permet de définir une personne de type Marie avec un nom, un âge, un prénom, un prix de vêtement, un prix de chaussures, un prix d'alliance, un email et un numéro de téléphone
 */
public class Marie extends Personne implements Serializable {

    private static final long serialVersionUID = 1L;  // nécessaire pour la sérialisation

    // Attributs
    private double prixVetement;
    private double prixChaussures;
    private double prixAlliance;
    private String email;
    private String numtel;

    /**
     * Constructeur de la classe Marie permettant de définir une personne de type Marie avec un nom, un âge, un prénom, un prix de vêtement, un prix de chaussures, un prix d'alliance, un email et un numéro de téléphone
     * @param nom le nom de la personne
     * @param age l'âge de la personne
     * @param prenom le prénom de la personne
     * @param prixVetement le prix du vêtement de la personne
     * @param prixChaussures le prix des chaussures de la personne
     * @param prixAlliance le prix de l'alliance de la personne
     * @param email l'email de la personne
     * @param numtel le numéro de téléphone de la personne
     */
    public Marie(String nom, int age, String prenom, double prixVetement, double prixChaussures, double prixAlliance, String email, String numtel) throws MariageException {
        super(nom, age, prenom);
        setPrixVetement(prixVetement);
        setPrixChaussures(prixChaussures);
        setPrixAlliance(prixAlliance);
        setEmail(email);
        setNumtel(numtel);
    }

    // Getteurs

    /**
     * Méthode permettant de récupérer le prix du vêtement de la personne de type Marie
     * @return prixVetement le prix du vêtement
     */
    public double getPrixVetement() {
        return this.prixVetement;
    }

    /**
     * Méthode permettant de récupérer le prix des chaussures de la personne de type Marie
     * @return prixChaussures le prix des chaussures
     */
    public double getPrixChaussures() {
        return this.prixChaussures;
    }

    /**
     * Méthode permettant de récupérer le prix de l'alliance de la personne de type Marie
     * @return prixAlliance le prix de l'alliance
     */
    public double getPrixAlliance() {
        return this.prixAlliance;
    }

    /**
     * Méthode permettant de récupérer l'email de la personne de type Marie
     * @return email l'email de la personne
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Méthode permettant de récupérer le numéro de téléphone de la personne de type Marie
     * @return numtel le numéro de téléphone de la personne
     */
    public String getNumtel() {
        return this.numtel;
    }

    // Setteurs

    /**
     * Méthode permettant de définir le prix du vêtement de la personne de type Marie
     * @param prixVetement le prix du vêtement
     */
    public void setPrixVetement(double prixVetement) throws MariageException {
        if (prixVetement < 0) {
            throw new MariageException("Le prix du vêtement ne peut pas être négatif");
        } else {
            this.prixVetement = prixVetement;
        }
    }

    /**
     * Méthode permettant de définir le prix des chaussures de la personne de type Marie
     * @param prixChaussures le prix des chaussures
     */
    public void setPrixChaussures(double prixChaussures) throws MariageException {
        if (prixChaussures < 0) {
            throw new MariageException("Le prix des chaussures ne peut pas être négatif");
        } else {
            this.prixChaussures = prixChaussures;
        }
    }

    /**
     * Méthode permettant de définir le prix de l'alliance de la personne de type Marie
     * @param prixAlliance le prix de l'alliance
     */
    public void setPrixAlliance(double prixAlliance) throws MariageException {
        if (prixAlliance < 0) {
            throw new MariageException("Le prix de l'alliance ne peut pas être négatif ou null");
        } else {
            this.prixAlliance = prixAlliance;
        }
    }

    /**
     * Méthode permettant de définir l'email de la personne de type Marie
     * @param email l'email de la personne
     */
    public void setEmail(String email) throws MariageException {
        if (email == null || email.isEmpty()) {
            throw new MariageException("L'email ne peut pas être nul");
        } else {
            this.email = email;
        }
    }

    /**
     * Méthode permettant de définir le numéro de téléphone de la personne de type Marie
     * @param numtel le numéro de téléphone de la personne
     */
    public void setNumtel(String numtel) throws MariageException {
        if (numtel == null || numtel.isEmpty()) {
            throw new MariageException("Le numéro de téléphone ne peut pas être nul");
        } else {
            this.numtel = numtel;
        }
    }
}
