$(document).ready(function() {
	$('#addBtn-container').css('width', $('#t1').width());

	$('#addBtn').click(function() {
		$('#addBtn-container').fadeToggle("fast", "linear", function() {
			$('#addEntity').fadeToggle("fast", "linear");
		});
	});
	

});
	

function editButton(deptId){
	$('#editEntity input[type=hidden]').val(deptId);
	$('#editBtn-container' + deptId).fadeToggle("fast", "linear", function() {
		$('#editEntity').fadeToggle("fast", "linear");
	});
}
	
