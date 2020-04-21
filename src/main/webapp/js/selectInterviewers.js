var initData;
var theTemplate;
var displayData;

$(document).ready(function () {

    var theTemplateScript = $("#example-template").html();
    theTemplate = Handlebars.compile(theTemplateScript);
    $.ajax({
        type: 'GET',
        url: "/api/interviewerslist",
        success: findItemResponse
    });

    $('#competency').on('change',function () {
        // alert("Change to " + this.value);
        var competencySelected = $(this).val();
        var list = displayData;
        console.log(list);
        console.log(competencySelected);

        var data = {
            'competency':competencySelected,
            'data': list
        };

        $.ajax({
            type: 'POST',
            url: '/api/filter1',
            data: JSON.stringify(data),
            cache: false,
            dataType:"json",
            contentType: "text/plain",
            processData: false,
            success: function (data) {
                displayData = data;
                displayItems();
            }
        })
    });

    $('#joblevel').on('change',function () {
        // alert("Change to " + this.value);
        var joblevelSelected = $(this).val();
        var list = displayData;
        console.log(list);
        console.log(joblevelSelected);

        var data = {
            'joblevel':joblevelSelected,
            'data': list
        };

        $.ajax({
            type: 'POST',
            url: '/api/filter2',
            data: JSON.stringify(data),
            cache: false,
            // dataType:"json",
            contentType: "text/plain",
            processData: false,
            success: function (data) {
                displayData = data;
                displayItems();
            }
        })
    });

    $('#submit').click(function (event) {

        var checked = [];
        $.each($("input[name='interviewer']:checked"), function(){
            checked.push($(this).val());
        });

        var selected = [];
        $.each($(".rnd option:selected"), function(){
            if($(this).val() != "0") {
                selected.push($(this).val());
            }
        });

        var urlQuery = url('?');
        var eventId = urlQuery.id;

        var data = {
            'eventId':eventId,
            'selected': selected,
            'checked': checked
        };
        console.log(data);

        $.ajax({
            type:'POST',
            url:'/api/selectint/'+eventId,
            data: JSON.stringify(data),
            dataType:"json",
            contentType:"text/plain",
            cache: false,
            headers:{
                'authToken':$.cookie('authToken')
            },
            success:interviewersSelected
        });
    });

})

function clearFilter() {
    displayData = JSON.parse(JSON.stringify(initData));
    displayItems();
    $('#competency').val('000')
    $('#joblevel').val('000')
}


function findItemResponse(response) {
    displayData = response;
    initData = JSON.parse(JSON.stringify(response));
    //console.log(displayData);
    displayItems();
}

function displayItems() {

    console.log("done");
    console.log(displayData);

    var compiledHtml = theTemplate(displayData);
    $('#items').html(compiledHtml);

    /* for (var i = 0; i < displayData.items.length; i++) {
         console.log("hi");
         $('#answer').append('<div class="col-md-1">' + displayData.items[i].username+ '</div>');
         $('#answer').append('<div class="col-md-1">' + displayData.items[i].competency+ '</div>');
         $('#answer').append('<div class="col-md-1">' + displayData.items[i].joblevel+ '</div>');
         $('#answer').append('<br>');
     }*/
}

function interviewersSelected() {
    alert("Request Sent to Interviewers..");
}




