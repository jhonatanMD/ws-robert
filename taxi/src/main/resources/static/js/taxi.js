var numeroPag = 0;
$(document).ready(function(){

//listarSolicitudes();

listarSolicitudesPag(0)


  $(".next").click( function(){
    listarSolicitudesPag(++numeroPag);
    console.log(numeroPag);
  });

  $(".back").click( function(){
        if(numeroPag > 0){
        listarSolicitudesPag(--numeroPag);
            console.log(numeroPag);
        }
    });

});

function listarSolicitudes(){

    fetch('/api/transporte/listarTransportePorEmpleado', {
                method: 'GET',
                headers: {
                    "Content-type": "application/json",
                    'Access-Control-Allow-Origin': '*',
                }
                })
              .then(response => response.json())
              .then(function(data){
                if( data != null){
                    console.log(data);
                    var trama ="";
                     data.forEach(function(res){
                       var fecha_entrega = (res.fecha_entrega == null) ? 'Pendiente....' : res.fecha_entrega;
                       trama+='<tr><td>'+res.direccion_origen+'</td><td>'+res.dirrecion_destino+'</td><td>'+res.fecha_solicitud+'</td><td>'+fecha_entrega+'</td><td >'+res.cod_estado+'</td></tr>'                     })
                     $('#body-control-transporte').html(trama);

                }
              });

}

function listarSolicitudesPag(pag){

    fetch('/api/transporte/listarTransportePag/'+pag, {
                method: 'GET',
                headers: {
                    "Content-type": "application/json",
                    'Access-Control-Allow-Origin': '*',
                }
                })
              .then(response => response.json())
              .then(function(data){
                if( data != null){
                    var trama ="";
                     data.forEach(function(res){
                       var fecha_entrega = (res.fecha_entrega == null) ? 'Pendiente....' : res.fecha_entrega;
                       trama+='<tr><td>'+res.direccion_origen+'</td><td>'+res.dirrecion_destino+'</td><td>'+res.fecha_solicitud+'</td><td>'+fecha_entrega+'</td><td >'+res.cod_estado+'</td></tr>'                     })
                     $('#body-control-transporte').html(trama);
                }
              });

}

