package frontiere;

import controleur.ControlAcheterProduit;

public class BoundaryAcheterProduit {
	private ControlAcheterProduit controlAcheterProduit;

	public BoundaryAcheterProduit(ControlAcheterProduit controlAcheterProduit) {
		this.controlAcheterProduit = controlAcheterProduit;
	}
	
	public void acheterProduit(String nomAcheteur) {
		if (!(controlAcheterProduit.isHabitant(nomAcheteur))) { //Si l'acheteur n'appartient pas au village 
			System.out.println("Je suis désolé " + nomAcheteur + " mais il faut être un habitant de notre village pour commercer ici.");
		} else {
			StringBuilder questionAcheterProduit = new StringBuilder();
			questionAcheterProduit.append("Quel produit voulez-vous acheter ?\n");
			String produitDemande =  Clavier.entrerChaine(questionAcheterProduit.toString());
			String[] produitVendu = controlAcheterProduit.produitVendu();
			boolean isProduitAVendre = false;
			for (int i = 0; i < produitVendu.length && !(isProduitAVendre); i++) {
				if (produitDemande.equals(produitVendu[i])) {
					isProduitAVendre = true;
				}
			}
			if (!(isProduitAVendre)) {		// Si le produit demandé n'est pas à vendre
				System.out.println("Désolé, personne ne vend ce produit au marché.");
			} else {		// Si le produit demandé est à vendre
				int numCommercant = 0;
				String[] nomVendeurProduit = controlAcheterProduit.vendeurProduit(produitDemande);
				while(numCommercant<1 || numCommercant>(nomVendeurProduit.length)) {
					StringBuilder questionChoisirCommercant = new StringBuilder();
					questionChoisirCommercant.append("Chez quel commerçant voulez-vous acheter des ").append(produitDemande).append(" ?\n");
					
					for (int i = 0; i < nomVendeurProduit.length; i++) {
						questionChoisirCommercant.append(i+1).append("-").append(nomVendeurProduit[i]).append("\n");
					}
					numCommercant =  Clavier.entrerEntier(questionChoisirCommercant.toString());
					//Rajouter phrase si erreurs.
				}
				
				System.out.println(nomAcheteur + " se déplace jusqu'à l'étal du vendeur " 
						+ nomVendeurProduit[numCommercant-1] + "\n"
						+ "Bonjour " + nomAcheteur);
				
				StringBuilder questionQuantiteProduit = new StringBuilder();
				questionQuantiteProduit.append("Combien de " + produitDemande + " voulez-vous acheter ?");
				int quantiteProduitDemande =  Clavier.entrerEntier(questionQuantiteProduit.toString());
				int quantiteProduit = controlAcheterProduit.quantiteProduitEtal(nomVendeurProduit[numCommercant-1]);
				
				if (quantiteProduit == 0) {		// Si le vendeur n'a plus le produit en stock
					System.out.println(nomAcheteur + " veut acheter " + quantiteProduitDemande + " "
							+ produitDemande + ", malheureusement il n’y en a plus !");
				} else if (quantiteProduit < quantiteProduitDemande) {		// Si le vendeur n'a pas assez de stock
					System.out.println(nomAcheteur + " veut acheter " + quantiteProduitDemande + " "
							+ produitDemande + ", malheureusement " + nomVendeurProduit[numCommercant-1]
							+ " n'en a plus que " + quantiteProduit + ". " + nomAcheteur
							+ " achète tout le stock de " + nomVendeurProduit[numCommercant-1] + ".");
					controlAcheterProduit.acheterProduit(nomVendeurProduit[numCommercant-1], quantiteProduit);
				} else { 		// Si le vendeur peut vendre
					System.out.println(nomAcheteur + " achète " + quantiteProduitDemande 
							+ " " + produitDemande + " à " + nomVendeurProduit[numCommercant-1] + ".");
					controlAcheterProduit.acheterProduit(nomVendeurProduit[numCommercant-1], quantiteProduitDemande);
				}
			}
		}
	}
}
