package com.sist.view;

import java.io.*;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.dao.MovieDAO;
import com.sist.vo.MovieVO;


@WebServlet("/MonthlyServlet")
public class MonthlyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		
	
		//DAO연결 
		MovieDAO dao=MovieDAO.newInstance();
		ArrayList<MovieVO> list=dao.movieListData(1, 4);
		
		//list출력하기
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
			out.println("<div class=\"caption\">");
			out.println("<p>"+title2+"</p>");
			out.println("</div>");
			out.println("</a>");
			out.println("</div>");
			out.println("</div>");
						
		}
		
		
		out.println("</div>");
		
	
		
		out.println("</body>");
		out.println("</html>");
	
		
		
		
	}

}
