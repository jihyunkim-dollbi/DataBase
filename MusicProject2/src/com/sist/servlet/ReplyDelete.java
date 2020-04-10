package com.sist.servlet;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.dao.MusicDAO;



@WebServlet("/ReplyDelete")
public class ReplyDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//사용자 요청 데이터 받기
		
		//1. no
		String no=request.getParameter("no");
		//2. mno
		String mno=request.getParameter("mno");
		
		//DAO연결
		MusicDAO dao=new MusicDAO();
		dao.replyDelete(Integer.parseInt(no)); 
		
		
		
		//MUSICDETAIL로 이동!
		response.sendRedirect("MusicDetail?mno="+mno);
		
		
	}

}
