function almacenar(valor) {
    window.localStorage.setItem("idcliente", valor);
}

function guardarConfig() {

    var serverIp = document.getElementById("serverIp").value;

    function ValidateIPaddress(serverIp) {
        if (/^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/.test(serverIp)) {
            return (true)
        }
        alert("Dirección IP no válida")
        return (false)
    }


    if (ValidateIPaddress(serverIp)) {
        window.localStorage.setItem("serverIp", serverIp);

    }


}

$("#btnGuardar").on('click', () => {
    guardarConfig();
});

$(window).on('load', () => {
    document.getElementById("serverIp").value = window.localStorage.getItem("serverIp");
});