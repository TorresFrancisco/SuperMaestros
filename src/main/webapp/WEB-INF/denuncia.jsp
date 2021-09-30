<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css" rel="stylesheet">
    <link rel="shortcut icon" href="/archivos/logos/iconoSuperMaestro.png" type="image/x'icon">
    <link rel="stylesheet" href="/css/denuncia.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <script src="/js/backbutton.js"></script>
    <title>Denunciar Comentario</title>
</head>
<body>
<header class="navbar p-2">
    <nav class="container">
        <c:if test="${user.photo == null}">
            <a style="text-decoration: none" class="link-light" href="/perfil/${user.id}"><img src="/archivos/default/default.png" width="100px" height="50px"> <c:out value="${user.firstName} ${user.lastName}" /> </a>
        </c:if>
        <c:if test="${user.photo != null}">
            <a style="text-decoration: none" class="link-light" href="/perfil/${user.id}"><img src="/${user.photo}" width="50px" height="50px"> <c:out value="${user.firstName} ${user.lastName}"/> </a>
        </c:if>
        <c:if test="${user.rol == 3 }">
            <a style="text-decoration: none" class="link-light" href="/admin">Administrar</a>
        </c:if>
        <a style="text-decoration: none" class="link-light" href="/">Inicio</a>
        <a style="text-decoration: none" class="link-light" href="/publicaciones/add">Crear Publicación</a>
        <a style="text-decoration: none" class="link-light" href="javascript: history.go(-1)"><i class="bi bi-arrow-left-circle"></i> Volver atrás</a>
        <c:if test="${user.id != null }">
            <a style="text-decoration: none" class="link-light" href="/logout">Cerrar Sesión</a>
        </c:if>
    </nav>
</header>
    <div class="col mb-5 mt-5 text-center p-3" style="background-color: rgba(0, 0, 0, 0.2);">
        <h3 class="fs-2">
            <h2 style="color: white">¿Por qué quiere denunciar este comentario?</h2>
        </h3>
    </div>
    <div class="container">
        <div class="row mt-5 mb-5">
            <div class="col-2"></div>
            <div class="col border-1 p-5 bordeado">
                <span style="color: red;"><form:errors path="post.*"/></span>
                <form:form action="" modelAttribute="denuncia" method="post">
                    <p style="color: white" class="col">
                        <form:label path="detalles">Elige una opción:</form:label>
                        <form:select class="form-select" path="tipoDenuncia">
                            <option disabled selected value="">Selecciona una</option>
                            <form:option value="1">Ofrece un servicio que es ilegal o no cumple con nuestras políticas.</form:option>
                            <form:option value="2">Promociona otro servicio.</form:option>
                            <form:option value="3">Tiene contenido ofensivo, obsceno o discriminatorio.</form:option>

                        </form:select>
                    </p>
                    <p style="color: white" class="col">
                        <form:label path="detalles">Detalles de la denuncia:</form:label>
                        <form:textarea cssClass="form-control" path="detalles"/>
                    </p>
                    <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                        <input class="btn btn-danger me-md-2" type="submit" value="Enviar">
                    </div>
                </form:form>
            </div>
            <div class="col-2"></div>
        </div>
    </div>
<footer class="bg-dark text-center text-white">
    <!-- Grid container -->
    <div class="container p-4">
        <!-- Section: Social media -->
        <section class="mb-4">
            <!-- Facebook -->
            <a class="btn btn-outline-light btn-floating m-1" href="https://www.facebook.com/SuperMaestros-153113033550715" target="_blank" role="button"
            ><i class="fab fa-facebook-f"></i
            ></a>

            <!-- Google -->
            <a class="btn btn-outline-light btn-floating m-1" href="https://www.youtube.com/watch?v=dQw4w9WgXcQ" target="_blank" role="button"
            ><i class="fab fa-google"></i
            ></a>
        </section>
        <!-- Section: Social media -->
        <div>
            <!-- Section: Text -->
            <div class="footer-widget-1">
                <p class="fs-6"></p>
            </div>
            <!-- Section: Text -->
            <div class="footer-widget-2"></div>
            <!-- Section: Links -->
            <div class="footer-widget-3">
                <!--Grid row-->
                <sec class="row">
                    <!--Grid column-->
                    <div>
                        <h5 class="text-uppercase">Enlaces</h5>
                        <ul class="list-unstyled mb-0">
                            <li>
                                <a style="text-decoration: none" href="/politicas" class="text-white">Políticas de Privacidad</a>
                            </li>
                            <li>
                                <a style="text-decoration: none" href="/quienessomos" class="text-white">Quiénes Somos</a>
                            </li>
                            <li>
                                <a style="text-decoration: none" href="contacto" class="text-white">Contáctanos</a>
                            </li>
                            <li>
                                <a style="text-decoration: none" href="recuperarcontraseña" class="text-white">Recuperación de Contraseña</a>
                            </li>
                        </ul>
                    </div>
                    <!--Grid column-->
                    <!-- Copyright -->
                    <div class="text-center p-3" style="background-color: rgba(0, 0, 0, 0.2);">
                        SuperMaestros© 2021 Copyright:
                        <a style="text-decoration: none" class="text-white " href="/">supermaestros.com</a>
                    </div>
                    <!-- Copyright -->
                </sec>
            </div>
        </div>
    </div>
</footer>
</body>
</html>