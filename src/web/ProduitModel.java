package web;

import java.util.ArrayList;
import java.util.List;
import metier.Produit;
public class ProduitModel {
		private String motCle;
		private Produit produit = new Produit();
		private List<Produit> produits = new ArrayList<Produit>();
		private String error;
		private String sucess;
		
		//Getters and Setters
		public String getMotCle() {
			return motCle;
		}
		public void setMotCle(String motCle) {
			this.motCle = motCle;
		}
		public List<Produit> getProduits() {
			return produits;
		}
		public void setProduits(List<Produit> produits) {
			this.produits = produits;
		}
		public Produit getProduit() {
			return produit;
		}
		public void setProduit(Produit produit) {
			this.produit = produit;
		}
		public String getError() {
			return error;
		}
		public void setError(String error) {
			this.error = error;
		}
		public String getSucess() {
			return sucess;
		}
		public void setSucess(String sucess) {
			this.sucess = sucess;
		}
		
		
		
}
