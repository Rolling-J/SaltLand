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
	
	//입력확인
	if(!id.value){
		alert("아이디를 입력하세요");
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
	
	if(!name.value){
		alert("이름을 입력하세요");
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
	if(!b_month.value){
		alert("생월을 입력하세요");
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
	if(!email1.value){
		alert("이메일을 입력하세요");
		email1.select();
		email1.focus();
		return false;
	}
	if(!phone_1.value){
		alert("전화번호를 입력하세요");
		phone_1.select();
		phone_1.focus();
		return false;
	}
	if(!phone_2.value){
		alert("전화번호를 입력하세요");
		phone_2.select();
		phone_2.focus();
		return false;
	}
	if(!phone_3.value){
		alert("전화번호를 입력하세요");
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
