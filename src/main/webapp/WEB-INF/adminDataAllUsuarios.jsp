<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
    <link rel="shortcut icon" href="/archivos/logos/iconoSuperMaestro.png" type="image/x'icon">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <title>Admin Usuarios</title>
    <script src="/js/backbutton.js"></script>
</head>
<body>
<div class="container">
    <header class="d-flex justify-content-between">
        <h1>Administrar Usuarios</h1>
        <a class="btn btn-link" href="/admin">Inicio Administrador</a>
        <a style="text-decoration: none" class="link-light" href="javascript: history.go(-1)"><i class="bi bi-arrow-left-circle"></i> Volver atrás</a>
    </header>
    <table class="table">
        <thead class="thead-dark">
        <tr>
            <th scope="col">Nombre</th>
            <th scope="col">Rol</th>
            <th scope="col">N de publicaciones</th>
            <th scope="col">Acciones</th>
        </tr>
        </thead>
        <tbody>
        <span style="color: red;"><form:errors path="d.*"/></span>
        <c:forEach var="d" items="${data}">
            <tr>
                <td><c:out value="${d.firstName}"/> <c:out value="${d.lastName}"/></td>
                <td>
                    <c:if test="${d.rol == 1}">Prestador de Servicios</c:if>
                    <c:if test="${d.rol == 2}">Solicitador de Servicios</c:if>
                    <c:if test="${d.rol == 3}">Administrador</c:if>
                </td>
                <td><c:out value="${d.publications.size()}"/></td>
                <td>
                    <c:if test="${d.available == false}">Baneado
                        <a class="btn btn-link" href="/perfil/${d.id}/estadoCuenta">Desbanear</a>
                    </c:if>
                    <c:if test="${d.available == true}">Activo
                        <a class="btn btn-link" href="/perfil/${d.id}/estadoCuenta">Banear</a>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
