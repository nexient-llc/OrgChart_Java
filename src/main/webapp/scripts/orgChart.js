$(document).ready(function() {
	$('#login').click(function() {
		window.location = "admin/login?page=" + window.location.pathname;
	});
});
