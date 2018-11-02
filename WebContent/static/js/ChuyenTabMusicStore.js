$(document).ready(function() {
	function activeTab(obj){
		$('#tab li').remove('active');
		$(obj).addClass('active');
		var id=$(obj).find('a').attr('href');
		$('.noidung').hide();
		$(id).show();
	}
	$('#tab li').click(function() {
		/* Act on the event */
		activeTab(this);
		return false;
	});
	$('#tab1 li').click(function() {
		/* Act on the event */
		activeTab(this);
		return false;
	});
	activeTab($('#tab li:first-child'));
});