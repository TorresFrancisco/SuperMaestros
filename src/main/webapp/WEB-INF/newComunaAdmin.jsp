<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
    <link rel="shortcut icon" href="/archivos/logos/iconoSuperMaestro.png" type="image/x'icon">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <title>Agregar Comunas</title>
    <script src="/js/backbutton.js"></script>
</head>
<body>
<div class="container">
    <header class="d-flex justify-content-between">
        <h1>Agregar Comunas</h1>
        <a class="btn btn-link" href="/admin">Inicio Administrador</a>
        <a style="text-decoration: none" class="link-light" href="javascript: history.go(-1)"><i class="bi bi-arrow-left-circle"></i> Volver atrás</a>
    </header>
    <form:errors path="comuna.*"/>
    <form:form method="POST" action="" cssClass="form col border border-1 rounded" modelAttribute="comuna" >
        <p class="form-group col">
            <form:label path="nameComuna">Nombre: </form:label>
            <form:input cssClass="form-control" path="nameComuna"/>
        </p>
        <p class="col">
            <form:label path="region">Region:</form:label>
            <form:select cssClass="form-control" path="region">
                <c:forEach items="${regiones}" var="region">
                    <form:option value="${region.id}"><c:out value="${region.nameRegion}"/></form:option>
                </c:forEach>
            </form:select>
        </p>
        <input class="btn btn-warning" type="submit" value="Crear Comuna"/>
    </form:form>
</div>
</body>
</html>
