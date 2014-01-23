$(document).ready(function() {
	$('#login').click(function() {
		window.location = "/app/admin/login?page=" + window.location.pathname;
	});
});
