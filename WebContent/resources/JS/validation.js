function checkaddattraction(){
	

	var name = document.getElementById("name");


	if(name.value.length<4 || name.value.length>12){
		alert("[어트렉션 명]\n최소 4자에서 최대 12자까지 입력하세요");
		name.select();
		name.focus();
		return false;
		
		
	}
	
	
	function check(regExp, e, msg){
		
		if(regExp.test(e.value)){
			return true;
		}
		alert(msg);
		s.select();
		e.focus();
		return false;
	}		
	document.newattraction.submit()
}