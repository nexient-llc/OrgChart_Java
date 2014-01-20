var editStack = 0;

$(document).ready(function() {
	
	$('#addBtn-container').css('width', $('#t1').width());
	$('#editBtn-container').css('width', $('#t1').width());
	$('#filterBtn-container').css('width', $('#t1').width());

	$('#addBtn').click(function() {
		$('#addBtn-container').fadeToggle("fast", "linear", function() {
			$('#addEntity').fadeToggle("fast", "linear");
		});
	});
	
	$('#filterBtn').click(function() {
		$('#filterBtn-container').fadeToggle("fast", "linear", function() {
			$('#filterEntity').fadeToggle("fast", "linear");
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
		$('#ViewEmps'+num).fadeToggle("fast", "linear", function() {
			$('#EditEmps'+num).fadeToggle("fast", "linear");
		});
		if(editStack <= 0){
			$('#preEditViewBar').fadeToggle("fast", "linear", function() {
				$('#postEditViewBar').fadeToggle("fast", "linear");
			});
		}
		
		editStack++;
	});
	
	$('.deleteBtn').click(function(evnt){
		var num = $(this).val();
		$.ajax({
			url : "emps",
			type : "POST",
			data: {
				_method : "delete",
				id : num
			},
			success: function(response){
				window.location.href="emps";
			},	
		});
	});
	
	$('.saveBtn').click(function(evnt) {
		
		var num = $(this).val();
		$.ajax({
			url : "emps",
			type: "POST",
			data : {
				_method : "put",
				id : num ,
				firstName : $('#empFirstName'+num).val() ,
				middleInitial : $('#empMidInitial'+num).val() ,
				lastName : $('#empLastName'+num).val() ,
				email : $('#empEmail'+num).val() ,
				skypeName : $('#empSkypeName'+num).val() ,
				departmentID : $('#deptId'+num).val() ,
				jobTitleID : $('#jobId'+num).val(),
			},
			
			success: function(response){
				window.location.href="emps";
			},
		});
		
		editStack--;
		
		if(editStack <= 0){
			removeEditViewBar();
		}
	});
	
	$('.cancelEditBtn').click(function(evnt){
		var num = $(this).val();
		$('#EditEmps'+num).fadeToggle("fast", "linear", function(){
			$('#ViewEmps'+num).fadeToggle("fast", "linear");
		});
		
		editStack--;
		
		if(editStack <= 0){
			removeEditViewBar();
		}
	});
	
	$('.sendFilterBtn').click(function(evnt){
		$.ajax({
			url : "emps/filter",
			type : "POST",
			data : {
				_method : "put",
				name : $('#filterFullName').val(),
				departmentID: $('#filterDepartment').val(),
				jobTitleID: $('#filterJobTitle').val(),
			},
			
			success: function(response){
				alert("Did I get here?");
			},
		});
	});
	
	$('#cancelFilterBtn').click(function(evnt) {
		evnt.preventDefault();
		$('#filterEntity').fadeToggle("fast", "linear", function() {
			$('#filterBtn-container').fadeToggle("fast", "linear");
		});
	});
	
	function removeEditViewBar(){
		$('#postEditViewBar').fadeToggle("fast", "linear", function() {
			$('#preEditViewBar').fadeToggle("fast", "linear");
		});
		
		editStack = 0;
	}
	
});