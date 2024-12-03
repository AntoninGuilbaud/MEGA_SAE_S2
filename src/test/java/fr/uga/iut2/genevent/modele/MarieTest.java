package fr.uga.iut2.genevent.modele;

import fr.uga.iut2.genevent.exception.MariageException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MarieTest {

    @Test
    void getPrixVetement() throws MariageException {
        Marie marie = new Marie("nom", 20, "prenom", 100, 50, 200, "email", "numtel");
        assertEquals(100, marie.getPrixVetement());
    }

    @Test
    void getPrixChaussures() throws MariageException {
        Marie marie = new Marie("nom", 20, "prenom", 100, 50, 200, "email", "numtel");
        assertEquals(50, marie.getPrixChaussures());
    }

    @Test
    void getPrixAlliance() throws MariageException {
        Marie marie = new Marie("nom", 20, "prenom", 100, 50, 200, "email", "numtel");
        assertEquals(200, marie.getPrixAlliance());
    }

    @Test
    void getEmail() throws MariageException {
        Marie marie = new Marie("nom", 20, "prenom", 100, 50, 200, "email", "numtel");
        assertEquals("email", marie.getEmail());
    }

    @Test
    void getNumtel() throws MariageException {
        Marie marie = new Marie("nom", 20, "prenom", 100, 50, 200, "email", "numtel");
        assertEquals("numtel", marie.getNumtel());
    }

    @Test
    void setPrixVetement() throws MariageException {
        Marie marie = new Marie("nom", 20, "prenom", 100, 50, 200, "email", "numtel");
        marie.setPrixVetement(150);
        assertEquals(150, marie.getPrixVetement());
        assertThrows(MariageException.class, () -> marie.setPrixVetement(-10));
    }

    @Test
    void setPrixChaussures() throws MariageException {
        Marie marie = new Marie("nom", 20, "prenom", 100, 50, 200, "email", "numtel");
        marie.setPrixChaussures(150);
        assertEquals(150, marie.getPrixChaussures());
        assertThrows(MariageException.class, () -> marie.setPrixChaussures(-10));
    }

    @Test
    void setPrixAlliance() throws MariageException {
        Marie marie = new Marie("nom", 20, "prenom", 100, 50, 200, "email", "numtel");
        marie.setPrixAlliance(150);
        assertEquals(150, marie.getPrixAlliance());
        assertThrows(MariageException.class, () -> marie.setPrixAlliance(-10));
    }

    @Test
    void setEmail() throws MariageException {
        Marie marie = new Marie("nom", 20, "prenom", 100, 50, 200, "email", "numtel");
        marie.setEmail("email2");
        assertEquals("email2", marie.getEmail());
        assertThrows(MariageException.class, () -> marie.setEmail(""));
    }

    @Test
    void setNumtel() throws MariageException {
        Marie marie = new Marie("nom", 20, "prenom", 100, 50, 200, "email", "numtel");
        marie.setNumtel("numtel2");
        assertEquals("numtel2", marie.getNumtel());
        assertThrows(MariageException.class, () -> marie.setNumtel(""));
    }
}