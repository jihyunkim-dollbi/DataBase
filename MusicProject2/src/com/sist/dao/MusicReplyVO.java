package com.sist.dao;
// 멤버는 회원가입하지 않기 때문에 로그인을 하면 수정삭제 가능하도록 하면 된다.
// 따라서 insert


/*
 * M이름      널?       유형            
------- -------- ------------- 
NO      NOT NULL NUMBER        
MNO              NUMBER        
ID               VARCHAR2(50)  
NAME    NOT NULL VARCHAR2(100) 
MSG     NOT NULL CLOB          
REGDATE          DATE    => 자바에서 시간출력은 같으니까 TO_CHAR(regdate,'YYYY-MM-DD HH24:MI:SS') ==> 입력된 시간 가져옴! 오라클에서 가져와야함   ==>VO에 스트링 변수를 추가한다. DATE는 못받기 때문에..    
=============================
이름(날짜, 시간)		수정 삭제 댓글
=============================
댓글
==============================
=============================
이름(날짜, 시간)			댓글
=============================
댓글
==============================

 * 따라서 VO에 데이터형이 안맞으니까 데이터형에 맞는 변수를 한개더 설정해주고 TO_CHAR는 문자형 리턴형이 VARCHAR2이기때문에 이걸로 받으면 OK!
 * 공지사항 등 시간이 나올때는 변수한개더 추가!!
 * 날짜는 그대로 가져올수 있다.
 * 재사용 해야하기 때문에 기능별로 나눠있어야한다
 * 게시판 클래스, 
 * 리플라이 같은 경우 어떤 위치든 붙여사용 가능하기 때문에!!
 * 
 */
import java.util.*;

public class MusicReplyVO {

	private int no;
	private int mno;
	private String id;
	private String name;
	private String msg;
	private Date regdate;
	private String dbDay;
	
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public String getDbDay() {
		return dbDay;
	}
	public void setDbDay(String dbDay) {
		this.dbDay = dbDay;
	}
	
	
	
}
