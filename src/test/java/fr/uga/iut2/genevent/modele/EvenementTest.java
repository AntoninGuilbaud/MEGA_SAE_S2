package fr.uga.iut2.genevent.modele;

import fr.uga.iut2.genevent.exception.UtilisateurException;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;


class EvenementTest {

    @org.junit.jupiter.api.Test
    void initialiseEvenement() throws UtilisateurException {
        // Création d'un genevent
        GenEvent genevent = new GenEvent();
        // Création d'un utilisateur
        Utilisateur admin = new Utilisateur("jean@gmail.com", "Jean", "Dupont");
        // Création d'un événement et initialisation
        Evenement evt = Evenement.initialiseEvenement(genevent, "Mariage", LocalDate.of(2022, 6, 1), LocalDate.of(2022, 6, 2), admin);
        // vérification que l'événement est affilié à l'utilisateur
        assertTrue(admin.getEvenementsAdministres().containsValue(evt));
    }

    @org.junit.jupiter.api.Test
    void getNom() {
        // création d'un evenement
        Evenement evt = new Evenement(new GenEvent(), "Mariage", LocalDate.of(2022, 6, 1), LocalDate.of(2022, 6, 2));
        // vérification du nom
        assertEquals("Mariage", evt.getNom());
    }

    @org.junit.jupiter.api.Test
    void getDateDebut() {
        // création d'un evenement
        Evenement evt = new Evenement(new GenEvent(), "Mariage", LocalDate.of(2022, 6, 1), LocalDate.of(2022, 6, 2));
        // vérification de la date de début
        assertEquals(LocalDate.of(2022, 6, 1), evt.getDateDebut());
    }

    @org.junit.jupiter.api.Test
    void setDateDebut() {
        // création d'un evenement
        Evenement evt = new Evenement(new GenEvent(), "Mariage", LocalDate.of(2022, 6, 1), LocalDate.of(2022, 6, 2));
        // modification de la date de début
        evt.setDateDebut(LocalDate.of(2022, 6, 2));
        // vérification de la date de début
        assertEquals(LocalDate.of(2022, 6, 2), evt.getDateDebut());
    }

    @org.junit.jupiter.api.Test
    void getDateFin() {
        // création d'un evenement
        Evenement evt = new Evenement(new GenEvent(), "Mariage", LocalDate.of(2022, 6, 1), LocalDate.of(2022, 6, 2));
        // vérification de la date de fin
        assertEquals(LocalDate.of(2022, 6, 2), evt.getDateFin());
    }

    @org.junit.jupiter.api.Test
    void setDateFin() {
        // création d'un evenement
        Evenement evt = new Evenement(new GenEvent(), "Mariage", LocalDate.of(2022, 6, 1), LocalDate.of(2022, 6, 2));
        // modification de la date de fin
        evt.setDateFin(LocalDate.of(2022, 6, 3));
        // vérification de la date de fin
        assertEquals(LocalDate.of(2022, 6, 3), evt.getDateFin());
    }

    @org.junit.jupiter.api.Test
    void ajouteAdministrateur() throws UtilisateurException {
        // Création d'un utilisateur
        Utilisateur admin = new Utilisateur("jean@gmail.com", "Jean", "Dupont");
        // Création d'un événement et initialisation
        Evenement evt = new Evenement(new GenEvent(), "Mariage", LocalDate.of(2022, 6, 1), LocalDate.of(2022, 6, 2));
        // ajout de l'administrateur
        evt.ajouteAdministrateur(admin);
        // vérification que l'événement est affilié à l'utilisateur
        assertTrue(admin.getEvenementsAdministres().containsValue(evt));
    }
}