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

	$('#createdEmployeeContainer').ready(function() {
		if (CREATED_EMPLOYEE)
			$('#createdEmployeeContainer').toggle();
	});

	$('#filterBtn').click(function() {
		$('#filterBtn-container').fadeToggle("fast", "linear");
		$('#filterEntity').fadeToggle("fast", "linear");
	});

	$('#resetFilterBtn').click(function() {
		$('#filterBtn-container').fadeToggle("fast", "linear");
		$('#filterEntity').fadeToggle("fast", "linear");
	});

	$('#filterBox').focus().autocomplete({
		source : function(request, response) {
			$.ajax({
				dataType : "text",
				type : "GET",
				url : "searchEmployeeName/" + $('#filterBox').val(),
				success : function(data) {
					$('#filterBox').removeClass('ui-autocomplete-loading');
					if (data.length > 0)
						var output = data.split(",");
					response(output);
				}
			});
		}
	});

	$('#newEmployee').submit(function() {
		var success = false;
		$.ajax({
			url : "findEmployee",
			data : $(this).serialize(),
			type : 'GET',
			async : false,
			success : function(data) {
				if (data != "Ok") {
					alert(data);
					success = false;
				} else {
					success = true;
				}
			}
		})
		return success;
	});

	$('#editEmp').submit(function() {
		var success = false;
		$.ajax({
			url : "findEmployee",
			data : $(this).serialize(),
			type : 'GET',
			async : false,
			success : function(data) {
				if (data != "Ok") {
					alert(data);
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
	$('.tableEntry').remove();
	$.ajax({
		type : 'GET',
		url : 'getEmployees',
		data : "page=" + page,
		success : populate_table
	})
}

function populate_table(data) {
	update_pages(data.number, data.totalPages);
	data.content.forEach(function(element) {
		var row = "<tr class='tableEntry' id='tablerow-" + element.id + "'>";
		row += "<td id='tablename-" + element.id + "'>"
		row += element.fullName;
		row += "<div id='tablefName-" + element.id + "' style='display:none'>"
				+ element.firstName + "</div>"
		row += "<div id='tablemName-" + element.id + "' style='display:none'>"
				+ ((element.middleInitial) ? element.middleInitial : "")
				+ "</div>"
		row += "<div id='tablelName-" + element.id + "' style='display:none'>"
				+ element.lastName + "</div>"
		row += "<div id='tableEmail-" + element.id + "' style='display:none'>"
				+ element.email + "</div>"
		row += "<div id='tableSkype-" + element.id + "' style='display:none'>"
				+ element.skypeName + "</div>"
		row += "</td>";
		if (element.department != null) {
			row += "<td><div id='tabledName-" + element.id + "'>"
					+ element.department.name + "</div></td>"
			row += "<td style='display:none'><div id='tabledId-" + element.id
					+ "'>" + element.department.id + "</div></td>"
		} else {
			row += "<td><div id='tabledId-" + element.id
					+ "' value=''></div></td>"
		}
		if (element.jobTitle != null) {
			row += "<td><div id='tablejName-" + element.id + "'>"
					+ element.jobTitle.name + "</div></td>"
			row += "<td style='display:none'><div id='tablejId-" + element.id
					+ "'>" + element.jobTitle.id + "</div></td>"
		} else {
			row += "<td><div id='tablejId-" + element.id
					+ "' value=''></div></td>"
		}
		row += "<td class='editBtnContainer'><button onclick='editRow("
				+ element.id + ")'>Edit</button></td>";
		row += "</tr>";
		$('#t1 tr:last').after(row);
	});
}

function editRow(rowId) {
	$('#editFirstName').val($('#tablefName-' + rowId).text());
	$('#editMidInit').val($('#tablemName-' + rowId).text());
	$('#editLastName').val($('#tablelName-' + rowId).text());
	$('#editDept').val($('#tabledId-' + rowId).text());
	$('#editEmail').val($('#tableEmail-' + rowId).text());
	$('#editSkype').val($('#tableSkype-' + rowId).text());
	$('#editJob').val($('#tablejId-' + rowId).text());
	$('#editId').val(rowId);
	$('.editBtnContainer').fadeToggle("fast", "linear");
	$('#editContainer').fadeToggle("fast", "linear");
}

function performDelete() {
	var empId = $('#editId').val();
	$('.editBtnContainer').fadeToggle("fast", "linear");
	$('#editContainer').fadeToggle("fast", "linear");
	$('#tablerow-' + empId).remove();
	$.ajax({
		type : 'DELETE',
		url : "emp/delete/" + empId,
	})
}

function changePage(pageNum) {
	page = pageNum;
	get_table_data();
}

function update_pages(pageNumber, totalPages) {
	$('#pagination').empty();
	var pagespan = "";
	for (i = 0; i < totalPages; i++) {
		if (i == page)
			pagespan += "<li class='active' onclick='changePage(" + i + ")'>" + (i + 1) + "</li>";
		else
			pagespan += "<li onclick='changePage(" + i + ")'>" + (i + 1) + "</li>";
	}
	$('#pagination').append(pagespan);
}