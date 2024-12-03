package fr.uga.iut2.genevent.modele;

import fr.uga.iut2.genevent.exception.MariageException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GroupeMusiqueTest {

    @Test
    void getDuree() throws MariageException {
        // Création d'un groupe de musique
        GroupeMusique groupe = new GroupeMusique(2,18, "Les Beatles", GenreMusical.ROCK, 1000);
        // Vérification de la durée
        assertEquals(2, groupe.getDuree());
        // Vérification de la levée d'exception
        assertThrows(MariageException.class, () -> new GroupeMusique(-2,18, "Les Beatles", GenreMusical.ROCK, 1000));
    }

    @Test
    void getHeurePassage() throws MariageException {
        // Création d'un groupe de musique
        GroupeMusique groupe = new GroupeMusique(2,18, "Les Beatles", GenreMusical.ROCK, 1000);
        // Vérification de l'heure de passage
        assertEquals(18, groupe.getHeurePassage());
        // Vérification de la levée d'exception
        assertThrows(MariageException.class, () -> new GroupeMusique(2,-18, "Les Beatles", GenreMusical.ROCK, 1000));
    }

    @Test
    void getNom() throws MariageException {
        // Création d'un groupe de musique
        GroupeMusique groupe = new GroupeMusique(2,18, "Les Beatles", GenreMusical.ROCK, 1000);
        // Vérification du nom
        assertEquals("Les Beatles", groupe.getNom());
        // Vérification de la levée d'exception
        assertThrows(MariageException.class, () -> new GroupeMusique(2,18, "", GenreMusical.ROCK, 1000));
    }

    @Test
    void getGenreMusical() throws MariageException {
        // Création d'un groupe de musique
        GroupeMusique groupe = new GroupeMusique(2,18, "Les Beatles", GenreMusical.ROCK, 1000);
        // Vérification du genre musical
        assertEquals(GenreMusical.ROCK, groupe.getGenreMusical());
        // Vérification de la levée d'exception
        assertThrows(MariageException.class, () -> new GroupeMusique(2,18, "Les Beatles", null, 1000));
    }

    @Test
    void getPrix() throws MariageException {
        // Création d'un groupe de musique
        GroupeMusique groupe = new GroupeMusique(2,18, "Les Beatles", GenreMusical.ROCK, 1000);
        // Vérification du prix
        assertEquals(1000, groupe.getPrix());
        // Vérification de la levée d'exception
        assertThrows(MariageException.class, () -> new GroupeMusique(2,18, "Les Beatles", GenreMusical.ROCK, -1000));
    }

    @Test
    void setDuree() throws MariageException {
        // Création d'un groupe de musique
        GroupeMusique groupe = new GroupeMusique(2,18, "Les Beatles", GenreMusical.ROCK, 1000);
        // Modification de la durée
        groupe.setDuree(3);
        // Vérification de la durée
        assertEquals(3, groupe.getDuree());
        // Vérification de la levée d'exception
        assertThrows(MariageException.class, () -> groupe.setDuree(-3));
    }

    @Test
    void setHeurePassage() throws MariageException {
        // Création d'un groupe de musique
        GroupeMusique groupe = new GroupeMusique(2,18, "Les Beatles", GenreMusical.ROCK, 1000);
        // Modification de l'heure de passage
        groupe.setHeurePassage(19);
        // Vérification de l'heure de passage
        assertEquals(19, groupe.getHeurePassage());
        // Vérification de la levée d'exception
        assertThrows(MariageException.class, () -> groupe.setHeurePassage(-19));
    }

    @Test
    void setNom() throws MariageException {
        // Création d'un groupe de musique
        GroupeMusique groupe = new GroupeMusique(2,18, "Les Beatles", GenreMusical.ROCK, 1000);
        // Modification du nom
        groupe.setNom("Les Rolling Stones");
        // Vérification du nom
        assertEquals("Les Rolling Stones", groupe.getNom());
        // Vérification de la levée d'exception
        assertThrows(MariageException.class, () -> groupe.setNom(""));
    }

    @Test
    void setGenreMusical() throws MariageException {
        // Création d'un groupe de musique
        GroupeMusique groupe = new GroupeMusique(2,18, "Les Beatles", GenreMusical.ROCK, 1000);
        // Modification du genre musical
        groupe.setGenreMusical(GenreMusical.POP);
        // Vérification du genre musical
        assertEquals(GenreMusical.POP, groupe.getGenreMusical());
        // Vérification de la levée d'exception
        assertThrows(MariageException.class, () -> groupe.setGenreMusical(null));
    }

    @Test
    void setPrix() throws MariageException {
        // Création d'un groupe de musique
        GroupeMusique groupe = new GroupeMusique(2,18, "Les Beatles", GenreMusical.ROCK, 1000);
        // Modification du prix
        groupe.setPrix(2000);
        // Vérification du prix
        assertEquals(2000, groupe.getPrix());
        // Vérification de la levée d'exception
        assertThrows(MariageException.class, () -> groupe.setPrix(-2000));
    }
}