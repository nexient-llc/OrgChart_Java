var ajaxResponse;

var nextPage = 1;
var previousPage = 0;
var currentPage = 0;

$(document).ready(function() {
	// Toggles visibility of add container and add button
	$("#cancelButton").click(function() {
		$("#addEntity").toggle();
		$("#addBtn-container").toggle();
	});
	
	// Toggles visibility of filterContainer
	$("#toggleFilter").click(function() {
		$("#filterContainer").toggle();
	});
	
	// Toggles visibility of filterContainer
	$("#cancelFilter").click(function() {
		$("#filterContainer").toggle();
	});
	
	// Checks that all inputs were used and checks validity of email and skype name
	$("#newEmployeeForm").submit(function() {
		var id = -1;
		var email = $("#newEmail").val();
		var skype = $("#newSkypeName").val();
		
		if($("#addDepartmentSelect").val() == "" || $("#addJobTitleSelect").val() == "") {
			alert("Department and Job Title are required.");
			return false;
		}
		
		validateEmailAndSkype(id, email, skype, "add");
		
		return ajaxResponse;
	});
	
	// Checks that all inputs were used and checks validity of email and skype name
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
	
	// Toggles visibility of the add button and addEntitiy form
	$('#addBtn').click(function() {
		$('#addBtn-container').toggle();
		$('#addEntity').toggle();
	});
	
	// This function does an Ajax call to autocomplete the name field
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

// This toggles the edit container and auto-populates the various fields with the 
//    employee being edited
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

// This function does an Ajax call to removeEmployee then removes the table row
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

// This function does an Ajax call to reenableEmployee then removes the table row
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

// This function does an Ajax call to make sure the email and skype name aren't already in use.
//    Sets the boolean flag true if they aren't in use and false if they are.
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

// This function takes a parameter for next or previous, updates the current, next, and previous page variables.
//    Then it does an Ajax call to get the next page of employees, empties the table, and re-populates it.
function page(flag) {
	// Update the page variables
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
	
	// for displaying the page number on screen
	$("#currentPage").html(currentPage + 1);
	
	// Ajax call to get the next page of emps
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
			
			// loop through and rebuild the tbody with the next page of employees
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
