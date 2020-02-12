package com.sist.music;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
// 사용자 입력값이 없으므로 dopost필요x
import java.util.*;
import com.sist.dao.*;

@WebServlet("/MusicListServlet")
public class MusicListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		//데이터읽기
		//사용자 요청한 페이지 받기
		//MusicListServlet?page=1
		
		
		
		String strPage=request.getParameter("page");
		if(strPage==null)
			strPage="1";
		
		int curpage=Integer.parseInt(strPage);
		
		MusicDAO dao=new MusicDAO();
		ArrayList<MusicVO> list=dao.musicListData(curpage);
		
		int totalpage=dao.musicTotalPage();
		
		//출력
		out.println("<html>");
		out.println("<head>");
		out.println("<link rel=stylesheet href=\"CSS/table.css\">");
		out.println("<script type=text/javascript src=\"http://code.jquery.com/jquery.js\"></script>"); //라이브러리 가져옴!!
		out.println("<script type=text/javascript>");
		out.println("$(function(){");
		out.println("$('#keyword').keyup(function(){");// 입력란에 키를 놓았다
		out.println("var k=$(this).val();"); //입력한 값을 가져와
		out.println("$('.user_table > tbody > tr').hide();");
		out.println("var temp=$('.user_table > tbody > tr > td:nth-child(5n+4):contains('+k+')')"); // 곡이 4번째 콜럼에 있기때문에 4번째 줄에 있는 것만 ㅊ찾도록 만듬
		out.println("$(temp).parent().show();");		
		out.println("});");
		out.println("});");
		out.println("</script>");		
		out.println("</head>");
		
		out.println("<body>");
		out.println("<center>");
		out.println("<h1 style=\"text-align:center\">뮤직 Top 200</h1>");
		
		// [테이블1]
		out.println("<table id=table_content width=800>");
		out.println("<tr>");
		out.println("<td align=left>");
		out.println("Search:<input type=text id=keyword size=20>");
		out.println("</td>");
		out.println("</tr>");
		out.println("</table>");
		
		// [테이블2]
		out.println("<table id=table_content width=800 class=user_table>");
		out.println("<thead>");	
		out.println("<tr>");
		out.println("<th width=10%>순위</th>");
		out.println("<th width=10%>등폭</th>");
		out.println("<th width=15%></th>");
		out.println("<th width=45%>곡명</th>");
		out.println("<th width=20%>가수</th>");
		out.println("</tr>");
		out.println("</thead>");
		out.println("<tbody>");
		
		for(MusicVO vo:list)
		{
			
			String temp="";
			if(vo.getState().equals("상승"))
			{
				temp="<font color=red>▲</font>&nbsp;"+vo.getIdCliment();
				
			}else if(vo.getState().equals("하강"))
			{
				temp="<font color=blue>▼</font>&nbsp;"+vo.getIdCliment();
				
			}else
			{
				temp="<font color=gray>-</font>";
				
			}
			
			out.println("<tr>");
			out.println("<td width=10% align=center>"+vo.getRank()+"</td>");
			out.println("<td width=10% align=center>"+temp+"</td>");
			out.println("<td width=15% align=center><img src="+vo.getPoster()+" width=35 height=35></td>");
			out.println("<td width=45%>"+vo.getTitle()+"</td>");
			out.println("<td width=20%>"+vo.getSinger()+"</td>");
			out.println("</tr>");
			
			
		}
		out.println("</tbody>");
		out.println("</table>");
		out.println("</center>");
		out.println("</body>");
		out.println("</html>");
		
		
		
	}

}




