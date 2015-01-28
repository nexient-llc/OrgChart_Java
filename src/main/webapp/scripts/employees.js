$(document).ready(function() {
	$('#addBtn-container').css('width', $('#t1').width());

	$('#addBtn').click(function() {
		$('#addBtn-container').fadeToggle("fast", "linear", function() {
			$('#addEntity').fadeToggle("fast", "linear");
		});
	});
	
	// Ajax call to get name suggestions from the server
	$('input#autocompletename').autocomplete({
		source: function(request, response) {
			$.ajax({
				delay: 0,
				type: "GET",
				url: "namesuggestions/" + $("#autocompletename").val(),
				dataType: "text",
				success: function(data) {
					var suggestions = data.split(",");
					suggestions.pop();
					response(suggestions);
				}
			});
		}
	});
	
});

function toggleSave() {
	$('#addBtn-container').fadeToggle("fast", "linear");
	$('#addEntity').fadeToggle("fast", "linear");
};

function editEmployee(id) {
	$('.editBtn-container').fadeToggle("fast", "linear");
	$('#editEntity' + id ).fadeToggle("fast", "linear");
};

function toggleFilter() {
	$('#filterEntity').fadeToggle("fast", "linear");
};