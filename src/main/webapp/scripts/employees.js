var editStack = 0;
var cancelButtonSwitch = true;

$(document).ready(function() {
	
	$('#addBtn-container').css('width', $('#t1').width());
	$('#filterBtn-container').css('width', $('#t1').width());

	$('#addBtn').click(function() {
		$('#addBtn-container').fadeToggle("fast", "linear", function() {
			$('#addEntity').fadeToggle("fast", "linear");
		});
	});
	
	$('#filterBtn').click(function() {
		$(this).toggle();
		$('#filterEntity').fadeToggle("fast", "linear");
		$.ajax({
			type: "GET",
			url: "emps/autocomplete",
			success: function(data){
				$('#filterFullName').autocomplete({ source: $.parseJSON(data) });
			}
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
				jobTitleID : $('#jobId'+num).val()
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
			url : "emps",
			type : "GET",
			data : {
				name : $('#filterFullName').val(),
				departmentID: $('#filterDepartment').val(),
				jobTitleID: $('#filterJobTitle').val(),
			},
			
			success: function(data){
				data = jQuery.parseJSON(data);
				$('#t1 .divRow').fadeOut('fast');
				
				data.forEach(function(employeeId){
					$('#t1 #ViewEmps'+employeeId.id).fadeIn("fast");
				});
				
				if(cancelButtonSwitch == true){
					$('#cancelFilterBtn').fadeToggle("fast", "linear", function() {
						$('#resetFilterBtn').fadeToggle("fast", "linear");
					});
					cancelButtonSwitch = false;
				}
				
			},
		});
	});
	
	$('#cancelFilterBtn').click(function(evnt) {
		evnt.preventDefault();
		$('#filterEntity').fadeToggle("fast", "linear", function() {
			$('#filterBtn').toggle();
		});
	});
	
	$('#resetFilterBtn').click(function(evnt) {
		evnt.preventDefault();
		$('#t1 .divRow:not(.editRow)').fadeIn("fast");
		if(cancelButtonSwitch = false){
			$('#resetFilterBtn').fadeToggle("fast", "linear", function() {
				$('#cancelFilterBtn').fadeToggle("fast", "linear");
			});
			cancelButtonSwitch = true;
		}
		$('#filterEntity').fadeToggle("fast", "linear");
		$('#filterBtn').toggle();
	});
	
	function removeEditViewBar(){
		$('#postEditViewBar').fadeToggle("fast", "linear", function() {
			$('#preEditViewBar').fadeToggle("fast", "linear");
		});
		
		editStack = 0;
	}
	
});