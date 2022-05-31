function checkAttraction(){
	const name = document.getElementById('name');
	const info = document.getElementById('info');
	const image = document.getElementById('filename');
	const updateImg = document.getElementById('imageName');
	const ride = document.getElementById('ride');
	const age = document.getElementById('age');
	const tall = document.getElementById('tall');
	
	var checkNameChar = /^[a-zA-Z0-9ㄱ-하-ㅣ가-힣]{4,12}$/;
	var checkInfoChar = /^[a-zA-Z0-9ㄱ-하-ㅣ가-힣]{10,140}$/;
	
	if(!checkNameChar.test(name.value)){
		alert("[어트렉션명]\n최소 4자에서 최대 12자까지 입력하세요");
		name.select();
		name.focus();
		return false;	
	}
	if(!checkInfoChar.test(info.value)){
		alert("[설명]\n최소 10자에서 최대 140자까지 입력하세요");
		info.select();
		info.focus();
		return false;
	}
	if(!ride.value||ride.value==""){
		alert("[탑승 인원]\n탑승 인원을 선택해주세요");
		return false;
	}
	if(!age.value||age.value==""){
		alert("[탑승 연령]\n탑승 연령을 선택해주세요");
		return false;
	}
	if(!tall.value||tall.value==""){
		alert("[탑승 가능 신장]\n탑승 가능 신장을 선택해주세요");
		return false;
	}
	if(!updateImg){
		if(!image.value){
			alert("[이미지]\n이미지를 삽입해주세요");
			return false;
		}
	}
	document.attraction.submit()
}