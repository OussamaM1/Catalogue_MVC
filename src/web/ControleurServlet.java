package web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import metier.CatalogueMetierImpl;
import metier.ICatalogueMetier;
import metier.Produit;

public class ControleurServlet extends HttpServlet {
		private ICatalogueMetier metier;
		
		
		@Override
		public void init() throws ServletException {
			metier = new CatalogueMetierImpl();
			
		}
		
		@Override
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
				doPost(request, response);
				
		}
		
		@Override
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				String action = request.getParameter("action");
				ProduitModel model = new ProduitModel();
				model.setProduits(metier.listProduits());
				if(action!=null)
				{
					if(action.equals("rechercher"))
					{
						model.setMotCle(request.getParameter("motCle"));
						List<Produit> produits = metier.produitParId(model.getMotCle());
						model.setProduits(produits);
					}
					else if(action.equals("delete"))
					{
						String reference = request.getParameter("ref");
						if(metier.deleteProduit(reference))
							model.setSucess("Le produit a été supprimé!");
						model.setProduits(metier.listProduits());
					}
					else if(action.equals("save") && request.getParameter("saveORediter").equals("save"))
					{
						try {
						model.getProduit().setReference(request.getParameter("reference"));
						model.getProduit().setDesignation(request.getParameter("designation"));
						model.getProduit().setPrix(Double.parseDouble(request.getParameter("prix")));
						model.getProduit().setQuantite(Integer.parseInt(request.getParameter("quantite")));
						if(metier.addProduit(model.getProduit()))
							model.setSucess("Le produit a été ajouté !");
						model.setProduits(metier.listProduits());
						doGet(request, response);
						}
						catch(Exception e)
						{
							model.setError("Enregistrement Invalide !");
							
						}
					}
					else if(action.equals("edit"))
					{
						
						model.setProduit(metier.getProduit(request.getParameter("ref")));
						model.setSaveORediter("edit");
					}
					else if(action.equals("save") && request.getParameter("saveORediter").equals("edit"))
					{
						
						try {
						Produit updatedProduit = new Produit();
						updatedProduit.setReference(request.getParameter("reference"));
						updatedProduit.setDesignation(request.getParameter("designation"));
						updatedProduit.setPrix(Double.parseDouble(request.getParameter("prix")));
						updatedProduit.setQuantite((Integer.parseInt(request.getParameter("quantite"))));
						if(metier.updateProduit(updatedProduit))
							model.setSucess("Le produit a été modifié!");
						model.setProduits(metier.listProduits());
						}
						catch(Exception e)
						{
							model.setError("Enregistrement Invalide !");
						}
					}
				}
				request.setAttribute("model", model);
				request.getRequestDispatcher("VueProduits.jsp").forward(request, response);
				
			
		}
		

}
