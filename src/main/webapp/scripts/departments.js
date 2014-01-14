$(document).ready(function() {
	$('#addBtn-container').css('width', $('#t1').width());

	$('#addBtn').click(function() {
		$('#addBtn-container').fadeToggle("fast", "linear", function() {
			$('#addEntity').fadeToggle("fast", "linear");
			document.getElementById('name').focus();
		});
	});
});

function resetForm() {
	document.getElementById("newDept").reset();
	document.getElementById('name').focus();
}

function editDepartment(department) {
	$('#button-container' + department).css('width', $('#t1').width());
	
	$('#button-container' + department).fadeToggle("fast", "linear", function() {
		$('#editEntity' + department).fadeToggle("fast", "linear");
		document.getElementById('editName' + department).focus();
	});
}