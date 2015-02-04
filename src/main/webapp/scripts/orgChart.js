$(document).ready(function() {
	$('#login').click(function() {
		//window.location = "/app/admin/login?page=" + window.location.pathname;
		//window.location = path + "admin/login?page=" + window.location.pathname;
		window.location = path + "admin/";
	});
	
	$('#logout').click(function() {
		document.getElementById("#logout").submit();
	});
});
