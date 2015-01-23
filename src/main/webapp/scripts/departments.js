$(document).ready(function() {
	$('#addBtn-container').css('width', $('#t1').width());

	$('#addBtn').click(function() {
		$('#addBtn-container').fadeToggle("fast", "linear", function() {
			$('#addEntity').fadeToggle("fast", "linear");
		});
	});
	
});

function editDepartment(id) {
	$('.editBtn-container').fadeToggle("fast", "linear");
	$('#editEntity' + id ).fadeToggle("fast", "linear");
};

function toggleSave() {
	$('#addBtn-container').fadeToggle("fast", "linear");
	$('#addEntity').fadeToggle("fast", "linear");
}
