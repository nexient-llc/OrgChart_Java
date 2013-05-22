var path = "/orgchart/app/";

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
		window.location = path + "jobs";	
	});
	
});