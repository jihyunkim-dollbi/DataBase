package com.sist.servlet;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.dao.*;

@WebServlet("/ReplyInsert")
public class ReplyInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try
		{
			request.setCharacterEncoding("UTF-8");
			
			
		}catch(Exception ex)
		{}
		
		//사용자가 보낸 데이터 받기
		String mno=request.getParameter("mno");  // => name=mno =>name으로 주어진 값을 가져옴. => input은 반드시 name필요
		String msg=request.getParameter("msg");
		
		
		//ex)
		//session.setattribute("hap",100) =>int => (integer)로!  => 100이 형변환 필요한 것임!
		
		//String=> id name 받기 (String)session
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id"); //로그인된 상태에서만 받기 때문에 id를 검열할 필요x
		String name=(String)session.getAttribute("name");
		//session 리턴형은 object이기 때문에 형변환 필요
		
		
		
		
		
		
		// DAO로 전송
		
		MusicReplyVO vo=new MusicReplyVO();
		vo.setMno(Integer.parseInt(mno));
		vo.setMsg(msg);
		vo.setId(id);
		vo.setName(name);
		
		MusicDAO dao=new MusicDAO();

		//dao에서 메소드 만들고 와서 replyInsert에 dao값 넣어주기!!
		dao.replyInsert(vo);
		
				
		
		//이동 => MusicDetails로 이동
		response.sendRedirect("MusicDetail?mno="+mno);	 // 
			
			
			
		
		
	}

}
