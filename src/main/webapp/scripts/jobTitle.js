$(document).ready(function() {
	var $lastClickedButton = null;

	$('#addBtn-container').css('width', $('#t1').width());

	//Add Button
	$('#addBtn').click(function() {
		$("#CreateEntity").fadeIn("fast");
		$("#removeEntity").fadeOut("fast");
		$("#EditEntity").fadeOut("fast", "linear");
		
		resetButton($(this));
	});

	//Create Form Submit
	$("#CreateEntity").submit(function(){
		//TODO ID
	});

	//Edit Button
	$('.editBtn').click(function() {
		$("#EditEntity").fadeIn("fast");

		$("#removeEntity").fadeOut("fast");

		$("#CreateEntity").fadeOut("fast");
		
		resetButton($(this));
		
	});

	//Edit Form Submit
	$("#EditEntity").submit(function(){
		//TODO id
	});

	//Remove Button
	$('.removeBtn').click(function() {
		$("#removeEntity").fadeIn("fast");

		$("#EditEntity").fadeOut("fast");

		$("#CreateEntity").fadeOut("fast");
		
		resetButton($(this));
	});
	
	function resetButton($button){
		$button.fadeOut("fast");
		
		if($lastClickedButton != null){
			$lastClickedButton.fadeIn("fast");
		}
		
		$lastClickedButton = $button;
	}
});


