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

	$('#createdJobTitleContainer').ready(function() {
		if (CREATED_JOB)
			$('#createdJobTitleContainer').toggle();
	});

	$('#newJobTitle').submit(function() {
		var success = false;
		$.ajax({
			dataType : "text",
			type : 'GET',
			url : "findJob",
			data : "name=" + $('#newJobName').val(),
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

	$('#editJob').submit(function() {
		var success = false;
		var name = $('#editName').val();
		var id = $('#editId').val()
		$.ajax({
			dataType : "text",
			type : 'GET',
			url : "findJob",
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
	$('.tableEntry').remove();
	$.ajax({
		type : 'GET',
		url : 'getJobTitles',
		data : "page=" + page,
		success : populate_table
	})
}

function populate_table(data) {
	update_pages(data.number, data.totalPages);
	data.content.forEach(function(element) {
		var row = "<tr class='tableEntry' id='tablerow-" + element.id + "'>";
		row += "<td id='tablename-" + element.id + "'>" + element.name
				+ "</td>";
		row += "<td class='editBtnContainer' " + (!secure ? "style='display:none'" : "") + "><button onclick='editRow("
				+ element.id + ")'>Edit</button></td>";
		row += "</tr>";
		$('#t1 tr:last').after(row);
	});
}

function editRow(rowId) {
	$('#editName').val($('#tablename-' + rowId).text());
	$('#editId').val(rowId);
	$('.editBtnContainer').fadeToggle("fast", "linear");
	$('#editContainer').fadeToggle("fast", "linear");
}

function performDelete() {
	var jobId = $('#editId').val();
	$('.editBtnContainer').fadeToggle("fast", "linear");
	$('#editContainer').fadeToggle("fast", "linear");
	$('#tablerow-' + jobId).remove();
	$.ajax({
		type : 'DELETE',
		url : "job/delete/" + jobId,
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
