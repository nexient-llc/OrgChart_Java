$(document).ready(function() {
	alert('it worked');
        $('#addBtn').click(function() {
        	
                $('#addBtn-container').fadeToggle("fast", "linear", function() {
                        $('#addEntity').fadeToggle("fast", "linear");
                });
        });
        
        $('#canceladdBtn').click(function(e){
            e.preventDefault();
            $('#addEntity').fadeToggle("fast", "linear", function() {
                    $('#addBtn-container').fadeToggle("fast", "linear");
            });
            
            $('#addEntity input[type=text], #addEntity select').val("");
    });
});