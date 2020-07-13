<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Gestion de produits</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
	<script type="text/javascript">
			function confirmer(url)
			{
				var rep=confirm("�tes-vous s�r de vouloir supprimer ce produit ?");
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
						<label for="motCle"  class="form-label mx-4 col-form-label-lg">Chercher un produit :</label>
						<input type="text" id="motCle" name="motCle" value="${model.motCle }" class="form-control form-control-lg"  />
						<button type="submit" value="chercher"  name="action" class="btn btn-outline-primary ml-4 mt-0 btn-lg">Chercher</button>
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
						<div class="form-group">
						<label>Reference : </label>
						<input class="form-control" type="text" name="reference"  value="${ model.produit.reference}"/>						
						</div>
						<div class="form-group">
						<label>Designation : </label>
						<input class="form-control" type="text" name="designation"  value="${ model.produit.designation}"/>						
						</div>
						<div class="form-group">
						<label>Prix : </label>
						<input class="form-control" type="text" name="prix"  />						
						</div>
						<div class="form-group">
						<label>Quantit� : </label>
						<input class="form-control" type="text" name="quantite"  />						
						</div>

						<input type="submit" value="ajouter"  name="action" class="btn btn-outline-success col-sm-12"/>
			</form>
		  </div>
		</div>
		</div>
		</div>
				
		<div class="col-sm-8 mb-3">
			<table class="table table-bordered">
			<thead class="thead-dark">
				<tr>
					<th>R�ference</th> <th>Designation</th> <th>Prix</th> <th>Quantit�</th>  
				</tr>
			</thead>
				<c:forEach items="${model.produits}" var="produit">
					<tr>
						<td>${produit.reference}</td>
						<td>${produit.designation}</td>
						<td>${produit.prix} DH</td>
						<td>${produit.quantite}</td>
						<td><a href="javascript:confirmer('produit?action=delete&ref=${produit.reference }')" class="badge badge-danger">Supprimer</a></td>
						<td><a href="" class="badge badge-warning">Editer</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
		</div>
		</div>
		

</body>
</html>