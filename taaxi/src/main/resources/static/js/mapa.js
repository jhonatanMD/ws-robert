var origen = 0;
var destino = 0;

var distancia = 0;
var direccion_origen_txt ="";
var direccion_destino_txt ="";

function initialize() {
  var txt_origen = document.getElementById('txt-origen');
  var txt_destino = document.getElementById('txt-destino');
  new google.maps.places.Autocomplete(txt_origen);
  new google.maps.places.Autocomplete(txt_destino);
}

google.maps.event.addDomListener(window, 'load', initialize);

$(document).ready(function(){

 $("#btn-buscar").click( function(){

    if($('#txt-origen').val().trim() != '' && $('#txt-destino').val().trim() != ''){
      pintarOrigen($('#txt-origen').val());
      pintarDestino($('#txt-destino').val());
      map.removeMarkers();
      map.removePolylines();
      direccion_origen_txt = $('#txt-origen').val();
      direccion_destino_txt = $('#txt-destino').val();
      $('#btn-solicitar').removeAttr("disabled");
    }else{
        alert("Ingrese campos de busqueda");
    }


});

$("#btn-solicitar").click( function(){

    if(window.confirm("Seguro que desea solicitar el transporte")){
     pintarRuta(origen,destino);
      $('#btn-solicitar').prop("disabled", true);
      $('input[type="text"]').val('');
    }




});

$("#btn-cancelar").click( function(){

    if(window.confirm("Seguro que desea cancelar el transporte")){
        map.removeMarkers();
        map.removePolylines();
        $('input[type="text"]').val('');
        $('#btn-solicitar').prop("disabled", true);

    }

});


   map = new GMaps({
    div: '#map',
    lat: -12.2399986,
    lng: -76.9655348,
  });

const buscar = new google.maps.places.Autocomplete($('#txt-origen').val());
buscar.bindTo("bounds",map);
/*
  GMaps.geolocate({
    success: function(position) {
      map.setCenter(position.coords.latitude, position.coords.longitude);
      map.addMarker({
        lat: position.coords.latitude,
        lng: position.coords.longitude
      });

      console.log(position.coords.latitude+" ; "+position.coords.longitude);
    },
    error: function(error) {
      alert('Geolización Fallida: '+error.message);
    },
    not_supported: function() {
      alert("Tu navegador no soporta la geocalización");
    },
    always: function() {
      console.log("Listo capturada la ubicación!");
    }
  });*/
/*
  GMaps.geocode({
    address: "Municipalidad de villa el salvador",
    callback: function(results, status) {
      if (status == 'OK') {
        var latlng = results[0].geometry.location;
        map.setCenter(latlng.lat(), latlng.lng());
        map.addMarker({
          lat: latlng.lat(),
          lng: latlng.lng()
        });
        console.log(latlng.lat()+" ; "+latlng.lng());
      }
    }
  });


  map.addMarker({
    lat: -12.2132173,
    lng: -76.9371281,
    icon:"https://img.icons8.com/fluent/48/000000/marker-storm.png"
  })
  map.drawRoute({
    origin: [-12.2132173, -76.9371281],
    destination: [-12.2149707, -76.9369986],
    travelMode: 'driving',
    strokeColor: '#131540',
    strokeOpacity: 0.6,
    strokeWeight: 6
  });

  map.getRoutes({
    origin: [-12.2132173, -76.9371281],
    destination: [-12.2149707, -76.9369986],
    callback: function (e) {
        var time = 0;
        var distance = 0;
        for (var i=0; i<e[0].legs.length; i++) {
            time += e[0].legs[i].duration.value;
            distance += e[0].legs[i].distance.value;
        }
        console.log(time+' Seconds');
        console.log(distance+' Meters');
    }
});

*/


    function pintarOrigen(ubicacion){
        GMaps.geocode({
            address: ubicacion,
            callback: function(results, status) {
              if (status == 'OK') {
                var latlng = results[0].geometry.location;
                map.setCenter(latlng.lat(), latlng.lng());
                map.addMarker({
                  lat: latlng.lat(),
                  lng: latlng.lng()
                });

                origen = [ latlng.lat() , latlng.lng() ]
              }
            }
      });
    }

    function pintarDestino(ubicacion){


        GMaps.geocode({
            address: ubicacion,
            callback: function(results, status) {
              if (status == 'OK') {
                var latlng = results[0].geometry.location;
                map.setCenter(latlng.lat(), latlng.lng());
                map.addMarker({
                  lat: latlng.lat(),
                  lng: latlng.lng()
                });

                destino = [ latlng.lat() , latlng.lng() ]
              }
            }
      });

    }


});








function pintarRuta(origenObtenido , destinoObtenido){

 map.drawRoute({
    origin: origenObtenido,
    destination: destinoObtenido,
    travelMode: 'driving',
    strokeColor: '#131540',
    strokeOpacity: 0.6,
    strokeWeight: 6
  });

    map.getRoutes({
        origin: origenObtenido,
        destination: destinoObtenido,
        callback: function (e) {
            var time = 0;
            var distance = 0;
            for (var i=0; i<e[0].legs.length; i++) {
                time += e[0].legs[i].duration.value;
                distance += e[0].legs[i].distance.value;
            }

            var peticion = {
                    "direccion_origen":direccion_origen_txt,
                    "dirrecion_destino":direccion_destino_txt,
                    "coordenadas_origen":origen,
                    "coordenadas_destino":destino,
                    "kilometraje":distance,
                    "cod_estado": "0"
                  }

            console.log(peticion);
            fetch('/api/transporte/guardarTransporte', {
                            method: 'POST',
                            body: JSON.stringify(peticion),
                            headers: {
                                "Content-type": "application/json",
                                'Access-Control-Allow-Origin': '*',
                            }
                            })
                          .then(response => response.json())
                          .then(function(data){

                              console.log(data);

                          });

        }
    });
}


/*function iniciarMap(){



    var coord = {lat:-12.208307 ,lng: -76.9421924};

    var map = new google.maps.Map(document.getElementById('map'),{
      zoom: 14,
      center: coord
    });


    var marker = new google.maps.Marker({
      position: coord,
      map: map
    });


    var coord2 = {lat:-12.214970,lng: -76.936997};
    var marker2 = new google.maps.Marker({
     
        position: coord2,
        map: map
     
    });



    map.drawRoute({
      origin: coord,
      destination: coord2,
      travelMode: 'walking',
      strokeColor: '#0054c2',
      strokeOpacity: 0.6,
      strokeWeight: 6
  });
    
}*/