package com.sist.board;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.dao.*;
import com.sist.dao.BoardVO;

/**
 * Servlet implementation class BoardInsert

 */
/*
 * 
 * <<<목록(제서 제목을 누르면 상세보기로 넘어감)
 * -새글 - 목록으로간다
 * -찾기
 * 
 * 
 * <<<상세보기
 * -수정 => 수정되면 상세보기로
 * -삭제 => 목록으로
 * 
 * 
 * 따라서 둘중의 하나를 보여주게 됨(목록으로 가거나, 상세보기로 가거나!)
 * 
 * 
 * 2020 02 12
 * <새글쓰기 만들기 위함 >
 * 
 * name 속성! 자바에서 값을 받을때 필요
 * css 에서는 id와 class 가 주!!
 * 
 */
@WebServlet("/BoardInsert")
public class BoardInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 여기서 글을쓰면
		
		response.setContentType("text/html;charset=UTF-8"); // 텍스트 타입은 html로 출력할 것임?
		PrintWriter out=response.getWriter(); // 사용자 찾기
		//한사람당 웹서버가 하나씩 만들어져서 동시에 한 곳에서 여러 행위가 가능하다 (쓰레드를 생성함)
		
		out.println("<!DOCTYPE html>"); //5.0을 쓰기위해
		//5.0에서 required 기능을 제공한다!
		
		
		out.println("<html>");
		out.println("<head>");
		out.println("<link rel=stylesheet href=\"CSS/table.css\">");
		out.println("</head>");
		out.println("<body>");
		out.println("<center>");
		out.println("<h1>글쓰기</h1>");
		out.println("<form method=post action=BoardInsert>");
		// submit은 항상 form 태그가 있다(submit이란 데이터 전송이기 때문에) post방식으로 받아서 처리해야하기 때문에
		// action 누구한테 보낼거냐 BOARDINSERT로
		// dopost가 호출되면서 dopost에서 처리하러 고고! 여기서 오라클로 보냄
		// jsp는 다름
		// getpost는 출력처리만함 dopost는 전송처리까지.
		
		// 첫번째 줄
		// 글쓰기 => 클릭하면 BoardInsert 이 파일을 가져옴. 
		out.println("<table id=\"table_content\" width=700>");
		out.println("<tr>");
		// th => center   td =left
		// 글자수가 가변형인 것은 left정렬, 글자수 정해진 것은 센터정렬
		out.println("<th width=15% align=right>이름</th>");
		out.println("<td width=85%>");
		out.println("<input type=text name=name size=15 required>");
		out.println("</td>");
		out.println("</tr>");
		
		
		// 두번째줄
		out.println("<tr>");
		out.println("<th width=15% align=right>제목</th>");
		out.println("<td width=85%>");
		out.println("<input type=text name=subject size=50 required>");
		out.println("</td>");
		out.println("</tr>");
		
		//valign=top, center, botton 위아래에서 위로 정렬하겠다
		out.println("<tr>");
		out.println("<th width=15% align=right valign=top>내용</th>");
		out.println("<td width=85%>");
		out.println("<textarea rows=8 cols=70 name=content required></textarea>");
		out.println("</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<th width=15% align=right>비밀번호</th>");
		out.println("<td width=85%>");
		out.println("<input type=password name=pwd size=15 required>");
		out.println("</td>");
		out.println("</tr>");
		
		
		out.println("<tr>");
		out.println("<td colspan=2 align=center>");
		out.println("<input type=submit value=완료>");
		out.println("<input type=button value=취소 onclick=\"javascript:history.back()\" >"); //그전에 보고있던 목록으로 돌아가라!
		out.println("</td>");
		out.println("</tr>");
		
		out.println("<table>");
		
		out.println("</form>");
		out.println("</center>");
		out.println("</body>");
		out.println("</html>");
		
	}

	
	// name,subject,content,pwd => NOT NULL이다! 반드시 입력을 할 수 있게 만들어야함. 
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 아래서 처리함
		// html필요x response text html 필요 x
		
		//넘어올때 받은 데이터가 바이트 단위라서 디코딩 필요
		try // 컴파일 익셉션이기 때문에 예외처리 해주고 실행해줘야한다.
		{
			request.setCharacterEncoding("UTF-8"); //한글변환 필요
			
		}catch(Exception ex) {}
		
		
		// 사용자가 보낸 데이터 받기 .getParameter("name")
		
		String name=request.getParameter("name");
		String subject=request.getParameter("subject");
		String content=request.getParameter("content");
		String pwd=request.getParameter("pwd");
		
		/*
		// System.out.printlt 이기때문에 도수창에서 검색 가능
		System.out.println("name: " + name);
		System.out.println("subject: " + subject);
		System.out.println("content: " + content);
		System.out.println("pwd: " + pwd);
		
		*/
		
		// vo로 묶어서 오라클에 보낼 예정
		BoardVO vo = new BoardVO();
		vo.setName(name);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPwd(pwd);
		// 어떻게 오라클에 집어넣을 것이냐? DML활용예정!DAO로 가서!!
		
		
		
		// 오라클 연동 => DAO 메소드 호출
		BoardDAO dao=new BoardDAO();
		dao.boardInsert(vo);
		
		
		
		
		
		// 이동- 목록으로 ! response 서버에 명령할때
		response.sendRedirect("BoardListServlet");
		// sendRedirect 내가 원하는 페이지로 동시킴 => 목록으로 돌아옴!
		
		
		
		
		
		
		
		
	}

}
