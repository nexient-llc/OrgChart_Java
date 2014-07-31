$(document).ready(function() {
	$('#addBtn-container').css('width', $('#t1').width());

	$('#addBtn').click(function() {
		$('#addBtn-container').fadeToggle("fast", "linear");
		$('#addEntity').fadeToggle("fast", "linear");
	});

	$('#cancelBtn').click(function() {
		$('#addBtn-container').fadeToggle("fast", "linear");
		$('#addEntity').fadeToggle("fast", "linear");
	});

	$('#cancelEditBtn').click(function() {
		$('.editBtnContainer').fadeToggle("fast", "linear");
		$('#editContainer').fadeToggle("fast", "linear");
	});

	$('#createdDepartmentContainer').ready(function() {
		if (CREATED_DEPT)
			$('#createdDepartmentContainer').toggle();
	});

	$('#newDepartment').submit(function() {
		var success = false;
		$.ajax({
			dataType : "text",
			type : 'GET',
			url : "findDepart",
			data : "name=" + $('#newDeptName').val(),
			async : false,
			success : function(data) {
				if (data == "true") {
					alert("That name already exists in the database.");
					success = false;
				} else {
					success = true;
				}
			}
		})
		return success;
	});

	$('#editDept').submit(function() {
		var success = false;
		var name = $('#editName').val();
		var id = $('#editId').val()
		$.ajax({
			dataType : "text",
			type : 'GET',
			url : "findDepart",
			data : "name=" + name + "&id=" + id,
			async : false,
			success : function(data) {
				if (data == "true") {
					alert("'" + name + "' already exists in the database.");
					success = false;
				} else {
					success = true;
				}
			}
		})
		return success;
	});

	get_table_data();
});

function get_table_data() {
	$.ajax({
		type : 'GET',
		url : 'getDepartments',
		success : populate_table
	})
}

function populate_table(data) {
	data.forEach(function(element) {
		var row = "<tr id='tablerow-" + element.id + "'>";
		row += "<td id='tablename-" + element.id + "'>" + element.name
				+ "</td>";
		if (element.parentDepartment != null) {
			row += "<td><div id='tablepName-" + element.id + "'>"
					+ element.parentDepartment.name + "</div></td>"
			row += "<td style='display:none'><div id='tablepId-" + element.id
					+ "'>" + element.parentDepartment.id + "</div></td>"
		} else {
			row += "<td><div id='tablepId-" + element.id
					+ "' value=''></div></td>"
		}
		row += "<td class='editBtnContainer'><button onclick='editRow("
				+ element.id + ")'>Edit</button></td>";
		row += "</tr>";
		$('#t1 tr:last').after(row);
	});
}

function editRow(rowId) {
	$('#editName').val($('#tablename-' + rowId).text());
	$('#editParent').val($('#tablepId-' + rowId).text());
	$('#editId').val(rowId);
	$('.editBtnContainer').fadeToggle("fast", "linear");
	$('#editContainer').fadeToggle("fast", "linear");
}

function performDelete() {
	var deptId = $('#editId').val();
	$('.editBtnContainer').fadeToggle("fast", "linear");
	$('#editContainer').fadeToggle("fast", "linear");
	$('#tablerow-' + deptId).remove();
	$('.parentSelect-' + deptId).remove();
	$.ajax({
		type : 'DELETE',
		url : "depart/delete/" + deptId,
	})
}
