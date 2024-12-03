package fr.uga.iut2.genevent.modele;

import fr.uga.iut2.genevent.exception.MariageException;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe Repas qui permet de définir un repas avec un moment de repas, une liste de plats et une liste de boissons
 */
public class Repas implements Serializable  {

    private static final long serialVersionUID = 1L;  // nécessaire pour la sérialisation

    // Attributs
    private MomentRepas momentRepas;
    private ArrayList<Plat> plats = new ArrayList<Plat>();
    private ArrayList<Boisson> boissons = new ArrayList<Boisson>();


    /**
     * Constructeur de la classe Repas permettant de définir un repas avec un moment de repas
     * @param momentRepas le moment du repas
     */
    public Repas(MomentRepas momentRepas){
        this.momentRepas = momentRepas;
    }

    // GETTERS

    /**
     * Méthode permettant de récupérer le moment du repas
     * @return momentRepas
     */
    public MomentRepas getMomentRepas() {
        return momentRepas;
    }

    /**
     * Méthode permettant de récupérer la liste des plats
     * @return plats
     */
    public ArrayList<Plat> getPlats() {
        return plats;
    }

    /**
     * Méthode permettant de récupérer la liste des boissons
     * @return boissons
     */
    public ArrayList<Boisson> getBoissons() {
        return boissons;
    }

    // Méthodes d'ajout d'éléments : Plat, Boisson

    /**
     * Méthode qui ajoute un plat à la liste des plats
     * @param plat un plat
     */
    public void addPlat(Plat plat) throws MariageException {
        if (plat == null) {
            throw new MariageException("Le plat ne peut pas être vide");
        } else {
            plats.add(plat);
        }
    }

    /**
     * Méthode qui ajoute une boisson à la liste des boissons
     * @param boisson une boisson
     */
    public void addBoisson(Boisson boisson) throws MariageException {
        if (boisson == null) {
            throw new MariageException("La boisson ne peut pas être vide");
        } else {
            boissons.add(boisson);
        }
    }


    /**
     * Méthode qui ajoute une liste complète de plats à notre Repas
     * @param plats une liste de plats
     */
    public void addListPlats(ArrayList<Plat> plats) throws MariageException {
        for (Plat plat : plats) {
            if (plat == null) {
                throw new MariageException("Le plat ne peut pas être vide");
            } else {
                this.plats.add(plat);
            }
        }
    }


}
