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
		
		//등록된 글 목록 페이지 출력하기
		if(command.equals("/BoardListAction.do")) {
			requestBoardList(request);
			RequestDispatcher rd = request.getRequestDispatcher("./board/list.jsp");
			rd.forward(request, response);
		}
		
		// 글 등록 페이지 출력하기
		else if(command.equals("/BoardWriteForm.do")){ //리스트 페이지의 작성 버튼으로부터 request 받음
		requestLoginName(request);
		RequestDispatcher rd = request.getRequestDispatcher("./board/writeForm.jsp");
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
			requestBoardView(request);
			RequestDispatcher rd = request.getRequestDispatcher("/BoardView.do");
			rd.forward(request, response);
		}
		
		//글 상세 페이지 출력하기
		else if(command.equals("/BoardView.do")) {
		//조회수 증가 메서드 사용 필요
		RequestDispatcher rd = request.getRequestDispatcher("./board/view.jsp");
		rd.forward(request, response);
		}
		
		//선택된 글의 수정 업데이트
		else if(command.equals("/BoardUpdateAction.do")){
		requestBoardUpdate(request);
		RequestDispatcher rd = request.getRequestDispatcher("./");
		rd.forward(request, response);
		}

		//선택된 글 삭제하기
		else if(command.equals("/BoardDeleteAction.do")) {
		requestBoardDelete(request);
		RequestDispatcher rd = request.getRequestDispatcher("/BoardListAction.do");
		rd.forward(request, response);
		}
		
		
	}
	//command.equals("/BoardListAction.do") true일때 작동, ./board/list.jsp로 request 전송
	public void requestBoardList(HttpServletRequest request) { //게시판 글 목록 출력 리퀘스트
		BoardDAO dao = BoardDAO.getInstance();
		List<BoardDTO> boardlist = new ArrayList<BoardDTO>();
		
		int pageNum=1;
		int limit=LISTCOUNT; //listcount = 위에서 지정한 값 5.
		
		if(request.getParameter("pageNum") != null) {
			pageNum=Integer.parseInt(request.getParameter("pageNum"));
		}
		
		String items = request.getParameter("items");
		String text = request.getParameter("text");
		
		int total_record = dao.getListCount(items, text);
		// dao.getListCount : board 테이블의 레코드 수 (조건에 맞는 게시판의 글 수를 sql에서 계산하여 숫자로 가져옴)
		boardlist = dao.getBoardList(pageNum, limit, items, text);
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
	//command.equals("/BoardWriteAction.do") true (등록페이지에서 등록 클릭)때 작동
	//게시글의 리퀘스트를 받아 등록일시, IP 추가하여 DAO로 업로드
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
	
	//command.equals("/BoardListAction.do") true일때 작동. board/view.jsp 로 리퀘스트 전송
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
	//command.equals("/BoardUpdateAction.do" true일때 작동.
	//기존 내용, 수정 내용 모두 dao에 저장
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
		
		dao.updateBoard(board); //dao에 수정내용 업데이트
	}
	//선택된 글 삭제하기. command.equals("/BoardDeleteAction.do" true일때 작동.
	public void requestBoardDelete(HttpServletRequest request) {
		int num = Integer.parseInt(request.getParameter("num"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));	
		
		BoardDAO dao = BoardDAO.getInstance();
		dao.deleteBoard(num);
	}
	
	
	
	
	
	
	
	
	
}
