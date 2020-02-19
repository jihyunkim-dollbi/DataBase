package com.sist.view;

import java.io.*;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter(); 
		
		/*
		 * <nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">WebSiteName</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="#">Home</a></li>
      <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Page 1 <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="#">Page 1-1</a></li>
          <li><a href="#">Page 1-2</a></li>
          <li><a href="#">Page 1-3</a></li>
        </ul>
      </li>
      <li><a href="#">Page 2</a></li>
      <li><a href="#">Page 3</a></li>
    </ul>
  </div>
</nav>
  
<div class="container">
  <h3>Navbar With Dropdown</h3>
  <p>This example adds a dropdown menu for the "Page 1" button in the navigation bar.</p>
</div>

		 * 
		 * 
		 */
		
		//변경할 서블릿 명
		
		String mode=request.getParameter("mode");
		
		//맨첫페이지 - 현재상영 띄우기!
		if(mode==null)
			mode="1";
		
		int n=Integer.parseInt(mode);
		
		String sname="";
		
		switch(n)
		{
		case 1:
			sname="ReleasedServlet";
			break;
		case 2:
			sname="ScheduleServlet";
			break;
		case 3:
			sname="NewsServlet";
			break;
		case 4:
			sname="WeeklyServlet";
			break;	
		case 5:
			sname="MonthlyServlet";
			break;	
		case 6:
			sname="YearlyServlet";
			break;	
		case 7:
			sname="MovieDetailServlet";
			break;
		}
		
		
		out.println("<html>");
		
		//해드 안에 속성/js
		out.println("<head>");
		out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">"); // \해주기!
		out.println("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js\"></script>");
		out.println("<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js\"></script>");
		out.println("</head>");
		
		//화면출력
		out.println("<body>");
		out.println("<nav class=\"navbar navbar-inverse\">");
		out.println("<div class=\"container-fluid\">");
		out.println(" <div class=\"navbar-header\">");
		out.println("<a class=\"navbar-brand\" href=\"MainServlet\">SIST MC</a>");
		out.println("</div>");
		out.println(" <ul class=\"nav navbar-nav\">");
		out.println("<li class=\"active\"><a href=\"MainServlet\">현재상영</a></li>");
		out.println("<li class=\"dropdown\"><a class=\"dropdown-toggle\" data-toggle=\"dropdown\" href=\"#\">박스오피스<span class=\"caret\"></span></a>");
		out.println("<ul class=\"dropdown-menu\">");
		out.println("<li><a href=\"MainServlet?mode=4\">주간</a></li>");
		out.println("<li><a href=\"MainServlet?mode=5\">월간</a></li>");
		out.println("<li><a href=\"MainServlet?mode=6\">연간</a></li>");
		out.println("</ul>");
		out.println("</li>");
		out.println("<li><a href=\"MainServlet?mode=2\">개봉예정</a></li>");
		out.println("<li><a href=\"MainServlet?mode=3\">뉴스</a></li>");
		out.println("</ul>");
		out.println("</div>");
		out.println("</nav>");
		
		
		//head안에 매뉴 넣기! 
		//https://www.w3schools.com/bootstrap/tryit.asp?filename=trybs_navbar_dropdown&stacked=h		
		
		out.println("<div class=\"container\">");
	
		//목록은 유지하고
		//5개의 페이지를 바꿔주면 됨! 5개 페이지 include하기! 
		RequestDispatcher rd=request.getRequestDispatcher(sname);
		rd.include(request, response);
		// 			mode=1 & page=2
		//			main처리	  released처리
		// getParameter()로 각각 메인에서 =>"mode"와 릴리즈서블렛에서 =>"page"
		// jqeury, css충돌 일어난다.
		//include는 request를 공유함!!
		/*
		 * void include(HttpServletRequest request, HttpServletResponse response)
		 * 	released
		 * 
		 */
		
		
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
		
		
		
	}

}
