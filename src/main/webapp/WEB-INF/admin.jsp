<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="shortcut icon" href="/archivos/logos/iconoSuperMaestro.png" type="image/x'icon">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <title>Panel del Administrador</title>
</head>
<body>
<div class="container">
    <header>
        <ul class="list-group">
            <li class="list-group-item"><a class="btn btn-link" href="/">Inicio</a></li>
            <li class="list-group-item"><a class="btn btn-link" href="/admin/allpublications">Todas las publicaciones</a></li>
            <li class="list-group-item"><a class="btn btn-link" href="/admin/allusers">Todos los Usuarios</a></li>
            <li class="list-group-item"><a class="btn btn-link" href="/admin/comunas/new">Crear Comunas</a></li>
            <li class="list-group-item"><a class="btn btn-link" href="/admin/regiones/new">Crear Región</a></li>
            <li class="list-group-item"><a class="btn btn-link" href="/admin/categories/new">Crear Categoría</a></li>
            <li class="list-group-item"><a class="btn btn-link" href="/admin/comunas">Lista Comunas</a></li>
            <li class="list-group-item"><a class="btn btn-link" href="/admin/regiones">Lista Regiones</a></li>
            <li class="list-group-item"><a class="btn btn-link" href="/admin/categories">Lista Categorías</a></li>
            <li class="list-group-item"><a class="btn btn-link" href="/admin/denuncias">Lista Mensajes Denunciados</a></li>
            <li class="list-group-item"><a class="btn btn-link" href="/logout">Cerrar Sesión</a></li>
        </ul>
    </header>
</div>
</body>
</html>
