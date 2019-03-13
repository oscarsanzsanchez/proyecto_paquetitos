function cargarTodosLosEnvios() {
    var xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            var obj = jQuery.parseJSON(this.responseText);

            var contenedor = document.getElementById("envios");

            contenedor.innerHTML = "";

            for (let index = 0; index < obj.length; index++) {
                const elem = obj[index];

                contenedor.innerHTML = contenedor.innerHTML + '<div class="row mb-3"> </div> <div class="col-12 colEnvio">' +
                    '<a href="infoEnvio.html" class="linkEnvio" onclick="almacenar(' + elem['idEnvio'] + ')">' + "<b>[ID de envío: " + elem['idEnvio'] + "]</b><br> " + elem['direccionCompleta'] + ": " + elem['nombreDestinatario'] + '</a> </div> </div>';

            }
        } else {
            var contenedor = document.getElementById("envios");
            contenedor.innerHTML = "";
            contenedor.innerHTML = '<div class="row mt-3"><div class="col-12"> <h3 class="text-center alert alert-danger"><i class="fas fa-exclamation-circle animated flash infinite"></i>&nbsp;ERROR: comprueba la conexión a Internet y que la dirección al servidor sea la correcta.</h3> </div></div>'
        }
    };
    xhttp.open("GET", "http://" + window.localStorage.getItem("serverIp") + ":8080/servidor/webapi/envios/", true);
    xhttp.send();
};


$(window).on('load', () => {
    cargarTodosLosEnvios();
});

$("#btnAct").on('click', () => {
    cargarTodosLosEnvios();
});


$("#btnConf").on('click', () => {
    window.location.href = "configuracion.html";
});