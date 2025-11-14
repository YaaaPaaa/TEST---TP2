package controleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import personnages.Gaulois;
import villagegaulois.Village;

class ControlAfficherMarcheTest {
	private Village village;
	
	@BeforeEach
	void initialiserSituation() {
		village = new Village("le village des Irréductibles",10,2);
		Chef abraracourcix = new Chef("Abraracourcix", 10, village);
		village.setChef(abraracourcix);
	}

	@Test
	void testControlAfficherMarche() {
		ControlAfficherMarche controlAfficherMarche = new ControlAfficherMarche(village);
		assertNotNull(controlAfficherMarche);
	}

	@Test
	void testDonnerInfosMarche() {
		ControlAfficherMarche controlAfficherMarche = new ControlAfficherMarche(village);
		Gaulois gaulois = new Gaulois("Gaulois 1", 5);
		village.ajouterHabitant(gaulois);
		village.installerVendeur(gaulois, "Produit 1", 5);
		assertEquals(controlAfficherMarche.donnerInfosMarche()[0],"Gaulois 1");
		assertEquals(controlAfficherMarche.donnerInfosMarche()[1],"5");
		assertEquals(controlAfficherMarche.donnerInfosMarche()[2],"Produit 1");
	}

}
