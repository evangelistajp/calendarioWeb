<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Gerenciar Anotação</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
</head>
<body>
	<c:if test="${sessionScope.usuario == null}">
		<c:redirect url="index.jsp"></c:redirect>
	</c:if>
	<div class="container">
		<h3>Usuário : ${usuario}</h3>
		<div class="menu">
			<a href="usuario.jsp">Home</a>
			<a href="CadastrarAnotacao.jsp">Add Anotação </a>
			<a href="<c:url value="executa?op=logout"/>">logout</a>
		</div>
	</div>
	
	<table class="table">
		<c:forEach var="anotacao" items="${anotacoes}">
			<hr>
			<tr>
				<td>${anotacao.mensagem}</td>
				<td>${anotacao.data}</td>
				<td><a href="">Editar</a></td>
				<td><a href="">Excluir</a></td>
			</tr>
		</c:forEach>
	</table>
	<hr>
	<div class="container">
		<h1>Calendario</h1>
		<div id="calendar"><%@ include file ="calendario.jsp" %></div>
		
	</div>
</body>
</html>