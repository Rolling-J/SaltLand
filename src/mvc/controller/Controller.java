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
	
		
	// ** ��ü **
		//���� ������ ����ϱ�
		if(command.equals("/MainPage.do")) {
			RequestMainPage(request);
			RequestDispatcher rd = request.getRequestDispatcher("./main.jsp");
			rd.forward(request, response);
		}
		//���� ���� ������ ����ϱ�
		else if(command.equals("/BusInfo.do")) {
			RequestDispatcher rd = request.getRequestDispatcher("./bus.jsp");
			rd.forward(request, response);
		}
	// ** �Խ��� ��Ʈ�� **
		//��ϵ� �� ��� ������ ����ϱ�
		else if(command.equals("/BoardListAction.do")) {
			requestBoardList(request);
			RequestDispatcher rd = request.getRequestDispatcher("./noticeboard/boardList.jsp");
			rd.forward(request, response);
		}
		// �� ��� ������ ����ϱ�
		else if(command.equals("/BoardWriteForm.do")){ //����Ʈ �������� �ۼ� ��ư���κ��� request ����
			requestLoginName(request);
			RequestDispatcher rd = request.getRequestDispatcher("./noticeboard/addNotice.jsp");
			rd.forward(request, response);
		}
		// ���ο� �� ����ϱ�
		else if(command.equals("/BoardWriteAction.do")){ //����������κ��� request ����
			requestBoardWrite(request); //�Խñ� ���ε� �޼���
			RequestDispatcher rd = request.getRequestDispatcher("/BoardListAction.do"); 
			//���ε� �޼��� �۵� �� �Խñ� ������� ����.
			rd.forward(request, response);
		}
		//���õ� �� �� ������ ��������
		else if(command.equals("/BoardViewAction.do")) {
			requestSelectedBoard(request);
			RequestDispatcher rd = request.getRequestDispatcher("/DetailView.do");
			rd.forward(request, response);
		}
		//�� �� ������ ����ϱ�
		else if(command.equals("/DetailView.do")) {
			RequestDispatcher rd = request.getRequestDispatcher("./noticeboard/noticeDetail.jsp");
			rd.forward(request, response);
		}
		//���õ� ���� ������ ��������
		else if(command.equals("/UpdateViewAction.do")) {
			requestSelectedBoard(request);
			RequestDispatcher rd = request.getRequestDispatcher("/UpdateView.do");
			rd.forward(request, response);
		}
		//�� ���� ������ ����ϱ�
		else if(command.equals("/UpdateView.do")) {
			RequestDispatcher rd = request.getRequestDispatcher("./noticeboard/updateNotice.jsp");
			rd.forward(request, response);
		}
		//���õ� ���� ���� ������Ʈ
		else if(command.equals("/BoardUpdateAction.do")){
			requestBoardUpdate(request);
			RequestDispatcher rd = request.getRequestDispatcher("/BoardListAction.do");
			rd.forward(request, response);
		}
		//���õ� �� �����ϱ�
		else if(command.equals("/BoardDeleteAction.do")) {
			requestBoardDelete(request);
			RequestDispatcher rd = request.getRequestDispatcher("/BoardListAction.do");
			rd.forward(request, response);
		}
		
		
	// ** ��Ʈ���� ��Ʈ�� **
		//��Ʈ���� ��� ������ ���
		if(command.equals("/AttractionList.do")) {
			requestAtrList(request);
			RequestDispatcher rd = request.getRequestDispatcher("./attraction/attractions.jsp");
			rd.forward(request, response);
		}
		//��Ʈ���� ��� ������ ���
		else if(command.equals("/AddAttractionForm.do")) {
			RequestDispatcher rd = request.getRequestDispatcher("./attraction/addAttraction.jsp");
			rd.forward(request, response);
		}
		//��Ʈ���� �� ������ ���
		else if(command.equals("/AttractionDetailView.do")) {
			requestSelectedAtr(request);
			RequestDispatcher rd = request.getRequestDispatcher("./attraction/attractionDetail.jsp");
			rd.forward(request, response);
		}
		//��Ʈ���� ���� �� ���� ������ ���
		else if(command.equals("/EditAttractionView.do")) {
			requestAtrList(request);
			RequestDispatcher rd = request.getRequestDispatcher("./attraction/editAttraction.jsp");
			rd.forward(request, response);
		}
		//��Ʈ���� �� ���� ������ ���
		else if(command.equals("/UpdateAttractionForm.do")) {
			requestSelectedAtr(request);
			RequestDispatcher rd = request.getRequestDispatcher("./attraction/updateAttraction.jsp");
			rd.forward(request, response);
		}
				
		//��Ʈ���� ���
		else if(command.equals("/AddAttractionAction.do")) {
			requestAddAtr(request);
			RequestDispatcher rd = request.getRequestDispatcher("/AttractionList.do");
			rd.forward(request, response);
		}
		//��Ʈ���� ���� ������Ʈ
		else if(command.equals("/UpdateAttractionAction.do")) {
			requestUpdateAtr(request);
			RequestDispatcher rd = request.getRequestDispatcher("/AttractionList.do");
			rd.forward(request, response);
		}
		//��Ʈ���� ����
		else if(command.equals("/DeleteAttraction.do")) {
			requestAtrDelete(request);
			RequestDispatcher rd = request.getRequestDispatcher("/AttractionList.do");
			rd.forward(request, response);
		}
		
		// ** ����� ��Ʈ�� **
		//�α��� ������ ���
		if(command.equals("/LoginView.do")) {
			requestGetResultMessage(request);
			RequestDispatcher rd = request.getRequestDispatcher("./member/login.jsp");
			rd.forward(request, response);
		}
		//ȸ������ ������ ���
		else if(command.equals("/RegisterForm.do")) {
			RequestDispatcher rd = request.getRequestDispatcher("./member/register.jsp");
			rd.forward(request, response);
		}
		//ȸ�� ���� ������ ���
		else if(command.equals("/MemberDetailView.do")) {
			requestMemberAndTicketInfo(request);
			RequestDispatcher rd = request.getRequestDispatcher("./member/memberDetail.jsp");
			rd.forward(request, response);
		}
		//ȸ�� ���� ���� ������ ���
		else if(command.equals("/UpdateMemberForm.do")) {
			requestMemberAndTicketInfo(request);
			RequestDispatcher rd = request.getRequestDispatcher("./member/updateMember.jsp");
			rd.forward(request, response);
		}
		//��� ������ ���
		else if(command.equals("/MemberResultView.do")) {
			requestGetResultMessage(request);
			RequestDispatcher rd = request.getRequestDispatcher("./member/resultMember.jsp");
			rd.forward(request, response);
		}
		//�α��� ���μ���
		else if(command.equals("/LoginAction.do")) {
			requestLoginOkOrNot(request);
			RequestDispatcher rd = request.getRequestDispatcher("./member/processLoginMember.jsp");
			rd.forward(request, response);
		}
		//�α׾ƿ� ���μ���
		else if(command.equals("/LogoutAction.do")) {
			RequestDispatcher rd = request.getRequestDispatcher("./member/processLogoutMember.jsp");
			rd.forward(request, response);
		}
		//ȸ������
		else if(command.equals("/AddMemberAction.do")) {
			requestAddMember(request);
			RequestDispatcher rd = request.getRequestDispatcher("/MemberResultView.do");
			rd.forward(request, response);
		}
		//ȸ�� ���� ���� ������Ʈ
		else if(command.equals("/UpdateMemberAction.do")) {
			requestMemberUpdate(request);
			RequestDispatcher rd = request.getRequestDispatcher("/MemberResultView.do");
			rd.forward(request, response);
		}
		//ȸ�� Ż��
		else if(command.equals("/DeleteMemberAction.do")) {
			requestMemberDelete(request);
			RequestDispatcher rd = request.getRequestDispatcher("/LogoutAction.do");
			rd.forward(request, response);
		}
		
		// ** Ƽ�� ���� ��Ʈ�� **
		//���� ������ ���
		else if(command.equals("/ReservationFormView.do")) {
			RequestDispatcher rd = request.getRequestDispatcher("./ticket/reservation.jsp");
			rd.forward(request, response);
		}
		//Ƽ�� �������� ���
		else if(command.equals("/TicketDetailView.do")) {
			requestTicketInfo(request);
			RequestDispatcher rd = request.getRequestDispatcher("./ticket/ticketDetail.jsp");
			rd.forward(request, response);
		}
		//�˾� ����
		else if(command.equals("/LoginPopup.do")) {
			RequestDispatcher rd = request.getRequestDispatcher("./ticket/popupLogin.jsp");
			rd.forward(request, response);
		}
		//Ƽ�� ����
		else if(command.equals("/ReservateTicket.do")) {
			requestReserving(request);
			RequestDispatcher rd = request.getRequestDispatcher("/MemberDetailView.do");
			rd.forward(request, response);
		}
		//Ƽ�� ���
		else if(command.equals("/CancleReservation.do")) {
			requestCancleReservation(request);
			RequestDispatcher rd = request.getRequestDispatcher("/MemberDetailView.do");
			rd.forward(request, response);
		}
	}
	
	////** ����� ���� �޼��� **
	
	//�α��� Ȯ��
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

	//��� �޼��� ��
	public void requestGetResultMessage(HttpServletRequest request) {
		if(request.getAttribute("msg")==null) {
			String msg=request.getParameter("msg");
			request.setAttribute("msg", msg);
		}else {
			String msg=String.valueOf(request.getAttribute("msg")); //error �߻�. 
			//error message : class java.lang.Integer cannot be cast to class java.lang.String (java.lang.Integer and java.lang.String are in module java.base of loader 'bootstrap')
			request.setAttribute("msg", msg);
		}
	}
	
	//ȸ������
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
	//ID �������� ����� ���� �ҷ����� (����¡ ��� �߰�)
	public void requestMemberAndTicketInfo(HttpServletRequest request) {
		String id = request.getParameter("sessionId");
		
		// ��� ���� ���� ��������
		MemberDAO dao = MemberDAO.getInstance();
		MemberDTO member = new MemberDTO();
		
		member = dao.getMemberById(id);
		
		// �ش� ȸ���� Ƽ�� ���� ���� �������� (����¡ ��� �߰�)
		TicketDAO ticketdao = TicketDAO.getInstance();
		List<TicketDTO> ticketList = new ArrayList<TicketDTO>();
		int pageNum=1;
		if(request.getParameter("pageNum") != null) { //������ ��ư�� Ŭ������ ��� ������ ������ �Ķ���� get
			pageNum=Integer.parseInt(request.getParameter("pageNum"));
		}
		int limit=TICKETLISTCOUNT;
		int total_ticket = ticketdao.getTicketCount(id);
		
		
		ticketList = ticketdao.getTicketList(pageNum, limit, id); //�������� Ƽ�ϸ���Ʈ ��������
		
		//�� ������ �� ��� (total_page ������ ���)
		int total_page;
		if(total_ticket % limit == 0) { //������ �������� Ƽ�� ���� �������� ǥ�ð����� ���� ��ġ�ϴ� ���
			total_page = total_ticket/limit;
			Math.floor(total_page);	
		}else { //������ �������� Ƽ�� ���� �������� ǥ�ð����� ���� �̴��ϴ� ���
			total_page = total_ticket/limit;
			Math.floor(total_page);
			total_page = total_page + 1;
		}
		
		request.setAttribute("member", member); // ����� ���� ����
		request.setAttribute("ticketList", ticketList); // �������� ǥ�õ� Ƽ�ϸ���Ʈ ����
		request.setAttribute("total_ticket", total_ticket); // �������� ǥ�õ� Ƽ�ϸ���Ʈ ����
		request.setAttribute("pageNum", pageNum); // request���� ������ �Ķ���� pageNum�� ������ 1, ������ �ش� ��ȣ ����.
		request.setAttribute("total_page", total_page); // ����� �� �������� ����
		
	}
	//���� ���� dto�� ���� �� DB ������Ʈ
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

		System.out.println("requestMemberUpdate() test. id : "+id+" , birth : "+birth); //sessionId �Ķ���� ���� �׽�Ʈ
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
	//ȸ�� Ż��
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
	
	//// ** Ƽ�� ���� �޼��� **
	
	//Ƽ�� �� ���� ��������
	public void requestTicketInfo(HttpServletRequest request) {
		TicketDAO dao = TicketDAO.getInstance();
		TicketDTO ticket = new TicketDTO();
		int ticketNumber = Integer.parseInt(request.getParameter("reserve_num"));
		ticket = dao.getTicketInfo(ticketNumber);
		
		request.setAttribute("ticket", ticket);
	}
	//Ƽ�� �����ϱ�
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
	//Ƽ�� ����ϱ� (����)
	public void requestCancleReservation(HttpServletRequest request) {
		TicketDAO dao = TicketDAO.getInstance();
		
		String id = request.getParameter("sessionId");
		int ticketNumber = Integer.parseInt(request.getParameter("reserve_num"));
		System.out.println("requestCancleReservation message - member ID : "+id); //sessionId �Ķ���� ���� �׽�Ʈ
		dao.cancleTicket(ticketNumber);
	}
	
	//// ** �Խ��� ���� �޼��� **
	
	//����Ʈ ������ ��� ���� ���� �Ϻ� ���� ���
	public void requestBoardList(HttpServletRequest request) { //�Խ��� �� ��� ��� ������Ʈ
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
		// dao.getListCount : board ���̺��� ���ڵ� �� (���ǿ� �´� �Խ����� �� ���� sql���� ����Ͽ� ���ڷ� ������)
		boardlist = dao.getBoardList(pageNum, limit, category, text);
		// dao.getBoardList : board ���̺��� ���ڵ带 num ��ȣ �������(�ֽű� ����) ��������(�� �������� �� �Խñ� ��� ��������)
		int total_page;
		
		if(total_record % limit == 0) {
			total_page = total_record/limit;
			Math.floor(total_page);	
		}else {
			total_page = total_record/limit;
			Math.floor(total_page);
			total_page = total_page + 1;
		}
		
		// request ��� �ۼ�, board.list.jsp �������� ���۵�
		request.setAttribute("pageNum", pageNum); // request���� ������ �Ķ���� pageNum�� ������ 1, ������ �ش� ��ȣ ����.
		request.setAttribute("total_page", total_page); //������ ����� �� �������� ����
		request.setAttribute("total_record", total_record); //getListCount�� ������ �Խñ� �� ����
		request.setAttribute("boardlist", boardlist); //getBoardList�� ������ ����Ʈ boardlist ��ҿ� ����
	}
	//id�� ���� name ��������
	public void requestLoginName(HttpServletRequest request) {
		String id = request.getParameter("id");
		BoardDAO dao = BoardDAO.getInstance();
		String name = dao.getLoginNameById(id);
		
		request.setAttribute("name", name);
	}
	//�ű� �Խñ��� ������ �޾� ����Ͻ�, IP �߰��Ͽ� DB�� ���ε�
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
	//���õ� �Խñ� ������ ��ȣ �������� ������
	public void requestSelectedBoard(HttpServletRequest request) {
		BoardDAO dao = BoardDAO.getInstance();
		int num = Integer.parseInt(request.getParameter("num"));
		
		BoardDTO board = new BoardDTO();
		board = dao.getBoardByNum(num);
		
		request.setAttribute("num", num);
		request.setAttribute("board", board);
	}
	//���� ���� dto�� ���� �� DB�� ������Ʈ
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
	//���õ� �� �����ϱ�
	public void requestBoardDelete(HttpServletRequest request) {
		BoardDAO dao = BoardDAO.getInstance();
		
		int num = Integer.parseInt(request.getParameter("num"));

		dao.deleteBoard(num);
	}
	
	//// ** ��Ʈ���� ���� �޼��� **
	
	//�˻�������� ��Ʈ���� ����Ʈ �ҷ�����
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
	//�ű� ��Ʈ���� ������ �޾� DB�� ���ε�
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
	//������ �Խñ� ������ ��ȣ�� �������� ��������
	public void requestSelectedAtr(HttpServletRequest request) {
		AttractionDAO dao = AttractionDAO.getInstance();
		AttractionDTO atr = new AttractionDTO();
			
		int id = Integer.parseInt(request.getParameter("id"));
		atr = dao.getAttractionById(id);
			
		request.setAttribute("attraction", atr);
	}
	//���� ���� dto�� ���� �� DB�� ������Ʈ
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
	//��Ʈ���� ���� ���
	public void requestAtrDelete(HttpServletRequest request) {
		AttractionDAO dao = AttractionDAO.getInstance();
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		dao.deleteAttraction(id);
	}
	
	// ** total **
	
	//������������ �ֽŰ���, �ֽž�Ʈ���� ���� �ҷ�����
	public void RequestMainPage(HttpServletRequest request) {
		//�ֽ� �������� ���� list
		BoardDAO boardDao = BoardDAO.getInstance();
		List<BoardDTO> boardlist = new ArrayList<BoardDTO>();
		int limit=MAINPAGENOTICE;
		
		boardlist = boardDao.getMPBoardList(limit);
		
		//�ֽ� ��Ʈ���� list
		AttractionDAO atrDao = AttractionDAO.getInstance();
		List<AttractionDTO> atrList = new ArrayList<AttractionDTO>();
		int atrLimit = MAINPAGEATR;
		
		atrList = atrDao.getMPAttractions(atrLimit);
		
		request.setAttribute("boardlist", boardlist);
		request.setAttribute("atrList", atrList);
	}
	
	
	
}
