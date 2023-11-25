<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%--Validacion --%>
<html>
    <head>
        <title>Matenimiento </title>        
        <%@include file="../WEB-INF/jspf/acceso.jspf" %>
        <jsp:useBean id ="binHistorial_clinico" class="com.embarcar.app.Controler.srvHistorial_clinico" scope="session"></jsp:useBean>
        <jsp:useBean id ="binSintoma" class="com.embarcar.app.Controler.srvSintoma" scope="session"></jsp:useBean>


        <script type="text/javascript">
            var varOptHistorial_clinico =<%= binHistorial_clinico.metOptionsALL()%>;
            var varOptSintoma =<%= binSintoma.metOptionsALL()%>;
            $(document).ready(function() {
                $('#MantenimientoTablaContenidos').jtable({
                    title: 'Matenimiento',
                    paging: false, //Enable paging
                    sorting: false, //Enable sorting
                    useBootstrap: true,
                    actions: {
                        listAction: '../srvHistorial_sintomas?varPostAccion=Select',
                        createAction: '../srvHistorial_sintomas?varPostAccion=Insert',
                        updateAction: '../srvHistorial_sintomas?varPostAccion=Update',
                        deleteAction: '../srvHistorial_sintomas?varPostAccion=Delete'
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
                        historial_clinico_id: {
                            title: 'Historial_clinico_id',
                            width: '7%',
                            options: varOptHistorial_clinico.Records
,                            create: true,
                            list: true,
                            edit: true,
                            inputClass: 'form-control  jtMetSelect clsMetNumero '
                        },
                        sintoma_id: {
                            title: 'Sintoma_id',
                            width: '7%',
                            options: varOptSintoma.Records
,                            create: true,
                            list: true,
                            edit: true,
                            inputClass: 'form-control  jtMetSelect clsMetNumero '
                        },
                        validado: {
                            title: 'Validado',
                            width: '7%',
                            type: 'checkbox',
                            values: {'false': 'INACTIVO', 'true': 'ACTIVO'}, display: function(data) {
                            var estado = "";
                            if (data.record.validado === true)
                                estado = "checked";
                                else
                                estado = "";
                                return '<input type="checkbox"  onclick="return false" ' + estado + '>';
                            },
                            create: true,
                            list: true,
                            edit: true,
                            inputClass: 'form-control  jtMetCheckbox '
                        },
                        estado: {
                            title: 'Estado',
                            width: '7%',
                            type: 'checkbox',
                            values: {'false': 'INACTIVO', 'true': 'ACTIVO'}, display: function(data) {
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
                            inputClass: 'form-control  jtMetCheckbox '
                        },
                    },
                    formSubmitting: function (event, data) {
                        return data.form.validationEngine('validate');
                        $('input[type="text"]').each(function(index, value) {
                            $(value).val($(value).val().toUpperCase());
                        });
                    },
                    formCreated: function(event, data) {
                        data.form.validationEngine();

                    },
                    recordsLoaded: function(event, data) {
                    },
                    //Dispose validation logic when form is closed
                    formClosed: function(event, data) {
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