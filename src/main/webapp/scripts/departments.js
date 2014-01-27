$(document).ready(function() {
	$('#addBtn-container').css('width', $('#t1').width());
	$('#editBtn-container').css('width', $('#t1').width());

	$('#addBtn').click(function() {
		$('#addBtn-container').fadeToggle("fast", "linear", function() {
			$('#addEntity').fadeToggle("fast", "linear");
		});
	});
	
	$('#cancelBtn').click(function(evnt) {
		evnt.preventDefault();
		$('#addEntity').fadeToggle("fast", "linear", function() {
			$('#addBtn-container').fadeToggle("fast", "linear");
		});
	});
	
	$('.editBtn').click(function(evnt) {
		var num = $(this).val();
		evnt.preventDefault();
		$('#ViewDepts'+num).fadeToggle("fast", "linear", function() {
			$('#EditDepts'+num).fadeToggle("fast", "linear");
		});
	});
	
	$('.saveBtn').click(function(evnt) {
		
		var num = $(this).val();
		$.ajax({
			url : "depts",
			type: "POST",
			data : {
				_method : "put",
				id : num ,
				name : $('#deptName'+num).val() ,
				parentID : $('#deptParentId'+num).val()
			},
			
			success: function(response){
				window.location.href="depts";
			},	
		});

	});
	
	$('.cancelEditBtn').click(function(evnt){
		var num = $(this).val();
		$('#EditDepts'+num).fadeToggle("fast", "linear", function(){
			$('#ViewDepts'+num).fadeToggle("fast", "linear");
		});
	});
	
	$('.deleteBtn').click(function(evnt){
		var num = $(this).val();
		$.ajax({
			url : "depts/"+num,
			type : "DELETE",
			success: function(response){
				window.location.href="depts";
			},	
		});
	});
});
