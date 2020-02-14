package com.sist.servlet;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.dao.*;
// util은 필요x 리스트로 가져올것 x



/*
 * 웹 클래스 - 총 8개 
 * 1.자입력값 받기 => 리퀘스트
 * 2.화면 이동 응답  => 리스폰스
 * 3.값을 저장하고 싶다!=> 세션
 * 
 * 
 * 
 * 
 * 
 */
@WebServlet("/MusicDetail")
public class MusicDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		//화면출력
		PrintWriter out=response.getWriter(); //브라우저가 읽는 위치 => 브라우저가 알아서 읽어갈 예정!
		
		// 보내준 값이 있으면 받고 dv연동해야함!
		// musicdetaildata(int no) ==> getMNO==>값을 받고 연동 시작
		
		String mno=request.getParameter("mno");
		
		//DAO 연동
				
		MusicDAO dao= new MusicDAO();
		MusicVO vo = dao.musicDetailData(Integer.parseInt(mno)); //
		
		
		HttpSession session = request.getSession();
		out.println("<html>");
		out.println("<head>");
		out.println("<link rel=stylesheet href=\"css/bootstrap.min.css\">");
		out.println("<style type=text/css>");
		out.println(".row{");
		out.println("margin: 0px auto;");
		out.println("width: 800px;");
		out.println("}");
		out.println("h1{");
		out.println("text-align: center;");
		out.println("}");
		out.println("</style>");
		out.println("</head>");
		out.println("<body>");
	//	out.println("<h1>"+session.getAttribute("name")+"님 환영합니다</h1>"); // 키를 넣어줌!
		out.println("<div class=container>");
		/*
		 * 
		 * <select>
		 * 
		 * WHERE no<10  => no&lt; 10 <  >    &nbsp;
		 * <select>
		 * 
		 * 
		 */
		
		
		
		
		out.println("<h1>&lt;"+vo.getTitle()+"&gt상세보기</h1>");
		out.println("<div class=row>");
		out.println("<table class=\"table table-bordered\">");
		
		
		out.println("<tr>");
		out.println("<td colspan=2 class=text-center>");
		out.println("<embed src=\"http://youtube.com/embed/"+vo.getKey()+"\" width=100% height=350>");
		out.println("</td>");
		out.println("</tr>");
		
		
		out.println("<tr>");
		out.println("<td width=10% class=text-right>");
		out.println("노래명");
		out.println("</td>");

		out.println("<td width=90% class=text-left>");
		out.println(vo.getTitle());
		out.println("</td>");
		out.println("</tr>");
		
		
		out.println("<tr>");
		out.println("<td width=10% class=text-right>");
		out.println("가수명");
		out.println("</td>");
		out.println("<td width=90% class=text-left>");
		out.println(vo.getSinger());
		out.println("</td>");
		out.println("</tr>");
		
		
		out.println("<tr>");
		out.println("<td width=10% class=text-right>");
		out.println("앨범명");
		out.println("</td>");
		out.println("<td width=90% class=text-left>");
		out.println(vo.getAlbum());
		out.println("</td>");
		out.println("</tr>");
		
		
		
		
		
		out.println("</table>");
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
		
		
		
		//연동후  값을 받아서 화면 출력!
		
		
		
		
		
	}

}
