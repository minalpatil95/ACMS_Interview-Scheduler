
$(document).ready(function () {

    var urlQuery = url('?');
    var eventId = urlQuery.id;
    $.ajax({
        type: 'GET',
        url: "/api/eventdetailsforrecruiter/"+eventId,
        headers:{
            'authToken':$.cookie('authToken')
        },
        success: findDetailsResponse
    });
})

function findDetailsResponse(response) {
    console.log(response);
    for (var i = 0; i < response.interviewers.length; i++) {
        $('#response').append('<div class="row">');
        $('#response').append('<div class="col-sm-2">' + response.interviewers[i].userName + '</div>');
        $('#response').append('<div class="col-sm-2">' + response.status[i] + '</div>');
        $('#response').append('</div>');
        $('#response').append('<br>');
    }
}