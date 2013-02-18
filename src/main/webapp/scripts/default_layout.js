var path = "/OrgChart_Java/app/";

$(document).ready(
		function() 
		{
			$('#navBarHome').click(function() {
				window.location = path + "home";
			});
			
			$('#navBarDepts').click(function() {
				window.location = path + "depts";	
			});
			
			$('#navBarEmps').click(function() {
				window.location = path + "emps";	
			});
			
			$('#navBarJobs').click(function() {
				window.location = path + "jobs";
			});
	}
);

$(document).ready(
		function() 
		{
			//split the path into an array of strings
			var pathSplit = window.location.pathname.split('/');

			//find the last value in the array, the page we're on
			//Cap the first letter for later use
			var page = pathSplit[pathSplit.length-1]
				.toLowerCase()
				.replace(/\b[a-z]/g, 
					function(letter) 
					{
				    	return letter.toUpperCase();
					}
			);
			
			//now adjust the nav classes accordingly
			$('#navBar').children('.navActive').attr('class','navInactive');
			$('#navBar'+page).attr('class','navActive');
		}
);
