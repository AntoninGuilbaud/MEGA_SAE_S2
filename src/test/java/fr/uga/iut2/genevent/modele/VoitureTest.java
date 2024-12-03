package fr.uga.iut2.genevent.modele;

import fr.uga.iut2.genevent.exception.MariageException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class VoitureTest {

    @Test
    void getMarque() throws MariageException {
        Voiture voiture = new Voiture("Renault", 10000);
        assertEquals("Renault", voiture.getMarque());
    }

    @Test
    void getPrix() throws MariageException {
        Voiture voiture = new Voiture("Renault", 10000);
        assertEquals(10000, voiture.getPrix());
    }


    @Test
    void setMarque() throws MariageException {
        Voiture voiture = new Voiture("Renault", 10000);
        voiture.setMarque("Peugeot");
        assertEquals("Peugeot", voiture.getMarque());

        assertThrows(MariageException.class, () -> voiture.setMarque(""));
    }

    @Test
    void setPrix() throws MariageException {
        Voiture voiture = new Voiture("Renault", 10000);
        voiture.setPrix(20000);
        assertEquals(20000, voiture.getPrix());

        assertThrows(MariageException.class, () -> voiture.setPrix(-1));
    }

}