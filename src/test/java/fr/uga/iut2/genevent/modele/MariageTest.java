package fr.uga.iut2.genevent.modele;

import fr.uga.iut2.genevent.exception.MariageException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MariageTest {

    @Test
    void getId() throws MariageException {
        // Création d'un mariage
        Mariage mariage = new Mariage("27951", LocalDate.of(2024,12,1), "mariage de Pierre et Sophie", "Princesse", 82, 24, 78);
        // Vérification de l'identifiant
        assertEquals("27951", mariage.getId());
        // Vérification de la levée d'exception
        assertThrows(MariageException.class, () -> new Mariage("", LocalDate.of(2024,12,1), "mariage de Pierre et Sophie", "Princesse", 82, 24, 78));
    }

    @Test
    void getDate() throws MariageException {
        // Création d'un mariage
        Mariage mariage = new Mariage("27951", LocalDate.of(2024,12,1), "mariage de Pierre et Sophie", "Princesse", 82, 24, 78);
        // Vérification de la date
        assertEquals(LocalDate.of(2024,12,1), mariage.getDate());
        // Vérification de la levée d'exception
        assertThrows(MariageException.class, () -> new Mariage("27951", null, "mariage de Pierre et Sophie", "Princesse", 82, 24, 78));
    }

    @Test
    void getNom() throws MariageException {
        // Création d'un mariage
        Mariage mariage = new Mariage("27951", LocalDate.of(2024,12,1), "mariage de Pierre et Sophie", "Princesse", 82, 24, 78);
        // Vérification du nom
        assertEquals("mariage de Pierre et Sophie", mariage.getNom());
        // Vérification de la levée d'exception
        assertThrows(MariageException.class, () -> new Mariage("27951", LocalDate.of(2024,12,1), "", "Princesse", 82, 24, 78));
    }

    @Test
    void getMarie1() throws MariageException {
        // Création d'un mariage
        Mariage mariage = new Mariage("27951", LocalDate.of(2024,12,1), "mariage de Pierre et Sophie", "Princesse", 82, 24, 78);
        // Création d'un marié
        Marie marie = new Marie("Dupond", 24, "Pierre",250,126,785,"pierre.dupond38@gmail.com", "0612345678");
        // Ajout du marié
        mariage.addMarie(marie);
        // Vérification du nom du premier marié
        assertEquals(marie, mariage.getMarie1());
}

    @Test
    void getMarie2() throws MariageException {
        // Création d'un mariage
        Mariage mariage = new Mariage("27951", LocalDate.of(2024,12,1), "mariage de Pierre et Sophie", "Princesse", 82, 24, 78);
        // Création de deux mariés
        Marie marie = new Marie("Dupond", 24, "Pierre",250,126,785,"pierre.dupond38@gmail.com", "0612345678");
        Marie marie2 = new Marie("Dupond", 24, "Sophie",250,126,785,"sophie.dupond61@gmail.com", "0612345678");
        // Ajout des deux mariés
        mariage.addMarie(marie);
        mariage.addMarie(marie2);
        // Vérification du nom du premier marié
        assertEquals(marie2, mariage.getMarie2());
    }

    @Test
    void getTheme() throws MariageException {
        // Création d'un mariage
        Mariage mariage = new Mariage("27951", LocalDate.of(2024,12,1), "mariage de Pierre et Sophie", "Princesse", 82, 24, 78);
        // Vérification du thème
        assertEquals("Princesse", mariage.getTheme());
        // Vérification de la levée d'exception
        assertThrows(MariageException.class, () -> new Mariage("27951", LocalDate.of(2024,12,1), "mariage de Pierre et Sophie", "", 82, 24, 78));
    }

    @Test
    void getNombreAdultes() throws MariageException {
        // Création d'un mariage
        Mariage mariage = new Mariage("27951", LocalDate.of(2024,12,1), "mariage de Pierre et Sophie", "Princesse", 82, 24, 78);
        // Vérification du nombre d'adultes
        assertEquals(82, mariage.getNombreAdultes());
        // Vérification de la levée d'exception
        assertThrows(MariageException.class, () -> new Mariage("27951", LocalDate.of(2024,12,1), "mariage de Pierre et Sophie", "Princesse", -82, 24, 78));
    }

    @Test
    void getNombreEnfants() throws MariageException {
        // Création d'un mariage
        Mariage mariage = new Mariage("27951", LocalDate.of(2024,12,1), "mariage de Pierre et Sophie", "Princesse", 82, 24, 78);
        // Vérification du nombre d'enfants
        assertEquals(24, mariage.getNombreEnfants());
        // Vérification de la levée d'exception
        assertThrows(MariageException.class, () -> new Mariage("27951", LocalDate.of(2024,12,1), "mariage de Pierre et Sophie", "Princesse", 82, -24, 78));
    }

    @Test
    void getNombreInvitesVinHonneur() throws MariageException {
        // Création d'un mariage
        Mariage mariage = new Mariage("27951", LocalDate.of(2024,12,1), "mariage de Pierre et Sophie", "Princesse", 82, 24, 78);
        // Vérification du nombre d'invités au vin d'honneur
        assertEquals(78, mariage.getNombreInvitesVinHonneur());
        // Vérification de la levée d'exception
        assertThrows(MariageException.class, () -> new Mariage("27951", LocalDate.of(2024,12,1), "mariage de Pierre et Sophie", "Princesse", 82, 24, -78));
    }

    @Test
    void getPrix() throws MariageException {
        // Création d'un mariage
        Mariage mariage = new Mariage("27951", LocalDate.of(2024,12,1), "mariage de Pierre et Sophie", "Princesse", 82, 24, 78);
        // Création d'un groupe de musique
        GroupeMusique groupe = new GroupeMusique(2,18, "Les Beatles", GenreMusical.ROCK, 1000);
        // création d'un lieux
        Lieux lieu = new Lieux("Salle des fêtes", "1 rue de la mairie", 1000, 200, true);
        // création d'un palt
        Plat plat = new Plat("Poulet", 10);
        // création d'un repas
        Repas repas = new Repas(MomentRepas.SOIR);
        repas.addPlat(plat);
        // création d'un intervenant
        Contact intervenant = new Contact("Dupond", "pierre@gmail.com", "0612345678", "animateur", 1000);
        // Création d'une voiture
        Voiture voiture = new Voiture("Peugeot", 500);
        // ajout a mariage
        mariage.addGroupe(groupe);
        mariage.addLieux(lieu);
        mariage.addRepas(repas);
        mariage.addIntervenant(intervenant);
        mariage.setVoiture(voiture);
        // Vérification du prix
        assertEquals(4560, mariage.getPrix());
    }

    @Test
    void isDeleted() throws MariageException {
        // Création d'un mariage
        Mariage mariage = new Mariage("27951", LocalDate.of(2024,12,1), "mariage de Pierre et Sophie", "Princesse", 82, 24, 78);
        // Vérification de l'existence du mariage
        assertFalse(mariage.isDeleted());
    }

    @Test
    void setDeleted() throws MariageException {
        // Création d'un mariage
        Mariage mariage = new Mariage("27951", LocalDate.of(2024,12,1), "mariage de Pierre et Sophie", "Princesse", 82, 24, 78);
        // Suppression du mariage
        mariage.setDeleted(true);
        // Vérification de la suppression du mariage
        assertTrue(mariage.isDeleted());
    }

    @Test
    void setId() throws MariageException {
        // Création d'un mariage
        Mariage mariage = new Mariage("27951", LocalDate.of(2024,12,1), "mariage de Pierre et Sophie", "Princesse", 82, 24, 78);
        // Modification de l'identifiant
        mariage.setId("27952");
        // Vérification de l'identifiant
        assertEquals("27952", mariage.getId());
        // Vérification de la levée d'exception
        assertThrows(MariageException.class, () -> mariage.setId(""));
    }

    @Test
    void setDate() throws MariageException {
        // Création d'un mariage
        Mariage mariage = new Mariage("27951", LocalDate.of(2024,12,1), "mariage de Pierre et Sophie", "Princesse", 82, 24, 78);
        // Modification de la date
        mariage.setDate(LocalDate.of(2024,12,2));
        // Vérification de la date
        assertEquals(LocalDate.of(2024,12,2), mariage.getDate());
        // Vérification de la levée d'exception
        assertThrows(MariageException.class, () -> mariage.setDate(null));
    }

    @Test
    void setNom() throws MariageException {
        // Création d'un mariage
        Mariage mariage = new Mariage("27951", LocalDate.of(2024,12,1), "mariage de Pierre et Sophie", "Princesse", 82, 24, 78);
        // Modification du nom
        mariage.setNom("mariage de Pierre et Sophie");
        // Vérification du nom
        assertEquals("mariage de Pierre et Sophie", mariage.getNom());
        // Vérification de la levée d'exception
        assertThrows(MariageException.class, () -> mariage.setNom(""));
    }

    @Test
    void setTheme() throws MariageException {
        // Création d'un mariage
        Mariage mariage = new Mariage("27951", LocalDate.of(2024,12,1), "mariage de Pierre et Sophie", "Princesse", 82, 24, 78);
        // Modification du thème
        mariage.setTheme("Princesse");
        // Vérification du thème
        assertEquals("Princesse", mariage.getTheme());
        // Vérification de la levée d'exception
        assertThrows(MariageException.class, () -> mariage.setTheme(""));
    }

    @Test
    void setNombreAdultes() throws MariageException {
        // Création d'un mariage
        Mariage mariage = new Mariage("27951", LocalDate.of(2024,12,1), "mariage de Pierre et Sophie", "Princesse", 82, 24, 78);
        // Modification du nombre d'adultes
        mariage.setNombreAdultes(100);
        // Vérification du nombre d'adultes
        assertEquals(100, mariage.getNombreAdultes());
        // Vérification de la levée d'exception
        assertThrows(MariageException.class, () -> mariage.setNombreAdultes(-100));
    }

    @Test
    void setNombreEnfants() throws MariageException {
        // Création d'un mariage
        Mariage mariage = new Mariage("27951", LocalDate.of(2024,12,1), "mariage de Pierre et Sophie", "Princesse", 82, 24, 78);
        // Modification du nombre d'enfants
        mariage.setNombreEnfants(30);
        // Vérification du nombre d'enfants
        assertEquals(30, mariage.getNombreEnfants());
        // Vérification de la levée d'exception
        assertThrows(MariageException.class, () -> mariage.setNombreEnfants(-30));
    }

    @Test
    void setNombreInvitesVinHonneur() throws MariageException {
        // Création d'un mariage
        Mariage mariage = new Mariage("27951", LocalDate.of(2024,12,1), "mariage de Pierre et Sophie", "Princesse", 82, 24, 78);
        // Modification du nombre d'invités au vin d'honneur
        mariage.setNombreInvitesVinHonneur(50);
        // Vérification du nombre d'invités au vin d'honneur
        assertEquals(50, mariage.getNombreInvitesVinHonneur());
        // Vérification de la levée d'exception
        assertThrows(MariageException.class, () -> mariage.setNombreInvitesVinHonneur(-50));
    }

    @Test
    void setIntervenants() throws MariageException {
        // Création d'un mariage
        Mariage mariage = new Mariage("27951", LocalDate.of(2024,12,1), "mariage de Pierre et Sophie", "Princesse", 82, 24, 78);
        // Création d'un intervenant
        Contact intervenant = new Contact("Dupond", "dupond@gmail.com", "0612345678", "animateur", 1000);
        // Modification de la liste des intervenants
        ArrayList<Contact> intervenants = new ArrayList<>();
        intervenants.add(intervenant);
        mariage.setIntervenants(intervenants);
        // Vérification de la liste des intervenants
        assertEquals(intervenant, mariage.getIntervenants().get(0));
        // Vérification de la levée d'exception
        assertThrows(MariageException.class, () -> mariage.setIntervenants(null));
    }

    @Test
    void setGroupes() throws MariageException {
        // Création d'un mariage
        Mariage mariage = new Mariage("27951", LocalDate.of(2024,12,1), "mariage de Pierre et Sophie", "Princesse", 82, 24, 78);
        // Création d'un groupe de musique
        GroupeMusique groupe = new GroupeMusique(2,18, "Les Beatles", GenreMusical.ROCK, 1000);
        // Modification de la liste des groupes
        ArrayList<GroupeMusique> groupes = new ArrayList<>();
        groupes.add(groupe);
        mariage.setGroupes(groupes);
        // Vérification de la liste des groupes
        assertEquals(groupe, mariage.getGroupes().get(0));
        // Vérification de la levée d'exception
        assertThrows(MariageException.class, () -> mariage.setGroupes(null));
    }

    @Test
    void setRepas() throws MariageException {
        // Création d'un mariage
        Mariage mariage = new Mariage("27951", LocalDate.of(2024,12,1), "mariage de Pierre et Sophie", "Princesse", 82, 24, 78);
        // création d'un palt
        Plat plat = new Plat("Poulet", 10);
        // création d'un repas
        Repas repas = new Repas(MomentRepas.SOIR);
        repas.addPlat(plat);
        // Modification de la liste des repas
        ArrayList<Repas> repasList = new ArrayList<>();
        repasList.add(repas);
        mariage.setRepas(repasList);
        // Vérification de la liste des repas
        assertEquals(repas, mariage.getRepas().get(0));
        // Vérification de la levée d'exception
        assertThrows(MariageException.class, () -> mariage.setRepas(null));
    }

    @Test
    void setInvitesImportants() throws MariageException {
        // Création d'un mariage
        Mariage mariage = new Mariage("27951", LocalDate.of(2024,12,1), "mariage de Pierre et Sophie", "Princesse", 82, 24, 78);
        // Création d'un invité
        Personne invite = new Personne("Dupond", 56,"Pierre");
        // Modification de la liste des invités importants
        ArrayList<Personne> invites = new ArrayList<>();
        invites.add(invite);
        mariage.setInvitesImportants(invites);
        // Vérification de la liste des invités importants
        assertEquals(invite, mariage.getInvitesImportants().get(0));
        // Vérification de la levée d'exception
        assertThrows(MariageException.class, () -> mariage.setInvitesImportants(null));
    }

    @Test
    void setMarie() throws MariageException {
        // Création d'un mariage
        Mariage mariage = new Mariage("27951", LocalDate.of(2024,12,1), "mariage de Pierre et Sophie", "Princesse", 82, 24, 78);
        // Création d'un marié
        Marie marie = new Marie("Dupond", 24, "Pierre",250,126,785,"pierre.dupond38@gmail.com", "0612345678");
        // Modification de la liste des mariés
        ArrayList<Personne> maries = new ArrayList<>();
        maries.add(marie);
        mariage.setMarie(maries);
        // Vérification de la liste des mariés
        assertEquals(marie, mariage.getMarie1());
        // Vérification de la levée d'exception
        assertThrows(MariageException.class, () -> mariage.setMarie(null));
    }

    @Test
    void setVoiture() throws MariageException {
        // Création d'un mariage
        Mariage mariage = new Mariage("27951", LocalDate.of(2024,12,1), "mariage de Pierre et Sophie", "Princesse", 82, 24, 78);
        // Création d'une voiture
        Voiture voiture = new Voiture("Peugeot", 500);
        // Modification de la voiture
        mariage.setVoiture(voiture);
        // Vérification de la voiture
        assertEquals(voiture, mariage.getVoiture());
    }

    @Test
    void setLieux() throws MariageException {
        // Création d'un mariage
        Mariage mariage = new Mariage("27951", LocalDate.of(2024,12,1), "mariage de Pierre et Sophie", "Princesse", 82, 24, 78);
        // création d'un lieux
        Lieux lieu = new Lieux("Salle des fêtes", "1 rue de la mairie", 1000, 200, true);
        // Modification de la liste des lieux
        ArrayList<Lieux> lieux = new ArrayList<>();
        lieux.add(lieu);
        mariage.setLieux(lieux);
        // Vérification de la liste des lieux
        assertEquals(lieu, mariage.getLieux().get(0));
        // Vérification de la levée d'exception
        assertThrows(MariageException.class, () -> mariage.setLieux(null));
    }

    @Test
    void getIntervenants() throws MariageException {
        // Création d'un mariage
        Mariage mariage = new Mariage("27951", LocalDate.of(2024,12,1), "mariage de Pierre et Sophie", "Princesse", 82, 24, 78);
        // Création d'un intervenant
        Contact intervenant = new Contact("Dupond", "dupond@gmail.com", "0612345678", "animateur", 1000);
        // Ajout de l'intervenant
        mariage.addIntervenant(intervenant);
        // Vérification de la liste des intervenants
        assertEquals(intervenant, mariage.getIntervenants().get(0));
        // Vérification de la levée d'exception
        assertThrows(MariageException.class, () -> mariage.addIntervenant(null));
    }

    @Test
    void getGroupes() throws MariageException {
        // Création d'un mariage
        Mariage mariage = new Mariage("27951", LocalDate.of(2024,12,1), "mariage de Pierre et Sophie", "Princesse", 82, 24, 78);
        // Création d'un groupe de musique
        GroupeMusique groupe = new GroupeMusique(2,18, "Les Beatles", GenreMusical.ROCK, 1000);
        // Ajout du groupe
        mariage.addGroupe(groupe);
        // Vérification de la liste des groupes
        assertEquals(groupe, mariage.getGroupes().get(0));
        // Vérification de la levée d'exception
        assertThrows(MariageException.class, () -> mariage.addGroupe(null));
    }

    @Test
    void getRepas() throws MariageException {
        // Création d'un mariage
        Mariage mariage = new Mariage("27951", LocalDate.of(2024,12,1), "mariage de Pierre et Sophie", "Princesse", 82, 24, 78);
        // création d'un plat
        Plat plat = new Plat("Poulet", 10);
        // création d'un repas
        Repas repas = new Repas(MomentRepas.SOIR);
        repas.addPlat(plat);
        // Ajout du repas
        mariage.addRepas(repas);
        // Vérification de la liste des repas
        assertEquals(repas, mariage.getRepas().get(0));
        // Vérification de la levée d'exception
        assertThrows(MariageException.class, () -> mariage.addRepas(null));

    }

    @Test
    void getInvitesImportants() throws MariageException {
        // Création d'un mariage
        Mariage mariage = new Mariage("27951", LocalDate.of(2024,12,1), "mariage de Pierre et Sophie", "Princesse", 82, 24, 78);
        // Création d'un invité
        Personne invite = new Personne("Dupond", 56,"Pierre");
        // Ajout de l'invité
        mariage.addInvite(invite);
        // Vérification de la liste des invités importants
        assertEquals(invite, mariage.getInvitesImportants().get(0));
        // Vérification de la levée d'exception
        assertThrows(MariageException.class, () -> mariage.addInvite(null));
    }

    @Test
    void getMarie() throws MariageException {
        // Création d'un mariage
        Mariage mariage = new Mariage("27951", LocalDate.of(2024,12,1), "mariage de Pierre et Sophie", "Princesse", 82, 24, 78);
        // Création d'un marié
        Marie marie = new Marie("Dupond", 24, "Pierre",250,126,785,"pierre.dupond@gmail.com", "0612345678");
        // Ajout du marié
        mariage.addMarie(marie);
        // Vérification de la liste des mariés
        assertEquals(marie, mariage.getMarie1());
        // Vérification de la levée d'exception
        assertThrows(MariageException.class, () -> mariage.addMarie(null));
    }

    @Test
    void getVoiture() throws MariageException {
        // Création d'un mariage
        Mariage mariage = new Mariage("27951", LocalDate.of(2024,12,1), "mariage de Pierre et Sophie", "Princesse", 82, 24, 78);
        // Création d'une voiture
        Voiture voiture = new Voiture("Peugeot", 500);
        // Ajout de la voiture
        mariage.setVoiture(voiture);
        // Vérification de la voiture
        assertEquals(voiture, mariage.getVoiture());
        // Vérification de la levée d'exception
        assertThrows(MariageException.class, () -> mariage.setVoiture(null));
    }

    @Test
    void getLieux() throws MariageException {
        // Création d'un mariage
        Mariage mariage = new Mariage("27951", LocalDate.of(2024,12,1), "mariage de Pierre et Sophie", "Princesse", 82, 24, 78);
        // création d'un lieux
        Lieux lieu = new Lieux("Salle des fêtes", "1 rue de la mairie", 1000, 200, true);
        // Ajout du lieu
        mariage.addLieux(lieu);
        // Vérification de la liste des lieux
        assertEquals(lieu, mariage.getLieux().get(0));
        // Vérification de la levée d'exception
        assertThrows(MariageException.class, () -> mariage.addLieux(null));
    }

    @Test
    void addGroupe() throws MariageException {
        // Création d'un mariage
        Mariage mariage = new Mariage("27951", LocalDate.of(2024,12,1), "mariage de Pierre et Sophie", "Princesse", 82, 24, 78);
        // Création d'un groupe de musique
        GroupeMusique groupe = new GroupeMusique(2,18, "Les Beatles", GenreMusical.ROCK, 1000);
        // Ajout du groupe
        mariage.addGroupe(groupe);
        // Vérification de la liste des groupes
        assertEquals(groupe, mariage.getGroupes().get(0));
        // Vérification de la levée d'exception
        assertThrows(MariageException.class, () -> mariage.addGroupe(null));
    }

    @Test
    void addLieux() throws MariageException {
        // Création d'un mariage
        Mariage mariage = new Mariage("27951", LocalDate.of(2024,12,1), "mariage de Pierre et Sophie", "Princesse", 82, 24, 78);
        // création d'un lieux
        Lieux lieu = new Lieux("Salle des fêtes", "1 rue de la mairie", 1000, 200, true);
        // Ajout du lieu
        mariage.addLieux(lieu);
        // Vérification de la liste des lieux
        assertEquals(lieu, mariage.getLieux().get(0));
        // Vérification de la levée d'exception
        assertThrows(MariageException.class, () -> mariage.addLieux(null));
    }

    @Test
    void addRepas() throws MariageException {
        // Création d'un mariage
        Mariage mariage = new Mariage("27951", LocalDate.of(2024,12,1), "mariage de Pierre et Sophie", "Princesse", 82, 24, 78);
        // création d'un plat
        Plat plat = new Plat("Poulet", 10);
        // création d'un repas
        Repas repas = new Repas(MomentRepas.SOIR);
        repas.addPlat(plat);
        // Ajout du repas
        mariage.addRepas(repas);
        // Vérification de la liste des repas
        assertEquals(repas, mariage.getRepas().get(0));
        // Vérification de la levée d'exception
        assertThrows(MariageException.class, () -> mariage.addRepas(null));
    }

    @Test
    void addIntervenant() throws MariageException {
        // Création d'un mariage
        Mariage mariage = new Mariage("27951", LocalDate.of(2024,12,1), "mariage de Pierre et Sophie", "Princesse", 82, 24, 78);
        // Création d'un intervenant
        Contact intervenant = new Contact("Dupond", "dupond@gmail.com", "0612345678", "animateur", 1000);
        // Ajout de l'intervenant
        mariage.addIntervenant(intervenant);
        // Vérification de la liste des intervenants
        assertEquals(intervenant, mariage.getIntervenants().get(0));
        // Vérification de la levée d'exception
        assertThrows(MariageException.class, () -> mariage.addIntervenant(null));
    }

    @Test
    void addInvite() throws MariageException {
        // Création d'un mariage
        Mariage mariage = new Mariage("27951", LocalDate.of(2024,12,1), "mariage de Pierre et Sophie", "Princesse", 82, 24, 78);
        Personne invite = new Personne("Dupond", 56,"Pierre");
        // Ajout de l'invité
        mariage.addInvite(invite);
        // Vérification de la liste des invités importants
        assertEquals(invite, mariage.getInvitesImportants().get(0));
        // Vérification de la levée d'exception
        assertThrows(MariageException.class, () -> mariage.addInvite(null));
    }

    @Test
    void addMarie() throws MariageException {
        // Création d'un mariage
        Mariage mariage = new Mariage("27951", LocalDate.of(2024,12,1), "mariage de Pierre et Sophie", "Princesse", 82, 24, 78);
        // Création d'un marié
        Marie marie = new Marie("Dupond", 24, "Pierre",250,126,785,"pierre.dupond@gmail.com", "0612345678");
        // Ajout du marié
        mariage.addMarie(marie);
        // Vérification de la liste des mariés
        assertEquals(marie, mariage.getMarie1());
        // Vérification de la levée d'exception
        assertThrows(MariageException.class, () -> mariage.addMarie(null));
    }
}