package fr.uga.iut2.genevent.modele;

import fr.uga.iut2.genevent.exception.MariageException;
import fr.uga.iut2.genevent.exception.UtilisateurException;
import org.apache.commons.validator.routines.EmailValidator;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Cette classe représente un utilisateur de l'application.
 */
public class Utilisateur implements Serializable {

    private static final long serialVersionUID = 1L;  // nécessaire pour la sérialisation
    private final String email;
    private String nom;
    private String prenom;
    private String telephone;
    private String siret;
    private final Map<String, Evenement> evenementsAdministres;  // association qualifiée par le nom
    private Map<String, Contact> contacts;
    private Map<String, Mariage> mariagesAdministres;


    /**
     * Constructeur de la classe Utilisateur qui initialise un utilisateur avec un email, un nom et un prénom.
     * @param email l'email de l'utilisateur
     * @param nom le nom de l'utilisateur
     * @param prenom le prénom de l'utilisateur
     */
    public Utilisateur(String email, String nom, String prenom)  throws UtilisateurException  {
        assert EmailValidator.getInstance(false, false).isValid(email);
        this.email = email;
        setNom(nom);
        setPrenom(prenom);
        // on initialise les listes des événements, des contacts et des mariages administrés
        this.evenementsAdministres = new HashMap<>();
        this.contacts = new HashMap<>();
        this.mariagesAdministres = new HashMap<>();
    }

    // GETTERS

    /**
     * Retourne l'email de l'utilisateur.
     * @return l'email de l'utilisateur
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Retourne le nom de l'utilisateur.
     * @return le nom de l'utilisateur
     */
    public String getNom() {
        return nom;
    }

    /**
     * Retourne le prénom de l'utilisateur.
     * @return le prénom de l'utilisateur
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Retourne le numéro de téléphone de l'utilisateur.
     * @return le numéro de téléphone de l'utilisateur
     */
    public String getTelephone() {
        return this.telephone;
    }

    /**
     * Retourne le numéro de SIRET de l'utilisateur.
     * @return le numéro de SIRET de l'utilisateur
     */
    public String getSiret() {
        return this.siret;
    }

    /**
     * Retourne la liste des contacts de l'utilisateur.
     * @return la liste des contacts de l'utilisateur
     */
    public Map<String, Contact> getContacts(){
        return this.contacts;
    }

    /**
     * Retourne la liste des mariages administrés par l'utilisateur.
     * @return la liste des mariages administrés par l'utilisateur
     */
    public Map<String, Mariage> getMariages(){
        return this.mariagesAdministres;
    }

    /**
     * Retourne la liste des événements administrés par l'utilisateur.
     * @return la liste des événements administrés par l'utilisateur
     */
    public Map<String, Evenement> getEvenementsAdministres() {
        return this.evenementsAdministres;
    }




    // Setteurs

    /**
     * Modifie le nom de l'utilisateur.
     * @param nom le nouveau nom de l'utilisateur
     */
    public void setNom(String nom) throws UtilisateurException {
        if (nom == null || nom.isEmpty()) {
            throw new UtilisateurException("Le nom ne peut pas être vide.");
        } else {
            this.nom = nom;
        }
    }

    /**
     * Modifie le prénom de l'utilisateur.
     * @param prenom le nouveau prénom de l'utilisateur
     */
    public void setPrenom(String prenom) throws UtilisateurException {
        if (prenom == null || prenom.isEmpty()) {
            throw new UtilisateurException("Le prénom ne peut pas être vide.");
        } else {
            this.prenom = prenom;
        }
    }

    /**
     * Modifie le numéro de téléphone de l'utilisateur.
     * @param telephone le nouveau numéro de téléphone de l'utilisateur
     */
    public void setTelephone(String telephone) throws UtilisateurException {
        if (telephone == null || telephone.isEmpty()) {
            throw new UtilisateurException("Le numéro de téléphone ne peut pas être vide.");
        } else {
            this.telephone = telephone;
        }
    }

    /**
     * Modifie le numéro de SIRET de l'utilisateur.
     * @param siret le nouveau numéro de SIRET de l'utilisateur
     */
    public void setSiret(String siret) throws UtilisateurException {
        if (siret == null || siret.isEmpty()) {
            throw new UtilisateurException("Le numéro de SIRET ne peut pas être vide.");
        } else {
            this.siret = siret;
        }
    }

    /**
     * Modifie la liste des contacts de l'utilisateur.
     * @param contacts la nouvelle liste des contacts de l'utilisateur
     */
    public void setContacts(Map<String, Contact> contacts){
        this.contacts = contacts;
    }

    /**
     * Modifie la liste des mariages administrés par l'utilisateur.
     * @param mariages la nouvelle liste des mariages administrés par l'utilisateur
     */
    public void setMariages(Map<String, Mariage> mariages){
        this.mariagesAdministres = mariages;
    }

    // Méthodes

    /**
     * Ajoute un événement administré par l'utilisateur.
     * @param evt l'événement à ajouter
     */
    public void ajouteEvenementAdministre(Evenement evt) {
        assert !this.evenementsAdministres.containsKey(evt.getNom());
        this.evenementsAdministres.put(evt.getNom(), evt);
    }

    /**
     * Ajoute un contact à la liste des contacts de l'utilisateur.
     * @param nom le nom du contact
     * @param mail l'adresse mail du contact
     * @param tel le numéro de téléphone du contact
     * @param profession la profession du contact
     * @param honoraires les honoraires du contact
     */
    public void ajouterContact(String nom, String mail, String tel, String profession, double honoraires) throws UtilisateurException, MariageException {
        if(this.contacts.containsKey(mail)){
            throw new UtilisateurException("Le contact existe déjà.");
        } else {
            // on ajoute le nouveau contact à la liste des contacts
            this.contacts.put(mail, new Contact(nom, mail, tel, profession, honoraires));
        }
    }

    /**
     * Ajoute un mariage à la liste des mariages administrés par l'utilisateur.
     * @param mariage le mariage à ajouter
     */
    public void ajouterMariage(Mariage mariage) throws UtilisateurException {
        if (this.mariagesAdministres.containsKey(mariage.getId())) {
            throw new UtilisateurException("Le mariage existe déjà.");
        } else {
            // on ajoute le mariage à la liste des mariages administrés
            this.mariagesAdministres.put(mariage.getId(), mariage);
        }
    }

    /**
     * Supprime un contact de la liste des contacts de l'utilisateur.
     * @param mail l'adresse mail du contact à supprimer
     */
    public void supprimerContact(String mail){
        // on supprime le contact de la liste des contacts
        this.contacts.remove(mail);
    }
}
