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
 * http://localhost/MoiveProject/NeswServlet
 * 					==========================URI
 * ===========================================URL
 * 
 * 
 *    
 *   request 안에 1. 사용자정보(IP, PORT...어느컴퓨터에서 어느폴더(페이지)로 접근했는지// 따라서 아파치 안에LOG 파일이 많음!), 2.사용자 요청값!
 *   ==> 우리가 값을 받을 수 있다!
 *   
 *   톰캣 역할=>servlet, jsp파일을 컴파일하여 html만 출력되게함!(request안에 모든 정보(사용자가 요청한 값!!)가 묶여서 톰캣으로 이동됨!) 
 *   웹서버 =>html, xml이면 자동 처리해주지만
 *   		servlet, jsp는 컴파일해야하기 때문에!=>톰캣이 번역을 해줌 컴파일해줌!컴파일하여 html만 출력하도록함 => out.println() => <html만 읽음 >=>getwriter() => buffer로 읽어서 화면에 출력!!
 *   
 *   서버개발자 -LOG파일분석!!
 *   response => 사용자가 보도록 해줌.
 *   session은 우리가 만들어야함!!
 *   
 *   
 * 	 JSP는 doget dopost의 메소드 영역 안에 있다. 따라서 모든 소스가 servlet의 doget으로 들어가게 되어 모든 메소드를 사용할 수 있다.
 * 	 jspservice안에 doget, dopost가 모두 포함됨.	 
 *   ** 유일하게 선언되지 않은 것은 내장객체가 아닌것은 쿠키이다.
 *   
 * 	 spring(공부전에 XML과 어노테이션 선행학습필요!)-> annotation기반!(클래스를 찾을 수 있도록 별칭을 부여하여..)으로 클래스 자동찾음!
 * 		==> annotation -> 클래스를 찾을 수 있도록 인덱스를 줘서 자동으로 찾을수 있도록 만듬
 * 		==> xml -> 만들어 놓은 클래스를 xml등록을 하여 사용?,,,,,
 * 		
 *   @WebServlet("/NewsServlet") =>	case 3:  sname="NewsServlet";

=>@Target(value={TYPE})  ==> type-> class찾음, method-> method찾음, parameter=> 매개변수 찾음
=>@Retention(value=RUNTIME)
=>@Documented
 * 	
 */

@WebServlet("/NewsServlet")
public class NewsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//한글변환
		response.setContentType("text/html;charset=UTF-8");
		//사용자읽음
		PrintWriter out=response.getWriter();

		//요청값 받기 - 페이지 받기 -사용자가 보내준값!
		String strPage=request.getParameter("page");
				
		if(strPage==null)
			strPage="1";
				
		/*
		 *  request => 값 받는 두가지 방식
		 *    1. getParameter() -->낱게씩 받을때 리턴형-> string
		 *    2. gerParameterValues() --> 한번에 여럿 받을때 -> string[] => 체크박스 => 6개중 선택된 3개값을 배열로 받음!! 
		 *  
		 *  화면이동시 response!
		 *  get방식-> doget호출
		 *  post방식->dopost호출!
		 * 
		 */
		
		
		
		//strPage를 int로 변환하여 현재페이지 설정!
		int curpage=Integer.parseInt(strPage);
		
		//DAO연결 
		MovieDAO dao=MovieDAO.newInstance();// 시글톤 생성!
		
		ArrayList<NewsVO> list=dao.newsListData(curpage); // curpage를 넘김
		int totalpage=dao.newsTotalPage();
		
		
		/*
		 * <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
		 *  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
		 * 
		 * 
		 * 
		 */
		
		
		out.println("<html>");
		out.println("<head>");
		
		/*
		out.println("<meta charset=\"utf-8\">");
		out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
		out.println(" <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">");
		out.println("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js\"></script>");
		out.println("<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js\"></script>");
		*/
		
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
		out.println("<table class=\"table table-condensed\">");
		out.println("<tr>");
		out.println("<td>");
		
		for(NewsVO vo:list)
		{
			out.println("<table class=\"table table-hover\">");
			out.println("<tr>");
			
			out.println("<td width=30% class=text-center rowspan=3>");
			out.println("<img src="+vo.getPoster()+" width=100%>");
			out.println("</td>");
			
			out.println("<td class=text-center width=70%><b><a href="+vo.getLink()+">"+vo.getTitle()+"</a></b></td>");
			out.println("</tr>");
			
			out.println("<tr class=\"success\">");
			out.println("<td width=70%>"+vo.getContent()+"</td>");
			out.println("</tr>");
			
			
			out.println("<tr class=\"info\">");
			out.println("<td width=70%>"+vo.getAuthor()+"＊"+vo.getRegdate()+"</td>");
			out.println("</tr>");
			
		
			out.println("</table>");
			
		}
		
		out.println("</td>");
		out.println("</tr>");
		out.println("</table>");
		out.println("</div>");
		
		
		//페이지출력
        out.println("<div class=\"row text-center\">");
        out.println("<ul class=\"pagination pagination-lg\">");
        
        for(int i=1;i<=totalpage;i++)
        {
        	out.println("<li><a href=\"MainServlet?mode=3&page="+i+"\">"+i+"</a></li>");
        }								
		
		out.println("</ul>");
		out.println("</div>");
		
		
		out.println("</body>");
		out.println("</html>");
		
		
	}

}
