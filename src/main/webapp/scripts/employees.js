$(document).ready(function() {
	$('#addBtn-container').css('width', $('#t1').width());
	
	$("#fullName").keyup(function(){
		  $.post("emps", {
		    ajaxName: document.getElementById("fullName").value
		  },
		  function(data,status){
				data = jQuery.parseJSON(data);
				var emps = [];
				
				for(var i = 0; i < data.length; i++) {
					emps.push(data[i].firstName + " " + data[i].lastName);
				}
				
				$("#fullName").autocomplete({source: emps});
		  });
		});

	$('#addBtn').click(function() {
		$('#addBtn-container').fadeToggle("fast", "linear", function() {
			$('#addEntity').fadeToggle("fast", "linear");
			document.getElementById('firstName').focus();
		});
	});
	
});

function editEmployee(employee) {
	$('#button-container' + employee).css('width', $('#taskHead').width());
	
	$('#button-container' + employee).fadeToggle("fast", "linear", function() {
		$('#editEntity' + employee).fadeToggle("fast", "linear");
		document.getElementById('editFirstName' + employee).focus();
	});
}

function resetForm() {
	document.getElementById("newEmployee").reset();
	
		$('#addBtn-container').fadeToggle("fast", "linear", function() {
			$('#addEntity').fadeToggle("fast", "linear");
		});
}

function closeEdit(employee) {
	document.getElementById("editEmployee" + employee).reset();
	
	$('#button-container' + employee).fadeToggle("fast", "linear", function() {
		$('#editEntity' + employee).fadeToggle("fast", "linear");
	});
}

function expandFilter() {
	initAjax();
	
	$('#filterBox').fadeToggle("fast", "linear", function() {
		$('#filterContent').fadeToggle("fast", "linear");
		document.getElementById("fullName").focus();
	});
}

function initAjax() {
	if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp=new XMLHttpRequest();
	} else {// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
}

//var xmlhttp;

function clearForm() {
	document.getElementById("filterForm").reset();
	document.getElementById("fullName").focus();
}

function completeField(empName) {
	document.getElementById("fullName").value = empName;
	document.getElementById("filter").focus();
}

function typing() {
	var fullName = document.getElementById("fullName");
	
//	xmlhttp.onreadystatechange=function() {
//		if (xmlhttp.readyState==4 && xmlhttp.status==200) {
//			emps = jQuery.parseJSON(xmlhttp.responseText);
//			
//			$("#fullName").autocomplete({source: emps.firstName});
//		}
//	};
//	
//	xmlhttp.open("POST","emps",true);
//	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
//	xmlhttp.send("ajaxName=" + fullName.value);
	

}