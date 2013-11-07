$(document).ready(function(){
	$("#addBtn").click(function(){
		$("#name").val('');
		$("#id").val('');
		$("#deleteId").val('');
		$("#dangerZone").css('display', 'none');
		$("#editForm").attr("action", "job_titles/add");
		
		popupDialog($("#jobEditDialog"));
	});
	
	$(".jobEditBtn").click(function(){
		$.ajax({
			url: 'job_titles/' + $(this).data("id") + '/json',
			type : 'get',
			dataType : 'json'
		}).done(function(jobtitle){
			$("#name").val(jobtitle.name);
			$("#id").val(jobtitle.id);
			$("#deleteId").val(jobtitle.id);
			$("#dangerZone").css('display', 'inline');
			$("#editForm").attr("action", "job_titles/edit");
			
			popupDialog($("#jobEditDialog"));
		});
	});
});