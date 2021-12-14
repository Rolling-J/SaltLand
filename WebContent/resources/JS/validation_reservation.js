
function checkReservation(){
	var time = new Date();
	var year = time.getFullYear();
	var month = time.getMonth()+1;
	var date = time.getDate();
	var day = time.getDay();
	var week = ['일','월','화','수','목','금','토']
	var today = year +""+ plusZero(month) +""+ plusZero(date);
	var tomorrow = year +""+ plusZero(month) +""+ plusZero(date+1);	
	
	var r_year = document.getElementById("reserve_year");
	var r_month = document.getElementById("reserve_month");
	var r_day = document.getElementById("reserve_day");
	var reserveDate = r_year +""+ plusZero(r_month) +""+ plusZero(r_day);
	
	var adultN = document.getElementById("adultN");
	var teenagerN = document.getElementById("teenagerN");
	var childN = document.getElementById("childN");
	
	//date check
	if((r_year==null)||(r_month==null)||(r_day==null)){
		alert("예약 일자를 입력해주세요.");
		return false;
	}
	if(!check(/^[0-9]{3}$/,r_year,"연도에 4자리 숫자를 입력해주세요."))
		return false;
	if(!check(/^[0-9]{0,1}$/,r_month,"월에 1자리 또는 2자리 숫자를 입력해주세요."))
		return false;
	if(!check(/^[0-9]{0,1}$/,r_day,"일에 1자리 또는 2자리 숫자를 입력해주세요."))
		return false;
	
	
	//member check
	if(((adultN==null)||(teenagerN==null)||(childN==null))||
		((adultN==0)&&(teenagerN==0)&&(childN==0))){
		alert("예약 인원을 입력해주세요.");
		return false;
	}
	if(!check(/^[0-9]*$/,r_year,"인원에는 숫자를 입력해주세요."))
		return false
	
	document.reserveTicket.submit()
}

function plusZero(standIn) {
    if (standIn < 10) {standIn = '0' + standIn}                 
    return standIn;
}