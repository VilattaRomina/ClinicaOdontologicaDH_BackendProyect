window.addEventListener('load', function () {
    (function(){


      const url = '/pacientes';
      const settings = {
        method: 'GET'
      }

      fetch(url,settings)
      .then(response => response.json())
      .then(data => {

         for(paciente of data){

            var table = document.getElementById("pacienteTable");
            var pacienteRow =table.insertRow();
            let tr_id = 'tr_' + paciente.id;
            pacienteRow.id = tr_id;


            let deleteButton = '<button' +
                                      ' id=' + '\"' + 'btn_delete_' + paciente.id + '\"' +
                                      ' type="button" onclick="deleteBy('+paciente.id+')" class="btn btn-danger btn_delete">' +
                                      '&times' +
                                      '</button>';


            let updateButton = '<button' +
                                      ' id=' + '\"' + 'btn_id_' + paciente.id + '\"' +
                                      ' type="button" onclick="findBy('+paciente.id+')" class="btn btn-info btn_id">' +
                                      paciente.id +
                                      '</button>';


            pacienteRow.innerHTML = '<td>' + updateButton + '</td>' +
                    '<td class=\"td_nombre\">' + paciente.nombre.charAt(0).toUpperCase() + paciente.nombre.slice(1) + '</td>' +
                    '<td class=\"td_apellido\">' + paciente.apellido.charAt(0).toUpperCase() + paciente.apellido.slice(1) + '</td>' +
                    '<td class=\"td_dni\">' + paciente.dni + '</td>' +
                    '<td class=\"td_fechaDeAlta\">' + new Date(paciente.fechaDeAlta).toISOString().slice(0,10)+ '</td>' +
                    '<td class=\"td_domicilio\"> Calle ' + paciente.domicilio.calle + ' ' + paciente.domicilio.numero + '</td>' +


                    '<td>' + deleteButton + '</td>';

        };

    })
    })



    (function(){
      let pathname = window.location.pathname;
      if (pathname == "/pacientes.html") {
          document.querySelector(".nav .nav-item a:last").addClass("active");
      }
    })


    })