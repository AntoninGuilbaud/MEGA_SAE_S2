package fr.uga.iut2.genevent.modele;

import fr.uga.iut2.genevent.exception.MariageException;

import java.io.Serializable;

/**
 * Classe Lieux qui permet de définir un lieu avec un nom, une adresse, un coût de location, une capacité et un logement
 */
public class Lieux implements Serializable {
    private static final long serialVersionUID = 1L;  // nécessaire pour la sérialisation

    // Attributs
    private String nom;
    private String adresse;
    private double coutLocation;
    private int capacite;
    private boolean logement;

    /**
     * Constructeur de la classe Lieux permettant de définir un lieu avec un nom, une adresse, un coût de location, une capacité et un logement
     * @param nom le nom du lieu
     * @param adresse l'adresse du lieu
     * @param coutLocation le coût de location du lieu
     * @param capacite la capacité du lieu
     * @param logement un booléen indiquant si le lieu propose un logement
     */
    public Lieux(String nom, String adresse, double coutLocation, int capacite, boolean logement) throws MariageException {
        setNom(nom);
        setAdresse(adresse);
        setCoutLocation(coutLocation);
        setCapacite(capacite);
        setLogement(logement);
    }

    // Getteurs

    /**
     * Méthode permettant de récupérer le nom du lieu
     * @return nom le nom du lieu
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * Méthode permettant de récupérer l'adresse du lieu
     * @return adresse l'adresse du lieu
     */
    public String getAdresse() {
        return this.adresse;
    }

    /**
     * Méthode permettant de récupérer le coût de location du lieu
     * @return coutLocation le coût de location du lieu
     */
    public double getCoutLocation() {
        return this.coutLocation;
    }

    /**
     * Méthode permettant de récupérer la capacité du lieu
     * @return capacite la capacité du lieu
     */
    public int getCapacite() {
        return this.capacite;
    }

    /**
     * Méthode permettant de récupérer le logement du lieu
     * @return logement un booléen indiquant si le lieu propose un logement
     */
    public boolean getLogement() {
        return this.logement;
    }

    // Setteurs

    /**
     * Méthode permettant de définir le nom du lieu
     * @param nom le nom du lieu
     */
    public void setNom(String nom) throws MariageException {
        if (nom == null || nom.isEmpty()) {
            throw new MariageException("Le nom du lieu ne peut pas être vide");
        } else {
            this.nom = nom;
        }
    }

    /**
     * Méthode permettant de définir l'adresse du lieu
     * @param adresse l'adresse du lieu
     */
    public void setAdresse(String adresse) throws MariageException {
        if (adresse == null || adresse.isEmpty()) {
            throw new MariageException("L'adresse du lieu ne peut pas être vide");
        } else {
            this.adresse = adresse;
        }

    }

    /**
     * Méthode permettant de définir le coût de location du lieu
     * @param coutLocation le coût de location du lieu
     */
    public void setCoutLocation(double coutLocation) throws MariageException {
        if (coutLocation < 0) {
            throw new MariageException("Le coût de location du lieu ne peut pas être négatif");
        } else {
            this.coutLocation = coutLocation;
        }
    }

    /**
     * Méthode permettant de définir la capacité du lieu
     * @param capacite la capacité du lieu
     */
    public void setCapacite(int capacite) throws MariageException {
        if (capacite < 0) {
            throw new MariageException("La capacité du lieu ne peut pas être négative");
        } else {
            this.capacite = capacite;
        }
    }

    /**
     * Méthode permettant de définir le logement du lieu
     * @param logement un booléen indiquant si le lieu propose un logement
     */
    public void setLogement(boolean logement) throws MariageException {
        this.logement = logement;
    }
}
