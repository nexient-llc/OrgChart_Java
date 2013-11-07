$(document).ready(function() {
	$('#login').click(function() {
		window.location = "/OrgChart_Java/app/admin/login?page=" + window.location.pathname;
	});
});
