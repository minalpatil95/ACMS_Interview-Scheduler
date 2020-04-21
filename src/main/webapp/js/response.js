$(document).ready(function () {

    $('#submit').click(function (event) {

        var selectedOption = $("input:radio[name=response]:checked").val()
        var urlQuery = url('?');
        var eventId = urlQuery.id;

        var data = {
            'eventId':eventId,
            'checked': selectedOption
        };
        console.log(data);

        $.ajax({
            type:'POST',
            url:'/api/response/'+eventId,
            data: JSON.stringify(data),
            dataType:"json",
            contentType:"text/plain",
            cache: false,
            headers:{
                'authToken':$.cookie('authToken')
            },
            success:responseRecorded
        });
    });

})

function responseRecorded() {
    alert("Response saved");
}
