package fr.uga.iut2.genevent.modele;

import fr.uga.iut2.genevent.exception.MariageException;

import static org.junit.jupiter.api.Assertions.*;

class BoissonTest {

    @org.junit.jupiter.api.Test
    void getNom() throws MariageException {
        // Création d'une boisson
        Boisson boisson = new Boisson("Coca", 2.5);
        // Vérification du nom
        assertEquals("Coca", boisson.getNom());
        // Vérification de la levée d'exception
        assertThrows(MariageException.class, () -> new Boisson("", 2.5));
    }

    @org.junit.jupiter.api.Test
    void getPrixParPersonne() throws MariageException {
        // Création d'une boisson
        Boisson boisson = new Boisson("Coca", 2.5);
        // Vérification du prix par personne
        assertEquals(2.5, boisson.getPrixParPersonne());
        // Vérification de la levée d'exception
        assertThrows(MariageException.class, () -> new Boisson("Coca", -2.5));
    }

    @org.junit.jupiter.api.Test
    void setNom() throws MariageException {
        // Création d'une boisson
        Boisson boisson = new Boisson("Coca", 2.5);
        // Modification du nom
        boisson.setNom("Pepsi");
        // Vérification du nom
        assertEquals("Pepsi", boisson.getNom());
        // Vérification de la levée d'exception
        assertThrows(MariageException.class, () -> boisson.setNom(""));
    }

    @org.junit.jupiter.api.Test
    void setPrixParPersonne() throws MariageException {
        // Création d'une boisson
        Boisson boisson = new Boisson("Coca", 2.5);
        // Modification du prix par personne
        boisson.setPrixParPersonne(5);
        // Vérification du prix par personne
        assertEquals(5, boisson.getPrixParPersonne());
        // Vérification de la levée d'exception
        assertThrows(MariageException.class, () -> boisson.setPrixParPersonne(-2.5));
    }
}