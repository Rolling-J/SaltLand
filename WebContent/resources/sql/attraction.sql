use SaltLand;
CREATE TABLE IF NOT EXISTS attraction(
	id int auto_increment,
	name varchar(12) not null,
    info varchar(150) not null,
    tag char(20) not null,
    ride varchar(10),
    age varchar(10),
    tall varchar(15),
    filename varchar(40),
    primary key(id)    
)default charset=utf8;
select*from attraction;
INSERT INTO attraction VALUES(null,'허리케인', '허리케인 구름이 맞닿을 듯한 높이까지 올라갔다 한 순간에 떨어지는 스릴만점 어트랙션입니다.', 'adventure', '40', '9~64', '130cm~190cm','hurricane.jpg'),
(null,'좀비탈출', '12구간으로 복잡하게 얽힌 이곳에서 어디서 튀어나올지 모르는 좀비를 피해 탈출하라!!', 'horror', '30', '9~64', 'none','zombiescape.jpg'),
(null,'자이로스윙', '40여명이 둘러앉은 거대한 회전기구가 시계추처럼 움직여 회오리바람에 날려가 버리는 듯한 새로운 공포를 느낄 수 있습니다.', 'adventure', '40', '9~64', '130cm~190cm','ziroswing.jpg'),
(null,'자이로스핀', '아시아 최초 저소음 무진동 회전 어트랙션! 입이 쩍~ 벌어지는 놀라운 스피드를 경험해 보세요!', 'adventure', '40', '9~64', '130cm~190cm','zirospin.jpg'),
(null,'아트란티스', '최고 스릴 어트랙션! 시속 72km로 출발하는 보트를 타고 미스터리 신전 속으로 여행을 떠나보세요!', 'adventure', '10', '9~64', '30cm~190cm','atlantis.jpg'),
(null,'혜성특급', '좌우로 회전하는 롤러코스터를 타고 떠나는 어둠 속 스펙터클 우주여행', 'adventure', '20', '9~64', 'none','cometexpress.jpg'),
(null,'번지드롭', '높이 38m의 짜릿함과 시속 90km로 추락 할 때의 스릴! 끝났구나 하고 안심하는 순간 한번 더~ 30초간 반복되는 번지드롭!', 'survival', '20', '9~64', '130cm~190cm','bungedrop.jpg'),
(null,'회전그네', '나뭇잎 의자에 앉아 4.5m 상공에서 시속 50km의 빠른 속도로 빙글빙글 도는 느낌이 금방이라도 하늘로 튕겨져 오르는 기분을 느낄 수 있습니다. 호흡을 가다듬고 하늘로 출발!', 'kiddyzone', '10', 'none', 'none','swing_round.jpg'),
(null,'머킹의 회전목마', '머킹 왕실의 해마와 바다 생물들을 타며 즐기는 축제 한 마당', 'kiddyzone', '20', 'none', '130cm~190cm','merkings_marry_go_round.jpg'),
(null,'황야의 무법자', '말 모양의 시뮬레이터를 타고 3D로 선보이는 서부 광산에서 악당을 물리치세요! 내가 바로 일등 보안관~', 'experience', '30', 'none', '130cm~190cm','desperado.jpg'),
(null,'회전목마', '64필의 아름다운 백마를 타고 떠나는 로맨틱한 여행!', 'photozone', '50', 'none', 'none','marry_go_round.jpg'),
(null,'스윙팡팡', '로티와 친구들과 함께 빙글빙글 돌아가는 바구니를 타고 동화나라를 방문했어요. 동화나라 방문을 축하해 주는 힘찬 헹가래를 위, 아래로 퉁! 퉁! 튕기는 재미가 가득', 'kiddyzone', '10', '9~64', '130cm~190cm','swing_pangpang.jpg');