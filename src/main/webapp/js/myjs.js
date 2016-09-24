
 function myfunction(hh){
	
	if(hh=="396"){
		 
		loadData('city',hh);
		
	}else{
				
	}
}



function loadData(loadType,id){
	
	var dataString = 'id='+ id +'&user=abc';
		
	$("#"+loadType+"_loader").show();

	$.ajax({
		type: "POST",
		url: "click1.php",
		data: dataString,
		cache: false,
		success: function(result){
			$("#"+loadType+"_loader").hide();
			$("#"+loadType+"_dropdown").html("<input type='text' name='text'>");  
			$("#"+loadType+"_dropdown").append(result);  
		alert(result);
		}
	});
	
}     
