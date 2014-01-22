$(document).ready(function() {
	$("#t1").dataTable( {
        "bPaginate": false,
        "bLengthChange": false,
        "bFilter": true,
        "bSort": false,
        "bInfo": false,
        "bAutoWidth": false
    } );

	$("#edit-modal").dialog({
		autoOpen : false,
		modal : true,
		show : {
			effect : "fade",
			duration : 500
		},
		hide : {
			effect : "explode",
			duration : 300
		},
		buttons : [ {
			text : "Save",
			click : function() {
				var form = $(this).find('form');
				form.submit();
			}
		} ]
	});

	$("#active-modal").dialog({
		autoOpen : false,
		modal : true,
		show : {
			effect : "fade",
			duration : 500
		},
		hide : {
			effect : "explode",
			duration : 300
		},
		buttons : [ {
			text : "Activate",
			click : function() {
				var form = $(this).find('form');
				form.submit();
			}
		}, {
			text : "Deactivate",
			click : function() {
				var parent = $(this).closest("tr");
				parent.find('input[name=isActive]').val(false);
				var form = $(this).find('form');
				form.submit();
			}
		} ]
	});

	$('#addBtn').click(function() {
		$(this).parent().fadeToggle("fast", "linear", function() {
			$('#addEntity').fadeToggle("fast", "linear");
		});
	});

	$('#cancelAddBtn').click(function(dept) {
		dept.preventDefault();
		$('#addEntity').fadeToggle("fast", "linear", function() {
			$('#addBtn-container').fadeToggle("fast", "linear");
			document.getElementById("addForm").reset();
		});

		$('#addEntity input[type=text], #addEntity select').val("");
	});

	$('.editBtn').click(function() {
		$("#edit-modal").dialog("open");
		var parent = $(this).closest('tr');

		var deptId = parent.find('input[name=deptId]').val();
		var deptName = parent.find('.deptName').html();
		var parentId = parent.find('input[name=parentDeptId]').val();

		var modal = $("#edit-modal");
		modal.find('input[name=id]').val(deptId);
		modal.find('input[name=name]').val(deptName);
		modal.find('select[name="parentDepartment.id"]').val(parentId);

	});

	$('.deleteBtn').click(function() {
		$("#active-modal").dialog("open");
		var parent = $(this).closest("tr");

		var deptActive = parent.find('input[name= is_dept_active]').val();
		var deptId = parent.find('input[name=deptId]').val();
		var deptName = parent.find('.deptName').html();

		var modal = $("#active-modal");
		modal.find('input[name=id]').val(deptId);
		modal.find('input[name=name]').val(deptName);

	});

	$('.cancelEditBtn').click(function(dept) {
		dept.preventDefault();
		var deptNum = $(this).val();
		$('#editEntity' + deptNum).fadeToggle("fast", "linear", function() {
			$('#deptRow' + deptNum).fadeToggle("fast", "linear");
			document.getElementById("editForm").reset();
		});

		$('#editEntity' + deptNum + ' .editDeptName').val("");
		$('#editEntity' + deptNum + ' .editDeptParent').val("");
	});

	$('.removeDeptBtn').click(function() {
		$.ajax({
			type : "POST",
			url : "depts",
			data : {
				_method : "delete",
				id : $(this).val()
			},
			success : function() {
				window.location.href = "depts";
			}
		});
	});

	$('.saveDeptBtn').click(function() {
		var deptNum = $(this).val();
		var deptName = $('#editDeptRow' + deptNum + ' .editDeptName').val();
		var parentId = $('#editDeptRow' + deptNum + ' .editDeptParent').val();

		$.ajax({
			type : "POST",
			url : "depts",
			data : {
				_method : "put",
				id : deptNum,
				name : deptName,
				parentId : parentId
			},
			success : function() {
				window.location.href = "depts";
			}
		});
	});
});