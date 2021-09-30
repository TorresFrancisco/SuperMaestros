<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <title>Denuncias Mensaje</title>
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
                <th scope="col">Tipo de Denuncia</th>
                <th scope="col">Detalles</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="denuncia" items="${message.denuncias}">
                <tr>
                    <td>
                        <c:if test="${denuncia.tipoDenuncia == 1}">
                            <c:out value="Contenido Ofensivo"/>
                        </c:if>
                        <c:if test="${denuncia.tipoDenuncia == 2}">
                            <c:out value="Información Personal"/>
                        </c:if>
                    </td>
                    <td><c:out value="${denuncia.detalles}"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
