function disableF5(e) { if ((e.which || e.keyCode) == 116 || (e.which || e.keyCode) == 82) e.preventDefault(); };
$(document).ready(function(){

  $(document).on("keydown", disableF5);

      $("#control-mapa").click( function(){
          $("#contenedor-principal").load('mapa');
        });

      $("#control-transporte").click( function(){
        $("#contenedor-principal").load('control-transporte');
      });



});