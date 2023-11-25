<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%--Validacion --%>
<html>
    <head>
        <title>Matenimiento </title>        
        <%@include file="../WEB-INF/jspf/acceso.jspf" %>


        <script type="text/javascript">
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
                        codigo: {
                            title: 'Codigo',
                            width: '7%',
                            key: true,
                            create: true,
                            list: false,
                            edit: false,
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
                        abreviacion: {
                            title: 'Abreviacion',
                            width: '7%',
                            create: true,
                            list: true,
                            edit: true,
                            inputClass: 'form-control  jtMetInput clsMetLeter clsMetCapital '
                        },
                        min: {
                            title: 'Min',
                            width: '7%',
                            create: true,
                            list: true,
                            edit: true,
                            inputClass: 'form-control  jtMetInput clsMetNumero '
                        },
                        max: {
                            title: 'Max',
                            width: '7%',
                            create: true,
                            list: true,
                            edit: true,
                            inputClass: 'form-control  jtMetInput clsMetNumero '
                        },
                        tipo: {
                            title: 'Tipo',
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
                    url: conGloURL + '/Tipodocumento?',
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
                    url: conGloURL + '/Tipodocumento?' + jtParams,
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
                    url: conGloURL + '/Tipodocumento?' + jtParams,
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
                    url: conGloURL + '/Tipodocumento?' + jtParams,
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