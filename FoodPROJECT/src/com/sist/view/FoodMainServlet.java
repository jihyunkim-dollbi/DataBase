package com.sist.view;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sist.dao.*;
import com.sist.manager.CategoryVO;

import java.io.*; // has printwriter
import java.util.*; // has arraylist



@WebServlet("/FoodMainServlet")
public class FoodMainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		
		
		FoodDAO dao=FoodDAO.newInstance();
		ArrayList<CategoryVO> list=dao.categoryAllData();
		
		
		out.println("<html>");
		out.println("<head>");
		out.println(" <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">");
		out.println("<style type=class/css>");
		out.println(".row{");
		out.println("margin: 0px auto;");
		out.println("width:1200px;");
		
		out.println("}");
		out.println("</style>");
		
		out.println("</head>");
		
		out.println("<body>");
		out.println("<div class=container>");
		out.println("<div class=row>");
		out.println("<h1>믿고 보는 맛집 리스트</h1>");
		
		
		int i=0;
		String color="";
				
		for(CategoryVO vo:list)
		{
			i=i%3;
			if(i==0)
				color="panel panel-danger";
			else if(i==1)
				color="panel panel-primary";
			else if(i==2)
				color="panel panel-success";
			//else if(i==3)
			//	color="panel panel-info";
			
			
			out.println("<div class=\"col-md-4\">");
			out.println("<div class=\""+color+"\">");
			out.println("<div class=\"panel-heading\">"+vo.getTitle()+"<br>"+vo.getSubject()+"</br></div>");
			out.println("<div class=\"panel-body\">");
			out.println("<a href=\"FoodListServlet?cno="+vo.getCateno()+"\">"); 
			out.println("<img src=\""+vo.getPoster()+"\" width=100% class=img-rounded>");
			out.println("</a>");	
			
			out.println("</div>");
			out.println("</div>");
			out.println("</div>");
			
			i++;
		}
		
		out.println("</div>");
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
		
	}

}
