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

function popupDialog(div) {
	div.dialog({
		modal: true,
		width: 400
	});
}
