package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.exception.MariageException;
import fr.uga.iut2.genevent.exception.UtilisateurException;
import fr.uga.iut2.genevent.modele.GenEvent;
import fr.uga.iut2.genevent.modele.Utilisateur;
import fr.uga.iut2.genevent.vue.IHM;
import fr.uga.iut2.genevent.vue.JavaFXGUI;

import java.util.Map;


public class Controleur {

    private final GenEvent genevent;
    private final IHM ihm;

    private Map<String, Utilisateur> utilisateurs;

    public Controleur(GenEvent genevent) {
        this.genevent = genevent;
        setUtilisateurs(genevent.getUtilisateurs());

        // choisir la classe CLI ou JavaFXGUI
//        this.ihm = new CLI(this);
        this.ihm = new JavaFXGUI(this);
    }

    public void demarrer() throws UtilisateurException {
        this.ihm.demarrerInteraction();
    }

    public void saisirUtilisateur() throws UtilisateurException {
        this.ihm.saisirUtilisateur();
    }

    public void setUtilisateurs(Map<String, Utilisateur> utilisateurs) {
        this.utilisateurs = utilisateurs;
    }

    public Map<String, Utilisateur> getUtilisateurs() {
        return utilisateurs;
    }

    public void creerUtilisateur(IHM.InfosUtilisateur infos) throws UtilisateurException {
        boolean nouvelUtilisateur = this.genevent.ajouteUtilisateur(
                infos.email,
                infos.nom,
                infos.prenom
        );
        if (nouvelUtilisateur) {
            this.ihm.informerUtilisateur(
                    "Nouvel·le utilisa·teur/trice : " + infos.prenom + " " + infos.nom + " <" + infos.email + ">",
                    true
            );
        } else {
            this.ihm.informerUtilisateur(
                    "L'utilisa·teur/trice " + infos.email + " est déjà connu·e de GenEvent.",
                    false
            );
        }
    }

    public void afficherUtilisateurs() {
        this.ihm.afficherUtilisateurs(this.genevent.getEvenements().keySet());
    }

    public void saisirEvenement() throws UtilisateurException {
        this.ihm.saisirNouvelEvenement(this.genevent.getEvenements().keySet());
    }

    public void creerEvenement(IHM.InfosNouvelEvenement infos) throws UtilisateurException {
        // création d'un Utilisateur si nécessaire
        boolean nouvelUtilisateur = this.genevent.ajouteUtilisateur(
                infos.admin.email,
                infos.admin.nom,
                infos.admin.prenom
        );
        if (nouvelUtilisateur) {
            this.ihm.informerUtilisateur("Nouvel·le utilisa·teur/trice : " + infos.admin.prenom + " " + infos.admin.nom + " <" + infos.admin.email + ">",
                    true
            );
        }

        this.genevent.nouvelEvenement(
                infos.nom,
                infos.dateDebut,
                infos.dateFin,
                infos.admin.email
        );
        this.ihm.informerUtilisateur(
                "Nouvel évènement : " + infos.nom + ", administré par " + infos.admin.email,
                true
        );
    }

    public void creerContact(IHM.InfosNouveauContact infos) throws UtilisateurException, MariageException {
        // création d'un Utilisateur si nécessaire
        boolean nouvelUtilisateur = this.genevent.ajouteUtilisateur(
                infos.admin.email,
                infos.admin.nom,
                infos.admin.prenom
        );
        if (nouvelUtilisateur) {
            this.ihm.informerUtilisateur("Nouvel·le utilisa·teur/trice : " + infos.admin.prenom + " " + infos.admin.nom + " <" + infos.admin.email + ">",
                    true
            );
        }

        this.genevent.nouveauContact(
                infos.nom,
                infos.mail,
                infos.numTel,
                infos.profession,
                infos.honoraire,
                infos.admin.email
        );
        this.ihm.informerUtilisateur(
                "Nouveau contact : " + infos.nom + ", administré par " + infos.admin.email,
                true
        );
    }

    public void creerMariage(IHM.InfosNouveauMariage infos) throws UtilisateurException {
        // création d'un Utilisateur si nécessaire
        boolean nouvelUtilisateur = this.genevent.ajouteUtilisateur(
                infos.admin.email,
                infos.admin.nom,
                infos.admin.prenom
        );
        if (nouvelUtilisateur) {
            this.ihm.informerUtilisateur("Nouvel·le utilisa·teur/trice : " + infos.admin.prenom + " " + infos.admin.nom + " <" + infos.admin.email + ">",
                    true
            );
        }

        this.genevent.nouveauMariage(
                infos.marriage,
                infos.admin.email
        );

    }

    public GenEvent getGenevent() {
        return genevent;
    }

}
