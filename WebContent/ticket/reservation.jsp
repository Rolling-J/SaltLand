<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/SaltProject/resources/css/reservation.css">
    <link rel="stylesheet" href="/SaltProject/resources/css/bar.css">
    <link rel="stylesheet" href="/SaltProject/resources/css/footer.css">
    
    
    <title>티켓 예매</title>
</head>
<body>
    <jsp:include page="/bar.jsp"></jsp:include>
    <section id="reserve_main">
        <div class="background">
            <div class="wrap_body">
                <div class="contents_wrap">
                    <div class="contents_head">
                        <h1>티켓 예매</h1>
                    </div>
                    <div class="divine_h"></div>
                    <div class="contents_sellect">
                        <div class="sellect_head">
                            <div class="sellct_title">
                                <p class="title_txt">방문일자/인원 선택</p>
                            </div>
                            <div class="cal_area">
                                <div class="cal_date">
                                    <span class="month">10</span>
                                    <span>. </span> 
                                    <span class="day">20</span>
                                    <span class="weekday">수</span>
                                </div>
                                <button type="button" class="cal_btn">
                                    <img src="../resources/image/btn_cal.png" alt="cal" class="cal_icon">
                                </button>
                            </div>
                        </div>
                        <div class="sellect_date">
                             <div class="wrap_date">
                                <ul class="scr_date">
                                    <li class="day_ns">
                                        <button type="button" class="sellectable-wednesday" data-date="20211020">
                                            <em class="weekday">수</em>
                                            <em class="day">20</em>
                                        </button>
                                    </li>
                                    <li class="day_ns">
                                        <button type="button" class="sellectable-thursday" data-date="20211021">
                                            <em class="weekday">목</em>
                                            <em class="day">21</em>
                                        </button>
                                    </li>
                                    <li class="day_ns">
                                        <button type="button" class="sellectable-friday" data-date="20211022">
                                            <em class="weekday">금</em>
                                            <em class="day">22</em>
                                        </button>
                                    </li>
                                    <li class="day_ns">
                                        <button type="button" class="sellectable-saturday" data-date="20211023">
                                            <em class="weekday">토</em>
                                            <em class="day">23</em>
                                        </button>
                                    </li>
                                    <li class="day_ns">
                                        <button type="button" class="sellectable-sunday" data-date="20211024">
                                            <em class="weekday">일</em>
                                            <em class="day">24</em>
                                        </button>
                                    </li>
                                    <li class="day_ns">
                                        <button type="button" class="sellectable-monday" data-date="20211025">
                                            <em class="weekday">월</em>
                                            <em class="day">25</em>
                                        </button>
                                    </li>
                                    <li class="day_ns">
                                        <button type="button" class="sellectable-tuesday" data-date="20211026">
                                            <em class="weekday">화</em>
                                            <em class="day">26</em>
                                        </button>
                                    </li>
                                </ul>
                            </div>
                            
                        </div>
                        <div class="sellect_people">
                            <ul= class="count_people">
                                <li class="cnt_adult">
                                    <div class="cnt_txt">
                                        <div class="txt_a">
                                            <p>성인</p>
                                        </div>
                                        <div class="txt_des">
                                            <p>만 19세 이상</p>
                                        </div>
                                    </div>
                                    <div class="cnt_input">
                                        <input type="number" class="nbr_input">
                                    </div>
                                </li>
                                <li class="cnt_teenager">
                                    <div class="cnt_txt">
                                        <div class="txt_a">
                                            <p>청소년</p>
                                        </div>
                                        <div class="txt_des">
                                            <p>만 13세 이상 ~ 만 18세</p>
                                        </div>
                                    </div>
                                    <div class="cnt_input">
                                        <input type="number" class="nbr_input">
                                    </div>
                                </li>
                                <li class="cnt_child">
                                    <div class="cnt_txt">
                                        <div class="txt_a">
                                            <p>어린이</p>
                                        </div>
                                        <div class="txt_des">
                                            <p>만 3세 ~ 만 12세</p>
                                        </div>
                                    </div>
                                    <div class="cnt_input">
                                        <input type="number" class="nbr_input">
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="divine_h"></div>
                    <div class="contents_eventinfo">
                        <div class="event_head">
                            <div class="event_title">
                                <p class="title_txt">할인 이벤트</p>
                            </div>
                        </div>
                        <div class="event_cat">
                            <ul class="cat_sellect">
                                <li class="cont_block tab1 selected">
                                    <a class="tab_box">
                                        기간제 혜택
                                    </a>
                                </li>
                                <li class="cont_block tab2">
                                    <a class="tab_box">
                                        제휴카드
                                    </a>
                                </li>
                             </ul>
                        </div>
                        <div id="event_tab01" class="event_cont selected">
                            <ul class="event_list">
                                <li class="cont_list">
                                    <div class="list_title">
                                        <p>온라인 예매 할인</p>
                                    </div>
                                    <div class="list_under">
                                        <div class="list_des">
                                            <div class="event_img">
                                                <img src="../resources/image/event01.png" alt class="online_event">
                                            </div>
                                            <div class="event_des">
                                                <p>[2021.07.01 ~ 2021.12.31] 하반기 온라인 이벤트!</p>
                                                <p>하반기동안 진행되는 온라인 예매 이벤트 입니다.</p>
                                            </div>
                                        </div>
                                        <div class="list_sellect">
                                            <div class="sellect_btn">
                                                <button type="button" class="btn_sellect_event" onclick="">적용하기</button>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                                <li class="cont_list">
                                    <div class="list_title">
                                        <p>야간 파티 테마 할인</p>
                                    </div>
                                    <div class="list_under">
                                        <div class="list_des">
                                            <div class="event_img">
                                                <img src="../resources/image/event02.jpg" alt class="online_event">
                                            </div>
                                            <div class="event_des">
                                                <p>[2021.08.01 ~ 2021.10.30] 야간 파티 테마 할인 이벤트!</p>
                                                <p>Party tonight! 야간 파티 테마의 할인 이벤트입니다.</p>
                                            </div>
                                        </div>
                                        <div class="list_sellect">
                                            <div class="sellect_btn">
                                                <button type="button" class="btn_sellect_event" onclick="">적용하기</button>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                                <li class="cont_list">
                                    <div class="list_title">
                                        <p>할로윈 테마 할인</p>
                                    </div>
                                    <div class="list_under">
                                        <div class="list_des">
                                            <div class="event_img">
                                                <img src="../resources/image/event03.jpg" alt class="online_event">
                                            </div>
                                            <div class="event_des">
                                                <p>[2021.10.01 ~ 2021.11.31] 할로윈 이벤트!</p>
                                                <p>Trick or Treat! 할로윈 축제 이벤트입니다.</p>
                                            </div>
                                        </div>
                                        <div class="list_sellect">
                                            <div class="sellect_btn">
                                                <button type="button" class="btn_sellect_event" onclick="">적용하기</button>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                            </ul>
                        </div>
                        <div id="event_tab02" class="event_cont">
                            <ul class="event_list">
                                <li class="cont_list">
                                    <div class="list_title">
                                        <p>NN 카드</p>
                                    </div>
                                    <div class="list_under">
                                        <div class="list_des">
                                            <div class="event_img">
                                                <img src="../resources/image/creditcard.jpg" alt class="online_event">
                                            </div>
                                            <div class="event_des">
                                                <p>[2021.07.01 ~ 2021.12.31] 하반기 온라인 이벤트!</p>
                                                <p>하반기동안 진행되는 온라인 예매 이벤트 입니다.</p>
                                            </div>
                                        </div>
                                        <div class="list_sellect">
                                            <div class="sellect_btn">
                                                <button type="button" class="btn_sellect_event" onclick="">적용하기</button>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                                <li class="cont_list">
                                    <div class="list_title">
                                        <p>DD 카드</p>
                                    </div>
                                    <div class="list_under">
                                        <div class="list_des">
                                            <div class="event_img">
                                                <img src="../resources/image/creditcard.jpg" alt class="online_event">
                                            </div>
                                            <div class="event_des">
                                                <p>[2021.08.01 ~ 2021.10.30] 야간 파티 테마 할인 이벤트!</p>
                                                <p>Party tonight! 야간 파티 테마의 할인 이벤트입니다.</p>
                                            </div>
                                        </div>
                                        <div class="list_sellect">
                                            <div class="sellect_btn">
                                                <button type="button" class="btn_sellect_event" onclick="">적용하기</button>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                                <li class="cont_list">
                                    <div class="list_title">
                                        <p>TT 카드</p>
                                    </div>
                                    <div class="list_under">
                                        <div class="list_des">
                                            <div class="event_img">
                                                <img src="../resources/image/creditcard.jpg" alt class="online_event">
                                            </div>
                                            <div class="event_des">
                                                <p>[2021.10.01 ~ 2021.11.31] 할로윈 이벤트!</p>
                                                <p>Trick or Treat! 할로윈 축제 이벤트입니다.</p>
                                            </div>
                                        </div>
                                        <div class="list_sellect">
                                            <div class="sellect_btn">
                                                <button type="button" class="btn_sellect_event" onclick="">적용하기</button>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="reserve">
                        <a href="ticketDetail.jsp">예매하기</a>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <jsp:include page="/footer.jsp"></jsp:include>
    <script src="/SaltProject/resources/JS/reservationTab.js"></script>
</body>
</html>