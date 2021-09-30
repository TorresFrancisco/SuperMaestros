<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <title>Mensajes con denuncias</title>
</head>
<body>
    <div class="container">
        <header class="d-flex justify-content-between">
            <h1>Mensajes Denunciados</h1>
            <a class="btn btn-link" href="/admin">Inicio Administrador</a>
            <a style="text-decoration: none" class="link-light" href="javascript: history.go(-1)"><i class="bi bi-arrow-left-circle"></i> Volver atrás</a>
        </header>
        <table class="table">
            <thead class="thead-dark">
            <tr>
                <th scope="col">Mensaje</th>
                <th scope="col">Número de denuncias</th>
                <th scope="col">Publicación</th>
                <th scope="col">Todas las denuncias</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="message" items="${messages}">
                <tr>
                    <td><c:out value="${message.text}"/></td>
                    <td><c:out value="${message.numDenuncias}"/></td>
                    <td><a href="/publicaciones/${message.publication.id}"><c:out value="${message.publication.title}"/></a></td>
                    <td><a href="/admin/denuncias/${message.id}">Ver Denuncias</a>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
