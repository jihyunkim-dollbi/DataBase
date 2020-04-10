<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<link rel=stylesheet href="CSS/table.css">
</head>
<body>
<center>
<h1> 자유게시판</h1>
<table id="table_content" width=700>
<tr>
<td align=right>
<a href="BoardInsert">글쓰기</a>
</td>
</tr>
<table>
<table id="table_content" width=700>
<tr>
<th width=10%>번호</th>
<th width=45%>제목</th>
<th width=15%>이름</th>
<th width=20%>작성일</th>
<th width=10%>조회수</th>
</tr>
<tr class=dataTr>
<td width=10% align=center>12</td>
<td width=45% align=left>
<a href=BoardDetailServlet?no=12>
처음하는 서블릿 게시판</a>
</td>
<td width=15% align=center>홍길동</td>
<td width=20% align=center>2020-02-11</td>
<td width=10% align=center>1</td>
</tr>
<tr class=dataTr>
<td width=10% align=center>11</td>
<td width=45% align=left>
<a href=BoardDetailServlet?no=11>
처음하는 서블릿 게시판</a>
</td>
<td width=15% align=center>홍길동</td>
<td width=20% align=center>2020-02-11</td>
<td width=10% align=center>4</td>
</tr>
<tr class=dataTr>
<td width=10% align=center>10</td>
<td width=45% align=left>
<a href=BoardDetailServlet?no=10>
처음하는 서블릿 게시판</a>
</td>
<td width=15% align=center>홍길동</td>
<td width=20% align=center>2020-02-11</td>
<td width=10% align=center>4</td>
</tr>
<tr class=dataTr>
<td width=10% align=center>9</td>
<td width=45% align=left>
<a href=BoardDetailServlet?no=9>
처음하는 서블릿 게시판</a>
</td>
<td width=15% align=center>홍길동</td>
<td width=20% align=center>2020-02-11</td>
<td width=10% align=center>3</td>
</tr>
<tr class=dataTr>
<td width=10% align=center>8</td>
<td width=45% align=left>
<a href=BoardDetailServlet?no=8>
처음하는 서블릿 게시판</a>
</td>
<td width=15% align=center>홍길동</td>
<td width=20% align=center>2020-02-11</td>
<td width=10% align=center>4</td>
</tr>
<tr class=dataTr>
<td width=10% align=center>7</td>
<td width=45% align=left>
<a href=BoardDetailServlet?no=7>
처음하는 서블릿 게시판</a>
</td>
<td width=15% align=center>홍길동</td>
<td width=20% align=center>2020-02-11</td>
<td width=10% align=center>1</td>
</tr>
<tr class=dataTr>
<td width=10% align=center>6</td>
<td width=45% align=left>
<a href=BoardDetailServlet?no=6>
처음하는 서블릿 게시판</a>
</td>
<td width=15% align=center>홍길동</td>
<td width=20% align=center>2020-02-11</td>
<td width=10% align=center>1</td>
</tr>
<tr class=dataTr>
<td width=10% align=center>5</td>
<td width=45% align=left>
<a href=BoardDetailServlet?no=5>
처음하는 서블릿 게시판</a>
</td>
<td width=15% align=center>홍길동</td>
<td width=20% align=center>2020-02-11</td>
<td width=10% align=center>0</td>
</tr>
<tr class=dataTr>
<td width=10% align=center>4</td>
<td width=45% align=left>
<a href=BoardDetailServlet?no=4>
처음하는 서블릿 게시판</a>
</td>
<td width=15% align=center>홍길동</td>
<td width=20% align=center>2020-02-11</td>
<td width=10% align=center>2</td>
</tr>
<tr class=dataTr>
<td width=10% align=center>3</td>
<td width=45% align=left>
<a href=BoardDetailServlet?no=3>
처음하는 서블릿 게시판</a>
</td>
<td width=15% align=center>홍길동</td>
<td width=20% align=center>2020-02-11</td>
<td width=10% align=center>0</td>
</tr>
</table>
<table id="table_content" width=700>
<tr>
<td align=left>
Search:
<select>
<option>이름</option>
<option>제목</option>
<option>내용</option>
</select>
<input type=text size=15>
<input type=button value=찾기>
</td>
<td align=right>
<a href="BoardListServlet?page=1">&lt;이전&gt;</a>
1 page / 2 pages
<a href="BoardListServlet?page=2">&lt;다음&gt;</a>
</td>
</tr>
<table>
</center>
</body>
</html>