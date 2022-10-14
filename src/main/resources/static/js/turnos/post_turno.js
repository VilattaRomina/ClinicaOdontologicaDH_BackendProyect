window.addEventListener("load", function () {

  const formulario = document.querySelector("#add_new_turno");
  const pacienteList = document.querySelector("#paciente-list")
  const odontologoList = document.querySelector("#odontologo-list")

  formulario.addEventListener("submit", function (event) {

    const formData = {
      fecha: document.querySelector("#fecha").value,
      paciente: {
        id: document.querySelector("#paciente-list").value,
      },
      odontologo: {
        id: document.querySelector("#odontologo-list").value,
      },
    };
    console.log(formData);

    const url = "/turnos";
    const settings = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(formData),
    };

    fetch(url, settings)
      .then((response) => response.json())
      .then((data) => {

        let successAlert =
          '<div class="alert alert-success alert-dismissible">' +
          '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
          "<strong></strong> Turno agregado </div>";

        document.querySelector("#response").innerHTML = successAlert;
        document.querySelector("#response").style.display = "block";
        resetUploadForm();

      })
      
      .catch((error) => {

        let errorAlert =
          '<div class="alert alert-danger alert-dismissible">' +
          '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
          "<strong> Error intente nuevamente</strong> </div>";

        document.querySelector("#response").innerHTML = errorAlert;
        document.querySelector("#response").style.display = "block";

        resetUploadForm();
      });

   
  });

  function resetUploadForm() {
    document.querySelector("#fecha").value = "";
    document.querySelector("#paciente-list").value = "";
    document.querySelector("#odontologo-list").value = "";
  }

  (function () {
    let pathname = window.location.pathname;
    if (pathname === "/") {
      document.querySelector(".nav .nav-item a:first").addClass("active");
    } else if (pathname == "/turnos.html") {
      document.querySelector(".nav .nav-item a:last").addClass("active");
    }
  })();


  const urlPaciente = "/pacientes";
  const settingsPaciente = {
    method: "GET",
  };
  fetch(urlPaciente, settingsPaciente)
    .then((response) => response.json())
    .then((data) => {

      for (paciente of data) {
        pacienteList.appendChild(
          new Option(paciente.nombre + " " + paciente.apellido,
            paciente.id )
        );
      }
    });

  const urlOdontologo = "/odontologos";
  const settingsOdontologo = {
    method: "GET",
  };
  fetch(urlOdontologo, settingsOdontologo)
    .then((response) => response.json())
    .then((data) => {

      for (odontologo of data) {
        odontologoList.appendChild(new Option(odontologo.nombre +" " +odontologo.apellido, odontologo.id)
        );
      }
    });

});
