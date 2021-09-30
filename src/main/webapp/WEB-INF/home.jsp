<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Inicio</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css" rel="stylesheet">
    <link href="/css/home.css" rel="stylesheet">
    <link rel="shortcut icon" href="/archivos/logos/iconoSuperMaestro.png" type="image/x'icon">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
</head>
    <body>
    <header class="navbar p-2">
        <nav class="container">
            <c:if test="${user != null}">
                <div class="dropdown" id="notificaciones">
                    <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                        <c:out value="${user.notificacions.size()}"/>
                    </button>
                    <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                        <c:forEach var="notificacion" items="${user.notificacions}">
                            <li><a class="dropdown-item" href="/notificacion/${notificacion.id}/"> <c:out value="${notificacion.contenido}"/> </a></li>
                        </c:forEach>
                    </ul>

                </div>
            </c:if>
            <c:if test="${user.id == null}">
                <a style="text-decoration: none" class="link-light" class="btn btn-link" href="/login">Iniciar Sesión</a>
                <a style="text-decoration: none" class="link-light" href="/registration">Registrarse</a>
            </c:if>
            <c:if test="${user.id != null}">
                <a style="text-decoration: none" class="link-light" href="/perfil/${user.id}"><img src="${user.photo}" width="50px" height="50px"> <c:out value="${user.firstName}"/> <c:out value="${user.lastName}"/></a>
                <c:if test="${user.id != null && user.rol == 3 }">
                    <a style="text-decoration: none" class="link-light" href="/admin">Administrar</a>
                </c:if>
                <a style="text-decoration: none" class="link-light" href="/publicaciones/add">Crear Publicación</a>
                <a style="text-decoration: none" class="link-light" href="/publicaciones">Publicaciones</a>
                <a style="text-decoration: none" class="link-light" href="/logout">Cerrar Sesión</a>
            </c:if>
        </nav>
    </header>
        <div class="container">
            <div class="row mt-5 mb-5">
                <div class="col-lg-4 col-sm-12"></div>
                <div class="col">
                    <form method="get" action="/buscando" class="col align-self-center">
                        <h3 class="fw-bolder" style="text-align: center">Buscar publicaciones</h3>
                        <label class="m-2" for="categoria"> Categoría: </label>
                        <select id="categoria" class="form-select" aria-label="Default select example" name="idCategoria">
                            <option disabled selected value="">Selecciona una</option>
                            <c:forEach var="categoria" items="${categorias}">
                                <option value="${categoria.id}"> <c:out value="${categoria.name}"/></option>
                            </c:forEach>
                        </select>

                        <label class="m-2" for="region"> Región: </label>
                        <select id="region" class="form-select" aria-label="Default select example" name="idRegion">
                            <option disabled selected value="">Selecciona una</option>
                            <c:forEach var="region" items="${regiones}">
                                <option value="${region.id}"> <c:out value="${region.nameRegion}"/></option>
                            </c:forEach>
                        </select>

                        <label class="m-2" for="comunas"> Comunas: </label>
                        <select id="comunas" class="form-select" aria-label="Default select example" name="idComuna">
                            <option disabled selected value="">Selecciona una</option>
                        </select>
                        <br>
                        <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                            <button class="btn btn-light me-md-2" type="submit">Buscar</button>
                        </div>
                    </form>
                </div>
                <div class="col-lg-4 col-sm-12"></div>
            </div>
        </div>
    </div>
    <c:if test="${user != null}">
        <div class="d-grid gap-2 d-md-flex justify-content-md-center mb-5">
            <a class="btn btn-danger" href="/sos">Anuncios SOS</a>
        </div>
    </c:if>
    <div class="container">
        <div class="d-flex justify-content-center row mt-5 mb-5">
            <c:forEach var="categoria" items="${categorias}">
                <div class="card col-4 m-1" style="width: 18rem;">
                    <div class="card-body">
                        <h5 class="card-title">Buscar <c:out value="${categoria.name}"/></h5>
                        <p class="card-text">Todos la categoría <c:out value="${categoria.name}"/> en todo Chile </p>
                    </div>
                    <a class="btn btn-primary m-2" href="/buscador/categoria/${categoria.id}" class="card-link">Buscar</a>
                </div>
            </c:forEach>
        </div>
    </div>
    <script>
        regiones = JSON.parse('${regionesObject}');

    </script>
    <script src="/js/app.js"></script>
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
