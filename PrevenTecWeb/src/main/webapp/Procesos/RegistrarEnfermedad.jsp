<%-- 
    Document   : RegistrarEnfermedad
    Created on : 26/11/2023, 05:46:15 AM
    Author     : NomadaArlequin4
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@include file="../WEB-INF/jspf/acceso.jspf" %>             
        <script type="text/javascript">

            const varOptSintoma = funGetOptSintomas();
            var varOptTipodocumento = funGetOptTipodocumento();

            $(document).ready(function () {
                $('#ckbPersonaActual').change(function (i, e) {
                    if ($(this).is(':checked')) {
                        funBuscarPersona();
                    } else {
                        funLimpiarPersona();
                    }

                });


                funCargar();
            });

            function funCargar() {
                funLocalizacion();
                $.each(varOptSintoma.Records, function (index, value) {
                    //if (value.estado === true) {
                    if (value.Value !== "") {
                        $('#EnfermedadesGroup').append('<div class="form-check form-switch">' +
                                '<input class="form-check-input ckbClsEnfermedad" type="checkbox" id="ckbEnfermedad' + value.Value + '" value="' + value.Value + '" data-codigo="0">' +
                                '<label class="form-check-label" for="ckbEnfermedad' + value.Value + '">' + value.DisplayText + '</label>' +
                                '</div> ');
                    }
                    //}
                });
                $.each(varOptTipodocumento.Records, function (index, value) {
                    //if (value.estado === true) {
                    if (value.Value !== "") {
                        $('#cmbTipoDocumento').append('<option value="' + value.Value + '">' + value.DisplayText + '</option>');
                    }
                    //}
                });
                $('#ckbPersonaActual').change();

            }

            function funGetOptSintomas() {
                //srvTm_tipo_acreditacion
                let postData = "";
                let letReturn = [];
                $.ajax({
                    type: "GET",
                    url: conGloURL + '/Sintoma/getOption/',
                    async: false,
                    data: postData,
                    success: function (data) {
                        var varJson = JSON.parse(data);
                        letReturn = varJson;
                    }
                });
                return letReturn;
            }


            function funUsuarioCargar() {
                $.ajax({
                    type: "POST",
                    url: '../srvDocumentoentidad',
                    async: false,
                    data: {varPostAccion: 'BuscarByNumTipo',
                        varTipoDocumento: varTipoDocumento,
                        varNumDocumento: varNumDocumento
                    },
                    success: function (data) {
                        varRespuesta = JSON.parse(data);
                        return varRespuesta;
                    }
                });

            }

            function funGuardar() {
                let letTipoDocumento = $('#cmbTipoDocumento').val();
                var letNumDocumento = $('#txtNumDocumento').val().trim().toUpperCase();
                var letNombre = $('#txtNombre').val().trim().toUpperCase();
                var letApellidoPaterno = $('#txtApellidoPaterno').val().trim().toUpperCase();
                var letApellidoMaterno = $('#txtApellidoMaterno').val().trim().toUpperCase();
                //cena_cod = (cena_cod === "") ? null : cena_cod;
                var letDireccion = $('#txtDireccion').val().trim().toUpperCase();
                var letTelefono = $('#txtTelefono').val().trim().toUpperCase();
                var letEmail = $('#txtEmail').val().trim().toUpperCase();
                var letFecNacimiento = $('#txtFecNacimiento').val();
                var letLatitud = $('#txtLatitud').val();
                //--var letLongitud = $('#txtLongitud').prop('checked');
                var letLongitud = $('#txtLongitud').val();
                var letSexo = $('#cmbSexo').val();
                var letDescripcion = $('#txtObservacion').val();

                var itemsDATASintoma = [];
                $.each(varOptSintoma.Records, function (index, value) {
                    if ($('#ckbEnfermedad' + value.Value).prop('checked')) {
                        itemsDATASintoma.push({
                            sintoma_id: value.Value
                        });
                    }
                });

                $.ajax({
                    type: "POST",
                    url: conGloURL + '/Historial/',
                    async: false,
                    data: {varPostAccion: 'Insert',
                        tipodocumento_cod: letTipoDocumento,
                        numdocumento: letNumDocumento,
                        nombre: letNombre,
                        apepaterno: letApellidoPaterno,
                        apematerno: letApellidoMaterno,
                        direccion: letDireccion,
                        telefono: letTelefono,
                        email: letEmail,
                        fecnacimiento: letFecNacimiento,
                        latitud: letLatitud,
                        longitud: letLongitud,
                        sexo_id: letSexo,
                        descripcion: letDescripcion,
                        varJson: JSON.stringify(itemsDATASintoma)
                    },
                    success: function (data) {
                        var jsoncito = JSON.parse(data);
                        if (jsoncito.Result === "OK") {
                            alert("Se ingreso correctamente");
                        } else {
                            alert("" + jsoncito.Message);
                        }
                    }
                });

            }
            function funGetOptTipodocumento() {
                //srvTm_tipo_acreditacion
                let postData = "";
                let letReturn = [];
                $.ajax({
                    type: "GET",
                    url: conGloURL + '/Tipodocumento/getOption/',
                    async: false,
                    data: postData,
                    success: function (data) {
                        var varJson = JSON.parse(data);
                        letReturn = varJson;
                    }
                });

                return letReturn;
            }

            function funLocalizacion() {
                if ("geolocation" in navigator) {
                    navigator.geolocation.getCurrentPosition(
                            function (position) {
                                // Éxito: se obtuvo la ubicación
                                let latitud = position.coords.latitude;
                                let longitud = position.coords.longitude;


                                $('#txtLatitud').val(latitud);
                                $('#txtLongitud').val(longitud);

                                console.log("Latitud: " + latitud + ", Longitud: " + longitud);

                                // Aquí puedes utilizar la ubicación, por ejemplo, mostrarla en un mapa
                            },
                            function (error) {
                                // Error al obtener la ubicación
                                switch (error.code) {
                                    case error.PERMISSION_DENIED:
                                        console.log("El usuario denegó el permiso para obtener la ubicación.");
                                        break;
                                    case error.POSITION_UNAVAILABLE:
                                        console.log("La información de ubicación no está disponible.");
                                        break;
                                    case error.TIMEOUT:
                                        console.log("Se agotó el tiempo para obtener la ubicación.");
                                        break;
                                    case error.UNKNOWN_ERROR:
                                        console.log("Error desconocido al obtener la ubicación.");
                                        break;
                                }
                            }
                    );
                } else {
                    console.log("Geolocalización no está disponible en este navegador.");
                }
            }

            function funGeoLocalizacionOpen() {
                // Verificar si el navegador admite la geolocalización
                if ("geolocation" in navigator) {
                    // Función para abrir la configuración de permisos
                    function abrirConfiguracionPermisos() {
                        navigator.geolocation.getCurrentPosition(
                                function () {
                                    console.log("Permiso concedido.");
                                },
                                function (error) {
                                    if (error.code === error.PERMISSION_DENIED) {
                                        // Si se deniega el permiso, se intenta abrir la configuración de permisos
                                        var urlConfiguracion = 'chrome://settings/content/location';
                                        window.open(urlConfiguracion, '_blank');
                                    }
                                }
                        );
                    }

                    // Llamada a la función para abrir la configuración de permisos
                    abrirConfiguracionPermisos();
                } else {

                    console.log("Geolocalización no está disponible en este navegador.");
                }
            }

            function funBuscarPersona() {
                $.ajax({
                    type: "GET",
                    url: conGloURL + '/Persona/Actual',
                    async: false,
                    data: {varPostAccion: 'test'
                    },
                    success: function (data) {
                        var jsoncito = JSON.parse(data);
                        if (jsoncito.Result === "OK") {
                            $.each(jsoncito.Records, function (index, value) {
                                $('#cmbTipoDocumento').val(value.tipodocumento_cod);
                                $('#txtNumDocumento').val(value.numdocumento);
                                $('#txtNombre').val(value.nombre);
                                $('#txtApellidoPaterno').val(value.apepaterno);
                                $('#txtApellidoMaterno').val(value.apematerno);
                                $('#txtDireccion').val(value.direccion);
                                $('#txtTelefono').val(value.telefono);
                                $('#txtEmail').val(value.email);
                                $('#txtFecNacimiento').val(value.fecnacimiento);
                                $('#cmbSexo').val(value.sexo_id);
                            });
                        } else {
                            alert("" + jsoncito.Message);
                        }
                    }
                });
            }

            function funLimpiarPersona() {
                $('#cmbTipoDocumento').val("");
                $('#txtNumDocumento').val("");
                $('#txtNombre').val("");
                $('#txtApellidoPaterno').val("");
                $('#txtApellidoMaterno').val("");
                $('#txtDireccion').val("");
                $('#txtTelefono').val("");
                $('#txtEmail').val("");
                $('#txtFecNacimiento').val("");
                $('#cmbSexo').val("");
            }

        </script>       

    </head>
    <body>
        <%@include file="../WEB-INF/jspf/Menu.jspf" %>
        <br>
        <div class="container">
            <div class="row">
                <div class="col-md-12 col-12">
                    <div class="p-3 mb-2 bg-dark text-white">Registro</div>

                    <div class="form-check form-switch">
                        <input class="form-check-input ckbClsEnfermedad" type="checkbox" id="ckbPersonaActual" value="" data-codigo="0" checked >
                        <label class="form-check-label" for="ckbPersonaActual" >Fenomeno del niño</label>
                    </div> 

                </div>

                <div class="col-md-2">
                    <label for="cmbTipoDocumento" class="form-label">Tipo Documento</label>                                
                    <select id="cmbTipoDocumento" class="form-select input-sm" >

                    </select>
                </div>                       
                <div class="col-md-2">
                    <label for="txtNumDocumento" class="col-form-label">N° Documento</label>
                    <input id="txtNumDocumento" type="text" class="form-control input-sm number " value="" placeholder="" title="">                    
                </div>

                <div class="col-md-3 col-12">
                    <label for="txtNombre" class="col-form-label">Nombre</label>                    
                    <input id="txtNombre" type="text" class="form-control input-sm  " value="" placeholder="" title="">
                </div>

                <div class="col-md-3 col-xs-6">
                    <label for="txtApellidoPaterno" class="col-form-label">Ape.Paterno</label>                    
                    <input id="txtApellidoPaterno" type="text" class="form-control input-sm "  title="" placeholder="">

                </div>
                <div class="col-md-2 col-6">
                    <label for="txtApellidoMaterno" class="col-form-label">Ape.Materno</label>                    
                    <input id="txtApellidoMaterno" type="text" class="form-control input-sm "  title="" placeholder="">
                </div>
                <div class="col-md-2">
                    <label for="cmbSexo" class="form-label">Sexo</label>                                
                    <select id="cmbSexo" class="form-select input-sm">
                        <option value=""></option>                        
                        <option value="0">Femenino</option>
                        <option value="1">Masculino</option>
                    </select>
                </div>                                       

                <div class="col-md-6 col-6">
                    <label for="txtDireccion" class="col-form-label">Direccion</label>                    
                    <input id="txtDireccion" type="text" class="form-control input-sm "  title="" placeholder="">
                </div>

                <div class="col-md-6 col-6">
                    <label for="txtTelefono" class="col-form-label">Telefono</label>                    
                    <input id="txtTelefono" type="text" class="form-control input-sm "  title="" placeholder="">
                </div>

                <div class="col-md-6 col-6">
                    <label for="txtEmail" class="col-form-label">Email</label>                    
                    <input id="txtEmail" type="email" class="form-control input-sm "  title="" placeholder="">
                </div>                
                <div class="col-md-6 col-6">
                    <label for="txtFecNacimiento" class="col-form-label">Fec.Nacimiento</label>                    
                    <input id="txtFecNacimiento" type="date" class="form-control input-sm "  title="" placeholder="">
                </div>                


                <div class="col-md-6 col-6">
                    <label for="txtObservacion" class="col-form-label">Observacion</label>                    
                    <input id="txtObservacion" type="text" class="form-control input-sm "  title="" placeholder="">
                </div>                                
                <div id="EnfermedadesGroup">

                </div>


                <div class="col-md-3">
                    <label for="txtLatitud" class="col-form-label">Latitud</label>
                    <input id="txtLatitud" type="text" class="form-control input-sm number " value="" placeholder="" title="" disabled>
                </div>
                <div class="col-md-3">
                    <label for="txtLongitud" class="col-form-label">Longitud</label>
                    <input id="txtLongitud" type="text" class="form-control input-sm number " value="" placeholder="" title="" disabled>
                </div>

                <div class="col-md-1">
                    <div class="d-grid gap-2">
                        <button type="button" class="btn btn-dark" onclick="funGuardar()">Buscar</button>    
                    </div>             
                </div>                
            </div>    



        </div>        
    </body>
</html>
