package fr.uga.iut2.genevent.vue;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.view.CalendarView;
import fr.uga.iut2.genevent.Main;
import fr.uga.iut2.genevent.controleur.Controleur;
import fr.uga.iut2.genevent.exception.MariageException;
import fr.uga.iut2.genevent.exception.UtilisateurException;
import fr.uga.iut2.genevent.modele.*;
import fr.uga.iut2.genevent.util.Persisteur;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.apache.commons.validator.routines.EmailValidator;
import org.controlsfx.control.ToggleSwitch;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;




/**
 * La classe JavaFXGUI est responsable des interactions avec
 * l'utilisa·teur/trice en mode graphique.
 * <p>
 * Attention, pour pouvoir faire le lien avec le
 * {@link fr.uga.iut2.genevent.controleur.Controleur}, JavaFXGUI n'est pas une
 * sous-classe de {@link javafx.application.Application} !
 * <p>
 * Le démarrage de l'application diffère des exemples classiques trouvés dans
 * la documentation de JavaFX : l'interface est démarrée à l'initiative du
 * {@link fr.uga.iut2.genevent.controleur.Controleur} via l'appel de la méthode
 * {@link #demarrerInteraction()}.
 */
public class JavaFXGUI extends IHM {
    private final Controleur controleur;

    private final GenEvent genevent;
    private final CountDownLatch eolBarrier;  // /!\ ne pas supprimer /!\ : suivi de la durée de vie de l'interface

    // importation du LOGGER
    private static final Logger LOGGER = Logger.getLogger(JavaFXGUI.class.getName());


    // on récupère les utilisateurs
    private final Map<String, Utilisateur> utilisateurs = new HashMap<>();
    private Utilisateur utilisateurCourant;
    private Contact contactCourant;

    private boolean modifContactEnCours = false;
    private String valeurChangee = "";

    // Modéle de données
    private final ArrayList<Mariage> mariages = new ArrayList<>();
    private Mariage mariageCourant;
    int col = 0;
    int row = 0;

    // éléments vue nouvel·le utilisa·teur/trice
    @FXML private TextField newUserForenameTextField;
    @FXML private TextField newUserSurnameTextField;
    @FXML private TextField newUserEmailTextField;
    @FXML private Button newUserOkButton;
    @FXML private Button newUserCancelButton;

    // élément vue principale - Home Application
    @FXML private VBox vBoxMain;
    @FXML private GridPane gridPaneMariage;
    private GridPane gridPaneMariageSauv;

    // éléments vue -- Page Informations de l'utilisateur
    @FXML private TextField tfInfosNom;
    @FXML private TextField tfInfosPrenom;
    @FXML private TextField tfInfosSiret;
    @FXML private TextField tfInfosTel;
    @FXML private TextField tfInfosMail;
    @FXML private PasswordField pwdFInfosMdp;
    @FXML private VBox calendar;

    // éléments vue -- Page Contact
    @FXML private Button btnNewContact;
    @FXML private Label labelVboxContact;
    @FXML private Button btnModifierContact;
    @FXML private Button btnSupprimerContact;
    @FXML private GridPane gridpaneContact;
    @FXML private TextField tfContactNom;
    @FXML private TextField tfContactMail;
    @FXML private TextField tfContactTel;
    @FXML private TextField tfContactProfession;
    @FXML private TextField tfContactHonoraires;
    @FXML private GridPane gridPaneContact;

    @FXML private Button btnCreerContact;
    @FXML private TextField tfNewContactNom;
    @FXML private TextField tfNewContactMail;
    @FXML private TextField tfNewContactTel;
    @FXML private TextField tfNewContactProfession;
    @FXML private TextField tfNewContactHonoraires;


    // éléments vue -- Page Infos
    @FXML private Label labelTelInfo;
    @FXML private Label labelMailInfo;
    @FXML private Label labelSiretInfo;
    @FXML private Label labelMdpInfo;


    // éléments vue -- Page Nouvelles Infos
    @FXML private Label titleModifInfo;
    @FXML private Label labelAncienInfo;
    @FXML private Label labelNouveauInfo;
    @FXML private Label labelConfirmerInfo;

    @FXML private TextField tfAncienInfo;
    @FXML private TextField tfNouveauInfo;
    @FXML private TextField tfConfirmerInfo;

    @FXML private Button btnValiderInfo;
    @FXML private Button btnAnnulerInfo;

    // éléments HBox de nouveau mariage
    @FXML private ScrollPane spInvite;
    @FXML private ScrollPane spLieux;
    @FXML private HBox hbVoiture;
    @FXML private ScrollPane spPlat;
    @FXML private ScrollPane spBoisson;
    @FXML private ScrollPane spIntervenant;
    @FXML private ScrollPane spMusique;
    @FXML private VBox vbVoiture;
    @FXML private VBox vbMarie1;
    @FXML private VBox vbMarie2;
    @FXML private TextField tfNbInvitesAdultes;
    @FXML private TextField tfNbInvitesEnfants;
    @FXML private TextField tfNbInvitesVinHonneur;

    // éléments ajout groupe musique
    @FXML private TextField tfGPNom;
    @FXML private TextField tfGPPrix;
    @FXML private TextField tfGPHeure;
    @FXML private TextField tfGPGenre;
    @FXML private ChoiceBox<GenreMusical> cbGPGenre;
    @FXML private TextField tfGPDuree;

    // éléments ajout lieux
    @FXML private TextField tfLNom;
    @FXML private TextField tfLAdresse;
    @FXML private TextField tfLPrix;
    @FXML private TextField tfLCapacite;
    @FXML private ToggleSwitch tsLLogement;

    // éléments ajout voiture
    @FXML private TextField tfVMarque;
    @FXML private TextField tfVPrix;

    // éléments ajout marié
    @FXML private TextField tfMPrenom;
    @FXML private TextField tfMNom;
    @FXML private TextField tfMAge;
    @FXML private TextField tfMTel;
    @FXML private TextField tfMEmail;
    @FXML private TextField tfMVetement;
    @FXML private TextField tfMChaussure;
    @FXML private TextField tfMAlliance;

    // éléments ajout repas
    @FXML private TextField tfRNom;
    @FXML private TextField tfRPrix;
    @FXML private ChoiceBox<MomentRepas> cbRRepas;

    // éléments ajout invité
    @FXML private TextField tfIPrenom;
    @FXML private TextField tfINom;
    @FXML private TextField tfIAge;

    // éléments ajout intervenant
    @FXML private ComboBox<String> cbIntervenantNom;
    @FXML private TextField tfIntervenantTel;
    @FXML private TextField tfIntervenantEmail;
    @FXML private TextField tfIntervenantPrix;
    @FXML private TextField tfIntervenantMetier;

    // éléments ajout id, nom et date
    @FXML private TextField tfIdMariage;
    @FXML private TextField tfNomMariage;
    @FXML private DatePicker dateMariage;


    // éléments consultation d'un mariage
    @FXML Label labelConsult;
    @FXML private TextField tfConsultMarie1Nom;
    @FXML private TextField tfConsultMarie1Prenom;
    @FXML private TextField tfConsultMarie1Age;
    @FXML private TextField tfConsultMarie1Prix;
    @FXML private TextField tfConsultMarie2Nom;
    @FXML private TextField tfConsultMarie2Prenom;
    @FXML private TextField tfConsultMarie2Age;
    @FXML private TextField tfConsultMarie2Prix;

    @FXML private VBox vBoxInvites;
    @FXML private VBox vBoxRepas;
    @FXML private VBox vBoxMusique;
    @FXML private VBox vBoxLieux;
    @FXML private VBox vBoxIntervenants;

    @FXML private TextField tfConsultVoitureMarque;
    @FXML private TextField tfConsultVoiturePrix;
    @FXML private TextField tfConsultPrix;

    @FXML private Button btnUser;

    // éléments vue Archives
    @FXML GridPane gridPaneArchives;

    @FXML private ScrollPane spinvite;
    @FXML private ScrollPane sprepas;
    @FXML private ScrollPane spmusique;
    @FXML private ScrollPane spLieu;
    @FXML private ScrollPane spintervenant;

    public JavaFXGUI(Controleur controleur) {
        this.controleur = controleur;
        this.genevent = controleur.getGenevent();
        this.eolBarrier = new CountDownLatch(1);  // /!\ ne pas supprimer /!\

        // on récupère les utilisateurs
        this.utilisateurs.putAll(controleur.getUtilisateurs());

    }

    /**
     * Point d'entrée principal pour le code de l'interface JavaFX.
     *
     * @param primaryStage stage principale de l'interface JavaFX, sur laquelle
     *     définir des scenes.
     *
     * @throws IOException si le chargement de la vue FXML échoue.
     *
     * @see Application#start(Stage)
     */
    private void start(Stage primaryStage) throws IOException {
        FXMLLoader mainViewLoader = new FXMLLoader(getClass().getResource("main-view.fxml"));
        mainViewLoader.setController(this);
        Scene mainScene = new Scene(mainViewLoader.load());

        primaryStage.setTitle("Wed'Dreamer");
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }



//-----  Éléments du dialogue  -------------------------------------------------

    private void exitAction() {
        // fermeture de l'interface JavaFX : on notifie sa fin de vie
        this.eolBarrier.countDown();
    }

    // menu principal  -----

    @FXML
    private void newUserMenuItemAction() throws UtilisateurException {
        this.controleur.saisirUtilisateur();
    }

    @FXML
    private void exitMenuItemAction() {
        Platform.exit();
        this.exitAction();
    }

    // vue nouvel·le utilisa·teur/trice  -----

    @FXML
    private void createNewUserAction() throws UtilisateurException {
        IHM.InfosUtilisateur data = new IHM.InfosUtilisateur(
                this.newUserEmailTextField.getText().strip().toLowerCase(),
                this.newUserSurnameTextField.getText().strip(),
                this.newUserForenameTextField.getText().strip()
        );
        this.controleur.creerUtilisateur(data);
        this.newUserOkButton.getScene().getWindow().hide();
    }

    @FXML
    private void cancelNewUserAction() {
        this.newUserCancelButton.getScene().getWindow().hide();
    }

    @FXML
    private void validateTextFields() {
        boolean isValid = true;

        isValid &= validateNonEmptyTextField(this.newUserForenameTextField);
        isValid &= validateNonEmptyTextField(this.newUserSurnameTextField);
        isValid &= validateEmailTextField(this.newUserEmailTextField);

        this.newUserOkButton.setDisable(!isValid);
    }

    private static void markTextFieldErrorStatus(TextField textField, boolean isValid) {
        if (isValid) {
            textField.setStyle(null);
        } else {
            textField.setStyle("-fx-control-inner-background: f8d7da");
        }
    }

    private static boolean validateNonEmptyTextField(TextField textField) {
        boolean isValid = textField.getText().strip().length() > 0;

        markTextFieldErrorStatus(textField, isValid);

        return isValid;
    }

    private static boolean validateEmailTextField(TextField textField) {
        EmailValidator validator = EmailValidator.getInstance(false, false);
        boolean isValid = validator.isValid(textField.getText().strip().toLowerCase());

        markTextFieldErrorStatus(textField, isValid);

        return isValid;
    }

//-----  Implémentation des méthodes abstraites  -------------------------------

    @Override
    public void demarrerInteraction() {
        // démarrage de l'interface JavaFX
        Platform.startup(() -> {
            Stage primaryStage = new Stage();
            primaryStage.setOnCloseRequest((WindowEvent t) -> this.exitAction());
            try {
                this.start(primaryStage);
            }
            catch (IOException exc) {
                throw new RuntimeException(exc);
            }
        });


        // attente de la fin de vie de l'interface JavaFX
        try {
            this.eolBarrier.await();
        }
        catch (InterruptedException exc) {
            System.err.println("Erreur d'exécution de l'interface.");
            System.err.flush();
        }
    }

    @Override
    public void informerUtilisateur(String msg, boolean succes) {
        final Alert alert = new Alert(
                succes ? Alert.AlertType.INFORMATION : Alert.AlertType.WARNING
        );
        alert.setTitle("GenEvent");
        alert.setContentText(msg);
        alert.showAndWait();
    }

    @Override
    public void saisirUtilisateur() {
        try {
            FXMLLoader newUserViewLoader = new FXMLLoader(getClass().getResource("new-user-view.fxml"));
            newUserViewLoader.setController(this);
            Scene newUserScene = new Scene(newUserViewLoader.load());

            Stage newUserWindow = new Stage();
            newUserWindow.setTitle("Créer un·e utilisa·teur/trice");
            newUserWindow.initModality(Modality.APPLICATION_MODAL);
            newUserWindow.setScene(newUserScene);
            newUserWindow.showAndWait();
        } catch (IOException exc) {
            throw new RuntimeException(exc);
        }
    }

    @Override
    public void saisirNouvelEvenement(Set<String> nomsExistants) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void afficherUtilisateurs(Set<String> nomsExistants) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    // Fenêtre d'accueil ----------------------------------------------------------------------------------------------

    @FXML
    private void btnUserOnClick(ActionEvent event) throws IOException {
        // on charge la vue utilisateurs
        FXMLLoader userViewLoader = new FXMLLoader(getClass().getResource("list-user-view.fxml"));
        userViewLoader.setController(this);
        Scene userScene = new Scene(userViewLoader.load());
        userScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("listuser.css")).toExternalForm());


        if (vBoxMain != null) {
            vBoxMain.getChildren().clear();

            for (Utilisateur utilisateur : this.utilisateurs.values()) {
                Label label = new Label(utilisateur.getPrenom() + " " + utilisateur.getNom() + " <" + utilisateur.getEmail() + ">");
                label.setPadding(new javafx.geometry.Insets(5, 5, 5, 5));
                label.setMaxWidth(Double.MAX_VALUE);
                label.setId("labelVboxMain");
                label.setOnMouseClicked(event1 -> {
                    try {
                        mariageMenuItemAction(event1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

                vBoxMain.getChildren().add(label);
            }
        }

        Stage userWindow = (Stage) btnUser.getScene().getWindow();
        userWindow.setTitle("Utilisateurs");
        userWindow.setScene(userScene);
        userWindow.show();
    }

    @FXML
    private void btnRetourOnClick(ActionEvent event) throws IOException {
        // on charge la vue principale
        FXMLLoader mainViewLoader = new FXMLLoader(getClass().getResource("main-view.fxml"));
        mainViewLoader.setController(this);

        Scene mainScene = new Scene(mainViewLoader.load());
        mainScene.getStylesheets().add(getClass().getResource("accueil.css").toExternalForm());

        Stage mainWindow = (Stage) ((Button) event.getSource()).getScene().getWindow();
        mainWindow.setTitle("Wed'Dreamer");
        mainWindow.setScene(mainScene);
        mainWindow.show();
    }

    // Fenêtre Principale ----------------------------------------------------------------------------------------------

    @FXML
    private void mariageMenuItemAction(MouseEvent event) throws IOException {
        // on récupère le bouton sur lequel on a cliqué
        Label btn = (Label) event.getSource();
        // on récupère l'utilisateur sur lequel on a cliqué
        String email = btn.getText().substring(btn.getText().indexOf("<") + 1, btn.getText().indexOf(">"));
        this.utilisateurCourant = this.utilisateurs.get(email);
        // on charge la vue mariage
        FXMLLoader mariageViewLoader = new FXMLLoader(getClass().getResource("home-view.fxml"));
        mariageViewLoader.setController(this);
        Stage mariageWindow = (Stage) btn.getScene().getWindow();
        Scene mariageScene = new Scene(mariageViewLoader.load());
        mariageScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("main.css")).toExternalForm());
        if(gridPaneMariage == null) {
            return;
        } else {
            VBox addMariage = (VBox) gridPaneMariage.getChildren().get(0);
            // on vide le gridpane
            gridPaneMariage.getChildren().clear();
            col = 0;
            row = 0;
            LocalDate currentDate = LocalDate.now();
            // on ajoute le bouton d'ajout de mariage
            gridPaneMariage.add(addMariage, col, row);
            col++;
            for(Mariage mariage : this.utilisateurCourant.getMariages().values()) {
                if (mariage.getDate().isBefore(currentDate) || mariage.getDate().isEqual(currentDate)){
                    mariage.setDeleted(true);
                } else {
                    mariage.setDeleted(false);
                }
                if(!mariage.isDeleted()){
                    VBox vbox = new VBox();
                    Label label = new Label(mariage.getId());
                    label.setMaxWidth(Double.MAX_VALUE);
                    label.setMaxHeight(Double.MAX_VALUE);
                    label.setId("labelMariage");
                    label.setStyle("-fx-alignment: CENTER; -fx-font-size: 20px;");
                    vbox.getChildren().add(label);
                    Button btn1 = new Button("Consulter");
                    btn1.setId("btnMariage");
                    btn1.setStyle("-fx-background-color: #FFF4FF; -fx-background-radius: 10px; -fx-border-radius: 10px; -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.7), 3, 0.5, 0, 0); -fx-min-height: 40px; -fx-min-width: 40px; -fx-font-weight: bold;");
                    btn1.setOnAction(event1 -> {
                        try {
                            mariageCourant = mariage;
                            mariageConsulterOnAction(event1);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                    vbox.getChildren().add(btn1);
                    // style de la Vbox
                    vbox.setAlignment(Pos.CENTER);
                    // on ajoute le mariage à la gridpane, on commence à partir de la colonne 1, ligne 0
                    gridPaneMariage.add(vbox, col, row);
                    col++;
                    if (col == 4) {
                        col = 0;
                        row++;
                    }
                }
            }
        }
        mariageWindow.setTitle("Espace Mariage");
        mariageWindow.setScene(mariageScene);
        mariageWindow.show();
    }

    // Fenêtre vue Consultation Mariage ----------------------------------------------------------------------------------------------

    @FXML
    private void mariageConsulterOnAction(ActionEvent event) throws IOException {
        // chargement de la nouvelle page
        FXMLLoader mariageViewLoader = new FXMLLoader(getClass().getResource("mariage-consult-view.fxml"));
        mariageViewLoader.setController(this);
        // on ouvre dans une nouvelle fenêtre
        Stage mariageWindow = new Stage();
        Scene mariageScene = new Scene(mariageViewLoader.load());
        mariageScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("main.css")).toExternalForm());
        // on récupère les informations du mariage
        // on vérifie si les mariés sont renseignés
        if (mariageCourant.getMarie1() != null) {
            tfConsultMarie1Nom.setText(mariageCourant.getMarie1().getNom());
            tfConsultMarie1Prenom.setText(mariageCourant.getMarie1().getPrenom());
            tfConsultMarie1Age.setText(String.valueOf(mariageCourant.getMarie1().getAge()));
            tfConsultMarie1Prix.setText(String.valueOf(mariageCourant.getMarie1().getPrixAlliance()));
        }
        if (mariageCourant.getMarie2() != null) {
            tfConsultMarie2Nom.setText(mariageCourant.getMarie2().getNom());
            tfConsultMarie2Prenom.setText(mariageCourant.getMarie2().getPrenom());
            tfConsultMarie2Age.setText(String.valueOf(mariageCourant.getMarie2().getAge()));
            tfConsultMarie2Prix.setText(String.valueOf(mariageCourant.getMarie2().getPrixAlliance()));
        }

        tfNbInvitesAdultes.setText(String.valueOf(mariageCourant.getNombreAdultes()));
        tfNbInvitesEnfants.setText(String.valueOf(mariageCourant.getNombreEnfants()));
        tfNbInvitesVinHonneur.setText(String.valueOf(mariageCourant.getNombreInvitesVinHonneur()));

        if(mariageCourant.getVoiture() != null){
            tfConsultVoitureMarque.setText(mariageCourant.getVoiture().getMarque());
            tfConsultVoiturePrix.setText(String.valueOf(mariageCourant.getVoiture().getPrix()));
        }
        double prix = mariageCourant.getPrix();
        tfConsultPrix.setText(String.valueOf(mariageCourant.getPrix()));

        // on récupère les invités du mariage
        for (Personne invite : mariageCourant.getInvitesImportants()) {
            VBox vBox = new VBox();
            Label label = new Label("Prénom : " + invite.getPrenom() + " Nom : " + invite.getNom());
            label.setPadding(new javafx.geometry.Insets(5, 5, 5, 5));
            label.setMaxWidth(Double.MAX_VALUE);
            label.setId("labelVboxMain");
            vBox.getChildren().add(label);
            vBoxInvites.getChildren().add(label);
        }
        // on récupère les plats du mariage
        for(Repas repas : mariageCourant.getRepas()) {
            for(Plat plat : repas.getPlats()) {
                Label label = new Label("Nom du plat : " + plat.getNom() + ", prix : " + plat.getPrixParPersonne() + "€");
                label.setPadding(new javafx.geometry.Insets(5, 5, 5, 5));
                label.setMaxWidth(Double.MAX_VALUE);
                label.setId("labelVboxMain");
                vBoxRepas.getChildren().add(label);
            }
            for (Boisson boisson : repas.getBoissons()) {
                Label label = new Label("Nom de la boisson : " + boisson.getNom() + ", prix : " + boisson.getPrixParPersonne() + "€");
                label.setPadding(new javafx.geometry.Insets(5, 5, 5, 5));
                label.setMaxWidth(Double.MAX_VALUE);
                label.setId("labelVboxMain");
                vBoxRepas.getChildren().add(label);
            }
        }
        // on récupère les musiques du mariage
        for (GroupeMusique musique : mariageCourant.getGroupes()) {
            Label label = new Label("Nom du groupe : " + musique.getNom() + ", prix : " + musique.getPrix() + "€");
            label.setPadding(new javafx.geometry.Insets(5, 5, 5, 5));
            label.setMaxWidth(Double.MAX_VALUE);
            label.setId("labelVboxMain");
            vBoxMusique.getChildren().add(label);
        }
        // on récupère les lieux du mariage
        for (Lieux lieu : mariageCourant.getLieux()) {
            Label label = new Label("Localisation : " + lieu.getNom() + ", prix de la location : " + lieu.getCoutLocation() + "€");
            label.setPadding(new javafx.geometry.Insets(5, 5, 5, 5));
            label.setMaxWidth(Double.MAX_VALUE);
            label.setId("labelVboxMain");
            vBoxLieux.getChildren().add(label);
        }
        // on récupère les intervenants du mariage
        for (Contact intervenant : mariageCourant.getIntervenants()) {
            Label label = new Label("Nom : " + intervenant.getNom() + ", spécialité de l'intervenant : " +intervenant.getProfession() + ", honoraires : " + intervenant.getHonoraire() + "€");
            label.setPadding(new javafx.geometry.Insets(5, 5, 5, 5));
            label.setMaxWidth(Double.MAX_VALUE);
            label.setId("labelVboxMain");
            vBoxIntervenants.getChildren().add(label);
        }
        mariageWindow.setTitle("Consultation Mariage");
        mariageWindow.setScene(mariageScene);
        mariageWindow.show();
    }

    @FXML
    private void btnModifierMariageOnClick(ActionEvent event) throws IOException {
        // modification des éléments du mariage

    }

    @FXML
    private void btnSupprimerMariageOnClick(ActionEvent event) throws IOException {
        // alerte de suppression du mariage
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Suppression du mariage");
        alert.setHeaderText("Voulez-vous vraiment supprimer ce mariage ?");
        alert.setContentText("Attention, cette action est irréversible.");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            // LOGGER
            LOGGER.log(Level.INFO, "Suppression du mariage " + mariageCourant.getId());
            // suppression du mariage
            // on supprime le mariage de la liste des mariages de l'utilisateur et on le supprime de la liste des mariages de genevent
            // on récupère l'id du mariage
            String idMariage = mariageCourant.getId();
            // on supprime le mariage de la liste des mariages de l'utilisateur
            this.utilisateurCourant.getMariages().remove(idMariage);
            // on supprime le mariage de la liste des mariages de genevent
            this.genevent.getMariages().remove(idMariage);
            // on ferme la page de consultation du mariage
            Stage mariageWindow = (Stage) ((Button) event.getSource()).getScene().getWindow();
            mariageWindow.close();
            if(gridPaneMariage == null) {
                return;
            } else {
                VBox addMariage = (VBox) gridPaneMariage.getChildren().get(0);
                // on vide le gridpane
                gridPaneMariage.getChildren().clear();
                col = 0;
                row = 0;
                LocalDate currentDate = LocalDate.now();
                // on ajoute le bouton d'ajout de mariage
                gridPaneMariage.add(addMariage, col, row);
                col++;
                for(Mariage mariage : this.utilisateurCourant.getMariages().values()) {
                    if (mariage.getDate().isBefore(currentDate) || mariage.getDate().isEqual(currentDate)){
                        mariage.setDeleted(true);
                    } else {
                        mariage.setDeleted(false);
                    }
                    if(!mariage.isDeleted()){
                        VBox vbox = new VBox();
                        Label label = new Label(mariage.getId());
                        label.setMaxWidth(Double.MAX_VALUE);
                        label.setMaxHeight(Double.MAX_VALUE);
                        label.setId("labelMariage");
                        label.setStyle("-fx-alignment: CENTER; -fx-font-size: 20px;");
                        vbox.getChildren().add(label);
                        Button btn1 = new Button("Consulter");
                        btn1.setId("btnMariage");
                        btn1.setStyle("-fx-background-color: #FFF4FF; -fx-background-radius: 10px; -fx-border-radius: 10px; -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.7), 3, 0.5, 0, 0); -fx-min-height: 40px; -fx-min-width: 40px; -fx-font-weight: bold;");
                        btn1.setOnAction(event1 -> {
                            try {
                                mariageCourant = mariage;
                                mariageConsulterOnAction(event1);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
                        vbox.getChildren().add(btn1);
                        // style de la Vbox
                        vbox.setAlignment(Pos.CENTER);
                        // on ajoute le mariage à la gridpane, on commence à partir de la colonne 1, ligne 0
                        gridPaneMariage.add(vbox, col, row);
                        col++;
                        if (col == 4) {
                            col = 0;
                            row++;
                        }
                    }
                }
            }
        }
        // LOGGER
        LOGGER.log(Level.INFO, "Annulation de la suppression du mariage " + mariageCourant.getId());
    }


    @FXML
    private void btnGenererFactureOnClick(ActionEvent event) throws IOException {
        // génération de la facture du mariage




    }


    @FXML
    private void btnGenererDevisOnClick(ActionEvent event) throws IOException {
        // génération du devis du mariage


    }


    // Boutons latéraux OnAction ----------------------------------------------------------------------------------------------

    @FXML
    private void btnMariagesOnClick(ActionEvent event) throws IOException {
        // on charge la vue mariages
        FXMLLoader mariagesViewLoader = new FXMLLoader(getClass().getResource("home-view.fxml"));
        mariagesViewLoader.setController(this);
        Stage mariagesWindow = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Scene mariagesScene = new Scene(mariagesViewLoader.load());
        mariagesScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("main.css")).toExternalForm());

        // on itère sur les mariages de l'utilisateur avec iterator
        if(gridPaneMariage == null) {
            return;
        } else {
            VBox addMariage = (VBox) gridPaneMariage.getChildren().get(0);
            // on vide le gridpane
            gridPaneMariage.getChildren().clear();
            col = 0;
            row = 0;
            LocalDate currentDate = LocalDate.now();
            // on ajoute le bouton d'ajout de mariage
            gridPaneMariage.add(addMariage, col, row);
            col++;
            for(Mariage mariage : this.utilisateurCourant.getMariages().values()) {
                if (mariage.getDate().isBefore(currentDate) || mariage.getDate().isEqual(currentDate)){
                    mariage.setDeleted(true);
                } else {
                    mariage.setDeleted(false);
                }
                if(!mariage.isDeleted()){
                    VBox vbox = new VBox();
                    Label label = new Label(mariage.getId());
                    label.setMaxWidth(Double.MAX_VALUE);
                    label.setMaxHeight(Double.MAX_VALUE);
                    label.setId("labelMariage");
                    label.setStyle("-fx-alignment: CENTER; -fx-font-size: 20px;");
                    vbox.getChildren().add(label);
                    Button btn1 = new Button("Consulter");
                    btn1.setId("btnMariage");
                    btn1.setStyle("-fx-background-color: #FFF4FF; -fx-background-radius: 10px; -fx-border-radius: 10px; -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.7), 3, 0.5, 0, 0); -fx-min-height: 40px; -fx-min-width: 40px; -fx-font-weight: bold;");
                    btn1.setOnAction(event1 -> {
                        try {
                            mariageCourant = mariage;
                            mariageConsulterOnAction(event1);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                    vbox.getChildren().add(btn1);
                    // style de la Vbox
                    vbox.setAlignment(Pos.CENTER);
                    // on ajoute le mariage à la gridpane, on commence à partir de la colonne 1, ligne 0
                    gridPaneMariage.add(vbox, col, row);
                    col++;
                    if (col == 4) {
                        col = 0;
                        row++;
                    }
                }
            }
        }

        mariagesWindow.setTitle("Espace Mariage");
        mariagesWindow.setScene(mariagesScene);
        mariagesWindow.show();

    }

    @FXML
    private void btnCalendarOnClick(ActionEvent event) throws IOException {
        CalendarView calendarView = new CalendarView();
        // on charge la vue calendrier
        FXMLLoader calendarViewLoader = new FXMLLoader(getClass().getResource("calendar-view.fxml"));
        calendarViewLoader.setController(this);
        Stage calendarWindow = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Scene calendarScene = new Scene(calendarViewLoader.load());
        calendarScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("main.css")).toExternalForm());
        Calendar cal1 = new Calendar("Cal1");

        CalendarSource cal1source = new CalendarSource("Cal1");
        cal1source.getCalendars().add(cal1);

        calendarView.getCalendarSources().setAll(cal1source);
        calendarView.showMonthPage();
        calendarView.setShowAddCalendarButton(false);
        calendarView.setShowToolBar(false);

        // on ajoute au calendrier une nouvelle date pour chaque mariage de l'utilisateur
        for (Mariage mariage : this.utilisateurCourant.getMariages().values()) {
            LocalDateTime startDateTime = mariage.getDate().atStartOfDay();
            String key = "Mariage " + mariage.getId(); // Use a more descriptive key
            Entry<String> entry = new Entry<>("Mariage " + mariage.getId());
            entry.changeStartDate(LocalDate.from(startDateTime));
            entry.changeEndDate(LocalDate.from(startDateTime));
            // on vérifie que le mariage n'existe pas déjà dans le calendrier
            cal1.addEntry(entry);
        }

        calendar.getChildren().add(calendarView);
        calendarWindow.setTitle("Calendrier");
        calendarWindow.setScene(calendarScene);
        calendarWindow.show();
    }

    @FXML
    private void btnArchivesOnClick(ActionEvent event) throws IOException {
        // on charge la vue archives
        FXMLLoader archivesViewLoader = new FXMLLoader(getClass().getResource("archives-view.fxml"));
        archivesViewLoader.setController(this);
        Stage archivesWindow = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Scene archivesScene = new Scene(archivesViewLoader.load());
        archivesScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("main.css")).toExternalForm());

        // on ajoute tous les mariages archivés de l'utilisateur dans la gridpane
        if(gridPaneArchives == null) {
            return;
        } else {
            // on vide le gridpane
            gridPaneArchives.getChildren().clear();
            col = 0;
            row = 0;

            for(Mariage mariage : this.utilisateurCourant.getMariages().values()) {
                if (mariage.getDate().isBefore(LocalDate.now()) || mariage.getDate().isEqual(LocalDate.now())){
                    mariage.setDeleted(true);
                } else {
                    mariage.setDeleted(false);
                }
                if(mariage.isDeleted()){
                    VBox vbox = new VBox();
                    Label label = new Label(mariage.getId());
                    label.setMaxWidth(Double.MAX_VALUE);
                    label.setMaxHeight(Double.MAX_VALUE);
                    label.setId("labelMariage");
                    label.setStyle("-fx-alignment: CENTER; -fx-font-size: 20px;");
                    vbox.getChildren().add(label);

                    Button btn1 = new Button("Consulter");
                    btn1.setId("btnMariage");
                    btn1.setStyle("-fx-background-color: #FFF4FF; -fx-background-radius: 10px; -fx-border-radius: 10px; -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.7), 3, 0.5, 0, 0); -fx-min-height: 40px; -fx-min-width: 40px; -fx-font-weight: bold;");
                    btn1.setOnAction(event1 -> {
                        try {
                            mariageCourant = mariage;
                            mariageConsulterOnAction(event1);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                    vbox.getChildren().add(btn1);
                    // on centre les éléments dans la vbox
                    vbox.setAlignment(Pos.CENTER);
                    // on ajoute le mariage à la gridpane, on commence à partir de la colonne 1, ligne 0
                    gridPaneArchives.add(vbox, col, row);
                    col++;
                    if (col == 4) {
                        col = 0;
                        row++;
                    }
                }
            }
        }
        archivesWindow.setTitle("Archives");
        archivesWindow.setScene(archivesScene);
        archivesWindow.show();
    }

    @FXML
    private void btnContactOnClick(ActionEvent event) throws IOException {
        // on charge la vue contact
        FXMLLoader contactViewLoader = new FXMLLoader(getClass().getResource("contact-view.fxml"));
        contactViewLoader.setController(this);
        Stage contactWindow = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Scene contactScene = new Scene(contactViewLoader.load());
        contactWindow.setScene(contactScene);
        // ajout du css
        contactScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("main.css")).toExternalForm());

        // on récupère les contacts de l'utilisateur qui sont sérialisés dans un fichier
        controleur.getUtilisateurs().get(this.utilisateurCourant.getEmail()).getContacts();

        if (this.utilisateurCourant == null ||this.utilisateurCourant.getContacts().isEmpty()) {
            Label label = new Label("Aucun contact");
            label.setAlignment(Pos.CENTER);
            label.setMaxWidth(Double.MAX_VALUE);
            label.setMaxHeight(Double.MAX_VALUE);
            label.setId("labelContact");
            gridpaneContact.add(label, 0, 1);
        } else {
            // on récupère les contacts de l'utilisateur et on l'insère dans la gridpane, on commence à itérer à partir de la ligne 1
            int i = 1;
            for (Map.Entry<String, Contact> entry : this.utilisateurCourant.getContacts().entrySet()) {
                Label label = new Label(entry.getValue().getNom());
                label.setMaxWidth(Double.MAX_VALUE);
                label.setMaxHeight(Double.MAX_VALUE);
                label.setId("labelContact");
                Label label1 = new Label(entry.getValue().getNumTel());
                label1.setMaxWidth(Double.MAX_VALUE);
                label1.setMaxHeight(Double.MAX_VALUE);

                label.setStyle("-fx-alignment: CENTER;");
                label1.setStyle("-fx-alignment: CENTER;");

                label.setOnMouseClicked(event1 -> {
                    try {
                        afficheContact(event1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                gridpaneContact.add(label, 1, i);
                gridpaneContact.add(label1, 0, i);
                i++;
            }
        }

        contactWindow.setTitle("Contact");
        contactWindow.setScene(contactScene);
        contactWindow.show();
    }

    @FXML
    private void btnInfoOnClick(ActionEvent event) throws IOException {
        // on charge la vue info
        FXMLLoader infoViewLoader = new FXMLLoader(getClass().getResource("info-view.fxml"));
        infoViewLoader.setController(this);
        Stage infoWindow = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Scene infoScene = new Scene(infoViewLoader.load());
        infoScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("main.css")).toExternalForm());

        tfInfosNom.setText(this.utilisateurCourant.getNom());
        tfInfosPrenom.setText(this.utilisateurCourant.getPrenom());
        tfInfosMail.setText(this.utilisateurCourant.getEmail());

        // on récupère les informations renforcées de l'utilisateur

        if(this.utilisateurCourant.getTelephone() != null) {
            tfInfosTel.setText(this.utilisateurCourant.getTelephone());
        }
        if(this.utilisateurCourant.getSiret() != null) {
            tfInfosSiret.setText(this.utilisateurCourant.getSiret());
        }

        infoWindow.setTitle("Informations");
        infoWindow.setScene(infoScene);
        infoWindow.show();
    }

    // vue espace Mariage ----------------------------------------------------------------------------------------------

    @FXML
    private void btnNouveauMariageOnClick(ActionEvent event) throws IOException {
        FXMLLoader newMariageViewLoader = new FXMLLoader(getClass().getResource("new-mariage-view.fxml"));
        newMariageViewLoader.setController(this);
        Scene newMariageScene = new Scene(newMariageViewLoader.load());
        newMariageScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("newMariage.css")).toExternalForm());

        // on initialise une sauvegarde du mariage courant, lorsque l'on clique sur créer un nouveau mariage
        mariages.add(new Mariage());
        mariageCourant = new Mariage();

        Stage newUserWindow = new Stage();
        newUserWindow.setTitle("Créer un mariage");
        newUserWindow.initModality(Modality.APPLICATION_MODAL);
        newUserWindow.setScene(newMariageScene);
        newUserWindow.showAndWait();
    }

    @FXML
    private void btnNouveauGroupeMusiqueOnClick(ActionEvent event) throws IOException {

        FXMLLoader newgGroupeMusiqueViewLoader = new FXMLLoader(getClass().getResource("new-groupemusique-view.fxml"));
        newgGroupeMusiqueViewLoader.setController(this);
        Scene newGroupeMusiqueScene = new Scene(newgGroupeMusiqueViewLoader.load());
        newGroupeMusiqueScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("fenetreexterieur.css")).toExternalForm());

        // on ajoute à la comboBox les genres musicaux
        for (GenreMusical genre : GenreMusical.values()) {
            cbGPGenre.getItems().add(genre);
        }

        Stage newUserWindow = new Stage();
        newUserWindow.setTitle("Créer un mariage");
        newUserWindow.initModality(Modality.APPLICATION_MODAL);
        newUserWindow.setScene(newGroupeMusiqueScene);
        newUserWindow.showAndWait();
    }

    @FXML
    private void btnValiderGroupeMusiqueOnClick(ActionEvent event) throws IOException, MariageException {
        if (tfGPNom.getText().isEmpty() || tfGPHeure.getText().isEmpty() || tfGPDuree.getText().isEmpty() || tfGPPrix.getText().isEmpty() || cbGPGenre.getValue() == null) {
            // on affiche un message d'erreur
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur lors de la création du groupe de musique");
            alert.setContentText("Veuillez remplir tous les champs");
            alert.showAndWait();

            // LOGGER ERROR
            LOGGER.log(Level.SEVERE, "Erreur lors de la création du groupe de musique, veuillez remplir tous les champs");
        }
        else {
            // on récupère les informations que l'on a entré dans le formulaire de création de contact
            GroupeMusique groupeMusique = null ;
            try {
                groupeMusique = new GroupeMusique(Integer.parseInt(tfGPDuree.getText()), Double.parseDouble(tfGPHeure.getText()), tfGPNom.getText(), cbGPGenre.getValue(), Integer.parseInt(tfGPPrix.getText()));
                mariages.get(mariages.size()-1).addGroupe(groupeMusique);
                mariageCourant.addGroupe(groupeMusique);
                // LOGGER SUCCESS
                LOGGER.log(Level.INFO, "Groupe de musique créé avec succès");
            } catch (NumberFormatException e){
                // on affiche un message d'erreur
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Erreur lors de la création du groupe de musique");
                alert.setContentText("Veuillez rentrer un chiffre pour les honoraires");
                alert.showAndWait();
                // LOGGER ERROR
                LOGGER.log(Level.SEVERE, "Erreur lors de la création du groupe de musique, veuillez rentrer un chiffre pour l'horaire de passage, la durée et le prix");
            }
            // on ferme la fenêtre
            ((Stage) ((Button) event.getSource()).getScene().getWindow()).close();
            // on met à jour la vue
            GridPane g = new GridPane();
            g.add(new Label("Nom : "),0,0);
            g.add(new Label("Heure de passage : "),0,1);
            g.add(new Label("Durée : "),0,2);
            g.add(new Label("Prix : "),0,3);
            g.add(new Label("Genre : "),0,4);
            g.add(new Label(groupeMusique.getNom()),1,0);
            g.add(new Label(String.valueOf(groupeMusique.getHeurePassage() + "h")),1,1);
            g.add(new Label(String.valueOf(groupeMusique.getDuree())),1,2);
            g.add(new Label(String.valueOf(groupeMusique.getPrix() + "€")),1,3);
            g.add(new Label(groupeMusique.getGenreMusical().toString()),1,4);
            HBox h = new HBox();
            g.setStyle("-fx-border-color: #bfbcbc; -fx-border-width: 1; -fx-padding: 0 5px 0 5px;-fx-border-radius: 10px");
            h.setSpacing(10);
            h.getChildren().addAll(spMusique.getContent(),g);
            spMusique.setContent(h);
        }
    }

    @FXML
    private void btnNouveauIntervantOnClick(ActionEvent event) throws IOException {
        FXMLLoader newgIntervenantViewLoader = new FXMLLoader(getClass().getResource("new-intervenant-view.fxml"));
        newgIntervenantViewLoader.setController(this);
        Scene newIntervenantScene = new Scene(newgIntervenantViewLoader.load());
        newIntervenantScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("fenetreexterieur.css")).toExternalForm());
        // on ajoute à la comboBox les contacts de l'utilisateur
        if (!utilisateurCourant.getContacts().isEmpty()) {
            for (Contact contact : this.utilisateurCourant.getContacts().values()) {
                cbIntervenantNom.getItems().add(contact.getNom());
            }
        }
        // lorsque l'on clique sur un contact, on récupère les informations du contact et on les affiche dans les textfields
        cbIntervenantNom.setOnAction(event1 -> {
            String selectedItem = cbIntervenantNom.getSelectionModel().getSelectedItem();

            // on récupère les informations du contact
            // on a le nom, on récupère le reste des informations
            Map<String, Contact> contacts = utilisateurCourant.getContacts();
            for (Map.Entry<String, Contact> entry : contacts.entrySet()) {
                if (entry.getValue().getNom().equals(selectedItem)) {
                    tfIntervenantTel.setText(entry.getValue().getNumTel());
                    tfIntervenantEmail.setText(entry.getValue().getMail());
                    tfIntervenantMetier.setText(entry.getValue().getProfession());
                    tfIntervenantPrix.setText(String.valueOf(entry.getValue().getHonoraire()));
                }
            }

        });


        Stage newUserWindow = new Stage();
        newUserWindow.setTitle("Créer un mariage");
        newUserWindow.initModality(Modality.APPLICATION_MODAL);
        newUserWindow.setScene(newIntervenantScene);
        newUserWindow.showAndWait();
    }

    @FXML
    private void btnValiderIntervenantOnClick(ActionEvent event) throws IOException, MariageException {
        if (tfIntervenantEmail.getText().isEmpty() || tfIntervenantMetier.getText().isEmpty() || tfIntervenantPrix.getText().isEmpty() || tfIntervenantTel.getText().isEmpty() || cbIntervenantNom.getValue() == null) {
            // on affiche un message d'erreur
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur lors de la création de l'intervenant");
            alert.setContentText("Veuillez remplir tous les champs");
            alert.showAndWait();
            // LOGGER ERROR
            LOGGER.log(Level.SEVERE, "Erreur lors de la création de l'intervenant, veuillez remplir tous les champs");
        }
        else {
            // on récupère les informations que l'on a entré dans le formulaire de création de contact
            Contact intervenant = null ;
            try {
                intervenant = new Contact(cbIntervenantNom.getValue(), tfIntervenantEmail.getText(), tfIntervenantTel.getText(), tfIntervenantMetier.getText(), Double.parseDouble(tfIntervenantPrix.getText()));
                mariages.get(mariages.size() - 1).addIntervenant(intervenant);
                mariageCourant.addIntervenant(intervenant);
                // LOGGER SUCCESS
                LOGGER.log(Level.INFO, "Intervenant créé avec succès");
            } catch (NumberFormatException e) {
                // on affiche un message d'erreur
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Erreur lors de la création de l'intervenant");
                alert.setContentText("Veuillez rentrer un chiffre pour les honoraires");
                alert.showAndWait();
                // LOGGER ERROR
                LOGGER.log(Level.SEVERE, "Erreur lors de la création de l'intervenant, veuillez rentrer un chiffre pour les honoraires");
            }
            // on ferme la fenêtre
            ((Stage) ((Button) event.getSource()).getScene().getWindow()).close();
            // on met à jour la vue
            GridPane g = new GridPane();
            g.add(new Label("Nom : "), 0, 0);
            g.add(new Label("Prix : "), 0, 1);
            g.add(new Label("Mail : "), 0, 2);
            g.add(new Label("Téléphone : "), 0, 3);
            g.add(new Label("Métier : "), 0, 4);
            g.add(new Label(intervenant.getNom()), 1, 0);
            g.add(new Label(intervenant.getHonoraire() + "€"), 1, 1);
            g.add(new Label(intervenant.getMail()), 1, 2);
            g.add(new Label(intervenant.getNumTel()), 1, 3);
            g.add(new Label(intervenant.getProfession()), 1, 4);
            HBox h = new HBox();
            g.setStyle("-fx-border-color: #bfbcbc; -fx-border-width: 1; -fx-padding: 0 5px 0 5px;-fx-border-radius: 10px");
            h.setSpacing(10);
            h.getChildren().addAll(spIntervenant.getContent(),g);
            spIntervenant.setContent(h);
        }
    }

    @FXML
    private void btnNouveauLieuxOnClick(ActionEvent event) throws IOException {
        FXMLLoader newgLieuxLoader = new FXMLLoader(getClass().getResource("new-lieux-view.fxml"));
        newgLieuxLoader.setController(this);
        Scene newLieuxScene = new Scene(newgLieuxLoader.load());
        newLieuxScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("fenetreexterieur.css")).toExternalForm());
        Stage newUserWindow = new Stage();
        newUserWindow.setTitle("Créer un mariage");
        newUserWindow.initModality(Modality.APPLICATION_MODAL);
        newUserWindow.setScene(newLieuxScene);
        newUserWindow.showAndWait();
    }

    @FXML
    private void btnValiderLieuxOnClick(ActionEvent event) throws IOException, MariageException {
        if (tfLNom.getText().isEmpty() || tfLAdresse.getText().isEmpty() || tfLPrix.getText().isEmpty() || tfLCapacite.getText().isEmpty()) {
            // on affiche un message d'erreur
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur lors de la création du lieu");
            alert.setContentText("Veuillez remplir tous les champs");
            alert.showAndWait();
            // LOGGER ERROR
            LOGGER.log(Level.SEVERE, "Erreur lors de la création du lieu, veuillez remplir tous les champs");
        }
        else {
            Lieux lieu = null;
            try {
                // on récupère les informations que l'on a entré dans le formulaire de création de contact
                lieu = new Lieux(tfLNom.getText(), tfLAdresse.getText(), Integer.parseInt(tfLPrix.getText()), Integer.parseInt(tfLCapacite.getText()), tsLLogement.isSelected());
                mariages.get(mariages.size() - 1).addLieux(lieu);
                mariageCourant.addLieux(lieu);
            } catch (NumberFormatException e) {
                // on affiche un message d'erreur si l'utilisateur n'a pas entré un chiffre pour le prix et la capacité
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Erreur lors de la création du lieu");
                alert.setContentText("Veuillez entrer un chiffre pour le prix et la capacité");
                alert.showAndWait();
                // LOGGER ERROR
                LOGGER.log(Level.SEVERE, "Erreur lors de la création du lieu, veuillez entrer un chiffre pour le prix et la capacité");
            } catch (IllegalArgumentException e) {
                // on affiche un message d'erreur si l'utilisateur n'a pas entré un booléen pour le logement
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Erreur lors de la création du lieu");
                alert.setContentText("Veuillez entrer un booléen pour le logement");
                alert.showAndWait();
                // LOGGER ERROR
                LOGGER.log(Level.SEVERE, "Erreur lors de la création du lieu, veuillez entrer un booléen pour le logement");
            }
            // on ferme la fenêtre
            ((Stage) ((Button) event.getSource()).getScene().getWindow()).close();
            // on met à jour la vue
            GridPane g = new GridPane();
            g.add(new Label("Nom : "), 0, 0);
            g.add(new Label("Adresse : "), 0, 1);
            g.add(new Label("Prix : "), 0, 2);
            g.add(new Label("Capacité : "), 0, 3);
            g.add(new Label("Logement : "), 0, 4);
            g.add(new Label(lieu.getNom()), 1, 0);
            g.add(new Label(lieu.getAdresse()), 1, 1);
            g.add(new Label(lieu.getCoutLocation() + "€"), 1, 2);
            g.add(new Label(String.valueOf(lieu.getCapacite())), 1, 3);
            g.add(new Label(String.valueOf(lieu.getLogement())), 1, 4);
            HBox h = new HBox();
            g.setStyle("-fx-border-color: #bfbcbc; -fx-border-width: 1; -fx-padding: 0 5px 0 5px;-fx-border-radius: 10px");
            h.setSpacing(10);
            h.getChildren().addAll(spLieux.getContent(),g);
            spLieux.setContent(h);
        }
    }

    @FXML
    private void btnNouveauVoitureOnClick(ActionEvent event) throws IOException {
        FXMLLoader newVoitureLoader = new FXMLLoader(getClass().getResource("new-voiture-view.fxml"));
        newVoitureLoader.setController(this);
        Scene newVoitureScene = new Scene(newVoitureLoader.load());
        newVoitureScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("fenetreexterieur.css")).toExternalForm());
        Stage newUserWindow = new Stage();
        newUserWindow.setTitle("Créer un mariage");
        newUserWindow.initModality(Modality.APPLICATION_MODAL);
        newUserWindow.setScene(newVoitureScene);
        newUserWindow.showAndWait();
    }

    @FXML
    private void btnValiderVoitureOnClick(ActionEvent event) throws IOException, MariageException {
        try {
            if (tfVMarque.getText().isEmpty() || tfVPrix.getText().isEmpty()) {
                // on affiche un message d'erreur
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Erreur lors de la création de la voiture");
                alert.setContentText("Veuillez remplir tous les champs");
                alert.showAndWait();
                // LOGGER ERROR
                LOGGER.log(Level.SEVERE, "Erreur lors de la création de la voiture, veuillez remplir tous les champs");
            }
            else {
                // on récupère les informations que l'on a entré dans le formulaire de création de contact
                Voiture voiture = new Voiture(tfVMarque.getText(), Double.parseDouble(tfVPrix.getText()));
                mariages.get(mariages.size() - 1).setVoiture(voiture);
                mariageCourant.setVoiture(voiture);
                // LOGGER SUCCESS
                LOGGER.log(Level.INFO, "Voiture créée avec succès");
                // on ferme la fenêtre
                ((Stage) ((Button) event.getSource()).getScene().getWindow()).close();
                // on met à jour la vue
                vbVoiture.getChildren().clear();
                GridPane g = new GridPane();
                g.add(new Label("Marque : "), 0, 0);
                g.add(new Label("Prix : "), 0, 1);
                g.add(new Label(voiture.getMarque()), 1, 0);
                g.add(new Label(voiture.getPrix() + "€"), 1, 1);
                g.setStyle("-fx-border-color: #bfbcbc; -fx-border-width: 1; -fx-padding: 0 5px 0 5px;-fx-border-radius: 10px");
                vbVoiture.getChildren().add(g);
            }

        } catch (NumberFormatException e){
            // on affiche un message d'erreur
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur lors de la création de la voiture");
            alert.setContentText("Veuillez entrer un chiffre pour le prix");
            alert.showAndWait();
            // LOGGER ERROR
            LOGGER.log(Level.SEVERE, "Erreur lors de la création de la voiture, veuillez entrer un chiffre pour le prix");
        }

    }

    @FXML
    private void btnNouveauMarie1OnClick(ActionEvent event) throws IOException {
        FXMLLoader newMarieLoader = new FXMLLoader(getClass().getResource("new-marie1-view.fxml"));
        newMarieLoader.setController(this);
        Scene newMarieScene = new Scene(newMarieLoader.load());
        newMarieScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("fenetreexterieur.css")).toExternalForm());

        Stage newUserWindow = new Stage();
        newUserWindow.setTitle("Créer un mariage");
        newUserWindow.initModality(Modality.APPLICATION_MODAL);
        newUserWindow.setScene(newMarieScene);
        newUserWindow.showAndWait();
    }

    @FXML
    private void btnNouveauMarie2OnClick(ActionEvent event) throws IOException {
        FXMLLoader newMarieLoader = new FXMLLoader(getClass().getResource("new-marie2-view.fxml"));
        newMarieLoader.setController(this);
        Scene newMarieScene = new Scene(newMarieLoader.load());
        newMarieScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("fenetreexterieur.css")).toExternalForm());

        Stage newUserWindow = new Stage();
        newUserWindow.setTitle("Créer un mariage");
        newUserWindow.initModality(Modality.APPLICATION_MODAL);
        newUserWindow.setScene(newMarieScene);
        newUserWindow.showAndWait();
    }

    @FXML
    private void btnValider1MarieOnClick(ActionEvent event) throws IOException, MariageException {
        if (tfMNom.getText().isEmpty() || tfMAge.getText().isEmpty() || tfMPrenom.getText().isEmpty() || tfMVetement.getText().isEmpty() || tfMChaussure.getText().isEmpty() || tfMAlliance.getText().isEmpty() || tfMEmail.getText().isEmpty() || tfMTel.getText().isEmpty()) {
            // on affiche un message d'erreur
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur lors de la création du marié");
            alert.setContentText("Veuillez remplir tous les champs");
            alert.showAndWait();
            // LOGGER ERROR
            LOGGER.log(Level.SEVERE, "Erreur lors de la création du marié, veuillez remplir tous les champs");
        }
        else {
            Marie marie = null;
            // on catch l'exception d'un entier qui n'est pas un entier
            try {
                // on récupère les informations que l'on a entré dans le formulaire de création de contact
                Mariage mariage = mariages.get(mariages.size() - 1);
                marie = new Marie(tfMNom.getText(), Integer.parseInt(tfMAge.getText()), tfMPrenom.getText(), Integer.parseInt(tfMVetement.getText()), Integer.parseInt(tfMChaussure.getText()), Integer.parseInt(tfMAlliance.getText()), tfMEmail.getText(), tfMTel.getText());
                mariage.addMarie(marie);
                mariageCourant.addMarie(marie);
                // LOGGER SUCCESS
                LOGGER.log(Level.INFO, "Marié créé avec succès");
            } catch (NumberFormatException e) {
                // on affiche un message d'erreur
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Erreur lors de la création du marié");
                alert.setContentText("Veuillez entrer un nombre entier pour l'âge, le prix du vêtement, le prix des chaussures et le prix de l'alliance");
                alert.showAndWait();
                // LOGGER ERROR
                LOGGER.log(Level.SEVERE, "Erreur lors de la création du marié, veuillez entrer un nombre entier pour l'âge, le prix du vêtement, le prix des chaussures et le prix de l'alliance");
            }
            // on ferme la fenêtre
            ((Stage) ((Button) event.getSource()).getScene().getWindow()).close();
            // on met à jour la vue
            vbMarie1.getChildren().clear();
            GridPane g = new GridPane();
            g.add(new Label("Prénom : "), 0, 0);
            g.add(new Label("Nom : "), 0, 1);
            g.add(new Label("Age : "), 0, 2);
            g.add(new Label("Numéro de téléphone : "), 0, 3);
            g.add(new Label("Email : "), 0, 4);
            g.add(new Label("Vêtement : "), 0, 5);
            g.add(new Label("Chaussure : "), 0, 6);
            g.add(new Label("Alliance : "), 0, 7);
            g.add(new Label(marie.getPrenom()), 1, 0);
            g.add(new Label(marie.getNom()), 1, 1);
            g.add(new Label(String.valueOf(marie.getAge())), 1, 2);
            g.add(new Label(String.valueOf(marie.getNumtel())), 1, 3);
            g.add(new Label(marie.getEmail()), 1, 4);
            g.add(new Label((marie.getPrixVetement() + "€")), 1, 5);
            g.add(new Label((marie.getPrixChaussures() + "€")), 1, 6);
            g.add(new Label((marie.getPrixAlliance() + "€")), 1, 7);
            g.setAlignment(Pos.CENTER);
            g.setStyle("-fx-border-color: #bfbcbc; -fx-border-width: 1; -fx-padding: 0 5px 0 5px;-fx-border-radius: 10px");
            vbMarie1.getChildren().add(g);
        }
    }

    @FXML
    private void btnValider2MarieOnClick(ActionEvent event) throws IOException, MariageException {
        if (tfMNom.getText().isEmpty() || tfMAge.getText().isEmpty() || tfMPrenom.getText().isEmpty() || tfMVetement.getText().isEmpty() || tfMChaussure.getText().isEmpty() || tfMAlliance.getText().isEmpty() || tfMEmail.getText().isEmpty() || tfMTel.getText().isEmpty()) {
            // on affiche un message d'erreur
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur lors de la création du marié");
            alert.setContentText("Veuillez remplir tous les champs");
            alert.showAndWait();
            // LOGGER ERROR
            LOGGER.log(Level.SEVERE, "Erreur lors de la création du marié, veuillez remplir tous les champs");
        }
        else {
            // on récupère les informations que l'on a entré dans le formulaire de création de contact
            Mariage mariage = mariages.get(mariages.size() - 1);
            Marie marie = new Marie(tfMNom.getText(), Integer.parseInt(tfMAge.getText()), tfMPrenom.getText(), Integer.parseInt(tfMVetement.getText()), Integer.parseInt(tfMChaussure.getText()), Integer.parseInt(tfMAlliance.getText()), tfMEmail.getText(), tfMTel.getText());
            mariage.addMarie(marie);
            mariageCourant.addMarie(marie);
            // LOGGER SUCCESS$
            LOGGER.log(Level.INFO, "Marié créé avec succès");
            // on ferme la fenêtre
            ((Stage) ((Button) event.getSource()).getScene().getWindow()).close();
            // on met à jour la vue
            vbMarie2.getChildren().clear();
            GridPane g = new GridPane();
            g.add(new Label("Prénom : "), 0, 0);
            g.add(new Label("Nom : "), 0, 1);
            g.add(new Label("Age : "), 0, 2);
            g.add(new Label("Numéro de téléphone : "), 0, 3);
            g.add(new Label("Email : "), 0, 4);
            g.add(new Label("Vêtement : "), 0, 5);
            g.add(new Label("Chaussure : "), 0, 6);
            g.add(new Label("Alliance : "), 0, 7);
            g.add(new Label(marie.getPrenom()), 1, 0);
            g.add(new Label(marie.getNom()), 1, 1);
            g.add(new Label(String.valueOf(marie.getAge())), 1, 2);
            g.add(new Label(String.valueOf(marie.getNumtel())), 1, 3);
            g.add(new Label(marie.getEmail()), 1, 4);
            g.add(new Label((marie.getPrixVetement() + "€")), 1, 5);
            g.add(new Label((marie.getPrixChaussures() + "€")), 1, 6);
            g.add(new Label((marie.getPrixAlliance() + "€")), 1, 7);
            g.setStyle("-fx-border-color: #bfbcbc; -fx-border-width: 1; -fx-padding: 0 5px 0 5px;-fx-border-radius: 10px");
            vbMarie2.getChildren().add(g);
        }
    }

    @FXML
    private void btnNouveauInviteOnClick(ActionEvent event) throws IOException {
        FXMLLoader newInviteLoader = new FXMLLoader(getClass().getResource("new-invite-view.fxml"));
        newInviteLoader.setController(this);
        Scene newInviteScene = new Scene(newInviteLoader.load());
        newInviteScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("fenetreexterieur.css")).toExternalForm());

        Stage newUserWindow = new Stage();
        newUserWindow.setTitle("Créer un invité");
        newUserWindow.initModality(Modality.APPLICATION_MODAL);
        newUserWindow.setScene(newInviteScene);
        newUserWindow.showAndWait();
    }

    @FXML
    private void btnValiderInviteOnClick(ActionEvent event) throws IOException, MariageException{
        if (tfINom.getText().isEmpty() || tfIAge.getText().isEmpty() || tfIPrenom.getText().isEmpty()) {
            // on affiche un message d'erreur
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur lors de la création de l'invité");
            alert.setContentText("Veuillez remplir tous les champs");
            alert.showAndWait();
            // LOGGER ERROR
            LOGGER.log(Level.SEVERE, "Erreur lors de la création de l'invité, veuillez remplir tous les champs");
        }
        else {
            // on récupère les informations que l'on a entré dans le formulaire de création de contact
            Personne invite = new Personne(tfINom.getText(), Integer.parseInt(tfIAge.getText()), tfIPrenom.getText());
            mariages.get(mariages.size() - 1).addInvite(invite);
            // LOGGER SUCCESS
            LOGGER.log(Level.INFO, "Invité créé avec succès");
            // on ferme la fenêtre
            ((Stage) ((Button) event.getSource()).getScene().getWindow()).close();
            // on met à jour la vue
            GridPane g = new GridPane();
            g.add(new Label("Prénom : "), 0, 0);
            g.add(new Label("Nom : "), 0, 1);
            g.add(new Label("Age : "), 0, 2);
            g.add(new Label(tfIPrenom.getText()), 1, 0);
            g.add(new Label(tfINom.getText()), 1, 1);
            g.add(new Label(tfIAge.getText()), 1, 2);
            HBox h = new HBox();
            g.setStyle("-fx-border-color: #bfbcbc; -fx-border-width: 1; -fx-padding: 0 5px 0 5px;-fx-border-radius: 10px");
            h.setSpacing(10);
            h.getChildren().addAll(spInvite.getContent(), g);
            spInvite.setContent(h);
        }
    }

    @FXML
    private void btnNouveauBoissonOnClick(ActionEvent event) throws IOException {
        FXMLLoader newBoissonLoader = new FXMLLoader(getClass().getResource("new-boisson-view.fxml"));
        newBoissonLoader.setController(this);
        Scene newBoissonScene = new Scene(newBoissonLoader.load());
        newBoissonScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("fenetreexterieur.css")).toExternalForm());

        // on ajoute à la comboBox les moments de repas ou la boisson est prévue
        for (MomentRepas momentRepas : MomentRepas.values()) {
            cbRRepas.getItems().add(momentRepas);
        }

        Stage newUserWindow = new Stage();
        newUserWindow.setTitle("Créer une boisson");
        newUserWindow.initModality(Modality.APPLICATION_MODAL);
        newUserWindow.setScene(newBoissonScene);
        newUserWindow.showAndWait();

    }

    @FXML
    private void btnNouveauPlatOnClick(ActionEvent event) throws IOException {
        FXMLLoader newPlatLoader = new FXMLLoader(getClass().getResource("new-plat-view.fxml"));
        newPlatLoader.setController(this);
        Scene newPlatScene = new Scene(newPlatLoader.load());
        newPlatScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("fenetreexterieur.css")).toExternalForm());

        // on ajoute à la comboBox les moments de repas ou le plat est prévu
        for (MomentRepas momentRepas : MomentRepas.values()) {
            cbRRepas.getItems().add(momentRepas);
        }

        Stage newUserWindow = new Stage();
        newUserWindow.setTitle("Créer un plat");
        newUserWindow.initModality(Modality.APPLICATION_MODAL);
        newUserWindow.setScene(newPlatScene);
        newUserWindow.showAndWait();

    }

    @FXML
    private void btnValiderPlatClick(ActionEvent event) throws IOException, MariageException {
        if (tfRNom.getText().isEmpty() || tfRPrix.getText().isEmpty() || cbRRepas.getValue() == null) {
            // on affiche un message d'erreur
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur lors de la création du plat");
            alert.setContentText("Veuillez remplir tous les champs");
            alert.showAndWait();
            // LOGGER ERROR
            LOGGER.log(Level.SEVERE, "Erreur lors de la création du plat, veuillez remplir tous les champs");
        }
        else {
            try {
                boolean repasExiste = false;
                for (Repas repas : mariageCourant.getRepas()) {
                    if (repas.getMomentRepas().equals(cbRRepas.getValue())) {
                        repas.addPlat(new Plat(tfRNom.getText(), Double.parseDouble(tfRPrix.getText())));
                        repasExiste = true;
                    }
                }
                if (!repasExiste){
                    Repas repas = new Repas(cbRRepas.getValue());
                    repas.addPlat(new Plat(tfRNom.getText(), Double.parseDouble(tfRPrix.getText())));
                    mariageCourant.addRepas(repas);
                }
            } catch (NumberFormatException e){
                // on affiche un message d'erreur
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Erreur lors de la création du plat");
                alert.setContentText("Veuillez entrer un chiffre pour le prix");
                alert.showAndWait();
                // LOGGER ERROR
                LOGGER.log(Level.SEVERE, "Erreur lors de la création du plat, veuillez entrer un chiffre pour le prix");
            }
            // LOGGER SUCCESS
            LOGGER.log(Level.INFO, "Plat créé avec succès");
            // on ferme la fenêtre
            ((Stage) ((Button) event.getSource()).getScene().getWindow()).close();
            // on met à jour la vue
            GridPane g = new GridPane();
            g.add(new Label("Nom : "),0,0);
            g.add(new Label("Prix : "),0,1);
            g.add(new Label("moment : "),0,2);
            g.add(new Label(tfRNom.getText()),1,0);
            g.add(new Label(tfRPrix.getText() + "€"),1,1);
            g.add(new Label(cbRRepas.getValue().toString()),1,2);
            HBox h = new HBox();
            g.setStyle("-fx-border-color: #bfbcbc; -fx-border-width: 1; -fx-padding: 0 5px 0 5px;-fx-border-radius: 10px");
            h.setSpacing(10);
            h.getChildren().addAll(spPlat.getContent(),g);
            spPlat.setContent(h);
        }
    }


    @FXML
    private void btnValiderBoissonClick(ActionEvent event) throws IOException, MariageException {
        if (tfRNom.getText().isEmpty() || tfRPrix.getText().isEmpty() || cbRRepas.getValue() == null) {
            // on affiche un message d'erreur
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur lors de la création de la boisson");
            alert.setContentText("Veuillez remplir tous les champs");
            alert.showAndWait();
            // LOGGER ERROR
            LOGGER.log(Level.SEVERE, "Erreur lors de la création de la boisson, veuillez remplir tous les champs");
        }
        else {
            try {
                boolean repasExiste = false;
                for (Repas repas : mariages.get(mariages.size() - 1).getRepas()) {
                    if (repas.getMomentRepas().equals(cbRRepas.getValue())) {
                        repas.addBoisson(new Boisson(tfRNom.getText(), Double.parseDouble(tfRPrix.getText())));
                        repasExiste = true;
                    }
                }
                if (!repasExiste) {
                    Repas repas = new Repas(cbRRepas.getValue());
                    repas.addBoisson(new Boisson(tfRNom.getText(), Double.parseDouble(tfRPrix.getText())));
                    mariages.get(mariages.size() - 1).addRepas(repas);
                }
            } catch (NumberFormatException e){
                // on affiche un message d'erreur
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Erreur lors de la création de la boisson");
                alert.setContentText("Veuillez entrer un chiffre pour le prix");
                alert.showAndWait();
                // LOGGER ERROR
                LOGGER.log(Level.SEVERE, "Erreur lors de la création de la boisson, veuillez entrer un chiffre pour le prix");
            }
            // LOGGER SUCCESS
            LOGGER.log(Level.INFO, "Boisson créée avec succès");
            // on ferme la fenêtre
            ((Stage) ((Button) event.getSource()).getScene().getWindow()).close();
            // on met à jour la vue
            GridPane g = new GridPane();
            g.add(new Label("Nom : "), 0, 0);
            g.add(new Label("Prix : "), 0, 1);
            g.add(new Label("moment : "), 0, 2);
            g.add(new Label(tfRNom.getText()), 1, 0);
            g.add(new Label(tfRPrix.getText() + "€"), 1, 1);
            g.add(new Label(cbRRepas.getValue().toString()), 1, 2);
            HBox h = new HBox();
            g.setStyle("-fx-border-color: #bfbcbc; -fx-border-width: 1; -fx-padding: 0 5px 0 5px;-fx-border-radius: 10px");
            h.setSpacing(10);
            h.getChildren().addAll(spBoisson.getContent(), g);
            spBoisson.setContent(h);
        }
    }

    @FXML
    private void btnCloseOnClick(ActionEvent event) throws IOException {
        // on ferme la fenêtre
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void btnValiderMariageOnClick(ActionEvent event) throws IOException, MariageException, UtilisateurException {
        if (tfNbInvitesAdultes.getText().isEmpty() || tfNbInvitesEnfants.getText().isEmpty() || tfNbInvitesVinHonneur.getText().isEmpty() || tfIdMariage.getText().isEmpty() || tfNomMariage.getText().isEmpty() || dateMariage.getValue() == null){
            // on affiche un message d'erreur
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur lors de la création du mariage");
            alert.setContentText("Veuillez remplir tous les champs");
            alert.showAndWait();
            // LOGGER ERROR
            LOGGER.log(Level.SEVERE, "Erreur lors de la création du mariage, veuillez remplir tous les champs");
        }
        else{
            try {
                mariageCourant.setNombreAdultes(Integer.parseInt(tfNbInvitesAdultes.getText()));
                mariageCourant.setNombreEnfants(Integer.parseInt(tfNbInvitesEnfants.getText()));
                mariageCourant.setNombreInvitesVinHonneur(Integer.parseInt(tfNbInvitesVinHonneur.getText()));
                mariageCourant.setNom(tfNomMariage.getText());
                mariageCourant.setDate(dateMariage.getValue());
                mariageCourant.setId(tfIdMariage.getText());
                // on sauvegarde le mariage dans la base de données
                genevent.nouveauMariage(mariageCourant, utilisateurCourant.getEmail());
                // on sauvegarde l'état de l'application
                // LOGGER SUCCESS
                LOGGER.log(Level.INFO, "Mariage créé avec succès");

                // on ferme la fenêtre
                ((Stage) ((Button) event.getSource()).getScene().getWindow()).close();
            } catch (NumberFormatException e) {
                // on affiche un message d'erreur
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Erreur lors de la création du mariage");
                alert.setContentText("Veuillez entrer un nombre valide");
                alert.showAndWait();
                // LOGGER ERROR
                LOGGER.log(Level.SEVERE, "Erreur lors de la création du mariage, veuillez entrer un nombre valide");
            } catch (UtilisateurException e){
                // on affiche un message d'erreur
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Erreur lors de la création du mariage");
                alert.setContentText("Erreur lors de la création du mariage");
                alert.showAndWait();
                // LOGGER ERROR
                LOGGER.log(Level.SEVERE, "Erreur lors de la création du mariage");
            }

        }
    }


    // Fenêtre Contact ----------------------------------------------------------------------------------------------

    @FXML
    private void btnModifierContactOnAction(ActionEvent event) throws IOException, MariageException {
        try {
            if(!tfContactProfession.getText().isEmpty() && !tfContactMail.getText().isEmpty() && !tfContactNom.getText().isEmpty() && !tfContactTel.getText().isEmpty() && !tfContactHonoraires.getText().isEmpty() && !modifContactEnCours){
                // on supprime le contenu du label d'affichage du contact
                labelVboxContact.setText("Modification du contact");
                btnModifierContact.setText("Valider");
                btnSupprimerContact.setText("Annuler");
                // les textfields sont éditables
                tfContactNom.setEditable(true);
                tfContactMail.setEditable(true);
                tfContactTel.setEditable(true);
                tfContactProfession.setEditable(true);
                tfContactHonoraires.setEditable(true);
                modifContactEnCours = true;
            } else if (modifContactEnCours){
                // on récupère les informations que l'on a entré dans le formulaire de création de contact
                String nom = tfContactNom.getText();
                String mail = tfContactMail.getText();
                String tel = tfContactTel.getText();
                String profession = tfContactProfession.getText();
                String honoraires = tfContactHonoraires.getText();
                // on supprime le contact de la liste des contacts de l'utilisateur et de genevent
                this.utilisateurCourant.supprimerContact(contactCourant.getMail());
                genevent.getContacts().remove(contactCourant.getMail());
                // on ajoute le contact à la liste des contacts de l'utilisateur
                genevent.ajouteContact(nom, mail, tel, profession, Double.parseDouble(honoraires), utilisateurCourant);
                // on met à jour le contenu des boutons
                btnModifierContact.setText("Modifier");
                btnSupprimerContact.setText("Supprimer");
                // les textfields ne sont plus éditables
                tfContactNom.setEditable(false);
                tfContactMail.setEditable(false);
                tfContactTel.setEditable(false);
                tfContactProfession.setEditable(false);
                tfContactHonoraires.setEditable(false);
                modifContactEnCours = false;
            }

            // LOGGER INFO
            LOGGER.log(Level.INFO, "Modification du contact réussie");

        } catch (NumberFormatException e) {
            // on affiche un message d'erreur
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur lors de la modification du contact");
            alert.setContentText("Veuillez rentrer un chiffre pour les honoraires");
            alert.showAndWait();

            // LOGGER ERROR
            LOGGER.log(Level.SEVERE, "Erreur lors de la modification du contact : ");
        } catch (UtilisateurException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    private void btnSupprimerContactOnClick(ActionEvent event) throws IOException {

        // alerte de confirmation
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Suppression du contact");
        alert.setContentText("Êtes-vous sûr de vouloir supprimer ce contact ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            // on supprime le contact
            // on vérifie que les champs ne sont pas vide, sinon on ne fait rien et on affiche un message d'erreur
            if(!tfContactProfession.getText().isEmpty() && !tfContactMail.getText().isEmpty() && !tfContactNom.getText().isEmpty() && !tfContactTel.getText().isEmpty() && !tfContactHonoraires.getText().isEmpty() && !modifContactEnCours){
                // on supprime le contact de la liste des contacts de l'utilisateur
                this.utilisateurCourant.supprimerContact(tfContactMail.getText());
                genevent.getContacts().remove(utilisateurCourant.getEmail());

                // on supprime le contenu de chacun des labels à partir de la ligne 1
                for (Node label2 : gridpaneContact.getChildren()) {
                    // on récuềre la position du label
                    if (gridpaneContact.getRowIndex(label2) != null) {
                        int row = gridpaneContact.getRowIndex(label2);
                        if(row > 0){
                            // on met à jour le label
                            ((Label) label2).setText("");
                        }
                    }
                }

                // on ajoute un cadriage au gridpane
                gridpaneContact.setGridLinesVisible(true);

                // on ajoute "Numéro" et "Nom" en tant que titre des colonnes
                Label labelNom = new Label("Nom");
                labelNom.setMaxWidth(Double.MAX_VALUE);
                labelNom.setMaxHeight(Double.MAX_VALUE);
                labelNom.setId("labelContact");
                labelNom.setStyle("-fx-alignment: CENTER;");
                Label labelTel = new Label("Numéro");
                labelTel.setMaxWidth(Double.MAX_VALUE);
                labelTel.setMaxHeight(Double.MAX_VALUE);
                labelTel.setId("labelContact");
                labelTel.setStyle("-fx-alignment: CENTER;");
                gridpaneContact.add(labelTel, 0, 0);
                gridpaneContact.add(labelNom, 1, 0);

                // on efface les champs de la vue
                tfContactNom.clear();
                tfContactMail.clear();
                tfContactTel.clear();
                tfContactProfession.clear();
                tfContactHonoraires.clear();

                // on récupère les contacts de l'utilisateur et on l'insère dans la gridpane, on commence à itérer à partir de la ligne 1
                int i = 1;
                for (Map.Entry<String, Contact> entry : this.utilisateurCourant.getContacts().entrySet()) {
                    Label label = new Label(entry.getValue().getNom());
                    label.setMaxWidth(Double.MAX_VALUE);
                    label.setMaxHeight(Double.MAX_VALUE);
                    label.setId("labelContact");
                    Label label1 = new Label(entry.getValue().getNumTel());
                    label1.setMaxWidth(Double.MAX_VALUE);
                    label1.setMaxHeight(Double.MAX_VALUE);

                    label.setOnMouseClicked(event1 -> {
                        try {
                            afficheContact(event1);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                    gridpaneContact.add(label, 1, i);
                    gridpaneContact.add(label1, 0, i);
                    i++;
                }
            } else if(!modifContactEnCours) {
                // on affiche un message d'erreur
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Erreur lors de la suppression du contact");
                alert.setContentText("Veuillez remplir tous les champs");
                alert.showAndWait();
            } else {
                // on affiche un message d'erreur
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Erreur");
                alert.setHeaderText("Vous allez annuler les modifications apportées au contact");
                alert.setContentText("Veuillez remplir tous les champs");
                alert.showAndWait();
            }

        }





    }

    @FXML
    private void btnNewContactOnAction(ActionEvent event) throws IOException {
        // on ouvre une nouvelle fenêtre pour ajouter un contact
        FXMLLoader newContactViewLoader = new FXMLLoader(getClass().getResource("new-contact-view.fxml"));
        newContactViewLoader.setController(this);
        Stage newContactWindow = new Stage();
        Scene newContactScene = new Scene(newContactViewLoader.load());
        newContactScene.getStylesheets().add(getClass().getResource("fenetreexterieur.css").toExternalForm());
        // cacher la fenêtre principale
        ((Stage) ((Button) event.getSource()).getScene().getWindow()).hide();
        newContactWindow.setTitle("Nouveau Contact");
        newContactWindow.setScene(newContactScene);
        newContactWindow.show();
    }

    @FXML
    private void btnCreerContactOnAction(ActionEvent event) throws IOException, UtilisateurException, MariageException {
        // on récupère les informations que l'on a entré dans le formulaire de création de contact
        String nom = tfNewContactNom.getText();
        String mail = tfNewContactMail.getText();
        String tel = tfNewContactTel.getText();
        String profession = tfNewContactProfession.getText();
        String honoraires = tfNewContactHonoraires.getText();
        // convertir les honoraires en double
        double honorairesDouble = 0.0;
        // on vérifie que le champ est bien un chiffre
        try {
            honorairesDouble = Double.parseDouble(honoraires);
            // on ajoute le contact à la liste des contacts de l'utilisateur
            genevent.nouveauContact(nom, mail, tel, profession, honorairesDouble, utilisateurCourant.getEmail());
            // on enregistre l'état de genevent
            try {
                Persisteur.sauverEtat(genevent);
            }
            catch (IOException ignored) {
                System.err.println("Erreur irrécupérable pendant la sauvegarde de l'état : fin d'exécution !");
                System.err.flush();
                System.exit(Main.EXIT_ERR_SAVE);
            }
            // on charge la vue contact
            FXMLLoader contactViewLoader = new FXMLLoader(getClass().getResource("contact-view.fxml"));
            contactViewLoader.setController(this);
            Stage contactWindow = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Scene contactScene = new Scene(contactViewLoader.load());
            contactScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("main.css")).toExternalForm());
            // on récupère les contacts de l'utilisateur et on l'insère dans la gridpane, on commence à itérer à partir de la ligne 1
            int i = 1;
            for (Map.Entry<String, Contact> entry : this.utilisateurCourant.getContacts().entrySet()) {
                Label label = new Label(entry.getValue().getNom());
                label.setMaxWidth(Double.MAX_VALUE);
                label.setMaxHeight(Double.MAX_VALUE);
                label.setId("labelContact");
                Label label1 = new Label(entry.getValue().getNumTel());
                label1.setMaxWidth(Double.MAX_VALUE);
                label1.setMaxHeight(Double.MAX_VALUE);
                // on ajoute un événement lorsqu'on clique sur le label
                label.setOnMouseClicked(event1 -> {
                    try {
                        afficheContact(event1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                gridpaneContact.add(label, 1, i);
                gridpaneContact.add(label1, 0, i);
                i++;
            }
            contactWindow.setTitle("Contact");
            contactWindow.setScene(contactScene);
            contactWindow.show();
        } catch (NumberFormatException e) {
            // on affiche un message d'erreur
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur lors de la création du contact");
            alert.setContentText("Veuillez entrer un chiffre pour les honoraires");
            alert.showAndWait();
        }


    }

    @FXML
    private void btnAnnulerContactOnAction(ActionEvent event) throws IOException {
        // on charge la vue contact
        FXMLLoader contactViewLoader = new FXMLLoader(getClass().getResource("contact-view.fxml"));
        contactViewLoader.setController(this);
        Stage contactWindow = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Scene contactScene = new Scene(contactViewLoader.load());
        contactScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("main.css")).toExternalForm());

        // on récupère les contacts de l'utilisateur et on l'insère dans la gridpane, on commence à itérer à partir de la ligne 1
        int i = 1;
        for (Map.Entry<String, Contact> entry : this.utilisateurCourant.getContacts().entrySet()) {
            Label label = new Label(entry.getValue().getNom());
            label.setMaxWidth(Double.MAX_VALUE);
            label.setMaxHeight(Double.MAX_VALUE);
            label.setId("labelContact");
            Label label1 = new Label(entry.getValue().getNumTel());
            label1.setMaxWidth(Double.MAX_VALUE);
            label1.setMaxHeight(Double.MAX_VALUE);

            label.setOnMouseClicked(event1 -> {
                try {
                    afficheContact(event1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            gridpaneContact.add(label, 1, i);
            gridpaneContact.add(label1, 0, i);
            i++;
        }

        contactWindow.setTitle("Contact");
        contactWindow.setScene(contactScene);
        contactWindow.show();
    }

    @FXML
    private void afficheContact(MouseEvent event) throws IOException {
        // afficher le contact sur lequel on a cliqué
        Label btn = (Label) event.getSource();
        String nom = btn.getText();
        Contact contact = null;

        int i = 0;
        // on parcourt toute la liste des contacts tant que l'on a pas trouvé le contact sur lequel on a cliqué, et on le récupère
        for (Map.Entry<String, Contact> entry : this.utilisateurCourant.getContacts().entrySet()) {
            if (entry.getValue().getNom().equals(nom)) {
                contact = entry.getValue();
                contactCourant = contact;
                break;
            }
            i++;
        }

        if (contact == null ||contact.getNom().isEmpty()) {
            tfContactNom.setText("Aucune information");
            tfContactMail.setText("Aucune information");
            tfContactTel.setText("Aucune information");
            tfContactProfession.setText("Aucune information");
            tfContactHonoraires.setText("Aucune information");
        } else {
            // on met à jour les champs de la vue
            tfContactNom.setText(contact.getNom());
            tfContactMail.setText(contact.getMail());
            tfContactTel.setText(contact.getNumTel());
            tfContactProfession.setText(contact.getProfession());
            tfContactHonoraires.setText(String.valueOf(contact.getHonoraire()));
        }
    }

    // Fenêtre Archives ----------------------------------------------------------------------------------------------


    // Fenêtre Informations ----------------------------------------------------------------------------------------------


    @FXML
    private void btnModifierOnClick(ActionEvent event) throws IOException {
        // on charge la nouvelle vue pour modifier le numéro de téléphone
        FXMLLoader newInfoViewLoader = new FXMLLoader(getClass().getResource("new-info-view.fxml"));
        newInfoViewLoader.setController(this);
        Stage newInfoWindow = new Stage();
        Scene newInfoScene = new Scene(newInfoViewLoader.load());
        newInfoScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("fenetreexterieur.css")).toExternalForm());

        // cacher la fenêtre principale
        ((Stage) ((Button) event.getSource()).getScene().getWindow()).hide();

        // on récupère le parent de l'élément sur lequel on a cliqué
        HBox parent = (HBox) ((Button) event.getSource()).getParent();
        // on récupère le label
        Label label = (Label) parent.getChildren().get(0);
        // on récupère l'id du label
        String id = label.getId();
        if(id.compareTo("labelTelInfo") == 0){
            valeurChangee = "telephone";
            titleModifInfo.setText("Modifier le numéro de téléphone");
            labelAncienInfo.setText("Ancien numéro de téléphone :");
            labelNouveauInfo.setText("Nouveau numéro de téléphone :");
            labelConfirmerInfo.setText("Confirmer le nouveau numéro de téléphone :");
            if (this.utilisateurCourant.getTelephone() != null) {
                tfAncienInfo.setText(this.utilisateurCourant.getTelephone());
            }
        } else if(id.compareTo("labelSiretInfo") == 0){
            valeurChangee = "siret";
            titleModifInfo.setText("Modifier le numéro de Siret");
            labelAncienInfo.setText("Ancien numéro de Siret :");
            labelNouveauInfo.setText("Nouveau numéro de Siret :");
            labelConfirmerInfo.setText("Confirmer le nouveau numéro de Siret :");
            if (this.utilisateurCourant.getSiret() != null) {
                tfAncienInfo.setText(this.utilisateurCourant.getSiret());
            }
        } else if(id.compareTo("labelMailInfo") == 0){
            valeurChangee = "email";
            titleModifInfo.setText("Modifier l'adresse mail");
            labelAncienInfo.setText("Ancienne adresse mail :");
            labelNouveauInfo.setText("Nouvelle adresse mail :");
            labelConfirmerInfo.setText("Confirmer la nouvelle adresse mail :");
            if (this.utilisateurCourant.getEmail() != null) {
                tfAncienInfo.setText(this.utilisateurCourant.getEmail());
            }
        } else if (id.compareTo("labelMdpInfo") == 0) {
            valeurChangee = "mdp";
            titleModifInfo.setText("Modifier le mot de passe");
            labelAncienInfo.setText("Ancien mot de passe :");
            labelNouveauInfo.setText("Nouveau mot de passe :");
            labelConfirmerInfo.setText("Confirmer le nouveau mot de passe :");
        }

        newInfoWindow.setScene(newInfoScene);
        newInfoWindow.show();
    }

    @FXML
    private void btnValiderNewInfos(ActionEvent event) throws IOException, UtilisateurException {
        if (valeurChangee.compareTo("telephone") == 0 && tfNouveauInfo.getText() != null && tfNouveauInfo.getText().compareTo(tfConfirmerInfo.getText()) == 0 ) {
            // On créé un nouvel utilisateur avec les mêmes informations que l'utilisateur courant
            Utilisateur nouvelUtilisateur = new Utilisateur(utilisateurCourant.getNom(), utilisateurCourant.getPrenom(), utilisateurCourant.getEmail());
            nouvelUtilisateur.setContacts(utilisateurCourant.getContacts());
            nouvelUtilisateur.setSiret(utilisateurCourant.getSiret());
            nouvelUtilisateur.setTelephone(tfNouveauInfo.getText());

            // On remplace l'utilisateur courant par le nouvel utilisateur et on met à jour la liste des utilisateurs dans la base de données
            genevent.getUtilisateurs().remove(utilisateurCourant.getEmail());
            genevent.getUtilisateurs().put(nouvelUtilisateur.getEmail(), nouvelUtilisateur);
            utilisateurCourant = nouvelUtilisateur;


        } else if (valeurChangee.compareTo("siret") == 0 && tfNouveauInfo.getText() != null && tfNouveauInfo.getText().compareTo(tfConfirmerInfo.getText()) == 0) {
            utilisateurCourant.setSiret(tfNouveauInfo.getText());
        } else if (valeurChangee.compareTo("email") == 0 && tfNouveauInfo.getText() != null && tfNouveauInfo.getText().compareTo(tfConfirmerInfo.getText()) == 0) {

            // on créé un nouvel utilisateur avec les mêmes informations que l'utilisateur courant
            Utilisateur utilisateur = new Utilisateur(utilisateurCourant.getNom(), utilisateurCourant.getPrenom(), tfNouveauInfo.getText());
            utilisateur.setContacts(utilisateurCourant.getContacts());
            utilisateur.setSiret(utilisateurCourant.getSiret());
            utilisateur.setTelephone(utilisateurCourant.getTelephone());

        } else {
            if (tfNouveauInfo.getText().compareTo(tfConfirmerInfo.getText()) != 0){
                // on renvoie un erreur Alerte
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Erreur lors de la modification des informations");
                alert.setContentText("Les deux champs ne correspondent pas");
                alert.showAndWait();
            } else if (tfNouveauInfo.getText().isEmpty() || tfConfirmerInfo.getText().isEmpty()){
                // on renvoie un erreur Alerte
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Erreur lors de la modification des informations");
                alert.setContentText("Veuillez remplir tous les champs");
                alert.showAndWait();
            }
        }

        // on charge la vue contact
        FXMLLoader infoViewLoader = new FXMLLoader(getClass().getResource("info-view.fxml"));
        infoViewLoader.setController(this);
        Stage infoWindow = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Scene infoScene = new Scene(infoViewLoader.load());
        infoScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("main.css")).toExternalForm());

        if (utilisateurCourant.getTelephone() != null) {
            tfInfosTel.setText(utilisateurCourant.getTelephone());
        }
        if (utilisateurCourant.getPrenom() != null) {
            tfInfosPrenom.setText(utilisateurCourant.getPrenom());
        }
        if (utilisateurCourant.getSiret() != null) {
            tfInfosSiret.setText(utilisateurCourant.getSiret());
        }
        if (utilisateurCourant.getEmail() != null) {
            tfInfosMail.setText(utilisateurCourant.getEmail());
        }
        if (utilisateurCourant.getNom() != null) {
            tfInfosNom.setText(utilisateurCourant.getNom());
        }

        infoWindow.setTitle("Informations");
        infoWindow.setScene(infoScene);
        infoWindow.show();


    }

    @FXML
    private void btnAnnulerNewInfos(ActionEvent event) throws IOException {
        // on charge la vue contact
        FXMLLoader infoViewLoader = new FXMLLoader(getClass().getResource("info-view.fxml"));
        infoViewLoader.setController(this);
        Stage infoWindow = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Scene infoScene = new Scene(infoViewLoader.load());
        infoScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("main.css")).toExternalForm());

        if (utilisateurCourant.getTelephone() != null) {
            tfInfosTel.setText(utilisateurCourant.getTelephone());
        }
        if (utilisateurCourant.getPrenom() != null) {
            tfInfosPrenom.setText(utilisateurCourant.getPrenom());
        }
        if (utilisateurCourant.getSiret() != null) {
            tfInfosSiret.setText(utilisateurCourant.getSiret());
        }
        if (utilisateurCourant.getEmail() != null) {
            tfInfosMail.setText(utilisateurCourant.getEmail());
        }
        if (utilisateurCourant.getNom() != null) {
            tfInfosNom.setText(utilisateurCourant.getNom());
        }

        infoWindow.setTitle("Informations");
        infoWindow.setScene(infoScene);
        infoWindow.show();
    }


    // Fenêtre Calendrier ----------------------------------------------------------------------------------------------

}

