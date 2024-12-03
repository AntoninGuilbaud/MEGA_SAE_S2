package fr.uga.iut2.genevent.modele;

import fr.uga.iut2.genevent.exception.MariageException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RepasTest {

    @Test
    void getMomentRepas() {
        Repas repas = new Repas(MomentRepas.MIDI);
        assertEquals(MomentRepas.MIDI, repas.getMomentRepas());
    }

    @Test
    void getPlats() throws MariageException {
        Plat plat = new Plat("Plat1", 10.0);
        Repas repas = new Repas(MomentRepas.MIDI);
        repas.addPlat(plat);
        assertEquals(plat, repas.getPlats().get(0));
    }

    @Test
    void getBoissons() throws MariageException {
        Boisson boisson = new Boisson("Boisson1", 10.0);
        Repas repas = new Repas(MomentRepas.MIDI);
        repas.addBoisson(boisson);
        assertEquals(boisson, repas.getBoissons().get(0));
    }

    @Test
    void addPlat()   {
        Plat plat = null;
        Repas repas = new Repas(MomentRepas.MIDI);
        assertThrows(MariageException.class, () -> repas.addPlat(plat));
    }

    @Test
    void addBoisson() {
        Boisson boisson = null;
        Repas repas = new Repas(MomentRepas.MIDI);
        assertThrows(MariageException.class, () -> repas.addBoisson(boisson));
    }

    @Test
    void addListPlats() throws MariageException{
        Repas repas = new Repas(MomentRepas.MIDI);
        Plat plat1 = new Plat("Plat1", 10.0);
        Plat plat2 = null;
        ArrayList<Plat> plats = new ArrayList<>();
        plats.add(plat1);
        plats.add(plat2);
        assertThrows(MariageException.class, () -> repas.addListPlats(plats));


    }
}