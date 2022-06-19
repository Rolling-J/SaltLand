package mvc.controller;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import mvc.model.BoardDAO;
import mvc.model.BoardDTO;
import mvc.model.AttractionDAO;
import mvc.model.AttractionDTO;
import mvc.model.MemberDAO;
import mvc.model.MemberDTO;
import mvc.model.TicketDAO;
import mvc.model.TicketDTO;

public class Controller extends HttpServlet{
	private static final long serialVersionUID = 1L;
	static final int MAINPAGENOTICE = 8;
	static final int MAINPAGEATR = 3;
	static final int NOTICELISTCOUNT = 10;
	static final int TICKETLISTCOUNT = 3;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
		
		response.setContentType("text/html; charset = utf-8");
		request.setCharacterEncoding("utf-8");
	
		
	// ** 전체 **
		//메인 페이지 출력하기
		if(command.equals("/MainPage.do")) {
			RequestMainPage(request);
			RequestDispatcher rd = request.getRequestDispatcher("./main.jsp");
			rd.forward(request, response);
		}
		//버스 정보 페이지 출력하기
		else if(command.equals("/BusInfo.do")) {
			RequestDispatcher rd = request.getRequestDispatcher("./bus.jsp");
			rd.forward(request, response);
		}
	// ** 게시판 컨트롤 **
		//등록된 글 목록 페이지 출력하기
		else if(command.equals("/BoardListAction.do")) {
			requestBoardList(request);
			RequestDispatcher rd = request.getRequestDispatcher("./noticeboard/boardList.jsp");
			rd.forward(request, response);
		}
		// 글 등록 페이지 출력하기
		else if(command.equals("/BoardWriteForm.do")){ //리스트 페이지의 작성 버튼으로부터 request 받음
			requestLoginName(request);
			RequestDispatcher rd = request.getRequestDispatcher("./noticeboard/addNotice.jsp");
			rd.forward(request, response);
		}
		// 새로운 글 등록하기
		else if(command.equals("/BoardWriteAction.do")){ //등록페이지로부터 request 받음
			requestBoardWrite(request); //게시글 업로드 메서드
			RequestDispatcher rd = request.getRequestDispatcher("/BoardListAction.do"); 
			//업로드 메서드 작동 후 게시글 목록으로 복귀.
			rd.forward(request, response);
		}
		//선택된 글 상세 페이지 가져오기
		else if(command.equals("/BoardViewAction.do")) {
			requestSelectedBoard(request);
			RequestDispatcher rd = request.getRequestDispatcher("/DetailView.do");
			rd.forward(request, response);
		}
		//글 상세 페이지 출력하기
		else if(command.equals("/DetailView.do")) {
			RequestDispatcher rd = request.getRequestDispatcher("./noticeboard/noticeDetail.jsp");
			rd.forward(request, response);
		}
		//선택된 수정 페이지 가져오기
		else if(command.equals("/UpdateViewAction.do")) {
			requestSelectedBoard(request);
			RequestDispatcher rd = request.getRequestDispatcher("/UpdateView.do");
			rd.forward(request, response);
		}
		//글 수정 페이지 출력하기
		else if(command.equals("/UpdateView.do")) {
			RequestDispatcher rd = request.getRequestDispatcher("./noticeboard/updateNotice.jsp");
			rd.forward(request, response);
		}
		//선택된 글의 수정 업데이트
		else if(command.equals("/BoardUpdateAction.do")){
			requestBoardUpdate(request);
			RequestDispatcher rd = request.getRequestDispatcher("/BoardListAction.do");
			rd.forward(request, response);
		}
		//선택된 글 삭제하기
		else if(command.equals("/BoardDeleteAction.do")) {
			requestBoardDelete(request);
			RequestDispatcher rd = request.getRequestDispatcher("/BoardListAction.do");
			rd.forward(request, response);
		}
		
		
	// ** 어트랙션 컨트롤 **
		//어트랙션 목록 페이지 출력
		if(command.equals("/AttractionList.do")) {
			requestAtrList(request);
			RequestDispatcher rd = request.getRequestDispatcher("./attraction/attractions.jsp");
			rd.forward(request, response);
		}
		//어트랙션 등록 페이지 출력
		else if(command.equals("/AddAttractionForm.do")) {
			RequestDispatcher rd = request.getRequestDispatcher("./attraction/addAttraction.jsp");
			rd.forward(request, response);
		}
		//어트랙션 상세 페이지 출력
		else if(command.equals("/AttractionDetailView.do")) {
			requestSelectedAtr(request);
			RequestDispatcher rd = request.getRequestDispatcher("./attraction/attractionDetail.jsp");
			rd.forward(request, response);
		}
		//어트랙션 수정 및 삭제 페이지 출력
		else if(command.equals("/EditAttractionView.do")) {
			requestAtrList(request);
			RequestDispatcher rd = request.getRequestDispatcher("./attraction/editAttraction.jsp");
			rd.forward(request, response);
		}
		//어트랙션 상세 수정 페이지 출력
		else if(command.equals("/UpdateAttractionForm.do")) {
			requestSelectedAtr(request);
			RequestDispatcher rd = request.getRequestDispatcher("./attraction/updateAttraction.jsp");
			rd.forward(request, response);
		}
				
		//어트랙션 등록
		else if(command.equals("/AddAttractionAction.do")) {
			requestAddAtr(request);
			RequestDispatcher rd = request.getRequestDispatcher("/AttractionList.do");
			rd.forward(request, response);
		}
		//어트랙션 수정 업데이트
		else if(command.equals("/UpdateAttractionAction.do")) {
			requestUpdateAtr(request);
			RequestDispatcher rd = request.getRequestDispatcher("/AttractionList.do");
			rd.forward(request, response);
		}
		//어트랙션 삭제
		else if(command.equals("/DeleteAttraction.do")) {
			requestAtrDelete(request);
			RequestDispatcher rd = request.getRequestDispatcher("/AttractionList.do");
			rd.forward(request, response);
		}
		
		// ** 사용자 컨트롤 **
		//로그인 페이지 출력
		if(command.equals("/LoginView.do")) {
			requestGetResultMessage(request);
			RequestDispatcher rd = request.getRequestDispatcher("./member/login.jsp");
			rd.forward(request, response);
		}
		//회원가입 페이지 출력
		else if(command.equals("/RegisterForm.do")) {
			RequestDispatcher rd = request.getRequestDispatcher("./member/register.jsp");
			rd.forward(request, response);
		}
		//회원 정보 페이지 출력
		else if(command.equals("/MemberDetailView.do")) {
			requestMemberAndTicketInfo(request);
			RequestDispatcher rd = request.getRequestDispatcher("./member/memberDetail.jsp");
			rd.forward(request, response);
		}
		//회원 정보 수정 페이지 출력
		else if(command.equals("/UpdateMemberForm.do")) {
			requestMemberAndTicketInfo(request);
			RequestDispatcher rd = request.getRequestDispatcher("./member/updateMember.jsp");
			rd.forward(request, response);
		}
		//결과 페이지 출력
		else if(command.equals("/MemberResultView.do")) {
			requestGetResultMessage(request);
			RequestDispatcher rd = request.getRequestDispatcher("./member/resultMember.jsp");
			rd.forward(request, response);
		}
		//로그인 프로세스
		else if(command.equals("/LoginAction.do")) {
			requestLoginOkOrNot(request);
			RequestDispatcher rd = request.getRequestDispatcher("./member/processLoginMember.jsp");
			rd.forward(request, response);
		}
		//로그아웃 프로세스
		else if(command.equals("/LogoutAction.do")) {
			RequestDispatcher rd = request.getRequestDispatcher("./member/processLogoutMember.jsp");
			rd.forward(request, response);
		}
		//회원가입
		else if(command.equals("/AddMemberAction.do")) {
			requestAddMember(request);
			RequestDispatcher rd = request.getRequestDispatcher("/MemberResultView.do");
			rd.forward(request, response);
		}
		//회원 정보 수정 업데이트
		else if(command.equals("/UpdateMemberAction.do")) {
			requestMemberUpdate(request);
			RequestDispatcher rd = request.getRequestDispatcher("/MemberResultView.do");
			rd.forward(request, response);
		}
		//회원 탈퇴
		else if(command.equals("/DeleteMemberAction.do")) {
			requestMemberDelete(request);
			RequestDispatcher rd = request.getRequestDispatcher("/LogoutAction.do");
			rd.forward(request, response);
		}
		
		// ** 티켓 관련 컨트롤 **
		//예약 페이지 출력
		else if(command.equals("/ReservationFormView.do")) {
			RequestDispatcher rd = request.getRequestDispatcher("./ticket/reservation.jsp");
			rd.forward(request, response);
		}
		//티켓 상세페이지 출력
		else if(command.equals("/TicketDetailView.do")) {
			requestTicketInfo(request);
			RequestDispatcher rd = request.getRequestDispatcher("./ticket/ticketDetail.jsp");
			rd.forward(request, response);
		}
		//팝업 열기
		else if(command.equals("/LoginPopup.do")) {
			RequestDispatcher rd = request.getRequestDispatcher("./ticket/popupLogin.jsp");
			rd.forward(request, response);
		}
		//티켓 예약
		else if(command.equals("/ReservateTicket.do")) {
			requestReserving(request);
			RequestDispatcher rd = request.getRequestDispatcher("/MemberDetailView.do");
			rd.forward(request, response);
		}
		//티켓 취소
		else if(command.equals("/CancleReservation.do")) {
			requestCancleReservation(request);
			RequestDispatcher rd = request.getRequestDispatcher("/MemberDetailView.do");
			rd.forward(request, response);
		}
	}
	
	////** 사용자 관련 메서드 **
	
	//로그인 확인
	public void requestLoginOkOrNot(HttpServletRequest request) {
		MemberDAO dao = MemberDAO.getInstance();
		String id = (String)request.getParameter("id");
		String password = (String)request.getParameter("password");
		
		if(dao.loginTest(id, password)) {
			request.setAttribute("result", 1);
			request.setAttribute("id", id);
		}else {
			request.setAttribute("result", 0);
		}
	}

	//결과 메세지 셋
	public void requestGetResultMessage(HttpServletRequest request) {
		if(request.getAttribute("msg")==null) {
			String msg=request.getParameter("msg");
			request.setAttribute("msg", msg);
		}else {
			String msg=String.valueOf(request.getAttribute("msg")); //error 발생. 
			//error message : class java.lang.Integer cannot be cast to class java.lang.String (java.lang.Integer and java.lang.String are in module java.base of loader 'bootstrap')
			request.setAttribute("msg", msg);
		}
	}
	
	//회원가입
	public void requestAddMember(HttpServletRequest request) {
		MemberDAO dao = MemberDAO.getInstance();
		MemberDTO member = new MemberDTO();
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String year = request.getParameter("b_year");
		String month = request.getParameter("b_month");
		String day = request.getParameter("b_day");
		String birth = year + "/" + month + "/" + day;
		String gender = request.getParameter("gender");
		String mail1 = request.getParameter("email1");
		String mail2 = request.getParameter("email2");
		String mail = mail1 + "@" + mail2;
		String phone1 = request.getParameter("phone_1");
		String phone2 = request.getParameter("phone_2");
		String phone3 = request.getParameter("phone_3");
		String phone = phone1 + "-" + phone2 + "-" + phone3;
		
		Date currentDatetime = new Date(System.currentTimeMillis());
		java.sql.Timestamp timestamp = new java.sql.Timestamp(currentDatetime.getTime());
		
		DateTimeFormatter format = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
		String registDay = format.format(timestamp.toLocalDateTime());
		
		member.setId(id);
		member.setPassword(password);
		member.setName(name);
		member.setBirth(birth);
		member.setGender(gender);
		member.setMail(mail);
		member.setPhone(phone);
		member.setRegistDay(registDay);
		dao.addMember(member);
		
		request.setAttribute("msg",2);
	}
	//ID 바탕으로 사용자 정보 불러오기 (페이징 기능 추가)
	public void requestMemberAndTicketInfo(HttpServletRequest request) {
		String id = request.getParameter("sessionId");
		
		// 멤버 가입 정보 가져오기
		MemberDAO dao = MemberDAO.getInstance();
		MemberDTO member = new MemberDTO();
		
		member = dao.getMemberById(id);
		
		// 해당 회원의 티켓 예약 정보 가져오기 (페이징 기능 추가)
		TicketDAO ticketdao = TicketDAO.getInstance();
		List<TicketDTO> ticketList = new ArrayList<TicketDTO>();
		int pageNum=1;
		if(request.getParameter("pageNum") != null) { //페이지 버튼을 클릭했을 경우 선택한 페이지 파라미터 get
			pageNum=Integer.parseInt(request.getParameter("pageNum"));
		}
		int limit=TICKETLISTCOUNT;
		int total_ticket = ticketdao.getTicketCount(id);
		
		
		ticketList = ticketdao.getTicketList(pageNum, limit, id); //페이지별 티켓리스트 가져오기
		
		//총 페이지 수 계산 (total_page 변수에 담기)
		int total_page;
		if(total_ticket % limit == 0) { //마지막 페이지의 티켓 수가 페이지별 표시가능한 수와 일치하는 경우
			total_page = total_ticket/limit;
			Math.floor(total_page);	
		}else { //마지막 페이지의 티켓 수가 페이지별 표시가능한 수에 미달하는 경우
			total_page = total_ticket/limit;
			Math.floor(total_page);
			total_page = total_page + 1;
		}
		
		request.setAttribute("member", member); // 사용자 정보 전송
		request.setAttribute("ticketList", ticketList); // 페이지별 표시될 티켓리스트 전송
		request.setAttribute("total_ticket", total_ticket); // 페이지별 표시될 티켓리스트 전송
		request.setAttribute("pageNum", pageNum); // request에서 가져온 파라미터 pageNum이 없으면 1, 있으면 해당 번호 전송.
		request.setAttribute("total_page", total_page); // 계산한 총 페이지수 전송
		
	}
	//수정 내용 dto에 저장 및 DB 업데이트
	public void requestMemberUpdate(HttpServletRequest request) {
		MemberDAO dao = MemberDAO.getInstance();
		MemberDTO member = new MemberDTO();
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String year = request.getParameter("b_year");
		String month = request.getParameter("b_month");
		String day = request.getParameter("b_day");
		String birth = year + "/" + month + "/" + day;
		String gender = request.getParameter("gender");
		String mail1 = request.getParameter("email1");
		String mail2 = request.getParameter("email2");
		String mail = mail1 + "@" + mail2;
		String phone1 = request.getParameter("phone_1");
		String phone2 = request.getParameter("phone_2");
		String phone3 = request.getParameter("phone_3");
		String phone = phone1 + "-" + phone2 + "-" + phone3;

		System.out.println("requestMemberUpdate() test. id : "+id+" , birth : "+birth); //sessionId 파라미터 수신 테스트
		member.setId(id);
		member.setPassword(password);
		member.setName(name);
		member.setBirth(birth);
		member.setGender(gender);
		member.setMail(mail);
		member.setPhone(phone);
		dao.updateMember(member);
		
		request.setAttribute("msg",1);
	}
	//회원 탈퇴
	public void requestMemberDelete(HttpServletRequest request) {
		MemberDAO memberdao = MemberDAO.getInstance();
		TicketDAO ticketdao = TicketDAO.getInstance();
		
		String id = (String) request.getParameter("id");
		System.out.println("requestMemberDelete got attribute id : "+id);
		memberdao.deleteMember(id);
		ticketdao.cancleTicketByID(id);
		request.setAttribute("delete", true);
		request.setAttribute("msg",4);
	}
	
	//// ** 티켓 관련 메서드 **
	
	//티켓 상세 정보 가져오기
	public void requestTicketInfo(HttpServletRequest request) {
		TicketDAO dao = TicketDAO.getInstance();
		TicketDTO ticket = new TicketDTO();
		int ticketNumber = Integer.parseInt(request.getParameter("reserve_num"));
		ticket = dao.getTicketInfo(ticketNumber);
		
		request.setAttribute("ticket", ticket);
	}
	//티켓 예약하기
	public void requestReserving(HttpServletRequest request) {
		TicketDAO dao = TicketDAO.getInstance();
		TicketDTO ticket = new TicketDTO();
		
		System.out.println("requestReserving totalC parameter : "+request.getParameter("totalC"));
		String id = request.getParameter("id");
		String r_year = request.getParameter("r_year");
		String r_month = request.getParameter("r_month");
		String r_day = request.getParameter("r_day");
		String visit_date = r_year+"/"+r_month+"/"+r_day;
		int adult=0;
		int teenager=0;
		int children=0;
		String charge=request.getParameter("totalC");
		try {
			adult = Integer.parseInt(request.getParameter("adultN"));
			teenager = Integer.parseInt(request.getParameter("teenagerN"));
			children = Integer.parseInt(request.getParameter("childrenN"));
		}catch(NumberFormatException ne) {
			System.out.println("controller requestReserving error : "+ne);
		}catch(Exception e) {
			System.out.println("controller requestReserving error : "+e);
		}
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy/MM/dd(HH:mm:ss)");
		String reserve_time = formatter.format(new java.util.Date());
		
		//ticket.setReserve_num(reserve_num);
		ticket.setId(id);
		ticket.setVisit_date(visit_date);
		ticket.setAdult(adult);
		ticket.setTeenager(teenager);
		ticket.setChildren(children);
		ticket.setCharge(charge);
		ticket.setReserve_time(reserve_time);
		
		dao.reserveTicket(ticket);
	}	
	//티켓 취소하기 (삭제)
	public void requestCancleReservation(HttpServletRequest request) {
		TicketDAO dao = TicketDAO.getInstance();
		
		String id = request.getParameter("sessionId");
		int ticketNumber = Integer.parseInt(request.getParameter("reserve_num"));
		System.out.println("requestCancleReservation message - member ID : "+id); //sessionId 파라미터 수신 테스트
		dao.cancleTicket(ticketNumber);
	}
	
	//// ** 게시판 관련 메서드 **
	
	//리스트 페이지 기능 구현 위한 일부 변수 계산
	public void requestBoardList(HttpServletRequest request) { //게시판 글 목록 출력 리퀘스트
		BoardDAO dao = BoardDAO.getInstance();
		List<BoardDTO> boardlist = new ArrayList<BoardDTO>();
		
		int pageNum=1;
		int limit=NOTICELISTCOUNT;
		
		if(request.getParameter("pageNum") != null) {
			pageNum=Integer.parseInt(request.getParameter("pageNum"));
		}
		
		String category = request.getParameter("s_category");
		String text = request.getParameter("s_text");
		
		int total_record = dao.getListCount(category, text);
		// dao.getListCount : board 테이블의 레코드 수 (조건에 맞는 게시판의 글 수를 sql에서 계산하여 숫자로 가져옴)
		boardlist = dao.getBoardList(pageNum, limit, category, text);
		// dao.getBoardList : board 테이블의 레코드를 num 번호 순서대로(최신글 먼저) 가져오기(각 페이지에 들어갈 게시글 목록 가져오기)
		int total_page;
		
		if(total_record % limit == 0) {
			total_page = total_record/limit;
			Math.floor(total_page);	
		}else {
			total_page = total_record/limit;
			Math.floor(total_page);
			total_page = total_page + 1;
		}
		
		// request 요소 작성, board.list.jsp 페이지로 전송됨
		request.setAttribute("pageNum", pageNum); // request에서 가져온 파라미터 pageNum이 없으면 1, 있으면 해당 번호 저장.
		request.setAttribute("total_page", total_page); //위에서 계산한 총 페이지수 저장
		request.setAttribute("total_record", total_record); //getListCount로 가져온 게시글 수 저장
		request.setAttribute("boardlist", boardlist); //getBoardList로 가져온 리스트 boardlist 요소에 저장
	}
	//id를 통해 name 가져오기
	public void requestLoginName(HttpServletRequest request) {
		String id = request.getParameter("id");
		BoardDAO dao = BoardDAO.getInstance();
		String name = dao.getLoginNameById(id);
		
		request.setAttribute("name", name);
	}
	//신규 게시글의 정보를 받아 등록일시, IP 추가하여 DB로 업로드
	public void requestBoardWrite(HttpServletRequest request) throws IOException {
		BoardDAO dao = BoardDAO.getInstance();
		BoardDTO board = new BoardDTO();

		String realFolder = "D:\\Study_Programming\\Web_programming\\SaltLand\\WebContent\\resources\\image";
		int maxSize = 5*1024*1024;
		String encType = "utf-8";
		
		MultipartRequest multi = new MultipartRequest(request, realFolder, maxSize, encType, new DefaultFileRenamePolicy());
		
		Enumeration files = multi.getFileNames();
		String fname = (String)files.nextElement();
		String fileName = multi.getFilesystemName(fname);
		
		int num = dao.getNumRecentBoard();
		
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy/MM/dd(HH:mm:ss)");
		String regist_day = formatter.format(new java.util.Date());		
		
		board.setNum(num);
		board.setId(multi.getParameter("id"));
		board.setName(multi.getParameter("name"));
		board.setCategory(multi.getParameter("category"));
		board.setTitle(multi.getParameter("title"));
		board.setContent(multi.getParameter("content"));
		board.setFileName(fileName);
		board.setRegist_day(regist_day);
		board.setIp(request.getRemoteAddr());
		
		dao.insertBoard(board);
	}
	//선택된 게시글 정보를 번호 바탕으로 가져옴
	public void requestSelectedBoard(HttpServletRequest request) {
		BoardDAO dao = BoardDAO.getInstance();
		int num = Integer.parseInt(request.getParameter("num"));
		
		BoardDTO board = new BoardDTO();
		board = dao.getBoardByNum(num);
		
		request.setAttribute("num", num);
		request.setAttribute("board", board);
	}
	//수정 내용 dto에 저장 및 DB에 업데이트
	public void requestBoardUpdate(HttpServletRequest request) throws IOException {
		int num = Integer.parseInt(request.getParameter("num"));
		
		BoardDAO dao = BoardDAO.getInstance();
		BoardDTO board = new BoardDTO();
		
		String realFolder = "D:\\Study_Programming\\Web_programming\\SaltLand\\WebContent\\resources\\image";
		int maxSize = 5*1024*1024;
		String encType = "utf-8";
		
		MultipartRequest multi = new MultipartRequest(request, realFolder, maxSize, encType, new DefaultFileRenamePolicy());
		
		Enumeration files = multi.getFileNames();
		String fname = (String)files.nextElement();
		String fileName = multi.getFilesystemName(fname);
		
		board.setNum(num);
		board.setName(multi.getParameter("name"));
		board.setCategory(multi.getParameter("category"));
		board.setTitle(multi.getParameter("title"));
		board.setFileName(fileName);
		board.setContent(multi.getParameter("content"));
		
		dao.updateBoard(board);
	}
	//선택된 글 삭제하기
	public void requestBoardDelete(HttpServletRequest request) {
		BoardDAO dao = BoardDAO.getInstance();
		
		int num = Integer.parseInt(request.getParameter("num"));

		dao.deleteBoard(num);
	}
	
	//// ** 어트랙션 관련 메서드 **
	
	//검색기능으로 어트랙션 리스트 불러오기
	public void requestAtrList(HttpServletRequest request) {
		AttractionDAO dao = AttractionDAO.getInstance();
		List<AttractionDTO> atrList = new ArrayList<AttractionDTO>();
		
		String tag = request.getParameter("tag_search");
		String age = request.getParameter("age_search");
		String tall = request.getParameter("tall_search");
		
		atrList = dao.getAttractions(tag, age, tall);
		
		request.setAttribute("atrList", atrList);
		request.setAttribute("tag_search", tag);
		request.setAttribute("age_search", age);
		request.setAttribute("tall_search", tall);
	}
	//신규 어트랙션 정보를 받아 DB로 업로드
	public void requestAddAtr(HttpServletRequest request) throws IOException{
		AttractionDAO dao = AttractionDAO.getInstance();
		AttractionDTO atr = new AttractionDTO();
		
		String realFolder = "D:\\Study_Programming\\Web_programming\\SaltLand\\WebContent\\resources\\image";
		int maxSize = 5*1024*1024;
		String encType = "utf-8";
		
		MultipartRequest multi = new MultipartRequest(request, realFolder, maxSize, encType, new DefaultFileRenamePolicy());
		
		Enumeration files = multi.getFileNames();
		String fname = (String)files.nextElement();
		String fileName = multi.getFilesystemName(fname);
		
		atr.setName(multi.getParameter("name"));
		atr.setInfo(multi.getParameter("info"));
		atr.setTag(multi.getParameter("tag"));
		atr.setRide(multi.getParameter("ride"));
		atr.setAge(multi.getParameter("age"));
		atr.setTall(multi.getParameter("tall"));
		atr.setFilename(fileName);
		
		dao.addAttraction(atr);
	}
	//선택한 게시글 정보를 번호를 바탕으로 가져오기
	public void requestSelectedAtr(HttpServletRequest request) {
		AttractionDAO dao = AttractionDAO.getInstance();
		AttractionDTO atr = new AttractionDTO();
			
		int id = Integer.parseInt(request.getParameter("id"));
		atr = dao.getAttractionById(id);
			
		request.setAttribute("attraction", atr);
	}
	//수정 내용 dto에 저장 및 DB에 업데이트
	public void requestUpdateAtr(HttpServletRequest request) throws IOException{
		int id=0;
		
		try {
			id = Integer.parseInt(request.getParameter("id"));
			//id = Integer.parseInt(request.getParameter("id"));
		}catch(NumberFormatException ne) {
			System.out.println("controller requestUpdateAtr error : "+ne);
		}catch(Exception e) {
			System.out.println("controller requestUpdateAtr error : "+e);
		}
		
		AttractionDAO dao = AttractionDAO.getInstance();
		AttractionDTO atr = new AttractionDTO();
		
		String realFolder = "D:\\Study_Programming\\Web_programming\\SaltLand\\WebContent\\resources\\image";
		int maxSize = 5*1024*1024;
		String encType = "utf-8";
		
		MultipartRequest multi = new MultipartRequest(request, realFolder, maxSize, encType, new DefaultFileRenamePolicy());
		
		Enumeration files = multi.getFileNames();
		String fname = (String)files.nextElement();
		String fileName = multi.getFilesystemName(fname);
		
		atr.setId(id);
		atr.setName(multi.getParameter("name"));
		atr.setInfo(multi.getParameter("info"));
		atr.setTag(multi.getParameter("tag"));
		atr.setRide(multi.getParameter("ride"));
		atr.setAge(multi.getParameter("age"));
		atr.setTall(multi.getParameter("tall"));
		if(fileName!=null) {
			atr.setFilename(fileName);
		}else {
			atr.setFilename(null);
		}
		dao.updateAttraction(atr);
	}
	//어트랙션 삭제 기능
	public void requestAtrDelete(HttpServletRequest request) {
		AttractionDAO dao = AttractionDAO.getInstance();
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		dao.deleteAttraction(id);
	}
	
	// ** total **
	
	//메인페이지에 최신공지, 최신어트랙션 정보 불러오기
	public void RequestMainPage(HttpServletRequest request) {
		//최신 공지사항 제목 list
		BoardDAO boardDao = BoardDAO.getInstance();
		List<BoardDTO> boardlist = new ArrayList<BoardDTO>();
		int limit=MAINPAGENOTICE;
		
		boardlist = boardDao.getMPBoardList(limit);
		
		//최신 어트랙션 list
		AttractionDAO atrDao = AttractionDAO.getInstance();
		List<AttractionDTO> atrList = new ArrayList<AttractionDTO>();
		int atrLimit = MAINPAGEATR;
		
		atrList = atrDao.getMPAttractions(atrLimit);
		
		request.setAttribute("boardlist", boardlist);
		request.setAttribute("atrList", atrList);
	}
	
	
	
}
