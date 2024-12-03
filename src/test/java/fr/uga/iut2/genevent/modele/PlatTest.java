package fr.uga.iut2.genevent.modele;

import fr.uga.iut2.genevent.exception.MariageException;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

class PlatTest {

    @Test
    void getNom() throws MariageException {
        Plat plat = new Plat("nom", 10.0);
        assertEquals("nom", plat.getNom());
    }

    @Test
    void getPrixParPersonne() throws MariageException {
        Plat plat = new Plat("nom", 10.0);
        assertEquals(10.0, plat.getPrixParPersonne());
    }

    @Test
    void setPrixParPersonne() throws MariageException {
        Plat plat = new Plat("nom", 10.0);
        plat.setPrixParPersonne(20.0);
        assertEquals(20.0, plat.getPrixParPersonne());
        assertThrows(MariageException.class, () -> plat.setPrixParPersonne(-1.0));
    }

    @Test
    void setNom() throws MariageException {
        Plat plat = new Plat("nom", 10.0);
        assertEquals("nom", plat.getNom());
        assertThrows(MariageException.class, () -> plat.setNom(null));
    }
}