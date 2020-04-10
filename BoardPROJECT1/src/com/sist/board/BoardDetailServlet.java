package com.sist.board;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sist.dao.*;


// 상세페이지 만들 기!! 
// 보던 상세페이지를 확인한 후 해당 목록 페이지를 그대로 보여줘야함! 삭제, 목록보기,답변 등등!!  ==> hidden을 사용하여!! 있던 목록페이지로 다시 돌아가야함!!




@WebServlet("/BoardDetailServlet") //a 태그 안에 이주소와 일치하면 이 페이지로 넘어옴
public class BoardDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	response.setContentType("text/html;charset=UTF-8");  //한글 읽을 수 있음
	PrintWriter out=response.getWriter(); // 브라우저가 읽어가는 위치
	
	//no라는 이름으로 보내줌, 사용자가 보내준값
	String no=request.getParameter("no");
	
	//오라클에서 데이터를 가지고 온다. 
	BoardDAO dao= new BoardDAO(); // 정수형을 string으로 변환해야한다.
	BoardVO vo=dao.boardDetailData((Integer.parseInt(no)));
	
	//윈도우나 웹상은 모두 넘어온느 것이 스트링이기때문에 인트나 더블형은 모두 스트링으로 변환해줘야한다
	//래퍼클래스
	
	out.println("<html>");
	out.println("<head>");
	out.println("<link rel=stylesheet href=\"CSS/table.css\">");
	out.println("</head>");
	out.println("<body>");
	out.println("<center>");
	out.println("<h1> 내용보기</h1>");
	
	// 글쓰기 => 클릭하면 BoardInsert 이 파일을 가져옴. 
	out.println("<table id=\"table_content\" width=700>");
	
	out.println("<tr>");
	out.println("<th width=20%>번호</th>"); // th는 가운데 정렬이 디폴트
	out.println("<td width=30% align=center>"+vo.getNo()+"</td>");
	out.println("<th width=20%>작성일</th>"); // th는 가운데 정렬이 디폴트
	out.println("<td width=30% align=center>"+vo.getRegdate()+"</td>");
	out.println("</tr>");
	
	
	out.println("<tr>");
	out.println("<th width=20%>이름</th>"); // th는 가운데 정렬이 디폴트
	out.println("<td width=30% align=center>"+vo.getName()+"</td>");
	out.println("<th width=20%>조회수</th>"); // th는 가운데 정렬이 디폴트
	out.println("<td width=30% align=center>"+vo.getHit()+"</td>");
	out.println("</tr>");
	
	
	
	out.println("<tr>");
	out.println("<th width=20%>제목</th>"); // th는 가운데 정렬이 디폴트
	out.println("<td colspan=3>"+vo.getSubject()+"</td>");
	out.println("</tr>");
	
	
	//내용보기
	out.println("<tr>");
	out.println("<td colspan=4 align=left valign=top height=200>"+vo.getContent()+"</td>");
	out.println("</tr>");
	
	
	out.println("<tr>");
	out.println("<td conlspan=4 align=right>");
	
	// 보드업데이트 보여줘야함
	out.println("<a href=\"BoardUpdate?no="+vo.getNo()+"\">수정</a>&nbsp;");
	//?no="+vo.getNo()+ 이번호가 넘어가야 이 번호에 해당하는 데이터를 받을 수 있음.
	// 매개변수와 동일해야함.
	
	
	out.println("<a href=\"BoardDelete?no="+vo.getNo()+"\">삭제</a>&nbsp;");
	
	
	out.println("<a href=\"BoardListServlet\">목록</a>");
	out.println("</td>");
	out.println("</tr>");
	
	out.println("<table>");
		
	}

}
