package com.sist.view;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import com.sist.vo.*;
import com.sist.dao.*;

//<jsp:include page="">
// html안에 html들어갈 수 있다.
// 메뉴 클릭시 모든 메뉴는 메인 서블릿으로 해야 (메인에서 번호로 구분하여 페이지 가져옴!)
@WebServlet("/ReleasedServlet")
public class ReleasedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		
		//페이지 받기 -사용자가 보내준값!
		String strPage=request.getParameter("page");
		
		if(strPage==null)
			strPage="1";
			
		//strPage를 int로 변환
		int curpage=Integer.parseInt(strPage);
		
		//DAO연결 
		MovieDAO dao=MovieDAO.newInstance();
		ArrayList<MovieVO> list=dao.movieListData(curpage, 1);
		
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
			//디테일로 넘어감=>  메인에서는 mode7과 mno에서
			out.println("<a href=\"MainServlet?mode=7&mno="+vo.getMno()+"\">");
			out.println("<img src=\""+vo.getPoster()+"\" alt=\"Lights\" style=\"width:100%\">");
			out.println("</a>"); //이미지에만 링크가 걸림!
			out.println("<div class=\"caption\">");
			out.println("<p>"+title2+"</p>");
			out.println("<p>네티즌&nbsp; <font color=red>"+vo.getScore()+"</font></p>");
			out.println("<p><font color=gray><sup>"+vo.getRegdate()+"</sup></font></p>");
			
			
			out.println("</div>");
		
			out.println("</div>");
			out.println("</div>");
						
		}
		
		
		out.println("</div>");
		
		
		// 페이지 버튼!!
		out.println("<div class=\"row text-center\">");
		
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
		
		out.println("<ul class=\"pagination pagination-lg\">");
		out.println("<li><a href=\"MainServlet?mode=1&page=1\">1</a></li>");
		out.println("<li><a href=\"MainServlet?mode=1&page=2\">2</a></li>");
		out.println("</ul>");
		out.println("</div>");
		
		out.println("</body>");
		out.println("</html>");
	
		
		
		
		
		
		
	}

}
