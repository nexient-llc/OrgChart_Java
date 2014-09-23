$(document).ready(function(e) {
	var nav = $("#navBar");
	nav.css("left", String(-1 * (nav.offset().left + nav.outerWidth(true)) + "px"));
	nav.animate({left: 0}, {duration: 1550, easing: "easeInOutElastic"});	
	$("#bodyWrapper").css("display", "none");
	$("#bodyWrapper").fadeToggle(1000, "easeInQuint");
});

(function(parent){
	parent.OrgChart = 
		{
			_checkScrollBars: function() 
			{
				var body = $("body"),
				scrollW = 0;
				if(body.prop("scrollHeight") > body.height()){
					scrollW = window.innerWidth - body.width();
				}
				return scrollW;
			},
			_setPageMargin: function (offset){
				$("body").css("margin-right", "-" + String(offset) + "px");
			},
		}
}(window));