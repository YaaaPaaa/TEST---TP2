package frontiere;

import controleur.ControlPrendreEtal;
import villagegaulois.Village;

public class BoundaryPrendreEtal {
	private ControlPrendreEtal controlPrendreEtal;

	public BoundaryPrendreEtal(ControlPrendreEtal controlChercherEtal) {
		this.controlPrendreEtal = controlChercherEtal;
	}

	public void prendreEtal(String nomVendeur) {
		boolean nomVendeurConnu = controlPrendreEtal.verifierIdentite(nomVendeur);
		if (!nomVendeurConnu) {
			System.out.println("Je suis désolée " + nomVendeur + " mais il "
					+ "faut être un habitant de notre village pour commencer ici.");
		} else {
			System.out.println("Bonjour " + nomVendeur + ", je vais regarder si je peux trouver une étal.");
			boolean etalDisonible = controlPrendreEtal.resteEtals();
			if (!etalDisonible) {
				System.out.println("Désolée " + nomVendeur + " je n'ai plus d'étal qui ne soit pas déjà occupé.");
			} else {
				installerVendeur(nomVendeur);
			}
		}
	}

	private void installerVendeur(String nomVendeur) {
		System.out.println("C'est parfait, il me reste un étal pour vous !");
		
		StringBuilder questionProduit = new StringBuilder();
		System.out.println("Il me faudrait quelques renseignements :");
		System.out.println("Quel produit souhaitez-vous vendre ?");
		String produit;
		produit = Clavier.entrerChaine(questionProduit.toString());
		System.out.println("Combien souhaitez-vous en vendre ?");
		int nbProduit;
		nbProduit = Clavier.entrerEntier(questionProduit.toString());
		
		int numeroEtal;
		numeroEtal = controlPrendreEtal.prendreEtal(nomVendeur, produit, nbProduit);
		if (numeroEtal != -1) {
			System.out.println("Le vendeur " + nomVendeur + " s'est installé à l'étal n°" + numeroEtal);
		}
	}
}
