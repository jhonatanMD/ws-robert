var resUsuario = {};
$(document).ready(function(){
    logeo($("#login-usuario").val(),$("#login-password").val());
});


function logeo(usuario , password){

    fetch('/api/login', {
                method: 'POST',
                body: JSON.stringify({
                    "password": password,
                    "usuario": usuario
                  }),
                headers: {
                    "Content-type": "application/json",
                    'Access-Control-Allow-Origin': '*',
                }
                })
              .then(response => response.json())
              .then(function(data){

                  resUsuario = data;
                  console.log(data);

              });
}