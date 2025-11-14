package controleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import villagegaulois.Village;

class ControlAfficherVillageTest {
	private Village village;
	
	@BeforeEach
	public void initialiserSituation() {
		System.out.println("Initialisation...");
		village = new Village("le village des Irréductibles",10,5);
		Chef abraracourcix = new Chef("Abraracourcix", 10, village);
		village.setChef(abraracourcix);
	}

	@Test
	void testControlAfficherVillage() {
		ControlAfficherVillage controlAfficherVillage = new ControlAfficherVillage(village);
		assertNotNull(controlAfficherVillage, "Constructeur ne renvoie pas null");
	}

	@Test
	void testDonnerNomsVillageois() {
		ControlAfficherVillage controlAfficherVillage = new ControlAfficherVillage(village);
		
		ControlEmmenager controlEmmenager = new ControlEmmenager(village);
		controlEmmenager.ajouterGaulois("Gaulois 1",10);
		controlEmmenager.ajouterGaulois("Gaulois 2",10);
		controlEmmenager.ajouterDruide("Druide 1",10, 5, 8);
		
		String[] donnees = controlAfficherVillage.donnerNomsVillageois();
		
		assertEquals("Abraracourcix", donnees[0]);
		assertEquals("Gaulois 1", donnees[1]);
		assertEquals("Gaulois 2", donnees[2]);
		assertEquals("le druide Druide 1", donnees[3]);
		assertNotEquals("Personne", donnees[3]);
	}

	@Test
	void testDonnerNomVillage() {
		ControlAfficherVillage controlAfficherVillage = new ControlAfficherVillage(village);
		
		assertEquals("le village des Irréductibles", controlAfficherVillage.donnerNomVillage());;
	}

	@Test
	void testDonnerNbEtals() {
		ControlAfficherVillage controlAfficherVillage = new ControlAfficherVillage(village);
		
		assertEquals(5, controlAfficherVillage.donnerNbEtals());;
	}

}
