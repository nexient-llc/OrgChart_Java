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
	if(val == undefined)
		return;
	
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
			url: "employees/" + $(this).data("id") + "/json",
			type: "GET",
			dataType: "json"
		
		}).done(function(employee){
			/* Clear state */
			$("#managerError").html("");
			errors = [];
			$("#editSubmit").attr("disabled", false);
			 
			/* Pre-fill edit forms. */
			$("#id").val(employee.id);
			$("#deleteId").val(employee.id);
			$("#firstName").val(employee.firstName);
			$("#lastName").val(employee.lastName);
			$("#lastName").val(employee.lastName);
			$("#skypeName").val(employee.skypeName);
			$("#email").val(employee.email);
			$("#isManager").prop('checked', employee.isManager	);
			
			if(employee.manager != undefined && employee.manager.id != undefined)
				$("#manager").val(employee.manager.id);
			else
				$("#manager").val("");
			
			/* Select appropriate Job Title*/
			selectByValue($('#jobTitle'), employee.jobTitle.id);
			
			/* Select appropriate Department */
			selectByValue($('#department'), employee.department.id);
			
			/* Check Is Manager box if they're a manager. Make sure
			 * it's unchecked if they're not. */
			if(employee.isManager == true) {
				$('#isManager').prop('checked',true);
			} else {
				$('#isManager').prop('checked',false);
			}
			
			/* Define onChange handler for managed by field to validate
			 * against them managing themselves.
			 */
			
			$("#manager").change(function(){
				var ourId = $("#id").val();
				if(ourId == $(this).val()) {
					$(this).css({'background-color' : 'light red'});
					$("#managerError").html("You cannot manage yourself<br>");
					$("#editSubmit").attr("disabled", true);
				} else {
					$(this).css({'background-color' : 'white'});
					$("#managerError").html("");
					$("#editSubmit").attr("disabled",false);
				}
				
			});
		});
		
		$("#empEditDialog").dialog({
			modal: true,
			width: 400
		});
	
	});
});