package dao;
import java.util.ArrayList;
import dto.attraction;

public class attractionbox {
	private ArrayList<attraction> listOfattractions = new ArrayList<attraction>();
	private static attractionbox instance = new attractionbox();

	
	public static attractionbox getInstance() {
		return instance;
	}

	public static void setInstance(attractionbox instance) {
		attractionbox.instance = instance;
	}

	public attractionbox() {
		attraction survival = new attraction("허리케인","서바이벌");
		survival.setInfo("허리케인 구름이 맞닿을 듯한 높이까지 올라갔다 한 순간에 떨어지는 스릴만점 어트랙션입니다.");
		survival.setRide("40명");
		survival.setAge("5세~64세");
		survival.setTall("130cm~190cm");
		survival.setFilename("허리케인.png");
		
		attraction horror = new attraction("좀비탈출","호러");
		horror.setInfo("12구간으로 복잡하게 얽힌 이곳에서 어디서 튀어나올지 모르는 좀비를 피해 탈출하라!!");
		horror.setRide("30명");
		horror.setAge("9세~64세");
		horror.setTall("제한 없음");
		horror.setFilename("좀비탈출.png");
		
		attraction adventure = new attraction("자이로스윙","어드벤쳐");
		adventure.setInfo("40여명이 둘러앉은 거대한 회전기구가 시계추처럼 움직여 회오리바람에 날려가 버리는 듯한 새로운 공포를 느낄 수 있습니다.");
		adventure.setRide("40명");
		adventure.setAge("9세~64세");
		adventure.setTall("130cm~190cm");
		adventure.setFilename("자이로스윙.png");
		
		attraction kidzone = new attraction("회전그네","키디존");
		kidzone.setInfo("나뭇잎 의자에 앉아 4.5m 상공에서 시속 50km의 빠른 속도로 빙글빙글 도는 느낌이 금방이라도 하늘로 튕겨져 오르는 기분을 느낄 수 있습니다. 호흡을 가다듬고 하늘로 출발!");
		kidzone.setRide("10명");
		kidzone.setAge("제한 없음");
		kidzone.setTall("제한 없음");
		kidzone.setFilename("회전그네.png");
		
		attraction photozone = new attraction("회전목마","포토존");
		photozone.setInfo("64필의 아름다운 백마를 타고 떠나는 로맨틱한 여행!");
		photozone.setRide("50명");
		photozone.setAge("제한 없음");
		photozone.setTall("제한 없음");
		photozone.setFilename("회전목마.png");
		
		attraction exp = new attraction("황야의 무법자","체험관");
		photozone.setInfo("말 모양의 시뮬레이터를 타고 3D로 선보이는 서부 광산에서 악당을 물리치세요! 내가 바로 일등 보안관~");
		photozone.setRide("30명");
		photozone.setAge("제한 없음");
		photozone.setTall("130cm~190cm");
		photozone.setFilename("황야의 무법자.png");

		

		listOfattractions.add(survival);
		listOfattractions.add(horror);
		listOfattractions.add(adventure);
		listOfattractions.add(kidzone);
		listOfattractions.add(photozone);
		listOfattractions.add(exp);
	}

	public ArrayList<attraction> getAllattractions() {
		return listOfattractions;
	}
	
	public attraction getattractionByname(String name) {
		attraction attractionByname = null;

		for (int i = 0; i < listOfattractions.size(); i++) {
			attraction attraction = listOfattractions.get(i);
			if (attraction != null && attraction.getName() != null && attraction.getName().equals(name)) {
				attractionByname = attraction;
				break;
			}
		}
		return attractionByname;
	}
	
	public void addattraction(attraction attraction) {
		listOfattractions.add(attraction);
	}
}