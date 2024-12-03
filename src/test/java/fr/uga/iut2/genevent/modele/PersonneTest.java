package fr.uga.iut2.genevent.modele;

import fr.uga.iut2.genevent.exception.MariageException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonneTest {

    @Test
    void getNom() throws MariageException {
        Personne personne = new Personne("nom", 10, "prenom");
        assertEquals("nom", personne.getNom());
    }

    @Test
    void getAge() throws MariageException {
        Personne personne = new Personne("nom", 10, "prenom");
        assertEquals(10, personne.getAge());
    }

    @Test
    void getPrenom() throws MariageException {
        Personne personne = new Personne("nom", 10, "prenom");
        assertEquals("prenom", personne.getPrenom());
    }

    @Test
    void setNom() throws MariageException {
        Personne personne = new Personne("nom", 10, "prenom");
        assertEquals("nom", personne.getNom());
        assertThrows(MariageException.class, () -> personne.setNom(null));
    }

    @Test
    void setAge() throws MariageException {
        Personne personne = new Personne("nom", 10, "prenom");
        assertEquals(10, personne.getAge());
        assertThrows(MariageException.class, () -> personne.setAge(-1));
    }

    @Test
    void setPrenom() throws MariageException {
        Personne personne = new Personne("nom", 10, "prenom");
        assertEquals("prenom", personne.getPrenom());
        assertThrows(MariageException.class, () -> personne.setPrenom(null));
    }
}