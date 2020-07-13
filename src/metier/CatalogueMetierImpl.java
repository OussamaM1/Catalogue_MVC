package metier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//Mapping objet relationel travail d'un framework hibernate ORM
public class CatalogueMetierImpl implements ICatalogueMetier{

		@Override
		public boolean addProduit(Produit produit) {
			Connection conn = SingletonConnection.getConnection();
			try {
				PreparedStatement ps = conn.prepareStatement("insert into produits(Ref_Prod,Designation,Prix,Quantite) values(?,?,?,?)");
				ps.setString(1, produit.getReference());
				ps.setString(2, produit.getDesignation());
				ps.setDouble(3, produit.getPrix());
				ps.setInt(4, produit.getQuantite());
				
				ps.executeUpdate();
				ps.close();
				return true;
				
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
	
		@Override
		public List<Produit> listProduits() {
			List<Produit> produitsList = new ArrayList<Produit>();
			Connection conn = SingletonConnection.getConnection();
			try {
				PreparedStatement ps = conn.prepareStatement("select * from produits");
				ResultSet rs = ps.executeQuery();
				while(rs.next())
				{
					Produit produit = new Produit();
					produit.setReference(rs.getString("Ref_Prod"));
					produit.setDesignation(rs.getString("Designation"));
					produit.setPrix(rs.getDouble("Prix"));
					produit.setQuantite(rs.getInt("Quantite"));
					produitsList.add(produit);
				}
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return produitsList;
		}
	
		@Override
		public List<Produit> produitParId(String id) {
			List<Produit> produitsList = new ArrayList<Produit>();
			Connection conn = SingletonConnection.getConnection();
			try {
				PreparedStatement ps = conn.prepareStatement("select * from produits where Designation like ? ");
				ps.setString(1, "%"+id+"%");
				ResultSet rs = ps.executeQuery();
				while(rs.next())
				{
					Produit produit = new Produit();
					produit.setReference(rs.getString("Ref_Prod"));
					produit.setDesignation(rs.getString("Designation"));
					produit.setPrix(rs.getDouble("Prix"));
					produit.setQuantite(rs.getInt("Quantite"));
					produitsList.add(produit);
				}
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return produitsList;
		}
	
		@Override
		public Produit getProduit(String ref) {
			Produit produit = null;
			Connection conn = SingletonConnection.getConnection();
			try {
				PreparedStatement ps = conn.prepareStatement("select * from produits where Ref_Prod=? ");
				ps.setString(1, ref);
				ResultSet rs = ps.executeQuery();
				if(rs.next())
				{
					produit = new Produit();
					produit.setReference(rs.getString("Ref_Prod"));
					produit.setDesignation(rs.getString("Designation"));
					produit.setPrix(rs.getDouble("Prix"));
					produit.setQuantite(rs.getInt("Quantite"));
				}
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(produit == null) throw new  RuntimeException("Produit "+ref+" Introuvable");
			return produit;
		}
	
		@Override
		public void updateProduit(Produit produit) {
			Connection conn = SingletonConnection.getConnection();
			try {
				PreparedStatement ps = conn.prepareStatement("update  produits set Designation=?, Prix=?,Quantite=? where Ref_Prod =?");
				ps.setString(1, produit.getDesignation());
				ps.setDouble(2, produit.getPrix());
				ps.setInt(3, produit.getQuantite());
				ps.setString(4, produit.getReference());
				
				ps.executeUpdate();
				ps.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
		@Override
		public boolean deleteProduit(String ref) {
			Connection conn = SingletonConnection.getConnection();
			try {
				PreparedStatement ps = conn.prepareStatement("delete from produits  where Ref_Prod = ?");
				ps.setString(1, ref);
				
				ps.executeUpdate();
				ps.close();
				return true;
				
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
}
	
