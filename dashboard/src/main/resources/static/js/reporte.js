$("#navTitleName").html("Reporte Prestamos");
$("#navDashboard").removeClass("active");
$("#navReporte").addClass("active");
obtenerReportePrestamos();

function obtenerReportePrestamos() {
    $.ajax({
      url: "api/dashboard/reporte_prestamos",
      dataType: "json",
    }).done(function (jsonData) {
        if(jsonData == null || jsonData == "")
        {
            demo.showNotification('top','right','Biblioteca', 'No se obtuvo respuesta del reporte de prestamos', 3);
        }
        else
        {
            if(jsonData.codigo == 1 && jsonData.lista_prestamos != null && jsonData.lista_prestamos.length > 0)
            {
                $.each(jsonData.lista_prestamos, function (index, element) {
                    var estado;
                    if (element.estado == 'A') {
                        estado = "Activo";
                    }
                    $('#tbodyPrestamos').append('<tr>' +
                                                    '<td>' + element.nombre_solicitante + '</td>' +
                                                    '<td>' + element.nombre_prestador + '</td>' +
                                                    '<td>' + element.cod_material + '</td>' +
                                                    '<td>' + element.isbn + '</td>' +
                                                    '<td>' + element.titulo_material + '</td>' +
                                                    '<td>' + element.fecha_prestamo + '</td>' +
                                                    '<td>' + element.fecha_pactada_devolucion + '</td>' +
                                                    '<td>' + element.fecha_devolucion + '</td>' +
                                                    '<td>' + element.correo_solicitante + '</td>' +
                                                    '<td>' + element.telefono_solicitante + '</td>' +
                                                '</tr>')
                });

                demo.showNotification('top','right','Biblioteca', "Reporte de prestamos cargado correctamente", 2);
            }else
            {
                demo.showNotification('top','right','Biblioteca', jsonData.descripcion , 3);
            }   
        }    
    }).fail(function (jq, text, err) {
        demo.showNotification('top','right','Biblioteca', 'No se pudo procesar la solicitud de reporte de prestamos', 4);
    });
}