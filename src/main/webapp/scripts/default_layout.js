var path = "/OrgChart_Java/app/";

$(document).ready(function() {
	$('#navBarHome').click(function() {
		window.location = path + "home";	
	});
	
	$('#navBarDepts').click(function() {
		window.location = path + "departments";	
	});
	
	$('#navBarEmps').click(function() {
		window.location = path + "employees";	
	});
	
	$('#navBarJobs').click(function() {
		window.location = path + "jobtitles";	
	});
	
});