function cargaRemitentes() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            var obj = jQuery.parseJSON(this.responseText);

            var ele = document.getElementById('seleccionarRemitente');

            ele.innerHTML = ele.innerHTML + '<option value="">Selecciona</option>';

            for (let index = 0; index < obj.length; index++) {
                const element = obj[index];

                ele.innerHTML = ele.innerHTML + '<option value="' + element['idCliente'] + '">' + element['nombreCliente'] + '</option>';
            }

        }
    };
    xhttp.open("GET", "http://localhost:8080/servidor/webapi/clientes/", true);
    xhttp.send();
}

function cargaDestinatarios(valor) {
    var xhttp = new XMLHttpRequest();

    var remitente = valor;
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            var obj = jQuery.parseJSON(this.responseText);

            var ele2 = document.getElementById('seleccionarDestinatario');

            for (i = ele2.length - 1; i >= 0; i--) {
                ele2.remove(i);
            }


            ele2.innerHTML = ele2.innerHTML + '<option value="">Selecciona</option>';


            for (let index = 0; index < obj.length; index++) {
                const element = obj[index];
                ele2.innerHTML = ele2.innerHTML + '<option value="' + element['idDestinatario'] + '">' + element['nombreDestinatario'] + '</option>';
            }

        }
    };
    xhttp.open("GET", "http://localhost:8080/servidor/webapi/destinatarios/porCliente/" + valor, true);
    xhttp.send();
}

function rellenarDestinatarios(valor) {
    var xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            var obj = jQuery.parseJSON(this.responseText);


            document.getElementById('nombreDestinatario').value = obj['nombreDestinatario'];
            document.getElementById('dninif').value = obj['dninif'];
            document.getElementById('codigoPostal').value = obj['codigoPostal'];
            document.getElementById('direccionCompleta').value = obj['direccionCompleta'];

        }
    };

    xhttp.open("GET", "http://localhost:8080/servidor/webapi/destinatarios/" + valor, true);
    xhttp.send();
}

// cargaRemitentes();

$('#seleccionarRemitente').change(function() {
    cargaDestinatarios($(this).val());
});

$('#seleccionarDestinatario').change(function() {
    rellenarDestinatarios($(this).val());
});

$(window).on('load', () => {
    cargaRemitentes();
});