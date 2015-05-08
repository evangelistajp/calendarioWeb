<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastrar Feriado</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
</head>
<body>
	<c:if test="${sessionScope.admin == null}">
		<c:redirect url="index.jsp"></c:redirect>
	</c:if>
	<div class="container" style="width: 300px">
		<div style='color:red'><h4>${erro}</h4></div>
	    <div style='color:green'><h4>${ok}</h4></div>
	    <form class="form-signin" action="executa?op=cadastrarFeriado" method="POST">
	        <h2 class="form-signin-heading">Cadastrar Feriado</h2>
	        
	        <label for="inputnome" class="sr-only">Nome</label>
	        <input type="text" id="inputnome" name="nome" class="form-control" placeholder="Nome">
	        <label for="inputdata" class="sr-only">Data</label>
	        <input type="date" id="inputdata" name="data" class="form-control">
	        <input type="checkbox" name="fixo" value="true"> FIXO<br>
	        
	        <br>
	        <button class="btn btn-lg btn-primary btn-block" type="submit">Cadastrar</button>
	    </form>
	    <a href="admin.jsp">voltar</a>
	    
	 </div> <!-- /container -->
</body>
</html>