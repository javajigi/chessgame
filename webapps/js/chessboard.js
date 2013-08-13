$(function(){

    $('#boards tr td').click(function() {
      alert('Handler for .click() called.');
    });
    
    $('#move').click(function() {
    	var source = $('#source').val();
    	if (source == '') {
    		alert("이동할 말을 선택해 주세요.");
    		$('#source').focus();
    		return false;
    	}
    	var target = $('#target').val();
     	if (target == '') {
    		alert("이동할 지점을 입력해 주세요.");
    		$('#target').focus();
    		return false;
    	}
    	   	
    	var moveUrl = "/move?source=" + source + "&target=" + target;
    	
    	$(this).attr('href', moveUrl);
    	
    	return true;
    });

});