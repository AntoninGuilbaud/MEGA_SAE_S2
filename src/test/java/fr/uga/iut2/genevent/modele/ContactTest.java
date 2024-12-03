package fr.uga.iut2.genevent.modele;

import fr.uga.iut2.genevent.exception.MariageException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContactTest {

    @Test
    void getNom() throws MariageException {
        // Création d'un contact
        Contact contact = new Contact("Jean", "jean@gmail.com", "0123456789", "Professeur", 1000);
        // Vérification du nom
        assertEquals("Jean", contact.getNom());
        // Vérification de la levée d'exception
        assertThrows(MariageException.class, () -> new Contact("", "jean@gmail.com", "0123456789", "Professeur", 1000));
    }

    @Test
    void getNumTel() throws MariageException {
        // Création d'un contact
        Contact contact = new Contact("Jean", "jean@gmail.com", "0123456789", "Professeur", 1000);
        // Vérification du numéro de téléphone
        assertEquals("0123456789", contact.getNumTel());
        // Vérification de la levée d'exception
        assertThrows(MariageException.class, () -> new Contact("Jean", "jean@gmail.com", "", "Professeur", 1000));
    }

    @Test
    void getProfession() throws MariageException {
        // Création d'un contact
        Contact contact = new Contact("Jean", "jean@gmail.com", "0123456789", "Professeur", 1000);
        // Vérification de la profession
        assertEquals("Professeur", contact.getProfession());
        // Vérification de la levée d'exception
        assertThrows(MariageException.class, () -> new Contact("Jean", "jean@gmail.com", "0123456789", "", 1000));
    }

    @Test
    void getMail() throws MariageException {
        // Création d'un contact
        Contact contact = new Contact("Jean", "jean@gmail.com", "0123456789", "Professeur", 1000);
        // Vérification du mail
        assertEquals("jean@gmail.com", contact.getMail());
        // Vérification de la levée d'exception
        assertThrows(MariageException.class, () -> new Contact("Jean", "", "0123456789", "Professeur", 1000));
    }

    @Test
    void getHonoraire() throws MariageException {
        // Création d'un contact
        Contact contact = new Contact("Jean", "jean@gmail.com", "0123456789", "Professeur", 1000);
        // Vérification de l'honoraire
        assertEquals(1000, contact.getHonoraire());
        // Vérification de la levée d'exception
        assertThrows(MariageException.class, () -> new Contact("Jean", "jean@gmail.com", "0123456789", "Professeur", -1000));
    }

    @Test
    void setNom() throws MariageException {
        // Création d'un contact
        Contact contact = new Contact("Jean", "jean@gmail.com", "0123456789", "Professeur", 1000);
        // Modification du nom
        contact.setNom("Paul");
        // Vérification du nom
        assertEquals("Paul", contact.getNom());
        // Vérification de la levée d'exception
        assertThrows(MariageException.class, () -> contact.setNom(""));
    }

    @Test
    void setMail() throws MariageException {
        // Création d'un contact
        Contact contact = new Contact("Jean", "jean@gmail.com", "0123456789", "Professeur", 1000);
        // Modification du mail
        contact.setMail("change@free.fr");
        // Vérification du mail
        assertEquals("change@free.fr", contact.getMail());
        // Vérification de la levée d'exception
        assertThrows(MariageException.class, () -> contact.setMail(""));
    }

    @Test
    void setNumTel() throws MariageException {
        // Création d'un contact
        Contact contact = new Contact("Jean", "jean@gmail.com", "0123456789", "Professeur", 1000);
        // Modification du numéro de téléphone
        contact.setNumTel("1111111111");
        // Vérification du numéro de téléphone
        assertEquals("1111111111", contact.getNumTel());
        // Vérification de la levée d'exception
        assertThrows(MariageException.class, () -> contact.setNumTel(""));
    }

    @Test
    void setMetier() throws MariageException {
        // Création d'un contact
        Contact contact = new Contact("Jean", "jean@gmail.com", "0123456789", "Professeur", 1000);
        // Modification de la profession
        contact.setMetier("Etudiant");
        // Vérification de la profession
        assertEquals("Etudiant", contact.getProfession());
        // Vérification de la levée d'exception
        assertThrows(MariageException.class, () -> contact.setMetier(""));
    }

    @Test
    void setHonoraire() throws MariageException {
        // Création d'un contact
        Contact contact = new Contact("Jean", "jean@gmail.com", "0123456789", "Professeur", 1000);
        // Modification de l'honoraire
        contact.setHonoraire(2000);
        // Vérification de l'honoraire
        assertEquals(2000, contact.getHonoraire());
        // Vérification de la levée d'exception
        assertThrows(MariageException.class, () -> contact.setHonoraire(-1000));
    }
}