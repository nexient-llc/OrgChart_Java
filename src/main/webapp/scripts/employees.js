$(document).ready(function()
{
	
	

	$('#addBtn-container').css('width', $('#t1').width());

	$('#addBtn').click(function() {
		
		
		$('#addEntity').fadeToggle("fast", "linear");
	
	});
	
			
	$('.cancelButton').click(function(){
		
		var x=$(this).parent().parent();
		
		$(x).css("display", "none");
						
		$('.input').val(null);

	});
	
	
$('#delBtn').click( function()
		
{

	$('#delEntity').fadeToggle("fast", "linear");

});
	


	$('.editBtn').click( function()
{

	//get values to pre-fill text fields 	
	var iterator=$(this).parent().next().html();	
	$('#firstName').val(iterator);	
	
	iterator=$(this).parent().next().next().html();	
	$('#lastName').val(iterator);	
	
	iterator=$(this).parent().next().next().next().html();	
	$('#email').val(iterator);
	
	iterator=$(this).parent().next().next().next().next().html();	
	$('#skypeName').val(iterator);

	iterator=$(this).parent().next().next().next().next().next().html();	
	$('#id').val(iterator);
	
	
	
	$('#updateEntity').fadeToggle("fast", "linear");
			
});




});