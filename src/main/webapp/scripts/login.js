$(document).ready(function() {
	alert("here");
	document.forms[0].elements['userName'].focus();
	
	$('#submitBtn').submit(function(){
		alert("Here");
	});
});
