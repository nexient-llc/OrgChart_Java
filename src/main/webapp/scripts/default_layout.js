
$(document).ready(function() {
	$('#navBarHome').click(function() {
		window.location = path + "home";	
	});
	
	$('#navBarDepts').click(function() {
		window.location = path + "depts";	
	});
	
	$('#navBarEmps').click(function() {
		window.location = path + "emps";	
	});
	
	$('#navBarJobs').click(function() {
		window.location = path + "titles";	
	});
	
	$('#navBarAdmin').click(function() {
		window.location = path + "admin";	
	});
	
	$('#login').click(function() {
		window.location = path + "login";	
	});
	$('#logout').click(function() {
		window.location = path + "logout";	
	});
	
});