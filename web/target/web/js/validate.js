function validateForm() {

    var error = "";
    var form = document.forms["formularioCreacionEnvio"];

    var nombre = form["nombreDestinatario"].value;
    var estadoInicial = form["estadoInicial"].value;
    var dninif = form["dninif"].value;
    var codpostal = form["codigoPostal"].value;
    var direccion = form["direccionCompleta"].value;
    var idcliente = form["seleccionarRemitente"].value;
    var iddestinatario = form["seleccionarDestinatario"].value;

    if (nombre == "" || nombre == null) {
        error += "- Campo nombre incompleto \n";
    }
    if (dninif == "" || dninif == null) {
        error += "- Campo DNI/NIF incompleto \n";
    }
    if (codpostal == "" || codpostal == null) {
        error += "- Campo codigo postal incompleto \n";
    }
    if (direccion == "" || direccion == null) {
        error += "- Campo direccion incompleto \n";
    }
    if (estadoInicial == "" || estadoInicial == null) {
        error += "- Campo estado inicial incompleto \n";
    }
    if (idcliente == "" || idcliente == null) {
        error += "- No se ha seleccionado un remitente \n";
    }
    if (iddestinatario == "" || iddestinatario == null) {
        error += "- No se ha seleccionado un destinatario \n";
    }

    if (error.length <= 0) {
        document.getElementById('errores').style.display = "none";
        var objs = {
            codigoPostal: codpostal,
            direccionCompleta: direccion,
            dninif: dninif,
            idEstado: estadoInicial,
            idCliente: idcliente,
            nombreDestinatario: nombre,
            numIntentosEntrega: 0
        }
        var myJSON = JSON.stringify(objs);


        var xhttp = new XMLHttpRequest();

        xhttp.open("POST", "http://localhost:8080/servidor/webapi/envios/", true);
        xhttp.setRequestHeader("Content-type", "application/json");
        xhttp.send(myJSON);



        xhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 201) {
                document.getElementById('alertaSuccesful').style.display = "block";
                document.getElementById('alertaFail').style.display = "none";
            } else {
                document.getElementById('alertaFail').style.display = "block";
                document.getElementById('alertaSuccesful').style.display = "none";
            }
        };


    } else {
        document.getElementById('errores').innerText = error;
        document.getElementById('errores').style.display = "block";
        return false;
    }




}

$('#btnBorrar').on('click', () => {
    document.getElementById('alertaFail').style.display = "none";
    document.getElementById('alertaSuccesful').style.display = "none";
    document.getElementById('errores').innerText = '';
    document.getElementById('errores').style.display = "none";
});

$("#btnEnviar").on('click', () => {
    validateForm();
});