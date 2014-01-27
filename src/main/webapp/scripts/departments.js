$(document).ready(function () {
    $('#addBtn-container').css('width', $('#t1').width());

    $('#addBtn').click(function () {
        $('#addBtn-container').fadeToggle("fast", "linear", function () {
            $('#addEntity').fadeToggle("fast", "linear");
        });
    });

    $('#cancelBtn').click(function () {
        $('#addEntity').fadeToggle("fast", "linear", function () {
        });
        $('#addBtn-container').fadeToggle("fast", "linear");
    });

    $('#loader').click(function() {
        $("#resultreturn").load("edit.jsp");

        $("resultreturn").dialog({modal: true});
    });

});
