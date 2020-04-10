package com.sist.view;

import java.io.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.dao.MovieDAO;
import com.sist.vo.MovieVO;


@WebServlet("/WeeklyServlet")
public class WeeklyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		
		
		//DAO연결 
		MovieDAO dao=MovieDAO.newInstance();
		ArrayList<MovieVO> list=dao.movieListData(1, 3); // (int page, int type)
		
		//list출력하기
		out.println("<html>");
		out.println("<head>");
		out.println("<style type=text/css>"); // 메인에 컨테이너 있음 => row만들기
		out.println(".row{");
		out.println("margin:0px auto;");
		out.println("width:700px;");
		out.println("}");
		out.println("</style>");
		out.println("</head>");
		out.println("<body>");		
		//div 4개씩 한줄 돌리기
		out.println("<div class=row>");
		
		/*  <div class="col-md-4">
			    <div class="thumbnail">
			      <a href="/w3images/lights.jpg">
			        <img src="/w3images/lights.jpg" alt="Lights" style="width:100%">
			        <div class="caption">
			          <p>Lorem ipsum...</p>
			        </div>
			      </a>
			    </div>
			  </div>
			
		 * 
		 * 
		 * 4개씩 출력 예정 => col-md-3 => 4번이 되야 12번이 되므로 => 한개당 3개!
		 */
		
		for(MovieVO vo:list)
		{
			
			String title=vo.getTitle();
			String title2 ="";
			if(title.length()>15)		
			{
				title2=title.substring(0,15)+"...";
			}
			else
			{
				title2=title;
				
			}
				
			
			out.println("<div class=\"col-md-3\">");	
			out.println("<div class=\"thumbnail\">");
			out.println("<a href=\"#\">");
			out.println("<img src=\""+vo.getPoster()+"\" alt=\"Lights\" style=\"width:100%\">");
			out.println("</a>");
			out.println("<div class=\"caption\">");
			out.println("<p>"+title2+"</p>");
			out.println("<p>네티즌&nbsp; <font color=red>"+vo.getScore()+"</font></p>");
			out.println("<p><font color=gray><sup>"+vo.getRegdate()+"</sup></font></p>");
			out.println("</div>");
			out.println("</div>");
			out.println("</div>");
						
		}
		
		
		out.println("</div>");
		
		
		
		/*
		 * <ul class="pagination pagination-lg">
  			<li><a href="#">1</a></li>
  			<li><a href="#">2</a></li>
  			<li><a href="#">3</a></li>
  			<li><a href="#">4</a></li>
  			<li><a href="#">5</a></li>
			</ul>
		 * 
		 */

		
		out.println("</body>");
		out.println("</html>");
	
		
	}

}
