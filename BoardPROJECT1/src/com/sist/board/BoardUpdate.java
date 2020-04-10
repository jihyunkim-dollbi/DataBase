package com.sist.board;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sist.dao.*;
/*
 * 
 * doget  - 모양보여주기 -SELECT
 * dopost - 값 전송 처리 -INSERT, UPDATE, DELETE
 * 
 * 두개필요 - 모양처리, 전송처리!
 * 
 * 
 * 
 */
/**
 * Servlet implementation class BoardUpdate
 */
@WebServlet("/BoardUpdate")
public class BoardUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//BoardInsert 에서 아래 복사!
		//이미 쓴글 입력값을 가져와야함!
		
		
		response.setContentType("text/html;charset=UTF-8"); // 텍스트 타입은 html로 출력할 것임?
		PrintWriter out=response.getWriter(); // 사용자 찾기
		//한사람당 웹서버가 하나씩 만들어져서 동시에 한 곳에서 여러 행위가 가능하다 (쓰레드를 생성함)
		
		
		//사용자 요청한 번호를 받는다.
		/*
		 * http://localhost/BoardPROJECT1/BoardDetailServlet?no=13
		 * 
		 */
		
		String no=request.getParameter("no");
		// 현재 이 no값은 브라우저에 출력이 안된상태에서 값을 보내야함 => 값을 감추면서 보내야함!
		BoardDAO dao= new BoardDAO();
		BoardVO vo= dao.boardUpdateData(Integer.parseInt(no));
		//no값을 받음!!
		
		
		
		
		out.println("<!DOCTYPE html>"); //5.0을 쓰기위해
		//5.0에서 required 기능을 제공한다!
		
		
		out.println("<html>");
		out.println("<head>");
		out.println("<link rel=stylesheet href=\"CSS/table.css\">");
		out.println("</head>");
		out.println("<body>");
		out.println("<center>");
		out.println("<h1>수정하기</h1>");
		out.println("<form method=post action=BoardUpdate>");
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
		out.println("<input type=text name=name size=15 required value="+vo.getName()+">");  // input type은 value로  칸 안에 값을 넣어줌!
		out.println("<input type=hidden name=no value="+vo.getNo()+">");  //hidden으로 감춰서 값을 보낼수 있다.!!!!
		out.println("</td>");
		out.println("</tr>");
		
		
		// 두번째줄
		out.println("<tr>");
		out.println("<th width=15% align=right>제목</th>");
		out.println("<td width=85%>");
		out.println("<input type=text name=subject size=50 required value=\""+vo.getSubject()+"\">"); // 준이유!!\" 데이터중에 공백이 들어가 잇으면 \" 를 붙여줘야함!! 공백 포함출력!! 제목은 공백이 포함되어있으므로!
		out.println("</td>");
		out.println("</tr>");
		
		//valign=top, center, botton 위아래에서 위로 정렬하겠다
		out.println("<tr>");
		out.println("<th width=15% align=right valign=top>내용</th>");
		out.println("<td width=85%>");
		out.println("<textarea rows=8 cols=70 name=content required>"+vo.getContent()+"</textarea>"); //+vo.getContent() ==> textarea 태그 사이에 넣어줘야함!!!!!!
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
		out.println("<input type=submit value=수정>");
		out.println("<input type=button value=취소 onclick=\"javascript:history.back()\" >"); //그전에 보고있던 목록으로 돌아가라!
		out.println("</td>");
		out.println("</tr>");
		
		out.println("</table>");
		
		out.println("</form>");
		out.println("</center>");
		out.println("</body>");
		out.println("</html>");
		
		
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
				
		
		//그리고 저장된 값을 DAO로 보냄
		String no=request.getParameter("no");
		
	
		String name=request.getParameter("name");
		String subject=request.getParameter("subject");
		String content=request.getParameter("content");
		String pwd=request.getParameter("pwd");
		
		BoardVO vo = new BoardVO();
		vo.setNo(Integer.parseInt(no));   // hidden을 안했을 경우 여기서 넘버폴멧익셉션 에러가 발생함!!!
		vo.setName(name);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPwd(pwd);
		
		// 처리=> DAO에서! 업데이트 만들기!!
		
		BoardDAO dao=new BoardDAO();
		boolean bCheck=dao.boardUpdate(vo);
		
		
		
		// 이동 상세보기로 !
		if(bCheck==true)
		{
			
			response.sendRedirect("BoardDetailServlet?no="+no);
			
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
