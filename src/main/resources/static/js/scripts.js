$(document).ready(function(){
	$(".progress-bar").animate({
	    width: "100%"
	}, 4000);

	window.setTimeout(function () {
	    $(".updating-text").text('Success! Update Complete.');
	    $(".progress-container").hide();
	    $(".report-button, .flash-report-button, .return-button").removeClass("hidden").addClass("shown");
	}, 4000);	
	
	$('.report-button').click(function() {
		console.log('report button clicked');
		window.location = "/download";
	});
	
	$('.error-button').click(function() {
		console.log('error button clicked');
		window.location = "/upc";
	});
	
	$('.flash-error-button').click(function() {
		console.log('error button clicked');
		window.location = "/";
	});
	
	$('.flash-report-button').click(function() {
		console.log('report button clicked');
		window.location = "/flash-download";
	});
	
	$('.return-button').click(function() {
		console.log('report button clicked');
		window.location = "/";
	});
	
//	$('#update_flag').click(function() {
//		console.log("You Clicked ME!!!!!");
//
//	}).prop('checked', true);
	
//	$('#update_flag').click( console.log('You Clicked Me Too!!!!'));
	


})

