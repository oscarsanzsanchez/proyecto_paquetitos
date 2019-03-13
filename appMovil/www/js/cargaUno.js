$(window).on('load', () => {
    var xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            var obj = jQuery.parseJSON(this.responseText);

            var form = document.forms["formInfo"];

            var nombre = form["nombre"];
            var dninif = form["dninif"];
            var direccion = form["direccion"];
            var numenvio = form["numEnvio"];

            nombre.value = obj['nombreDestinatario'];
            dninif.value = obj['dninif'];
            direccion.value = obj['direccionCompleta'];
            numenvio.value = obj['idEnvio'];

        }
    };
    xhttp.open("GET", "http://" + window.localStorage.getItem("serverIp") + ":8080/servidor/webapi/envios/" + window.localStorage.getItem("idcliente"), true);
    xhttp.send();
});

$('#leerqr').click(function() {
    cordova.plugins.barcodeScanner.scan(
        function(result) {
            var valor = document.getElementById("numEnvioConfirma").value = result.text;
            document.getElementById("numEnvioConfirma").value;
            if (valor == document.getElementById("numEnvio").value) {
                document.getElementById("numEnvioConfirma").style.border = "2px solid green";
            } else {
                document.getElementById("numEnvioConfirma").style.border = "2px solid red";
            }
        },
        function(error) {
            alert("Scanning failed: " + error);
        });
});


$("#btnAtras").on('click', () => {
    window.location.href = "index.html";
});