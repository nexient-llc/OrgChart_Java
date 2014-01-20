$(document).ready(function() {
        $('#addBtn').click(function() {
                $('#addBtn-container').fadeToggle("fast", "linear", function() {
                        $('#addEntity').fadeToggle("fast", "linear");
                });
        });
        
        $('#cancelAddBtn').click(function(e){
                e.preventDefault();
                $('#addEntity').fadeToggle("fast", "linear", function() {
                        $('#addBtn-container').fadeToggle("fast", "linear");
                });
                
                $('#addEntity input[type=text]').val("");
        });
        
        $('.editJobBtn').click(function() {
                var jobNum = $(this).val();
                $('#jobRow'+jobNum).fadeToggle("fast","linear",function(){
                        $('#editJobRow'+jobNum).fadeToggle("fast","linear");
                });
                
                $('#editJobRow'+jobNum+' .editJobName').val($('#jobRow'+jobNum+' .jobName').data('value'));
                $('#editJobRow'+jobNum+' .editJobDesc').val($('#jobRow'+jobNum+' .jobDesc').data('value'));
        });
        
        $('.cancelJobEditBtn').click(function(e){
                e.preventDefault();
                var jobNum = $(this).val();
                $('#editJobRow'+jobNum).fadeToggle("fast","linear",function(){
                        $('#jobRow'+jobNum).fadeToggle("fast","linear");
                });
                
                $('#editJobRow'+jobNum+' .editJobName').val("");
                $('#editJobRow'+jobNum+' .editJobDesc').val("");
        });
        
        $('.saveJobBtn').click(function(){
                var jobNum = $(this).val();
                $.ajax({
                        type: "POST",
                        url: "jobs",
                        data: {
                                        _method: "put",
                                        id: $(this).val(),
                                        name: $('#editJobRow'+jobNum+' .editJobName').val(),
                                        description: $('#editJobRow'+jobNum+' .editJobDesc').val()
                                 },
                        success: function(){
                                window.location.href="jobs";
                        }
                });
        });
});