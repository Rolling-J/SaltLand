function checkMember() {
	const id = document.getElementById("id");
	const password = document.getElementById("password");
	const password_confirm = document.getElementById("password_confirm");
	const name = document.getElementById("name");
	const b_year = document.getElementById("b_year");
	const b_month = document.getElementById("b_month");
	const b_day = document.getElementById("b_day");
	const gender = document.getElementById("gender");
	const email1 = document.getElementById("email1");
	const email2 = document.getElementById("email2");
	const phone_1 = document.getElementById("phone_1");
	const phone_2 = document.getElementById("phone_2");
	const phone_3 = document.getElementById("phone_3");
	
	var checkIdChar = /^[a-zA-Z][a-zA-Z0-9]{3,9}$/;
	var checkNameChar = /^[a-zA-Z0-9ㄱ-하-ㅣ가-힣]{2,10}$/;
	var checkPWChar = /^[0-9]{4,10}$/;
	var checkYearChar = /^[0-9]{4}$/;
	var checkMDChar = /^[0-9]{1,2}$/;
	var checkPhoneChar1 = /^01[0|1|6-9]$/;
	var checkPhoneChar2 = /^[0-9]{3,4}$/;
	var checkPhoneChar3 = /^[0-9]{4}$/;
	
	if(!id.value){
		alert("아이디를 입력하세요");
		id.select();
		id.focus();
		return false;
	}
	if(!checkIdChar.test(id.value)){
		alert("아이디는 영문자(소문자,대문자)와 숫자로 최소 4자, 최대 10자 이내로 입력해주세요\n첫글자는 영문자여야 합니다");
		id.select();
		id.focus();
		return false;
	}
	if(!password.value){
		alert("비밀번호를 입력하세요");
		password.select();
		password.focus();
		return false;
	}
	if(!checkPWChar.test(password.value)){
		alert("비밀번호는 숫자로 최소 4자, 최대 10자 이내로 입력해주세요");
		password.select();
		password.focus();
		return false;
	}
	if(!name.value){
		alert("이름을 입력하세요");
		name.select();
		name.focus();
		return false;
	}
	if(!checkNameChar.test(name.value)){
		alert("이름은 영문자(소문자,대문자)와 한글, 숫자로 최소 2자, 최대 10자 이내로 입력해주세요");
		name.select();
		name.focus();
		return false;
	}
	if(!b_year.value){
		alert("생년을 입력하세요");
		b_year.select();
		b_year.focus();
		return false;
	}
	if(!checkYearChar.test(b_year.value)){
		alert("생년은 숫자 4글자로 입력해주세요");
		b_year.select();
		b_year.focus();
		return false;
	}
	if(!b_month.value){
		alert("생월을 입력하세요");
		b_month.select();
		b_month.focus();
		return false;
	}
	if(!checkMDChar.test(b_month.value)||b_month.value>12||b_month.value<1){
		alert("생월은 1~12 사이 숫자로 입력해주세요");
		b_month.select();
		b_month.focus();
		return false;
	}
	if(!b_day.value){
		alert("생일을 입력하세요");
		b_day.select();
		b_day.focus();
		return false;
	}
	if(b_month.value==2){
		if(!checkMDChar.test(b_day.value)||b_day.value>29){
			alert("생일은 숫자로 입력해주세요\n해당 월은 1~29 사이로 입력하실 수 있습니다");
			b_day.select();
			b_day.focus();
			return false;
		}
	}else if(b_month.value==1||b_month.value==3||b_month.value==5||b_month.value==7||b_month.value==8||b_month.value==10||b_month.value==12){
		if(!checkMDChar.test(b_day.value)||b_day.value>31){
			alert("생일은 숫자로 입력해주세요\n해당 월은 1~31 사이로 입력하실 수 있습니다");
			b_day.select();
			b_day.focus();
			return false;
		}
	}else{
		if(!checkMDChar.test(b_day.value)||b_day.value>30){
			alert("생일은 숫자로 입력해주세요\n해당 월은 1~30 사이로 입력하실 수 있습니다");
			b_day.select();
			b_day.focus();
			return false;
		}
	}
	if(!gender.value||gender.value==""){
		alert("성별을 선택하세요");
		return false;
	}
	if(!email1.value){
		alert("이메일을 입력하세요");
		email1.select();
		email1.focus();
		return false;
	}
	if(!email2.value){
		alert("이메일 홈페이지를 선택하세요");
		return false;
	}
	if(!phone_1.value){
		alert("전화번호의 첫번째 칸을 입력하세요");
		phone_1.select();
		phone_1.focus();
		return false;
	}
	if(!phone_2.value){
		alert("전화번호의 두번째 칸을 입력하세요");
		phone_2.select();
		phone_2.focus();
		return false;
	}
	if(!phone_3.value){
		alert("전화번호의 세번째 칸을 입력하세요");
		phone_3.select();
		phone_3.focus();
		return false;
	}
	if(!checkPhoneChar1.test(phone_1.value)){
		alert("전화번호 형식이 올바르지 않습니다\n첫번째 칸에는 010,011,016~9 사이 숫자를 입력해주세요");
		phone_1.select();
		phone_1.focus();
		return false;
	}
	if(!checkPhoneChar2.test(phone_2.value)){
		alert("전화번호 형식이 올바르지 않습니다\n두번째 칸에는 3자리 또는 4자리 숫자를 입력해주세요");
		phone_2.select();
		phone_2.focus();
		return false;
	}
	if(!checkPhoneChar3.test(phone_3.value)){
		alert("전화번호 형식이 올바르지 않습니다\n세번째 칸에는 4자리 숫자를 입력해주세요");
		phone_3.select();
		phone_3.focus();
		return false;
	}
	if(password.value != password_confirm.value){
		alert("비밀번호를 동일하게 입력하세요");
		return false;
	}
	document.member.submit()
}


