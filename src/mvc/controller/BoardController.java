package mvc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.model.BoardDTO;
import mvc.model.BoardDAO;

public class BoardController extends HttpServlet{
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
		
		//��ϵ� �� ��� ������ ����ϱ�
		if(command.equals("/BoardListAction.do")) {
			requestBoardList(request);
			RequestDispatcher rd = request.getRequestDispatcher("./board/list.jsp");
			rd.forward(request, response);
		}
		
		// �� ��� ������ ����ϱ�
		else if(command.equals("/BoardWriteForm.do")){ //����Ʈ �������� �ۼ� ��ư���κ��� request ����
		requestLoginName(request);
		RequestDispatcher rd = request.getRequestDispatcher("./board/writeForm.jsp");
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
			requestBoardView(request);
			RequestDispatcher rd = request.getRequestDispatcher("/BoardView.do");
			rd.forward(request, response);
		}
		
		//�� �� ������ ����ϱ�
		else if(command.equals("/BoardView.do")) {
		//��ȸ�� ���� �޼��� ��� �ʿ�
		RequestDispatcher rd = request.getRequestDispatcher("./board/view.jsp");
		rd.forward(request, response);
		}
		
		//���õ� ���� ���� ������Ʈ
		else if(command.equals("/BoardUpdateAction.do")){
		requestBoardUpdate(request);
		RequestDispatcher rd = request.getRequestDispatcher("./");
		rd.forward(request, response);
		}

		//���õ� �� �����ϱ�
		else if(command.equals("/BoardDeleteAction.do")) {
		requestBoardDelete(request);
		RequestDispatcher rd = request.getRequestDispatcher("/BoardListAction.do");
		rd.forward(request, response);
		}
		
		
	}
	//command.equals("/BoardListAction.do") true�϶� �۵�, ./board/list.jsp�� request ����
	public void requestBoardList(HttpServletRequest request) { //�Խ��� �� ��� ��� ������Ʈ
		BoardDAO dao = BoardDAO.getInstance();
		List<BoardDTO> boardlist = new ArrayList<BoardDTO>();
		
		int pageNum=1;
		int limit=LISTCOUNT; //listcount = ������ ������ �� 5.
		
		if(request.getParameter("pageNum") != null) {
			pageNum=Integer.parseInt(request.getParameter("pageNum"));
		}
		
		String items = request.getParameter("items");
		String text = request.getParameter("text");
		
		int total_record = dao.getListCount(items, text);
		// dao.getListCount : board ���̺��� ���ڵ� �� (���ǿ� �´� �Խ����� �� ���� sql���� ����Ͽ� ���ڷ� ������)
		boardlist = dao.getBoardList(pageNum, limit, items, text);
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
	//command.equals("/BoardWriteAction.do") true (������������� ��� Ŭ��)�� �۵�
	//�Խñ��� ������Ʈ�� �޾� ����Ͻ�, IP �߰��Ͽ� DAO�� ���ε�
	public void requestBoardWrite(HttpServletRequest request) {
		BoardDAO dao = BoardDAO.getInstance();
		BoardDTO board = new BoardDTO();
		
		board.setId(request.getParameter("id"));
		board.setName(request.getParameter("name"));
		board.setTitle(request.getParameter("title"));
		board.setContent(request.getParameter("content"));
		
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy/MM/dd(HH:mm:ss)");
		String regist_day = formatter.format(new java.util.Date());
		
		board.setRegist_day(regist_day);
		board.setIp(request.getRemoteAddr());
		
		dao.insertBoard(board);
	}
	
	//command.equals("/BoardListAction.do") true�϶� �۵�. board/view.jsp �� ������Ʈ ����
	public void requestBoardView(HttpServletRequest request) {
		BoardDAO dao = BoardDAO.getInstance();
		int num = Integer.parseInt(request.getParameter("num"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		
		BoardDTO board = new BoardDTO();
		board = dao.getBoardByNum(num, pageNum);
		
		request.setAttribute("num", num);
		request.setAttribute("page", pageNum);
		request.setAttribute("board", board);
	}
	//command.equals("/BoardUpdateAction.do" true�϶� �۵�.
	//���� ����, ���� ���� ��� dao�� ����
	public void requestBoardUpdate(HttpServletRequest request) {
		int num = Integer.parseInt(request.getParameter("num"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		
		BoardDAO dao = BoardDAO.getInstance();
		BoardDTO board = new BoardDTO();
		
		board.setNum(num);
		board.setName(request.getParameter("name"));
		board.setCategory(request.getParameter("category"));
		board.setTitle(request.getParameter("title"));
		board.setContent(request.getParameter("content"));
		
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy/MM/dd(HH:mm:ss)");
		String regist_day = formatter.format(new java.util.Date());
		
		board.setRegist_day(regist_day);
		board.setIp(request.getRemoteAddr());
		
		dao.updateBoard(board); //dao�� �������� ������Ʈ
	}
	//���õ� �� �����ϱ�. command.equals("/BoardDeleteAction.do" true�϶� �۵�.
	public void requestBoardDelete(HttpServletRequest request) {
		int num = Integer.parseInt(request.getParameter("num"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));	
		
		BoardDAO dao = BoardDAO.getInstance();
		dao.deleteBoard(num);
	}
	
	
	
	
	
	
	
	
	
}
