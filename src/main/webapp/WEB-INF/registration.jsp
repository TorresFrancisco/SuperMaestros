<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css" rel="stylesheet">
    <link rel="shortcut icon" href="/archivos/logos/iconoSuperMaestro.png" type="image/x'icon">
    <link rel="stylesheet" href="/css/registration.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <script src="/js/backbutton.js"></script>
    <title>Registrarse</title>
</head>
<body>
<header class="navbar p-2">
    <nav class="container">
        <a style="text-decoration: none" class="link-light" href="javascript: history.go(-1)"><i class="bi bi-arrow-left-circle"></i> Volver atrás</a>
        <a style="text-decoration: none" class="link-light" href="/login"> ¿Ya estás registrado? Inicia sesión aquí</a>
    </nav>
</header>
    <div class="container">
        <div class="row mt-5 mb-5">
            <div class="col-2"></div>
            <div class="col border-1 p-5 mr-3">
                <p style="color: red"><form:errors path="user.*"/></p>
                <h1>Registro</h1>
                <form:form method="POST" action="" cssClass="form" enctype="multipart/form-data" modelAttribute="user">

                    <p class="form-group">
                        <form:label path="firstName">Nombre: </form:label>
                        <form:input cssClass="form-control" path="firstName"/>
                    </p>

                    <p class="form-group">
                        <form:label path="lastName">Apellido: </form:label>
                        <form:input cssClass="form-control" path="lastName"/>
                    </p>
                   <p class="form-group">
                       <form:label path="address.comuna.region">Región: </form:label>
                       <form:select cssClass="form-control" path="address.comuna.region">
                           <c:forEach var="region" items="${regiones}">
                               <form:option value="${region.id}"><c:out value="${region.nameRegion}"/> </form:option>
                           </c:forEach>
                       </form:select>
                    </p>
                    <p class="form-group">
                        <form:label path="address.comuna">Comuna: </form:label>
                        <form:select cssClass="form-control" path="address.comuna">
                            <c:forEach var="comuna" items="${comunas}">
                                <form:option value="${comuna.id}"> <c:out value="${comuna.nameComuna}"/> </form:option>
                            </c:forEach>
                        </form:select>
                    </p>
                    <p class="form-group">
                        <form:label path="address.nameCalle">Calle: </form:label>
                        <form:input cssClass="form-control" path="address.nameCalle" placeholder="Ejemplo: calle falsa 123"/>
                    </p>

                    <p class="form-group">
                        <form:label path="phone">Celular: </form:label>
                        <form:input cssClass="form-control" path="phone" placeholder="Ejemplo: +56912345678"/>
                    </p>
                    <c:if test="${userList.size() != 0}">
                        <p class="form-group">
                            <form:label path="rol">¿Que quieres hacer?: </form:label>
                            <form:select cssClass="form-control" path="rol">
                                <option disabled selected value="">Selecciona una</option>
                                <form:option value="1">Prestar Servicios</form:option>
                                <form:option value="2">Contratar Servicios</form:option>
                            </form:select>
                        </p>
                    </c:if>
                    <c:if test="${userList.size() == 0}">
                        <p class="form-group">
                            <form:label path="rol">¿Que quieres hacer?: </form:label>
                            <form:select cssClass="form-control" path="rol">
                                <option disabled selected value="">Selecciona una</option>
                                <form:option value="1">Prestar Servicios</form:option>
                                <form:option value="2">Contratar Servicios</form:option>
                                <form:option value="3">Administrador</form:option>
                            </form:select>
                        </p>
                    </c:if>
                    <p>
                        <form:label path="email">Email:</form:label>
                        <form:input cssClass="form-control" type="email" path="email"/>
                    </p>
                    <p>
                        <form:label path="password">Contraseña:</form:label>
                        <form:password cssClass="form-control" path="password"/>
                    </p>
                    <p>
                        <form:label path="passwordConfirmation">Confirmar contraseña:</form:label>
                        <form:password cssClass="form-control" path="passwordConfirmation"/>
                    </p>
                    <div class="mb-3">
                        <label for="formFile" class="form-label">Subir una Foto</label>
                        <input class="form-control" type="file" id="formFile" accept="image/png, image/jpeg" name="file">
                    </div>
                    <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                        <input class="btn btn-light me-md-2" type="submit" value="Registrar!"/>
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


