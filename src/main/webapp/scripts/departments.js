$(document).ready(
		function() {
			$('#addBtn-container').css('width', $('#t1').width());

			$('#addBtn').click(function() {
				$('#addBtn-container').fadeToggle("fast", "linear", function() {
					$('#addEntity').fadeToggle("fast", "linear");
				});
			});

			$('#editBtn-container').css('width', $('#t1').width());

			$('.editBtn').click(
					function() {
						$('#editBtn-container' + $(this).val()).fadeToggle("fast", "linear", function() {
									$('#editEntity').fadeToggle("fast", "linear");
								});
						$('#editEntityId').val($(this).val());
				});
			$('#deleteBtn-container').css('width', $('#t1').width());

			$('.deleteBtn').click(
					function() {
						$('#deleteBtn-container' + $(this).val()).fadeToggle("fast", "linear", function() {
									$('#deleteEntity').fadeToggle("fast", "linear");
								});
						$('#deleteEntityId').val($(this).val());
					});

		});

function editDepartment(department) {
	docutment.getElementById("department").innerHtml;
}