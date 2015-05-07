<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastra Usuario</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
</head>
<body>
	<body>
	 <div class="container" style="width: 300px">
	    <form class="form-signin" action="executa?op=cadastrar" method="POST">
	        <h2 class="form-signin-heading">Cadastrar Usuário</h2>
	        <label for="inputname" class="sr-only">Nome</label>
	        <input type="text" id="inputname" name="nome" class="form-control" placeholder="Nome" required autofocus>
	        <label for="inputEmail" class="sr-only">Email address</label>
	        <input type="email" id="inputEmail" name="email" class="form-control" placeholder="Email address" required autofocus>
	        <label for="inputPassword" class="sr-only">Password</label>
	        <input type="password" id="inputPassword" name="senha" class="form-control" placeholder="Password" required>
	        <br>
	        <button class="btn btn-lg btn-primary btn-block" type="submit">Cadastrar</button>
	    </form>
	    <a href="index.jsp">voltar</a>
	 </div> <!-- /container -->
</body>
</html>