package com.sist.servlet;

import java.io.*;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.sist.dao.*;


@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// 화면출력용
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter(); // 현재 사용자가 보고 있는 위치, 여기서 읽음
		// getwriter() 각자 쓰레드를 가지고 잇다
		
		
		out.println("<html>");
		out.println("<head>"); // 화면출력x ,설정파일 js 등등 css
		
		out.println("<link rel=stylesheet href=\"css/bootstrap.min.css\">");   ///   \"   \"  따옴표 붙이는 이유 html은 .을 공백으로 인식한다. 안에 파일명에 .이 있으니까 하나의 문자열로 인식하기 위해
		out.println("<style type=text/css>"); //내부 css
		// 한프로그램 만들때 css를 한개씩 만들면서 해야 는다
		out.println(".row {"); //로그인창 만들기 div
		out.println("margin: 0px auto;"); //auto 가운데 정렬
		out.println("width:350px");
		out.println("}");
		
		out.println("h1 {");
		out.println("text-align:center");
		out.println("}");
		
		
		out.println("</style>");
		out.println("</head>");
		out.println("<body>");
		
		// container 전체브라우저 안에 가장 큰 네모칸 . 브라우저와의 공백x => container-fluid =>전체화면, container - 여백주기 (네이버, 다음)
		out.println("<div class=container>");
		// row는 그 안에 한줄씩 만들어 나가는 것. 가로줄 박스- 만개의 레시피 row => 11개의 row! =>한줄 전체 출력
		//한줄 만들어서 테이블 만들 예엊ㅇ
		out.println("<h1>Login</h1>");
		out.println("<div class=row>");
		
		out.println("<form method=post action=Login>");  // Login 의 dopost를 호출하라!
		
		
		
		out.println("<table class=\"table table-hover\">"); // 테이블에 갖다 대었을때 움직임 // 위에서 row의 크기를 설정해놓음
		
		// 로그인창 row
		out.println("<tr>");
		out.println("<td width=20% class=text-right>ID</td>");
		out.println("<td width=80% class=text-left><input type=text name=id size=15 class=input-sm></td>");
		out.println("</tr>");
		
		//비번창 row
		out.println("<tr>");
		out.println("<td width=20% class=text-right>PASSWORD</td>");
		out.println("<td width=80% class=text-left><input type=password name=pwd size=15 class=input-sm></td>");
		out.println("</tr>");
		
		

		out.println("<tr>");
		out.println("<td colspan=2 class=text-center><input type=submit class=\"btn btn-xs btn-warning\" value=로그인></td>"); // \"\"=> 사이에 공백을 넣을 것이기때문에! 
		out.println("</tr>");
		
		/*
		 * xs
		 * md
		 * sm - 기본크기?
		 * lg
		 *
		 */
		
		
		out.println("</table>");
		out.println("</form>");
		
		
		out.println("</div>");  // .row닫기
		out.println("</body>");
		out.println("</container>"); //.container => 브라우저에서 가장 먼저 설정해두기 때문에 가장 마지막에 닫아둠
		out.println("</html>");
		
		
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				//사용자 요청처리를 톰캣이 두 매개변수룰 처리해줌 // 위에 request와 response지역변수!!
		
		
		
		response.setContentType("text/html;charset=UTF-8"); //RESPONSE를 통해 보내줘야함..
		PrintWriter out = response.getWriter();
		//js로 상대방에게 뭔가를 보내면 html로 작성해야함.
		// insert는 html처리 x
		//로그인은 
		//HTML => CSS, JS포함!
		
		MusicDAO dao=new MusicDAO();
		
		//사용자가 보내준 id, pwd읽어오기, 입력값 받기
		String id=request.getParameter("id"); //위에 name값에 들어간 것과 동일하게 적어줘야함. name에 해당하는 값을
		String pwd=request.getParameter("pwd");
		String result=dao.isLogin(id, pwd);
		
		//넘어오는 것 3가지
		if(result.equals("NOID"))
		{
			//아이디 틀림
			out.println("<script>");
			out.println("<alert(\"ID가 존재하지 않는다\")");  // 조그만 창 만들때
			out.println("history.back();"); //뒤로감
			out.println("</script>");
			
		}else if(result.equals("NOPWD"))
		{
			// 비번틀림
			out.println("<script>");
			out.println("<alert(\"비밀번호가 틀립니다\")");  // 조그만 창 만들때
			out.println("history.back();"); //뒤로감
			out.println("</script>");
			
		}else
		{
			
			
			//로그인 성공
			//request 사용자가 보내준값을...?
			//response 화면이동 
			//session  내가 일부정보를 저장해놓기 위해 / 페이지 넘아감과 동시에(메모리 회수당할 예정) 아이디를 톰캣안에 저장하려고 , 저장한 상태에서 가려고
			//30분후에 - 기간을 연장할 수도 있다., 로그인 했을 경우, 등등
			// 필요한 경우 어떤 파일도 갖다쓸수 있다 세션이 종료되기 전까지는.. 따라서 글을쓸때 로그인할 필요없음
			HttpSession session = request.getSession(); // 한명당 공간은  하나뿐이다.따라서 다음에 가져올때 갖은 공간에서 가져옴. 데이터는 여러개 사용가능
			// id와 name은 저장해놓아야함 ==> 세션에서 가져다 쓸 예정
			
			//따라서 로그인시 세션에 이름과 id를 저장해놓기 => 서버에 저장됨
			//쿠기는 본인 컴퓨터 안에 저장 => 다른 컴퓨터에서는 적용x
			//다른 곳에서 또 로그인시 로그인 중입니다.. 라고 나올수...
			// get아니고 set!
			session.setAttribute("id", id); // 저장하기 get으로 id주면 가져올수있다.
			
			StringTokenizer st= new StringTokenizer(result,"|");
			session.setAttribute("name",st.nextToken());
			session.setAttribute("sex",st.nextToken());
			
			session.setAttribute("name", result);
			// 쿠기는 문자열만 저장가능 매개변수 String!
			//session오브젝트 형이라 어레이리스트 전체 저장 가능
			
			//파일이동
			response.sendRedirect("MusicList"); //이렇게 값을 주면 톰갯이 넘겨줄 것임 => 서블렛파일 musiclist로 화면이동 할 예정! 파일 만들어줘야함1
		
			
			
			
			//화면이 바뀌면 request와 response는 값이 모두 사라짐=초기화됨
			//session은 웹서버안에 저장되기 때문에 화면이 바뀌어도 값을 가지고 있음 => 전역변수와 같은 개념.
			
			//call back 함수 => 톰캣이란 시스템이의해 호출되므로 , main, doget, dopost
			//request는 계속 초기화됨! => map형식= 키와 값으로 되어있다. => setattribute
			//request의 키는 name에 넣는 값들이다! 
			//맵은 키가 중복되면 안된다! => 웹의 모든 것은 맵형식 
			// request.setAttribute(arg0, arg1);
			//						===== =====
			//						String Object
			//						키         값
			//set은 중복된 데이터를 받으면 안됨
			//map은 데이터관리에 많이 사용! (중복 데이터 ok)
			
			
			// 팝업창 - 다이얼로그 JS!
			//모달 다이얼로그! =? JQUERY UI필요!
			// 
			
			
			
			
			
			
			
			
			
			
			
			
		}
		
		
	}

}
