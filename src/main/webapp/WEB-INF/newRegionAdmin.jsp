<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
    <link rel="shortcut icon" href="/archivos/logos/iconoSuperMaestro.png" type="image/x'icon">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <title>Agregar Regiones</title>
    <script src="/js/backbutton.js"></script>
</head>
<body>
<div class="container">
    <header class="d-flex justify-content-between">
        <h1>Agregar Regiones</h1>
        <a class="btn btn-link" href="/admin">Inicio Administrador</a>
        <a style="text-decoration: none" class="link-light" href="javascript: history.go(-1)"><i class="bi bi-arrow-left-circle"></i> Volver atrás</a>
    </header>
    <div class="row">
        <div class="col-12  border border-1 rounded">
            <form:errors path="region.*" cssClass="col"/>
            <form:form method="POST" action="" cssClass="form col" modelAttribute="region" >
                <p class="form-group col">
                    <form:label path="nameRegion">Nombre de la Region: </form:label>
                    <form:input cssClass="form-control" path="nameRegion"/>
                </p>
                <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                    <input class="btn btn-dark me-md-2" type="submit" value="Crear Región"/>
                </div>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>

