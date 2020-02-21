package com.sist.view;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.dao.*;
import com.sist.manager.*;
import java.util.*;



@WebServlet("/FoodListServlet")
public class FoodListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		
		
		
		
		//cno를 =>사용자가 보낸 cno먼저 받고 아래서 매개변수로 받기
		String cno=request.getParameter("cno"); //여기의cno와 
		//FoodMainServlet의  out.println("<a href=\"FoodListServlet?cno="+vo.getCateno()+"\">"); 의 cno가 동일해야한다!
		FoodDAO dao=FoodDAO.newInstance();
		
		
		ArrayList<FoodHouseVO> list=dao.foodHouseListData(Integer.parseInt(cno));
		
		CategoryVO vo=dao.categoryInfoData(Integer.parseInt(cno));
		
		
		
		
		// 위 아래 두 부분의 두개의 테이블이 필요하다!!
		
		out.println("<html>");
		out.println("<head>");
		out.println(" <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">");
		out.println("<style type=text/css>");
		out.println(".row{");
		out.println("margin: 0px auto;");
		out.println("width:1200px;");
		
		out.println("}");
		out.println("</style>");
		
		out.println("</head>");
		
		out.println("<body>");
		out.println("<div class=container>");
		out.println("<div class=row>");
		
		
		
		
		
		out.println("<h1 class=text-center>"+vo.getTitle()+"</h1>");
		out.println("<h3 class=text-center style=\"color:gray\">"+vo.getSubject()+"</h3>");
		
		out.println("<table class=\"table table-striped\">");
		out.println("<tr>");
		
		out.println("<td>");
		//테잉블 여러개
		int i=1;
		for(FoodHouseVO fvo:list)
		{
			
			out.println("<table class=\"table\">");
			out.println("<tr>");
			out.println("<td width=30% rowspan=2 class=text-center>");
			//링크 걸어 디테일로 고고
			out.println("<a href=\"FoodDetailServlet?no="+fvo.getNo()+"\">");
			out.println("<img src="+fvo.getImage()+" width=180 height=150 class=img-rounded>");
			out.println("</a>");
			out.println("</td>");
			
			out.println("<td width=70%>");
			out.println("<h2>"+i+".&nbsp;<a href=\"FoodDetailServlet?no="+fvo.getNo()+"\">"+fvo.getTitle()+"&nbsp;&nbsp;<font color=orange>"+fvo.getScore()+"</font></h2>");
			out.println("</a>");
			out.println("</td>");
			
			
			out.println("<tr>");
			out.println("<td width=70%>"+fvo.getAddress().substring(0,fvo.getAddress().indexOf("지번"))+"</td>");
			out.println("</tr>");
			
			out.println("</tr>");
			out.println("</table>");
			
			i++;
		}
		
		out.println("</td>");
		out.println("</tr>");
		
		out.println("</table>");
		out.println("</div>");
		out.println("</div>");
		
		out.println("</body>");
		out.println("</html>");
		
		
		
		
		
		
	}

}
