<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*, java.util.*"%>
<!-- 

		system을 추가하면 도수창에 출력되고
		system을 삭체하면 윈도 화면에 출력된다.
		
		out.println("<li>"+vo.getEmpno()+" "
				+vo.getEname()+" "
				+vo.getJob()+" "
				+vo.getHiredate()+" "
				+vo.getSal()+" "
				+vo.getDvo().getDname()+" "
				+vo.getDvo().getLoc()+" "
				+vo.getSvo().getGrade()+"</li><br>");	


 -->
    
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.row {
margin: 0px auto;
width: 950px;
}
</style>

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<ul>

	<%
	EmpDAO dao=new EmpDAO();
	ArrayList<EmpVO> list =dao.empJoinData();
	
	
	for(EmpVO vo:list)
	{
		
		out.println("<li>"+vo.getEmpno()+" "
				+vo.getEname()+" "
				+vo.getJob()+" "
				+vo.getHiredate()+" "
				+vo.getSal()+" "
				+vo.getDvo().getDname()+" "
				+vo.getDvo().getLoc()+" "
				+vo.getSvo().getGrade()+"</li><br>");	
	
	}

	%>
	</ul>
</body>
</html>