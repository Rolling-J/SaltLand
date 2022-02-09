function checkAttraction(){
	const name = document.getElementById('name');
	const info = document.getElementById('info');
	
	if(name.value.length<4 || name.value.length>12){
		alert("[어트렉션명]\n최소 4자에서 최대 12자까지 입력하세요");
		name.select();
		name.focus();
		return false;	
	}
	if(info.value.length<10 || info.value.length>140){
		alert("[설명]\n최소 10자에서 최대 140자까지 입력하세요");
		info.select();
		info.focus();
		return false;
	}
	document.attraction.submit()
}