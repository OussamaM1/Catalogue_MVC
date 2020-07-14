<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="web.ProduitModel"%>
<%
ProduitModel model=new ProduitModel();
if(request.getAttribute("model") != null){
	 model=(ProduitModel)request.getAttribute("model");
}
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Gestion de produits</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
	<script type="text/javascript">
			function confirmer(url)
			{
				var rep=confirm("êtes-vous sûr de vouloir supprimer ce produit ?");
				if(rep == true)
					{
						document.location=url;	
					}
			}
	</script>
</head>
<body>		
		
			<form action="produit" method="post" class="form-inline col-sm mb-4 mt-4">
					<div class="form-group row mb-2" >
						<label for="motCle"  class="form-label mx-4 col-form-label-lg">Rehercher un produit :</label>
						<input type="text" id="motCle" name="motCle" value="${model.motCle }" class="form-control form-control-lg"  />
						<button type="submit" value="rechercher"  name="action" class="btn btn-outline-primary ml-4 mt-0 btn-lg">Rechercher</button>
					</div>
						
			</form>
		
		
		<c:if test="${model.error !=null}">
		<div class="col-sm-12">
		<div class="alert alert-danger" >
			${model.error}
		</div>
		</div>
		</c:if>
		
		<c:if test="${model.sucess !=null}">
		<div class="col-sm-12">
		<div class="alert alert-success" >
			${model.sucess}
		</div>
		</div>
		</c:if>
		
		<div class="col-sm-12">
		<div class="row">
		<div class="col-sm-4 mb-3">
		<div class="card">
		  <div class="card-header">
		    <h5>Nouveau / modifier produit :</h5>
		  </div>
		  <div class="card-body"> 
		    <div>
			<form action="produit" method="post">
						<input type="hidden" name="saveORediter" value="${model.getSaveORediter()}"/>
						<div class="form-group">
						<label>Reference : </label>
						<c:choose>
							<c:when test="${model.getSaveORediter() == 'save'}">
									<input class="form-control" type="text" name="reference"  value="${ model.produit.reference}"/>	
							</c:when>
							<c:otherwise>
									<input class="form-control" type="text" name="reference"  value="${ model.produit.reference}" readonly/>	
							</c:otherwise>
						</c:choose>					
						</div>
						<div class="form-group">
						<label>Designation : </label>
						<input class="form-control" type="text" name="designation"  value="${ model.produit.designation}"/>						
						</div>
						<div class="form-group">
						<label>Prix : </label>
						<input class="form-control" type="text" name="prix"  value="${model.produit.prix != 0 ?  model.produit.prix : ''}" />						
						</div>
						<div class="form-group">
						<label>Quantité : </label>
						<input class="form-control" type="text" name="quantite"  value="${model.produit.quantite!=0 ? model.produit.quantite : '' }" />						
						</div>

						<button type="submit" value="save"  name="action" class="btn btn-outline-success col-sm-12">Enregistrer</button>
			</form>
		  </div>
		</div>
		</div>
		</div>
				
		<div class="col-sm-8 mb-3">
			<table class="table table-bordered">
			<thead class="thead-dark">
				<tr>
					<th>Réference</th> <th>Designation</th> <th>Prix</th> <th>Quantité</th>  
				</tr>
			</thead>
				<c:forEach items="${model.produits}" var="produit">
					<tr>
						<td>${produit.reference}</td>
						<td>${produit.designation}</td>
						<td>${produit.prix} DH</td>
						<td>${produit.quantite}</td>
						<td><a href="javascript:confirmer('produit?action=delete&ref=${produit.reference }')" class="badge badge-danger">Supprimer</a></td>
						<td><a href="produit?action=edit&ref=${produit.reference}" class="badge badge-warning">Editer</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
		</div>
		</div>
		

</body>
</html>