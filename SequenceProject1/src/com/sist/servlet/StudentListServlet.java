package com.sist.servlet;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.student.dao.StudentDAO;
import com.sist.student.dao.StudentVO;

import java.util.*;
/**
 * Servlet implementation class StudentListServlet
 */
/*
 *   /list.jsp?no=10
 *   =========
 *   여기까지만 읽음@
 *   ?뒤에는 읽지 않아서 여기까지만 적어줘도 읽음!
 *
 * 	http://localhost/list.jsp?no=10
 * 	========================
 * 여기까지 URL 
 * 					=========
 * 					/list.jsp
 * 					========= URI!!! 자른게 이것과 같냐? 같으면 StudentListServlet메모리 할당 시작!!
 * 					@WebServlet ==> 어노테이션!!
 * 
 * 
 */




@WebServlet("/list.do")  // 얘를 찾아서 아래를 수행함!!!
//@WebServlet("/list.do") ==>서블릿을 이용했구나! 
public class StudentListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//String temp=request.getRequestURI();
		//System.out.println(temp); 
		///SequenceProject1/StudentListServlet 
		
		
	
		
		
		
		//보내기
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		
		
		String strPage=request.getParameter("page");
		if(strPage==null)
			strPage="1";
		
		int curpage=Integer.parseInt(strPage);
			
			
		StudentDAO dao=new StudentDAO();
		ArrayList<StudentVO> list=dao.stdAllData(curpage);
		
		
		out.println("<html>");
		out.println("<body>");
		out.println("<center>");
		out.println("<h1>성적관리</h1>");
		out.println("<table border=1 width=500>");
		out.println("<tr>");
		
		out.println("<th>번호</th>");
		out.println("<th>이름</th>");
		out.println("<th>국어</th>");
		out.println("<th>영어</th>");
		out.println("<th>수학</th>");
		out.println("<th></th>");
	
		out.println("</tr>");
		/*
		 * 화면이동
		 * HTML => a => get방식만 사용
		 * 		  form => get,post모두 사용가능
		 * java => sendRedirect() => get             ==> 파일을 바꿔주는 것!
		 * javascript => location.href => get
		 * 
		 * post방식은 form 태그를 사용해야함!
		 * 
		 * &로 두개이상의 값을 보낼수 있다...
		 * 오라클에서 &는 입력값을 받는 기호이기 때문에 &를 다른 기호로 바꿔서 가져와서 다시 바꿔서 사용!!
		 * replace!
		 */
		
		int count=dao.stdRowCount();
		count=count-((curpage*10)-10);
		//11번까지 넘어감
		// 이 코딩으로 중복이 안들어감.
		// 순차적으로 처리!
		
		
		int totalpage=(int)Math.ceil(count/10.0);
		//전체개수를 10으로 나눈다음에 10으로 나눔
		//총페이지 구하기!
		
		//10이라면..  9,8,7..로 된다.
		//실제로 보여지는 수..
		for(StudentVO vo:list)
		{
			
			out.println("<tr>");
			
			out.println("<td>"+(count--)+"</td>");
			out.println("<td>"+vo.getName()+"</td>");
			out.println("<td>"+vo.getKor()+"</td>");
			out.println("<td>"+vo.getEng()+"</td>");
			out.println("<td>"+vo.getMath()+"</td>");
			out.println("<td><a href=delete.do?hakbun="+vo.getHakbun()+">삭제</a></td>");  // a tag는 get방식만 사용할 수 있어서 아래로 못 내려감
		
			out.println("</tr>");
			
			
			
		}
		
		
		
		out.println("</table>");
		out.println("<table width=500>");
		out.println("<tr >");
		out.println("<td align=center>");
		out.println("<a href=list.do?page="+(curpage>1?curpage-1:curpage)+">이전</a>");
		
		out.println(curpage+" page / "+totalpage + " pages ");
		
		out.println("<a href=list.do?page="+(curpage<totalpage?curpage+1:curpage)+">다음</a>");
		out.println("</td>");
		out.println("</table>");
		out.println("</center>");
		out.println("</body>");
		out.println("</html>");
		
	
		
		
		
		
		
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
