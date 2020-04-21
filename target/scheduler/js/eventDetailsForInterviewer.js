
$(document).ready(function () {

    var urlQuery = url('?');
    var eventId = urlQuery.id;
    $.ajax({
        type: 'GET',
        url: "/api/eventdetailsforinterviewer/"+eventId,
        headers:{
            'authToken':$.cookie('authToken')
        },
        success: findDetailsResponse
    });
})

function findDetailsResponse(response) {
    console.log(response);
    $('#response').append('<div>' +response.status+ '</div>');
}