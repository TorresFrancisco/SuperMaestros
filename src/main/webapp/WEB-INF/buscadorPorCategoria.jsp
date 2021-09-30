<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css" rel="stylesheet">
    <link rel="shortcut icon" href="/archivos/logos/iconoSuperMaestro.png" type="image/x'icon">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">    <title>Buscar por Categoría</title>
    <link rel="stylesheet" href="/css/categoria.css">
    <script src="/js/backbutton.js"></script>
    <title>Buscar por Categoría</title>
</head>
<body>
    <header class="navbar p-2">
            <nav class="container">
                <c:if test="${user.id != null}">
                    <a style="text-decoration: none" class="link-light" href="/perfil/${user.id}"><img src="/${user.photo}" width="50px" height="50px"> <c:out value="${user.firstName}"/> <c:out value="${user.lastName}"/></a>
                    <c:if test="${user.rol == 3 }">
                        <a style="text-decoration: none" class="link-light" href="/admin">Administrar</a>
                    </c:if>
                </c:if>
                <a style="text-decoration: none" class="link-light" href="/">Inicio</a>
                <c:if test="${user.id != null}">
                    <a style="text-decoration: none" class="link-light" href="/publicaciones/add">Crear Publicación</a>
                </c:if>
                <a style="text-decoration: none" class="link-light" href="javascript: history.go(-1)"><i class="bi bi-arrow-left-circle"></i> Volver atrás</a>
                <c:if test="${user.id != null }">
                    <a style="text-decoration: none" class="link-light" href="/logout">Cerrar Sesión</a>
                </c:if>
            </nav>
    </header>
    <div class="container mb-5 mt-5">
        <c:forEach var="publicacion" items="${publicacionesPorCategoria}">
            <div class="row border d-flex justify-content-center p-3">
                <ul class="list-group col">
                    <li class="list-group-item">Nombre: <c:out value="${publicacion.user.firstName} ${publicacion.user.lastName}"/></li>
                    <li class="list-group-item">Celular: <i style="color: 7FFF00" class="bi bi-whatsapp"></i><c:out value="${publicacion.user.phone}"/></li>
                    <li class="list-group-item">Título: <c:out value="${publicacion.title}"/></li>
                    <li class="list-group-item">Descripción: <c:out value="${publicacion.description}"/></li>
                    <li class="list-group-item">Dirección: <c:out value="${publicacion.user.address.comuna.region.nameRegion} ${publicacion.user.address.comuna.nameComuna} ${publicacion.user.address.nameCalle}"/></li>
                    <li class="list-group-item">Precio: <c:out value="${publicacion.price}"/></li>
                    <a class="btn btn-primary" href="/publicaciones/${publicacion.id}">Ir a la publicación</a>
                </ul>
                <div class="mapouter col"><div class="gmap_canvas"><iframe width="100%" height="300" id="gmap_canvas" src="https://maps.google.com/maps?q=${publicacion.user.address.comuna.region.nameRegion}%20${publicacion.user.address.comuna.nameComuna}%20${publicacion.user.address.nameCalle}&t=&z=13&ie=UTF8&iwloc=&output=embed" frameborder="0" scrolling="no" marginheight="0" marginwidth="0"></iframe><a href="https://123movies-to.org"></a><br><style>.mapouter{position:relative;text-align:right;height:300px;width:100%;}</style><a href="https://www.embedgooglemap.net"></a><style>.gmap_canvas {overflow:hidden;background:none!important;height:300px;width:100%;}</style></div></div>
            </div>
        </c:forEach>
    </div>
    <div class="agrandar"></div>
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
