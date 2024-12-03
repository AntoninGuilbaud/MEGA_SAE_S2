package fr.uga.iut2.genevent.vue;

import fr.uga.iut2.genevent.exception.UtilisateurException;
import fr.uga.iut2.genevent.modele.Mariage;

import java.time.LocalDate;
import java.util.Set;


public abstract class IHM {
    /**
     * Classe conteneur pour les informations saisies à propos d'un
     * {@link fr.uga.iut2.genevent.modele.Utilisateur}.
     *
     * <ul>
     * <li>Tous les attributs sont `public` par commodité d'accès.</li>
     * <li>Tous les attributs sont `final` pour ne pas être modifiables.</li>
     * </ul>
     */
    public static class InfosUtilisateur {
        public final String email;
        public final String nom;
        public final String prenom;

        public InfosUtilisateur(final String email, final String nom, final String prenom) {
            this.email = email;
            this.nom = nom;
            this.prenom = prenom;
        }
    }

    /**
     * Classe conteneur pour les informations saisies pour un nouvel
     * {@link fr.uga.iut2.genevent.modele.Evenement}.
     *
     * <ul>
     * <li>Tous les attributs sont `public` par commodité d'accès.</li>
     * <li>Tous les attributs sont `final` pour ne pas être modifiables.</li>
     * </ul>
     */
    public static class InfosNouvelEvenement {
        public final String nom;
        public final LocalDate dateDebut;
        public final LocalDate dateFin;
        public final InfosUtilisateur admin;

        public InfosNouvelEvenement(final String nom, final LocalDate dateDebut, final LocalDate dateFin, final InfosUtilisateur admin) {
            assert !dateDebut.isAfter(dateFin);
            this.nom = nom;
            this.dateDebut = dateDebut;
            this.dateFin = dateFin;
            this.admin = admin;
        }
    }

    /**
     * Classe conteneur pour les informations saisies pour un nouvel
     * {@link fr.uga.iut2.genevent.modele.Evenement}.
     *
     * <ul>
     * <li>Tous les attributs sont `public` par commodité d'accès.</li>
     * <li>Tous les attributs sont `final` pour ne pas être modifiables.</li>
     * </ul>
     */
    public static class InfosNouveauContact {
        public final String nom;
        public final String mail;
        public final String numTel;
        public final String profession;
        public final double honoraire;
        public final InfosUtilisateur admin;

        public InfosNouveauContact(final String nom, final String mail, final String numTel, final String profession, final double honoraire, final InfosUtilisateur admin) {
            this.nom = nom;
            this.mail = mail;
            this.numTel = numTel;
            this.profession = profession;
            this.honoraire = honoraire;
            this.admin = admin;
        }
    }
    
    public static class InfosNouveauMariage {

        public final Mariage marriage;
        public final InfosUtilisateur admin;

        public InfosNouveauMariage(final Mariage mariage,  final InfosUtilisateur admin) {
            this.marriage = mariage;
            this.admin = admin;
        }
    }


    /**
     * Rend actif l'interface Humain-machine.
     *
     * L'appel est bloquant : le contrôle est rendu à l'appelant une fois que
     * l'IHM est fermée.
     *
     */
    public abstract void demarrerInteraction() throws UtilisateurException;

    /**
     * Affiche un message d'information à l'attention de l'utilisa·teur/trice.
     *
     * @param msg Le message à afficher.
     *
     * @param succes true si le message informe d'une opération réussie, false
     *     sinon.
     */
    public abstract void informerUtilisateur(final String msg, final boolean succes);

    /**
     * Récupère les informations à propos d'un
     * {@link fr.uga.iut2.genevent.modele.Utilisateur}.
     *
     */
    public abstract void saisirUtilisateur() throws UtilisateurException;

    public abstract void afficherUtilisateurs(final Set<String> nomsExistants);

    /**
     * Récupère les informations nécessaires à la création d'un nouvel
     * {@link fr.uga.iut2.genevent.modele.Evenement}.
     *
     * @param nomsExistants L'ensemble des noms d'évenements qui ne sont plus
     *     disponibles.
     *
     */
    public abstract void saisirNouvelEvenement(final Set<String> nomsExistants) throws UtilisateurException;
}
