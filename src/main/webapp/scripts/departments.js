$(document).ready(function() {
	var lastClickedButton;
	$('#addBtn-container').css('width', $('#t1').width());

	$('#addBtn').click(function() {
		$('#addBtn-container').fadeToggle("fast", "linear", function() {
			$('#addEntity').fadeToggle("fast", "linear");
		});
	});
	
	$("[id^=editBtn]").click(function() {
		if(lastClickedButton != null){
			$(lastClickedButton).fadeToggle("fast", "linear");
		}
		else{
			$('#editEntity').fadeToggle("fast", "linear");
		}
		lastClickedButton = this;
		$(this).fadeToggle("fast", "linear");
	});
	
});
