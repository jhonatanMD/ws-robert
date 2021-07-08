
$(document).ready(function(){

fetch('/api/transporte/listarTransportePorEmpleado', {
                method: 'GET',
                headers: {
                    "Content-type": "application/json",
                    'Access-Control-Allow-Origin': '*',
                }
                })
              .then(response => response.json())
              .then(function(data){
              console.log(data);
                if( data != null){
                    $('#table-transporte').DataTable({
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
                      data: data,
                      columns: [
                        { title: "Direccion Origen", data: "direccion_origen" },
                        { title: "Direccion Destino", data: "dirrecion_destino" },
                        { title: "Fecha Solicitud", data: "fecha_solicitud" },
                        { title: "Fecha Entrega", data: "fecha_entrega" },
                        { title: "Estado", data: "cod_estado" },
                      ]
                    });

                }else{
                 $('#table-transporte').DataTable({});
                }
              });


   fetch('/api/transporte/listarTransporte', {
       method: 'GET',
       headers: {
                    "Content-type": "application/json",
                    'Access-Control-Allow-Origin': '*',
                }
                })
              .then(response => response.json())
              .then(function(data){
              console.log(data);
                if( data != null){
                    $('#table-transporte-admin').DataTable({
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
                      data: data,
                      columns: [
                        { title: "Empleado", data: "empleado.nombre" },
                        { title: "Direccion Origen", data: "taxi.direccion_origen" },
                        { title: "Direccion Destino", data: "taxi.dirrecion_destino" },
                        { title: "Fecha Solicitud", data: "taxi.fecha_solicitud" },
                        { title: "Fecha Entrega", data: "taxi.fecha_entrega" },
                        { title: "Precio", data: "taxi.precio" },
                        { title: "Estado", data: "taxi.cod_estado" },
                        
                      ]
                    });

                }else{
                 $('#table-transporte-admin').DataTable({});
                }
              });
});



