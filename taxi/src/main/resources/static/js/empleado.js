var indice = 0;
var tabla;

$(document).ready(function(){

fetch('/api/empleado/listaPorEmpresa', {
                method: 'GET',
                headers: {
                    "Content-type": "application/json",
                    'Access-Control-Allow-Origin': '*',
                }
                })
              .then(response => response.json())
              .then(function(res){
                if( res != null){


                  tabla =  $('#table-empleado').DataTable({
                    language: {
                            "decimal": "",
                            "emptyTable": "No hay información",
                            "info": "Mostrando _START_ a _END_ de _TOTAL_ Entradas",
                            "infoEmpty": "Mostrando 0 to 0 of 0 Entradas",
                            "infoFiltered": "(Filtrado de _MAX_ total entradas)",
                            "infoPostFix": "",
                            "thousands": ",",
                            "lengthMenu": "Mostrar _MENU_ Entradas",
                            "loadingRecords": "Cargando...",
                            "processing": "Procesando...",
                            "search": "Buscar:",
                            "zeroRecords": "Sin resultados encontrados",
                            "paginate": {
                                "first": "Primero",
                                "last": "Ultimo",
                                "next": "Siguiente",
                                "previous": "Anterior"
                            }
                        },
                      paging: true,
                      searching: true,
                      info: true,
                      data: res,
                      columns: [
                        { title: "Nombre", data: "empleado.nombre" },
                        { title: "Apellido Paterno", data: "empleado.ape_pat" },
                        { title: "Apellido Materno", data: "empleado.ape_mat" },
                        { title: "Dni", data: "empleado.dni" },
                        { title: "Edad", data: "empleado.edad" },
                        { title: "Correo", data: "empleado.email" },
                        { title: "Cargo", data: "cargo.cargo" },
                        {"defaultContent": "<div class='text-center'><div class='btn-group'><button class='btn btn-info btn-sm btnEditar'>Editar</button><button class='btn btn-danger btn-sm btnBorrar'>Borrar</button></div></div>"}
                      ]
                    });

                }else{
                 $('#table-empleado').DataTable({});
                }
              });






});

