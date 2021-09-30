// let regiones = {};
// function cargarRegiones(listaRegiones){
//     regiones = listaRegiones;
//     console.log(regiones);
//     let json = JSON.stringify(regiones);
//     console.log(json);
//     for (const region in regiones) {
//         console.log(region);
//     }
// }
//

$(document).ready(function (){
    leerNotificaciones();
    $("#region").change(function(){
        var region = $(this).find(":selected").val();
        var json = {
            'id':region
        };
        $.ajax({
            type:"post",
            contentType: "application/json",
            url:"/cargarComunas",
            headers: {"X-CSRF-TOKEN": $("input[name='_csrf']").val()},
            data: JSON.stringify(json),
            dataType: 'json',
            cache: false,
            timeout:600000,
            success:function (data){
                var html = "";
                var len = data.length;
                html += '<option selected></option>';
                for (let i = 0; i < len; i++) {
                    html += '<option value="'+data[i].id+'"> '+data[i].nameComuna+' </option>'
                }
                html += '</option>';
                $('#comunas').html(html);

            },
            error:function (e){
                console.log(e);
            }
        });

    });
});



function leerNotificaciones (){
    $.ajax({
        type: 'get',
        url: '/notificaciones/leer'
    })
    .done(function (respuesta){
        let notificaciones = document.getElementById("notificaciones")
        let contador = notificaciones.querySelector("button");
        contador.innerHTML = respuesta;
    })
    .fail(function (respuesta){
        console.log("error" + respuesta);
    })

}

setInterval(leerNotificaciones, 5000);


// console.log(regiones[1]['comunas'][2]);
// console.log(regiones[1]['comunas'][3]);
// console.log(regiones[1]['comunas'][4]);






