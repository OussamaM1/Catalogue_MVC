package metier;

import java.util.List;

public interface ICatalogueMetier {
		public boolean addProduit(Produit produit);
		public List<Produit> listProduits();
		public List<Produit> produitParId(String id);
		public Produit getProduit(String ref);
		public void updateProduit(Produit produit);
		public boolean deleteProduit(String ref);
		
}
