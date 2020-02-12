package com.sist.board;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.dao.BoardDAO;
import com.sist.dao.BoardVO;

/*
 *  class A
 *  {
 *  
 *  	void a() {삼각형}
 *  
 *  	void b() {네모}
 *  
 *  
 *  }
 * 
 * 	A aa =new A();
 * if(method==get)
 * 	aa.doget()
 * else if(method==post)
 * 	aa.dopost()
 * 
 */



/**
 * Servlet implementation class BoardDelete
 */
@WebServlet("/BoardDelete")
public class BoardDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 모양잡기
		response.setContentType("text/html;charset=UTF-8"); // 텍스트 타입은 html로 출력할 것임?
		PrintWriter out=response.getWriter(); // no를 받아놔서 디비에 보내줘야함
		
		// 삭제하기 눌렀을때 어떤 게시물을 삭제할지 값을 정해줘야함
		// 해당 게시물 번호를 받기
		String no=request.getParameter("no"); // 이 번호를 삭제할 것이다!!
		
		
		out.println("<!DOCTYPE html>"); //5.0을 쓰기위해
		//5.0에서 required 기능을 제공한다!
		
		
		out.println("<html>");
		out.println("<head>");
		out.println("<link rel=stylesheet href=\"CSS/table.css\">");
		out.println("</head>");
		out.println("<body>");
		out.println("<center>");
		out.println("<h1>삭제하기</h1>");
		out.println("<form method=post action=BoardDelete>");   // board dopost에 있는 post를 호출함
		// submit은 항상 form 태그가 있다(submit이란 데이터 전송이기 때문에) post방식으로 받아서 처리해야하기 때문에
		// action 누구한테 보낼거냐 BOARDINSERT로
		// dopost가 호출되면서 dopost에서 처리하러 고고! 여기서 오라클로 보냄
		// jsp는 다름
		// getpost는 출력처리만함 dopost는 전송처리까지.
		
		
		
		/*
		 * submit 누르면 => input, select , textarea
		 *  ==> 입력된 것만 넘겨줌!
		 *  위 3개의 값 외에는 값이 넘어가지 않는다!
		 * 
		 * 
		 * 
		 * 
		 */
		
		// 첫번째 줄
		// 글쓰기 => 클릭하면 BoardInsert 이 파일을 가져옴. 
		out.println("<table id=\"table_content\" width=500>");
		out.println("<tr>");
		// th => center   td =left
		// 글자수가 가변형인 것은 left정렬, 글자수 정해진 것은 센터정렬
		out.println("<th width=35% align=right>비밀번호</th>");
		out.println("<td width=65%>");
		out.println("<input type=password name=pwd size=15 required>");
		// 히든을 이용해 받은 pwd값을 오라클에 보내줘야함!
		// 자바로 안넘어가기 때문에 여기서 보내줘야함 no 를
		out.println("<input type=hidden name=no value="+no+">");  // no와 pwd가 넘어감!!!
		
		
		
		
		out.println("</td>");
		out.println("</tr>");
		
		
		out.println("<tr>");
		out.println("<td colspan=2 align=center>");
		out.println("<input type=submit value=삭제하기>");
		out.println("<input type=button value=취소 onclick=\"javascript:history.back()\" >"); //그전에 보고있던 목록으로 돌아가라!
		out.println("</td>");
		out.println("</tr>");
		
		
		out.println("</table>");
		out.println("</center>");
		out.println("</body>");
		out.println("</html>");
		
		
		
		
	}

	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		response.setContentType("text/html;charset=UTF-8"); //HTML로 출력값이 필요! ==> PWD확인하려고!, 
		PrintWriter out=response.getWriter();
		
		//비번은 한글 x
		//try catch 필요 x
		
		//그리고 저장된 값을 DAO로 보냄
		String no=request.getParameter("no");
		
	
		String pwd=request.getParameter("pwd");
		
			
		// 처리=> DAO에서! 업데이트 만들기!!
		
		BoardDAO dao=new BoardDAO();
		boolean bCheck=dao.boardDelete(Integer.parseInt(no), pwd); // 넘어오는 값은 무조건 스트링이니까!!!
		
		// no와 pwd 두개이므로 vo는 안만든다!
		
		// 이동 상세보기로 !
		if(bCheck==true)
		{
			
			response.sendRedirect("BoardListServlet");
			// 삭제가 되면 이 화면을 보여주고
			// 비번이 안맞으면 아래 실행
			
		}else
		{

			
			// history.back() => 이전의 값을 다시 가져옴.
			
			out.println("<html>");
			out.println("<head>");
			out.println("<script type=\"text/javascript\">");
			out.println("alert(\"비밀번호가 틀립니다!!\");");
			out.println("history.back();");
			out.println("</script>");
			out.println("</head>");
			out.println("</html>");
			
		}
		
		
		//처리가 끝나고 나면 다시 상세보기로 화면전화이 되어야한다!
		//response.sendRedirect("BoardDetailServlet?no="+no);
		
		
	}

		
	

}
