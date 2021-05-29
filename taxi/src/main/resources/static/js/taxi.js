$(document).ready(function(){
  Cookies.set('nombre','jhonatan');
listarSolicitudes();

});

function listarSolicitudes(){

    fetch('/api/listarTransporte', {
                method: 'GET',
                headers: {
                    "Content-type": "application/json",
                    'Access-Control-Allow-Origin': '*',
                }
                })
              .then(response => response.json())
              .then(function(data){
                if( data != null){
                     data.forEach(function(res){
                       var fecha_entrega = (res.fecha_entrega == null) ? 'Pendiente....' : res.fecha_entrega;
                       $('#body-control-transporte').html('<tr><td>'+res.direccion_origen+'</td><td>'+res.dirrecion_destino+'</td><td>'+res.fecha_solicitud+'</td><td>'+fecha_entrega+'</td><td >'+res.cod_estado+'</td></tr>')
                     })
                }
              });

}