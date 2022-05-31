function checkReservation(){
	var time = new Date();
	var year = time.getFullYear();
	var month = time.getMonth()+1;
	var date = time.getDate();
	var fullDay = year+plusZero(month)+plusZero(date);
	var aYearLater = (year+1)+plusZero(month)+plusZero(date);
	var r_year = document.getElementById("reserve_year");
	var r_month = document.getElementById("reserve_month");
	var r_day = document.getElementById("reserve_day");
	var resDay = r_year.value+plusZero(r_month.value)+plusZero(r_day.value);
	var adultN = document.getElementById("adultN");
	var teenagerN = document.getElementById("teenagerN");
	var childN = document.getElementById("childN");
	
	var checkYearChar = /^[0-9]{4}$/;
	var checkMDChar = /^[0-9]{1,2}$/;
	var checkNum = /^[0-9]*$/;
	
	if(!r_year.value){
		alert("방문년을 입력하세요");
		r_year.select();
		r_year.focus();
		return false;
	}
	if(!checkYearChar.test(r_year.value)){
		alert("방문년은 숫자 4글자로 입력해주세요");
		r_year.select();
		r_year.focus();
		return false;
	}
	if(!r_month.value){
		alert("방문월을 입력하세요");
		r_month.select();
		r_month.focus();
		return false;
	}
	if(!checkMDChar.test(r_month.value)||r_month.value>12||r_month.value<1){
		alert("방문월은 1~12 사이 숫자로 입력해주세요");
		r_month.select();
		r_month.focus();
		return false;
	}
	if(!r_day.value){
		alert("방문일을 입력하세요");
		r_day.select();
		r_day.focus();
		return false;
	}
	if((!adultN.value||!teenagerN.value||!childN.value)||
		((adultN.value==0)&&(teenagerN.value==0)&&(childN.value==0))){
		alert("예약 인원을 입력해주세요.");
		return false;
	}
	if(!checkNum.test(adultN.value)||adultN.value<0){
		alert("방문인원은 0 또는 0 이상의 숫자를 입력해주세요\n성인");
		adultN.select();
		adultN.focus();
		return false;
	}
	if(!checkNum.test(teenagerN.value)||teenagerN.value<0){
		alert("방문인원은 0 또는 0 이상의 숫자를 입력해주세요\n청소년");
		teenagerN.select();
		teenagerN.focus();
		return false;
	}
	if(!checkNum.test(childN.value)||childN.value<0){
		alert("방문인원은 0 또는 0 이상의 숫자를 입력해주세요\n어린이");
		childN.select();
		childN.focus();
		return false;
	}
	if((adultN.value*1+teenagerN.value*1+childN.value*1)>50){
		alert("예약 인원은 총 50명을 넘을 수 없습니다");
		return false;
	}
	if(fullDay>=resDay){
		alert("예약은 익일 입장하는 티켓부터 가능합니다");
		return false;
	}
	if(aYearLater<resDay){
		alert("예약일로부터 1년이 초과하는 일자에 입장하는 티켓은 예약이 불가합니다");
		return false;
	}
	document.reserveTicket.submit()
}

function plusZero(standIn) {
    if (standIn < 10) {standIn = '0' + standIn}                 
    return standIn;
}