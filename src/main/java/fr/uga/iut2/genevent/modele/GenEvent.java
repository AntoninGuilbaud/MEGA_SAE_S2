package fr.uga.iut2.genevent.modele;

import fr.uga.iut2.genevent.exception.MariageException;
import fr.uga.iut2.genevent.exception.UtilisateurException;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;


public class GenEvent implements Serializable {

    private static final long serialVersionUID = 1L;  // nécessaire pour la sérialisation
    private final Map<String, Utilisateur> utilisateurs;  // association qualifiée par l'email
    private final Map<String, Evenement> evenements;  // association qualifiée par le nom
    private final Map<String, Contact> contacts;  // association qualifiée par le nom
    private final Map<String, Mariage> mariages;  // association qualifiée par le nom

    public GenEvent() {
        this.utilisateurs = new HashMap<>();
        this.evenements = new HashMap<>();
        this.contacts = new HashMap<>();
        this.mariages = new HashMap<>();
    }

    public boolean ajouteUtilisateur(String email, String nom, String prenom) throws UtilisateurException {
        if (this.utilisateurs.containsKey(email)) {
            return false;
        } else {
            this.utilisateurs.put(email, new Utilisateur(email, nom, prenom));
            return true;
        }
    }

    public void ajouteContact(String nom, String mail, String numTel, String profession, double honoraire, Utilisateur utilisateur) throws UtilisateurException, MariageException {
        // on ajoute un contact à un utilisateur
        utilisateur.ajouterContact(nom, mail, numTel, profession, honoraire);

        // on ajoute un contact à l'ensemble des contacts
        this.contacts.put(utilisateur.getEmail(), new Contact(nom, mail, numTel, profession, honoraire));
    }


    public void nouvelEvenement(String nom, LocalDate dateDebut, LocalDate dateFin, String adminEmail) {
        assert !this.evenements.containsKey(nom);
        assert this.utilisateurs.containsKey(adminEmail);
        Utilisateur admin = this.utilisateurs.get(adminEmail);
        Evenement evt = Evenement.initialiseEvenement(this, nom, dateDebut, dateFin, admin);
        this.evenements.put(nom, evt);
    }

    public void nouveauContact(String nom, String mail, String numTel, String profession, double honoraire, String adminEmail) throws UtilisateurException, MariageException {
        assert !this.contacts.containsKey(mail);
        assert this.utilisateurs.containsKey(adminEmail);
        Utilisateur admin = this.utilisateurs.get(adminEmail);
        // on ajoute un contact à un utilisateur
        admin.ajouterContact(nom, mail, numTel, profession, honoraire);
        // on créé un contact
        Contact contact = new Contact(nom, mail, numTel, profession, honoraire);
        // on l'ajoute à la liste des contacts
        this.contacts.put(mail, contact);
    }

    public void nouveauMariage(Mariage mariage, String adminEmail) throws UtilisateurException {
        assert !this.mariages.containsKey(mariage.getId());
        assert this.utilisateurs.containsKey(adminEmail);
        Utilisateur admin = this.utilisateurs.get(adminEmail);

        // on ajoute un mariage à un utilisateur
        admin.ajouterMariage(mariage);
        // on créé un mariage
        this.mariages.put(mariage.getId(), mariage);
    }


    public Map<String, Evenement> getEvenements() {
        return this.evenements;
    }
    public Map<String, Utilisateur> getUtilisateurs() {
        return this.utilisateurs;
    }
    public Map<String, Contact> getContacts() {
        return this.contacts;
    }

    public Map<String, Mariage> getMariages() {
        return this.mariages;
    }



}
