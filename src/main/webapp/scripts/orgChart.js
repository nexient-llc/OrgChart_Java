$(document).ready(function() {
	$('#login').click(function() {
		window.location = "/app/login?page=" + window.location.pathname;
		
	});
	$('#logout').click(function() {
		window.location = "/app/logout?page=" + window.location.pathname;
		
	});
});
