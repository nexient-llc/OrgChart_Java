$(document).ready(function() {
	$('#login').click(function() {
		window.location = "/app/login?page=" + window.location.pathname;
		
	});
});
