$(document).ready(function() {
	$('#login').click(function() {
		window.location = "/orgchart/app/admin/login?page=" + window.location.pathname;
	});
});
