package com.sist.view;

import java.io.*;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;
import com.sist.dao.*;
import com.sist.manager.*;

/*
 * doget 화면출력
 * dopost 데이터전송
 * service = doget+ dopost
 * 
 * MVC =>SERVICE사용!!
 * 
 * 
 */


@WebServlet("/FoodDetailServlet")
public class FoodDetailServlet extends HttpServlet { 
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 상세보기와 지역맛집리스트 출력 예정!!
		
		response.setContentType("text/html;charset=UTF-8"); 
		// => 브라우저에 text type을 html로 보내겠다 !! 알려줌!!
		// xml은 문서 자체를 보여줌! => 데이터 보낼때 xml로 해야한다.
		// default=>text/html;charset=ISO-8859
		// 따라서 다시 설정해줘야함!
		// 여러 언어를 한 페이지에 출력해야할때는?
		
		// 브라우저가 읽어갈 위치 지정!(메모리이용!) => TCP로 해야HTML를 제대로 읽음! ==>OUT에 저장(메모리)
		// 한줄씩 보내주면 UDP로! => 받은 것과 상관없이 계속 보내기만 함!
		PrintWriter out=response.getWriter(); //=> autoflush!!
		// 여기에 저장하면 해당 사이트로 넘어감!
		// 다 읽어가면 refresh됨!! => flush기능
		// page 103, out이 버퍼! 버퍼=임시기억장소!!
		// 소스미리보기하면 버퍼를 읽을 수 있다? =>버퍼는 한줄씩 읽어나간다. 
		// page 56
		
		
		
		// 출력하기 전에 자바가 데이터를 가지고 온 다음에 시작해야한다! 사용자가 보내준 값을 받고 시작하기.
		// 따라서 데이터 연동작업이 선행되어야한다. getParameter()!!
		// 사용자 요청값에 해당하는 데이터를 가져와 출력하기!!
		// 1. 사용자 요청값을 받고 => 2. db에서 요청값에 해당하는 데이터를 가져오고 => 3. db에서 선택된 데이터를 출력!
		/*
		 * <MVC> 
		 * 1. C 컨트롤러- 데이터 받는 아이 ===> 2. M-받은 값에 대한 처리 => 3. V- 처리 된것 출력(화면  UI)
		 	
		 	자바를 쓰는 이유, 재사용, 확장, 어떤 운영체재도 동일하게 사용가능(플랫폼의 동일성 -이식성이 좋다!) => 어디에서 컴파일하든 2진파일로 실행됨!
		 	운영체재=> 우분투?, 리눅스, 윈도우........................................................................... 	
		 */
		 
		
		//1. 요청값 받기
		
		
			
		String no=request.getParameter("no");
		//2. 요청에 해당하는 DAO
		FoodDAO dao=FoodDAO.newInstance();
		FoodHouseVO vo=dao.foodDetailData(Integer.parseInt(no));
		
		//String target = "지번";
		String temp=vo.getAddress();
		
		String temp2=temp.substring(temp.lastIndexOf("지번")+1);
		String[] array = temp2.split(" ");
		
		
		
	   // temp=temp.substring(temp.lastIndexOf("구")+1,temp.lastIndexOf("동")+1);	
		
		
		
		
		/*
		 
		if(temp.substring(temp.lastIndexOf("구")+2,temp.lastIndexOf("동")+1)=="동"){
		
		   temp=temp.substring(temp.lastIndexOf("구")+1,temp.lastIndexOf("동")+1);
		}
		else if(temp.substring(temp.lastIndexOf("구")+3,temp.lastIndexOf("가")+1)=="가"){
			
		   temp=temp.substring(temp.lastIndexOf("구")+1,temp.lastIndexOf("가")+1);
		   
		}else if(temp.substring(temp.lastIndexOf("구")+3,temp.lastIndexOf("동")+1)=="동"){
			
			 temp=temp.substring(temp.lastIndexOf("구")+1,temp.lastIndexOf("가")+1);
			 
		}else if(temp.substring(temp.lastIndexOf("구")+4,temp.lastIndexOf("동")+1)=="동"){
			
			 temp=temp.substring(temp.lastIndexOf("구")+1,temp.lastIndexOf("가")+1);
		}
		
		*/
		
		//temp=temp.substring(temp.lastIndexOf("구")+1,temp.lastIndexOf("동")+1);	
		
			
	//	StringTokenizer tokens=new StringTokenizer(temp, " ");
		
	//	String thirdTokens=tokens.nextToken("구");
		
		
		
	//	System.out.println(tokens);
		
		
		
		
		
		ArrayList<FoodHouseVO> list=dao.foodLocationData(array[3]);
		//3. HTML 출력!!
		/*
		 * 예약: 병원, 극장, 항공권, 버스, 기차, 
		 * 추천  => AI
		 * 장바구니
		 * 스트리밍
		 * ERP - 급여관리, 퇴직금계산.. 
		 * 게시판
		 * 회원가입
		 * 
		 */
		
		out.println("<html>");
		out.println("<head>");
		out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">");
		out.println("<style type=text/css>");
		out.println(".row{");
		out.println("margin:0px auto;");
		out.println("width:1200px;");
		
		out.println("}");
		out.println("</style>");
		
		out.println("</head>");
		
		out.println("<body>");
		out.println("<div class=container>");
		out.println("<div class=row>");
		
		out.println("<div class=col-md-8>"); //col-md-8인라인 속성으로 바꿔줌! 원래는 블록 속성!!
		out.println("<table class=\"table\">");
		
		
		out.println("<tr>");
		
		//그림 5개 자르기
		StringTokenizer st=new StringTokenizer(vo.getImage(),"^");
		while(st.hasMoreTokens()) //있는 개수만큼 돌아감
		{
			out.println("<td>");
			out.println("<img src=\""+st.nextToken()+"\" width=100%>");
			out.println("</td>");
		}
		
		out.println("</tr>");
		out.println("</table>");	
		
		
		//제목 => 상세
		out.println("<table class=\"table\">");
		
		out.println("<tr>");
		
		out.println("<td colspan=2>"); //한줄에 출력됨
		out.println("<h2>"+vo.getTitle()+"&nbsp;<font color=orange>"+vo.getScore()+"</font></h2>");
		out.println("</td>");
		out.println("</tr>");
		
		
		
		out.println("<tr>");
		out.println("<td width=15% class=text-right>주소</td>");
		out.println("<td width=85% class=text-left>");
		out.println(vo.getAddress());
		out.println("</td>");
		out.println("</tr>");
		
		
		out.println("<tr>");
		out.println("<td width=15% class=text-right>전화번호</td>");
		out.println("<td width=85% class=text-left>");
		out.println(vo.getTel());
		out.println("</td>");
		out.println("</tr>");
		
		
		out.println("<tr>");
		out.println("<td width=15% class=text-right>음식종류</td>");
		out.println("<td width=85% class=text-left>");
		out.println(vo.getType());
		out.println("</td>");
		out.println("</tr>");
		
		
		out.println("<tr>");
		out.println("<td width=15% class=text-right>가격대</td>");
		out.println("<td width=85% class=text-left>");
		out.println(vo.getPrice());
		out.println("</td>");
		out.println("</tr>");
		
		
		out.println("<tr>");
		out.println("<td colspan=2 class=text-center>");
		out.println("좋아요("+vo.getGood()+")|");
		out.println("괜찮다("+vo.getSoso()+")|");
		out.println("별로("+vo.getBad()+")");
		out.println("</td>");
		out.println("</tr>");
		
		
		
		out.println("<tr>");
		out.println("<td colspan=2 class=text-right>");
		out.println("<a href=\"#\" class=\"btn btn-xs btn-danger\">찜</a>");
		out.println("<a href=\"#\" class=\"btn btn-xs btn-success\">예약</a>");
		out.println("<a href=\"javascript:history.back()\" class=\"btn btn-xs btn-primary\">목록</a>");
		out.println("</td>");
		out.println("</tr>");
		

		
		out.println("</table>");
		out.println("</div>");
		
		
		
		
		//오른쪽 div!주변 맛집 리스트!
		
		out.println("<div class=col-md-4>");
		out.println("<table class\"table\">");
		out.println("<caption><h3>주변 인기 식당</h3></caption>");
		out.println("<tr>");
		out.println("<td>");
		
		for(FoodHouseVO fvo:list)
		{
			out.println("<table class\"table\">");
			out.println("<tr>");
			out.println("<td class=text-center width=30% rowspan=4>");
			out.println("<img src="+fvo.getImage().substring(0, fvo.getImage().indexOf("^"))+" width=100%>");
			out.println("</td>");
			
			
			out.println("<td width=70%>");
			out.println(fvo.getTitle()+"&nbsp;"+fvo.getScore());
			out.println("</td>");
			out.println("</tr>");
			
			
			out.println("<tr>");
			out.println("<td width=70%>");
			out.println("음식종류:"+fvo.getType());
			out.println("</td>");
			out.println("</tr>");
			
			
			out.println("<tr>");
			out.println("<td width=70%>");
			out.println("전화번호:"+fvo.getTel());
			out.println("</td>");
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td width=70%>");
			out.println("가격대:"+fvo.getPrice());
			out.println("</td>");
			out.println("</tr>");
			
			
			out.println("</tr>");
			out.println("</table>");
			
		}
		
		out.println("</td>");
		out.println("</tr>");
		out.println("</table>");
		
		
		out.println("</div>");
		out.println("</div>");
		out.println("</div>");
		
		out.println("</body>");
		out.println("</html>");
		
		
		
		
		
		
	}

}
