package controleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import personnages.Gaulois;
import villagegaulois.Village;

class ControlAcheterProduitTest {
	private Village village;
	private ControlVerifierIdentite controlVerifierIdentite;
	private ControlTrouverEtalVendeur controlTrouverEtalVendeur;
	
	@BeforeEach
	void initialiserSituation() {
		village = new Village("le village des Irréductibles",10,2);
		Chef abraracourcix = new Chef("Abraracourcix", 10, village);
		village.setChef(abraracourcix);
		
		controlVerifierIdentite = new ControlVerifierIdentite(village);
		controlTrouverEtalVendeur = new ControlTrouverEtalVendeur(village);
	}

	@Test
	void testControlAcheterProduit() {
		ControlAcheterProduit controlAcheterProduit = new ControlAcheterProduit(controlVerifierIdentite, controlTrouverEtalVendeur, village);
		assertNotNull(controlAcheterProduit);
	}

	@Test
	void testIsHabitant() {
		ControlAcheterProduit controlAcheterProduit = new ControlAcheterProduit(controlVerifierIdentite, controlTrouverEtalVendeur, village);
		
		ControlEmmenager controlEmmenager = new ControlEmmenager(village);
		controlEmmenager.ajouterGaulois("Gaulois 1",10);
		
		assertTrue(controlAcheterProduit.isHabitant("Gaulois 1"));
		assertFalse(controlAcheterProduit.isHabitant("Existe pas"));
		
		assertTrue(controlAcheterProduit.isHabitant("Abraracourcix"));
	}

	@Test
	void testProduitVendu() {
		ControlAcheterProduit controlAcheterProduit = new ControlAcheterProduit(controlVerifierIdentite, controlTrouverEtalVendeur, village);
		Gaulois gaulois = new Gaulois("Gaulois 1", 5);
		village.ajouterHabitant(gaulois);
		village.installerVendeur(gaulois, "Produit 1", 5);
		
		Gaulois gaulois2 = new Gaulois("Gaulois 2", 5);
		village.ajouterHabitant(gaulois2);
		village.installerVendeur(gaulois2, "Produit 2", 5);
		
		assertEquals(controlAcheterProduit.produitVendu()[0], "Produit 1");
		assertEquals(controlAcheterProduit.produitVendu()[1], "Produit 2");
		assertNotEquals(controlAcheterProduit.produitVendu()[1], "Existe pas");
	}

	@Test
	void testVendeurProduit() {
		ControlAcheterProduit controlAcheterProduit = new ControlAcheterProduit(controlVerifierIdentite, controlTrouverEtalVendeur, village);
		Gaulois gaulois = new Gaulois("Gaulois 1", 5);
		village.ajouterHabitant(gaulois);
		village.installerVendeur(gaulois, "Produit 1", 5);
		
		Gaulois gaulois2 = new Gaulois("Gaulois 2", 5);
		village.ajouterHabitant(gaulois2);
		village.installerVendeur(gaulois2, "Produit 1", 5);
		
		Gaulois gaulois3 = new Gaulois("Gaulois 3", 5);
		village.ajouterHabitant(gaulois3);
		village.installerVendeur(gaulois3, "Produit 3", 5);
		
		assertEquals(controlAcheterProduit.vendeurProduit("Produit 1")[0], "Gaulois 1");
		assertEquals(controlAcheterProduit.vendeurProduit("Produit 1")[1], "Gaulois 2");
		
		assertNotEquals(controlAcheterProduit.vendeurProduit("Produit 1")[0], "Gaulois 3");		
		assertNotEquals(controlAcheterProduit.vendeurProduit("Produit 1")[1], "Existe pas");
	}

	@Test
	void testQuantiteProduitEtal() {
		ControlAcheterProduit controlAcheterProduit = new ControlAcheterProduit(controlVerifierIdentite, controlTrouverEtalVendeur, village);
		Gaulois gaulois = new Gaulois("Gaulois 1", 5);
		village.ajouterHabitant(gaulois);
		village.installerVendeur(gaulois, "Produit 1", 10);
		
		assertEquals(controlAcheterProduit.quantiteProduitEtal("Gaulois 1"), 10);
	}

	@Test
	void testAcheterProduit() {
		ControlAcheterProduit controlAcheterProduit = new ControlAcheterProduit(controlVerifierIdentite, controlTrouverEtalVendeur, village);
		Gaulois gaulois = new Gaulois("Gaulois 1", 5);
		village.ajouterHabitant(gaulois);
		village.installerVendeur(gaulois, "Produit 1", 10);
		
		assertEquals(controlAcheterProduit.quantiteProduitEtal("Gaulois 1"), 10);
		assertEquals(controlAcheterProduit.acheterProduit("Gaulois 1", 5),5);
		assertEquals(controlAcheterProduit.quantiteProduitEtal("Gaulois 1"), 5);
	}

}
