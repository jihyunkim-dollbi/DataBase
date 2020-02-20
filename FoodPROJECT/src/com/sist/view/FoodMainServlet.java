package com.sist.view;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sist.dao.*;
import java.util.*;


@WebServlet("/FoodMainServlet")
public class FoodMainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		
		out.println("<html>");
		out.println("<head>");
		out.println(" <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">");
		
		out.println("</head>");
		
		out.println("<body>");
		out.println("<div class=container>");
		
		out.println("<div class=row>");
		//for 한줄에 4개씩 출력
		
		out.println("<div class=\"col-md-3\">");
		
		
		out.println("<>");
		out.println("<>");
		out.println("<>");
		out.println("<>");
		
		out.println("</div>");
		out.println("</div>");
		out.println("</div>");

		out.println("</body>");
		out.println("</html>");
		
	}

}
