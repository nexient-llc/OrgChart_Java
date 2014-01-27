
$(document).ready(function() {


    $('#navBarHome').css('cursor', 'pointer');
    $('#navBarDepts').css('cursor', 'pointer');
    $('#navBarEmps').css('cursor', 'pointer');
    $('#navBarJobs').css('cursor', 'pointer');


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