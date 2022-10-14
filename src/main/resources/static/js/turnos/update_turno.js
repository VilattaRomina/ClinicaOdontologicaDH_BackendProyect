window.addEventListener('load', function () {

    const formulario = document.querySelector('#update_turno_form');
    const pacienteList = document.querySelector("#paciente-list")
    const odontologoList = document.querySelector("#odontologo-list")
    formulario.addEventListener('submit', function (event) {

        const formData = {
                        id: $("#turno_id").val(),
                                   fecha: $('#fecha').val(),
                                   paciente: {
                                     id:  $('#paciente-list').val(),
                                   },
                                   odontologo: {
                                     id:$('#odontologo-list').val(),
                                   }

        };

        const url = '/turnos';
        const settings = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }
          fetch(url,settings)
          .then(response => response.json())
            location.reload();

    })

    const urlPaciente = '/pacientes';
    const settingsPaciente = {
      method: 'GET'
    }
    fetch(urlPaciente,settingsPaciente)
    .then(response => response.json())
    .then(data => {

       for(paciente of data){
            pacienteList.appendChild(new Option(paciente.id + " " + paciente.nombre + " " + paciente.apellido, `${paciente.id}`))  
      };
    })
    
    const urlOdontologo = '/odontologos';
    const settingsOdontologo = {
      method: 'GET'
    }
    fetch(urlOdontologo,settingsOdontologo)
    .then(response => response.json())
    .then(data => {

       for(odontologo of data){
            odontologoList.appendChild(new Option(odontologo.id + " " + odontologo.nombre + " " + odontologo.apellido, `${odontologo.id}`))  
      };
    })
 })



    function findBy(id) {
          const url = '/turnos'+"/"+id;
          const settings = {
              method: 'GET'
          }
          fetch(url,settings)
          .then(response => response.json())
          .then(data => {
          console.log(data)
              let turno = data;
                  document.querySelector('#turno_id').value = turno.id;
                  document.querySelector('#fecha').value = turno.fecha;
                  document.querySelector('#paciente-list').value = turno.paciente.id;
                  document.querySelector('#odontologo-list').value = turno.odontologo.id;

              //el formulario por default esta oculto y al editar se habilita
              document.querySelector('#div_turno_updating').style.display = "block";
          }).catch(error => {
              alert("Error: " + error);
          })
      }