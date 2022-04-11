package mvc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

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

public class Controller extends HttpServlet{
	private static final long serialVersionUID = 1L;
	static final int LISTCOUNT = 10;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
		
		response.setContentType("text/html; charset = utf-8");
		request.setCharacterEncoding("utf-8");
	

		
		
	// ** �Խ��� ��Ʈ�� **
		//��ϵ� �� ��� ������ ����ϱ�
		if(command.equals("/BoardListAction.do")) {
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
			RequestDispatcher rd = request.getRequestDispatcher("./AttractionList.do");
			rd.forward(request, response);
		}
		//��Ʈ���� ���� ������Ʈ
		else if(command.equals("/UpdateAttractionAction.do")) {
			requestUpdateAtr(request);
			RequestDispatcher rd = request.getRequestDispatcher("./AttractionList.do");
			rd.forward(request, response);
		}
		//��Ʈ���� ����
		else if(command.equals("/DeleteAttraction.do")) {
			requestAtrDelete(request);
			RequestDispatcher rd = request.getRequestDispatcher("./AttractionList.do");
			rd.forward(request, response);
		}
		
		
		
		// ** ����� ��Ʈ�� **
		//�α��� ������ ���
		if(command.equals("/LoginView.do")) {
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
			requestMemberId(request);
			RequestDispatcher rd = request.getRequestDispatcher("./member/boardList.jsp");
			rd.forward(request, response);
		}
		//ȸ�� ���� ���� ������ ���
		else if(command.equals("/UpdateMemberForm.do")) {
			requestMemberId(request);
			RequestDispatcher rd = request.getRequestDispatcher("./member/updateMember.jsp");
			rd.forward(request, response);
		}
		//��� ������ ���
		else if(command.equals("/MemberResultView.do")) {
			
			RequestDispatcher rd = request.getRequestDispatcher("./member/resultMember.jsp");
			rd.forward(request, response);
		}
		//�α��� ���μ���
		else if(command.equals("/LoginAction.do")) {
			
			RequestDispatcher rd = request.getRequestDispatcher("./MemberResultView.do?msg=2");
			rd.forward(request, response);
		}
		//�α׾ƿ� ���μ���
		else if(command.equals("/LogoutAction.do")) {
			
			RequestDispatcher rd = request.getRequestDispatcher("./member/resultMember.jsp");
			rd.forward(request, response);
		}
		//ȸ������
		else if(command.equals("/LoginAction.do")) {
			
			RequestDispatcher rd = request.getRequestDispatcher("./MemberResultView.do");
			rd.forward(request, response);
		}
		//ȸ�� ���� ���� ������Ʈ
		else if(command.equals("/UpdateMemberAction.do")) {
			requestMemberUpdate(request);
			RequestDispatcher rd = request.getRequestDispatcher("./MemberResultView.do");
			rd.forward(request, response);
		}
		//ȸ�� Ż��
		else if(command.equals("/DeleteMemberAction.do")) {
			requestMemberDelete(request);
			RequestDispatcher rd = request.getRequestDispatcher("./MemberResultView.do");
			rd.forward(request, response);
		}
	}
	
	////** ����� ���� �޼��� **
	
	//ȸ������
	
	//ID �������� ����� ���� �ҷ�����
	public void requestMemberId(HttpServletRequest request) {
		
	}
	//���� ���� dto�� ���� �� DB ������Ʈ
	public void requestMemberUpdate(HttpServletRequest request) {
		
	}
	//ȸ�� Ż��
	public void requestMemberDelete(HttpServletRequest request) {
		MemberDAO dao = MemberDAO.getInstance();
	}
	
	//// ** �Խ��� ���� �޼��� **
	
	//����Ʈ ������ ��� ���� ���� �Ϻ� ���� ���
	public void requestBoardList(HttpServletRequest request) { //�Խ��� �� ��� ��� ������Ʈ
		BoardDAO dao = BoardDAO.getInstance();
		List<BoardDTO> boardlist = new ArrayList<BoardDTO>();
		
		int pageNum=1;
		int limit=LISTCOUNT;
		
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
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		
		BoardDTO board = new BoardDTO();
		board = dao.getBoardByNum(num);
		
		request.setAttribute("num", num);
		request.setAttribute("page", pageNum);
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
		int id = Integer.parseInt(request.getParameter("id"));
		
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
		atr.setFilename(fileName);
		
		dao.updateAttraction(atr);
	}
	//��Ʈ���� ���� ���
	public void requestAtrDelete(HttpServletRequest request) {
		AttractionDAO dao = AttractionDAO.getInstance();
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		dao.deleteAttraction(id);
	}
	
	
	
	
	
}
