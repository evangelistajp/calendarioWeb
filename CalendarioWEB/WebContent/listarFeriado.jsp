<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Feriados</title>
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
	
	<div class="container">
		<h3>Admin : ${admin}</h3>
		<h4>Feriados</h4>			
		<c:if test="${not empty feriados}">
			<table class="table">
				<c:forEach var="feriado" items="${feriados}">
					<tr>
						<td>${feriado.nome}</td>
						<td>${feriado.data}</td>
						<td><a href="executa?op=buscarFeriado&id=${feriado.id}">Editar</a></td>
						<td><a href="executa?op=excluirferiado&id=${feriado.id}">Excluir</a></td>
					</tr>
				</c:forEach>
			</table>
		</c:if>	
		<a href="admin.jsp">voltar</a>
	</div>
</body>
</html>