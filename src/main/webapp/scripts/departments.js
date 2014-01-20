$(document).ready(function() {
        $('#addBtn').click(function() {
                $(this).parent().fadeToggle("fast", "linear", function() {
                        $('#addEntity').fadeToggle("fast", "linear");
                });
        });
        
        $('#cancelAddBtn').click(function(e){
                e.preventDefault();
                $('#addEntity').fadeToggle("fast", "linear", function() {
                        $('#addBtn-container').fadeToggle("fast", "linear");
                        document.getElementById("addForm").reset();
                });
                
                $('#addEntity input[type=text], #addEntity select').val("");
        });
        
        $('#editDeptBtn').click(function() {
                var deptNum = $(this).val();
                $('#deptRow'+deptNum).fadeToggle("fast","linear",function(){
                        $('#editDeptRow'+deptNum).fadeToggle("fast","linear");
                });
                
                $('#editDeptRow'+deptNum+' .editDeptName').val($('#deptRow'+deptNum+' .deptName').data('value'));
                $('#editDeptRow'+deptNum+' .editDeptParent').val($('#deptRow'+deptNum+' .deptParent').data('value'));
        });
        
        $('.cancelDeptEditBtn').click(function(e){
                e.preventDefault();
                var deptNum = $(this).val();
                $('#editDeptRow'+deptNum).fadeToggle("fast","linear",function(){
                        $('#deptRow'+deptNum).fadeToggle("fast","linear");
                });
                
                $('#editDeptRow'+deptNum+' .editDeptName').val("");
                $('#editDeptRow'+deptNum+' .editDeptParent').val("");
        });
        
        $('.removeDeptBtn').click(function(){        
                $.ajax({
                        type: "POST",
                        url: "depts",
                        data: {
                                _method: "delete",
                                id: $(this).val()
                        },
                        success: function(){
                                window.location.href="depts";
                        }
                });
        });
        
        $('.saveDeptBtn').click(function(){
                var deptNum = $(this).val();
                var deptName = $('#editDeptRow'+deptNum+' .editDeptName').val();
                var parentId = $('#editDeptRow'+deptNum+' .editDeptParent').val();
                
                $.ajax({
                        type: "POST",
                        url: "depts",
                        data: {
                                        _method: "put",
                                        id: deptNum,
                                        name: deptName,
                                        parentId: parentId
                                 },
                        success: function(){
                                window.location.href="depts";
                        }
                });
        });
});