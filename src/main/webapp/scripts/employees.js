$(document).ready(function() {
	$("#addBtn").click(function() {
		popupDialog($("#empEditDialog"));
		$("#id").val('');
		$("#deleteId").val('');
		$("#firstName").val('');
		$("#lastName").val('');
		$("#skypeName").val('');
		$("#email").val('');
		$("#isManager").prop('checked',false);
		$("#manager").val('');
		$("#dangerZone").css('display','none');
		$("#editForm").attr("action", "employees/add");

	});
	
	$(".empEditBtn").click(function() {
		
		/* Requests JSON representing an employee
		 * based on the passed in id.
		 */
		$.ajax({
			url: "employees/" + $(this).data("id") + "/json",
			type: "GET",
			dataType: "json"
		
		}).done(function(employee){
			/* Clear state */
			$("#managerError").html("");
			$("#editSubmit").attr("disabled", false);
			 
			/* Pre-fill edit forms. */
			$("#id").val(employee.id);
			$("#deleteId").val(employee.id);
			$("#firstName").val(employee.firstName);
			$("#lastName").val(employee.lastName);
			$("#lastName").val(employee.lastName);
			$("#skypeName").val(employee.skypeName);
			$("#email").val(employee.email);
			$("#isManager").prop('checked', employee.isManager	);
			$("#dangerZone").css('display', 'inline');
			$("#editForm").attr("action", "employees/edit");
			
			if(employee.manager != undefined && employee.manager.id != undefined)
				$("#manager").val(employee.manager.id);
			else
				$("#manager").val("");
			
			/* Select appropriate Job Title*/
			selectByValue($('#jobTitle'), employee.jobTitle.id);
			
			/* Select appropriate Department */
			selectByValue($('#department'), employee.department.id);
			
			/* Check Is Manager box if they're a manager. Make sure
			 * it's unchecked if they're not. */
			if(employee.isManager == true) {
				$('#isManager').prop('checked',true);
			} else {
				$('#isManager').prop('checked',false);
			}
			
			/* Define onChange handler for managed by field to validate
			 * against them managing themselves.
			 */
			
			$("#manager").change(function(){
				var ourId = $("#id").val();
				if(ourId == $(this).val()) {
					$(this).css({'background-color' : 'light red'});
					$("#managerError").html("You cannot manage yourself<br>");
					$("#editSubmit").attr("disabled", true);
				} else {
					$(this).css({'background-color' : 'white'});
					$("#managerError").html("");
					$("#editSubmit").attr("disabled",false);
				}
				
			});
			
			popupDialog($("#empEditDialog"));
		});
	});
	
	/* Autocomplete for filtering */
	$("#empFilterString").keyup(function(){
		
		var search = $("#empFilterString").val();
		var acBox = $("#autoComplete");
		acBox.empty();
		
		/* Hide autocomplete suggestions if there
		 * is no text.
		 */
		if(search.length == 0) {
			acBox.css("display", "none");
			return; // In fact, no need to query.
		}
		
		$.ajax({
			url: "employees/getBy/name/"+search+"/json",
			type: "GET",
			dataType: "json"
		
		}).done(function(employees){
			acBox.empty();
			/* No results found */
			if(employees == undefined || employees.length == 0) {
				acBox.css("display", none);
				return;
			}
		
			acBox.css("display", "inline");
			acBox.attr("size", employees.length+1);
			for(var i = 0; i < employees.length; ++i) {
				acBox
		          .append($('<option>', { value : employees[i].id })
		          .text("" + employees[i].id + " - " + employees[i].firstName + 
		        		" " + employees[i].lastName)); 
			}
		});
	
	});
	
});