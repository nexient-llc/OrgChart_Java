$(document).ready(function() {
	$('#addBtn-container').css('width', $('#t1').width());
	
	$('#addBtn').click(function() {
		$('#addBtn-container').fadeToggle("fast", "linear", function() {
			$('#addEntity').fadeToggle("fast", "linear");
		});
	});
	
	$('.editBtn').click(function(evnt) {
		var num = $(this).val();
		$('#ViewEmps' + num).fadeToggle("fast", "linear", function() {
			$('#EditEmps' + num).fadeToggle("fast", "linear");
		});
	});
	
	$('.filter-container').click(function() {
		$('#filter-container').fadeToggle("fast", "linear", function() {
			$('#search').fadeToggle("fast", "linear");
		});
	});
	
	$('.filterBtn').click(function(evnt) {
		$.ajax({
			url : "emps",
			type : "GET",
			data : {
				fullName : $('#search #empFullName').val(),
				deptId : $('#search #empDeptId').val(),
				jobId : $('#search #empJobId').val()
			},
			success : function(data) {
				data = jQuery.parseJSON(data);
				$('#t1 .row:not(.header)').fadeOut('fast');
				data.forEach(function(empId) {
					$('#t1 #ViewEmps' + empId).fadeIn('fast');
				});
			},
		});
	});
	
	$('.autocomplete').keyup(
			function() {
				var name = $(this).val();
				$.ajax({
					url : "emps",
					type : "GET",
					data : {
						name : name
					},
					success : function(data) {
						// data = ;
						// var names = [];
						// var ids = [];
						// for(var i = 0; i < data.length; i++) {
						// names.push(data[i]);
						// //ids.push(data[i].id);
						// }
						$('#empFullName').autocomplete({
							source : jQuery.parseJSON(data)
						}).data("autocomplete")._renderItem = function(
								ul, item) {
							var re = new RegExp("^" + name);
							var t = item.label.replace(re,
									"<style='font-weight:bold;color:Blue;'>" + name
											+ "</span>");
							return $("<li></li>").data("item.autocomplete", item).append(
									"<a>" + t + "</a>").appendTo(ul);
						};
						// $('#t1 .row:not(.header)').fadeOut('fast');
						// ids.forEach(function(empId) {
						// $('#t1 #ViewEmps' + empId).fadeIn('fast');
						// });
					},
				});
			});
	
//	$('#empFullName').autocomplete.prototype._renderItem = function(ul,
//			item) {
//		var re = new RegExp("^" + this.term);
//		var t = item.label.replace(re,
//				"<style='font-weight:bold;color:Blue;'>" + this.term
//						+ "</span>");
//		return $("<li></li>").data("item.autocomplete", item).append(
//				"<a>" + t + "</a>").appendTo(ul);
//	};
//	
	$('.deleteBtn').click(function(evnt) {
		var num = $(this).val();
		alert(num);
		$.ajax({
			url : "emps",
			type : "POST",
			data : {
				_method : "delete",
				id : num
			},
			success : function(response) {
				window.location.href = "emps";
			},
		});
	});
	
	$('.saveBtn').click(function(evnt) {
		var num = $(this).val();
		$.ajax({
			url : "emps",
			type : "POST",
			data : {
				_method : "put",
				id : num,
				firstName : $('#empFirstName' + num).val(),
				middleInitial : $('#empMidInitial' + num).val(),
				lastName : $('#empLastName' + num).val(),
				email : $('#empEmail' + num).val(),
				skypeName : $('#empSkypeName' + num).val(),
				departmentId : $('#empdeptId' + num).val(),
				jobTitleId : $('#empjobId' + num).val(),
			},
			success : function(response) {
				window.location.href = "emps";
				},
			});
		});

});