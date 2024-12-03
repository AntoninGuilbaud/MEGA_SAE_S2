package fr.uga.iut2.genevent.modele;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenreMusicalTest {

    @Test
    void fromString() {
        assertEquals(GenreMusical.ROCK, GenreMusical.fromString("ROCK"));
        assertEquals(GenreMusical.COUNTRY, GenreMusical.fromString("COUNTRY"));
        assertEquals(GenreMusical.POP, GenreMusical.fromString("POP"));
        assertEquals(GenreMusical.RAP, GenreMusical.fromString("RAP"));
        assertEquals(GenreMusical.SLOW, GenreMusical.fromString("SLOW"));
        assertEquals(GenreMusical.DISCO, GenreMusical.fromString("DISCO"));
        assertEquals(GenreMusical.LOUNGE, GenreMusical.fromString("LOUNGE"));
        assertThrows(IllegalArgumentException.class, () -> GenreMusical.fromString("JAZZ"));
    }

    @Test
    void testToString() {
        assertEquals("ROCK", GenreMusical.ROCK.toString());
        assertEquals("COUNTRY", GenreMusical.COUNTRY.toString());
        assertEquals("POP", GenreMusical.POP.toString());
        assertEquals("RAP", GenreMusical.RAP.toString());
        assertEquals("SLOW", GenreMusical.SLOW.toString());
        assertEquals("DISCO", GenreMusical.DISCO.toString());
        assertEquals("LOUNGE", GenreMusical.LOUNGE.toString());
    }
}