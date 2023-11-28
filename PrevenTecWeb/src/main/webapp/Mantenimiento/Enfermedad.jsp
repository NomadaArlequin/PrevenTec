<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%--Validacion --%>
<html>
    <head>
        <title>Matenimiento </title>        
        <%@include file="../WEB-INF/jspf/acceso.jspf" %>


        <script type="text/javascript">
            var varOptSintoma = funGetOptEnfermedad();

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
                        Emfermedad_sintomas: {
                            title: '',
                            width: '3%',
                            sorting: false,
                            edit: false,
                            create: false,
                            display: function (DataHijo) {
                                //Create an image that will be used to open child table
                                var $img = $('<img class="divSvgimg" src="../assets/img/svg/mail-conf.svg" title="Edit Permisos" />');
                                //Open child table when user clicks the image
                                $img.click(function () {
                                    $('#MantenimientoTablaContenidos').jtable('openChildTable',
                                            $img.closest('tr'), //Parent row
                                            {
                                                title: ' Sintomas',
                                                actions: {
                                                    listAction: function (postData, jtParams) {
                                                        let enfermedad_id = DataHijo.record.id;
                                                        return funListJtChildren(postData, jtParams, enfermedad_id);
                                                    },
                                                    createAction: function (postData, jtParams) {
                                                        let enfermedad_id = DataHijo.record.id;
                                                        return funInsertJtChildren(postData, jtParams, enfermedad_id);
                                                    },
                                                    updateAction: function (postData, jtParams) {
                                                        let enfermedad_id = DataHijo.record.id;
                                                        return funUpdateJtChildren(postData, jtParams,enfermedad_id);
                                                    },
                                                    deleteAction: function (postData, jtParams) {
                                                        return funDeleteJtChildren(postData, jtParams);
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
                                                    sintoma_id: {
                                                        title: 'Sintoma_id',
                                                        width: '7%',
                                                        options: varOptSintoma.Records,
                                                        create: true,
                                                        list: true,
                                                        edit: true,
                                                        inputClass: 'form-control  jtMetSelect clsMetNumero '
                                                    },
                                                    descripcion: {
                                                        title: 'Descripcion',
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
                                                formCreated: function (event, data) {

                                                },
                                                recordsLoaded: function (event, data) {
                                                }
                                            }, function (data) { //opened handler
                                        data.childTable.jtable('load');
                                    });
                                });
                                //Return image to show on the person row
                                return $img;
                            }

                        },
                        id: {
                            title: 'Id',
                            width: '7%',
                            key: true,
                            create: false,
                            list: false,
                            edit: false,
                            inputClass: 'form-control  jtMetInput clsMetNumero '
                        },
                        nombre: {
                            title: 'Nombre',
                            width: '7%',
                            create: true,
                            list: true,
                            edit: true,
                            inputClass: 'form-control  jtMetInput clsMetLeter clsMetCapital '
                        },
                        descripcion: {
                            title: 'Descripcion',
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
                            inputClass: ' jtMetCheckbox '
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
                    url: conGloURL + '/Enfermedad?',
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
                    url: conGloURL + '/Enfermedad?' + jtParams,
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
                    url: conGloURL + '/Enfermedad?' + jtParams,
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
                    url: conGloURL + '/Enfermedad?' + jtParams,
                    async: false,
                    data: postData,
                    success: function (data) {
                        var varJson = JSON.parse(data);
                        letReturn = varJson;
                    }
                });
                return letReturn;
            }

            ///*************************************************
            function funListJtChildren(postData, jtParams, enfermedad_id) {
                let letReturn = [];
                $.ajax({
                    type: "GET",
                    url: conGloURL + '/Enfermedad_sintoma/enfermedad?enfermedad_id=' + enfermedad_id,
                    async: false,
                    data: postData,
                    success: function (data) {
                        var varJson = JSON.parse(data);
                        letReturn = varJson;
                    }
                });
                return letReturn;
            }

            function funInsertJtChildren(postData, jtParams, enfermedad_id) {
                let letReturn = [];
                $.ajax({
                    type: "POST",
                    url: conGloURL + '/Enfermedad_sintoma?' + jtParams + '&enfermedad_id=' + enfermedad_id,
                    async: false,
                    data: postData,
                    success: function (data) {
                        var varJson = JSON.parse(data);
                        letReturn = varJson;
                    }
                });
                return letReturn;
            }

            function funUpdateJtChildren(postData, jtParams) {
                let letReturn = [];
                $.ajax({
                    type: "PUT",
                    url: conGloURL + '/Enfermedad_sintoma?' + jtParams + '&enfermedad_id=' + enfermedad_id,
                    async: false,
                    data: postData,
                    success: function (data) {
                        var varJson = JSON.parse(data);
                        letReturn = varJson;
                    }
                });
                return letReturn;
            }

            function funDeleteJtChildren(postData, jtParams) {
                let letReturn = [];
                $.ajax({
                    type: "DELETE",
                    url: conGloURL + '/Enfermedad_sintoma?' + jtParams,
                    async: false,
                    data: postData,
                    success: function (data) {
                        var varJson = JSON.parse(data);
                        letReturn = varJson;
                    }
                });
                return letReturn;
            }

            function funGetOptEnfermedad() {
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
            

        function ActualizarSintomasEnfermedades() {
            var formData = new FormData();
            fetch(conGloURL + '/login/Crear', {
                method: 'POST',
                body: formData
            })
                    .then(resp => resp.json())
                    .then(data => {
                        console.log(data.Result);
                    })
                    .catch(e => console.log(e));


        }
            
            
        </script>

    </head>
    <body>
        <%@include file="../WEB-INF/jspf/Menu.jspf" %>
        <br>
        <div class="container">
             <div class="col-md-1">
                    <div class="d-grid gap-2">
                        <button type="button" class="btn btn-dark" onclick="ActualizarSintomasEnfermedades()">Actualizar</button>    
                    </div>             
                </div>  
        </div>
        
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