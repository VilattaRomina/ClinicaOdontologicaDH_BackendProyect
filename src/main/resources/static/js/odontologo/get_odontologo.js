$(document).ready(function(){
    (function(){
        $.ajax({
            type : "GET",
            url : "/odontologos",
            success: function(response){
              $.each(response, (i, odontologo) => {
              console.log("desde get " + odontologo.matricula)
              console.log("desde get " + odontologo)
                let get_More_Info_Btn = '<button' +
                                            ' id=' + '\"' + 'btn_id_' + odontologo.id + '\"' +
                                            ' type="button" class="btn btn-info btn_id">' + 
                                            odontologo.id +
                                            '</button>';
                 let deleteButton = '<button' +
                                                      ' id=' + '\"' + 'btn_delete_' + odontologo.id + '\"' +
                                                      ' type="button" onclick="deleteBy('+odontologo.id+')" class="btn btn-danger btn_delete">' +
                                                      '&times' +
                                                      '</button>';
                let tr_id = 'tr_' + odontologo.id;
                let odontologoRow = '<tr id=\"' + tr_id + "\"" + '>' +
                          '<td>' + get_More_Info_Btn + '</td>' +
                          '<td class=\"td_first_name\">' + normalizarString(odontologo.nombre) + '</td>' +
                          '<td class=\"td_last_name\">' + normalizarString(odontologo.apellido) + '</td>' +
                          '<td class=\"td_matricula\">' + odontologo.matricula + '</td>' +
                            '<td>' + deleteButton + '</td>' +

                          '</tr>';                
                $('#odontologoTable tbody').append(odontologoRow);
              });
            },
            error : function(e) {
              alert("ERROR: ", e);
              console.log("ERROR: ", e);
            }
        });
    })();     
    
    function normalizarString(nombre) {
      let nombreNormalizado = nombre.charAt(0).toUpperCase() + nombre.slice(1);
      
        return nombreNormalizado
    }
    
    (function(){
        let pathname = window.location.pathname;
        if (pathname == "/odontologos.html") {
            $(".nav .nav-item a:last").addClass("active");
        }
    })();
});