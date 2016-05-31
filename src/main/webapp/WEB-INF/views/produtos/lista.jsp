<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lista de livros</title>
<c:url value="/resources/css" var="cssPath" />
<link rel="stylesheet" href="${cssPath}/bootstrap.min.css">
<link rel="stylesheet" href="${cssPath}/bootstrap-theme.min.css">
</head>
<body>
	<ul class="nav nav-tabs">
	
		<li role="presentation" class="active"><a href="${s:mvcUrl('HC#index').build() }">Casa do código </a></li>
		<li role="presentation"><a href="${s:mvcUrl('PC#listar').build() }">Lista de Produtos</a></li>
		<li role="presentation"><a href="${s:mvcUrl('PC#form').build() }">Cadastrode Produtos</a></li>
		
	</ul>
	<ul class="nav navbar-nav navbar-right">
		<li><a href="#">
		
			<security:authentication property="principal" var="usuario"/>
			Usuário: ${usuario.username}
		</a>
		</li>
	
	</ul>

	<div class="container">
		<h1>Lista de produtos</h1>
		<div>${sucesso} ${falha }</div>
		<table class="table table-bordered table-striped table-hover">
			<tr>
				<th>Titulo</th>
				<th>Descrição</th>
				<th>Páginas</th>
			</tr>

			<c:forEach items="${produtos}" var="produto">
				<tr>

					<td><a
						href="${s:mvcUrl('PC#detalhe').arg(0,produto.id).build() }">${produto.titulo}</a></td>
					<td>${produto.descricao}</td>
					<td>${produto.paginas}</td>
				</tr>
			</c:forEach>

		</table>
	</div>
</body>
</html>