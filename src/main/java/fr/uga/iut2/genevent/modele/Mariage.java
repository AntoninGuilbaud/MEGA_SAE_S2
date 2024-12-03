package fr.uga.iut2.genevent.modele;

import fr.uga.iut2.genevent.exception.MariageException;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Classe Mariage qui permet de définir un mariage avec une date, un nom, un thème, un nombre d'adultes, un nombre d'enfants et un nombre d'invités au vin d'honneur
 */
public class Mariage implements Serializable {

    private static final long serialVersionUID = 1L;  // nécessaire pour la sérialisation

    // Attributs
    private String id;
    private LocalDate date;
    private String nom;
    private String theme;
    private int nombreAdultes;
    private int nombreEnfants;
    private int nombreInvitesVinHonneur;
    private ArrayList<Contact> intervenants = new ArrayList<>();
    private ArrayList<GroupeMusique> groupes = new ArrayList<>();
    private ArrayList<Repas> repas = new ArrayList<>();
    private ArrayList<Personne> invitesImportants = new ArrayList<>();
    private ArrayList<Personne> marie = new ArrayList<>();
    private Voiture voiture;
    private ArrayList<Lieux> lieux =new ArrayList<>();

    private boolean isDeleted = false;

    /**
     * Constructeur de la classe Mariage permettant de définir un mariage avec une date, un nom, un thème, un nombre d'adultes, un nombre d'enfants et un nombre d'invités au vin d'honneur
     * @param id l'identifiant du mariage
     * @param date la date du mariage
     * @param nom le nom du mariage
     * @param theme le thème du mariage
     * @param nombreAdultes le nombre d'adultes du mariage
     * @param nombreEnfants le nombre d'enfants du mariage
     * @param nombreInvitesVinHonneur le nombre d'invités au vin d'honneur du mariage
     */
    public Mariage(String id, LocalDate date, String nom, String theme, int nombreAdultes, int nombreEnfants, int nombreInvitesVinHonneur) throws MariageException {
        setId(id);
        setDate(date);
        setNom(nom);
        setTheme(theme);
        setNombreAdultes(nombreAdultes);
        setNombreEnfants(nombreEnfants);
        setNombreInvitesVinHonneur(nombreInvitesVinHonneur);
    }

    /**
     * Constructeur de la classe Mariage par défault
     */
    public Mariage() {
    }

    // Getteurs

    /**
     * Méthode permettant de récupérer l'identifiant du mariage
     * @return id l'identifiant du mariage
     */
    public String getId() {
        return this.id;
    }

    /**
     * Méthode permettant de récupérer la date du mariage
     * @return date la date du mariage
     */
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Méthode permettant de récupérer le nom du mariage
     * @return nom le nom du mariage
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * Méthode permettant de récupérer le prénom du marié 1
     * @return prenom le prénom du marié 1
     */
    public Marie getMarie1(){
        if (marie.size() == 0)
            return null;
        return (Marie) marie.get(0);
    }

    /**
     * Méthode permettant de récupérer le prénom du marié 2
     * @return prenom le prénom du marié 2
     */
    public Marie getMarie2(){
        if (marie.size() == 0)
            return null;
        return (Marie) marie.get(1);
    }

    /**
     * Méthode permettant de récupérer le thème du mariage
     * @return theme le thème du mariage
     */
    public String getTheme() {
        return this.theme;
    }

    /**
     * Méthode permettant de récupérer le nombre d'adultes du mariage
     * @return nombreAdultes le nombre d'adultes du mariage
     */
    public int getNombreAdultes() {
        return this.nombreAdultes;
    }

    /**
     * Méthode permettant de récupérer le nombre d'enfants du mariage
     * @return nombreEnfants le nombre d'enfants du mariage
     */
    public int getNombreEnfants() {
        return this.nombreEnfants;
    }

    /**
     * Méthode permettant de récupérer le nombre d'invités au vin d'honneur du mariage
     * @return nombreInvitesVinHonneur le nombre d'invités au vin d'honneur du mariage
     */
    public int getNombreInvitesVinHonneur() {
        return this.nombreInvitesVinHonneur;
    }

    /**
     * Méthode permettant de récupérer le prix total du mariage
     * @return prix le prix total du mariage
     */
    public double getPrix(){
        double prix = 0.0;

        if(getMarie1() != null) {
            prix += getMarie1().getPrixAlliance() + getMarie1().getPrixChaussures() + getMarie1().getPrixVetement();
        }
        if(getMarie2() != null) {
            prix += getMarie2().getPrixAlliance() + getMarie2().getPrixChaussures() + getMarie2().getPrixVetement();
        }


        // on parcoure les repas pour calculer le prix
        for (Repas repas : this.repas) {
            for (Plat plat : repas.getPlats()) {
                prix = plat.getPrixParPersonne() * (this.nombreAdultes + this.nombreEnfants);
            }
            for (Boisson boisson : repas.getBoissons()) {
                prix = boisson.getPrixParPersonne() * (this.nombreAdultes + this.nombreEnfants);
            }
        }
        // on parcoure les groupes de musique pour calculer le prix
        for (GroupeMusique groupe : this.groupes) {
            prix += groupe.getPrix();
        }
        // on parcoure les intervenants pour calculer le prix
        for (Contact intervenant : this.intervenants) {
            prix += intervenant.getHonoraire();
        }
        // on parcoure les lieux pour calculer le prix
        for (Lieux lieu : this.lieux) {
            prix += lieu.getCoutLocation();
        }
        // on ajoute le prix de la voiture
        if (this.voiture != null){
            prix += this.voiture.getPrix();
        }
        // on retourne le prix
        return prix;
    }

    /**
     * Méthode permettant de savoir si un mariage est archivé
     * @return isDeleted un booléen qui indique si le mariage est archivé, true si le mariage est archivé, false sinon
     */
    public boolean isDeleted() {
        return isDeleted;
    }

    // Setteurs

    /**
     * Méthode permettant de définir si le mariage est archivé
     * @param deleted un booléen qui indique si le mariage est archivé, true si le mariage est archivé, false sinon
     */
    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }


    /**
     * Méthode permettant de définir l'identifiant du mariage
     * @param id l'identifiant du mariage
     */
    public void setId(String id) throws MariageException {
        // si l'identifiant est vide, on lève une exception
        if (id == null || id.isEmpty()) {
            throw new MariageException("L'identifiant du mariage ne peut pas être vide");
        } else {
            this.id = id;
        }
    }

    /**
     * Méthode permettant de définir la date du mariage
     * @param date la date du mariage
     */
    public void setDate(LocalDate date) throws MariageException {
        // si la date est vide, on lève une exception
        if (date == null) {
            throw new MariageException("La date du mariage ne peut pas être vide");
        } else {
            this.date = date;
        }
    }

    /**
     * Méthode permettant de définir le nom du mariage
     * @param nom le nom du mariage
     */
    public void setNom(String nom) throws MariageException {
        // si le nom est vide, on lève une exception
        if (nom == null || nom.isEmpty()) {
            throw new MariageException("Le nom du mariage ne peut pas être vide");
        } else {
            this.nom = nom;
        }
    }

    /**
     * Méthode permettant de définir le théme du mariage
     * @param theme le thème du mariage
     */
    public void setTheme(String theme) throws MariageException {
        // si le thème est vide, on lève une exception
        if (theme == null || theme.isEmpty()) {
            throw new MariageException("Le thème du mariage ne peut pas être vide");
        } else {
            this.theme = theme;
        }

    }

    /**
     * Méthode permettant de définir le nombre d'adultes du mariage
     * @param nombreAdultes le nombre d'adultes du mariage
     */
    public void setNombreAdultes(int nombreAdultes) throws MariageException {
        // si le nombre d'adultes est négatif, on lève une exception
        if (nombreAdultes < 0) {
            throw new MariageException("Le nombre d'adultes ne peut pas être négatif");
        } else {
            this.nombreAdultes = nombreAdultes;
        }
    }

    /**
     * Méthode permettant de définir le nombre d'enfants du mariage
     * @param nombreEnfants le nombre d'enfants du mariage
     */
    public void setNombreEnfants(int nombreEnfants) throws MariageException {
        // si le nombre d'enfants est négatif, on lève une exception
        if (nombreEnfants < 0) {
            throw new MariageException("Le nombre d'enfants ne peut pas être négatif");
        } else {
            this.nombreEnfants = nombreEnfants;
        }
    }

    /**
     * Méthode permettant de définir le nombre d'invités au vin d'honneur du mariage
     * @param nombreInvitesVinHonneur le nombre d'invités au vin d'honneur du mariage
     */
    public void setNombreInvitesVinHonneur(int nombreInvitesVinHonneur) throws MariageException {
        if (nombreInvitesVinHonneur < 0) {
            throw new MariageException("Le nombre d'invités au vin d'honneur ne peut pas être négatif");
        } else {
            this.nombreInvitesVinHonneur = nombreInvitesVinHonneur;
        }
    }

    /**
     * Méthode permettant de définir les intervenants du mariage
     * @param intervenants les intervenants du mariage
     */
    public void setIntervenants(ArrayList<Contact> intervenants) throws MariageException{
        // si la liste des intervenants est vide, on lève une exception
        if (intervenants == null || intervenants.isEmpty()) {
            throw new MariageException("La liste des intervenants ne peut pas être vide");
        } else {
            this.intervenants = intervenants;
        }
    }

    /**
     * Méthode permettant de définir les groupes de musique du mariage
     * @param groupes les groupes de musique du mariage
     */
    public void setGroupes(ArrayList<GroupeMusique> groupes) throws MariageException{
        // si la liste des groupes de musique est vide, on lève une exception
        if (groupes == null || groupes.isEmpty()) {
            throw new MariageException("La liste des groupes de musique ne peut pas être vide");
        } else {
            this.groupes = groupes;
        }
    }



    /**
     * Méthode permettant de définir les repas du mariage
     * @param repas les repas du mariage
     */
    public void setRepas(ArrayList<Repas> repas) throws MariageException {
        if (repas == null || repas.isEmpty()) {
            throw new MariageException("La liste des repas ne peut pas être vide");
        } else {
            this.repas = repas;
        }
    }

    /**
     * Méthode permettant de définir les invités importants du mariage
     * @param invitesImportants les invités importants du mariage
     */
    public void setInvitesImportants(ArrayList<Personne> invitesImportants) throws MariageException {
        // si la liste des invités importants est vide, on lève une exception
        if (invitesImportants == null || invitesImportants.isEmpty()) {
            throw new MariageException("La liste des invités importants ne peut pas être vide");
        } else {
            this.invitesImportants = invitesImportants;
        }
    }

    /**
     * Méthode permettant de définir les mariés du mariage
     * @param marie les mariés du mariage
     */
    public void setMarie(ArrayList<Personne> marie) throws MariageException {
        if (marie == null || marie.isEmpty()) {
            throw new MariageException("La liste des mariés ne peut pas être vide");
        } else {
            this.marie = marie;
        }
    }

    /**
     * Méthode permettant de définir la voiture du mariage
     * @param voiture la voiture du mariage
     */
    public void setVoiture(Voiture voiture) throws MariageException {
        // si la voiture est vide, on lève une exception
        if (voiture == null) {
            throw new MariageException("La voiture ne peut pas être vide");
        } else {
            this.voiture = voiture;
        }
    }

    /**
     * Méthode permettant de définir les lieux du mariage
     * @param lieux les lieux du mariage
     */
    public void setLieux(ArrayList<Lieux> lieux) throws MariageException {
        // si la liste des lieux est vide, on lève une exception
        if (lieux == null || lieux.isEmpty()) {
            throw new MariageException("La liste des lieux ne peut pas être vide");
        } else {
            this.lieux = lieux;
        }
    }

    // Guetteurs

    /**
     * Méthode permettant de récupérer les intervenants du mariage
     * @return intervenants une liste des intervenants du mariage
     */
    public ArrayList<Contact> getIntervenants()  {
        return intervenants;
    }

    /**
     * Méthode permettant de récupérer les groupes de musique du mariage
     * @return groupes une liste des groupes de musique du mariage
     */
    public ArrayList<GroupeMusique> getGroupes() {
        return groupes;
    }

    /**
     * Méthode permettant de récupérer les repas du mariage
     * @return repas une liste des repas du mariage
     */
    public ArrayList<Repas> getRepas() {
        return repas;
    }

    /**
     * Méthode permettant de récupérer les invités importants du mariage
     * @return invitesImportants une liste des invités importants du mariage
     */
    public ArrayList<Personne> getInvitesImportants() {
        return invitesImportants;
    }

    /**
     * Méthode permettant de récupérer les mariés du mariage
     * @return marie une liste des mariés du mariage
     */
    public ArrayList<Personne> getMarie() {
        return marie;
    }

    /**
     * Méthode permettant de récupérer la voiture du mariage
     * @return voiture la voiture du mariage
     */
    public Voiture getVoiture() {
        return voiture;
    }


    /**
     * Méthode permettant de récupérer les lieux du mariage
     * @return lieux une liste des lieux du mariage
     */
    public ArrayList<Lieux> getLieux() {
        return lieux;
    }



    // Ajout d'éléments

    /**
     * Méthode permettant d'ajouter un groupe de musique au mariage
     * @param groupe le groupe de musique à ajouter
     */
    public void addGroupe(GroupeMusique groupe) throws MariageException {
        // si le groupe de musique est vide, on lève une exception
        if (groupe == null) {
            throw new MariageException("Le groupe de musique ne peut pas être vide");
        } else {
            this.groupes.add(groupe);
        }
    }

    /**
     * Méthode permettant d'ajouter un lieu au mariage
     * @param lieu le lieu à ajouter
     */
    public void addLieux(Lieux lieu) throws MariageException {
        // si le lieu est vide, on lève une exception
        if (lieu == null) {
            throw new MariageException("Le lieu ne peut pas être vide");
        } else {
            this.lieux.add(lieu);
        }
    }

    /**
     * Méthode permettant d'ajouter un repas au mariage
     * @param repas le repas à ajouter
     */
    public void addRepas(Repas repas) throws MariageException {
        // si le repas est vide, on lève une exception
        if (repas == null) {
            throw new MariageException("Le repas ne peut pas être vide");
        } else {
            this.repas.add(repas);
        }
    }

    /**
     * Méthode permettant d'ajouter un intervenant au mariage
     * @param intervenant l'intervenant à ajouter
     */
    public void addIntervenant(Contact intervenant) throws MariageException {
        // si l'intervenant est vide, on lève une exception
        if (intervenant == null) {
            throw new MariageException("L'intervenant ne peut pas être vide");
        } else {
            this.intervenants.add(intervenant);
        }
    }

    /**
     * Méthode permettant d'ajouter un invité important au mariage
     * @param invite l'invité important à ajouter
     */
    public void addInvite(Personne invite) throws MariageException {
        // si l'invité important est vide, on lève une exception
        if (invite == null) {
            throw new MariageException("L'invité important ne peut pas être vide");
        } else {
            this.invitesImportants.add(invite);
        }
    }

    /**
     * Méthode permettant d'ajouter un marié au mariage
     * @param marie le marié à ajouter
     */
    public void addMarie(Personne marie) throws MariageException {
        // si le marié est vide, on lève une exception
        if (marie == null) {
            throw new MariageException("Le marié ne peut pas être vide");
        } else {
            this.marie.add(marie);
        }
    }
}
