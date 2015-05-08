<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listar</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
</head>
<body>
	<div id='wrap'>
		<div class="container">

		<a href="executa?op=feriadosAll"><h4>Feriados</h4></a>
		<c:if test="${not empty feriados}">
			<table class="table">
				<c:forEach var="feriado" items="${feriados}">
					<tr>
						<td>${feriado.nome}</td>
						<td>${feriado.data}</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
		<c:if test="${not empty anotacoes}">
			<h4>Feriados</h4>
			<table class="table">
				<c:forEach var="anotacao" items="${anotacoes}">
					<tr>
						<td>${anotacao.desc}</td>
						<td>${anotacao.data}</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>		
	</div>
	
</body>
</html>