<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%--Validacion --%>
<html>
    <head>
        <title>Matenimiento </title>        
        <%@include file="../WEB-INF/jspf/acceso.jspf" %>


        <script type="text/javascript">
            var varOptTipodocumento = funGetOptTipodocumento();
            var varOptTipousuario = funGetOptTipousuario();

            var varOptPersona = funGetOptPersona();

            $(document).ready(function () {
                $('#MantenimientoTablaContenidos').jtable({
                    title: 'Matenimiento',
                    paging: false, //Enable paging
                    sorting: false, //Enable sorting
                    useBootstrap: true,
                    actions: {
                        listAction: function (postData, jtParams) {
                            return funListJt(postData, jtParams);
                        },
                        createAction: function (postData, jtParams) {
                            return funInsertJt(postData, jtParams);
                        },
                        updateAction: function (postData, jtParams) {
                            return funUpdateJt(postData, jtParams);
                        },
                        deleteAction: function (postData, jtParams) {
                            return funDeleteJt(postData, jtParams);
                        }
                    },
                    fields: {
                        id: {
                            title: 'Id',
                            width: '7%',
                            key: true,
                            create: false,
                            list: false,
                            edit: false,
                            inputClass: 'form-control  jtMetInput clsMetNumero '
                        },
                        tipodocumento_cod: {
                            title: 'Tipodocumento_cod',
                            width: '7%',
                            options: varOptTipodocumento.Records,
                            create: true,
                            list: true,
                            edit: true,
                            inputClass: 'form-select  jtMetInput clsMetLeter clsMetCapital '
                        },
                        tipousuario_cod: {
                            title: 'tipousuario_cod',
                            width: '7%',
                            options: varOptTipousuario.Records,
                            create: true,
                            list: true,
                            edit: true,
                            inputClass: 'form-select  jtMetInput clsMetLeter clsMetCapital '
                        },

                        persona_id: {
                            title: 'persona',
                            width: '7%',
                            options: varOptPersona.Records,
                            create: true,
                            list: true,
                            edit: true,
                            inputClass: 'form-select  jtMetInput clsMetLeter clsMetCapital '
                        },

                        documento: {
                            title: 'Documento',
                            width: '7%',
                            create: true,
                            list: true,
                            edit: true,
                            inputClass: 'form-control  jtMetInput clsMetLeter clsMetCapital '
                        },
                        usuario: {
                            title: 'Usuario',
                            width: '7%',
                            create: true,
                            list: true,
                            edit: true,
                            inputClass: 'form-control  jtMetInput clsMetLeter clsMetCapital '
                        },
                        password: {
                            title: 'Password',
                            width: '7%',
                            create: true,
                            list: true,
                            edit: true,
                            type: 'password',
                            inputClass: 'form-control  jtMetInput clsMetLeter clsMetCapital '
                        },
                        nombre: {
                            title: 'Nombre',
                            width: '7%',
                            create: true,
                            list: true,
                            edit: true,
                            inputClass: 'form-control  jtMetInput clsMetLeter clsMetCapital '
                        },
                        apellidopaterno: {
                            title: 'Apellidopaterno',
                            width: '7%',
                            create: true,
                            list: true,
                            edit: true,
                            inputClass: 'form-control  jtMetInput clsMetLeter clsMetCapital '
                        },
                        apellidomaterno: {
                            title: 'Apellidomaterno',
                            width: '7%',
                            create: true,
                            list: true,
                            edit: true,
                            inputClass: 'form-control  jtMetInput clsMetLeter clsMetCapital '
                        },
                        email: {
                            title: 'Email',
                            width: '7%',
                            create: true,
                            list: true,
                            edit: true,
                            inputClass: 'form-control  jtMetInput clsMetLeter clsMetCapital '
                        },
                        telefono: {
                            title: 'Telefono',
                            width: '7%',
                            create: true,
                            list: true,
                            edit: true,
                            inputClass: 'form-control  jtMetInput clsMetLeter clsMetCapital '
                        },
                        direccion: {
                            title: 'Direccion',
                            width: '7%',
                            create: true,
                            list: true,
                            edit: true,
                            inputClass: 'form-control  jtMetInput clsMetLeter clsMetCapital '
                        },
                        estado: {
                            title: 'Estado',
                            width: '7%',
                            type: 'checkbox',
                            values: {'false': 'INACTIVO', 'true': 'ACTIVO'}, display: function (data) {
                                var estado = "";
                                if (data.record.estado === true)
                                    estado = "checked";
                                else
                                    estado = "";
                                return '<input type="checkbox"  onclick="return false" ' + estado + '>';
                            },
                            create: true,
                            list: true,
                            edit: true,
                            inputClass: 'jtMetCheckbox '
                        }
                    },
                    formSubmitting: function (event, data) {
                        $('input[type="text"]').each(function (index, value) {
                            $(value).val($(value).val().toUpperCase());
                        });
                        return data.form.validationEngine('validate');
                    },
                    formCreated: function (event, data) {
                        data.form.validationEngine();

                    },
                    recordsLoaded: function (event, data) {
                    },
                    //Dispose validation logic when form is closed
                    formClosed: function (event, data) {
                        data.form.validationEngine('hide');
                        data.form.validationEngine('detach');
                    },
                    messages: {
                        serverCommunicationError: 'Ocurrio un error mientras se comunicaba con el servidor.',
                        loadingMessage: 'Cargando...',
                        noDataAvailable: 'No hay datos disponibles!',
                        addNewRecord: 'Nuevo',
                        editRecord: 'Editar',
                        areYouSure: 'Estas seguro?',
                        deleteConfirmation: 'Este Regisstro será eliminado. Estas seguro?',
                        save: 'Guardar',
                        saving: 'Guardando',
                        cancel: 'Cancelar',
                        deleteText: 'Eliminar',
                        deleting: 'Eliminando',
                        error: 'Error',
                        close: 'Cerrar',
                        cannotLoadOptionsFor: 'No se puede cargar opciones por campo {0}',
                        pagingInfo: 'Mostrando {0}-{1} de {2}',
                        pageSizeChangeLabel: 'Cantidad',
                        gotoPageLabel: 'Ir a la pagina',
                        canNotDeletedRecords: 'No se puede eliminar {0} de {1} registros!',
                        deleteProggress: 'Eliminando {0} de {1} registros, procesando...'
                    }
                });
                $('#MantenimientoTablaContenidos').jtable('load');


            });

            function funListJt(postData, jtParams) {
                let letReturn = [];
                $.ajax({
                    type: "GET",
                    url: conGloURL + '/Usuario?',
                    async: false,
                    data: postData,
                    success: function (data) {
                        var varJson = JSON.parse(data);
                        letReturn = varJson;

                    }
                });
                return letReturn;
            }

            function funInsertJt(postData, jtParams) {
                let letReturn = [];
                $.ajax({
                    type: "POST",
                    url: conGloURL + '/Usuario?' + jtParams,
                    async: false,
                    data: postData,
                    success: function (data) {
                        var varJson = JSON.parse(data);
                        letReturn = varJson;
                    }
                });

                return letReturn;
            }

            function funUpdateJt(postData, jtParams) {
                let letReturn = [];
                $.ajax({
                    type: "PUT",
                    url: conGloURL + '/Usuario?' + jtParams,
                    async: false,
                    data: postData,
                    success: function (data) {
                        var varJson = JSON.parse(data);
                        letReturn = varJson;
                    }
                });

                return letReturn;
            }

            function funDeleteJt(postData, jtParams) {
                let letReturn = [];
                $.ajax({
                    type: "DELETE",
                    url: conGloURL + '/Usuario?' + jtParams,
                    async: false,
                    data: postData,
                    success: function (data) {
                        var varJson = JSON.parse(data);
                        letReturn = varJson;
                    }
                });

                return letReturn;
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
            function funGetOptTipousuario() {
                //srvTm_tipo_acreditacion
                let postData = "";
                let letReturn = [];
                $.ajax({
                    type: "GET",
                    url: conGloURL + '/Tipousuario/getOption/',
                    async: false,
                    data: postData,
                    success: function (data) {
                        var varJson = JSON.parse(data);
                        letReturn = varJson;
                    }
                });

                return letReturn;
            }
            
            function funGetOptPersona() {
                //srvTm_tipo_acreditacion
                let postData = "";
                let letReturn = [];
                $.ajax({
                    type: "GET",
                    url: conGloURL + '/Persona/getOption/',
                    async: false,
                    data: postData,
                    success: function (data) {
                        var varJson = JSON.parse(data);
                        letReturn = varJson;
                    }
                });

                return letReturn;
            }            

        </script>

    </head>
    <body>
        <%@include file="../WEB-INF/jspf/Menu.jspf" %>
        <br>
        <div class="container table-responsive">
            <table class="table">
                <tr>
                    <td>
                        <div id="MantenimientoTablaContenidos">
                        </div> 
                    </td>
                </tr>
            </table>
        </div>                

    </body>
</html>