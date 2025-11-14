package controleur;

import villagegaulois.Village;

public class ControlAcheterProduit {
	private Village village;
	private ControlTrouverEtalVendeur controlTrouverEtalVendeur;
	private ControlVerifierIdentite controlVerifierIdentite;

	public ControlAcheterProduit(ControlVerifierIdentite controlVerifierIdentite,
			ControlTrouverEtalVendeur controlTrouverEtalVendeur,
			Village village) {
		this.village = village;
		this.controlVerifierIdentite = controlVerifierIdentite;
		this.controlTrouverEtalVendeur = controlTrouverEtalVendeur;
	}
	
	public boolean isHabitant(String nom) {
		return controlVerifierIdentite.verifierIdentite(nom);
	}
	
	public String[] produitVendu(){
		String[] etatMarche = village.donnerEtatMarche();
		String[] produitVendu = new String[etatMarche.length/3];
		int j = 0;
		for (int i = 2; i < etatMarche.length; i+=3) {
			produitVendu[j] = etatMarche[i];
			j++;
		}
		return produitVendu;
	}
	
	public String[] vendeurProduit(String produit) {
		String[] etatMarche = village.donnerEtatMarche();
		String[] vendeurProduit = new String[village.rechercherVendeursProduit(produit).length];
		int j = 2;
		for (int i = 0; i < vendeurProduit.length; i++) {
			vendeurProduit[i] = etatMarche[j-2];
			j+=3;
		}
		return vendeurProduit;
	}
	
	public int quantiteProduitEtal(String nomVendeur) {
		return controlTrouverEtalVendeur.trouverEtalVendeur(nomVendeur).getQuantite();
	}
	
	public int acheterProduit(String nomVendeur, int quantite) {
		return controlTrouverEtalVendeur.trouverEtalVendeur(nomVendeur).acheterProduit(quantite);
	}
}
