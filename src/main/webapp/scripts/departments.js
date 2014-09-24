//(function(){
"use strict";
$(document).ready(function() 
{ 
	// Define the Dialog and its properties.
	$("#addDialog").dialog(
	{
		autoOpen: false,
		draggable: false,
		dialogClass: "d1",
		resizable: false,
		modal: true,
		title: "Add New Department",
		height: 250,
		width: "auto",
		buttons: {
		    "Add": function () {
		    		$(this).dialog("close");
		    		callback(true);
		    	},
		    "Cancel": function () {
		    		$(this).dialog("close");
		                callback(false);
		            }
		        }
	});
	$("#addBtn").click(function(){
		$("#addDialog").dialog("open");
	});
	jQuery.ajax({ type: "POST", 
		url: "depts", 
		data: null,//{page: 0, size: 2}, 
		dataType: "JSON", // Execute the response as a script once we get it. 
		success: function(data, textStatus){
			repopulateTable(data);
		}// Call this function when done. 
	});
	$("#clickme").click(function() {
			saveDepartment({name: "Justice League", parentDepartment: null, manager: null})
		}
	);
	
	function emptyTable(){
		$("#departmentTable").children("tbody").children(":not(tr:first)").remove();
	}
	function repopulateTable(depts){ //departments should be an array
		var tbody = $("#departmentTable").children("tbody");
		var i; //iterator
		emptyTable();
		for(i = 0; i < depts.length; i++){
			tbody.append("<tr><td>" + depts[i].name + "</td><td>"
					+ (depts[i].parentDepartment ? depts[i].parentDepartment.name : "N/A")	+ "</td><td>"
					+ (depts[i].manager ? (depts[i].manager.firstName + " " + depts[i].manager.lastName) : "N/A")
					+ "</td></tr>");
		}
		
	}
	
	function saveDepartment(dep){
		jQuery.ajax({ 
			type: "POST", 
			url: "depts", 
			data: {dep: "whatwhat"},//{page: 0, size: 2}, 
			dataType: "text", // Execute the response as a script once we get it. 
			success: function(data, textStatus){
				alert(data);
			}// Call this function when done. 
		});	
	}
	
	function callback(value) {
	}	
} );//end of init
//}());