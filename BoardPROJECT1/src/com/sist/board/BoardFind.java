package com.sist.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.dao.BoardDAO;
import com.sist.dao.BoardVO;


@WebServlet("/BoardFind")
public class BoardFind extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void dopost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");  // 한글변화은 dopost에서만 적용가능!! doget에서는 적용이 안된다!!!!
		PrintWriter out=response.getWriter(); //브라우저에 데이터를 뿌리기 전에 브라우저의 어떤 위치에 뿌릴지 위치선정..
		// 어디에 뿌릴지 out이 알려줌
		// 어디에 저장할 지 알려줌
		
		
		//사용자가 요청한 페이지를 받는다=> 받은 페이지를 list=dao.boardListData(1); 여기에 넣으면 ok
		String strPage=request.getParameter("page");
		
		//맨처음에 한번은 페이지를 입력하지 않기때문에 null값이다.
		if(strPage==null)
			strPage="1"; //항상 디폴트로  1 페이지를 줘야한다.
		
		
		int curpage=Integer.parseInt(strPage); 
		
		
		
		BoardDAO dao=new BoardDAO();
		ArrayList<BoardVO> list=dao.boardListData(curpage); // 1페이지!
		
		//총페이지 받기
		int totalPage=dao.boardTotalPage(); //html 출력하기 전에 데이터 값을 모두 받아놔야한다.
		
	/*
	 * 사용자 입력값 받고
	 * 오라클에서 값 받고
	 * MVC = 모델수행(자바) => 뷰수행(HTML)
	 * 폼을 만들고
	 * 폼에 뿌려준다
	 *
	 */
		
		// HTML 에 출력하기!
		out.println("<html>");
		out.println("<head>");
		out.println("<link rel=stylesheet href=\"CSS/table.css\">");
		out.println("</head>");
		out.println("<body>");
		out.println("<center>");
		out.println("<h1> 자유게시판</h1>");
		
		// 글쓰기 => 클릭하면 BoardInsert 이 파일을 가져옴. 
		out.println("<table id=\"table_content\" width=700>");
		out.println("<tr>");
		out.println("<td align=right>");
		out.println("<a href=\"BoardInsert\">글쓰기</a>");
		out.println("</td>");
		out.println("</tr>");
		out.println("<table>");
		
		
		out.println("<table id=\"table_content\" width=700>");
		out.println("<tr>");
		out.println("<th width=10%>번호</th>");
		out.println("<th width=45%>제목</th>");
		out.println("<th width=15%>이름</th>");
		out.println("<th width=20%>작성일</th>");
		out.println("<th width=10%>조회수</th>");
		out.println("</tr>");
		
		for(BoardVO vo:list) // 10바퀴 돌기!
		{
			out.println("<tr class=dataTr>");
			out.println("<td width=10% align=center>"+vo.getNo()+"</td>");
			out.println("<td width=45% align=left>");
			
			out.println("<a href=BoardDetailServlet?no="+vo.getNo()+">");
			out.println(vo.getSubject()+"</a>");
			
			
			
			out.println("</td>");
			
			out.println("<td width=15% align=center>"+vo.getName()+"</td>");
			out.println("<td width=20% align=center>"+vo.getRegdate()+"</td>");
			out.println("<td width=10% align=center>"+vo.getHit()+"</td>");
			out.println("</tr>");
			
		}
		
		
		
		out.println("</table>");
		
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		response.setContentType("text/html;charset=UTF-8"); //HTML로 출력값이 필요! ==> PWD확인하려고!, 
		PrintWriter out=response.getWriter();
		
		//사용자가 보내준 값을 저장해 놔야..
		// 사용자가 보내준 값이 한글이라면 예외처리 해주고 가야
		try
		{
			request.setCharacterEncoding("UTF-8"); //한글변환
			
		}catch(Exception ex) {}
				
		
		String name=request.getParameter("name");
		String subject=request.getParameter("subject");
		String content=request.getParameter("content");
		
		
		
		
		
		
		
		
		
	}

}
