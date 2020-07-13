package metier;

import java.util.List;

public class TestMetier {

		public static void main(String[] args) {
			ICatalogueMetier metier = new CatalogueMetierImpl();
			/*metier.addProduit(new Produit("REF05", "AAA", 890, 3));
			metier.addProduit(new Produit("REF06", "BBB", 990, 5));
			metier.addProduit(new Produit("REF07", "CCC", 390, 13));*/
			List<Produit> produitsList= metier.listProduits();
			for(Produit produit:produitsList)
			{
				System.out.println(produit.getDesignation());
			}
			System.out.println("------------------------");
			List<Produit> produitsList2= metier.produitParId("HP");
			for(Produit produit:produitsList2)
			{
				System.out.println(produit.getDesignation());
			}
			System.out.println("--------------------");
			try {
				Produit produit = metier.getProduit("PR02");
				System.out.println(produit.getDesignation());
				produit.setPrix(700);
				metier.updateProduit(produit);
			}catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
			System.out.println("---------------------");
			//metier.deleteProduit("REF05");
		}

}
