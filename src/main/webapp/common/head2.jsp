<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en"> 
<head>
  <title>Bootstrap Case</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
 <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css"></head>
<style>a { text-decoration: none; }</style>
<%  
  String board = "";
  //boardid가 넘어와야 세션이 변경됨
  if (request.getParameter("boardid")!=null)
  session.setAttribute("boardid",	request.getParameter("boardid"));
  
  
  
  String boardid = (String)session.getAttribute("boardid");
 
  
  
  if(boardid==null) boardid="1";
/*   int id = 1;
  if (boardid!=null) id = Integer.parseInt(boardid)-1;
  */ 
  
  
  if (boardid.equals("1"))      board="공지사항";
  else if (boardid.equals("2")) board="자유게시판";
  else if (boardid.equals("3")) board="Q&A";
  else board="회원입력";
 %>
<script>
  window.onload = function() {
 var atag = document.getElementsByTagName("a");
 var url = document.location.href.split("/"); 
 

	
 if (url[url.length-2]=="member"){
	 atag[4].className +=" w3-red"; 
 }  else if (url[url.length-2]=="chartjsPro"){
	 atag[5].className +=" w3-red"; 
 }  else if (url[url.length-2]=="awesomePro"){
	 atag[6].className +=" w3-red"; 
 }  else {
	 atag[<%=boardid%>].className +=" w3-red";
 }
 else if (1==<%=session.getAttribute("boardid")%>){
	 atag[1].className +=" w3-red"; 
 } 
 else if (2==<%=session.getAttribute("boardid")%>){
	 atag[2].className +=" w3-red"; 
 }
 else if (3==<%=session.getAttribute("boardid")%>){
	 atag[3].className +=" w3-red"; 
 }
  }
</script>

<body>
<div class="w3-bar w3-green  w3-large">
  <a href="<%=request.getContextPath()%>/member/main.jsp"                  class="w3-bar-item w3-button">HOME</a>
  <a href="<%=request.getContextPath()%>/board/list.jsp?boardid=1" class="w3-bar-item w3-button">공지사항</a>
  <a href="<%=request.getContextPath()%>/board/list.jsp?boardid=2" class="w3-bar-item w3-button">자유게시판</a>
  <a href="<%=request.getContextPath()%>/board/list.jsp?boardid=3" class="w3-bar-item w3-button">Q&A</a>
  <a href="<%=request.getContextPath()%>/member/inputForm.jsp"    class="w3-bar-item w3-button">회원가입</a>
  <a href="<%=request.getContextPath()%>/chartjsPro/bar_sample.jsp"    class="w3-bar-item w3-button">BarGraph</a>
  <a href="<%=request.getContextPath()%>/awesomePro/aweSomeCloud.jsp"    class="w3-bar-item w3-button">WordCloud</a>

</div>






