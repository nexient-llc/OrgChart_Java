/* selectByValue
 * Accepts: JQuery Object, String
 * Returns: No Return.
 * 
 * Helper function that accepts a jquery objects
 * which points to an <option> and default selects
 * the <select> which has the value we provided
 * with val. It also updates the value appropriately.
 */
function selectByValue(obj, val) {
	obj.val(val);
	obj.each(function(){
	    if ($(this).text() == val) {
	        $(this).attr("selected",true);
	    } else {
	        $(this).removeAttr("selected");
	    }
	});
}

$(document).ready(function() {
	$(".empEditBtn").click(function() {
		
		/* Requests JSON representing an employee
		 * based on the passed in id.
		 */
		$.ajax({
			url: "emps/" + $(this).data("id") + "/json",
			type: "GET",
			dataType: "json"
		
		}).done(function(employee){
			/* Pre-fill edit forms. */
			$("#firstName").val(employee.firstName);
			$("#lastName").val(employee.lastName);
			$("#lastName").val(employee.lastName);
			$("#skypeName").val(employee.skypeName);
			$("#email").val(employee.email);
			$("#isManager").prop('checked', employee.isManager	);
			
			/* Select appropriate Job Title*/
			selectByValue($('#jobTitles'), employee.jobTitle.id);
			
			/* Select appropriate Department */
			selectByValue($('#departments'), employee.department.id);
			
			/* Check Is Manager box if they're a manager. Make sure
			 * it's unchecked if they're not. */
			if(employee.isManager == true) {
				$('#isManager').prop('checked',true);
			} else {
				$('#isManager').prop('checked',false);
			}
		});
		
		$("#empEditDialog").dialog({
			modal: true,
			width: 400
		});
		
		
		/* Clear manager field on focus. */
		$('#manager').on('click focusin', function() {
		    this.value = '';
		});
	});
});