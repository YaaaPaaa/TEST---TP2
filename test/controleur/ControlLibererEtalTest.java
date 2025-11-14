package controleur;

import static org.junit.jupiter.api.Assertions.*;

import javax.management.monitor.GaugeMonitor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import personnages.Gaulois;
import villagegaulois.Village;

class ControlLibererEtalTest {
	private ControlTrouverEtalVendeur controlTrouverEtalVendeur;
	private ControlLibererEtal controlLibererEtal;
	private Village village;
	
	@BeforeEach
	void initialiserSituation(){
		village = new Village("le village des Irréductibles",10,2);
		Chef abraracourcix = new Chef("Abraracourcix", 10, village);
		village.setChef(abraracourcix);
		
		controlTrouverEtalVendeur = new ControlTrouverEtalVendeur(village);
		controlLibererEtal = new ControlLibererEtal(controlTrouverEtalVendeur);
	}
	
	@Test
	void testControlLibererEtal() {
		ControlLibererEtal controlLibererEtal = new ControlLibererEtal(controlTrouverEtalVendeur);
		assertNotNull(controlLibererEtal);
	}

	@Test
	void testIsVendeur() {
		ControlLibererEtal controlLibererEtal = new ControlLibererEtal(controlTrouverEtalVendeur);
		Gaulois gaulois = new Gaulois("Gaulois 1", 5);
		village.ajouterHabitant(gaulois);
		village.installerVendeur(gaulois, "Produit 1", 5);
		
		assertTrue(controlLibererEtal.isVendeur("Gaulois 1"));
		assertFalse(controlLibererEtal.isVendeur("Existe pas"));
	}

	@Test
	void testLibererEtal() {
		assertNull(controlLibererEtal.libererEtal("Existe pas"));
		
		ControlLibererEtal controlLibererEtal = new ControlLibererEtal(controlTrouverEtalVendeur);
		Gaulois gaulois = new Gaulois("Gaulois 1", 5);
		village.ajouterHabitant(gaulois);
		village.installerVendeur(gaulois, "Produit 1", 5);
		
		assertNotNull(controlLibererEtal.libererEtal("Gaulois 1"));
	}

}
