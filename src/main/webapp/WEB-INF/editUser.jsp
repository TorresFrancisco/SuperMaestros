<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css" rel="stylesheet">
    <link rel="shortcut icon" href="/archivos/logos/iconoSuperMaestro.png" type="image/x'icon">
    <link rel="stylesheet" href="/css/editUser.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <title>Editar Perfil</title>
    <script src="/js/backbutton.js"></script>
</head>
<body>
    <header class="navbar p-2">
        <nav class="container">
            <a style="text-decoration: none" class="link-light" href="/">Inicio</a>
            <c:if test="${user.rol == 3 }">
                <a style="text-decoration: none" class="link-light" href="/admin">Administrar</a>
            </c:if>
            <a style="text-decoration: none" class="link-light" href="/publicaciones/add">Crear Publicación</a>
            <a style="text-decoration: none" class="link-light" href="/perfil/${user.id}/estadoCuenta">Deshabilitar Cuenta</a>
            <a style="text-decoration: none" class="link-light" href="javascript: history.go(-1)"><i class="bi bi-arrow-left-circle"></i> Volver atrás</a>
            <c:if test="${user.id != null }">
                <a style="text-decoration: none" class="link-light" href="/logout">Cerrar Sesión</a>
            </c:if>
        </nav>
    </header>
    <div class="container">
        <div class="col mt-5 text-center p-3" style="background-color: rgba(0, 0, 0, 0.2);">
            <h3 class="fs-2">
                <img src="/${user.photo}" width="50px" height="50px">
                <c:out value="${user.firstName}"/> <c:out value="${user.lastName}"/>
            </h3>
        </div>
        <div class="row mt-5 mb-5">
            <div class="col-2"></div>
            <div class="formulario col border-1 p-5 mr-3">
                <h1>Editar Perfil</h1>
                <span style="color: red;"><form:errors path="user.*"/></span>
                <form:form method="POST" action="" enctype="multipart/form-data" modelAttribute="user">
                    <input type="hidden" name="_method" value="PUT">
                    <form:input type="hidden" path="email" value="${user.email}"/>
                    <form:input type="hidden" path="password" value="${user.password}"/>
                    <form:input type="hidden" path="passwordConfirmation" value="${user.passwordConfirmation}"/>
                    <form:input type="hidden" path="photo" value="${user.photo}"/>

                    <p style="color: black;" class="form-group col">
                        <form:label path="firstName">Nombre: </form:label>
                        <form:input cssClass="form-control" path="firstName"/>
                    </p>

                    <p style="color: black;" class="col">
                        <form:label path="lastName">Apellido: </form:label>
                        <form:input cssClass="form-control" path="lastName"/>
                    </p>

                    <p style="color: black;" class="col">
                        <form:label path="address.comuna.region">Región: </form:label>
                        <form:select cssClass="form-control" path="address.comuna.region">
                            <c:forEach var="region" items="${regiones}">
                                <form:option value="${region.id}"><c:out value="${region.nameRegion}"/> </form:option>
                            </c:forEach>
                        </form:select>
                    </p>

                    <p style="color: black;" class="col">
                        <form:label path="address.comuna">Comuna: </form:label>
                        <form:select cssClass="form-control" path="address.comuna">
                            <c:forEach var="comuna" items="${comunas}">
                                <form:option value="${comuna.id}"> <c:out value="${comuna.nameComuna}"/> </form:option>
                            </c:forEach>
                        </form:select>
                    </p>

                    <p style="color: black;" class="col">
                        <form:label path="address.nameCalle">Calle: </form:label>
                        <form:input cssClass="form-control" path="address.nameCalle"/>
                    </p>

                    <p style="color: black;" class="col">
                        <form:label path="phone">Celular: </form:label>
                        <form:input cssClass="form-control" path="phone"/>
                    </p>
                    <c:if test="${user.rol != 3}">
                        <p style="color: black;" class="col">
                            <form:label path="rol">Rol: </form:label>
                            <form:select cssClass="form-control" path="rol">
                                <option disabled selected value="">Selecciona una</option>
                                <form:option value="1">Prestar Servicios</form:option>
                                <form:option value="2">Contratar Servicios</form:option>
                            </form:select>
                        </p>
                    </c:if>
                    <c:if test="${user.rol == 3}">
                        <p style="color: black;" class="col">
                        <form:label path="rol">Rol: </form:label>
                        <form:select cssClass="form-control" path="rol">
                            <option disabled selected value="">Selecciona una</option>
                            <form:option value="1">Prestar Servicios</form:option>
                            <form:option value="2">Contratar Servicios</form:option>
                            <form:option value="3">Administrador</form:option>
                        </form:select>
                        </p>
                    </c:if>
                    <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                        <input class="btn btn-dark me-md-2" type="submit" value="Editar!"/>
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