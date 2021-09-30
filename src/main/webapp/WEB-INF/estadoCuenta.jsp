<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/css/perfilUsuario.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <title>Estado Cuenta</title>
    <script src="/js/backbutton.js"></script>
</head>
<body>
<header class="navbar p-2">
    <nav class="container">
        <a class="link-light" href="/">Inicio</a>
        <c:if test="${user.rol == 3 }">
            <a class="link-light" href="/admin">Administrar</a>
        </c:if>
        <a class="link-light" href="/publicaciones/add">Crear Publicación</a>
        <a style="text-decoration: none" class="link-light" href="javascript: history.go(-1)"><i class="bi bi-arrow-left-circle"></i> Volver atrás</a>
        <c:if test="${user.id != null }">
            <a style="text-decoration: none" class="link-light" href="/logout">Cerrar Sesión</a>
        </c:if>
    </nav>
</header>
    <div class="container mb-5">
        <div class="col mt-5 text-center p-3" style="background-color: rgba(0, 0, 0, 0.2);">
            <h3 class="fs-2">
                <c:if test="${user.available == false && user.id != userLog.id}">¿Estás seguro de que quieres desbanear a este usuario?</c:if>
                <c:if test="${user.available == true && user.id != userLog.id}">¿Estás seguro de que quieres banear a este usuario?</c:if>
                <c:if test="${user.available == true && user.id == userLog.id}">¿Estás seguro de que quieres deshabilitar tu cuenta? (No podrás volver a habilitarla)</c:if>
            </h3>
        </div>
        <div class="row mb-5">
            <div class="col mt-5 mb-5" >
                <img src="/${user.photo}" height="320px" width="500px" class="img-thumbnail">
            </div>
            <div class="col mt-5 mb-5 d-flex justify-content-center align-items-center bordeado">
                <ul class="list-group">
                    <li class="list-group-item">Nombre Usuario: <c:out value="${user.firstName}"/> <c:out value="${user.lastName}"/></li>
                    <li class="list-group-item">Rol:
                        <c:if test="${user.rol == 1}">Prestador de Servicios</c:if>
                        <c:if test="${user.rol == 2}">Solicitador de Servicios</c:if>
                        <c:if test="${user.rol == 3}">Administrador</c:if>
                    </li>
                    <li class="list-group-item">Email: <c:out value="${user.email}"/></li>
                    <li class="list-group-item"> Celular: <i style="color: 7FFF00" class="bi bi-whatsapp"></i><c:out value="${user.phone}"/> </li>
                    <li class="list-group-item">Número de publicaciones: <c:out value="${user.publications.size()}"/></li>
                    <li class="list-group-item">Dirección: <c:out value="${user.address.nameCalle}"/></li>
                    <li class="list-group-item">Comuna: <c:out value="${user.address.comuna.nameComuna}"/></li>
                    <li class="list-group-item">Región: <c:out value="${user.address.comuna.region.nameRegion}"/></li>
                        <c:if test="${user.available == false}">
                        <a class="btn btn-danger" href="/${user.id}/estadoCuenta">Habilitar Cuenta</a>
                        </c:if>
                        <c:if test="${user.available == true}">
                            <a class="btn btn-danger" href="/${user.id}/estadoCuenta">Deshabilitar Cuenta</a>
                        </c:if>
                </ul>
            </div>
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
