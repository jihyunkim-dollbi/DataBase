package com.sist.board;

import java.io.*;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BoardListServlet
 */

/*
 * 
 * request, HttpServletResponse response
 * =======						========
 * 요청-사용자로부터 값을 받음  			응답!- 페이지 변경
 * 
 * 
 */
import com.sist.dao.*;
import java.util.*;

@WebServlet("/BoardListServlet") // 사이트 주소
public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		/*
		 * request : 사용자 요청정보
		 * response: 응답(HTML => 사용자에게 전송) 
		 * 
		 * 
		 * XML, HTML => CONTENTTYPE
		 * 
		 * charset=UTF-8 =>한글꺠짐 방지
		 *
		 * 
		 * html 파일에서 초록색 글자는 모두 out.println이 생략된 것이다. 
		 * 
		 * 
		 * http://localhost/BoardPROJECT1/BoardListServlet
		 * ==============================> 루트 => webcontent
		 * 
		 * 모든 서블랫은 webcontent안에 있다! 
		 * 
		 * &lt;다음&gt; 
		 * 
		 * 
		 * =======================
		 * 1. 데이터 저장소  => Oracle
		 * 2. 데이터 수집  
		 * =======================
		 * 3. 자바 연동    => 자바  		==> 관리하는 프로그램(Spring)
		 * =======================
		 * 4. html화면 제작   
		 * 5. html데이터 출력  =>html 
		 * 6. css모양 다듬기  => css
		 * 7. Javascript 로 이벤트  => javascript
		 * 
		 * 
		 * 
		 * 
		 */
		// 실행한 결과를 어떤 형태로 실행할 것인지. 어떤 브라우저로 실행할 것인가 xml? , html?
		response.setContentType("text/html;charset=UTF-8");
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
		
		
		out.println("<form method=post action=BoardFind>"); // post방식으로 받아야한다!! 글자가 안깨진다!
		out.println("</table>");
		out.println("<table id=\"table_content\" width=700>");
		out.println("<tr>");
		
		out.println("<td align=left>");
		out.println("Search:");
		out.println("<select name=fs>");
		out.println("<option value=name>이름</option>");   // value값을 준이유는 데이터베이스에 아노이게 값을 넘겨준다name은 name을 subject는 subject를!!!!!
		out.println("<option value=subject>제목</option>");  // 보여줄때는 태그사이에 값을 보여주고
		out.println("<option value=content>내용</option>");
		out.println("</select>");
		out.println("<input type=text size=15 name=ss>");
		out.println("<input type=submit value=찾기>");
		out.println("</td>");
		
		out.println("<td align=right>");
		out.println("<a href=\"BoardListServlet?page="+(curpage>1?curpage-1:curpage)+"\">&lt;이전&gt;</a>");
		
		/*
		 * 특수문자
		 * &nbsp; " "
		 * &lt;   <
		 * &gt;   >
		 * 
		 */
		
		out.println(curpage+" page / " + totalPage + " pages");
		out.println("<a href=\"BoardListServlet?page="+(curpage<totalPage?curpage+1:curpage)+"\">&lt;다음&gt;</a>");
		
		out.println("</td>");
		out.println("</tr>");
		out.println("<table>");
		
		
		
		out.println("</center>");
		out.println("</body>");
		out.println("</html>");
		// 메모리 ()안의 값이 저장되고 
		// 브라우저에서는 메모리에 있는 것을 불러옴.
		
		
	}

}
