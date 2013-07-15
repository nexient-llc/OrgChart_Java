

$(document).ready(function() {
	
		
	$('#addBtn-container').css('width', $('#t1').width());

	$('#addBtn').click(function() {
		
		$('#addBtn-container').fadeToggle("fast", "linear", function() {
			$('#addEntity').fadeToggle("fast", "linear");
		});
	});
	
	
	
	$('#delBtn').click(function() {
		
		$('#addBtn-container').fadeToggle("fast", "linear", function() {
			$('#delEntity').fadeToggle("fast", "linear");
		});
	});
	
	
	
	$('.editBtn').click(function()
	{			
		//get value of name for row
		var name=$(this).parent().next().html();
		
		$('#addBtn-container').fadeToggle("fast", "linear", function() 
				{
			$('#updateEntity').fadeToggle("fast", "linear");});

		$('#updateOldName').val(name);
		$('#updateName').val(name);
		
	
	
	});
		
	//alert(this.title);
	
	
});
