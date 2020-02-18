package com.sist.servlet;

import java.io.*;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.dao.*;
// util은 필요x 리스트로 가져올것 x

/*
 * 웹 클래스 - 총 8개 
 * 1.자입력값 받기 => 리퀘스트
 * 2.화면 이동 응답  => 리스폰스
 * 3.값을 저장하고 싶다!=> 세션



	// 에러 잡는 방법 => 개발자 도구로 가서 error확인하기!!! -> 콘솔!!
 * 
 */
@WebServlet("/MusicDetail")
public class MusicDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		//화면출력
		PrintWriter out=response.getWriter(); //브라우저가 읽는 위치 => 브라우저가 알아서 읽어갈 예정!
		
		// 보내준 값이 있으면 받고 dv연동해야함!
		// musicdetaildata(int no) ==> getMNO==>값을 받고 연동 시작
		
		String mno=request.getParameter("mno");
		
		//DAO 연동
				
		MusicDAO dao= new MusicDAO();
		MusicVO vo = dao.musicDetailData(Integer.parseInt(mno)); //
		
		
		ArrayList<MusicReplyVO> list=dao.replyListDate(Integer.parseInt(mno)); //값을 가져오고 
		//값이 없는 경우는?? 
		//조건을 걸어야한다. 0보다 크면? 안크면?
		//빈공백으로 처리하면 안된다1!
		
		
		ArrayList<MusicVO> topList=dao.musicTop5();
		
		
		
		
		
		HttpSession session = request.getSession();
		out.println("<html>");
		out.println("<head>");
		out.println("<link rel=stylesheet href=\"css/bootstrap.min.css\">");
		out.println("<style type=text/css>");
		out.println(".col-md-9{"); // 화면분할 9대3으로
		out.println("margin: 100px 50px 100px 230px;");
		out.println("width: 900px;");  //9의 크기를 800으로 하겠다!!
		out.println("}");
		out.println("h1{");
		out.println("text-align:center;");
		out.println("}");
		out.println("</style>");
		
		out.println("<script type=text/javascript src=\"http://code.jquery.com/jquery.js\"></script>");
		
		out.println("<script>");
		out.println("var i=0;");
		
		/* JS
		 * 변수 의 데이터형 1개! var! ==> 자동지정변수!! 
		 * var i=0  => i(int)
		 * var i=10.5 => i(double)
		 * var i="hello" i(string)
		 * var i=[] => 배열
		 * var i={} => 객체
		 * 뒤에 무슨 값을 줄 것인지 생각해야!
		 * 
		 * jquery : library!
		 * Mybatis - 데이터베이스 연동 library!
		 * 
		 */
		
		out.println("$(function(){");
		
		out.println("$('#ubtn').click(function(){");
		
		out.println("if(i==0){");
		
		out.println("$('#ubtn').val('취소');");
		
		out.println("i=1;");
		
		out.println("}");
		
		out.println("else{");
		
		out.println("$('#ubtn').val('수정');");
		
		out.println("i=0;");
		
		out.println("}");
		
		
		out.println("});");
		
		out.println("});");
		
		out.println("</script>");
		
		
		
		out.println("</head>");
		
		out.println("<body>");
		out.println("<center>");
	//	out.println("<h1>"+session.getAttribute("name")+"님 환영합니다</h1>"); // 키를 넣어줌!
		out.println("<div class=container-fluid>"); //여백주기!
		
		/*
		 * <select>
		 * 
		 * WHERE no<10  => no&lt; 10 <  >    &nbsp;
		 * <select>
		 */
		
		
		
		
		out.println("<h1>&lt;"+vo.getTitle()+"&gt상세보기</h1>");
		out.println("<div class=col-md-9>");
		out.println("<table class=\"table table-bordered\">");
		
		
		out.println("<tr>");
		out.println("<td colspan=2 class=text-center>");
		out.println("<embed src=\"http://youtube.com/embed/"+vo.getKey()+"\" width=100% height=350>");
		out.println("</td>");
		out.println("</tr>");
		
		
		out.println("<tr>");
		out.println("<td width=10% class=text-right>");
		out.println("노래명");
		out.println("</td>");

		out.println("<td width=90% class=text-left>");
		out.println(vo.getTitle());
		out.println("</td>");
		out.println("</tr>");
		
		
		out.println("<tr>");
		out.println("<td width=10% class=text-right>");
		out.println("가수명");
		out.println("</td>");
		out.println("<td width=90% class=text-left>");
		out.println(vo.getSinger());
		out.println("</td>");
		out.println("</tr>");
		
		
		out.println("<tr>");
		out.println("<td width=10% class=text-right>");
		out.println("앨범명");
		out.println("</td>");
		out.println("<td width=90% class=text-left>");
		out.println(vo.getAlbum());
		out.println("</td>");
		out.println("</tr>");
		
		
	
		out.println("</table>");	
		
		//
		
		//
		
		
		
		out.println("<div style=\"height=20px\"></div>");
		
		//댓글 출력하는 부분
		//댓글 입력
		HttpSession session1=request.getSession() ; //session 얻기
		// request =>세션과 쿠키를 가지고 올수 있따.
		String id=(String)session.getAttribute("id"); // 세션이 저장이 안되었을때 null값이다.
		
			
		//1 - 댓글 안달린 상태
		if(list.size()<1)
		{
		out.println("<table class=\"table table-striped\">");
		out.println("<tr>");
		out.println("<td class=text-center>");
	
		out.println("<h3>댓글이 존재하지 않습니다.</h3>");
		
		out.println("</td>");
		out.println("</tr>");
		//사이즈가 0이라는 것은 메모리는 할당 되었는데 데이터가 없다.
		
		out.println("</table>");
		
		} //null은 없다 => replaylistdate()에서 vo로 메모리 할당을 했기 때문에 null은 없다.
		else
		{
		//2- 댓글 달린 상태
		out.println("<table class=\"table table-hover\">");
		//댓글이 여러개 일수 있어서 for문 사용
	
		
		for(MusicReplyVO rvo:list) // rvo위에서 vo사용하고 있다
		{
			out.println("<tr>");
			out.println("<td class=text-left>");
			
			String temp="";
			if(rvo.getSex().equals("남자"))
				temp="m2.png";
			else
				temp="w2.png";
			
			out.println("<img src=\"image/"+temp+"\" width=25 height=25>");
			out.println("&nbsp;"+rvo.getName()+"("+rvo.getDbDay()+")");
			
			out.println("</td>");
			
			out.println("<td class=text-right>");
			/*
			 * String id=null;
			 * if(id.equals("aa")) == >id가 주소가 없는데 비교를 할 수 가 없는 것!
			 * 
			 * 
			 * 
			 */
			if(rvo.getId().equals(id)) // =>값이 있는 것과 비교를 해야함!!  앞에는 값이반드시 있는 것 & 뒤에는 null값일 수도 있는 것 넣어주기!
			{
				//id가 동일하다면 수정, 삭제 버튼 주려함
				//rvo.getId()댓글 쓴 사람과 id로 로그인 한 사람이 동일한가?
				out.println("<input type=button class=\"btn btn-xs btn-primary\" value=수정  id=ubtn data="+rvo.getName()+">"); //수정버튼!
				
				out.println("<a href=\"ReplyDelete?no="+rvo.getNo()+"&mno="+mno+"\" class=\"btn btn-xs btn-danger\">삭제</a>"); // 삭제시 필요한 값 => mno 같은 페이지! & no:리플라이no
				//여러 값이 넘어갈때 & 이용!
				//replydelete는 doget으로 form 태그 외에는 모드doget
			}
			
			out.println("</td>");
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td colspan=2 class=text-left><pre style=\"white-space:pre-wrap;border:none;background:white\">"+rvo.getMsg()+"</pre></td>"); //엔터 했을때 문단처리<pre>
			out.println("</tr>");
			
			
			//수정
			out.println("<tr id=\"m"+rvo.getNo()+"\" style=\"display:none\">"); // tr tag를 감춰놓음 =>수정 버튼을 눌렀을때 보여줄 예정!! 이미 창은 만들어놓음!
			out.println("<td colspan=2>");
			out.println("<textarea rows=3 cols=80 style=\"float:left\" required name=msg>"+rvo.getMsg()+"</textarea>"); //msg가 넘어감.
			out.println("<input type=hidden value="+mno+" name=mno>");   //히든으로 뮤직번호가 넘어감.  ==> 세션에서 넘어가는 값 외에 다른 것들을 생각해야함!
			out.println("<input type=submit value=댓글수정 style=\"height:70px;float:left\" class=\"btn  btn-primary\">");
			
			out.println("</td>");
			out.println("</tr>");
			
		}
		
		
		//
		
		
		out.println("</table>");
		
	}
	
		
		//저장:id값
		//저장안된 경우:null값 => 로그인이 안된 상태(null), Login된 상태 id등록됨!
		//메뉴 여부 부여주기, 로그인된 창 보여주기!
		if(id != null)
		{
		
		//로그인경우 댓글창에 적어서
		out.println("<form method=post action=\"ReplyInsert\">"); //replyinsert를 만들어서 바로 전송하려함
			// post로 보냈기 때문에 post로 받기!!
			
			
		//null 일 경우 댓글창이 안보인다!
			
		out.println("<table class=\"table table-striped\">");
		out.println("<tr>");
		out.println("<td>");
		out.println("<textarea rows=3 cols=80 style=\"float:left\" required name=msg></textarea>"); //msg가 넘어감.
		out.println("<input type=hidden value="+mno+" name=mno>");   //히든으로 뮤직번호가 넘어감.  ==> 세션에서 넘어가는 값 외에 다른 것들을 생각해야함!
		out.println("<input type=submit value=댓글쓰기 style=\"height:70px;float:left\" class=\"btn  btn-primary\">");
		/*
		 * float:left => 화면 왼쪽으로 붙는다.
		 * 
		 * 
		 */
		
		out.println("</td>");
		out.println("</tr>");
		
		
		out.println("<div style=\"height=20px\"></div>");
		
		out.println("<div>");
		out.println("<tr>");
		out.println("<td colspan=2 class=text-right>");
		out.println("<a href=\"MusicList\" class=\"btn btn=lg btn-success\">목록</a>"); //목록으로 이동
		out.println("</td>");
		out.println("</tr>");
		out.println("</div>");
		
		
		out.println("</table>");
		out.println("</form>");
		
		}
		
		
		
		out.println("</div>");
		
		//5개 인기순 출력하기 - 디테일 오른쪽에!!
		out.println("<div class=col-md-3>");
		out.println("<table class=\"table table-striped\">");
		
		out.println("<caption>인기순위 Top 5</caption>"); // 테이블제목
		
		for(MusicVO tvo:topList)
		{
			
			out.println("<tr>");
			
			out.println("<td>"+tvo.getRank()+"</td>");
			out.println("<td>");
			out.println("<img src=\""+tvo.getPoster()+"\" width=35 height=35>");
			out.println("</td>");
			
			out.println("<td>"+tvo.getTitle()+"</td>");
					
			out.println("</tr>");
			
		}
			
		out.println("</table>");
		
		out.println("</div>");
		out.println("</div>");
		out.println("</center>");
		out.println("</body>");
		out.println("</html>");
		
		
		
		
		
		
		//연동후  값을 받아서 화면 출력!
		
		
		
		
		
	}

}
