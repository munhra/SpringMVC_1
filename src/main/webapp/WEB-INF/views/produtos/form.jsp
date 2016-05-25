<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Venda de livros</title>
<c:url value="/resources/css" var="cssPath" />
<link rel="stylesheet" href="${cssPath}/bootstrap.min.css">
<link rel="stylesheet" href="${cssPath}/bootstrap-theme.min.css">
<style type="text/css">

	body {
	
		padding: 60px, 0px;
	}

</style>

</head>
<body>

	<ul class="nav nav-tabs">
		<li role="presentation" class="active"><a href="${s:mvcUrl('HC#index').build() }">Casa do código </a></li>
		<li role="presentation"><a
			href="${s:mvcUrl('PC#listar').build() }">Lista de Produtos</a></li>
		<li role="presentation"><a href="${s:mvcUrl('PC#form').build() }"">Cadastro
				de Produtos</a></li>
	</ul>

	<div class="container">
		<h1>Cadastro de Produtos</h1>
		<form:form action="${s:mvcUrl('PC#grava').build() }" method="post"
			commandName="produto" enctype="multipart/form-data">
			<div class="form-group">
				<label>Titulo</label>
				<form:input path="titulo" cssClass="form-control" />
				<form:errors path="titulo" />
			</div>
			<div class="form-group">
				<label>Descrição</label>
				<form:textarea path="descricao" cssClass="form-control" />
				<form:errors path="descricao" />
			</div>
			<div class="form-group">
				<label>Páginas</label>
				<form:input path="paginas" cssClass="form-control" />
				<form:errors path="paginas" />
			</div>
			<div class="form-group">
				<label>Data de Lançamento</label>
				<form:input path="dataLancamento" cssClass="form-control"/>
				<form:errors path="dataLancamento" />
			</div>
			<div class="form-group">
				<label>Data Lançamento</label>
				<form:input path="dataLancamento" cssClass="form-control"/>
			</div>
			<c:forEach items="${tipos}" var="tipoPreco" varStatus="status">
				<div class="form-group">
					<label>${tipoPreco}</label>
					<form:input path="precos[${status.index}].valor" cssClass="form-control"/>
					<form:hidden path="precos[${status.index}].tipo"
						value="${tipoPreco}" />
				</div>
			</c:forEach>
			<div class="form-group">
				<label>Sumário</label>
				<input name="sumario" type="file" class="form-control" />
			</div>
			<button type="submit" class="btn btn-primary">Cadastrar</button>
		</form:form>
	</div>
</body>
</html>