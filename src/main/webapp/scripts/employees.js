var ajaxResponse;

var nextPage = 1;
var previousPage = 0;
var currentPage = 0;

$(document).ready(function() {
	
	
	
	$("#cancelButton").click(function() {
		$("#addEntity").toggle();
		$("#addBtn-container").toggle();
	});
	
	$("#toggleFilter").click(function() {
		$("#filterContainer").toggle();
	});
	
	$("#cancelFilter").click(function() {
		$("#filterContainer").toggle();
	});
	
	
	$("#newEmployeeForm").submit(function() {
		var id = -1;
		var email = $("#newEmail").val();
		var skype = $("#newSkypeName").val();
		
		if($("#addDepartmentSelect").val() == "" || $("#addJobTitleSelect").val() == "") {
			alert("Department and Job Title are required.");
			return false;
		}
		
		validateEmailAndSkype(id, email, skype, "add");
		
//		if(ajaxResponse) {
//			var dataString = $("#newEmployeeForm").serialize();
//			console.log(dataString);
//			$.ajax({
//				type: "POST",
//				url: "newEmployee",
//				data: dataString,
//				dataType: "text",
//				async: false,
//				success: function(data) {
//					var employeeData = $.parseJSON(data);
//					
//					var tbody = $("#empTable > tbody");
//					tbody.append("<tr id='empRow" + employeeData['id'] + "'></tr>");
//					
//					var tr = $("#empRow" + employeeData['id']);
//					tr.append("<td>" + employeeData['firstName'] + " " + employeeData['middleInitial'] + ". " + employeeData['lastName'] + "</td>");
//					tr.append("<td>" + employeeData['departmentName'] + "</td>");
//					tr.append("<td>" + employeeData['jobTitleName'] + "</td>");
//					tr.append("<td><button class=\"pure-button\" onclick=\"openEditForm('" + employeeData['id'] + "', '" + employeeData['firstName'] + "', '" + employeeData['middleInitial'] + "', '" + employeeData['lastName'] + "', '" + employeeData['departmentId'] + "', '" + employeeData['email'] + "', '" + employeeData['skype'] + "', '" + employeeData['jobTitleId'] + "')\">Edit</button></td>");
//					tr.append("<td><button class=\"pure-button\" onclick=\"removeEmployee('" + employeeData['id'] + "')\">Delete</button></td>");
//					
//					$("#cancelButton").click();
//				}
//			});
//		}
		return ajaxResponse;
		//reset ajaxResponse and return
//		ajaxResponse = false;
//		return false;
	});
	
	$("#editForm").submit(function() {
		var id = $("#editEmpId").val();
		var email = $("#editEmail").val();
		var skype = $("#editSkype").val();
		
		
		if($("#editDepartmentSelect").val() == "" || $("#editJobTitleSelect").val() == "") {
			alert("Department and Job Title are required.");
			return false;
		}
		
		validateEmailAndSkype(id, email, skype, "edit");
		
		return ajaxResponse;
	});
	
	
	$('#addBtn').click(function() {
		$('#addBtn-container').toggle();
		$('#addEntity').toggle();
	});
	
	
	
	$("input#autocompleteText").autocomplete({
		source: function(request, response) {
			$.ajax({
				delay: 0,
				type: "GET",
				url: "suggestions/" + $("#autocompleteText").val(),
				dataType: "text",
				success: function(data) {
					var suggestions = data.split(",");
					suggestions.pop();  // remove the empty element at the end
					response(suggestions);
				}
			});
		}
	});
	
});



function openEditForm(id, firstName, middle, lastName, dept, email, skype, job) {
	$("#editContainer").toggle();
	
	$("#editEmpId").val(id);
	$("#editFirstName").val(firstName);
	$("#editMiddleInitial").val(middle);
	$("#editLastName").val(lastName);
	$("#editDepartmentSelect").val(dept);
	$("#editEmail").val(email);
	$("#editSkype").val(skype);
	$("#editJobTitleSelect").val(job);
}

function removeEmployee(id) {
	if(confirm("You sure you want to delete the employee?") == true) {
		$.ajax({
			type: "POST",
			url: "removeEmployee/" + id,
			dataType: "text",
			success: function(data) {
				$("#empRow" + id).remove();
			}
		});
	}
}

function reenableEmployee(id) {
	if(confirm("You sure you want to re-enable the employee?") == true) {
		$.ajax({
			type: "POST",
			url: "reenableEmployee/" + id,
			dataType: "text",
			success: function(data) {
				$("#empRow" + id).remove();
			}
		});
	}
}

function validateEmailAndSkype(id, email, skype, addOrEdit) {
	$.ajax({
		type: "GET",
		url: "checkEmailAndSkype",
		data: {id: id, email: email, skypeName: skype, addOrEdit: addOrEdit},
		dataType: "text",
		async: false,
		success: function(data) {
			if(data == "") {
				ajaxResponse = true;
			} else {
				alert(data);
				ajaxResponse = false;
			}
		}
	});
}

function page(flag) {
	if(flag == 'next') {
		//update current page
		currentPage = nextPage;
		
		nextPage = currentPage + 1;
		if(currentPage - 1 >= 0)
			previousPage = currentPage - 1;
		else
			previousPage = 0;
		
		
	}
	else if(flag == 'previous') {
		currentPage = previousPage;
		
		nextPage = currentPage + 1;
		if(currentPage - 1 >= 0)
			previousPage = currentPage - 1;
		else
			previousPage = 0;
	}
	
	$("#currentPage").html(currentPage + 1);
	$.ajax({
		type: "GET",
		url: "nextPage",
		data: {pageNum: currentPage},
		dataType: "text",
		success: function(data) {
			var employees = data.split("\n");
			var emp = null;
			var tbody = null;
			var tr = null;
			
			// remove empty last element
			employees.pop();
			
			// clear the tbody
			tbody = $("#container");
			tbody.empty();
			
			// loop through and rebuild the tbdoy with the next page of dudes
			for(var i = 0; i < employees.length; i++) {
				emp = $.parseJSON(employees[i]);
				tbody.append("<tr id='empRow" + emp['id'] + "'></tr>");
				tr = $("#empRow" + emp['id']);
				
				tr.append("<td>" + emp['firstName'] + " " + emp['middleInitial'] + ". " + emp['lastName'] + "</td>");
				tr.append("<td>" + emp['departmentName'] + "</td>");
				tr.append("<td>" + emp['jobTitleName'] + "</td>");
				tr.append("<td><button class=\"pure-button\" onclick=\"openEditForm('" + emp['id'] + "', '" + emp['firstName'] + "', '" + emp['middleInitial'] + "', '" + emp['lastName'] + "', '" + emp['departmentId'] + "', '" + emp['email'] + "', '" + emp['skype'] + "', '" + emp['jobTitleId'] + "')\">Edit</button></td>");
				tr.append("<td><button class=\"pure-button\" onclick=\"removeEmployee('" + emp['id'] + "')\">Delete</button></td>");
				
				
			}
			
		}
	});
}
