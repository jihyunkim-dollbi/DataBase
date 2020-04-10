package com.sist.view;

import java.io.*;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sist.dao.*;
import com.sist.vo.*;

/*
 * insert ,update, delete => dopost
 * select => doget
 * 
 */

@WebServlet("/MovieDetailServlet")
public class MovieDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//한글변환
		response.setContentType("text/html;charset=UTF-8");
		//사용자읽음
		PrintWriter out=response.getWriter();

		String mno=request.getParameter("mno");
		MovieDAO dao=MovieDAO.newInstance();
		MovieVO vo=dao.movieDetailData(Integer.parseInt(mno));
		
		
		out.println("<html>");
		out.println("<head>");
		
			
		out.println("<style type=text/css>"); // 메인에 컨테이너 있음 => row만들기
		out.println(".row{");
		out.println("margin:0px auto;");
		out.println("width:1000px;");
		out.println("}");
		out.println("</style>");
		
		out.println("</head>");
		out.println("<body>");		
		//div 4개씩 한줄 돌리기
		//화면출력
		out.println("<div class=row>");
		
		out.println("<table class=\"table table-hover\">");
		

		out.println("<tr>");
		out.println("<td colspan=2 class=text-center><h3>"+vo.getTitle()+" 상세보기</h3></td>");
		out.println("</tr>");
		
		
		
		
		out.println("<tr>");
		out.println("<td width=30% class=\"text-center\" rowspan=7>");
		out.println("<img src="+vo.getPoster()+" width=100%>");
		out.println("</td>");
		out.println("<td width=80%>"+vo.getTitle()+"</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td width=80%>평점:"+vo.getScore()+"</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td width=80%>"+vo.getGenre()+"</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td width=80%>"+vo.getRegdate()+"|"+vo.getType()+","+vo.getGrade()+"</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td width=80%>(감독)"+vo.getDirector()+"</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td width=80%>(출연)"+vo.getActor()+"</td>");
		out.println("</tr>");
		
		
		//목록버튼
		out.println("<tr>");
		out.println("<td width=80% class=text-center>");
		//클릭하기 이전페이지로 back!
		out.println("<a href=\"javascript:history.back()\" class=\"btn btn-sm btn-danger\">목록</a>");
		
		out.println("</td>");
		out.println("</tr>");
		
		
		out.println("<tr>");
		out.println("<td colspan=2>"+vo.getStory()+"</td>");
		out.println("</tr>");
		
		
		
		out.println("</table>");
		
		
		
		
		
		
		
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
		
	}

}
