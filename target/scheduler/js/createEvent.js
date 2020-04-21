$(document).ready(function () {

    $('#eventType').on('change',function () {
        // alert("Change to " + this.value);
        var eventSelect = $(this).val();
        console.log(eventSelect);
        if(eventSelect == "Hiring") {
            $("#candidateNumber").show();
            $("#level").hide();
        } else if(eventSelect == "Onsite"){
            $("#level").show();
            $("#candidateNumber").hide();
        }else{
            $("#candidateNumber").hide();
            $("#level").hide();
        }

    });

    $('#round').change(function() {
          //alert( this.value );
          var round = $('#round').val();
          console.log(round);
        $('#competency').append('<br>');
        for (var i = 0; i < round; i++) {
            $('#competency').append('<div class="row">');
            $('#competency').append('<label class="col-md-4 control-label">Competency for Round' + (i + 1) + ' :</label>' +
                '<label class="checkbox-inline" for="technical"><input type="checkbox" name="comp" id="technical" value="Technical"><b>Technical</b></label>' +
                '<label class="checkbox-inline" for="hr"><input type="checkbox" name="comp" id="hr" value="HR"><b>HR</b></label>' +
                '<label class="checkbox-inline" for="mr"><input type="checkbox" name="comp" id="mr" value="MR"><b>MR</b></label>');
            $('#competency').append('</div>');
            $('#competency').append('<br><br>');
        }
    });

    $('#next').click(function (event) {

        console.log("clicked");

    });

    function nextPage(id) {
        var id = id;
        console.log(id);
    }

    $('#logout').click(function () {
        $.removeCookie("authToken", {path: '/'});
        window.location.replace("/");
    });
})