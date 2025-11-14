package controleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import villagegaulois.Village;

class ControlPrendreEtalTest {
	private ControlVerifierIdentite controlVerifierIdentite;
	private Village village;
	
	
	@BeforeEach
	public void initialiserSituation() {
		System.out.println("Initialisation...");
		village = new Village("le village des Irréductibles",10,2);
		Chef abraracourcix = new Chef("Abraracourcix", 10, village);
		village.setChef(abraracourcix);
	}

	@Test
	void testControlPrendreEtal() {
		ControlPrendreEtal controlPrendreEtal = new ControlPrendreEtal(controlVerifierIdentite, village);
		assertNotNull(controlPrendreEtal, "Constructeur ne renvoie pas null");
	}

	@Test
	void testResteEtals() {
		ControlEmmenager controlEmmenager = new ControlEmmenager(village);
		
		ControlPrendreEtal controlPrendreEtal = new ControlPrendreEtal(controlVerifierIdentite, village);
		assertTrue(controlPrendreEtal.resteEtals());
		
		controlEmmenager.ajouterGaulois("Gaulois 1",10);
		controlPrendreEtal.prendreEtal("Gaulois 1", "Produit 1", 5);
		assertTrue(controlPrendreEtal.resteEtals());
		
		controlEmmenager.ajouterGaulois("Gaulois 2",10);
		controlPrendreEtal.prendreEtal("Gaulois 2", "Produit 2", 5);
		assertFalse(controlPrendreEtal.resteEtals()); 
		// Après l'installation de 2 Gaulois dans les Etals, il ne reste plus d'étals (nbEtal = 2)
	}

	@Test
	void testPrendreEtal() {
		ControlEmmenager controlEmmenager = new ControlEmmenager(village);
		
		ControlPrendreEtal controlPrendreEtal = new ControlPrendreEtal(controlVerifierIdentite, village);
		controlEmmenager.ajouterGaulois("Gaulois 1",10);
		assertEquals(1, controlPrendreEtal.prendreEtal("Gaulois 1", "Produit 1", 5));
		
		controlEmmenager.ajouterGaulois("Gaulois 2",10);
		assertEquals(2, controlPrendreEtal.prendreEtal("Gaulois 2", "Produit 2", 5));
		
		controlEmmenager.ajouterGaulois("Gaulois 3",10);
		assertEquals(0, controlPrendreEtal.prendreEtal("Gaulois 3", "Produit 2", 5)); 
	}

	@Test
	void testVerifierIdentite() {
		ControlEmmenager controlEmmenager = new ControlEmmenager(village);
		controlEmmenager.ajouterGaulois("Bonemine",10);
		
		ControlVerifierIdentite controlVerifierIdentite = new ControlVerifierIdentite(village);
		assertTrue(controlVerifierIdentite.verifierIdentite("Bonemine"));
		assertFalse(controlVerifierIdentite.verifierIdentite("Existe pas"));
	}

}
