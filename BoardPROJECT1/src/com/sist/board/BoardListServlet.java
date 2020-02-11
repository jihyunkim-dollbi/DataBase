package com.sist.board;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BoardListServlet
 */

/*
 * 
 * request, HttpServletResponse response
 * =======						========
 * 요청-사용자로부터 값을 받음  			응답!- 페이지 변경
 * 
 * 
 */
import com.sist.dao.*;
import java.util.*;

@WebServlet("/BoardListServlet")
public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		/*
		 * request : 사용자 요청정보
		 * response: 응답(HTML => 사용자에게 전송) 
		 * 
		 * XML, HTML => CONTENTTYPE
		 * 
		 * charset=UTF-8 =>한글꺠짐 방지
		 *
		 * 
		 * html 파일에서 초록색 글자는 모두 out.println이 생략된 것이다. 
		 * 
		 * 
		 */
		// 실행한 결과를 어떤 형태로 실행할 것인지. 어떤 브라우저로 실행할 것인가 xml? , html?
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter(); //브라우저에 데이터를 뿌리기 전에 브라우저의 어떤 위치에 뿌릴지 위치선정..
		// 어디에 뿌릴지 out이 알려줌
		// 어디에 저장할 지 알려줌
		
		out.println("<html>");
		out.println("<body>");
		out.println("<h>Hello Servlet</h1>");
		out.println("</body>");
		out.println("</html>");
		// 메모리아 ()안의 값이 저장되고 
		// 브라우저에서는 메모리에 있는 것을 불러옴.
		
		
	}

}
