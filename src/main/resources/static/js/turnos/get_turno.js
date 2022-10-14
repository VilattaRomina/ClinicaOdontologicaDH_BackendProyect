window.addEventListener('load', function () {
    (function(){


      const url = '/turnos';
      const settings = {
        method: 'GET'
      }

      fetch(url,settings)
      .then(response => response.json())
      .then(data => {

         for(turno of data){

            var table = document.getElementById("turnoTable");
            var turnoRow =table.insertRow();
            let tr_id = 'tr_' + turno.id;
            turnoRow.id = tr_id;


            let deleteButton = '<button' +
                                      ' id=' + '\"' + 'btn_delete_' + turno.id + '\"' +
                                      ' type="button" onclick="deleteBy('+turno.id+')" class="btn btn-danger btn_delete">' +
                                      '&times' +
                                      '</button>';


            let updateButton = '<button' +
                                      ' id=' + '\"' + 'btn_id_' +turno.id + '\"' +
                                      ' type="button" onclick="findBy('+turno.id+')" class="btn btn-info btn_id">' +
                                      turno.id +
                                      '</button>';


            turnoRow.innerHTML = '<td>' + updateButton + '</td>' +
                    '<td class=\"td_fecha\">' + new Date(turno.fecha).toISOString().slice(0,10)  + '</td>' +
                    '<td class=\"td_paciente_id\">' + turno.paciente.id + " " + turno.paciente.nombre.charAt(0).toUpperCase() + turno.paciente.nombre.slice(1) + " " + turno.paciente.apellido.charAt(0).toUpperCase() + turno.paciente.apellido.slice(1) + '</td>' +
                    '<td class=\"td_odontologo_id\">' +turno.odontologo.id + " " + turno.odontologo.nombre.charAt(0).toUpperCase() + turno.odontologo.nombre.slice(1) + " " + turno.odontologo.apellido.charAt(0).toUpperCase() + turno.odontologo.apellido.slice(1) + '</td>' +
                    '<td>' + deleteButton + '</td>';
        };
    })
    })
     

    (function(){
      let pathname = window.location.pathname;
      if (pathname == "/turnos.html") {
          document.querySelector(".nav .nav-item a:last").addClass("active");
      }
    })


    })