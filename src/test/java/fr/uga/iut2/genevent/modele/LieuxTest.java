package fr.uga.iut2.genevent.modele;

import fr.uga.iut2.genevent.exception.MariageException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LieuxTest {

    @Test
    void getNom() throws MariageException {
        // Création d'un lieu
        Lieux lieu = new Lieux("Salle des fêtes", "1 rue de la mairie", 1000, 200, true);
        // Vérification du nom
        assertEquals("Salle des fêtes", lieu.getNom());
        // Vérification de la levée d'exception
        assertThrows(MariageException.class, () -> new Lieux("", "1 rue de la mairie", 1000, 200, true));
    }

    @Test
    void getAdresse() throws MariageException {
        // Création d'un lieu
        Lieux lieu = new Lieux("Salle des fêtes", "1 rue de la mairie", 1000, 200, true);
        // Vérification de l'adresse
        assertEquals("1 rue de la mairie", lieu.getAdresse());
        // Vérification de la levée d'exception
        assertThrows(MariageException.class, () -> new Lieux("Salle des fêtes", "", 1000, 200, true));
    }

    @Test
    void getCoutLocation() throws MariageException {
        // Création d'un lieu
        Lieux lieu = new Lieux("Salle des fêtes", "1 rue de la mairie", 1000, 200, true);
        // Vérification du coût de location
        assertEquals(1000, lieu.getCoutLocation());
        // Vérification de la levée d'exception
        assertThrows(MariageException.class, () -> new Lieux("Salle des fêtes", "1 rue de la mairie", -1000, 200, true));
    }

    @Test
    void getCapacite() throws MariageException {
        // Création d'un lieu
        Lieux lieu = new Lieux("Salle des fêtes", "1 rue de la mairie", 1000, 200, true);
        // Vérification de la capacité
        assertEquals(200, lieu.getCapacite());
        // Vérification de la levée d'exception
        assertThrows(MariageException.class, () -> new Lieux("Salle des fêtes", "1 rue de la mairie", 1000, -200, true));
    }

    @Test
    void getLogement() throws MariageException {
        // Création d'un lieu
        Lieux lieu = new Lieux("Salle des fêtes", "1 rue de la mairie", 1000, 200, true);
        // Vérification du logement
        assertTrue(lieu.getLogement());
    }

    @Test
    void setNom() throws MariageException {
        // Création d'un lieu
        Lieux lieu = new Lieux("Salle des fêtes", "1 rue de la mairie", 1000, 200, true);
        // Modification du nom
        lieu.setNom("Salle des fêtes de la mairie");
        // Vérification du nom
        assertEquals("Salle des fêtes de la mairie", lieu.getNom());
        // Vérification de la levée d'exception
        assertThrows(MariageException.class, () -> lieu.setNom(""));
    }

    @Test
    void setAdresse() throws MariageException {
        // Création d'un lieu
        Lieux lieu = new Lieux("Salle des fêtes", "1 rue de la mairie", 1000, 200, true);
        // Modification de l'adresse
        lieu.setAdresse("2 rue de la Touque");
        // Vérification de l'adresse
        assertEquals("2 rue de la Touque", lieu.getAdresse());
        // Vérification de la levée d'exception
        assertThrows(MariageException.class, () -> lieu.setAdresse(""));
    }

    @Test
    void setCoutLocation() throws MariageException {
        // Création d'un lieu
        Lieux lieu = new Lieux("Salle des fêtes", "1 rue de la mairie", 1000, 200, true);
        // Modification du coût de location
        lieu.setCoutLocation(2000);
        // Vérification du coût de location
        assertEquals(2000, lieu.getCoutLocation());
        // Vérification de la levée d'exception
        assertThrows(MariageException.class, () -> lieu.setCoutLocation(-2000));
    }

    @Test
    void setCapacite() throws MariageException {
        // Création d'un lieu
        Lieux lieu = new Lieux("Salle des fêtes", "1 rue de la mairie", 1000, 200, true);
        // Modification de la capacité
        lieu.setCapacite(300);
        // Vérification de la capacité
        assertEquals(300, lieu.getCapacite());
        // Vérification de la levée d'exception
        assertThrows(MariageException.class, () -> lieu.setCapacite(-300));
    }

    @Test
    void setLogement() throws MariageException {
        // Création d'un lieu
        Lieux lieu = new Lieux("Salle des fêtes", "1 rue de la mairie", 1000, 200, true);
        // Modification du logement
        lieu.setLogement(false);
        // Vérification du logement
        assertFalse(lieu.getLogement());
    }
}