$(document).ready(function() {
	$('#addBtn-container').css('width', $('#taskhead').width());

	$('#addBtn').click(function() {
		$('#addBtn-container').fadeToggle("fast", "linear", function() {
			$('#addEntity').fadeToggle("fast", "linear");
			
			document.getElementById('name').focus();
			
		});
	});
	
});

function editJobTitle(jobTitleId, jobTitleName) {
	var nameField = document.getElementById('editName');
	window.alert("id: " + jobTitleId + " name: " + jobTitleName);
	
	$('#innerRow' + jobTitleId).fadeToggle("fast", "linear", function() {
		$('#jobTitleEditEntity').fadeToggle("fast", "linear");
		document.getElementById('row' + jobTitleId).appendChild(document.getElementById("jobTitleEditEntity"));
		nameField.value = jobTitleName;
		nameField.focus();
	});
}

function resetForm() {
	document.getElementById("newJob").reset();
	
		$('#addBtn-container').fadeToggle("fast", "linear", function() {
			$('#addEntity').fadeToggle("fast", "linear");
		});
}

function closeEdit(jobTitle) {
	document.getElementById("jobTitleEditEntity").reset();
	
	$('#button-container' + jobTitle).fadeToggle("fast", "linear", function() {
		$('#jobTitleEditEntity' + jobTitle).fadeToggle("fast", "linear");
	});
}